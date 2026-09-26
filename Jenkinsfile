pipeline {
    agent any   // Run on any available node

    tools {
        maven 'Maven_3.9.6'   // Configure Maven in Jenkins Global Tool Config
        jdk 'JDK_21'          // Configure JDK in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sathishpranav/RohitspringbootM.git'
            // It tells Jenkins to use the Git tool to clone the main
            //  branch from the specified repository
            //  url (https://github.com/your-org/your-repo.git) 
            // into the current workspace directory.
            }
        }

        stage('Build') {
            steps {
                // Windows command (PowerShell/Command Prompt)
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success { echo '✅ Build & Test completed successfully!' }
        failure { echo '❌ Build or Test failed. Check logs.' }
    }
}
