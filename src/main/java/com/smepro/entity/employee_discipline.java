package com.smepro.entity;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.smepro.dao.Identifiable;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "employee_discipline")
public class employee_discipline implements Identifiable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	
	private int employee_id;
	private String employee_name;
    private int warning_count;
    private int penalty_points;
    private String last_warning;
    private String last_penalty_date;
	@Column(name = "edata_id")
	private int edataId;
	private String tempId; 
	private String  doc;
	
	@Transient
	private String  check;
	@Transient
	private String  id_refer;
	@Transient
	private int count;
	
	@Column(name = "created_at", updatable = false, insertable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false)
	private Timestamp updatedAt;
	
	  
	@Transient
	private List<Object> join1;
	@Transient
	private List<Object> join2;


  @Column(name = "processId", columnDefinition = "int default 0")
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
	        return this.id;
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
