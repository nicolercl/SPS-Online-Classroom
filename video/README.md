This is the ffmpeg encoding backend service running on custom runtime.

### Preparing the service:

    mvn clean package

    ./copy_war.sh

### Running locally

    mvn jetty:run-exploded

### Deploying

    gcloud app deploy
