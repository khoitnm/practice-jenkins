pipeline {
    // agent { docker { image 'maven:3.3.3' } }
    agent any
//     triggers {pollSCM('* * * * *')}
    stages {
//        stage('build') {
//            steps {
//                sh 'mvn --version'
//            }
//        }

        stage('hello') {
            steps {
                // View variables here: http://localhost:8080/env-vars.html/
                echo "Hello World GitBranch: $GIT_BRANCH & BranchName: $BRANCH_NAME"
            }
        }
    }
}