FROM openjdk:17-oracle
COPY target/*.jar infobip_project.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "infobip_project.jar"]