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
</style>
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
<div id="wpf-app-frame"  >
  

   
  
      

    <div class="wpf-ribbon">
        <div class="ribbon-group">
            <div class="ribbon-btn" onclick="shows2('draggable-window3','New File');getData('/appform-filemanager/${process.id}', 'mainbody3');">
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
<aside class="wpf-sidebar">
    <div class="sidebar-header">Navigation Pane</div>
    
    <div class="tree-node" style="cursor: default;color:black"><i class="fa fa-folder-open" style="cursor: default;color:black"></i> กล่องงานของฉัน (${reviewed_count + transaction_sends.size() + draft_count + 0})</div>
    
    <div class="tree-node" id="in" style="padding-left:30px;" onclick="selectNode(this); getData('/app-filemanager/${process.id}?boxs=in', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ เอกสารขาเข้า]')">
        <i class="fa fa-file-import"></i> งานขาเข้า (${reviewed_count + 0})
    </div>
    
    <div class="tree-node active" style="padding-left:30px;" onclick="selectNode(this); getData('/app-filemanager/${process.id}', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [<spring:message code="btn.InProgress" text="-" />]')">
        <i class="fa fa-pen-to-square"></i> งานรอดำเนินการ (${draft_count + 0})
    </div>
    
    <div class="tree-node" id="sends" style="padding-left:30px;" onclick="selectNode(this); getData('/app-filemanager/${process.id}?boxs=sends', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ส่งออก]')">
        <i class="fa fa-paper-plane"></i> งานส่งออก (${send_count + 0})
    </div>
    
    <div class="tree-node" style="cursor: default;color:black"><i class="fa fa-folder-open" style="cursor: default;color:black"></i> ประวัติเอกสาร</div>
    
    <div class="tree-node" id="refer" style="padding-left:30px;" onclick="selectNode(this); getData('/app-filemanager/${process.id}?boxs=refer', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [ที่เกี่ยวข้อง]')">
        <i class="fa fa-share-nodes"></i> ที่เกี่ยวข้อง
    </div>
    
    <div class="tree-node" id="completion" style="padding-left:30px;" onclick="selectNode(this); getData('/app-filemanager/${process.id}?boxs=completion', 'microsofts${process.id}'); set_title_app('app${process.id}','${process.name} [เสร็จสิ้น]')">
        <i class="fa fa-circle-check"></i> เสร็จสิ้น
    </div>
    
    <div class="tree-node" id="trash" style="padding-left:30px;"><i class="fa fa-trash-can"></i> ถังขยะ</div>
</aside>
        <main class="wpf-workspace">
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

            <div class="datagrid-container" id="container${process.id}">
             
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
 ผู้สร้าง<i class="material-icons sorted" id="arrow_title_4">arrow_upward</i>
    </span>
        </th>
                <th onclick="sorted('title_5')">
        
                 <span class="header-content">
 ผู้ส่ง<i class="material-icons sorted" id="arrow_title_5">arrow_upward</i>
    </span>
        </th>
                <th onclick="sorted('title_6')">  <span class="header-content">วันที่สร้าง <i class="material-icons sorted" id="arrow_title_6">arrow_upward</i>   </span></th>
 	<th onclick="sorted('title_7')"><span class="header-content">วันที่ส่ง <i class="material-icons sorted" id="arrow_title_7">arrow_upward</i>   </span></th>
 	 	<th onclick="sorted('title_8')"><span class="header-content">การรับ <i class="material-icons sorted" id="arrow_title_8">arrow_upward</i>   </span></th>
        <th></th>

  </tr>
  </thead>
  <tbody>
    <c:forEach items="${box }" var="row" varStatus="count">  
    <tr  class="tr_datatable "> 
    <td width="40"></td>
      <td>${count.count}</td>
           <td>
           
           <c:if test="${row.doc == null}">
           Auto 
           </c:if>
     
         ${row.doc}[${row.data_id}]
     
           
           </td>
          <td>${row.data_title} </td>
      
        
      <td>${row.data_nameadd}</td>
            <td>${row.data_namesend}</td>
        <td> ${row.data_dateadd}  </td>
        <td> ${row.data_date_update}</td>
    <td>  

    <c:if test="${row.acknowledge == 1}"> 
    <img  src="/app/system/icon/flag green.ico" /></c:if>  
    <c:if test="${row.acknowledge == -1}">
     <img  src="/app/system/icon/flag red.ico" />
    </c:if>
    </td>
      <td>
 <span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect" >
        <a 
        onclick="getData('/editdocumentapp-filemanager/${row.data_id}/${process.id}?boxs=${boxs }', 'microsofts${process.id}','${process.id}');
        setTitleApp('app${process.id}','${process.name} [เเก้ไขข้อมูล]');Loader.show('container${process.id}','กำลังโหลดข้อมูล process...');"  
         data-original-title="Img" >
            <img  src="/app/system/icon/preview.ico" />
         </a>
         
      </span>
      
              <c:if test="${row.remarks_rejected =='Rejected' }"> 
              <span style="margin-left:5px;" title="<spring:message code="check.result_reject"/>" ><img  src="/app/system/icon/Undo.ico" /></span>
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
// ฟังก์ชันสำหรับสลับ Class active
function selectNode(element) {
    // ลบ class active ออกจากทุกโหนดที่มี class tree-node
    const nodes = document.querySelectorAll('.tree-node');
    nodes.forEach(node => node.classList.remove('active'));
    
    // เพิ่ม class active ให้ตัวที่ถูกคลิก
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









    






