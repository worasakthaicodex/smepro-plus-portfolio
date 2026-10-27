<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  


<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enterprise ERP v2026 - Master Full GUI</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        /* =========================================
           [1] CORE LAYOUT & VARIABLES
           ========================================= */
        :root {
            --wpf-purple: #4c4391; 
            --wpf-bg: #f0f0f0; 
            --wpf-border: #999999;
            --wpf-selected: #0078d7; 
            --dim-opacity: 0.5;
        }
 
         #wpf-app-window body,#wpf-app-window html { margin: 0; padding: 0; height: 100vh; background-color: #333; overflow: hidden; display: flex; 
         justify-content: center; align-items: center; }
        
        #wpf-app-window {
         box-sizing: border-box; font-family: 'Segoe UI', 'Sarabun', sans-serif;
            width: 100%; height: 92%; background: var(--wpf-bg);
           display: flex; flex-direction: column; position: relative; 
            box-shadow: 0 15px 50px rgba(0,0,0,0.6);
        }

        /* Title Bar */
        .title-bar { height: 32px; background: var(--wpf-purple); color: white; display: flex; justify-content: space-between; align-items: center; padding: 0 12px; font-size: 12px;
         z-index: 1; }
        .sys-buttons i { margin-left: 15px; cursor: pointer; opacity: 0.8; font-size: 14px; }

       /* =========================================
           [2] TOP SECTION & DIMMER
           ========================================= */
        #top-section-container { 
            display: flex; flex-direction: column; flex: 1; position: relative; 
            /* กลับมาใช้แบบเดิม เบสิคที่สุดครับ */
            min-height:30%;
        }
        
        /* เลเยอร์สีดำ */
        #top-dimmer {
            position: absolute; top: 0; left: 0; right: 0; bottom: 0; /* ขึงให้ตึงทั้ง 4 ด้าน */
            background: rgba(0,0,0, var(--dim-opacity));
            z-index: 1500; display: none; cursor: pointer;
            backdrop-filter: blur(1px);
        }

        /* =========================================
           [3] BOTTOM TRAY (FOCUS MODE)
           ========================================= */
        #bottom-tray { 
            height: 35px; /* ความสูงปกติเมื่อหุบ */
            flex-shrink: 0; 
            background: #fff; border-top: 1px solid var(--wpf-purple); 
            display: flex; flex-direction: column; position: relative; 
            z-index: 1000; 
            box-shadow: 0 -5px 25px rgba(0,0,0,0.3);
            /* แอนิเมชันยืดหดแบบสมูทๆ */
            transition: height 0.3s ease; 
        }

        #bottom-tray.expanded {
            /* ยืด 50% ของหน้าจอทันทีเมื่อคลิก */
            height: 50% !important; 
        }

        /* Tool Strip */
        .tool-strip { height: 60px; background: #fff; border-bottom: 1px solid var(--wpf-border); display: flex; align-items: center; 
        padding: 0 10px; gap: 2px; position: relative; z-index: 1; }
        .tool-btn { display: flex; flex-direction: column; align-items: center; padding: 6px 10px; cursor: pointer; min-width: 60px; border: 1px solid transparent; }
        .tool-btn:hover { background: #e5f3ff; border: 1px solid #99d1ff; }
        .tool-btn i { font-size: 18px; margin-bottom: 4px; color: #444; }
        .tool-btn span { font-size: 10px; color: #333; }
        .tool-sep { width: 1px; height: 35px; background: #ddd; margin: 0 8px; }

        /* Main Workspace Layout */
        .main-container { display: flex; flex: 1; overflow: hidden; }
        #wpf-app-window .tree-sidebar { width: 280px; background: #f5f5f5; border-right: 1px solid var(--wpf-border); display: flex; flex-direction: column; }
        .workspace { flex: 1; background: #fff; display: flex; flex-direction: column; overflow: hidden; }

        /* Sidebar Elements */
        .sidebar-tabs { display: flex; background: #e1e1e1; height: 30px; border-bottom: 1px solid #ccc; }
        .s-tab { flex: 1; display: flex; align-items: center; justify-content: center; font-size: 11px; cursor: pointer; background: #dcdcdc; border-right: 1px solid #ccc; color: #666; }
        .s-tab.active { background: #f5f5f5; font-weight: bold; color: var(--wpf-purple); border-bottom: 2px solid var(--wpf-purple); }
        .tree-item { padding: 8px 15px; font-size: 13px; cursor: pointer; display: flex; align-items: center; gap: 8px; color: #333; }
        .tree-item:hover { background: #e5f3ff; }
        .tree-item.active { background: #cce8ff; font-weight: bold; border-right: 3px solid var(--wpf-selected); }

        /* Stepper */
        .step-bar { background: #f8f9fa; padding: 10px; border-bottom: 1px solid #ddd; display: flex; justify-content: space-around; font-size: 11px; }
        .step { display: flex; align-items: center; gap: 8px; color: #999; }
        .step.done { color: #28a745; font-weight: bold; }
        .step.active { color: var(--wpf-purple); font-weight: bold; }

        /* Tabs */
        .main-tabs { display: flex; background: #eee; height: 32px; padding-left: 10px; border-bottom: 1px solid #ccc; }
        .m-tab { padding: 0 25px; display: flex; align-items: center; font-size: 12px; cursor: pointer; background: #ddd; border: 1px solid #ccc; border-bottom: none; margin-top: 5px; margin-right: 2px; border-radius: 4px 4px 0 0; }
        .m-tab.active { background: #fff; border-bottom: 2px solid #fff; font-weight: bold; margin-bottom: -1px; position: relative; z-index: 2; }

        /* Form Controls */
        .work-content { flex: 1; padding: 20px; overflow-y: auto; 
        min-height: 500px; /* ✨ พระเอกของเราอยู่ตรงนี้! เติมบรรทัดนี้เข้าไปครับ ✨ */
        max-height: 90%;
         }
        .group-box { border: 1px solid #ccc; padding: 15px; margin-bottom: 15px; position: relative; background: #fff; }
        .group-label { position: absolute; top: -10px; left: 10px; background: #fff; padding: 0 5px; font-size: 11px; font-weight: bold; color: var(--wpf-purple); }
        .f-row { display: grid; grid-template-columns: 140px 1fr; margin-bottom: 8px; align-items: center; gap: 10px; }
        .f-row label { font-size: 12px; color: #555; }
        .f-row input, .f-row select { padding: 5px; border: 1px solid #ccc; }
        
        .erp-grid { width: 100%; border-collapse: collapse; font-size: 12px; border: 1px solid #ccc; }
        .erp-grid th { background: #f0f0f0; padding: 8px; border: 1px solid #ccc; text-align: left; }
        .erp-grid td { padding: 8px; border: 1px solid #eee; }

        /* =========================================
           [3] BOTTOM TRAY (FOCUS MODE)
           ========================================= */
    
        .resizer { height: 6px; cursor: ns-resize; width: 100%; position: absolute; top: -3px; z-index: 10; }
        .tray-tabs { background: #eee; display: flex; height: 35px; border-bottom: 1px solid #ccc; flex-shrink: 0;  z-index: 15; }
        .t-tab { padding: 0 20px; font-size: 11px; display: flex; align-items: center; cursor: pointer; border-right: 1px solid #ccc; background: #ddd; color: #666; }
        .t-tab.active { background: #fff; border-top: 3px solid var(--wpf-purple); font-weight: bold; color: #000; }

        .tab-panel { flex: 1; display: none; padding: 15px; overflow: hidden; flex-direction: column;z-index:15; }
        .tab-panel.active { display: flex; }

        /* Smart Form */
        .bottom-smart-form { 
            display: grid; grid-template-columns: 320px 1fr; gap: 20px; 
            background: #fdfdfd; padding: 15px; border: 1px solid #ddd; margin-bottom: 10px;
        }
        .b-field { display: flex; flex-direction: column; gap: 4px; margin-bottom: 8px; }
        .b-field label { font-size: 10px; font-weight: bold; color: #777; }
        .b-field input, .b-field select { padding: 5px; border: 1px solid #ccc; font-size: 12px; }
        .b-field textarea { height: 110px; padding: 8px; border: 1px solid var(--wpf-purple); font-size: 13px; resize: none; }
        .btn-save-tray { align-self: flex-end; padding: 8px 30px; background: var(--wpf-purple); color: white; border: none; cursor: pointer; font-weight: bold; border-radius: 2px; }


  /* [3] MENU & RIBBON BAR */
        .wpf-menu-bar { height: 25px; background: #fff; display: flex; align-items: center; padding: 0 10px; font-size: 11px; gap: 15px; }
        .wpf-menu-item { cursor: pointer; padding: 2px 5px; }
        .wpf-menu-item:hover { background: #ddd; }









/* 1. บีบความสูงของหัวตาราง (Header) */
.erp-grid th {
    background-color: #f5f6f8;
    border: 1px solid #e5e5e5;
    border-bottom: 2px solid #cccccc;
    padding: 4px 6px; /* ลด Padding ลงจาก 8px เหลือ 4px */
    color: #555;
    font-weight: bold;
    text-align: center;
    font-size: 11px; /* ปรับฟอนต์ให้กะทัดรัดขึ้น */
}

/* 2. บีบความสูงของแถวข้อมูล (Row Height) */
.erp-grid td {
    border: 1px solid #e5e5e5;
    padding: 0 !important;
    height: 26px; /* ลดความสูงเซลล์ลงให้เท่ากับ Input ด้านบน (ปกติประมาณ 24-26px) */
    vertical-align: middle;
}

/* 3. บีบ Input และ Select ให้พอดีกับเซลล์ */
.erp-grid input, 
.erp-grid select {
    width: 98%;
    height: 30px; /* บังคับความสูง Input ให้พอดีเป๊ะ */
    min-height: 24px;
    border: none !important;
    padding: 0 6px; /* ลดช่องว่างซ้ายขวาลงนิดนึง */
    background-color: transparent;
    outline: none;
    font-size: 11px; /* ขนาดฟอนต์ 11px จะดูโปรและเข้ากับ ERP มากกว่า 12px */
    color: #222;
    line-height: 1; /* ป้องกันไม่ให้ตัวหนังสือดันกล่องให้สูงขึ้น */
}

/* ปรับระยะลูกศรของ Dropdown ให้พอดีกับความสูงใหม่ */
.erp-grid select {
    background-position: right 6px center;
    padding-right: 20px;
}

/* 4. บีบส่วนสรุปยอด (Summary) ให้เพรียวลงด้วย */
.tif-footer-panel {
    display: flex;
    justify-content: flex-end;
    background: #fafbfc;
    border: 1px solid #dcdcdc;
    border-top: none;
    padding: 10px 15px; /* ลดพื้นที่ว่างรอบกล่องสรุปยอด */
}

.tif-summary-table td {
    padding: 4px; /* ลดความห่างระหว่างบรรทัด */
    background: transparent !important;
    border: none !important;
    border-bottom: 1px dotted #ccc !important;
}

.tif-summary-table input {
    width: 100%;
    height: 24px; /* ลดความสูง Input สรุปยอด */
    border: 1px solid transparent;
    border-radius: 2px;
    outline: none;
    text-align: right;
    padding: 0 5px;
    font-size: 11px;
    font-weight: bold;
    background: transparent;
    color: #222;
    transition: 0.2s;
}

/* แถวยอดสุทธิ */
.tif-summary-table tr:last-child td {
    border-bottom: none !important;
    border-top: 2px solid #0A6ED1 !important;
    padding-top: 6px; /* ลดช่องว่างด้านบนของยอดสุทธิ */
}
.tif-summary-table tr:last-child input {
    color: #0A6ED1;
    font-size: 13px; /* ลดจาก 15px ลงมานิดนึงไม่ให้โดดเกินไป */
}


/* ========================================================
   TOM SELECT - EXCEL PERFECT FIT (แก้ปัญหาช่องลอย ไม่เต็มแถว)
   ======================================================== */

/* 1. ต้องสั่งให้ td เป็น relative ก่อน เพื่อให้กล่องด้านในอิงขนาดตาม td */
.erp-grid td {
    position: relative !important; 
}

/* 2. บังคับให้ Wrapper ขึงตึงเต็ม 100% ของช่อง td (บน ซ้าย ล่าง ขวา) */
.erp-grid .ts-wrapper {
    position: absolute !important; 
    top: 0 !important;
    left: 0 !important;
    right: 0 !important;
    bottom: 0 !important;
    padding: 0 !important;
    margin: 0 !important;
    height: 100% !important;
    width: 100% !important;
}

/* 3. บังคับกล่องข้อความด้านในให้เต็ม 100% และจัดกึ่งกลางแนวตั้ง */
.erp-grid .ts-control {
    position: absolute !important;
    top: 0 !important;
    left: 0 !important;
    right: 0 !important;
    bottom: 0 !important;
    height: 100% !important;
    min-height: 100% !important;
    border: none !important;
    border-radius: 0 !important;
    background: transparent !important;
    padding: 0 25px 0 8px !important;
    margin: 0 !important;
    display: flex !important;
    align-items: center !important;
    box-shadow: none !important; /* ล้างเงาสีฟ้าตั้งต้นของ Tom Select ทิ้ง */
}

/* 4. จัดการช่องพิมพ์ให้พอดี ไม่ดันตาราง */
.erp-grid .ts-control > input {
    height: 100% !important;
    margin: 0 !important;
    padding: 0 !important;
    line-height: normal !important;
}

/* 5. ตอนคลิกเลือก (Focus) ให้ขึ้นขอบสีน้ำเงินชิดเส้นตารางเป๊ะๆ */
.erp-grid .ts-wrapper.focus .ts-control,
.erp-grid .ts-wrapper.input-active .ts-control {
    background-color: #fff !important;
    /* 💡 ถ้าไม่อยากได้เส้นสีน้ำเงินเลย ให้ลบบรรทัด box-shadow ด้านล่างนี้ทิ้งครับ 💡 */

    z-index: 10 !important; /* ยกให้ลอยขึ้นมาบังเส้นตารางขอบๆ */
}

/* 6. จัดตำแหน่งลูกศรชี้ลง */
.erp-grid .ts-wrapper.single .ts-control::after {
    border-color: #888 transparent transparent transparent !important;
    right: 8px !important;
}



/* ใส่ในไฟล์ CSS ของคุณ */
.loading-overlay {
    /* หัวใจสำคัญ: ใช้ absolute เพื่อให้อยู่แค่ในกรอบแม่ */
    position: absolute; 
    top: 0; left: 0; right: 0; bottom: 0;
    width: 100%; height: 100%;
    background: rgba(255, 255, 255, 0.85);
    display: flex; flex-direction: column; justify-content: center; align-items: center;
    z-index: 1000; /* อยู่บนสุดของกล่องแม่ */
    backdrop-filter: blur(3px);
}

.loader-spinner {
    width: 40px; height: 40px;
    border: 4px solid #f3f3f3; border-top: 4px solid #3498db;
    border-radius: 50%; animation: spin-loader 1s linear infinite;
}

.loading-text {
    margin-top: 10px; font-size: 14px; color: #333; font-weight: bold;
}

@keyframes spin-loader {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}
    </style>
    
    
    
    
    
    
    
    
    
    
    
    
        <style>
/* สร้างคลาสสำหรับหมุน 1 รอบ (ใช้เวลา 1 วินาที) */
.spin-once {
    animation: spin-once-anim 1s linear;
}

@keyframes spin-once-anim {
    from { transform: rotate(0deg); }
    to { transform: rotate(360deg); }
}







</style>
<style>
@keyframes big-red-glow {
    0%   { 
        box-shadow: 0 0 0 0 rgba(255, 0, 0, 0.8); 
        background-color: transparent; 
    }
    50%  { 
        /* สร้างวงแสงสีแดงกว้างๆ แผ่ออกไปรอบปุ่ม */
        box-shadow: 0 0 20px 10px rgba(255, 0, 0, 0.5); 
        background-color: rgba(255, 0, 0, 0.15); /* พื้นหลังอมแดงนิดๆ */
        color: #ff0000 !important; /* บังคับเปลี่ยนสีตัวหนังสือเป็นแดง */
        transform: scale(1.05); /* ขยายปุ่มให้เด้งขึ้นมา 5% */
        border-radius: 8px; /* ลบมุมให้ขอบแสงดูมนๆ สวยงาม */
    }
    100% { 
        box-shadow: 0 0 0 0 rgba(255, 0, 0, 0); 
        background-color: transparent; 
    }
}

.alert-big-glow {
    animation: big-red-glow 1.2s infinite ease-in-out;
}




/* =========================================
   [DARK MODE STYLES - NUCLEAR OPTION]
   บังคับมืดทุกตารางนิ้ว
   ========================================= */

/* 1. กำหนดตัวแปรสีหลัก */
#wpf-app-window.dark-mode, 
#wpf-app-frame.dark-mode {
    --wpf-light-bg: #1e1e1e;
    --wpf-panel-bg: #2d2d30;
    --wpf-border: #444444;
    --wpf-header-grad: linear-gradient(to bottom, #3e3e42, #2d2d30);
    --wpf-selected: #0078d7; /* สีฟ้าให้สว่างขึ้นเวลา Active */
    --wpf-hover: #3e3e42;
    --text-main: #e0e0e0;
    --text-muted: #aaaaaa;
}

/* 2. บังคับพื้นหลังกล่องหลักทั้งหมดให้เป็นโหมดมืด */
#wpf-app-window.dark-mode,
#wpf-app-window.dark-mode #top-section-container,
#wpf-app-window.dark-mode .tool-strip,
#wpf-app-window.dark-mode .menus,
#wpf-app-window.dark-mode .wpf-menu-bar,
#wpf-app-window.dark-mode .menu,
#wpf-app-window.dark-mode .main-container,
#wpf-app-window.dark-mode .workspace,
#wpf-app-window.dark-mode .work-content,
#wpf-app-window.dark-mode .group-box,

#wpf-app-window.dark-mode .tif-pane-bottom,
#wpf-app-window.dark-mode .tif-toolbar,
#wpf-app-window.dark-mode .tif-footer-panel,
#wpf-app-window.dark-mode .tif-summary-table,
#wpf-app-window.dark-mode .tree-sidebar,
#wpf-app-window.dark-mode .sidebar-content,
#wpf-app-window.dark-mode .main-tabs{
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
}

#wpf-app-window.dark-mode .step-bar{
  background-color: #252526 !important;
    border-color: var(--wpf-border) !important;
}



/* 3. 🎯 กู้ชีพตัวหนังสือที่จมหายไปให้สว่างขึ้น 🎯 */
#wpf-app-window.dark-mode .menu label,
#wpf-app-window.dark-mode .menu span,
#wpf-app-window.dark-mode .tool-btn,
#wpf-app-window.dark-mode .tool-btn span,
#wpf-app-window.dark-mode label,
#wpf-app-window.dark-mode .group-label,
#wpf-app-window.dark-mode .f-row label,
#wpf-app-window.dark-mode .tif-summary-table td,
#wpf-app-window.dark-mode .tif-summary-table b,
#wpf-app-window.dark-mode .tree li,
#wpf-app-window.dark-mode .step {
    color: var(--text-main) !important;
    background-color: transparent !important;
}

/* ไอคอนในปุ่ม (ที่ไม่มีสีระบุไว้) ให้เปลี่ยนเป็นสีขาวสว่าง */
#wpf-app-window.dark-mode .tool-btn i:not([style*="color"]) {
    color: var(--text-main) !important;
}

/* 4. จัดการลูกเล่นปุ่ม Hover และ Tabs */
#wpf-app-window.dark-mode .tool-btn:hover { background-color: var(--wpf-hover) !important; border-radius: 4px; }
#wpf-app-window.dark-mode .s-tab,
#wpf-app-window.dark-mode .m-tab,
#wpf-app-window.dark-mode .t-tab {
    background-color: #1e1e1e !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-muted) !important;
}
#wpf-app-window.dark-mode .s-tab:hover,
#wpf-app-window.dark-mode .m-tab:hover,
#wpf-app-window.dark-mode .t-tab:hover { background-color: var(--wpf-hover) !important; }
#wpf-app-window.dark-mode .s-tab.active,
#wpf-app-window.dark-mode .m-tab.active,
#wpf-app-window.dark-mode .t-tab.active {
    background-color: var(--wpf-selected) !important;
    color: #ffffff !important;
    border-color: var(--wpf-selected) !important;
}

/* 5. จัดการช่อง Input และ Dropdown ทั้งหมด */
#wpf-app-window.dark-mode input[type="text"],
#wpf-app-window.dark-mode input[type="number"],
#wpf-app-window.dark-mode select,
#wpf-app-window.dark-mode .tif-inp-num,
#wpf-app-window.dark-mode .tif-inp-text,
#wpf-app-window.dark-mode .wpf-select {
    background-color: #1e1e1e !important;
    color: #ffffff !important;
    border: 1px solid var(--wpf-border) !important;
}
#wpf-app-window.dark-mode input:focus,
#wpf-app-window.dark-mode select:focus { border-color: var(--wpf-selected) !important; }

/* ปุ่ม + Add Row และปุ่มอื่นๆ ในตารางย่อย */
#wpf-app-window.dark-mode .tif-toolbar button {
    background-color: #444 !important;
    color: #fff !important;
    border: 1px solid #666 !important;
}
#wpf-app-window.dark-mode .tif-toolbar button:hover { background-color: #555 !important; }

/* 6. จัดการ TomSelect (กล่องค้นหาที่มีรูปแว่นขยาย) */
#wpf-app-window.dark-mode .ts-control {
    background-color: #1e1e1e !important;
    border-color: var(--wpf-border) !important;
    color: #ffffff !important;
}
#wpf-app-window.dark-mode .ts-control input { color: #ffffff !important; }
#wpf-app-window.dark-mode .ts-dropdown,
#wpf-app-window.dark-mode .ts-dropdown .ts-dropdown-content {
    background-color: #2d2d30 !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
}
#wpf-app-window.dark-mode .ts-dropdown .option:hover,
#wpf-app-window.dark-mode .ts-dropdown .option.active {
    background-color: var(--wpf-hover) !important;
    color: #ffffff !important;
}

/* 7. จัดการตาราง (ERP Grid) */
#wpf-app-window.dark-mode .erp-grid th,
#wpf-app-window.dark-mode .erp-grid thead tr {
    background: var(--wpf-header-grad) !important;
    color: #ffffff !important;
    border-color: var(--wpf-border) !important;
}
#wpf-app-window.dark-mode .erp-grid td {
    border-color: var(--wpf-border) !important;
    background-color: transparent !important;
    color: var(--text-main) !important;
}
#wpf-app-window.dark-mode .erp-grid tbody tr { background-color: var(--wpf-panel-bg) !important; }
#wpf-app-window.dark-mode .erp-grid tbody tr:nth-child(even) { background-color: #252526 !important; }
#wpf-app-window.dark-mode .erp-grid tbody tr:hover { background-color: var(--wpf-hover) !important; }

/* 8. จัดการ Tree View (โฟลเดอร์ฝั่งซ้าย) */
#wpf-app-window.dark-mode .tree folder.selected {
    background-image: linear-gradient(to bottom, #005a9e, #004578) !important;
    color: #fff !important;
}
#wpf-app-window.dark-mode .tree folder:hover { background-image: linear-gradient(to bottom, #3e3e42, #2d2d30) !important; }
#wpf-app-window.dark-mode .tree:before, 
#wpf-app-window.dark-mode .tree ul:before,
#wpf-app-window.dark-mode .tree li:before {
 border-color: #666 !important; 
 background-color: var(--wpf-panel-bg) !important;
 }

/* 9. ซ่อน Dropdown Menu (พวก Submenu เวลากด) */
#wpf-app-window.dark-mode .submenu, 
#wpf-app-window.dark-mode .submenu-item {
    background-color: var(--wpf-panel-bg) !important;
    color: var(--text-main) !important;
    border-color: var(--wpf-border) !important;
}
#wpf-app-window.dark-mode .submenu-item:hover { background-color: var(--wpf-hover) !important; }

/* 10. แถบสถานะด้านบน (1. สร้าง | 2. ติดตาม) */
#wpf-app-window.dark-mode .step.active { color: #4da6ff !important; font-weight: bold; }
      
/* =========================================
   [DARK MODE - เก็บตกเมนูซ้าย & ขอบล่างขาว]
   ========================================= */

/* 1. กู้ชีพเมนูด้านซ้าย (ERP Modules) */
#wpf-app-window.dark-mode .tree-item {
    color: var(--text-main) !important;
}
#wpf-app-window.dark-mode .tree-item:hover {
    background-color: var(--wpf-hover) !important;
    color: #ffffff !important;
}
#wpf-app-window.dark-mode .tree-item.active {
    background-color: var(--wpf-selected) !important; /* เปลี่ยนแถบฟ้าอ่อน เป็นน้ำเงินเข้ม */
    color: #ffffff !important;
    border-left: 3px solid #66b2ff !important; /* เพิ่มเส้นขอบซ้ายให้ดูเท่ๆ */
}

/* 2. ถมดำแถบขาวด้านล่างสุด (Bottom Tray & Panel) */
#wpf-app-window.dark-mode #bottom-tray,
#wpf-app-window.dark-mode .tray-tabs {
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
}
/* =========================================
   [DARK MODE - SHIELD: ปกป้อง .tab-panel]
   คืนค่าทุกอย่างในนี้ให้เป็นปกติ ห้ามมืด!
   ========================================= */

/* 1. คืนพื้นหลังขาว และสีตัวหนังสือเดิมให้ Panel */
#wpf-app-window.dark-mode .tab-panel {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: none !important;
}

/* 2. ป้องกัน Input, Select, Textarea ข้างในไม่ให้โดนถมดำ */
#wpf-app-window.dark-mode .tab-panel input,
#wpf-app-window.dark-mode .tab-panel select,
#wpf-app-window.dark-mode .tab-panel textarea,
#wpf-app-window.dark-mode .tab-panel .form-control {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: 1px solid #cccccc !important;
}

/* 3. ป้องกันตารางและตัวหนังสือข้างใน */
#wpf-app-window.dark-mode .tab-panel table,
#wpf-app-window.dark-mode .tab-panel th,
#wpf-app-window.dark-mode .tab-panel td,
#wpf-app-window.dark-mode .tab-panel label,
#wpf-app-window.dark-mode .tab-panel span {
    background-color: transparent !important;
    color: inherit !important; /* ดูดสีตาม CSS ดั้งเดิมของมันมาใช้ */
    border-color: inherit !important;
}



     
      /* =========================================
   [DARK MODE - อุดรูรั่ว & ปรับความสวยงามตาราง]
   ========================================= */

/* 1. อุดรูโหว่ด้านล่างขวาให้ดำมิด */
#wpf-app-window.dark-mode #bottom-tray {
    background-color: #1e1e1e !important;
}
#wpf-app-window.dark-mode .tray-tabs {
    background-color: #1e1e1e !important;
    width: 100% !important; /* ยืดพื้นหลังให้เต็มจอขวา */
    border-bottom: 1px solid var(--wpf-border) !important;
    display: flex;
}

/* 2. จัดระเบียบตาราง (ลดความลายตา) */
#wpf-app-window.dark-mode .erp-grid {
    table-layout: fixed; /* บังคับคอลัมน์ไม่ให้ยืดมั่วซั่ว */
}
#wpf-app-window.dark-mode .erp-grid th,
#wpf-app-window.dark-mode .erp-grid td {
    border: 1px solid #38383a !important; /* ดรอปสีเส้นตารางให้อ่อนลง จะได้ไม่ดูลายตา */
    padding: 6px 8px !important;
}

/* 3. เอาเส้นขอบ Input ในตารางออก (สำคัญมาก! ช่วยให้ตารางไม่ดูรก) */
#wpf-app-window.dark-mode .erp-grid input,
#wpf-app-window.dark-mode .erp-grid select,
#wpf-app-window.dark-mode .erp-grid .ts-control {
    border: none !important;
    background-color: transparent !important;
    box-shadow: none !important;
}
/* แต่พอกดคลิก (Focus) ค่อยให้กรอบโผล่มา */
#wpf-app-window.dark-mode .erp-grid input:focus,
#wpf-app-window.dark-mode .erp-grid select:focus,
#wpf-app-window.dark-mode .erp-grid .ts-control.focus {
    background-color: #333333 !important;
    border: 1px solid var(--wpf-selected) !important;
    border-radius: 4px;
}

/* 4. จัดกล่องสรุปยอด (มุมขวาล่าง) ให้ดูเนียนขึ้น */
#wpf-app-window.dark-mode .tif-footer-panel {
    background-color: #252526 !important;
    padding: 10px !important;
    border-radius: 4px;
   
    border: 1px solid var(--wpf-border) !important;
}
#wpf-app-window.dark-mode .tif-summary-table td {
    border: none !important; /* เอาเส้นตารางสรุปยอดออก */
}
/* เปลี่ยนช่อง Input สรุปยอดให้มีแค่เส้นใต้ (สไตล์ Modern) */
#wpf-app-window.dark-mode .tif-summary-table input {
    border-bottom: 1px solid #555 !important;
    border-top: none !important;
    border-left: none !important;
    border-right: none !important;
    border-radius: 0 !important;
    background-color: transparent !important;
    text-align: right;
}
  
  
/* =========================================
   [DARK MODE - เก็บตก Textarea และ Checkbox]
   ========================================= */

