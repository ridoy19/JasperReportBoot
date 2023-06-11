package com.jasper;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;


/**
 * Deadline Class
 * 
 * Generate this month's closing date and start date for invoice from current date.  
 * 
 * @author yusuke
 * @version 1.0
 * @since 2018/1/24
 */
public class Deadline {
//	//Now Date
//	LocalDateTime nowdate;
//	// Start Date
//	LocalDate startdate;
//	// End Date
//	LocalDate enddate;
//
//	/**
//	 * Constructor
//	 *
//	 * get current date.
//	 *
//	 * generated started date and closing date for invoice.
//	 *
//	 */
//	public Deadline(){
//		// Start Date
//		nowdate=LocalDateTime.now();
//		startdate=LocalDate.now();
//		startdate=startdate.minusMonths(1);
//		startdate=LocalDate.of(startdate.getYear(), startdate.getMonth(),26);
//		// End Date
//		enddate=LocalDate.now();
//		enddate=LocalDate.of(enddate.getYear(), enddate.getMonth(), 25);
//	}
////
////	public String startdate(){
////		return startdate.toString();
////	}
////
////	public String enddate(){
////		return enddate.toString();
////	}
//
//	public void setNowdate(LocalDateTime nowdate) {
//		this.nowdate = nowdate;
//	}
//
//	public LocalDate getStartdate() {
//		return startdate;
//	}
//
//	public void setStartdate(LocalDate startdate) {
//		this.startdate = startdate;
//	}
//
//	public LocalDate getEnddate() {
//		return enddate;
//	}
//
//	public void setEnddate(LocalDate enddate) {
//		this.enddate = enddate;
//	}
//
//	/**
//	 * getParameter Method
//	 *
//	 * get generated date packed in HashMap.
//	 *
//	 * this date change java.sql.date.
//	 * JasperReports don't deal java.time and can deal java.sql.date.
//	 *
//	 * @return parameters  startdate and enddate
//	 */
//	public HashMap<String, Object> getParameter(){
//		HashMap<String,Object> parameters =new HashMap<>();
//		Date sdate=Date.valueOf(startdate);
//		Date edate=Date.valueOf(enddate);
//
//        parameters.put("startdate",sdate);
//        parameters.put("enddate",edate);
//
//        return parameters;
//	}
//
//	public LocalDateTime getNowdate(){
//		return this.nowdate;
//	}

	private LocalDateTime nowDte;
	private LocalDate startDate;
	private LocalDate endDate;

	public LocalDateTime getNowDte() {
		return nowDte;
	}

	public void setNowDte(LocalDateTime nowDte) {
		this.nowDte = nowDte;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
