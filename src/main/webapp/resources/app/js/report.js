var jsonData;
var _csrf;
let activeStorageKey = ""; 
let activeConfigObj = null; // เก็บเป็น Object จะได้เรียก .tomodel ได้
let importPendingFile = null;
let globalDuplicateGroups = []; // เก็บกลุ่มตัวซ้ำรอการประมวลผล
let globalOriginalColumns = []; // เก็บชื่อคอลัมน์เดิมไว้
let globalUniqueRows = []; // <--- เพิ่มตัวแปรนี้!
let valueMappings= [];
let smartFillRules= [];
let totalRows = 0;
 var csrfToken = ""; 
let currentCheeseData = {};


   // ฟังก์ชันสลับแท็บ Ribbon
        function RibbonswitchTab(tabName, element) {
            // 1. Remove active class from all tabs
            const tabs = document.querySelectorAll('.reporttab');
            tabs.forEach(t => t.classList.remove('active'));

            // 2. Add active to clicked tab
            element.classList.add('active');

            // 3. Hide all ribbon contents
            const contents = document.querySelectorAll('.ribbons-tools');
            contents.forEach(c => c.style.display = 'none');

            // 4. Show the selected ribbon content
            const selectedContent = document.getElementById('ribbon-' + tabName);
            if (selectedContent) {
                selectedContent.style.display = 'flex';
            }
            
         
            
            
        }

        
        function RibbontoggleFilters(id) {
		    const panel = document.getElementById(id);
		    
		    // ป้องกัน Error กรณีหา Element ไม่เจอ
		    if (!panel) return;
		
		    // เช็คค่า display ปัจจุบัน
		    // ใช้ getComputedStyle เพื่อความชัวร์ว่าสถานะจริงๆ คืออะไร (แม้จะไม่ได้เขียน inline style ไว้)
		    const currentDisplay = window.getComputedStyle(panel).display;
		
		    if (currentDisplay === 'none') {
		        // ถ้าซ่อนอยู่ -> ให้โชว์ (ต้องใช้ flex เพื่อรักษารูปแบบ Layout เดิม)
		        panel.style.display = 'flex';
		    } else {
		        // ถ้าโชว์อยู่ (เป็น flex หรือ block) -> ให้ซ่อน
		        panel.style.display = 'none';
		    }
		}

        // ฟังก์ชัน Toggle Field Tables
        function RibbontoggleField(headerElement) {
            const group = headerElement.parentElement;
            group.classList.toggle('open');
            
            // เปลี่ยน icon หน้าชื่อตาราง (Optional enhancement)
            const text = headerElement.innerText;
            if(group.classList.contains('open')) {
                headerElement.innerHTML = text.replace('⊞', '⊟');
            } else {
                headerElement.innerHTML = text.replace('⊟', '⊞');
            }
        }
        
        
   
   
   
   
   
             
// ตัวแปรเก็บข้อมูล JSON หลัก
    let filterDataList = [];
    let runningId = 1;
   
      function clearCondition() {
	  filterDataList = [];
  		 runningId = 1;
	}
   
   // --- 0. ฟังก์ชันใหม่: เช็ค Condition เพื่อเปลี่ยน Operation และซ่อน Value ---
    function checkCondition() {
        const cond = document.getElementById('conditionSelect').value;
        const opSelect = document.getElementById('operationSelect');
        const valWrapper = document.getElementById('valueWrapper');

        if (cond === 'End') {
    
            valWrapper.style.display = 'none';
            
        } else {
            // กรณีเลือก Start, And, Or
            // 1. คืนค่า Operation ชุดเดิม (Standard)
            opSelect.innerHTML = `
                <option value="">-</option>
                <option value="=">Equals (=)</option>
                <option value="!=">Not Equal (<>)</option> 
                <option value=">">Greater Than (>)</option>
                <option value="<">Less Than (<)</option>
                <option value="=>">Greater Than or Equal (>=)</option>
                <option value="<=">Less Than or Equal (<=)</option>
         		<option value="(D)=>">(Date)Greater Than or Equal (>=)</option>
                <option value="(D)<=">(Date)Less Than or Equal (<=)</option>
                <option value="(M)=>">(Month)Greater Than or Equal (>=)</option>
                <option value="(M)<=">(Month)Less Than or Equal (<=)</option>
                <option value="link%">Starts With</option>     
                <option value="%link">Ends With</option>       
                <option value="%link%">Contains</option>       
                <option value="find_in_set">In List</option>
                <option value="Is null">Is Null</option>
                <option value="Is not null">Is Not Null</option>
                <option value="Is empty">Is Empty</option>
                <option value="Is not empty">Is Not Empty</option>
                <option value="(C)>=">Count Than or Equal (>=)</option>
                <option value="(C)<=">Count Than or Equal (<=)</option>
                <option value="3_months_passed">3 months have passed.</option>
                <option value="6_months_passed">6 months have passed.</option>
                <option value="12_months_passed">12 months have passed.</option>
                  <option value="24_months_passed">24 months have passed.</option>
            `;
            // 2. แสดงช่อง Value กลับมา
            valWrapper.style.display = 'flex'; // หรือ block ก็ได้
        }
    }
   
   
        
     // 1. ฟังก์ชันเปลี่ยน Input ตามประเภท Column (จากโค้ดเดิม)
    function updateValueInput() {
        var colSelect = document.getElementById("columnSelect");
        var valueWrapper = document.getElementById("valueWrapper");
        var selectedOption = colSelect.options[colSelect.selectedIndex];
        
        var type = selectedOption.getAttribute("data-custom");
        var valuesStr = selectedOption.getAttribute("data-value");

        var newHtml = '<label>ค่าที่ใช้ (Value)</label>';

        if (type === "select" || type === "select_multiple") {
            var multipleAttr = (type === "select_multiple") ? "multiple" : "";
            // ใส่ ID ให้ select ที่สร้างใหม่ด้วย เพื่อให้ดึงค่าได้ง่ายๆ
            newHtml += '<select class="form-control" id="valueInput" ' + multipleAttr + '>';
            if (valuesStr) {
                var items = valuesStr.split(",");
                for (var i = 0; i < items.length; i++) {
                    newHtml += '<option value="' + items[i].trim() + '">' + items[i].trim() + '</option>';
                }
            }
            newHtml += '</select>';
        } else if (type === "number") {
            newHtml += '<input type="number" class="form-control" id="valueInput" placeholder="ระบุตัวเลข...">';
        } 
         else if (type === "date") {
            newHtml += '<input type="date" class="form-control" id="valueInput" placeholder="ระบุตัวเลข...">';
        } 
        else {
            newHtml += '<input type="text" class="form-control" id="valueInput" placeholder="ระบุค่า...">';
        }
        valueWrapper.innerHTML = newHtml;
    }
        
/// ==========================================
// 🚀 ฟังก์ชันเช็กโหมดรายงาน และเปิด/ซ่อน โซน Summary / Trend
// ==========================================
function applyReportModeToUI() {
    const summaryWrapper = document.getElementById('summarySectionWrapper');
    const trendWrapper = document.getElementById('trendSettingsWrapper');
    const joinWrapper = document.getElementById('joinSettingsWrapper');
    const displayWrapper = document.getElementById('masterDisplayWrapper');
    const titleText = document.getElementById('summaryTitleText');
    
    if (!currentCheeseData || !currentCheeseData.PrepareReport) return;

    let reportData = currentCheeseData.PrepareReport; 
    let currentMode = String(reportData.report_mode).toLowerCase();

    // 1. เปิด/ปิดกล่อง (โค้ดเดิมของคุณ)
    if (currentMode === 'summary' ) { /*...เปิดกล่อง summary...*/ } 
    else if (currentMode === 'trend' ) { /*...เปิดกล่อง trend...*/ } 
    else if (currentMode === 'join' ) { 
        if(summaryWrapper) summaryWrapper.style.display = 'none';
        if(trendWrapper) trendWrapper.style.display = 'none';
        if(joinWrapper) joinWrapper.style.display = 'block';
        if(displayWrapper) displayWrapper.style.display = 'block';
    } 
    // ...

    // 2. ดึงค่า Global (Summary & Trend) จาก reportData
    if(document.getElementById('summaryNameInput')) document.getElementById('summaryNameInput').value = reportData.summary_name || '';
    // ... ดึง Summary ตัวอื่นๆ ...


// 🚀 🌟 1. สร้าง Dropdown คีย์ตารางแม่แบบไดนามิก (ดึงจากตารางแรกสุดเสมอ)
    const masterKeySelect = document.getElementById('joinMasterKeySelect');
    if (masterKeySelect && currentCheeseData.FilterChain && currentCheeseData.FilterChain.length > 0) {
        // ล้างของเก่าทิ้ง
        masterKeySelect.innerHTML = '<option value="" data-table="">- เลือกคีย์หลัก -</option>';
        
        // ดึงคอลัมน์จากตารางแรกสุด (Index 0 = ตารางแม่)
        const masterTable = currentCheeseData.FilterChain[0];
        
        if (masterTable && masterTable.saved_columns) {
            let optGroup = document.createElement('optgroup');
            optGroup.label = "📌 คีย์จากตารางแม่ (Master Table)";
            
            masterTable.saved_columns.forEach(col => {
                let opt = document.createElement('option');
                opt.value = col.value;
                opt.text = col.text; // จะแสดงชื่อคอลัมน์ของตารางแม่เป๊ะๆ
                opt.setAttribute('data-table', col.table);
                optGroup.appendChild(opt);
            });
            masterKeySelect.appendChild(optGroup);
        } else {
            // เผื่อไว้กรณีตารางแม่ยังไม่เคยถูกเซฟ
            masterKeySelect.innerHTML += '<option value="id" data-table="MAIN_TBL">id (Default)</option>';
            masterKeySelect.innerHTML += '<option value="data_id" data-table="MAIN_TBL">data_id (Default)</option>';
            masterKeySelect.innerHTML += '<option value="customerCode" data-table="MAIN_TBL">customerCode (Default)</option>';
        }
    }


    // 🚀 3. ดึงค่า Join และ Display จาก FilterChain ของตารางนี้!
    const editIdInput = document.querySelector('input[name="id"]');
    if (editIdInput && currentCheeseData.FilterChain) {
        const targetId = parseInt(editIdInput.value);
        const targetFilter = currentCheeseData.FilterChain.find(row => row.tables === targetId);

        if (targetFilter) {
            // โหลดค่า Join กลับมาโชว์
            if(document.getElementById('joinTypeSelect')) document.getElementById('joinTypeSelect').value = targetFilter.join_type || '';
           if(document.getElementById('joinMasterKeySelect')) document.getElementById('joinMasterKeySelect').value = targetFilter.join_master_key || '';
            if(document.getElementById('joinChildKeySelect')) document.getElementById('joinChildKeySelect').value = targetFilter.join_child_key || '';

          // ----------------------------------------------------
            // โหลดค่า Join กลับมาโชว์ (อันนี้ของเดิม เก็บไว้ครับ)
            if(document.getElementById('joinTypeSelect')) document.getElementById('joinTypeSelect').value = targetFilter.join_type || '';
            if(document.getElementById('joinMasterKeySelect')) document.getElementById('joinMasterKeySelect').value = targetFilter.join_master_key || '';
            if(document.getElementById('joinChildKeySelect')) document.getElementById('joinChildKeySelect').value = targetFilter.join_child_key || '';
            // ----------------------------------------------------

            // 🚀 🌟 โหลดค่า Display Columns สำหรับ UI ลากวาง (อันใหม่!)
            window.activeDisplayColumns = []; // เคลียร์กระดานก่อน
            
            if (targetFilter.display_columns && targetFilter.display_columns.trim() !== "") {
                const savedColsArray = targetFilter.display_columns.split(',');
                
                savedColsArray.forEach(val => {
                    let labelText = val; // ชื่อ Default กรณีหาไม่เจอ
                    
                    // ไปงัดเอาชื่อที่บอสเคยพิมพ์แก้ไว้ (ใน custom_labels) มาแสดง
                    if (targetFilter.custom_labels) {
                        const foundCol = targetFilter.custom_labels.find(c => c.value === val);
                        if (foundCol && foundCol.text) {
                            labelText = foundCol.text;
                        }
                    }
                    
                    // ยัดลง Array สำหรับวาด UI
                    window.activeDisplayColumns.push({ 
                        value: val, 
                        label: labelText, 
                        table: "MAIN_TBL" 
                    });
                });
            }
            
            // สั่งเครื่องจักรวาดกล่องลากวางขึ้นมาใหม่!
            if (typeof renderSortableColumns === "function") {
                renderSortableColumns();
            }
        }
    }
}
// 2. ฟังก์ชันเพิ่มข้อมูลลงตารางและ JSON Array (เหลือแค่ส่วนเงื่อนไขตัวกรองเพียวๆ)
function addToTable() {
    // ดึง Element ของเงื่อนไขการกรอง
    const conditionSelect = document.getElementById('conditionSelect');
    const columnSelect = document.getElementById('columnSelect');
    const operationSelect = document.getElementById('operationSelect');
    const valueElement = document.getElementById('valueInput'); 
    const groupElement = document.getElementById('groupInput'); 

    // ดึงค่าเดิม
    const condition = conditionSelect ? conditionSelect.value : "";
    const operation = operationSelect ? operationSelect.value : "";
    
    // 🚀 1. ดึง Element สำหรับ profile_id
    const profileIdInput = document.getElementById('profileIdInput'); 
    const profileIdVal = profileIdInput ? profileIdInput.value.trim() : "data_value2";
    
    // ป้องกัน Error กรณีไม่ได้เลือก Column
    let columnTag = "";
    let type = "";
    let values = "";
    let columnId = "";
    
    if (columnSelect && columnSelect.selectedIndex >= 0) {
        const columnOption = columnSelect.options[columnSelect.selectedIndex];
        columnTag = columnOption.getAttribute('data-tag') || ""; 
        type = columnOption.getAttribute('data-custom') || ""; 
        values = columnOption.getAttribute('data-value') || ""; 
        columnId = columnOption.value;
    }

    // 🚀 [เพิ่มตรงนี้!] ถ้าเลือก Operation เป็นแบบใหม่ บังคับ type ให้เป็น date ทันที!
    if (operation === "(RD)=>" || operation === "(RD)<=") {
        type = "date";
    }
    
    // ดึงค่า Value (รองรับ Multiple Select)
    let finalValue = "";
    if (valueElement && valueElement.tagName === 'SELECT' && valueElement.multiple) {
        finalValue = Array.from(valueElement.selectedOptions).map(opt => opt.value).join(',');
    } else if (valueElement) {
        finalValue = valueElement.value;
    }

    // สร้าง Object JSON
    const newData = {
        id: runningId,
        group: groupElement ? groupElement.value : "1",
        column_id: columnId,
        condition: condition,
        column_tag: columnTag, 
        operation: operation,
        value: finalValue,
        valueselected: values,
        type: type, // ตอนนี้ถ้าเป็น (RD) มันจะกลายเป็น "date" แน่นอน
        profile_id: profileIdVal
    };

    // เพิ่มลง Array
    filterDataList.push(newData);

    // เตรียมค่าแสดงผลในตาราง 
    const displayCol = columnTag || '-';
    const displayOp = operation || '-';
    const displayVal = finalValue || '-';

    // วาดลงตาราง HTML
    const tbody = document.querySelector('#filterTable tbody');
    const tr = document.createElement('tr');
    tr.setAttribute('data-id', runningId); 
    
    // 🌟 วาดตารางเฉพาะเงื่อนไข
    tr.innerHTML = `
        <td>[${newData.group}] ${condition}</td>
        <td style="color: #0078d7; font-weight:500;">${displayCol}</td>
        <td><span style="color:#e83e8c;">${displayOp}</span></td>
        <td>${displayVal}</td>
        <td class="delete-btn" onclick="removeRow(${runningId})" style="text-align:center; cursor:pointer;">
            <img src="/app/system/icon/delete.ico" width="16">
        </td>
    `;
    
    tbody.appendChild(tr);

    // อัปเดตส่วนอื่นๆ
    runningId++;
    updateJsonDisplay();
    document.getElementById('recordCount').innerText = filterDataList.length + " records added";
}

function loadFromInput() {
    const jsonString = document.getElementById('jsonOutput').value;

    // เช็คว่ามีค่าหรือไม่
    if (!jsonString.trim()) {
        console.log("ไม่มีข้อมูล JSON ในช่อง Input");
        return;
    }

    try {
        // 2. แปลง String กลับเป็น Array Object
        const data = JSON.parse(jsonString);

        if (!Array.isArray(data)) {
            console.log("รูปแบบข้อมูลไม่ถูกต้อง (ต้องเป็น Array [...])");
            return;
        }

        // 3. อัปเดต Global Variable
        filterDataList = [...data];

        // 4. คำนวณ runningId ใหม่
        if (filterDataList.length > 0) {
            const maxId = filterDataList.reduce((max, item) => (item.id > max ? item.id : max), 0);
            runningId = maxId + 1;
        } else {
            runningId = 1;
        }

        // 5. เคลียร์ตารางเก่า
        const tbody = document.querySelector('#filterTable tbody');
        tbody.innerHTML = ''; // เพิ่มบรรทัดนี้เพื่อเคลียร์ของเดิมชัวร์ๆ

        // 6. วนลูปวาดตาราง
        filterDataList.forEach(item => {
            const tr = document.createElement('tr');
            tr.setAttribute('data-id', item.id);

            // เตรียมค่า (เผื่อบางบรรทัดไม่มีค่า Summary หรือ Filter จะได้ไม่แสดง undefined)
            let groupCond = `[${item.group || '1'}] ${item.condition || ''}`;
            let op = item.operation || '-';
            let val = item.value || '-';
            
            // 🌟 3 ค่าใหม่สำหรับโหมด Summary
            let sumName = item.summary_name || '-';
            let aggFunc = item.agg_func || '-';
            let targetCol = item.target_col || '-';

            // หมายเหตุ: วาดตารางโดยมีทั้งฝั่ง "เงื่อนไข (WHERE)" และ "สรุปผล (SUMMARY)"
            tr.innerHTML = `
                <td>${groupCond}</td>
                <td style="color: #0078d7; font-weight:500;">${item.column_tag || '-'}</td>
                <td><span style="color:#e83e8c;">${op}</span></td>
                <td>${val}</td>

            

                <td class="delete-btn" onclick="removeRow(${item.id})" style="text-align:center; cursor:pointer;">  
                    <img src="/app/system/icon/delete.ico" width="16">
                </td>
            `;

            tbody.appendChild(tr);
        });

        // 7. อัปเดตตัวนับ
        document.getElementById('recordCount').innerText = filterDataList.length + " records loaded";
        
        console.log("โหลดข้อมูลสำเร็จ, Running ID ถัดไปคือ: " + runningId);

    } catch (e) {
        console.error("JSON Error:", e);
        console.log("รูปแบบ JSON ไม่ถูกต้อง กรุณาตรวจสอบ Syntax");
    }
}





    // 3. ฟังก์ชันลบแถว
    function removeRow(id) {
        // ลบออกจาก Array
        filterDataList = filterDataList.filter(item => item.id !== id);
        
        // ลบออกจาก HTML Table
        const row = document.querySelector(`tr[data-id='${id}']`);
        if (row) row.remove();

        updateJsonDisplay();
        document.getElementById('recordCount').innerText = filterDataList.length + " records added";
    }

    // 4. ฟังก์ชันแสดง JSON (จำลองการส่งข้อมูล)
    function updateJsonDisplay() {
        const jsonText = JSON.stringify(filterDataList, null, 2);
        document.getElementById('jsonOutput').value = jsonText;
        console.log("Current JSON:", filterDataList);
    }

    // 5. Clear Input
    function clearInputs() {
        document.getElementById('columnSelect').value = "0";
        updateValueInput(); // รีเซ็ต value input
    }            
    
    
    
    
    
      function DiffCl(jsonDatas) {
	
	
	
	if (typeof jsonDatas === 'string') {
        try {
            jsonData = JSON.parse(jsonDatas);
        } catch (e) {
            console.error("JSON Error:", e);
            return;
        }
    }
	
	
	
    // 1. เช็คก่อนว่ามีข้อมูลไหม
    if (!jsonData || jsonData.length === 0) {
        console.log("ไม่มีข้อมูล");
        return;
    }
    

    
	const dataset = jsonData[0]; 
    if (!dataset.headers || !dataset.data) return;


    if(document.getElementById("total_data_select")) {
	
			let number = document.getElementById("total_data_select").value.replace(/\D/g, ""); 
			let finalNumber = Number(number);
				
			var total_same = 	   dataset.total_data  - finalNumber;
			document.getElementById("total_same").value =  dataset.total_data+" ROWS" ;
			
			if(total_same > 0){
				
				if(finalNumber == 0){ finalNumber = 1}
				
				var diff = total_same * 100 / finalNumber;
				document.getElementById("diff_data").value =  diff+" %" ;
			}else{
				document.getElementById("diff_data").value =  "0 %" ;
			}
			
	}

}


    
function formatTrendDate(rawDate, formatType) {
    if (!rawDate) return "";
    
    let d;
    
    // 🌟 ดักจับ Timestamp: ถ้าเป็นตัวเลขล้วนๆ (เช่น 1769014800000)
    if (!isNaN(rawDate) && String(rawDate).trim() !== "") {
        let ts = Number(rawDate);
        // ถ้าเป็นวินาที (10 หลัก) ให้คูณ 1000 เป็นมิลลิวินาที
        if (String(ts).length <= 10) ts *= 1000; 
        d = new Date(ts);
    } else {
        d = new Date(rawDate); // ถ้าเป็น 2026-04-15 ก็เข้าปกติตรงนี้
    }
    
    // ถ้ารูปแบบประหลาดจนแปลงไม่ได้ ให้คืนค่าเดิมกลับไปเลย
    if (isNaN(d.getTime())) return rawDate; 

    const yyyy = d.getFullYear();
    const mm = String(d.getMonth() + 1).padStart(2, '0');
    const dd = String(d.getDate()).padStart(2, '0');

    // สลับร่าง!
    switch (formatType) {
        case 'dd-mm-yyyy': return `${dd}-${mm}-${yyyy}`;
        case 'mm-yyyy': return `${mm}-${yyyy}`;
        case 'yyyy': return `${yyyy}`;
        case 'timestamp_ms': return d.getTime();
        case 'timestamp_sec': return Math.floor(d.getTime() / 1000);
        case 'yyyy-mm-dd':
        default: return `${yyyy}-${mm}-${dd}`;
    }
}

// 🌟 เพิ่ม parameter ตัวที่ 3: filterStr (ตั้งค่า Default เป็นค่าว่าง)
function renderFirstTable(jsonDatas, id, filterStr = "", filterCategory = "") {
    
 
    jsonData = jsonDatas;
    if (typeof jsonDatas === 'string') {
        try {
            jsonData = JSON.parse(jsonDatas);
        } catch (e) {
            console.error("JSON Error:", e);
            return;
        }
    }
    
    if (!jsonData || jsonData.length === 0) {
        console.log("ไม่มีข้อมูล");
        return;
    }
    
    const dataset = jsonData[0]; 
    if (!dataset.headers || !dataset.data) return;

    // 🚀 [เพิ่มใหม่] ดึงค่า Global Date Format มารอไว้ก่อนเริ่มวาดตาราง
    let globalDateFormat = "yyyy-mm-dd";
    if (typeof currentCheeseData !== 'undefined' && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.date_format) {
        globalDateFormat = currentCheeseData.PrepareReport.date_format;
    }


// 🎯 ชั้นที่ 1: กรองด้วย "วันที่" (Trend Date)
    if (filterStr && filterStr.trim() !== "") {
        const targetValue = filterStr.split("_")[0]; 
        
        dataset.data = dataset.data.filter(row => {
            return Object.values(row).some(val => 
                val !== null && String(val).includes(targetValue)
            );
        });
    }

    // 🚀 🎯 ชั้นที่ 2: กรองด้วย "หมวดหมู่" (Group By Category)
    if (typeof filterCategory !== 'undefined' && filterCategory && filterCategory.trim() !== "") {
        const targetCatValue = filterCategory.split("_")[0]; 
        
        dataset.data = dataset.data.filter(row => {
            return Object.values(row).some(val => 
                val !== null && String(val).includes(targetCatValue)
            );
        });
    }

    let totalRows = dataset.data.length;
    const isTruncated = totalRows > 110; 


// 🚀 ==========================================
    // 🌟 เครื่องจักร Pivot Table (พลิกแถวเป็นคอลัมน์)
    // ==========================================
    let globalTableView = "normal";
    if (typeof currentCheeseData !== 'undefined' && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.table_view) {
        globalTableView = currentCheeseData.PrepareReport.table_view;
    }

    if (globalTableView === "pivot" && dataset.headers.includes("วันที่ (Trend Axis)")) {
       // 🚀 ==========================================
    // 🌟 เครื่องจักร Pivot Table อัปเกรด! (รองรับ SUM, AVG, MAX, MIN, MEDIAN, MODE)
    // ==========================================
    let globalTableView = "normal";
    let globalPivotAgg = "SUM"; // สูตรเริ่มต้น
    if (typeof currentCheeseData !== 'undefined' && currentCheeseData.PrepareReport) {
        if (currentCheeseData.PrepareReport.table_view) globalTableView = currentCheeseData.PrepareReport.table_view;
        if (currentCheeseData.PrepareReport.pivot_agg) globalPivotAgg = currentCheeseData.PrepareReport.pivot_agg;
    }

    if (globalTableView === "pivot" && dataset.headers.includes("วันที่ (Trend Axis)")) {
        const pivotCol = "วันที่ (Trend Axis)";
        const valCol = dataset.headers.find(h => String(h).includes("Summary_Col_")) || dataset.headers[dataset.headers.length - 1];
        const rowIdentifierCols = dataset.headers.filter(h => h !== pivotCol && h !== valCol);

        let dynamicHeaders = [...new Set(dataset.data.map(row => formatTrendDate(row[pivotCol], globalDateFormat)))].filter(Boolean).sort();
        let pivotedDataMap = {};

        // 🌟 1. วนลูปจับยัดข้อมูล "ใส่เป็น Array" รอไว้ก่อน
        dataset.data.forEach(row => {
            let rowKey = row['id'] + '_' + rowIdentifierCols.map(col => row[col]).join('_');

            if (!pivotedDataMap[rowKey]) {
                pivotedDataMap[rowKey] = { id: row['id'] };
                rowIdentifierCols.forEach(col => pivotedDataMap[rowKey][col] = row[col]);
                // เตรียมกล่อง Array เปล่าๆ ไว้ใส่วันที่
                dynamicHeaders.forEach(h => pivotedDataMap[rowKey][h] = []);
            }

            let rawVal = row[valCol];
            if (typeof rawVal === 'string') rawVal = rawVal.replace(/<[^>]*>?/gm, ''); 
            
            let formattedDate = formatTrendDate(row[pivotCol], globalDateFormat);
            let val = parseFloat(rawVal) || 0;
            
            if (formattedDate) {
                // โยนตัวเลขใส่กล่อง Array ของวันที่นั้นๆ
                pivotedDataMap[rowKey][formattedDate].push(val); 
            }
        });

        // 🌟 2. เข้าสู่กระบวนการ "บีบอัด" Array ให้เหลือเลขเดียว ตามสูตรที่เลือก!
        Object.keys(pivotedDataMap).forEach(rowKey => {
            dynamicHeaders.forEach(h => {
                let arr = pivotedDataMap[rowKey][h];
                if (!arr || arr.length === 0) {
                    pivotedDataMap[rowKey][h] = 0; // ถ้าไม่มีข้อมูลเลย ให้เป็น 0
                } else {
                    // คำนวณตามสูตร
                    if (globalPivotAgg === "SUM") {
                        pivotedDataMap[rowKey][h] = arr.reduce((a, b) => a + b, 0);
                    } else if (globalPivotAgg === "MAX") {
                        pivotedDataMap[rowKey][h] = Math.max(...arr);
                    } else if (globalPivotAgg === "MIN") {
                        pivotedDataMap[rowKey][h] = Math.min(...arr);
                    } else if (globalPivotAgg === "AVG") {
                        pivotedDataMap[rowKey][h] = Number((arr.reduce((a, b) => a + b, 0) / arr.length).toFixed(2));
                    } else if (globalPivotAgg === "MEDIAN") {
                        arr.sort((a, b) => a - b);
                        const mid = Math.floor(arr.length / 2);
                        pivotedDataMap[rowKey][h] = arr.length % 2 !== 0 ? arr[mid] : (arr[mid - 1] + arr[mid]) / 2;
                    } else if (globalPivotAgg === "MODE") {
                        const modeMap = {};
                        let maxEl = arr[0], maxCount = 1;
                        arr.forEach(el => {
                            modeMap[el] = (modeMap[el] || 0) + 1;
                            if (modeMap[el] > maxCount) { maxEl = el; maxCount = modeMap[el]; }
                        });
                        pivotedDataMap[rowKey][h] = maxEl;
                    }
                }
            });
        });

        // 3. สลับร่างข้อมูลเดิมทิ้งไป
        dataset.headers = [...rowIdentifierCols, ...dynamicHeaders];
        dataset.data = Object.values(pivotedDataMap);
        globalDateFormat = "raw"; 
    }
    // ==========================================
    }
    // ==========================================


	// 🚀 ==========================================
    // 🌟 สับรางรถไฟ: ถ้าเป็นโหมด BI ให้โยนเข้าเครื่องจักรชุดใหญ่
    // ==========================================
 // 🚀 ==========================================
    // 🌟 เช็คสถานะโหมด BI จากการตั้งค่าของผู้ใช้
    // ==========================================
    let activeBIMode = false; // รับค่าจาก Parameter เป็นหลักก่อน
    if (currentCheeseData.PrepareReport.bi_mode === "on") {
        activeBIMode = true;
    }


    // 🌟 สับรางรถไฟ: ถ้าเป็นโหมด BI ให้โยนเข้าเครื่องจักรชุดใหญ่
    if (activeBIMode) {
        let fakeSql = `INSERT INTO \`${dataset.tables || 'DrillDownData'}\` (\`${dataset.headers.join('`, `')}\`) VALUES \n`;
        
        const sqlRows = dataset.data.map(row => {
            const values = dataset.headers.map(h => {
                let val = row[h];
                if (val === null || val === undefined) val = "";
                
                // 🌟 1. ดักแปลง Format วันที่ (สำหรับตาราง Normal ที่ไม่ได้ผ่านการ Pivot)
                if (h === "วันที่ (Trend Axis)" && globalDateFormat !== "raw") {
                    val = formatTrendDate(val, globalDateFormat);
                }

                // 🌟 2. ล้างแท็ก HTML (เช่น <a>) ออกให้หมดก่อนแปลงเป็น SQL
                if (typeof val === 'string') {
                    val = val.replace(/<[^>]*>?/gm, '');
                }

                return typeof formatSqlValue === "function" ? formatSqlValue(val) : `'${val}'`;
            });
            return `(${values.join(', ')})`;
        });

        fakeSql += sqlRows.join(',\n') + ";";

        if (typeof renderSqlPreview === "function") {
            renderSqlPreview(fakeSql, dataset.tables || 'DrillDownData');
            console.log("🚀 [BI Mode] ยิงข้อมูลเข้าชุดคำสั่ง CSV/SQL สำเร็จ!");
        }
        
        // จบการทำงานตรงนี้เลย ไม่ต้องเลื่อนลงไปวาดตาราง HTML ดั้งเดิม!
        return; 
    }
   // 🚀 ==========================================
    // 🌟 เครื่องจักรจัดลำดับ & วุ้นแปลภาษา (เปลี่ยนชื่อคอลัมน์ + ข้อมูล)
    // ==========================================
    if (typeof currentCheeseData !== 'undefined' && currentCheeseData.FilterChain) {
        let headerMapping = {}; // สมุดจดว่า คำเดิม -> คำใหม่ คืออะไร
        let finalHeaders = [];  // หัวตารางที่จะเอาไปโชว์จริงๆ

        currentCheeseData.FilterChain.forEach(filter => {
            if (filter.display_columns && filter.display_columns.trim() !== "") {
                const selectedValues = filter.display_columns.split(',').map(v => v.trim()).filter(Boolean);
                
                selectedValues.forEach(val => { // val คือรหัส เช่น data_value5
                    let originalLabel = val; 
                    let newLabel = val;      

                    // 1. ไปหา "ชื่อเดิม" ที่ Java น่าจะส่งมา (หาจาก saved_columns)
                    if (filter.saved_columns) {
                        const foundCol = filter.saved_columns.find(c => c.value === val);
                        if (foundCol && foundCol.text) originalLabel = foundCol.text;
                    }

                    // 2. ไปหา "ชื่อใหม่" ที่บอสพิมพ์แก้ไว้ (หาจาก custom_labels)
                    if (filter.custom_labels) {
                        const foundCol = filter.custom_labels.find(c => c.value === val);
                        if (foundCol && foundCol.text) newLabel = foundCol.text;
                    }

                    // 3. เช็คว่าในข้อมูลดิบ (dataset.headers) Java มันส่งชื่อเดิม หรือ ส่งรหัสมา?
                    let matchedOriginalHeader = null;
                    if (dataset.headers.includes(originalLabel)) {
                        matchedOriginalHeader = originalLabel;
                    } else if (dataset.headers.includes(val)) {
                        matchedOriginalHeader = val;
                    }

                    // 4. ถ้าหาเจอ จับคู่ลงสมุดจดเลย! (คำเดิม -> คำใหม่)
                    if (matchedOriginalHeader) {
                        headerMapping[matchedOriginalHeader] = newLabel;
                        if (!finalHeaders.includes(newLabel)) {
                            finalHeaders.push(newLabel);
                        }
                    }
                });
            }
        });

        // 🌟 5. ทำการ "แปลงร่าง" ข้อมูลให้เป็นชื่อใหม่ทั้งหมด!
        if (Object.keys(headerMapping).length > 0) {
            // เปลี่ยนหัวตาราง
            dataset.headers = finalHeaders;
            
            // เปลี่ยน Key ของข้อมูลแต่ละบรรทัด ให้ตรงกับหัวตารางใหม่
            dataset.data = dataset.data.map(row => {
                let newRow = { id: row.id }; // เก็บ id ไว้เหมือนเดิม
                
                Object.keys(row).forEach(oldKey => {
                    if (headerMapping[oldKey]) {
                        newRow[headerMapping[oldKey]] = row[oldKey]; // ย้ายข้อมูลไปชื่อใหม่
                    } else {
                        newRow[oldKey] = row[oldKey]; // คอลัมน์อื่นๆ ที่ไม่ได้แก้ ก็เก็บไว้เหมือนเดิม
                    }
                });
                return newRow;
            });
        }
    }
    // ==========================================

    let html = '<table class="my-tables">';
    
    // --- 1. หัวตาราง ---
    html += '<thead><tr>';
    html += '<th>#</th>'; 
    dataset.headers.forEach(headerName => {
        html += `<th>${headerName}</th>`;
    });
    html += '<th>Reference_id</th></tr></thead>';

    // --- 2. เนื้อหาตาราง ---
    html += '<tbody>';
    
    dataset.data.forEach((row, index) => {
        const rowNum = index + 1; 
        
        let shouldShow = true;
        if (isTruncated) {
            if (index >= 100 && index < totalRows - 10) {
                shouldShow = false;
            }
        }

        if (shouldShow) {
            let refValue = row['id'];
            if (refValue === null || refValue === undefined) refValue = "-";
            
            html += `<tr data-id="${refValue}">`;
            html += `<td style="background: #f4f4f4; font-weight: bold; text-align: center;">${rowNum}</td>`; 
            
            dataset.headers.forEach(headerName => {
                let value = row[headerName];
                if (value === null || value === undefined) value = "";
                
                // 🚀 [พระเอกอยู่ตรงนี้!] ถ้าเจอคอลัมน์ชื่อนี้ ให้โยนเข้าเครื่องแปลงวันที่เลย!
                else if (headerName === "วันที่ (Trend Axis)") {
                    value = formatTrendDate(value, globalDateFormat);
                }

                html += `<td>${value}</td>`;
            });

            html += `<td style="color: gray;">${refValue}</td>`;
            html += '</tr>';
        } 
        else if (isTruncated && index === 100) {
            const colSpan = dataset.headers.length + 2; 
            html += `<tr><td colspan="${colSpan}" style="text-align: center; background: #fafafa; color: #999;">...</td></tr>`;
        }
    });

    html += '</tbody></table>';

    // --- 3. การแสดงผล ---
    const vizGrid = document.getElementById("viz-grid");
    if(vizGrid) vizGrid.innerHTML = "";
    
    propertyicon(1, dataset.id_refer, dataset.id_config);
    
    const targetElement = document.getElementById(id);
    if(targetElement) targetElement.innerHTML = html;

    if(document.getElementById("total_json")) document.getElementById("total_json").innerHTML = dataset.total_json;
    if(document.getElementById("total_data")) document.getElementById("total_data").innerHTML = dataset.total_data;
}






