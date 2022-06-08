FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/dcs-0.0.1-SNAPSHOT.jar
ARG DB_URL
ARG DB_USER
ARG DB_PWD
ARG JWT_SECRET
ARG JWT_ACCESS_EXP
ARG JWT_REFRESH_EXP
ARG AWS_SES_ACCESS
ARG AWS_SES_SECRET
ARG AWS_SES_REGION
ARG SENDER_EMAIL

ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PWD}
ENV JWT_SECRET=${JWT_SECRET}
ENV JWT_ACCESS_EXP=$JWT_ACCESS_EXP}
ENV JWT_REFRESH_EXP=$JWT_REFRESH_EXP}
ENV AWS_SES_ACCESS=${AWS_SES_ACCESS}
ENV AWS_SES_SECRET=${AWS_SES_SECRET}
ENV AWS_SES_REGION=${AWS_SES_REGION}
ENV SENDER_EMAIL=${SENDER_EMAIL}
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.datasource.url=${DB_URL}","-Dspring.datasource.username=${DB_USER}","-Dspring.datasource.password=${DB_PASSWORD}", "-Dspring.jwt.secret-key=${JWT_SECRET}", "-Dspring.jwt.access-exp=${JWT_ACCESS_EXP}", "-Dspring.jwt.refresh-exp=${JWT_REFRESH_EXP}", "-Dspring.aws.ses.access-key=${AWS_SES_ACCESS}", "-Dspring.aws.ses.secret-key=${AWS_SES_SECRET}", "-Dspring.aws.ses.region=${AWS_SES_REGION}", "-Dspring.aws.ses.email=${SENDER_EMAIL}","/app.jar"]