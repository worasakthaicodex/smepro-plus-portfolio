<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>


    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WPF Enterprise ERP - Task Inbox</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        /* =========================================
           [1] WPF CORE SYSTEM STYLES
           ========================================= */
        :root {
            --wpf-purple: #4c4391;
            --wpf-light-bg: #f0f0f0;
            --wpf-panel-bg: #ffffff;
            --wpf-border: #999999;
            --wpf-header-grad: linear-gradient(to bottom, #f8f8f8, #e5e5e5);
            --wpf-selected: #0078d7;
            --wpf-hover: #e5f3ff;
        }
.material-icons.sorted{
font-size:14px;
}
   
          #wpf-app-frame body,  #wpf-app-frame  html { 
          margin: 0; padding: 0; height: 100vh; background-color: #2b2b2b; display: flex; justify-content: center; align-items: center; overflow: hidden;
       
           }

        /* Window Container */
        #wpf-app-frame {
         box-sizing: border-box; font-family: 'Segoe UI', 'Sarabun', sans-serif;
            width: 100%; height: 92%; background: var(--wpf-light-bg);
            border: 1px solid #555; display: flex; flex-direction: column;
            box-shadow: 0 10px 40px rgba(0,0,0,0.5); position: relative;
               cursor: default !important; 
        }

        /* [2] TITLE BAR (Windows Style) */
        .wpf-title-bar {
            height: 30px; background: var(--wpf-purple); color: white;
            display: flex; justify-content: space-between; align-items: center;
            padding: 0 10px; font-size: 12px;
        }
        .sys-controls i { margin-left: 15px; cursor: pointer; opacity: 0.8; }

        /* [3] MENU & RIBBON BAR */
        .wpf-menu-bar { height: 25px; background: #fff; border-bottom: 1px solid #ccc; display: flex; align-items: center; padding: 0 10px; font-size: 11px; gap: 15px; }
        .wpf-menu-item { cursor: pointer; padding: 2px 5px; }
        .wpf-menu-item:hover { background: #ddd; }

        .wpf-ribbon {
            height: 75px; background: #fff; border-bottom: 1px solid var(--wpf-border);
            display: flex; align-items: center; padding: 0 15px; gap: 5px;
        }
        .ribbon-group { display: flex; flex-direction: column; align-items: center; border-right: 1px solid #ddd; padding: 0 15px; height: 80%; justify-content: center; }
        .ribbon-btn { display: flex; flex-direction: column; align-items: center; cursor: pointer; min-width: 50px; gap: 4px; }
        .ribbon-btn i { font-size: 20px; color: #444; }
        .ribbon-btn span { font-size: 10px; color: #333; }
        .ribbon-btn:hover { background: var(--wpf-hover);  }

        /* [4] MAIN CONTENT AREA */
        .wpf-main-area { display: flex; flex: 1; overflow: hidden; }

        /* Sidebar TreeView */
        .wpf-sidebar { width: 260px; background: #fff; border-right: 1px solid var(--wpf-border); overflow-y: auto; }
        .sidebar-header { padding: 8px 12px; background: #eee; font-size: 11px; font-weight: bold; border-bottom: 1px solid #ccc; }
        .tree-node { padding: 6px 15px; font-size: 13px; cursor: pointer; display: flex; align-items: center; gap: 8px; }
        .tree-node i { color: #d4af37; }
        .tree-node:hover { background: var(--wpf-hover); }
        .tree-node.active { background: #cce8ff; font-weight: bold; }

        /* Workspace & DataGrid */
        .wpf-workspace { flex: 1; display: flex; flex-direction: column; padding: 10px; gap: 10px; }
        
        /* Search/Filter GroupBox (สไตล์ WPF GroupBox) */
        .wpf-group-box {
            border: 1px solid #bbb; padding: 15px 10px 10px 10px; position: relative; margin-top: 5px;
        }
        .group-box-label {
            position: absolute; top: -10px; left: 10px; background: var(--wpf-light-bg);
            padding: 0 5px; font-size: 11px; font-weight: bold; color: #555;
        }
        .filter-controls { display: flex; gap: 15px; align-items: center; }
        .filter-controls input { padding: 4px; border: 1px solid #999; width: 250px; font-size: 12px; }

        /* WPF DataGrid Styling */
        .datagrid-container { flex: 1; background: #fff; border: 1px solid var(--wpf-border); overflow: auto;max-height:90% !important; }
        .wpf-datagrid { width: 100%; border-collapse: collapse; font-size: 12px; }
        .wpf-datagrid thead th {
            position: sticky; top: 0; background: var(--wpf-header-grad);
            border-right: 1px solid #ccc; border-bottom: 1px solid #999;
            padding: 8px; text-align: left; font-weight: normal; color: #333;
        }
        .wpf-datagrid tbody td { padding: 6px 8px; border-right: 1px solid #eee; border-bottom: 1px solid #eee; color: #333; }
        
        /* Alternating Rows */
        .wpf-datagrid tbody tr:nth-child(even) { background-color: #f9f9f9; }
        
        /* Selected Row */
        .wpf-datagrid tbody tr:hover { background-color: #e5f3ff; cursor: default; }
        .wpf-datagrid tbody tr.selected { background-color: var(--wpf-selected) !important; color: white !important; }
        .wpf-datagrid tbody tr.selected td { color: white !important; border-right: 1px solid #005a9e; }

        /* Badges in Table */
        .wpf-badge { padding: 2px 8px; border-radius: 2px; font-size: 10px; border: 1px solid #999; }
        .status-wait { background: #fff3cd; }
        .status-process { background: #d1ecf1; }

        /* [5] STATUS BAR */
        .wpf-status-bar {
            height: 25px; background: #eee; border-top: 1px solid #ccc;
            display: flex; align-items: center; padding: 0 10px; font-size: 11px; gap: 20px;
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



        #wpf-app-frame.dark-mode {
            --wpf-light-bg: #1e1e1e;
            --wpf-panel-bg: #2d2d30;
            --wpf-border: #444444;
            --wpf-header-grad: linear-gradient(to bottom, #3e3e42, #2d2d30);
            --wpf-selected: #005a9e;
            --wpf-hover: #3e3e42;
            --text-main: #d4d4d4;
            
            background: var(--wpf-light-bg);
            color: var(--text-main);
        }

        /* เปลี่ยนสีพื้นหลังและตัวหนังสือหลัก (อยู่แค่ในแอปนี้) */
        #wpf-app-frame.dark-mode .wpf-menu-bar,
        #wpf-app-frame.dark-mode .wpf-ribbon,
        #wpf-app-frame.dark-mode .wpf-sidebar,
        #wpf-app-frame.dark-mode .datagrid-container,
        #wpf-app-frame.dark-mode .wpf-workspace {
            background: var(--wpf-panel-bg);
            border-color: var(--wpf-border);
            color: var(--text-main);
        }

        /* เปลี่ยนสีตัวหนังสือต่างๆ */
        #wpf-app-frame.dark-mode .tree-node,
        #wpf-app-frame.dark-mode .ribbon-btn span,
        #wpf-app-frame.dark-mode .group-box-label,
        #wpf-app-frame.dark-mode label,
        #wpf-app-frame.dark-mode .wpf-datagrid tbody td,
        #wpf-app-frame.dark-mode .wpf-status-bar {
            color: var(--text-main) ;
        }

        /* เปลี่ยนสีไอคอนให้สว่างขึ้น */
        #wpf-app-frame.dark-mode .ribbon-btn i,
        #wpf-app-frame.dark-mode .tree-node i.fa-folder-open,
        #wpf-app-frame.dark-mode .tree-node i.fa-file-lines {
            color: #ccc;
        }

        /* จัดการตาราง (DataGrid) */
        #wpf-app-frame.dark-mode .wpf-datagrid thead th {
            background: var(--wpf-header-grad);
            color: #fff;
            border-color: var(--wpf-border);
        }
        #wpf-app-frame.dark-mode .wpf-group-box {
          background: #252526;
            border-color: var(--wpf-border); 
        }
      
        
          #wpf-app-frame.dark-mode   .ribbon-group {  
           border-color: var(--wpf-border); 
           }
        
        
        
        #wpf-app-frame.dark-mode .wpf-datagrid tbody tr:nth-child(even) { background-color: #252526; }
        #wpf-app-frame.dark-mode .wpf-datagrid tbody tr:hover { background-color: var(--wpf-hover); color: #fff;}
        #wpf-app-frame.dark-mode .wpf-datagrid tbody td { border-color: #444; }
        
        /* เมนูและสถานะ */
        #wpf-app-frame.dark-mode .sidebar-header,
        #wpf-app-frame.dark-mode .wpf-status-bar { background: #252526; border-color: var(--wpf-border); }
        #wpf-app-frame.dark-mode .tree-node.active { background: var(--wpf-selected); color: white; }
        
        /* Input & Select */
        #wpf-app-frame.dark-mode input, #wpf-app-frame.dark-mode select {
            background: #333; color: white; border: 1px solid var(--wpf-border);
        }
        /* =========================================
           [DARK MODE STYLES - แก้ไขปุ่มและ Loading]
           ========================================= */

        /* 1. จัดการปุ่ม btn-hover-effect และกล่องลิงก์ด้านใน */
      #wpf-app-frame.dark-mode .btn-hover-effect,
       #wpf-app-frame.dark-mode .btn-hover-effect a {
            background-color: transparent !important; /* ลบพื้นหลังสีขาวออก */
            color: var(--text-main);
        }

        /* เสริม: ถ้าอยากให้ตอนเอาเมาส์ชี้ (Hover) มีสีพื้นหลังจางๆ ในโหมดมืด */
       #wpf-app-frame.dark-mode .btn-hover-effect a:hover {
            background-color: rgba(255, 255, 255, 0.1) !important;
            border-radius: 4px;
        }

        /* 2. จัดการ Loading Overlay ให้มืดตาม */
      #wpf-app-frame.dark-mode .loading-overlay {
            background: rgba(40, 40, 40, 0.85); /* เปลี่ยนจากสีขาวเป็นสีเทาเข้มแบบโปร่งแสง */
        }

        /* 3. จัดการสีของวงแหวนโหลด (Spinner) */
       #wpf-app-frame.dark-mode .loader-spinner {
            border: 4px solid #444444; /* เส้นวงแหวนพื้นฐานให้มืดลง */
            border-top: 4px solid #3498db; /* เส้นหมุนๆ สีฟ้าเหมือนเดิม */
        }

        /* 4. จัดการข้อความ "กำลังโหลด..." */
        #wpf-app-frame.dark-mode .loading-text {
            color: #d4d4d4; /* เปลี่ยนข้อความเป็นสีเทาอ่อน/ขาว ให้ตัดกับพื้นหลังมืด */
        }

        
        
        
        
        
        
        
        
        
        
        
        /* =========================================
   [VS CODE BLUE THEME - โทนน้ำเงินโปรแกรมเมอร์]
   ========================================= */

/* 1. กำหนดตัวแปรสีโทน Navy Blue */
#wpf-app-frame.vscode-blue {
    --wpf-light-bg: #041427; /* พื้นหลังหลัก (สีกรมท่าเข้มสุด) */
    --wpf-panel-bg: #0b1d32; /* กล่องและพาเนล (สีกรมท่าสว่างขึ้นนิด) */
    --wpf-border: #163250; /* เส้นขอบ (สีน้ำเงินตุ่นๆ) */
    --wpf-header-grad: linear-gradient(to bottom, #112d4e, #0b1d32); /* ไล่สีหัวตาราง */
    --wpf-selected: #007acc; /* สีน้ำเงินไฮไลท์ VS Code */
    --wpf-hover: #163250; /* สีเวลา Hover */
    --text-main: #d4d4d4; /* ตัวหนังสือสีเทาอ่อน */
    
    background: var(--wpf-light-bg);
    color: var(--text-main);
}

/* 2. เปลี่ยนสีพื้นหลังและตัวหนังสือหลัก */
#wpf-app-frame.vscode-blue .wpf-menu-bar,
#wpf-app-frame.vscode-blue .wpf-ribbon,
#wpf-app-frame.vscode-blue .wpf-sidebar,
#wpf-app-frame.vscode-blue .datagrid-container,
#wpf-app-frame.vscode-blue .wpf-workspace {
    background: var(--wpf-panel-bg);
    border-color: var(--wpf-border);
    color: var(--text-main);
}

/* 3. เปลี่ยนสีตัวหนังสือและไอคอน */
#wpf-app-frame.vscode-blue .tree-node,
#wpf-app-frame.vscode-blue .ribbon-btn span,
#wpf-app-frame.vscode-blue .group-box-label,
#wpf-app-frame.vscode-blue label,
#wpf-app-frame.vscode-blue .wpf-datagrid tbody td,
#wpf-app-frame.vscode-blue .wpf-status-bar,
#wpf-app-frame.vscode-blue .ribbon-btn i,
#wpf-app-frame.vscode-blue .tree-node i.fa-folder-open,
#wpf-app-frame.vscode-blue .tree-node i.fa-file-lines {
    color: var(--text-main);
}

/* 4. จัดการตาราง (DataGrid) และ Group Box (แก้จุดที่เคย Hardcode สีดำ) */
#wpf-app-frame.vscode-blue .wpf-datagrid thead th {
    background: var(--wpf-header-grad);
    color: #fff;
    border-color: var(--wpf-border);
}
#wpf-app-frame.vscode-blue .wpf-group-box,
#wpf-app-frame.vscode-blue .wpf-datagrid tbody tr:nth-child(even),
#wpf-app-frame.vscode-blue .sidebar-header,
#wpf-app-frame.vscode-blue .wpf-status-bar {
    background: #0a1a2a; /* สีกรมท่าลึกแทนสี #252526 */
    border-color: var(--wpf-border); 
}
#wpf-app-frame.vscode-blue .ribbon-group {  
    border-color: var(--wpf-border); 
}
#wpf-app-frame.vscode-blue .wpf-datagrid tbody tr:hover { 
    background-color: var(--wpf-hover); 
    color: #fff;
}
#wpf-app-frame.vscode-blue .wpf-datagrid tbody td { 
    border-color: var(--wpf-border); 
}
#wpf-app-frame.vscode-blue .tree-node.active { 
    background: var(--wpf-selected); 
    color: white; 
}

