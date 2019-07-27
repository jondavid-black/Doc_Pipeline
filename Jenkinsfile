// For this pipeline to work, many of these commands must run as root.
//  The best way I see to do this is to run Jenkins under a Jenkins user.
//  Then setup that Jenkins user with special permissions in the sudoers.d directory for the needed commands.
//  If done correctly, this should allow the commands to be run by the Jenkins user without a password.

// But, I don't have time to work all that out so I'll just run Jenkins as root.  Do not do this!!

pipeline {
    agent  any 

    stages {
        
        stage('Generate Document Front Matter') {
            parallel {

                stage('Generate TOF') {
                    steps {
                        sh 'echo "Generate TOF"'
                        sh 'python ./src/tools/python/genTOF.py ./design-document/_posts/ > ./design-document/_includes/tof.html'
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
            }
        }

        stage('Document Quality Checks') {
            parallel {
                stage('Spell Check') {
                    steps {
                        sh 'echo "Spell Check"'
                        // this command will generate spelling_errors.json in the current directory
                        sh 'python ./src/tools/python/spell_check.py ./dictionary.dic ./design-document/_posts > spelling_errors.json'
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
        }
    }
}