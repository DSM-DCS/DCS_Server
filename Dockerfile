FROM openjdk:11-jre-slim
COPY ./build/libs/*.jar app.jar
ARG DB_URL
ARG DB_USER
ARG DB_PWD
ARG JWT_SECRET
ARG AWS_SES_ACCESS
ARG AWS_SES_SECRET
ARG AWS_SES_REGION
ARG SENDER_EMAIL
ARG REDIS_PORT
ARG REDOS_HOST
ARG REDIS_PWD

ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PASSWORD=${DB_PWD}
ENV JWT_SECRET=${JWT_SECRET}
ENV AWS_SES_ACCESS=${AWS_SES_ACCESS}
ENV AWS_SES_SECRET=${AWS_SES_SECRET}
ENV AWS_SES_REGION=${AWS_SES_REGION}
ENV SENDER_EMAIL=${SENDER_EMAIL}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PWD=${REDIS_PWD}

ENTRYPOINT ["java","-jar","-Dspring.datasource.url=${DB_URL}","-Dspring.datasource.username=${DB_USER}", "-Dspring.jwt.secret-key=${JWT_SECRET}", "-Dspring.datasource.password=${DB_PASSWORD}", "-Dspring.aws.ses.access-key=${AWS_SES_ACCESS}", "-Dspring.aws.ses.secret-key=${AWS_SES_SECRET}", "-Dspring.aws.ses.region=${AWS_SES_REGION}", "-Dspring.aws.ses.email=${SENDER_EMAIL}","/app.jar"]
