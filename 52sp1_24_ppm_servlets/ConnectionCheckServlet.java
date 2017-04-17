// $Id: ConnectionCheckServlet.java,v 1.1 2006/08/29 13:57:03 build Exp $

package com.adventnet.nms.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

public class ConnectionCheckServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<HTML>");
		out.println("<BODY bgColor=white>");
		out.println("<BR>");
		out.println("<BR>");
		out.println("<CENTER>");
		out.println("<FONT size=6 color=green>");
		out.println("Successfully connected to the server !!");
		out.println("</FONT>");
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");

	}
}
