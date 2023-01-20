pipeline {
    agent {
        label 'ecsnode'
    }
    stages {
        stage('git-pull') {
            steps {
                sh 'sudo apt-get update -y'
                sh 'sudo apt-get install git -y'
                git 'https://github.com/shubhamg21g/student-ui.git'
           }
        }
        stage(maven-build) {
            steps {
                sh 'sudo apt-get update -y'
                sh 'sudo apt-get install maven -y'
                sh 'mvn clean package'
            }
        }
        stage(push-artifact) {
            steps {
                sh 'wget "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
                    unzip awscliv2.zip
                    sudo ./aws/install'
            }
        }
        stage(deploy) {
            steps {
                echo 'git deploy'
            }
        }
     }            
}