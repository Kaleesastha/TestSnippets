
package com.adventnet.security.authorization;

public class Coding{

//      public static void main(String args[]){
//          callmain(args);
//      }
        
//          try{
//              System.out.println(args[0]);
//              String temp = convertToNewBase(args[0]);
//              System.out.println(temp);
//              try{
//                  System.out.println(convertFromBase(temp));
//              }catch (Exception e){
//                  System.out.println(e);
//              }
//          }catch (Exception e){
//              System.out.println(e);
//              System.out.println(e.getMessage());
//              e.printStackTrace();

//          }
//      callmain(String[] args){
//          String temp = convertToNewBase(args[0]);
//      }
    
    public static String convertToNewBase(String string) {
        //  System.out.println("TO CONVERT " + string);
        string = new StringBuffer().append(string).reverse().toString();
        int[] bits = new int[string.length()];
        StringBuffer whole = new StringBuffer();
        for(int k = 0; k < string.length(); k++)
        {
            bits[k] = (int)string.charAt(k) - 28;
	    System.out.println("-->"+bits[k]);

            StringBuffer temp3 = new StringBuffer().append(bits[k]);
	    System.out.println("---->"+bits[k]);

            while(temp3.length() != 2) {
                temp3.insert(0,"0");
	    System.out.println("@@@>"+temp3);
            }
	    System.out.println("==>"+temp3);
            whole.append(temp3.toString());
        }
        //whole.reverse();
        string = whole.toString();
        return(baseConvertor(string));
    }


    private static String baseConvertor(String name1) 
    {
        StringBuffer encoded = new StringBuffer();
        //String name1 = whole.toString();
        String base[] = new String[60];
        base[0] = "0" ;base[1] = "1" ;base[2] = "2" ;base[3] = "3" ;
        base[4] = "4" ;base[5] = "5" ;base[6] = "6" ;base[7] = "7" ;
        base[8] = "8" ;base[9] = "9" ;base[10] = "a";base[11] = "b";
        base[12] = "c";base[13] = "d";base[14] = "e";base[15] = "f";
        base[16] = "g";base[17] = "h";base[18] = "i";base[19] = "j";
        base[20] = "k";base[21] = "l";base[22] = "m";base[23] = "n";
        base[24] = "o";base[25] = "p";base[26] = "q";base[27] = "r";
        base[28] = "s";base[29] = "t";base[30] = "u";base[31] = "v";
        base[32] = "w";base[33] = "x";base[34] = "y";base[35] = "z";
        base[36] = "A";base[37] = "B";base[38] = "C";base[39] = "D";
        base[40] = "E";base[41] = "F";base[42] = "G";base[43] = "H";
        base[43] = "I";base[44] = "J";base[45] = "K";base[46] = "L";
        base[47] = "M";base[48] = "N";base[49] = "O";base[50] = "P";
        base[51] = "Q";base[52] = "R";base[53] = "S";base[54] = "T";
        base[55] = "U";base[56] = "V";base[57] = "W";base[58] = "X";
        base[59] = "Y";
        //base[60] = "Z";
        /* base[61] is not defined.base[62] = "!";
           base[63] = "@";base[64] = "#";base[65] = "$";base[66] = "%";
           base[67] = "^";base[68] = "&";base[69] = "*";base[70] = "(";
           base[71] = ")";base[72] = "-";base[73] = "_";base[74] = "=";
           base[75] = "+";base[76] = "|";base[77] = ";";base[78] = ">";
           base[79] = "[";base[80] = "]";base[81] = "{";base[82] = "}";
           base[83] = "/";base[84] = "?";base[85] = ".";base[86] = ",";
           base[87] = "<";		*/
        long quotient = 0;
        long reminder = 0;
        boolean check = false;
        int k1 = 0;
        String fame = new String(name1);
        for(int j1 = 0; j1 < name1.length(); j1++) {
            fame=name1.substring(j1);
            StringBuffer part = new StringBuffer();
            int p =0;
            for(k1 =0,reminder=0; 
                part.length() != 5 && k1 < fame.length(); 
                k1++) 
            {
                String test = fame.substring(p,k1+1);
				//System.out.println("test = " + test);
                if(test.equals("0")) { 
                    part.append("0");p++;
                    continue;
                }
                quotient = Long.parseLong(test) / 60;
                reminder = Long.parseLong(test) % 60;
		// This checks if divided part is of five digit length 
				// in base format
                StringBuffer elabtemp = new StringBuffer();
                if(quotient!=0) {elabtemp.append(quotient).toString();}
                String elab = elabtemp.toString();
                StringBuffer temp1 = new StringBuffer();
                for(int q = 0; q < elab.length(); q++) {
                    if((elab.length() != (q+1)) 
                       && (!(elab.substring(q,q+1)).equals("0")) 
                       && (Integer.parseInt(elab.substring(q,q+2)) < 60)) {
                        temp1.append(base[Integer.parseInt(elab.
                                                           substring(q,q+2))]);
                        q++;
                    }
                    else {
                        temp1.append(Integer.parseInt(elab.substring(q,q+1)));
                    }
                }
                if(temp1.length() == (5-part.length()) || k1 == 
                   (fame.length() - 1)) 
                {
                    part.append(temp1.toString());
                }
            }
            part.append(base[(int)reminder]);
            encoded.append(part.toString());
            //System.out.println(" part=" + part);
            j1 = j1 + k1-1;
        }
        String toBeReturned = encoded.toString();
        int triplepos = 0;
        while( (triplepos = toBeReturned.indexOf("000")) != -1) {
            toBeReturned = new StringBuffer(toBeReturned).
                replace(triplepos, triplepos+3,"Z").toString();
        }

        return(toBeReturned);
        //System.out.println(encoded);
    }
    
