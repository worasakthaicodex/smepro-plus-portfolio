<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<style>
.action-column {
    display: flex;
    gap: 5px; /* เว้นระยะห่างระหว่างปุ่ม */
    justify-content: center; /* จัดกึ่งกลาง */
}
.btn-modern {
    background-color: #ffffff;
    border: 1px solid #e2e8f0; /* สีขอบเทาอ่อนๆ */
    color: #4a4a4a;
    padding: 4px 8px;
    border-radius: 6px; /* มุมมนเล็กน้อย */
    font-size: 12px;
    cursor: pointer;
    transition: all 0.2s ease;
}

.btn-modern:hover {
    border-color: #5b4f96; /* สีเดียวกับ Header เวลาเอาเมาส์ชี้ */
    color: #5b4f96;
    background-color: #f8f9fa;
}


.truncate-cell {
    white-space: nowrap;      /* ห้ามขึ้นบรรทัดใหม่ */
    overflow: hidden;         /* ส่วนที่เกินให้ซ่อน */
    text-overflow: ellipsis;  /* ส่วนที่ซ่อนให้แสดงเป็น ... */
    cursor: pointer;          /* เปลี่ยนเมาส์เป็นรูปมือ ให้รู้ว่ากดได้ */
    max-width: 0;             /* เทคนิคเพื่อให้ Flex/Grid หรือ Table cell บีบตัวได้ */
}

/* คลาสสำหรับตอนที่กดขยายแล้ว */
.truncate-cell.expandeds {
    white-space: normal;      /* กลับมาขึ้นบรรทัดใหม่ได้ */
    overflow: visible;        /* แสดงข้อความทั้งหมด */
    word-wrap: break-word;    /* ตัดคำถ้ายาวเกิน */
}

/* เส้นแบ่งสำหรับการดึงขยาย (Resizer) */
.resizer {
    cursor: col-resize;
    user-select: none;
  font-size: 12px;
position: absolute;
    right: 0;  /* ชิดขวาสุด */
    top: 0;    /* ชิดบนสุด */
  display: flex;
    align-items: center;   /* กึ่งกลางแนวตั้ง */
    justify-content: center; /* กึ่งกลางแนวนอน */
    color:#fff;
}
.ant-table-cell th {
    position: relative !important; /* บังคับให้เป็นจุดอ้างอิงของเส้น Resizer */
    background-clip: padding-box; /* ป้องกันสีพื้นหลังทับเส้น */
}

/* ตัวตาราง */
.table-wrapper table {
  width: 100%;
  border-collapse: collapse; /* สำคัญ: เพื่อให้ขอบตารางแนบสนิทตอน sticky */
}

/* ส่วนหัวตารางที่ต้องการตรึง */
.table-wrapper th {
  position: sticky !important;
  top: 0; /* ตรึงไว้ด้านบนสุดของคอนเทนเนอร์ */
 z-index:5;
  background-color: #f1f1f1; /* ต้องใส่สีพื้นหลัง ไม่งั้นหัวตารางจะโปร่งใสเห็นตัวหนังสือซ้อนกัน */
  box-shadow: inset 0 -1px 0 #ccc; /* ใช้แทน border ล่าง เพราะ border ปกติจะหายไปเวลาทำ sticky */
}

.table-wrapper, .ant-table-cell {
    -webkit-user-select: text !important; /* Safari */
    -moz-user-select: text !important;    /* Firefox */
    -ms-user-select: text !important;     /* IE/Edge */
    user-select: text !important;        /* Standard */
}
/* เส้น Resizer */
.resizer {
    position: absolute;
    top: 0;
    right: 0;
    width: 5px;
    cursor: col-resize;
    user-select: none;
    height: 100% !important;
}
.resizer:hover, .resizing {
    border-right: 2px solid #007bff;
}

/* Icon การเรียงลำดับ */
.sort-handle {
    font-size: 12px;
    margin-left: 5px;
    display: inline-block;
    transition: 0.2s;
}

