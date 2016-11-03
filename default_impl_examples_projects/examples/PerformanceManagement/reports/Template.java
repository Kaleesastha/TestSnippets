/*
 * $Id: Template.java,v 1.1.1.1 2006/11/16 15:39:58 gramkumar Exp $
 *
 * Copyright (c) 2002 Adventnet, Inc. All Rights Reserved.
 * Please read the associated COPYRIGHTS file for more details.
 *
 * ADVENTNET, INC. MAKES NO REPRESENTATIONS OR WARRANTIES  ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. ADVENTNET, INC. SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS
 * SOFTWARE OR ITS DERIVATIVES.
 */

package com.adventnet.nms.example.report;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

/** 
 * Encapsulates a template in a private <code>char[]</code>. The
 * <code>writeReport</code> method writes out the report based on the stored
 * template with the template variables substituted with values from the passed
 * Map.
 *
 * @author  Paul Ponraj S.P.
 * @version $Revision: 1.1.1.1 $
 *
 * @since WebNMS 2.3 + SP6
 */
public class Template {
	/* 
	 * Templates are HTML files with HTML content interspersed with:
	 *		1. template single variables, 
	 *		2. template block variables and
     *      3. template optional variables.
	 * 1. Template single variables ($xyz$): These variables are replaced with
	 * their corresponding value. One variable one value mapping.
	 * 2. Template block variables ($$xyz$$): A template block is delimited by a
	 * template block start variable and a corresponding template block end
	 * variable. The block itself may contain template single variables or
	 * nested template block variables.
	 * For example, 
	 *		$topData1$
	 *		$topData2$
	 *		$$block1$$
	 *		<TR>
	 *			$$block2$$
	 *			<TD>$data1$</TD> <TD>$data2$</TD>
	 * 			$$block2$$
	 *		</TR>
	 *		$$block1$$
     * substituteTemplateParams method accepts a Map that has keys as top-level
     * template single variables ($topData1$) or top-level template block
     * variables ($$block1$$). Template block variables contain an array of Maps
     * with each Map containing template variables for that block.
     * 3. Template optional block variables ($?xyz?$): This is similar to the
     * template block variables above, except that if the block variable does
     * not have a value (in the Map that is passed) then the block is
     * suppressed.
     *
     * Note: This works for non-HTML textual files also.
	 */
	 
    /** Represents a template single token. */
    private static final byte SINGLE_TOKEN = 1;
   	 
    /** Represents a template block token. */
    private static final byte BLOCK_TOKEN = 2;
   	 
    /** Represents a template optional block token. */
    private static final byte OPTIONAL_BLOCK_TOKEN = 3;

	/** Maximum number of characters in a token */
	private final byte MAX_TOK_CHARS_SIZE;
	
	/** Internal template storage array. */
	private final char[] template; 
	
	
	/** 
	 * Reads in the contents of the file given by <code>template</code> as the
	 * template. <code>maxTokCharsSize</code> gives the maximum number of
	 * characters that can be in the token between the token delimiters.
	 *
	 * @param  template
	 *		   The <code>Reader</code> to a template HTML file.
	 * @param  maxTokCharsSize 
     *		   Maximum number of characters that can be in the token (excluding
     *		   the '$', '$$' and '$?' delimiters for template single, block and
     *		   optional block variables respectively). If this is &lt;= 0, a
     *		   size of 40 is assumed.
	 * @throws IOException 
	 *		   If <code>fileName</code> could not be read or an IO error occurs
	 *		   while reading the file.
	 */ 
	public Template(Reader template, byte maxTokCharsSize) throws IOException {
		BufferedReader br = null;
		MAX_TOK_CHARS_SIZE = (maxTokCharsSize <= 0) ? 40 : maxTokCharsSize;
		try {
			br = new BufferedReader(template);
			CharArrayWriter caw = new CharArrayWriter();
			String s;
			String lineSeparator = System.getProperty("line.separator");
			while ((s = br.readLine()) != null) {
				caw.write(s);
				caw.write(lineSeparator);
			}
			// Store the file contents in template
			this.template = caw.toCharArray();
			caw.close();
		} finally {
			if (br != null) br.close();
		}
	}

