pipeline {
    agent any
    stages {
        stage('git-pull') {
            steps {
                echo 'git pull from the git repository'
            }
        }
        stage(build) {
            steps {
                echo 'git build sucully'
            }
        }
        stage(test) {
            steps {
                echo 'git test'
            }
        }
        stage(deploy) {
            steps {
                echo 'git deploy'
            }
        }
     }            
}