/* ป้องกันการคลุมดำตอนกด Resize */
.table-wrapper th  {
    user-select: none !important;
    position: relative;

    overflow: hidden;
    text-overflow: ellipsis;
}
</style>


<div  class="ant-table-wrapper workflow-table css-xepvsj" >


<table style="table-layout: auto;" id="myTable-${process.id }-${thisTab.id}" class="table-wrapper">
 <colgroup>
  <col class="ant-table-expand-icon-col">
 </colgroup>
 <thead class="ant-table-thead">
  <tr>
     <th class="ant-table-cell" scope="col">#</th>
  <c:forEach items="${heads }"   var="row" varStatus="count"> 
  	 <th class="ant-table-cell"  scope="col">${row.field_label } 
  	 
  	 </th>
   </c:forEach>

   <th class="ant-table-cell" scope="col">Action</th>
  </tr>
 </thead>
 <tbody class="ant-table-tbody">
 
 
<c:set var="thisCode" value="null" />
<c:set var="thisCode2" value="null" />
 <c:set var="thisCode3" value="null" />   	
    	
    <c:forEach items="${edataAppManagement }"   var="rows" varStatus="count"> 
  <tr class="ant-table-row ant-table-row-level-0 workflow-row" data-row-key="0">
  
     <td class="ant-table-cell">${count.count } </td>


 <c:forEach items="${heads }"   var="row" varStatus="counts"> 

 	<c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
 
 
 
 
   <td class="ant-table-cell truncate-cell" title="${rows[row.field_datarefercols] }">
   
	    <c:if test="${row.field_datarefercols != 'acknowledge' && row.field_datarefercols != 'closing_status'  }"> 
          ${rows[row.field_datarefercols] }
	</c:if>
	
       <c:if test="${row.field_datarefercols == 'closing_status'  }"> 
          ${rows["closing_status"] }
	</c:if>
    <c:if test="${row.field_datarefercols == 'acknowledge'  }"> 
  
        <c:if test="${rows.acknowledge == 1}"> 
    <img  src="/app/system/icon/flag green.ico" />
    </c:if>  
    <c:if test="${rows.acknowledge == -1}">
     <img  src="/app/system/icon/flag red.ico" />
    </c:if>
      <c:if test="${rows.remarks_rejected =='Rejected' }"> 
              <span style="margin-left:5px;" title="<spring:message code="check.result_reject"/>" ><img  src="/app/system/icon/Undo.ico" /></span>
        </c:if> 
	</c:if>
   
   
   </td>

    <c:if test="${thisValue == thisTab.shortcut_profile_code &&  rows[row.field_datarefercols] != null && rows[row.field_datarefercols] != ''   }"> 
      	<c:set var="thisCode" value="${rows[row.field_datarefercols] }" />
    </c:if>
    <c:if test="${thisValue == thisTab.shortcut_newdoc_code &&  rows[row.field_datarefercols] != null  }"> 
      	<c:set var="thisCode2" value="${rows[row.field_datarefercols] }" />
    </c:if>
    <c:if test="${thisValue == thisTab.shortcut_segment_code &&  rows[row.field_datarefercols] != null  }"> 
      	<c:set var="thisCode3" value="${rows[row.field_datarefercols] }" />
    </c:if>
    

	

 </c:forEach>  



   <td class="ant-table-cell">
