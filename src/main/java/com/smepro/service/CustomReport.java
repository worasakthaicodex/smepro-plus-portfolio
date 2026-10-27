package com.smepro.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;



/*
 * 
 * CustomReport config = new CustomReport.Builder()
    .columnValue("data_value1")
    .columnDate("data_due")
    .operation("avg")
    .group(1)
    .build();
 */


public class CustomReport {
	
	   private String columnValue;
	    private String columnDate;
	    private String operation;
	    private String group;
	    private String binning;
	    private String title;
	
	  public static class Builder {
	        private String columnValue;
	        private String columnDate;
	        private String operation = "sum";
	        private String group;
	        private String binning;
	        private String title;
	        
	        public Builder columnValue(String val) { columnValue = val; return this; }
	        public Builder columnDate(String date) { columnDate = date; return this; }
	        public Builder operation(String op) { operation = op; return this; }
	        public Builder group(String g) { group = g; return this; }
	        public Builder binning(String b) { binning = b; return this; }
	        public Builder title(String t) { title = t; return this; }
	        
	        
	        public CustomReport build() {
	            return new CustomReport(columnValue, columnDate, operation, group, binning,title);
	        }
	    }
	
	  
	   CustomReport(String columnValue, String columnDate, String operation, String group, String binning, String title) {
	        this.columnValue = columnValue;
	        this.columnDate = columnDate;
	        this.operation = operation;
	        this.group = group;
	        this.binning = binning;
	        this.title = title;
	    }
	  


    	public <T> List<T>  PivotUtil(List<T> data) {
    		
    		
    		data = PivotResult(data,columnValue,columnDate);
    		
    		if(group != null) {
    			data = operation(data,columnValue,columnDate,operation,group);
    		}
        	
    		
            return data; 
            
            
		 }
    	
    	
    	
