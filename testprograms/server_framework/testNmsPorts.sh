checkport()
{
	module=$1
	port=$2
	protocol=$3
   
	status=1
    if test $protocol = "UDP"
    then
      netstat -an | tr -s " " | grep " *.$port " >/dev/null 2>/dev/null
	  status=$?
	else
      netstat -an | grep $port | grep "LISTEN" >/dev/null 2>/dev/null
	  status=$?
	fi

	if test $status -eq 0
    then
	  echo "$module running on port $port"
    else
	  echo "Error: $module is not running on port $port"
    fi
}
checkport "Apache" 9090 TCP
checkport "BE" 2000 TCP
checkport "JSERV" 8007 TCP
checkport "Agent" 8001 UDP
checkport "RmiRegistry" 1099 TCP