/* 1. จัดการ Textarea และคลาส .form-control ให้ดำเนียนๆ */
#wpf-app-window.dark-mode textarea,
#wpf-app-window.dark-mode .form-control {
    background-color: #1e1e1e !important;
    color: #ffffff !important;
    border: 1px solid var(--wpf-border) !important;
}

#wpf-app-window.dark-mode textarea:focus,
#wpf-app-window.dark-mode .form-control:focus {
    border-color: var(--wpf-selected) !important;
    outline: none !important;
}

/* 2. จัดการข้อความที่อยู่ข้างๆ Checkbox / Radio ให้เป็นสีสว่าง */
#wpf-app-window.dark-mode .f-row div {
    color: var(--text-main) !important;
}

/* 3. ลูกเล่นเสริม: เปลี่ยนสีติ๊กถูกของ Checkbox ให้เป็นสีของตีม (เช่น สีฟ้า/น้ำเงิน) */
#wpf-app-window.dark-mode input[type="checkbox"],
#wpf-app-window.dark-mode input[type="radio"] {
    accent-color: var(--wpf-selected) !important; 
    cursor: pointer;
}  

        
        
        
        
        
        /* =========================================
   [BROWN THEME STYLES - NUCLEAR OPTION]
   โทนน้ำตาล Sepia สบายตา อ้างอิงจาก VS Code 
   ========================================= */