	/**
	 * Writes out the report into the <code>reportFile</code> substituting the
	 * template single and block variables with values from
	 * <code>parameters</code>. <code>reportFile</code> is not closed. It has to
	 * be closed externally.
	 *
	 * @param  parameters  <code>Map</code> containing the template single or
	 * block variables as keys and their corresponding <code>Strings</code> or
	 * <code>Map[]</code> as values.
	 * @param  w  The Writer to use to write out the report using the template.
	 * @throws ClassCastException If for a template block token a corresponding
	 * HashMap array is not found.
	 * @throws IOException If an IOException occurs while using reportFile.
	 * @throws NullPointerException If <code>parameters</code> or
	 * <code>reportFile</code> is null.
	 */
	public void writeReport(Map parameters, Writer w) 
			throws IOException {
        BufferedWriter reportFile = new BufferedWriter(w);
		substituteTemplateParams(0, template.length - 1, parameters,reportFile);
	}
	
	/**
	 * The values from the <code>parameters</code> Map are substituted in the
	 * template and written out to the <code>Writer w</code>. A
	 * <code>BufferedWriter</code> is wrapped around the passed
	 * <code>Writer</code> for efficiency.
	 * 
	 * @throws ClassCastException If for a template block token a corresponding
	 * Map array is not found.
	 * @throws NullPointerException If <code>parameters</code> or
	 * <code>reportFile</code> is null.
	 */
	private void substituteTemplateParams(int startIndex, int endIndex,
			Map parameters, Writer reportFile) throws IOException { 
		//System.err.println("Start of call, SI: " + startIndex 
		//					+ ", EI: " + endIndex);
		String token;
		StringBuffer report = new StringBuffer();
		for (int index = startIndex; index <= endIndex; index++) {
			if (template[index] == '$') {
				if (((index + 1) < endIndex) && (template[index + 1] == '$')) {
                    // Block token
					token = getToken(index, endIndex, BLOCK_TOKEN);
					if (token == null) {
                        // This is to handle "$$xyz$" type variables which
                        // should be read as "$xyz$".
						reportFile.write(template[index]);
						continue;
					}
                    index = handleBlockToken(token, index, endIndex, 
                                             parameters, reportFile);
                } else if (((index + 1) < endIndex) 
                        && (template[index + 1] == '?')) {
                    // Optional block token
					token = getToken(index, endIndex, OPTIONAL_BLOCK_TOKEN);
					if (token == null) {
						reportFile.write(template[index]);
						continue;
					}
                    index = handleOptionalBlockToken(token, index, endIndex, 
                                             parameters, reportFile);
				} else { 
                    // Single token
					token = getToken(index, endIndex, SINGLE_TOKEN);
					if (token == null) {
						reportFile.write(template[index]);
						continue;
					}
                    index = handleSingleToken(token, index, parameters,
                                              reportFile);
				}
			} else {
				reportFile.write(template[index]);
			}
		} // repeat till endIndex is reached.
		
		reportFile.flush();
		//System.err.println("End of call, SI: " + startIndex 
		//					+ ", EI: " + endIndex);
	} // end of method substituteTemplateParams.

