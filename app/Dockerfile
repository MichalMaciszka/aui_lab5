FROM nginx

COPY nginx-server.conf /etc/nginx/conf.d/default.conf

RUN apt-get update
RUN apt-get install -y openjdk-11-jdk-headless

COPY ./company.jar /usr/share/java/company.jar
COPY ./employee.jar /usr/share/java/employee.jar
COPY ./JavaScript/ /usr/share/nginx/html/JavaScript/
# COPY ./run.sh /
ENTRYPOINT sh -c 'service nginx start && java -jar /usr/share/java/employee.jar & sleep 5 && java -jar /usr/share/java/company.jar'
