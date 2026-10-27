let viewer_WidgetsConfig = {}; 
let viewer_ChartInstances = {};
let viewer_SelectedItems = []; 
var csrfToken_use;
var alignCss = 'center';
var textAlignCss = 'center';

// ==========================================
// 1. ฟังก์ชันคลิกกล่อง (ส่งแค่ ID ไปให้เครื่องจักรตาราง)
// ==========================================
function viewer_toggleSelect(id) {
    const el = document.getElementById(id);
    if (!el) return;

    const config = viewer_WidgetsConfig[id];
    
    if (config && config.dataset && config.dataset.data) {
        console.log(`[Viewer] แสดงข้อมูลของกล่อง ${id} ลงในตาราง`);
        // โยน ID ให้ฟังก์ชันตารางจัดการ Pivot และจัด Format 
        viewer_renderDataToTable(id);
    } else {
        const container = document.getElementById("table-container");
        if(container) container.innerHTML = `<div style="padding: 20px; text-align: center; color: #888;">ไม่มีข้อมูลลูกสำหรับกราฟนี้</div>`;
    }
}

















// ==========================================
// 2. เครื่องจักรสร้างตาราง Viewer (จำลองมาจาก renderFirstTable)
// ==========================================
function viewer_renderDataToTable(widgetId, filterStr = "") {
	
	alert(55);
	
    const config = viewer_WidgetsConfig[widgetId];
    if (!config || !config.dataset || config.dataset.data.length === 0) {
        document.getElementById("table-container").innerHTML = `<div style="padding: 20px; text-align: center; color: #888;">ไม่มีข้อมูล</div>`;
        return;
    }

    // 🌟 โคลนข้อมูล (Deep Copy) เพื่อไม่ให้ Pivot ไปกวนข้อมูลที่ใช้วาดกราฟ
    let dataset = JSON.parse(JSON.stringify(config.dataset)); 

    // ดึง Config ของรายงาน (ถ้าแต่ละกล่องมีค่าชีสไม่เหมือนกัน ให้ดึงแยก หรือใช้ Global)
    let cheeseConfig = typeof currentCheeseData !== 'undefined' ? currentCheeseData : null;

    let globalDateFormat = "yyyy-mm-dd";
    if (cheeseConfig && cheeseConfig.PrepareReport && cheeseConfig.PrepareReport.date_format) {
        globalDateFormat = cheeseConfig.PrepareReport.date_format;
    }

    // ==========================================
    // 🌟 Client-Side Filtering (กรองข้อมูล)
    // ==========================================
    if (filterStr && filterStr.trim() !== "") {
        const targetValue = filterStr.split("_")[0]; 
        dataset.data = dataset.data.filter(row => {
            return Object.values(row).some(val => 
                val !== null && String(val).includes(targetValue)
            );
        });
        dataset.total_data = dataset.data.length; 
    }

    let totalRows = dataset.data.length;
    const isTruncated = totalRows > 110; 

    // ==========================================
    // 🌟 เครื่องจักร Pivot Table อัปเกรด!
    // ==========================================
    let globalTableView = "normal";
    let globalPivotAgg = "SUM"; 
    if (cheeseConfig && cheeseConfig.PrepareReport) {
        if (cheeseConfig.PrepareReport.table_view) globalTableView = cheeseConfig.PrepareReport.table_view;
        if (cheeseConfig.PrepareReport.pivot_agg) globalPivotAgg = cheeseConfig.PrepareReport.pivot_agg;
    }

    if (globalTableView === "pivot" && dataset.headers.includes("วันที่ (Trend Axis)")) {
        const pivotCol = "วันที่ (Trend Axis)";
        const valCol = dataset.headers.find(h => String(h).includes("Summary_Col_")) || dataset.headers[dataset.headers.length - 1];
        const rowIdentifierCols = dataset.headers.filter(h => h !== pivotCol && h !== valCol);

        let dynamicHeaders = [...new Set(dataset.data.map(row => typeof formatTrendDate === 'function' ? formatTrendDate(row[pivotCol], globalDateFormat) : row[pivotCol]))].filter(Boolean).sort();
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
            
            let formattedDate = typeof formatTrendDate === 'function' ? formatTrendDate(row[pivotCol], globalDateFormat) : row[pivotCol];
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
    // 🌟 วาดตาราง HTML (เหมือนต้นฉบับเป๊ะ)
    // ==========================================
    // ใช้ class="my-tables" ให้เหมือนตัวหลัก เผื่อมี CSS ผูกไว้
    let html = '<table class="my-tables" style="width: 100%; border-collapse: collapse; font-size: 14px; text-align: left;">';
    
    // --- 1. หัวตาราง ---
    html += '<thead><tr style="background-color: #f3f4f6; border-bottom: 2px solid #ccc;">';
    html += '<th style="padding: 8px 12px; border: 1px solid #e5e7eb;">#</th>'; 
    dataset.headers.forEach(headerName => {
        html += `<th style="padding: 8px 12px; border: 1px solid #e5e7eb;">${headerName}</th>`;
    });
    html += '<th style="padding: 8px 12px; border: 1px solid #e5e7eb;">Reference_id</th></tr></thead>';

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
            
            // 🌟 ดึงค่า Chain ID (id_config) ออกมาจาก dataset เผื่อต้องส่งให้ Server
            let targetChainId = dataset.id_config || ""; 
            
            // 🚀 ฝัง onclick เรียก viewer_openDrillDownData ทันทีที่กดแถวนี้!
            html += `<tr data-id="${refValue}" class="viewer-row-hover" 
                         style="border-bottom: 1px solid #e5e7eb; cursor: pointer;" 
                         onmouseover="this.style.backgroundColor='#f9fafb'" 
                         onmouseout="this.style.backgroundColor='transparent'">`;
                         
            html += `<td style="background: #f4f4f4; font-weight: bold; text-align: center; border: 1px solid #e5e7eb;">${rowNum}</td>`; 
            
            dataset.headers.forEach(headerName => {
                let value = row[headerName];
                if (value === null || value === undefined) value = "";
                
                // 🚀 เครื่องมือแปลงวันที่ Trend Axis
                if (headerName === "วันที่ (Trend Axis)" && globalDateFormat !== "raw") {
                    if (typeof formatTrendDate === 'function') {
                        value = formatTrendDate(value, globalDateFormat);
                    }
                }
                // 🚀 2. [แก้ปัญหาตรงนี้!] ถ้าในข้อมูลมีแท็ก <a> ของระบบเก่าติดมา
                // ให้ใช้ Replace ดัดแปลงชื่อฟังก์ชันเป็นของ Viewer ซะเลย
                if (typeof value === 'string' && value.includes('openDrillDownData')) {
                  value = value.replace(/openDrillDownData\(/g, `viewer_openDrillDownData('${widgetId}', `);
                    
                    // (Optional) ถ้าไม่อยากให้มันเป็นลิงก์สีฟ้าๆ แล้ว (เพราะเรากดได้ทั้งแถว) 
                    // คุณสามารถเอาลิงก์ออกให้เหลือแค่ตัวเลขได้โดยเปิดคอมเมนต์บรรทัดข้างล่างนี้ครับ:
                    // value = value.replace(/(<([^>]+)>)/gi, ""); 
                }

                html += `<td style="padding: 8px 12px; border: 1px solid #e5e7eb;">${value}</td>`;
            });

            html += `<td style="color: gray; padding: 8px 12px; border: 1px solid #e5e7eb;">${refValue}</td>`;
            html += '</tr>';
        }
        else if (isTruncated && index === 100) {
            const colSpan = dataset.headers.length + 2; 
            html += `<tr><td colspan="${colSpan}" style="text-align: center; background: #fafafa; color: #999; padding:10px;">... มีข้อมูลซ่อนอยู่ (ตัดแถวที่ 100) ...</td></tr>`;
        }
    });

    html += '</tbody></table>';

    // --- 3. การแสดงผล ---
    const container = document.getElementById("table-container");
    if(container) container.innerHTML = html;
}


