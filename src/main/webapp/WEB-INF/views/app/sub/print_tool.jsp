<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
		    @font-face {
		    font-family: 'THSarabunNew';
		    /* ตัดคำว่า static ออก แล้วเริ่มด้วย / */
		    src: url('/app/system/font/font/THSarabunNew.eot?#iefix') format('embedded-opentype'),
		         url('/app/system/font/THSarabunNew.woff') format('woff'),
		         url('/app/system/font/THSarabunNew.ttf') format('truetype'),
		         url('/app/system/font/THSarabunNew.svg#THSarabunNew') format('svg');
		    font-weight: normal;
		    font-style: normal;
		    
		}
		    @font-face {
    font-family: 'THSarabunNew-Bold'; /* ชื่อนี้ต้องตรงกับ value ใน <option> */
    
    /* แก้ Path ให้เหมือนตัวปกติ แต่เปลี่ยนชื่อไฟล์เป็นตัว Bold */
    src: url('/app/system/font/THSarabunNew-Bold.eot?#iefix') format('embedded-opentype'),
         url('/app/system/font/THSarabunNew-Bold.woff') format('woff'),
         url('/app/system/font/THSarabunNew-Bold.ttf') format('truetype'),
         url('/app/system/font/THSarabunNew-Bold.svg#THSarabunNew-Bold') format('svg');
         
    font-weight: bold; /* ระบุว่าเป็นตัวหนา */
    font-style: normal;
}
    
    
      /* --- Base & Layout --- */
#window-container { 
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f0f0f0; font-size: 13px; color: black;
}

#window-container { display: grid; grid-template-rows: 25px auto 1fr 25px; height: 100%; width: 100%; overflow: hidden; }

/* แก้ไขให้เว้นวรรคให้ถูกต้อง */
#window-container .top-menu-bar,   
#window-container .ribbon-toolbar { background-color: #f8f8f8; border-bottom: 1px solid #ccc; }

/* Ribbon Styles */
#window-container .ribbon-toolbar { background-color: #fff; display: flex; flex-direction: column; }
#window-container .ribbon-tabs { display: flex; border-bottom: 1px solid #ddd; padding-top: 5px; user-select: none; }
#window-container .ribbon-tab-item { padding: 5px 20px; cursor: pointer; border: 1px solid transparent; margin-bottom: -1px; font-size: 12px; }
#window-container .ribbon-tab-item:hover { background-color: #eeffff; }
#window-container .ribbon-tab-active { background-color: white; border-color: #ddd; border-bottom-color: white; color: #3867d6; font-weight: bold; }

/* Table Tab */
#window-container #tab-table-design { display: none; color: #d63031; border-top: 2px solid #d63031; } 
#window-container #tab-table-design.visible { display: block; }

#window-container .ribbon-content { padding: 8px; background-color: white; min-height: 80px; display: flex; align-items: center; overflow-x: auto; }
#window-container .ribbon-group { display: none; gap: 5px; align-items: center; border-right: 1px solid #eee; padding-right: 10px; margin-right: 10px; }
#window-container .ribbon-group.active { display: flex; } 

#window-container #tab-table-design .tool-btn { border: 1px solid transparent; background: none; padding: 5px 5px; cursor: pointer; border-radius: 3px; display: flex; flex-direction: column; align-items: center; font-size: 11px; color: #444; min-width: 35px; text-align: center; }
#window-container #tab-table-design .tool-btn:hover { background-color: #def; border-color: #bdf; }
#window-container #tab-table-design .tool-btn:active { background-color: #cfe; transform: translateY(1px); }
#window-container #tab-table-design .tool-btn i { font-size: 14px; margin-bottom: 2px; font-style: normal; font-weight: bold; font-family: serif; display: block; }

/* Inputs & Colors */
#window-container select.ribbon-select { padding: 2px; border: 1px solid #ccc; font-size: 11px; height: 22px; }
#window-container .color-tool-container { display: flex; align-items: center; gap: 2px; }
#window-container .big-color-btn { width: 30px; height: 30px; border: 1px solid #ccc; cursor: pointer; border-radius: 3px; display: flex; align-items: center; justify-content: center; background-color: #f0f0f0; }
#window-container .color-input-wrapper { width: 15px; height: 30px; overflow: hidden; position: relative; cursor: pointer; border: 1px solid #ccc; border-radius: 3px; }
#window-container #tab-table-design .color-input-wrapper input { position: absolute; left: -5px; top: -5px; width: 40px; height: 40px; cursor: pointer; }

