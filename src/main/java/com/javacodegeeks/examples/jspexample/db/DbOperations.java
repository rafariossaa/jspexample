
package com.javacodegeeks.examples.jspexample.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javacodegeeks.examples.jspexample.db.entity.Issue;

public class DbOperations {

	private static DbOperations	theInstance	= new DbOperations();

	private Connection			connection;

	private DbOperations() {
		try {

          String url = "jdbc:mysql://localhost:3306/test";
          String username = "usu1";
          String password = "usu1";

          Class.forName("org.mariadb.jdbc.Driver");
          connection = DriverManager.getConnection(url, username, password);

		  // Create table
		  final PreparedStatement ps = connection
				.prepareStatement( "CREATE TABLE ISSUE( ID INT unique key auto_increment, TITLE VARCHAR(100), OPENEDBY VARCHAR(100), PRIORITY VARCHAR(100), STATUS VARCHAR(100) DEFAULT 'OPEN', COMMENT TEXT, CREATE_DATE TIMESTAMP DEFAULT NOW() )" );
			ps.executeUpdate();
		} catch ( final ClassNotFoundException e ) {
			e.printStackTrace();
		} catch ( final SQLException e ) {
			e.printStackTrace();
		}
	}

	public static DbOperations getTheInstance() {
		return theInstance;
	}

	public void addNewIssueRecord( final String title, final String openedBy, final String priority,
		final String comments ) {

		try {
			final PreparedStatement ps = connection
				.prepareStatement( "INSERT INTO ISSUE( TITLE, OPENEDBY, PRIORITY, COMMENT ) VALUES ( ?, ?, ?, ? )" );

			ps.setString( 1, title );
			ps.setString( 2, openedBy );
			ps.setString( 3, priority );
			ps.setString( 4, comments );

			ps.executeUpdate();
		} catch ( final SQLException e ) {
			e.printStackTrace();
		}
	}

	public List< Issue > getAllIssues() {

		final List< Issue > issueList = new ArrayList< Issue >();

		try {
			final PreparedStatement ps = connection
				.prepareStatement( "SELECT ID, TITLE, OPENEDBY, PRIORITY, STATUS, COMMENT, CREATE_DATE FROM ISSUE" );

			final ResultSet rs = ps.executeQuery();

			while ( rs.next() ) {

				final Issue issue = new Issue();

				issue.setComments( rs.getString( "COMMENT" ) );
				issue.setCreateDate( new Date() );
				issue.setId( rs.getInt( "ID" ) );
				issue.setOpenedby( rs.getString( "OPENEDBY" ) );
				issue.setPriority( rs.getString( "PRIORITY" ) );
				issue.setStatus( rs.getString( "STATUS" ) );
				issue.setTitle( rs.getString( "TITLE" ) );

				issueList.add( issue );
			}

		} catch ( final SQLException e ) {
			e.printStackTrace();
		}

		return issueList;
	}

	public void updateIssueRecord( final String id, final String newStatus ) {
		try {
			final PreparedStatement ps = connection
				.prepareStatement( "UPDATE ISSUE SET STATUS = ? WHERE ID = ?" );

			ps.setString( 1, newStatus );
			ps.setInt( 2, Integer.parseInt( id ) );

			ps.executeUpdate();
		} catch ( final SQLException e ) {
			e.printStackTrace();
		}
	}
}
