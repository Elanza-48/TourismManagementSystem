FROM postgres:14-alpine
LABEL maintainer="elanza48"

# docker build -t custom-postgres -f ./db.Dockerfile .
# docker run --interactive --tty --rm --env-file .env --network bridge \
#   --volume postgres-data:/var/lib/postgresql/data:z -p 5532:5432 \
#      --name postgres_db  custom-postgres

COPY postgres-extensions.sh /docker-entrypoint-initdb.d/

RUN chmod 755 /docker-entrypoint-initdb.d/postgres-extensions.sh