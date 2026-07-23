pipeline {
    agent any

    triggers {
        // This makes Jenkins run automatically when you push to GitHub
        githubPush()
    }

    stages {
        stage('Step 1: Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Step 2: Build & Compile') {
            steps {
                sh 'javac HelloWorld.java'
            }
        }

        stage('Step 3: SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        sonar-scanner \
                        -Dsonar.projectKey=saidevopspjt \
                        -Dsonar.projectName=saidevopspjt \
                        -Dsonar.sources=. \
                        -Dsonar.java.binaries=. \
                        -Dsonar.host.url=http://192.168.1.9:9000 \
                        -Dsonar.login=yourGeneratedTokenHere
                    '''
                }
            }
        }

        stage('Step 4: Package JAR File') {
            steps {
                sh 'jar cfe HelloWorld.jar HelloWorld HelloWorld.class'
            }
        }

        stage('Step 5: Docker Package') {
            steps {
                sh 'docker build -t helloworld-app:latest .'
            }
        }
    }
}

