pipeline {
agent any
environment {
    SERVER = "ubuntu@51.222.155.92"
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
            curl -f http://localhost:8080 || exit 1
            "
            '''
        }
    }
}
}