    private int handleBlockToken(String token, int startIndex, int endIndex, 
            Map parameters, Writer reportFile) throws IOException {
        //System.err.println("Handle block called ..."); 
        //System.err.println("SB: " + token);
        if (!parameters.containsKey(token)) {
            // If token is not present in parameters just print it
            // into the report.
            reportFile.write(token);
            startIndex += token.length() - 1;
        } else {
            // If token is present...
            // Get the start and end of block and substitute the
            // values for all the tokens.
            // If start and end block tokens are not present, this
            // just prints the start block token as such into the
            // report.
            int blockStartIndex = startIndex + token.length();
            int blockIndex = blockStartIndex;
            String endBlock = null;
            for ( ; blockIndex <= endIndex; blockIndex++) {
                if ((template[blockIndex] == '$') 
                        && ((blockIndex + 1) < endIndex) 
                        && (template[blockIndex + 1] == '$')) {
                    endBlock = getToken(blockIndex, endIndex,
                            BLOCK_TOKEN);
                    if (endBlock == null) {
                        continue;
                    } 
                    
                    if (endBlock.equals(token)) {
                        //System.err.println("EB: " + endBlock);
                        break;
                    } else {
                        blockIndex += endBlock.length() - 1;
                    }
                }
            }

            if (endBlock != null && endBlock.equals(token)) {
                int blockEndIndex;
                blockEndIndex = blockIndex - 1;
                // Get the Map array for the template block
                // containing the template variables for the block.
                Map[] m = (Map[])parameters.get(token);
                int length = m.length;
                // Substitute the template variables in the template
                // block with values from each element in the
                // Map array.
                for (int i = 0; i < length; i++) {
                    substituteTemplateParams(blockStartIndex,
                            blockEndIndex, m[i], reportFile);
                }
                startIndex = blockEndIndex + token.length();
            } else {
                reportFile.write(token);
                startIndex += token.length() - 1;
            }
        }
        
        return startIndex;
    }
	
    private int handleOptionalBlockToken(String token, int startIndex, 
            int endIndex, Map parameters, Writer reportFile) 
            throws IOException {
        //System.err.println("Handle optional block called ..."); 
        //System.err.println("SOB: " + token);

        int blockStartIndex = startIndex + token.length();
        int blockIndex = blockStartIndex;
        String endBlock = null;
        for ( ; blockIndex <= endIndex; blockIndex++) {
            if ((template[blockIndex] == '$') 
                    && ((blockIndex + 1) < endIndex) 
                    && (template[blockIndex + 1] == '?')) {
                endBlock = getToken(blockIndex, endIndex, OPTIONAL_BLOCK_TOKEN);
                if (endBlock == null) {
                    continue;
                } 

                if (endBlock.equals(token)) {
                    //System.err.println("EOB: " + endBlock);
                    break;
                } else {
                    blockIndex += endBlock.length() - 1;
                }
            }
        }

        if (endBlock != null && endBlock.equals(token)) {
            int blockEndIndex;
            blockEndIndex = blockIndex - 1;
            // Get the Map for the template optional block containing the
            // template variables for the optional block.
            Map hm = (Map)parameters.get(token);

            // If token is not present in parameters skip that block. DO NOT
            // print the block into the report.
            if (hm != null) {
                substituteTemplateParams(blockStartIndex, blockEndIndex, hm,
                                         reportFile);
            }
            startIndex = blockEndIndex + token.length();
        } else {
            reportFile.write(token);
            startIndex += token.length() - 1;
        }

        return startIndex;
    }
	
    private int handleSingleToken(String token, int startIndex, Map parameters,
            Writer reportFile) throws IOException {
        //System.err.println("Handle single token called ..."); 
        //System.err.println("T: " + token);
        //System.out.println("DBG: " + parameters); 
        if (parameters.containsKey(token)) {
            String temp = (String)parameters.get(token);
            if (temp == null) temp = "null";
            reportFile.write(temp);
        } else {
            // If token is not present in parameters just print it
            // into the report.
            reportFile.write(token);
        }
        startIndex += token.length() - 1;

        return startIndex;
    }
	