/* 1. กำหนดตัวแปรสีหลัก (โทน Wood & Coffee) */
#wpf-app-window.brown-theme, 
#wpf-app-frame.brown-theme {
    --wpf-light-bg: #231f1c;      /* พื้นหลังหลัก น้ำตาลเข้มกาแฟ */
    --wpf-panel-bg: #2d2824;      /* พื้นหลังพาเนล น้ำตาลไม้ */
    --wpf-border: #453c37;        /* สีเส้นขอบ */
    --wpf-header-grad: linear-gradient(to bottom, #453c37, #2d2824); /* หัวตาราง */
    --wpf-selected: #8b5a2b;      /* สีน้ำตาลทองเวลาเลือก */
    --wpf-hover: #453c37;         /* สีเวลา Hover */
    --text-main: #e8dcc4;         /* ตัวหนังสือสีครีม Sepia */
    --text-muted: #a89a8e;        /* ตัวหนังสือสีเทาน้ำตาลจาง */
}

/* 2. บังคับพื้นหลังกล่องหลักทั้งหมดให้เป็นโหมดน้ำตาล */
#wpf-app-window.brown-theme,
#wpf-app-window.brown-theme #top-section-container,
#wpf-app-window.brown-theme .tool-strip,
#wpf-app-window.brown-theme .menus,
#wpf-app-window.brown-theme .wpf-menu-bar,
#wpf-app-window.brown-theme .menu,
#wpf-app-window.brown-theme .main-container,
#wpf-app-window.brown-theme .workspace,
#wpf-app-window.brown-theme .work-content,
#wpf-app-window.brown-theme .group-box,
#wpf-app-window.brown-theme .tif-pane-bottom,
#wpf-app-window.brown-theme .tif-toolbar,
#wpf-app-window.brown-theme .tif-footer-panel,
#wpf-app-window.brown-theme .tif-summary-table,
#wpf-app-window.brown-theme .tree-sidebar,
#wpf-app-window.brown-theme .sidebar-content,
#wpf-app-window.brown-theme .main-tabs {
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
}

#wpf-app-window.brown-theme .step-bar {
    background-color: #231f1c !important;
    border-color: var(--wpf-border) !important;
}

/* 3. 🎯 กู้ชีพตัวหนังสือที่จมหายไปให้สว่างขึ้น 🎯 */
#wpf-app-window.brown-theme .menu label,
#wpf-app-window.brown-theme .menu span,
#wpf-app-window.brown-theme .tool-btn,
#wpf-app-window.brown-theme .tool-btn span,
#wpf-app-window.brown-theme label,
#wpf-app-window.brown-theme .group-label,
#wpf-app-window.brown-theme .f-row label,
#wpf-app-window.brown-theme .tif-summary-table td,
#wpf-app-window.brown-theme .tif-summary-table b,
#wpf-app-window.brown-theme .tree li,
#wpf-app-window.brown-theme .step {
    color: var(--text-main) !important;
    background-color: transparent !important;
}

/* ไอคอนในปุ่ม (ที่ไม่มีสีระบุไว้) ให้เปลี่ยนเป็นสีครีมสว่าง */
#wpf-app-window.brown-theme .tool-btn i:not([style*="color"]) {
    color: var(--text-main) !important;
}

/* 4. จัดการลูกเล่นปุ่ม Hover และ Tabs */
#wpf-app-window.brown-theme .tool-btn:hover { background-color: var(--wpf-hover) !important; border-radius: 4px; }
#wpf-app-window.brown-theme .s-tab,
#wpf-app-window.brown-theme .m-tab,
#wpf-app-window.brown-theme .t-tab {
    background-color: #1e1a17 !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-muted) !important;
}
#wpf-app-window.brown-theme .s-tab:hover,
#wpf-app-window.brown-theme .m-tab:hover,
#wpf-app-window.brown-theme .t-tab:hover { background-color: var(--wpf-hover) !important; }
#wpf-app-window.brown-theme .s-tab.active,
#wpf-app-window.brown-theme .m-tab.active,
#wpf-app-window.brown-theme .t-tab.active {
    background-color: var(--wpf-selected) !important;
    color: #ffffff !important;
    border-color: var(--wpf-selected) !important;
}

/* 5. จัดการช่อง Input และ Dropdown ทั้งหมด */
#wpf-app-window.brown-theme input[type="text"],
#wpf-app-window.brown-theme input[type="number"],
#wpf-app-window.brown-theme select,
#wpf-app-window.brown-theme .tif-inp-num,
#wpf-app-window.brown-theme .tif-inp-text,
#wpf-app-window.brown-theme .wpf-select {
    background-color: #1e1a17 !important;
    color: #f5ece0 !important;
    border: 1px solid var(--wpf-border) !important;
}
#wpf-app-window.brown-theme input:focus,
#wpf-app-window.brown-theme select:focus { border-color: var(--wpf-selected) !important; }

/* ปุ่ม + Add Row และปุ่มอื่นๆ ในตารางย่อย */
#wpf-app-window.brown-theme .tif-toolbar button {
    background-color: #4d433d !important;
    color: #f5ece0 !important;
    border: 1px solid #5d534d !important;
}
#wpf-app-window.brown-theme .tif-toolbar button:hover { background-color: #5d534d !important; }

/* 6. จัดการ TomSelect (กล่องค้นหาที่มีรูปแว่นขยาย) */
#wpf-app-window.brown-theme .ts-control {
    background-color: #1e1a17 !important;
    border-color: var(--wpf-border) !important;
    color: #f5ece0 !important;
}
#wpf-app-window.brown-theme .ts-control input { color: #f5ece0 !important; }
#wpf-app-window.brown-theme .ts-dropdown,
#wpf-app-window.brown-theme .ts-dropdown .ts-dropdown-content {
    background-color: #362f2b !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
}
#wpf-app-window.brown-theme .ts-dropdown .option:hover,
#wpf-app-window.brown-theme .ts-dropdown .option.active {
    background-color: var(--wpf-hover) !important;
    color: #ffffff !important;
}

/* 7. จัดการตาราง (ERP Grid) */
#wpf-app-window.brown-theme .erp-grid th,
#wpf-app-window.brown-theme .erp-grid thead tr {
    background: var(--wpf-header-grad) !important;
    color: #f5ece0 !important;
    border-color: var(--wpf-border) !important;
}
#wpf-app-window.brown-theme .erp-grid td {
    border-color: var(--wpf-border) !important;
    background-color: transparent !important;
    color: var(--text-main) !important;
        padding: 6px 8px !important;
}
#wpf-app-window.brown-theme .erp-grid tbody tr { background-color: var(--wpf-panel-bg) !important; }
#wpf-app-window.brown-theme .erp-grid tbody tr:nth-child(even) { background-color: #231f1c !important; }
#wpf-app-window.brown-theme .erp-grid tbody tr:hover { background-color: var(--wpf-hover) !important; }

/* 8. จัดการ Tree View (โฟลเดอร์ฝั่งซ้าย) */
#wpf-app-window.brown-theme .tree folder.selected {
    background-image: linear-gradient(to bottom, #8b5a2b, #6b4a2b) !important;
    color: #fff !important;
}
#wpf-app-window.brown-theme .tree folder:hover { background-image: linear-gradient(to bottom, #453c37, #362f2b) !important; }
#wpf-app-window.brown-theme .tree:before, 
#wpf-app-window.brown-theme .tree ul:before,
#wpf-app-window.brown-theme .tree li:before {
    border-color: #5d534d !important; 
    background-color: var(--wpf-panel-bg) !important;
}




/* 9. ซ่อน Dropdown Menu (พวก Submenu เวลากด) */
#wpf-app-window.brown-theme .submenu, 
#wpf-app-window.brown-theme .submenu-item {
    background-color: var(--wpf-panel-bg) !important;
    color: var(--text-main) !important;
    border-color: var(--wpf-border) !important;
}
#wpf-app-window.brown-theme .submenu-item:hover { background-color: var(--wpf-hover) !important; }

/* 10. แถบสถานะด้านบน (1. สร้าง | 2. ติดตาม) */
#wpf-app-window.brown-theme .step.active { color: #d2b48c !important; font-weight: bold; } /* สีทองน้ำตาลสว่าง */

/* =========================================
   [BROWN THEME - เก็บตกเมนูซ้าย & ขอบล่าง]
   ========================================= */

#wpf-app-window.brown-theme .tree-item { color: var(--text-main) !important; }
#wpf-app-window.brown-theme .tree-item:hover { background-color: var(--wpf-hover) !important; color: #ffffff !important; }
#wpf-app-window.brown-theme .tree-item.active {
    background-color: var(--wpf-selected) !important;
    color: #ffffff !important;
    border-left: 3px solid #d2b48c !important;
}

#wpf-app-window.brown-theme #bottom-tray,
#wpf-app-window.brown-theme .tray-tabs {
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
}

/* =========================================
   [BROWN THEME - SHIELD: ปกป้อง .tab-panel]
   ========================================= */
#wpf-app-window.brown-theme .tab-panel {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: none !important;
}
#wpf-app-window.brown-theme .tab-panel input,
#wpf-app-window.brown-theme .tab-panel select,
#wpf-app-window.brown-theme .tab-panel textarea,
#wpf-app-window.brown-theme .tab-panel .form-control {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: 1px solid #cccccc !important;
}


#wpf-app-window.brown-theme .f-row div
 {
  color: #ffff !important;
}


#wpf-app-window.brown-theme .tab-panel table,
#wpf-app-window.brown-theme .tab-panel th,
#wpf-app-window.brown-theme .tab-panel td,
#wpf-app-window.brown-theme .tab-panel label,
#wpf-app-window.brown-theme .tab-panel span {
    color: inherit !important;
    border-color: inherit !important;
}



/* ตารางและสรุปยอด */
#wpf-app-window.brown-theme #bottom-tray { background-color: #1e1a17 !important; }
#wpf-app-window.brown-theme .tray-tabs { background-color: #1e1a17 !important; }
#wpf-app-window.brown-theme .erp-grid th,
#wpf-app-window.brown-theme .erp-grid td { border: 1px solid #3d3530 !important; }
#wpf-app-window.brown-theme .tif-footer-panel { background-color: #231f1c !important; border: 1px solid var(--wpf-border) !important; }
#wpf-app-window.brown-theme .tif-summary-table input { border-bottom: 1px solid #5d534d !important; color: #f5ece0 !important; }

