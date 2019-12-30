FROM postgres:alpine
ADD ./scripts/schema.sql /docker-entrypoint-initdb.d
RUN chmod a+r /docker-entrypoint-initdb.d/*