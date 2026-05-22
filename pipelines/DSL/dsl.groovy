pipelineJob('AWS/PROD/infra-1') {
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
            scriptPath('pipelines/DSL/Jenkinsfile')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
pipelineJob('AWS/STAGE/infra-1') {
    description('Terraform + Infracost pipeline for infracost-aws-demo')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/rafstef/infracost-aws-demo.git')
                    }
                    branch('*/stage')
                    extensions {
                        cleanBeforeCheckout()
                    }
                }
            }
            scriptPath('pipelines/DSL/Jenkinsfile')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
pipelineJob('AWS/DEV/infra-1') {
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
            scriptPath('pipelines/DSL/Jenkinsfile')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
