pipeline {          //open pipeline block
    agent any

    tools { //open tools block
        maven 'Maven3'   // Configure Maven in Jenkins global tools
        jdk 'Java21'     // Configure JDK in Jenkins global tools
        sonarScanner 'SonarScanner'
    }   //close tools block
   
    triggers {      //open triggers block
        // Auto-trigger pipeline when you push to GitHub
        githubPush()
    }           //close triggers block

    environment {   //open environment block
        DOCKER_IMAGE = "sathishpranav/helloworld-app:latest"
        REGISTRY_URL = "docker.io"   // Docker Hub registry
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-cred') // Jenkins credentials ID
    }   //close environment block

    stages {        //open stages block
        stage('Step 1: Checkout Code') {//open stage block
            steps {     //open steps block
                checkout scm
            }//close steps block
        }   //close stage block

        stage('Step 2: Build & Compile') {//open stage block
            steps { //open steps block
                // You can keep javac for demo OR switch to Maven
                bat 'mvn clean package -DskipTests'
            }   //close steps block
        }       //close stage block

        stage('Step 3: SonarCloud Analysis') {  // open stage block
            steps {                            // open steps block
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
            }                                // close steps block
        }                                    // close stage block     //closes stages block

    post {  //open post block
        success {   //open success block
            echo '✅ Pipeline completed successfully!'
        }   //close success block
        failure {//open failure block
            echo '❌ Pipeline failed. Check logs.'
        }   //close failure block
    }   //close post block
}       //close pipeline block
