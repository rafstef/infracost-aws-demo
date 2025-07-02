#!/usr/bin/env groovy

def askUserInput(String message,String multipleChoise,String defaultChoice,int time) {
    def userInput = defaultChoice
    try{
        timeout(time: time, unit: 'SECONDS') {
        userInput = input message: message, ok: 'OK', parameters: [choice(name: 'USERINPUT', choices: multipleChoise, defaultChoice: defaultChoice, description: message)] }
    } catch (err) {
        defaultChoice
    }
    echo "User select : ${userInput}"
    userInput
}

def getEnvName(branchName) {
    if("origin/develop".equals(branchName)) {
        return "dev";
    } else if ("origin/master".equals(branchName)) {
        return "prod";
    } else if ("origin/release".equals(branchName)) {
        return "stage";
    }
}

pipeline {
    agent any
    environment {
        TERRAFORM_PLAN_FILE="DEMO"
        ENV_NAME = getEnvName(env.GIT_BRANCH)
    }
    tools {
        terraform 'terraform'
    }
    stages{
        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
        stage ("Setup Deployment Environment") {
            steps {
                script {
                    echo "branch name: ${env.GIT_BRANCH}"
                    echo "env name: ${env.ENV_NAME.toLowerCase()}"
                }
            }
        }

        stage('TF Init and validations checks') {
            steps {
                sh "terraform version"
                sh "echo env name ${env.ENV_NAME}"
                sh "terraform init -backend-config=bucket=infratest-demo-${env.ENV_NAME}-tfstate -no-color -reconfigure"
                sh "terraform validate -no-color"
            }
        }
        stage('TF Plan creation') {
            steps {
                sh "terraform version"
                sh "terraform init -backend-config=bucket=infratest-demo-${env.ENV_NAME}-tfstate -no-color -reconfigure"
                sh "terraform plan -destroy -no-color -input=false -var-file=envvars/${env.ENV_NAME}.tfvars -out terraform-destroy-plan-${env_name}.plan"
            }
        }
        stage('TF Destroy') {
            steps {
                script{
                    approve_plan=askUserInput("Apply Terraform destroy plan?","NO\nYES","NO",300)
                    if( approve_plan == "YES"){
                        sh "terraform version"
                        sh "terraform init -backend-config=bucket=infratest-demo-${env.ENV_NAME}-tfstate -no-color -reconfigure"
                        sh "terraform apply -destroy -no-color -input=false terraform-destroy-plan-${env_name}.plan"
                    }else{
                        echo "TF destroy plan not approved. Skip Apply . . . "
                    }
                }
            }
        }
    }
    post {
        always {
            cleanWs(cleanWhenNotBuilt: false,
            deleteDirs: true,
            disableDeferredWipeout: true,
            notFailBuild: true,
            patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
            [pattern: '.propsfile', type: 'EXCLUDE']])
        }
    }
}