<div class="action-column">



    <%-- 1. ส่วนของ Select Dropdown --%>
    <select class="ant-input btn-modern" style="z-index:0;width: 100%; text-align: left;" onchange="handleActionSelect(this)">
        <option value="">-- เลือกรายการ --</option>

        <%-- Item: Shortcut Profile App --%>
        <c:if test="${thisTab.shortcut_profile_app > 0 && thisCode != 'null' && thisTab.shortcut_profile_linkname != null }">
            <option value="eval" 
                data-script="openAppAndLoadData('${thisCode}', '${thisTab.shortcut_profile_app}','${rows.data_id}'); 
                getData('/profileapp-filemanager/${thisCode}/${thisTab.shortcut_profile_app}?from=${rows.data_id}', 'microsofts${thisTab.shortcut_profile_app}');
              
                ">
                ${thisTab.shortcut_profile_linkname}
            </option>
        </c:if>

        <%-- Item: เปิดเอกสาร (App2) --%>
        <option value="app2"
            data-id="${thisApp.process_id}"
            data-name="${thisTabprocess.name}"
            data-link="/editdocumentapp-filemanager/${rows.data_id}/"
            data-process_refer="${thisApp.process_id}"
            data-logo="/resources/app-assets/images/icons/APP/${thisTabprocess.process_logo}">
            เปิดเอกสาร [${rows.data_id}]
        </option>

        <%-- 🔥 Item ใหม่: เพิ่มปฏิทิน (Add Calendar) 🔥 --%>
        <option value="eval" 
            data-script="shows2('draggable-window3','Calendar'); getData('/calendar-filemanager/${process.id}?edata_id=${rows.data_id}', 'mainbody3');">
            เพิ่มปฏิทิน
        </option>

        <%-- Item: Shortcut New Doc --%>
        <c:if test="${thisTab.shortcut_newdoc_linkname != null && thisTab.shortcut_newdoc_linkname != '' && nextTabprocess != null}">
            <option value="eval"
                data-script="openAppAndLoadData2('${nextTabprocess.id}', '${rows['data_id']}${thisTab.id}','${thisApp.id}_${thisTab.id}', '${_csrf.parameterName}=${_csrf.token}');">
                ${thisTab.shortcut_newdoc_linkname}
            </option>
        </c:if>

        <%-- Item: Shortcut Change Closing Status --%>
        <c:if test="${thisTab.shortcut_change_closing_status != null }">
            <option value="eval"
                data-script='toggleasideInfo(); getData("/changeClosingStatus-filemanager/${rows.data_id}/${thisTab.id}", "body_asideinfo");'>
                ${thisTab.shortcut_change_closing_linkname}
            </option>
        </c:if>

        <%-- Item: Shortcut Segment --%>
        <c:if test="${thisTab.shortcut_segment_linkname != null && thisCode3 != 'null' }">
            <option value="eval"
                data-script='unsendCallBackKey();getData("/ShortcutCl-filemanager/${rows.data_id}/${process.id}/${thisCode3}/${thisTab.id}/${nextTabprocess.id+0}", "sub-${thisTab.id}-${rows.data_id}");'>
                ${thisTab.shortcut_segment_linkname}
            </option>
            <option value="eval" data-script="toggleasubdata();toggleasubdata();">
                ปิด (Segment)
            </option>
        </c:if>
    </select>

    <%-- 2. ส่วน Form ที่จำเป็นต้องคงไว้ (ห้ามลบ แต่ซ่อนไว้) --%>
    <c:if test="${thisTab.shortcut_newdoc_linkname != null && thisTab.shortcut_newdoc_linkname != '' && nextTabprocess != null}">
        <form name="newfile-${rows['data_id']}${thisTab.id}" id="newfile-${rows['data_id']}${thisTab.id}" style="display:none;">
            <input type="hidden" name="name_file" value="${nextTabprocess.name}_Undefined">
            <input type="hidden" name="customerCode" value="${thisCode2}">
            <input type="hidden" name="relation_id" value="${rows.data_id}">
            <input type="hidden" name="tab_id" value="${thisTab.id}">
            <input type="hidden" name="app_processes_id" value="${nextTabprocess.id}">
        </form>
    </c:if>

</div>


   </td>
  </tr>
  
  
   <tr class="subant-table ant-table-expanded-row ant-table-expanded-row-level-1" id="sub-${thisTab.id }-${rows.data_id }" >
   
  </tr>
  
  
  
  </c:forEach>
  
  
 

 
 </tbody>
</table>
 
<script>

