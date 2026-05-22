pipelineJob('AWS/infracost-aws-demo') {
    description('Terraform + Infracost pipeline for infracost-aws-demo')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/rafstef/infracost-aws-demo.git')
                    }
                    branch('*/master')
                    extensions {
                        cleanBeforeCheckout()
                    }
                }
            }
            scriptPath('Jenkinsfile')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
