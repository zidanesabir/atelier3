
FROM payara/server-full:latest
COPY target/gestion-commandes.war $DEPLOY_DIR
