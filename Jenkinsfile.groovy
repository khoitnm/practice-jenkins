pipeline {
    // agent { docker { image 'maven:3.3.3' } }
    agent any
//     triggers {pollSCM('* * * * *')}
    stages {
        stage('Verify Branch') {
            steps {
                // View variables here: http://localhost:8080/env-vars.html/
                // Note: Branch name is used for multi-branch project.
                echo "Hello World GitBranch: $GIT_BRANCH"
            }
        }
        stage('Docker Build') {
            steps {
                pwsh(script: 'docker images -a')
            }
        }
    }
}