pipeline {
    agent any

    tools {
        maven 'Maven3'   // Configure Maven in Jenkins global tools
        jdk 'Java21'     // Configure JDK in Jenkins global tools
    }

    triggers {
        // Auto-trigger pipeline when you push to GitHub
        githubPush()
    }

    environment {
        DOCKER_IMAGE = "sathishpranav/helloworld-app:latest"
        REGISTRY_URL = "docker.io"   // Docker Hub registry
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred') // Jenkins credentials ID
    }

    stages {
        stage('Step 1: Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Step 2: Build & Compile') {
            steps {
                // You can keep javac for demo OR switch to Maven
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Step 3: SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                   bat '''
                        sonar-scanner \
                        -Dsonar.projectKey=saidevopspjt \
                        -Dsonar.projectName=saidevopspjt \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=target/classes \
                        -Dsonar.host.url=http://192.168.1.9:9000 \
                        -Dsonar.login=yourGeneratedTokenHere
                    '''
                }
            }
        }

        stage('Step 4: Package JAR File') {
            steps {
                bat 'ls target/*.jar'
            }
        }

        stage('Step 5: Docker Build') {
            steps {
                bat "docker build -t $DOCKER_IMAGE ."
            }
        }

        stage('Step 6: Docker Push') {
            steps {
                bat """
                echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                docker push $DOCKER_IMAGE
                """
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }
}
