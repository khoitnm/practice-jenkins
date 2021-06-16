pipeline {
    agent any
//    parameters {
//        string(name: 'DEPLOY_COMMIT_ID', description: 'The git commit you want to deploy', defaultValue: '')
//    }
//     triggers {pollSCM('* * * * *')}
    stages {
        stage('Verify Branch - always run') {
            steps {
                // View variables here: http://localhost:8080/env-vars.html/
                // Note: Branch name is used for multi-branch project.
                echo "Hello World GitBranch: $GIT_BRANCH"
            }
        }
        stage('Docker Build on Main branch only') {
            when {
                branch 'main'
            }
            steps {
                bat(script: 'docker images -a')
            }
        }
        stage('Show tag') {
            steps {
                echo "Tag name $TAG_NAME"
            }
        }
        stage('Check has tag') {
            when {
                tag "release-*"
            }
            steps {
                echo "has tag $TAG_NAME"
            }
        }
        stage('Deploy if get approval and has tag') {
            when {
                allOf {
                    branch 'main'
                    tag "release-*"
                }
            }
            // Input: https://www.jenkins.io/doc/book/pipeline/syntax/#input
            input {
                message "Should we continue?"
                ok "Yes, we should."
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who are approving this deployment?')
                }
            }
            options {
                timeout(time: 10, unit: 'SECONDS')
            }
            steps {
                echo "OK ${PERSON}, we'll deploying the app with your approval."
            }
        }
        stage('Verify Deployment success') {
            steps {
                echo "Verify Deployment is already successful."
            }
        }
    }
}