    	public <T> List<T>  EDAUtil(List<T> data) {
    		data = Discretization(data,columnValue,binning,title); 
            return data; 
		 }
    	
    	
    	public <T> List<T> Discretization(List<T> data, String column_value, String binning, String Title) {
    	  
    		System.out.println("Title  "+Title);
    		System.out.println("binning  "+binning);
    		
    		
    	
    		
    		// Parse binning string into a list of [min, max] ranges
    	    List<double[]> bins = new ArrayList<>();
    	    String[] binRanges = binning.split(",");
    	    for (String range : binRanges) {
    	        String[] bounds = range.trim().split("-");
    	        double min = Double.parseDouble(bounds[0]);
    	        double max = Double.parseDouble(bounds[1]);
    	        bins.add(new double[]{min, max});
    	    }

    	    // Parse Title string into a list of labels
    	    List<String> titles = Title != null && !Title.isEmpty()
    	        ? Arrays.asList(Title.split(",")).stream().map(String::trim).collect(Collectors.toList())
    	        : new ArrayList<>();

    	    // Validate titles length
    	    if (titles.size() != bins.size()) {
    	        throw new IllegalArgumentException("Number of titles (" + titles.size() + ") must match number of bins (" + bins.size() + ")");
    	    }
    		

    	    // Create binLabels for frequency counting (use titles)
    	    List<String> binLabels = new ArrayList<>(titles);

    	    // Count frequency of data points in each bin
    	    Map<String, Integer> binFrequencies = new HashMap<>();
    	    for (T item : data) {
    	        try {
    	            // Get the value from column_value field
    	            Field valueField = getFieldRecursive(item.getClass(), column_value);
    	            valueField.setAccessible(true);
    	            Object valueObj = valueField.get(item);
    	            if (valueObj == null) continue;

    	            double value = ((Number) valueObj).doubleValue();

    	            // Find the bin for this value
    	            for (int i = 0; i < bins.size(); i++) {
    	                double[] bin = bins.get(i);
    	                double min = bin[0];
    	                double max = bin[1];
    	                if (value >= min && value < max) { // [min, max)
    	                    String binLabel = binLabels.get(i); // Use title (e.g., ช้า)
    	                    binFrequencies.merge(binLabel, 1, Integer::sum);
    	                    break;
    	                }
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    // Create output list with one row per bin
    	    List<T> result = new ArrayList<>();
    	    for (int i = 0; i < bins.size(); i++) {
    	        double[] bin = bins.get(i);
    	        String numericalRange = bin[0] + "-" + bin[1]; // Numerical range for binning
    	        String title = titles.get(i); // Title for this bin (e.g., ช้า)
    	        String binLabel = binLabels.get(i); // Title for frequency key
    	        Integer frequency = binFrequencies.getOrDefault(binLabel, 0);

    	        // Use the first data item as a template (or create a new instance if needed)
    	        T template = data.isEmpty() ? null : data.get(0);
    	        if (template == null) continue; // Skip if no template available

    	        try {
    	            // Create a new instance
    	            T newItem = (T) template.getClass().getDeclaredConstructor().newInstance();

    	            // Set title, binning, and frequency fields
    	            setFieldValue(newItem, "title", title);
    	            setFieldValue(newItem, "binning", numericalRange);
    	            setFieldValue(newItem, "frequency", frequency.doubleValue()); // Assuming frequency is Double

    	            result.add(newItem);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }

    	    return result;
    	}

    	
    	
    	// Helper method to set field value via reflection (from your previous code)
    	private <T> void setFieldValue(T item, String fieldName, Object value) throws Exception {
    	    Field field = getFieldRecursive(item.getClass(), fieldName);
    	    field.setAccessible(true);
    	    field.set(item, value);
    	}
    	
    	
    	public <T> List<T> PivotResult(List<T> data, String column_value, String column_date) {
    	    for (T item : data) {
    	        try {
    	            Field valueField = getFieldRecursive(item.getClass(), column_value);
    	            Double value = ((Number) valueField.get(item)).doubleValue();

    	            Field dateField = getFieldRecursive(item.getClass(), column_date);
    	            Object dateObj = dateField.get(item);
    	            if (dateObj == null || dateObj.toString().trim().isEmpty()) continue;

    	            String rawDate = dateObj.toString().trim();
    	            if (rawDate.contains("T")) {
    	                rawDate = rawDate.substring(0, rawDate.indexOf("T"));
    	            }

    	            LocalDate date = LocalDate.parse(rawDate);
    	            int month = date.getMonthValue();
    	            int year = date.getYear();

    	            Field monthField = getFieldRecursive(item.getClass(), "month" + month);
    	            
    	            if(monthField.get(item)  != null ) {
    	            	   Double current = ((Number) monthField.get(item)).doubleValue();
    	            }
    	         
    	            monthField.set(item,value);

    	            Field yearField = getFieldRecursive(item.getClass(), "year");
    	            yearField.set(item, year);

    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    	    return data;
    	}

    	
    	public List<Map<String, String>> PivotResults(List<Map<String, String>> edata, String columnValue, String columnDate) {
    		
    		
    		System.out.println("columnValue "+columnValue);
    		System.out.println("columnDate "+columnDate);
    	    for (Map<String, String> row : edata) {
    	        try {
    	            String rawDate = row.getOrDefault(columnDate, "").trim();
    	            if (rawDate.isEmpty()) continue;

    	            if (rawDate.contains("T")) {
    	                rawDate = rawDate.substring(0, rawDate.indexOf("T"));
    	            }

    	            LocalDate date = LocalDate.parse(rawDate);
    	            int month = date.getMonthValue();
    	            int year = date.getYear();

    	            String valueStr = row.getOrDefault(columnValue, "").trim();
    	            if (valueStr.isEmpty()) continue;

    	            double value = Double.parseDouble(valueStr);

    	            // ใส่ค่าเดือน เช่น month1, month2, ...
    	            String monthKey = "month" + month;
    	            double current = 0.0;
    	            if (row.containsKey(monthKey)) {
    	                String currentStr = row.get(monthKey);
    	                if (currentStr != null && !currentStr.isEmpty()) {
    	                    current = Double.parseDouble(currentStr);
    	                }
    	            }
    	            row.put(monthKey, String.valueOf(current + value));

    	            // ใส่ค่า year ลงไป
    	            row.put("year", String.valueOf(year));

    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	    }
    	    return edata;
    	}
    	
    	
    	
    	
    	
    	
    	
    	private Field getFieldRecursive(Class<?> clazz, String fieldName) throws NoSuchFieldException {
    	    while (clazz != null) {
    	        try {
    	            Field field = clazz.getDeclaredField(fieldName);
    	            field.setAccessible(true);
    	            return field;
    	        } catch (NoSuchFieldException e) {
    	            clazz = clazz.getSuperclass();
    	        }
    	    }
    	    throw new NoSuchFieldException(fieldName);
    	}
	
    	
		private List<Map<String, String>> operations(List<Map<String, String>> edata, String column_value, String column_date, String operation, String group) {
		    Map<String, List<Map<String, String>>> grouped = new HashMap<>();
		
		    // Group by multiple fields (e.g., year + group name)
		    String[] groupFields = group.split(",");
		    for (Map<String, String> item : edata) {
		        String key = Arrays.stream(groupFields)
		                .map(field -> field + "=" + item.getOrDefault(field, "null"))
		                .collect(Collectors.joining("|"));
		        grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
		    }
		
		    List<Map<String, String>> resultList = new ArrayList<>();
		
		    for (Map.Entry<String, List<Map<String, String>>> entry : grouped.entrySet()) {
		        List<Map<String, String>> groupItems = entry.getValue();
		        if (groupItems.isEmpty()) continue;
		
		        // Track sums and whether data exists for each month
		        double[] monthSums = new double[12];
		        boolean[] hasData = new boolean[12]; // Track if any data exists for each month
		
		        for (Map<String, String> item : groupItems) {
		            for (int i = 1; i <= 12; i++) {
		                String monthKey = "month" + i;
		                String value = item.get(monthKey);
		                if (value != null && !value.isEmpty()) {
		                    try {
		                        monthSums[i - 1] += Double.parseDouble(value);
		                        hasData[i - 1] = true; // Mark that this month has data
		                    } catch (NumberFormatException e) {
		                        e.printStackTrace();
		                    }
		                }
		            }
		        }
		
		        // Create a filtered monthList that only includes months with data
		        List<Double> monthList = new ArrayList<>();
		        for (int i = 0; i < 12; i++) {
		            if (hasData[i]) {
		                monthList.add(monthSums[i]);
		            }
		        }
		        System.out.println(" monthList " + monthList.size());
		        System.out.println(" operation Case " + operation.toLowerCase());
		
		        double result = 0.0;
		        double stdDev = 0.0;
		        double variance = 0.0;
		        double max = monthList.isEmpty() ? Double.MIN_VALUE : Collections.max(monthList);
		        double min = monthList.isEmpty() ? Double.MAX_VALUE : Collections.min(monthList);
		
		        switch (operation.toLowerCase()) {
		            case "sum":
		                result = monthList.stream().mapToDouble(Double::doubleValue).sum();
		                break;
		            case "average":
		                result = monthList.isEmpty() ? 0.0 : monthList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
		                stdDev = monthList.isEmpty() ? 0.0 : calculateStandardDeviation(monthList, result);
		                variance = monthList.isEmpty() ? 0.0 : calculateVariance(monthList, result);
		
		                System.out.println(" result " + result);
		                System.out.println(" stdDev " + stdDev);
		                System.out.println(" variance " + variance);
		                break;
		            case "median":
		                result = monthList.isEmpty() ? 0.0 : calculateMedian(monthList);
		                break;
		            case "mode":
		                result = monthList.isEmpty() ? 0.0 : calculateMode(monthList);
		                break;
		            case "maximum":
		                result = max;
		                break;
		            case "minimum":
		                result = min;
		                break;
		            default:
		                result = 0.0;
		        }
		
		        // Use the first item as a template and set values
		        Map<String, String> firstItem = new HashMap<>(groupItems.get(0)); // Create a copy to avoid modifying original
		        try {
		            for (int i = 1; i <= 12; i++) {
		                String monthKey = "month" + i;
		                firstItem.put(monthKey, hasData[i - 1] ? String.valueOf(monthSums[i - 1]) : null);
		            }
		
		            // Round to one decimal place
		            double roundedResult = Math.round(result * 10.0) / 10.0;
		            double roundedStdDev = Math.round(stdDev * 10.0) / 10.0;
		            double roundedVariance = Math.round(variance * 10.0) / 10.0;
		            double rsd = result != 0.0 ? (stdDev / result) * 100.0 : 0.0;
		            rsd = Math.round(rsd);
		
		            firstItem.put("summarize", String.valueOf(roundedResult));
		            firstItem.put("standard_deviation", String.valueOf(roundedStdDev));
		            firstItem.put("variance", String.valueOf(roundedVariance));
		            firstItem.put("rsd", String.valueOf(rsd));
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		
		        resultList.add(firstItem);
		    }
		
		    return resultList;
		}
		

		
		

    	private <T> List<T> operation(List<T> data, String column_value, String column_date, String operation, String group) {
    	    Map<String, List<T>> grouped = new HashMap<>();
    	    
    	    
    	    
    	    
    	    String[] groupFields = group.split(",");
    	    // Group by multiple fields (e.g., year + group name)
    	    for (T item : data) {
    	        String key = Arrays.stream(groupFields)
    	                .map(field -> {
    	                    try {
    	                        Field f = getFieldRecursive(item.getClass(), field);
    	                        f.setAccessible(true);
    	                        return field + "=" + f.get(item);
    	                    } catch (Exception e) {
    	                        e.printStackTrace();
    	                        return field + "=null";
    	                    }
    	                })
    	                .collect(Collectors.joining("|"));
    	        grouped.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
    	    }

    	    List<T> resultList = new ArrayList<>();

    	    for (Map.Entry<String, List<T>> entry : grouped.entrySet()) {
    	        List<T> groupItems = entry.getValue();
    	        if (groupItems.isEmpty()) continue;

    	        // Track sums and whether data exists for each month
    	        double[] monthSums = new double[12];
    	        boolean[] hasData = new boolean[12]; // Track if any data exists for each month

    	        for (T item : groupItems) {
    	            for (int i = 1; i <= 12; i++) {
    	                try {
    	                    Field monthField = getFieldRecursive(item.getClass(), "month" + i);
    	                    monthField.setAccessible(true);
    	                    Object value = monthField.get(item);
    	                    if (value != null) {
    	                        monthSums[i - 1] += ((Number) value).doubleValue();
    	                        hasData[i - 1] = true; // Mark that this month has data
    	                    }
    	                } catch (Exception e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	        }

    	        // Create a filtered monthList that only includes months with data
    	        List<Double> monthList = new ArrayList<>();
    	        for (int i = 0; i < 12; i++) {
    	            if (hasData[i]) {
    	                monthList.add(monthSums[i]);
    	            }
    	        }
    	        System.out.println(" monthList "+monthList.size());
    	        System.out.println(" operation Case "+operation.toLowerCase());
    	        
    	        
    	        double result = 0.0;
    	        double stdDev = 0.0;
    	        double variance = 0.0;
    	        double max = monthList.isEmpty() ? Double.MIN_VALUE : Collections.max(monthList);
    	        double min = monthList.isEmpty() ? Double.MAX_VALUE : Collections.min(monthList);

    	        switch (operation.toLowerCase()) {
    	            case "sum":
    	                result = monthList.stream().mapToDouble(Double::doubleValue).sum();
    	                break;
    	            case "average":
    	                result = monthList.isEmpty() ? 0.0 : monthList.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
     	                stdDev = monthList.isEmpty() ? 0.0 : calculateStandardDeviation(monthList, result);
    	                variance = monthList.isEmpty() ? 0.0 : calculateVariance(monthList, result);
    	                
    	                System.out.println(" result "+result);
    	     	        System.out.println(" stdDev "+stdDev);
    	     	        System.out.println(" variance "+variance);
    	                
    	                break;
    	            case "median":
    	                result = monthList.isEmpty() ? 0.0 : calculateMedian(monthList);
   
    	                break;
    	            case "mode":
    	                result = monthList.isEmpty() ? 0.0 : calculateMode(monthList);
    	                break;
    	            case "maximum":
    	                result = max;
    	                break;
    	            case "minimum":
    	                result = min;
    	                break;
    	            default:
    	                result = 0.0;
    	        }
    	        
    	        
    	     
    	        
    	        
    	        System.out.println(groupItems.toString());
             	 
    	        // Use the first item as a template and set values
    	        T firstItem = groupItems.get(0);
    	        try {
    	            for (int i = 1; i <= 12; i++) {
    	                Field monthField = getFieldRecursive(firstItem.getClass(), "month" + i);
    	                monthField.setAccessible(true);
    	                monthField.set(firstItem, hasData[i - 1] ? monthSums[i - 1] : null);
    	            }
    	            
    	            // Round to one decimal place
    	            double roundedResult = Math.round(result * 10.0) / 10.0;
    	            double roundedStdDev = Math.round(stdDev * 10.0) / 10.0;
    	            double roundedVariance = Math.round(variance * 10.0) / 10.0;
    	            double rsd = result != 0.0 ? (stdDev / result) * 100.0 : 0.0;
    	            rsd = Math.round(rsd);
    	            
    	            setFieldValue(firstItem, "summarize", roundedResult);
    	            setFieldValue(firstItem, "standard_deviation", roundedStdDev);
    	            setFieldValue(firstItem, "variance", roundedVariance);
    	            setFieldValue(firstItem, "rsd", rsd);
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }

    	        resultList.add(firstItem);
    	    }

    	    return resultList;
    	}
    	
    	private <T> void setFieldValue(T item, String fieldName, double value) throws Exception {
    	    Field f = getFieldRecursive(item.getClass(), fieldName);
    	    f.setAccessible(true);
    	    f.set(item, value);
    	}
    	
    	
		  private double calculateMedian(List<Double> values) {
			    List<Double> sorted = values.stream()
			        .filter(Objects::nonNull) // กรอง null ออกก่อน
			        .sorted()
			        .collect(Collectors.toList());

			    int size = sorted.size();
			    if (size == 0) return 0.0; // ป้องกันกรณีไม่มีข้อมูลเลย

			    if (size % 2 == 1) {
			        return sorted.get(size / 2);
			    } else {
			        return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
			    }
			}
		  
		  private double calculateMode(List<Double> values) {
			    Map<Double, Long> freqMap = values.stream()
			        .filter(Objects::nonNull) // กรอง null ออก
			        .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

			    return freqMap.entrySet().stream()
			        .max(Map.Entry.comparingByValue())
			        .map(Map.Entry::getKey)
			        .orElse(0.0);
			}
		  
		  
		// Calculate Standard Deviation
		  private double calculateStandardDeviation(List<Double> values, double mean) {
		      double sum = values.stream().filter(Objects::nonNull).mapToDouble(value -> Math.pow(value - mean, 2)).sum();
		      
		      
		      System.out.println(" calculateStandardDeviation "+sum);
		      
		      return Math.sqrt(sum / values.size());
		  }

		  // Calculate Variance
		  private double calculateVariance(List<Double> values, double mean) {
		      double sum = values.stream().filter(Objects::nonNull).mapToDouble(value -> Math.pow(value - mean, 2)).sum();
		      
		      System.out.println(" calculateVariance "+sum);
		      return sum / values.size();
		  }



		public  List<Map<String, String>> PivotUtils(List<Map<String, String>> edata) {
			
			  System.out.println(" PivotUtils .... ");
			
				edata = PivotResults(edata,columnValue,columnDate);
    		
	    		if(group != null) {
	    		edata = operations(edata,columnValue,columnDate,operation,group);
	    		}
	        	
    		
            return edata; 
		}



			public  List<Map<String, String>> EDAUtils(List<Map<String, String>> edata) {
				
				System.out.println(" EDAUtils run...");
				
				List<Map<String, String>> edatas = Discretizations(edata,columnValue,binning,title); 
	            return edatas; 
			}



			private List<Map<String, String>> Discretizations(List<Map<String, String>> data, String column_value,
				                String binning, String Title) {
				
						System.out.println("Title  " + Title);
						System.out.println("binning  " + binning);
						System.out.println("column_value  " + column_value);
						
						
						// Parse binning string into a list of [min, max] ranges
						List<double[]> bins = new ArrayList<>();
						String[] binRanges = binning.split(",");
						for (String range : binRanges) {
						String[] bounds = range.trim().split("-");
						double min = Double.parseDouble(bounds[0]);
						double max = Double.parseDouble(bounds[1]);
						bins.add(new double[]{min, max});
						}
						
						// Parse Title string into a list of labels
						List<String> titles = Title != null && !Title.isEmpty()
						? Arrays.asList(Title.split(",")).stream().map(String::trim).collect(Collectors.toList())
						: new ArrayList<>();
						
						// Validate titles length
						if (titles.size() != bins.size()) {
						throw new IllegalArgumentException("Number of titles (" + titles.size() + ") must match number of bins (" + bins.size() + ")");
						}
						
						// Create binLabels for frequency counting (use titles)
						List<String> binLabels = new ArrayList<>(titles);
						
						// Count frequency of data points in each bin
						Map<String, Integer> binFrequencies = new HashMap<>();
						for (Map<String, String> item : data) {
						try {
						// Get the value from column_value field
						String valueStr = item.get(column_value);
						
						System.out.println("valueStr  " + valueStr);
						
						if (valueStr == null || valueStr.isEmpty()) continue;
						
						double value = Double.parseDouble(valueStr);
						
						// Find the bin for this value
						for (int i = 0; i < bins.size(); i++) {
						double[] bin = bins.get(i);
						double min = bin[0];
						double max = bin[1];
						if (value >= min && value < max) { // [min, max)
						String binLabel = binLabels.get(i); // Use title (e.g., ช้า)
						binFrequencies.merge(binLabel, 1, Integer::sum);
						break;
						}
						}
						} catch (NumberFormatException e) {
						e.printStackTrace();
						}
						}
						
						// Create output list with one row per bin
						List<Map<String, String>> result = new ArrayList<>();
						for (int i = 0; i < bins.size(); i++) {
						double[] bin = bins.get(i);
						String numericalRange = bin[0] + "-" + bin[1]; // Numerical range for binning
						String title = titles.get(i); // Title for this bin (e.g., ช้า)
						String binLabel = binLabels.get(i); // Title for frequency key
						Integer frequency = binFrequencies.getOrDefault(binLabel, 0);
						
						// Create a new Map for this bin
						Map<String, String> newItem = new HashMap<>();
						newItem.put("title", title);
						newItem.put("binning", numericalRange);
						newItem.put("frequency", String.valueOf(frequency)); // Store frequency as string
						
						result.add(newItem);
						}
						
						System.out.println("Result: " + result.size());
						for (Map<String, String> row : result) {
						    System.out.println("Title: " + row.get("title"));
						    System.out.println("ช่วงข้อมูล (Binning): " + row.get("binning"));
						    System.out.println("ความถี่ (Frequency): " + row.get("frequency"));
						    System.out.println("----------------------------");
						}
						
						return result;
						
						
				}
		
	
}
	    
