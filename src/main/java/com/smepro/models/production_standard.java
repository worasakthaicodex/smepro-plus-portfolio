package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class production_standard implements Identifiable  {

	
	 	private int id;
	 	private int ps_id;
	 	private String ps_code;
	 	private int edata_id;
	 	private String tempId;
		private String title;
		private String process_step;
		private String product_code;
		private String product_name;
		private String  title_string1;
		private String  title_string2;
		private String  title_string3;
		private String  title_string4;
		private String  title_string5;
		private String  target_string1;
		private String  target_string2;
		private String  target_string3;
		private String  target_string4;
		private String  target_string5;
		
		private String  title_number1;
		private String  title_number2;
		private String  title_number3;
		private String  title_number4;
		private String  title_number5;
		private String  target_number1;
		private String  target_number2;
		private String  target_number3;
		private String  target_number4;
		private String  target_number5;
		private String  note;
		private String  status;
		
		
		private int count;
		

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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getProcess_step() {
			return process_step;
		}
		public void setProcess_step(String process_step) {
			this.process_step = process_step;
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
		public String getTitle_string1() {
			return title_string1;
		}
		public void setTitle_string1(String title_string1) {
			this.title_string1 = title_string1;
		}
		public String getTitle_string2() {
			return title_string2;
		}
		public void setTitle_string2(String title_string2) {
			this.title_string2 = title_string2;
		}
		public String getTitle_string3() {
			return title_string3;
		}
		public void setTitle_string3(String title_string3) {
			this.title_string3 = title_string3;
		}
		public String getTitle_string4() {
			return title_string4;
		}
		public void setTitle_string4(String title_string4) {
			this.title_string4 = title_string4;
		}
		public String getTitle_string5() {
			return title_string5;
		}
		public void setTitle_string5(String title_string5) {
			this.title_string5 = title_string5;
		}
		public String getTarget_string1() {
			return target_string1;
		}
		public void setTarget_string1(String target_string1) {
			this.target_string1 = target_string1;
		}
		public String getTarget_string2() {
			return target_string2;
		}
		public void setTarget_string2(String target_string2) {
			this.target_string2 = target_string2;
		}
		public String getTarget_string3() {
			return target_string3;
		}
		public void setTarget_string3(String target_string3) {
			this.target_string3 = target_string3;
		}
		public String getTarget_string4() {
			return target_string4;
		}
		public void setTarget_string4(String target_string4) {
			this.target_string4 = target_string4;
		}
		public String getTarget_string5() {
			return target_string5;
		}
		public void setTarget_string5(String target_string5) {
			this.target_string5 = target_string5;
		}
		public String getTitle_number1() {
			return title_number1;
		}
		public void setTitle_number1(String title_number1) {
			this.title_number1 = title_number1;
		}
		public String getTitle_number2() {
			return title_number2;
		}
		public void setTitle_number2(String title_number2) {
			this.title_number2 = title_number2;
		}
		public String getTitle_number3() {
			return title_number3;
		}
		public void setTitle_number3(String title_number3) {
			this.title_number3 = title_number3;
		}
		public String getTitle_number4() {
			return title_number4;
		}
		public void setTitle_number4(String title_number4) {
			this.title_number4 = title_number4;
		}
		public String getTitle_number5() {
			return title_number5;
		}
		public void setTitle_number5(String title_number5) {
			this.title_number5 = title_number5;
		}
		public String getTarget_number1() {
			return target_number1;
		}
		public void setTarget_number1(String target_number1) {
			this.target_number1 = target_number1;
		}
		public String getTarget_number2() {
			return target_number2;
		}
		public void setTarget_number2(String target_number2) {
			this.target_number2 = target_number2;
		}
		public String getTarget_number3() {
			return target_number3;
		}
		public void setTarget_number3(String target_number3) {
			this.target_number3 = target_number3;
		}
		public String getTarget_number4() {
			return target_number4;
		}
		public void setTarget_number4(String target_number4) {
			this.target_number4 = target_number4;
		}
		public String getTarget_number5() {
			return target_number5;
		}
		public void setTarget_number5(String target_number5) {
			this.target_number5 = target_number5;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public int getPs_id() {
			return ps_id;
		}
		public void setPs_id(int ps_id) {
			this.ps_id = ps_id;
		}
		
		public String getTempId() {
			return tempId;
		}
		public void setTempId(String tempId) {
			this.tempId = tempId;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getEdata_id() {
			return edata_id;
		}
		public void setEdata_id(int edata_id) {
			this.edata_id = edata_id;
		}
		public String getPs_code() {
			return ps_code;
		}
		public void setPs_code(String ps_code) {
			this.ps_code = ps_code;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
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
