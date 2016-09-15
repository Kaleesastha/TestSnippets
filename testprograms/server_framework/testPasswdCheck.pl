# $Id: testPasswdCheck.pl,v 1.1.1.1 2001/01/10 15:20:13 nms Exp $

# Parsing command line arguments
my($passwdFile,$_uid,$en_passwd) = @ARGV;

# check command line arguments are proper.
if ( $passwdFile eq "" || $_uid eq "" || $en_passwd eq "")
{
   printf "Invalid arguments \n";
   printf "Usage: perl testPasswdCheck.pl <passwdFile> <username> <password> \n";
   exit;

}

open(PASSWD,$passwdFile) || die "Error: cant able to open passwd_file" ;
@lines = <PASSWD>;
$passwd_line="";

foreach $line (@lines)
{
	if( $line =~ /^$_uid:(.*)/)
	{
		$passwd_line=$line;
	}
}
close(PASSWD);

print "passwd line = $passwd_line";
if( $passwd_line eq "" )
{
# The uid is wrong.
	print "Login Incorrect.\n"; 
	exit; 
	#return; 
}

($uidName,$passwd) = split(/:/,$passwd_line);

#Removing last \n character
($passwd) = split(/\n/,$passwd);

$test_passwd = crypt($en_passwd, substr($passwd, 0, 2));


if (!($test_passwd eq $passwd)) 
{
	print "Login Incorrect \n."; 
	exit;
	#return; 
}

print "User name and password are correct \n";
