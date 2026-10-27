<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
   

    <style>
    

    
/* บังคับให้ไอคอนในแถบเครื่องมือแสดงผลชัด 100% เสมอ */
#pbi-v3-wrapper .tool-btn svg {
    opacity: 1 !important;
    fill-opacity: 1 !important;
    stroke: none; /* ป้องกันเส้นขอบแปลกปลอม */
}

/* ถ้าอยากให้ปุ่มมี Effect เวลาเอาเมาส์ชี้ (เหมือน Power BI จริง) */
#pbi-v3-wrapper .tool-btn:hover {
    background-color: #e6e6e6; /* สีเทาอ่อนๆ เมื่อ Hover */
    border-radius: 3px;
}
        #pbi-v3-wrapper {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
            color: #201f1e;
            background-color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
   
            box-sizing: border-box;
            user-select: none;
               height: 65%;
        }

        #pbi-v3-wrapper * { box-sizing: border-box; }

        /* Window Frame */
        .pbi-window {
            width:100%;
            height: 750px;
            background-color: #f3f2f1;
            display: flex;
            flex-direction: column;
            box-shadow: 0 0 20px rgba(0,0,0,0.5);
            border: 1px solid #444;
             
        }
    .window.fullscreen .pbi-window{
        height: 90vh !important;
        min-height: 90vh !important; /* กันเหนียว */
    }
        /* --- 1. TITLE BAR --- */
        #pbi-v3-wrapper .title-bar {
            height: 32px;
            background-color: #f3f2f1;
            display: flex;
            align-items: center;
            padding: 0 10px;
            justify-content: space-between;
        }
        
        #pbi-v3-wrapper .quick-access {
            display: flex; gap: 15px; color: #666; font-size: 14px;
        }
        
        #pbi-v3-wrapper .title-text {
            font-size: 12px; color: #444; margin-right: 20px;
        }

        #pbi-v3-wrapper .search-box {
            background-color: white;
            border: 1px solid #d1d1d1;
            border-radius: 2px;
            width: 400px;
            height: 24px;
            display: flex;
            align-items: center;
            padding: 0 5px;
            color: #666;
            position: absolute;
            left: 50%;
            transform: translateX(-50%);
        }
        
        #pbi-v3-wrapper .user-info {
            display: flex; align-items: center; gap: 10px; font-size: 11px;
        }
        #pbi-v3-wrapper .user-name { font-weight: 400; color: #333; }
        #pbi-v3-wrapper .window-controls span {
            width: 30px; text-align: center; cursor: pointer; font-size: 14px;
        }
        #pbi-v3-wrapper .window-controls span:hover { background: #e1dfdd; }
        #pbi-v3-wrapper .window-controls span.close:hover { background: #e81123; color: white; }

        /* --- 2. RIBBON MENU --- */
        #pbi-v3-wrapper .ribbons-container {
            background-color: #f3f2f1;
            border-bottom: 1px solid #d2d0ce;
        }

        /* Tabs */
        #pbi-v3-wrapper .ribbons-reporttabs {
            display: flex; padding-left: 5px;
        }
        #pbi-v3-wrapper .reporttab {
            padding: 5px 12px; cursor: pointer; color: #444; font-size: 12px; position: relative;
        }
        #pbi-v3-wrapper .reporttab:hover { background-color: #edebe9; }
        #pbi-v3-wrapper .reporttab.active {
            font-weight: 600; color: #000;
            border-bottom: 3px solid #f2c811; /* PowerBI Yellow */
            background-color: #f3f2f1;
        }
        #pbi-v3-wrapper .reporttab.file-reporttab {
            background-color: #f3f2f1; color: #000;
        }

        /* Toolbar Icons Area */
        #pbi-v3-wrapper .ribbons-tools {
            height: 90px;
            background-color: #fcfcfc; /* White ribbons bg */
            display: flex;
            padding: 10px 10px;
            align-items: flex-start;
            	
        }

        
        /* Ribbon Group */
        #pbi-v3-wrapper .ribbons-group {
            display: flex; gap: 4px; height: 100%; 
            border-right: 1px solid #e1dfdd; 
            padding-right: 8px; margin-right: 8px;
            align-items: flex-start;
        }
        #pbi-v3-wrapper .ribbons-group:last-child { border: none; }

        /* Ribbon Button */
        #pbi-v3-wrapper .ribbons-btns {
            display: flex; flex-direction: column; align-items: center; justify-content: flex-start;
            text-align: center; min-width: 45px; cursor: pointer; padding: 2px;
            border: 1px solid transparent;
        }
        #pbi-v3-wrapper .ribbons-btns:hover { background-color: #eff6fc; border-color: #c7e0f4; }
        
        #pbi-v3-wrapper .ribbons-btns svg { 
        width: 32px; height: 32px; margin-bottom: 2px; 
        opacity: 1 !important;
            fill-opacity: 1 !important;
            stroke: none;
            
        }
		    #pbi-v3-wrapper .ribbons-btns      svg {
		    width: 32px;
		    height: 32px;
		    margin-bottom: 2px;
		    z-index: 55;
		    /* บรรทัดล่างนี้สำคัญ: ให้แสดงผลชัดสุด */
		    shape-rendering: geometricPrecision; 
		}
		
		/* 2. บังคับเปลี่ยนสี "ไส้ใน" (Path) ให้เป็นสีดำเข้ม */
		 #pbi-v3-wrapper svg path, svg rect, svg circle {
		    fill: #666; !important; /* เปลี่ยนจากสีเทา เป็นสีดำเข้ม (เกือบดำสนิท) */
		    fill-opacity: 1 !important;
		    stroke: none;
		}
		
		.view-item  svg path, svg rect, svg circle {
		       fill: #666; !important; /* เปลี่ยนจากสีเทา เป็นสีดำเข้ม (เกือบดำสนิท) */
		    fill-opacity: 1 !important;
		    stroke: none;
		}     
        
        
        #pbi-v3-wrapper .btn-label { font-size: 11px; line-height: 1.1; color: #201f1e; }
        #pbi-v3-wrapper .btn-sub-arrow { font-size: 8px; margin-left: 2px; }

        /* Small button stack */
        #pbi-v3-wrapper .small-btn-stack {
            display: flex; flex-direction: column; gap: 2px;
        }
        #pbi-v3-wrapper .ribbons-btns-sm {
            display: flex; align-items: center; gap: 5px; padding: 2px 5px; cursor: pointer;
            border: 1px solid transparent;
        }
        #pbi-v3-wrapper .ribbons-btns-sm:hover { background-color: #eff6fc; border-color: #c7e0f4; }
        #pbi-v3-wrapper .ribbons-btns-sm svg { width: 16px; height: 16px; margin: 0; }

        /* Group Label at bottom */
        #pbi-v3-wrapper .group-label-container {
            position: relative; height: 100%; display: flex; flex-direction: column;
        }
        #pbi-v3-wrapper .group-names {
            position: absolute; top: 70px; width: 100%; text-align: center; 
            font-size: 10px; color: #888;
        }


        /* --- 3. MAIN AREA --- */
        #pbi-v3-wrapper .main-area {
            flex: 1; display: flex; overflow: hidden;height: 100%;
        }

        /* Left Sidebar (Views) */
        #pbi-v3-wrapper .view-bar {
            width: 48px; background-color: #f3f2f1; border-right: 1px solid #d2d0ce;
            display: flex; flex-direction: column; align-items: center; padding-top: 10px; gap: 10px;
            flex-shrink: 0; /* ไม่ให้หด */
        }
        #pbi-v3-wrapper .view-item {
            width: 32px; height: 32px; display: flex; justify-content: center; align-items: center; cursor: pointer;
        }
        #pbi-v3-wrapper .view-item.active {
            background-color: #e1dfdd; border-left: 3px solid #f2c811;
        }
        #pbi-v3-wrapper .view-item svg { width: 20px; height: 20px; fill: #605e5c; }
        #pbi-v3-wrapper .view-item.active svg { fill: #000; }

        /* --- Canvas Viewport (ส่วนที่แก้ไข) --- */
        #pbi-v3-wrapper .canvas-viewport {
            flex: 1; 
            background-color: #eaeaea; 
            padding: 20px; 
            overflow: hidden; /* เปลี่ยนจาก hidden เป็น auto เพื่อให้มี scrollbar แทนการบีบ */
            display: flex;
            align-items: flex-start; /* จัดชิดซ้ายบน */
            justify-content: flex-start;
        }
        
        #pbi-v3-wrapper .report-sheet {
            background: white; 
            /* กำหนดขนาดที่แน่นอนหรือขั้นต่ำ เพื่อไม่ให้ flex บีบจนเพี้ยน */
            width: 100%; 
            min-width: 900px;  /* <--- สำคัญ: กำหนดความกว้างขั้นต่ำของรายงาน */
            height: 100%; 
            min-height: 500px; /* <--- สำคัญ: กำหนดความสูงขั้นต่ำ */
            box-shadow: 0 0 4px rgba(0,0,0,0.2);
            padding: 15px; 
            position: relative;
            flex-shrink: 0; /* <--- สำคัญ: สั่งห้ามหดตัวเด็ดขาด */
        }

        /* Right Panes */
        #pbi-v3-wrapper .panes-area {
            display: flex; border-left: 1px solid #d2d0ce; background: white;
            flex-shrink: 0; /* ไม่ให้ Panes หด */
        }
        
        /* Filters Pane */
        #pbi-v3-wrapper .pane-filters {
            width: 35px; border-right: 1px solid #e1dfdd; background: #f3f2f1; 
            display: flex; align-items: center; flex-direction: column; padding-top: 10px; cursor: pointer;
        }
        
        /* Visualizations Pane */
        #pbi-v3-wrapper .pane-viz {
            width: 220px; border-right: 1px solid #e1dfdd; display: flex; flex-direction: column;
        }
        #pbi-v3-wrapper .pane-header {
            padding: 8px 10px; font-weight: 600; text-transform: uppercase; font-size: 11px;
            border-bottom: 1px solid #edebe9; display: flex; justify-content: space-between;
        }
        
        #pbi-v3-wrapper .viz-grid {
            display: grid; grid-template-columns: repeat(6, 1fr); padding: 10px; gap: 5px;
        }
        #pbi-v3-wrapper .viz-btn {
            width: 24px; height: 24px; background: #f3f2f1; display: flex; justify-content: center; align-items: center; cursor: pointer;
        }
        #pbi-v3-wrapper .viz-btn:hover { background: #e1dfdd; }
        #pbi-v3-wrapper .viz-btn svg { width: 16px; height: 16px; fill: #666; }
        
        /* Fields Pane */
        #pbi-v3-wrapper .pane-fields {
            width: 240px; display: flex; flex-direction: column;
        }
        #pbi-v3-wrapper .search-field {
            padding: 5px 10px; border-bottom: 1px solid #edebe9;
        }
        #pbi-v3-wrapper .search-field input {
            width: 100%; border: 1px solid #666; padding: 4px; outline: 1px solid transparent;
        }
        #pbi-v3-wrapper .search-field input:focus { border-color: #0078d4; }

        #pbi-v3-wrapper .field-list {
            flex: 1; overflow-y: auto; padding: 5px;
        }
        #pbi-v3-wrapper .reporttable-item { font-weight: 600; margin-top: 5px; cursor: pointer; display: flex; align-items: center; }
        #pbi-v3-wrapper .col-item {
            margin-left: 15px; padding: 2px 0; display: flex; align-items: center; cursor: pointer;
        }
        #pbi-v3-wrapper .col-item:hover { background-color: #f3f2f1; }
        #pbi-v3-wrapper .hl-yellow { background-color: #fff100; color: black; }

        /* Footer */
        #pbi-v3-wrapper .footer-bar {
            height: 24px; background-color: #f3f2f1; border-top: 1px solid #d2d0ce;
            display: flex; align-items: center;
        }
        #pbi-v3-wrapper .page-reporttab {
            padding: 0 15px; height: 100%; display: flex; align-items: center; cursor: pointer; font-size: 11px;
        }
        #pbi-v3-wrapper .page-reporttab.active { background-color: #f2c811; font-weight: 600; }

        /* SVG Styles */
        #pbi-v3-wrapper .icon-green { fill: #217346; }
        #pbi-v3-wrapper .icon-blue { fill: #0078d4; }
        #pbi-v3-wrapper .icon-yellow { fill: #f2c811; }
        #pbi-v3-wrapper .icon-grey { fill: #666; }
        
        /* CSS ภายใน Report Sheet เพื่อให้แสดงผลสวยงามเมื่อมีพื้นที่จำกัด */
        #pbi-v3-wrapper .kpi-container {
            display: flex; gap: 10px; flex-wrap: wrap; /* ยอมให้ปัดบรรทัดได้ถ้าจำเป็น */
        }
        #pbi-v3-wrapper .kpi-card {
            flex: 1; min-width: 120px; background: #f9f9f9; padding: 10px; text-align: center; border: 1px solid #eee;
        }
        #pbi-v3-wrapper .kpi-val { font-size: 16px; font-weight: bold; color: #333; }
        #pbi-v3-wrapper .kpi-lbl { font-size: 11px; color: #666; margin-top: 5px; }

        #pbi-v3-wrapper .charts-mid {
            display: flex; gap: 10px;
        }
        #pbi-v3-wrapper .chart-panel {
            flex: 1; border: 1px solid #eee; padding: 5px;
        }
        #pbi-v3-wrapper .chart-header { font-weight: 600; font-size: 11px; margin-bottom: 5px; color: #444; }
        
        #pbi-v3-wrapper reporttable { width: 100%; border-collapse: collapse; font-size: 11px; }
        #pbi-v3-wrapper reporttable th { text-align: left; border-bottom: 1px solid #ccc; padding: 3px; background: #f3f2f1; }
        #pbi-v3-wrapper reporttable td { border-bottom: 1px solid #eee; padding: 3px; }
        
       /* คลาสสำหรับปิดการใช้งาน */
