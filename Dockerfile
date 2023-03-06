FROM openjdk:17-jdk-alpine
WORKDIR wallet/
COPY target/banklyWallet0.0.1-SNAPSHOT.war wallet/
ENTRYPOINT ["java", "-jar","wallet/banklyWallet0.0.1-SNAPSHOT.war"]