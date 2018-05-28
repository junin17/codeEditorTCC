FROM jboss/wildfly:10.1.0.Final
LABEL version="1.0"

#Instalando Pacotes
USER root
RUN yum -y install epel-release
RUN yum -y install nodejs
RUN yum -y install python34


RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main
COPY wildfly/driverDB  /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main
COPY wildfly/deploys /opt/jboss/wildfly/standalone/deployments
COPY wildfly/standalone /opt/jboss/wildfly/standalone/configuration/
COPY wildfly/padroes /opt/jboss/

USER jboss

EXPOSE 8080

#CMD ["/bin/bash"]
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]