.ribbons-btns.disabled {
    opacity: 0.4;              /* ปรับความจาง (0.0 - 1.0) */
    pointer-events: none;      /* สำคัญ: ป้องกันการคลิกและการ Hover */
    cursor: not-allowed;       /* เปลี่ยนรูปเมาส์เป็นวงกลมขีดฆ่า (เผื่อกรณี browser ไม่รองรับ pointer-events) */
    filter: grayscale(100%);   /* (ทางเลือก) ทำให้ icon สีสดกลายเป็นขาวดำ */
}

/* 2. ปลดล็อกข้อความ */
.report-sheet, 
.report-sheet *, 
#table-container, 
#table-container2,
table {
    user-select: text !important;
    -webkit-user-select: text !important;
    -moz-user-select: text !important;
    -ms-user-select: text !important;
    cursor: auto !important; 
}

/* 3. จัดการขอบตาราง (ใช้ separate ถูกต้องแล้วครับ! collapse มักจะบั๊กกับ sticky) */
.my-tables {
    border-collapse: separate; 
    border-spacing: 0;         
    width: 100%; /* แนะนำให้ใส่ 100% เผื่อตารางไม่กางเต็ม */
}

/* 4. หัวตาราง Sticky (แก้จุดที่พิมพ์ผิดแล้ว) */
.my-tables thead th {
    position: sticky;
    top: 0;
    z-index: 10;                
    background: #f4f4f4;
    border: 1px solid #ccc; 
    white-space: nowrap;        
    padding: 12px 8px;          
    color: #666;
    font-size: 13px;
    font-weight: bold; /* หัวตารางปรับเป็น bold นิดนึงจะสวยครับ (หรือ normal ตามเดิมก็ได้) */
}



