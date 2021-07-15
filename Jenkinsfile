pipeline {
    agent any

    environment {
        registry = "360433695343.dkr.ecr.ap-south-1.amazonaws.com"
        dockerImage = ''
    }

     stages {
      stage("Git Checkout") {
          steps {
              git 'https://github.com/Ripurwar80/mile1.git'
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
              sh 'docker tag testing:latest 360433695343.dkr.ecr.ap-south-1.amazonaws.com/testing:latest'
          }

          post {
              success {
                  sh 'echo Image built successfully.'
              }
          }
      }

      stage("Deploy Image") {
          steps {
              sh 'docker login --username AWS -p eyJwYXlsb2FkIjoidnJTcTJlN0puQzVFWVdsZXYyei9YNmVvVU9jMTdZdno0Rkl6V3M5aHo3OG5kQWxUeVFFaGxFTENpdU5HUHFOMC9MUmx5M3RFRU9sdUZZenpoSTNJdXVJNWc5OThiMmRnL3c3NDZEcnBOTm5MOFhSVEovbUFDWTRmSUVCZVVpTDNwaGt2aGRsREdPdTJaS0NXaGh2QVp4Y0JyQWl6di9oSVZyclNzY1JnMlF5OTdBbmp5K3Zua3pzc0xZQk9xU0RLVEY1dWJiSGhSb1dodVc2LzBrWjAwSmw0M05UNjJBOWRVZ1NqQzcrSVFiWk9LYmNPRk5pZWpIMVpreFYveVlSOHBMdWJNc3lwKzdxUFQ5akZzVVVKeDYrbEtBTlJ5L1BEWXJ6TFowTXREWERRbGVrN2xhOEFTYlNpVFNDZ1BKY2JtU24wckIwMGhvRFA3amcwd2FlQTBtbm90SkVjM1A5NEl3bnYxL25RakdaVHF2MXViMGNnRU5HeXoxWVVtT3h2SFN2cTdLN0VYWllPWlJrL3BGeHZMaUhnMjlBNnNVSzdXUnVQVkxaV1hsK3VHR25RZjRIekZ2NTZXQUtoekJsVDFpSW8wc080RGh2WW5zcHl3d3kybG5XQUhwRi82c3dTQUpJVDhPQlhEemZHVDh4d3kwMTB2b0V3U1V6b2tNNFJLZVhLSlJCQXE0bUZnMzZlTjVOMi9iRFBPYnUyRkhmT1NML2EvdE9lWUNOSnJnb0ZiSENDUXJwN3IzdFJkamY3Q01pMkwwNzh2anRGTGU3T3Vid25UcnBhU3dKVXZUeGFTWFU5eDJjTDNTcVRyb2l1VmlxMG9zWEJVTVBvSnRCOXFFUE5SMTRCRW52alFYdW1ScjcrOThIazRWVGpkNkVXWlNHMnZ5Mi9YUS9CeU9QMTFvZFp1S3d4UklDWnRWOE5UTE9Wa1dIV2czV3FIc1BHOG5icTRQY2ZBWlZXYWtyZWdxbnNoa21tOXBjaVpoVkdKbjNGK0QvMDlsMURBR1ppeXZwT1pTV0NlSUxTRXlLVXd3ckJEeUJmZ2c1T3FyOEdycGl4VXNjMURYTGs4eXRScm5xMWV3SzJVa2dZWDBGQlEvTjRld1hjbUtZNEFrVDFpbUc4cDdJelJidGNsR09JVEcxVUFjaU1oZ28wYkZxTW8wL0ZHcXZJcm1MVE5SbTJIZFdpV291M3ZaWHZVVTVJQ3BiWngweWlTN01tem1rYVgyWUt5aHBRajBCNHg4NHUyc09EaWhCOEtiNSs3T1pQWUlPbWRVYTI3TVVBY082RWJvdFB1bEk2eXUzWE9zOFlWSmlvVWd0TmZxcUN6SVB2Nitnc1NKYUFDVWQxOTdBc2hlZ2g4bUFYeHhGZ1kvTzIyVHBuTmpDN09EekRSa1JkWFYyczJya09aSDNRZnhxYkpoc0NUNmhXenJFblB2cllsUWZGdjlTVHRiTEp2bFA4amRRaCtMTEZwek5LcFlnUHJDSFdzeC9SNGs4WjFubGlZTWRSSGtZWWk0c1d0ZXk2dE51NmNXaDhiaTZwaGZLR0NnaG5Pck9POU92elRjMExGeDlETkEwbjVBTU9Gd1UrQzRMSmNmZmRyc1o5S01LZ1hnckUxY0ZQWmI2S25OZXBVdjZYYW9xSUNXOStHNXg5dHVkeFQzc3dIL1VMREdIbUhMeW1IZm9mYThTVzhwVkpjb0VqWk1MTHp4d2ZIeDh4cERSNm5aZ2xpQ09rYXY3UVFSc2xOOVMzQmhicmt5Nzh1cUxCa0VGNEF4bk40MnIwOVpFWUJINlVCRFBoMUhmK3NQSTB2RTdtRnBtU0JwUXcyekl4Nzl4b205MFlUWjZCcStNZUZZOC9RMmVDaE9mZ01LaWpjRXZaZU83OVFhWFhzTERVRXRLUkhvTjc3MXIwa25wMWZsU2czdlBCclNQUElHZUZxM1dTQlRIcTZOYnNFVm9qVHdMaCtIWW1QRHFJM0V5N043OVh4U1EvWldBaUl6ejJEaEdQMTh1MFV4bzlvVnJNcWVRWWlhdXRMaXpyWjRFYW8ybDAzOFZ4Vkg1M1dXem80ZmRpVkxla2V0MnJPMlUwUnk5LzJ2UGZJa3N2dlM2SThuSmI2YW0zUmYxekw5bjB6WjJvWk9ETHFTRTBHRjAzelREVlQ2bjBaUG14NzlYeitwK0RBWkt2aGJqNG9GWW90VFlXdU5RamtiNEhuc1FDbGRWYVJjZ09NMGNSdnc9PSIsImRhdGFrZXkiOiJBUUlCQUhpSFdhWVRuUlVXQ2Jueis3THZNRytBUHZUSHpIbEJVUTlGcUVtVjI2QmR3d0d4N0txNHhtcmpSTUN2bjE5T1NvZjRBQUFBZmpCOEJna3Foa2lHOXcwQkJ3YWdiekJ0QWdFQU1HZ0dDU3FHU0liM0RRRUhBVEFlQmdsZ2hrZ0JaUU1FQVM0d0VRUU1JdjlYVVIzaXFicFZYenJJQWdFUWdEc3VqQUFhV2J1ekJoZEdGVFFNRXZ1WTZnMWh3R3RYRUI5NGlUUnR0V1YyS2s5d2tydDIveityb0N0WmVEUUxPVzRKZ1Uwa1V0QVA3S3FDdkE9PSIsInZlcnNpb24iOiIyIiwidHlwZSI6IkRBVEFfS0VZIiwiZXhwaXJhdGlvbiI6MTYyNjI5NzY0OX0= 360433695343.dkr.ecr.ap-south-1.amazonaws.com'
              sh 'docker push 360433695343.dkr.ecr.ap-south-1.amazonaws.com/testing:latest'
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
