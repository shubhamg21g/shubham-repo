pipeline{
     agent {
        node 'agent-ec2'
     }
    stages {
      stage("git-pull") {
        steps {
            sh 'sudo apt-get update -y'
            sh 'sudo apt-get install git -y'
            git 'https://github.com/shubhamg21g/student-ui.git'
            sh 'ls'
        }
    }
    stage("Maven-Build") {
        steps {
            sh 'sudo apt-get update -y'
            sh 'sudo apt-get install maven curl unzip -y'
            sh 'mvn clean package'
        }
    }
    stage("push-artifact") {
        steps {
    sh 'curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"'
    sh 'unzip awscliv2.zip'
    sh 'sudo ./aws/install'
         }
      }
  }
}