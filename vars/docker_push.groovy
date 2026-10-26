
def call(String Project, String ImageTag, String dockerhubuser) {
    withCredentials([usernamePassword(
        credentialsId: 'docHubCred',
        passwordVariable: 'dockerHubPass',
        usernameVariable: 'dockerHubUser'
    )]) {
        sh '''
            set +x
            echo "$dockerHubPass" | docker login \
                -u "$dockerHubUser" \
                --password-stdin
        '''
    }

    sh "docker push ${dockerhubuser}/${Project}:${ImageTag}"
}
