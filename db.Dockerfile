FROM postgres:14-alpine
LABEL maintainer="elanza48"

# docker build -t tms-postgres:latest -f ./db.Dockerfile .

# docker run --rm --env-file .env --network bridge \
#   --volume tms-pgdata:/var/lib/postgresql/data:z -p 5432:5432 \
#      --name postgres_db  tms-postgres:latest

COPY postgres-extensions.sh /docker-entrypoint-initdb.d/

RUN chmod 755 /docker-entrypoint-initdb.d/postgres-extensions.sh