/* 5. Input & Select (แก้สีพื้นหลังช่องกรอกข้อมูล) */
#wpf-app-frame.vscode-blue input, 
#wpf-app-frame.vscode-blue select {
    background: #06182c; /* พื้นหลัง Input โทนน้ำเงินดำ */
    color: white; 
    border: 1px solid var(--wpf-border);
}
#wpf-app-frame.vscode-blue input:focus, 
#wpf-app-frame.vscode-blue select:focus {
    border-color: var(--wpf-selected);
    outline: none;
}

/* 6. ปุ่ม Hover และลิงก์ */
#wpf-app-frame.vscode-blue .btn-hover-effect,
#wpf-app-frame.vscode-blue .btn-hover-effect a {
    background-color: transparent !important; 
    color: var(--text-main);
}
#wpf-app-frame.vscode-blue .btn-hover-effect a:hover {
    background-color: rgba(255, 255, 255, 0.05) !important;
    border-radius: 4px;
}

/* 7. Loading Overlay ให้มืดแบบกลืนไปกับตีมน้ำเงิน */
#wpf-app-frame.vscode-blue .loading-overlay {
    background: rgba(4, 20, 39, 0.85); 
}
#wpf-app-frame.vscode-blue .loader-spinner {
    border: 4px solid var(--wpf-border); 
    border-top: 4px solid var(--wpf-selected); 
}
#wpf-app-window.vscode-blue .loading-text {
    color: var(--text-main); 
}