    public static String convertFromBase(String encodedString) 
        throws Exception
    {
        encodedString = baseDeconvertor(encodedString);
        //System.out.println("ENCODED =" + encodedString);
            
        StringBuffer temp2 = new StringBuffer().append(encodedString);
        String[] strbits = new String[temp2.length()/2];
        int[] intbits = new int[temp2.length()/2];
        StringBuffer fin = new StringBuffer();
        for(int k = 0; k < (temp2.length()/2); k++)
        {
            strbits[k] = temp2.toString().substring(2*k,(2*k + 2));
            intbits[k] = Integer.parseInt(strbits[k]) + 28;
            fin.append((char)intbits[k]);
        }
        fin = new StringBuffer().append(fin).reverse();
        return fin.toString();
            
    }
    
    
    private static String baseDeconvertor(String input) throws Exception 
    {
        String base[] = new String[60];
        base[0] = "0";base[1] = "1";base[2] = "2";base[3] = "3";
        base[4] = "4";base[5] = "5";base[6] = "6";base[7] = "7";
        base[8] = "8";base[9] = "9";base[10] = "a";base[11] = "b";
        base[12] = "c";base[13] = "d";base[14] = "e";base[15] = "f";
        base[16] = "g";base[17] = "h";base[18] = "i";base[19] = "j";
        base[20] = "k";base[21] = "l";base[22] = "m";base[23] = "n";
        base[24] = "o";base[25] = "p";base[26] = "q";base[27] = "r";
        base[28] = "s";base[29] = "t";base[30] = "u";base[31] = "v";
        base[32] = "w";base[33] = "x";base[34] = "y";base[35] = "z";
        base[36] = "A";base[37] = "B";base[38] = "C";base[39] = "D";
        base[40] = "E";base[41] = "F";base[42] = "G";base[43] = "H";
        base[43] = "I";base[44] = "J";base[45] = "K";base[46] = "L";
        base[47] = "M";base[48] = "N";base[49] = "O";base[50] = "P";
        base[51] = "Q";base[52] = "R";base[53] = "S";base[54] = "T";
        base[55] = "U";base[56] = "V";base[57] = "W";base[58] = "X";
        base[59] = "Y";
        //base[60] = "Z";
        /* base[61] is not defined.base[62] = "!";
           base[63] = "@";base[64] = "#";base[65] = "$";base[66] = "%";
           base[67] = "^";base[68] = "&";base[69] = "*";base[70] = "(";
           base[71] = ")";base[72] = "-";base[73] = "_";base[74] = "=";
           base[75] = "+";base[76] = "|";base[77] = ";";base[78] = ":";
           base[79] = "[";base[80] = "]";base[81] = "{";base[82] = "}";
           base[83] = "/";base[84] = "?";base[85] = ".";base[86] = ",";
           base[87] = "<";base[88] = ">";*/

        int Zpos = 0;
        while( (Zpos = input.indexOf("Z")) != -1) {

            String temp1 = input.substring(0,Zpos);
            String temp2 = input.substring(Zpos+1);
            input = new StringBuffer(temp1).
                append("000").
                append(temp2).
                toString();

            /** This is jdk1.2.2 dependant */
            //StringBuffer tempor = new StringBuffer(input);
            //tempor.delete(Zpos,Zpos+1);
            //tempor.insert(Zpos,"000");
            //input = tempor.toString();
            //input = new StringBuffer(input).replace(Zpos, Zpos+1, "000")
            //		.toString();
        }
        StringBuffer answer = new StringBuffer();
        int k = 0;
        long reminder = 0;
        int co = input.length()/6;
        while(k < co) 
        {
            String part = input.substring(6*k,6*k+6);
            StringBuffer partnum = new StringBuffer();
            boolean startnum = false;	
            for(int i = 0; i < 5; i++) 
            {
                boolean isthere = false;
                for(int pos = 0;isthere == false;pos++) 
                {
                    if(part.substring(i,i+1).equals(base[pos])) 
                    {
                        isthere = true;
                        partnum.append(pos);
                        if(pos == 0) {
                            if(startnum == false) answer.append("0");
                        }
                        else startnum = true;
                    }
                }
            }
            boolean isthere = false;
            for(int pos = 0;isthere == false;pos++) 
            {
                if(part.substring(5).equals(base[pos])) {
                    isthere = true;
                    reminder = (long)pos;
                }
            }
            if(partnum.toString().equals("00000"))
            {
                if(reminder != 0) {

                    String tempo = new Long(reminder).toString();
                    String temp1 = answer.toString().substring( 0,
                                                                (answer.length()-tempo.length()) );
                    answer = new StringBuffer(temp1).append(tempo);
			
                    //answer.delete(answer.length()-tempo.length(),answer.length());
                    //answer.insert(answer.length(),tempo);
                    //answer.replace(answer.length()-tempo.length(),answer.length(),tempo);
                }
            }
            else {
                answer.append((Long.parseLong(partnum.toString())*60 + 
                               reminder));
            }
            k++;
        }
        if(input.length() % 6 != 0) 
        {
            String end = input.substring(6*k);
            StringBuffer partnum = new StringBuffer();
            if(end.length() > 1) 
            {
                int i = 0;
                boolean startnum = false;
                for(i = 0; i < end.length()-1; i++) 
                {
                    boolean isthere = false;
                    for(int pos = 0;isthere == false;pos++) 
                    {
                        if(end.substring(i,i+1).equals(base[pos])) 
                        {
                            isthere = true;
                            partnum.append(pos);
                            if(pos == 0) {
                                if(startnum == false) answer.append("0");
                            }
                            else startnum = true;
                        }
                    }
                }
                boolean isthere = false;
                for(int pos = 0;isthere == false;pos++) {
                    if(end.substring(i).equals(base[pos])) {
                        isthere = true;
                        reminder = (long)pos;
                    }
                }
                answer.append(Long.parseLong(partnum.toString())*60 + 
                              reminder);
            }
            else 
            {
                boolean isthere = false;
                for(int pos = 0;isthere == false;pos++) 
                {
                    if(end.equals(base[pos])) {
                        isthere = true;
                        reminder = (long)pos;
                    }
                }
                answer.append(reminder);
            }
        }
        return(answer.toString());
    }
}











