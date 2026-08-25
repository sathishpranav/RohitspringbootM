pipeline {          // open pipeline block
    agent any

    tools {         // open tools block
        maven 'Maven3'
        jdk 'Java21'
        sonarScanner 'SonarScanner'
    }               // close tools block

    triggers {      // open triggers block
        githubPush()
    }               // close triggers block

    environment {   // open environment block
        DOCKER_IMAGE = "sathishpranav/helloworld-app:latest"
        REGISTRY_URL = "docker.io"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred')
    }               // close environment block

    stages {        // open stages block

        stage('Step 1: Checkout Code') {   // open stage block
            steps {                        // open steps block
                checkout scm
            }                              // close steps block
        }                                  // close stage block

        stage('Step 2: Build & Compile') { // open stage block
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

        stage('Step 6: Docker Push') {
            steps {
                bat """
                echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin
                docker push %DOCKER_IMAGE%
                """
            }
        }

    }               // ✅ close stages block

    post {          // open post block
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed. Check logs.'
        }
    }               // close post block
}                   // close pipeline block
