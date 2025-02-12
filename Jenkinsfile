
pipeline {
  agent any
  tools {
        jdk 'JDK_17' 
        maven 'Maven3'
    }

  stages {
    stage('Checkout'){
      steps {
        git credentialsId: 'github_credentials', 
        url: 'https://github.com/dhivya1806/Java_Demo_BenchProject',
        branch: 'main'
      }
    }
    stage('Build and Test') {
      steps {
        sh 'ls -ltr'
          sh 'mvn clean install'
      }
    }
    stage('Static Code Analysis') {
      environment {
        SONAR_URL = "http://44.220.55.193:9000/"
      }
      steps {
        withCredentials([string(credentialsId: 'sonar', variable: 'SONAR_AUTH_TOKEN')]) {
            sh '''
            mvn sonar:sonar \
            -Dsonar.login=$SONAR_AUTH_TOKEN \
            -Dsonar.host.url=${SONAR_URL}
            '''  
        }
      }
    }
     stage('Build Docker'){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'Docker_cred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                    echo 'Logging in to Docker Hub'
                    echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                    echo 'Buid Docker Image'
                    docker build -t dhivya1806/demofeb9:${BUILD_NUMBER} .
                    '''
                    }
                }
            }
        }

        stage('Push the artifacts'){
           steps{
                script {
                    withCredentials([usernamePassword(credentialsId: 'Docker_cred', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh '''
                    echo 'Logging in to Docker Hub'
                    echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                    echo 'Push to Repo'
                    docker push dhivya1806/demofeb9:${BUILD_NUMBER}
                    '''
                    }
                }
           }
        }
    
  stage('Checkout K8S manifest SCM'){
            steps {
                git credentialsId: 'github_credentials', 
                url: 'https://github.com/dhivya1806/Java_Demo_BenchProject/argocd_manifest',
                branch: 'main'
            }
        }
    stage('Update K8S manifest & push to Repo'){
            steps {
                script{
                    withCredentials([string(credentialsId: 'github_credentials', variable: 'GIT_PAT')]) {
                        sh '''
                        cat deployment.yaml
                        sed -i "s/\\(image:.*:\\)[0-9]\\+/\\1${BUILD_NUMBER}/" deployment.yaml
                        cat deployment.yaml
                        git add deployment.yaml
                        if ! git diff --cached --quiet; then
                        git commit -m "Updated the deploy yaml | Jenkins Pipeline"
                        else
                        echo "No changes to commit"
                        fi
                        git remote -v
                        git config user.name "Dhivya"
                        git config user.email "dhivyams97@gmail.com"
                        git push https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/dhivya1806/Manifests2_repo.git HEAD:main
                        '''                        
                    }
                }
            }
        }
    
  }
}
