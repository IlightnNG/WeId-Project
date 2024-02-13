#!/bin/bash

cd ..
source ./common/script/common.inc

if [ $# -lt 2 ] ;then
    echo "input error."
    exit 1
fi

cd ${SOURCE_CODE_DIR}

build_classpath

if [ "$1" = "--weid" ] ; then
    echo "Showing WeID, please wait..."
    weid=$2

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowWeIdOrIssuer $@

elif [ "$1" = "--issuer-index" ] &&[ "$3" = "--issuer-num"  ] ; then
    echo "Showing issuer list, please wait..."
    issuerIndex=$2
    issuerNum=$4

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowWeIdOrIssuer $@

elif [ "$3" = "--issuer-index" ] && [ "$1" = "--issuer-num"  ] ; then
    echo "Showing issuer list, please wait..."
    issuerIndex=$4
    issuerNum=$2

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowWeIdOrIssuer $@

fi


if [ ! $? -eq 0 ]; then
    echo "Show failed, please check the log -> ../logs/error.log."
    exit $?;
fi

if [ "$1" == "no_del" ];then
    exit 0
fi

echo "Show succeed"