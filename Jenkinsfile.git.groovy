def GIT_CREDENTIALS_ID = 'global_credentials_ssh'
def LATEST_TAG = "deploy-latest"

pipeline {
    agent any
    parameters {
        string(name: 'DEPLOY_COMMIT_ID', defaultValue: '', description: 'The Commit ID that we want to deploy')
    }
    stages {
        stage('Checkout associated commit') {
            steps {
                echo "DEPLOY_COMMIT_ID: ${params.DEPLOY_COMMIT_ID}"
                checkout scm
                script {
//                    sh "git checkout ${params.DEPLOY_COMMIT_ID}"
                    bat "git checkout ${params.DEPLOY_COMMIT_ID}"
                }
            }
        }
        stage('Update latest tag') {
            steps {
//                sshagent(credentials: [GIT_CREDENTIALS_ID]) {
//                    bat "git tag --force ${LATEST_TAG}"
//                    bat "git push origin --delete ${LATEST_TAG}"
//                    bat "git push origin ${LATEST_TAG}"
//                }
                script {
                    existingTag = bat(returnStdout: true, script: "git tag --force ${LATEST_TAG}").trim()
                    if (existingTag) {
                        bat "git push origin --delete ${LATEST_TAG}"
                    }
                    bat "git push origin ${LATEST_TAG}"
                }
            }
        }
    }
}