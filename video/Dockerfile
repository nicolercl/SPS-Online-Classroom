FROM gcr.io/google-appengine/jetty
RUN apt-get update && apt-get install -y ffmpeg
ADD video-1.0-SNAPSHOT.war $JETTY_BASE/webapps/root.war