pipeline {
    agent {
        label('ecsnode')
    }
    stages {
        stage('git-pull') {
            steps {
                sh 'sudo apt install git -y'
                git credentialsId: 'firstgit', url: 'git@github.com:shubhamg21g/student-ui.git'
           }
        }
        stage(maven-build) {
            steps {
                sh 'sudo apt update -y'
                sh 'sudo apt install maven -y'
                sh 'mvn clean package'
            }
        }
        stage(push-artifact) {
            steps {
                sh 'curl curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
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