while true;
	do
		sh startnmsFE.sh &
		echo "before sleep"
		sleep 200
		killall -9 java httpd rmiregistry
		sh ShutDownFE.sh root public
		sleep 10
	done
