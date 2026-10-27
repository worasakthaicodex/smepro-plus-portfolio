package com.smepro.entity;

import javax.persistence.*; // หรือ jakarta.persistence

import com.smepro.dto.savefileDTO;
import com.smepro.models.file;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "help_desk_tickets")
public class HelpDeskTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ข้อมูลลูกค้า/สินค้า ที่อ้างอิง
    @Column(name = "ref_id")
    private Integer refId; // item_id หรือ customer_id

    @Column(name = "ref_name")
    private String refName; // ชื่อลูกค้า (เก็บไว้กันงง)

    // ส่วนของผู้แจ้ง (User)
    @Column(name = "request_topic")
    private String requestTopic; // หัวข้อเรื่อง

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // รายละเอียด

    @Column(name = "priority")
    private String priority = "NORMAL"; // NORMAL, URGENT

    // ส่วนของเจ้าหน้าที่ (Admin)
    @Column(name = "status")
    private String status = "OPEN"; // OPEN, PROCESSING, COMPLETED, REJECTED

    @Column(name = "result_doc_no")
    private String resultDocNo; // เลขที่เอกสารผลลัพธ์ (RE-xxxx)

    @Column(name = "admin_note", columnDefinition = "TEXT")
    private String adminNote; // บันทึกของ Admin

    @Column(name = "is_direct_action")
    private Boolean isDirectAction = false;

    // Timestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;
    
    
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_id")
    private int createdId;

    @Column(name = "update_id")
    private Integer updateId;
    
    @Column(name = "process_id")
    private int processId;
    
    
 // --- แก้ตรงนี้ครับ ---
    @Transient // สำคัญมาก! บอก JPA ว่า field นี้ไม่มีใน DB
    private List<file> attachedFiles; 

    // Getter & Setter
    public List<file> getAttachedFiles() {
        return attachedFiles;
    }

    public void setAttachedFiles(List<file> attachedFiles) {
        this.attachedFiles = attachedFiles;
    }
    
    // Getters & Setters Standard...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getRefId() { return refId; }
    public void setRefId(Integer refId) { this.refId = refId; }
    public String getRefName() { return refName; }
    public void setRefName(String refName) { this.refName = refName; }
    public String getRequestTopic() { return requestTopic; }
    public void setRequestTopic(String requestTopic) { this.requestTopic = requestTopic; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getResultDocNo() { return resultDocNo; }
    public void setResultDocNo(String resultDocNo) { this.resultDocNo = resultDocNo; }
    public String getAdminNote() { return adminNote; }
    public void setAdminNote(String adminNote) { this.adminNote = adminNote; }
    public Boolean getIsDirectAction() { return isDirectAction; }
    public void setIsDirectAction(Boolean isDirectAction) { this.isDirectAction = isDirectAction; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    

	public int getCreatedId() {return createdId;}
	public void setCreatedId(int createdId) {this.createdId = createdId;}

	
	
	
	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	@PrePersist
    protected void onCreate() { createdAt = LocalDateTime.now(); }
	
	
	
}