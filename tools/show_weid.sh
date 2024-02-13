#!/bin/bash

cd ..
source ./common/script/common.inc

cd ${SOURCE_CODE_DIR}

build_classpath

echo "Showing WeID, please wait..."
java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowWeId

if [ ! $? -eq 0 ]; then
    echo "Show WeID failed, please check the log -> ../logs/error.log."
    exit $?;
fi

if [ "$1" == "no_del" ];then
    exit 0
fi

echo "Show succeed"