/* =========================================
   [BROWN THEME - โทนน้ำตาลนุ่มนวลสบายตา]
   ========================================= */

/* 1. กำหนดตัวแปรสีโทน Brown Sepia */
#wpf-app-frame.brown-theme {
    --wpf-light-bg: #2b2622;      /* พื้นหลังหลัก (น้ำตาลเข้มกาแฟ) */
    --wpf-panel-bg: #362f2b;      /* กล่องและพาเนล (น้ำตาลไม้) */
    --wpf-border: #4d433d;        /* เส้นขอบ (น้ำตาลเทา) */
    --wpf-header-grad: linear-gradient(to bottom, #453c37, #362f2b); /* ไล่สีหัวตาราง */
    --wpf-selected: #8b5a2b;      /* สีน้ำตาลทองเวลาไฮไลท์ */
    --wpf-hover: #453c37;         /* สีเวลา Hover */
    --text-main: #e8dcc4;         /* ตัวหนังสือสีครีม Sepia (อ่านง่ายมาก) */
    
    background: var(--wpf-light-bg);
    color: var(--text-main);
}

/* 2. เปลี่ยนสีพื้นหลังและตัวหนังสือหลัก */
#wpf-app-frame.brown-theme .wpf-menu-bar,
#wpf-app-frame.brown-theme .wpf-ribbon,
#wpf-app-frame.brown-theme .wpf-sidebar,
#wpf-app-frame.brown-theme .datagrid-container,
#wpf-app-frame.brown-theme .wpf-workspace {
    background: var(--wpf-panel-bg);
    border-color: var(--wpf-border);
    color: var(--text-main);
}

