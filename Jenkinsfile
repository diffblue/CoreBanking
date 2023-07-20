pipeline{
    agent any
    stages {
        stage('sanity checks') {
            steps {
              sh 'git branch'
              sh 'git status'
            }
        }
    }
}
