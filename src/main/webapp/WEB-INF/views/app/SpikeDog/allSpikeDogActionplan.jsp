<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="actionplan-container" style="display: flex; flex-direction: column; width: 100%; max-height: 250px; background: #fff; border: 1px solid #b5b5b5;">
    
    <div class="db-toolbar" style="flex-shrink: 0;">
<div class="db-toolbar-group" style="display: flex; align-items: center; gap: 5px;">
    <button class="db-btn view-item" title="ตัวกรองข้อมูล" 
        onclick="openwindow('draggable-window3','ค้นหาข้อมูล','9000');getData('/SpikeDogQuickQueryChain-filemanager', 'mainbody3');" 
        style="pointer-events: auto; cursor: pointer;">
        <svg viewBox="0 0 24 24">
            <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
        </svg>
    </button>
    
    <button class="db-btn view-item" style="pointer-events: auto; cursor: pointer;" title="ส่งออก CSV (ดาวน์โหลดทั้งหมด)" onclick="viewer_exportCSV()">
        <svg viewBox="0 0 24 24"><path d="M19 9h-4V3H9v6H5l7 7 7-7zM5 18v2h14v-2H5z"/></svg>
    </button>

    <div style="border-left: 1px solid #ccc; height: 24px; margin: 0 10px;"></div> <button class="db-btn view-item" title="หน้าก่อนหน้า" onclick="viewer_prevPage()" style="cursor: pointer;">
        <svg viewBox="0 0 24 24"><path d="M15.41 16.59L10.83 12l4.58-4.59L14 6l-6 6 6 6 1.41-1.41z"/></svg>
    </button>
    
    <span id="table-page-info" style="font-size: 13px; font-weight: bold; color: #475569; min-width: 80px; text-align: center;">1-100</span>
    
    <button class="db-btn view-item" title="หน้าถัดไป" onclick="viewer_nextPage()" style="cursor: pointer;">
        <svg viewBox="0 0 24 24"><path d="M8.59 16.59L13.17 12 8.59 7.41 10 6l6 6-6 6-1.41-1.41z"/></svg>
    </button>
</div>
        
        <div class="db-separator"></div>
        
        <div class="db-toolbar-group">
            <button class="db-btn view-item" style="pointer-events: auto; cursor: pointer;"  title="เพิ่ม Action Plan" 
                    onclick="openwindow('draggable-window6','Add Action Plan','10000');getData('/addSpikeDogActionplan-filemanager/${idreport}', 'mainbody6');">
                <svg viewBox="0 0 24 24" style="fill: #217346;"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
            </button>
        </div>
        
        <div style="margin-left: auto; font-size: 13px; color: #555; font-family: 'Segoe UI', Tahoma, sans-serif; padding-right: 12px;">
          Report : ${reports.reportName} | Action Plan : <span id="db-record-count">${fn:length(listActionplanData)}</span> Rows
        </div>
    </div>

    <div class="db-grid-container" style="flex-grow: 1; overflow-y: auto;">
       <table class="db-grid-table" style="width: 100%; min-width: 800px; border-collapse: collapse; table-layout: fixed;">
            <thead>
                <tr>
                    <th class="row-header" style="position: sticky; top: 0; z-index: 2; background: #f5f5f5; width: 40px;"></th>
                    <th style="width: 25%; position: sticky; top: 0; z-index: 1; background: #f5f5f5;">ปัญหา / หัวข้อ</th>
                    <th style="width: 25%; position: sticky; top: 0; z-index: 1; background: #f5f5f5;">แผนดำเนินการ (Action Plan)</th>
                    <th style="width: 15%; position: sticky; top: 0; z-index: 1; background: #f5f5f5;">สถานะ</th>
                    <th style="width: 10%; position: sticky; top: 0; z-index: 1; background: #f5f5f5;">กำหนดเสร็จ</th>
                    <th style="width: 15%; position: sticky; top: 0; z-index: 1; background: #f5f5f5;">ผู้รับผิดชอบ</th>
                    <th style="width: 50px; position: sticky; top: 0; z-index: 1; background: #f5f5f5; text-align: center;">⚙️</th>
                </tr>
            </thead>
            <tbody id="action-plan-tbody">
                
                <c:forEach var="item" items="${listActionplanData}" varStatus="status">
                    <tr>
                        <td class="row-header" style="text-align: center;">${status.index + 1}</td>
        
                        <td style="padding: 4px 8px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" title="${item.issueTopic}">
                            ${item.issueTopic}
                        </td>
                        
                        <td style="padding: 4px 8px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" title="${item.actionPlanDetails}">
                            ${item.actionPlanDetails}
                        </td>

                        <td style="padding: 4px 8px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; font-size: 11px;">
                            <c:choose>
                                <c:when test="${item.status == 'Completed'}"><span style="color: #107C41; font-weight: bold;">● เสร็จสิ้น</span></c:when>
                                <c:when test="${item.status == 'In Progress'}"><span style="color: #005FB8; font-weight: bold;">● กำลังทำ</span></c:when>
                                <c:when test="${item.status == 'Not Started'}"><span style="color: #7A7574;">○ รอดำเนินการ</span></c:when>
                                <c:when test="${item.status == 'Cancelled'}"><span style="color: #D13438;">⊗ ยกเลิก</span></c:when>
                                <c:when test="${item.status == 'On Hold'}"><span style="color: #8A2BE2;">⏸ ระงับชั่วคราว</span></c:when>
                                <c:otherwise><span style="color: #888;">${empty item.status ? '-' : item.status}</span></c:otherwise>
                            </c:choose>
                        </td>

                        <td style="padding: 4px 8px; text-align: center; white-space: nowrap;">
                            ${empty item.targetDate ? '-' : item.targetDate}
                        </td>

                        <td style="padding: 4px 8px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;" title="${item.owner}">
                            ${item.owner}
                        </td>
                        
                        <td style="padding: 4px 8px; text-align: center;">
                            <button class="db-btn view-item" title="แก้ไข/ดูรายละเอียด" 
                                    onclick="openwindow('draggable-window6','Edit Action Plan','10000');getData('/editSpikeDogActionplan-filemanager/${item.idreport}/${item.id}', 'mainbody6');"
                                    style="padding: 2px;">
                                <svg viewBox="0 0 24 24" style="width: 14px; height: 14px; fill: #005FB8;"><path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/></svg>
                            </button>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty listActionplanData}">
                    <tr>
                        <td colspan="7" style="text-align: center; padding: 20px; color: #888;">ไม่พบข้อมูล Action Plan</td>
                    </tr>
                </c:if>

            </tbody>
        </table>
    </div>
</div>