function changetabElement() {
	//เก็บค่า element ไว้ในตัวแปรก่อน
	const tabElement = document.getElementById('TAB-${thisApp.id}-${thisTab.id}');

	// เช็คว่าหาเจอไหม ถ้าเจอค่อยใส่ค่า
	if (tabElement) {
	    tabElement.innerHTML = "[${edataAppManagement.size()}]";
	} else {
	    console.warn("หา TAB-${thisApp.id}-${thisTab.id} ไม่เจอ เลยไม่ได้อัปเดตตัวเลข");
	}
}

	changetabElement();


function toggleasubdata(){
    const elements = document.getElementsByClassName('subant-table');
    for (let i = 0; i < elements.length; i++) {
        elements[i].innerHTML = "";
    }
}



new SmartTable('#myTable-${process.id}-${thisTab.id}', {
   //truncateClass: '.ant-table-cell',
    sort: true,      // เปิด Sort
    resize: true,     // เปิด Resize
    toggleEvent:true
    <c:if test="${thisTab.grouping_row == 1}">
    ,grouping: {
    	idColIndex: ${thisTab.grouping_row_check},   // ช่องรหัส
        dateColIndex: ${thisTab.grouping_row_date}  // ช่องวันที่ไว้หาตัวล่าสุด
    }
    </c:if>
});





</script>
<%-- 3. Script จัดการ --%>
<script>
function handleActionSelect(selectElement) {
	

    var selectedOption = selectElement.options[selectElement.selectedIndex];
    var actionType = selectedOption.value;

    if (!actionType) return; 

    if (actionType === 'eval') {
        var script = selectedOption.getAttribute('data-script');
        if (script) {
            // รันคำสั่ง JS ที่ฝังไว้
            new Function(script)();
        }
    } 
    else if (actionType === 'app2') {
        // จำลองปุ่มสำหรับเปิดเอกสาร
        var dummyBtn = document.createElement('button');
        dummyBtn.setAttribute('data-id', selectedOption.getAttribute('data-id'));
        dummyBtn.setAttribute('data-name', selectedOption.getAttribute('data-name'));
        dummyBtn.setAttribute('data-link', selectedOption.getAttribute('data-link'));
        dummyBtn.setAttribute('data-process_refer', selectedOption.getAttribute('data-process_refer'));
        dummyBtn.setAttribute('data-logo', selectedOption.getAttribute('data-logo'));
        
        App2(dummyBtn);
        
        
      
        
    }

    // รีเซ็ต dropdown
    selectElement.value = "";
}


function searchTable(inputElement) {
    const filterValue = inputElement.value.toLowerCase().trim();
    const rows = document.querySelectorAll('.workspace tbody tr');
    let firstFoundWorkspace = null;

    // 1. เคลียร์ไฮไลท์เก่าออกให้หมดก่อน (รีเซ็ตกลับเป็นข้อความปกติ)
    document.querySelectorAll('mark.search-highlight').forEach(mark => {
        const parent = mark.parentNode;
        parent.replaceChild(document.createTextNode(mark.textContent), mark);
        parent.normalize(); // เชื่อม Text Node ที่แตกกันให้กลับเป็นเนื้อเดียว
    });

    // 2. วนลูปค้นหาและซ่อน/แสดง
    rows.forEach(row => {
        const rowText = row.textContent.toLowerCase();
        
        if (filterValue !== '' && rowText.includes(filterValue)) {
            row.style.display = ''; // ค้นเจอ -> แสดง
            
            // --- 🟢 ไฮไลท์ข้อความที่ค้นเจอ ---
            highlightTextInNode(row, filterValue);

            // จำ Workspace แรกที่เจอไว้สำหรับวาร์ป
            if (!firstFoundWorkspace) {
                const workspace = row.closest('.workspace');
                if (workspace) firstFoundWorkspace = workspace;
            }
        } else if (filterValue === '') {
            row.style.display = ''; // ถ้าไม่ได้พิมพ์อะไร ให้แสดงทุกแถวเหมือนเดิม
        } else {
            row.style.display = 'none'; // ไม่เจอ -> ซ่อน
        }
    });

    // 3. ระบบวาร์ปอัตโนมัติ (Auto-Switch Tab)
    if (filterValue !== '' && firstFoundWorkspace) {
        const isHidden = firstFoundWorkspace.style.getPropertyValue('display') === 'none';
        
        if (isHidden) {
            const targetTabId = firstFoundWorkspace.id.replace('ra-workspace', '');
            const allTabs = document.querySelectorAll('.window-tab');
            let targetTabButton = null;

            allTabs.forEach(tab => {
                const onclickAttr = tab.getAttribute('onclick') || '';
                if (onclickAttr.includes("'" + targetTabId + "'") || onclickAttr.includes(targetTabId + ",")) {
                    targetTabButton = tab;
                }
            });

            if (targetTabButton) {
                console.log("Global Search: เจอข้อมูลในแท็บ " + targetTabId + " กำลังวาร์ป...");
                targetTabButton.click(); 
            }
        }
    }
}

