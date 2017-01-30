TCP_PORT=4567
SECONDARY_PORT=36006
HOST=venkat-0773

echo "***  $HOST:$TCP_PORT -TLSv1 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $TCP_PORT  -tls1
echo "***  $HOST:$TCP_PORT -TLSv1.1 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $TCP_PORT  -tls1_1
echo "***  $HOST:$SECONDARY_PORT -TLSv1 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $SECONDARY_PORT  -tls1
echo "***  $HOST:$SECONDARY_PORT -TLSv1.1 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $SECONDARY_PORT  -tls1_1
echo "***  $HOST:$TCP_PORT -TLSv1.2 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $TCP_PORT  -tls1_2
echo "***  $HOST:$SECONDARY_PORT -TLSv1 ******" >> nohup.out
nohup openssl s_client -host $HOST -port $SECONDARY_PORT  -tls1_2
