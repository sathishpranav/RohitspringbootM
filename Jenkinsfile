pipeline {          // open pipeline
    agent any

    tools {         // open tools
        maven 'Maven3'
        jdk 'Java21'
       
    }               // close tools

    triggers {      // open triggers
        githubPush()
    }               // close triggers

    environment {   // open environment
        DOCKER_IMAGE = "sathishpranav/helloworld-app:latest"
        REGISTRY_URL = "docker.io"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred')
    }               // close environment

    stages {        // open stages

        stage('Step 1: Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Step 2: Build & Compile') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Step 3: SonarCloud Analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonarcloud-token', variable: 'SONAR_TOKEN')]) {
                    bat """
                    sonar-scanner ^
                      -Dsonar.projectKey=saidevopspjt ^
                      -Dsonar.organization=sathishpranav ^
                      -Dsonar.sources=. ^
                      -Dsonar.java.binaries=target/classes ^
                      -Dsonar.host.url=https://sonarcloud.io ^
                      -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }

        stage('Step 4: Package JAR File') {
            steps {
                bat 'dir target\\*.jar'
            }
        }

        stage('Step 5: Docker Build') {
            steps {
                bat "docker build -t %DOCKER_IMAGE% ."
            }
        }

        stage('Docker Push') {
    steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            bat """
            echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
            docker build -t %DOCKER_IMAGE% .
            docker push %DOCKER_IMAGE%
            """
        }
    }
}


    }               // ✅ close stages

    post {          // open post
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }               // close post
}                   // ✅ close pipeline