// ==========================================
// 🔍 ฟังก์ชันเจาะลึกข้อมูล (Drill-down) สำหรับโหมด Viewer
// ==========================================
async function viewer_openDrillDownData(widgetId, targetChainId, mainId, clickedElement) {
    console.log("[Viewer] กล่อง: " + widgetId + ", Chain ID: " + targetChainId + ", อ้างอิง ID: " + mainId);

    const activeConfig = viewer_WidgetsConfig[widgetId];
    let jsonPayload = "{}";

    // 🌟 1. เข้าถึงลิงก์ไฟล์ .cheese ของกล่องนี้โดยตรง
    if (activeConfig && activeConfig.dataSource) {
        try {
            // แปลงลิงก์ Idoc ให้เป็น URL ที่เบราว์เซอร์อ่านได้
            const fileUrl = activeConfig.dataSource.replace("Idoc:/", "/document-filemanager/");
            console.log("📥 กำลังดาวน์โหลดไฟล์ .cheese จาก: ", fileUrl);
            
            // ใช้ fetch วิ่งไปดูดไฟล์มาสดๆ
            const res = await fetch(fileUrl);
            if (!res.ok) throw new Error("ไม่สามารถดาวน์โหลดไฟล์ .cheese ได้");
            
            // อ่านเนื้อหาไฟล์ออกมาเป็น JSON
            const cheeseJson = await res.json();
            
            // แพ็กเป็น String เตรียมส่งให้ Java Backend
            jsonPayload = JSON.stringify(cheeseJson);
            
        } catch (error) {
            console.error("❌ โหลดไฟล์การตั้งค่า .cheese ล้มเหลว:", error);
            alert("เกิดข้อผิดพลาด: ไม่สามารถอ่านการตั้งค่าของกราฟนี้ได้");
            return; // หยุดการทำงานถ้าโหลดไฟล์ไม่สำเร็จ
        }
    } else {
        console.error("❌ กล่องนี้ไม่มีการเชื่อมต่อกับไฟล์ข้อมูล (dataSource)");
        return;
    }

    console.log("📦 JSON Payload ที่ได้จากไฟล์ พร้อมส่งให้ Server แล้ว!");


    const url = `/showdata-drilldown-filemanager?${csrfToken_use}`;

    const params = new URLSearchParams();
    params.append('id_refer', mainId);
    params.append('chain_id', targetChainId);
    params.append('cheeseJsonPayload', jsonPayload); // ส่งค่าที่เพิ่งดูดมาตะกี้นี้เลย!

    // 🌟 3. สลับหน้าต่างมาที่ Tab 2 (แสดง Loading รอก่อน)
    const sheet1 = document.getElementById('showdata-1');
    const sheet2 = document.getElementById('showdata-2');
    if (sheet1) sheet1.style.display = 'none';
    if (sheet2) sheet2.style.display = 'block';

    const container2 = document.getElementById("table-container2");
    if (container2) {
        container2.innerHTML = `
            <div style="padding: 15px;">
                <button onclick="viewer_closeDrillDown()" style="margin-bottom: 15px; padding: 8px 15px; cursor: pointer; background: #e5e7eb; border: 1px solid #ccc; border-radius: 4px;">⬅️ ย้อนกลับไปตารางหลัก</button>
                <div style='text-align:center; padding: 20px; font-size: 16px;'>กำลังโหลดข้อมูลเจาะลึก... ⏳</div>
            </div>`;
    }

    // 🌟 4. ยิง Fetch (POST Request) ไปเจาะข้อมูล
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded', 
            'Accept': 'application/json'
        },
        body: params.toString()
    })
    .then(response => {
        if (!response.ok) throw new Error("Network response was not ok");
        return response.text(); 
    })
    .then(data => {
        console.log("[Viewer] ได้รับข้อมูล Drill-down จาก Server สำเร็จ!");
        const filterValue = clickedElement ? clickedElement.getAttribute('data-filter') : "";
        viewer_renderDrillDownTable(data, "table-container2", filterValue);
    })
    .catch(error => {
        console.error('[Viewer] Error Executing Drill-down Query:', error);
        if (container2) {
            container2.innerHTML = `
                <div style="padding: 15px;">
                    <button onclick="viewer_closeDrillDown()" style="margin-bottom: 15px; padding: 8px 15px; cursor: pointer; background: #e5e7eb; border: 1px solid #ccc; border-radius: 4px;">⬅️ ย้อนกลับไปตารางหลัก</button>
                    <p style='color:red; text-align:center; font-weight: bold;'>❌ เกิดข้อผิดพลาดในการดึงข้อมูลเจาะลึก</p>
                </div>`;
        }
    });
}