/* ป้องกันไม่ให้ส่วนที่เป็น UI อื่นๆ เช่นปุ่ม หรือ Tab โดนลากคลุม (คงไว้ตามสไตล์แอป) */
.ribbons-container, .view-bar, .panes-area, .title-bar {
    user-select: none !important;
}
                        .disabled-click {
    pointer-events: none; /* ห้ามคลิก */
    opacity: 0.5;         /* ทำให้จางลง */
    cursor: not-allowed;
}



/* =========================================
   คอนเทนเนอร์หลัก และ โซนมาตรฐาน
   ========================================= */
#table-containers3 {
    display: flex;
    flex-direction: column;
    height: 100%; /* สูงเกือบเต็มจอ */
    gap: 15px;
    padding: 15px;
}
.zone {
    display: flex;
    flex-wrap: nowrap;
    gap: 10px;
    /* transition ช่วยให้ตอนสลับ Layout มันค่อยๆ ยืดหด สวยงาม */
    transition: height 0.3s ease; 
}

/* 🌟 ซ่อนโซน 3 ไว้เป็นค่าเริ่มต้น (จะโชว์ก็ต่อเมื่อใช้ Layout 4) */
.zone-3 { display: none; }

/* =========================================
   ระบบ Layout ทั้ง 4 แบบ
   ========================================= */
