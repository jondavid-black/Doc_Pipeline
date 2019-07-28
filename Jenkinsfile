#!/usr/bin/env groovy

// For this pipeline to work, many of these commands must run as root.
//  The best way I see to do this is to run Jenkins under a Jenkins user.
//  Then setup that Jenkins user with special permissions in the sudoers.d directory for the needed commands.
//  If done correctly, this should allow the commands to be run by the Jenkins user without a password.

// But, I don't have time to work all that out so I'll just run Jenkins as root.  Do not do this!!

pipeline {
    agent  any 

    stages {

        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew build -x checkstyleMain -x checkstyleTest -x spotbugsMain -x spotbugsTest'
                step( [ $class: 'JacocoPublisher' ] )
            }
        }
        stage('Static Analysis') {
            steps {
                sh './gradlew check'
                step([$class: 'hudson.plugins.checkstyle.CheckStylePublisher', 
                     pattern: 'build/reports/checkstyle/main.xml', 
                     unstableTotalAll:'0',unhealthy:'100', healthy:'100'])
                step([$class: 'FindBugsPublisher', pattern: 'build/reports/spotbugs/main.xml', unstableTotalAll:'0'])
            }

        }
        stage('Docs') {
            steps {
                sh './gradlew javadoc'
            }

        }
        stage('Acceptance Test') {
            steps {
                sh './gradlew cucumber'
            }

        }
        
        stage('Generate Document Front Matter') {
            parallel {

                stage('Generate TOF') {
                    steps {
                        sh 'echo "Generate TOF"'
                        sh 'python ./src/tools/python/genTOF.py ./design-document/_posts/ ./design-document/_includes/tof.html'
                    }
                }

                stage('Generate Acronyms') {
                    steps {
                        sh 'echo "Generate Acronyms"'
                    }
                }
            }
        }

        stage('Generate Figures') {
            steps {
                sh 'echo "Generate Figures"'
                sh 'gradle plantUml'
            }
        }

        stage('Generate Tables') {
            steps {
                sh 'echo "Generate Tables"'
                sh 'python genHtmlTableFromCsv.py resource/requirements.csv "Top Level Requirments" design-document/_includes/requirements_table.html'
                sh 'python genHtmlTableFromCsv.py resource/derived_requirements.csv "Derived Requirments" design-document/_includes/derived_requirements_table.html'
            }
        }

        stage('Document Quality Checks') {
            parallel {
                stage('Spell Check') {
                    steps {
                        sh 'echo "Spell Check"'
                        // this command will generate spelling_errors.json in the current directory
                        sh 'gradle spellCheck'
                    }
                }
                stage('Verify img tags') {
                    steps {
                        sh 'echo "Verify img tags"'
                    }
                }
            }
        }

        stage('Launch Jekyll') {
            steps {
                sh './runJekyll.sh'
            }
        }

        stage('Save to PDF') {
            steps {
                sh 'echo "Save to PDF"'
                sh 'google-chrome-stable --headless --print-to-pdf="doc.pdf" http://localhost:4000'
            }
        }

        stage('Clean Up') {
            steps {
                sh 'echo "Clean Up"'
                sh 'echo "Shutdown Jekyll"'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'doc.pdf', fingerprint: true
            archiveArtifacts artifacts: 'spelling_errors.json', fingerprint: true
            archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            junit 'build/test-results/**/*.xml'
            cucumber 'build/reports/cucumber/*.json'
            
            // publish JavaDocs
		    publishHTML (target: [
		        allowMissing: false,
		        alwaysLinkToLastBuild: false,
		        keepAll: true,
		        reportDir: 'build/docs/javadoc/',
		        reportFiles: 'allclasses-noframe.html',
		        reportName: "JavaDocs"
		    ])
		    
		    // publish Test Coverage
		    publishHTML (target: [
		        allowMissing: false,
		        alwaysLinkToLastBuild: false,
		        keepAll: true,
		        reportDir: 'build/reports/jacoco/test/html/',
		        reportFiles: 'index.html',
		        reportName: "Coverage Reports"
		    ])
        }
    }
}