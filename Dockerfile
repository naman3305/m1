FROM java:openjdk-8-jre-alpine

WORKDIR /home

COPY target/*.jar /home
COPY *.sh /home

ENTRYPOINT ["/home/control.sh"]
