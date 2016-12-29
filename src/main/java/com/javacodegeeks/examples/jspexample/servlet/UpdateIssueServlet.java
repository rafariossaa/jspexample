
package com.javacodegeeks.examples.jspexample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javacodegeeks.examples.jspexample.db.DbOperations;

@WebServlet( value = "/updateIssue" )
public class UpdateIssueServlet extends HttpServlet {

	private static final long	serialVersionUID	= -1L;

	@Override
	protected void doGet( final HttpServletRequest request, final HttpServletResponse response )
		throws ServletException, IOException {

		final String newStatus = request.getParameter( "newStatus" );
		final String id = request.getParameter( "id" );

		DbOperations.getTheInstance().updateIssueRecord( id, newStatus );

		response.sendRedirect( "/jspexample/" );
	}
}
