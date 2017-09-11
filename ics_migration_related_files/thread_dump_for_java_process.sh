set +x
# is WebNMS Running ?
pid=$(/usr/bin/pgrep java)
if [ "$?" -ne "0" ]; then
   echo "failed to determine id of java process"
   echo "aborting script"
   echo
   exit 0
fi

# is port HAP sends notifications on listening ?
listen=$(netstat -an | grep 54001)
if [ "$?" -ne "0" ]; then
   echo "HAP is not listening on port 54001"
   echo "aborting script"
   echo
   exit 0

fi
count=0
LIMIT=4
while [ "$count" -lt "$LIMIT" ]
do
   (/bin/kill -3 $pid)
   # pause
   sleep 5
   count=`expr $count + 1`
   echo "iteration $count completed"
   echo
   sleep 1
done
#exit 0
