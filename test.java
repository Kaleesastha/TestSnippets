package test;

import java.io.*;
import java.lang.*;
import java.sql.*;
import com.adventnet.security.authorization.*;
import java.util.*;
public class test
{
   public static void main(String args[])
   {
/*long int x,x1,j;
int i;
 for (j=1;j<=1000000;j++)
 {
  x=j;
  do
  {
   x1= (x % 10);
   if (x1 == 1)
   {
    ++i;
   }
   x = x/10;
  }while(x>=1);
  if (j==i)
  {
   System.out.println("\nThe number is " + j);
  }
 }*/    //Coding code = new Coding();
// To know the length of encrypted password in 1000 tries   
     //int arr[] = new int;	   
     long arr[] = new long[1000];
   for(int i=0;i<=999;i++)
	   {
	   Random ran = new Random(System.currentTimeMillis());
	   arr[i] = ran.nextLong();
	   arr[i] = Math.abs(arr[i]);// Random number generation from Current Time
	   System.out.println("Array = " + arr[i] + "\t length of password = " + encryptLong(arr[i]));
	   //System.out.println(arr[i]);
	   }
         }
      public static int encryptLong(long a)
   {
	   String S = Long.toString(a);
	   String password = new String();
	   int i=0;
   	   String a1 = "ABCDEFGHIJ";
	   String a2 = "KLMNOPQRST";
	   String a3 = "UVWXYZabcd";
	   String a4 = "efghijklmn";
	   String a5 = "opqrstuvwx";
	   String a6 = "yz01234567";
	   String a7 = "~`!@#%^()_";
	   String a8 = "-=+}{]['\";";
	   char b;
	   /* For each & every digit in that random number correspoding character in 
	    * the above array is chosen.*/
	   try{
	   for(i=0;i<=1;i++)
	   {    
		 b = a1.charAt(Integer.parseInt(Character.toString(S.charAt(i))));  
		 password = password.concat(Character.toString(b));
	   }
	   for(i=2;i<=3;i++)
	   {
		 b = a2.charAt(Integer.parseInt(Character.toString(S.charAt(i))));  
		 password = password.concat(Character.toString(b));
		 
	   }
	   for(i=4;i<=5;i++)
	   {     b = a3.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   for(i=6;i<=7;i++)
	   {     b = a4.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   for(i=8;i<=10;i++)
	   {	b = a5.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   for(i=11;i<=12;i++)
	   {	   b = a6.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   for(i=13;i<=14;i++)
	   {    	b = a7.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   for(i=15;i<=18;i++)
	   {	   b = a8.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   password = password.concat(Character.toString(b));
	   }
	   /*for(i=17;i<=18;i++)
	   {	b = a9.charAt(Integer.parseInt(Character.toString(S.charAt(i))));
		   System.out.println("In a9 b = " + b);
		   password = password.concat(Character.toString(b));
	   }*/
	   }
	   catch (Exception ex)
	   {}
	   
	   System.out.println(Coding.convertToNewBase(password));
	   return Coding.convertToNewBase(password).length();
   }
	   

