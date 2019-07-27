// For this pipeline to work, many of these commands must run as root.
//  The best way I see to do this is to run Jenkins under a Jenkins user.
//  Then setup that Jenkins user with special permissions in the sudoers.d directory for the needed commands.
//  If done correctly, this should allow the commands to be run by the Jenkins user without a password.

// But, I don't have time to work all that out so I'll just run Jenkins as root.  Do not do this!!

pipeline {
    agent  any 

    stages {
        
        stage('Generate TOC') {
            steps {
                sh 'echo "Generate TOC"'
            }
        }

        stage('Generate TOF') {
            steps {
                sh 'echo "Generate TOF"'
            }
        }

        stage('Generate Acronyms') {
            steps {
                sh 'echo "Generate Acronyms"'
            }
        }

        stage('Generate Figures') {
            steps {
                sh 'echo "Generate Figures"'
            }
        }

        

        stage('Launch Jekyll') {
            steps {
                sh 'echo "Launch Jekyll"'
            }
        }

        stage('Document Quality Checks') {
            parallel {
                stage('Spell Check') {
                    sh 'echo "Spell Check"'
                }
                stage('Verify img tags') {
                    sh 'echo "Verify img tags"'
                }
            }
        }

        stage('Save to PDF') {
            steps {
                sh 'echo "Save to PDF"'
            }
        }

        stage('Clean Up') {
            steps {
                sh 'echo "Clean Up"'
            }
        }
    }
}