/* 3. เปลี่ยนสีตัวหนังสือและไอคอน */
#wpf-app-frame.brown-theme .tree-node,
#wpf-app-frame.brown-theme .ribbon-btn span,
#wpf-app-frame.brown-theme .group-box-label,
#wpf-app-frame.brown-theme label,
#wpf-app-frame.brown-theme .wpf-datagrid tbody td,
#wpf-app-frame.brown-theme .wpf-status-bar,
#wpf-app-frame.brown-theme .ribbon-btn i,
#wpf-app-frame.brown-theme .tree-node i.fa-folder-open,
#wpf-app-frame.brown-theme .tree-node i.fa-file-lines {
    color: var(--text-main);
}

/* 4. จัดการตาราง (DataGrid) และ Group Box */
#wpf-app-frame.brown-theme .wpf-datagrid thead th {
    background: var(--wpf-header-grad);
    color: #f5ece0;
    border-color: var(--wpf-border);
}
#wpf-app-frame.brown-theme .wpf-group-box,
#wpf-app-frame.brown-theme .wpf-datagrid tbody tr:nth-child(even),
#wpf-app-frame.brown-theme .sidebar-header,
#wpf-app-frame.brown-theme .wpf-status-bar {
    background: #231f1c; /* สีน้ำตาลเข้มลึก */
    border-color: var(--wpf-border); 
}
#wpf-app-frame.brown-theme .ribbon-group {  
    border-color: var(--wpf-border); 
}
#wpf-app-frame.brown-theme .wpf-datagrid tbody tr:hover {  
    background-color: var(--wpf-hover);  
    color: #fff;
}
#wpf-app-frame.brown-theme .wpf-datagrid tbody td {  
    border-color: var(--wpf-border);  
}
#wpf-app-frame.brown-theme .tree-node.active {  
    background: var(--wpf-selected);  
    color: white;  
}

