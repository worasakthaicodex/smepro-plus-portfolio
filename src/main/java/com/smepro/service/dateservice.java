package com.smepro.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;




import org.springframework.stereotype.Service;

@Service
public class dateservice {

	public String DateSplit(String Date,int No) {
	
    int count = 0;
	     
	     String[] split = Date.split(" - ");    
   
			for ( String splits : split) {
				Date = splits;
				
				if(count == No) {
					break;
				}
				
				count++;
			}
	     
	     
	     return	 Date; 
	}
	
	public static  int getDiffMonth(String  first, String last) {
		long monthsBetween = ChronoUnit.MONTHS.between(
			     YearMonth.from(LocalDate.parse(first)), 
			     YearMonth.from(LocalDate.parse(last))
			);
				
				int diff = (int)monthsBetween;
				return diff;
	}  
	public static int getDiffDays(Date first, Date last) {
	    int daysdiff = 0;
	    long diff = last.getTime() - first.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
	    daysdiff = (int) diffDays;
	    return daysdiff;
	}
	
	
	
	public  String TimestampToDate(String time) {
		
		long timestampLong = Long.parseLong(time)*1000;
		TimeZone timeZone = TimeZone.getTimeZone("GMT+07:00");
		Calendar calendar = Calendar.getInstance(timeZone);
		Date d = new Date(timestampLong);
		calendar.setTime(d);
		
		
		SimpleDateFormat simpleDateFormat = 
		       new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		simpleDateFormat.setTimeZone(timeZone);
		

		/*
			SimpleDateFormat simpleDateFormat = 
		       new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz yyyy", Locale.US);
		System.out.println("Time zone: " + timeZone.getID());
		System.out.println("default time zone: " + TimeZone.getDefault().getID());
		System.out.println();
		System.out.println("GMT:     " + simpleDateFormat.format(calendar.getTime()));
		System.out.println("Default: " + calendar.getTime());
		System.out.println("GMT: " +  simpleDateFormat2.format(calendar.getTime()));
		*/
		
		return  simpleDateFormat.format(calendar.getTime());
	}
	public  String format(long s){
	        if (s < 10) return "0" + s;
	        else return "" + s;
	    }
	
	
	public  String Convert(int seconds) {
		
		int  hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;

		String timeString = String.format("%02d:%02d", hours, minutes);
		return timeString;
	}

	
	public  String MonthConvertTh(String Month) {
		String stringmonth = null;
		
      if(Month.equals("01")) {
    	  stringmonth ="มกราคม";
      }
      
      else if(Month.equals("02")) {
    	  stringmonth ="กุมภาพันธ์";
      }
      else if(Month.equals("03")) {
    	  stringmonth ="มีนาคม";
      }
      else if(Month.equals("04")) {
    	  stringmonth ="เมษายน";
      }
      else if(Month.equals("05")) {
    	  stringmonth ="พฤษภาคม";
      }
      else if(Month.equals("06")) {
    	  stringmonth ="มิถุนายน";
      }
      else if(Month.equals("07")) {
    	  stringmonth ="กรกฎาคม";
      }
      else if(Month.equals("08")) {
    	  stringmonth ="สิงหาคม";
      }
      else if(Month.equals("09")) {
    	  stringmonth ="กันยายน";
      }
      else if(Month.equals("10")) {
    	  stringmonth ="ตุลาคม";
      }
      else if(Month.equals("11")) {
    	  stringmonth ="พฤศจิกายน";
      }
      else if(Month.equals("12")) {
    	  stringmonth ="ธันวาคม";
      }
      
		return stringmonth;
	}

	public  String MonthConvertTh(int Month) {
		String stringmonth = null;
		
      if(Month == 1 ) {
    	  stringmonth ="มกราคม";
      }
      else if(Month == 2 ) {
    	  stringmonth ="กุมภาพันธ์";
      }
      else if(Month == 3 ) {
    	  stringmonth ="มีนาคม";
      }
      else if(Month == 4 ) {
    	  stringmonth ="เมษายน";
      }
      else if(Month == 5 ) {
    	  stringmonth ="พฤษภาคม";
      }
      else if(Month == 6 ) {
    	  stringmonth ="มิถุนายน";
      }
      else if(Month == 7 ) {
    	  stringmonth ="กรกฎาคม";
      }
      else if(Month == 8 ) {
    	  stringmonth ="สิงหาคม";
      }
      else if(Month == 9 ) {
    	  stringmonth ="กันยายน";
      }
      else if(Month == 10 ) {
    	  stringmonth ="ตุลาคม";
      }
      else if(Month == 11 ) {
    	  stringmonth ="พฤศจิกายน";
      }
      else if(Month == 12 ) {
    	  stringmonth ="ธันวาคม";
      }
      
		return stringmonth;
	}
	
	
	
	
	public  boolean Criteria_Upload(String date1,String date2) {

		    // แปลง String เป็น LocalDate
		    LocalDate localDate1 = LocalDate.parse(date1);
		    LocalDate localDate2 = LocalDate.parse(date2);
		
		    // เช็คว่า date1 น้อยกว่าหรือเท่ากับ date2 ไหม
		    if (localDate1.isBefore(localDate2) || localDate1.isEqual(localDate2)) {
		        return true;
		    } else {
		        return false;
		    }
	 }    
		    
		    
	public  boolean Criteria_Check(String date1,String date2) {

			    // แปลง String เป็น LocalDate
			    LocalDate localDate1 = LocalDate.parse(date1);
			    LocalDate localDate2 = LocalDate.parse(date2);
			
			     // เพิ่ม 1 วันให้กับ date2
		        LocalDate date2PlusOne = localDate2.plusDays(1);
			    
			    // เช็คว่า date1 น้อยกว่าหรือเท่ากับ date2 ไหม
		        if (localDate1.isBefore(localDate2) || localDate1.isEqual(localDate2)) {
			        return true;
			    } else {
			        return false;
			    } 
		      
			}
	
		public  String[] toTimeAgo(String date) {
			
			String[] TimeAgo = new String[2];
			
			  // แปลง String → LocalDateTime

			System.out.print("date "+date);
			
			
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		        
		       LocalDateTime now = LocalDateTime.now();


	        
	        
	        
	        // คำนวณระยะเวลาต่าง ๆ
	        Period period = Period.between(dateTime.toLocalDate(), now.toLocalDate());
	        long years = period.getYears();
	        long months = period.getMonths();
	        long days = period.getDays();

	        Duration duration = Duration.between(dateTime, now);
	        long seconds = duration.getSeconds();
	        long minutes = seconds / 60;
	        long hours = minutes / 60;

	       
	        System.out.println("minutes "+ minutes);
	        
	        
	        // แสดงข้อความตามลำดับหน่วยเวลา
	        if (years > 0) {
	        	TimeAgo[0] = String.valueOf(years); 
	        	TimeAgo[1] =  "timeago.yearsago"; 
	        	}
	        else if (months > 0) {
	        	TimeAgo[0] = String.valueOf(months); 
	        	TimeAgo[1] =  "timeago.monthsago"; 
	        	}
	        else if (days > 0) {
	        	TimeAgo[0] = String.valueOf(days); 
	        	TimeAgo[1] =  "timeago.daysago"; 
	        	}
	        else if (hours > 0) {
	        	TimeAgo[0] = String.valueOf(hours); 
	        	TimeAgo[1] =  "timeago.hoursago"; 
	        	}
	        else if (minutes > 0) {
	        	TimeAgo[0] = String.valueOf(minutes); 
	        	TimeAgo[1] =  "timeago.minsago"; 
	        	}	        
        

            return TimeAgo;
		}
		 
	
	
		
}