	/**
     * Given a <code>startIndex</code> and an <code>endIndex</code> into the
     * private <code>char[] </code>template</code>, a token with $&lt;max. token
     * chars&gt;$ or $$&lt;max. token chars&gt;$$ or $?&lt;max. token
     * chars&gt;?$ is returned. 
     * <p>A token can be formed of characters 'A' to 'Z', 'a' to 'z', numbers
     * and any special characters other than '$'.  If starting $ or $$ or $? was
     * found and no matching ending $ or $$ or $? could be found, then this
     * method returns null.  
	 * <p>
	 * <b>NOTE:</b> This mtd. should never throw
	 * <code>ArrayIndexOutOfBoundsException</code>. If it throws one then it is
	 * a bug.
	 * <p>
	 * Assuming MAX_TOK_CHARS_SIZE = 10, this is how getToken mtd. behaves:
	 * <p>
	 * <table align="center" border="1">
	 * <tr bgcolor="#CCCCCC">
	 * <td align="center">VALUE</td><td align="center">TOKEN</td></tr>
	 * <tr><td align="center" colspan="2">(tokenType = SINGLE_TOKEN)</td></tr>
	 * <tr><td>$xyz$asdf</td><td>$xyz$</td></tr>
	 * <tr><td>$1234567890$</td><td>$1234567890$</td></tr>
	 * <tr><td>$123456789012345$</td><td>null</td></tr>
	 * <tr><td>$xyz$$abc def $$jkl$$</td><td>$xyz$ -> $abc def $ -> $jkl$ ->
	 * ...</td></tr>
	 * <tr><td align="center" colspan="2">(tokenType = BLOCK_TOKEN)</td></tr>
	 * <tr><td>$$xyz$$</td><td>$$xyz$$</td></tr>
	 * <tr><td>$$xyz$</td><td>null</td></tr>
	 * <tr><td>$$1234567890$$</td><td>$$1234567890$$</td></tr>
	 * <tr><td>$$12345678901234$$</td><td>null</td></tr>
     * <tr><td align="center" colspan="2">(tokenType =
     * OPTIONAL_BLOCK_TOKEN)</td></tr>
	 * <tr><td>$?xyz?$</td><td>$?xyz?$</td></tr>
	 * <tr><td>$?xyz$</td><td>null</td></tr>
	 * <tr><td>$?1234567890?$</td><td>$?1234567890?$</td></tr>
	 * <tr><td>$?12345678901234?$</td><td>null</td></tr>
	 * </table>
	 */
	private String getToken(int startIndex, int endIndex, byte tokenType) {
		StringBuffer token = new StringBuffer();
		boolean validToken = false;

		if (tokenType == BLOCK_TOKEN) { 
            // search for block token
			token.append("$$");
			startIndex += 2;
			// avoid ArrayIndexOutOfBoundsException
			int eI;
			if ((eI = startIndex + MAX_TOK_CHARS_SIZE + 1) <= endIndex) {
				endIndex = eI;
			}
			
			for (int tokIndex = startIndex; tokIndex <= endIndex; tokIndex++) {
				if ((template[tokIndex] == '$') && ((tokIndex + 1) <= endIndex) 
						&& (template[tokIndex + 1] == '$')) {
					token.append("$$");
					validToken = true;				
					break;
				} else {
					token.append(template[tokIndex]);
				}
			}
		} else if (tokenType == OPTIONAL_BLOCK_TOKEN) { 
            // search for optional block token
			token.append("$?");
			startIndex += 2;
			// avoid ArrayIndexOutOfBoundsException
			int eI;
			if ((eI = startIndex + MAX_TOK_CHARS_SIZE + 1) <= endIndex) {
				endIndex = eI;
			}
			
			for (int tokIndex = startIndex; tokIndex <= endIndex; tokIndex++) {
				if ((template[tokIndex] == '?') && ((tokIndex + 1) <= endIndex) 
						&& (template[tokIndex + 1] == '$')) {
					token.append("?$");
					validToken = true;				
					break;
				} else {
					token.append(template[tokIndex]);
				}
			}
		} else { 
            // search for ordinary token
			token.append('$');
			startIndex++;
			// avoid ArrayIndexOutOfBoundsException
			int eI;
			if ((eI = startIndex + MAX_TOK_CHARS_SIZE) <= endIndex) {
				endIndex = eI;
			}
			
			for (int tokIndex = startIndex; tokIndex <= endIndex; tokIndex++) {
				if (template[tokIndex] == '$') {
					token.append('$');
					validToken = true;
					break;
				} else {
					token.append(template[tokIndex]);
				}
			}
		}
		return (validToken ? token.toString() : null);
	}
}
