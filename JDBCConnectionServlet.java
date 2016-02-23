/*

  gchii/db2servlet1a/JDBCConnectionServlet.java
  Copyright (C) 1999, 2001 Gilbert Carl Herschberger II.
  gchii@mindspring.com
  www.mindspring.com/~gchii/jos

  This program is free software; you can redistribute it and/or
  modify it under the terms of the GNU General Public License
  as published by the Free Software Foundation; either version 2
  of the License, or any later version.

  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.

  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

  Installation : CJOS Project @ cjos.sourceforge.net
  Date Written : GCHII September 21, 1999
  Date Revised : GCHII September 24, 2001
  Upgraded source to include license information.

*/
package gchii.db2servlet1a;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

public class JDBCConnectionServlet
    extends HttpServlet {
  public static final String SELF = "/servlet/gchii.db2servlet1a.JDBCConnectionServlet";
  private static boolean isFirst = true;

  /**
   * Creates a servlet.
   */
  public JDBCConnectionServlet() {
    super();
  }

  /**
   * Process one request.
   */
  public void doGet( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    displayJDBCForm( request, response );
  }

  /**
   * Process one request.
   */
  public void doPost( HttpServletRequest request, HttpServletResponse response )
      throws ServletException, IOException {
    String url = request.getParameter( "url" );
    if ( url == null ) {
      msgError( response, "url parameter is missing" );
      return;
    }

    String user = request.getParameter( "user" );
    if ( user == null ) {
      msgError( response, "user parameter is missing." );
      return;
    }

    String pass = request.getParameter( "pass" );
    if ( pass == null ) {
      msgError( response, "password parameter is missing." );
      return;
    }

    // *T! System.out.println( "url=" + url );

    if ( isFirst ) {
      try {
        Class.forName( "com.ibm.db2.jdbc.app.DB2Driver" );
      }
      catch( ClassNotFoundException e ) {
        // *T! System.out.println( "Class Not Found: " + e.getMessage() );
        msgError( response, "Class Not Found: " + e.getMessage() );
        return;
      }
      isFirst = false;
    }

    Connection connect;
    try {
      connect = DriverManager.getConnection( url, user, pass );
    }
    catch( SQLException e ) {
      // *T! System.out.println( "SQL Exception: " + e.getMessage() );
      msgError( response, "SQL Exception: " + e.getMessage() );
      return;
    }

    try {
      connect.close();
    }
    catch( SQLException e ) {
      // *T! System.out.println( "SQL Exception: " + e.getMessage() );
      msgError( response, "SQL Exception: " + e.getMessage() );
      return;
    }

    msgInfo( response, "JDBC connection was successful." );
  }

  /**
   * Display an HTML entry form for JDBC URL.
   */
  protected void displayJDBCForm( HttpServletRequest request, HttpServletResponse response ) {
    response.setContentType( "text/html" );

    HTMLWriter writer;
    try {
      writer = new HTMLWriter( response.getOutputStream() );
    }
    catch( IOException e ) {
      return;
    }

    writer.setTitle( "JDBC Connection" );
    writer.println( "<H4>Connection</H4>" );
    writer.println( "<form action=\"" + SELF + "\" method=post>" );
    writer.println( "<TABLE BGCOLOR=\"#EFEFEF\">" );
    writer.println( "<TR>" );
    writer.println( "<TD ALIGN=\"RIGHT\">" );
    writer.println( "JDBC URL:" );
    writer.println( "</TD>" );
    writer.println( "<TD>" );
    writer.println( "<input name=\"url\" type=\"text\" length=\"40\">" );
    writer.println( "</TD>" );
    writer.println( "</TR>" );
    writer.println( "<TR>" );
    writer.println( "<TD ALIGN=\"RIGHT\">" );
    writer.println( "AS/400 User Name:" );
    writer.println( "</TD>" );
    writer.println( "<TD>" );
    writer.println( "<input name=\"user\" type=\"text\" length=\"25\">" );
    writer.println( "</TD>" );
    writer.println( "</TR>" );
    writer.println( "<TR>" );
    writer.println( "<TD>" );
    writer.println( "AS/400 Password:" );
    writer.println( "</TD>" );
    writer.println( "<TD>" );
    writer.println( "<input name=\"pass\" type=\"password\" length=\"25\">" );
    writer.println( "</TD>" );
    writer.println( "</TR>" );
    writer.println( "<TR>" );
    writer.println( "<TD ALIGN=\"CENTER\" COLSPAN=\"2\">" );
    writer.println( "<input type=\"submit\" value=\"Connect\">" );
    writer.println( "<input type=\"reset\">" );
    writer.println( "</TD>" );
    writer.println( "</TR>" );
    writer.println( "</TABLE>" );
    writer.println( "</form>" );
    writer.close();
  }

  protected void msgError( HttpServletResponse response, String v ) {
    // *D! System.out.println( "[E]" + v );

    response.setContentType( "text/html" );

    HTMLWriter writer;
    try {
      writer = new HTMLWriter( response.getOutputStream() );
    }
    catch( IOException e ) {
      // *T! System.out.println( "I/O Exception: " + e.getMessage() );
      return;
    }

    writer.setTitle( "JDBC Connection" );
    writer.println( "<H4>Connection Error</H4>" );
    writer.println( "<P>" + v + "</P>" );
    writer.close();
  }

  protected void msgInfo( HttpServletResponse response, String v ) {
    // *D! System.out.println( "[I]" + v );

    response.setContentType( "text/html" );

    HTMLWriter writer;
    try {
      writer = new HTMLWriter( response.getOutputStream() );
    }
    catch( IOException e ) {
      // *T! System.out.println( "I/O Exception: " + e.getMessage() );
      return;
    }

    writer.setTitle( "JDBC Connection" );
    writer.println( "<H4>Connection Information</H4>" );
    writer.println( "<P>" + v + "</P>" );
    writer.close();
  }
}
