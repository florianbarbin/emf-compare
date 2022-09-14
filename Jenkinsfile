pipeline {
	agent {
		label 'migration'
	}
	
	options {
		buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5'))
		timestamps()
	}
	
	tools {
		maven 'apache-maven-latest'
		jdk 'adoptopenjdk-openj9-jdk11-latest'
	}
	
	environment {
		// Target platform to build against (must correspond to a profile in the parent pom.xml)
		PLATFORM = '2022-03'
		PLATFORM-EXTRAS = '2022-03-extras'
	}
	
	stages {
		stage ('Nightly') {
			when {
				not {
					branch 'PR-*'
				}
			}
			steps {
				dir ("org.eclipse.emf.compare-parent") {
					wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
						sh "mvn clean verify -P$PLATFORM-EXTRAS -Pextra-modules -Psign -Pjavadoc"
					}
				}
				sh "./releng/org.eclipse.emf.compare.releng/publish-nightly.sh"
			}
		}
		stage ('PR Verify') {
			when {
				branch 'PR-*'
			}
			steps {
				dir ("org.eclipse.emf.compare-parent") {
					wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
						sh "mvn clean verify -P$PLATFORM"
					}
				}
			}
		}
	}
	
	post {
		always {
			junit "**/tests/**/target/surefire-reports/*.xml"
		}
		failure {
			unsuccessful (
				subject: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
				body: """FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':
				Check console output at ${env.BUILD_URL}""",
				to: 'emfcompare-build@eclipse.org'
			)
		}
	}
}