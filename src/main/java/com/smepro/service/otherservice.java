package com.smepro.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class otherservice {
	
	
	
	public  String generateRandom(int digi) {
		
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < digi; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    return res.toString();
	}
	
public  long generateRandomnumber(int digi) {
		
		String aToZ = "1234567890";
	    Random rand=new Random();
	    StringBuilder res=new StringBuilder();
	    for (int i = 0; i < digi; i++) {
	       int randIndex=rand.nextInt(aToZ.length()); 
	       res.append(aToZ.charAt(randIndex));            
	    }
	    System.out.println("testss ");
	    long number = Long.parseLong(res.toString());
	    System.out.println("test " +number);
	    return number;
	}
	
	
	public boolean DataStatus(String data) {
		
		boolean Status = false;
		
		if(data != null) {
			if(data.isEmpty()) {
				Status= true;
			}
		}
		
		return Status;
	}

	
	
	public  double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	
	  //Is JSON ?
    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

	
    public  boolean checkAnyNotNull(String... strings) {
    	 return Arrays.stream(strings).anyMatch(str -> str != null && !str.trim().isEmpty() );
    }
    
    
    
     public Object normalize(String input) {
		    return Arrays.stream(input.split(","))
	                 .map(String::trim)
	                 .sorted()
	                 .collect(Collectors.joining(","));
	}
     
     
     public boolean checkAllHaveValidValue(String... strings) {
    	    return Arrays.stream(strings)
    	        .allMatch(str -> str != null && !str.trim().isEmpty() && !str.trim().equals("0") && !str.trim().equals("-"));
    	}
    

	     public boolean haveValue(String value) 
	     
	     {
	    	 if(value == null) { return false;}
	    	 if(value.equals("0")) { return false;}
	    	 if(value.equals("-")) { return false;}
	    	 if(value.isBlank()) { return false;}
	    	 if(value == "") { return false;}
			    return true;
		}

	     public String StringValueOf(Object value) {
	    	 if (value == null) return "";
	    	    return value.toString();
	    	}
	     
	     public BigDecimal BigDecimalValueOf(Object value) {
	    	    if (value == null) return BigDecimal.ZERO;
	    	    try {
	    	        return new BigDecimal(value.toString().trim());
	    	    } catch (NumberFormatException e) {
	    	        return BigDecimal.ZERO; // หรือ throw ก็ได้ แล้วแต่คุณ
	    	    }
	    	}
	     
	     
	     public boolean IsStringDate(Object value) {
	    	 if (value == null) return false;
	    	 if (value == "") return false;
	    	 
	    	    String isoPattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$";
	    	    return ((String) value).matches(isoPattern);
	    	}
	     
	     
	     
	     
	     
	     public Double DoubleValueOf(Object value) {
	    	    
	    	 if (value == null) return 0.0;

	    	    if (value instanceof Number) {
	    	        return ((Number) value).doubleValue();
	    	    }

	    	    if (value instanceof String) {
	    	        try {
	    	            return Double.valueOf((String) value);
	    	        } catch (NumberFormatException e) {
	    	            // ไม่สามารถแปลง String เป็น Double
	    	            return 0.0;
	    	        }
	    	    }

	    	    // ไม่รองรับประเภทอื่น
	    	    return 0.0;
	    	}
	     
	     
	     
	     public Integer getIntValueOrDefault(Object value) {
	    	 if (value == null) return 0;

	    	    if (value instanceof Number) {
	    	        return ((Number) value).intValue();
	    	    }

	    	    if (value instanceof String) {
	    	        try {
	    	            return Integer.valueOf((String) value);
	    	        } catch (NumberFormatException e) {
	    	            // ไม่สามารถแปลง String เป็น Double
	    	            return 0;
	    	        }
	    	    }

	    	    // ไม่รองรับประเภทอื่น
	    	    return 0;
	     }
	     
	     
	     public Integer IntegerValueOf(Object value) {
	    	    if (value == null) return null;

	    	    if (value instanceof Number) {
	    	        return ((Number) value).intValue();
	    	    }

	    	    if (value instanceof String) {
	    	        try {
	    	            return Integer.valueOf((String) value);
	    	        } catch (NumberFormatException e) {
	    	            // ไม่สามารถแปลง String เป็น Double
	    	            return 0;
	    	        }
	    	    }

	    	    // ไม่รองรับประเภทอื่น
	    	    return 0;
	    	}
	     
	     
	     public String DefaultValueString(Object value) {
	    	    if (value == null) return "-";
	    	    if (value.toString().isEmpty()) return "-";
	    	    if (value.toString().isBlank()) return "-";
	    	    return value.toString();
	    	}
	     
	     
	     
	     
	     public String StringValueNull(String value) {
	    	 
	    	    if (value == null || value == "null" || value == "0"  || value.equals("-") || value.isBlank() || value.isEmpty()) { return null;} 	    
	    	    if (value.matches("\\d+")) {
	    	    	 if(Integer.valueOf(value) == 0) {  return null; }
	    	    }

	    	    return value.toString();
	    	}
	     
	     
	     public String getStringByReflection(Object obj, String fieldName) {
	    	 
	    	 
	    	 if(fieldName == null || obj == null ) {  return null;}
	    	 
	    	    try {
	    	        Field field = obj.getClass().getDeclaredField(fieldName);
	    	        field.setAccessible(true);
	    	        Object value = field.get(obj);
	    	        return value != null ? value.toString() : null;
	    	    } catch (Exception e) {
	    	    	
	    	    	System.err.println("element cannot be mapped to a null key");
	    	    	
	    	       // e.printStackTrace();
	    	        return null;
	    	    }
	    	}
	     
	     
	     
	     public <T> Double getDoubleByReflection(Object obj, T fieldName) {
	    	 
	    	 if(fieldName == null || obj == null ) {  return 0.0;}
	    	    try {
	    	        Field field = obj.getClass().getDeclaredField((String) fieldName);
	    	        field.setAccessible(true);
	    	        Object value = field.get(obj);
	    	        return  DoubleValueOf(value.toString());
	    	        
	    	    } catch (Exception e) {
	    	    	System.err.println("element cannot be mapped to a null key");
	    	        return 0.0;
	    	    }
	    	}
	     
	     
	     
	     public <T> Integer getIntegerByReflection(Object obj, T fieldName) {
	    	 
	    	 if(fieldName == null || obj == null ) {  return 0;}
	    	    try {
	    	        Field field = obj.getClass().getDeclaredField((String) fieldName);
	    	        field.setAccessible(true);
	    	        Object value = field.get(obj);
	    	        return  IntegerValueOf(value.toString());
	    	        
	    	    } catch (Exception e) {
	    	    	System.err.println("element cannot be mapped to a null key");
	    	        return 0;
	    	    }
	    	}

		public int calculateSimilarity(String name1, String name2) {
			   LevenshteinDistance ld = new LevenshteinDistance();
			    int distance = ld.apply(name1, name2);
			    int maxLen = Math.max(name1.length(), name2.length());
			    Double  sim =   1.0 - ((double) distance / maxLen);  // ได้ค่า 0.0 - 1.0
			    
			    
			    System.out.println("sim "+sim);
			    
			     return (int)(sim * 100);
		}

		public String splitToString(String Array, String string, int i) {
		
			if(haveValue(Array) == true ) {
				return	Array.split(string)[i];
			}
			
			return null;
		}
	     
	 

		
		public static <T> List<T> ObjectListClone(Object object) {
			
			List<T> list = (List<T>)object;
			
	        try {
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();
	            ObjectOutputStream out = new ObjectOutputStream(bos);
	            out.writeObject(list); // list และ element ต้อง Serializable
	            out.flush();
	            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
	            return (List<T>) in.readObject();
	        } catch (Exception e) {
	        	System.out.println(" e->"+e);
	        	
	            throw new RuntimeException("Deep clone of list failed", e);
	        }
	    }
	     
}
