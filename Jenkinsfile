pipeline {
    agent any
    environment {
        SERVER   = "ubuntu@51.222.155.92"
        APP_PATH = "/home/ubuntu"
        JAR_NAME = "aleap.jar"
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                        url: 'https://github.com/Saic98779/aleap.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to Server') {
            steps {
                sh '''
            scp target/aleap.jar $SERVER:$APP_PATH/$JAR_NAME
            '''
            }
        }

        stage('Restart Application') {
            steps {
                sh '''
            ssh $SERVER "
            sudo systemctl daemon-reload
            sudo systemctl restart aleap
            sleep 10
            sudo systemctl status aleap
            "
            '''
            }
        }

        stage('Health Check') {
            steps {
                sh '''
            ssh $SERVER "
            echo 'Checking application health on port 8084...'
            for i in {1..5}; do
                curl -f http://localhost:8084/workflow/swagger-ui/index.html && exit 0
                echo 'Attempt '$i' failed. Waiting 2 minutes before retry...'
                sleep 120
            done
            echo 'Application failed after 5 retries'
            exit 1
            "
            '''
            }
        }

    }
}
