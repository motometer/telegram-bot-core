#!/bin/bash

./gradlew clean build -x check
aws lambda update-function-code --function-name motometer-bot --zip-file 'fileb://build/distributions/telegram-bot-core-0.0.1-SNAPSHOT.zip'