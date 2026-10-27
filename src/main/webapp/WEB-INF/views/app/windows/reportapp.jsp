<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Power BI Desktop Replica</title>
    
        <script src="<c:url value="/app/js/report_board.js" />"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
            flex: 1; display: flex; overflow: hidden;
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
#table-container3 {
    display: flex;
    flex-direction: column;
    height: 100%; /* สูงเกือบเต็มจอ */
    gap: 15px;
    padding: 15px;
}
.dash-zone {
    display: flex;
    flex-wrap: nowrap;
    gap: 10px;
    border: 1px dashed #ccc;
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


   <style>
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

</head>
<body>

    <div id="pbi-v3-wrapper" style="cursor: default;">
        <div class="pbi-window">
            
       

            <div class="ribbons-container">
                <div class="ribbons-reporttabs">
                    <div class="reporttab file-reporttab">File</div>
                    <div class="reporttab active" onclick="RibbonswitchTab('Home', this)">Data Prep</div>
                      <div class="reporttab" onclick="RibbonswitchTab('Data', this)">Query Builder</div>
                    <div class="reporttab" onclick="RibbonswitchTab('Insert', this)">Visualization</div>

                </div>
                
                <div class="ribbons-tools " id="ribbon-Home" >
                    
                      <div class="ribbons-group" onclick="refreshPreviewTable();">
                               <div class="ribbons-btns">
                            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M17.65 6.35C16.2 4.9 14.21 4 12 4c-4.42 0-7.99 3.58-7.99 8s3.57 8 7.99 8c3.73 0 6.84-2.55 7.73-6h-2.08c-.82 2.33-3.04 4-5.65 4-3.31 0-6-2.69-6-6s2.69-6 6-6c1.66 0 3.14.69 4.22 1.78L13 11h7V4l-2.35 2.35z"/></svg>
                            <span class="btn-label">Refresh</span>
                        </div>
                        </div>
                    <div class="ribbons-group">
                    
                       <input type="file" id="sqlFileInput" accept=".sql" style="display: none;" onchange="readSqlFile(this)">

              

					<div class="ribbons-btns <c:if test='${ Config.source_type_method == 1 }'>disabled</c:if>" 
					     onclick="if(!this.classList.contains('disabled')) document.getElementById('sqlFileInput').click();">
					    <svg viewBox="0 0 24 24" class="icon-green"><path d="M14 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zM6 20V4h7v5h5v11H6z"/><path d="M10 10v4h4v-4h-4z" fill="#fff"/><rect x="8" y="9" width="8" height="8" rx="1" fill="#217346"/><path d="M10.5 11.5h3v3h-3z" fill="#fff"/></svg>
					    <span class="btn-label">Import Mysql</span>
					</div>
					
				
					
					<div class="ribbons-btns <c:if test='${ Config.source_type_method == 1 }'>disabled</c:if>" 
					     onclick="if(!this.classList.contains('disabled')) document.getElementById('csvFileInput').click();">
					    <svg viewBox="0 0 24 24" class="icon-green">
					        <path d="M14 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zM6 20V4h7v5h5v11H6z"/>
					        <path d="M8 12h8v1.5H8V12zm0 2.5h8v1.5H8v-1.5zm0 2.5h5v1.5H8V17z" fill="#217346"/>
					        <circle cx="17" cy="17" r="5" fill="#217346"/>
					        <path d="M17 14.5l1.5 2.5h-3l1.5-2.5z" fill="#fff"/>
					    </svg>
					    <span class="btn-label">Import CSV</span>
					</div>
					
					<input type="file" id="csvFileInput" style="display:none;" accept=".csv" onchange="handleCsvUpload(event)">
					
					<div class="ribbons-btns" 
     onclick="if(!this.classList.contains('disabled')) document.getElementById('configFileInput').click();">
    
    <svg viewBox="0 0 24 24" width="24" height="24" style="margin-bottom: 5px;">
        <path d="M14 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zM6 20V4h7v5h5v11H6z" fill="#555"/>
        <path d="M19.4 13c0-.3-.1-.6-.1-.9s.1-.6.1-.9l2.1-1.7c.2-.2.2-.4.1-.6l-2-3.5c-.1-.2-.4-.3-.6-.2l-2.5 1c-.5-.4-1.1-.7-1.7-1l-.4-2.7c0-.2-.2-.4-.5-.4h-4c-.2 0-.4.2-.4.4l-.4 2.7c-.6.3-1.2.6-1.7 1l-2.5-1c-.2-.1-.5 0-.6.2l-2 3.5c-.1.2 0 .5.2.6l2.1 1.7c0 .3-.1.6-.1.9s.1.6.1.9l-2.1 1.7c-.2.2-.2.4-.1.6l2 3.5c.1.2.4.3.6.2l2.5-1c.5.4 1.1.7 1.7 1l.4 2.7c0 .2.2.4.5.4h4c.2 0 .4-.2.4-.4l.4-2.7c.6-.3 1.2-.6 1.7-1l2.5 1c.2.1.5 0 .6-.2l2-3.5c.1-.2 0-.5-.2-.6l-2.1-1.7z" transform="scale(0.55) translate(16, 16)" fill="#e67e22"/>
        <circle cx="20.8" cy="20.8" r="1.5" fill="#fff"/>
    </svg>
    
    <span class="btn-label" style="color: #333; font-weight: bold;">นำเข้า Config</span>
</div>
		<input type="file" id="configFileInput" accept=".json" style="display:none;" onchange="importConfigFile(event)">			
					
					
   					 <div class="ribbons-btns <c:if test="${Config.source_type_method == 1  }">disabled</c:if>">
                            <svg viewBox="0 0 24 24" class="icon-blue"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-1 17.93c-3.95-.49-7-3.85-7-7.93 0-.62.08-1.21.21-1.79L9 15v1c0 1.1.9 2 2 2v1.93zm6.9-2.54c-.26-.81-1-1.39-1.9-1.39h-1v-3c0-.55-.45-1-1-1H8v-2h2c.55 0 1-.45 1-1V7h2c1.1 0 2-.9 2-2v-.41c2.93 1.19 5 4.06 5 7.41 0 2.08-.8 3.97-2.1 5.39z"/></svg>
                            <span class="btn-label">API</span>
                        </div>
                    
                     
                      
                    </div>


           <div class="ribbons-group">


<div class="ribbons-btns disabled"  id="row_mapper"
     onclick="shows2('draggable-window3','Form &amp; Layout'); getData('/rowmapper-filemanager', 'mainbody3')">
    <svg viewBox="0 0 24 24" class="icon-green">
        <path d="M9.01 14H2v2h7.01v3L13 15l-3.99-4v3zm5.98-1v-3H22V8h-7.01V5L11 9l3.99 4z" fill="#217346"/>
    </svg>
    <span class="btn-label">Row Mapper</span>
</div>



                        <div class="ribbons-btns disabled" id="transformdata"  onclick="shows2('draggable-window3','Form &amp; Layout'); getData('/transformdata-filemanager', 'mainbody3')">
                            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M3 17.25V21h3.75L17.81 9.94l-3.75-3.75L3 17.25zM20.71 7.04c.39-.39.39-1.02 0-1.41l-2.34-2.34c-.39-.39-1.02-.39-1.41 0l-1.83 1.83 3.75 3.75 1.83-1.83z"/></svg>
                            <span class="btn-label">Transform data </span>
                        </div>
                        <div class="ribbons-btns disabled" id="DuplicateScan" onclick="scanAndShowDuplicates()">
    <svg viewBox="0 0 24 24" class="icon-grey">
        <path d="M16 1H4c-1.1 0-2 .9-2 2v14h2V3h12V1zm3 4H8c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h11c1.1 0 2-.9 2-2V7c0-1.1-.9-2-2-2zm0 16H8V7h11v14z" fill="currentColor"/>
    </svg>
    <span class="btn-label">Duplicate Scan</span>
</div>
                        
                    </div>   



                    <div class="ribbons-group" >
                    <div class="ribbons-btns" id="ToConfigFile" onclick="exportConfigToJson()" style="cursor: pointer;">
    <svg viewBox="0 0 24 24" width="24" height="24" style="margin-bottom: 5px;">
        <path d="M19.4 13c0-.3-.1-.6-.1-.9s.1-.6.1-.9l2.1-1.7c.2-.2.2-.4.1-.6l-2-3.5c-.1-.2-.4-.3-.6-.2l-2.5 1c-.5-.4-1.1-.7-1.7-1l-.4-2.7c0-.2-.2-.4-.5-.4h-4c-.2 0-.4.2-.4.4l-.4 2.7c-.6.3-1.2.6-1.7 1l-2.5-1c-.2-.1-.5 0-.6.2l-2 3.5c-.1.2 0 .5.2.6l2.1 1.7c0 .3-.1.6-.1.9s.1.6.1.9l-2.1 1.7c-.2.2-.2.4-.1.6l2 3.5c.1.2.4.3.6.2l2.5-1c.5.4 1.1.7 1.7 1l.4 2.7c0 .2.2.4.5.4h4c.2 0 .4-.2.4-.4l.4-2.7c.6-.3 1.2-.6 1.7-1l2.5 1c.2.1.5 0 .6-.2l2-3.5c.1-.2 0-.5-.2-.6l-2.1-1.7z" transform="scale(0.8) translate(3, 3)" fill="#e67e22"/>
        <circle cx="12" cy="12" r="2.5" fill="#fff"/>
    </svg>
    <span class="btn-label" style="font-weight: bold; color: #555;">Export Config</span>
</div>
                                            <div class="ribbons-group ">   
    <div class="ribbons-btns disabled " id="ToSqlFile" onclick="exportToSqlFile()" style="cursor: pointer;">
        <svg viewBox="0 0 24 24" class="icon-grey" fill="currentColor">
            <path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm-1 7V3.5L18.5 9H13zm-1.01 9.5L7.5 15h3v-4h3v4h3l-4.49 3.5z"/>
        </svg>
        <span class="btn-label">Save to SQL</span>
    </div>

                    <div class="ribbons-btns disabled" id="ToCsvFile" onclick="exportToCsvFile()" style="cursor: pointer;">
        <svg viewBox="0 0 24 24" class="icon-grey" fill="currentColor">
            <path d="M14 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V8l-6-6zM6 20V4h7v5h5v11H6zM8 15h8v2H8v-2zm0-4h8v2H8v-2zm0-4h5v2H8V7z"/>
        </svg>
        <span class="btn-label">Save to CSV</span>
    </div>
</div>
               
                      
                    </div>

                  
                  

                </div>
                
                
                
                
                     <div class="ribbons-tools" id="ribbon-Data" style="display:none">
                    
  

                    <div class="ribbons-group">
                      
                     
                        <div class="ribbons-btns"  
                        onclick="executeCheeseQuery('jsonbody','${_csrf.parameterName}=${_csrf.token}')">
                           <svg viewBox="0 0 24 24" class="icon-grey"><path d="M7 2v11h3v9l7-12h-4l4-8z"/></svg>  <span class="btn-label">Get data </span>
                        </div>
                    
                        
<input type="hidden" name="csrfParam" value="${_csrf.parameterName}=${_csrf.token}">
                    
                       
                   
                
                    </div>

            
                        
                      <div class="ribbons-group">    
                         <div class="ribbons-btns " onclick="openwindow('draggable-window3','Filter','9000'); getData('/QueryChain-filemanager', 'mainbody3')">
                       
                           <svg viewBox="0 0 24 24" class="icon-grey" >
    <path fill="currentColor" d="M14.73 13.31l5.72-7.31c.66-.84.07-2-1-2H4.54c-1.06 0-1.65 1.16-1 2l5.72 7.31v5.19c0 .35.18.68.49.86l2 1.15c.53.3 1.19-.08 1.19-.69v-6.51z"/>
    <path fill="currentColor" d="M19 13h-2v2h2v-2zm-2 4h2v2h-2v-2zm4-8h-2v2h2V9z" opacity="0.3"/> </svg>
                           
                            <span class="btn-label" >Filter</span>
                        </div>
                        <div class="ribbons-btns  " onclick="openwindow('draggable-window3','Quick Filter','9000');
                        getData('/QuickQueryChain-filemanager', 'mainbody3');">
                        <svg viewBox="0 0 24 24" class="icon-blue">
    <path fill="currentColor" d="M15.5 14h-.79l-.28-.27A6.471 6.471 0 0 0 16 9.5 6.5 6.5 0 1 0 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"/>
</svg>
                            <span class="btn-label">Quick Filter</span>
                        </div>
                      
                    </div>
                  
                    

                             <div class="ribbons-group">   
 					<div class="ribbons-btns <c:if test="${ Config == null   }">disabled</c:if>"  style="display:none">
                            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M12 2C6.48 2 2 4.02 2 6.5v11C2 19.98 6.48 22 12 22s10-2.02 10-4.5v-11C22 4.02 17.52 2 12 2zm0 2c4.27 0 8 1.45 8 3s-3.73 3-8 3-8-1.45-8-3 3.73-3 8-3zm0 16c-4.27 0-8-1.45-8-3v-2.06c1.7 1.3 4.67 2.06 8 2.06s6.3-0.76 8-2.06V17c0 1.55-3.73 3-8 3z"/></svg>
                            <span class="btn-label">Commit to DB</span>
                        </div>
                          <div class="ribbons-group">
                        <div class="ribbons-btns" 
                        onclick="shows2('draggable-window2','Sync Back');
                         getData('/SyncBack-filemanager', 'mainbody2');">
                         
                            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M19.35 10.04C18.67 6.59 15.64 4 12 4 9.11 4 6.6 5.64 5.35 8.04 2.34 8.36 0 10.91 0 14c0 3.31 2.69 6 6 6h13c2.76 0 5-2.24 5-5 0-2.64-2.05-4.78-4.65-4.96zM14 13v4h-4v-4H7l5-5 5 5h-3z"/></svg>
                            <span class="btn-label">Sync Back</span>
                        </div>
                     
                    </div>
                    
                    
<div class="ribbons-btns" 
     title="เริ่มต้นตั้งค่าใหม่ (New .cheese)"
     onclick="createNewCheese();">
    <svg viewBox="0 0 24 24" class="icon-yellow" style="fill: #2ECC71;">
        <path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 14h-3v3h-2v-3H8v-2h3v-3h2v3h3v2zm-3-7V3.5L18.5 9H13z"/>
    </svg>
    <span class="btn-label">New .cheese</span>
</div>                    
<input type="file" id="cheeseFileInput" style="display:none;" accept=".cheese" onchange="importCheeseFile(event)">

<div class="ribbons-btns" 
     title="โหลดไฟล์การตั้งค่า (.cheese)"
     onclick="document.getElementById('cheeseFileInput').click();">
    <svg viewBox="0 0 24 24" class="icon-yellow" style="fill: #F5B041;">
        <path d="M20 6h-8l-2-2H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2zm0 12H4V8h16v10z"/>
        <path d="M12 10l-4 4h3v3h2v-3h3z"/> 
    </svg>
    <span class="btn-label">Load .cheese</span>
</div>

<div class="ribbons-btns" 
     title="บันทึกการตั้งค่าเป็นไฟล์ (.cheese)"
     onclick="exportCheeseFile();">
    <svg viewBox="0 0 24 24" class="icon-yellow" style="fill: #F39C12;">
        <path d="M19 9h-4V3H9v6H5l7 7 7-7zM5 18v2h14v-2H5z"/>
    </svg>
    <span class="btn-label">Save .cheese</span>
</div>     
                    
  						 </div>
                </div>
                
                
                  
                     <div class="ribbons-tools"  id="ribbon-Insert"  style="display:none">
                    
                
     <div class="ribbons-group">
                         <div class="ribbons-btns" onclick="buildDashboardFromImage()">
                            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
                            <span class="btn-label">NEW Page</span>
                        </div>
                     

                      </div>

               <div class="ribbons-group">
    
    <div class="ribbons-btns" onclick="mergeSelected()">
        <svg viewBox="0 0 24 24" class="icon-blue">
            <path d="M3.9 12c0-1.71 1.39-3.1 3.1-3.1h4V7H7c-2.76 0-5 2.24-5 5s2.24 5 5 5h4v-1.9H7c-1.71 0-3.1-1.39-3.1-3.1zM8 13h8v-2H8v2zm9-6h-4v1.9h4c1.71 0 3.1 1.39 3.1 3.1s-1.39 3.1-3.1 3.1h-4V17h4c2.76 0 5-2.24 5-5s-2.24-5-5-5z"/>
        </svg>
        <span class="btn-label">Merge<br>Boxes</span>
    </div>

    <div class="small-btn-stack">
        <div class="ribbons-btns-sm" onclick="addWidgetToZone('zone-1')">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
            <span class="btn-label">ใส่กล่อง (แถว 1)</span>
        </div>
        <div class="ribbons-btns-sm" onclick="addWidgetToZone('zone-2')">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
            <span class="btn-label">ใส่กล่อง (แถว 2)</span>
        </div>
        <div class="ribbons-btns-sm" onclick="addWidgetToZone('zone-3')">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M19 13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z"/></svg>
            <span class="btn-label">ใส่กล่อง (แถว 3)</span>
        </div>
    </div>
</div>

<div class="ribbons-group">
    
    <div class="small-btn-stack">
        <div class="ribbons-btns-sm" onclick="changeDashboardLayout(1)">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M3 3h18v4H3V3zm0 6h18v12H3V9z"/></svg>
            <span class="btn-label">Layout 10:90</span>
        </div>
        <div class="ribbons-btns-sm" onclick="changeDashboardLayout(3)">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M3 3h18v6H3V3zm0 8h18v10H3v-10z"/></svg>
            <span class="btn-label">Layout 30:70</span>
        </div>
    </div>

    <div class="small-btn-stack">
        <div class="ribbons-btns-sm" onclick="changeDashboardLayout(2)">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M3 3h18v8H3V3zm0 10h18v8H3v-8z"/></svg>
            <span class="btn-label">Layout 50:50</span>
        </div>
        <div class="ribbons-btns-sm" onclick="changeDashboardLayout(4)">
            <svg viewBox="0 0 24 24" class="icon-grey"><path d="M3 3h18v4H3V3zm0 6h18v4H3V9zm0 6h18v4H3v-4z"/></svg>
            <span class="btn-label">Layout 3 แถว</span>
        </div>
    </div>
</div>
                               <div class="ribbons-group">
                     <div class="ribbons-btns" onclick="addBarVisual()">
    <svg viewBox="0 0 24 24" class="icon-blue"><path d="M5 19h14v2H5zM5 9h4v8H5zm5-4h4v12h-4zm5 4h4v8h-4z"/></svg>
    <span class="btn-label">Bar<br>visual</span>
</div>

<div class="ribbons-btns" onclick="addTextBox()">
    <svg viewBox="0 0 24 24" class="icon-grey"><path d="M21 5H3v14h18V5zm-2 12H5V7h14v10z"/><path d="M7 9h2v6H7zm4 0h2v6h-2zm4 0h2v6h-2z"/></svg>
    <span class="btn-label">Text<br>box</span>
</div>
                        <div class="ribbons-btns" onclick="openwindow('draggable-window3','More visuals','9000');
                        getData('/visuals-filemanager', 'mainbody3');">
                            <svg viewBox="0 0 24 24" class="icon-grey"><circle cx="5" cy="12" r="2"/><circle cx="12" cy="12" r="2"/><circle cx="19" cy="12" r="2"/></svg>
                            <span class="btn-label">More<br>visuals </span>
                        </div>




                        
                    </div>
                 <div class="ribbons-group">
            <div class="ribbons-btns" onclick="saveDashboardToFile()">
    <svg viewBox="0 0 24 24" class="icon-blue" fill="currentColor">
        <path d="M17 3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V7l-4-4zm-5 16c-1.66 0-3-1.34-3-3s1.34-3 3-3 3 1.34 3 3-1.34 3-3 3zm3-10H5V5h10v4z"/>
    </svg>
    <span class="btn-label">Save<br>Dashboard</span>
</div>
                        </div>     
<input type="file" id="importDashboardFile" accept=".dash" style="display: none;" onchange="loadDashboardFromFile(event)">

<div class="ribbons-btns" onclick="document.getElementById('importDashboardFile').click()">
    <svg viewBox="0 0 24 24" class="icon-blue" fill="currentColor">
        <path d="M9 16h6v-6h4l-7-7-7 7h4zm-4 2h14v2H5z"/>
    </svg>
    <span class="btn-label">Load<br>Dashboard</span>
</div>
                </div>
                
                
                
                
                
                
                
                
                
                
            </div>

            <div class="main-area">
                
                <div class="view-bar">
                    <div id="tabdata-3"  class="view-item " title="Report" onclick="switchtabActiveAndData('data','3','3')">
                        <svg viewBox="0 0 24 24"><path d="M5 19h14v2H5zM5 9h4v8H5zm5-4h4v12h-4zm5 4h4v8h-4z"/></svg>
                    </div>
                    <div id="tabdata-1"  class="view-item active" title="Data" onclick="switchtabActiveAndData('data','3','1')">
                        <svg viewBox="0 0 24 24"><path d="M4 4h16v16H4V4zm2 2v3h12V6H6zm0 5v3h12v-3H6zm0 5v3h12v-3H6z"/></svg>
                    </div>
               <div  id="tabdata-2"  class="view-item" title="Sub Data" onclick="switchtabActiveAndData('data','3','2')">
                        <svg viewBox="0 0 24 24"><path d="M4 4h16v16H4V4zm2 2v3h12V6H6zm0 5v3h12v-3H6zm0 5v3h12v-3H6z"/></svg>
                    </div>
             
                </div>

                <div class="canvas-viewport">
               
                    <div class="report-sheet" id="showdata-1">
                    
                    
                   
                     <div id="table-container"  style="max-height: 100%;width:100%;overflow:auto"></div>
                           

                    </div>
                         <div class="report-sheet" id="showdata-2" style="overflow:auto">
                    
                   
                     <div id="table-container2" style="max-height: 100%;width:100%;overflow:auto"></div>
                       

                    </div>
                        <div class="report-sheet" id="showdata-3" style="overflow:auto">
                          
						<div id="table-container3" class="layout-type-1">
						    <div class="dash-zone zone-1" id="zone-1"></div>
						    <div class="dash-zone zone-2" id="zone-2"></div>
						       <div class="dash-zone zone-3" id="zone-3"></div>
						</div>
                       

                    </div>
                    
                </div>

                <div class="panes-area" >
                    <div class="pane-filters">
                        <span style="writing-mode:vertical-rl; transform:rotate(180deg); margin-top:20px; font-weight:600; color:#444;" onclick="RibbontoggleFilters('pane-viz')">Property</span>
                       
                    </div>

                    <div class="pane-viz" id="pane-viz" style="display:none">
                        <div class="pane-header">
                            <span>Select Property </span> <span>»</span>
                        </div>
                       
                        <div class="viz-grid" id="viz-grid">
                      <div style ="display:none">
                            <div class="viz-btn" onclick="RibbontoggleFilters('pane-fields')"><svg viewBox="0 0 24 24"><path d="M4 4h16v16H4V4zm2 2v12h12V6H6z"/></svg></div>
                            <div class="viz-btn"><svg viewBox="0 0 24 24"><path d="M5 19h14v2H5zM5 9h4v8H5zm5-4h4v12h-4zm5 4h4v8h-4z"/></svg></div>
                            <div class="viz-btn"><svg viewBox="0 0 24 24"><path d="M16 6l2.29 2.29-4.88 4.88-4-4L2 16.59 3.41 18l6-6 4 4 6.3-6.29L22 12V6z"/></svg></div>
                            <div class="viz-btn"><svg viewBox="0 0 24 24"><path d="M11 2v20c-5.07-.5-9-4.79-9-10s3.93-9.5 9-10zm2 0v11h10.99c-.01-5.22-3.98-9.49-9-9.99z"/></svg></div>
                            <div class="viz-btn" style="background:#e1dfdd; border:1px solid #999;"><svg viewBox="0 0 24 24"><path d="M10 10v4h4v-4h-4z" fill="#000"/><rect x="8" y="9" width="8" height="8" rx="1" fill="none" stroke="#000"/></svg></div>
                            <div class="viz-btn">...</div>
                       </div>
                        </div>
                        <div style="padding:10px; border-top:1px solid #eee;display:none">
                            <div style="font-size:11px; margin-bottom:5px;">Values</div>
                            <div style="border:1px dashed #ccc; background:#fafafa; height:25px; display:flex; align-items:center; padding-left:5px; color:#999; font-size:11px;">Add data fields here</div>
                        </div>
                    </div>

                    <div class="pane-fields" id="pane-fields" style="display:none">
                        <div class="pane-header">
                            <span>Fields</span> <span>»</span>
                        </div>
                        <div class="search-field">
                            <input type="text" id="Fields" onkeyup="Filterlist(this)"  value="">
                        </div>
                        <div class="field-list" id="field-list">
                      
                        </div>
                    </div>
                </div>

            </div>


            <div class="footer-bar">
                          
                 <c:if test="${Config.id > 0 && Config.source_type  != null }">
              	  <div class="page-reporttab active">Sync Data : ${Config.source_type }
                  <c:if test="${Config.data_info_showsample_total == 1}">,Sample Records : &nbsp; </c:if><p id="total_json" style="display:<c:if test="${Config.data_info_showsample_total == 0}">none</c:if>">0</p> &nbsp; 
                  <c:if test="${Config.data_info_showtotal_row == 1}"> ,Total Records : &nbsp; </c:if><p id="total_data"  style="display: <c:if test="${Config.data_info_showtotal_row == 0}">none</c:if>">0</p> &nbsp; 
                 </div>
         </c:if>
              
            </div>

        </div>
    </div>

<div id="jsonbody"></div>

</body>
</html>


 <script>
 csrfToken = '${_csrf.parameterName}=${_csrf.token}';
 resetGlobalStates();

  </script>
