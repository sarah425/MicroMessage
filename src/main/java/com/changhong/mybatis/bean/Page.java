package com.changhong.mybatis.bean;


public class Page {
	
	//数据总条数
	public int totalNumber;
	
	//每个页面的数据条数
	public int pageNumber = 5;
	
	//总页数
	public int totalPage;
	
	//当前页为第几页
	public int currentPage;
	
	//分页查询开始的index
	public int dbIndex;
	
	//每次查询的条数
	public int size;
	

	public int getTotalNumber() {
		return totalNumber;
	}


	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		this.count();
	}


	public int getPageNumber() {
		return pageNumber;
	}


	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getDbIndex() {
		return dbIndex;
	}


	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public void count() {
		
		
		if (this.totalNumber % this.pageNumber == 0) {
			this.totalPage = this.totalNumber/this.pageNumber;
		}else {
			this.totalPage = this.totalNumber/this.pageNumber + 1;
		}
		
		if (this.currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		}
		
		if (this.currentPage < 1) {
			this.currentPage  = 1;
		}
		
		this.dbIndex = (this.currentPage-1) * this.pageNumber;
		this.size = this.pageNumber;
		
	}
	
	
}
