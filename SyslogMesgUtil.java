//$Id: SyslogMesgUtil.java,v 1.1 2008/10/06 10:36:50 venkatramanan Exp $
package com.adventnet.nms.syslog.common.model;

import java.lang.*;
import java.text.*;
import java.util.*;

public class SyslogMesgUtil
{
        
        public static final int LOG_EMERG    = 0; /* system is unusable */
        public static final int LOG_ALERT    = 1; /* action must be taken immediately */
        public static final int LOG_CRIT    = 2; /* critical conditions */
        public static final int LOG_ERR        = 3; /* error conditions */
        public static final int LOG_WARNING    = 4; /* warning conditions */
        public static final int LOG_NOTICE    = 5; /* normal but significant condition */
        public static final int LOG_INFO    = 6; /* informational */
        public static final int LOG_DEBUG    = 7; /* debug-level messages */
        public static final int LOG_ALL        = 8; /* '*' in config, all levels */
        
        public static final int LOG_KERN    = 0; /* kernel messages */
        public static final int LOG_USER    = 1; /* random user-level messages */
        public static final int LOG_MAIL    = 2; /* mail system */
        public static final int LOG_DAEMON    = 3; /* system daemons */
        public static final int LOG_AUTH    = 4; /* security/authorization messages */
        public static final int LOG_SYSLOG    = 5; /* messages generated internally by syslogd */
        public static final int LOG_LPR        = 6; /* line printer subsystem */
        public static final int LOG_NEWS    = 7; /* network news subsystem */
        public static final int LOG_UUCP    = 8; /* UUCP subsystem */
        public static final int LOG_CRON    = 9; /* clock daemon */
        
        /* other codes through 15 reserved for system use */
        
        public static final int LOG_LOCAL0    = 16; /* reserved for local use */
        public static final int LOG_LOCAL1    = 17; /* reserved for local use */
        public static final int LOG_LOCAL2    = 18; /* reserved for local use */
        public static final int LOG_LOCAL3    = 19; /* reserved for local use */
        public static final int LOG_LOCAL4    = 20; /* reserved for local use */
        public static final int LOG_LOCAL5    = 21; /* reserved for local use */
        public static final int LOG_LOCAL6    = 22; /* reserved for local use */
        public static final int LOG_LOCAL7    = 23; /* reserved for local use */
        
        public static final int LOG_NFACILITIES    = 24;    /* current number of facilities */
        
        public static final int LOG_PRIMASK    = 0x07;        /* mask to extract priority part (internal) */            
        public static final int LOG_FACMASK    = 0x03F8;    /* mask to extract facility part */
        public static final int INTERNAL_NOPRI = 0x10;    /* the "no priority" priority */
        
        public static final int LOG_PID        = 0x01;    /* log the pid with each message */
        public static final int LOG_CONS    = 0x02;    /* log on the console if errors in sending */
        public static final int LOG_ODELAY    = 0x04;    /* delay open until first syslog() (default) */
        public static final int LOG_NDELAY    = 0x08;    /* don't delay open */
        public static final int LOG_NOWAIT    = 0x10;    /* don't wait for console forks: DEPRECATED */
        public static final int LOG_PERROR    = 0x20;    /* log to stderr as well */
        
        public static final int    DEFAULT_PORT = 514;

        public final static int MATCH_EQUALS=25;
        
        public final static int MATCH_CONTAINS=26;
        
        public final static int MATCH_STARTS_WITH=27;
        
        public final static int MATCH_ENDS_WITH=28;
        
        public final static int MATCH_EQUALS_IGNORE_CASE=29;
        
        public final static int CONDITION_OR=30;
        
        public final static int CONDITION_AND=31;
        
        
        
        static private Hashtable    facHash;
        static private Hashtable    lvlHash;
        static private Hashtable    priHash;	
        
