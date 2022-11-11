pipeline {
    agent any
    environment{
        PATH = "$PATH:/usr/share/maven/bin"
    }

    stages {
         stage('Cloning from GitHub') {
                steps {
                    git branch: 'main', url: 'https://github.com/KlaiGhassen/devops'
                }  
            }
              
          stage('MVN COMPILE') {
            steps {
               sh 'mvn compile'
           }
        }
          stage('mvn Test') {
            steps {
               sh 'mvn test'
            }
        }
          stage('mvn Verify') {
             steps {
               sh 'mvn verify'
          }
       }
         stage ('Scan Sonar'){
            steps {
    sh "mvn sonar:sonar \
          -Dsonar.projectKey=achat \
          -Dsonar.host.url=http://192.168.33.10:9000 \
          -Dsonar.login=68359cf3e9b325d9706fedbb1b2747978c9163d6"
    }
        }
        stage('Nexus') {
      steps {
        sh 'mvn deploy -DskipTests'
      }
    }
       
     stage("Building Docker Image") {
                steps{
                    sh 'docker build -t gaston2100/achat .'
                }
        }
        
        
           stage("Login to DockerHub") {
                steps{
                   // sh 'sudo chmod 666 /var/run/docker.sock'
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u gaston2100 -p hinda2100@@'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push gaston2100/achat'
                }
        }
    
               stage("Docker-compose") {
                steps{
                    sh 'docker-compose up -d'
                }
        }
    }
    
    post {
                success {
                   echo 'succes'
                }
failure {
                  echo 'failed'   
                }
             
       
    }

}
