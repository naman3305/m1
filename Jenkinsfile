pipeline {
    agent any

    environment {
        registry = "236473115837.dkr.ecr.ap-south-1.amazonaws.com/mysampledockerrepo"
        dockerImage = ''
    }

     stages {
      stage("Git Checkout") {
          steps {
               git branch: 'main', url: 'https://github.com/naman3305/m1.git'
          }
      }


      stage("Build") {
          steps {
              sh 'mvn -B -DskipTests clean package'
          }
      }

      stage("Unit Test") {
          steps {
              sh 'mvn test'
          }

          post {
              always {
                  junit 'target/surefire-reports/*.xml'
              }
          }
      }

     stage('Building image') {
      steps{
        script {
          dockerImage = docker.build registry
           }
           }

          post {
              success {
                  sh 'echo Image built successfully.'
              }
          }
      }

     stage('Pushing to ECR') {
     steps{  
         script {
                sh 'aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 236473115837.dkr.ecr.ap-south-1.amazonaws.com'
                sh 'docker push 236473115837.dkr.ecr.ap-south-1.amazonaws.com/mysampledockerrepo:latest'
         }
        }

          post {
              success {
                  sh 'echo deployed success.'
              }
          }
      }
     stage('stop previous containers') {
         steps {
            sh 'docker ps -f name=mypythonContainer -q | xargs --no-run-if-empty docker container stop'
            sh 'docker container ls -a -fname=mypythonContainer -q | xargs -r docker container rm'
         }
       }
      stage("Remove Image") {
          steps {
              sh 'docker rmi testing'
          }
      }
  }
    post {
        always {
            cleanWs()
        }
    }
}
