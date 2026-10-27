 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    /* 1. Container หลัก: จัดกลุ่มรายการไฟล์ให้มีกรอบคลุมดูเรียบร้อย */
    .file-chip-container {
        display: flex;
        flex-direction: column;
        gap: 8px; 
        padding: 12px;
        background-color: #f8fafc; /* พื้นหลังสีเทาอ่อนให้ตัดกับตัวการ์ดไฟล์ */
        border: 1px solid #e2e8f0;
        border-radius: 8px;
    }

    /* 2. กล่องย่อยแต่ละไฟล์ (Card Style) */
    .file-chip {
        display: flex;
        align-items: center;
        background-color: #ffffff;
        border: 1px solid #e2e8f0;
        border-radius: 6px;
        padding: 10px 12px; 
        transition: all 0.2s ease;
        width: 100%;
        box-shadow: 0 1px 2px rgba(0,0,0,0.02);
    }

    .file-chip:hover {
        background-color: #f1f5f9;
        border-color: #cbd5e1;
        box-shadow: 0 2px 4px rgba(0,0,0,0.06);
    }

    /* 3. ส่วนไอคอน หรือ รูปภาพ (ซ้ายสุด) */
    .file-icon-left {
        margin-right: 14px;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 32px;
        height: 32px;
        flex-shrink: 0;
        background-color: #f1f5f9; /* พื้นหลังรองรับไอคอน */
        border-radius: 4px;
    }

    .file-thumbnail {
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 4px;
        border: 1px solid #e2e8f0;
    }

    .icon-default { color: #64748b; }
    .icon-pdf { color: #ef4444; } /* PDF สีแดง */

    /* 4. ส่วนชื่อไฟล์และ Tag (ตรงกลาง) */
    .file-name-container {
        display: flex;
        flex-direction: column;
        flex-grow: 1;
        overflow: hidden;
        margin-right: 12px;
    }

    .file-name {
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        font-size: 13px;
        font-weight: 500;
        color: #1e293b;
    }

    .file-meta {
        font-size: 11px;
        color: #64748b;
        margin-top: 3px;
    }

    /* 5. ส่วนปุ่ม Action (ขวาสุด) */
    .file-actions {
        display: flex;
        align-items: center;
        gap: 6px;
        margin-left: auto;
        flex-shrink: 0;
        border-left: 1px solid #e2e8f0;
        padding-left: 12px;
    }

    .action-btn {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 30px;
        height: 30px;
        border-radius: 4px;
        color: #94a3b8;
        text-decoration: none;
        transition: all 0.2s;
        background-color: transparent;
    }

    .action-btn svg {
        width: 16px;
        height: 16px;
    }

    /* Hover effect สำหรับปุ่มตา และ ถังขยะ */
    .btn-view:hover { 
        color: #0ea5e9; 
        background-color: #e0f2fe; 
    }
    .btn-delete:hover { 
        color: #ef4444; 
        background-color: #fee2e2; 
    }
</style>

<div class="file-chip-container">
    <c:forEach items="${files}" var="row" varStatus="count">
        
        <%-- เตรียมตัวแปรสำหรับ URL และเช็คนามสกุลไฟล์ --%>
        <c:set var="fileUrl" value="/document/${row.file_path}/${row.file_name}" />
        <c:set var="fileNameLower" value="${fn:toLowerCase(row.file_name_original)}" />
        <c:set var="isImage" value="${fn:endsWith(fileNameLower, '.jpg') || fn:endsWith(fileNameLower, '.jpeg') || fn:endsWith(fileNameLower, '.png') || fn:endsWith(fileNameLower, '.gif') || fn:endsWith(fileNameLower, '.webp')}" />
        <c:set var="isPdf" value="${fn:endsWith(fileNameLower, '.pdf')}" />

        <div class="file-chip">
            
            <%-- โซน 1: ไอคอน / รูปภาพ Thumbnail --%>
            <div class="file-icon-left">
                <c:choose>
                    <c:when test="${isImage}">
                        <img src="${fileUrl}" alt="thumbnail" class="file-thumbnail" loading="lazy" />
                    </c:when>
                    <c:when test="${isPdf}">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon-pdf" width="18" height="18">
                            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                            <polyline points="14 2 14 8 20 8"></polyline>
                            <path d="M16 13H8"></path><path d="M16 17H8"></path><path d="M10 9H8"></path>
                        </svg>
                    </c:when>
                    <c:otherwise>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon-default" width="18" height="18">
                            <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                            <polyline points="14 2 14 8 20 8"></polyline>
                        </svg>
                    </c:otherwise>
                </c:choose>
            </div>

            <%-- โซน 2: ชื่อไฟล์ และ Tag (จัดเป็น 2 บรรทัดบนล่าง) --%>
          <div class="file-name-container">
		    <span class="file-name" title="${row.file_name_original}">
		        ${row.file_name_original}          
		    </span>
		    <div class="file-meta">
		        <c:choose>
		            <c:when test="${not empty row.file_tag}">
		                📌 ${row.file_tag}
		                <input type="hidden" class="uploaded_file_tag" value="${row.file_tag}">
		            </c:when>
		            <c:otherwise>
		                📄 ไม่ระบุประเภท
		            </c:otherwise>
		        </c:choose>
		    </div>
		</div>
            
            <%-- โซน 3: ปุ่ม Action (เปิดดู / ลบ) --%>
            <div class="file-actions">
                <a href="${fileUrl}" target="_blank" class="action-btn btn-view" title="เปิดดูไฟล์">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                        <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path>
                        <circle cx="12" cy="12" r="3"></circle>
                    </svg>
                </a>

                <c:if test="${row.file_by == USERNAME }">
                    <a href="javascript:void(0);" 
                       class="action-btn btn-delete"
                       onclick="return getData('/unfile_app-filemanager/${row.file_id}/${edatas.data_id}','subdataformupload${edatas.data_id}');"
                       title="ลบไฟล์">
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                            <polyline points="3 6 5 6 21 6"></polyline>
                            <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                        </svg>
                    </a>
                </c:if>
            </div>
            
        </div>
    </c:forEach>
</div>