// ==========================================
// 🛠️ ฟังก์ชันวาดตารางผลลัพธ์ Drill-down (แยกอิสระ)
// ==========================================
function viewer_renderDrillDownTable(jsonDatas, containerId, filterStr = "") {
    let dataset = jsonDatas;
    if (typeof jsonDatas === 'string') {
        try { dataset = JSON.parse(jsonDatas); } 
        catch (e) { 
            console.error("JSON Parse Error:", e);
            return; 
        }
    }
    
    if (Array.isArray(dataset)) dataset = dataset[0]; // เผื่อ Server หุ้ม Array มาให้
    
    const container = document.getElementById(containerId);
    if (!container) return;

    if (!dataset || !dataset.headers || !dataset.data || dataset.data.length === 0) {
        container.innerHTML = `
            <div style="padding: 15px;">
                <button onclick="viewer_closeDrillDown()" style="margin-bottom: 15px; padding: 8px 15px; cursor: pointer; background: #e5e7eb; border: 1px solid #ccc; border-radius: 4px;">⬅️ ย้อนกลับ</button>
                <p style='text-align:center; color: #888;'>ไม่มีข้อมูลเจาะลึกสำหรับรายการนี้</p>
            </div>`;
        return;
    }

    // 🚀 [เอากลับมาแล้ว!] ==========================================
    // 🌟 Client-Side Filtering (กรองข้อมูลจาก data-filter ที่คลิกมา)
    // ==========================================
    if (filterStr && filterStr.trim() !== "") {
        // แยกร่างเอาเฉพาะวันที่ เช่น จาก "2026-04-10_data_value3" จะได้ "2026-04-10"
        const targetValue = filterStr.split("_")[0]; 
        console.log(`[Viewer] กรองข้อมูล Drill-down เฉพาะที่ตรงกับ: ${targetValue}`);
        
        dataset.data = dataset.data.filter(row => {
            // ค้นหาว่าในแต่ละแถว มีคอลัมน์ไหนไหมที่มีคำว่า "2026-04-10" รวมอยู่
            return Object.values(row).some(val => 
                val !== null && String(val).includes(targetValue)
            );
        });

        // ดักจับกรณีที่พอกรองเสร็จแล้วไม่เหลือข้อมูลเลย
        if (dataset.data.length === 0) {
            container.innerHTML = `
            <div style="padding: 15px;">
                <button onclick="viewer_closeDrillDown()" style="margin-bottom: 15px; padding: 8px 15px; cursor: pointer; background: #e5e7eb; border: 1px solid #ccc; border-radius: 4px;">⬅️ ย้อนกลับ</button>
                <p style='text-align:center; color: #888;'>ไม่มีข้อมูลเจาะลึกสำหรับ <b>${targetValue}</b></p>
            </div>`;
            return;
        }
    }
    // ==========================================

    // สร้างตาราง HTML พร้อมปุ่มย้อนกลับ
    let html = `
        <table class="my-tables" style="width: 100%; border-collapse: collapse; font-size: 14px; text-align: left; background: white;">
            <thead><tr style="background-color: #f3f4f6; border-bottom: 2px solid #ccc;">
                <th style="padding: 8px 12px; border: 1px solid #e5e7eb;">#</th>
    `;

    dataset.headers.forEach(h => {
        html += `<th style="padding: 8px 12px; border: 1px solid #e5e7eb;">${h}</th>`;
    });
    html += `</tr></thead><tbody>`;

    dataset.data.forEach((row, index) => {
        html += `<tr class="viewer-row-hover" style="border-bottom: 1px solid #e5e7eb;" onmouseover="this.style.backgroundColor='#f9fafb'" onmouseout="this.style.backgroundColor='transparent'">`;
        html += `<td style="background: #f4f4f4; font-weight: bold; text-align: center; border: 1px solid #e5e7eb;">${index + 1}</td>`; 
        
        dataset.headers.forEach(h => {
            let val = row[h];
            if (val === null || val === undefined) val = "-";
            html += `<td style="padding: 8px 12px; border: 1px solid #e5e7eb;">${val}</td>`;
        });
        
        html += `</tr>`;
    });

    html += `</tbody></table>`;
    container.innerHTML = html;
}

