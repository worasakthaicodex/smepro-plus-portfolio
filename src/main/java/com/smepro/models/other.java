package com.smepro.models;

import java.lang.reflect.Field;
import java.util.List;

import com.smepro.dao.Identifiable;

public class other  extends custom_report  implements Identifiable  {

	private int id;
	private int edata_id;
	private String tempId;
	private String doc_code;
	private String product_code;
	private String product_name;
	private String lot_batch;
	private String lot;
	private String process_step;
	private String department;
	
	private String list;
	private String employee;
	private String inspection;
	private String inspection2;
	private String inspection3;
	private String inspection4;
	private String inspection5;
	private String inspection6;
	
	
	private String equipment;
	private String date_start;
	private int count;
	private int check = 0;
	private int  id_refer;
	
	private String grouping;
	private String classification;
	
	
	private int other_signature = 0;
	private int other_encrypted = 0;

	
	private String reference_code;

	private Double order_quantity;
	private String unit;
	private Double received_today;
	private Double received_accumulated;
	private Double pending_received;
	private Double paid_today;
	private Double paid_accumulated;
	private Double pending_paid;

	private String data_nameadd;
	
	


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
	
	
	

		public other() {
			this.other_signature = 0;
			this.other_encrypted = 0;
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
	public String getTempId() {
		return tempId;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
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
	public String getLot_batch() {
		return lot_batch;
	}
	public void setLot_batch(String lot_batch) {
		this.lot_batch = lot_batch;
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
	public String getDoc_code() {
		return doc_code;
	}
	public void setDoc_code(String doc_code) {
		this.doc_code = doc_code;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getInspection() {
		return inspection;
	}
	public void setInspection(String inspection) {
		this.inspection = inspection;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getDate_start() {
		return date_start;
	}
	public void setDate_start(String date_start) {
		this.date_start = date_start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getId_refer() {
		return id_refer;
	}
	public void setId_refer(int id_refer) {
		this.id_refer = id_refer;
	}
	public String getGrouping() {
		return grouping;
	}
	public void setGrouping(String grouping) {
		this.grouping = grouping;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getOther_signature() {
		return other_signature;
	}
	public void setOther_signature(int other_signature) {
		this.other_signature = other_signature;
	}
	public int getOther_encrypted() {
		return other_encrypted;
	}
	public void setOther_encrypted(int other_encrypted) {
		this.other_encrypted = other_encrypted;
	}

	public String getInspection2() {
		return inspection2;
	}

	public void setInspection2(String inspection2) {
		this.inspection2 = inspection2;
	}

	public String getInspection3() {
		return inspection3;
	}

	public void setInspection3(String inspection3) {
		this.inspection3 = inspection3;
	}

	public String getInspection4() {
		return inspection4;
	}

	public void setInspection4(String inspection4) {
		this.inspection4 = inspection4;
	}

	public String getInspection5() {
		return inspection5;
	}

	public void setInspection5(String inspection5) {
		this.inspection5 = inspection5;
	}

	public String getInspection6() {
		return inspection6;
	}

	public void setInspection6(String inspection6) {
		this.inspection6 = inspection6;
	}

	public Double getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(Double order_quantity) {
		this.order_quantity = order_quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getReference_code() {
		return reference_code;
	}

	public void setReference_code(String reference_code) {
		this.reference_code = reference_code;
	}

	public Double getReceived_today() {
		return received_today;
	}

	public void setReceived_today(Double received_today) {
		this.received_today = received_today;
	}

	public Double getReceived_accumulated() {
		return received_accumulated;
	}

	public void setReceived_accumulated(Double received_accumulated) {
		this.received_accumulated = received_accumulated;
	}

	public Double getPending_received() {
		return pending_received;
	}

	public void setPending_received(Double pending_received) {
		this.pending_received = pending_received;
	}

	public Double getPaid_today() {
		return paid_today;
	}

	public void setPaid_today(Double paid_today) {
		this.paid_today = paid_today;
	}

	public Double getPaid_accumulated() {
		return paid_accumulated;
	}

	public void setPaid_accumulated(Double paid_accumulated) {
		this.paid_accumulated = paid_accumulated;
	}

	public Double getPending_paid() {
		return pending_paid;
	}

	public void setPending_paid(Double pending_paid) {
		this.pending_paid = pending_paid;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}
	
	
	

	public String getData_nameadd() {
		return data_nameadd;
	}

	public void setData_nameadd(String data_nameadd) {
		this.data_nameadd = data_nameadd;
	}
	
	
	

	@Override
	public String toString() {
		return String.format(
				"other [id=%s, edata_id=%s, tempId=%s, doc_code=%s, product_code=%s, product_name=%s, lot_batch=%s, process_step=%s, department=%s, list=%s, employee=%s, inspection=%s, inspection2=%s, inspection3=%s, inspection4=%s, inspection5=%s, inspection6=%s, equipment=%s, date_start=%s, count=%s, check=%s, id_refer=%s, grouping=%s, classification=%s, other_signature=%s, other_encrypted=%s, reference_code=%s, order_quantity=%s, unit=%s, received_today=%s, received_accumulated=%s, pending_received=%s, paid_today=%s, paid_accumulated=%s, pending_paid=%s, month1=%s, month2=%s, month3=%s, month4=%s, month5=%s, month6=%s, month7=%s, month8=%s, month9=%s, month10=%s, month11=%s, month12=%s, target=%s, year=%s, summarize=%s, old_summarize=%s, standard_deviation=%s, variance=%s, rsd=%s, binning=%s, frequency=%s, title=%s, getId()=%s, getEdata_id()=%s, getTempId()=%s, getProduct_code()=%s, getProduct_name()=%s, getLot_batch()=%s, getProcess_step()=%s, getDepartment()=%s, getDoc_code()=%s, getList()=%s, getEmployee()=%s, getInspection()=%s, getEquipment()=%s, getDate_start()=%s, getCount()=%s, getCheck()=%s, getId_refer()=%s, getGrouping()=%s, getClassification()=%s, getOther_signature()=%s, getOther_encrypted()=%s, getInspection2()=%s, getInspection3()=%s, getInspection4()=%s, getInspection5()=%s, getInspection6()=%s, getOrder_quantity()=%s, getUnit()=%s, getReference_code()=%s, getReceived_today()=%s, getReceived_accumulated()=%s, getPending_received()=%s, getPaid_today()=%s, getPaid_accumulated()=%s, getPending_paid()=%s, getMonth1()=%s, getMonth2()=%s, getMonth3()=%s, getMonth4()=%s, getMonth5()=%s, getMonth6()=%s, getMonth7()=%s, getMonth8()=%s, getMonth9()=%s, getMonth10()=%s, getMonth11()=%s, getMonth12()=%s, getTarget()=%s, getYear()=%s, getSummarize()=%s, getOld_summarize()=%s, getStandard_deviation()=%s, getVariance()=%s, getRsd()=%s, getBinning()=%s, getFrequency()=%s, getTitle()=%s, getClass()=%s, hashCode()=%s, toString()=%s]",
				id, edata_id, tempId, doc_code, product_code, product_name, lot_batch, process_step, department, list,
				employee, inspection, inspection2, inspection3, inspection4, inspection5, inspection6, equipment,
				date_start, count, check, id_refer, grouping, classification, other_signature, other_encrypted,
				reference_code, order_quantity, unit, received_today, received_accumulated, pending_received,
				paid_today, paid_accumulated, pending_paid, month1, month2, month3, month4, month5, month6, month7,
				month8, month9, month10, month11, month12, target, year, summarize, old_summarize, standard_deviation,
				variance, rsd, binning, frequency, title, getId(), getEdata_id(), getTempId(), getProduct_code(),
				getProduct_name(), getLot_batch(), getProcess_step(), getDepartment(), getDoc_code(), getList(),
				getEmployee(), getInspection(), getEquipment(), getDate_start(), getCount(), getCheck(), getId_refer(),
				getGrouping(), getClassification(), getOther_signature(), getOther_encrypted(), getInspection2(),
				getInspection3(), getInspection4(), getInspection5(), getInspection6(), getOrder_quantity(), getUnit(),
				getReference_code(), getReceived_today(), getReceived_accumulated(), getPending_received(),
				getPaid_today(), getPaid_accumulated(), getPending_paid(), getMonth1(), getMonth2(), getMonth3(),
				getMonth4(), getMonth5(), getMonth6(), getMonth7(), getMonth8(), getMonth9(), getMonth10(),
				getMonth11(), getMonth12(), getTarget(), getYear(), getSummarize(), getOld_summarize(),
				getStandard_deviation(), getVariance(), getRsd(), getBinning(), getFrequency(), getTitle(), getClass(),
				hashCode(), super.toString());
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