/* แบบที่ 1: 20:80 (บนเล็ก ล่างใหญ่) */
.layout-type-1 .zone-1 { height: 10%; }
.layout-type-1 .zone-2 { height: 90%; }

/* แบบที่ 2: 50:50 (ครึ่งๆ) */
.layout-type-2 .zone-1 { height: 50%; }
.layout-type-2 .zone-2 { height: 50%; }

/* แบบที่ 3: 30:70 (ตามที่พี่ขอ) */
.layout-type-3 .zone-1 { height: 30%; }
.layout-type-3 .zone-2 { height: 70%; }

/* แบบที่ 4: 3 แถวเท่ากัน (33:33:33) */
.layout-type-4 .zone-1,
.layout-type-4 .zone-2,
.layout-type-4 .zone-3 {
    height: 33.33%;
    display: flex; /* 🌟 เปิดให้โซน 3 โผล่มาทำงาน */
}

/* =========================================
   ตัวกล่องเปล่า (ลบขอบ Header ออกให้คลีนที่สุด)
   ========================================= */
.dash-item {
    background: white;
    border: 1px solid #e1e4e8;
    border-radius: 4px;
    flex: 1; /* หารความกว้างกันเอง */
    display: flex;
    align-items: center;
    justify-content: center;
    min-width: 100px;
    cursor: pointer;
    transition: all 0.2s ease;
}

