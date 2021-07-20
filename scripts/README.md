# set-codeartifact-token.sh

This script will request codeartifact token from AWS and then:

- Export AWS_CODEARTIFACT_TOKEN env variable to host machine only on development env
- Set AWS_CODEARTIFACT_TOKEN variable in .env file (for any env)

Run the script from the project home directory as: 

```shell
. ./scripts/set-codeartifact-token.sh /path/to/base/dir
```
where /path/to/base/dir is where docker-compose.yml resides, defauls to /var/www