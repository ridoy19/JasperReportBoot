package com.jasper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * pdfController Class
 * 
 * Controller for View PDF from jrxml template .
 *  
 * @author yusuke
 * @version 1.0
 * @since 2018/1/24
 * 
 */
@RestController
public class pdfController {
	
  private ApplicationContext context;
  @Autowired
  public pdfController(ApplicationContext context) {
    this.context = context;
  }

  private static final Logger LOG = LoggerFactory.getLogger(pdfController.class);
    	
	/**
     * getPdf method
     * 
     * Get JRXML Template name From PathVarialbe and View PDF.
     * 
     * It's include Database connection.
     * 
     * ConnectionString is sensitive privacy.
     * So, Don't upload github.
     * 
     * @param jrxml Template Name. not {@code null}
     * @param response HttpServletResponse need for export PDF Stream.
     * @throws IOException jrxml template not found
     * @throws JRException jrxml complie error
     *  
     */    
	@GetMapping(path = "pdf/{jrxml}")
	@ResponseBody
    public void getPdf(@PathVariable String jrxml,@RequestParam("no") Optional<Integer> no,
      @RequestParam("date") Optional<Date> date, HttpServletResponse response) throws IOException, JRException {
    // Load the report template
    Resource resource = context.getResource("classpath:jasperreports/" + jrxml + (no.isPresent() ? no.get() : "") + ".jrxml");
    InputStream inputStream = resource.getInputStream();
    JasperReport report = JasperCompileManager.compileReport(inputStream);

// Create data source
    Deadline deadline = new Deadline();
    deadline.setStartDate(LocalDate.now());
    deadline.setEndDate(LocalDate.now().plusDays(3));

    List<Deadline> list = new ArrayList<>();
    list.add(deadline);
    JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);

// Set parameters
    Map<String, Object> params = new HashMap<>();
    params.put("no", no.orElse(null));
    params.put("date", date.orElse(null));
    params.put("dataSource", beanCollectionDataSource);
    params.put("startDate", Date.valueOf(deadline.getStartDate()));
    params.put("endDate", Date.valueOf(deadline.getEndDate()));
    params.put("amount", 200.40);

// Log information
//    LOG.info("Template is [{}]. Params are [{}]. Date is [{}]", jrxml, params, deadline.nowdate);
    JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, beanCollectionDataSource);
    // Export the report to PDF and send it as response
    JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

    // Set response content type
    response.setContentType(MediaType.APPLICATION_PDF_VALUE);
  }
}