/* Textarea & Checkbox */
#wpf-app-window.brown-theme textarea,
#wpf-app-window.brown-theme .form-control {
    background-color: #1e1a17 !important;
    color: #ffffff !important;
    border: 1px solid var(--wpf-border) !important;
}
#wpf-app-window.brown-theme input[type="checkbox"],
#wpf-app-window.brown-theme input[type="radio"] {
    accent-color: var(--wpf-selected) !important;
}


/* =========================================
   [VS CODE BLUE THEME - NUCLEAR OPTION]
   โทนน้ำเงินกรมท่า ดุดันแบบโปรแกรมเมอร์
   ========================================= */

/* 1. กำหนดตัวแปรสีหลัก (โทน Navy & Cobalt) */
#wpf-app-window.vscode-blue, 
#wpf-app-frame.vscode-blue {
    --wpf-light-bg: #041427;      /* พื้นหลังหลัก (น้ำเงินกรมท่าเข้มสุด) */
    --wpf-panel-bg: #0b1d32;      /* พื้นหลังพาเนล (น้ำเงินสว่างขึ้นนิด) */
    --wpf-border: #1b3a5d;        /* สีเส้นขอบ */
    --wpf-header-grad: linear-gradient(to bottom, #112d4e, #0b1d32); /* หัวตาราง */
    --wpf-selected: #007acc;      /* สีน้ำเงินไฮไลท์ VS Code */
    --wpf-hover: #163250;         /* สีเวลา Hover */
    --text-main: #d1d8e0;         /* ตัวหนังสือสีเทาฟ้า */
    --text-muted: #94a3b8;        /* ตัวหนังสือสีฟ้าหม่น */
}

/* 2. บังคับพื้นหลังกล่องหลักทั้งหมดให้เป็นโหมดน้ำเงิน */
#wpf-app-window.vscode-blue,
#wpf-app-window.vscode-blue #top-section-container,
#wpf-app-window.vscode-blue .tool-strip,
#wpf-app-window.vscode-blue .menus,
#wpf-app-window.vscode-blue .wpf-menu-bar,
#wpf-app-window.vscode-blue .menu,
#wpf-app-window.vscode-blue .main-container,
#wpf-app-window.vscode-blue .workspace,
#wpf-app-window.vscode-blue .work-content,
#wpf-app-window.vscode-blue .group-box,
#wpf-app-window.vscode-blue .tif-pane-bottom,
#wpf-app-window.vscode-blue .tif-toolbar,
#wpf-app-window.vscode-blue .tif-footer-panel,
#wpf-app-window.vscode-blue .tif-summary-table,
#wpf-app-window.vscode-blue .tree-sidebar,
#wpf-app-window.vscode-blue .sidebar-content,
#wpf-app-window.vscode-blue .main-tabs {
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
}

#wpf-app-window.vscode-blue .step-bar {
    background-color: #041427 !important;
    border-color: var(--wpf-border) !important;
}

/* 3. 🎯 กู้ชีพตัวหนังสือที่จมหายไปให้สว่างขึ้น 🎯 */
#wpf-app-window.vscode-blue .menu label,
#wpf-app-window.vscode-blue .menu span,
#wpf-app-window.vscode-blue .tool-btn,
#wpf-app-window.vscode-blue .tool-btn span,
#wpf-app-window.vscode-blue label,
#wpf-app-window.vscode-blue .group-label,
#wpf-app-window.vscode-blue .f-row label,
#wpf-app-window.vscode-blue .tif-summary-table td,
#wpf-app-window.vscode-blue .tif-summary-table b,
#wpf-app-window.vscode-blue .tree li,
#wpf-app-window.vscode-blue .step {
    color: var(--text-main) !important;
    background-color: transparent !important;
}

/* ไอคอนในปุ่ม (ที่ไม่มีสีระบุไว้) ให้เปลี่ยนเป็นสีฟ้าสว่าง */
#wpf-app-window.vscode-blue .tool-btn i:not([style*="color"]) {
    color: #4da6ff !important;
}

/* 4. จัดการลูกเล่นปุ่ม Hover และ Tabs */
#wpf-app-window.vscode-blue .tool-btn:hover { background-color: var(--wpf-hover) !important; border-radius: 4px; }
#wpf-app-window.vscode-blue .s-tab,
#wpf-app-window.vscode-blue .m-tab,
#wpf-app-window.vscode-blue .t-tab {
    background-color: #041427 !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-muted) !important;
}
#wpf-app-window.vscode-blue .s-tab:hover,
#wpf-app-window.vscode-blue .m-tab:hover,
#wpf-app-window.vscode-blue .t-tab:hover { background-color: var(--wpf-hover) !important; }
#wpf-app-window.vscode-blue .s-tab.active,
#wpf-app-window.vscode-blue .m-tab.active,
#wpf-app-window.vscode-blue .t-tab.active {
    background-color: var(--wpf-selected) !important;
    color: #ffffff !important;
    border-color: var(--wpf-selected) !important;
}

/* 5. จัดการช่อง Input และ Dropdown ทั้งหมด */
#wpf-app-window.vscode-blue input[type="text"],
#wpf-app-window.vscode-blue input[type="number"],
#wpf-app-window.vscode-blue select,
#wpf-app-window.vscode-blue .tif-inp-num,
#wpf-app-window.vscode-blue .tif-inp-text,
#wpf-app-window.vscode-blue .wpf-select 

{
    background-color: #06182c !important;
    color: #ffffff !important;
    border: 1px solid var(--wpf-border) !important;
}

#wpf-app-window.vscode-blue .f-row div
 {
  color: #ffffff !important;
}
#wpf-app-window.vscode-blue .tree-item
 {
    color: var(--text-main) !important;
}


#wpf-app-window.vscode-blue input:focus,
#wpf-app-window.vscode-blue select:focus { border-color: var(--wpf-selected) !important; }

/* 6. จัดการ TomSelect (กล่องค้นหา) */
#wpf-app-window.vscode-blue .ts-control {
    background-color: #06182c !important;
    border-color: var(--wpf-border) !important;
    color: #ffffff !important;
}
#wpf-app-window.vscode-blue .ts-dropdown,
#wpf-app-window.vscode-blue .ts-dropdown .ts-dropdown-content {
    background-color: #0b1d32 !important;
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
}

/* 7. จัดการตาราง (ERP Grid) */
#wpf-app-window.vscode-blue .erp-grid th,
#wpf-app-window.vscode-blue .erp-grid thead tr {
    background: var(--wpf-header-grad) !important;
    color: #ffffff !important;
    border-color: var(--wpf-border) !important;
}
#wpf-app-window.vscode-blue .erp-grid td {
    border-color: var(--wpf-border) !important;
    color: var(--text-main) !important;
    background-color: #041427 !important; 
        padding: 6px 8px !important;
}
#wpf-app-window.vscode-blue .erp-grid tbody tr:nth-child(even) { background-color: #041427 !important; }
#wpf-app-window.vscode-blue .erp-grid tbody tr:hover { background-color: var(--wpf-hover) !important; }

/* 8. จัดการ Tree View (โฟลเดอร์ฝั่งซ้าย) */
#wpf-app-window.vscode-blue .tree folder.selected {
    background-image: linear-gradient(to bottom, #007acc, #005a9e) !important;
    color: #fff !important;
}
#wpf-app-window.vscode-blue .tree-item.active {
    background-color: var(--wpf-selected) !important;
    color: #ffffff !important;
    border-left: 3px solid #4da6ff !important;
}


#wpf-app-window.vscode-blue .ts-dropdown .active {
  background-color: var(--wpf-selected) !important;
}


/* =========================================
   [VS CODE BLUE - SHIELD & FIXES]
   ========================================= */

#wpf-app-window.vscode-blue .tab-panel {
    background-color: #ffffff !important;
    color: #333333 !important;
}

#wpf-app-window.vscode-blue #bottom-tray,
#wpf-app-window.vscode-blue .tray-tabs {
    background-color: var(--wpf-panel-bg) !important;
    border-color: var(--wpf-border) !important;
}



#wpf-app-window.vscode-blue .tif-summary-table input {
    border-bottom: 1px solid #1b3a5d !important;
    color: #ffffff !important;
    background: transparent !important;
}



#wpf-app-window.vscode-blue textarea {
    background-color: #06182c !important;
    color: #ffffff !important;
}

#wpf-app-window.vscode-blue input[type="checkbox"] { accent-color: var(--wpf-selected) !important; }
/* =========================================
   [VS CODE BLUE - SHIELD: ปกป้อง .tab-panel]
   คืนค่าหน้าต่างที่ดึงมาจากที่อื่นให้เป็นปกติ (สีขาว)
   ========================================= */

#wpf-app-window.vscode-blue .tab-panel {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: none !important;
}

#wpf-app-window.vscode-blue .tab-panel input,
#wpf-app-window.vscode-blue .tab-panel select,
#wpf-app-window.vscode-blue .tab-panel textarea,
#wpf-app-window.vscode-blue .tab-panel .form-control {
    background-color: #ffffff !important;
    color: #333333 !important;
    border: 1px solid #cccccc !important;
}

#wpf-app-window.vscode-blue .tab-panel table,
#wpf-app-window.vscode-blue .tab-panel th,
#wpf-app-window.vscode-blue .tab-panel td,
#wpf-app-window.vscode-blue .tab-panel label,
#wpf-app-window.vscode-blue .tab-panel span {
    background-color: transparent !important;
    color: inherit !important; /* ใช้สีตามต้นฉบับของหน้านั้นๆ */
    border-color: inherit !important;
}


#wpf-app-window.vscode-blue .tree folder:hover { background-image: linear-gradient(to bottom, #3e3e42, #2d2d30) !important; }
#wpf-app-window.vscode-blue .tree:before, 
#wpf-app-window.vscode-blue .tree ul:before,
#wpf-app-window.vscode-blue .tree li:before {
 border-color: #666 !important; 
 background-color: var(--wpf-panel-bg);
 }

.tab-panel {
    cursor: default !important; /* บังคับให้เป็นเมาส์ปกติ */
}

/* ถ้าต้องการให้ไอเทมข้างใน (เช่น ตัวอักษร) ไม่เป็นรูปตัว I (text selection) ด้วย */
.tab-panel .val-content, 
.tab-panel .val-title {
    cursor: default;
}

</style>

<script>
setupDarkModeSystem({
    containerId: 'wpf-app-window', 
    processId: '${process.id}',
    observerTargetId: 'microsofts${process.id}'
});
</script>
</head>
<body>  <div class="container"  id="" >
   <div class="menus wpf-menu-bar">
   
   
   
      <div class="menu">
        <label> <span class="align-middle">เอกสาร [Document]</span></label>
        <div class="submenu">

<c:set var="policies" value="${fn:split(PoliciesString, ',')}" />
<c:set var="isMatch" value="false" />

<c:forEach var="p" items="${policies}">
    <c:if test="${fn:trim(p) eq flow.cleared}">
        <c:set var="isMatch" value="true" />
    </c:if>
</c:forEach>

       <a onclick="openwindows('draggable-window3','New File','850px');getData('/renewfile-filemanager/${edatas.data_id}', 'mainbody3');">
      <div class="submenu-item ">
   		เปลี่ยนชื่อเอกสาร
         </div>
        </a>   


     <c:if test="${edatas.acknowledge == 0  }">  
          <a onclick="Loader2.show('edatas-${edatas.data_id}','กำลังโหลดข้อมูล process...');getData('/acknowledge-filemanager/${process.id}/${edatas.data_id  }/1/${Appstep_no}', 'microsofts${process.id}')">
      <div class="submenu-item ">
   			รับเอกสาร
         </div>
        </a>   
             <a onclick="Loader2.show('edatas-${edatas.data_id}','กำลังโหลดข้อมูล process...');getData('/acknowledge-filemanager/${process.id}/${edatas.data_id  }/-1/${Appstep_no}', 'microsofts${process.id}')">
      <div class="submenu-item ">
   			ไม่รับเอกสาร
         </div>
        </a> 
     </c:if>
         <c:if test="${edatas.acknowledge == 1 || edatas.acknowledge == -1 }">  
          <a onclick="Loader2.show('edatas-${edatas.data_id}','กำลังโหลดข้อมูล process...');getData('/acknowledge-filemanager/${process.id}/${edatas.data_id  }/0/${Appstep_no}', 'microsofts${process.id}')">
      <div class="submenu-item ">
   			ยกเลิกการรับเอกสาร
         </div>
        </a>   
     </c:if>
    <HR> 
        <c:if test="${ process.closingStatusCanRevise ==  flow.closing_status}"> 
 <c:if test="${flow.receiver == Member_id }">   
     <c:if test="${edatas.doc != null}">    
       <a onclick="confirmAndGet('/appRevision-filemanager/${edatas.data_id}/${process.id}', 
    'microsofts${process.id}','ต้องการดำเนินการ Revision หรือไม่? [Do you want to proceed with the revision?]')">
      <div class="submenu-item ">
     เเก้ไขเอกสารเก็บต้นฉบับไว้ (Revision)
         </div>
        </a>   
     </c:if>    
     </c:if>   
         </c:if>  

    
<c:if test="${Node != null}">  
	     <a onclick="shows2('draggable-window3','New File');getData('/appform-filemanager/${process.id}?id_refer=${Node.relations_id}', 'mainbody3');">
	      <div class="submenu-item ">
	     เพิ่มเอกสาร
	         </div>
	        </a>   