/* 5. Input & Select (พื้นหลังช่องกรอกข้อมูลโทนกาแฟเข้ม) */
#wpf-app-frame.brown-theme input, 
#wpf-app-frame.brown-theme select {
    background: #1e1a17; 
    color: #f5ece0;  
    border: 1px solid var(--wpf-border);
}
#wpf-app-frame.brown-theme input:focus, 
#wpf-app-frame.brown-theme select:focus {
    border-color: var(--wpf-selected);
    outline: none;
}

/* 6. ปุ่ม Hover และลิงก์ */
#wpf-app-frame.brown-theme .btn-hover-effect,
#wpf-app-frame.brown-theme .btn-hover-effect a {
    background-color: transparent !important; 
    color: var(--text-main);
}
#wpf-app-frame.brown-theme .btn-hover-effect a:hover {
    background-color: rgba(232, 220, 196, 0.05) !important;
    border-radius: 4px;
}

/* 7. Loading Overlay ให้มืดแบบกลืนไปกับตีมน้ำตาล */
#wpf-app-frame.brown-theme .loading-overlay {
    background: rgba(43, 38, 34, 0.85); 
}
#wpf-app-frame.brown-theme .loader-spinner {
    border: 4px solid var(--wpf-border); 
    border-top: 4px solid var(--wpf-selected); 
}
#wpf-app-window.brown-theme .loading-text {
    color: var(--text-main); 
}
</style>
<script>
        // ใช้ try-catch เผื่อไว้กันเหนียว
        try {
            var savedTheme = localStorage.getItem('ERP_THEME_${process.id}');
            if (savedTheme === 'dark') {
                // แปะคลาสที่ <html> แทน
                document.documentElement.classList.add('dark-mode'); 
            }
        } catch (e) {}
    </script>
    <script>
    // เปลี่ยนแค่ชื่อ containerId อย่างเดียว!
    setupDarkModeSystem({
        containerId: 'wpf-app-frame', 
        processId: '${process.id}',
        observerTargetId: 'microsofts${process.id}'
    });
</script>
</head>
<body >
  <div class="container"  >

   
   
  <div class="menus wpf-menu-bar">
   
   
   
   
   
   




      







      <div class="menu">
         <label>ตั้งค่า</label>
         <div class="submenu">
    
            <a  onclick="getData('/holiday/all', 'mainbodyiadmin');closeed('draggable-iadmin');set_title_app('app','I-Admin [วันหยุด]')" >
          <div class="submenu-item ">
    <spring:message code="app.Markasread"/>
          </div>
        </a>
        
         <a  onclick="toggleDarkMode('light','${process.id}')" >
          <div class="submenu-item ">
ธีมขาว
          </div>
        </a>    
               <a  onclick="toggleDarkMode('dark-mode','${process.id}')">
          <div class="submenu-item ">
ธีมมืด
          </div>
        </a>
               <a  onclick="toggleDarkMode('vscode-blue','${process.id}')" >
          <div class="submenu-item ">
ธีมน้ำเงิน
          </div>
        </a>
                  <a  onclick="toggleDarkMode('brown-theme','${process.id}')" >
          <div class="submenu-item ">
ธีมน้ำตาล
          </div>
        </a>  
     </div>
      </div>
  
  
      <div class="menu">
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
      </div></div>
   </div>   
<div id="wpf-app-frame" id="container${process.id}">
  

   
  
      

    <div class="wpf-ribbon">
        <div class="ribbon-group">
            <div class="ribbon-btn" onclick="openwindows('draggable-window3','New File','850px');getData('/appform-filemanager/${process.id}', 'mainbody3');">
                <i class="fa fa-circle-plus" style="color:#28a745"></i>
                <span>สร้างใหม่</span>
            </div>
        </div>
      <div class="ribbon-group">
    <div class="ribbon-btn" onclick="refreshData(this, '/app-filemanager/${process.id}?boxs=${boxs}', 'microsofts${process.id}')">
        <i class="fa fa-arrows-rotate" style="color:var(--wpf-selected)"></i>
        <span>รีเฟรช</span>
    </div>
