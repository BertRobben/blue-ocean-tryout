pipeline {
  agent any
  stages {
    stage('Init') {
      steps {
        echo 'Hello from the blue ocean!'
        bat '''
          path
          set
        '''
      }
    }
    stage('Build') {
      steps {        
          bat 'mvn clean install'
          junit(allowEmptyResults: true, testResults: 'target/surefire-reports/**/*.xml')
      }
      post {
        success {
          junit '**/target/surefire-reports/**/*.xml'          
        }        
      }
    }
    stage('Integration Test') {
      steps {
        dir (path: 'integration-tests') { 
          bat 'mvn clean verify -P integration'
          junit(allowEmptyResults: true, testResults: 'target/failsafe-reports/**/*.xml')
        }        
      }
      post {
        success {
          junit '**/target/failsafe-reports/**/*.xml'
          
        }
        
      }
    }
  }
  tools {
    maven 'Maven 3.5.0'
    jdk 'JDK 8u144'
  }
}