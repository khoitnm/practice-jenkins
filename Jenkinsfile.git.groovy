def GIT_CREDENTIALS_ID = 'global_credentials_ssh'

pipeline {
    agent any
    parameters {
        string(name: 'DEPLOY_COMMIT_ID', description: 'The Commit ID that we want to deploy', defaultValue: '')
    }
    stage('Checkout associated commit') {
        steps {
            checkout scm
            script {
                sh "git checkout ${DEPLOY_COMMIT_ID}"
            }
        }
    }
}