</div>
        <div class="ribbon-group">
            <div class="ribbon-btn">
                <i class="fa fa-file-export"></i>
                <span>ส่งออก</span>
            </div>
        </div>
        <div class="ribbon-group" style="border:none;">
            <div class="ribbon-btn">
                <i class="fa fa-magnifying-glass-chart"></i>
                <span>รายงานสรุป</span>
            </div>
        </div>
    </div>




    <div class="wpf-main-area">
<aside class="wpf-sidebar" id="sidebar-${process.id }">
    <div class="sidebar-header">Navigation Pane</div>
    
    <div class="tree-node" style="cursor: default">
        <i class="fa fa-folder-open" style="cursor: default;"></i> กล่องงานของฉัน (${reviewed_count + transaction_sends.size() + draft_count+refer_count + 0})
    </div>
    
    <div class="tree-node"  style="padding-left:30px;cursor: default;" >
        <i class="fa fa-file-import"></i> งานขาเข้า (${reviewed_count+refer_count + 0})
    </div>
    <c:if test="${APP_JOIN != null }">
	    <div class="tree-node" id="in_refer" style="padding-left:50px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}?boxs=in_refer', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ เอกสารขาเข้า : ${APP_JOIN.name }]')">
	       <i class="fa fa-share-from-square"></i> งานจาก ${APP_JOIN.name } (${refer_count + 0})
	    </div>
    </c:if>
    
    
    <div class="tree-node" id="in" style="padding-left:50px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}?boxs=in', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ เอกสารขาเข้า]')">
        <i class="fa fa-file-lines"></i> คำขอทั่วไป(${reviewed_count })
    </div>
    <div class="tree-node active" style="padding-left:30px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [<spring:message code="btn.InProgress" text="-" />]')">
        <i class="fa fa-pen-to-square"></i> งานรอดำเนินการ (${draft_count + 0})
    </div>
    
    <div class="tree-node" id="sends" style="padding-left:30px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}?boxs=sends', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ส่งออก]')">
        <i class="fa fa-paper-plane"></i> งานส่งออก (${send_count + 0})
    </div>
    
    <div class="tree-node" style="cursor: default;">
        <i class="fa fa-folder-open" style="cursor: default;"></i> ประวัติเอกสาร
    </div>
    
    <div class="tree-node" id="refer" style="padding-left:30px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}?boxs=refer', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ที่เกี่ยวข้อง]')">
        <i class="fa fa-share-nodes"></i> ที่เกี่ยวข้อง
    </div>
    
    <div class="tree-node" id="completion" style="padding-left:30px;" onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');selectNode(this); getData('/app-filemanager/${process.id}?boxs=completion', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [เสร็จสิ้น]')">
        <i class="fa fa-circle-check"></i> เสร็จสิ้น
    </div>
    
    <div class="tree-node" id="trash" style="padding-left:30px;">
        <i class="fa fa-trash-can"></i> ถังขยะ
    </div>