// ==========================================
// ฟังก์ชันเสริม: ไฮไลท์เฉพาะ Text Node (ปลอดภัย ไม่ทำลาย HTML)
// ==========================================
function highlightTextInNode(node, searchText) {
	const regex = new RegExp('(' + searchText + ')', 'gi');
    
    // ใช้ TreeWalker เดินมุดหาเฉพาะ "ข้อความเพียวๆ"
    const walker = document.createTreeWalker(node, NodeFilter.SHOW_TEXT, null, false);
    const textNodes = [];
    let currentNode;
    
    while (currentNode = walker.nextNode()) {
        textNodes.push(currentNode);
    }

    // ตัดคำแล้วหุ้มด้วย <mark>
    textNodes.forEach(textNode => {
        if (regex.test(textNode.nodeValue)) {
            const fragment = document.createDocumentFragment();
         const parts = textNode.nodeValue.split(new RegExp('(' + searchText + ')', 'gi'));
            
            parts.forEach(part => {
                if (part.toLowerCase() === searchText.toLowerCase()) {
                    const mark = document.createElement('mark');
                    mark.className = 'search-highlight';
                    // แต่งสีไฮไลท์ (สีเหลืองอ่อน ตัวอักษรสีน้ำตาลสไตล์ Web OS)
                    mark.style.backgroundColor = '#fef08a'; 
                    mark.style.color = '#854d0e';
                    mark.style.borderRadius = '3px';
                    mark.style.padding = '0 3px';
                    mark.textContent = part;
                    fragment.appendChild(mark);
                } else if (part) {
                    fragment.appendChild(document.createTextNode(part));
                }
            });
            textNode.parentNode.replaceChild(fragment, textNode);
        }
    });
}








</script>
<div id="textViewerModal" style="display:none; position:fixed; z-index:9999; left:0; top:0; width:100%; height:100%; background:rgba(0,0,0,0.5);">
    <div style="background:#fff; margin:10% auto; padding:20px; width:50%; border-radius:8px; box-shadow:0 4px 15px rgba(0,0,0,0.3); position:relative;">
        <span onclick="closeTextViewer()" style="position:absolute; right:15px; top:10px; cursor:pointer; font-size:24px;">&times;</span>
        <h3 style="margin-top:0; border-bottom:1px solid #eee; padding-bottom:10px;">รายละเอียดข้อมูล</h3>
        <pre id="fullTextArea" style="max-height:400px; overflow-y:auto; line-height:1.6; white-space:pre-wrap; padding:10px; background:#f9f9f9; border:1px solid #ddd; margin:0;">
</pre>
        <div style="text-align:right; margin-top:15px;">
            <button onclick="closeTextViewer()" style="padding:8px 20px; cursor:pointer; background:#1890ff; color:#fff; border:none; border-radius:4px;">ตกลง</button>
        </div>
    </div>
</div>

</div>         
            
      