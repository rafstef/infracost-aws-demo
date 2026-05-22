pipelineJob('AWS/PROD/deploy-infra-1') {
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
pipelineJob('AWS/STAGE/deploy-infra-1') {
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
            scriptPath('Jenkinsfile')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
pipelineJob('AWS/DEV/deploy-infra-1') {
    description('Terraform + Infracost pipeline for infracost-aws-demo')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/rafstef/infracost-aws-demo.git')
                    }
                    branch('*/develop')
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


pipelineJob('AWS/PROD/destroy-infra-1') {
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
            scriptPath('destroy.groovy')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
pipelineJob('AWS/STAGE/destroy-infra-1') {
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
            scriptPath('destroy.groovy')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}
pipelineJob('AWS/DEV/destroy-infra-1') {
    description('Terraform + Infracost pipeline for infracost-aws-demo')

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/rafstef/infracost-aws-demo.git')
                    }
                    branch('*/develop')
                    extensions {
                        cleanBeforeCheckout()
                    }
                }
            }
            scriptPath('destroy.groovy')
            lightweight(true)
        }
    }

    properties {
        disableConcurrentBuilds()
    }
}