</aside>
        <main class="wpf-workspace" id="wpf-workspace${process.id}">
            <div class="wpf-group-box">
                <div class="group-box-label">ตัวกรองข้อมูล (Filters)</div>
                <div class="filter-controls">
                    <label style="font-size:12px;" >ค้นหาด่วน:</label>
                    <input type="text" placeholder="พิมพ์ชื่อเอกสาร หรือ รหัส..." id="searchInput" onkeyup="searchTable()" >
                    <label style="font-size:12px;">สถานะ:</label>
                    <select style="font-size:12px; padding:2px;"><option>ทั้งหมด</option></select>
                    <button style="font-size:12px; cursor:pointer;">ค้นหา</button>
                </div>
            </div>

            <div class="datagrid-container">
             
                <table class="wpf-datagrid indexapptable"  id="apptable-${process.id}"> 
             <thead>
    <tr>
     <th >  </th>
      <th onclick="sorted('title_1')">
      <span class="header-content">
        ลำดับ
        <i class="material-icons sorted" id="arrow_title_1">arrow_upward</i>
    </span>
    </th>
            <th onclick="sorted('title_2')">
             <span class="header-content">
        รหัสเอกสาร<i class="material-icons sorted" id="arrow_title_2">arrow_upward</i>
    </span>
            
          </th>
                    <th onclick="sorted('title_3')">
             <span class="header-content">
        เอกสาร<i class="material-icons sorted" id="arrow_title_3">arrow_upward</i>
    </span>
            
          </th>    
        <th onclick="sorted('title_4')">
        
                 <span class="header-content">
 ผู้ส่ง<i class="material-icons sorted" id="arrow_title_4">arrow_upward</i>
    </span>
        </th>
                <th onclick="sorted('title_5')">
        
                 <span class="header-content">
 ผู้รับ<i class="material-icons sorted" id="arrow_title_5">arrow_upward</i>
    </span>
        </th>
                <th onclick="sorted('title_6')">  <span class="header-content">วันที่ส่ง <i class="material-icons sorted" id="arrow_title_6">arrow_upward</i>   </span></th>
 	<th onclick="sorted('title_7')"><span class="header-content">วันที่รับ <i class="material-icons sorted" id="arrow_title_7">arrow_upward</i>   </span></th>
 	 	<th onclick="sorted('title_8')"><span class="header-content">การรับ <i class="material-icons sorted" id="arrow_title_8">arrow_upward</i>   </span></th>
     
     	<th onclick="sorted('title_9')"><span class="header-content">สถานะ <i class="material-icons sorted" id="arrow_title_8">arrow_upward</i>   </span></th>
     
        <th></th>

  </tr>
  </thead>
  <tbody>
    <c:forEach items="${box }" var="row" varStatus="count">  
    <tr  class="tr_datatable "> 
    <td width="40"></td>
      <td>${count.count}</td>
           <td title="[${row.data_id}]">
           
           <c:if test="${row.doc == null}">
           Auto 
           </c:if>
     
         ${row.doc}
     
           
           </td>
          <td>${row.data_title} </td>
      
        
      <td>${row.data_namesend}</td>
            <td>${row.data_namereceiver}</td>
        <td> ${row.data_dateadd}  </td>
        <td> ${row.data_date_receiver}</td>
    <td>  

    <c:if test="${row.acknowledge == 1}"> 
    <img  src="/app/system/icon/flag green.ico" /></c:if>  
    <c:if test="${row.acknowledge == -1}">
     <img  src="/app/system/icon/flag red.ico" />
    </c:if>
    </td>
    
            <td> ${row.closing_status}</td>
    
      <td>
      
         <c:if test="${boxs != 'in_refer'}">
 <span style="margin-left:5px;cursor: pointer;" class="" >
        <a 
        onclick="Loader.show('wpf-workspace${process.id}','กำลังโหลดข้อมูล process...');getData('/editdocumentapp-filemanager/${row.data_id}/${process.id}?boxs=${boxs }', 'microsofts${process.id}','${process.id}');
        setTitleApp('app${process.id}','${process.name} [เเก้ไขข้อมูล]');"  
         data-original-title="Img" >
            <img  src="/app/system/icon/preview.ico" />
         </a>
         
      </span>
      
                <c:if test="${row.remarks_rejected =='Rejected' }"> 
              <span style="margin-left:5px;" title="<spring:message code="check.result_reject"/>" ><img  src="/app/system/icon/Undo.ico" /></span>
        </c:if> 
      
        </c:if>
      <c:if test="${APP_JOIN != null &&   boxs == 'in_refer'}">
      
       <span style="margin-left:5px;cursor: pointer;" class="" >
        <a  value="app2"
            data-id="${APP_JOIN.id}"
            data-name="${APP_JOIN.name}"
            data-link="/editdocumentapp-filemanager/${row.data_id}/"
            data-process_refer="${process.id}"
            data-logo="/resources/app-assets/images/icons/APP/${APP_JOIN.process_logo}"
        onclick="App2(this);"  
         data-original-title="Img" >
            <img  src="/app/system/icon/preview.ico" />
         </a>
       
       
    
       
      </span>
          <span style="cursor: pointer;justify-content: center; " class="btn-hover-effect" >
        <a onclick="shows2('draggable-window3','New File');getData('/appform-filemanager/${process.id}?id_refer=${row.data_id}', 'mainbody3');"  
         data-original-title="Img" ><i class="fa fa-circle-plus" style="color:#28a745"></i>
         </a>
         
      </span>
      </c:if>
      
      
      
      
      
    
      
      </td>
</tr>
</c:forEach>

</tbody>
</table>
            
            
              
            </div>
        </main>

    </div>
      
    <div class="wpf-status-bar">
        <span>พร้อมใช้งาน</span>
        <span>|</span>
        <span>จำนวนรายการทั้งหมด: ${reviewed_count+transaction_sends.size() + draft_count +0   } รายการ</span>
        <span>|</span>
        <span>User: ${Member_name }</span>
        <span style="margin-left:auto;"><i class="fa fa-database"></i> Connected to ERP_DB_PROD</span>
    </div>
