pipeline {
    agent any
//    parameters {
//        string(name: 'DEPLOY_COMMIT_ID', description: 'The git commit you want to deploy', defaultValue: '')
//    }
//     triggers {pollSCM('* * * * *')}
    stages {
//        stage('Verify Branch - always run') {
//            steps {
//                // View variables here: http://localhost:8080/env-vars.html/
//                // Note: Branch name is used for multi-branch project.
//                echo "Hello World GitBranch: $GIT_BRANCH"
//            }
//        }
//        stage('Docker Build on Main branch only') {
//            when {
//                branch 'main'
//            }
//            steps {
//                bat(script: 'docker images -a')
//            }
//        }
        stage('Show branch info') {
            steps {
                echo "Branch: ${env.BRANCH_NAME}"
                echo "Git Commit: ${env.GIT_COMMIT}"
                echo "Tag: ${env.TAG_NAME}"

                script {
                    // To make this commandline works, we need to fetch tag when cloning git repo:
                    // https://stackoverflow.com/questions/48292080/jenkins-multibranch-reference-git-repos-tag-from-pipeline-file-jenkinsfile
                    stdout = bat(returnStdout: true, script: "git tag --points-at=HEAD").trim()
                    env.TAG_CURRENT_BRANCH = stdout.readLines().drop(1).join(" ")
                    // The reason we have to do that is because with bat, it also returns the execution command line, not just result.
                    // Please view more in https://issues.jenkins.io/browse/JENKINS-44569
                    // Note: we don't get that problem with sh
                }
                echo "TAG_CURRENT_BRANCH: ${env.TAG_CURRENT_BRANCH}"
                echo "TAG_CURRENT_BRANCH: ${stdout}"
            }
        }
        stage('Check has tag') {
            when {
                // tag "release-*"
                expression { env.TAG_CURRENT_BRANCH && !env.TAG_CURRENT_BRANCH.allWhitespace}
            }
            steps {
                echo "Has tag ${env.TAG_CURRENT_BRANCH}"
            }
        }
        stage('Deploy if get approval and has tag') {
            when {
                allOf {
                    branch 'main'
                    //tag "release-*"
                    expression { env.TAG_CURRENT_BRANCH && !env.TAG_CURRENT_BRANCH.allWhitespace}
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