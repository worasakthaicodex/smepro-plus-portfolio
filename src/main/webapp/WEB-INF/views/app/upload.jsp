<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<style>
           :root {
            --ms-blue: #0078d4;
            --ms-blue-hover: #005a9e;
            --bg-gray: #f3f2f1;
            --border-color: #d2d0ce;
            --text-main: #323130;
            --text-secondary: #605e5c;
            --font-family: 'Segoe UI', system-ui, sans-serif;
        }


  

        /* Container หลัก ขนาดเดียวกับหน้าจอ Alert */
        .upload-panel {
            width: 100%;
            min-height:500px;
            background: white;
            display: flex;
            flex-direction: column;
            position: relative;
            margin-top:10px;
        }

        /* แถบด้านบน (Black Bar เหมือนเดิม) */
        .top-nav {
            background: #252423;
            height: 48px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding: 0 15px;
            gap: 20px;
            color: white;
        }

   
        /* ส่วนเนื้อหา */
        .content { padding: 0 24px; display: flex; flex-direction: column; flex-grow: 1; overflow: hidden; }

        /* พื้นที่ลากและวางไฟล์ (Drop Zone) */
        .upload-area {
            border: 2px dashed var(--border-color);
            border-radius: 4px;
            padding: 30px 20px;
            text-align: center;
            background: var(--bg-gray);
            cursor: pointer;
            transition: all 0.2s ease;
            margin-bottom: 20px;
        }

        .upload-area:hover, .upload-area.drag-over {
            border-color: var(--ms-blue);
            background: #ebf3fc;
        }

        .upload-icon { font-size: 40px; color: var(--ms-blue); margin-bottom: 10px; }
        .upload-text { font-size: 14px; color: var(--text-main); font-weight: 600; }
        .upload-subtext { font-size: 12px; color: var(--text-secondary); margin-top: 5px; }

        /* รายการไฟล์ (Vertical File List) */
        .file-list-title {
            font-size: 14px;
            font-weight: 600;
            color: var(--text-main);
            margin-bottom: 10px;
            display: flex;
            justify-content: space-between;
        }

        .file-list {
            flex-grow: 1;
            overflow-y: auto;
            border: 1px solid var(--bg-gray);
            border-radius: 4px;
            margin-bottom: 20px;
        }

        .file-item {
            display: flex;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #eee;
            animation: slideIn 0.3s ease-out;
        }

        @keyframes slideIn {
            from { opacity: 0; transform: translateY(10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .file-icon { font-size: 24px; margin-right: 12px; }
        
        .file-info { flex-grow: 1; min-width: 0; }
        .file-name {
            font-size: 13px;
            font-weight: 600;
            color: var(--text-main);
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .file-size { font-size: 11px; color: var(--text-secondary); }

        .remove-btn {
            color: var(--text-secondary);
            cursor: pointer;
            padding: 5px;
            font-size: 14px;
        }
        .remove-btn:hover { color: #d13438; }

        /* ปุ่มบันทึกด้านล่าง */
        .footer-actions {
            padding: 20px 0;
            border-top: 1px solid var(--border-color);
            display: flex;
            gap: 10px;
        }

     
        #file-input { display: none; }
</style>

<%@ include file="index_upload.jspf" %> 
 