// ==========================================
// 🔙 ฟังก์ชันกดย้อนกลับจาก Drill-down
// ==========================================
function viewer_closeDrillDown() {
    const sheet1 = document.getElementById('showdata-1');
    const sheet2 = document.getElementById('showdata-2');
    if (sheet1) sheet1.style.display = 'block'; // โชว์ตารางหลัก
    if (sheet2) sheet2.style.display = 'none';  // ซ่อนตาราง Drill-down
    
    // ล้างข้อมูลเก่าออกเพื่อคืนพื้นที่หน่วยความจำ
    const container2 = document.getElementById("table-container2");
    if (container2) container2.innerHTML = "";
}










// ============================================================================
// 2. ฟังก์ชันโหลดไฟล์และวาดหน้าจอ (เปลี่ยนชื่อแล้ว)
// ============================================================================

function viewer_loadDashboardFromUrl(fileUrl, segmentationId) {
	

    console.log("[Viewer] กำลังโหลด Dashboard จาก URL: ", fileUrl);

    fetch(fileUrl)
        .then(response => {
            if (!response.ok) throw new Error("HTTP error! Status: " + response.status);
            return response.json(); 
        })
        .then(stateObj => {
            console.log("[Viewer] ✅ โหลดไฟล์ Dashboard สำเร็จ!");
            viewer_restoreDashboardState(stateObj);
        })
        .catch(error => {
            console.error("[Viewer] ❌ ไม่สามารถโหลดไฟล์ Dashboard ได้:", error);
            document.getElementById('table-container3').innerHTML = 
                `<div style="color: red; padding: 20px; text-align: center;">
                    <b>ไม่สามารถโหลดรายงานได้</b><br>
                    URL: ${fileUrl} <br>
                    (ไฟล์อาจถูกลบหรือไม่มีสิทธิ์เข้าถึง)
                </div>`;
        });
}

