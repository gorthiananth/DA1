package com.javacodegeeks.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ProcessBuilderExample {
	public static void main(String a[]) throws ParseException
	{
		//DasPojo poj= new DasPojo("ma_stat_epd_-_F_time", datatoString("08-09-2019 00:11:52"),  datatoString("09-09-2019 23:11:52"), "egress_hits", "htp", 310860, "egress_hits", 100);
		//System.out.println(poj.getColumn());
		main1();

	}
	
    public static void main1() {
    	
        try {
        	
      //ma_stat_epd_-_F_time
        	Process p = Runtime.getRuntime().exec("sh /Users/agorthi/ncscript.sh  ma_stat_epd_-_F_time 1567967400  1568053799   sum(egress_hits) htp 310860   egress_hits 100"); 
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    public static String datatoString(String data1) throws ParseException
    {
    SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss");
    Date date = df.parse(data1);
    long epoch = date.getTime()/1000;
    System.out.println(epoch);
	return epoch+"";
    	
    }
}