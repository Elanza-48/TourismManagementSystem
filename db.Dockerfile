FROM postgres:14-alpine

ENV POSTGRES_DB ''
ENV POSTGRES_USER ''
ENV POSTGRES_PASSWORD ''

COPY postgres-extensions.sh /docker-entrypoint-initdb.d/