function viewer_restoreDashboardState(stateObj) {
    const container = document.getElementById("table-container3");
    if (container) {
        container.className = stateObj.layoutClass || "";
    }

    document.querySelectorAll('.dash-item').forEach(el => el.remove());
    viewer_WidgetsConfig = {}; 
    viewer_SelectedItems = []; 

    if (stateObj.widgets && Array.isArray(stateObj.widgets)) {
        stateObj.widgets.forEach(widget => {
            const zone = document.getElementById(widget.zoneId); 
            if (!zone) return;

            viewer_WidgetsConfig[widget.id] = widget.config || {};

            const el = document.createElement('div');
            el.className = 'dash-item';
            el.id = widget.id;
            el.style.flex = widget.flex || "1"; 
            el.style.position = "relative";
            // เปลี่ยนมาเรียกใช้ toggleSelect ของ viewer
            el.onclick = () => viewer_toggleSelect(widget.id);
            el.style.cursor = "pointer"; // ให้รู้ว่ากดดูตารางได้

            zone.appendChild(el);
            viewer_renderWidgetUI(widget.id);
        });
    }
}

function viewer_renderWidgetUI(widgetId) {
    const config = viewer_WidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);
    if (!boxEl) return;

    // ✂️ (นำปุ่มกากบาท ลบทิ้งออกไป เพราะโหมดนี้ดูได้อย่างเดียว)

    if (!config || !config.chartType) {
        boxEl.innerHTML = `
            <div style="color: #adb5bd; font-size: 14px; text-align: center; pointer-events: none; padding-top:20px;">
                กล่องว่าง
            </div>
        `;
        return; 
    } 
    else if (config.chartType === "text") {
        // วาด Text ทันที
        if (typeof viewer_drawTextWidget === 'function') {
            viewer_drawTextWidget(widgetId);
        }
    } 
    else {
   boxEl.innerHTML = `
            <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%; color: #0078d7; pointer-events: none;">
                <svg viewBox="0 0 24 24" width="64" height="64" fill="currentColor"><path d="M5 9.2h3V19H5zM10.6 5h2.8v14h-2.8zm5.6 8H19v6h-2.8z"></path></svg>
                <div style="font-size: 16px; font-weight: bold; margin-top: 10px;">${config.chartType.toUpperCase()} Chart</div>
                <div style="font-size: 12px; color: #888; margin-top: 5px;" id="status-${widgetId}">กำลังเตรียมข้อมูล...</div>
            </div>
        `;

        if (config.dataSource) {
            viewer_loadWidgetDataFromURL(widgetId, config.dataSource);
        }
    }
}

