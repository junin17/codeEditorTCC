FROM jboss/wildfly:10.1.0.Final
LABEL version="1.0"
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main
COPY wildfly/driverDB  /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main
COPY wildfly/deploys /opt/jboss/wildfly/standalone/deployments
COPY wildfly/standalone /opt/jboss/wildfly/standalone/configuration/
EXPOSE 8080
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]