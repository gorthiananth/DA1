package com.javacodegeeks.example;

public class DasPojo {

	public DasPojo() {
		super();
	}

	

	public DasPojo(String tableName, String startDate, String endDate, String column, String serviceType, int cpcode,
			String groupBy, int limit, String dasServer, String order, String orderBy) {
		super();
		this.tableName = tableName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.column = column;
		this.serviceType = serviceType;
		this.cpcode = cpcode;
		this.groupBy = groupBy;
		this.limit = limit;
		this.dasServer = dasServer;
		this.order = order;
		this.orderBy = orderBy;
	}



	String tableName = null;
	String startDate;
	String endDate;
	String column = null;
	String serviceType = null;
	int cpcode = 0;
	String groupBy = null;
	int limit = 0;
	String dasServer=null;
	String order=null;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setCpcode(int cpcode) {
		this.cpcode = cpcode;
	}

	String orderBy=null;

	public String getDasServer() {
		return dasServer;
	}

	public void setDasServer(String dasServer) {
		this.dasServer = dasServer;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public int getCpcode() {
		return cpcode;
	}

	public void setCpccode(int cpccode) {
		this.cpcode = cpccode;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "DasPojo [tableName=" + tableName + ", startDate=" + startDate + ", endDate=" + endDate + ", column="
				+ column + ", serviceType=" + serviceType + ", cpcode=" + cpcode + ", groupBy=" + groupBy + ", limit="
				+ limit + ", dasServer=" + dasServer + ", order=" + order + ", orderBy=" + orderBy + "]";
	}

	

	
	

}
