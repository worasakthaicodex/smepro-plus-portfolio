      <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
  #table-wrapper thead th {
    position: sticky;
    top: 0;
    background: white;   /* ต้องมี ไม่งั้นมันจะโปร่ง */
    z-index: 10;
}
 </style>    
<c:if test="${process.register == 'Profile'}">
<c:set  var="url"  value="profileapp-filemanager" />
</c:if>
<c:if test="${process.register == 'Product'}">
<c:set  var="url"  value="productapp-filemanager" />
</c:if>

        <div class="wpf-header-text"><spring:message code="table.DataGridSample"  text="-" />: ${exampleData.size() } 
                  <spring:message code="table.for"  text="-" /> ${countData } 
                  <spring:message code="table.rows"  text="-" />
     
     
         <div style="float:right">
         <form action="#" id="myForms${process.id}"   name="myForm" modelAttribute="edata"   method="POST" enctype="multipart/form-data" >
		                       <input type="text"  id="search_input_id" name="search" placeholder="Search" style="padding: 5px" 
		       onkeyup="if (event.key === 'Enter') { 
		           saveSearchValue();postDataNotReset('/indexappsearch-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', 'wpf-searchdata${process.id}'); 
		       }"
		       onkeydown="if(event.key === 'Enter') event.preventDefault();" >
		       
		       
                         <input type="hidden" name="id" value="${process.id+0}"  id="id"  placeholder="Search">
                       
<a href="#" id="btn_search_id" class="btn btn-primary" onclick="copyCodesFromTable(event)" style="padding: 5px 10px;color:black">
    <i class="fa fa-copy"></i>
</a>
                         
                  <a href="#" id="btn_search_id" class="btn btn-primary" onclick="toggleRightPanel(event)" style="padding: 5px 10px;color:black">
           <i class="fa fa-magnifying-glass-chart"></i>
</a>
                     </form>     
                   
  
                     </div>
     </div>
                        
                  <div style="overflow:auto !important;max-height: 100%"  class="wpf-datagrid-container"> 
                        <table id="table-wrapper">
                            <thead>
                                <tr >
                                    <th>#</th>
                                      <th><spring:message code="${profile.id}"  text="-" /></th>
                                   <c:forEach items="${heads }" var="row" varStatus="count"> 
                     					   <th style="max-width:200px"><spring:message code="${row.title}"  text="${row.title}" /></th>
                   				  </c:forEach> 
                               <th>คำขอ</th>
                             
                                
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${exampleData }" var="rows" varStatus="counts"> 
	                    <tr ondblclick="getData('/${url}/${rows.id}/${process.id}?from=${from+0 }', 'microsofts${process.id}')" style="cursor:copy;">
	                        <td  style="border-left: 0px solid #bbb;">${counts.count }</td>
	                        
	                        <c:if test="${registerType == 'Profile' }">
	                         <td  style="border-left: 0px solid #bbb;">${rows['register_code']}${rows['register_daterun']}-${rows['register_id']}</td>
	                          </c:if>
	                               <c:if test="${registerType == 'Product' }">
	                         <td  style="border-left: 0px solid #bbb;">${rows['product_code']}${rows['product_daterun']}-${rows['product_id']}</td>
	                          </c:if> 
	                          
	                  <c:forEach items="${heads }" var="row" varStatus="count"> 
                    <td style="
    border-left: 0px solid #bbb;
    max-width: 200px; 
    white-space: nowrap; 
    overflow: hidden; 
    text-overflow: ellipsis;
" title="${rows[row.mainkey]}">${rows[row.mainkey]}</td>
                       
                     </c:forEach> 
	             <td style="border-left: 0px solid #bbb;">${rows["extension_column_20"]}</td>
	           
	                     
	                    </tr>
                      </c:forEach>   
                        
                            </tbody>
                        </table>
                        </div>
              
              
              
        <script>      
        function copyCodesFromTable(event) {
            // ป้องกันไม่ให้หน้าจอเลื่อนหรือ Refresh
            event.preventDefault();

            // 1. เข้าถึงตารางและแถวทั้งหมดใน tbody
            const table = document.getElementById('table-wrapper');
            const rows = table.querySelectorAll('tbody tr');
            let codeList = [];

            // 2. วนลูปดึงข้อมูลจากคอลัมน์ที่ 2 (index คือ 1)
            rows.forEach(row => {
                const cells = row.querySelectorAll('td');
                if (cells.length >= 2) {
                    // ดึงข้อความ ตัดช่องว่างออก (trim)
                    const codeText = cells[1].innerText.trim(); 
                    if (codeText) {
                        codeList.push(codeText);
                    }
                }
            });

            // 3. รวมรหัสด้วยเครื่องหมาย comma (,)
            const finalString = codeList.join(',');

            if (finalString === "") {
                alert("ไม่มีข้อมูลรหัสให้คัดลอก");
                return;
            }

            // 4. คำสั่งคัดลอกลง Clipboard (ปรับปรุงใหม่ ทะลุบล็อก HTTP)
            // เช็กก่อนว่าเบราว์เซอร์รองรับ Modern API และอยู่ในโหมดปลอดภัย (HTTPS/localhost) ไหม
            if (navigator.clipboard && window.isSecureContext) {
                navigator.clipboard.writeText(finalString).then(() => {
                    console.log("Copied (Modern): " + finalString);
                    alert("คัดลอกรหัสเรียบร้อยแล้ว: " + finalString);
                }).catch(err => {
                    console.error('Modern API ถูกบล็อก, กำลังสลับไปใช้ Fallback', err);
                    fallbackCopyTextToClipboard(finalString); // ถ้าโดนบล็อก ให้เรียกแผนสำรอง
                });
            } else {
                // ถ้าเป็น HTTP (เช่น รันผ่าน IP Tomcat) จะวิ่งเข้าแผนสำรองทันที
                fallbackCopyTextToClipboard(finalString);
            }
        }

        // ==========================================
        // ฟังก์ชันแผนสำรอง (สร้าง Textarea ล่องหน)
        // ==========================================
        function fallbackCopyTextToClipboard(text) {
            // สร้างช่องกรอกข้อความจำลอง
            var textArea = document.createElement("textarea");
            textArea.value = text;
            
            // ซ่อนไม่ให้ผู้ใช้มองเห็น และไม่ให้หน้าจอกระตุก
            textArea.style.top = "0";
            textArea.style.left = "0";
            textArea.style.position = "fixed";
            textArea.style.opacity = '0';

            // แปะลงเว็บ -> โฟกัส -> ระบายสีคลุมข้อความ
            document.body.appendChild(textArea);
            textArea.focus();
            textArea.select();

            try {
                // สั่งคัดลอกแบบคลาสสิก (ทะลุบล็อกบน HTTP ได้)
                var successful = document.execCommand('copy');
                if (successful) {
                    console.log("Copied (Fallback): " + text);
                    alert("คัดลอกรหัสเรียบร้อยแล้ว: " + text);
                } else {
                    alert("เบราว์เซอร์ของคุณไม่อนุญาตให้คัดลอก");
                }
            } catch (err) {
                console.error('Fallback copy พัง: ', err);
                alert("เกิดข้อผิดพลาดในการคัดลอก");
            }

            // ทำลายช่องกรอกข้อความทิ้งหลังคัดลอกเสร็จ
            document.body.removeChild(textArea);
        }
</script>