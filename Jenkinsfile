pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    environment {
        DOCKER_IMAGE = 'banking-system'
        DOCKER_TAG = "${BUILD_NUMBER}"
        SONAR_TOKEN = credentials('sonar-token') // Assurez-vous que ce credential est configuré dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    deleteDir() // Nettoyer le workspace
                    echo "Clonage du dépôt Git..."
                    bat '''
                        git clone -b dev https://github.com/Sanalouafi/eBankify-p1 .
                        echo "Dépôt cloné avec succès."
                    '''
                }
            }
        }

        stage('Environment Check') {
            steps {
                bat '''
                    echo "Version de Git :"
                    git --version
                    echo "Branche Git actuelle :"
                    git branch --show-current
                    echo "Statut de Git :"
                    git status
                    echo "Version de Java :"
                    java -version
                    echo "Version de Javac :"
                    javac -version
                    echo %SONAR_TOKEN%
                    echo "Contenu du répertoire de travail :"
                    cd
                    dir
                '''
            }
        }

        stage('Build') {
            steps {
                bat '''
                echo "Affichage du token SonarQube :" %SONAR_TOKEN%
                mvn clean package
                '''
            }
        }

        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Quality Analysis') {
            steps {
                bat '''
                    mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=${SONAR_TOKEN} -e
                '''
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                    docker.build("${DOCKER_IMAGE}:latest")
                }
            }
        }

        stage('Manual Approval') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    input message: 'Déployer en production ?', ok: 'Procéder'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").run('-p 8082:8080')
                }
            }
        }
    }

    post {
        success {
            mail to: 'sanalouafiart@gmail.com',
                 subject: "Pipeline Success - eBankify",
                 body: "Le pipeline Jenkins s'est terminé avec succès !"
        }
        failure {
            mail to: 'sanalouafiart@gmail.com',
                 subject: "Pipeline Failure - eBankify",
                 body: "Le pipeline Jenkins a échoué. Veuillez vérifier les logs."
        }
    }
}