.dash-item.selected {
    border: 2px solid #0078d4;
    box-shadow: 0 0 10px rgba(0, 120, 212, 0.3);
}

.dash-item:hover {
    background: #f8f9fa;
}  

        /* CSS Scoped ภายใต้ #pg-container เพื่อป้องกัน Style ชนกัน */
        #pg-container {
            width: 320px;
            background-color: #f0f0f0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            font-size: 12px;
            border: 1px solid #a0a0a0;
            color: #000;
            user-select: none;
        }

        /* Header Section */
        #pg-container .pg-header {
            background: linear-gradient(to bottom, #ffffff, #e2e2e2);
            padding: 4px 6px;
            border-bottom: 1px solid #ccc;
        }

        #pg-container .pg-title {
            font-weight: bold;
            margin-bottom: 4px;
        }

        #pg-container .pg-toolbar {
            display: flex;
            gap: 2px;
            margin-bottom: 4px;
        }

        #pg-container .pg-search-box {
            display: flex;
            background: white;
            border: 1px solid #abadb3;
            padding: 1px;
        }

        #pg-container .pg-search-box input {
            border: none;
            width: 100%;
            outline: none;
            padding: 2px;
            font-size: 12px;
        }

        /* Section Categories */
        #pg-container .pg-category {
            border-bottom: 1px solid #d0d0d0;
        }

        #pg-container .pg-cat-header {
            background-color: #f0f0f0;
            padding: 4px 4px;
            cursor: pointer;
            font-weight: bold;
            display: flex;
            align-items: center;
            border-bottom: 1px solid #e0e0e0;
        }

        #pg-container .pg-cat-header:hover {
            background-color: #e5eefb;
        }

        #pg-container .pg-arrow {
            width: 0; 
            height: 0; 
            border-left: 4px solid transparent;
            border-right: 4px solid transparent;
            border-top: 5px solid #2e4d7b;
            margin-right: 6px;
            transform: rotate(-90deg);
            transition: transform 0.1s;
        }

        #pg-container .pg-category.open .pg-arrow {
            transform: rotate(0deg);
        }

        /* Property Rows */
        #pg-container .pg-cat-content {
            display: none;
            background: white;
        }

        #pg-container .pg-category.open .pg-cat-content {
            display: block;
        }

        #pg-container .pg-row {
            display: flex;
            border-bottom: 1px solid #f0f0f0;
            min-height: 22px;
            align-items: stretch;
        }

        #pg-container .pg-row:hover {
            background-color: #f5faff;
        }

        #pg-container .pg-label {
            width: 40%;
            padding: 3px 5px;
            border-right: 1px dotted #ccc;
            display: flex;
            align-items: center;
            white-space: nowrap;
            overflow: hidden;
        }

        #pg-container .pg-value {
            width: 60%;
            padding: 0;
            display: flex;
            align-items: center;
            position: relative;
        }

        /* Input Styles */
        #pg-container input[type="text"], 
          #pg-container input[type="number"], 
        #pg-container select {
            width: 100%;
            border: none;
            padding: 3px 5px;
            font-family: inherit;
            font-size: 12px;
            outline: none;
            background: transparent;
        }

        #pg-container .pg-icon-marker {
            width: 12px;
            height: 12px;
            border: 1px solid #ccc;
            margin-right: 4px;
            flex-shrink: 0;
        }

        #pg-container .selected {
            background-color: #3399ff !important;
            color: white !important;
        }

        #pg-container .selected .pg-label { border-right: 1px solid #3399ff; }

        /* Scrollbar */
        #pg-container .pg-body {
            max-height: 600px;
            overflow-y: auto;
        }
    </style>

