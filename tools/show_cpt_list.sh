#!/bin/bash

cd ..
source ./common/script/common.inc

cd ${SOURCE_CODE_DIR}

build_classpath

if [ "$1" = "--index" ] &&[ "$3" = "--num"  ] && [ "$5" = "--type" ] ; then
    echo "Showing cpt list, please wait..."
    index=$2
    num=$4
    type=$6

    java ${JAVA_OPTS} -cp "$CLASSPATH" com.webank.weid.command.ShowCptList $@

else
    echo "input error. index + num + type"
    exit 1
fi


if [ ! $? -eq 0 ]; then
    echo "Show cpt list failed, please check the log -> ../logs/error.log."
    exit $?;
fi

if [ "$1" == "no_del" ];then
    exit 0
fi

echo "Show succeed"