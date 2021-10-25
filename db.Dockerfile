FROM postgres:14-alpine
LABEL maintainer="elanza48"

# docker build -t custom-postgres -f ./db.Dockerfile .
# docker run --interactive --tty --rm --env-file project.env --network bridge \
#   --volume postgres-data:/var/lib/postgresql/data:z -p 5532:5432 \
#      --name postgres_db  custom-postgres

ENV TZ=Asia/Kolkata \
    LANG=en_US.UTF-8 \
    LANGUAGE=en_US.UTF-8 \
    LC_ALL=en_US.UTF-8
ENV POSTGRES_DB ''
ENV POSTGRES_USER ''
ENV POSTGRES_PASSWORD ''

COPY postgres-extensions.sh /docker-entrypoint-initdb.d/

RUN chmod 755 /docker-entrypoint-initdb.d/postgres-extensions.sh