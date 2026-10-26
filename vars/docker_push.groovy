
def call(String Project, String ImageTag, String dockerhubuser) {
    def imageName = "${dockerhubuser}/${Project}:${ImageTag}"

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

    withEnv(["IMAGE_NAME=${imageName}"]) {
        sh 'docker push "$IMAGE_NAME"'
    }
}
