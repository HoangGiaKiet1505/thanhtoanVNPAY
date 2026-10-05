# ==============================
# BUILD
# ==============================

FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests


# ==============================
# RUN TOMCAT
# ==============================

FROM tomcat:10.1-jdk17-temurin

RUN rm -rf /usr/local/tomcat/webapps/*


# ==============================
# SQL SERVER JDBC DRIVER
# ==============================

COPY --from=build \
    /root/.m2/repository/com/microsoft/sqlserver/mssql-jdbc/13.2.0.jre11/mssql-jdbc-13.2.0.jre11.jar \
    /usr/local/tomcat/lib/mssql-jdbc-13.2.0.jre11.jar


# ==============================
# DEPLOY WAR
# ==============================

COPY --from=build \
    /app/target/sendmailC14.war \
    /usr/local/tomcat/webapps/ROOT.war


# ==============================
# RENDER PORT
# ==============================

EXPOSE 10000


# ==============================
# START TOMCAT
# ==============================

CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-10000}\\\"/\" /usr/local/tomcat/conf/server.xml && exec catalina.sh run"]