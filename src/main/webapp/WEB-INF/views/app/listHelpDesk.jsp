<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="cl_window">
    <div class="window-header">
        <span> ประวัติการแจ้งดำเนินการ (Help Desk History)</span>
        <button class="primary" onclick="shows2('draggable-window3','New Request'); getData('/HelpDesk-filemanager/Profile/${item_id}/${process_id}', 'mainbody3');">
            + แจ้งเรื่องใหม่
        </button>
    </div>

    <div class="window-body">
        <div class="table-container" style="height: 400px; overflow-y: auto;">
            <table border="1" style="width:100%; border-collapse: collapse;">
                <thead>
                    <tr style="background:#f0f0f0;">
                        <th style="padding:5px;">ID</th>
                        <th style="padding:5px;">วันที่</th>
                        <th style="padding:5px;">หัวข้อ</th>
                        <th style="padding:5px;">รายละเอียด</th>
                        <th style="padding:5px;">สถานะ</th>
                        <th style="padding:5px;">ผลลัพธ์ (Ref)</th>
                       <th style="padding:5px;">โดย</th>
                        <th style="padding:5px;">จัดการ</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tickets}" var="t">
                    <tr>
                        <td style="padding:5px;">${t.id}</td>
                        <td style="padding:5px;">${t.createdAt}</td>
                        <td style="padding:5px;">${t.requestTopic}</td>
                     <td>
    ${t.description}

    <c:if test="${not empty t.attachedFiles}">
        <div style="margin-top:5px; font-size:12px;">
            <strong>📎 ไฟล์แนบ:</strong><br>
            
            <c:forEach items="${t.attachedFiles}" var="f">
                
                    📄 ${f.file_name_original} 
                    <span style="color:#888;">(${f.file_size2})</span>
               
                <br>
            </c:forEach>
            
        </div>
    </c:if>
</td>
                        <td style="padding:5px;">
                            <span style="font-weight:bold; color:${t.status == 'COMPLETED' ? 'green' : 'orange'}">
                                ${t.status}
                            </span>
                        </td>
                        <td style="padding:5px;">
                            <c:if test="${not empty t.resultDocNo}">
                                <a href="#">${t.resultDocNo}</a>
                            </c:if>
                        </td>
                         <td style="padding:5px;">
                            ${t.createdBy}
                        </td>
                        
                        <td style="padding:5px; text-align:center;">
                         
                <c:set var="accessRole" value="APP-USER-${process.id}" />

<sec:authorize access="hasAnyAuthority('${accessRole}') or #t.createdId == #Member_id">            
                            <span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect">
        <a  onclick="shows2('draggable-window3','Edit Ticket #${t.id}'); getData('/HelpDesk-filemanager/Profile/${item_id}/${process_id}?ticket_id=${t.id}', 'mainbody3');" data-original-title="Img">
            <img src="/app/system/icon/preview.ico">
         </a>
         
      </span>



	<span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect">
        <a onclick="getData('/delHelpDesk-filemanager/${t.id}/${item_id}/${process.id }', 'main${item_id}')" data-original-title="Img">
            <img src="/app/system/icon/recycle bin.ico">
         </a>
      </span>
      
      
	</sec:authorize>	        
                            
                        </td>
                    </tr>
                    </c:forEach>
                    <c:if test="${empty tickets}">
                        <tr><td colspan="8" style="text-align:center; padding:10px;">ไม่พบรายการแจ้งปัญหา</td></tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
  
</div>