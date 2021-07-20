#!/bin/bash

BASE_DIR="${1:-/var/www}"

if [ ! -d "$BASE_DIR" ]; then
  echo "Invalid base directory '$BASE_DIR'. Exiting"
#  exit 1
fi

DOCKER_COMPOSE_DOTENV_FILE="$BASE_DIR/.env"
DOTENV_FILE="./.env"

[ -f $DOTENV_FILE ] && chmod a+x $DOTENV_FILE && . $DOTENV_FILE

if [ -z "${HOST_ENV}" ];then
  echo "Error: environment must be set. Exitting."
#  exit 1
fi

echo "Requesting AWS CodeArtifact token"
CA_TOKEN=`aws codeartifact get-authorization-token --domain win --domain-owner 643459500120 --query authorizationToken --output text`

set_env_var () {
  KEY=$1
  VALUE=$2
  FILE=$3

  line_number=$(grep -n ^"$KEY=" $FILE | head -n 1 | cut -d: -f1)
  if [ -n "${line_number}" ];then
    echo "Setting variable $KEY to $FILE"
    RECORD=$(echo "$KEY=$VALUE" | sed -e 's/\//\\\//g')
    sed -i "${line_number}""s/.*/$RECORD/" $FILE
  else
    echo "$KEY=$VALUE" >> $FILE
  fi
}

if [ ${HOST_ENV} == "development" ];then
  echo "Exporting codeartifact token on host env ${HOST_ENV}"

  export AWS_CODEARTIFACT_TOKEN="$CA_TOKEN"
  touch ~/.bashrc
  set_env_var "export AWS_CODEARTIFACT_TOKEN" "$CA_TOKEN" ~/.bashrc
fi

echo "Exporting codeartifact token to ${DOTENV_FILE} for ${HOST_ENV}"
set_env_var "AWS_CODEARTIFACT_TOKEN" "$CA_TOKEN" "$DOTENV_FILE"

echo "Exporting codeartifact token to ${DOCKER_COMPOSE_DOTENV_FILE} for ${HOST_ENV}"
set_env_var "AWS_CODEARTIFACT_TOKEN" "$CA_TOKEN" "$DOCKER_COMPOSE_DOTENV_FILE"