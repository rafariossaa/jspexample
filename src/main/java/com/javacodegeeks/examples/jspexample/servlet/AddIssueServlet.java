
package com.javacodegeeks.examples.jspexample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javacodegeeks.examples.jspexample.db.DbOperations;

@WebServlet( value = "/addIssue" )
public class AddIssueServlet extends HttpServlet {

	private static final long	serialVersionUID	= -1L;

	@Override
	protected void doPost( final HttpServletRequest request, final HttpServletResponse response )
		throws ServletException, IOException {

		final String title = request.getParameter( "title" );
		final String openedBy = request.getParameter( "openedby" );
		final String priority = request.getParameter( "priority" );
		final String comments = request.getParameter( "comments" );

		DbOperations.getTheInstance().addNewIssueRecord( title, openedBy, priority, comments );

		response.sendRedirect( "/jspexample/" );
	}
}