<style>
    /* =========================================
       Toolbar Styles (Database UI Look)
       ========================================= */
    .db-toolbar {
        display: flex;
        align-items: center;
        background: #f0f0f0; /* สีเทาเรียบๆ เหมือน Window ปกติ */
        border-bottom: 1px solid #b5b5b5;
        padding: 4px 8px;
        gap: 6px;
        min-height: 36px; /* ขยายแถบ Toolbar ให้สูงขึ้น */
    }
    .db-toolbar-group {
        display: flex;
        align-items: center;
        gap: 4px;
    }
    .db-separator {
        width: 1px;
        height: 20px;
        background-color: #a0a0a0;
        border-right: 1px solid #ffffff;
        margin: 0 6px;
    }
    .db-btn {
        background: transparent;
        border: 1px solid transparent;
        border-radius: 3px;
        padding: 4px 6px;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        outline: none;
        transition: 0.1s;
    }
    .db-btn:hover {
        background: #e5f1fb;
        border: 1px solid #a5c7eb;
    }
    .db-btn:active {
        background: #cce4f7;
    }
    .db-btn svg {
        width: 18px; /* ขยายไอคอนให้ใหญ่ขึ้น */
        height: 18px;
        fill: #444;
    }

    /* =========================================
       Data Grid Styles (ใหญ่ขึ้น / สีเหมือนรูปตัวอย่าง)
       ========================================= */
    .db-grid-container {
        flex: 1;
        overflow: auto;
        background: #fff;
    }
    .db-grid-table {
        width: 100%;
        border-collapse: collapse;
        font-family: 'Segoe UI', Tahoma, Arial, sans-serif;
        font-size: 14px; /* 🌟 ขยายขนาดตัวอักษรให้อ่านง่าย */
        table-layout: fixed;
    }
    .db-grid-table th, 
    .db-grid-table td {
        border: 1px solid #d0d0d0; /* เส้น Grid สีเทากลางๆ */
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        height: 32px; /* 🌟 ขยายความสูงของแถว (Row Height) ให้ใหญ่ขึ้น */
        padding: 0;
    }
    
    /* สไตล์หัวตาราง */
    .db-grid-table th {
        background: #f5f5f5; /* สีเทาอ่อนๆ เหมือน Header ของตารางในรูป */
        color: #333;
        font-weight: normal; /* หัวตารางของ DB ส่วนใหญ่จะไม่หนามาก */
        padding: 4px 8px;
        text-align: left;
        position: sticky;
        top: 0;
        z-index: 10;
        border-bottom: 1px solid #999;
    }

    /* คอลัมน์ซ้ายสุด (Row Indicator) */
    .db-grid-table th.row-header,
    .db-grid-table td.row-header {
        background: #f0f0f0;
        width: 35px; /* กว้างขึ้น */
        text-align: center;
        color: #000;
        font-size: 12px;
        user-select: none;
        cursor: default;
        padding:2px;
        border-right: 1px solid #b5b5b5;
    }
    
    /* สลับสีแถว ขาว สลับ ฟ้าเทา (ให้เหมือนในรูปตัวอย่าง) */
    .db-grid-table tr:nth-child(even) td:not(.row-header) {
        background-color: #f2f6fc; /* สีฟ้าอมเทาอ่อนๆ */
    }
    .db-grid-table tr:nth-child(odd) td:not(.row-header) {
        background-color: #ffffff;
    }

    /* Hover ที่แถว -> ขึ้นลูกศร ▶ ที่คอลัมน์แรกเหมือนเลือกบรรทัดนั้นอยู่ */
    .db-grid-table tr:hover td.row-header::before {
        content: "▶";
        font-size: 10px;
    }

    /* 🌟 สไตล์ช่องกรอกข้อมูล (Input) */
    .db-cell-input {
        width: 100%;
        height: 100%;
        border: none;
        background: transparent;
        font-family: inherit;
        font-size: 14px; /* ขยายตามตาราง */
        padding: 0 8px;
        outline: none;
        color: #000;
    }
    
    /* เวลาคลิกช่องไหน ให้ช่องนั้นเป็นสีขาวและมีขอบ */
    .db-cell-input:focus {
        background: #fff;
        box-shadow: inset 0 0 0 1px #0078d7; /* ขอบน้ำเงินบางๆ เหมือนกำลัง Edit */
    }

    /* ไฮไลท์ข้อมูล Pareto (ยังคงไว้เผื่อกดวิเคราะห์ 80/20) */
    .db-grid-table tr.pareto-highlight td:not(.row-header) {
        background-color: #fdf2f8 !important; /* ชมพูอ่อนมากๆ */
    }
