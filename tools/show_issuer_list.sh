#!/bin/bash

cd ..
source ./common/script/common.inc

cd ${SOURCE_CODE_DIR}

build_classpath

if [ "$1" = "--index" ] &&[ "$3" = "--num"  ] ; then
    echo "Showing issuer list, please wait..."
    index=$2
    num=$4

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowIssuerList $@

elif [ "$3" = "--index" ] && [ "$1" = "--num"  ] ; then
    echo "Showing issuer list, please wait..."
    index=$4
    num=$2

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowIssuerList $@

else
    echo "input error."
    exit 1
fi


if [ ! $? -eq 0 ]; then
    echo "Show issuer list failed, please check the log -> ../logs/error.log."
    exit $?;
fi

if [ "$1" == "no_del" ];then
    exit 0
fi

echo "Show succeed"