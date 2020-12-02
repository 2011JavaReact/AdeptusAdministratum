pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('deploy'){
        	steps{
        		sh 'cp /home/ec2-user/.jenkins/workspace/AdeptusPipeline/target/AdeptusAdministratum-0.0.1-SNAPSHOT.war /home/ec2-user/Downloads/Tomcat/apache-tomcat-8.5.60/webapps/'
        	}
        }
    }
}