</style>
<div id="pbi-v3-wrapper">

  <div class="main-area">
                
                <div class="view-bar">
                    <div id="tabdatas-3"  class="view-item active" title="Report" onclick="switchtabActiveAndData('datas','3','3')">
                        <svg viewBox="0 0 24 24"><path d="M5 19h14v2H5zM5 9h4v8H5zm5-4h4v12h-4zm5 4h4v8h-4z"/></svg>
                    </div>
                    <div id="tabdatas-1"  class="view-item " title="Data" onclick="switchtabActiveAndData('datas','3','1')">
                        <svg viewBox="0 0 24 24"><path d="M4 4h16v16H4V4zm2 2v3h12V6H6zm0 5v3h12v-3H6zm0 5v3h12v-3H6z"/></svg>
                    </div>
               <div  id="tabdatas-2"  class="view-item" title="Sub Data" onclick="switchtabActiveAndData('datas','3','2')">
                        <svg viewBox="0 0 24 24"><path d="M4 4h16v16H4V4zm2 2v3h12V6H6zm0 5v3h12v-3H6zm0 5v3h12v-3H6z"/></svg>
                    </div>
			           <div class="view-item" onclick="getData('/editAnalytics-filemanager/${reports.id}','analytics-main-content');">
			    <svg viewBox="0 0 24 24">
			        <path d="M19.43 12.98c.04-.32.07-.64.07-.98s-.03-.66-.07-.98l2.11-1.65c.19-.15.24-.42.12-.64l-2-3.46c-.12-.22-.39-.3-.61-.22l-2.49 1c-.52-.4-1.08-.73-1.69-.98l-.38-2.65C14.46 2.18 14.25 2 14 2h-4c-.25 0-.46.18-.49.42l-.38 2.65c-.61.25-1.17.59-1.69.98l-2.49-1c-.23-.09-.49 0-.61.22l-2 3.46c-.13.22-.07.49.12.64l2.11 1.65c-.04.32-.07.65-.07.98s.03.66.07.98l-2.11 1.65c-.19.15-.24.42-.12.64l2 3.46c.12.22.39.3.61.22l2.49-1c.52.4 1.08.73 1.69.98l.38 2.65c.03.24.24.42.49.42h4c.25 0 .46-.18.49-.42l.38-2.65c.61-.25 1.17-.59 1.69-.98l2.49 1c.23.09.49 0 .61-.22l2-3.46c.12-.22.07-.49-.12-.64l-2.11-1.65zM12 15.5c-1.93 0-3.5-1.57-3.5-3.5s1.57-3.5 3.5-3.5 3.5 1.57 3.5 3.5-1.57 3.5-3.5 3.5z"/>
			    </svg>
			</div>
             
 	   <div    class="view-item" title="Sub Data" onclick="viewer_clearFilter()">
                  <svg viewBox="0 0 24 24" fill="currentColor">
        <path d="M19.36 2.72l1.42 1.42-5.72 5.71c1.07 1.54 1.22 3.39.32 4.59l-6.32-6.32c1.2-1.2 3.05-1.05 4.59.32l5.71-5.72zm-13.43 14.85c-2.01-2.01-3.24-4.41-3.58-6.65l4.88-2.09 7.44 7.44-2.09 4.88c-2.24-.34-4.64-1.57-6.65-3.58z"/>
    </svg>  </div>
             
                </div>

                <div class="canvas-viewport">
               
                    <div class="report-sheet" id="showdatas-1" style="display:none">
                    
                    
                   
                     <div id="table-containers"  style="max-height: 100%;width:100%;overflow:auto;"></div>
                           

                    </div>
                         <div class="report-sheet" id="showdatas-2" style="overflow:auto;display:none">
                    
                   
                     <div id="table-containers2" style="max-height: 100%;width:100%;overflow:auto"></div>
                       

                    </div>
                        <div class="report-sheet" id="showdatas-3" style="overflow:auto;">
                          
						<div id="table-containers3" class="layout-type-1">
						    <div class="zone zone-1" id="zone-1"></div>
						    <div class="zone zone-2" id="zone-2"></div>
						       <div class="zone zone-3" id="zone-3"></div>
						</div>
                       

                    </div>
                    
                </div>

            

            </div>
            

             
                      </div>
                      <div id="actionplan-container" style="padding: 2px; display: flex; flex-direction: column; gap: 16px;">
          <%@ include file="allSpikeDogActionplan.jsp" %>   
</div>
 <script>
 csrfToken_use = '${_csrf.parameterName}=${_csrf.token}';
var url = "${reports.reportUrl}";
var formattedURL = url.replace("Idoc:/", "/document-filemanager/");
viewer_loadDashboardFromUrl(formattedURL, "${reports.id}");


setTimeout(() => {
    viewer_autoSelectFirstChart();
}, 500); 

</script>                     
           
           
           
 <script>
    // 1. รับค่าจาก Spring ModelMap มาสร้างเป็น JavaScript Array (ของคุณ)
    var reportsData2 = [
        <c:forEach items="${relatedReports}" var="report" varStatus="status">
        {
            id: "${report.id}",
            reportName: "${report.reportName}",
            category: "${report.category}",
            description: "${report.description}",
            iconType: "${report.iconType}"
        }${!status.last ? ',' : ''}
        </c:forEach>
    ];

  

   renderRelatedReportsSidebar('${reports.category}', '${reports.id}');
   var analyticsBtn = document.getElementById("newAnalytics");
   if (analyticsBtn) {
       analyticsBtn.style.display = "none"; // ซ่อนปุ่ม/กล่อง
   }
</script>                     