// ============================================================================
// 3. ฟังก์ชันดึงข้อมูล และวาดกราฟ/ข้อความ (ชื่อใหม่ ไส้ในคงเดิมเป๊ะ)
// ============================================================================

async function viewer_loadWidgetDataFromURL(widgetId, rawUrl) {
    if (!rawUrl || rawUrl.trim() === "") return;

    if(!viewer_WidgetsConfig[widgetId]) viewer_WidgetsConfig[widgetId] = {};
    viewer_WidgetsConfig[widgetId].dataSource = rawUrl;

    const statusText = document.getElementById(`status-${widgetId}`);
    if (statusText) statusText.innerText = "⏳ กำลังดึงข้อมูลจาก Server...";

    try {
        const formattedURL = rawUrl.replace("Idoc:/", "/document-filemanager/");
        const cheeseRes = await fetch(formattedURL);
        if (!cheeseRes.ok) throw new Error("โหลดไฟล์ .cheese ไม่สำเร็จ");
        const widgetCheeseData = await cheeseRes.json();

        if (widgetCheeseData.FilterChain.length === 0) throw new Error("ไฟล์นี้ยังไม่มีเงื่อนไขการกรอง");

        // หมายเหตุ: ตัวแปร csrfToken คาดหวังว่ามีอยู่ในหน้าหลักแล้ว
        const execUrl = `/getdatareport-filemanager/execute?${csrfToken_use}`;

        const dataRes = await fetch(execUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(widgetCheeseData) 
        });

        if (!dataRes.ok) throw new Error("Execute Query ไม่สำเร็จ");
        
        const resultData = await dataRes.json(); 

        if (!resultData || !Array.isArray(resultData) || resultData.length === 0) {
            throw new Error("ไม่มีข้อมูลส่งกลับมา");
        }

        const dataObj = resultData[0];
        viewer_WidgetsConfig[widgetId].dataset = dataObj;
        
        viewer_drawChart(widgetId);

    } catch (error) {
        console.error(`[Viewer] Widget: ${widgetId} ❌ Error:`, error);
        if (statusText) statusText.innerText = `❌ Error: ${error.message}`;
    }
}

function viewer_drawTextWidget(widgetId) {
    const config = viewer_WidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);
    
    if (!boxEl || !config) return;

    const textToDisplay2 = (config.textContent || "").replace(/\n/g, '<br>');
    
    let alignCss = 'center';
    let textAlignCss = 'center';
    if (config.textAlign === 'left') { alignCss = 'flex-start'; textAlignCss = 'left'; }
    if (config.textAlign === 'right') { alignCss = 'flex-end'; textAlignCss = 'right'; }

    // ✂️ เอาเส้นปะ (border: 1px dashed #ccc;) ออกแล้วเพื่อให้ดูเนียนตา
    boxEl.innerHTML = `
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: ${alignCss}; 
             color: ${config.fontColor || '#333333'}; 
             font-size: ${config.fontSize || 24}px; 
             padding: 15px; box-sizing: border-box; font-weight: bold; overflow: hidden; 
             word-break: break-word; text-align: ${textAlignCss};">
            ${textToDisplay2}
        </div>
    `;
}

