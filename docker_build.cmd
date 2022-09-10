docker build . -t jenkins-snap:latest
docker tag jenkins-snap:latest healthscannerit/jenkins-snap:latest
docker push healthscannerit/jenkins-snap:latest
