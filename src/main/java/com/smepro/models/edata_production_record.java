package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class edata_production_record  extends custom_report implements Identifiable  {
	
	private int id;
	private int edata_id;
    private String lot_batch;
    private String product_code;
    private String product_name;
    private String process_step;
    private String department;
    private Integer order_quantity;
    private Integer actual_output;
    private Integer defective_output;
    
    private Integer total_output;
    private Integer total_defective;
    private Integer outstanding;
    private Integer amount_output;

    

    
    private String record_date;
    private String recorder;
    private String operator;
    private String line;
    private String inspection_result;
    private String remarks;
    private String status;
    private String shift;
    private String unit_of_measure;
    private String start_date_time;
    private String end_date_time;
    private String machine_id;
    private String  item_code;
    private String pr_code;
    
    private String defective_output_issues;   
    private String downtime_title;
    private Double speed;
    private Double setup_time;
    private Double downtime;
	private int count;
	private int  id_refer;
	private String reference_code;

	private int processId;
	
	
	
	
	
    
	public edata_production_record() {
		this.order_quantity = 0;
		this.actual_output = 0;
		this.defective_output = 0;
		this.speed = 0.0;
		this.setup_time = 0.0;
		this.downtime = 0.0;
	}
    
	private List<Object> join1;
	private List<Object> join2;

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
	public int getEdata_id() {
		return edata_id;
	}
	public void setEdata_id(int edata_id) {
		this.edata_id = edata_id;
	}
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getRecord_date() {
		return record_date;
	}
	public void setRecord_date(String record_date) {
		this.record_date = record_date;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getInspection_result() {
		return inspection_result;
	}
	public void setInspection_result(String inspection_result) {
		this.inspection_result = inspection_result;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getUnit_of_measure() {
		return unit_of_measure;
	}
	public void setUnit_of_measure(String unit_of_measure) {
		this.unit_of_measure = unit_of_measure;
	}
	public String getStart_date_time() {
		return start_date_time;
	}
	public void setStart_date_time(String start_date_time) {
		this.start_date_time = start_date_time;
	}
	public String getEnd_date_time() {
		return end_date_time;
	}
	public void setEnd_date_time(String end_date_time) {
		this.end_date_time = end_date_time;
	}
	public String getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(String machine_id) {
		this.machine_id = machine_id;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getPr_code() {
		return pr_code;
	}
	public void setPr_code(String pr_code) {
		this.pr_code = pr_code;
	}




	public Integer getOrder_quantity() {
		return order_quantity;
	}




	public void setOrder_quantity(Integer order_quantity) {
		this.order_quantity = order_quantity;
	}




	public Integer getActual_output() {
		return actual_output;
	}




	public void setActual_output(Integer actual_output) {
		this.actual_output = actual_output;
	}




	public Integer getDefective_output() {
		return defective_output;
	}




	public void setDefective_output(Integer defective_output) {
		this.defective_output = defective_output;
	}




	public Integer getTotal_output() {
		return total_output;
	}




	public void setTotal_output(Integer total_output) {
		this.total_output = total_output;
	}




	public Integer getTotal_defective() {
		return total_defective;
	}




	public void setTotal_defective(Integer total_defective) {
		this.total_defective = total_defective;
	}




	public Integer getOutstanding() {
		return outstanding;
	}




	public void setOutstanding(Integer outstanding) {
		this.outstanding = outstanding;
	}




	public String getDefective_output_issues() {
		return defective_output_issues;
	}




	public void setDefective_output_issues(String defective_output_issues) {
		this.defective_output_issues = defective_output_issues;
	}




	public String getDowntime_title() {
		return downtime_title;
	}




	public void setDowntime_title(String downtime_title) {
		this.downtime_title = downtime_title;
	}




	public Double getSpeed() {
		return speed;
	}




	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getSetup_time() {
		return setup_time;
	}

	public void setSetup_time(Double setup_time) {
		this.setup_time = setup_time;
	}
	public Double getDowntime() {
		return downtime;
	}
	public void setDowntime(Double downtime) {
		this.downtime = downtime;
	}




	public int getCount() {
		return count;
	}




	public void setCount(int count) {
		this.count = count;
	}




	public int getId_refer() {
		return id_refer;
	}




	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}




	public String getReference_code() {
		return reference_code;
	}




	public void setReference_code(String reference_code) {
		this.reference_code = reference_code;
	}




	public Integer getAmount_output() {
		return amount_output;
	}




	public void setAmount_output(Integer amount_output) {
		this.amount_output = amount_output;
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