function viewer_drawChart(widgetId) {
    const config = viewer_WidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);

    if (!boxEl || !config || !config.dataset || !config.dataset.data) return;
    if (!config.axisX || !config.axisY) return; 

    const rawData = config.dataset.data; 

    const cleanedData = rawData.map(row => {
        let newRow = { ...row }; 
        let rawXVal = row[config.axisX];
        let xVal = rawXVal !== null && rawXVal !== undefined 
            ? String(rawXVal).replace(/(<([^>]+)>)/gi, "").trim()  
            : null;

        if (xVal && !isNaN(Number(xVal)) && String(xVal).length > 10) {
            let d = new Date(Number(xVal));
            newRow._cleanX = `${d.getFullYear()}-${(d.getMonth()+1).toString().padStart(2,'0')}-${d.getDate().toString().padStart(2,'0')}`;
        } else {
            newRow._cleanX = xVal ? String(xVal) : 'ไม่ระบุ';
        }

        let yVal = row[config.axisY];
        if (yVal !== null && yVal !== undefined) {
            let strippedStr = String(yVal).replace(/(<([^>]+)>)/gi, ""); 
            let num = parseFloat(strippedStr); 
            newRow._cleanY = isNaN(num) ? 0 : num; 
        } else {
            newRow._cleanY = 0;
        }
        return newRow;
    });

    const uniqueXLabels = [...new Set(cleanedData.map(row => row._cleanX))].sort();
    let chartDatasets = [];

    if (config.groupBy && config.groupBy !== "") {
        const uniqueGroups = [...new Set(cleanedData.map(row => row[config.groupBy] || 'ไม่ระบุ'))];

        chartDatasets = uniqueGroups.map((groupName, index) => {
            const groupData = uniqueXLabels.map(xLabel => {
                const foundRow = cleanedData.find(row => row._cleanX === xLabel && (row[config.groupBy] || 'ไม่ระบุ') === groupName);
                return foundRow ? foundRow._cleanY : null; 
            });

            const color = `hsl(${(index * 360) / uniqueGroups.length}, 70%, 50%)`;

            return {
                label: groupName,
                data: groupData,
                backgroundColor: config.chartType === 'pie' ? undefined : color,
                borderColor: color,
                borderWidth: 2,
                fill: config.chartType === 'bar',
                tension: 0.1
            };
        });

    } else {
        const dataValues = uniqueXLabels.map(xLabel => {
            const foundRow = cleanedData.find(row => row._cleanX === xLabel);
            return foundRow ? foundRow._cleanY : 0;
        });

        let bgColors = config.themeColor || '#0078d7';
        if (config.chartType === 'pie' || config.chartType === 'doughnut') {
            bgColors = uniqueXLabels.map((_, i) => `hsl(${(i * 360) / uniqueXLabels.length}, 70%, 50%)`);
        }

        chartDatasets = [{
            label: config.axisY,
            data: dataValues,
            backgroundColor: bgColors, 
            borderColor: config.chartType === 'pie' ? '#ffffff' : (config.themeColor || '#0078d7'),
            borderWidth: 2,
            fill: config.chartType === 'bar',
            tension: 0.1
        }];
    }

    const displayXLabels = uniqueXLabels.map(label => {
        if (label.match(/^\d{4}-\d{2}-\d{2}$/)) {
            let parts = label.split('-');
            return `${parts[2]}/${parts[1]}/${parts[0]}`; 
        }
        return label;
    });

    boxEl.innerHTML = `
        <div style="width: 100%; height: 100%; padding: 15px; box-sizing: border-box; position: relative; pointer-events: none;">
            <canvas id="viewer-canvas-${widgetId}"></canvas>
        </div>
    `;

    if (viewer_ChartInstances[widgetId]) {
        viewer_ChartInstances[widgetId].destroy();
    }

    const ctx = document.getElementById(`viewer-canvas-${widgetId}`).getContext('2d');
    viewer_ChartInstances[widgetId] = new Chart(ctx, {
        type: config.chartType, 
        data: {
            labels: displayXLabels, 
            datasets: chartDatasets 
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            spanGaps: true, 
            plugins: {
                legend: {
                    display: config.showLabels !== "false",
                    position: 'top'
                }
            }
        }
    });
}