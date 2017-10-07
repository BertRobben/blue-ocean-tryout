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
        }        
        script { 
          env.branch2build = input message: 'Continue to next stage?', parameters: [string(defaultValue: '', description: 'branch to build', name: 'branch2build')]
        }
        echo "This is the branch to build ${env.branch2build}"
      }
      post {
        success {
          junit '**/target/failsafe-reports/**/*.xml'
          
        }
        
      }
    }
    stage('Chaos') { 
      parallel {
        stage ('chaos-1') { 
          steps {
            dir (path: 'chaos-tests-1') { 
              bat 'mvn clean verify -P integration'
            }
          }        
          post {
            always {
              junit '**/target/failsafe-reports/**/*.xml'
            }
          }          
        }
        stage ('chaos-2') { 
          steps {
            dir (path: 'chaos-tests-2') { 
              bat 'mvn clean verify -P integration'
            }
          }        
          post {
            always {
              junit '**/target/failsafe-reports/**/*.xml'
            }
          }          
        }        
      }
    }
  }
  tools {
    maven 'Maven 3.5.0'
    jdk 'JDK 8u144'
  }
}