</c:if>
        
 
     
     <c:if test="${edatas.followup == 0 }">  
          <a onclick="Loader2.show('edatas-${edatas.data_id}','กำลังโหลดข้อมูล process...');getData('/FollowUp-filemanager/${process.id}/${edatas.data_id  }/1', 'microsofts${process.id}')">
      <div class="submenu-item ">
      ตึงเอกสารไว้ 
         </div>
        </a>   
     </c:if>   
     <c:if test="${edatas.followup == 1 }">  
      <a onclick="Loader2.show('edatas-${edatas.data_id}','กำลังโหลดข้อมูล process...');getData('/FollowUp-filemanager/${process.id}/${edatas.data_id }/0', 'microsofts${process.id}')">
      <div class="submenu-item ">
     ยกเลิกการตึง
         </div>
        </a>   
      </c:if>   
             <HR> 
        <a onclick="shows2('draggable-window2','Link'); getData('/link-filemanager/${process.id}/${process.process_join }', 'mainbody2')">
      <div class="submenu-item ">
      Link
         </div>
        </a>    

    <div class="submenu-item has-submenu">
     Derive
      <div class="submenu">

      <a  onclick="getData('/boxtask-filemanager/${process.id}/reviewed', 'microsofts');closeed('draggable-iadmin');set_title_app('app','${process.name} [เอกสารรอตรวจ]')" >
          <div class="submenu-item ">
            Normal
          </div>
      </a>

   <a  onclick="getData('/app-filemanager/${process.id}', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [แบบฟอร์มขอจัดตารางกะ]')" >
          <div class="submenu-item ">
            Split
          </div>
      </a>
   <a  onclick="getData('/app-filemanager/${process.id}', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [แบบฟอร์มขอจัดตารางกะ]')" >
          <div class="submenu-item ">
           Merge
          </div>
      </a>

   
      
</div>
</div>
   
    </div>
     </div>
   
   
      <div class="menu">
         <label>การติดตาม [Tracking]</label>
         <div class="submenu">
            
                   <a onclick="shows2('draggable-window3','Step Logs'); getData('/steplog-filemanager/${edatas.data_id}/${process.id}', 'mainbody3')">
      <div class="submenu-item ">
ประวัติเอกสาร [Step]
         </div>
        </a>   
 <a onclick="shows2('draggable-window3','Access');getData('/workflowaccess-filemanager/${edatas.data_id}/${process.id}', 'mainbody3')">
      <div class="submenu-item ">
ผู้ใช้งานที่เกี่ยวข้อง [Workflow Access]
         </div>
        </a>
        
        <c:if test="${flow.sender == Member_id }">   
    <a onclick="confirmAndGet('/undosend-filemanager/${edatas.data_id}/${process.id}/${flow.id}', 
    'microsofts${process.id}','Bring a document back from a sent state?')">
      <div class="submenu-item ">
ดึงกลับ [Undo Send]
         </div>
        </a>
        </c:if>     
      

        
        </div>
      </div>



      <div class="menu">
        <label>Validation</label>
        <div class="submenu">
       
        <a  onclick="getData('/holiday/all', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [วันหยุด]')" >
          <div class="submenu-item ">
    แจ้งเตือนความถูกต้อง
          </div>
        </a>
        
         <a onclick="shows2('draggable-window3','Levenshtein'); getData('/Levenshtein-filemanager/${edatas.data_id}', 'mainbody3')">
      <div class="submenu-item ">
Levenshtein index
         </div>
        </a>
        
        
             <a  onclick="getData('/holiday/all', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [วันหยุด]')" >
          <div class="submenu-item ">
      ผลสำเร็จเอกสาร
          </div>
        </a>
    
     
 
    
    </div>
     </div>






    <div class="menu">
        <label>Settings</label>
        <div class="submenu">
       <a  onclick="toggleDarkMode()" >
          <div class="submenu-item ">
ธีมมืด
          </div>
        </a>
                      <a  onclick="openwindows('draggable-window3','Form & Layout','950px');getData('/setwindow-filemanager/${process.id}', 'mainbody3')" >
          <div class="submenu-item ">
แบบฟอร์มและเลย์เอาต์
          </div>
        <a  onclick="openwindows('draggable-window3','Workflow Setting','950px');getData('/setworkflow-filemanager/${process.id}', 'mainbody3')" >
          <div class="submenu-item ">
กระบวนการทำงาน
          </div>
        </a>
        

        </a>
        
             <a  onclick="openwindow('draggable-window3','Setup Print Form',9000); getData('/setprint-filemanager/${process.id}/${edatas.data_id+0}', 'mainbody3')" >
          <div class="submenu-item ">
ฟอร์มพิมพ์
          </div>
        </a>
        <a  onclick="getData('/holiday/all', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [วันหยุด]')" >
          <div class="submenu-item ">
   ซ่อน/จับคู่คอลัมน์
          </div>
        </a>
     
     
 
    
    </div>
     </div>

  
      <div class="menu" >
         <label>ช่วยเหลือ</label>
         <div class="submenu">
         <div class="submenu-item ">
            ข้อการสนับสนุน
            
         </div>
         <div class="submenu-item ">
            คู่มือการใช้งาน
            
         </div>
  <hr>
         <div class="submenu-item ">
            กลุ่มสำหรับพูดคุย
            
         </div>
         <div class="submenu-item ">
            รายงานปัญหา
            
         </div>
         <div class="submenu-item ">
            ปัญหาที่พบบ่อย
            
         </div><hr>
         <div class="submenu-item ">
            การเเนะนำ
            
         </div></div>
      </div>
      
      </div>
      
        </div>  
<div id="wpf-app-window">
 

    <div id="top-section-container">
        <div id="top-dimmer" onclick="closeTray()"></div>

        <div class="tool-strip">
        
        
     <c:if test ="${process_refer == null}">     
            <div class="tool-btn" onclick="getData('/app-filemanager/${process.id}?boxs=${boxs }', 'microsofts${process.id}');
   setTitleApp('app${process.id}','${process.name} [${apptitle }]');"><i class="fa fa-arrow-left"></i><span>กลับ</span></div>
   </c:if>
 
   
   
   
          <div class="tool-btn" id="refresh-${process.id}"  onclick="refreshData2(this, '/editdocumentapp-filemanager/${edatas.data_id}/${process.id}?boxs=${boxs }&&process_refer=${process_refer}', 'microsofts${process.id}')"   >
          <i class="fa fa-arrows-rotate" style="color:#0078d7;"></i><span>รีเฟรช</span></div>
          
          

   <c:if test ="${flow.current_step  == 'Create' ||  flow.current_step  == 'Review'  ||  flow.current_step  == 'Proceed'}">
         
		 <div class="tool-btn" id="tool-btn" onclick="this.style.opacity='0.4'; this.style.pointerEvents='none'; 
   postDataNotReset('/saveapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', 'savestatus${process.id}');
   sendCallBack();
      setTimeout(() => { 
            this.style.opacity='1'; 
            this.style.pointerEvents='auto'; 
        }, 1000);
   " 
   style="cursor: pointer; transition: 0.3s;" ><i class="fa fa-floppy-disk" style="color:#097969"></i><span >Save</span>
   </div>
   

         <div class="tool-btn" id="tool-btn" onclick="this.style.opacity='0.4'; this.style.pointerEvents='none'; 
   postDataNotReset('/saveapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', 'savestatus${process.id}');
      setTimeout(() => { 
           refreshData2(this, '/editdocumentapp-filemanager/${edatas.data_id}/${process.id}?boxs=${boxs }', 'microsofts${process.id}')
        }, 1000);
   " style="cursor: pointer; transition: 0.3s;" >
 <span class="fa-stack" style="color:#E67E22; font-size: 0.8em; ">
  <i class="fa fa-floppy-disk fa-stack-2x"></i>
  <i class="fa fa-rotate-right fa-stack-1x fa-inverse" style="transform: translate(50%, -50%) scale(0.6); background: #097969; border-radius: 50%; color: #E67E22; padding: 3px;"></i>
</span>
   
   <span >Save</span></div>
   

   


   </c:if>       
            
			
			
			

            <div class="tool-sep"></div>
     
<div class="tool-btn ${(edatas.problem != null) || (flow.expired == 1) || (flow.request_to_edit == 1) ? 'alert-big-glow' : ''}" 
     style="${edatas.problem == null ? 'color:var(--wpf-selected)' : ''}" onclick="toggleTray(0);">
				<i class="fa-solid fa-shield-halved"></i><span>การตรวจทาน</span></div>
            <div class="tool-btn" style="color:var(--wpf-selected)" onclick="toggleTray(1)"><i class="fa fa-file"></i><span>ไฟล์เเนบ</span></div>
      
      
		    <c:catch var="catchError">
		    <c:set var="snapshotVal" value="${transaction['segmentation_profile_static_snapshot']}" />
		</c:catch>
		
		<c:if test="${empty catchError and not empty snapshotVal and snapshotVal > 0}">                 
		    <div class="tool-btn" style="color:var(--wpf-selected)" onclick="
		     getData('/ResultsSegmentation-extension/${process.id}/${snapshotVal}/${edatas['data_id']}', 'panel-4');
		     toggleTray(4)">
		        <i class="fa fa-tasks"></i><span>สรุปผล</span>
		    </div>
		</c:if>
                 
 
         

<c:set var="arr_ex" value="${fn:split(process.use_ex, ',')}" />
<c:set var="arr_icon" value="${fn:split(process.use_ex_icon, ',')}" />
<c:set var="arr_name" value="${fn:split(process.use_ex_name, ',')}" />
<c:set var="appId" value="${process.use_index_app}" />

<c:catch var="errorCaught">
 <c:set var="regCode" value="${transaction['customerCode']}" />
</c:catch>

<c:choose>
    <c:when test="${not empty errorCaught || appId == 0 }">
        <c:set var="regCode" value="NO_CODE" />
        </c:when>
    <c:otherwise>
        <c:if test="${empty regCode || appId == 0}">
            <c:set var="regCode" value="NO_CODE" />
        </c:if>
    </c:otherwise>
</c:choose>


              

 <c:if test="${regCode != 'NO_CODE'}">   
 
 
        <div class="tool-btn" style="color:var(--wpf-selected)" onclick="getData('/profileapp-filemanager/${regCode}/${appId}?Tray=1','panel-2');toggleTray(2)">
             <i class="fa fa-user-tag"></i><span>ข้อมูลลูกค้า</span></div>
    
    <div class="tool-btn" style="color:var(--wpf-selected)" onclick="
                 getData('/calendar-filemanager/${process.id}?edata_id=${edatas.data_id}&&index_id=${regCode}&&Tray=1', 'panel-3');
                 toggleTray(3)"><i class="fa fa-calendar-check"></i><span>นัดติดตาม</span></div>
 
     
         <c:forEach var="exId" items="${arr_ex}" varStatus="status">
       
        <c:if test="${not empty exId  }">
            <c:set var="idx" value="${status.index + 5}" />
            <c:set var="icon" value="${arr_icon[status.index]}" />
            <c:set var="name" value="${arr_name[status.index]}" />

            <div class="tool-btn" style="color:var(--wpf-selected)" 
                 onclick="getData('/extension-filemanager/${exId}/${regCode}/${appId}?from=${edatas.data_id}&&Tray=1', 'panel-${idx}');toggleTray(${idx})">
                <i class="fa ${icon}"></i><span>${name}</span>
            </div>
        </c:if>
    </c:forEach>
          </c:if>  
                
           
            
        
            <div class="tool-sep"></div>
            <div class="tool-btn" onclick="getData('/setprint-filemanager/${process.id}/${edatas.data_id+0}', 'mainbody3');openwindow('draggable-window3','Setup Print Form',9000); "><i class="fa fa-print"></i><span>Print</span></div>
          
             <c:if test ="${flow.current_step  != 'Completion'}">
          
          
             <c:if test="${flow.creator == Member_id &&  flow.current_step == 'Create'  && flow.request_to_edit == 0}">   
            <div class="tool-btn" style="color:#a00;" 
            onclick="confirmAndDelete('/undocumentapp-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myForms${process.id}', 'microsofts${process.id}')" >
            <i class="fa fa-trash-can" style="color:#E33B5C"></i><span>ลบเอกสาร</span></div>
            </c:if>
                </c:if>  
                   <c:if test="${ flow.current_step != 'Create' && flow.current_step  != 'Completion'}">   
<div class="tool-btn"  onclick="openwindow('draggable-window6','Link','9500');
                 getData('/Ticketflowdoc-filemanager/${edatas.data_id+0}/${process.id}', 'mainbody6'); 
                 set_title_app('miniwindows','ส่งกลับแก้ไข (Rework)')" >
    <i class="fa fa-undo" style="color:#d9534f;"></i>
    <span>ขอแก้ไข</span>
</div>
   </c:if> 

 
            <c:if test="${  flow.expired != 1 && ( edatas.acknowledge == 1  && process.is_acknowledge_required == 1 ) ||  process.is_acknowledge_required != 1}">  
              <c:if test="${flow.receiver == Member_id }">
                 <div class="tool-btn"  onclick="checkAndOpenFlowDoc('${edatas.data_id}', '${process.id}');" >
    <i class="fa fa-check-circle" style="color:#097969;"></i>
    <span>ส่ง / ปิด</span>
