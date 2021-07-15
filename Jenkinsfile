pipeline {
    agent any

    environment {
        registry = "236473115837.dkr.ecr.ap-south-1.amazonaws.com/mysampledockerrepo"
        dockerImage = ''
    }

     stages {
      stage("Git Checkout") {
          steps {
              git 'https://github.com/naman3305/m1.git'
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

      stage("Build Image") {
          steps {
              sh 'docker build -t testing .'
              sh 'docker tag mysampledockerrepo:latest 236473115837.dkr.ecr.ap-south-1.amazonaws.com/mysampledockerrepo:latest'
          }

          post {
              success {
                  sh 'echo Image built successfully.'
              }
          }
      }

      stage("Deploy Image") {
          steps {
              sh 'aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 236473115837.dkr.ecr.ap-south-1.amazonaws.com'
              sh 'docker push 236473115837.dkr.ecr.ap-south-1.amazonaws.com/mysampledockerrepo:latest'
              }

          post {
              success {
                  sh 'echo deployed success.'
              }
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
