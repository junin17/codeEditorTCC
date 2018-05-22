FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD flamengo87
ENV POSTGRES_DB codeeditor
LABEL version="1.0"
COPY init.sql /docker-entrypoint-initdb.d/init.sql
EXPOSE 5432
CMD ["postgres"]