function renderQuickWindow(data) {

    const tabContainer = document.getElementById('tab-container');
    const panelContainer = document.getElementById('panel-container');
    
    // Clear ของเก่า
    tabContainer.innerHTML = '';
    

    // 1. สร้าง Tabs
    data.forEach((chain, index) => {
        const tab = document.createElement('div');
        tab.className = `tab ${index === 0 ? 'active' : ''}`; // ตัวแรกให้ Active เสมอ
        tab.innerText = chain.type;
        
        // Event คลิกเปลี่ยน Tab
        tab.onclick = () => {
            // ย้าย class active
            document.querySelectorAll('.tab').forEach(t => t.classList.remove('active'));
            tab.classList.add('active');
            // สั่งวาด Form ใหม่
            renderFormContent(chain.rules, panelContainer);
        };
        
        tabContainer.appendChild(tab);
    });

    // วาดหน้าแรกทันที
    if (data.length > 0) {
        renderFormContent(data[0].rules, panelContainer);
    }
}

function renderFormContent(rules, container) {
    container.innerHTML = ''; // ล้างหน้าจอ panel

    if (!rules || rules.length === 0) {
        container.innerHTML = '<div style="color:gray;">No conditions defined.</div>';
        return;
    }

    // 2. จัดกลุ่มข้อมูลตาม 'group' (Group 1, Group 2...)
    // ผลลัพธ์จะเป็น Object เช่น { "1": [...], "2": [...] }
    const groupedRules = rules.reduce((acc, rule) => {
        const g = rule.group || "Other";
        if (!acc[g]) acc[g] = [];
        acc[g].push(rule);
        return acc;
    }, {});

    // 3. วนลูปวาดทีละกลุ่ม
    Object.keys(groupedRules).forEach((groupId, index) => {
        const groupItems = groupedRules[groupId];

        // สร้าง Header คั่นกลุ่ม (AND / OR)
        const headerDiv = document.createElement('div');
        headerDiv.className = 'section-header';
        
        // Logic การตั้งชื่อ Header: กลุ่มแรกเป็น "Conditions", กลุ่มถัดไปเป็น "OR (Group X)"
        let headerText = index === 0 ? `Condition Group ${groupId}` : `OR (Group ${groupId})`;
        
        headerDiv.innerHTML = `
            <span>${headerText}</span>
            <div class="line"></div>
        `;
        container.appendChild(headerDiv);

        // วาด Input fields ในกลุ่มนั้น
        groupItems.forEach(rule => {
            const row = createInputRow(rule);
            container.appendChild(row);
        });
    });
}

// ฟังก์ชันสร้าง HTML ของ Input แต่ละประเภท
function createInputRow(rule) {
    const row = document.createElement('div');
    row.className = 'form-row';

    const label = document.createElement('span');
    label.className = 'label';
    label.innerText = rule.column_tag + ":";
    row.appendChild(label);

    let input;

    // ตรวจสอบ Type และสร้าง Input
    switch (rule.type) {
        case 'text':
            input = document.createElement('input');
            input.type = 'text';
            input.value = rule.value || '';
            break;
            
        case 'number':
            input = document.createElement('input');
            input.type = 'number';
            input.value = rule.value || '';
            break;
            
        case 'date':
            input = document.createElement('input');
            input.type = 'date';
            input.value = rule.value || '';
            break;

        case 'select':
        case 'select_multiple': // หรือ selecte ตามที่คุณพิมพ์
            input = document.createElement('select');
            if (rule.type === 'select_multiple') {
                input.multiple = true;
                input.style.height = "80px"; // ถ้า multiple ให้สูงหน่อย
            }
            
            // แปลง valueselected เป็น Options
            // สมมติรูปแบบเป็น "Value:Text,Value:Text" หรือ "Text,Text"
            if (rule.valueselected) {
                const options = rule.valueselected.split(',');
                options.forEach(opt => {
                    let [val, txt] = opt.split(':');
                    if (!txt) txt = val; // กรณีไม่มี : ให้ใช้ค่าเดิม

                    const optionElem = document.createElement('option');
                    optionElem.value = val.trim();
                    optionElem.innerText = txt.trim();
                    
                    // เช็ค Selected (ถ้าตรงกับ rule.value)
                    if (rule.value && rule.value.includes(val.trim())) {
                         optionElem.selected = true;
                    }
                    
                    input.appendChild(optionElem);
                });
            }
            break;

        default: // กรณีไม่รู้จัก Type ให้เป็น Text ธรรมดา
            input = document.createElement('input');
            input.type = 'text';
            input.value = rule.value || '';
    }
    
    // ใส่ id เผื่อดึงค่ากลับ
    input.id = `field-${rule.id}`; 
    input.setAttribute('data-rule-id', rule.id);

    row.appendChild(input);
    return row;
}


 function renderSubTable(key,id) {
	
	
	
	
    // 1. เช็คก่อนว่ามีข้อมูลไหม
    if (!jsonData || jsonData.length === 0) {
        console.log("ไม่มีข้อมูล");
        return;
    }
    

    

    // 2. ดึงแค่ "ชุดแรก" (Index 0) ตามที่ขอ
    const dataset = jsonData[key]; 
    
    // ถ้าชุดแรกไม่มี data หรือ headers ก็จบข่าว
    if (!dataset.headers || !dataset.data) return;


    // 3. เริ่มสร้าง HTML Table
    let html = `<b>${dataset.tables}</b><br><br><table class="my-tables">`;
    
    // --- ส่วนหัวตาราง (Thead) ---
    html += '<thead><tr>';
    // วนลูปสร้างหัวตารางตาม list "headers" ที่ส่งมา
    dataset.headers.forEach(headerName => {
        html += `<th>${headerName}</th>`;
    });
    html += '<th>Refer_id</th></tr></thead>';

    // --- ส่วนเนื้อหา (Tbody) ---
    html += '<tbody>';
    dataset.data.forEach(row => {
        // แอบฝัง id ไว้ใน tr เผื่อเอาไปใช้กดคลิก (row.id มาจาก Java ที่เราเตรียมไว้)
        html += `<tr data-id="${row.id}">`;
        
        // วนลูป headers อีกครั้ง เพื่อดึงค่าให้ตรงกับคอลัมน์ (Data mapping)
        dataset.headers.forEach(headerName => {
            // ดึงค่าโดยใช้ชื่อ Header เป็น Key (เพราะใน Java เรา put เป็นชื่อ Header ไว้แล้ว)
            let value = row[headerName]; 
            
            // เช็ค null/undefined กันเหนียว
            if (value === null || value === undefined) value = "";
            
            html += `<td>${value}</td>`;
        });
    let refValue = row['id_refer'];
        if (refValue === null || refValue === undefined) refValue = "-"; // หรือ ""
        
        html += `<td style="color: gray;">${refValue}</td>`;
        html += '</tr>';
    });
    html += '</tbody>';
    html += '</table>';

  

	//document.getElementById("total_json").innerHTML = dataset.totalData;
    // 4. แปะลงในหน้าเว็บ

    document.getElementById(id).innerHTML = html;

}



function buildValueMappingUI() {
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

    if (!activeConfigObj.valueMappings) activeConfigObj.valueMappings = [];

    // ==========================================
    // 🌟 ระบบ ERP แทรกแซง: เตรียมคอลัมน์ปกติ + คอลัมน์เสมือน
    // ==========================================
    let displayColumns = [];

    // 1. ดึงคอลัมน์ปกติ
    activeConfigObj.mappedColumns.forEach(col => {
        displayColumns.push({ name: col.source, isVirtual: false });
    });

    // 2. ดึงคอลัมน์เสมือน (Merge Rules) มาต่อท้าย
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!displayColumns.find(c => c.name === rule.targetName)) {
                displayColumns.push({ name: rule.targetName, isVirtual: true });
            }
        });
    }

    // 3. กรองคอลัมน์ที่ถูกตัดทิ้ง (Ignored) ออกไป จะได้ไม่ต้องเอามาตั้งค่าให้เสียเวลา
    let filteredColumns = displayColumns.filter(col => 
        !(activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(col.name))
    );

    // 4. สร้าง HTML สำหรับ Option 
    let optionsHtml = filteredColumns.map(col => {
        let label = col.isVirtual ? `${col.name} 🧩` : col.name;
        return `<option value="${col.name}">${label}</option>`;
    }).join('');
    // ==========================================

    let html = '<h4 style="margin-bottom: 10px; color: #2e6da4; border-bottom: 2px solid #2e6da4; padding-bottom: 8px; font-weight: bold;">🔄 กำหนดการแทนค่าข้อมูล (Value Transformation)</h4>';
    
    // --- ส่วนเพิ่มเงื่อนไขใหม่ ---
    html += `
    <div style="background: #ebf2f7; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #bcdff1;">
        <p style="font-size: 13px; font-weight: bold; margin-bottom: 10px; color: #31708f;">เพิ่มเงื่อนไขการแทนค่าใหม่:</p>
        <div style="display: flex; gap: 10px; align-items: flex-end;">
            <div style="flex: 1;">
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">เลือกคอลัมน์</label>
                <select id="vm_column" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
                    ${optionsHtml}
                </select>
            </div>
            <div style="flex: 1;">
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">ถ้าค่าเดิมคือ (Exact)</label>
                <input type="text" id="vm_source_val" placeholder="เช่น 4" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
            </div>
            <div style="flex: 1;">
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">แทนด้วยค่าใหม่</label>
                <input type="text" id="vm_target_val" placeholder="เช่น BLACKLISTED" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
            </div>
            <button onclick="addValueMapping()" style="background: #31708f; color: white; border: none; padding: 7px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;">+ เพิ่ม</button>
        </div>
    </div>`;

    // --- ส่วนรายการที่ตั้งค่าไว้แล้ว ---
    html += `<div id="mapping_list_container">`;
    html += renderMappingList(); // เรียกฟังก์ชันย่อยวาดรายการ
    html += `</div>`;

    const container = document.getElementById('Form-TabSet-trans-2');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block';
    }
}
function addValueMapping() {
    const col = document.getElementById('vm_column').value;
    const srcRaw = document.getElementById('vm_source_val').value.trim();
    const tgtRaw = document.getElementById('vm_target_val').value.trim();

    if (srcRaw === "" ) {
        alert("กรุณากรอกค่าเดิมและค่าใหม่ให้ครบถ้วน");
        return;
    }

    const parseInput = (str) => {
        if (str.startsWith('[') && str.endsWith(']')) {
            try {
                let jsonValid = str.replace(/'/g, '"');
                return JSON.parse(jsonValid);
            } catch (e) {
                return null;
            }
        }
        return [str]; 
    };

    const srcArray = parseInput(srcRaw);
    const tgtArray = parseInput(tgtRaw);

    if (!srcArray || !tgtArray || srcArray.length !== tgtArray.length) {
        alert("ข้อมูลไม่ถูกต้อง หรือจำนวนค่าไม่สัมพันธ์กัน");
        return;
    }

    // สร้าง ID สำหรับกลุ่มนี้โดยเฉพาะ
    const groupId = Date.now();

    srcArray.forEach((val, index) => {
        let s = val.toString().trim();
        let t = tgtArray[index].toString().trim();

        // ลบค่าซ้ำเก่าออกก่อน (ถ้ามี)
        activeConfigObj.valueMappings = activeConfigObj.valueMappings.filter(m => !(m.column === col && m.source === s));
        
        activeConfigObj.valueMappings.push({
            groupId: groupId, // 🌟 เก็บ ID กลุ่มไว้
            column: col,
            source: s,
            target: t
        });
    });

    saveAndRefreshVM();
    document.getElementById('vm_source_val').value = "";
    document.getElementById('vm_target_val').value = "";
}
function removeValueMappingGroup(groupId) {
    if (!confirm("คุณต้องการลบเงื่อนไขการแทนค่ากลุ่มนี้ทั้งหมดใช่หรือไม่?")) return;

    // กรองเอาตัวที่ groupId ไม่ตรงกับที่ส่งมา (ลบยกกลุ่ม)
    activeConfigObj.valueMappings = activeConfigObj.valueMappings.filter(m => {
        const mGid = m.groupId || `single-${m.column}-${m.source}`;
        return mGid.toString() !== groupId.toString();
    });

    saveAndRefreshVM();
}
function saveAndRefreshVM() {
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    document.getElementById('mapping_list_container').innerHTML = renderMappingList();
    
    // อัปเดตตารางพรีวิวหลักทันทีเพื่อให้เห็นการเปลี่ยนแปลง
    if (typeof refreshPreviewTable === "function") refreshPreviewTable();
}


function renderMappingList() {
    let mappings = activeConfigObj.valueMappings || [];
    if (mappings.length === 0) {
        return '<p style="text-align: center; color: #999; font-style: italic; padding: 20px;">ยังไม่มีการตั้งค่า</p>';
    }

    // --- 🌟 ขั้นตอนการจัดกลุ่ม (Grouping) ---
    const groups = {};
    mappings.forEach(m => {
        const gid = m.groupId || `single-${m.column}-${m.source}`; // รองรับข้อมูลเก่าที่ไม่มี groupId
        if (!groups[gid]) groups[gid] = [];
        groups[gid].push(m);
    });

    let listHtml = '<table style="width: 100%; border-collapse: collapse; font-size: 13px; background: white; border: 1px solid #ddd;">';
    listHtml += '<thead style="background: #f9f9f9;"><tr><th style="padding: 8px; border-bottom: 2px solid #ddd; text-align: left;">คอลัมน์</th><th style="padding: 8px; border-bottom: 2px solid #ddd;">ค่าที่จับคู่ (เป็นกลุ่ม)</th><th style="padding: 8px; border-bottom: 2px solid #ddd; text-align: center;">จัดการ</th></tr></thead>';
    listHtml += '<tbody>';

    Object.keys(groups).forEach(gid => {
        const items = groups[gid];
        const first = items[0];
        
        // รวบรวมค่า source และ target มาแสดงเป็นก้อนเดียว
        const displayMaps = items.map(it => 
            `<div style="margin-bottom:4px;"><code style="background:#eee;padding:2px 5px;">${it.source}</code> <span style="color:#2e6da4;">→</span> <b>${it.target}</b></div>`
        ).join('');

        listHtml += `
        <tr style="border-bottom: 1px solid #eee;">
            <td style="padding: 10px; vertical-align: top;"><b>${first.column}</b></td>
            <td style="padding: 10px;">${displayMaps}</td>
            <td style="padding: 10px; text-align: center; vertical-align: middle;">
                <button onclick="removeValueMappingGroup('${gid}')" style="background: #f2dede; border: 1px solid #ebccd1; color: #a94442; padding: 4px 8px; border-radius: 4px; cursor: pointer; font-size: 11px;">
                    🗑️ ลบทั้งก้อน
                </button>
            </td>
        </tr>`;
    });

    listHtml += '</tbody></table>';
    return listHtml;
}











// ⚙️ ฟังก์ชันสร้างเมนูเครื่องมือด้านข้าง
function propertyicon(type, id_refer, id_config) {
    const vizGrid = document.getElementById("viz-grid");
    if (!vizGrid) return;

    // ==========================================
    // 📊 TYPE 1: สำหรับตารางข้อมูล (ของเดิมของคุณ)
    // ==========================================
    if (type == 1) {
        let html = `<div class="viz-btn" title="Filter" onclick="RibbontoggleFilters('pane-fields');getData('/setupFilterChain-filemanager','pane-fields')">
            <svg viewBox="0 0 24 24" width="24" height="24" fill="currentColor"><path d="M10 18h4v-2h-4v2zM3 6v2h18V6H3zm3 7h12v-2H6v2z"></path></svg>
        </div>`;
        vizGrid.insertAdjacentHTML('beforeend', html);
        
        let html2 = `<div class="viz-btn" title="Property" onclick="RibbontoggleFilters('pane-fields');getData('/setproperty-filemanager','pane-fields')">...</div>`;
        vizGrid.insertAdjacentHTML('beforeend', html2);  
    }
    
    // ==========================================
    // 🎨 TYPE 2: สำหรับกล่อง Dashboard (แยกมาใหม่เลย!)
    // ==========================================
    else if (type == 2) {
        // 1. ไอคอนตั้งค่ากราฟ (Bar Chart Icon)
        let btnChart = `<div class="viz-btn" title="ตั้งค่ากราฟ (Chart)" onclick="openChartSettings('${id_refer}');">
            <svg viewBox="0 0 24 24" width="24" height="24" fill="currentColor"><path d="M5 9.2h3V19H5zM10.6 5h2.8v14h-2.8zm5.6 8H19v6h-2.8z"></path></svg>
        </div>`;
        vizGrid.insertAdjacentHTML('beforeend', btnChart);

    // 2. ไอคอนเพิ่มข้อความ (Text Box Icon - รูปตัว T)
    let btnProp = `<div class="viz-btn" title="เพิ่มข้อความ (Text Box)" onclick="addTextBox('${id_refer}')">
        <svg viewBox="0 0 24 24" width="24" height="24" fill="currentColor">
            <path d="M5 4v3h5.5v12h3V7H19V4H5z"></path>
        </svg>
    </div>`;
        vizGrid.insertAdjacentHTML('beforeend', btnProp);

        // 3. (แถมให้ครับ) ไอคอนลบกล่องทิ้งจากเมนูนี้ได้เลย เผื่อขี้เกียจไปกดกากบาทที่มุมกล่อง
        let btnDelete = `<div class="viz-btn" title="ลบกล่องทิ้ง" onclick="document.getElementById('${id_refer}').remove(); this.parentElement.innerHTML=''; " style="color: #e74c3c;">
            <svg viewBox="0 0 24 24" width="24" height="24" fill="currentColor"><path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"></path></svg>
        </div>`;
        vizGrid.insertAdjacentHTML('beforeend', btnDelete);
    }
}



function propertyFilter(){


    let html = '';

    // ลูปที่ 1: วนตามจำนวน Table
    jsonData.forEach((tableObj, index) => {
        
        // --- แก้ตรงนี้: ดึงชื่อ Table จาก JSON (Key ชื่อ "tables") ---
        // ถ้าไม่มีค่า ให้แสดงเป็น Table + ลำดับ แทน
        let tableName = tableObj.tables || `Table ${index + 1}`;
        
        // สร้าง ID สำหรับควบคุมการ Show/Hide
        let groupId = `group-${index}`;

        html += `<div class="table-group-wrapper">`;

        // --- ส่วน Parent (ชื่อตาราง) ---
        html += `
            <div class="reporttable-item" >
                <input type="checkbox" 
                       id="cb-table-${index}" 
                       onchange="toggleGroup(this, '${groupId}')"
                       style="margin-right:5px;"> 
                ${tableName} 
          (${tableObj.totalData || 0} รายการ)
            </div>
        `;

        html += `</div>`; // ปิด wrapper
    });

    document.getElementById("field-list").innerHTML = html;
}



function getDataJson(url, div, appId ) {


            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.timeout = 60000; 
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    var responseURL = xhr.responseURL;
                    if (responseURL && responseURL.indexOf('login') !== -1) {
                        // ถ้า URL ของการตอบสนองมี "login", แสดงว่า session หมดอายุ
                        alert('Session หมดอายุ กรุณา login ใหม่');
                        window.location.href = '/login'; // หรือ redirect ไปยังหน้า login
                    } else {    

						if(appId == "QuickWindow"){
								QuickWindow(xhr.responseText);
						}
						else if(appId == "DiffCl"){
								DiffCl(xhr.responseText);
						}
						else{
							renderFirstTable(xhr.responseText,appId);
						}

                    	
                    
                    	
                    }
                } else {
                    alert(`HTTP error! Status: ${xhr.status}`);
                }
            
            };

            xhr.onerror = function () {
                alert('Network error occurred');
                element.style.display = 'none';
            };

         xhr.ontimeout = function () {
          alert('คำขอใช้เวลานานเกินไป กรุณาลองใหม่อีกครั้ง');
              if (element) {
                  element.style.display = 'none';
              }
          };

            xhr.send();
        

}



function Filterlist(ex){
  const Filterlistkeyword = ex.value.toLowerCase();
  const Filterlistitemss = document.querySelectorAll("#field-list .reporttable-item");

  Filterlistitemss.forEach(item => {
      const text = item.textContent.toLowerCase();
      item.style.display = text.includes(Filterlistkeyword) ? "" : "none";
  });

}

function updateHiddenField(ruleId, selectElem) {
    var hiddenInput = document.getElementById('hid_' + ruleId);
    
    // กรณี Multiple: เอาค่ามาต่อกันด้วย comma (หรือแล้วแต่ Logic Java คุณ)
    // เช่น "A,B,C"
    var selectedValues = Array.from(selectElem.selectedOptions).map(option => option.value);
    
    // อัพเดทลง Hidden Input
    hiddenInput.value = selectedValues.join('|'); 
}


function translateFullCheeseToHuman() {
    var cheeseData = currentCheeseData;
    if (!cheeseData || !cheeseData.FilterChain) return "ดึงข้อมูลทั้งหมด";

    let allChainDescriptions = [];
    cheeseData.FilterChain.forEach(chainObj => {
	
	
        if (chainObj.status === 0 || !chainObj.json_out_put) return;

        try {
            const rules = JSON.parse(chainObj.json_out_put);
            let currentChainRules = [];

            rules.forEach(rule => {
	
				 // 🌟 [เงื่อนไขใหม่] เช็คว่าเป็นกฎกลุ่มเวลาหรือไม่
                const isTimeRule = rule.operation.includes("_months_passed");
                // 🌟 [เงื่อนไขใหม่] เช็คค่าว่าง (Empty, Quotes, Dash)
                const isEmpty = (!rule.value || rule.value.trim() === "" || rule.value === "''" || rule.value === "'-'");

                // 1. ถ้าว่างและไม่ใช่กลุ่มเวลา ให้ข้ามไปเลย
                if (isEmpty && !isTimeRule) return;
                // 2. ถ้ามีคำว่า #this_ ให้ตัดออกจากการเเสดงผล
                if (rule.value && rule.value.includes("#this_")) return;


                let colName = rule.column_tag;
                if (chainObj.saved_columns) {
                    const found = chainObj.saved_columns.find(c => c.value === rule.column_id);
                    if (found) colName = found.text; // 🔍 ใช้ชื่อคอลัมน์ที่ตั้งใหม่
                }

                let opReadable = "";
                switch(rule.operation) {
                    case "=": opReadable = "เป็น"; break;
                    case "!=": opReadable = "ไม่เป็น"; break;
                    case ">": opReadable = "มากกว่า"; break;
                    case "<": opReadable = "น้อยกว่า"; break;
                    case "=>": case "(D)=>": case "(M)=>": opReadable = "ตั้งแต่"; break;
                    case "<=": case "(D)<=": case "(M)<=": opReadable = "ไม่เกิน"; break;
                    case "link%": opReadable = "ขึ้นต้นด้วย"; break;
                    case "%link": opReadable = "ลงท้ายด้วย"; break;
                    case "%link%": opReadable = "มีคำว่า"; break;
                    case "find_in_set": opReadable = "เลือกเฉพาะ"; break; // 💡 แก้คำให้พนักงานไม่ งง[cite: 1]
                    case "not_find_in_set": opReadable = "ยกเว้น"; break;
                    case "Is null": case "Is empty": opReadable = "ไม่มีข้อมูล"; break;
                    case "Is not null": case "Is not empty": opReadable = "มีข้อมูล"; break;
                    case "(C)>=": opReadable = "มีจำนวนตั้งแต่"; break;
                    case "(C)<=": opReadable = "มีจำนวนไม่เกิน"; break;
                    case "3_months_passed": opReadable = "ผ่านมาแล้ว 3 เดือน"; break;
                    case "6_months_passed": opReadable = "ผ่านมาแล้ว 6 เดือน"; break;
                    case "12_months_passed": opReadable = "ผ่านมาแล้ว 1 ปี"; break;
                    case "24_months_passed": opReadable = "ผ่านมาแล้ว 2 ปี"; break;
                    default: opReadable = rule.operation;
                }
                currentChainRules.push(`'${colName}' ${opReadable} '${rule.value}'`);
            });
            if (currentChainRules.length > 0) allChainDescriptions.push(currentChainRules.join(" และ "));
        } catch (e) { console.error(e); }
    });
    return allChainDescriptions.length > 0 ? allChainDescriptions.join(" และ ") : "ดึงข้อมูลทั้งหมด";
}

// 🌟 เพิ่ม parameter: filterStr เผื่อใช้กรองข้อมูลก่อน SyncBack
function loadSyncBack(filterStr = "") {
	
	    
	console.log("🚀 loadSyncBack เริ่มทำงาน...");

    // 1. ตรวจสอบว่ามีข้อมูลไหม
    if (!jsonData) {
        console.error("❌ jsonData เป็นค่าว่าง");
        return;
    }

    let rawData;
    try {
        // 2. ถ้า jsonData เป็น String ให้ Parse ก่อน
        rawData = (typeof jsonData === "string") ? JSON.parse(jsonData) : jsonData;
       var FullCheese = translateFullCheeseToHuman();
  
	  var hiddenInput = document.getElementById("FullCheeseDescInput");
	    if (hiddenInput) {
	        hiddenInput.value = FullCheese;
	    }
  
  
       console.log("🚀 FullCheese."+FullCheese);
    } catch (e) {
        console.error("❌ Parse JSON ไม่สำเร็จ:", e);
        return;
    }

    // 3. ตรวจสอบโครงสร้าง Array [ { headers:..., data:... } ]
    if (!Array.isArray(rawData) || rawData.length === 0) {
        console.error("❌ โครงสร้างข้อมูลไม่ถูกต้อง (ไม่ใช่ Array หรือ Array ว่าง)");
        return;
    }

    // 🌟 4. ดึงก้อนข้อมูลออกมา
    let dataset = rawData[0]; 

    // ตรวจสอบ headers และ data
    if (!dataset.headers || !dataset.data) {
        console.error("❌ ไม่พบ headers หรือ data ใน dataset", dataset);
        return;
    }

   

    const processIdElem = document.getElementById("SyncBackprocess_id");
    if (processIdElem) processIdElem.value = "1";

    // ==========================================
    // 🌟 ดึงการตั้งค่า Global (Date Format, Pivot, BI Mode)
    // ==========================================
    let globalDateFormat = "yyyy-mm-dd";
    let globalTableView = "normal";
    let globalPivotAgg = "SUM";
    let activeBIMode = false;



    if (typeof currentCheeseData !== 'undefined' && currentCheeseData.PrepareReport) {
        if (currentCheeseData.PrepareReport.date_format) globalDateFormat = currentCheeseData.PrepareReport.date_format;
        if (currentCheeseData.PrepareReport.table_view) globalTableView = currentCheeseData.PrepareReport.table_view;
        if (currentCheeseData.PrepareReport.pivot_agg) globalPivotAgg = currentCheeseData.PrepareReport.pivot_agg;
        if (currentCheeseData.PrepareReport.bi_mode === "on") activeBIMode = true;
    }


	
			
			// 🎯 แก้ไขลอจิกการกรองให้ฉลาดขึ้น
			if (filterStr && filterStr.trim() !== "") {
			    const targetValue = filterStr.split("_")[0]; // เช่น "2026-04-15"
			    
			    dataset.data = dataset.data.filter(row => {
			        return Object.entries(row).some(([key, val]) => {
			            if (val === null || val === undefined) return false;
			
			            // ถ้าเป็นคอลัมน์วันที่ หรือค่าดูเหมือนเป็น Timestamp (ตัวเลข 10 หลักขึ้นไป)
			            if (key === "วันที่ (Trend Axis)" || (!isNaN(val) && String(val).length >= 10)) {
			                // แปลงค่าใน row เป็น yyyy-mm-dd ก่อนนำไปเทียบ
			                const formattedRowDate = formatTrendDate(val, 'yyyy-mm-dd');
			                return formattedRowDate.includes(targetValue);
			            }
			
			            // คอลัมน์ทั่วไปก็ค้นหาแบบ String ปกติ
			            return String(val).includes(targetValue);
			        });
			    });
			}

    // ==========================================
    // 🌟 2. เครื่องจักร Pivot Table (ยุบรวมข้อมูลก่อน Sync)
    // ==========================================
    if (globalTableView === "pivot" && dataset.headers.includes("วันที่ (Trend Axis)")) {
        const pivotCol = "วันที่ (Trend Axis)";
        const valCol = dataset.headers.find(h => String(h).includes("Summary_Col_")) || dataset.headers[dataset.headers.length - 1];
        const rowIdentifierCols = dataset.headers.filter(h => h !== pivotCol && h !== valCol);

        let dynamicHeaders = [...new Set(dataset.data.map(row => formatTrendDate(row[pivotCol], globalDateFormat)))].filter(Boolean).sort();
        let pivotedDataMap = {};

        dataset.data.forEach(row => {
            let rowKey = row['id'] + '_' + rowIdentifierCols.map(col => row[col]).join('_');

            if (!pivotedDataMap[rowKey]) {
                pivotedDataMap[rowKey] = { id: row['id'] };
                rowIdentifierCols.forEach(col => pivotedDataMap[rowKey][col] = row[col]);
                dynamicHeaders.forEach(h => pivotedDataMap[rowKey][h] = []);
            }

            let rawVal = row[valCol];
            if (typeof rawVal === 'string') rawVal = rawVal.replace(/<[^>]*>?/gm, ''); 
            
            let formattedDate = formatTrendDate(row[pivotCol], globalDateFormat);
            let val = parseFloat(rawVal) || 0;
            
            if (formattedDate) {
                pivotedDataMap[rowKey][formattedDate].push(val); 
            }
        });

        Object.keys(pivotedDataMap).forEach(rowKey => {
            dynamicHeaders.forEach(h => {
                let arr = pivotedDataMap[rowKey][h];
                if (!arr || arr.length === 0) {
                    pivotedDataMap[rowKey][h] = 0; 
                } else {
                    if (globalPivotAgg === "SUM") pivotedDataMap[rowKey][h] = arr.reduce((a, b) => a + b, 0);
                    else if (globalPivotAgg === "MAX") pivotedDataMap[rowKey][h] = Math.max(...arr);
                    else if (globalPivotAgg === "MIN") pivotedDataMap[rowKey][h] = Math.min(...arr);
                    else if (globalPivotAgg === "AVG") pivotedDataMap[rowKey][h] = Number((arr.reduce((a, b) => a + b, 0) / arr.length).toFixed(2));
                    else if (globalPivotAgg === "MEDIAN") {
                        arr.sort((a, b) => a - b);
                        const mid = Math.floor(arr.length / 2);
                        pivotedDataMap[rowKey][h] = arr.length % 2 !== 0 ? arr[mid] : (arr[mid - 1] + arr[mid]) / 2;
                    } else if (globalPivotAgg === "MODE") {
                        const modeMap = {};
                        let maxEl = arr[0], maxCount = 1;
                        arr.forEach(el => {
                            modeMap[el] = (modeMap[el] || 0) + 1;
                            if (modeMap[el] > maxCount) { maxEl = el; maxCount = modeMap[el]; }
                        });
                        pivotedDataMap[rowKey][h] = maxEl;
                    }
                }
            });
        });

        dataset.headers = [...rowIdentifierCols, ...dynamicHeaders];
        dataset.data = Object.values(pivotedDataMap);
        globalDateFormat = "raw"; 
    }

    // ==========================================
    // 🌟 3. สับรางรถไฟ: ถ้าเปิดโหมด BI ให้โยนออกไปจัดการแบบมหาศาล!
    // ==========================================
    if (activeBIMode) {
        let fakeSql = `INSERT INTO \`${dataset.tables || 'SyncBackData'}\` (\`${dataset.headers.join('`, `')}\`) VALUES \n`;
        
        const sqlRows = dataset.data.map(row => {
            const values = dataset.headers.map(h => {
                let val = row[h];
                if (val === null || val === undefined) val = "";
                
                // ดักแปลง Format วันที่
                if (h === "วันที่ (Trend Axis)" && globalDateFormat !== "raw") {
                    val = formatTrendDate(val, globalDateFormat);
                }

                // ล้างแท็ก HTML ออกให้หมด
                if (typeof val === 'string') {
                    val = val.replace(/<[^>]*>?/gm, '');
                }

                return typeof formatSqlValue === "function" ? formatSqlValue(val) : `'${val}'`;
            });
            return `(${values.join(', ')})`;
        });

        fakeSql += sqlRows.join(',\n') + ";";

        if (typeof renderSqlPreview === "function") {
            renderSqlPreview(fakeSql, dataset.tables || 'SyncBackData');
            console.log("🚀 [SyncBack - BI Mode] ยิงข้อมูลเตรียม Export สำเร็จ!");
        }
        
        // จบการทำงานตรงนี้เลย ไม่ต้องไปวาดตาราง Checkbox ด้านล่าง
        return; 
    }

    // ==========================================
    // 🌟 4. โหมดปกติ: วาดตาราง HTML พร้อม Checkbox เพื่อรอ Sync Back
    // ==========================================
    
    // อัปเดตยอดรวม
    const totalcallElem = document.getElementById("totalcall");
    if(totalcallElem) totalcallElem.innerText = dataset.data.length;




    let html = '<table class="my-tables">';

    // --- ส่วนหัวตาราง ---
    html += '<thead><tr>';
    html += '<th style="text-align:center; width: 40px;">';
    html += '  <input type="checkbox" id="chkSelectAllSync" onchange="toggleAllSyncBack(this)">';
    html += '</th>';

    dataset.headers.forEach(headerName => {
        html += `<th>${headerName}</th>`;
    });
    html += '<th>Reference_id</th></tr></thead>';

    // --- ส่วนเนื้อหา ---
    html += '<tbody>';
    dataset.data.forEach(row => {
        let refValue = row['id'];
        if (refValue === null || refValue === undefined) refValue = "-";

        html += `<tr data-id="${refValue}">`;

        // Checkbox ดั้งเดิม
        html += `<td style="text-align:center;">`;
        html += `  <input type="checkbox" class="chkSyncItem" value="${refValue}" onchange="updateSyncBackOutput()">`;
        html += `</td>`;

        dataset.headers.forEach(headerName => {
            let value = row[headerName];
            if (value === null || value === undefined) value = "";
            
            // 🚀 โยนเข้าเครื่องแปลงวันที่
            else if (headerName === "วันที่ (Trend Axis)" && globalDateFormat !== "raw") {
                value = formatTrendDate(value, globalDateFormat);
            }

            html += `<td>${value}</td>`;
        });

        html += `<td style="color: gray;">${refValue}</td>`;
        html += '</tr>';
    });
    html += '</tbody></table>';

    // แปะลงหน้าเว็บ
    const gridSyncBackElem = document.getElementById("gridSyncBack");
    if (gridSyncBackElem) gridSyncBackElem.innerHTML = html;

    // ====================================================
    // 🌟 5. การตั้งค่าเริ่มต้น 60 แถว (Auto Select)
    // ====================================================
    let defaultAmount = dataset.data.length; 
    const inputSelect = document.getElementById("totalselect_input");
    if(inputSelect) {
        inputSelect.value = defaultAmount;
    }

    if (typeof autoSelectRows === "function") {
        autoSelectRows(defaultAmount);
    } else {
        console.warn("ไม่พบฟังก์ชัน autoSelectRows");
    }
    
     
}






