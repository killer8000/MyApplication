#!/usr/bin/env bash

if [ -z "$1" ];then
 echo -e "\033[31;49;1m-------------------"
 echo "|请输入包名|"
 echo "-------------------"
 echo  -e " \033[39;49;0m"
exit
else
PARAMS=$1
fi
if [ -z "$2" ];then
 echo -e "\033[31;49;1m-------------------"
 echo "|请输入待测试次数|"
 echo "-------------------"
 echo  -e " \033[39;49;0m"
exit
else
TEST_COUNT=$2
fi
FILE_NAME=aa.txt
CLASS_NAME=${PARAMS#*/}
PACKAGE_NAME=${PARAMS%/*}
echo $((`date +%y%m%d%H%M%S`)) >> $FILE_NAME
echo "----------------" >> $FILE_NAME
echo "热启动" >> $FILE_NAME
echo "----------------" >> $FILE_NAME
for ((i=0;i<$TEST_COUNT;i++))
do
  adb shell am force-stop $PACKAGE_NAME
  adb shell am start -W $PARAMS |grep TotalTime >> $FILE_NAME
done
echo "----------------" >> $FILE_NAME
echo "温启动" >> $FILE_NAME
echo "----------------" >> $FILE_NAME
adb shell am start -W $PARAMS |grep TotalTime
for ((i=0;i<$TEST_COUNT;i++))
do
    #返回键
  adb shell input keyevent KEYCODE_BACK
  adb shell am start -W $PARAMS |grep TotalTime >> $FILE_NAME
done
echo "----------------" >> $FILE_NAME
echo "冷启动" >> $FILE_NAME
echo "----------------" >> $FILE_NAME
adb shell am start -W $PARAMS |grep TotalTime
for ((i=0;i<$TEST_COUNT;i++))
do
    #返回键
  adb shell input keyevent KEYCODE_HOME
  adb shell am start -W $PARAMS |grep TotalTime >> $FILE_NAME
done
echo "----------------" >> $FILE_NAME