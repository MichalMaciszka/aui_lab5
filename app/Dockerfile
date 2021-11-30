FROM nginx

COPY nginx-server.conf /etc/nginx/conf.d/default.conf

RUN apt-get update
RUN apt-get install -y openjdk-11-jdk-headless

COPY ./company.jar /usr/share/java/company.jar
COPY ./employee.jar /usr/share/java/employee.jar
COPY ./gateway.jar /usr/share/java/gateway.jar
COPY ./run.sh /

# CMD nohup java -jar /usr/share/java/employee.jar &
# CMD nohup java -jar /usr/share/java/company.jar
# CMD nohup java -jar /usr/share/java/gateway.jar
COPY ./JavaScript/ /usr/share/nginx/html/JavaScript/

##################################################
# ENTRYPOINT ["java", "-jar", "/usr/share/java/employee.jar"]
# ENTRYPOINT ["java", "-jar", "/usr/share/java/company.jar"]
# ENTRYPOINT ["java", "-jar", "/usr/share/java/gateway.jar", "&"]
