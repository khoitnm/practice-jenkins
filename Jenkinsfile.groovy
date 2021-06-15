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
                echo 'Hello World'
            }
        }
    }
}