package com.javacodegeeks.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

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

	String line = null;
	String tableName = null;
	String startDate;
	String endDate;
	String column = null;
	String serviceType = null;
	int cpcode = 0;
	String groupBy = null;
	int limit = 0;
	String dasServer = null;
	String orderBy=null;
	String order=null;

	@GetMapping("/health")
	public String sayHello() {
		return "i am  ok";
	}

	@PostMapping(path = "/dasclient")
	@ResponseBody
	public List<List<String>> dasClient(@RequestBody DasPojo poj) throws ParseException {
		StringBuilder billder = new StringBuilder();
		List<List<String>> list = new ArrayList<List<String>>();
		try {
			// DasPojo poj= new DasPojo("ma_stat_epd_-_F_time", datatoString("06-09-2019
			// 00:00:00"), datatoString("10-09-2019 00:00:00"), "sum(egress_hits),cpcode",
			// "htp", 310860, "egress_hits,cpcode", 100);
			// Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript1.sh
			// ma_stat_epd_-_F_time 1546886512 1547055712 cpcode sum(egress_hits) htp 310860
			// egress_hits ,cpcode 100");

			tableName = poj.getTableName().trim();
			startDate = poj.getStartDate().trim();
			endDate = poj.getEndDate().trim();
			column = poj.getColumn().trim();
			serviceType = poj.getServiceType().trim();
			cpcode = poj.getCpcode();
			groupBy = poj.getGroupBy().trim();
			limit = poj.getLimit();
			dasServer = poj.getDasServer().trim();
			orderBy=poj.getOrderBy().trim();
			order= poj.getOrder().trim();

			// Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript.sh
			// ma_stat_epd_-_F_time 1567967400 1568053799 egress_hits htp 310860 egress_hits
			// 100");
			Process p = Runtime.getRuntime()
					.exec("sh  ncscript.sh " + tableName + " " + datatoString(startDate) + "  "
							+ datatoString(endDate) + " " + column + " " + serviceType + " " + cpcode + " " + groupBy
							+ " " + limit + " " + dasServer + " "+orderBy+" "+order+"  ");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));

			/*billder.append(poj.getColumn());
			billder.append(System.getProperty("line.separator"));
			billder.append("---------------------------------");
			billder.append("\n");*/
			int index = 0;
			list.add(Arrays.asList(poj.getColumn().toUpperCase().split("\\,")));
			
			line = in.readLine().replace("OK", "");
			String result = null;
			while ((line = in.readLine()) != null) {
				if (poj.getColumn().contains("timestamp")) {
					String[] split = poj.getColumn().split("\\,");
					for (int i = 0; i < split.length; i++) {
						String name = split[i].trim();
						if (name.equals("timestamp")) {
							index = i;
						}
					}
					String[] timestamp = line.split("\\ ");
					result = line.replace(timestamp[index], epoctoDate(Long.parseLong(timestamp[index]) * 1000));
				} else {
					result = line;
				}
				list.add(Arrays.asList(result.split("\\ ")));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;

	}

	public String datatoString(String data1) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date date = df.parse(data1);
		long epoch = date.getTime() / 1000;
		return epoch + "";

	}

	public static String epoctoDate(long millis) {
		Date date = new Date(millis);
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String formatted = format.format(date);
		formatted = format.format(date);
		return formatted;

	}
}
