pipeline{
    agent any
    stages {
        stage('sanity checks') {
            steps {
              sh 'git branch'
              sh 'git status'
            }
        }

        stage('Build project') {
            steps {
              sh 'mvn --batch-mode --no-transfer-progress clean install -DskipTests'
              // batch-mode = suppress upload messages to avoid polluting the console log
              // no-transfer-progress = remove some output; doanloading messages etc
              // -DskipTests = skipping tests to save time (tests assumed to compile and pass)
            }
        }
    }
}