// ==========================================
// ฟังก์ชันคำนวณและดึงข้อมูลเต็ม (Full Object)
// ==========================================
function updateSyncBackOutput() {
    
    // 1. หาว่า Checkbox ตัวไหนถูกติ๊กบ้าง
    const allChecked = document.querySelectorAll('.chkSyncItem:checked');
    
    // 2. เก็บ ID ของตัวที่ติ๊กใส่ Array ไว้
    const selectedIds = Array.from(allChecked).map(cb => String(cb.value));


// 2. จัดการเรื่อง jsonData (Check & Parse)
    let rawData = window.jsonData; // เรียกผ่าน window เพื่อความชัวร์เรื่อง Scope
    
    // ถ้า jsonData เป็น String ให้แปลงเป็น Object ก่อน
    if (typeof rawData === "string") {
        try {
            rawData = JSON.parse(rawData);
        } catch (e) {
            console.error("❌ ไม่สามารถ Parse jsonData ได้:", e);
            return;
        }
    }

    // 3. ตรวจสอบโครงสร้างข้อมูล [ { data: [...] } ]
    if (!rawData || !rawData[0] || !rawData[0].data) {
        console.warn("⚠️ ไม่พบข้อมูลใน jsonData หรือโครงสร้างไม่ถูกต้อง");
        return;
    }

    const fullSourceData = rawData[0].data;
// 4. ค้นหาข้อมูลเต็ม (Full Object) จาก ID ที่เลือก
    const selectedFullObjects = fullSourceData.filter(item => 
        selectedIds.includes(String(item.id))
    );




    // 5. อัปเดตตัวเลขจำนวนที่เลือก (ใส่ if ดักไว้ กัน error)
    
    // 5.1 อัปเดต Label (ถ้ามี)
    const lblTotal = document.getElementById("totalselect");
    if (lblTotal) {
        lblTotal.innerText = selectedFullObjects.length;
    }

    // 5.2 อัปเดต Input (ถ้ามี)
    const inpTotal = document.getElementById("totalselect_input");
    if (inpTotal) {
        inpTotal.value = selectedFullObjects.length;
    }


    // 5.3 อัปเดต Hidden Field สำหรับ Save (สำคัญมาก!)
    const inpSegId = document.getElementById("segmentation_member_id");
    if (inpSegId) {
        inpSegId.value = selectedIds.join(',');
    } else {
        console.warn("หา id='segmentation_member_id' ไม่เจอ! อาจทำให้ Save ไม่ได้");
    }
    

    // 6. ยัด Object เต็มๆ ลงไปใน Textarea
    const inpJson = document.getElementById("SyncBackjsonOutput");
    if (inpJson) {
        inpJson.value = JSON.stringify(selectedFullObjects, null, 2); 
    }

    // (Optional) เช็คสถานะ Select All
    const allItems = document.querySelectorAll('.chkSyncItem');
    const chkSelectAll = document.getElementById("chkSelectAllSync");
    if(chkSelectAll){
         chkSelectAll.checked = (allItems.length > 0 && allItems.length === allChecked.length);
    }
}


async function SyncBackSend(url, form, link) {
    // ใส่ await เพื่อสั่งให้หยุดรอจนกว่า postData จะทำงานเสร็จ
    await postData(url, form, link); 
    // เมื่อเสร็จแล้วค่อยทำบรรทัดนี้ต่อ
    
}
    
async function SyncBackSend2(url, form, link,process_id,segmentation_id) {
    // ใส่ await เพื่อสั่งให้หยุดรอจนกว่า postData จะทำงานเสร็จ
    await postData(url, form, link); 
    getData('/editstaticsnapshot-filemanager/'+segmentation_id+'/'+process_id, 'mainbody3');
    
}    
    


// ==========================================
// ฟังก์ชันใหม่ 2: ควบคุมปุ่ม Select All
// ==========================================
function toggleAllSyncBack(masterCheckbox) {
    // ดูว่าปุ่มแม่ (Select All) ติ๊กอยู่ไหม
    const isChecked = masterCheckbox.checked;

    // ไปสั่งลูกน้องทุกตัวให้มีสถานะตามแม่
    const checkboxes = document.querySelectorAll('.chkSyncItem');
    checkboxes.forEach(cb => {
        cb.checked = isChecked;
    });

    // สั่งคำนวณค่าใหม่
    updateSyncBackOutput();
}
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
function Syncshow() {
	
	
	  console.log("Syncshow...");
	 
}

function loadSyncshow() {
	
    var rawValue = document.getElementById("Syncjsoninput").textContent.trim();
 	var process_id = document.getElementById("process_id").value;
 
 

    var dataset = [];
    try {
        dataset = JSON.parse(rawValue);
    } catch (e) {
        console.error("JSON Error:", e);
        return;
    }

    document.getElementById("Syncprocess").value = "1";

    if (!Array.isArray(dataset) || dataset.length === 0) {
        return;
    }

    // --- จุดที่เพิ่ม: ระบุชื่อคอลัมน์ที่ไม่ต้องการแสดง ---
    // ใส่ชื่อ key ที่ตรงกับใน JSON เป๊ะๆ ลงไปใน array นี้ครับ
    const excludeColumns = ["id", "id_refer", "Reference_id"]; 

    // สร้าง Headers โดยกรองตัวที่ไม่เอาออก
    const headers = Object.keys(dataset[0]).filter(key => !excludeColumns.includes(key));

    // --- เริ่มสร้าง HTML Table ---
    let html = '<table class="my-table">';
    html += '<thead><tr>';


    headers.forEach(headerName => {
        html += `<th>${headerName}</th>`;
    });
    
    // ตรงนี้ผมคอมเมนต์ออกเพราะ Reference_id มักจะซ้ำกับ id ถ้าอยากโชว์ก็เอาคอมเมนต์ออกครับ
    // html += '<th>Reference_id</th>'; 
    
    html += '</tr></thead>';

    html += '<tbody>';
    dataset.forEach(row => {
        // ยังดึง id มาใช้เป็น value ของ checkbox ได้ปกติ แม้จะซ่อนไม่ให้โชว์ในตาราง
        let refValue = row['id']; 
        if (refValue === null || refValue === undefined) refValue = "-";

        html += `<tr data-id="${refValue}"  ondblclick="
        openAppAndLoadData('${refValue}', '${process_id}');
        getData('/profileapp-filemanager/${refValue}/${process_id}?from=0', 'microsofts${process_id}')" style="cursor:copy;">`;

        // วนลูปเฉพาะ headers ที่ผ่านการกรองมาแล้ว
        headers.forEach(headerName => {
            let value = row[headerName];
            if (value === null || value === undefined) value = "";
            html += `<td>${value}</td>`;
        });
		
        // ส่วน column ท้ายสุดที่โชว์ id ถ้าไม่เอาด้วย ก็ลบบรรทัดนี้ทิ้งได้เลยครับ
        // html += `<td style="color: gray;">${refValue}</td>`;
        
        html += '</tr>';
    });
    html += '</tbody></table>';

    document.getElementById("Synctable").innerHTML = html;
}





// ฟังก์ชันอัปเดตค่าเมื่อ User เปลี่ยน Dropdown
function updateToModelSetting(selectedValue) {
    if (activeConfigObj && activeStorageKey) {
        // อัปเดตค่าใน Object
        activeConfigObj.tomodel = selectedValue;
        
        // เซฟทับกลับลง LocalStorage (ต้องแปลงกลับเป็น String)
        localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
        
        console.log(`อัปเดต tomodel ของไฟล์ ${activeConfigObj.fileName} เป็น: ${selectedValue}`);
        
        LoadSetupData();
        
    } else {
        console.error("ไม่สามารถอัปเดตได้: ข้อมูล Config หรือ Storage Key สูญหาย");
    }
}

// ฟังก์ชันสำหรับเรียกค่าเดิมมาตั้งใน Dropdown ตอนเปิดหน้าจอ
function loadToModelSetting() {
    if (activeConfigObj && activeConfigObj.tomodel) {
        const selectElement = document.getElementById('tomodel_select'); // <- อย่าลืมใส่ id="tomodel_select" ที่ HTML ของคุณนะครับ
        if (selectElement) {
            selectElement.value = activeConfigObj.tomodel;
        }
    }
    
    
}




var system_column ="";


function getModel(url) {


            var xhr = new XMLHttpRequest();
            xhr.open('GET', url, true);
            xhr.timeout = 60000; 
            xhr.onload = function () {
                if (xhr.status >= 200 && xhr.status < 300) {
                    var responseURL = xhr.responseURL;
                    if (responseURL && responseURL.indexOf('login') !== -1) {
                    } else {
                        
                    	system_column = xhr.responseText;
                    }
                } else {
                    alert(`HTTP error! Status: ${xhr.status}`);
                }
              
            };

            xhr.onerror = function () {
                alert('Network error occurred');
                element.style.display = 'none';
            };

         xhr.ontimeout = function () {
          alert('คำขอใช้เวลานานเกินไป กรุณาลองใหม่อีกครั้ง');
              if (element) {
                  element.style.display = 'none';
              }
          };

            xhr.send();
        

}


function LoadSetupData() {
	
		if(activeConfigObj.tomodel != "-" ){
			getModel("/loadmodel-filemanager/"+activeConfigObj.tomodel);
		}
}

function buildMappingUI() {
    if(activeConfigObj.tomodel == "-" && system_column != ""){
         LoadSetupData();
    }

    // ==========================================
    // 🛡️ ระบบ ERP แทรกแซง 1: แบ็คอัพคอลัมน์เสมือน (หนีการโดนล้าง)
    // ==========================================
    let virtualBackup = [];
    if (activeConfigObj && activeConfigObj.mappedColumns) {
        // แอบคัดลอกคอลัมน์เสมือน (index เป็น -1) เก็บใส่กระเป๋าไว้ก่อน
        virtualBackup = activeConfigObj.mappedColumns.filter(c => c.index === -1);
    }

    // 🌟 พระเอก(จอมทำลายล้าง): ซิงค์คอลัมน์เก่า-ใหม่ (ตอนนี้มันจะลบคอลัมน์เสมือนเราทิ้ง)
    syncConfigWithCurrentColumns();

    // ==========================================
    // 🛡️ ระบบ ERP แทรกแซง 2: กู้คืนคอลัมน์เสมือนกลับเข้าสู่ระบบ
    // ==========================================
    if (activeConfigObj && activeConfigObj.mappedColumns) {
        virtualBackup.forEach(vm => {
            // ถ้าโดนลบไปแล้ว ให้เอาจากแบ็คอัพยัดกลับเข้าไปใหม่!
            if (!activeConfigObj.mappedColumns.find(c => c.source === vm.source)) {
                activeConfigObj.mappedColumns.push(vm);
            }
        });
        // บันทึกทับลง LocalStorage อีกรอบเพื่อความชัวร์
        if(activeStorageKey) localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    }

    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

    // ==========================================
    // 🌟 เตรียมตะกร้าคอลัมน์รวม (ตัวจริง + ตัวเสมือน) เพื่อวาด UI
    // ==========================================
    let displayColumns = [];

    // 1. ดึงคอลัมน์จาก mappedColumns มาใส่ก่อน
    activeConfigObj.mappedColumns.forEach(col => {
        let isVirt = activeConfigObj.mergeRules && activeConfigObj.mergeRules.some(r => r.targetName === col.source);
        displayColumns.push({ ...col, isVirtual: isVirt });
    });

    // 2. ดึงคอลัมน์เสมือนที่อาจจะยังไม่เคยถูก Mapping มาต่อท้าย
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!displayColumns.find(c => c.source === rule.targetName)) {
                displayColumns.push({ index: -1, source: rule.targetName, target: "", isVirtual: true });
            }
        });
    }
    // ==========================================

    const allTargetOptions = system_column.split(',').map(c => c.trim());
    const usedTargets = displayColumns.map(c => c.target).filter(t => t !== "");

    let html = '';

    displayColumns.forEach((col, index) => {
        let isSkipped = (col.target === "");
        
        let sortedOptions = allTargetOptions.map(opt => ({
            name: opt,
            score: getStringSimilarity(col.source, opt)
        }));

        sortedOptions.sort((a, b) => b.score - a.score);

        let sourceBadge = col.isVirtual 
            ? `<span style="background-color: #e0f7fa; color: #00838f; font-size: 11px; padding: 4px 8px; border-radius: 4px; margin-right: 10px;">🧩 Merged</span>`
            : `<span style="background-color: #e6f0ed; color: #1c5e39; font-size: 11px; padding: 4px 8px; border-radius: 4px; margin-right: 10px;">📄 ไฟล์ SQL</span>`;

        html += `
        <div class="form-row" style="display: flex; align-items: center; margin-bottom: 12px; border-bottom: 1px solid #d4d4d4; padding-bottom: 10px;">
            <span class="label" style="width: 50%; font-weight: bold; font-size: 14px;">
                ${sourceBadge} ${col.source}
            </span>

            <select onchange="updateColumnMapping('${col.source}', this.value, this)"
                style="width: 50%; padding: 7px; border: 1px solid ${isSkipped ? '#ccc' : '#217346'}; border-radius: 4px; background-color: ${isSkipped ? '#f5f5f5' : '#eefbf4'};">
                
                <option value="" style="color: #888;">-- ไม่นำเข้า (Skip) --</option>`;
        
        sortedOptions.forEach(optObj => {
            const opt = optObj.name;
            const isAvailable = !usedTargets.includes(opt) || col.target === opt;
            
            if (isAvailable) {
                let isSelected = (col.target === opt) ? 'selected' : '';
                let label = optObj.score > 0.4 && col.target !== opt ? `✨ ${opt}` : opt;
                
                html += `<option value="${opt}" ${isSelected}>${label}</option>`;
            }
        });

        html += `</select></div>`;
    });

    const container = document.getElementById('Form-TabSet-report-2');
    if (container) container.innerHTML = html;
}


function getStringSimilarity(s1, s2) {
    let target = s1.toLowerCase();
    let source = s2.toLowerCase();
    if (target === source) return 1.0; // เหมือนกันเป๊ะ
    
    // คำนวณเบื้องต้น: ดูว่ามีคำที่เหมือนกันอยู่ในประโยคไหม
    if (target.includes(source) || source.includes(target)) return 0.8;

    // หาจำนวนตัวอักษรที่ตรงกัน (Intersection)
    let bigger = target.length > source.length ? target : source;
    let smaller = target.length > source.length ? source : target;
    let matches = 0;
    
    for (let char of smaller) {
        if (bigger.includes(char)) {
            matches++;
            bigger = bigger.replace(char, ''); // ลบตัวที่ Match แล้วออกเพื่อไม่ให้ซ้ำ
        }
    }
    return matches / Math.max(s1.length, s2.length);
}


// เพิ่มพารามิเตอร์ selectElement เข้ามา
function updateColumnMapping(sourceCol, selectedTarget, selectElement) {
    if (!activeConfigObj || !activeStorageKey) return;
        
    let colIndex = activeConfigObj.mappedColumns.findIndex(c => c.source === sourceCol);
    
    // 🌟 ระบบ ERP แทรกแซง: แยกกรณีคอลัมน์เก่า และ คอลัมน์ใหม่ (Merge)
    if (colIndex !== -1) {
        // กรณีเจอคอลัมน์ปกติ: อัปเดตข้อมูลเป้าหมาย
        activeConfigObj.mappedColumns[colIndex].target = selectedTarget;
    } else {
        // 🌟 กรณีหาไม่เจอ: แปลว่าเป็น "คอลัมน์เสมือน (Merge/Split)" 
        // ให้จับยัดเข้าไปในประวัติ mappedColumns ใหม่เลย
        activeConfigObj.mappedColumns.push({
            index: -1, // กำหนด index เป็น -1 เพื่อบอกว่าเป็นคอลัมน์ที่สร้างขึ้นมาใหม่
            source: sourceCol,
            target: selectedTarget
        });
    }

    // 1. เซฟการเปลี่ยนแปลงทั้งหมดลง LocalStorage
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    
    // รีเฟรชหน้าจอ (เพื่อให้ Dropdown อื่นๆ รู้ว่าคอลัมน์นี้ถูกจับคู่ไปแล้ว จะได้ไม่ให้เลือกซ้ำ)
    buildMappingUI(); 

    // 2. เปลี่ยนสี Dropdown ทันทีที่กด (ใส่ if ดักเผื่อ DOM หลุดจากการ re-render)
    if (selectElement) {
        if (selectedTarget === "") {
            // กรณีเลือกเป็น ไม่นำเข้า (Skip) -> กลับเป็นสีเทา
            selectElement.style.backgroundColor = "#f5f5f5";
            selectElement.style.borderColor = "#ccc";
            selectElement.style.color = "#888";
            selectElement.style.fontWeight = "normal";
        } else {
            // กรณีจับคู่แล้ว -> เปลี่ยนเป็นสีเขียว
            selectElement.style.backgroundColor = "#eefbf4";
            selectElement.style.borderColor = "#217346";
            selectElement.style.color = "#000";
            selectElement.style.fontWeight = "bold";
        }
    }
}



function buildIgnoreColumnsUI() {
    // เช็กว่ามีข้อมูลคอลัมน์ต้นทางหรือไม่
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

// 🌟 พระเอกของเรา: ซิงค์คอลัมน์เก่า-ใหม่ให้ตรงกันก่อนวาด UI 🌟
    syncConfigWithCurrentColumns();

    // เตรียม Array สำหรับเก็บค่าคอลัมน์ที่ถูกตัดทิ้ง (ถ้ายังไม่มีให้สร้างเป็น Array ว่าง)
    if (!activeConfigObj.ignoredColumns) {
        activeConfigObj.ignoredColumns = [];
    }

    // สร้างส่วนหัว ใช้ธีมสีแดง/ส้ม เพื่อสื่อถึงการ "ตัดทิ้ง/ไม่เอา"
    let html = '<h4 style="margin-bottom: 10px; color: #d9534f; border-bottom: 2px solid #d9534f; padding-bottom: 8px; font-weight: bold;">🗑️ เลือกคอลัมน์ต้นทางที่ต้องการตัดทิ้ง (Ignore Columns)</h4>';
    html += '<p style="font-size: 13px; color: #666; margin-bottom: 15px;">ติ๊กเลือกคอลัมน์จากไฟล์ SQL ที่คุณไม่ต้องการนำเข้า หรือคอลัมน์ที่ข้อมูลต้นทางผิดพลาด</p>';

    // สร้างกล่องที่มี Scrollbar เผื่อว่าคอลัมน์มีเยอะ จะได้ไม่ดันหน้าเว็บยาวเกินไป
    html += `<div style="border: 1px solid #d4d4d4; border-radius: 4px; max-height: 250px; overflow-y: auto; padding: 10px; background-color: #fafafa;">`;

    // วนลูปเอาชื่อคอลัมน์ต้นทางมาสร้างเป็น Checkbox
    activeConfigObj.mappedColumns.forEach((col) => {
        // เช็กว่าคอลัมน์นี้เคยถูกติ๊กตัดทิ้งไว้ใน LocalStorage หรือยัง
        let isChecked = activeConfigObj.ignoredColumns.includes(col.source) ? 'checked' : '';
        let textStyle = isChecked ? 'color: #d9534f; text-decoration: line-through;' : 'color: #333;';

        html += `
        <label style="display: flex; align-items: center; margin-bottom: 8px; cursor: pointer; padding: 8px; border-bottom: 1px dashed #eee; transition: background 0.2s;" onmouseover="this.style.backgroundColor='#f0f0f0'" onmouseout="this.style.backgroundColor='transparent'">
            <input type="checkbox" value="${col.source}" ${isChecked} 
                   style="margin-right: 12px; width: 16px; height: 16px; cursor: pointer; accent-color: #d9534f;" 
                   onchange="updateIgnoredColumns(this)">
            <span id="label_ignore_${col.source}" style="font-size: 14px; ${textStyle}">
                ${col.source}
            </span>
        </label>`;
    });

    html += `</div>`;

    // นำไปแสดงใน div ที่เตรียมไว้
    const container = document.getElementById('Form-TabSet-report-3');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block'; 
    }
}

function updateIgnoredColumns(checkboxElem) {
    if (!activeConfigObj || !activeStorageKey) return;

    if (!activeConfigObj.ignoredColumns) {
        activeConfigObj.ignoredColumns = [];
    }

    const colName = checkboxElem.value;
    const labelSpan = document.getElementById(`label_ignore_${colName}`);

    if (checkboxElem.checked) {
        // ถ้าติ๊กถูก -> เอาชื่อคอลัมน์ยัดใส่ Array (ถ้ายังไม่มี)
        if (!activeConfigObj.ignoredColumns.includes(colName)) {
            activeConfigObj.ignoredColumns.push(colName);
        }
        // ขีดฆ่าตัวหนังสือให้รู้ว่าโดนตัดทิ้งแล้ว
        if(labelSpan) {
            labelSpan.style.color = '#d9534f';
            labelSpan.style.textDecoration = 'line-through';
        }
    } else {
        // ถ้าเอาติ๊กออก -> ลบชื่อคอลัมน์ออกจาก Array
        activeConfigObj.ignoredColumns = activeConfigObj.ignoredColumns.filter(c => c !== colName);
        // เอาขีดฆ่าออก
        if(labelSpan) {
            labelSpan.style.color = '#333';
            labelSpan.style.textDecoration = 'none';
        }
    }

    // เซฟทับลง LocalStorage
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    console.log("อัปเดตคอลัมน์ที่ตัดทิ้ง:", activeConfigObj.ignoredColumns);
}


function buildDuplicateScanUI() {
    // เช็กว่ามีข้อมูลคอลัมน์ต้นทางหรือไม่
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

    // เตรียม Array สำหรับเก็บค่าคอลัมน์ที่จะใช้เช็กซ้ำ (ถ้ายังไม่มีให้สร้างเป็น Array ว่าง)
    if (!activeConfigObj.scanDuplicateColumns) {
        activeConfigObj.scanDuplicateColumns = [];
    }

    // สร้างส่วนหัว ใช้ธีมสีน้ำเงิน เพื่อสื่อถึงการ "ค้นหา/ตรวจสอบ"
    let html = '<h4 style="margin-bottom: 10px; color: #31708f; border-bottom: 2px solid #31708f; padding-bottom: 8px; font-weight: bold;">🔍 เลือกคอลัมน์สำหรับตรวจสอบข้อมูลซ้ำ (Duplicate Scan)</h4>';
    html += '<p style="font-size: 13px; color: #666; margin-bottom: 15px;">ติ๊กเลือกคอลัมน์ที่ต้องการใช้สแกนหาข้อมูลที่อาจซ้ำกัน (เช่น ชื่อ, เบอร์โทร, เลขผู้เสียภาษี) ระบบจะแจ้งเตือนหากเจอข้อมูลซ้ำในคอลัมน์ใดคอลัมน์หนึ่ง (เงื่อนไขแบบ OR)</p>';

    // สร้างกล่องที่มี Scrollbar
    html += `<div style="border: 1px solid #d4d4d4; border-radius: 4px; max-height: 250px; overflow-y: auto; padding: 10px; background-color: #f4f8fa;">`;

    // วนลูปเอาชื่อคอลัมน์ต้นทางมาสร้างเป็น Checkbox
    activeConfigObj.mappedColumns.forEach((col) => {
        // ข้ามคอลัมน์ที่ถูกตั้งค่าให้ "ตัดทิ้ง (Ignore)" ไปแล้ว จะได้ไม่เอามาแสดงให้สับสน
        if (activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(col.source)) {
            return; // ข้ามบรรทัดนี้ไปเลย
        }

        // เช็กว่าคอลัมน์นี้เคยถูกติ๊กให้สแกนซ้ำไว้ใน LocalStorage หรือยัง
        let isChecked = activeConfigObj.scanDuplicateColumns.includes(col.source) ? 'checked' : '';
        // ถ้าเลือกไว้ ให้ทำตัวหนาและเปลี่ยนสีตัวหนังสือเป็นสีน้ำเงิน
        let textStyle = isChecked ? 'color: #31708f; font-weight: bold;' : 'color: #333;';

        html += `
        <label style="display: flex; align-items: center; margin-bottom: 8px; cursor: pointer; padding: 8px; border-bottom: 1px dashed #e1e8ed; transition: background 0.2s;" onmouseover="this.style.backgroundColor='#e8f1f5'" onmouseout="this.style.backgroundColor='transparent'">
            <input type="checkbox" value="${col.source}" ${isChecked} 
                   style="margin-right: 12px; width: 16px; height: 16px; cursor: pointer; accent-color: #31708f;" 
                   onchange="updateDuplicateScanColumns(this)">
            <span id="label_dup_${col.source}" style="font-size: 14px; ${textStyle}">
                ${col.source}
            </span>
        </label>`;
    });

    html += `</div>`;

    // นำไปแสดงใน div Form-trans-report-1
    const container = document.getElementById('Form-TabSet-trans-1');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block'; 
    }
}


function updateDuplicateScanColumns(checkboxElem) {
    if (!activeConfigObj || !activeStorageKey) return;

    if (!activeConfigObj.scanDuplicateColumns) {
        activeConfigObj.scanDuplicateColumns = [];
    }

    const colName = checkboxElem.value;
    const labelSpan = document.getElementById(`label_dup_${colName}`); // อ้างอิง id ให้ตรงกับที่สร้างไว้

    if (checkboxElem.checked) {
        // ถ้าติ๊กถูก -> เอาชื่อคอลัมน์ยัดใส่ Array
        if (!activeConfigObj.scanDuplicateColumns.includes(colName)) {
            activeConfigObj.scanDuplicateColumns.push(colName);
        }
        // เปลี่ยนสีตัวหนังสือเป็นสีน้ำเงินและตัวหนา
        if(labelSpan) {
            labelSpan.style.color = '#31708f';
            labelSpan.style.fontWeight = 'bold';
        }
    } else {
        // ถ้าเอาติ๊กออก -> ลบชื่อคอลัมน์ออกจาก Array
        activeConfigObj.scanDuplicateColumns = activeConfigObj.scanDuplicateColumns.filter(c => c !== colName);
        // กลับเป็นสีดำปกติ
        if(labelSpan) {
            labelSpan.style.color = '#333';
            labelSpan.style.fontWeight = 'normal';
        }
    }

    // เซฟทับลง LocalStorage
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    console.log("อัปเดตคอลัมน์สแกนข้อมูลซ้ำ:", activeConfigObj.scanDuplicateColumns);
}











function readSqlFile(input) {
    if (!input.files || !input.files[0]) return;
    const file = input.files[0];
    importPendingFile = file;
    // 1. กำหนดขนาดไฟล์ที่จะตัดมาอ่าน (5 MB = 5 * 1024 * 1024 bytes)
    const CHUNK_SIZE = 20 * 1024 * 1024; 
    
    // 2. ใช้คำสั่ง slice เพื่อเฉือนไฟล์เอาแค่ส่วนหัว
    // ถ้าไฟล์เล็กกว่า 5MB มันก็จะอ่านทั้งไฟล์ปกติ แต่ถ้าใหญ่กว่า มันจะหยุดแค่นี้
    const blob = file.slice(0, CHUNK_SIZE); 

    const reader = new FileReader();

    // แจ้งเตือนผู้ใช้กรณีไฟล์ใหญ่
    let loadingMsg = '<p style="color:#217346;">กำลังประมวลผลข้อมูล...</p>';
    if (file.size > CHUNK_SIZE) {
        loadingMsg = `<p style="color:#e67e22;">
            <i>* ตรวจพบไฟล์ขนาดใหญ่ (${(file.size / (1024*1024)).toFixed(2)} MB)<br>
            ระบบทำการดึงข้อมูลเฉพาะส่วนต้นมาเพื่อจับคู่คอลัมน์ (ป้องกันหน้าเว็บค้าง)</i>
        </p>`;
    }
    document.getElementById('table-container').innerHTML = loadingMsg;

    reader.onload = function(e) {
        // ส่งข้อมูลส่วนหัวที่ตัดมาแล้ว และชื่อไฟล์ ไปให้ฟังก์ชันสร้างตาราง
        renderSqlPreview(e.target.result, file.name); 
    };

    // 3. อ่านเฉพาะก้อน Blob ที่เราหั่นมา
    reader.readAsText(blob);
    
    input.value = ''; // Reset ค่า input
        NewCheese();
    
}

function parseSqlRowToColumns(rowStr) {
    // Regex นี้จะสแกนหาข้อความที่อยู่ใน '...' ก่อน เพื่อข้ามคอมม่าที่อยู่ข้างใน
    const colRegex = /'(''|[^'])*'|[^,]+/g;
    const matches = rowStr.match(colRegex) || [];
    
    return matches.map(val => {
        let clean = val.trim();
        if (clean.toUpperCase() === "NULL") return "NULL";
        
        // ถอดเครื่องหมาย ' ' ออก และเปลี่ยน '' กลับเป็น ' 
        if (clean.startsWith("'") && clean.endsWith("'")) {
            clean = clean.substring(1, clean.length - 1).replace(/''/g, "'");
        }
        return clean;
    });
}

// 1. ฟังก์ชันตัดแถว (อัปเดต: ดักจับ Backslash ได้ 100%)
function extractSqlRows(valuesStr) {
    let rows = [];
    let inString = false;
    let depth = 0;
    let currentRow = "";
    
    for (let i = 0; i < valuesStr.length; i++) {
        let char = valuesStr[i];
        
        // 🌟 ดักจับ Backslash (\) ถ้าเจอให้กวาดตัวถัดไปติดมาด้วยเลย (แก้ปัญหา \' ตัดแถวผิด)
        if (char === '\\' && inString && i + 1 < valuesStr.length) {
            currentRow += char + valuesStr[i+1];
            i++; 
            continue;
        }

        if (char === "'" && !inString) {
            inString = true;
            currentRow += char;
        } else if (char === "'" && inString) {
            if (i + 1 < valuesStr.length && valuesStr[i+1] === "'") {
                currentRow += "''"; 
                i++; 
            } else {
                inString = false;
                currentRow += char;
            }
        } else if (!inString && char === '(') {
            if (depth > 0) currentRow += char; 
            depth++;
        } else if (!inString && char === ')') {
            depth--;
            if (depth === 0) {
                if (currentRow.trim() !== "") rows.push(currentRow.trim());
                currentRow = "";
            } else {
                currentRow += char;
            }
        } else {
            if (depth > 0) currentRow += char;
        }
    }
    return rows;
}

