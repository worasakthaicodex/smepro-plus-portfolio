<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <title>Notification Panel</title>
    <style>
        /* ใช้ ID ครอบทั้งหมดเพื่อป้องกัน CSS ชนกับส่วนอื่น (Scope Isolation) */
        #jerry-notification-container {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            width: 100%;
            background-color: #f0f3f5;
            border: 1px solid #d1d8de;
            border-radius: 4px;
            overflow: hidden;
            display: flex;
            flex-direction: column;
            /* ปรับความยาวให้ลงสุดตามต้องการ */
            height: 95vh; 
            margin: 0px;
        }

        #jerry-notification-container .noti-header {
            padding: 12px 15px;
            background: #ffffff;
            border-bottom: 2px solid #ff8c00; /* เส้นสีส้มแบบในรูป */
            display: flex;
            align-items: center;
            font-weight: bold;
            color: #444;
        }

        #jerry-notification-container .noti-header i {
            margin-right: 10px;
        }

        #jerry-notification-container .noti-list {
            overflow-y: auto; /* ยาวลงสุดและ scroll ได้ */
            flex-grow: 1;
            background: #ffffff;
        }

        #jerry-notification-container .noti-item {
            border-bottom: 1px solid #eee;
            transition: background 0.2s;
            cursor: pointer;
        }

        #jerry-notification-container .noti-item:hover {
            background-color: #f9f9f9;
        }

        #jerry-notification-container .noti-main-row {
            padding: 12px 15px;
            display: flex;
            align-items: flex-start;
        }

        #jerry-notification-container .noti-icon {
            width: 24px;
            margin-right: 12px;
            color: #666;
            text-align: center;
        }

        #jerry-notification-container .noti-content {
            flex-grow: 1;
        }

        #jerry-notification-container .noti-title {
            font-size: 0.9em;
            color: #557a95; /* สีฟ้าหม่นแบบในรูป */
            margin-bottom: 4px;
        }

        #jerry-notification-container .noti-title strong {
            color: #333;
        }

        #jerry-notification-container .noti-time {
            font-size: 0.85em;
            color: #999;
            margin-left: 8px;
        }

        #jerry-notification-container .noti-preview {
            font-size: 0.85em;
            color: #777;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            max-width: 240px;
        }

        /* ส่วนเนื้อหาที่จะยืดหด (Expandable) */
        #jerry-notification-container .noti-details {
            max-height: 0;
            overflow: hidden;
            transition: max-height 0.3s ease-out, padding 0.3s;
            background: #fcfcfc;
            font-size: 0.85em;
            color: #444;
            padding: 0 15px 0 50px; /* เว้นล่วงหน้าให้ตรงกับเนื้อหา */
        }

        #jerry-notification-container .noti-item.active {
            background-color: #edf2f7;
        }

        #jerry-notification-container .noti-item.active .noti-details {
            max-height: 200px; /* ปรับขนาดความยาวเนื้อหาที่ต้องการโชว์ */
            padding: 5px 15px 15px 50px;
            border-top: 1px dashed #ddd;
        }

        #jerry-notification-container .noti-footer {
            padding: 10px 15px;
            text-align: right;
            background: #ffffff;
            border-top: 1px solid #eee;
        }

        #jerry-notification-container .clear-all {
            color: #557a95;
            text-decoration: none;
            font-size: 0.85em;
        }

        #jerry-notification-container .clear-all:hover {
            text-decoration: underline;
        }

        /* Scrollbar สไตล์ WPF/Modern */
        #jerry-notification-container .noti-list::-webkit-scrollbar {
            width: 6px;
        }
        #jerry-notification-container .noti-list::-webkit-scrollbar-thumb {
            background: #ccc;
            border-radius: 10px;
        }

/* สถานะ "ยังไม่อ่าน" (Unread) */
    #jerry-notification-container .noti-item.unread {
        background-color: #f4f8fb; /* สีฟ้าอ่อนสะดุดตา */
        border-left: 3px solid #ff8c00; /* แถบสีส้มด้านข้างแบบ WPF */
    }

    /* จุด Indicator สำหรับรายการที่ยังไม่อ่าน */
    #jerry-notification-container .unread-dot {
        width: 8px;
        height: 8px;
        background-color: #ff8c00;
        border-radius: 50%;
        display: inline-block;
        margin-right: 5px;
    }

    /* สถานะ "อ่านแล้ว" (Read) */
    #jerry-notification-container .noti-item.read {
        background-color: #ffffff;
        border-left: 3px solid transparent;
    }

    #jerry-notification-container .noti-item.read .unread-dot {
        display: none; /* อ่านแล้วจุดหายไป */
    }

    /* ตัวหนังสือชื่อคนส่ง ถ้ายังไม่อ่านให้หนาขึ้น */
    #jerry-notification-container .noti-item.unread .noti-title strong {
        color: #000;
        font-weight: 800;
    }
    </style>
</head>
<body>

<div id="savemark"></div>

<div id="jerry-notification-container">
    <div class="noti-header">
        <span class="noti-icon">🔔</span> Notifications
    </div>

    <div class="noti-list" id="notiList">
      
      <c:forEach items="${notifications}" var="row">
        <div class="noti-item <c:if  test='${row.notification_read == 0}'>unread</c:if> <c:if test='${row.notification_read == 1}'>read</c:if> "
         onclick="markAsRead(this);toggleNoti(this);getData('markAsRead-filemanager/${row.notification_id}','savemark')">
            <div class="noti-main-row">
         <div class="noti-icon">💬</div>
                <div class="noti-content">
                    <div class="noti-title"><strong>${row.notification_type }</strong> <span class="noti-time">${row.notification_date }</span></div>
                    <div class="noti-preview">RE: ${row.notification_alert }...</div>
                </div>
            </div>
            <div class="noti-details">
               ${row.notification_note }
            </div>
        </div>
   </c:forEach>
      
   

     
    </div>

    <div class="noti-footer">
        <a href="#" class="clear-all">Clear all</a>
    </div>
</div>

<script>
    function toggleNoti(element) {
        // ปิดอันอื่นก่อนถ้าต้องการ (Accordion style) - ถ้าไม่ต้องการปิดอันเดิมให้คอมเมนท์ออก
        // const items = document.querySelectorAll('.noti-item');
        // items.forEach(item => { if(item !== element) item.classList.remove('active'); });

        // เปิด/ปิด อันที่กด
        element.classList.toggle('active');
    }

// ฟังก์ชันเปลี่ยนสถานะเป็นอ่านแล้วเมื่อคลิก
    function markAsRead(element) {
        if (element.classList.contains('unread')) {
            element.classList.remove('unread');
            element.classList.add('read');
        }
    }
</script>

</body>
</html>        