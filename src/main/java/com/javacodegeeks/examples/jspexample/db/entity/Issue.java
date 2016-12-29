
package com.javacodegeeks.examples.jspexample.db.entity;

import java.util.Date;

public class Issue {

	private String	title;
	private String	comments;
	private String	openedby;
	private String	priority;
	private String	status;
	private Date	createDate;
	private Integer	id;

	public String getTitle() {
		return title;
	}

	public void setTitle( final String title ) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments( final String comments ) {
		this.comments = comments;
	}

	public String getOpenedby() {
		return openedby;
	}

	public void setOpenedby( final String openedby ) {
		this.openedby = openedby;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority( final String priority ) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( final String status ) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate( final Date createDate ) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId( final Integer id ) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Issue [title=" + title + ", comments=" + comments + ", openedby=" + openedby + ", priority=" + priority
			+ ", status=" + status + ", createDate=" + createDate + ", id=" + id + "]";
	}
}