</div> 


             </c:if> 
                 </c:if> 
                 
                 
                 
              
         
  
  
        </div>

        <div class="main-container" id="edatas-${edatas.data_id}">
            <aside class="tree-sidebar">
                <div class="sidebar-tabs">
                    <div class="s-tab  "  id="Tab-Tabs-ability${process.id}-1"  style="display:none">ERP Modules</div>
                    <div class="s-tab  active"  id="Tab-Tabs-ability${process.id}-2"  >Documents</div>
                </div>
               
                
                
                
                <div class="sidebar-content" id="Form-Tabs-ability${process.id}-1"  style="display:none">
                    <div class="tree-item active"><i class="fa fa-folder-open" style="color:#d4af37"></i> Register Entry</div>
                    <div class="tree-item" style="padding-left:40px;"><i class="fa fa-file-invoice"></i> 1. ขอขึ้นทะเบียน</div>
                    <div class="tree-item" style="padding-left:40px;"><i class="fa fa-gears"></i> 2. ดำเนินการทะเบียน</div>
                    <div class="tree-item"><i class="fa fa-folder"></i> Blueprint (PD/BOM)</div>
                    <div class="tree-item"><i class="fa fa-calendar-days"></i> Scheduling</div>
                </div>
                 <div class="sidebar-content" id="Form-Tabs-ability${process.id}-2">
                 <c:if test="${Node !=  null }">  
                 <br>         
 					<%@ include file="node.jspf" %> 
 				</c:if>
                </div>
            </aside>

            <main class="workspace">
           <div class="step-bar">
    <c:forEach items="${all_processes}" var="rows" varStatus="count">
        
        <%-- ==========================================
             1. ตรวจสอบสถานะ (State: done, active, หรือว่าง) 
             ========================================== --%>
        <c:set var="stepClass" value="" />
        <c:choose>
            <c:when test="${Appstep_no > rows.id}">
                <c:set var="stepClass" value="done" />
            </c:when>
            <c:when test="${Appstep_no == rows.id}">
                <c:set var="stepClass" value="active" />
            </c:when>
        </c:choose>

        <%-- ==========================================
             2. จับคู่ไอคอนให้สื่อความหมาย (Semantic Icons)
             ========================================== --%>
        <c:set var="stepIcon" value="fa-circle-dot" /> <%-- ไอคอน Default --%>
        
        <c:choose>
            <c:when test="${fn:containsIgnoreCase(rows.step, 'Create')}">
                <c:set var="stepIcon" value="fa-file-pen" /> <%-- ไอคอนกระดาษกับปากกา (สร้าง/จัดทำ) --%>
            </c:when>
            <c:when test="${fn:containsIgnoreCase(rows.step, 'Proceed')}">
                <c:set var="stepIcon" value="fa-gears" /> <%-- ไอคอนฟันเฟือง (กำลังดำเนินการ) --%>
            </c:when>
            <c:when test="${fn:containsIgnoreCase(rows.step, 'Review')}">
                <c:set var="stepIcon" value="fa-clipboard-check" /> <%-- ไอคอนคลิปบอร์ดตรวจสอบ (ตรวจสอบ) --%>
            </c:when>
            <c:when test="${fn:containsIgnoreCase(rows.step, 'Approval')}">
                <c:set var="stepIcon" value="fa-stamp" /> <%-- ไอคอนตรายาง (อนุมัติ) --%>
            </c:when>
            <c:when test="${fn:containsIgnoreCase(rows.step, 'Completion')}">
                <c:set var="stepIcon" value="fa-flag-checkered" /> <%-- ไอคอนธงหมากรุก (เสร็จสิ้นกระบวนการ) --%>
            </c:when>
        </c:choose>

        <%-- ==========================================
             3. แสดงผล HTML (Render)
             ========================================== --%>
        <div class="step ${stepClass}">
            <%-- ทริค: ถ้าขั้นตอนไหนทำเสร็จแล้ว (done) จะบังคับเปลี่ยนไอคอนเป็น "เครื่องหมายถูก" เสมอ --%>
            <c:choose>
                <c:when test="${stepClass == 'done'}">
                    <i class="fa fa-check-circle"></i>
                </c:when>
                <c:when test="${stepClass == 'active'}">
                    <%-- เพิ่ม fa-fade หรือ fa-beat ให้กระพริบนิดๆ เบาๆ เพื่อดึงดูดสายตาว่า "อยู่ที่นี่นะ!" --%>
                    <i class="fa ${stepIcon} fa-fade"></i>
                </c:when>
                <c:otherwise>
                    <i class="fa ${stepIcon}"></i>
                </c:otherwise>
            </c:choose>
            
            ${count.count}. ${rows.description}
        </div>
        
    </c:forEach>
</div>

                    
          <form action="#" id="myForms${process.id}"  class="fromfull" name="myForm" modelAttribute="edata"   method="POST" enctype="multipart/form-data" >           
        

<input type="hidden" name="form_status" value="app">                                       
<input type="hidden" id="myInput" name="app_id" value="${transaction['id']+0}" onfocus="console.log('Input focused!')"> 
<input type="hidden" name="app_edata_id" value="${edatas.data_id+0}">          
<input type="hidden" name="edata.data_id" value="${edatas.data_id+0}">              
<input type="hidden" name="app_tempId" value="${tempId}">                         
<input type="hidden" name="app_sub_processes_id"  id="sub_processes_id"  value="${sub_processes.id}">                                                           
<input type="hidden" name="app_processes_id"  id="processes_id"  value="${process.id}">
<input type="hidden" name="app_model" value="${appmodel}">
<input type="hidden" name="process_refer" value="${process_refer}">
     
     


     
<input type="hidden" name="${appmodel}edata_id" value="${edatas.data_id+0}">   
<input type="hidden" name="${appmodel}tempId"   value="${tempId}">   
<input type="hidden" name="${appmodel}processes_id"    value="${process.id}">  
<input type="hidden" name="${appmodel}sub_processes_id"  id="sub_processes_id"    value="${sub_processes.id}">  
                <div class="main-tabs">
      
                    
                    <c:forEach items="${TabForm}" var="rows" varStatus="counts">
    <div  class="m-tab 
         <%-- 2. เช็คเงื่อนไข: ต้องเป็น 1 และต้อง "ยังไม่เคย Active" มาก่อน --%>
         <c:if test="${counts.count == 1}">
             active
             <%-- 3. พอใส่ active แล้ว ให้สับสวิตช์เป็น true ทันที ตัวหลังจะเข้าไม่ได้แล้ว --%>
             <c:set var="hasActive" value="true" />
         </c:if>" 
         
         id="Tab-TabForm${process.id}-${counts.count}" 
         onclick="switchTab('TabForm${process.id}','${TabForm.size()}','${counts.count}')">
         ${rows.title}
    </div>
</c:forEach>
                </div>

                <div class="work-content">
                  
                  
<c:if test="${TabForms.size() > 0 }"> 

<c:set var="tabform_status" value="1" />    
   <c:forEach items="${TabForm }" var="Tab" varStatus="counts">     
	<div  id="Form-TabForm${process.id}-${counts.count }" style="display:<c:if test="${ counts.count > 1 }">none;</c:if>


	<c:if test="${Tab.tab_access ==  1 && TabFormStart == 0 }">pointer-events: none; opacity: 0.9;</c:if>">
	 <div style="display:grid; grid-template-columns:1fr 1fr; gap:20px;">

		<%@ include file="common.jsp" %> 
		
   		</div>
	   </div>
	</c:forEach>
</c:if>
                   

          

<c:if test="${action_batch_insert != null 
&& (  fn:contains(process.subtable_viewmode,Appstep_no) 
||  fn:contains(process.subtable_editmode,Appstep_no) )
 }">  
                    <div class="group-box">
                        <div class="group-label">รายการย่อย (Items)</div>
                   
    <div class="tif-pane-bottom">
        <div class="tif-toolbar">
        <c:if test="${ fn:contains(process.subtable_editmode,Appstep_no)}">  
           <button type="button" id="btnAddRow${process.id}" class="tif-btn" ><b>+ Add Row</b></button> <div style="border-left: 1px solid #ccc; height: 16px; margin: 0 4px;"></div>
       <button type="button"  onclick="initProductTomSelects();"><b>รีเฟรส</b></button> 
      </c:if> 
      
    
        </div>



        <div class="tif-table-wrapper" id="tifContainer${process.id}">                      
                         <table class="erp-grid" id="tifTable${process.id}" >
             
                <thead>
             <tr>
                 <th style="width:40px;">#</th>
                 
                <c:forEach items="${subeformfields }"   var="row" varStatus="count">
    
   			<c:choose>
            <c:when test="${count.index == 0}">
                <th style="width: 50%;">${row.field_label}</th> 
            </c:when>
            <c:otherwise>
                <th>${row.field_label}</th>
            </c:otherwise>
        </c:choose>
   			  	</c:forEach>
 			</tr>
         
		
                
        
                    
                </thead>
                <tbody id="tifTableBody${process.id}">
                  
                 <c:if test ="${subDataList == null }">
                    <tr data-row-id="1">
                    <td><div style="padding:12px; text-align:center;">1</div></td>
                    <c:forEach items="${subeformfields }"   var="row" varStatus="count">
                           <td><%@ include file="input/new_input_table.jspf" %></td>
                    </c:forEach>         
                    </tr>
                   </c:if> 
                    
                       <c:forEach items="${subDataList }"   var="subData" varStatus="count">
		                      <tr data-row-id="${count.count }">
		                    <td><div style="padding:12px; text-align:center;">${count.count }</div></td>
		                    <c:forEach items="${subeformfields }"   var="row" varStatus="count">
		                           <td><%@ include file="input/new_input_table.jspf" %></td>
		                    </c:forEach>         
		                    </tr>
                       </c:forEach>  
                </tbody>
            </table>
               <div class="tif-footer-panel">
            <table class="tif-summary-table">
                <tr>
                    <td>รวมเป็นเงิน</td>
                    <td><input type="text"  class="tif-inp-num" id="grand_totals" readonly></td>
                    <td>บาท</td>
                </tr>
                <tr>
                    <td>ส่วนลด</td>
                    <td><input type="text" class="tif-inp-num"  id="grand_discounts" > </td>
                    <td>บาท</td>
                </tr>
                <tr>
                    <td>ราคาหลังหลักส่วนลด</td>
                    <td><input type="text" class="tif-inp-num"  id="grand_totals2" > </td>
                    <td>บาท</td>
                </tr>
                <tr>
                    <td>ภาษี (7%)</td>
                    <td><input type="text" id="vats" class="tif-inp-num"  readonly></td>
                    <td>บาท</td>
                </tr>
                <tr style="border-top: 2px solid #aaa;">
                    <td><b>ยอดสุทธิ</b></td>
                    <td style="color:blue;"><b><input type="text" class="tif-inp-num" id="net_amounts" readonly></b></td>
                    <td><b>บาท</b></td>
                </tr>
            </table>
                    </div>
                     </div>
                     
                        <div id="tifContextMenu${process.id}" class="tif-context-menu">

 <div class="tif-ctx-item" data-action="insert_below">Insert Copy Below</div>
        <div class="tif-ctx-item" data-action="add_new">Add New Empty Row</div>
        <div class="tif-ctx-divider"></div>
        <div class="tif-ctx-item" style="color:red;" data-action="delete">Delete Row</div>
    </div>
 </c:if>                   
                    
                    
                </div>
 </form>               
                
                
            </main>
        </div>
    </div>

    <div id="bottom-tray">
    
    
        <div class="resizer" id="tray-handle"></div>
        <div class="tray-tabs">
             <div class="t-tab" data-idx="0" onclick="toggleTray(0);">การตรวจทาน</div>
            <div class="t-tab" data-idx="1" onclick="toggleTray(1)">ไฟล์เเนบ</div>
<c:if test="${snapshotVal > 0}">      
                      <div class="t-tab" data-idx="4" onclick="toggleTray(4)">สรุปผล</div>
             
                  </c:if>   



         <c:if test="${regCode != 'NO_CODE'}">   
         
                    <div class="t-tab" data-idx="2" onclick="toggleTray(2)">ข้อมูลลูกค้า</div>
             <div class="t-tab" data-idx="3" onclick="toggleTray(3)">กำหนดติดตาม</div>     
   
<c:forEach var="exId" items="${arr_ex}" varStatus="status">
        <c:if test="${not empty exId && regCode != 'NO_CODE'}">
            <c:set var="idx" value="${status.index +5}" />
            <c:set var="name" value="${arr_name[status.index]}" />
            
            <div class="t-tab" data-idx="${idx}" onclick="toggleTray(${idx})">${name}</div>
        </c:if>
    </c:forEach>
         </c:if>   
              
        </div>
    <div class="tab-panel" id="panel-0">  <%@ include file="ability.jspf" %> </div>
        <div class="tab-panel" id="panel-1">  <%@ include file="upload.jspf" %> </div>
        <div class="tab-panel" id="panel-2">กรุณากดที่เมนูหลักเพื่อเรียกก่อน</div>
         <div class="tab-panel" id="panel-3">กรุณากดที่เมนูหลักเพื่อเรียกก่อน</div> 
            <div class="tab-panel" id="panel-4">กรุณากดที่เมนูหลักเพื่อเรียกก่อน</div> 
                  <c:if test="${regCode != 'NO_CODE'}">   
                      
<c:forEach var="exId" items="${arr_ex}" varStatus="status">

        <c:if test="${not empty exId}">
            <c:set var="idx" value="${status.index + 5}" />
            
            <div class="tab-panel" id="panel-${idx}">กรุณากดที่เมนูหลักเพื่อเรียกก่อน</div>
        </c:if>
    </c:forEach>
      </c:if>
     
    </div>
</div>

<script>
/* =========================================
LOGIC: TABS & FOCUS MODE (แบบเบสิค ไม่ต้องคำนวณ)
========================================= */



var tray;
var dimmer;
var currentIdx = -1;

function StarttoggleTray() {
	 tray = document.getElementById('bottom-tray');
	 dimmer = document.getElementById('top-dimmer');

	// เสริม UX: ถ้าคลิกที่พื้นสีดำ ให้หุบถาดเก็บ
	if (dimmer) {
	 dimmer.addEventListener('click', closeTray);
	}
}

