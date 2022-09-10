import com.gfk.jenkins.components.Configuration
import com.gfk.jenkins.components.DockerBuildImage

import javax.swing.JLabel


def call() {
    def environmentVariables = new Configuration(this)
    pipeline {
        agent {
            label "docker-php"
        }
        options {
            buildDiscarder(logRotator(numToKeepStr: '25'))
            timeout(time: 10, unit: 'MINUTES')
            ansiColor('xterm')
            timestamps()
        }
        environment {
            BRANCH_NAME = "${BRANCH_NAME}"
        }
        stages {
            stage("env"){
                steps {
                    script {
                        environmentVariables.injectVars()
                    }
                }
            }
            stage ('build and push image') {
                steps {
                    script {
                        sh "ls"
                        sh 'composer update'
                    }
                }
            }
        } //stages
        post {
            // Always runs. And it runs before any of the other post conditions.
            always {
                // Let's wipe out the workspace before we finish!
                //deleteDir()
                echo "clean"
            }
        }
    }
}
