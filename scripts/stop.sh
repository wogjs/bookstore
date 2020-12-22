#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)

ABSDIR=$(dirname $ABSPATH)      # 현재 stop.sh가 속해 있는 경로를 찾음 
source ${ABSDIR}/profile.sh     # 자바로 보면 일종의 improt구문 해당 코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용 가능

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT 에서 구동 중인 애플리케이션 pid확인"
IDLE_PORT=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PORT} ]
then
    echo ">현재 구동 중인 애플리케이션이 없으므로 종료하지 않음"
else
    echo "> kill -15 $IDLE_PID"
    kill -15 ${IDLE_PID}
    sleep 5
fi