/* Menu */
#window-container .top-menu-bar-left { display: flex; align-items: center; }
#window-container .menu-item { padding: 0 10px; cursor: pointer; color: #555; position: relative; user-select: none; }
#window-container .dropdown-content { display: none; position: absolute; background-color: #fff; box-shadow: 0 4px 8px rgba(0,0,0,0.2); min-width: 180px; z-index: 200; top: 100%; left: 0; border: 1px solid #ccc; padding: 5px 0; }
#window-container .dropdown-content a { color: black; padding: 8px 16px; text-decoration: none; display: block; }
        /* --- Document Area (Fixed Vertical Stacking) --- */
        .document-area { 
          overflow: auto; 
        padding: 30px; 
        background-color: #e9e9e9; 
        display: flex; flex-direction: column; align-items: center; 
        gap: 20px; 
        padding-bottom: 150px; 
        width: 100%; height: 100%;
        cursor: auto;
    
        }
          .page-number-watermark { position: absolute; bottom: 10px; right: 20px; color: #ccc; font-size: 10px; pointer-events: none; }

       
		/* --- สถานะแนวนอน (Landscape) --- */
		.content-wrapper.landscape {
		    width: 1200px;      /* กว้างขึ้น */
		    min-height: 800px;  /* เตี้ยลง */
		}



        
        
        
        
        
      /* =========================================
       2. DOCUMENT AREA (แก้เรื่องขนาดกระดาษ)
       ========================================= */
    .document-area { 
        overflow-y: auto; 
        overflow-x: auto; /* เปิดให้เลื่อนซ้ายขวาได้เผื่อจอมันเล็ก */
        padding: 30px; 
        background-color: #e9e9e9; 
        display: flex; 
        flex-direction: column; 
        align-items: center; 
        gap: 20px; 
        padding-bottom: 150px; 
        width: 100%; height: 100%;
    }

    /* --- ตัวกระดาษสีขาว (Paper Container) --- */
    .document-page { 
background-color: white; 
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2); 
        border: 1px solid #ccc; 
        
        /* A4 Portrait: 210mm x 297mm */
        /* แปลงเป็น px ที่ 96dpi: 794px x 1123px */
        width: 800px;      
        height: 1200px; /* *** เปลี่ยนจาก min-height เป็น height *** */
        
        padding: 5mm; 
        box-sizing: border-box; 
        position: relative; 
        margin-bottom: 20px;
        flex: none; 
        overflow: hidden; /* *** สำคัญ: ส่วนเกินต้องถูกซ่อน เพื่อให้ JS รู้ว่าต้องตัด *** */
        
        transition: width 0.3s ease, height 0.3s ease;
    }

 /* --- ตัวกระดาษ ตอนเป็นแนวนอน (Landscape) --- */
    .document-page.landscape {
        /* ปรับความกว้างให้สอดคล้องกับ A4 Landscape (297mm) */
        /* 297mm * 96dpi / 25.4 ≈ 1122.5px -> ปัดเป็น 1123px */
        width: 1200px !important; 
        
        /* *** แก้ตรงนี้: ใช้ height แทน min-height *** */
        height: 800px !important;  
        
        /* บังคับไม่ให้ยืด */
        min-height: 800px !important; 
        max-height: 800px !important;
    }
    /* --- เนื้อหาข้างใน (Content Wrapper) --- */
    .content-wrapper { 
      width: 100%; 
        height: 100%; 
        outline: none; 
        word-wrap: break-word; 
        overflow: hidden;
    }
        .content-wrapper p, .content-wrapper div { margin: 0; } 
        .content-wrapper img { max-width: 100%; cursor: pointer; vertical-align: text-bottom; border: 1px solid transparent; }
        .content-wrapper img:hover { border: 1px dashed #ccc; }
        
        /* Table Styles */
        .content-wrapper table { border-collapse: collapse; width: 100%; margin-bottom: 10px; table-layout: fixed; }
        .content-wrapper td, .content-wrapper th { 
            border: 1px solid #000; padding: 5px; min-height: 20px; vertical-align: top; position: relative; 
            /* Fix table overflow */
            overflow: hidden; word-wrap: break-word; white-space: normal; word-break: break-all;
        }
        .content-wrapper td.selected-cell { background-color: #cce5ff !important;  }

        /* Column Break Marker */
        .col-break-marker {
            break-after: column; border-bottom: 1px dotted #999; margin-bottom: 5px; display: block; height: 1px;
            color: #999; font-size: 10px; text-align: center; pointer-events: none; position: relative;
        }
        .col-break-marker::after { content: "--- Column Break ---"; background: #fff; padding: 0 5px; }

        /* Resizer Frame */
        #resizer-frame { position: fixed; border: 2px solid #3867d6; display: none; pointer-events: none; z-index: 999; box-shadow: 0 0 5px rgba(56, 103, 214, 0.5); }
        .resize-handle { width: 10px; height: 10px; background-color: white; border: 1px solid #3867d6; position: absolute; pointer-events: auto; }
        
        
        
        
     
        
        
        .handle-se { bottom: -6px; right: -6px; cursor: se-resize; }

        /* Modal */
        .modal { display: none; position: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.4); }
        .modal-content { background-color: #fefefe; margin: 15% auto; padding: 20px; border: 1px solid #888; width: 250px; border-radius: 5px; }
        .modal-body input { width: 100%; padding: 5px; margin-bottom: 10px; }
        .modal-footer { text-align: right; }
        .btn-primary { background-color: #3867d6; color: white; border: none; padding: 5px 15px; cursor: pointer; border-radius: 3px; }
        .btn-secondary { background-color: #ccc; color: black; border: none; padding: 5px 15px; cursor: pointer; border-radius: 3px; margin-right: 5px; }

        .status-bar { background-color: #3867d6; color: white; display: flex; align-items: center; justify-content: space-between; padding: 0 10px; font-size: 12px; }
   
   
   /* --- Styles สำหรับรูปร่าง --- */
.shape-box {
    display: inline-block;
    width: 100px;
    height: 50px;
    border: 2px solid black;
    background: transparent;
    vertical-align: bottom;
    margin: 5px;
    padding: 5px; /* เผื่อพิมพ์ข้อความข้างใน */
    box-sizing: border-box;
}

.shape-circle {
    display: inline-block;
    width: 60px;
    height: 60px;
    border: 2px solid black;
    border-radius: 50%; /* ทำเป็นวงกลม */
    background: transparent;
    vertical-align: bottom;
    margin: 5px;
}

/* สามเหลี่ยม (ใช้เทคนิค Border) พิมพ์ข้างในไม่ได้นะอันนี้ */
.shape-triangle {
    display: inline-block;
    width: 0; 
    height: 0; 
    border-left: 30px solid transparent;
    border-right: 30px solid transparent;
    border-bottom: 60px solid black; /* สีสามเหลี่ยม */
    vertical-align: bottom;
    margin: 5px;
    position: relative;
}

.shape-line {
    display: inline-block;
    width: 100px;
    height: 2px;
    background-color: black;
    vertical-align: middle;
    margin: 5px;
}
   /* หูหิ้วสำหรับลาก (Drag Handle) */
.drag-handle {
    display: inline-block;
    width: 20px;
    height: 20px;
    background-color: #ddd; /* สีเทาๆ */
    color: #555;
    cursor: move; /* เมาส์เป็นรูปย้าย */
    font-size: 14px;
    line-height: 20px;
    text-align: center;
    border-radius: 3px;
    margin-right: 5px;
    user-select: none; /* ห้ามคลุมดำตัวนี้ */
    vertical-align: middle;
}

.drag-handle:hover {
    background-color: #bbb; /* เปลี่ยนสีตอนชี้ */
}

/* --- กระดานวาดรูป (Canvas Container) --- */
.drawing-canvas {
    display: inline-block;
    width: 300px;          /* ขนาดเริ่มต้น */
    height: 200px;
    border: 2px dashed #aaa; /* เส้นประให้รู้ขอบเขต */
    background-color: #fafafa;
    position: relative;    /* สำคัญ! เป็นจุดอ้างอิงให้ลูกๆ */
    resize: both;          /* ให้ user ดึงมุมขยายขนาดได้ */
    overflow: hidden;      /* ห้ามลูกหลุดออกนอกกล่อง */
    margin: 10px;
    vertical-align: top;
}

/* ถ้าคลิกเลือกกระดาน ให้เส้นเข้มขึ้น */
.drawing-canvas:focus-within, .drawing-canvas:hover {
    border-color: #007bff;
}

/* --- รูปร่างที่ลากได้ (Absolute Shape) --- */
.abs-shape {
    position: absolute;    /* ลอยตัวอิสระ */
    cursor: move;          /* เมาส์เป็นรูปย้าย */
    box-sizing: border-box;
}

/* ตัวเลือกกรอบสีแดงเวลาเลือก */
.selected-shape {
    outline: 2px solid red;
    z-index: 100; /* ให้ลอยทับเพื่อน */
}


/* --- อัปเดต: รูปร่างอิสระ --- */
.abs-shape {
    position: absolute;
    box-sizing: border-box;
    resize: both;       /* ย่อขยายได้ */
    overflow: hidden;   /* สำคัญมาก ไม่งั้นมุมดึงไม่ขึ้น */
    min-width: 30px;
    min-height: 30px;
    
    /* จัดกึ่งกลางสำหรับพวก Emoji/Shape */
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
}

/* ตอนเอาเมาส์ชี้ ให้ขึ้น Cursor ย้าย */
.abs-shape:hover {
    cursor: move; 
    outline: 1px dashed #ccc; /* เส้นประจางๆ บอกขอบเขต */
}

/* ตอนคลิกเลือก (Focus) */
.selected-shape {
    outline: 2px solid #007bff !important; /* เส้นสีฟ้าชัดๆ */
    z-index: 1000; /* ลอยทับเพื่อน */
}

/* Emoji Shape */
.emoji-shape {
    font-size: 40px;
    background: transparent;
    border: none;
    line-height: 1;
}

/* กล่องข้อความ (พิมพ์ได้) */
.textbox-shape {
    border: 1px dotted #999;
    background: transparent;
    cursor: text !important; /* เมาส์เป็นรูปตัว I */
    display: block; /* แก้เรื่องจัดกึ่งกลางให้พิมพ์ชิดซ้ายบน */
    padding: 5px;
    overflow: auto; /* ถ้าข้อความล้นให้เลื่อนได้ */
}

/* --- สไตล์สำหรับลูกศร --- */
.arrow-shape {
    display: flex;
    align-items: center;
    justify-content: center;
    background: transparent;
    font-size: 40px; /* ขนาดลูกศรเริ่มต้น */
    line-height: 1;
    color: black;
    border: none !important; /* ไม่มีขอบ */
        z-index: 5;    
}

/* --- สไตล์สำหรับกล่องข้อความ (พื้นใส) --- */
.textbox-shape {
    background-color: transparent; /* พื้นใส ไว้แปะทับคนอื่น */
    border: 1px dotted #aaa;       /* ขอบจางๆ พิมพ์เสร็จค่อยลบขอบได้ */
    padding: 5px;
    z-index: 10;                   /* ให้ลอยทับรูปทรงอื่นเสมอ */
}

/* แก้ไขสามเหลี่ยมให้ย่อขยายได้ (ใช้เทคนิค Clip Path แทน Border) */
.triangle-resize {
    background-color: black;
    clip-path: polygon(50% 0%, 0% 100%, 100% 100%);
    width: 100%;
    height: 100%;
}







/* --- Shape ทั่วไป --- */
.abs-shape {
    position: absolute;
    box-sizing: border-box;
    resize: both;
    overflow: hidden;
    min-width: 30px;
    min-height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    
    /* แก้ข้อ 1: ตั้งค่าเริ่มต้นให้พอมีลำดับ */
    z-index: 10; 
}

/* แก้ข้อ 1: ตัวที่ถูกเลือก ต้องอยู่หน้าสุดเสมอ! */
.selected-shape {
    outline: 2px solid #007bff !important;
    z-index: 9999 !important; /* เอาให้ทะลุฟ้า */
}

/* แก้ข้อ 2: กล่องข้อความ */
.textbox-shape {
    border: 1px dotted #999;
    background: transparent;
    display: block;
    padding: 5px;
    overflow: hidden; /* ซ่อน Scrollbar */
    z-index: 20; /* ให้สูงกว่ารูปปกตินิดนึง จะได้วางทับได้ */
    
    /* เทคนิค: ถ้าไม่ได้เลือก ให้เมาส์เป็น Move ไว้ก่อน จะได้ลากง่าย */
    cursor: move; 
}

/* พอ Double Click หรือ Focus เข้าไปพิมพ์ ค่อยเปลี่ยนเมาส์ */
.textbox-shape:focus {
    cursor: text;
    outline: 1px dashed #007bff;
    z-index: 9999; /* ตอนพิมพ์ก็ต้องอยู่บนสุด */
}


/* --- Ribbon Improvement --- */
    .ribbon-section {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        padding: 0 5px;
        height: 100%;
    }

    .ribbon-group {
        display: none; /* ซ่อนก่อน */
        align-items: flex-start; /* จัดชิดบน */
        height: 100%;
    }
    .ribbon-group.active { display: flex; }

    /* เส้นคั่นแนวตั้งระหว่างกลุ่ม */
    .separator {
        width: 1px;
        background-color: #ddd;
        height: 60px;
        margin: 0 5px;
        align-self: center;
    }
    .separator-small {
        width: 1px;
        background-color: #ddd;
        height: 20px;
        margin: 0 3px;
    }
/* ปุ่มมาตรฐาน */
#window-container .tool-btn {
    border: 1px solid transparent;
    background: none;
    padding: 2px 5px;
    cursor: pointer;
    border-radius: 2px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 11px;
    color: #333;
    min-width: 25px;
    text-align: center;
}
#window-container .tool-btn:hover { background-color: #cce8ff; border-color: #99d1ff; }
#window-container .tool-btn i, 
#window-container .tool-btn b { margin-bottom: 2px; font-size: 14px; }

/* ปุ่มแบบแถวแนวนอน (Small Button) */
#window-container .small-btn {
    flex-direction: row; /* เรียงแนวนอน */
    width: 100%;
    justify-content: flex-start;
    padding: 2px 4px;
    min-height: 22px;
    font-size: 11px;
}
#window-container .small-btn span { margin-right: 5px; }

/* Wrapper สำหรับปุ่มสี (ที่มีแถบสีข้างล่าง) */
#window-container .tool-btn-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    position: relative;
    cursor: pointer;
    border: 1px solid transparent;
}
#window-container .tool-btn-wrapper:hover { background-color: #cce8ff; border-color: #99d1ff; }

#window-container .mini-color-bar {
    width: 100%;
    height: 4px;
    margin-top: -2px;
    overflow: hidden;
}
#window-container .mini-color-bar input {
    width: 100%;
    height: 20px;
    margin-top: -10px;
    cursor: pointer;
    opacity: 0;
}
    
    
    
    
    
    
   
 /* คลาสสำหรับปิดการทำงานปุ่มและเครื่องมือ */
.ui-disabled {
    pointer-events: none; /* ห้ามคลิก */
    opacity: 0.5;         /* ทำให้สีจางลง */
    filter: grayscale(100%); /* (Optional) ทำให้เป็นขาวดำ */
    user-select: none;    /* ห้ามคลุมดำ */
}

/* ป้องกันไม่ให้ content-wrapper ถูกแก้ไข (เผื่อ contenteditable ยังค้าง) */
.content-wrapper[contenteditable="false"] {
    outline: none;
    cursor: default;
}   
    
    
    </style>
</head>
<body >

<div class="window-container" id="window-container">
  

  <div class="top-menu-bar">
        <div class="top-menu-bar-left">
            <span style="font-size: 18px; color: #3867d6; margin-right: 10px; padding-left: 10px;">&#9745;</span> 
            <div class="menu-item" onclick="toggleDropdown(this)">File
                <div class="dropdown-content">
				      <a href="#" onclick="runword()"><b>&#128190; Reload</b></a>
				<c:if test="${mydoc.doc_autoid > 0}">
                    <a href="#" onclick="saveDocumentToLocal('${mydoc.doc_autoid}')"><b>&#128190; Save</b></a>
				</c:if>	
					
					
                    <a href="#" onclick="saveDocumentOnly()"><b>&#128190; Save (ในเครื่อง)</b></a>
                      <a href="#" onclick="saveDocument()"><b>&#128190; Save (Download)</b></a>
                      
                      
                    <a href="#" onclick="setReadOnlyMode(true)"><b>isReadOnly</b></a>
                    
                     <a href="#" onclick="triggerLoad()"><b>&#128193; Open (UpLoad File)</b></a>
                    <a href="#" onclick="loadDocument()"><b>&#128193; Open  File</b></a>
                         <a href="#" onclick="scanAndFixTags()"><b>&#128193; Scan And Fix Tags</b></a>
                         <a href="#" onclick="printDocument()"><b>Print (Ctrl + P)</b></a>   <!-- เพิ่มตรงนี้ -->
                    <input type="file" id="file-load-input" hidden accept=".json" onchange="loadDocument(this)">
                    <hr style="margin: 5px 0;">
                    <a href="#" onclick="setPaperSize('A4')">Size: A4</a>
                    

                </div>
            </div>
       
        </div>
    
    </div>

    <div class="ribbon-toolbar">
        <div class="ribbon-tabs">
            <div class="ribbon-tab-item ribbon-tab-active" id="tab-home" onclick="wordswitchTab('home')">Home</div>
            <div class="ribbon-tab-item" id="tab-insert" onclick="wordswitchTab('insert')">Insert</div>
            <div class="ribbon-tab-item" id="tab-layout" onclick="wordswitchTab('layout')">Layout</div>
            <div class="ribbon-tab-item" id="tab-table-design" onclick="wordswitchTab('table-design')">Table Design</div>
        </div>
        
        <div class="ribbon-content">
            
            <div id="home-tools" class="ribbon-group active">
                
                <div class="ribbon-section">
                   <button class="tool-btn" 
    onmousedown="event.preventDefault();" 
    onclick="pasteFromClipboard()" 
    title="Paste" 
    style="height:55px; width:40px;">
    
    <span style="font-size:20px;">&#128203;</span><br>Paste
</button>
                    <div style="display:flex; flex-direction:column; justify-content:center;">
                        <button class="tool-btn small-btn" onclick="document.execCommand('cut')" title="Cut">&#9986; Cut</button>
                        <button class="tool-btn small-btn" onclick="document.execCommand('copy')" title="Copy">&#128196; Copy</button>
                          <div style="display:flex; flex-direction:column; justify-content:center;">
                       
                    </div>
                    </div>
                </div>
                
                
                
                <div class="separator"></div>

<button id="btnUndo" class="tool-btn" 
    onmousedown="event.preventDefault();" 
    onclick="undo()" 
    title="ย้อนกลับการกระทำล่าสุด (Ctrl+Z)" 
   >
    
    <span style="font-size:24px;">&#8634;</span><br> 
    <span style="font-size:12px;">ย้อนกลับ</span>
</button>

<button id="btnRedo" class="tool-btn" 
    onmousedown="event.preventDefault();" 
    onclick="redo()" 
    title="ทำซ้ำการกระทำที่เพิ่งย้อนกลับ (Ctrl+Y)" 
>
    
    <span style="font-size:24px;">&#8635;</span><br>
    <span style="font-size:12px;">ทำซ้ำ</span>
</button>


                
                <div class="separator"></div>
<button id="btnMemory" class="tool-btn" 
    onmousedown="event.preventDefault();" 
    onclick="toggleMemory()" 
    title="ดูดรูปแบบ (Pick Style)" 
   >
    
    <span id="iconMemory" style="font-size:24px;">&#129658;</span><br> 
    <span id="txtMemory" style="font-size:12px;">ดูด รูปเเบบ</span>
</button>
           




<button id="btnApply" class="tool-btn" 
    onmousedown="event.preventDefault();" 
    onclick="applyStyle()" 
    title="ทาสีรูปแบบ (Paint Style)" 
    style=" opacity: 0.5; cursor: not-allowed;" disabled>
    
    <span style="font-size:24px;">&#128396;</span><br>
    <span style="font-size:12px;">วางรูปเเบบ</span>
</button>
           <div class="separator"></div>
           
           
           
                <div class="ribbon-section">
                    <div style="margin-bottom:2px;">
                        <select class="ribbon-select" id="fontNameSelect" onchange="formatDoc('fontName', this.value)" style="width:110px;">
                            <option value="Segoe UI">Segoe UI</option>
                            <option value="Arial">Arial</option>
                            <option value="Times New Roman">Times New Roman</option>
                            <option value="Courier New">Courier New</option>
                           <option value="THSarabunNew">THSarabunNew</option>
                           <option value="THSarabunNew-Bold">THSarabunNew-Bold</option>
                            
                        </select>
                      <select class="ribbon-select" id="fontSizeSelect" onchange="setFontSize(this.value)" style="width:60px;">
    <option value="8pt">8</option>
    <option value="10pt" selected>10</option>
        <option value="11pt">11</option>
    <option value="12pt">12</option>
    <option value="14pt">14</option>
    <option value="16pt" >16</option> <option value="18pt">18</option>
    <option value="20pt">20</option>
    <option value="22pt">22</option>
    <option value="24pt">24</option>
    <option value="30pt">30</option>
    <option value="32pt">32</option>
    <option value="36pt">36</option>
</select>
                         
                    </div>
                    <div style="display:flex;">
                        <button class="tool-btn" onclick="formatDoc('bold')" title="Bold"><b>B</b></button>
                        <button class="tool-btn" onclick="formatDoc('italic')" title="Italic"><i>I</i></button>
                        <button class="tool-btn" onclick="formatDoc('underline')" title="Underline"><u>U</u></button>
                        <button class="tool-btn" onclick="formatDoc('strikeThrough')" title="Strikethrough"><s>S</s></button>
                        <div class="separator-small"></div>
                        <button class="tool-btn" onclick="formatDoc('subscript')" title="Sub">X<sub>2</sub></button>
                        <button class="tool-btn" onclick="formatDoc('superscript')" title="Super">X<sup>2</sup></button>
                    </div>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                    <div style="display:flex; margin-bottom:2px;">
                        <button class="tool-btn" onclick="formatDoc('insertUnorderedList')" title="Bullets">• List</button>
                        <button class="tool-btn" onclick="formatDoc('insertOrderedList')" title="Numbering">1. List</button>
                    </div>
                 <div style="display:flex;">
    <button class="tool-btn" onclick="alignContent('left')" title="Align Left">&#8801;</button>
    <button class="tool-btn" onclick="alignContent('center')" title="Center">&#8801;</button>
    <button class="tool-btn" onclick="alignContent('right')" title="Align Right">&#8801;</button>
    <button class="tool-btn" onclick="formatDoc('justifyFull')" title="Justify">&#8803;</button>
</div>

<div style="display:flex; margin-top:2px;">
    <button class="tool-btn small-btn" onclick="verticalAlignContent('top')" title="ชิดบน">Top</button>
    <button class="tool-btn small-btn" onclick="verticalAlignContent('middle')" title="กึ่งกลางแนวตั้ง">Mid</button>
    <button class="tool-btn small-btn" onclick="verticalAlignContent('bottom')" title="ชิดล่าง">Bot</button>
</div>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                     <div class="color-tool-container" style="flex-direction: column; align-items:center;">
                         <div style="display:flex; gap:2px;">
                                 <div class="tool-btn-wrapper">  
                             <div class=" ribbon-select color-input-wrapper" style="width:20px; height:20px; margin-top:1px;"><input type="color" id="hilite-color-picker"  ></div>
                                  </div>
                             <div class="tool-btn-wrapper">
                                 <button class="tool-btn" onclick="applyCurrentColor('fontcolor')"><b >A</b></button>
                             
                             </div>
                              
                                
                             <div class="tool-btn-wrapper">
                                 <button class="tool-btn" onclick="applyCurrentColor('hilite')"><b >ab</b></button>
                             
                             </div>
                         </div>
                         <span style="font-size:9px; color:#666; margin-top:2px;">Color</span>
                     </div>
                     <button class="tool-btn" onclick="formatDoc('removeFormat')" title="Clear Formatting" style="margin-left:5px;">
                        <span style="font-size:16px; color:red;">&times;</span><br><span style="font-size:9px;">Clear</span>
                     </button>
                </div>   
       <button onclick="runTest()" style="display:none">กดเพื่อแทนที่ข้อมูล</button>    </div>
       

            <div id="insert-tools" class="ribbon-group">
                
                <div class="ribbon-section">
                    <button class="tool-btn" onclick="insertColumnBreak()" title="Column Break">
                        <span style="font-size:18px;">&#8615;</span><br>Break
                    </button>
                    <button class="tool-btn" onclick="openTableModal()" title="Insert Table">
                        <span style="font-size:18px;">&#8862;</span><br>Table
                    </button>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                    <button class="tool-btn" onclick="document.getElementById('img-upload').click()">
                        <span style="font-size:18px;">&#128444;</span><br>Picture
                    </button>
                    <input type="file" id="img-upload" hidden accept="image/*" onchange="insertImageFile(this)">
                    
                    <button class="tool-btn" onclick="insertCanvas()">
                        <span style="font-size:18px;">&#9638;</span><br>Canvas
                    </button>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                    <div style="display:grid; grid-template-columns: repeat(4, 1fr); gap:2px;">
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('rect')" title="Rectangle">⬜</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('circle')" title="Circle">⚪</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('triangle')" title="Triangle">🔺</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('textbox')" title="Text Box"><b>T</b></button>
                        
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('arrow-up')">⬆</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('arrow-down')">⬇</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('arrow-left')">⬅</button>
                        <button class="tool-btn small-btn" onclick="addShapeToCanvas('arrow-right')">➜</button>
                    </div>
                </div>
                <div class="separator"></div>
                
                <div class="ribbon-section">
                     <div style="display:flex;">
                        <button class="tool-btn" onclick="addShapeToCanvas('emoji-star')">⭐</button>
                        <button class="tool-btn" onclick="addShapeToCanvas('emoji-heart')">❤️</button>
                        <button class="tool-btn" onclick="addShapeToCanvas('emoji-check')">✅</button>
                        <button class="tool-btn" onclick="addShapeToCanvas('emoji-smile')">😊</button>
                     </div>
                     <span style="font-size:9px; color:#666; display:block; text-align:center;">Icons</span>
                </div>
            </div>

            <div id="layout-tools" class="ribbon-group">
                <div class="ribbon-section">
                    <div style="display:flex;">
                         <button class="tool-btn" onclick="setPaperSize('A4')">
                            <span style="font-size:18px;">&#128196;</span><br>Size
                         </button>
                         <div style="display:flex; flex-direction:column; margin-left:5px;">
                            <button class="tool-btn small-btn" onclick="setPageOrientation('portrait')">&#128196; Portrait</button>
                            <button class="tool-btn small-btn" onclick="setPageOrientation('landscape')">&#128196; Landscape</button>
                         </div>
                    </div>
                    <span style="font-size:9px; color:#666; display:block; text-align:center;">Page Setup</span>
                </div>
                <div class="separator"></div>
                
                <div class="ribbon-section">
                    <span style="font-size:9px; display:block; margin-bottom:2px;">Columns</span>
                    <div style="display:flex;">
                        <button class="tool-btn" onclick="setColumns(1)">1</button>
                        <button class="tool-btn" onclick="setColumns(2)">2</button>
                        <button class="tool-btn" onclick="setColumns(3)">3</button>
                    </div>
                </div>
                
                <div class="separator"></div>
                
                <div class="ribbon-section">
                    <span style="font-size:9px; display:block; margin-bottom:2px;">Zoom (ย่อ/ขยายจอ)</span>
                    <div style="display:flex; align-items: center; gap: 2px;">
                        <button type="button" class="tool-btn" onclick="changeZoom(-10)" title="Zoom Out">-</button>
                        
                        <span id="zoom-level-text" style="min-width: 40px; text-align: center; font-size: 13px; font-weight: bold; color: #333;">100%</span>
                        
                        <button type="button" class="tool-btn" onclick="changeZoom(10)" title="Zoom In">+</button>
                        
                        <button type="button" class="tool-btn" onclick="setZoom(100)" style="margin-left: 4px; font-size: 11px;">Reset</button>
                    </div>
                </div>
            </div>

            <div id="table-design-tools" class="ribbon-group">
                <div class="ribbon-section">
                    <span style="font-size:9px; display:block;">Border Styles</span>
                    <div style="display:flex; gap:5px; margin-bottom:2px;">
                        <select onchange="setBorderWidth(this.value)" class="ribbon-select" style="width:60px;">
                             <option value="0px">0px</option>
                            <option value="1px">1px</option>
                            <option value="2px">2px</option>
                            <option value="4px">4px</option>
                        </select>
                        <select onchange="setBorderStyle(this.value)" class="ribbon-select" style="width:70px;">
                            <option value="solid">Solid</option>
                            <option value="dashed">Dashed</option>
                            <option value="dotted">Dotted</option>
                            <option value="double">Double</option>
                        </select>
                         <div class="color-input-wrapper" style="width:20px; height:20px; margin-top:1px;">
                            <input type="color" id="border-color-picker" value="#000000" title="Border Color">
                        </div>
                    </div>
                    <div style="display:flex;">
                         <button class="tool-btn" onclick="applyBorder('all')" title="All Borders">&#9638;</button>
                         <button class="tool-btn" onclick="applyBorder('none')" title="No Border">&#9633;</button>
                         <button class="tool-btn" onclick="applyBorder('top')" title="Top Border">&#8254;</button>
                         <button class="tool-btn" onclick="applyBorder('bottom')" title="Bottom Border">_</button>
                         <button class="tool-btn" onclick="applyBorder('left')" title="Left Border">&#124;</button>
                         <button class="tool-btn" onclick="applyBorder('right')" title="Right Border">&#124;</button>
                              <button class="tool-btn" onclick="applyTh()" title="Right Border">[th]</button>
                     
                    </div>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                    <span style="font-size:9px;">Shading</span>
                    <div style="display:flex; justify-content:center; margin-top:5px;">
                        <div class="tool-btn-wrapper">
                             <button class="tool-btn" onclick="applyCurrentColor('bgcolor')"><span style="font-size:16px;">&#128719;</span></button>
                             <div class="mini-color-bar"><input type="color" id="bg-color-picker" onchange="applyCurrentColor('bgcolor')"></div>
                        </div>
                    </div>
                </div>
                <div class="separator"></div>

                <div class="ribbon-section">
                    <span style="font-size:9px;">Rows & Columns</span>
                    <div style="display:grid; grid-template-columns: 1fr 1fr; gap:2px; margin-top:2px;">
                        <button class="tool-btn small-btn" onclick="tableModify('insertRowBelow')">+ Row</button>
                        <button class="tool-btn small-btn" onclick="tableModify('insertColRight')">+ Col</button>
                        <button class="tool-btn small-btn" onclick="tableModify('deleteRow')" style="color:red;">- Row</button>
                        <button class="tool-btn small-btn" onclick="tableModify('deleteCol')" style="color:red;">- Col</button>
                    </div>
                </div>
                <div class="separator"></div>
               <div class="ribbon-section">
    <button class="tool-btn" onclick="selectTableForResize()">
        <span style="font-size:16px;">&#8694;</span><br>Size
    </button>
    <div style="display:flex; flex-direction:column; gap:2px; margin-top:2px;">
        <div style="display:flex; gap: 2px;">
            <button class="tool-btn small-btn" onclick="resizeCell('w', 10)">W+</button>
            <button class="tool-btn small-btn" onclick="resizeCell('w', -10)">W-</button>
            <button class="tool-btn small-btn" onclick="resizeCell('h', 10)">H+</button>
            <button class="tool-btn small-btn" onclick="resizeCell('h', -10)">H-</button>
        </div>
        <div style="display:flex; gap: 2px;">
            <button class="tool-btn small-btn" onclick="resizeCell('p', 5)">Pad+</button>
            <button class="tool-btn small-btn" onclick="resizeCell('p', -5)">Pad-</button>
            <button class="tool-btn small-btn" style="width:auto; padding:0 5px;" onclick="clearCellTags()">ล้าง Tag</button>
               <button class="tool-btn small-btn" style="width:auto; padding:0 5px;" onclick="fixAllMessyTables()">ซ่อมแซมตาราง</button>
        </div>
    </div>
    <button class="tool-btn small-btn" onclick="mergeSelectedCells()">ผสานเซลล์ (Merge)</button>
      <button class="tool-btn small-btn" onclick="unmergeSelectedCells()">ยกเลิกผสานเซลล์ (Merge)</button>
        <button class="tool-btn small-btn" onclick="autoFitRowHeight()">พอดีเนื้อหา</button>
</div>




            </div>

        </div> </div>
       
   
      
    <div class="document-area" id="document-area"  onmouseup="syncToolbar()" 
     onkeyup="syncToolbar()">
        <div id="resizer-frame"><div class="resize-handle handle-se" onmousedown="startResize(event)"></div></div>
        
        <div id="page-1" class="document-page page-size-A4" >
   
            <div class="content-wrapper" contenteditable="true" spellcheck="false"">
           
  
            </div>
            <div class="page-number-watermark">1</div>
        </div>
    </div>
    <div class="status-bar" style="display:none"><div id="page-status">Page: 1/1</div><div>Zoom: 100%</div></div>
    
    
</div>

<div id="tableModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">Insert Table</div>
        <div class="modal-body"><label>Cols:</label><input type="number" id="table-cols" value="3"><label>Rows:</label><input type="number" id="table-rows" value="3"></div>
        <div class="modal-footer"><button class="btn-secondary" onclick="closeTableModal()">Cancel</button><button class="btn-primary" onclick="insertTable()">Insert</button></div>
    </div>
</div>



  <script>



  loadword();

var url = "${url}";
  var currentAutoId = "${mydoc.doc_autoid}"; // ดึง ID มาเก็บไว้ก่อน

  if(url != ""){
      // 1. ลองโหลดจากที่เซฟไว้ในเครื่องก่อน
      let isDraftLoaded = loadDraftFromLocal(currentAutoId);
      
      // 2. ถ้า "ไม่มี" Draft ในเครื่อง (isDraftLoaded เป็น false) ค่อยรันโค้ดข้อมูลใหม่
      if(!isDraftLoaded) {
          console.log("ไม่มีค่าที่เซฟไว้ กำลังสร้างหน้าใหม่...");
          runword(); 
      } else {
          console.log("โหลดข้อมูลที่จัดหน้าไว้เรียบร้อยแล้ว");
      }
  }

  <c:set var="fullSalesperson" value="${transaction.salesperson}" />
	  <c:set var="cleanSalesperson" value="${fn:substringAfter(fullSalesperson, '_')}" />
	  
		  <c:if test="${not empty fullSalesperson}">
	    
	    <%-- 2. หั่นออกมาเป็น Array --%>
	    <c:set var="parts" value="${fn:split(fullSalesperson, '_')}" />
	    
	    <%-- 3. เช็คอีกนิดว่าหั่นแล้วได้ของออกมาไหม (Array ไม่ว่าง) --%>
	    <c:if test="${fn:length(parts) > 0}">
	        <c:set var="cleanId" value="${parts[0]}" />
	    </c:if>
	    
	</c:if>
	  
  function runword(){
  var realData = {
		  "doc_date": `${transaction['followup_date']}`,
		    
		    // รวมรหัสเอกสาร (ติดกันไม่มีช่องว่าง)
		    "doc_code": cleanJoin('', `${mydoc.doc_type}`, `${mydoc.doc_date}`, `${mydoc.doc_autoid}`),
		    
		    "doc_rv": `${mydoc.doc_rv}`,
		    
		    // รวมรหัสอ้างอิง (ติดกันไม่มีช่องว่าง)
		    "doc_code_refer": cleanJoin('', `${mydocrefer.doc_type}`, `${mydocrefer.doc_date}`, `${mydocrefer.doc_autoid}`),
		    
		    "salesperson": `${cleanSalesperson}`,
		    
		    // รวมรหัสทะเบียน (คั่นด้วย - เฉพาะเมื่อมี register_id)
		    "register_code": cleanJoin('-', cleanJoin('', `${register.register_code}`, `${register.register_daterun}`), `${register.register_id}`),
		    
		    // รวมชื่อ (คั่นด้วย 1 ช่องว่าง)
		    "register_name": cleanJoin(' ', `${register.register_title}`, `${register.register_name}`),
		    
		    "contact_person": `${register.contact_person}`,
		    
		    // รวมที่อยู่ (ขึ้นบรรทัดใหม่ <br> เฉพาะเมื่อมีข้อมูลทั้งสองส่วน)
		    "register_address_full": cleanJoin('<br>&nbsp;&nbsp;', `${register.register_address}`, `${register.register_fulladdress}`),
		    
		    // รวมเบอร์โทร (คั่นด้วยช่องว่าง)
		    "phone_number": cleanJoin(' ', `${register.phone_number}`, `${register.mobile_number}`, `${register.person_number}`),
		    
		    // รวมอีเมล (คั่นด้วยช่องว่าง หรือเปลี่ยนเป็น ', ' ก็ได้ครับ)
		    "register_email": cleanJoin(' ', `${register.register_email}`, `${register.person_email}`),
	        
		   // "img_sign": "http://tracejob.trueddns.com:52890/tracejob/app/template/common/Screenshot%202021-08-03%20141406.png",
		    "img_logo": "/readdocument/system/15125499671819.png",
		    "img_sign": "/readdocument/system/${cleanId}.png",
		   
	        "NO":  [],
	        "t_1":[],
	        "t_2":[],
	        "t_3":[],
	        "t_4":[],
	        "t_5":[],
	        "t_6":[],
	        "t_7":[],
	        "t_8":[],
	        "t_9":[],
	        "t_10":[],
	        
	        "t_1D":[],
	        "t_2D":[],
	        "t_3D":[],
	        "t_4D":[],
	        "t_5D":[],
	        "t_6D":[],
	        "t_7D":[],
	        "t_8D":[],
	        "t_9D":[],
	        "t_10D":[],
	        "t_1M":[],
	        "t_2M":[],
	        "t_3M":[],
	        "t_4M":[],
	        "t_5M":[],
	        "t_6M":[],
	        "t_7M":[],
	        "t_8M":[],
	        "t_9M":[],
	        "t_10M":[],
	        
	        "grand_tD": "${transaction['grand_total']+0}",
	        "grand_dD": "${transaction['grand_discount']+0}",
	        "grand_t2D": "${transaction['grand_total2']+0}",
	        "vatD": "${transaction['vat']+0}",
	        "net_aD": "${transaction['net_amount']+0}",
	        
	        "promotion_info": `${transaction['promotion_info']}`,
	        "Paymentterms": "${PaymenttermsText}",
	    };
  

  <c:forEach items="${subDataList}" var="subData" varStatus="rowStatus">
  
  // push ลำดับที่ (NO)
  realData.NO.push('${rowStatus.count}'); 

  <c:forEach items="${subeformfields}" var="row" varStatus="colStatus">
      
      <c:set var="key" value="data_value${row.field_postion}" scope="page"/>
      
      if (!realData['table_${colStatus.count}']) {
           realData['table_${colStatus.count}'] = []; // ป้องกัน error ถ้า array ยังไม่ถูกสร้าง
      }
      
      realData['t_${colStatus.count}'].push('${subData[key]}');
      realData['t_${colStatus.count}D'].push('${subData[key]}');
      realData['t_${colStatus.count}M'].push('${subData[key]}');
  </c:forEach>

</c:forEach>
  
	  /*
  // วนลูปยัดข้อมูล 100 รอบ
  for (let i = 1; i <= 100; i++) {
      testData.name.push(`พนักงาน สมชาย ลำดับที่ ${i}`); 
      testData.score.push(Math.floor(Math.random() * 1000)); // สุ่มคะแนน
  }
  */

  const formattedURL = url.replace("Idoc:/", "/document-filemanager/");
 	loadDocumentFromURL(formattedURL,realData);
  //setReadOnlyMode(true);
  }
  
  
  
  function cleanJoin(separator, ...args) {
	    return args
	        .map(arg => String(arg).trim()) // แปลงเป็น String และตัดช่องว่าง
	        .filter(arg => arg !== '' && arg !== 'null' && arg !== 'undefined') // กรองค่าที่ไม่ต้องการออก
	        .join(separator); // รวมด้วยตัวคั่นที่กำหนด
	}
  
  
  loadDraftFromLocal("${mydoc.doc_autoid}");
  

  </script> 
  

</body>
</html>