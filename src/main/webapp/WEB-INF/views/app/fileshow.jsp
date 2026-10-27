<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:if test='${file.file_type.equals("image/png") or file.file_type.equals("image/webp") or file.file_type.equals("image/jpg") or file.file_type.equals("image/jpeg") }'>
	    <div style="position: relative;max-height: 700px; overflow-y:auto">
    <img src="/document-filemanager/${Pathfulls}/${file.file_name}" alt="Your Image" style="width: 100%; height: auto; z-index: 1;" />
    <div style="position: absolute; top: 0; left: 0; width: 100%; z-index: 2;"></div>
</div>
		 
</c:if>

<%-- ส่วนจัดการ PDF และไฟล์อื่นๆ --%>
<c:if test="${file.file_type.equals('application/pdf')}">
    <c:choose>
        <%-- กรณีเป็น PDF ให้แสดงพรีวิว --%>
        <c:when test="${file.file_type.equals('application/pdf')}">
            <br>
            <div style="width: 100%; height: 800px; border: 1px solid #ccc;">
                <iframe src="/document-filemanager/${Pathfulls}/${file.file_name}" width="100%" height="100%" frameborder="0">
                    <p>เบราว์เซอร์ของคุณไม่รองรับการแสดง PDF <a href="/document-filemanager/${Pathfulls}/${file.file_name}">คลิกที่นี่เพื่อดาวน์โหลด</a></p>
                </iframe>
            </div>
        </c:when>
        
        <%-- กรณีไม่ใช่ PDF (เช่น octet-stream) ให้แสดงปุ่มดาวน์โหลด --%>
        <c:otherwise>
            <br>
            <div style="padding: 20px; text-align: center; background-color: #f9f9f9; border: 1px dashed #ccc;">
                <p>ไฟล์นี้ไม่สามารถแสดงตัวอย่างได้</p>
                <a href="/document-filemanager/${Pathfulls}/${file.file_name}" target="_blank" download class="btn btn-primary">
                    คลิกเพื่อดาวน์โหลดไฟล์
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</c:if>