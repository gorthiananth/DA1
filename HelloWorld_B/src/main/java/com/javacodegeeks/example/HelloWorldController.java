package com.javacodegeeks.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jboss.logging.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;

@RestController
public class HelloWorldController {
	

	   String  line = null;
	    String  tableName=null;
		String    startDate;
		String  endDate;
		String column=null;
		String serviceType=null;
		int  cpcode=0;
		String groupBy=null;
		int  limit=0;
		String dasServer=null;
	
	
	

	@GetMapping("/health")
	public String sayHello() {
		return "i am  ok";
	}
	
	@PostMapping(path="/dasclient")
	@ResponseBody
	public Map<String, Object> dasClient(@RequestBody DasPojo poj ) throws ParseException
	{
	       StringBuilder billder = new StringBuilder();

		try {
			//DasPojo poj= new DasPojo("ma_stat_epd_-_F_time", datatoString("06-09-2019 00:00:00"),  datatoString("10-09-2019 00:00:00"), "sum(egress_hits),cpcode", "htp", 310860, "egress_hits,cpcode", 100);
			//        	Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript1.sh  ma_stat_epd_-_F_time 1546886512  1547055712 cpcode sum(egress_hits) htp 310860 egress_hits ,cpcode 100"); 

			tableName=poj.getTableName();
			startDate=poj.getStartDate();
			endDate=poj.getEndDate();
			column=poj.getColumn();
			serviceType=poj.getServiceType();
			cpcode=poj.getCpcode();
			groupBy= poj.getGroupBy();
			limit=poj.getLimit();
			dasServer=poj.getDasServer();
			
		  //  Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript.sh  ma_stat_epd_-_F_time 1567967400  1568053799 egress_hits htp 310860 egress_hits 100"); 
	       Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript.sh "+tableName+" "+datatoString(startDate)+"  "+datatoString(endDate)+" "+column+" "+serviceType+" "+cpcode+" "+groupBy+" "+limit+" "+dasServer+""); 
			BufferedReader in = new BufferedReader(
		                                new InputStreamReader(p.getInputStream()));

		 	billder.append(poj.getColumn());
		 	billder.append(System.getProperty("line.separator"));
		 	billder.append("---------------------------------");
		 	billder.append(System.getProperty("line.separator"));
		 	line=in.readLine().replace("OK", "");
		            while ((line = in.readLine()) != null) {
		            	billder.append(line);
		            billder.append("\n");
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		System.out.println(billder);
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("", billder.toString());
		return res;
		
	}
	
	 public  String datatoString(String data1) throws ParseException
	    {
	    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	    Date date = df.parse(data1);
	    long epoch = date.getTime()/1000;
		return epoch+"";
	    	
	    }
}