StarttoggleTray();   

//🌟 รับ event (e) เข้ามาด้วย เพื่อหยุดการกระเพื่อมของคลิก
function toggleTray(idx, e) {
    // 1. หยุดการส่งต่อคลิก ไม่ให้ทะลุไปโดนจอดำหรือ body
    if (e) {
        e.preventDefault();
        e.stopPropagation(); 
    }

    // 2. บังคับแปลง idx ให้เป็นตัวเลข (Number) เสมอ ป้องกันบั๊ก String vs Number
    const targetIdx = parseInt(idx);

    if (currentIdx === targetIdx) {
        closeTray();
    } else {
        openTray(targetIdx);
    }
}

function openTray(idx) {
    currentIdx = idx; // อัปเดตสถานะให้เป็นเลขปัจจุบัน
	 tray = document.getElementById('bottom-tray');
	 dimmer = document.getElementById('top-dimmer');
	 
	 
	   tray.classList.add('expanded');
    
    // เปิดจอสีดำ
    if (dimmer) dimmer.style.display = 'block';

    // จัดการ Tab Active
    document.querySelectorAll('.t-tab').forEach(t => {
        const tabIdx = parseInt(t.getAttribute('data-idx'));
        if (tabIdx === idx) {
            t.classList.add('active');
        } else {
            t.classList.remove('active');
        }
    });

    // จัดการ Panel Active
    document.querySelectorAll('.tab-panel').forEach(p => {
        if (p.id === 'panel-' + idx) {
            p.classList.add('active');
        } else {
            p.classList.remove('active');
        }
    });
}

function closeTray() {
    // เอาคลาส expanded ออก
    tray.classList.remove('expanded');
    
    // ปิดจอสีดำ
    if (dimmer) dimmer.style.display = 'none';
    
    currentIdx = -1; // รีเซ็ตสถานะ

    // ล้างสถานะ Active ของ Tab
    document.querySelectorAll('.t-tab').forEach(t => t.classList.remove('active'));
    document.querySelectorAll('.tab-panel').forEach(p => p.classList.remove('active'));
}

function switchMainTab(idx, e) {
    if (e) e.stopPropagation();
    const targetIdx = parseInt(idx);
    document.querySelectorAll('.m-tab').forEach((t, i) => {
        if (i === targetIdx) t.classList.add('active');
        else t.classList.remove('active');
    });
}
    
    
    
function refreshData2(btnElement, url, targetId) {
    // หา div ที่มี class="tool-strip" ที่ครอบปุ่มนี้อยู่ 
    // (หรือถ้าไม่ได้ครอบอยู่ ก็ให้หาอันแรกในหน้าเว็บแทน)
    const toolStrip = btnElement.closest('.tool-strip') || document.querySelector('.tool-strip');

    // ถ้าระบบกำลังล็อกอยู่ ให้ข้ามไปเลย ไม่ต้องทำอะไร
    if (toolStrip && toolStrip.classList.contains('is-locked')) return;

    // --- 1. ล็อกเฉพาะ .tool-strip ---
    if (toolStrip) {
        toolStrip.classList.add('is-locked');
        toolStrip.style.pointerEvents = "none"; // ห้ามกด
        toolStrip.style.opacity = "0.7"; // 💡 ทริคเสริม: ลดความสว่างลงนิดนึง ผู้ใช้จะได้รู้ว่ามันโหลดอยู่และกดไม่ได้
    }

    const icon = btnElement.querySelector('.fa-arrows-rotate');
    
    if (icon) {
        // 2. หมุนไอคอน
        icon.style.transition = "transform 0.5s ease-in-out";
        icon.style.transform = "rotate(360deg)";
        
        // 3. หน่วงเวลา 0.5 วินาที
        setTimeout(async () => {
            icon.style.transition = "none"; 
            icon.style.transform = "rotate(0deg)";
            
            // 4. เรียกข้อมูล
            await executeGetData(url, targetId, toolStrip);
        }, 500); 
        
    } else {
        (async () => {
            await executeGetData(url, targetId, toolStrip);
        })();
    }
}

// ฟังก์ชันสำหรับครอบ getData
async function executeGetData(url, targetId, toolStrip) {
    try {
        await getData(url, targetId);
    } catch (error) {
        console.error("เกิดข้อผิดพลาดในการดึงข้อมูล:", error);
    } finally {
        // 5. ปลดล็อก .tool-strip (ทำเสมอเมื่อโหลดเสร็จหรือเกิด Error)
        if (toolStrip) {
            toolStrip.classList.remove('is-locked');
            toolStrip.style.pointerEvents = "auto"; // กลับมากดได้ปกติ
            toolStrip.style.opacity = "1"; // คืนความสว่างกลับมาเป็นปกติ
        }
    }
}
    
    
    

savestatus();

    //หุ้มด้วย IIFE เพื่อสร้าง Scope ส่วนตัว ไม่ให้ตีกับคนอื่น
    (function() {
    	
        // 1. รับ ID เข้ามาเก็บไว้ใน Scope นี้
        var processId = '${process.id}'; 
    	
    	
        // A. ผูกปุ่ม Add Row (ด้วย ID เฉพาะ)
        var btnAdd = document.getElementById('btnAddRow' + processId);
        if (btnAdd) {
            btnAdd.addEventListener('click', function() {
                addNewRow(true);
            });
        }
  
    
        
        // ดึง Element หลักๆ ของหน้านี้มาเตรียมไว้
        var container = document.getElementById('tifContainer' + processId);
        var tbody = document.getElementById('tifTableBody' + processId);
        var table = document.getElementById('tifTable' + processId);
        var contextMenu = document.getElementById('tifContextMenu' + processId);

        // ถ้าหาไม่เจอ ให้จบการทำงาน (กัน Error)
        if (!container || !tbody || !table) return;


        // --- 2. ฟังก์ชัน Logic (ประกาศแบบ Local ไม่ใช้ window.) ---
      function addNewRow(isEmpty = true, sourceRow = null) {
        const templateRow = tbody.rows[0]; 
        if (!templateRow) return;

        const newRow = templateRow.cloneNode(true);
        const newId = tbody.rows.length + 1;
        
        newRow.setAttribute('data-row-id', newId);
        const idDiv = newRow.cells[0].querySelector('div');
        if(idDiv) idDiv.textContent = newId;

        // 🌟 1. ล้าง "ซาก" UI ของ TomSelect เก่าที่ติดมาจากการ Clone
        const tsWrappers = newRow.querySelectorAll('.ts-wrapper');
        tsWrappers.forEach(wrapper => wrapper.remove()); // ลบทิ้งเลย

        // Reset Inputs
        const inputs = newRow.querySelectorAll('input:not([type="checkbox"]):not([type="file"]), select');
        inputs.forEach((inp, index) => {
            
            // 🌟 2. ถ้าเป็น Select ให้เคลียร์ Class ที่ TomSelect ฝังไว้ออก เพื่อให้มันกลับมาเป็น Select ปกติ
            if (inp.tagName.toLowerCase() === 'select') {
                inp.classList.remove('tomselected', 'ts-hidden-accessible');
                // ถอด property ของ TomSelect เก่าออกเผื่อหลงเหลือ
                if(inp.tomselect) {
                    inp.tomselect.destroy(); // บางเบราว์เซอร์อาจจะก๊อปปี้ object มาด้วย
                    delete inp.tomselect;
                }
            }

            if(isEmpty) {
                if(inp.classList.contains('tif-readonly')) inp.value = "0.00";
                else inp.value = "";
            } else if (sourceRow) {
                const srcInp = sourceRow.querySelectorAll('input:not([type="checkbox"]):not([type="file"]), select')[index];
                if(srcInp) inp.value = srcInp.value;
            }
        });

        // Reset Checkboxes
        const newChecks = newRow.querySelectorAll('.tif-multi-popup input[type="checkbox"]');
        const srcChecks = sourceRow ? sourceRow.querySelectorAll('.tif-multi-popup input[type="checkbox"]') : null;
        newChecks.forEach((chk, i) => {
            if(isEmpty) chk.checked = false;
            else if (srcChecks && srcChecks[i]) chk.checked = srcChecks[i].checked;
            if(i === 0) updateMultiDisplay(chk); 
        });

        // Reset Image
        const imgPreview = newRow.querySelector('.tif-img-preview');
        const fileInput = newRow.querySelector('.tif-file-input');
        if(imgPreview) { imgPreview.src = ""; imgPreview.classList.remove('show'); }
        if(fileInput) fileInput.value = "";

        tbody.appendChild(newRow);
        calculateGrandTotal();

      
    }
        function updateMultiDisplay(checkbox) {
            const popup = checkbox.closest('.tif-multi-popup');
            const display = popup.parentElement.querySelector('.tif-multi-display');
            const checked = popup.querySelectorAll('input:checked');
            const vals = Array.from(checked).map(c => c.parentElement.textContent.trim());
            display.textContent = vals.length ? vals.join(', ') : 'Select...';
        }


        // --- 3. ผูก Event Listeners (Binding) ---

        
        
        <c:if test="${fn:contains(process.subtable_editmode,Appstep_no)}">  
        // B. Context Menu Logic (ตัวแปรเก็บ Row ที่ถูกเลือก เฉพาะหน้านี้)
        let selectedRowForMenu = null;

        if (contextMenu) {
            // ดัก Click ที่ตัวเมนูเลย (Event Delegation) แล้วดูว่ากดปุ่มไหน
            contextMenu.addEventListener('click', function(e) {
                // หาปุ่มที่กด โดยดูจาก attribute 'data-action'
                const actionItem = e.target.closest('[data-action]'); 
                if (!actionItem || !selectedRowForMenu) return;

                const action = actionItem.getAttribute('data-action');

                if (action === 'delete') {
                    if(tbody.rows.length > 1) {
                    	 selectedRowForMenu.remove();
                    	 calculateGrandTotal();
                    } else {
                        alert("ต้องเหลือไว้อย่างน้อย 1 แถว");
                    }
                } else if (action === 'insert_below') {
                    addNewRow(false, selectedRowForMenu);
                } else if (action === 'add_new') {
                    addNewRow(true);
                }

                // ปิดเมนู
                contextMenu.classList.remove('show');
            });
        }
        </c:if>

        // C. ดักคลิกขวา (Context Menu Trigger) เฉพาะตารางนี้
        container.addEventListener('contextmenu', (e) => {
            // เช็คว่ากดในตารางนี้ไหม
            const targetTable = e.target.closest('#tifTable' + processId);
            const targetRow = e.target.closest('tr');

            if (targetTable && targetRow && targetRow.parentElement.tagName !== 'THEAD' && contextMenu) {
                e.preventDefault();
                selectedRowForMenu = targetRow; // จำไว้ใน scope นี้
                
                contextMenu.style.left = e.clientX + 'px';
                contextMenu.style.top = e.clientY + 'px';
                contextMenu.classList.add('show');
            } else if (contextMenu) {
                contextMenu.classList.remove('show');
            }
        });

        // D. ดักคลิกทั่วไป (เพื่อปิด Popup/Menu) เฉพาะใน Container นี้
        container.addEventListener('click', (e) => {
            // ปิด Multi-Select
            if (!e.target.closest('.tif-multi-wrapper')) {
                const popups = container.querySelectorAll('.tif-multi-popup');
                popups.forEach(el => el.classList.remove('show'));
            }
            // ปิด Context Menu
            if (contextMenu) contextMenu.classList.remove('show');
            
            // Logic เปิด/ปิด Multi-Select (ย้ายมาไว้ตรงนี้เพื่อให้ Scope ถูกต้อง)
            const multiWrapper = e.target.closest('.tif-multi-wrapper');
            if (multiWrapper) {
                 // ปิดอันอื่นใน container นี้ก่อน
                 container.querySelectorAll('.tif-multi-popup').forEach(el => {
                    if(el !== multiWrapper.querySelector('.tif-multi-popup')) el.classList.remove('show');
                 });
                 const popup = multiWrapper.querySelector('.tif-multi-popup');
                 if(popup) popup.classList.toggle('show');
            }
        });

        // E. Preview Image (ใช้ Change Event Delegation)
        container.addEventListener('change', (e) => {
            if (e.target.classList.contains('tif-file-input')) {
                const input = e.target;
                if (input.files && input.files[0]) {
                    const wrapper = input.parentElement.parentElement;
                    const img = wrapper.querySelector('.tif-img-preview');
                    const reader = new FileReader();
                    reader.onload = (evt) => { img.src = evt.target.result; img.classList.add('show'); };
                    reader.readAsDataURL(input.files[0]);
                }
            }
            
            // Multi-Select Checkbox Change
            if (e.target.type === 'checkbox' && e.target.closest('.tif-multi-popup')) {
                 updateMultiDisplay(e.target);
            }
        });

    })();
        
        
    function tifResizer() {
        const resizer = document.getElementById('tifResizer${process.id}');
        const topPane = document.getElementById('tifTopPane${process.id}');
        const container = document.getElementById('tifContainer${process.id}');

        if (!resizer || !topPane || !container) return;

        // --- [จุดที่ 1] สร้าง Key สำหรับเก็บค่า (แยกตาม ID เพื่อไม่ให้ตีกัน) ---
        const storageKey = 'tif_height_' + '${process.id}';

        // --- [จุดที่ 2] โหลดค่าเดิมมาใส่ทันที (ถ้ามี) ---
        const savedHeight = localStorage.getItem(storageKey);
        if (savedHeight) {
            topPane.style.height = savedHeight + 'px';
        }

        let isResizing = false;

        const startResizing = () => {
            isResizing = true;
            document.body.style.cursor = 'row-resize';
            // ป้องกันการเลือก text ขณะลาก
            document.body.style.userSelect = 'none'; 
        };

        const stopResizing = () => {
            if (isResizing) {
                isResizing = false;
                document.body.style.cursor = 'default';
                document.body.style.userSelect = ''; // คืนค่าการเลือก text

                // --- [จุดที่ 3] บันทึกค่าลงเครื่อง เมื่อปล่อยเมาส์ ---
                // เก็บเฉพาะตัวเลข (ตัด px ออก) เพื่อความง่าย
                const currentHeight = parseInt(topPane.style.height || topPane.offsetHeight);
                localStorage.setItem(storageKey, currentHeight);
            }
        };

        const resize = (e) => {
            if (!isResizing) return;

            const rect = container.getBoundingClientRect();
            let h = e.clientY - rect.top;

            // จำกัดความสูง
            if (h < 50) h = 50;
            if (h > rect.height - 150) h = rect.height - 150;

            topPane.style.height = h + 'px';
        };

        // Event Listeners
        resizer.addEventListener('mousedown', startResizing);
        document.addEventListener('mousemove', resize);
        document.addEventListener('mouseup', stopResizing);

        // Cleanup function
        return () => {
            resizer.removeEventListener('mousedown', startResizing);
            document.removeEventListener('mousemove', resize);
            document.removeEventListener('mouseup', stopResizing);
        };
    }

    // เรียกใช้งาน
    tifResizer();
          
    function calculateRow(inputElement) {
        // 1. หาแถวปัจจุบัน
        var row = inputElement.closest('tr');
        
        // 2. ดึงค่า (ดึง Element มาก่อน)
        var priceInput = row.querySelector('.js-price');
        var qtyInput = row.querySelector('.js-qty');
        var discountInput = row.querySelector('.js-discount');

        // แปลงค่าเป็นตัวเลข 
        var price = priceInput ? parseFloat(priceInput.value) || 0 : 0;
        var discount = discountInput ? parseFloat(discountInput.value) || 0 : 0;
        
        // --- จุดที่แก้ไข ---
        // ถ้า qtyInput มีค่า (ไม่ว่าง) ให้ใช้ค่านั้น ถ้าว่างเปล่าให้ถือว่าเป็น 1 เพื่อให้ Price ยังคงอยู่
        var qtyValue = qtyInput ? qtyInput.value : "";
        var qty = (qtyValue === "" || isNaN(parseFloat(qtyValue))) ? 1 : parseFloat(qtyValue);

        // 3. คำนวณ (Price * Qty) - Discount
        // แม้ qty เป็น 1 แต่ถ้ามี Discount มันจะถูกลบออกจาก Price ทันที
        var total = (price * qty) - discount;
        
        // ป้องกันค่าติดลบ
        if (total < 0) total = 0;

        // 4. แสดงผล
        var totalInput = row.querySelector('.js-total');
        if (totalInput) {
            totalInput.value = total.toFixed(2);
        }
        
        calculateGrandTotal();
    }
         
         function calculateGrandTotal() {
        	    
        	    let sumGrossTotal = 0; // เก็บยอดรวมสินค้า (ก่อนส่วนลด)
        	    let sumDiscount = 0;   // เก็บส่วนลดรวม

        	    // 1. วนลูปหา Input ที่เป็น "ราคา" (.js-price) ทุกตัวในตาราง
        	    // เพื่อจะเอาไปคูณกับ จำนวน (.js-qty) ในแถวเดียวกัน
        	    document.querySelectorAll('.js-price').forEach(function(priceInput) {
        	        
        	        // หาแถวของตัวเอง
        	        let row = priceInput.closest('tr');
        	        if (!row) return;

        	        // ดึงค่า ราคา และ จำนวน
        	        let price = parseFloat(priceInput.value) || 0;
        	        let qtyInput = row.querySelector('.js-qty');
        	        let qty = qtyInput ? (parseFloat(qtyInput.value) || 0) : 0;

        	        // ดึงค่า ส่วนลด ของแถวนี้
        	        let discountInput = row.querySelector('.js-discount');
        	        let discount = discountInput ? (parseFloat(discountInput.value) || 0) : 0;

        	        // --- คำนวณสะสม ---
        	        
        	        // A. ยอดสินค้าก่อนลด (Gross) = ราคา x จำนวน
        	        sumGrossTotal += (price * qty);

        	        // B. ส่วนลดรวม
        	        sumDiscount += discount;
        	    });

        	    // 2. คำนวณยอดสุทธิหลังหักส่วนลด (เพื่อเอาไปคิด VAT)
        	    let amountAfterDiscount = sumGrossTotal - sumDiscount;
        	    if (amountAfterDiscount < 0) amountAfterDiscount = 0;

        	    // 3. คำนวณ VAT 7%
        	    let vatAmount = amountAfterDiscount * 0.07;

        	    // 4. คำนวณยอดสุทธิ (Net Amount)
        	    let netAmount = amountAfterDiscount + vatAmount;

        	    
        	    
        	    // --- แสดงผลลง Input ท้ายตาราง ---

        	    // Grand Total (ยอดรวมสินค้าแบบเต็มๆ)
        	    let elGrandTotal = document.getElementById('grand_totals');
        	    if(elGrandTotal) elGrandTotal.value = sumGrossTotal.toFixed(2);
        	    let elGrandTotal2 = document.getElementById('grand_total');
        	    if(elGrandTotal2) elGrandTotal2.value = sumGrossTotal.toFixed(2);
        	    
        	    
        	    let elGrandTotaled = document.getElementById('grand_totals2');
        	    if(elGrandTotaled) elGrandTotaled.value = amountAfterDiscount.toFixed(2);
        	    let elGrandTotaled2 = document.getElementById('grand_total2');
        	    if(elGrandTotaled2) elGrandTotaled2.value = amountAfterDiscount.toFixed(2);
        	    
        	    
        	    // Grand Discount (ส่วนลดรวม)
        	    let elGrandDiscount = document.getElementById('grand_discounts');
        	    if(elGrandDiscount) elGrandDiscount.value = sumDiscount.toFixed(2);
        	    
          	    let elGrandDiscount2 = document.getElementById('grand_discount');
        	    if(elGrandDiscount2) elGrandDiscount2.value = sumDiscount.toFixed(2);
        	    
        	    
        	    
        	

      
        	    
        	    

        	    // VAT
        	    let elVat = document.getElementById('vats');
        	    if(elVat) elVat.value = vatAmount.toFixed(2);

        	    let elVat2 = document.getElementById('vat');
        	    if(elVat2) elVat2.value = vatAmount.toFixed(2);
        	    
        	    // Net Amount
        	    let elNet = document.getElementById('net_amounts');
        	    if(elNet) elNet.value = netAmount.toFixed(2);
        	    
        	    let elNet2 = document.getElementById('net_amount');
        	    if(elNet2) elNet2.value = netAmount.toFixed(2);
        	    

        	}
        
       
         
         
         var app_title = document.getElementById('app${process.id}');
         if (app_title) { // กันเหนียว: เช็คว่าหาเจอไหม ก่อนสั่งงาน
             app_title.innerText = "${edatas.data_title}"; 
         }
