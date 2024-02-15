#!/bin/bash

cd ..
source ./common/script/common.inc

cd ${SOURCE_CODE_DIR}

build_classpath

echo "Adding public key, please wait..."
java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.AddPublicKey

if [ ! $? -eq 0 ]; then
    echo "Add public key failed, please check the log -> ../logs/error.log."
    exit $?;
fi