</div>

<script>
    // จำลองการเลือกแถวแบบ WPF DataGrid
    document.querySelectorAll('.wpf-datagrid tbody tr').forEach(row => {
        row.addEventListener('click', function() {
            document.querySelectorAll('.wpf-datagrid tbody tr').forEach(r => r.classList.remove('selected'));
            document.querySelectorAll('.wpf-datagrid tbody tr td:first-child').forEach(td => td.innerHTML = '');
            
            this.classList.add('selected');
            this.querySelector('td:first-child').innerHTML = '<i class="fa fa-caret-right"></i>';
        });
    });
</script>
<script>

restoreTableSort();

function restoreTableSort() {
    var table = document.querySelector('.indexapptable');
    if (!table) return; // ถ้าไม่มีตารางก็จบ

    // ดึง ID ตารางมาหา Key
    var tableId = table.getAttribute('id') || 'default_table';
    var storageKey = 'tableSort_' + tableId;

    var savedSort = localStorage.getItem(storageKey);

    if (savedSort) {
        var obj = JSON.parse(savedSort);
        if (columnList.includes(obj.col)) {
            // เรียก sorted เพื่อจัดเรียงตามที่จำไว้
            sorted(obj.col, obj.ord);
        }
    }
}
</script>




<script>
function selectNode(element) {
    // 1. วิ่งขึ้นไปหา Sidebar ที่ใกล้ที่สุด เพื่อตีกรอบพื้นที่ทำงาน
    const scope = element.closest('[id^="sidebar-"]'); 
    
    if (!scope) {
        // ถ้าหาขอบเขต sidebar ไม่เจอ ให้จัดการแค่กลุ่มพี่น้องรอบตัวมัน (Siblings)
        // เพื่อป้องกันการไปไล่ลบ active ทั่วทั้งระบบ ERP
        Array.from(element.parentNode.children).forEach(sibling => {
            if (sibling.classList.contains('tree-node')) {
                sibling.classList.remove('active');
            }
        });
    } else {
        // 2. เจอขอบเขตแล้ว! ลบ active เฉพาะ tree-node ที่เป็นลูกหลานของ sidebar นี้เท่านั้น
        const internalNodes = scope.querySelectorAll('.tree-node');
        internalNodes.forEach(node => node.classList.remove('active'));
        
        console.log("ล้างสถานะในขอบเขต: " + scope.id);
    }
    
    // 3. มาร์คตัวที่เราคลิกให้เป็น Active
    element.classList.add('active');
}
// จัดการเมื่อเปิดหน้าครั้งแรก
function boxs() {
    const currentBox = "${boxs}"; // รับค่า box จาก JSP
   
    let target;

    if (currentBox && currentBox.trim() !== "") {
        // ถ้า box มีค่า ให้หา Element ตาม ID
        target = document.getElementById(currentBox);
    } else {
        // ถ้า box ไม่มีค่า ให้หาตัวที่ไม่มี id (ในที่นี้คือ 'งานรอดำเนินการ')
        // โดยเช็คจาก class tree-node ที่ไม่มี attribute id
        target = document.querySelector('.tree-node:not([id])[onclick*="InProgress"]');
    }

    if (target) {
        selectNode(target);
    }
}

boxs();



</script>
<script>
function refreshData(btnElement, url, targetId) {
    // 1. หาไอคอน
    const icon = btnElement.querySelector('.fa-arrows-rotate');
    
    if (icon) {
        // 2. บังคับหมุน 1 รอบ (360 องศา) ในเวลา 0.5 วินาที
        icon.style.transition = "transform 0.5s ease-in-out";
        icon.style.transform = "rotate(360deg)";
        
        // 3. ตั้งเวลาหน่วง 0.5 วินาที
        setTimeout(() => {
            // รีเซ็ตค่ากลับเป็น 0 องศา (แบบไม่แสดงแอนิเมชันตอนดึงกลับ)
            icon.style.transition = "none"; 
            icon.style.transform = "rotate(0deg)";
            
            // 4. เรียกข้อมูล "หลังจาก" ไอคอนหมุนครบ 1 รอบแล้ว
            getData(url, targetId);
            
        }, 500); // รอ 500 มิลลิวินาที (0.5 วินาที)
        
    } else {
        // เผื่อกรณีหาไอคอนไม่เจอ ก็ให้เรียกข้อมูลไปเลย ระบบจะได้ไม่ค้าง
        getData(url, targetId);
    }
}


/**
 * ระบบแสดงผลหน้าจอ Loading
 */
 const Loader = {
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
		


</script>


</body>
</html>









    