</script>

 
<c:if test="${action_batch_insert != null 
    	 && (  fn:contains(process.subtable_viewmode,Appstep_no) 
    	 ||  fn:contains(process.subtable_editmode,Appstep_no) )
    	  }">   
     <script>
     calculateGrandTotal();
     

     // 🌟 ฟังก์ชันสำหรับผูก TomSelect กับ Dropdown ของ Product
     function initProductTomSelects() {
    	 

         // ค้นหาทุก Select ที่มีคลาส js-product-select ในหน้าจอ
         const selects = document.querySelectorAll('.js-product-select');
         
         selects.forEach(function(selectEl) {
             // ป้องกันการ Init ซ้ำซ้อน (ถ้าแถวไหนทำไปแล้ว ให้ข้าม)
             if (selectEl.tomselect) return;

             new TomSelect(selectEl, {
                 valueField: 'value',
                 labelField: 'text',
                 searchField: 'text',
                 create: false,
                 maxOptions: 50,
                 allowEmptyOption: true,
                 
                 // 🚀 โหลดข้อมูลผ่าน Ajax
                 load: function(query, callback) {
                     if (!query.length) return callback();

                     var formData = new FormData();
                     formData.append('search', query);
                     formData.append('type', 'Product'); 
                     
                     var targetUrl = '/indexappsearchajax-filemanage?${_csrf.parameterName}=${_csrf.token}';

                     fetch(targetUrl, {
                         method: 'POST',
                         body: formData
                     })
                     .then(response => response.json())
                     .then(json => {
                         if(!json) json = [];
                         
                         var formattedJson = json.map(function(item) {
                             var optValue = item.optVal2.trim();
                             return { value: optValue, text: optValue };
                         });
                         
                         callback(formattedJson);
                     }).catch(()=>{
                         callback();
                     });
                 }
             });
         });
     }

   
   

     </script>
</c:if>

     <script>
     
     
     
     
     
  
     
     
     
     
     /**
      * ระบบแสดงผลหน้าจอ Loading
      */
      var Loader2 = {
     		    // ใส่ชื่อ ID กล่องที่ต้องการให้มันไปโผล่
     		    show: function(containerId, message = "กำลังโหลด...") {
     		        const container = document.getElementById(containerId);
     		        if (!container) {
     		            console.warn("หาเป้าหมายไม่เจอ:", containerId);
     		            return;
     		        }

     		        // *** บังคับให้กล่องแม่เป็น relative ไม่งั้น overlay จะทะลุออกไปข้างนอก ***
     		        if (window.getComputedStyle(container).position === 'static') {
     		            container.style.position = 'relative';
     		        }

     		        // เช็คว่าในกล่องนี้มี overlay สร้างไว้หรือยัง
     		        let overlay = container.querySelector('.loading-overlay');
     		        
     		        if (!overlay) {
     		            // ถ้ายังไม่มี สร้างใหม่แล้วยัดลงไป
     		            overlay = document.createElement('div');
     		            overlay.className = 'loading-overlay';
     		            overlay.innerHTML = `
     		                <div class="loader-spinner"></div>
     		                <div class="loading-text">${message}</div>
     		            `;
     		            container.appendChild(overlay);
     		        } else {
     		            // ถ้ามีแล้ว แค่เปิดให้แสดงแล้วเปลี่ยนข้อความ
     		            overlay.querySelector('.loading-text').innerText = message;
     		            overlay.style.display = 'flex';
     		        }
     		    },

     		    // สั่งปิด โดยอ้างอิงจาก ID กล่องเดิม
     		    hide: function(containerId) {
     		        const container = document.getElementById(containerId);
     		        if (!container) return;

     		        const overlay = container.querySelector('.loading-overlay');
     		        if (overlay) {
     		            // ปิดการแสดงผล
     		            overlay.style.display = 'none'; 
     		            
     		            // หรือถ้าอยากลบทิ้งไปเลยเพื่อคืนหน่วยความจำ ให้ใช้:
     		            // overlay.remove();
     		        }
     		    }
     		};
     
     
     
      function checkAndOpenFlowDoc(dataId, processId) {
    	    // 1. ค้นหา Input ที่เก็บค่า segmentation_data
    	    var segInput = document.getElementById('segmentation_data');
    	    var evidence_type = '${process.closing_evidence_type}';
    	    // 2. ถ้ามี Input นี้อยู่ในหน้าจอ ให้ทำการตรวจสอบค่า
    	    if (segInput) {
    	        var segValue = segInput.value.trim();
    	        
    	        // ถ้าค่าว่าง, ไม่มีค่า, หรือเท่ากับ "0" ให้เตือนแล้วหยุดทำงาน
    	        if (!segValue || segValue === "" || segValue === "0") {
    	            
    	            // แจ้งเตือน (สามารถเปลี่ยนเป็น SweetAlert ได้ถ้าโปรเจกต์คุณใช้งานอยู่)
    	            alert("⚠️ กรุณาระบุข้อมูลกลุ่มลูกค้าก่อน หรือให้รีเฟรชถ้าระบุแล้ว");
    	            
    	            /* ถ้าใช้ SweetAlert (Swal.fire) แนะนำให้ใช้โค้ดด้านล่างนี้แทน alert()
    	            Swal.fire({
    	                icon: 'warning',
    	                title: 'ข้อมูลไม่ครบถ้วน',
    	                text: 'กรุณาระบุข้อมูลกลุ่มลูกค้าก่อน หรือให้รีเฟรชหน้าจอกด F5 ถ้าระบุแล้ว'
    	            });
    	            */
    	           
    	            return; // 🔴 คำสั่งนี้สำคัญมาก! คือการเตะออก ไม่ให้ทำบรรทัดด้านล่างต่อ
    	        }
    	    }

    	    // 3. ถ้าเงื่อนไขผ่านหมด (มีข้อมูล) ให้รันฟังก์ชันเปิดหน้าต่างตามปกติของคุณ
    	    openwindow('draggable-window6', 'Link', '9500');
    	    getData('/flowdoc-filemanager/' + dataId + '/' + processId, 'mainbody6');
    	    set_title_app('miniwindows', 'การส่งเอกสาร');
    	}
      
  
     </script>
<script>
      var currentUserId = "${Member_id}"; // รหัสพนักงานที่กำลังล็อกอิน
      var currentCustomerId = "${regCode}"; // รหัสลูกค้าที่กำลังเปิดดูข้อมูล
      var currentSessionId = "${Member_id}_${regCode}"; // ผลลัพธ์: "EMP99_6656"
      var currentEdataId = "${edatas.data_id}"; // ดึงค่านี้มาจากหน้าเว็บ (เช่น จาก Hidden Input หรือ URL)    
      loadChatHistory(currentSessionId);      
            
              </script>


</body>
</html>

