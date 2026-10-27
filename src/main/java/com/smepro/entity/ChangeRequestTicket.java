package com.smepro.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "change_request_tickets")
public class ChangeRequestTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. ข้อมูลหลักของ Ticket
    @Column(name = "ticket_no", unique = true, nullable = false)
    private String ticketNo; // เลขที่ใบ Ticket เช่น "TK-2603-0001"

    @Column(name = "subject", nullable = false)
    private String subject; // หัวเรื่องตั๋ว เช่น "ขอแก้ไขราคาลูกค้าต่อรอง"

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason; // เหตุผลแบบละเอียดที่ FO พิมพ์มา

    // 2. การเชื่อมโยงกับเอกสารหลัก (จุดสำคัญที่ทำให้ระบบไม่งง!)
    @Column(name = "ref_document_no", nullable = false)
    private String refDocumentNo; // อ้างอิงใบเก่าที่โดนล็อค เช่น "QT-26010"

    @Column(name = "new_revision_no")
    private String newRevisionNo; // เลขใบใหม่ที่ถูกสร้างหลังกด Accept เช่น "QT-26010-Rev01" (ตอนแรกจะว่างไว้)

    // 3. ข้อมูลผู้ใช้งานสำหรับการลงกล่อง (Inbox/Outbox)
    @Column(name = "requester_id", nullable = false)
    private String requesterId; // รหัส FO (คนขอ) -> เอาไว้ออก Outbox ของ FO

    @Column(name = "assignee_id", nullable = false)
    private String assigneeId; // รหัสคนทำ QT (คนรับเรื่อง) -> เอาไปเข้า Inbox คนทำ QT

    @Column(name = "ticket_status", nullable = false)
    private int ticketStatus; 

    @Column(name = "acknowledge", nullable = false)
    private int acknowledge; 
    
    
    @Column(name = "reject_reason", columnDefinition = "TEXT")
    private String rejectReason; // กรณีคนทำ QT กด Reject ให้ใส่เหตุผลตรงนี้

    // 5. Audit Trail (ใครทำอะไรเมื่อไหร่)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt; // เวลาที่คนทำ QT กด Accept หรือ Reject

    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRefDocumentNo() {
		return refDocumentNo;
	}

	public void setRefDocumentNo(String refDocumentNo) {
		this.refDocumentNo = refDocumentNo;
	}

	public String getNewRevisionNo() {
		return newRevisionNo;
	}

	public void setNewRevisionNo(String newRevisionNo) {
		this.newRevisionNo = newRevisionNo;
	}

	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public int getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(int ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public int getAcknowledge() {
		return acknowledge;
	}

	public void setAcknowledge(int acknowledge) {
		this.acknowledge = acknowledge;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getResolvedAt() {
		return resolvedAt;
	}

	public void setResolvedAt(LocalDateTime resolvedAt) {
		this.resolvedAt = resolvedAt;
	}

    
    
    
    
    
    
}