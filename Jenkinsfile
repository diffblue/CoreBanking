pipeline{
    agent any
    environment { // Step 2
        DB_RELEASE_URL = credentials('DB_RELEASE_URL')
        DB_LICENSE_KEY = credentials('DB_LICENSE_KEY')
        DB_LICENSE_LIC = credentials('DB_LICENSE_LIC')

    }

    stages {
        stage('sanity checks') {
            steps {
              sh 'git branch'
              sh 'git status'
            }
        }

        stage('Build project') {
            steps { // Step 1
              sh 'mvn --batch-mode --no-transfer-progress clean install -DskipTests'
              // batch-mode = suppress upload messages to avoid polluting the console log
              // no-transfer-progress = remove some output; doanloading messages etc
              // -DskipTests = skipping tests to save time (tests assumed to compile and pass)
            }
        }

        stage('Activate dcover') {
            steps { // Step 3
                 sh '''
                      echo "Get and unzip dcover jars into directory dcover, store dcover script location for later use"
                      mkdir --parents dcover
                      wget "$DB_RELEASE_URL" --output-document dcover/dcover.zip --quiet
                      unzip -o dcover/dcover.zip -d dcover
                      DCOVER_SCRIPT_LOCATION="dcover/dcover"

                      echo "Activate dcover"
                      "$DCOVER_SCRIPT_LOCATION" activate --offline "$DB_LICENSE_KEY"
                      cp "$DB_LICENSE_LIC" ${HOME}/.diffblue/offline/
                      "$DCOVER_SCRIPT_LOCATION" activate --offline "$DB_LICENSE_KEY"
                  '''
            }
        }
    }
}
