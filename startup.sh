#!/bin/bash

# Décode la variable d'environnement "MY_SECRET_FILE_BASE64" et enregistre-le dans un fichier
echo $FIREBASE_PRIVATE_KEY_BASE64 | base64 --decode > /tmp/firebase_private_key.json
echo $ENV_BASE64 | base64 --decode > /tmp/.env
ls -l /tmp/

# Maintenant, démarre ton application Spring Boot
java -Dserver.port=$PORT -jar target/log4aqua-1.0.1.jar