// 2. ฟังก์ชันแยกคอลัมน์ (อัปเดต: ทำความสะอาด Quote ได้เนียนขึ้น)
function parseSqlRowToColumns(rowStr) {
    let cols = [];
    let inString = false;
    let currentCol = "";
    
    for (let i = 0; i < rowStr.length; i++) {
        let char = rowStr[i];
        
        // 🌟 ดักจับ Backslash (\)
        if (char === '\\' && inString && i + 1 < rowStr.length) {
            currentCol += char + rowStr[i+1];
            i++;
            continue;
        }

        if (char === "'" && !inString) {
            inString = true;
            currentCol += char;
        } else if (char === "'" && inString) {
            if (i + 1 < rowStr.length && rowStr[i+1] === "'") {
                currentCol += "''";
                i++;
            } else {
                inString = false;
                currentCol += char;
            }
        } else if (!inString && char === ',') {
            cols.push(currentCol);
            currentCol = "";
        } else {
            currentCol += char;
        }
    }
    cols.push(currentCol);
    
    return cols.map(val => {
        let clean = val.trim();
        if (clean.toUpperCase() === "NULL") return "NULL";
        if (clean.startsWith("'") && clean.endsWith("'")) {
            // ถอดโควทหัวท้ายออก และแปลง \' ให้กลับเป็น ' ปกติ
            clean = clean.substring(1, clean.length - 1).replace(/''/g, "'").replace(/\\'/g, "'");
        }
        return clean;
    });
}

function renderSqlPreview(sql, fileName) {
// 1. ลบ Comment ทั้งแบบ -- และ /* ... */ ออกให้หมด
    const cleanSql = sql.replace(/--.*(?:\r\n|\r|\n)/g, " ").replace(/\/\*[\s\S]*?\*\//g, " ").trim();

    // 2. ปรับ Regex ใหม่ (หัวใจสำคัญ):
    // - [^()]+ เดิมจะหยุดทันทีเมื่อเจอวงเล็บในชื่อคอลัมน์ (เช่น (Tele Sale))
    // - เราจะเปลี่ยนมาใช้ [\s\S]+? เพื่อให้อ่านข้ามไปจนถึง ) ตัวสุดท้ายก่อนคำว่า VALUES
    const insertRegex = /INSERT\s+INTO\s+[`"']?([\w\u0E00-\u0E7F]+)[`"']?\s*\(([\s\S]+?)\)\s*VALUES/is;
    
    const match = cleanSql.match(insertRegex);

    if (!match) {
        // ถ้ายังไม่พบ ให้ลองตรวจดูว่า SQL ที่ส่งเข้ามาว่างเปล่าหรือไม่
        console.error("SQL Content Sample:", sql.substring(0, 200));
        document.getElementById('table-container').innerHTML = 
            `<p style="color:red;">❌ ไม่พบรูปแบบ INSERT INTO ในไฟล์ ${fileName}<br>
            <small>โปรดตรวจสอบว่าไฟล์มีการเข้ารหัส (Encoding) เป็น UTF-8</small></p>`;
        return;
    }
    
    
    const tableName = match[1].replace(/[`'" ]/g, '') || 'table_name';
    // 🌟 เราจะใช้ชื่อตัวแปร columns เป็นหลักเพื่อให้สอดคล้องกับ Logic ข้างล่าง
    const columns = match[2].split(',').map(c => c.replace(/[`'" ]/g, ''));
    
    // สำคัญ: อัปเดตตัวแปร Global เพื่อให้ Export และหน้าอื่นๆ ใช้งานได้
    window.globalOriginalColumns = columns; 

	// 🌟 เปลี่ยนมาใช้ลูปตัดแถว (ลาก่อนความค้าง!)
    let rawRows = [];
    const allValuesParts = sql.match(/VALUES\s*([\s\S]*?);/ig) || [];
    allValuesParts.forEach(valPart => {
        // ใช้ฟังก์ชันตัวใหม่ที่เราเขียน สกัดแถวออกมาได้เป๊ะ 100%
        let extracted = extractSqlRows(valPart);
        rawRows.push(...extracted);
    }); 

    activeStorageKey = `erp_mapper_${fileName}`;
    let savedString = localStorage.getItem(activeStorageKey);
    
    if (savedString) {
        activeConfigObj = JSON.parse(savedString);
        if(!activeConfigObj.ignoredColumns) activeConfigObj.ignoredColumns = [];
        if(!activeConfigObj.valueMappings) activeConfigObj.valueMappings = [];
        if(!activeConfigObj.smartFillRules) activeConfigObj.smartFillRules = [];
        if(!activeConfigObj.rowCleanRules) activeConfigObj.rowCleanRules = [];
		if(!activeConfigObj.splitRules) activeConfigObj.splitRules = [];
	    if(!activeConfigObj.mergeRules) activeConfigObj.mergeRules = [];
	    if(!activeConfigObj.formatRules) activeConfigObj.formatRules = [];
	    if(!activeConfigObj.mappedColumns) activeConfigObj.mappedColumns = [];
	    if(!activeConfigObj.ignoreRowRules) activeConfigObj.ignoreRowRules = [];
		
       
    } else {
        activeConfigObj = {
            fileName: fileName,
            tableName: tableName,
            lastImportDate: new Date().toISOString(),
            tomodel: "-", 
            mappedColumns: columns.map(col => ({ source: col, target: "" })),
            ignoredColumns: [],
            scanDuplicateColumns: [],
            valueMappings: [],
            smartFillRules: [],
            rowCleanRules: [] ,
	        splitRules: [] ,
	        mergeRules: [] ,
	        formatRules: [] ,
	        ignoreRowRules: [] // 🌟 เพิ่มบรรทัดนี้: เตรียม Array ว่างไว้รอรับเงื่อนไขตัดแถวทิ้ง!
        };
        localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    }

    LoadSetupData();
  
    const displayColumns = [];
    columns.forEach((colName, index) => {
        if (activeConfigObj.ignoredColumns.includes(colName)) return;
        let mapped = activeConfigObj.mappedColumns.find(c => c.source === colName);
        let targetName = (mapped && mapped.target) ? mapped.target : "";
        displayColumns.push({ index: index, source: colName, target: targetName });
    });
    
	// 🌟 1.1 เตรียมคอลัมน์เสมือน (Split) เข้าตะกร้า
    if (activeConfigObj.splitRules) {
        activeConfigObj.splitRules.forEach(rule => {
            rule.targets.forEach(targetName => {
                if (!displayColumns.find(c => c.source === targetName)) {
                    let mapped = activeConfigObj.mappedColumns.find(c => c.source === targetName);
                    let targetVal = (mapped && mapped.target) ? mapped.target : ""; 
                    displayColumns.push({ index: -1, source: targetName, target: targetVal, isVirtual: true });
                }
            });
        });
    }
	
	// 🌟 1.2 เตรียมคอลัมน์เสมือน (Merge) เข้าตะกร้า
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!displayColumns.find(c => c.source === rule.targetName)) {
                let mapped = activeConfigObj.mappedColumns.find(c => c.source === rule.targetName);
                let targetVal = (mapped && mapped.target) ? mapped.target : ""; 
                displayColumns.push({ index: -1, source: rule.targetName, target: targetVal, isVirtual: true });
            }
        });
    }

    let html = `<h4 style="color:#217346;">ตารางพรีวิว</h4>`;
    html += `<div style="max-height:500px; overflow: auto; border: 1px solid #ddd;">`;
    html += `<table style="width:100%; border-collapse: collapse; font-family: sans-serif; font-size: 13px; white-space: nowrap;">`;
    html += `<thead style="position: sticky; top: 0; z-index: 2; background-color: #eefbf4;"><tr>`;
    
    displayColumns.forEach(col => {
        let headerHtml = col.target ? `<b>${col.target}</b><br><small>${col.source}</small>` : col.source;
        html += `<th style="padding: 10px; border: 1px solid #ddd; text-align: left;">${headerHtml}</th>`;
    });
    html += `</tr></thead><tbody>`;

    window.currentPreviewPayload = []; 
    const maxProcessLimit = 120000; // 🌟 ตั้งเพดานไว้ที่ 10,000 แถว
	const totalToProcess = Math.min(rawRows.length, maxProcessLimit);
    const limit = Math.min(rawRows.length, 1000);

    for (let i = 0; i < totalToProcess; i++) {
       // let rowDataStr = rawRows[i].replace(/^\(/, '').replace(/\)$/, '');
         let rowValues = parseSqlRowToColumns(rawRows[i]);
         let processedRow = {}; 


		// 🌟 นำข้อมูลใส่ Object ชั่วคราวก่อน เพื่อเอาไว้ตรวจสอบ
		    let tempRow = {};
		    columns.forEach((colName, colIdx) => {
		        tempRow[colName] = rowValues[colIdx] || "";
		    });
		
		 


// 🚀 พระเอกออกโรง: ตรวจสอบกฎการตัดแถวทิ้ง (Exclude Rows) - อัปเกรดรองรับ ** (Wildcard) 🌟
let shouldSkipRow2 = false;
if (activeConfigObj.ignoreRowRules && activeConfigObj.ignoreRowRules.length > 0) {
    for (let rule of activeConfigObj.ignoreRowRules) {
        if (rule.column && rule.value !== "") {
            let cellValue = tempRow[rule.column];
            
            // กัน Error ถ้าค่าว่าง
            if (cellValue === undefined || cellValue === null) continue;
            
            // ถอด " หรือ ' ออกจากเซลล์เพื่อเทียบค่าตรงๆ
            let cleanCellVal = cellValue.toString().replace(/^["']|["']$/g, '').trim();
            let ruleVal = rule.value.toString().trim();
            
            // เช็กกรณีพิมพ์ NULL ถือเป็นข้อยกเว้นพิเศษ
            if (ruleVal.toUpperCase() === "NULL" && cleanCellVal.toUpperCase() === "NULL") {
                shouldSkipRow2 = true;
                break;
            }

            // 🌟 ลอจิกการเช็กแบบล้ำๆ (Wildcard) สำหรับการลบแถว 🌟
            let isMatch = false;
            
            if (ruleVal.startsWith('**') && ruleVal.endsWith('**') && ruleVal.length >= 4) {
                // 1. แบบ **RQT** (มีคำนี้อยู่ตรงไหนก็ได้ในประโยค ให้ลบแถวทิ้ง)
                let keyword = ruleVal.substring(2, ruleVal.length - 2);
                isMatch = cleanCellVal.includes(keyword);
            } 
            else if (ruleVal.startsWith('**')) {
                // 2. แบบ **RQT (ลงท้ายด้วยคำนี้ ให้ลบแถวทิ้ง)
                let keyword = ruleVal.substring(2);
                isMatch = cleanCellVal.endsWith(keyword);
            } 
            else if (ruleVal.endsWith('**')) {
                // 3. แบบ RQT** (ขึ้นต้นด้วยคำนี้ ให้ลบแถวทิ้ง)
                let keyword = ruleVal.substring(0, ruleVal.length - 2);
                isMatch = cleanCellVal.startsWith(keyword);
            } 
            else {
                // 4. แบบปกติ (Exact Match - ตรงกันเป๊ะๆ ให้ลบแถวทิ้ง)
                isMatch = (cleanCellVal === ruleVal);
            }

            // ถ้าเข้าเงื่อนไข (isMatch = true) ให้ตั้งค่าเตรียมลบแถว แล้วเบรกเลย (เพื่อความเร็ว)
            if (isMatch) {
                shouldSkipRow2 = true;
                break; 
            }
        }
    }
}
		
		// ถ้าโดนป้ายยาว่า shouldSkipRow2 = true ให้ข้ามการประมวลผลบรรทัดนี้ (เตะแถวทิ้ง) ไปเลย!
		if (shouldSkipRow2) {
		    continue; 
		}


        columns.forEach((colName, colIdx) => {
            let rawVal = rowValues[colIdx] || "";
            let cleanVal = getCleanValue(rawVal);
            let originalClean = cleanVal;


          
            // B. Smart Fill
            if ((cleanVal === "" || cleanVal === "NULL" || cleanVal === "-") && activeConfigObj.smartFillRules) {
                const rule = activeConfigObj.smartFillRules.find(r => r.targetCol === colName);
                if (rule) {
                    let refIdx = columns.indexOf(rule.refCol);
                    if (refIdx !== -1) {
                        let refClean = getCleanValue(rowValues[refIdx]).toLowerCase();
                        if (refClean !== "" && refClean !== "null") {
                            if (rule.method === 'fixed' && rule.searchTerms.some(t => refClean.includes(t.toLowerCase()))) {
                                cleanVal = rule.replaceVal;
                            } else if (rule.method === 'most_frequent') {
                                // หาค่า Mode จากแถวที่โหลดมาแล้ว (limit)
                                let possibleValues = {};
                                let scanLimit = Math.min(rawRows.length, 100); 
                                for (let j = 0; j < scanLimit; j++) {
                                    let rVals = parseSqlRow(rawRows[j].replace(/^\(/, '').replace(/\)$/, ''));
                                    if (getCleanValue(rVals[refIdx]).toLowerCase() === refClean) {
                                        let tVal = getCleanValue(rVals[colIdx]);
                                        if (tVal !== "" && tVal !== "NULL") possibleValues[tVal] = (possibleValues[tVal] || 0) + 1;
                                    }
                                }
                                let best = Object.keys(possibleValues).reduce((a, b) => possibleValues[a] > possibleValues[b] ? a : b, null);
                                if (best) cleanVal = best;
                            }
                        }
                    }
                }
            }
            processedRow[colName] = cleanVal;
        });




       	   // C. Format
			// 1. แยกคอลัมน์ (Split)
			activeConfigObj.splitRules.forEach(rule => {
			    let val = processedRow[rule.source] || "";
			    let parts = val.split(rule.delimiter === "" ? " " : rule.delimiter);
			    rule.targets.forEach((target, idx) => {
			        processedRow[target] = (parts[idx] || "").trim();
			    });
			    
		
			});
			
		// ==========================================
		    // 🎯 STEP 2: รวมคอลัมน์ (Merge)
		    // ==========================================
		    if (activeConfigObj.mergeRules) {
		        activeConfigObj.mergeRules.forEach(rule => {
		            let combined = rule.sources
		                .map(s => processedRow[s])
		                .filter(v => v !== null && v !== undefined && String(v).trim() !== "" && String(v).trim().toUpperCase() !== "NULL")
		                .join(rule.connector === "" ? " " : rule.connector);
		                
		            processedRow[rule.targetName] = combined;  
		        });
		    }
	    
		    
		    
		// ==========================================
		    // 🎯 STEP 4: ทำ Value Mapping (อัปเกรดระบบ Advanced Wildcard **) 🌟🚀
		    // ==========================================
		    if (activeConfigObj.valueMappings) {
		        activeConfigObj.valueMappings.forEach(m => {
		            // เช็กว่ามีคอลัมน์ที่ต้อง Mapping อยู่ใน processedRow ไหม
		            if (processedRow[m.column] !== undefined && processedRow[m.column] !== null) {
		                
		                // ล้างสัญลักษณ์ ✨ และ 🧩 ออกเผื่อก๊อปปี้มาติด
		                let cleanSourceMapping = m.source.toString().replace(/[✨🧩]/g, '').trim();
		                let currentRowValue = processedRow[m.column].toString().trim();
		                
		                
		                
		                let isMatch = false;
		
		                // 🌟 ลอจิกการเช็กแบบล้ำๆ (Wildcard) 🌟
		                if (cleanSourceMapping.startsWith('**') && cleanSourceMapping.endsWith('**') && cleanSourceMapping.length >= 4) {
		                    // 1. แบบ **draft** (มีคำนี้อยู่ตรงไหนก็ได้ในประโยค)
		                    let keyword = cleanSourceMapping.substring(2, cleanSourceMapping.length - 2);
		                    isMatch = currentRowValue.includes(keyword);
		                } 
		                else if (cleanSourceMapping.startsWith('**')) {
		                    // 2. แบบ **draft (ลงท้ายด้วยคำนี้)
		                    let keyword = cleanSourceMapping.substring(2);
		                    isMatch = currentRowValue.endsWith(keyword);
		                } 
		                else if (cleanSourceMapping.endsWith('**')) {
		                    // 3. แบบ draft** (ขึ้นต้นด้วยคำนี้)
		                    let keyword = cleanSourceMapping.substring(0, cleanSourceMapping.length - 2);
		                    isMatch = currentRowValue.startsWith(keyword);
		                } 
		                else {
		                    // 4. แบบปกติ (Exact Match - ตรงกันเป๊ะๆ)
		                    isMatch = (currentRowValue === cleanSourceMapping);
		                }
		
		                // ถ้าตรงเงื่อนไข (แบบใดแบบหนึ่ง) ให้ทำการแทนที่ค่า (Replace) ทันที!
		                if (isMatch) {
		                    processedRow[m.column] = m.target;
		                }
		            }
		        });
		    }
						// 2. จัดรูปแบบ (Format)
			activeConfigObj.formatRules.forEach(rule => {
			    // 🌟 เช็กก่อนว่ามีค่าใน processedRow หรือต้นฉบับไหม ถ้าไม่มีให้ข้ามไปเลย
			    let val = processedRow[rule.column];
			    
			    // ป้องกัน Error: ถ้า val เป็น null, undefined หรือไม่ใช่ String ให้ข้าม
			    if (val === undefined || val === null) return;
			
			    if (rule.type === 'number') {
			        // แปลงเป็น String ก่อนเรียกใช้ .replace() เพื่อความชัวร์
			        let strVal = val.toString().replace(/,/g, ''); 
			        let num = parseFloat(strVal);
			        
			        if (!isNaN(num)) {
			            processedRow[rule.column] = num.toLocaleString(undefined, { 
			                minimumFractionDigits: parseInt(rule.val || 0), 
			                maximumFractionDigits: parseInt(rule.val || 0) 
			            });
			        }
			    } else if (rule.type === 'date') {
			        let d = new Date(val);
			        if (!isNaN(d.getTime())) {
			            let day = String(d.getDate()).padStart(2, '0');
			            let month = String(d.getMonth() + 1).padStart(2, '0');
			            let year = d.getFullYear();
			            processedRow[rule.column] = `${day}/${month}/${year}`;
			        }
			    }
			     else if (rule.type === 'phone') {
			        // 🌟 แปลงเป็น String แล้วแทนที่ (replace) ทุกอย่างที่ "ไม่ใช่" ตัวเลข 0-9 และเครื่องหมายขีด (-) ด้วยค่าว่าง
			        processedRow[rule.column] = val.toString().replace(/[^0-9\-]/g, '');
    			}
    			else if (rule.type === 'eng') {
			    let strVal = val.toString();
			    
			    // 🌟 ตรวจสอบว่ามีตัวอักษรที่ "ไม่ใช่" ภาษาอังกฤษ/ตัวเลข/สัญลักษณ์มาตรฐาน ปนอยู่หรือไม่
			    if (/[^\x00-\x7F]/.test(strVal)) {
			        // ถ้าเงื่อนไขเป็นจริง (แปลว่ามีภาษาอื่นปนมา) ให้เคลียร์ค่าทิ้งเป็นช่องว่างเลย
			        processedRow[rule.column] = ""; 
			    } else {
			        // ถ้าไม่มีภาษาอื่นปนเลย ก็จับใส่กลับไปเป็น String ปกติ
			        processedRow[rule.column] = strVal; 
			    }
			}
			else if (rule.type === 'spam') {
			    let strVal = val.toString();
			    
			    // 1. ตรวจสอบว่ามีตัวอักษรแปลกปลอม (Non-ASCII) 
			    // 2. หรือ ความยาวเกิน 255 ตัวอักษร (Spam หรือ Data ยาวเกินพิกัด DB)
			    if (/[^\x00-\x7F]/.test(strVal) || strVal.length > 255) {
			        
			        // ถ้าเงื่อนไขใดเงื่อนไขหนึ่งเป็นจริง ให้เคลียร์ค่าทิ้งเลย
			        processedRow[rule.column] = ""; 
			        
			        // (Optional) พ่น Log ดูหน่อยว่าตัวไหนโดนสอย
			        if (strVal.length > 255) {
			            console.warn(`🚫 Spam detected by length (${strVal.length} chars) in column: ${rule.column}`);
			        }
			    } else {
			        // ถ้าผ่านเกณฑ์ ก็ใช้ค่าเดิม
			        processedRow[rule.column] = strVal; 
			    }
			}
			
			else if (rule.type === 'ID') {
			    // 1. คลีนค่า: แปลงเป็น String, ลบขีด (-) และช่องว่างออกให้หมด
			    let strVal = val.toString().replace(/[-\s]/g, '').trim();
			
			    // 2. ดึงจำนวนหลักที่ต้องการจาก rule.val (แปลงเป็นตัวเลขก่อน)
			    let digitsToKeep = parseInt(rule.val, 10);
			
			    // 3. ตรวจสอบว่า rule.val มีค่าเป็นตัวเลขที่ถูกต้องไหม
			    if (!isNaN(digitsToKeep) && digitsToKeep > 0) {
			        // ถ้าข้อมูลยาวกว่าที่กำหนด ให้ตัดเอาเฉพาะ 'digitsToKeep' ตัวสุดท้าย
			        // ตัวอย่าง: ถ้า rule.val = 5 และค่าคือ "1907000011" 
			        // .slice(-5) จะนับถอยหลังมาเอาแค่ "00011"
			        if (strVal.length > digitsToKeep) {
			            strVal = strVal.slice(-digitsToKeep);
			        }
			    }
			
			    // 4. ตัดเลข 0 ที่อยู่ด้านหน้าออก (Leading Zeros)
			    // จาก "00011" -> "11"
			    let finalVal = strVal.replace(/^0+/, '');
			
			    // 5. กันเหนียว: ถ้าตัดแล้วเกลี้ยง (ค่าเดิมเป็น 00000) ให้คืนค่า "0" ไม่ใช่ค่าว่าง ""
			    processedRow[rule.column] = finalVal === "" ? "0" : finalVal;
			}  else if (rule.type === 'numberonly') {
				    // 1. ล้างขยะ: เอาเฉพาะตัวเลข 0-9 และขีด - (เผื่อติดลบ)
				    let cleanVal = val.toString().replace(/[^0-9\-]/g, '');
				
				    // 2. 🌟 ตัด 0 ข้างหน้า: (เช่น 00123 -> 123, 000 -> 0)
				    // ใช้ Regex: ^0+ (ขึ้นต้นด้วย 0 กี่ตัวก็ได้) + (?=\d) (ต้องมีตัวเลขตามหลังอย่างน้อย 1 ตัว ถึงจะยอมลบ)
				    processedRow[rule.column] = cleanVal.replace(/^0+(?=\d)/, '');
				}
			
    			
			});
		


        window.currentPreviewPayload.push(processedRow);

        if (i < limit) {
            let bgColor = (i % 2 === 0) ? "#ffffff" : "#f9f9f9";
            html += `<tr style="background-color: ${bgColor};">`;
            displayColumns.forEach(col => {
                let cleanVal = processedRow[col.source];
                let originalVal = getCleanValue(rowValues[col.index]);
                let displayVal = (cleanVal === "" || cleanVal === "NULL") ? '<span style="color:#bbb;">NULL</span>' : cleanVal;
                
                if (cleanVal !== originalVal && cleanVal !== "NULL") {
                    displayVal = `<span style="color: #8e44ad; font-weight:bold;">✨ ${cleanVal}</span>`;
                }
                if (activeConfigObj.scanDuplicateColumns.includes(col.source) && originalVal !== 'NULL' && originalVal !== "") {
                    displayVal = `<b style="color: #c9302c;">${displayVal}</b>`;
                }
                html += `<td style="padding: 8px; border: 1px solid #ddd;">${displayVal}</td>`;
            });
            html += `</tr>`;
        }
    }

 html += `</tbody></table></div>`;



    if (rawRows.length > 0) {

        html += `<p style="font-size: 12px; color: #666; margin-top: 10px;">

                    <i>* แสดงตัวอย่าง ${limit} รายการ จากทั้งหมด ${rawRows.length} (คอลัมน์ที่คุณเลือกตัดทิ้งจะถูกซ่อนอัตโนมัติ)</i>

                 </p>`;

    }



    document.getElementById('table-container').innerHTML = html;

    

    // ปลดล็อกปุ่มต่างๆ

    ['row_mapper', 'transformdata', 'DuplicateScan', 'ToSqlFile', 'ToCsvFile'].forEach(id => {

        const btn = document.getElementById(id);

        if (btn) btn.classList.remove('disabled');

    });
}

 


// ==========================================
// 🔄 ฟังก์ชันรีเฟรชตาราง (อัปเกรดให้วิ่งเข้าท่อหลัก!)
// ==========================================
function refreshPreviewTable() {
    // เช็กว่ามีไฟล์ที่โหลดค้างไว้ในระบบหรือเปล่า
    if (!importPendingFile) {
        console.warn("ไม่พบไฟล์ต้นฉบับ ไม่สามารถรีเฟรชตารางได้");
        return;
    }

    console.log("กำลังรีเฟรชตารางพรีวิวใหม่ ผ่าน Pipeline หลัก...");
    
    // ใช้เทคนิคตัดไฟล์ 20MB 
    const CHUNK_SIZE = 20 * 1024 * 1024; 
    const blob = importPendingFile.slice(0, CHUNK_SIZE); 
    const reader = new FileReader();
    
    // ถ้าหน้าจอคุณใช้ id 'showdata-1' ในการโชว์โหลด ก็ทิ้งไว้แบบนี้ได้ครับ
    const showDataElem = document.getElementById('table-container');
    if (showDataElem) showDataElem.innerHTML = '<p style="color:#217346;">กำลังอัปเดตการแสดงผลตาราง...</p>';
    
    reader.onload = function(e) {
        const content = e.target.result;
        
        // 🌟 1. อ่านไฟล์แล้วชำแหละให้เป็น JSON (เหมือนตอน Upload)
        const lines = content.split(/\r?\n/).filter(line => line.trim() !== "");
        if (lines.length < 2) return;

        const headers = lines[0].split(',').map(h => h.replace(/["']/g, '').trim());
        const dataRows = [];

        for (let r = 1; r < lines.length; r++) {
            let line = lines[r];
            const values = [];
            let currentField = "";
            let inQuotes = false;

            for (let i = 0; i < line.length; i++) {
                let char = line[i];
                if (char === '"') {
                    inQuotes = !inQuotes; 
                } else if (char === ',' && !inQuotes) {
                    values.push(currentField.trim().replace(/^"|"$/g, ''));
                    currentField = "";
                } else {
                    currentField += char;
                }
            }
            values.push(currentField.trim().replace(/^"|"$/g, ''));

            let rowObj = { id: r }; 
            headers.forEach((h, index) => {
                rowObj[h] = values[index] !== undefined ? values[index] : "";
            });
            dataRows.push(rowObj);
        }

        // 🌟 2. แพ็กข้อมูลใส่กล่อง
        const mockDataset = [{
            "headers": headers,
            "data": dataRows,
            "total_json": dataRows.length,
            "total_data": dataRows.length,
            "tables": importPendingFile.name.split('.')[0], // เอาแค่นามสกุลออก
            "id_refer": 0,
            "id_config": 0
        }];

        // 🌟 3. บังคับยิงเข้าท่อ renderFirstTable แทนที่จะยิงเข้า renderSqlPreview ตรงๆ!
        // (เปลี่ยน "showdata-1" เป็น ID ของตารางที่คุณต้องการให้มันไปวาดลงนะครับ ถ้าใช้ชื่ออื่นอยู่)
        renderFirstTable(mockDataset, "table-container"); 
        
        console.log("🚀 รีเฟรชเสร็จสิ้น: ข้อมูลวิ่งผ่านโหมดตั้งค่าเรียบร้อย!");
    };
    
    reader.readAsText(blob, 'UTF-8');
}





// ฟังก์ชันสำหรับแยกคอลัมน์ SQL แบบแม่นยำ 100% (รองรับ JSON และข้อมูลที่มีลูกน้ำซ่อนอยู่)
function parseSqlRow(rowStr) {
    let values = [];
    let currentVal = '';
    let inString = false;
    let stringChar = ''; 
    let escapeNext = false;

    for (let i = 0; i < rowStr.length; i++) {
        let char = rowStr[i];

        if (escapeNext) {
            currentVal += char;
            escapeNext = false;
            continue;
        }

        if (char === '\\') { // ดักจับ Backslash 
            escapeNext = true;
            currentVal += char;
            continue;
        }

        if (!inString) {
            if (char === "'" || char === '"') {
                inString = true;
                stringChar = char;
                currentVal += char;
            } else if (char === ',') {
                values.push(currentVal.trim());
                currentVal = '';
            } else {
                currentVal += char;
            }
        } else {
            currentVal += char;
            if (char === stringChar) {
                // เช็กการเบิ้ล Quote (Escaped Quote ใน SQL)
                if (i + 1 < rowStr.length && rowStr[i+1] === stringChar) {
                    currentVal += rowStr[i+1];
                    i++; 
                } else {
                    inString = false;
                }
            }
        }
    }
    values.push(currentVal.trim());
    return values;
}




// ฟังก์ชันกดปุ่มสแกนหาข้อมูลซ้ำ (ดึงเฉพาะตัวซ้ำมาแสดง)
function scanAndShowDuplicates() {
    if (!importPendingFile || !activeConfigObj || !activeConfigObj.scanDuplicateColumns || activeConfigObj.scanDuplicateColumns.length === 0) {
        alert("กรุณาเลือกไฟล์ และติ๊กเลือกคอลัมน์ที่ต้องการสแกนหาข้อมูลซ้ำก่อนครับ");
        return;
    }

    document.getElementById('table-container').innerHTML = `
        <div style="padding: 20px; text-align: center;">
            <h4 style="color:#d9534f;">⏳ กำลังสแกนหาข้อมูลซ้ำและเตรียมหน้าต่างจัดการ...</h4>
        </div>`;

    const reader = new FileReader();
    reader.onload = function(e) {
        const fullSql = e.target.result;

        const insertRegex = /INSERT\s+INTO\s+([^()]+)\(([^)]+)\)\s*VALUES/is;
        const match = fullSql.match(insertRegex);
        if (!match) return;
        const columns = match[2].split(',').map(c => c.replace(/[`'" ]/g, ''));
        
        globalOriginalColumns = columns; // เก็บไว้ใช้ตอน export

        const scanIndices = [];
        activeConfigObj.scanDuplicateColumns.forEach(colName => {
            let idx = columns.indexOf(colName);
            if (idx !== -1) scanIndices.push({ index: idx, name: colName });
        });

        let parsedRows = [];
        
        const allValuesParts = fullSql.match(/VALUES\s*([\s\S]*?)(?:;|$)/ig) || [];
        
        allValuesParts.forEach(valPart => {
            const rowsInPart = valPart.match(/\(([\s\S]*?)\)(?=\s*[,;]|\s*$)/g) || [];
            rowsInPart.forEach(r => {
                let cleanStr = r.replace(/^\(/, '').replace(/\)$/, '');
                parsedRows.push(parseSqlRow(cleanStr));
            });
        });
    
        // อัลกอริทึมจัดกลุ่ม (Union-Find)
        let parent = Array.from({length: parsedRows.length}, (_, i) => i);
        function find(i) {
            if (parent[i] === i) return i;
            return parent[i] = find(parent[i]);
        }
        function union(i, j) {
            let rootI = find(i);
            let rootJ = find(j);
            if (rootI !== rootJ) parent[rootI] = rootJ;
        }

        function cleanForMatch(val) {
            let noQuoteVal = val ? val.trim().replace(/^['"]|['"]$/g, '') : "";
            if (!noQuoteVal || noQuoteVal.toUpperCase() === 'NULL' || noQuoteVal === '-') return "";
            return noQuoteVal.replace(/[\s\-\,]/g, '').toLowerCase();
        }

        scanIndices.forEach(colInfo => {
            let valueMap = new Map();
            for (let i = 0; i < parsedRows.length; i++) {
                let rawVal = parsedRows[i][colInfo.index];
                let cleanVal = cleanForMatch(rawVal);
                if (cleanVal === "") continue;

                if (valueMap.has(cleanVal)) {
                    union(i, valueMap.get(cleanVal)); 
                } else {
                    valueMap.set(cleanVal, i);
                }
            }
        });

        let groups = new Map();
        for (let i = 0; i < parsedRows.length; i++) {
            let root = find(i);
            if (!groups.has(root)) groups.set(root, []);
            groups.get(root).push(parsedRows[i]);
        }

        let duplicateGroups = Array.from(groups.values()).filter(group => group.length > 1);
        let uniqueGroups = Array.from(groups.values()).filter(group => group.length === 1); 

        globalDuplicateGroups = duplicateGroups; 
        globalUniqueRows = uniqueGroups.map(g => g[0]); 

        if (duplicateGroups.length === 0) {
            document.getElementById('table-container').innerHTML = `
                <div style="background-color: #eefbf4; padding: 20px; border: 1px solid #cce0d8; border-radius: 4px; text-align: center;">
                    <h4 style="color: #217346; margin:0;">✅ ยอดเยี่ยม! ไม่พบข้อมูลซ้ำในระบบตามเงื่อนไขที่คุณเลือก</h4>
                    <button onclick="refreshPreviewTable()" style="margin-top: 15px; background-color: #217346; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer;">กลับไปหน้าพรีวิวปกติ</button>
                </div>`;
            return;
        }

        // ==========================================
        // 🎨 สร้างตารางพร้อมช่องให้เลือก (จัดการ)
        // ==========================================
        let html = `
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
                <h4 style="color:#d9534f; margin: 0;">⚠️ พบตัวซ้ำ <span style="font-size: 20px;">${duplicateGroups.length}</span> กลุ่ม (เลือกการจัดการให้ถูกต้องก่อนกดรวม)</h4>
                <div>
                    <button onclick="exportDuplicatesToCSV()" style="background-color: #17a2b8; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight:bold; margin-right: 10px;">📥 นำออก CSV</button>
                    
                    <button onclick="processMergeDuplicates()" style="background-color: #5cb85c; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight:bold; margin-right: 10px;">✨ ยืนยันการรวมข้อมูล</button>
                    <button onclick="refreshPreviewTable()" style="background-color: #666; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight:bold;">🔙 ยกเลิก</button>
                </div>
            </div>`;
        
        html += `<div style="overflow-x: auto; overflow-y: auto; max-height: 500px; border: 2px solid #ebccd1; margin-bottom: 15px;">`;
        html += `<table style="width:100%; border-collapse: collapse; font-family: sans-serif; font-size: 13px; white-space: nowrap;">`;
        html += `<thead style="position: sticky; top: 0; z-index: 2; box-shadow: 0 2px 3px rgba(0,0,0,0.1);"><tr>`;
        
        html += `<th style="padding: 10px; border: 1px solid #ebccd1; text-align: center; background-color: #f2dede; color: #a94442;">⚙️ จัดการข้อมูล</th>`;

        const displayColumns = [];
        columns.forEach((colName, index) => {
            if (activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(colName)) return;
            displayColumns.push({ index: index, source: colName });
        });

        displayColumns.forEach(col => {
            let isScanCol = activeConfigObj.scanDuplicateColumns.includes(col.source);
            let headerHtml = isScanCol ? `🔍 <span style="color:#c9302c; font-weight:bold;">${col.source}</span>` : col.source;
            html += `<th style="padding: 10px; border: 1px solid #ebccd1; text-align: left; background-color: #f2dede; color: #a94442;">${headerHtml}</th>`;
        });
        html += `</tr></thead><tbody>`;

        let colorA = "#ffffff"; 
        let colorB = "#fff8e1"; 
        
        duplicateGroups.forEach((group, gIndex) => {
            let bgColor = (gIndex % 2 === 0) ? colorA : colorB;
            
            group.forEach((rowValues, rIndex) => {
                html += `<tr>`;
                
                let selKeep = (rIndex === 0) ? 'selected' : '';
                let selDup = (rIndex !== 0) ? 'selected' : '';

                html += `<td style="padding: 8px; border: 1px solid #ebccd1; background-color: ${bgColor}; text-align:center;">
                    <select id="action_g${gIndex}_r${rIndex}" style="padding: 5px;width:150px; border-radius: 4px; border: 1px solid #ccc; font-size: 13px; cursor: pointer; outline: none; background: #fff;">
                        <option value="keep" ${selKeep}>📌 เก็บไว้ (Master)</option>
                        <option value="dup" ${selDup}>🗑️ ซ้ำ (รวมไอดี)</option>
                        <option value="not_dup">✅ ไม่ซ้ำ (แยกออก)</option>
                    </select>
                </td>`;

                displayColumns.forEach(col => {
                    let val = rowValues[col.index] || "";
                    let cleanVal = val.trim().replace(/^['"]|['"]$/g, '');
                    let displayVal = cleanVal === 'NULL' ? '<span style="color:#bbb;">NULL</span>' : cleanVal;
                    
                    if(activeConfigObj.scanDuplicateColumns.includes(col.source) && cleanVal && cleanVal.toUpperCase() !== 'NULL') {
                        displayVal = `<b style="color: #c9302c;">${displayVal}</b>`;
                    }
                    html += `<td style="padding: 8px; border: 1px solid #ebccd1; background-color: ${bgColor};">${displayVal}</td>`;
                });
                html += `</tr>`;
            });
        });

        html += `</tbody></table></div>`;
        document.getElementById('table-container').innerHTML = html;
    };
    reader.readAsText(importPendingFile); 
}


function exportDuplicatesToCSV() {
    if (!globalDuplicateGroups || globalDuplicateGroups.length === 0) {
        alert("ไม่พบข้อมูลซ้ำที่จะส่งออก");
        return;
    }

    // 1. เตรียมหัวตาราง (Header)
    // เพิ่มคอลัมน์แรกเป็น Group ID เพื่อให้รู้ว่าแถวไหนอยู่กลุ่มซ้ำเดียวกัน
    let csvContent = "Duplicate Group ID," + globalOriginalColumns.join(",") + "\n";

    // 2. วนลูปข้อมูลแต่ละกลุ่ม
    globalDuplicateGroups.forEach((group, groupIndex) => {
        group.forEach(row => {
            // คลีนข้อมูล (ลบ Quote ของ SQL ออก)
            let cleanedRow = row.map(val => {
                let clean = val ? val.trim().replace(/^['"]|['"]$/g, '') : "";
                // ถ้ามี comma หรือ double quote ในเนื้อหา ให้ครอบด้วย "..." ตามมาตรฐาน CSV
                if (clean.includes(",") || clean.includes("\"") || clean.includes("\n")) {
                    clean = `"${clean.replace(/"/g, '""')}"`;
                }
                return clean;
            });

            // เพิ่ม Group ID ไปข้างหน้า (groupIndex + 1)
            csvContent += `${groupIndex + 1},${cleanedRow.join(",")}\n`;
        });
    });

    // 3. สร้าง Blob พร้อม BOM (\uFEFF) เพื่อให้ Excel อ่านภาษาไทยออก
    const blob = new Blob(["\uFEFF" + csvContent], { type: 'text/csv;charset=utf-8;' });

    // 4. สร้าง Link เพื่อดาวน์โหลด
    const link = document.createElement("a");
    const url = URL.createObjectURL(blob);
    const timestamp = new Date().toISOString().replace(/[:.]/g, "-"); // สร้างชื่อไฟล์แบบไม่ซ้ำ
    
    link.setAttribute("href", url);
    link.setAttribute("download", `duplicate_report_${timestamp}.csv`);
    link.style.visibility = 'hidden';
    
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

// ฟังก์ชันสำหรับประมวลผลการกดปุ่ม "ยืนยันการรวมข้อมูล" (ฉบับสมบูรณ์)
function processMergeDuplicates() {
    let finalResolvedRows = [];

    // 1. จัดการกลุ่มที่ซ้ำ (Duplicate Groups)
    globalDuplicateGroups.forEach((group, gIndex) => {
        let masterRow = null;
        let mergedIds = [];
        let notDupRows = [];

        group.forEach((rowValues, rIndex) => {
            let selectElem = document.getElementById(`action_g${gIndex}_r${rIndex}`);
            let action = selectElem ? selectElem.value : 'dup';

            // ทำความสะอาด ID (ลบ Quote/Backslash)
            let rowId = rowValues[0] ? rowValues[0].toString().trim().replace(/^(?:\\?['"])+|(?:\\?['"])+$/g, '') : "";

            if (action === 'keep') {
                if (!masterRow) {
                    masterRow = [...rowValues];
                } else {
                    notDupRows.push([...rowValues]);
                }
            } else if (action === 'dup') {
                if (rowId && rowId !== 'NULL') {
                    mergedIds.push(rowId);
                }
            } else if (action === 'not_dup') {
                notDupRows.push([...rowValues]);
            }
        });

        // จัดการแถวที่เป็นตัวหลัก (Master) ในกลุ่มนั้น
        if (masterRow) {
            mergedIds.sort((a, b) => isNaN(a) || isNaN(b) ? a.localeCompare(b) : parseFloat(a) - parseFloat(b));
            let mergedIdsString = mergedIds.length > 0 ? mergedIds.join(',') : "";
            
            // สำคัญ: เก็บค่าลงทั้งท้าย Array และ Named Key เพื่อให้ Export มองเห็น
            masterRow.push(mergedIdsString); 
            masterRow["merged_duplicate_ids"] = mergedIdsString; 
            finalResolvedRows.push(masterRow);
        }

        // จัดการแถวที่ User บอกว่า "ไม่ใช่ตัวซ้ำ" ในกลุ่มนั้น
        notDupRows.forEach(row => {
            let nRow = [...row];
            nRow.push(""); 
            nRow["merged_duplicate_ids"] = ""; 
            finalResolvedRows.push(nRow);
        });
    });

    // 2. นำข้อมูลที่ "ไม่ซ้ำตั้งแต่แรก" มาเติม (วางไว้นอกลูปกลุ่มซ้ำ เพื่อป้องกันข้อมูลเบิ้ล)
    globalUniqueRows.forEach(row => {
        let newRow = [...row];
        newRow.push(""); 
        newRow["merged_duplicate_ids"] = ""; 
        finalResolvedRows.push(newRow);
    });

    // 3. เรียงลำดับข้อมูลทั้งหมดตาม ID (คอลัมน์แรก) เพื่อความสวยงามเหมือนต้นฉบับ
    finalResolvedRows.sort((a, b) => {
        let idA = a[0] ? a[0].toString().replace(/^(?:\\?['"])+|(?:\\?['"])+$/g, '') : "";
        let idB = b[0] ? b[0].toString().replace(/^(?:\\?['"])+|(?:\\?['"])+$/g, '') : "";

        if (!isNaN(idA) && !isNaN(idB) && idA !== "" && idB !== "") {
            return parseFloat(idA) - parseFloat(idB);
        }
        return idA.localeCompare(idB);
    });

    // 4. วาดตารางพรีวิวผลลัพธ์
    renderMergedResultTable(finalResolvedRows);

    // 5. อัปเดต Global Payload เพื่อให้ปุ่ม Save to SQL/CSV ใช้งานได้ทันที
    window.globalFinalPayloadToImport = finalResolvedRows;
}

// ฟังก์ชันสำหรับวาดตาราง (แยกออกมาเพื่อให้โค้ดหลักสะอาด)
function renderMergedResultTable(finalResolvedRows) {
    let html = `
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;">
            <h4 style="color:#217346; margin: 0;">✅ ตรวจสอบความถูกต้องก่อนนำออก (${finalResolvedRows.length} รายการ)</h4>
            <div>
                 <button onclick="processImportData()" style="background-color: #217346; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight:bold; margin-right: 10px;">🚀 ไปหน้า Mapping</button>
                 <button onclick="refreshPreviewTable()" style="background-color: #666; color: white; padding: 8px 15px; border: none; border-radius: 4px; cursor: pointer; font-weight:bold;">🔙 กลับไปสแกนใหม่</button>
            </div>
        </div>`;
    
    html += `<div style="overflow: auto; max-height: 450px; border: 1px solid #ddd; user-select: text !important;">`;
    html += `<table style="width:100%; border-collapse: collapse; font-family: sans-serif; font-size: 13px; white-space: nowrap;">`;
    html += `<thead style="position: sticky; top: 0; z-index: 2; background-color: #eefbf4;"><tr>`;
    
    // สร้างหัวตาราง (ไม่เอาคอลัมน์ที่ถูก Ignore)
    const displayColumns = [];
    globalOriginalColumns.forEach((colName, index) => {
        if (activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(colName)) return;
        displayColumns.push({ index: index, source: colName });
        html += `<th style="padding: 10px; border: 1px solid #ddd; text-align: left;">${colName}</th>`;
    });
    
    html += `<th style="padding: 10px; border: 1px solid #ddd; text-align: left; background-color: #dff0d8; color: #3c763d;">🔗 ID ที่ซ้ำ</th>`;
    html += `</tr></thead><tbody>`;

    const limit = Math.min(finalResolvedRows.length, 1000);
    for (let i = 0; i < limit; i++) {
        let rowValues = finalResolvedRows[i];
        html += `<tr>`;
        displayColumns.forEach(col => {
            let val = rowValues[col.index] || "";
            let cleanVal = val.toString().replace(/^(?:\\?['"])+|(?:\\?['"])+$/g, '');
            html += `<td style="padding: 8px; border: 1px solid #ddd;">${cleanVal === 'NULL' ? '-' : cleanVal}</td>`;
        });
        
        // แสดงคอลัมน์พิเศษ (Merged IDs)
        let mIds = rowValues["merged_duplicate_ids"] || "";
        html += `<td style="padding: 8px; border: 1px solid #ddd; background-color: #f9fdf9;">${mIds ? `<b>${mIds}</b>` : "-"}</td>`;
        html += `</tr>`;
    }

    html += `</tbody></table></div>`;
    document.getElementById('table-container').innerHTML = html;
}

function exportToSqlFile() {
    let sourceData = window.globalFinalPayloadToImport || window.currentPreviewPayload;
    let originalCols = window.globalOriginalColumns; 

    if (!sourceData || sourceData.length === 0 || !originalCols) {
        alert("⚠️ ข้อมูลไม่พร้อมสำหรับนำออก");
        return;
    }
    
    // ==========================================
    // 🌟 พระเอกออกโรง: เตะแถวที่ไม่ต้องการทิ้ง (Exclude Rows)
    // ทำตรงนี้ก่อนที่จะคำนวณบรรทัด จะได้จำนวนแถวที่ถูกต้อง 100%
    // ==========================================
    if (activeConfigObj.ignoreRowRules && activeConfigObj.ignoreRowRules.length > 0) {
        let originalCount = sourceData.length;
        
        // กรองข้อมูล (ถ้า return true = เก็บไว้, return false = เตะทิ้ง)
        sourceData = sourceData.filter(row => {
            let shouldSkipRow = false;
            
            for (let rule of activeConfigObj.ignoreRowRules) {
                if (rule.column && rule.value !== "") {
                    
                    // ดึงค่าของเซลล์นั้นๆ ออกมาเช็ก
                    let cellValue = row[rule.column];
                    
                    // (Fallback) ถ้าหาด้วยชื่อ Key ไม่เจอ ลองหาด้วย Index
                    if (cellValue === undefined) {
                        let idx = originalCols.indexOf(rule.column);
                        if (idx !== -1) cellValue = row[idx];
                    }

                    if (cellValue !== undefined && cellValue !== null) {
                        let cleanCellVal = cellValue.toString().replace(/^["']|["']$/g, '').trim();
                        let ruleVal = rule.value.toString().trim();
                        
                        // ถ้าค่าตรงกันเป๊ะ หรือเช็ก NULL -> สั่งให้เตะทิ้ง
                        if (cleanCellVal === ruleVal || (ruleVal.toUpperCase() === "NULL" && cleanCellVal.toUpperCase() === "NULL")) {
                            shouldSkipRow = true;
                            break; 
                        }
                    }
                }
            }
            return !shouldSkipRow; // ถ้าควรข้าม(true) จะส่งกลับ false เพื่อลบออกจาก Array
        });

        // แจ้งเตือนดูว่าหายไปกี่แถว (Optional - ลบทิ้งได้ถ้าไม่อยากให้เด้ง)
        console.log(`🗑️ โดนตัดทิ้งไป: ${originalCount - sourceData.length} แถว`);
    }
    // ==========================================
    
    
    

    const chunkSize = 30000; 
    const totalRows = sourceData.length;
    const totalFiles = Math.ceil(totalRows / chunkSize);
    let tableName = activeConfigObj?.tableName || "clean_data";

    let finalHeaderNames = []; 
    let dataKeys = [];         

    // ==========================================
    // 1. ดึงคอลัมน์ต้นฉบับ (ที่ไม่ได้ถูก Ignore)
    // ==========================================
    originalCols.forEach((colName) => {
        if (activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(colName)) return;
        
        // ดึงชื่อเป้าหมายที่ Map ไว้ ถ้ามีเป้าหมายให้ใช้ ถ้าไม่มีก็ใช้ชื่อเดิม
        let mapped = activeConfigObj.mappedColumns ? activeConfigObj.mappedColumns.find(c => c.source === colName) : null;
        let finalName = (mapped && mapped.target && mapped.target.trim() !== "") ? mapped.target : colName;
        
        finalHeaderNames.push(finalName);
        dataKeys.push(colName);
    });

    // ==========================================
    // 2. ดึงคอลัมน์เสมือน (Split) พร้อมชื่อที่ Map ไว้
    // ==========================================
    if (activeConfigObj.splitRules) {
        activeConfigObj.splitRules.forEach(rule => {
            rule.targets.forEach(targetName => {
                if (!dataKeys.includes(targetName)) {
                    // หาชื่อ Target ที่ Map ไว้
                    let mapped = activeConfigObj.mappedColumns ? activeConfigObj.mappedColumns.find(c => c.source === targetName) : null;
                    let finalName = (mapped && mapped.target && mapped.target.trim() !== "") ? mapped.target : targetName;

                    finalHeaderNames.push(finalName); // ใส่ชื่อเป้าหมาย (ลงในคำสั่ง INSERT)
                    dataKeys.push(targetName);        // ใส่ชื่อต้นทาง (เพื่อเอาไปล้วงข้อมูลใน Object)
                }
            });
        });
    }

    // ==========================================
    // 3. ดึงคอลัมน์เสมือน (Merge) พร้อมชื่อที่ Map ไว้
    // ==========================================
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!dataKeys.includes(rule.targetName)) {
                // หาชื่อ Target ที่ Map ไว้
                let mapped = activeConfigObj.mappedColumns ? activeConfigObj.mappedColumns.find(c => c.source === rule.targetName) : null;
                let finalName = (mapped && mapped.target && mapped.target.trim() !== "") ? mapped.target : rule.targetName;

                finalHeaderNames.push(finalName); // ใส่ชื่อเป้าหมาย (ลงในคำสั่ง INSERT)
                dataKeys.push(rule.targetName);   // ใส่ชื่อต้นทาง (เพื่อเอาไปล้วงข้อมูลใน Object)
            }
        });
    }

    // 🌟 4. เช็กกรณีรวมแถวที่ซ้ำ (Duplicate Merge)
    let isMerged = (window.globalFinalPayloadToImport && sourceData === window.globalFinalPayloadToImport);
    if (isMerged) {
        finalHeaderNames.push("merged_duplicate_ids");
        dataKeys.push("merged_duplicate_ids");
    }

    // ==========================================
    // 🌟 ฟังก์ชัน purify ขั้นเทพ (ป้องกันการสร้าง '''เบิ้ล''')
    // ==========================================
    const purify = (val) => {
        if (val === null || val === undefined || val === "" || val === "NULL") return "NULL";
        
        // 1. แปลงเป็น String และลบสิ่งสกปรก รวมถึงอีโมจิ 🧩, ✨
        let clean = val.toString().replace(/<[^>]*>?/gm, '').replace(/[✨🧩]/g, '').trim();
        
        if (clean === "" || clean.toUpperCase() === "NULL") return "NULL";
        
        // 2. 🌟 เด็ดขาด: ถ้าข้อความมันมี ' หรือ " ครอบอยู่แล้ว ให้ถอดเปลือกมันออกก่อน! 
        // ป้องกันอาการซ้อนทับจนกลายเป็น '''บจก'''
        if ((clean.startsWith("'") && clean.endsWith("'")) || (clean.startsWith('"') && clean.endsWith('"'))) {
            clean = clean.substring(1, clean.length - 1);
        }

        // 3. ป้องกันการขึ้นบรรทัดใหม่ทำให้คำสั่ง SQL แตก (แปลง Enter เป็นรหัส \n)
        clean = clean.replace(/\r\n/g, '\\n').replace(/\n/g, '\\n').replace(/\r/g, '\\n');

        // 4. หุ้มด้วย ' ' เดียวให้ถูกต้องตามหลัก SQL
        return `'${clean.replace(/'/g, "''")}'`;
    };

    // ==========================================
    // --- 🚀 เริ่มสร้างไฟล์ SQL ---
    // ==========================================
    for (let f = 0; f < totalFiles; f++) {
        let sqlContent = `-- Part ${f + 1} | Table: ${tableName} | Rows: ${totalRows}\n`;
        
        // 🌟 หัวตาราง (INSERT INTO) ตอนนี้จะใช้ชื่อ Target ตามที่คุณเลือก Mapping ไว้เป๊ะๆ
        sqlContent += `INSERT INTO \`${tableName}\` (\`${finalHeaderNames.join('`, `')}\`) VALUES \n`;

        let start = f * chunkSize;
        let end = Math.min(start + chunkSize, totalRows);
        let rowStrings = [];

        for (let i = start; i < end; i++) {
            let row = sourceData[i];
            let valStrings = [];

            // ดึงข้อมูลออกมาตามคิว (กุญแจ) ที่เราจัดเรียงไว้
            dataKeys.forEach(key => {
                let cellValue = "NULL"; 
                
                // ค้นหาค่าจาก processedRow ที่เราทำมา
                if (row[key] !== undefined && row[key] !== null) {
                    cellValue = row[key];
                } 
                else {
                    // ถ้าหาไม่เจอ ให้ไปดึงจาก Index ดิบ (Fallback)
                    let idx = originalCols.indexOf(key);
                    if (idx !== -1 && row[idx] !== undefined) {
                        cellValue = row[idx];
                    }
                }
                
                // ส่งไปชุบตัว (Purify) แล้วยัดเข้า Array
                valStrings.push(purify(cellValue));
            });

            // รวมข้อมูล 1 แถว ครอบด้วย ( ... )
            rowStrings.push(`(${valStrings.join(', ')})`);
        }

        // เชื่อมแต่ละแถวด้วย , และจบคำสั่งด้วย ;
        sqlContent += rowStrings.join(',\n') + ";";

        // สร้างไฟล์และสั่งดาวน์โหลด
        const blob = new Blob([sqlContent], { type: 'text/sql;charset=utf-8;' });
        const link = document.createElement("a");
        link.href = URL.createObjectURL(blob);
        let suffix = totalFiles > 1 ? `_part${f + 1}` : "";
        link.download = `final_export_${tableName}${suffix}.sql`;
        link.click();
    }
}





function getCleanValue(val) {
    if (!val || val === 'NULL') return 'NULL';
    
    // 1. ลอกคราบ Quote และ Backslash ทุกรูปแบบ (', ", \', \")
    let clean = val.trim().replace(/^(?:\\?['"])+|(?:\\?['"])+$/g, '');
    
    // 2. ถ้าเป็น JSON (ภาษาต่างดาว \u0e23...) ให้ถอดรหัสเป็นภาษาไทยก่อน
    if (clean.includes('\\u')) {
        try {
            // เติมฟันหนูครอบเพื่อให้เป็น JSON string ที่ถูกต้องก่อน parse
            clean = JSON.parse('"' + clean.replace(/"/g, '\\"') + '"');
        } catch (e) { 
            console.log("ถอดรหัส JSON ไม่สำเร็จ:", clean);
        }
    }
    return clean;
}








function buildSmartFillUI() {
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;
    if (!activeConfigObj.smartFillRules) activeConfigObj.smartFillRules = [];

    // ==========================================
    // 🌟 ระบบ ERP แทรกแซง: เตรียมคอลัมน์ปกติ + คอลัมน์เสมือน
    // ==========================================
    let displayColumns = [];

    // 1. ดึงคอลัมน์ปกติมาใส่ก่อน
    activeConfigObj.mappedColumns.forEach(col => {
        displayColumns.push({ name: col.source, isVirtual: false });
    });

    // 2. ดึงคอลัมน์เสมือน (Merge Rules) มาต่อท้าย
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!displayColumns.find(c => c.name === rule.targetName)) {
                displayColumns.push({ name: rule.targetName, isVirtual: true });
            }
        });
    }

    // 🌟 สร้าง HTML สำหรับ Option ใน Dropdown รอรวบยอดทีเดียว
    let optionsHtml = displayColumns.map(col => {
        // ถ้าเป็นคอลัมน์เสมือน ใส่ไอคอน 🧩 ต่อท้ายให้ดูรู้เรื่อง (ในแท็ก <option> ใส่ CSS ไม่ได้ เลยใช้อีโมจิแทน)
        let label = col.isVirtual ? `${col.name} 🧩` : col.name;
        return `<option value="${col.name}">${label}</option>`;
    }).join('');
    // ==========================================

    let html = '<h4 style="margin-bottom: 10px; color: #8e44ad; border-bottom: 2px solid #8e44ad; padding-bottom: 8px; font-weight: bold;">🧠 ระบบเติมเต็มข้อมูลอัจฉริยะ (Smart Data Fill)</h4>';
    html += '<p style="font-size: 13px; color: #666; margin-bottom: 15px;">ตั้งค่าให้ระบบเติมข้อมูลในช่องที่ว่างอัตโนมัติ โดยวิเคราะห์จากคอลัมน์อื่นหรือใช้ค่ากลาง</p>';

    // --- ส่วนเพิ่มเงื่อนไขการเติมค่า ---
    html += `
    <div style="background: #fdfaff; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #dcd0ff;">
        <div style="display: grid; grid-template-columns: 1fr 1fr 1fr auto; gap: 10px; align-items: flex-end;">
            <div>
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">ถ้าคอลัมน์นี้ "ว่าง"</label>
                <select id="sf_target_col" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
                    ${optionsHtml}
                </select>
            </div>
            <div>
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">ให้ตรวจสอบคอลัมน์นี้</label>
                <select id="sf_ref_col" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
                    ${optionsHtml}
                </select>
            </div>
            <div>
                <label style="font-size: 11px; display: block; margin-bottom: 4px;">วิธีเติมค่า</label>
                <select id="sf_method" style="width: 100%; padding: 6px; border-radius: 4px; border: 1px solid #ccc;" onchange="toggleSfValueInput(this)">
                    <option value="fixed">เติมค่าคงที่ (Fixed Value)</option>
                    <option value="most_frequent">ค่าที่พบบ่อย (Mode/Most Frequent)</option>
                    <option value="average">ค่าเฉลี่ย (Mean - สำหรับตัวเลข)</option>
                </select>
            </div>
            <button onclick="addSmartFillRule()" style="background: #8e44ad; color: white; border: none; padding: 7px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;">+ เพิ่มกฎ</button>
        </div>
        
        <div id="sf_fixed_val_container" style="margin-top:10px;">
            <label style="font-size: 11px; display: block; margin-bottom: 4px;">ถ้าเจอคำว่า (ใช้เครื่องหมาย , คั่นได้)</label>
            <div style="display: flex; gap: 10px;">
                <input type="text" id="sf_search_text" placeholder="เช่น บจก, จำกัด" style="flex:1; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
                <input type="text" id="sf_replace_val" placeholder="เติมค่า... เช่น นิติบุคคล" style="flex:1; padding: 6px; border-radius: 4px; border: 1px solid #ccc;">
            </div>
        </div>
    </div>`;

    html += `<div id="sf_rules_list"> ${renderSfRulesList()} </div>`;

    const container = document.getElementById('Form-TabSet-trans-3');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block';
    }
}

function toggleSfValueInput(sel) {
    const container = document.getElementById('sf_fixed_val_container');
    container.style.display = (sel.value === 'fixed') ? 'block' : 'none';
}
function addSmartFillRule() {
    const target = document.getElementById('sf_target_col').value;
    const ref = document.getElementById('sf_ref_col').value;
    const method = document.getElementById('sf_method').value;
    const search = document.getElementById('sf_search_text').value.trim();
    const replace = document.getElementById('sf_replace_val').value.trim();

    const rule = {
        id: Date.now(),
        targetCol: target,
        refCol: ref,
        method: method,
        searchTerms: search.split(',').map(s => s.trim().toLowerCase()),
        replaceVal: replace
    };

    activeConfigObj.smartFillRules.push(rule);
    saveAndRefreshSF();
}

function renderSfRulesList() {
    let rules = activeConfigObj.smartFillRules || [];
    if (rules.length === 0) return '<p style="text-align: center; color: #999; font-style: italic; font-size: 13px;">ยังไม่มีกฎการเติมข้อมูลอัจฉริยะ</p>';

    let html = '<table style="width: 100%; border-collapse: collapse; font-size: 12px; background: white; border: 1px solid #ddd;">';
    html += '<thead style="background: #f8f9fa;"><tr><th style="padding:8px; border-bottom:1px solid #ddd; text-align:left;">กฎการเติมข้อมูล</th><th style="padding:8px; border-bottom:1px solid #ddd;">จัดการ</th></tr></thead><tbody>';

    rules.forEach(r => {
        let desc = "";
        if (r.method === 'fixed') {
            desc = `ถ้า <b>${r.targetCol}</b> ว่าง และ <b>${r.refCol}</b> มีคำว่า [${r.searchTerms.join(', ')}] → เติม <b>"${r.replaceVal}"</b>`;
        } else if (r.method === 'most_frequent') {
            desc = `ถ้า <b>${r.targetCol}</b> ว่าง → เติมด้วย <b>ค่าที่พบบ่อยที่สุด</b> โดยอิงจากกลุ่ม <b>${r.refCol}</b>`;
        } else {
            desc = `ถ้า <b>${r.targetCol}</b> ว่าง → เติมด้วย <b>ค่าเฉลี่ยตัวเลข</b> ตามกลุ่ม <b>${r.refCol}</b>`;
        }

        html += `<tr>
            <td style="padding:8px; border-bottom:1px solid #eee;">${desc}</td>
            <td style="padding:8px; border-bottom:1px solid #eee; text-align:center;">
                <button onclick="removeSfRule(${r.id})" style="color:red; background:none; border:none; cursor:pointer;">🗑️</button>
            </td>
        </tr>`;
    });
    html += '</tbody></table>';
    return html;
}


// ฟังก์ชันสำหรับลบกฎการเติมค่าว่าง (ลบตาม ID ของกฎ)
function removeSfRule(ruleId) {
    if (!confirm("คุณต้องการลบกฎการเติมค่านี้ใช่หรือไม่?")) return;

    // กรองเอาเฉพาะกฎที่ ID ไม่ตรงกับที่ส่งมา (คือการลบทิ้งนั่นเอง)
    activeConfigObj.smartFillRules = activeConfigObj.smartFillRules.filter(r => r.id !== ruleId);

    // บันทึกและรีเฟรชหน้าจอ
    saveAndRefreshSF();
}
// ฟังก์ชันบันทึกข้อมูลและอัปเดต UI (ใช้ร่วมกับตอนเพิ่มและลบ)
function saveAndRefreshSF() {
    // 1. เซฟลง LocalStorage
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    
    // 2. วาดรายการกฎใหม่ในตารางด้านล่าง
    document.getElementById('sf_rules_list').innerHTML = renderSfRulesList();
    
    // 3. รีเฟรชตารางพรีวิวหลักเพื่อให้ค่าที่เติมไว้หายไปหรือเปลี่ยนตามกฎใหม่
    if (typeof refreshPreviewTable === "function") {
        refreshPreviewTable();
    }
    
    console.log("อัปเดตกฎ Smart Fill เรียบร้อยแล้ว");
}



// ตัวอย่าง Logic ที่ต้องใส่ในลูปประมวลผลข้อมูล
function applySmartFill(currentRow, allRows, columns) {
    if (!activeConfigObj.smartFillRules) return;

    activeConfigObj.smartFillRules.forEach(rule => {
        const targetIdx = columns.indexOf(rule.targetCol);
        const refIdx = columns.indexOf(rule.refCol);
        
        let targetVal = getCleanValue(currentRow[targetIdx]);
        let refVal = getCleanValue(currentRow[refIdx]).toLowerCase();

        // ถ้าช่องเป้าหมายว่าง (NULL หรือ "")
        if (targetVal === "NULL" || targetVal === "") {
            
            // แบบที่ 1: เติมค่าคงที่ตามเงื่อนไข (เช่น บจก -> นิติบุคคล)
            if (rule.method === 'fixed') {
                const match = rule.searchTerms.some(term => refVal.includes(term));
                if (match) {
                    currentRow[targetIdx] = `'${rule.replaceVal}'`; // เติมค่า
                }
            }
            
            // แบบที่ 2: เติมด้วยค่าฐานนิยม (Mode) - ค่าที่พบบ่อยในกลุ่มเดียวกัน
            else if (rule.method === 'most_frequent') {
                const similarRows = allRows.filter(r => getCleanValue(r[refIdx]).toLowerCase() === refVal);
                const counts = {};
                similarRows.forEach(r => {
                    const v = getCleanValue(r[targetIdx]);
                    if (v !== "NULL" && v !== "") counts[v] = (counts[v] || 0) + 1;
                });
                const mostFrequent = Object.keys(counts).reduce((a, b) => counts[a] > counts[b] ? a : b, null);
                if (mostFrequent) currentRow[targetIdx] = `'${mostFrequent}'`;
            }
        }
    });
}




function buildRowCleanerUI() {
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;
    if (!activeConfigObj.rowCleanRules) activeConfigObj.rowCleanRules = [];

    let html = '<h4 style="margin-bottom: 10px; color: #c0392b; border-bottom: 2px solid #c0392b; padding-bottom: 8px; font-weight: bold;">🗑️ กรองข้อมูลขยะ (Row Cleaner)</h4>';
    html += '<p style="font-size: 13px; color: #666; margin-bottom: 15px;">ตั้งค่าเงื่อนไขเพื่อ "ลบแถว" ออกจากไฟล์โดยอัตโนมัติ (เช่น ถ้า ID และ Name ว่างทั้งคู่ ไม่ต้องนำเข้า)</p>';

    // --- ส่วนเลือกหลายคอลัมน์เพื่อสร้างกฎ ---
    html += `
    <div style="background: #fdf2f2; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #f5c6cb;">
        <p style="font-size: 12px; font-weight: bold; margin-bottom: 8px;">เลือกคอลัมน์ที่ต้องตรวจสอบ (เงื่อนไข AND - ว่างพร้อมกันถึงจะลบ):</p>
        <div style="display: flex; flex-wrap: wrap; gap: 10px; margin-bottom: 15px; background: white; padding: 10px; border-radius: 4px; border: 1px solid #ddd; max-height: 150px; overflow-y: auto;">
            ${activeConfigObj.mappedColumns.map(col => `
                <label style="font-size: 12px; display: flex; align-items: center; gap: 5px; background: #f8f9fa; padding: 4px 8px; border-radius: 3px; cursor: pointer;">
                    <input type="checkbox" class="rc_col_check" value="${col.source}"> ${col.source}
                </label>
            `).join('')}
        </div>
        <div style="text-align: right;">
            <button onclick="addRowCleanRule()" style="background: #c0392b; color: white; border: none; padding: 8px 15px; border-radius: 4px; cursor: pointer; font-weight: bold;">+ เพิ่มกฎการลบแถว</button>
        </div>
    </div>`;

    html += `<div id="rc_rules_list"> ${renderRcRulesList()} </div>`;

    const container = document.getElementById('Form-TabSet-trans-4');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block';
    }
}

function renderRcRulesList() {
    let rules = activeConfigObj.rowCleanRules || [];
    if (rules.length === 0) return '<p style="text-align: center; color: #999; font-style: italic; padding: 20px;">ยังไม่มีกฎการลบแถว</p>';

    let html = '<table style="width: 100%; border-collapse: collapse; font-size: 12px; background: white; border: 1px solid #ddd;">';
    html += '<thead style="background: #f8f9fa;"><tr><th style="padding:8px; border-bottom:1px solid #ddd; text-align:left;">เงื่อนไขการลบทิ้ง (ถ้าคอลัมน์เหล่านี้ว่างพร้อมกัน)</th><th style="padding:8px; border-bottom:1px solid #ddd; text-align:center;">จัดการ</th></tr></thead><tbody>';

    rules.forEach((rule, index) => {
        const colList = rule.columns.map(c => `<span style="background:#f2dede; color:#a94442; padding:2px 5px; border-radius:3px; margin:0 2px;">${c}</span>`).join(' และ ');
        html += `<tr>
            <td style="padding:10px; border-bottom:1px solid #eee;">ลบแถวทิ้งถ้า: ${colList} <b>ว่างทั้งหมด</b></td>
            <td style="padding:10px; border-bottom:1px solid #eee; text-align:center;">
                <button onclick="removeRcRule(${index})" style="background:none; border:none; color:#c0392b; cursor:pointer; font-size:16px;">&times;</button>
            </td>
        </tr>`;
    });
    html += '</tbody></table>';
    return html;
}


function addRowCleanRule() {
    const selectedCols = Array.from(document.querySelectorAll('.rc_col_check:checked')).map(cb => cb.value);
    
    if (selectedCols.length === 0) {
        alert("กรุณาเลือกอย่างน้อย 1 คอลัมน์ครับ");
        return;
    }

    activeConfigObj.rowCleanRules.push({
        columns: selectedCols
    });

    saveAndRefreshRC();
}

function removeRcRule(index) {
    activeConfigObj.rowCleanRules.splice(index, 1);
    saveAndRefreshRC();
}

function saveAndRefreshRC() {
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    document.getElementById('rc_rules_list').innerHTML = renderRcRulesList();
    if (typeof refreshPreviewTable === "function") refreshPreviewTable();
}


function resetGlobalStates() {
    // 1. ล้างตัวแปรเก็บข้อมูล
    window.currentPreviewPayload = [];
    window.globalFinalPayloadToImport = null;
    window.globalDuplicateGroups = [];
    window.globalUniqueRows = [];
    
 

    console.log("🧹 ล้างข้อมูลเก่าออกจากระบบเรียบร้อยแล้ว");
}



function buildAdvancedTransformUI() {
  //  if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

    let html = '<h4 style="margin-bottom: 15px; color: #2c3e50; border-bottom: 2px solid #2c3e50; padding-bottom: 8px; font-weight: bold;">🛠️ การจัดการโครงสร้างข้อมูลระดับสูง</h4>';

    // --- ส่วนที่ 1: แยกคอลัมน์ (Split) ---
    html += `
    <div style="background: #fff4e5; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #ffcc80;">
        <p style="font-weight: bold; color: #e67e22; margin-bottom: 10px;">✂️ 1. แยกคอลัมน์ (Column Splitter)</p>
        <div style="display: grid; grid-template-columns: 1fr 1fr 2fr auto; gap: 10px; align-items: flex-end;">
            <div>
                <label style="font-size: 11px; display: block;">คอลัมน์ต้นทาง</label>
                <select id="split_source" style="width: 100%; padding: 5px;">
                    ${activeConfigObj.mappedColumns.map(c => `<option value="${c.source}">${c.source}</option>`).join('')}
                </select>
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ตัวคั่น (เช่น ช่องว่าง หรือ ,)</label>
                <input type="text" id="split_delimiter" placeholder="Space" style="width: 100%; padding: 5px;">
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ชื่อคอลัมน์ใหม่ (คั่นด้วยคอมม่า)</label>
                <input type="text" id="split_targets" placeholder="ตำบล,อำเภอ,จังหวัด" style="width: 100%; padding: 5px;">
            </div>
            <button onclick="addSplitRule()" style="background: #e67e22; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer;">เพิ่มกฎ</button>
        </div>
    </div>`;

    // --- ส่วนที่ 2: รวมคอลัมน์ (Merge) ---
    html += `
    <div style="background: #e8f4fd; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #bbdefb;">
        <p style="font-weight: bold; color: #3498db; margin-bottom: 10px;">🔗 2. รวมคอลัมน์ (Column Merger)</p>
        <div style="display: grid; grid-template-columns: 2fr 1fr 1fr auto; gap: 10px; align-items: flex-end;">
            <div>
                <label style="font-size: 11px; display: block;">เลือกคอลัมน์ที่จะรวม (คั่นด้วย ,)</label>
                <input type="text" id="merge_sources" placeholder="fname,lname" style="width: 100%; padding: 5px;">
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ตัวเชื่อม</label>
                <input type="text" id="merge_connector" placeholder="ช่องว่าง" style="width: 100%; padding: 5px;">
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ชื่อคอลัมน์ใหม่</label>
                <input type="text" id="merge_target" placeholder="full_name" style="width: 100%; padding: 5px;">
            </div>
            <button onclick="addMergeRule()" style="background: #3498db; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer;">เพิ่มกฎ</button>
        </div>
    </div>`;

    // --- ส่วนที่ 3: จัดรูปแบบ (Format) ---
    html += `
    <div style="background: #eafaf1; padding: 15px; border-radius: 8px; margin-bottom: 20px; border: 1px solid #abebc6;">
        <p style="font-weight: bold; color: #27ae60; margin-bottom: 10px;">📅 3. จัดรูปแบบ (Data Formatter)</p>
        <div style="display: grid; grid-template-columns: 1fr 1fr 1fr auto; gap: 10px; align-items: flex-end;">
            <div>
                <label style="font-size: 11px; display: block;">คอลัมน์</label>
                <select id="fmt_col" style="width: 100%; padding: 5px;">
                    ${activeConfigObj.mappedColumns.map(c => `<option value="${c.source}">${c.source}</option>`).join('')}
                </select>
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ประเภท</label>
                <select id="fmt_type" style="width: 100%; padding: 5px;">
                 <option value="phone">หมายเลขโทรศัพท์</option>
                   <option value="numberonly">ตัวเลข</option>
                 
                   <option value="eng">ภาษาอังกฤษเท่านั้น</option>
                     <option value="spam">ห้าม spam</option>
                    <option value="number">ตัวเลข (ทศนิยม+Comma)</option>
                         <option value="ID">ดึง ID</option>
                    <option value="date">วันที่ (DD/MM/YYYY)</option>
                </select>
            </div>
            <div>
                <label style="font-size: 11px; display: block;">ตั้งค่า (เช่น ทศนิยมกี่หลัก)</label>
                <input type="number" id="fmt_val" value="2" style="width: 100%; padding: 5px;">
            </div>
            <button onclick="addFormatRule()" style="background: #27ae60; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer;">เพิ่มกฎ</button>
        </div>
    </div>`;

    html += `<div id="adv_rules_list"> ${renderAdvRules()} </div>`;

    const container = document.getElementById('Form-TabSet-trans-5');
    if (container) {
        container.innerHTML = html;
        container.style.display = 'block';
    }
}

function renderAdvRules() {
    let html = '<div style="margin-top: 20px;">';
    
    // --- สรุปกฎการแยก (Split) ---
    if (activeConfigObj.splitRules.length > 0) {
        html += '<p style="font-size: 13px; font-weight: bold; color: #e67e22;">📋 รายการกฎการแยกคอลัมน์:</p>';
        activeConfigObj.splitRules.forEach((r, idx) => {
            html += `<div style="display: flex; justify-content: space-between; background: #fff; border: 1px solid #ffcc80; padding: 8px; margin-bottom: 5px; border-radius: 4px; font-size: 12px;">
                <span>✂️ แยก <b>${r.source}</b> ด้วย "${r.delimiter === " " ? "Space" : r.delimiter}" เป็น [${r.targets.join(', ')}]</span>
                <button onclick="removeAdvRule('split', ${idx})" style="color: red; border: none; background: none; cursor: pointer;">🗑️</button>
            </div>`;
        });
    }

    // --- สรุปกฎการจัดรูปแบบ (Format) ---
    if (activeConfigObj.formatRules.length > 0) {
        html += '<p style="font-size: 13px; font-weight: bold; color: #27ae60; margin-top: 15px;">📋 รายการกฎการจัดรูปแบบ:</p>';
        activeConfigObj.formatRules.forEach((r, idx) => {
            html += `<div style="display: flex; justify-content: space-between; background: #fff; border: 1px solid #abebc6; padding: 8px; margin-bottom: 5px; border-radius: 4px; font-size: 12px;">
                <span>📅 จัดรูป <b>${r.column}</b> เป็น ${r.type} (ค่า: ${r.val})</span>
                <button onclick="removeAdvRule('format', ${idx})" style="color: red; border: none; background: none; cursor: pointer;">🗑️</button>
            </div>`;
        });
    }

    // --- สรุปกฎการรวม (Merge) ---
    if (activeConfigObj.mergeRules.length > 0) {
        html += '<p style="font-size: 13px; font-weight: bold; color: #3498db; margin-top: 15px;">📋 รายการกฎการรวมคอลัมน์:</p>';
        activeConfigObj.mergeRules.forEach((r, idx) => {
            html += `<div style="display: flex; justify-content: space-between; background: #fff; border: 1px solid #bbdefb; padding: 8px; margin-bottom: 5px; border-radius: 4px; font-size: 12px;">
                <span>🔗 รวม [${r.sources.join(' + ')}] เป็น <b>${r.targetName}</b></span>
                <button onclick="removeAdvRule('merge', ${idx})" style="color: red; border: none; background: none; cursor: pointer;">🗑️</button>
            </div>`;
        });
    }

    html += '</div>';
    return (activeConfigObj.splitRules.length === 0 && activeConfigObj.mergeRules.length === 0 && activeConfigObj.formatRules.length === 0) 
        ? '<p style="text-align: center; color: #999; font-style: italic; padding: 20px;">ยังไม่มีกฎการจัดการระดับสูง</p>' 
        : html;
}


// ฟังก์ชันลบกฎ
function removeAdvRule(type, index) {
    if (type === 'split') activeConfigObj.splitRules.splice(index, 1);
    if (type === 'merge') activeConfigObj.mergeRules.splice(index, 1);
    if (type === 'format') activeConfigObj.formatRules.splice(index, 1);
    saveAndRefresh();
}

// ฟังก์ชันบันทึกและรีเฟรชหน้าจอ
function saveAndRefresh() {
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    // รีเฟรชเฉพาะ UI ของ Tab 5
    document.getElementById('adv_rules_list').innerHTML = renderAdvRules();
    // รีเฟรชตารางพรีวิวหลัก
    if (typeof refreshPreviewTable === "function") refreshPreviewTable();
}

// ฟังก์ชันเพิ่มกฎ Split
function addSplitRule() {
    const source = document.getElementById('split_source').value;
    const delimiter = document.getElementById('split_delimiter').value; // ถ้าว่างจะเป็น Space
    const targets = document.getElementById('split_targets').value.split(',').map(s => s.trim());

    if (!targets[0]) { alert("กรุณาระบุชื่อคอลัมน์ใหม่"); return; }

    activeConfigObj.splitRules.push({
        source: source,
        delimiter: delimiter === "" ? " " : delimiter,
        targets: targets
    });
    saveAndRefresh();
}

// ฟังก์ชันเพิ่มกฎ Merge
function addMergeRule() {
    const sources = document.getElementById('merge_sources').value.split(',').map(s => s.trim());
    const connector = document.getElementById('merge_connector').value;
    const targetName = document.getElementById('merge_target').value;

    if (!targetName) { alert("กรุณาระบุชื่อคอลัมน์ใหม่ที่รวมแล้ว"); return; }

    activeConfigObj.mergeRules.push({
        sources: sources,
        connector: connector === "" ? " " : connector,
        targetName: targetName
    });
    saveAndRefresh();
}

// ฟังก์ชันเพิ่มกฎ Format
function addFormatRule() {
    const col = document.getElementById('fmt_col').value;
    const type = document.getElementById('fmt_type').value;
    const val = document.getElementById('fmt_val').value;

    activeConfigObj.formatRules.push({
        column: col,
        type: type,
        val: val
    });
    saveAndRefresh();
}

function exportToCsvFile() {
    let sourceData = window.globalFinalPayloadToImport || window.currentPreviewPayload;
    let originalCols = window.globalOriginalColumns;

    if (!sourceData || sourceData.length === 0 || !originalCols) {
        alert("⚠️ ข้อมูลไม่พร้อมสำหรับนำออก");
        return;
    }



// ==========================================
    // 🌟 พระเอกออกโรง: เตะแถวที่ไม่ต้องการทิ้ง (Exclude Rows)
    // ==========================================
    if (activeConfigObj.ignoreRowRules && activeConfigObj.ignoreRowRules.length > 0) {
        let originalCount = sourceData.length;
        
        sourceData = sourceData.filter(row => {
            let shouldSkipRow = false;
            
            for (let rule of activeConfigObj.ignoreRowRules) {
                if (rule.column && rule.value !== "") {
                    // หาค่าของคอลัมน์นั้นๆ
                    let cellValue = row[rule.column];
                    
                    // (Fallback) ถ้าหาด้วยชื่อไม่ได้ ลองหาด้วย Index
                    if (cellValue === undefined) {
                        let idx = originalCols.indexOf(rule.column);
                        if (idx !== -1) cellValue = row[idx];
                    }

                    if (cellValue !== undefined && cellValue !== null) {
                        let cleanCellVal = cellValue.toString().replace(/^["']|["']$/g, '').trim();
                        let ruleVal = rule.value.toString().trim();
                        
                        // ถ้าค่าตรงกันเป๊ะ หรือเช็กว่ามีค่าเป็น NULL -> เตะทิ้ง
                        if (cleanCellVal === ruleVal || (ruleVal.toUpperCase() === "NULL" && cleanCellVal.toUpperCase() === "NULL")) {
                            shouldSkipRow = true;
                            break; 
                        }
                    }
                }
            }
            return !shouldSkipRow; // return false จะทำให้บรรทัดนี้ถูกลบออกจาก Array
        });

        console.log(`🗑️ CSV: โดนตัดทิ้งไป: ${originalCount - sourceData.length} แถว`);
    }
    // ==========================================
    // 1. รวบรวม Header ทั้งหมด (Original + Generated)
    // ==========================================
    let allHeaders = [...originalCols]; // เริ่มต้นด้วย Header เดิม

    // เพิ่ม Header จาก Split Rules
    if (activeConfigObj.splitRules) {
        activeConfigObj.splitRules.forEach(rule => {
            rule.targets.forEach(targetName => {
                if (!allHeaders.includes(targetName)) {
                    allHeaders.push(targetName);
                }
            });
        });
    }

    // เพิ่ม Header จาก Merge Rules
    if (activeConfigObj.mergeRules) {
        activeConfigObj.mergeRules.forEach(rule => {
            if (!allHeaders.includes(rule.targetName)) {
                allHeaders.push(rule.targetName);
            }
        });
    }

    // เพิ่ม Header พิเศษ (ถ้ามีการ Merge ข้อมูลซ้ำ)
    let isMerged = (window.globalFinalPayloadToImport && sourceData === window.globalFinalPayloadToImport);
    if (isMerged && !allHeaders.includes("merged_duplicate_ids")) {
        allHeaders.push("merged_duplicate_ids");
    }

    // ==========================================
    // 2. แปลงชื่อ Header ตาม Mapping (ถ้ามี)
    // ==========================================
    let finalHeaderNames = allHeaders.map(colName => {
        // ถ้าอยู่ใน Ignore List ให้ข้าม (แต่เราจะข้ามตอนเขียนข้อมูลแทน เพื่อรักษ index ไว้ก่อน)
        if (activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(colName)) {
            return null; // Mark ไว้ว่าไม่เอา
        }

        let mapped = activeConfigObj.mappedColumns ? activeConfigObj.mappedColumns.find(c => c.source === colName) : null;
        return (mapped && mapped.target && mapped.target.trim() !== "") ? mapped.target : colName;
    }).filter(h => h !== null); // เอาเฉพาะที่ไม่ใช่ null

    // สร้าง Map เพื่อจำว่า Header ไหน เอามาจาก Key อะไร
    let validKeys = allHeaders.filter(colName => {
        return !(activeConfigObj.ignoredColumns && activeConfigObj.ignoredColumns.includes(colName));
    });


    // ==========================================
    // 3. สร้างเนื้อหา CSV (บรรทัดต่อบรรทัด)
    // ==========================================
    let csvRows = [];
    
    // 3.1 ใส่ BOM และ Header Row
    csvRows.push("\uFEFF" + finalHeaderNames.map(name => `"${name.replace(/"/g, '""')}"`).join(","));

    // 3.2 ใส่ Data Rows
    sourceData.forEach(row => {
        let rowValues = validKeys.map(key => {
            let val = "";

            // 🌟 กรณีที่ row เป็น Array (Index Based) และ key คือชื่อ Original Column
            let originalIndex = originalCols.indexOf(key);
            
            // 🌟 กรณีที่ row เป็น Object (Key Based) หรือมี property เพิ่มเข้ามาทีหลัง
            if (row[key] !== undefined) {
                val = row[key];
            } else if (originalIndex !== -1 && row[originalIndex] !== undefined) {
                // ถ้าหา Key ไม่เจอ ให้ลองไปหาจาก Index เดิม
                val = row[originalIndex];
            }

            // 🌟 Cleaning Process (ล้างขยะ)
            if (val === null || val === undefined) val = "";
            let strVal = val.toString();

            // ล้าง HTML Tags และสัญลักษณ์พิเศษที่ไม่อยากได้
            let cleanVal = strVal.replace(/<[^>]*>?/gm, '').replace(/[✨🧩]/g, '').trim();

            // จัดการ NULL และค่าว่าง
            if (cleanVal.toUpperCase() === "NULL" || cleanVal === "") {
                return '""'; // CSV Empty Field
            }

            // ถอด Quote ที่ติดมากับข้อมูลเดิม (เช่น 'Value' หรือ "Value")
            if ((cleanVal.startsWith("'") && cleanVal.endsWith("'")) || (cleanVal.startsWith('"') && cleanVal.endsWith('"'))) {
                cleanVal = cleanVal.substring(1, cleanVal.length - 1);
            }

            // Unescape SQL Characters
            cleanVal = cleanVal.replace(/''/g, "'").replace(/\\'/g, "'");

            // 🌟 Format CSV: หุ้มด้วย "..." และถ้ามี " ข้างใน ให้เบิ้ลเป็น ""
            return `"${cleanVal.replace(/"/g, '""')}"`;
        });

        csvRows.push(rowValues.join(","));
    });

    // ==========================================
    // 4. รวมร่างและดาวน์โหลด
    // ==========================================
    let csvContent = csvRows.join("\n");
    let tableName = activeConfigObj?.tableName || "clean_data";
    
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    
    // สร้างลิงก์ดาวน์โหลดแบบชั่วคราว
    const link = document.createElement("a");
    const url = URL.createObjectURL(blob);
    
    link.href = url;
    link.download = `final_export_${tableName}.csv`;
    link.style.display = 'none';
    
    document.body.appendChild(link);
    link.click();
    
    // Cleanup
    setTimeout(() => {
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
    }, 100);
}



 function handleCsvUpload(event) {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        const content = e.target.result;
        
        // แยกบรรทัด
        const lines = content.split(/\r?\n/).filter(line => line.trim() !== "");
        if (lines.length < 2) {
            alert("ไฟล์ CSV ไม่มีข้อมูลหรือรูปแบบไม่ถูกต้อง");
            return;
        }

        // 1. ดึง Header (บรรทัดแรก)
        const headers = lines[0].split(',').map(h => h.replace(/["']/g, '').trim());
        
        // 2. จำลองรูปแบบ SQL INSERT เพื่อให้ Logic เดิมทำงานต่อได้
        // (เราจะสร้าง SQL ปลอมขึ้นมาในหน่วยความจำเพื่อให้ระบบตัวเดิมประมวลผล)
        let fakeSql = `INSERT INTO \`${file.name.split('.')[0]}\` (\`${headers.join('`, `')}\`) VALUES \n`;
        
      const dataRows = lines.slice(1).map(line => {
            // 🌟 ระบบ ERP: ใช้ลอจิกถอดรหัส CSV ขั้นสูง 
            // แบ่งตามคอมม่าเฉพาะตัวที่อยู่ "ข้างนอก" เครื่องหมายคำพูดเท่านั้น
            const values = [];
            let currentField = "";
            let inQuotes = false;

            for (let i = 0; i < line.length; i++) {
                let char = line[i];

                if (char === '"') {
                    inQuotes = !inQuotes; // สลับสถานะเมื่อเจอเครื่องหมายคำพูด
                } else if (char === ',' && !inQuotes) {
                    // เจอคอมม่า "นอก" อัญประกาศ ให้ปิดฟิลด์ปัจจุบัน
                    values.push(formatSqlValue(currentField));
                    currentField = "";
                } else {
                    currentField += char;
                }
            }
            // ยัดฟิลด์สุดท้ายเข้าแถว
            values.push(formatSqlValue(currentField));

            return `(${values.join(', ')})`;
        });

        fakeSql += dataRows.join(',\n') + ";";

        // 3. ส่งไปให้ฟังก์ชันหลักทำงานต่อเหมือนไฟล์ SQL
        renderSqlPreview(fakeSql, file.name);
        
        console.log("🚀 CSV Import สำเร็จ: แปลงเป็นโครงสร้างระบบเรียบร้อย");
    };

    // รองรับภาษาไทย (TIS-620 หรือ UTF-8)
    reader.readAsText(file, 'UTF-8'); 
        NewCheese();
} 


// ฟังก์ชันช่วยจัดรูปแบบให้เหมือน SQL Value เพื่อส่งต่อให้ renderSqlPreview
function formatSqlValue(val) {
    // 🌟 1. ดักเผื่อเป็น null หรือ undefined จะได้ไม่พัง
    if (val === null || val === undefined) return "NULL";

    // 🌟 2. บังคับแปลงทุกอย่าง (รวมถึงตัวเลข) ให้เป็น String ก่อน แล้วค่อย trim!
    let clean = String(val).trim();
    
    // ถอดอัญประกาศหัวท้ายที่ติดมา (ถ้ามี)
    if (clean.startsWith('"') && clean.endsWith('"')) {
        clean = clean.substring(1, clean.length - 1);
    }
    
    // ถ้าเป็นค่าว่างให้เป็น NULL
    if (clean === "") return "NULL";
    
    // หุ้มด้วย ' ตามสไตล์ SQL (และ Escape ตัว ' ข้างใน)
    return `'${clean.replace(/'/g, "''")}'`;
}

// ฟังก์ชันสำหรับอัปเดต Config ให้ตรงกับไฟล์ปัจจุบันเสมอ
function syncConfigWithCurrentColumns() {
    // ต้องมีคอลัมน์ต้นฉบับปัจจุบัน และต้องมี Config ก่อน
    if (!window.globalOriginalColumns || !activeConfigObj) return;

    let actualCols = window.globalOriginalColumns; // คอลัมน์จากไฟล์ที่เพิ่งอัปโหลด
    let oldMappedCols = activeConfigObj.mappedColumns || [];
    let newMappedCols = [];

    // 1. วนลูปตาม "คอลัมน์จริงในไฟล์ปัจจุบัน" เป็นหลัก
    actualCols.forEach(colName => {
        // หาว่าคอลัมน์นี้เคยถูกตั้งค่าไว้ใน Template ไหม?
        let existingMap = oldMappedCols.find(c => c.source === colName);
        
        if (existingMap) {
            // ถ้าเคยตั้งค่าไว้ -> ดึงค่าเป๊ะๆ ของเดิมมาใช้เลย
            newMappedCols.push(existingMap);
        } else {
            // ถ้าไม่เคยมี (คอลัมน์ใหม่) -> สร้างชุดตั้งค่าใหม่ว่างๆ ให้มัน
            newMappedCols.push({ source: colName, target: "" });
        }
    });

    // 2. อัปเดตกลับเข้าไปใน Config
    activeConfigObj.mappedColumns = newMappedCols;

    // 3. ทำความสะอาดช่อง Ignored (ลบชื่อคอลัมน์ที่ไม่มีในไฟล์แล้วทิ้งไป)
    if (activeConfigObj.ignoredColumns) {
        activeConfigObj.ignoredColumns = activeConfigObj.ignoredColumns.filter(col => actualCols.includes(col));
    }

    // เซฟลง LocalStorage เพื่อให้จำค่าใหม่
    localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
}


function exportConfigToJson() {
    // 1. เช็กก่อนว่ามีการตั้งค่าในระบบหรือยัง
    if (!activeConfigObj) {
        alert("⚠️ ยังไม่มีการตั้งค่าใดๆ ให้ส่งออก");
        return;
    }

    // 2. จำลอง (Clone) Object ออกมา เพื่อไม่ให้แก้ไขตัวจริง
    let exportConfig = JSON.parse(JSON.stringify(activeConfigObj));
    let tableName = exportConfig.tableName || "clean_data";
    
    // 3. สร้างชื่อ Template ใหม่ใส่วันที่ปัจจุบัน
    let newTemplateName = `template_${tableName}_${new Date().toISOString().slice(0,10).replace(/-/g, '')}`;
    exportConfig.fileName = newTemplateName;

    // 4. แปลงข้อมูลกลับเป็น JSON String 
    // (ใช้ JSON.stringify(..., null, 4) เพื่อให้โค้ดเว้นบรรทัดสวยงาม อ่านง่าย)
    const jsonString = JSON.stringify(exportConfig, null, 4);

    // 5. สร้างเป็นไฟล์ Blob เพื่อดาวน์โหลด
    const configBlob = new Blob([jsonString], { type: 'application/json;charset=utf-8;' });
    const configLink = document.createElement("a");
    configLink.href = URL.createObjectURL(configBlob);
    configLink.download = `mapping_rule_${tableName}.json`;
    
    // 6. จำลองการคลิกเพื่อเด้งโหลดไฟล์
    configLink.click();
}


function importConfigFile(event) {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        try {
            const uploadedConfig = JSON.parse(e.target.result);
            
            // 1. เช็กโครงสร้างไฟล์ว่าใช่ไฟล์ Config ของระบบเราไหม
            if(!uploadedConfig.mappedColumns) {
                alert("❌ ไฟล์ไม่ถูกต้อง! กรุณาเลือกไฟล์การตั้งค่า (.json) ที่ส่งออกจากระบบเท่านั้น");
                return;
            }

            // 2. 🌟 ไฮไลท์สำคัญ: "สวมร่าง" ให้ Config ใหม่เข้ากับไฟล์ปัจจุบัน
            // บังคับเปลี่ยนชื่อไฟล์และตารางใน Config ให้เป็นของปัจจุบันที่เรากำลังเปิดอยู่
            if (typeof activeConfigObj !== 'undefined' && activeConfigObj !== null) {
                uploadedConfig.fileName = activeConfigObj.fileName;   // สวมชื่อไฟล์เดิม
                uploadedConfig.tableName = activeConfigObj.tableName; // สวมชื่อตารางเป้าหมายปัจจุบัน
                uploadedConfig.fileType = activeConfigObj.fileType;   // สวมนามสกุลไฟล์
            }
            
            // 3. เซฟลง LocalStorage ใน Key ปัจจุบันที่หน้าเว็บกำลังโฟกัสอยู่
            if (typeof activeStorageKey !== 'undefined' && activeStorageKey) {
                localStorage.setItem(activeStorageKey, JSON.stringify(uploadedConfig));
            } else {
                // เผื่อกรณีหา Key ไม่เจอ
                let fallbackKey = `erp_mapper_${uploadedConfig.fileName}`;
                localStorage.setItem(fallbackKey, JSON.stringify(uploadedConfig));
            }

            alert("✅ นำเข้าการตั้งค่าสำเร็จ! ระบบกำลังปรับใช้การตั้งค่ากับไฟล์ปัจจุบัน...");
           // ... (โค้ดก่อนหน้านี้เหมือนเดิม) ...

            // เซฟลง LocalStorage
            if (typeof activeStorageKey !== 'undefined' && activeStorageKey) {
                localStorage.setItem(activeStorageKey, JSON.stringify(uploadedConfig));
            } else {
                let fallbackKey = `erp_mapper_${uploadedConfig.fileName}`;
                localStorage.setItem(fallbackKey, JSON.stringify(uploadedConfig));
            }

            // 🌟 วิธีใหม่: ดึงค่ากลับมาใส่ตัวแปร Global ทันที
            activeConfigObj = uploadedConfig;

      
            
            // 🌟 ปิดการ Refresh หน้าจอ แล้วสั่งให้มันวาด UI ใหม่แทน
            // location.reload(); <-- ลบ/คอมเมนต์บรรทัดนี้ทิ้งไปเลย
            
            // เรียกฟังก์ชันวาดตารางและตั้งค่าใหม่ (ชื่อฟังก์ชันอาจต้องปรับตามที่คุณตั้งไว้ในระบบจริง)
            if (typeof buildMappingUI === 'function') buildMappingUI();
            if (typeof buildIgnoreColumnsUI === 'function') buildIgnoreColumnsUI();
            if (typeof buildDataRulesUI === 'function') buildDataRulesUI();
            
            // (อย่าลืมเรียกฟังก์ชันอื่นๆ ที่คุณใช้สร้างหน้าจอด้วยนะครับ)
            
        } catch (error) {
            console.error(error);
            alert("❌ เกิดข้อผิดพลาด: ไฟล์ที่อัปโหลดไม่ใช่รูปแบบ JSON ที่ถูกต้อง");
        }
            
       
    };
    reader.readAsText(file);
    
    // เคลียร์ค่า input เผื่อผู้ใช้กดอัปโหลดไฟล์เดิมซ้ำอีกรอบ จะได้ทำงานได้ปกติ
    event.target.value = ""; 

}



function buildIgnoreRowsUI() {
    if (!activeConfigObj || !activeConfigObj.mappedColumns) return;

    // เตรียม Array สำหรับเก็บเงื่อนไขการตัดแถวทิ้ง
    if (!activeConfigObj.ignoreRowRules) {
        activeConfigObj.ignoreRowRules = [];
    }

    let html = '<h4 style="margin-top: 20px; margin-bottom: 10px; color: #c9302c; border-bottom: 2px solid #c9302c; padding-bottom: 8px; font-weight: bold;">🚫 ตั้งค่าตัดแถวทิ้ง (Exclude Rows)</h4>';
    html += '<p style="font-size: 13px; color: #666; margin-bottom: 15px;">แถวข้อมูลไหนที่มีค่าตรงตามเงื่อนไขด้านล่าง จะถูก "ตัดทิ้งทั้งบรรทัด" และไม่ถูกนำเข้าระบบ</p>';

    // คอนเทนเนอร์สำหรับใส่รายการเงื่อนไข
    html += `<div id="ignoreRowRulesContainer" style="margin-bottom: 15px;"></div>`;
    
    // ปุ่มเพิ่มเงื่อนไข
    html += `<button type="button" onclick="addIgnoreRowRule()" style="background: #5bc0de; color: white; border: none; padding: 6px 12px; border-radius: 4px; cursor: pointer; font-size: 13px;">➕ เพิ่มเงื่อนไขตัดแถวทิ้ง</button>`;

    const container = document.getElementById('Form-TabSet-report-4') || document.getElementById('Form-TabSet-report-3'); 
    if (container) {
        // 🌟 วิธีแก้: เช็กก่อนว่ามีกล่องของ Exclude Rows สร้างไว้หรือยัง
        let wrapper = document.getElementById('ignoreRowsWrapper');
        
        if (!wrapper) {
            // ถ้ายังไม่มี ให้สร้าง div หุ้มขึ้นมาใหม่ แล้วเอาไปใส่ใน container
            wrapper = document.createElement('div');
            wrapper.id = 'ignoreRowsWrapper';
            container.appendChild(wrapper); 
        }
        
        // ยัด HTML ใส่ในกล่อง wrapper (ใช้ = เพื่อเขียนทับของเดิมในกล่อง ไม่เบิ้ลแล้ว!)
        wrapper.innerHTML = html;
        container.style.display = 'block'; 
        
        // วาดรายการเงื่อนไขที่มีอยู่แล้ว
        renderIgnoreRowRulesList();
    }
}

// 🌟 2. ฟังก์ชันวาดรายการเงื่อนไข
function renderIgnoreRowRulesList() {
    const container = document.getElementById('ignoreRowRulesContainer');
    if (!container) return;

    let html = '';
    
    // ดึงชื่อคอลัมน์ต้นทางทั้งหมดมาทำ Dropdown
    let colOptions = '<option value="">-- เลือกคอลัมน์ --</option>';
    activeConfigObj.mappedColumns.forEach(col => {
        colOptions += `<option value="${col.source}">${col.source}</option>`;
    });

    activeConfigObj.ignoreRowRules.forEach((rule, index) => {
        // สร้าง Dropdown ที่มีการ Select ค่าเดิมไว้
        let currentOptions = colOptions.replace(`value="${rule.column}"`, `value="${rule.column}" selected`);

        html += `
        <div style="display: flex; gap: 10px; margin-bottom: 10px; align-items: center; background: #fff5f5; padding: 10px; border: 1px solid #f2dede; border-radius: 4px;">
            <span style="font-size: 13px; color: #c9302c; font-weight: bold;">ถ้าคอลัมน์</span>
            
            <select onchange="updateIgnoreRowRule(${index}, 'column', this.value)" style="padding: 5px; border: 1px solid #ccc; border-radius: 3px; min-width: 150px;">
                ${currentOptions}
            </select>
            
            <span style="font-size: 13px;">มีค่าเท่ากับ</span>
            
            <input type="text" value="${rule.value}" onchange="updateIgnoreRowRule(${index}, 'value', this.value)" placeholder="เช่น 0, NULL, ว่าง" style="padding: 5px; border: 1px solid #ccc; border-radius: 3px; flex-grow: 1;">
            
            <button type="button" onclick="deleteIgnoreRowRule(${index})" style="background: #d9534f; color: white; border: none; padding: 5px 10px; border-radius: 3px; cursor: pointer;" title="ลบเงื่อนไขนี้">🗑️</button>
        </div>`;
    });

    if (activeConfigObj.ignoreRowRules.length === 0) {
        html = `<div style="font-size: 13px; color: #999; font-style: italic; padding: 10px; background: #fafafa; border: 1px dashed #ccc; text-align: center;">ยังไม่มีการตั้งเงื่อนไขตัดแถวทิ้ง</div>`;
    }

    container.innerHTML = html;
}

// 🌟 3. ฟังก์ชันเพิ่ม/แก้ไข/ลบเงื่อนไข (แล้วเซฟลง LocalStorage)
function addIgnoreRowRule() {
    activeConfigObj.ignoreRowRules.push({ column: '', value: '' });
    saveCurrentConfig(); // ฟังก์ชันเซฟของคุณ (ถ้ามี) หรือใช้ localStorage.setItem
    renderIgnoreRowRulesList();
}

function updateIgnoreRowRule(index, field, val) {
    activeConfigObj.ignoreRowRules[index][field] = val;
    saveCurrentConfig();
}

function deleteIgnoreRowRule(index) {
    activeConfigObj.ignoreRowRules.splice(index, 1);
    saveCurrentConfig();
    renderIgnoreRowRulesList();
}

// ฟังก์ชันช่วยเซฟ (ถ้าคุณมีฟังก์ชันเซฟหลักอยู่แล้ว สามารถเรียกใช้แทนได้เลย)
function saveCurrentConfig() {
    if (typeof activeStorageKey !== 'undefined' && activeStorageKey) {
        localStorage.setItem(activeStorageKey, JSON.stringify(activeConfigObj));
    }
    
    
    
}



// ==========================================
// ฟังก์ชันเลือกแถวอัตโนมัติตามจำนวนที่ระบุ
// ==========================================
function autoSelectRows(amount) {
	

    // 1. แปลงค่าที่รับมาเป็นตัวเลข (ถ้าว่างให้เป็น 0)
    let limit = parseInt(amount);
    if (isNaN(limit)) limit = 0;

    // 2. ดึง Checkbox รายการทั้งหมดออกมา
    const allCheckboxes = document.querySelectorAll('.chkSyncItem');
    const totalItems = allCheckboxes.length;

    // กันไม่ให้กรอกเกินจำนวนที่มีจริง
    if (limit > totalItems) limit = totalItems;
    if (limit < 0) limit = 0;

    // 3. วนลูป Checkbox ทุกตัว
    allCheckboxes.forEach((chk, index) => {
        // ถ้าลำดับ (index) น้อยกว่าจำนวนที่ระบุ -> ให้ติ๊กถูก
        // ถ้าเกิน -> ให้เอาติ๊กออก
        // (index เริ่มที่ 0 ดังนั้นถ้ากรอก 5: index 0,1,2,3,4 จะเป็น true)
        if (index < limit) {
            chk.checked = true;
        } else {
            chk.checked = false;
        }
    });

    // 4. อัปเดตสถานะปุ่ม Select All (เผื่อกรอกเลขเท่าจำนวนทั้งหมด)
    const chkSelectAll = document.getElementById("chkSelectAllSync");
    if (chkSelectAll) {
        chkSelectAll.checked = (limit === totalItems && totalItems > 0);
    }

    // 5. *** สำคัญ *** เรียกฟังก์ชันคำนวณ data ใหม่ 
    // เพื่อให้อัปเดต JSON และตัวแปรที่เกี่ยวข้องทันที
    updateSyncBackOutput();
}














// ==========================================
// 2. ฟังก์ชันโชว์ข้อมูลลงตาราง (ดึงจาก State เสมอ)
// ==========================================
function renderFilterChain() {
	

	
	
    const tbody = document.getElementById("filterChainBody");
    if (!tbody) return; // ถ้าหาตารางไม่เจอให้ข้ามไป
    
    tbody.innerHTML = ""; // เคลียร์ตารางเดิมทิ้ง
    
    const dataList = currentCheeseData.FilterChain || [];

    dataList.forEach((row, index) => {
        let checkboxHtml = `<input type="checkbox" name="FilterChain" value="${row.id}">`;

        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td style="text-align: center;">${checkboxHtml}</td>
            <td class="truncate" title="${row.type}">${row.type}</td>
            <td>${row.groups}</td>
            <td class="truncate">${row.readableText}</td>
            <td>  
                <button type="button" class="foredit" onclick="openwindow('draggable-window6','Filter Criteria','9500'); getData('/addquery-filemanager/${row.tables}/${row.groups}', 'mainbody6');">                        
                    <svg class="w-[34px] h-[34px] text-gray-800 dark:text-white" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.4" stroke-linecap="round" stroke-linejoin="round">
                        <path d="m14.304 4.844 2.852 2.852M7 7H4a1 1 0 0 0-1 1v10a1 1 0 0 0 1 1h11a1 1 0 0 0 1-1v-4.5m2.409-9.91a2.017 2.017 0 0 1 0 2.853l-6.844 6.844L8 14l.713-3.565 6.844-6.844a2.015 2.015 0 0 1 2.852 0Z"/>
                    </svg>
                </button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}


// ฟังก์ชันลบตัวกรอง (ลบสืบทอดถ้าลบแถวบนสุด)
// ==========================================
function deleteSelectedFilters() {
    // 1. หา Checkbox ที่ถูกติ๊กทั้งหมด
    const checkboxes = document.querySelectorAll('input[name="FilterChain"]:checked');
    
    if (checkboxes.length === 0) {
        alert("กรุณาเลือกรายการที่ต้องการลบครับ");
        return;
    }

    // 2. ดึงค่า ID ที่ต้องการลบมาใส่ใน Array (แปลงเป็นตัวเลข)
    const idsToDelete = Array.from(checkboxes).map(cb => parseInt(cb.value));

    // 3. เช็คว่า "แถวบนสุด" (Index 0) ถูกติ๊กลบด้วยหรือไม่?
    const topRowId = currentCheeseData.FilterChain.length > 0 ? currentCheeseData.FilterChain[0].id : null;

    if (topRowId !== null && idsToDelete.includes(topRowId)) {
        // --- กรณีที่ 1: ลบแถวบนสุด (ล้างไพ่ทั้งหมด) ---
        const confirmDelete = confirm("⚠️ คุณกำลังลบ 'เงื่อนไขหลัก' (แถวบนสุด) การกระทำนี้จะลบเงื่อนไขที่ตามมาทั้งหมด ยืนยันหรือไม่?");
        
        if (confirmDelete) {
            currentCheeseData.FilterChain = []; // เคลียร์ Array ทิ้งทั้งหมด!
            console.log("ล้างเงื่อนไขทั้งหมดแล้ว!");
        } else {
            return; // ถ้ายกเลิก ก็ออกจากฟังก์ชันไปเลย
        }
    } else {
        // --- กรณีที่ 2: ลบแถวธรรมดา (ลบเฉพาะตัวที่ติ๊ก) ---
        // ใช้ filter() เพื่อเก็บไว้เฉพาะแถวที่ "ไม่ได้อยู่ในกลุ่ม idsToDelete"
        currentCheeseData.FilterChain = currentCheeseData.FilterChain.filter(
            row => !idsToDelete.includes(row.id)
        );
        console.log(`ลบรายการ ID: ${idsToDelete.join(", ")} สำเร็จ`);
    }

    // 4. สั่งวาดตารางใหม่ให้หน้าจออัปเดต
    renderFilterChain();
}



// ==========================================
// ฟังก์ชันเช็คและสร้างโครงสร้างพื้นฐาน (.cheese)
// ==========================================
// ==========================================
// ฟังก์ชันเช็คและสร้างโครงสร้างพื้นฐาน (.cheese)
// ==========================================
function ensureCheeseDataExists() {
    if (!currentCheeseData) {
        currentCheeseData = {};
    }

    // 🌟 1. ดึงค่าจาก Dropdown ตอนนี้มาเตรียมไว้
    const modeSelect = document.getElementById("report_mode");
    const currentMode = modeSelect ? modeSelect.value : "detail";

    if (!currentCheeseData.PrepareReport) {
        // ใช้ Math.random() สุ่มตัวเลขตั้งแต่ 10,000 ถึง 99,999
        const randomProcessId = Math.floor(Math.random() * 90000) + 10000;

        currentCheeseData.PrepareReport = {
            "id": randomProcessId, // ยัดเลขสุ่มใส่เข้าไปเลย
            "report_mode": currentMode, // 🌟 2. ใส่ค่าโหมดรายงานเข้าไปที่นี่!
            "querychains_enabled": 1,
            "sync_back_limit": 60,
            "sync_back_random": 0,
            "sync_back_not_unique_active": 0,
            "data_info_show_row": 1,
            "data_info_showsample_total": 5,
            "data_info_showtotal_row": 1
        };
        console.log("🛠️ สร้าง PrepareReport สำเร็จ (Mode: " + currentMode + ")");
    } else {
        // 🌟 3. ป้องกันกรณีโหลด JSON เก่ามาแล้วไม่มีฟิลด์นี้ ให้เติมเข้าไป
        if (!currentCheeseData.PrepareReport.report_mode) {
            currentCheeseData.PrepareReport.report_mode = currentMode;
        }
    }

    if (!currentCheeseData.FilterChain) {
        currentCheeseData.FilterChain = [];
        console.log("🛠️ สร้างตระกร้า FilterChain สำเร็จ!");
    }
}



// ==========================================
// ฟังก์ชันเพิ่ม Filter ใหม่ลงใน State (.cheese)
// ==========================================
function addNewFilterRow() {
    
    ensureCheeseDataExists();
    
    // 1. ดึงค่าจาก Dropdown
    const selectElement = document.getElementById("tableSelect");
    const selectedValue = selectElement.value;

    // เช็คว่า User เลือกตารางหรือยัง
    if (selectedValue === "0") {
        alert("กรุณาเลือกตารางจาก Dropdown ก่อนครับ!");
        return;
    }

    // 2. หั่นข้อความด้วยเครื่องหมาย '-' 
    const parts = selectedValue.split('-');
    
    const typeVal = parts[0]; 
    const groupsVal = parts[1];
    const tablesIdVal = parseInt(parts[2]); // แปลงเป็นตัวเลข (int)

    // 🎯 2.5 ดึงค่า data-profile_id และ data-product_master_id จาก <option> ที่ถูกเลือก
    const selectedOption = selectElement.options[selectElement.selectedIndex];
    const profileIdAttr = selectedOption.getAttribute("data-profile_id");
    const productMasterIdAttr = selectedOption.getAttribute("data-product_master_id");
 	 const extension_id = selectedOption.getAttribute("data-extension_id");
  
    // 3. หาว่า ID (ลำดับของ FilterChain) ตัวถัดไปคือเลขอะไร
    let nextId = 1;
    if (currentCheeseData.FilterChain.length > 0) {
        // หาค่า id ที่มากที่สุด แล้ว +1
        const maxId = Math.max(...currentCheeseData.FilterChain.map(row => row.id));
        nextId = maxId + 1;
    }

    // 4. สร้าง Object ก้อนใหม่ตามโครงสร้าง JSON ของคุณ
    const newFilterObj = {
        "id": nextId,
        "tables": tablesIdVal, // ใส่ ID
        "type": typeVal,       // ใส่ชื่อ
        "groups": groupsVal,   // ใส่คำว่า process, extension หรือ register
        "query_text": "",      // ปล่อยว่างไว้รอ User ไปกรอกในหน้าต่าง Edit
        "readableText": "รอการตั้งค่าเงื่อนไข...", // ข้อความตั้งต้น
        "json_out_put": "",    // เผื่อฟิลด์นี้ไว้รอรับค่าจาก Popup 
        "datalist": null,
        "headlist": null,
        "datalist2": null,
        "status": 1,
        "filterDisplayItem": []
    };

    // 🎯 4.5 ยัดค่า profile_id และ product_master_id ลงไป (ถ้ามีค่าส่งมา)
    if (profileIdAttr) {
        newFilterObj["profile_id"] = profileIdAttr;
    }
    if (productMasterIdAttr) {
        newFilterObj["product_master_id"] = productMasterIdAttr;
    }
     if (extension_id) {
        newFilterObj["extension_id"] = extension_id;
    }

    // 5. ดันข้อมูลก้อนใหม่นี้เข้าไปใน Array ของ .cheese
    currentCheeseData.FilterChain.push(newFilterObj);
    console.log("เพิ่มเงื่อนไขใหม่เรียบร้อย:", newFilterObj);

    // 6. สั่งให้ตารางวาดตัวเองใหม่ (แถวใหม่จะเด้งขึ้นมาทันที)
    renderFilterChain();
    
    // (Optional) รีเซ็ต Dropdown กลับไปที่ค่า default
    selectElement.value = "0"; 
}

// ==========================================
// ฟังก์ชันเช็คการซ่อน/แสดง Dropdown โหมดรายงาน
// ==========================================
function toggleReportModeVisibility() {
    const modeWrapper = document.getElementById("report_mode_wrapper");
    const modeSelect = document.getElementById("report_mode");
    
    if (!modeWrapper || !currentCheeseData) return;

    // เช็คว่ามีรายการใน FilterChain หรือไม่
    if (currentCheeseData.FilterChain && currentCheeseData.FilterChain.length > 0) {
        // 🛑 ถ้ามีเงื่อนไขอยู่แล้ว -> ซ่อน (หรือจะเปลี่ยนเป็นปิดการใช้งานแทนก็ได้)
        modeWrapper.style.display = "none"; 
        
        /* 💡 ทริคเสริม: ถ้าไม่อยากซ่อนหายไปเลย (เดี๋ยว User งงว่าปุ่มหายไปไหน) 
           ใช้คำสั่ง disabled แทนได้ครับ ให้มันโชว์เป็นสีเทาๆ กดไม่ได้
           modeSelect.disabled = true; 
        */
    } else {
        // 🟢 ถ้าโดนลบออกหมดแล้ว (length === 0) -> แสดงกลับมาให้เลือกใหม่
        modeWrapper.style.display = "flex"; // หรือ "block" ตาม CSS เดิมของคุณ
        
        // modeSelect.disabled = false; // (ถ้าใช้ทริค disabled ด้านบน ต้องใส่บรรทัดนี้คืนค่าด้วย)
    }
}

// ==========================================
// ฟังก์ชันแปลง JSON Criteria เป็นภาษาคน (Readable Text)
// รองรับการแสดงผลแบบ Filter(WHERE), Trend(แกนเวลา) และ Summary(สรุปผล)
// ==========================================
function convertJsonToReadableText(jsonStr) {
    if (!jsonStr || jsonStr.trim() === "") return "";

    try {
        const list = JSON.parse(jsonStr);
        if (!Array.isArray(list) || list.length === 0) return "";

        // 1. จัดกลุ่มตาม key "group" 
        const grouped = {};
        list.forEach(item => {
            const groupKey = item.group ? String(item.group) : "1";
            if (!grouped[groupKey]) {
                grouped[groupKey] = [];
            }
            grouped[groupKey].push(item);
        });

        const sortedGroupKeys = Object.keys(grouped).sort((a, b) => a.localeCompare(b, undefined, { numeric: true }));
        const groupStrings = [];

        // 2. วนลูปสร้างข้อความทีละกลุ่ม
        sortedGroupKeys.forEach(key => {
            const items = grouped[key];
            let groupBuffer = "";

            items.forEach((item, index) => {
                
                // --- ดึงข้อมูลฝั่ง WHERE ---
                const col = item.column_tag || item.column || "";
                const op = item.operation || "";
                const valObj = item.value;
                const val = (valObj !== null && valObj !== undefined) ? String(valObj) : "";
                let currentJoin = item.condition || "AND";
                
                // --- ดึงข้อมูลฝั่ง SUMMARY ---
                const sumName = item.summary_name || "";
                const aggFunc = item.agg_func || "";
                const targetCol = item.target_col || "";

                // 🚀 [เพิ่มใหม่] --- ดึงข้อมูลฝั่ง TREND ---
                const trendDateCol = item.trend_date_col || "";
                const trendFormat = item.trend_format || "";

				              // 🚀 [เพิ่มใหม่] เช็คว่าเป็นการนับจำนวนไหม
				const isCountOp = op.toLowerCase().includes("(c)");
				
				// 🌟 ยอมให้ผ่านถ้ามีคอลัมน์ "หรือ" เป็นเงื่อนไขนับจำนวน
				let hasWhereCondition = (currentJoin.toLowerCase() !== "end") && (col.trim() !== "" || isCountOp);
				                
				let readableWhere = "";
				let readableSummary = "";
				let readableTrend = ""; 
				
				// =======================================
				// 📝 สเต็ป A: แปลภาษาฝั่ง WHERE (ถ้ามี)
				// =======================================
				if (hasWhereCondition) {
				    const opLower = op.toLowerCase();
				    
				    // 🚀 [เพิ่มใหม่] ดักเคสการนับจำนวน (C)>= หรือ (C)<=
				    if (isCountOp) {
				        let sqlOp = op.replace(/\(c\)/ig, "").trim(); // ตัดคำว่า (C) ออกให้เหลือแค่ >=, <=
				        let displayCol = col.trim() !== "" ? col : "รายการ"; // ถ้าไม่มีชื่อคอลัมน์ ให้ใช้คำว่า "รายการ"
				        readableWhere = `นับจำนวน (${displayCol}) ${sqlOp} ${val}`;
				    } 
				    // เงื่อนไขเดิมอื่นๆ
				    else if (opLower.includes("null") || opLower.includes("empty")) {
				        readableWhere = `'${col}' ${op}`;
				    } else if (opLower === "start" || op === "link%") {
				        readableWhere = `'${col}' Starts With '${val}'`;
				    } else if (op === "%link") {
				        readableWhere = `'${col}' Ends With '${val}'`;
				    } else if (op === "%link%") {
				        readableWhere = `'${col}' Contains '${val}'`;
				    } else if (op === "find_in_set") {
				        readableWhere = `'${col}' In List '${val}'`;
				    } else {
				        readableWhere = `'${col}' ${op} '${val}'`;
				    }
				}

                // =======================================
                // 📝 สเต็ป B: แปลภาษาฝั่ง TREND และ SUMMARY (ถ้ามี)
                // =======================================
                
                // แปลภาษา Trend
                if (trendDateCol !== "" && trendFormat !== "") {
                    // แปลง Format เป็นภาษาไทยให้ดูน่ารักๆ
                    let formatThai = trendFormat;
                    if (trendFormat === "DAY") formatThai = "รายวัน";
                    else if (trendFormat === "MONTH") formatThai = "รายเดือน";
                    else if (trendFormat === "YEAR") formatThai = "รายปี";

                    readableTrend = `[📈 แกนเวลา: ${formatThai} (${trendDateCol})]`;
                }

                // แปลภาษา Summary
                if (sumName !== "" || aggFunc !== "") {
                    let displayTarget = targetCol ? ` ${targetCol}` : "";
                    readableSummary = `[📊 สรุปผล: ${sumName} (${aggFunc}${displayTarget})]`;
                }

                // =======================================
                // 📝 สเต็ป C: ประกอบร่างข้อความ (ต่อคิวกันสวยๆ)
                // =======================================
                let parts = []; // ใช้ Array เก็บชิ้นส่วนที่มีค่า แล้วจับมาต่อกัน
                
                if (readableWhere !== "") parts.push(readableWhere);
                if (readableTrend !== "") parts.push(readableTrend);
                if (readableSummary !== "") parts.push(readableSummary);

                // จับทุกก้อนมาต่อกันด้วยลูกศร ➡️
                let finalReadableText = parts.join(" ➡️ ");

                // ถ้าบรรทัดนี้กลวงโบ๋ (ไม่มีอะไรเลย) ให้ข้ามไป
                if (finalReadableText === "") return; 

                // =======================================
                // 📝 สเต็ป D: นำไปต่อกันตาม Logic (AND/OR)
                // =======================================
                if (currentJoin.toLowerCase() === "start") currentJoin = "AND";

                if (groupBuffer === "") {
                    // ตัวแรกของกลุ่ม ใส่ข้อความลงไปเลย
                    groupBuffer += finalReadableText;
                } else {
                    // ถ้าบรรทัดนี้ไม่มี WHERE ให้ใช้เครื่องหมายบวก (+) แทน
                    if (!hasWhereCondition) {
                         groupBuffer += ` + ${finalReadableText}`;
                    } else {
                         groupBuffer += ` ${currentJoin.toUpperCase()} ${finalReadableText}`;
                    }
                }
            });

            if (groupBuffer.trim() !== "") {
                if (sortedGroupKeys.length > 1) {
                    groupStrings.push(`( ${groupBuffer} )`);
                } else {
                    groupStrings.push(groupBuffer);
                }
            }
        });

        return groupStrings.join(" OR ");

    } catch (e) {
        console.error("Error parsing JSON to readable text:", e);
        return "ข้อผิดพลาดในการแปลเงื่อนไข"; 
    }
}

// ==========================================
// ฟังก์ชันบันทึกข้อมูลจากหน้าต่างแก้ไข กลับเข้าสู่ .cheese
// ==========================================
// ==========================================
// ฟังก์ชันบันทึกข้อมูลจากหน้าต่างแก้ไข กลับเข้าสู่ .cheese
// ==========================================
function saveFilterCriteriaToCheese() {
    const editIdInput = document.querySelector('input[name="id"]'); 
    if (!editIdInput) {
        console.error("หา ID ของ Filter ไม่เจอ!");
        return;
    }
    const targetId = parseInt(editIdInput.value);
    const rawJsonOutput = document.getElementById("jsonOutput").value; 
    const generatedReadableText = convertJsonToReadableText(rawJsonOutput);

    // 🌟 วิ่งหา Object ของตารางนี้ใน FilterChain
    const targetFilter = currentCheeseData.FilterChain.find(row => row.tables === targetId);

    // 🚀 ดึงค่าการตั้งค่า Join จากหน้าจอ
    const joinType = document.getElementById('joinTypeSelect') ? document.getElementById('joinTypeSelect').value : "";
    
    const masterSelect = document.getElementById('joinMasterKeySelect');
    let joinMasterKey = "", joinMasterTable = "";
    if (masterSelect && masterSelect.selectedIndex > 0) {
        const opt = masterSelect.options[masterSelect.selectedIndex];
        joinMasterKey = opt.value;
        joinMasterTable = opt.getAttribute('data-table') || "";
    }

    const childSelect = document.getElementById('joinChildKeySelect');
    let joinChildKey = "", joinChildTable = "";
    if (childSelect && childSelect.selectedIndex > 0) {
        const opt = childSelect.options[childSelect.selectedIndex];
        joinChildKey = opt.value;
        joinChildTable = opt.getAttribute('data-table') || "";
    }

    // 🌟 ยัดการตั้งค่าทั้งหมดลงใน TargetFilter (ต้องอยู่ข้างใน if นี้เท่านั้น!)
    if (targetFilter) {
        targetFilter.json_out_put = rawJsonOutput;
        targetFilter.readableText = generatedReadableText !== "" ? generatedReadableText : "รอการตั้งค่าเงื่อนไข...";
        
        // 1. เซฟการตั้งค่า Join
        targetFilter.join_type = joinType;
        targetFilter.join_master_key = joinMasterKey;
        targetFilter.join_master_table = joinMasterTable;
        targetFilter.join_child_key = joinChildKey;
        targetFilter.join_child_table = joinChildTable;

        // 2. กวาด "ทุกคอลัมน์" ไปให้ตารางลูกใช้ Join (saved_columns)
        const allColsSelect = document.getElementById('columnSelectorToAdd');
        if (allColsSelect) {
            let currentTableCols = [];
            Array.from(allColsSelect.options).forEach(opt => {
                if (opt.value && opt.value !== "") {
                    currentTableCols.push({
                        value: opt.value,
                        text: opt.innerText.trim(), // ชื่อออริจินัล
                        table: opt.getAttribute('data-table') || "MAIN_TBL"
                    });
                }
            });
            targetFilter.saved_columns = currentTableCols;
        }

        // 3. เซฟคอลัมน์ที่ตั้งค่าโชว์ (ลากวาง & แก้ชื่อ)
        let selectedDisplayCols = window.activeDisplayColumns.map(col => col.value);
        targetFilter.display_columns = selectedDisplayCols.join(','); // เก็บ 'data_value5,data_value1' ส่งให้ Java
        
        // 4. เปลี่ยนมาใช้ชื่อ custom_labels จะได้ไม่ไปทับของเดิม!
        targetFilter.custom_labels = window.activeDisplayColumns.map(col => {
            return {
                value: col.value,
                text: col.label, // ชื่อใหม่ที่ผู้ใช้พิมพ์แก้
                table: col.table
            };
        });

        console.log(`💾 อัปเดตตารางที่ ${targetId} สำเร็จ!`, targetFilter);
    }

    // 🌟 อัปเดตหน้า Global Report (Summary & Trend)
    if (!currentCheeseData.PrepareReport) {
        currentCheeseData.PrepareReport = {};
    }
    
    currentCheeseData.PrepareReport.summary_name = document.getElementById('summaryNameInput') ? document.getElementById('summaryNameInput').value : "";
    currentCheeseData.PrepareReport.summary_func = document.getElementById('summaryFuncSelect') ? document.getElementById('summaryFuncSelect').value : "";
    currentCheeseData.PrepareReport.summary_column = document.getElementById('summaryColumnSelect') ? document.getElementById('summaryColumnSelect').value : "";
    currentCheeseData.PrepareReport.trend_date_column = document.getElementById('trendDateColumn') ? document.getElementById('trendDateColumn').value : "";
    currentCheeseData.PrepareReport.trend_format = document.getElementById('trendFormat') ? document.getElementById('trendFormat').value : "DAY";
    currentCheeseData.PrepareReport.group_by_column = document.getElementById('trendGroupByColumn') ? document.getElementById('trendGroupByColumn').value : "";

    renderFilterChain();
}


// ==========================================
// ฟังก์ชันสำหรับรันตอนเปิดหน้าต่าง Edit
// ==========================================
function loadFilter() {
    // 1. ดึง ID ของเงื่อนไขที่เรากำลังจะแก้ (จาก input hidden ที่ส่งมากับหน้าจอ)
    const editIdInput = document.querySelector('input[name="id"]');
    
    if (editIdInput && currentCheeseData && currentCheeseData.FilterChain) {
        const targetId = parseInt(editIdInput.value);

        // 2. วิ่งไปค้นหาข้อมูลใน State (.cheese) ว่า ID นี้เคยเซฟ json_out_put ไว้ไหม?
        const targetFilter = currentCheeseData.FilterChain.find(row => row.tables === targetId);

        // 3. ถ้าเจอข้อมูล ให้เอาไปใส่ใน Textarea รอไว้เลย
        if (targetFilter && targetFilter.json_out_put) {
            document.getElementById("jsonOutput").value = targetFilter.json_out_put;
            console.log(`ดึงข้อมูลเงื่อนไข ID: ${targetId} มาแสดงผลสำเร็จ!`);
        } else {
            // ถ้ายังไม่เคยเซฟ (พึ่งกดเพิ่มเข้ามาใหม่) ก็ให้ Textarea ว่างเปล่า
            document.getElementById("jsonOutput").value = "";
            console.log(`เงื่อนไข ID: ${targetId} เป็นของใหม่ ยังไม่มีข้อมูล`);
        }
    }
 filterDataList = [];
 loadFromInput();
}



// ==========================================
// ฟังก์ชันส่งไฟล์ .cheese ไปรันหาข้อมูล (Report/Data Analytics)
// ==========================================
async function executeCheeseQuery(targetContainerId,csrfParam) {
    // 1. เช็คให้ชัวร์ว่ามีข้อมูล .cheese อยู่
    ensureCheeseDataExists(); 
    
    if (currentCheeseData.FilterChain.length === 0) {
        alert("ยังไม่มีเงื่อนไขการกรองเลยครับ กรุณาเพิ่มเงื่อนไขก่อน!");
        return;
    }

    // 2. แปลง Object เป็น JSON String
    const jsonPayload = JSON.stringify(currentCheeseData);
 
    // 3. ดึง CSRF Token (สำคัญมาก! เพราะคุณใช้ Spring Security อยู่)
    // สังเกตจากโค้ดเก่าคุณมีการส่ง ${_csrf.parameterName}=${_csrf.token}
    // แนะนำให้ดึงจาก tag meta (ถ้ามี) หรือส่งแปะไปกับ URL

    
    // ตั้ง URL ใหม่ให้คลีนๆ ไม่ต้องมี ID แล้ว (ถ้ามี CSRF ก็ต่อท้ายไปเลย)
    const url = `/getdatareport-filemanager/execute?${csrfParam}`;

    // แสดงสถานะโหลด (Loading) ที่หน้าจอก่อนยิง
    

    // 4. ยิง POST Request ส่งก้อน JSON ไปให้ Java
   return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json', // บอก Server ว่านี่คือ JSON นะ
            'Accept': 'application/json'
        },
        body: jsonPayload // ยัดก้อน .cheese ใส่ Body ส่งไปเลย!
    })
    .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.text(); // หรือ response.json() ถ้า Server ส่งกลับมาเป็น JSON
    })
    .then(data => {
	
        console.log("ได้รับข้อมูลจาก Server แล้ว!"+data);
        const container = document.getElementById("table-container");
        if (targetContainerId && container) {
                renderFirstTable(data,"table-container");
        } else {
		
             jsonData = data;
     
     
        }
       
    })
    .catch(error => {
        console.error('Error Executing Cheese Query:', error);
        document.getElementById(targetContainerId).innerHTML = "<p style='color:red;'>❌ เกิดข้อผิดพลาดในการดึงข้อมูล</p>";
    });
}



// ==========================================
// 🔍 ฟังก์ชันเปิดดูข้อมูลเจาะลึก (Drill-down) แบบดึงมาโชว์ใน Tab 2 (Fetch POST)
// ==========================================
function openDrillDownData(targetChainId, mainId,clickedElement) {
    console.log("กำลังเปิดดูรายละเอียดของ Chain ID: " + targetChainId + ", อ้างอิง ID: " + mainId);

    // 1. ดึงข้อมูลก้อน JSON ปัจจุบัน และ CSRF
    const jsonPayload = JSON.stringify(currentCheeseData);
    const csrfParam = document.querySelector('input[name="csrfParam"]')?.value || "";

    // 2. ตั้งค่า URL ที่จะยิงไป
    const url = `/showdata-drilldown-filemanager?${csrfParam}`;

    // 3. เตรียมข้อมูลที่จะส่งไปให้ Java (แพ็กเป็นแบบ Form URL Encoded เพื่อให้ @RequestParam รับได้)
    const params = new URLSearchParams();
    params.append('id_refer', mainId);
    params.append('chain_id', targetChainId);
    params.append('cheeseJsonPayload', jsonPayload);

    // 4. สลับ Tab ทันทีที่กด เพื่อให้ผู้ใช้เห็นว่าระบบกำลังทำงาน
    switchtabActiveAndData('data', '2', '2');
    
    // ใส่ Loading ไว้ที่ตารางรอเลย
    document.getElementById("table-container2").innerHTML = "<div style='text-align:center; padding: 20px;'>กำลังโหลดข้อมูลเจาะลึก... ⏳</div>";

    // 5. ยิง Fetch (POST Request)
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', // สำคัญ! ต้องเป็น type นี้ Java ถึงจะอ่าน @RequestParam ออก
            'Accept': 'application/json'
        },
        body: params.toString() // ส่งข้อมูลแบบ text ที่ถูกเข้ารหัสแล้ว
    })
    .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.text(); 
    })
    .then(data => {
        console.log("ได้รับข้อมูล Drill-down จาก Server แล้ว!");
     // 1. ดึงค่าวันที่ (ของเดิม)
    const filterValue = clickedElement ? clickedElement.getAttribute('data-filter') : "";
    
    // 🚀 2. ดึงค่าหมวดหมู่ที่เพิ่งเพิ่มเข้าไป (ของใหม่)
    const filterCategory = clickedElement ? clickedElement.getAttribute('data-category') : "";

    // 🚀 3. ส่งทั้ง 2 ค่าไปให้ฟังก์ชันวาดตาราง
    renderFirstTable(data, "table-container2", filterValue, filterCategory);
    })
    .catch(error => {
        console.error('Error Executing Drill-down Query:', error);
        document.getElementById("table-container2").innerHTML = "<p style='color:red; text-align:center;'>❌ เกิดข้อผิดพลาดในการดึงข้อมูลเจาะลึก</p>";
    });
}




// ==========================================
// 1. ฟังก์ชัน: โหลดค่าจาก Object ลงหน้าจอ (เรียกตอนเปิดหน้าตั้งค่า)
// ==========================================
function loadReportSettingsToUI() {
    if (!currentCheeseData || !currentCheeseData.PrepareReport) return;

    let prep = currentCheeseData.PrepareReport;

    // โหลดค่า Limit (ดักไว้ด้วยว่าถ้าค่าเก่าเกิน 200 ให้ปัดลงมาเหลือ 200)
    let limitVal = prep.sync_back_limit || 60;
    if (limitVal > 2000) limitVal = 2000;
    
    document.getElementById('setting_sync_back_limit').value = limitVal;
    document.getElementById('setting_sync_back_random').value = prep.sync_back_random || 0;
    document.getElementById('setting_sync_back_not_unique_active').value = prep.sync_back_not_unique_active || 0;
    document.getElementById('TotalRecords').value = totalRows || 0;
}

// ==========================================
// 2. ฟังก์ชัน: อัปเดตค่าจากหน้าจอกลับลง Object (เรียกตอน User เปลี่ยนค่า)
// ==========================================
function updateReportSettingsToJS() {
    if (!currentCheeseData) currentCheeseData = {};
    if (!currentCheeseData.PrepareReport) currentCheeseData.PrepareReport = {};

    let prep = currentCheeseData.PrepareReport;

    // ดึงค่าจาก Input Limit พร้อมป้องกันไม่ให้ User พิมพ์เกิน 200
    let inputLimit = parseInt(document.getElementById('setting_sync_back_limit').value) || 60;
    if (inputLimit > 2000) {
        inputLimit = 2000; // บังคับค่าใน JS
        document.getElementById('setting_sync_back_limit').value = 2000; // ตบค่าในหน้าจอกลับเป็น 200 ให้ User เห็นด้วย
    }

    // อัปเดตลง Object เตรียมพร้อมสำหรับนำไปใช้สร้าง Report
    prep.sync_back_limit = inputLimit;
    prep.sync_back_random = parseInt(document.getElementById('setting_sync_back_random').value) || 0;
    prep.sync_back_not_unique_active = parseInt(document.getElementById('setting_sync_back_not_unique_active').value) || 0;
    

    console.log("🛠️ [ตั้งค่า Report อัปเดตแล้ว]:", currentCheeseData.PrepareReport);
}




// ==========================================
// 🛠️ Helper: ฟังก์ชันลบรายการตัวเลือก
// ==========================================
function deleteOption(chainId, ruleId, targetVal) {
    // ถามย้ำเพื่อความชัวร์ จะได้ไม่เผลอกดโดน
    if (!confirm(`ยืนยันการลบตัวเลือก '${targetVal}' ใช่หรือไม่?`)) return;

    let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
    if (chain && chain.json_out_put) {
        let rulesArray = JSON.parse(chain.json_out_put);
        let rule = rulesArray.find(r => r.id == ruleId);
        
        if (rule && rule.valueselected) {
            let options = rule.valueselected.split(',').filter(v => v.trim() !== "");
            
            // กรองเอาตัวเลือกเป้าหมายทิ้งไป
            let newOptions = options.filter(optStr => {
                let optVal = optStr.split(':')[0].trim();
                return optVal !== targetVal;
            });
            
            // เซฟค่าใหม่ที่ไม่มีตัวเลือกนั้นแล้ว
            rule.valueselected = newOptions.join(',');
            
            // ถ้าตัวเลือกล่าสุดที่ผู้ใช้เลือกอยู่ ดันเป็นตัวที่เพิ่งลบไป ให้เคลียร์ค่าทิ้งด้วย
            if (rule.value === targetVal) {
                rule.value = "";
            }
            
            chain.json_out_put = JSON.stringify(rulesArray);
            console.log(`ลบตัวเลือก '${targetVal}' เรียบร้อย!`);
            
            // วาดหน้าจอใหม่ เพื่อให้ตัวเลือกหายไปจาก Dropdown
            renderQuickEditFromJS();
            
            // เปิดกล่องตั้งค่าค้างไว้ให้ (เพื่อความลื่นไหล เผื่ออยากลบอันอื่นต่อ)
            setTimeout(() => {
                let editBox = document.getElementById(`editOpt_${ruleId}`);
                if(editBox) editBox.style.display = 'block';
            }, 50);
        }
    }
}

// อัปเดตสถานะ Tab
function toggleChainStatus(chainId, isChecked) {
    let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
    if (chain) {
        chain.status = isChecked ? 1 : 0;
        renderQuickEditFromJS(); // วาดใหม่ให้ Tab ขีดฆ่าทำงาน
    }
}

// อัปเดตข้อมูลข้างใน json_out_put (Label, Operation)
function updateRuleField(chainId, ruleId, fieldName, newValue) {
    let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
    if (chain && chain.json_out_put) {
        let rulesArray = JSON.parse(chain.json_out_put);
        let rule = rulesArray.find(r => r.id == ruleId);
        if (rule) {
            rule[fieldName] = newValue;
            chain.json_out_put = JSON.stringify(rulesArray);
        }
    }
}
// ==========================================
// 🛠️ Helper 4: อัปเดตชื่อป้าย (Label) ของตัวเลือก Select
// ==========================================
function updateOptionLabel(chainId, ruleId, targetVal, newText) {
    let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
    if (chain && chain.json_out_put) {
        let rulesArray = JSON.parse(chain.json_out_put);
        let rule = rulesArray.find(r => r.id == ruleId);
        
        if (rule && rule.valueselected) {
            let options = rule.valueselected.split(',');
            let finalTxt = newText.trim() === "" ? targetVal : newText.trim(); // ถ้าลบจนว่าง ให้ดึงค่า Value เดิมมาใช้

            // ประกอบร่าง string ใหม่ในรูปแบบ value:text,value:text
            let newOptions = options.map(optStr => {
                let parts = optStr.split(':');
                let optVal = parts[0].trim();
                if (optVal === targetVal) {
                    return `${optVal}:${finalTxt}`; // 🎯 อัปเดตเฉพาะตัวที่แก้
                }
                return optStr;
            });
            
            // เซฟลง JSON
            rule.valueselected = newOptions.join(',');
            chain.json_out_put = JSON.stringify(rulesArray);
            console.log(`เปลี่ยนป้าย '${targetVal}' เป็น '${finalTxt}' เรียบร้อย!`);

            // 🚀 อัปเดต DOM โดยตรง (หลีกเลี่ยงการ Render ใหม่ทั้งแผง เพื่อให้กล่องไม่โดนปิด)
            let selectEl = document.getElementById(`sel_${ruleId}`);
            if (selectEl) {
                for (let i = 0; i < selectEl.options.length; i++) {
                    if (selectEl.options[i].value === targetVal) {
                        selectEl.options[i].text = finalTxt; // ปลี่ยนตัวหนังสือใน Dropdown ทันที!
                        break;
                    }
                }
            }
        }
    }
}
// ==========================================
// 🛠️ Helper: ฟังก์ชัน "กลายร่าง" (Mutate Input Type)
// ==========================================
function mutateInputType(chainId, ruleId, targetType) {
    let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
    if (chain && chain.json_out_put) {
        let rulesArray = JSON.parse(chain.json_out_put);
        let rule = rulesArray.find(r => r.id == ruleId);
        
        if (rule) {
            // 🔄 กลายร่างจาก Text ➡️ Select Multiple (แบบเลือกหลายอัน)
            if (targetType === 'select') {
                
                // 🌟 เปลี่ยนจาก 'select' เป็น 'select_multiple' ตรงนี้ครับ
                rule.type = 'select_multiple'; 
                
                // ถ้ามีค่าที่พิมพ์ค้างไว้ในกล่อง Text ให้เอามันมาสร้างเป็นตัวเลือกแรกซะเลย!
                if (rule.value && String(rule.value).trim() !== "") {
                    let currentVal = String(rule.value).trim();
                    // เซฟลง valueselected ในรูปแบบ value:text
                    rule.valueselected = `${currentVal}:${currentVal}`;
                } else {
                    rule.valueselected = "";
                }
                console.log(`กลายร่าง Rule ID ${ruleId} เป็น Dropdown (Multiple) สำเร็จ!`);
            } 
            // 🔄 คืนร่างจาก Select ➡️ Text
            else if (targetType === 'text') {
                rule.type = 'text';
                // ค่า rule.value เดิมยังอยู่ ไม่ต้องลบ เพื่อให้กรอกต่อไปได้เลย
                console.log(`คืนร่าง Rule ID ${ruleId} กลับเป็น Text สำเร็จ!`);
            }

            // เซฟร่างใหม่ลง JSON
            chain.json_out_put = JSON.stringify(rulesArray);
            
            // สั่งวาดหน้าจอใหม่ทันที จะเห็นผลเอฟเฟกต์สลับกล่องเดี๋ยวนั้นเลย
            renderQuickEditFromJS(); 
        }
    }
}

// ==========================================
// 🛠️ Helper: ฟังก์ชันจัดการ Dropdown ที่ User เพิ่มค่าเองได้
// ==========================================
function handleDynamicSelect(chainId, ruleId, selectEl) {
    if (selectEl.value === 'ADD_NEW') {
        // 1. เด้ง Popup ให้พิมพ์
        let newValue = prompt("✍️ กรอก 'ตัวเลือกใหม่' ที่ต้องการเพิ่มเข้าไปในรายการ:");
        
        if (newValue && newValue.trim() !== "") {
            let val = newValue.trim();
            
            let chain = currentCheeseData.FilterChain.find(c => c.id == chainId);
            let rulesArray = JSON.parse(chain.json_out_put);
            let rule = rulesArray.find(r => r.id == ruleId);
            
            // 2. ดึงค่าที่มีอยู่เดิมมาต่อคิว
            let currentVals = rule.valueselected ? rule.valueselected.split(',').filter(v => v.trim() !== "") : [];
            
            // เช็คว่าเคยมีคำนี้อยู่แล้วหรือยัง ถ้ายังไม่มีค่อยเพิ่ม
            if (!currentVals.some(v => v.split(':')[0].trim() === val)) {
                currentVals.push(`${val}:${val}`); // ใส่รูปแบบ value:text
                rule.valueselected = currentVals.join(',');
            }
            
            // 3. บังคับเลือกค่าที่เพิ่งพิมพ์ไป
            rule.value = val;
            
            // 4. เซฟกลับลง JSON ของ Chain นั้น
            chain.json_out_put = JSON.stringify(rulesArray);
            console.log(`เพิ่มตัวเลือกสำเร็จ: ${val}`);
            
            // 5. วาดจอใหม่ (คราวนี้ไม่เด้งกลับ Tab แรกแล้ว เพราะเราทำระบบจำ Tab ไว้แล้ว!)
            renderQuickEditFromJS(); 
        } else {
            // ถ้ายกเลิก ให้กลับไปตัวเลือกบนสุด
            selectEl.selectedIndex = 0; 
        }
    } 
    else {
        // 🌟 ถ้าผู้ใช้กดเลือกตัวเลือกปกติ (ไม่ได้เพิ่มใหม่)
        updateRuleField(chainId, ruleId, 'value', selectEl.value);
        let hiddenEl = document.getElementById(`hid_${ruleId}`);
        if (hiddenEl) hiddenEl.value = selectEl.value;
    }
}







// =======================================================
// 🚀 ฟังก์ชันกวาดค่าจากหน้าจอ ฉีดกลับเข้า json_out_put
// =======================================================
function injectQuickFiltersToJS() {
    if (!currentCheeseData || !currentCheeseData.FilterChain) return;

    const inputs = document.querySelectorAll('.inject-val');

    // วนลูปตาม Chain แต่ละกล่อง
    currentCheeseData.FilterChain.forEach(chain => {
        if (!chain.json_out_put || chain.json_out_put.trim() === "") return;

        try {
            // 1. แกะ String ออกมาเป็น Array
            let rulesArray = JSON.parse(chain.json_out_put);

            // 2. วนลูปหา Input ที่ตรงกับ Chain นี้ แล้วเอาค่าไปยัดใส่ Array
            inputs.forEach(input => {
                let targetChainId = input.getAttribute('data-chain-id');
                let targetRuleId = input.getAttribute('data-rule-id');
                
                if (String(chain.id) === String(targetChainId)) {
                    let matchedRule = rulesArray.find(r => String(r.id) === String(targetRuleId));
                    if (matchedRule) {
                        // 🌟 ค้นหาว่ามีกล่อง Select (Dropdown) คู่กันอยู่ไหม
                        let selectEl = document.getElementById(`sel_${targetRuleId}`);
                        
                        if (selectEl && selectEl.multiple) {
                            // 🚀 ถ้าเป็น Dropdown แบบเลือกหลายค่า ให้กวาดค่าทั้งหมดมาคั่นด้วย comma
                            let selectedVals = Array.from(selectEl.selectedOptions).map(opt => opt.value);
                            matchedRule.value = selectedVals.join(','); 
                        } 
                        else if (selectEl) {
                            // 🚀 ถ้าเป็น Dropdown ธรรมดา (เลือกค่าเดียว)
                            matchedRule.value = selectEl.value;
                        } 
                        else {
                            // 🚀 ถ้าเป็น Input Text/Date/Number ธรรมดา
                            matchedRule.value = input.value; 
                        }
                    }
                }
            });

            // 3. แพ็ก Array กลับเป็น String แล้วอัปเดตทับของเดิม
            chain.json_out_put = JSON.stringify(rulesArray, null, 2);

        } catch (e) {
            console.error("พังตอนอัปเดต json_out_put ของ Chain ID: " + chain.id, e);
        }
    });

    console.log("✅ อัปเดตข้อมูล Quick Filters ลง json_out_put สำเร็จ!", currentCheeseData.FilterChain);
    
    // ตรงนี้สามารถเรียกคำสั่งให้โหลดตารางใหม่ (Fetch API / Render) ได้เลยครับ
}






async function injectQuickFiltersToJS2(_csrf, segmentation, process_id) {
    if (!currentCheeseData || !currentCheeseData.FilterChain) return;

    const inputs = document.querySelectorAll('.inject-val');

    // 1. กวาดค่าจากหน้าจอ
    currentCheeseData.FilterChain.forEach(chain => {
        if (!chain.json_out_put || chain.json_out_put.trim() === "") return;
        try {
            let rulesArray = JSON.parse(chain.json_out_put);
            inputs.forEach(input => {
                let targetChainId = input.getAttribute('data-chain-id');
                let targetRuleId = input.getAttribute('data-rule-id');
                if (String(chain.id) === String(targetChainId)) {
                    let matchedRule = rulesArray.find(r => String(r.id) === String(targetRuleId));
                    if (matchedRule) matchedRule.value = input.value;
                }
            });
            chain.json_out_put = JSON.stringify(rulesArray, null, 2);
        } catch (e) {
            console.error("Error updating json_out_put:", e);
        }
    });

    // 🌟 [เพิ่มตรงนี้] บันทึกข้อมูลที่เพิ่งอัปเดตลงในเครื่องผู้ใช้ (ใช้ segmentation เป็นคีย์เพื่อไม่ให้ตีกัน)
    const storageKey = `bi_filter_state_${segmentation}`;
    localStorage.setItem(storageKey, JSON.stringify(currentCheeseData.FilterChain));
    console.log("💾 บันทึก Filter ลงเครื่องผู้ใช้เรียบร้อย! (แยกรายบุคคล)");

    // 🌟 2. รัน Query ...
    await executeCheeseQuery('jsonbody', _csrf);

    // 🌟 3. เมื่อ Query เสร็จแล้ว ...
    shows2('draggable-window3', 'Sync Back');
    getData('/SyncBack2-filemanager/'+segmentation+'/'+process_id, 'mainbody3');
}



function applyLocalFiltersToData(segmentation) {
    if (!currentCheeseData || !currentCheeseData.FilterChain) return;

    const storageKey = `bi_filter_state_${segmentation}`;
    const savedDataStr = localStorage.getItem(storageKey);

    // ถ้าเคยจำไว้ ให้เอามาแปะทับ
    if (savedDataStr) {
        try {
            const localFilterChain = JSON.parse(savedDataStr);

            currentCheeseData.FilterChain.forEach(serverChain => {
                let localChain = localFilterChain.find(lc => String(lc.id) === String(serverChain.id));
                
                if (localChain) {
                    serverChain.status = localChain.status; // ทับสถานะการติ๊กถูกเปิด/ปิด
                    
                    if (serverChain.json_out_put && localChain.json_out_put) {
                        let serverRules = JSON.parse(serverChain.json_out_put);
                        let localRules = JSON.parse(localChain.json_out_put);

                        serverRules.forEach(sRule => {
                            let lRule = localRules.find(lr => String(lr.id) === String(sRule.id));
                            // ทับเฉพาะ "ค่าที่เคยพิมพ์" โครงสร้างอื่นๆ ใช้ของ Server เหมือนเดิม
                            if (lRule) {
                                sRule.value = lRule.value; 
                            }
                        });
                        serverChain.json_out_put = JSON.stringify(serverRules);
                    }
                }
            });
            console.log("📂 โหลด Filter จากเครื่องผู้ใช้มาแปะทับสำเร็จ!");
        } catch (error) {
            console.error("ข้อมูล Filter ในเครื่องเก่าเกินไปหรือพัง, ล้างทิ้ง", error);
            localStorage.removeItem(storageKey);
        }
    }
}


// ==========================================
// 📄 ฟังก์ชันเริ่มต้นใหม่ (New .cheese)
// ==========================================
function NewCheese() {

    // 2. เคลียร์ตัวแปรหลัก
    filterDataList = []; // ล้าง Array เงื่อนไข
    runningId = 1;       // รีเซ็ตลำดับ ID กลับเป็น 1
    
    // ถ้ามีการเก็บตัวโครงสร้างหลักไว้ ให้เคลียร์ FilterChain ทิ้ง
    if (currentCheeseData && currentCheeseData.FilterChain) {
        currentCheeseData.FilterChain = [];
    }

    // 3. ล้างหน้าจอ UI (ตารางกรอกข้อมูล)
    const tbody = document.querySelector('#filterTable tbody');
    if (tbody) {
        tbody.innerHTML = ''; // ล้างแถวในตารางทิ้งให้หมด
    }

    // 4. ล้างช่องกรอกข้อมูล (Inputs)
    if(document.getElementById('conditionSelect')) document.getElementById('conditionSelect').value = '';
    if(document.getElementById('columnSelect')) document.getElementById('columnSelect').value = '';
    if(document.getElementById('operationSelect')) document.getElementById('operationSelect').value = '';
    if(document.getElementById('valueInput')) document.getElementById('valueInput').value = '';
    if(document.getElementById('groupInput')) document.getElementById('groupInput').value = '1';

    // ล้างช่องของโหมด Summary (ถ้ามี)
    if(document.getElementById('summaryNameInput')) document.getElementById('summaryNameInput').value = '';
    if(document.getElementById('summaryFuncSelect')) document.getElementById('summaryFuncSelect').value = '';
    if(document.getElementById('summaryColumnSelect')) document.getElementById('summaryColumnSelect').value = '';

    // 5. ล้างตารางผลลัพธ์ (ตาราง Data เจาะลึกที่เราเพิ่งทำไป)
    if(document.getElementById('table-container')) document.getElementById('table-container').innerHTML = '';
    if(document.getElementById('table-container2')) document.getElementById('table-container2').innerHTML = '';
    if(document.getElementById('recordCount')) document.getElementById('recordCount').innerText = "0 records added";

    // 6. อัปเดต JSON Display (ถ้ามีโชว์บนหน้าจอ)
    if (typeof updateJsonDisplay === "function") {
        updateJsonDisplay();
    }

    // 7. แจ้งเตือนความสำเร็จ
    console.log("ล้างข้อมูลเรียบร้อย เริ่มการตั้งค่าใหม่ได้!");
}




// ==========================================
// 📄 ฟังก์ชันเริ่มต้นใหม่ (New .cheese)
// ==========================================
function createNewCheese() {
    // 1. ถามเพื่อความชัวร์ ป้องกันยูสเซอร์เผลอกดโดนแล้วร้องไห้
    if (!confirm("⚠️ คุณต้องการล้างข้อมูลและเริ่มการตั้งค่าใหม่ ใช่หรือไม่?\nข้อมูลที่ยังไม่ได้บันทึก (Save) จะสูญหายทั้งหมด!")) {
        return; // ถ้ากดยกเลิก ก็หยุดแค่นี้
    }

    // 2. เคลียร์ตัวแปรหลัก
    filterDataList = []; // ล้าง Array เงื่อนไข
    runningId = 1;       // รีเซ็ตลำดับ ID กลับเป็น 1
    
    // ถ้ามีการเก็บตัวโครงสร้างหลักไว้ ให้เคลียร์ FilterChain ทิ้ง
    if (currentCheeseData && currentCheeseData.FilterChain) {
        currentCheeseData.FilterChain = [];
    }

    // 3. ล้างหน้าจอ UI (ตารางกรอกข้อมูล)
    const tbody = document.querySelector('#filterTable tbody');
    if (tbody) {
        tbody.innerHTML = ''; // ล้างแถวในตารางทิ้งให้หมด
    }

    // 4. ล้างช่องกรอกข้อมูล (Inputs)
    if(document.getElementById('conditionSelect')) document.getElementById('conditionSelect').value = '';
    if(document.getElementById('columnSelect')) document.getElementById('columnSelect').value = '';
    if(document.getElementById('operationSelect')) document.getElementById('operationSelect').value = '';
    if(document.getElementById('valueInput')) document.getElementById('valueInput').value = '';
    if(document.getElementById('groupInput')) document.getElementById('groupInput').value = '1';

    // ล้างช่องของโหมด Summary (ถ้ามี)
    if(document.getElementById('summaryNameInput')) document.getElementById('summaryNameInput').value = '';
    if(document.getElementById('summaryFuncSelect')) document.getElementById('summaryFuncSelect').value = '';
    if(document.getElementById('summaryColumnSelect')) document.getElementById('summaryColumnSelect').value = '';

    // 5. ล้างตารางผลลัพธ์ (ตาราง Data เจาะลึกที่เราเพิ่งทำไป)
    if(document.getElementById('table-container')) document.getElementById('table-container').innerHTML = '';
    if(document.getElementById('table-container2')) document.getElementById('table-container2').innerHTML = '';
    if(document.getElementById('recordCount')) document.getElementById('recordCount').innerText = "0 records added";

    // 6. อัปเดต JSON Display (ถ้ามีโชว์บนหน้าจอ)
    if (typeof updateJsonDisplay === "function") {
        updateJsonDisplay();
    }

    // 7. แจ้งเตือนความสำเร็จ
    console.log("ล้างข้อมูลเรียบร้อย เริ่มการตั้งค่าใหม่ได้!");
}



function updateGlobalDateFormat() {
    const selectElement = document.getElementById("date-format-selector");
    const selectElementtable = document.getElementById("table-view-selector");
    const selectAgg = document.getElementById("pivot-agg-selector"); // 🌟 ดึงสูตรคำนวณมา
    	const selectBiMode = document.getElementById("bi-mode-selector");
    if (!selectElement) return;

    if (!currentCheeseData.PrepareReport) currentCheeseData.PrepareReport = {};

    currentCheeseData.PrepareReport.date_format = selectElement.value;
    currentCheeseData.PrepareReport.table_view = selectElementtable ? selectElementtable.value : "normal"; 
    currentCheeseData.PrepareReport.pivot_agg = selectAgg ? selectAgg.value : "SUM"; // 🌟 ยัดสูตรลงไป
	currentCheeseData.PrepareReport.bi_mode = selectBiMode ? selectBiMode.value : "off";
}


function loadGlobalSettings() {
    // ==========================================
    // 1. 📅 โหลดค่า Date Format
    // ==========================================
    let savedDateFormat = "-"; // ค่า Default
    if (currentCheeseData && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.date_format) {
        savedDateFormat = currentCheeseData.PrepareReport.date_format;
    }

    const dateSelectElem = document.getElementById("date-format-selector");
    if (dateSelectElem) {
        dateSelectElem.value = savedDateFormat;
        console.log(`โหลดการตั้งค่า Date Format เดิม: ${savedDateFormat}`);
    }

    // ==========================================
    // 2. 📊 โหลดค่า รูปแบบตาราง (Table View / Pivot)
    // ==========================================
    let savedTableView = "normal"; // ค่า Default
    if (currentCheeseData && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.table_view) {
        savedTableView = currentCheeseData.PrepareReport.table_view;
    }

    const tableViewElem = document.getElementById("table-view-selector");
    if (tableViewElem) {
        tableViewElem.value = savedTableView;
        console.log(`โหลดการตั้งค่า Table View เดิม: ${savedTableView}`);
    }
    
    
    // (ในฟังก์ชัน loadGlobalSettings ให้เพิ่มท่อนนี้เข้าไปครับ)
    let savedAgg = "SUM";
    if (currentCheeseData && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.pivot_agg) {
        savedAgg = currentCheeseData.PrepareReport.pivot_agg;
    }
    const aggElem = document.getElementById("pivot-agg-selector");
    if (aggElem) aggElem.value = savedAgg;
    
    

		
				// โหลดค่ามาโชว์บนหน้าจอ
		let savedBiMode = "off";
		if (currentCheeseData && currentCheeseData.PrepareReport && currentCheeseData.PrepareReport.bi_mode) {
		    savedBiMode = currentCheeseData.PrepareReport.bi_mode;
		}
		const biModeElem = document.getElementById("bi-mode-selector");
		if (biModeElem) biModeElem.value = savedBiMode;
    
}













// ==========================================
// 3. ฟังก์ชันบันทึกไฟล์ (Save .cheese) - อัปเกรด!
// ==========================================
function exportCheeseFile() {
    // 🌟 1. เพิ่มการถามยืนยันก่อนบันทึก
    const confirmSave = confirm("คุณต้องการส่งออกไฟล์การตั้งค่า (.cheese) ใช่หรือไม่?");
    if (!confirmSave) return;

    // 🌟 2. จัดการรูปแบบ วันที่-เวลา ให้มนุษย์อ่านง่าย (YYYY-MM-DD_HH-mm)
    const now = new Date();
    const dateStr = now.getFullYear() + "-" + 
                    String(now.getMonth() + 1).padStart(2, '0') + "-" + 
                    String(now.getDate()).padStart(2, '0');
    const timeStr = String(now.getHours()).padStart(2, '0') + "-" + 
                    String(now.getMinutes()).padStart(2, '0');
    
    const fileName = `jerry_report_${dateStr}_${timeStr}.cheese`;

    // แปลง Object เป็น JSON String
    const jsonString = JSON.stringify(currentCheeseData, null, 2);

    // สร้างไฟล์ .cheese ขึ้นมาในเบราว์เซอร์
    const blob = new Blob([jsonString], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    
    // จำลองการคลิกปุ่มดาวน์โหลด
    const a = document.createElement('a');
    a.href = url;
    a.download = fileName; // ใช้ชื่อไฟล์ที่จัดรูปแบบใหม่
    document.body.appendChild(a);
    a.click();
    
    // เคลียร์ขยะ
    setTimeout(() => {
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
    }, 0);

    console.log(`🚀 Export สำเร็จ: ${fileName}`);
}
// ==========================================
// 4. ฟังก์ชันเปิดไฟล์ (Load .cheese)
// ==========================================
function importCheeseFile(event) {
    const file = event.target.files[0];
    if (!file) return;

    if (!file.name.endsWith('.cheese')) {
        alert("ชีสปลอม! กรุณาเลือกไฟล์ .cheese เท่านั้นครับ 🧀");
        event.target.value = ''; 
        return;
    }

    const reader = new FileReader();
    reader.onload = function(e) {
        try {
            // 1. อ่านไฟล์ที่ User เลือก แล้วแปลงกลับเป็น Object
            const loadedData = JSON.parse(e.target.result);
            
            // 2. อัปเดตทับค่า Global State ตัวเก่า
            currentCheeseData = loadedData;
            console.log("🧀 โหลดชีสสำเร็จ!", currentCheeseData);
            
           alert("โหลด 🧀 สำเร็จ");

        } catch (err) {
            console.error("JSON Parse Error:", err);
            alert("ไฟล์ .cheese เสียหาย หรือโครงสร้างไม่ถูกต้องครับ!");
        }
    };
    reader.readAsText(file);
    event.target.value = ''; // เคลียร์ Input ป้องกันบั๊กกดไฟล์เดิมซ้ำไม่ติด
}





// ฟังก์ชันสำหรับโหลด Query อัตโนมัติจาก URL (เช่น จากตาราง Segment หรือ Report Template)
async function loadQueryFromURL(url,segmentation_id) {
    try {
        console.log(`📡 Fetching Reusable Filter from: ${url}`);
        
        // 1. ดึงข้อมูลจาก URL (จัดการ Path ให้ถูกต้อง)
        const response = await fetch(url);
        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

        // 2. แปลงเป็น JSON Object
        const loadedData = await response.json();

        // 3. สวมร่างข้อมูลเข้าสู่ Global State
        // ตรวจสอบโครงสร้างพื้นฐานก่อนนำไปใช้
        if (loadedData && loadedData.FilterChain) {
            currentCheeseData = loadedData;
            
            // 4. สั่ง Re-render UI ของตัวกรองเพื่อให้ User เห็นเงื่อนไขที่โหลดมา
            if (typeof renderFilterChain === "function") renderFilterChain();
            if (typeof renderQuickEditFromJS === "function") renderQuickEditFromJS();
            if (typeof loadGlobalSettings === "function") loadGlobalSettings();

            console.log("✅ Reusable Filter Loaded & Applied!");
        } else {
            throw new Error("Invalid .cheese format");
        }
    } catch (err) {
        console.error("❌ Failed to load query:", err);
        //alert('โหลดตัวกรองไม่สำเร็จ: ' + err.message);
    }
    
	    applyLocalFiltersToData(segmentation_id);
		renderQuickEditFromJS(true);
    
    
}


// ตัวแปรเก็บคอลัมน์ที่เลือกไว้
window.activeDisplayColumns = [];

// ฟังก์ชันเพิ่มคอลัมน์ลง List
function addDisplayColumnUI() {
    const select = document.getElementById('columnSelectorToAdd');
    if (select.selectedIndex <= 0) return; // ยังไม่ได้เลือก

    const opt = select.options[select.selectedIndex];
    const val = opt.value;
    const text = opt.text;

    // กันเพิ่มซ้ำ
    if (window.activeDisplayColumns.find(c => c.value === val)) {
        alert("เพิ่มคอลัมน์นี้ไปแล้วครับ!");
        return;
    }

    // ยัดลง Array
    window.activeDisplayColumns.push({ value: val, label: text, table: "MAIN_TBL" });
    renderSortableColumns();
    select.selectedIndex = 0; // เคลียร์ช่องเลือก
}

// ฟังก์ชันลบ
function removeDisplayColumnUI(index) {
    window.activeDisplayColumns.splice(index, 1);
    renderSortableColumns();
}

// ฟังก์ชันอัปเดตชื่อเมื่อพิมพ์แก้ (จำชื่อที่แก้แยกไว้)
function updateColumnLabelUI(index, newLabel) {
    window.activeDisplayColumns[index].label = newLabel;
}

// ฟังก์ชันวาด UI ลากวาง
let dragStartIndex = -1;
function renderSortableColumns() {
    const list = document.getElementById('sortableColumnsList');
    if (!list) return;
    list.innerHTML = "";

    if (window.activeDisplayColumns.length === 0) {
        list.innerHTML = '<li style="color:#aaa; text-align:center; padding:10px;">(ยังไม่ได้เลือกคอลัมน์)</li>';
        return;
    }

    window.activeDisplayColumns.forEach((col, index) => {
        const li = document.createElement('li');
        li.setAttribute('draggable', 'true');
        li.setAttribute('data-index', index);
        li.style.cssText = "display: flex; align-items: center; padding: 8px; margin-bottom: 5px; background: #f8f9fa; border: 1px solid #ddd; border-radius: 4px; cursor: grab;";
        
        // ไอคอนสำหรับบอกว่าลากได้
        const dragHandle = document.createElement('span');
        dragHandle.innerHTML = "☰";
        dragHandle.style.cssText = "margin-right: 10px; color: #888; cursor: grab;";

        // ช่องพิมพ์แก้ชื่อคอลัมน์
        const input = document.createElement('input');
        input.type = "text";
        input.className = "form-control form-control-sm";
        input.value = col.label; // ใส่ชื่อตั้งต้น
        input.onkeyup = (e) => updateColumnLabelUI(index, e.target.value); // พิมพ์ปุ๊บจำปั๊บ

        // รหัสคอลัมน์จริงๆ (โชว์ไว้ให้รู้เฉยๆ)
        const codeSpan = document.createElement('span');
        codeSpan.innerHTML = `(${col.value})`;
        codeSpan.style.cssText = "font-size: 0.8em; color: #999; margin-left: 10px; margin-right: auto;";

        // ปุ่มลบ
        const delBtn = document.createElement('button');
        delBtn.innerHTML = "❌";
        delBtn.style.cssText = "background: none; border: none; cursor: pointer; color: red; margin-left: 10px;";
        delBtn.onclick = () => removeDisplayColumnUI(index);

        // ประกอบร่าง
        li.appendChild(dragHandle);
        li.appendChild(input);
        li.appendChild(codeSpan);
        li.appendChild(delBtn);

        // --- ระบบลากวาง (Drag & Drop) ---
        li.ondragstart = (e) => { dragStartIndex = index; li.style.opacity = '0.5'; };
        li.ondragend = (e) => { li.style.opacity = '1'; };
        li.ondragover = (e) => { e.preventDefault(); };
        li.ondrop = (e) => {
            e.preventDefault();
            const dropIndex = index;
            if (dragStartIndex !== dropIndex && dragStartIndex > -1) {
                // สลับตำแหน่งใน Array
                const movedItem = window.activeDisplayColumns.splice(dragStartIndex, 1)[0];
                window.activeDisplayColumns.splice(dropIndex, 0, movedItem);
                renderSortableColumns(); // วาดใหม่ตามลำดับใหม่
            }
        };

        list.appendChild(li);
    });
}

// 🚀 ตัวแปร Global สำหรับจำโหมดปัจจุบัน
let currentViewModeState = false;

// ==========================================
// 🔍 Preview Count สำหรับ Tab 2+
// ==========================================
let _previewDebounceTimers = {};

async function previewTabCount(chainId, tabIndex) {
    // ลบ if (tabIndex <= 1) return; ออก — ให้ Tab 1 ทำงานได้ด้วย

    const inputs = document.querySelectorAll('.inject-val');
    const snapshotChains = JSON.parse(JSON.stringify(currentCheeseData.FilterChain));

    snapshotChains.forEach(chain => {
        if (!chain.json_out_put || chain.json_out_put.trim() === "") return;
        try {
            let rulesArray = JSON.parse(chain.json_out_put);
            inputs.forEach(input => {
                if (String(chain.id) === input.getAttribute('data-chain-id')) {
                    let matched = rulesArray.find(r => String(r.id) === input.getAttribute('data-rule-id'));
                    if (matched) matched.value = input.value;
                }
            });
            chain.json_out_put = JSON.stringify(rulesArray);
        } catch(e) {}
    });

    _setTabPreviewBadge(tabIndex, "...");
clearTimeout(_previewDebounceTimers[tabIndex]);
_previewDebounceTimers[tabIndex] = setTimeout(async () => {
        try {
            const url = `/getdatareport-filemanager/execute?${_csrf}`;
            const opts = (body) => ({
                method: 'POST',
                headers: { 'Content-Type': 'application/json', 'Accept': 'application/json' },
                body
            });

            // ตะกล้า: Tab 1 = chain[0], Tab 2 = chain[0]+chain[1], ...
            const stackedChains = snapshotChains
                .slice(0, tabIndex)
                .map(c => ({ ...c, status: 1 }));

            const payload = JSON.stringify({
                ...currentCheeseData,
                FilterChain: stackedChains
            });

            const res   = await fetch(url, opts(payload));
            const data  = await res.text();
            const count = JSON.parse(data)[0]?.total_data ?? "?";

            _setTabPreviewBadge(tabIndex, count);
        } catch (e) {
            console.error("previewTabCount error:", e);
            _setTabPreviewBadge(tabIndex, "?");
        }
    }, 400);
}

function _setTabPreviewBadge(tabIndex, value) {
    const badge = document.getElementById(`preview-badge-${tabIndex}`);
    if (badge) badge.textContent = value;
}
function triggerAllTabPreviews() {
    if (!currentCheeseData || !currentCheeseData.FilterChain) return;

    currentCheeseData.FilterChain.forEach((chain, index) => {
        let tabIndex = index + 1;
        previewTabCount(chain.id, tabIndex);
    });
}




// ==========================================
// 🖊️ renderQuickEditFromJS — เต็ม
// ==========================================
function renderQuickEditFromJS(isViewMode = null) {
    if (isViewMode !== null) {
        currentViewModeState = isViewMode;
    }

    const container = document.getElementById('dynamicQuickEditContainer');
    if (!currentCheeseData || !currentCheeseData.FilterChain) {
        container.innerHTML = "<p>ไม่พบข้อมูลเงื่อนไข</p>";
        return;
    }

    let activeTabNum = 1;
    let currentActiveTab = document.querySelector('.tabs .tab.active');
    if (currentActiveTab && currentActiveTab.id) {
        activeTabNum = parseInt(currentActiveTab.id.replace('tabChains-', ''));
    }

    const chains = currentCheeseData.FilterChain;
    let html = '';

    // ========== วาด Tabs ==========
    html += `<div class="tabs">`;
   chains.forEach((chain, index) => {
    let count = index + 1;
    let activeClass = (count === activeTabNum) ? 'active' : '';
    let tabStyle = chain.status === 0 ? 'text-decoration: line-through; opacity: 0.5;' : '';

    // ✅ ทุก tab มี badge หมด ไม่ใช่แค่ tab 2+
    let badgeHtml = `<span id="preview-badge-${count}" style="margin-left:5px; background:#dbeafe; color:#1d4ed8; padding:1px 6px; border-radius:10px; font-size:11px; font-weight:600;"></span>`;

    html += `
        <div class="tab ${activeClass}" id="tabChains-${count}" 
             onclick="switchtabActiveAndData('Chains','${chains.length}','${count}')" style="${tabStyle}">
            ${chain.type || 'Chain ' + count}${badgeHtml}
        </div>`;
});
    html += `</div>`;

    // ========== วาด Panel แต่ละ Tab ==========
    html += `<div id="quickFilterContainer">`;
    chains.forEach((chain, index) => {
        let count      = index + 1;
        let displayStyle   = (count === activeTabNum) ? '' : 'display:none;';
        // trigger preview เฉพาะ tab 2+, tab 1 เป็น string ว่าง
        let previewTrigger = count > 1 ? `previewTabCount(${chain.id}, ${count});` : '';

        html += `<div id="showChains-${count}" class="panel" style="${displayStyle}">`;

        let rulesArray = [];
        try {
            if (chain.json_out_put && chain.json_out_put.trim() !== "") {
                rulesArray = JSON.parse(chain.json_out_put);
            }
        } catch (e) {
            console.error("พังตอนแปลง json_out_put", e);
        }

        if (rulesArray.length > 0) {

            let toggleCheckbox = "";
            if (index > 0) {
                let isChecked = (chain.status === 1) ? 'checked' : '';
                toggleCheckbox = `<input type="checkbox" 
                    onchange="toggleChainStatus(${chain.id}, this.checked); ${previewTrigger}" 
                    ${isChecked} style="margin-right: 8px; cursor: pointer;">`;
            }

            html += `
                <div class="section-header">
                    <label style="display: inline-flex; align-items: center; margin: 0; cursor: pointer;" title="เปิด/ปิด การใช้งานกลุ่มนี้">
                        ${toggleCheckbox}
                        <span>Condition Group 1</span>
                    </label>
                    <div class="line"></div>
                </div>`;

            rulesArray.forEach(rule => {
                if (rule.condition === 'OR') {
                    html += `
                        <div class="section-header" style="margin-top: 15px;">
                            <span>OR (New Condition Group)</span>
                            <div class="line"></div>
                        </div>`;
                }

                let op = rule.operation ? rule.operation.toLowerCase() : "";
                let isMonthPassed      = op.includes('months_passed');
                let isNoValueCondition = op.includes('null') || op.includes('empty');
                let isThisVar          = rule.value && String(rule.value).startsWith('#this_');

                html += `<div class="form-row">`;

                // Label
                if (currentViewModeState) {
                    html += `<span class="label">${rule.column_tag || 'Column'}</span>`;
                } else {
                    html += `<span class="label" contenteditable="true" title="คลิกเพื่อแก้ไขชื่อ" 
                               onblur="updateRuleField(${chain.id}, ${rule.id}, 'column_tag', this.innerText)" 
                               onkeydown="if(event.keyCode===13){this.blur(); return false;}">${rule.column_tag || 'Column'}</span>`;
                }

                // 🛑 1. ตัวแปรระบบ #this_ — ล็อค
                if (isThisVar) {
                    html += `<div style="display:flex; flex:1; align-items:center; gap: 5px;">
                                <input type="text" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" 
                                       value="${rule.value}" readonly 
                                       title="ค่าเชื่อมโยงระบบ (แก้ไขไม่ได้)"
                                       style="flex:1; background-color:#f0f0f0; color:#6b7280; cursor:not-allowed; font-weight:bold; border:1px dashed #9ca3af; text-align:center;">
                             </div>`;
                }
                // 🟢 2. months_passed
                else if (isMonthPassed) {
                    html += `<select onchange="updateRuleField(${chain.id}, ${rule.id}, 'operation', this.value); ${previewTrigger}">`;
                    [3, 6, 9, 12, 24].forEach(m => {
                        let optVal   = `${m}_months_passed`;
                        let selected = (op === optVal) ? 'selected' : '';
                        html += `<option value="${optVal}" ${selected}>${m} months have passed.</option>`;
                    });
                    html += `</select>`;
                    html += `<input type="hidden" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" value="${rule.value || ''}">`;
                }
                // 🟢 3. Null / Empty — user แก้ไม่ได้ ไม่ต้อง trigger
                else if (isNoValueCondition) {
                    html += `<input type="text" readonly 
                                    style="background-color:#f0f0f0; color:#888; cursor:not-allowed; font-style:italic;" 
                                    placeholder="${rule.operation}">`;
                }
                // 🟢 4. ปกติ
                else {
                    if (rule.type === 'date') {
                        html += `<input type="date" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" 
                                        value="${rule.value || ''}" title="${rule.operation}"
                                        onchange="${previewTrigger}">`;
                    }
                    else if (rule.type === 'number') {
                        html += `<input type="number" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" 
                                        value="${rule.value || ''}" title="${rule.operation}"
                                        onchange="${previewTrigger}">`;
                    }
                    else if (rule.type === 'select' || rule.type === 'select_multiple') {
                        let isMultiple     = rule.type === 'select_multiple' ? 'multiple' : '';
                        let selectedValues = rule.value ? String(rule.value).split(',') : [];

                        html += `<div style="display:flex; flex-direction:column; flex:1;">`;
                        html += `  <div style="display:flex; align-items:center; gap: 5px;">`;

                        html += `<select id="sel_${rule.id}" ${isMultiple} 
                                         onchange="handleDynamicSelect(${chain.id}, ${rule.id}, this); ${previewTrigger}" 
                                         style="flex:1;">`;
                        html += `<option value=" ">-- Select (${rule.operation}) --</option>`;

                        let editBoxHtml = "";
                        if (!currentViewModeState) {
                            editBoxHtml = `<div id="editOpt_${rule.id}" style="display:none; margin-top:8px; padding:10px; background:#f8f9fa; border-radius:5px; border:1px solid #dfe1e6;">
                                <div style="font-size:0.85em; color:#505f79; margin-bottom:8px; font-weight:bold;">ปรับแต่งหรือลบรายการตัวเลือก:</div>`;
                        }

                        if (rule.valueselected) {
                            rule.valueselected.split(',').forEach(optStr => {
                                if (optStr.trim() === "") return;
                                let parts  = optStr.split(':');
                                let optVal = parts[0].trim();
                                let optTxt = parts.length > 1 ? parts[1].trim() : parts[0].trim();
                                let isSelected = selectedValues.includes(optVal) ? 'selected' : '';

                                html += `<option value="${optVal}" ${isSelected}>${optTxt}</option>`;

                                if (!currentViewModeState) {
                                    editBoxHtml += `
                                        <div style="display:flex; align-items:center; margin-bottom:6px; font-size:0.9em;">
                                            <span style="display:inline-block; width:100px; color:#7a869a; overflow:hidden; text-overflow:ellipsis;" title="ค่าส่งหลังบ้าน: ${optVal}">${optVal}</span>
                                            <svg viewBox="0 0 24 24" style="width:14px; height:14px; fill:#a5adba; margin:0 8px; flex-shrink:0;"><path d="M12 4l-1.41 1.41L16.17 11H4v2h12.17l-5.58 5.59L12 20l8-8z"/></svg>
                                            <input type="text" value="${optTxt}" placeholder="ชื่อป้ายใหม่..."
                                                   onblur="updateOptionLabel(${chain.id}, ${rule.id}, '${optVal}', this.value)"
                                                   onkeydown="if(event.keyCode===13){this.blur(); return false;}"
                                                   style="padding:3px 6px; border:1px solid #dfe1e6; border-radius:3px; flex:1; outline:none; color:#172b4d;">
                                            <div class="bi-icon-btn" style="margin-left:5px; background-color:#fee2e2; border-color:#fecaca;" 
                                                 onclick="deleteOption(${chain.id}, ${rule.id}, '${optVal}')" title="ลบตัวเลือกนี้">
                                                <svg viewBox="0 0 24 24" style="fill:#dc2626; width:14px; height:14px;"><path d="M6 19c0 1.1.9 2 2 2h8c1.1 0 2-.9 2-2V7H6v12zM19 4h-3.5l-1-1h-5l-1 1H5v2h14V4z"/></svg>
                                            </div>
                                        </div>`;
                                }
                            });
                        }

                        if (!currentViewModeState) {
                            html += `<option disabled>──────────</option>`;
                            html += `<option value="ADD_NEW" style="color:#007bff; font-weight:bold;">➕ เพิ่มตัวเลือกใหม่...</option>`;
                        }

                        html += `</select>`;
                        html += `<input type="hidden" id="hid_${rule.id}" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" value="${rule.value || ''}">`;

                        if (!currentViewModeState) {
                            html += `<div class="bi-icon-btn" onclick="mutateInputType(${chain.id}, ${rule.id}, 'text')" title="เปลี่ยนกลับเป็นช่องพิมพ์ข้อความ">
                                        <svg viewBox="0 0 24 24"><path d="M6.99 11L3 15l3.99 4v-3H14v-2H6.99v-3zM21 9l-3.99-4v3H10v2h7.01v3L21 9z"/></svg>
                                     </div>`;
                            html += `<div class="bi-icon-btn" onclick="let el=document.getElementById('editOpt_${rule.id}'); el.style.display=(el.style.display==='none')?'block':'none';" title="ปรับแต่งป้ายตัวเลือก">
                                        <svg viewBox="0 0 24 24">
                                            <circle cx="6" cy="12" r="2.5" fill="#eab308"/>
                                            <circle cx="12" cy="12" r="2.5" fill="#ef4444"/>
                                            <circle cx="18" cy="12" r="2.5" fill="#3b82f6"/>
                                        </svg>
                                     </div>`;
                            editBoxHtml += `</div>`;
                            html += editBoxHtml;
                        }

                        html += `  </div>`;
                        html += `</div>`;
                    }
                    else {
                        // text ทั่วไป
                        html += `<div style="display:flex; flex:1; align-items:center; gap: 5px;">`;
                        html += `<input type="text" class="inject-val" data-chain-id="${chain.id}" data-rule-id="${rule.id}" 
                                        value="${rule.value || ''}" placeholder="${rule.operation}" 
                                        onchange="updateRuleField(${chain.id}, ${rule.id}, 'value', this.value); ${previewTrigger}" 
                                        style="flex:1;">`;
                        if (!currentViewModeState) {
                            html += `<div class="bi-icon-btn" onclick="mutateInputType(${chain.id}, ${rule.id}, 'select')" title="กลายร่างเป็นแบบตัวเลือก (Dropdown)">
                                        <svg viewBox="0 0 24 24"><path d="M6.99 11L3 15l3.99 4v-3H14v-2H6.99v-3zM21 9l-3.99-4v3H10v2h7.01v3L21 9z"/></svg>
                                     </div>`;
                        }
                        html += `</div>`;
                    }
                }

                html += `</div>`; // ปิด form-row
            });
        }

        //

        html += `</div>`; // ปิด panel
    });

    html += `</div>`; // ปิด quickFilterContainer
    container.innerHTML = html;
    triggerAllTabPreviews();
}



 
/**
 * inject ค่า FO/QT เข้า currentCheeseData.FilterChain
 * แล้วเรียก injectQuickFiltersToJS2 เพื่อเปิด SyncBack popup
 *
 * @param group         register_groups เช่น "อาหาร"
 * @param year          ปี FO/QT เช่น 2025
 * @param docType       "FO" หรือ "QT"
 * @param closingStatus "ปิดได้" / "ปิดไม่ได้" / "-" (FO ไม่ใช้)
 * @param agingBucket   "<3M" / ">3M" / ">6M" / ">12M" / ">24M"
 * @param _csrf         csrf token string
 * @param segmentation  segmentation id
 * @param process_id    process id
 */
async function openFoQtSegment(group, year, docType, closingStatus, agingBucket, _csrf, segmentation, process_id) {
 
    // ================================================================
    // 0. เช็คว่า .cheese โหลดแล้วยัง
    // ================================================================
    if (!currentCheeseData || !currentCheeseData.FilterChain) {
        alert("กรุณาโหลด Query Template ก่อนครับ");
        return;
    }
 
    // ================================================================
    // 1. แปลง agingBucket → operation ของ months_passed
    // ================================================================
    // ตรงกับ bucket ใน DAO และ X_months_passed ในระบบ
    // X_months_passed = ผ่านมาแล้วมากกว่า X เดือน (ไม่มี upper bound)
    const agingOperationMap = {
        "<3M":  "3_months_passed",   // ยังไม่ถึง 3 เดือน → ใช้ 3 เป็น threshold
        ">3M":  "3_months_passed",   // ผ่านมาเกิน 3 เดือน
        ">6M":  "6_months_passed",   // ผ่านมาเกิน 6 เดือน
        ">12M": "12_months_passed",  // ผ่านมาเกิน 12 เดือน
        ">24M": "24_months_passed"   // ผ่านมาเกิน 24 เดือน
    };
    const agingOperation = agingOperationMap[agingBucket] || "12_months_passed";
 
    // ================================================================
    // 2. แปลง year → date range
    // ================================================================
    const dateFrom = year + "-01-01";
    const dateTo   = year + "-12-31";
 
    // ================================================================
    // 3. แปลง closingStatus → value สำหรับ find_in_set ของ QT
    //    ต้องตรงกับ valueselected ใน .cheese
    // ================================================================
    let closingValue = "";
    if (docType === "QT") {
        if (closingStatus === "ปิดได้") {
            closingValue = "ปิดการขายได้,ปิดการขายได้ยืนยันคำสั่งซื้อแล้ว";
        } else {
            // ปิดไม่ได้ = อื่นๆ ที่เหลือ
            closingValue = "หมดอายุ,ยกเลิกเพื่อทำใหม่:ยกเลิก,ปิดการขายไม่ได้";
        }
    }
 
    // ================================================================
    // 4. inject เข้า FilterChain แต่ละ chain
    // ================================================================
    currentCheeseData.FilterChain.forEach(chain => {
 
        let rules = [];
        try {
            if (chain.json_out_put && chain.json_out_put.trim() !== "") {
                rules = JSON.parse(chain.json_out_put);
            }
        } catch(e) {
            console.error("parse json_out_put error", e);
            return;
        }
 
      if (chain.tables === 55) {
            chain.status = 1; 
            rules.forEach(rule => {
                if (rule.column_id === "register_groups") {
                    if (group === "-" || group === "") {
                        // เปลี่ยน operation เป็นหาค่าว่างแทน (ชื่อ operator ขึ้นอยู่กับระบบคุณ)
                        rule.operation = "is_empty"; 
                        rule.value = ""; // ส่งว่างไปเผื่อไว้ แต่ระบบจะดูจาก operation แทน
                    } else {
                        rule.operation = "="; 
                        rule.value = group;
                    }
                }
            });
        }
        // --- Chain 2: FO (tables=58) ---
        else if (chain.tables === 58) {
            if (docType === "FO") {
                chain.status = 1; // เปิด FO
                rules.forEach(rule => {
                    if (rule.id === 12) rule.value = dateFrom; // เคยเปิด FO ตั้งแต่
                    if (rule.id === 13) rule.value = dateTo;   // เคยเปิด FO ถึง
                });
            } else {
                chain.status = 0; // ปิด FO ถ้าเลือก QT
            }
        }
 
        // --- Chain 3: QT (tables=60) ---
        else if (chain.tables === 60) {
            if (docType === "QT") {
                chain.status = 1; // เปิด QT
                rules.forEach(rule => {
                    if (rule.id === 16) rule.value = closingValue; // closing_status
                    if (rule.id === 18) rule.value = dateFrom;     // เคยปิด QT ตั้งแต่
                    if (rule.id === 19) rule.value = dateTo;       // เคยปิด QT ถึง
                });
            } else {
                chain.status = 0; // ปิด QT ถ้าเลือก FO
            }
        }
 
        // --- Chain 4: การสื่อสาร (tables=27) → inject aging ---
        else if (chain.tables === 27) {
            chain.status = 1; // เปิดใช้งาน
            rules.forEach(rule => {
                if (rule.id === 10) {
                    // ⚠️ ต้องแก้ operation ไม่ใช่ value!
                    rule.operation = agingOperation;
                }
            });
        }
 
        // บันทึก rules กลับ
        chain.json_out_put = JSON.stringify(rules, null, 2);
    });
 
    console.log("✅ Inject เสร็จแล้ว:", { group, year, docType, closingStatus, agingBucket, agingOperation });
 
    // ================================================================
    // 5. เรียก injectQuickFiltersToJS2 → executeCheeseQuery → SyncBack popup
    // ================================================================
    await injectQuickFiltersToJS2(_csrf, segmentation, process_id);
}
