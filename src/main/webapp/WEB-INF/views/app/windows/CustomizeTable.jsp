<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <title>ตั้งค่าชื่อคอลัมน์</title>
    <style>
        table { border-collapse: collapse; width: 99%;margin:auto  }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f4f4f4; }
        input[type="text"] { width: 100%;  box-sizing: border-box; }
        .btn-save { padding: 8px 16px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        .btn-save:hover { background-color: #218838; }
    </style>
</head>
<body>

       <c:forEach var="header" items="${TableHeaders}">
       ${header.ColumnName}
       </c:forEach>
       
    <form id="customizeForm">
        <table>
            <thead>
                <tr>
                    <th>ลำดับ (ID)</th>
                    <th>ชื่อคอลัมน์เดิม (Read-only)</th>
                    <th>ชื่อที่ต้องการแสดง (แก้ไขได้)</th>
                </tr>
            </thead>
            <tbody id="tableBody">
            

    
                
                <c:forEach var="header" items="${TableHeaders}">
                    <tr>
                        <td>${header.id}</td>
                        <td>
                            ${header.columnName}
                            
                            <input type="hidden" class="data-id" value="${header.id}">
                            <input type="hidden" class="data-type" value="${header.type}">
                            <input type="hidden" class="data-column" value="${header.columnName}">
                        </td>
                        <td>
                            <input type="text" class="data-custom" value="${header.customizeName}">
                        </td>
                    </tr>
                </c:forEach>
                
            </tbody>
        </table>
        
        <button type="button" class="btn-save" onclick="submitData()">บันทึกข้อมูล</button>
    </form>

    <script>
        function submitData() {
            const rows = document.querySelectorAll('#tableBody tr');
            const postData = []; 

            // วนลูปอ่านค่าจากแต่ละแถวในตาราง
            rows.forEach(row => {
                const id = row.querySelector('.data-id').value;
                const type = row.querySelector('.data-type').value;
                const columnName = row.querySelector('.data-column').value;
                const customizeName = row.querySelector('.data-custom').value;

                // เพิ่ม Object เข้าไปใน Array
                postData.push({
                    id: parseInt(id),
                    type: type,
                    columnName: columnName,
                    customizeName: customizeName
                });
            });

            console.log("เตรียมส่งข้อมูล postData:", postData);

            // ส่งข้อมูลไปยัง Backend ด้วย Fetch API
            // (อย่าลืมเปลี่ยน URL ให้ตรงกับ @PostMapping ใน Controller ของคุณ)
            fetch('/updateCustomizeTableHeaders-filemanager', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(postData)
            })
            .then(response => {
                if(response.ok) {
                    return response.json();
                }
                throw new Error('Network response was not ok.');
            })
            .then(data => {
                alert('บันทึกข้อมูลสำเร็จ!');
                // สามารถสั่งรีเฟรชหน้า หรือ ปิดหน้าต่างต่อได้ที่นี่
            })
            .catch(error => {
                console.error('Error:', error);
                alert('เกิดข้อผิดพลาดในการบันทึกข้อมูล');
            });
        }
    </script>

</body>
</html>