pipeline {
    agent {
        node ('agent-ec2')
    }
    stages {
        stage('git-pull'){
            steps {
                sh 'sudo apt-get update -y'
                sh 'sudo apt-get install git -y'
                git 'https://github.com/shubhamg21g/student-ui.git'
           }
        }
        stage('maven-build'){
            steps {
                sh 'sudo apt-get update -y'
                sh 'sudo apt-get install maven -y'
                sh 'mvn clean package'
            }
        }
        stage('push-artifact'){
            steps { 
             withAWS(credentials: 'shubham', region: 'ap-south-1') {
             sh 'aws s3 ls'
                sh '''
                 sudo apt-get install unzip -y
                # curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
                # unzip awscliv2.zip
                # sudo ./aws/install
                 sudo mv /home/ubuntu/workspace/jenkins/target/studentapp-2.2-SNAPSHOT.war /home/ubuntu/student-${BUILD_ID}.war
                 aws s3 cp /home/ubuntu/student-${BUILD_ID}.war s3://shubham.goutam
                 '''
             }
           }
        }
        stage('tomcat-deploy'){
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'new', keyFileVariable: 'new', usernameVariable: 'ubuntu')]) {

                    sh'''
                    ssh -i ${new} -o StrictHostKeyChecking=no ubuntu@43.205.143.41<<EOF
                    '''
                }
            }
        }
     }            
}