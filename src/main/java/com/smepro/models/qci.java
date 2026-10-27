package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class qci  extends custom_report implements Identifiable  {

	private int id;
	private int edata_id;
	private String qci_code;
	private String product_code;
	private String product_name;
	private String process_step;
	private String quantity;
	private String start_date_time;
	private String inspection;
	private String note;
	
	private String results_string1;
	private String results_string2;
	private String results_string3;
	private String results_string4;
	private String results_string5;
	
	private String results_number1;
	private String results_number2;
	private String results_number3;
	private String results_number4;
	private String results_number5;
	
	
	private String stock_list;
	private String stock_code;
	private int count;
	private int qci_signature;
	private int qci_encrypted;
	private int qci_signature2;
	private int qci_encrypted2;
	
	
		private List<Object> join1;

		private List<Object> join2;
	
	
	private int processId;
	
	
		@Override
		public void setJoin1(List<Object> join1) {
			this.join1 = join1;
		}
	
		public List<Object> getJoin1() {
			return join1;
		}
		@Override
		public void setJoin2(List<Object> join2) {
			this.join2 = join2;
		}
	
		public List<Object> getJoin2() {
			return join2;
		}
		
	
	
	  @Override
	    public int getProcessId() {
	        return this.processId;
	 }
	
	    public void setProcessId(int processId) {
	        this.processId = processId;
	 }
	


	@Override
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public qci() {
		this.qci_signature = 0;
		this.qci_encrypted = 0;
		this.qci_signature2 = 0;
		this.qci_encrypted2 = 0;
	}

	public int getEdata_id() {
		return edata_id;
	}


	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}


	public String getQci_code() {
		return qci_code;
	}


	public void setQci_code(String qci_code) {
		this.qci_code = qci_code;
	}


	public String getProduct_code() {
		return product_code;
	}


	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public String getProcess_step() {
		return process_step;
	}


	public void setProcess_step(String process_step) {
		this.process_step = process_step;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getStart_date_time() {
		return start_date_time;
	}


	public void setStart_date_time(String start_date_time) {
		this.start_date_time = start_date_time;
	}


	public String getInspection() {
		return inspection;
	}


	public void setInspection(String inspection) {
		this.inspection = inspection;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getResults_string1() {
		return results_string1;
	}


	public void setResults_string1(String results_string1) {
		this.results_string1 = results_string1;
	}


	public String getResults_string2() {
		return results_string2;
	}


	public void setResults_string2(String results_string2) {
		this.results_string2 = results_string2;
	}


	public String getResults_string3() {
		return results_string3;
	}


	public void setResults_string3(String results_string3) {
		this.results_string3 = results_string3;
	}


	public String getResults_string4() {
		return results_string4;
	}


	public void setResults_string4(String results_string4) {
		this.results_string4 = results_string4;
	}


	public String getResults_string5() {
		return results_string5;
	}


	public void setResults_string5(String results_string5) {
		this.results_string5 = results_string5;
	}


	public String getResults_number1() {
		return results_number1;
	}


	public void setResults_number1(String results_number1) {
		this.results_number1 = results_number1;
	}


	public String getResults_number2() {
		return results_number2;
	}


	public void setResults_number2(String results_number2) {
		this.results_number2 = results_number2;
	}


	public String getResults_number3() {
		return results_number3;
	}


	public void setResults_number3(String results_number3) {
		this.results_number3 = results_number3;
	}


	public String getResults_number4() {
		return results_number4;
	}


	public void setResults_number4(String results_number4) {
		this.results_number4 = results_number4;
	}


	public String getResults_number5() {
		return results_number5;
	}


	public void setResults_number5(String results_number5) {
		this.results_number5 = results_number5;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public String getStock_list() {
		return stock_list;
	}


	public void setStock_list(String stock_list) {
		this.stock_list = stock_list;
	}


	public String getStock_code() {
		return stock_code;
	}


	public void setStock_code(String stock_code) {
		this.stock_code = stock_code;
	}




	public void reset() {
	    for (Field field : this.getClass().getDeclaredFields()) {
	        field.setAccessible(true);
	        Class<?> type = field.getType();
	        try {
	            if (type.isPrimitive()) {
	                if (type == boolean.class) field.setBoolean(this, false);
	                else if (type == byte.class) field.setByte(this, (byte)0);
	                else if (type == short.class) field.setShort(this, (short)0);
	                else if (type == int.class) field.setInt(this, 0);
	                else if (type == long.class) field.setLong(this, 0L);
	                else if (type == float.class) field.setFloat(this, 0f);
	                else if (type == double.class) field.setDouble(this, 0d);
	                else if (type == char.class) field.setChar(this, '\u0000');
	            } else {
	                field.set(this, null);
	            }
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	
	
	
	
	
}