        static
        {
            facHash = new Hashtable( 20 );

            facHash.put( "KERN",        new Integer(SyslogMesgUtil.LOG_KERN) );
            facHash.put( "KERNEL",        new Integer(SyslogMesgUtil.LOG_KERN) );
            facHash.put( "USER",        new Integer(SyslogMesgUtil.LOG_USER) );
            facHash.put( "MAIL",        new Integer(SyslogMesgUtil.LOG_MAIL) );
            facHash.put( "DAEMON",        new Integer(SyslogMesgUtil.LOG_DAEMON) );
            facHash.put( "AUTH",        new Integer(SyslogMesgUtil.LOG_AUTH) );
            facHash.put( "SYSLOG",        new Integer(SyslogMesgUtil.LOG_SYSLOG) );
            facHash.put( "LPR",            new Integer(SyslogMesgUtil.LOG_LPR) );
            facHash.put( "NEWS",        new Integer(SyslogMesgUtil.LOG_NEWS) );
            facHash.put( "UUCP",        new Integer(SyslogMesgUtil.LOG_UUCP) );
            facHash.put( "CRON",        new Integer(SyslogMesgUtil.LOG_CRON) );
            facHash.put( "LOCAL0",        new Integer(SyslogMesgUtil.LOG_LOCAL0) );
            facHash.put( "LOCAL1",        new Integer(SyslogMesgUtil.LOG_LOCAL1) );
            facHash.put( "LOCAL2",        new Integer(SyslogMesgUtil.LOG_LOCAL2) );
            facHash.put( "LOCAL3",        new Integer(SyslogMesgUtil.LOG_LOCAL3) );
            facHash.put( "LOCAL4",        new Integer(SyslogMesgUtil.LOG_LOCAL4) );
            facHash.put( "LOCAL5",        new Integer(SyslogMesgUtil.LOG_LOCAL5) );
            facHash.put( "LOCAL6",        new Integer(SyslogMesgUtil.LOG_LOCAL6) );
            facHash.put( "LOCAL7",        new Integer(SyslogMesgUtil.LOG_LOCAL7) );
            
            priHash = new Hashtable( 20 );
            
            priHash.put( "EMERG",            new Integer(SyslogMesgUtil.LOG_EMERG) );
            priHash.put( "EMERGENCY",        new Integer(SyslogMesgUtil.LOG_EMERG) );
            priHash.put( "LOG_EMERG",        new Integer(SyslogMesgUtil.LOG_EMERG) );
            priHash.put( "ALERT",            new Integer(SyslogMesgUtil.LOG_ALERT) );
            priHash.put( "LOG_ALERT",        new Integer(SyslogMesgUtil.LOG_ALERT) );
            priHash.put( "CRIT",            new Integer(SyslogMesgUtil.LOG_CRIT) );
            priHash.put( "CRITICAL",        new Integer(SyslogMesgUtil.LOG_CRIT) );
            priHash.put( "LOG_CRIT",        new Integer(SyslogMesgUtil.LOG_CRIT) );
            priHash.put( "ERR",                new Integer(SyslogMesgUtil.LOG_ERR) );
            priHash.put( "ERROR",            new Integer(SyslogMesgUtil.LOG_ERR) );
            priHash.put( "LOG_ERR",            new Integer(SyslogMesgUtil.LOG_ERR) );
            priHash.put( "WARNING",            new Integer(SyslogMesgUtil.LOG_WARNING) );
            priHash.put( "LOG_WARNING",        new Integer(SyslogMesgUtil.LOG_WARNING) );
            priHash.put( "NOTICE",            new Integer(SyslogMesgUtil.LOG_NOTICE) );
            priHash.put( "LOG_NOTICE",        new Integer(SyslogMesgUtil.LOG_NOTICE) );
            priHash.put( "INFO",            new Integer(SyslogMesgUtil.LOG_INFO) );
            priHash.put( "LOG_INFO",        new Integer(SyslogMesgUtil.LOG_INFO) );
            priHash.put( "DEBUG",            new Integer(SyslogMesgUtil.LOG_DEBUG) );
            priHash.put( "LOG_DEBUG",        new Integer(SyslogMesgUtil.LOG_DEBUG) );
        }
        
        
        static public int computeFacility( int code )
        {
            return ( (code & SyslogMesgUtil.LOG_FACMASK) >> 3 );
        }
        
        static public int computePriority( int code )
        {
            return ( code & SyslogMesgUtil.LOG_PRIMASK );
        }
        
 
        static public String getPriorityString( int level )
        {
            switch ( level )
                {
                case SyslogMesgUtil.LOG_EMERG:        return "panic";
                case SyslogMesgUtil.LOG_ALERT:        return "alert";
                case SyslogMesgUtil.LOG_CRIT:        return "critical";
                case SyslogMesgUtil.LOG_ERR:        return "error";
                case SyslogMesgUtil.LOG_WARNING:    return "warning";
                case SyslogMesgUtil.LOG_NOTICE:        return "notice";
                case SyslogMesgUtil.LOG_INFO:        return "info";
                case SyslogMesgUtil.LOG_DEBUG:        return "debug";
                }
            
            return "unknown level='" + level + "'";
        }
        
        static public String getFacilityString( int facility )
        {
            switch ( facility )
                {
                case SyslogMesgUtil.LOG_KERN:        return "kernel";
                case SyslogMesgUtil.LOG_USER:        return "user";
                case SyslogMesgUtil.LOG_MAIL:        return "mail";
                case SyslogMesgUtil.LOG_DAEMON:        return "daemon";
                case SyslogMesgUtil.LOG_AUTH:        return "auth";
                case SyslogMesgUtil.LOG_SYSLOG:        return "syslog";
                case SyslogMesgUtil.LOG_LPR:        return "lpr";
                case SyslogMesgUtil.LOG_NEWS:        return "news";
                case SyslogMesgUtil.LOG_UUCP:        return "uucp";
                case SyslogMesgUtil.LOG_CRON:        return "cron";
                    
                case SyslogMesgUtil.LOG_LOCAL0:        return "local0";
                case SyslogMesgUtil.LOG_LOCAL1:        return "local1";
                case SyslogMesgUtil.LOG_LOCAL2:        return "local2";
                case SyslogMesgUtil.LOG_LOCAL3:        return "local3";
                case SyslogMesgUtil.LOG_LOCAL4:        return "local4";
                case SyslogMesgUtil.LOG_LOCAL5:        return "local5";
                case SyslogMesgUtil.LOG_LOCAL6:        return "local6";
                case SyslogMesgUtil.LOG_LOCAL7:        return "local7";
                }
            
            //return "unknown facility='" + facility + "'";
        	return "unknown facility=" + facility;
	}
        
        
}

