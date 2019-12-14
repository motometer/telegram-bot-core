#!/bin/bash

./gradlew clean build -x check > /dev/null
aws lambda update-function-code --function-name motometer-bot --zip-file 'fileb://build/distributions/telegram-bot-core-0.0.1-SNAPSHOT.zip' > /dev/null
echo "Lambda uploaded successfully"