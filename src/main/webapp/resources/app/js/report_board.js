// ตัวแปรจำสถานะของ Dashboard (เก็บค่าของแต่ละกล่อง)
let dashboardWidgetsConfig = {}; 
let chartInstances = {};


// ฟังก์ชันเรียกหน้าต่างตั้งค่ากราฟ (เรียกจาก propertyicon type 2)
function openChartSettings(widgetId) {
    const pane = document.getElementById('pane-fields');
    if (!pane) return;

    // ถ้ากล่องนี้ยังไม่เคยถูกตั้งค่า ให้สร้าง Config เริ่มต้นไว้
    if (!dashboardWidgetsConfig[widgetId]) {
        dashboardWidgetsConfig[widgetId] = {
            dataSource: "",
            chartType: "bar",
            axisX: "",
            axisY: "",
            showLabels: "true",
            themeColor: "#0078d7"
        };
    }
    
    let config = dashboardWidgetsConfig[widgetId];

    // สร้าง HTML Property Grid
    let html = `
    <div id="pg-container" style="height: 100%; overflow-y: auto;">
    <div class="pg-header">
        <div class="pg-title">ตั้งค่ากราฟ (Widget: ${widgetId.split('-')[1]}) </div>
   <div class="pg-search-box">
    <input type="text" id="pg-search-input" placeholder="Search" oninput="filterProperties()">
    <span style="padding:0 4px; color:#ccc; cursor:pointer;" onclick="clearSearch()">✕</span>
</div>
    </div>


        <div class="pg-body">
          <div class="pg-category">
                <div class="pg-cat-header" onclick="toggleCat(this)">
                    <div class="pg-arrow"></div> Data Source (แหล่งข้อมูล)
                </div>
                <div class="pg-cat-content">
                    <div class="pg-row">
                        <div class="pg-label">.cheese Link</div>
                        <div class="pg-value">
                            <input type="text" 
					       id="cfg_dataSource" 
					       placeholder="วางลิงก์ Idoc:/... ที่นี่" 
					       value="${config.dataSource || ''}" 
					       onchange="loadWidgetDataFromURL('${widgetId}', this.value)"
					       style="width: 100%; box-sizing: border-box; border: none; background: transparent; color: inherit; padding: 2px 5px;">
                        </div>
                    </div>
                </div>
            </div>

            <div class="pg-category">
                <div class="pg-cat-header" onclick="toggleCat(this)">
                    <div class="pg-arrow"></div> Chart Setup (รูปแบบกราฟ)
                </div>
                <div class="pg-cat-content">
                    <div class="pg-row">
                        <div class="pg-label">Chart Type</div>
                        <div class="pg-value"> 
                            <select id="cfg_chartType" onchange="updateWidgetConfig('${widgetId}', 'chartType', this.value)">
                                <option value="bar" ${config.chartType === 'bar' ? 'selected' : ''}>📊 กราฟแท่ง (Bar)</option>
                                <option value="line" ${config.chartType === 'line' ? 'selected' : ''}>📈 กราฟเส้น (Line)</option>
                                <option value="pie" ${config.chartType === 'pie' ? 'selected' : ''}>🥧 กราฟวงกลม (Pie)</option>
                                  <option value="stackedBar" ${config.chartType === 'stackedBar' ? 'selected' : ''}>📊 กราฟแท่งซ้อนทับ (Stacked Bar)</option>
                            </select>
                        </div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">Axis X (แกนแนวนอน)</div>
                        <div class="pg-value"> 
                            <select id="cfg_axisX" onclick="populateAxisOptions(this)" onchange="updateWidgetConfig('${widgetId}', 'axisX', this.value)">
                                <option value="${config.axisX}">${config.axisX || '-- เลือกคอลัมน์ --'}</option>
                            </select>
                        </div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">Axis Y (แกนแนวตั้ง/ตัวเลข)</div>
                        <div class="pg-value"> 
                            <select id="cfg_axisY" onclick="populateAxisOptions(this)" onchange="updateWidgetConfig('${widgetId}', 'axisY', this.value)">
                                <option value="${config.axisY}">${config.axisY || '-- เลือกคอลัมน์ --'}</option>
                            </select>
                        </div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">Group By (แยกเส้น)</div>
                        <div class="pg-value"> 
                            <select id="cfg_groupBy" onclick="populateAxisOptions(this)" onchange="updateWidgetConfig('${widgetId}', 'groupBy', this.value)">
                                <option value="${config.groupBy || ''}">${config.groupBy || '-- ไม่แบ่ง (เส้นเดียว) --'}</option>
                                
                            </select>
                        </div>
                    </div>
				                    <div class="pg-row">
				    <div class="pg-label">Group By 2 (หมวดหมู่ย่อย)</div>
				    <div class="pg-value"> 
				        <select id="cfg_groupBy2" onclick="populateAxisOptions(this)" onchange="updateWidgetConfig('${widgetId}', 'groupBy2', this.value)">
				            <option value="${config.groupBy2 || ''}">${config.groupBy2 || '-- ไม่แบ่ง (ตัวเลือกเสริม) --'}</option>
				            <option value="">-- ไม่แบ่ง (ตัวเลือกเสริม) --</option>
				        </select>
				    </div>
				</div>
                </div>
            </div>

            <div class="pg-category">
                <div class="pg-cat-header" onclick="toggleCat(this)">
                    <div class="pg-arrow"></div> Appearance (ตกแต่ง)
                </div>
                <div class="pg-cat-content">
                    <div class="pg-row">
                        <div class="pg-label">Show Labels</div>
                        <div class="pg-value">
                            <select id="cfg_showLabels" onchange="updateWidgetConfig('${widgetId}', 'showLabels', this.value)">
                                <option value="true" ${config.showLabels === 'true' ? 'selected' : ''}>เปิด (Yes)</option>
                                <option value="false" ${config.showLabels === 'false' ? 'selected' : ''}>ปิด (No)</option>
                            </select>
                        </div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">Theme Color</div>
                        <div class="pg-value">
                            <input type="color" id="cfg_themeColor" value="${config.themeColor}" onchange="updateWidgetConfig('${widgetId}', 'themeColor', this.value)" style="width:100%; border:none; padding:0;">
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>`;

    // ยัด HTML ลงไปใน Pane และเปิด Pane
    pane.innerHTML = html;
    RibbontoggleFilters('pane-fields'); // สมมติว่าเป็นฟังก์ชันเปิดปิด Panel ของคุณ
}





/// 📝 ฟังก์ชันเปลี่ยนกล่องเป็น "Text box"
function addTextBox(widgetId) {
    if (!widgetId) {
        if (selectedItems.length === 0) {
            alert("⚠️ กรุณาคลิกเลือกกล่องก่อนครับ");
            return;
        }
        widgetId = selectedItems[selectedItems.length - 1];
    }

    const boxEl = document.getElementById(widgetId);
    if (!boxEl) return;

    // 🛑 ระบบป้องกัน: เช็คว่ากล่องนี้เคยถูกตั้งค่าเป็นกราฟมาก่อนหรือไม่
    if (dashboardWidgetsConfig[widgetId] && dashboardWidgetsConfig[widgetId].chartType) {
        // ถ้าเป็นกราฟอยู่แล้ว (ไม่ใช่ text) ให้ถามเพื่อความแน่ใจ
        if (dashboardWidgetsConfig[widgetId].chartType !== 'text') {
            const isConfirm = confirm("⚠️ กล่องนี้มีกราฟอยู่แล้ว คุณแน่ใจหรือไม่ที่จะเปลี่ยนเป็น 'ข้อความ' (ข้อมูลกราฟเดิมจะหายไป)?");
            if (!isConfirm) return; // ถ้ากดยกเลิก (Cancel) ให้หยุดการทำงานทันที
        }
    } else {
        // ถ้าเป็นกล่องใหม่ซิงๆ สร้าง Object มารองรับ
        dashboardWidgetsConfig[widgetId] = {};
    }

    // 1. ตั้งค่าชนิดเป็น Text
    dashboardWidgetsConfig[widgetId].chartType = "text";
    
    // ตั้งค่าเริ่มต้น (เช็คว่าถ้ามีค่าเก่าอยู่แล้วจะได้ไม่โดนทับ)
    dashboardWidgetsConfig[widgetId].textContent = dashboardWidgetsConfig[widgetId].textContent || "พิมพ์หัวข้อที่นี่...";
    dashboardWidgetsConfig[widgetId].fontSize = dashboardWidgetsConfig[widgetId].fontSize || "24";
    dashboardWidgetsConfig[widgetId].fontColor = dashboardWidgetsConfig[widgetId].fontColor || "#333333";
    dashboardWidgetsConfig[widgetId].textAlign = dashboardWidgetsConfig[widgetId].textAlign || "center";

    // 2. สั่งเปิดหน้าต่างตั้งค่า Text Box
    openTextSettings(widgetId);
    
    // 3. 🌟 สั่งวาดข้อความลงกล่อง (เปลี่ยนมาใช้ drawTextWidget ตามที่เราแยกไว้)
    drawTextWidget(widgetId);
}

// 📊 ไหนๆ ก็แก้แล้ว ปรับฟังก์ชัน Bar Visual ให้รับค่าแบบเดียวกันไปด้วยเลยครับ
function addBarVisual(widgetId) {
    if (!widgetId) {
        if (selectedItems.length === 0) {
            alert("⚠️ กรุณาคลิกเลือกกล่องว่างบนหน้าจอก่อนครับ");
            return;
        }
        widgetId = selectedItems[selectedItems.length - 1];
    }

    const boxEl = document.getElementById(widgetId);
    if (!boxEl) return;

    if (!dashboardWidgetsConfig[widgetId]) {
        dashboardWidgetsConfig[widgetId] = {};
    }
    dashboardWidgetsConfig[widgetId].chartType = "bar";

    boxEl.innerHTML = `
        <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%; color: #0078d7; pointer-events: none;">
            <svg viewBox="0 0 24 24" width="64" height="64" fill="currentColor"><path d="M5 9.2h3V19H5zM10.6 5h2.8v14h-2.8zm5.6 8H19v6h-2.8z"></path></svg>
            <div style="font-size: 16px; font-weight: bold; margin-top: 10px;">กราฟแท่ง (Bar Chart)</div>
            <div style="font-size: 12px; color: #888; margin-top: 5px;" id="status-${widgetId}">รอการเชื่อมต่อ Data Source...</div>
        </div>
        
        <button onclick="event.stopPropagation(); this.parentElement.remove()" 
                style="position: absolute; top: 5px; right: 5px; border: none; background: none; color: #ff4d4d; cursor: pointer; font-size: 16px;">×</button>
    `;

    openChartSettings(widgetId);
}



// ฟังก์ชันเรียกหน้าต่างตั้งค่าสำหรับ "กล่องข้อความ (Text Box)" โดยเฉพาะ
function openTextSettings(widgetId) {
    const pane = document.getElementById('pane-fields');
    if (!pane) return;

    if (!dashboardWidgetsConfig[widgetId]) {
        dashboardWidgetsConfig[widgetId] = { chartType: "text" };
    }
    
    let config = dashboardWidgetsConfig[widgetId];
    
    // ตั้งค่าเริ่มต้น
    config.textContent = config.textContent || "พิมพ์หัวข้อที่นี่...";
    config.fontSize = config.fontSize || "24";
    config.fontColor = config.fontColor || "#333333";
    config.textAlign = config.textAlign || "center";

    let html = `
    <div id="pg-container" style="height: 100%; overflow-y: auto;">
       
            <div class="pg-header">
        <div class="pg-title">ตั้งค่าข้อความ (Widget: ${widgetId.split('-')[1]})</div>
   <div class="pg-search-box">
    <input type="text" id="pg-search-input" placeholder="Search" oninput="filterProperties()">
    <span style="padding:0 4px; color:#ccc; cursor:pointer;" onclick="clearSearch()">✕</span>
</div>
    </div>
        
        <div class="pg-body">
            <div class="pg-category">
                <div class="pg-cat-header" onclick="toggleCat(this)"><div class="pg-arrow"></div> ข้อความ (Content)</div>
                <div class="pg-cat-content">
                    <div class="pg-row" style="display:block; padding: 5px;">
                        <textarea id="cfg_textContent" onchange="updateWidgetConfig('${widgetId}', 'textContent', this.value)" 
                                  style="width:100%; height:80px; padding:5px; box-sizing:border-box; border:1px solid #ccc; border-radius:4px;">${config.textContent}</textarea>
                    </div>
                </div>
            </div>
            <div class="pg-category">
                <div class="pg-cat-header" onclick="toggleCat(this)"><div class="pg-arrow"></div> รูปแบบ (Appearance)</div>
                <div class="pg-cat-content">
                    <div class="pg-row">
                        <div class="pg-label">ขนาดอักษร (px)</div>
                        <div class="pg-value"><input type="number" value="${config.fontSize}" onchange="updateWidgetConfig('${widgetId}', 'fontSize', this.value)" style="width: 100%;"></div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">สีตัวอักษร</div>
                        <div class="pg-value"><input type="color" value="${config.fontColor}" onchange="updateWidgetConfig('${widgetId}', 'fontColor', this.value)" style="width: 100%; border:none; padding:0;"></div>
                    </div>
                    <div class="pg-row">
                        <div class="pg-label">จัดตำแหน่ง</div>
                        <div class="pg-value">
                            <select onchange="updateWidgetConfig('${widgetId}', 'textAlign', this.value)">
                                <option value="left" ${config.textAlign === 'left' ? 'selected' : ''}>ชิดซ้าย (Left)</option>
                                <option value="center" ${config.textAlign === 'center' ? 'selected' : ''}>กึ่งกลาง (Center)</option>
                                <option value="right" ${config.textAlign === 'right' ? 'selected' : ''}>ชิดขวา (Right)</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;

    pane.innerHTML = html;
    if (typeof RibbontoggleFilters === "function") RibbontoggleFilters('pane-fields');
}



// ฟังก์ชันบันทึกค่าและสั่งให้หน้าจอวาดใหม่ (Real-time)
function updateWidgetConfig(widgetId, key, value) {
    if(!dashboardWidgetsConfig[widgetId]) dashboardWidgetsConfig[widgetId] = {};
    dashboardWidgetsConfig[widgetId][key] = value;
    
    // 🌟 แยกการทำงานชัดเจน! ถ้าเป็น text ให้ไปวาดข้อความ ถ้าเป็นกราฟให้ไปวาดกราฟ
    if (dashboardWidgetsConfig[widgetId].chartType === 'text') {
        drawTextWidget(widgetId);
    } else {
        drawChart(widgetId);
    }
}

// ----------------------------------------------------
// ฟังก์ชันดึงชื่อคอลัมน์มายัดใส่ Dropdown (Axis X, Y, GroupBy 1, GroupBy 2)
// ----------------------------------------------------
function populateAxisOptions(selectElement, widgetId, axisType) {
    // ถ้ามีตัวเลือกอยู่แล้ว (มากกว่า 1) ไม่ต้องดึงใหม่ให้เสียเวลา
    if (selectElement.options.length > 1) return; 

    // ดึงข้อมูลของกล่องนี้มาเช็คว่ามี headers โหลดมาเก็บไว้หรือยัง
    const config = dashboardWidgetsConfig[widgetId];
    if (!config || !config.dataset || !config.dataset.headers) {
        console.log("⏳ ข้อมูลยังไม่พร้อม หรือยังไม่ได้โหลด Data");
        return;
    }

    const headers = config.dataset.headers;
    const currentVal = selectElement.value; // ค่าเดิมที่เคยเลือกไว้

    // 🚀 กำหนด Option บรรทัดแรกสุดตามประเภทของ Dropdown
    let defaultOptionText = "-- เลือกคอลัมน์ --";
    if (axisType === 'groupBy') {
        defaultOptionText = "-- ไม่แบ่ง (เส้นเดียว) --";
    } else if (axisType === 'groupBy2') {
        defaultOptionText = "-- ไม่แบ่ง (ตัวเลือกเสริม) --";
    }

    let optionsHtml = `<option value="">${defaultOptionText}</option>`;
    
    // วนลูปเอา headers มาใส่เป็นตัวเลือก
    headers.forEach(col => {
        let isSelected = (col === currentVal) ? "selected" : "";
        optionsHtml += `<option value="${col}" ${isSelected}>${col}</option>`;
    });

    // อัปเดต HTML ใส่กลับเข้าไปใน Dropdown
    selectElement.innerHTML = optionsHtml;
}
// ฟังก์ชันอัปเดต Dropdown หลังจากโหลดข้อมูลเสร็จ
function updateChartAxisDropdowns(headersArray, widgetId) {
    const config = dashboardWidgetsConfig[widgetId];
    const axisXSelect = document.getElementById('cfg_axisX');
    const axisYSelect = document.getElementById('cfg_axisY');
    const groupBySelect = document.getElementById('cfg_groupBy'); 
    const groupBy2Select = document.getElementById('cfg_groupBy2'); // 🚀 เพิ่มบรรทัดนี้: ดึงช่อง Group By 2

    if (!axisXSelect || !axisYSelect) return;

    // --- สำหรับ แกน X และ แกน Y ---
    let optionsHtml = `<option value="">-- เลือกคอลัมน์ --</option>`;
    headersArray.forEach(headerName => {
        optionsHtml += `<option value="${headerName}">${headerName}</option>`;
    });

    axisXSelect.innerHTML = optionsHtml;
    axisYSelect.innerHTML = optionsHtml;
    
    // 🌟 อัปเดต Dropdown Group By 1
    if (groupBySelect) {
        let groupOptionsHtml = `<option value="">-- ไม่แบ่ง (เส้นเดียว) --</option>`;
        headersArray.forEach(headerName => {
            groupOptionsHtml += `<option value="${headerName}">${headerName}</option>`;
        });
        groupBySelect.innerHTML = groupOptionsHtml;
        if (config.groupBy) groupBySelect.value = config.groupBy;
    }

    // 🚀 อัปเดต Dropdown Group By 2
    if (groupBy2Select) {
        let group2OptionsHtml = `<option value="">-- ไม่แบ่ง (ตัวเลือกเสริม) --</option>`;
        headersArray.forEach(headerName => {
            group2OptionsHtml += `<option value="${headerName}">${headerName}</option>`;
        });
        groupBy2Select.innerHTML = group2OptionsHtml;
        if (config.groupBy2) groupBy2Select.value = config.groupBy2; // คืนค่าเดิมถ้าเคยเลือกไว้
    }

    // คืนค่าเดิมให้กับ X และ Y
    if (config.axisX) axisXSelect.value = config.axisX;
    if (config.axisY) axisYSelect.value = config.axisY;
}

    /**
     * ฟังก์ชันสำหรับเปิด-ปิด Category (Vanilla JS)
     */
    function toggleCat(element) {
        var category = element.parentElement;
        if (category.classList.contains('open')) {
            category.classList.remove('open');
        } else {
            category.classList.add('open');
        }
    }

    /**
     * จัดการเรื่องการคลิกเลือก Row เพื่อความสวยงามเหมือนในรูป
     */
    document.querySelectorAll('.pg-row').forEach(function(row) {
        row.addEventListener('click', function() {
            document.querySelectorAll('.pg-row').forEach(r => r.classList.remove('selected'));
            this.classList.add('selected');
        });
    });
    
    
 // ฟังก์ชันดึงข้อมูล .cheese และนำไป Execute แยกตามกล่อง Widget
async function loadWidgetDataFromURL(widgetId, rawUrl) {
    if (!rawUrl || rawUrl.trim() === "") return;

    // 1. จำ URL ไว้ที่กล่อง
    if(!dashboardWidgetsConfig[widgetId]) dashboardWidgetsConfig[widgetId] = {};
    dashboardWidgetsConfig[widgetId].dataSource = rawUrl;
    
    // 🌟 เช็คก่อนว่าหน้าต่างตั้งค่าเปิดอยู่ไหม (แก้บัคตรงนี้แหละครับ!)
    const inputElement = document.getElementById('cfg_dataSource');
    let oldColor = "";
    if (inputElement) {
        oldColor = inputElement.style.color || "inherit";
        inputElement.style.color = "#f39c12";
        inputElement.value = "⏳ กำลังดึงข้อมูล...";
    }

    // อัปเดตข้อความบนตัวกล่องกราฟ (ถ้ามี)
    const statusText = document.getElementById(`status-${widgetId}`);
    if (statusText) statusText.innerText = "⏳ กำลังดึงข้อมูลจาก Server...";

    try {
        const formattedURL = rawUrl.replace("Idoc:/", "/document-filemanager/");
        
        const cheeseRes = await fetch(formattedURL);
        if (!cheeseRes.ok) throw new Error("โหลดไฟล์ .cheese ไม่สำเร็จ");
        const widgetCheeseData = await cheeseRes.json();

        if (widgetCheeseData.FilterChain.length === 0) {
            throw new Error("ไฟล์นี้ยังไม่มีเงื่อนไขการกรอง");
        }

  
        const execUrl = `/getdatareport-filemanager/execute?${csrfToken}`;

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
		console.log(resultData);


        if (!resultData || !Array.isArray(resultData) || resultData.length === 0) {
            throw new Error("ไม่มีข้อมูลส่งกลับมา");
        }

        const dataObj = resultData[0];
        dashboardWidgetsConfig[widgetId].dataset = dataObj;
        
        // 🌟 ถ้าหน้าต่างตั้งค่าเปิดอยู่ ค่อยเอาคอลัมน์ไปยัดใส่ Dropdown
        if (dataObj.headers && Array.isArray(dataObj.headers)) {
            // ลองอัปเดตแกน X, Y ถ้าหา select ไม่เจอ ฟังก์ชันนั้นจะ return ไปเอง
            updateChartAxisDropdowns(dataObj.headers, widgetId);
        }

        // 🌟 วาดกราฟ!
        if (typeof drawChart === 'function') {
            drawChart(widgetId);
        }
		
        // คืนค่าสีเดิมให้ช่องกรอกลิงก์
        if (inputElement) {
            inputElement.style.color = oldColor;
            inputElement.value = rawUrl;
        }

    } catch (error) {
        console.error(`[Widget: ${widgetId}] ❌ Error:`, error);
        
        if (statusText) statusText.innerText = `❌ Error: ${error.message}`;
        
        if (inputElement) {
            inputElement.style.color = "red";
            inputElement.value = "❌ โหลดไม่ได้";
            setTimeout(() => {
                const currentInput = document.getElementById('cfg_dataSource');
                if (currentInput) {
                    currentInput.style.color = oldColor;
                    currentInput.value = rawUrl;
                }
            }, 2000);
        }
    }
}




// 1. ➕ ฟังก์ชันเพิ่ม "กล่องเปล่า" (รับแค่ชื่อโซน)
function addWidgetToZone(zoneId) {
    const zone = document.getElementById(zoneId);
    if (!zone) return;

    const widgetId = "wdg-" + Date.now();

    // สร้างกล่องเปล่าๆ มีแค่ข้อความตรงกลาง
    const html = `
        <div class="dash-item" id="${widgetId}" onclick="toggleSelect('${widgetId}')">
            <div style="color: #adb5bd; font-size: 14px; text-align: center; pointer-events: none;">
                <div style="font-size: 24px;">+</div>
                กล่องว่าง<br><small>(รอกำหนดชนิด)</small>
            </div>
            
            <button onclick="event.stopPropagation(); this.parentElement.remove()" 
                    style="position: absolute; top: 5px; right: 5px; border: none; background: none; color: #ff4d4d; cursor: pointer; font-size: 16px;">
                ×
            </button>
        </div>
    `;
    
    // สำคัญ: ต้องใส่ position: relative ให้กล่องด้วย เพื่อให้ปุ่ม (×) ลอยอยู่มุมขวาบนได้
    zone.insertAdjacentHTML('beforeend', html);
    document.getElementById(widgetId).style.position = "relative";
}
let selectedItems = []; // ตัวแปรคอยจำว่าคลิกเลือกกล่องไหนไว้บ้าง

// 🖱️ ฟังก์ชันคลิกเลือกกล่อง (เปิด/ปิด ขอบสีฟ้า และเรียกเครื่องมือ)
function toggleSelect(id) {
    const el = document.getElementById(id);
    if (!el) return;

    // 🧹 เคลียร์เมนูเครื่องมือเก่าออกก่อนเสมอ! (ตามที่คุณแนะนำเลยครับ)
    const vizGrid = document.getElementById("viz-grid");
    if (vizGrid) vizGrid.innerHTML = ""; 

    if (el.classList.contains('selected')) {
        // ถ้ามีขอบฟ้าอยู่แล้ว (กดซ้ำ) ให้เอาออก
        el.classList.remove('selected');
        selectedItems = selectedItems.filter(item => item !== id);
        
        // ถ้ายังเหลือกล่องอื่นที่ถูกเลือกค้างไว้ ให้โชว์เมนูของกล่องล่าสุด
        if (selectedItems.length > 0) {
            let lastSelected = selectedItems[selectedItems.length - 1];
            propertyicon(2, lastSelected, 0); // 🌟 เรียก type 2
        }
    } else {
        // ถ้ายังไม่มีขอบ ให้ใส่สีฟ้าและจำไว้ใน Array
        el.classList.add('selected');
        selectedItems.push(id);

        // 🌟 เรียกเมนูเครื่องมือขึ้นมาใหม่ โดยใช้ type 2 (แยกจากของเดิม)
        propertyicon(2, id, 0); 
    }
}


// 3. 🔗 ฟังก์ชันผสานกล่อง (Merge)
function mergeSelected() {
    if (selectedItems.length < 2) {
        alert("⚠️ กรุณาคลิกเลือกกล่อง (ให้ขึ้นขอบสีฟ้า) อย่างน้อย 2 กล่องเพื่อผสานครับ");
        return;
    }

    // เอา "กล่องแรก" ที่เลือก เป็นตัวหลักที่จะขยายร่าง
    const firstId = selectedItems[0];
    const firstEl = document.getElementById(firstId);
    
    // สั่งให้กล่องแรก กินพื้นที่เท่ากับจำนวนกล่องทั้งหมดที่เลือกมา
    let currentFlex = parseInt(window.getComputedStyle(firstEl).flexGrow) || 1;
    firstEl.style.flex = currentFlex + (selectedItems.length - 1); 
    
    // ลบกล่องที่เหลือทิ้งไปเลย!
    for (let i = 1; i < selectedItems.length; i++) {
        const otherEl = document.getElementById(selectedItems[i]);
        if (otherEl) otherEl.remove();
    }

    // ล้างค่าที่จำไว้กลับเป็น 0 (เตรียมพร้อมสำหรับการ Merge รอบหน้า)
    selectedItems = [];
    firstEl.classList.remove('selected');
}

// 4. 🗂️ ฟังก์ชันสลับ Layout (เช่น 1 = บนเล็ก/ล่างใหญ่, 2 = ครึ่งๆ)
function changeDashboardLayout(typeNumber) {
    const container = document.getElementById("table-container3");
    if (!container) return;
    
    // ล้าง Class Layout เดิมทิ้ง
    container.className = ""; 
    
    // ใส่ Class Layout ใหม่เข้าไป
    container.classList.add(`layout-type-${typeNumber}`);
}




// ฟังก์ชันสำหรับรวบรวมโครงสร้างและการตั้งค่าทั้งหมดของ Dashboard
function exportDashboardState() {
    // 1. เก็บโครงสร้าง Layout หลัก
    const container = document.getElementById("table-container3"); // อ้างอิงจากฟังก์ชัน changeDashboardLayout ของคุณ
    
    let dashboardState = {
        layoutClass: container ? container.className : "",
        widgets: []
    };

    // 2. กวาดหากล่อง Widget ทั้งหมดบนหน้าจอ
    const widgetElements = document.querySelectorAll('.dash-item');
    
    widgetElements.forEach(el => {
        const wId = el.id;
        const parentId = el.parentElement ? el.parentElement.id : "";
        
        // ดึงค่าขนาดของกล่อง (ที่อาจจะโดน Merge มา)
        const flexValue = el.style.flex || el.style.flexGrow || "1";

        // ดึงค่า Config จากตัวแปรกลางที่เราทำไว้
        let wConfig = {};
        if (typeof dashboardWidgetsConfig !== 'undefined' && dashboardWidgetsConfig[wId]) {
            // โคลน Object มา เพื่อไม่ให้กระทบของจริง
            wConfig = { ...dashboardWidgetsConfig[wId] };
            
            // 🌟 สำคัญมาก: ลบข้อมูลดิบ (dataset) ทิ้งก่อนเซฟ! เพื่อไม่ให้ไฟล์เซฟบวม
            if (wConfig.dataset) {
                delete wConfig.dataset; 
            }
        }

        // 3. แพ็คข้อมูลลง Array
        dashboardState.widgets.push({
            id: wId,
            zoneId: parentId,
            flex: flexValue,
            config: wConfig
        });
    });

    return dashboardState;
}


// ฟังก์ชันสำหรับกดปุ่ม Save แล้วดาวน์โหลดเป็นไฟล์ (หรือส่ง API)
function saveDashboardToFile() {
    // 1. เรียกฟังก์ชันรวบรวมข้อมูล
    const stateObj = exportDashboardState();
    
    // 2. แปลงเป็นข้อความ JSON ให้ดูอ่านง่าย
    const jsonString = JSON.stringify(stateObj, null, 4);
    
    console.log("💾 ข้อมูล Dashboard ที่พร้อมบันทึก:", stateObj);

    // ==========================================
    // วิธีที่ A: โหลดเป็นไฟล์ลงเครื่อง (ใช้สำหรับทดสอบ หรือให้ User โหลดไฟล์ไปเก็บ)
    // ==========================================
    const blob = new Blob([jsonString], { type: "application/json" });
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "my_report.dash"; // 🌟 นามสกุลไฟล์ Dashboard ของเรา
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);

    // ==========================================
    // วิธีที่ B: ถ้าจะส่งไปเซฟบน Database จริงๆ ให้ใช้ Fetch API ตรงนี้ครับ
    // ==========================================
    /*
    fetch('/save-dashboard-endpoint', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: jsonString
    }).then(res => alert('บันทึกสำเร็จ!'));
    */
}


// ฟังก์ชันอ่านไฟล์เมื่อมีการอัปโหลด
function loadDashboardFromFile(event) {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        try {
            // 1. แปลงข้อความจากไฟล์กลับเป็น JSON Object
            const stateObj = JSON.parse(e.target.result);
            
            // 2. ส่งให้ฟังก์ชันประกอบร่างหน้าจอ
            restoreDashboardState(stateObj);
            
        } catch (error) {
            console.error("❌ รูปแบบไฟล์ไม่ถูกต้อง:", error);
            alert("ไฟล์ Dashboard ไม่ถูกต้อง หรือเกิดข้อผิดพลาดในการอ่านไฟล์");
        }
        
        // ล้างค่า input เผื่อผู้ใช้จะอัปโหลดไฟล์เดิมซ้ำอีกรอบ
        event.target.value = ''; 
    };
    reader.readAsText(file);
}



// ฟังก์ชันดึงไฟล์ JSON Dashboard จาก URL (แทนการอัปโหลดไฟล์)
function loadDashboardFromUrl(fileUrl, segmentationId) {
    console.log("กำลังโหลด Dashboard จาก URL: ", fileUrl);

    // ใช้ Fetch API เพื่อดึงข้อมูลไฟล์จาก Server
    fetch(fileUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP error! Status: " + response.status);
            }
            // แปลง Response ที่ได้ให้เป็น JSON ทันที (ไม่ต้องใช้ JSON.parse อีก)
            return response.json(); 
        })
        .then(stateObj => {
            console.log("✅ โหลดไฟล์ Dashboard สำเร็จ!");
            
            // ส่ง Object ไปให้ฟังก์ชันประกอบร่างหน้าจอของคุณทำงานต่อ
            restoreDashboardState(stateObj);

            // (Optional) หากต้องการใช้ segmentationId ทำอะไรต่อ เช่น แสดง Tab ที่ถูกต้อง
            // if (segmentationId) { ... }
        })
        .catch(error => {
            console.error("❌ ไม่สามารถโหลดไฟล์ Dashboard ได้:", error);
            // แสดงแจ้งเตือนกรณีดึงไฟล์ไม่สำเร็จ (เช่น ไฟล์โดนลบ หรือ URL ผิด)
            document.getElementById('table-container3').innerHTML = 
                `<div style="color: red; padding: 20px; text-align: center;">
                    <b>ไม่สามารถโหลดรายงานได้</b><br>
                    URL: ${fileUrl} <br>
                    (ไฟล์อาจถูกลบหรือไม่มีสิทธิ์เข้าถึง)
                </div>`;
        });
}


// ----------------------------------------------------
// 1. ฟังก์ชันประกอบร่าง Dashboard กลับคืนมา (ตัวที่ Error หาไม่เจอ)
// ----------------------------------------------------
function restoreDashboardState(stateObj) {
    // 1. จัดการ Layout หลัก
    const container = document.getElementById("table-container3");
    if (container) {
        container.className = stateObj.layoutClass || "";
    }

    // 2. ล้างหน้าจอเดิมทิ้งทั้งหมด (เคลียร์กล่องเก่า)
    document.querySelectorAll('.dash-item').forEach(el => el.remove());
    if (typeof dashboardWidgetsConfig !== 'undefined') {
        dashboardWidgetsConfig = {}; // ล้างตัวแปรจำค่า
    }
    if (typeof selectedItems !== 'undefined') {
        selectedItems = []; // เคลียร์สถานะการเลือก
    }

    // 3. วนลูปสร้างกล่องใหม่ตามไฟล์ที่เซฟไว้
    if (stateObj.widgets && Array.isArray(stateObj.widgets)) {
        stateObj.widgets.forEach(widget => {
            // หาโซนที่จะเอากล่องไปวาง
            const zone = document.getElementById(widget.zoneId); 
            if (!zone) {
                console.warn("⚠️ หา Zone ไม่เจอสำหรับกล่อง:", widget.id);
                return;
            }

            // คืนค่า Config ลงในหน่วยความจำ
            dashboardWidgetsConfig[widget.id] = widget.config || {};

            // สร้าง Element กล่อง
            const el = document.createElement('div');
            el.className = 'dash-item';
            el.id = widget.id;
            el.style.flex = widget.flex || "1"; // คืนค่าความกว้าง
            el.style.position = "relative";
            el.onclick = () => toggleSelect(widget.id);

            zone.appendChild(el);

            // 4. สั่งให้กล่องวาดหน้าตาตัวเองขึ้นมาใหม่
            renderWidgetUI(widget.id);
        });
    }

    console.log("🎉 คืนชีพ Dashboard สำเร็จ!");
}

// ----------------------------------------------------
// 2. ฟังก์ชันช่วยวาดหน้าตากล่อง (เรียกจากฟังก์ชันด้านบนอีกที)
// ----------------------------------------------------
function renderWidgetUI(widgetId) {
    const config = dashboardWidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);
    if (!boxEl) return;

    // ปุ่มกากบาทที่ต้องมีทุกกล่อง
    const closeBtnHtml = `<button onclick="event.stopPropagation(); this.parentElement.remove()" 
            style="position: absolute; top: 5px; right: 5px; border: none; background: none; color: #ff4d4d; cursor: pointer; font-size: 16px;">×</button>`;

    if (!config || !config.chartType) {
        // กรณีเป็นกล่องเปล่าๆ ที่ยังไม่ตั้งค่า
        boxEl.innerHTML = `
            <div style="color: #adb5bd; font-size: 14px; text-align: center; pointer-events: none; padding-top:20px;">
                <div style="font-size: 24px;">+</div>
                กล่องว่าง<br><small>(รอกำหนดชนิด)</small>
            </div>
            ${closeBtnHtml}
        `;
        return; 
    } 
    else if (config.chartType === "text") {
        // 🌟 1. วาดโครงสร้างกล่อง Text
        boxEl.innerHTML = `
            <div style="padding: 10px; width: 100%; height: 100%; box-sizing: border-box; position: relative;">
                <textarea id="text-${widgetId}" placeholder="พิมพ์ข้อความที่นี่..." style="width: 100%; height: 100%; border: 1px dashed #ccc; padding: 10px; box-sizing: border-box;"></textarea>
            </div>
            ${closeBtnHtml}
        `;
        
        // 🌟 2. สั่งวาดข้อความ (ดึงค่าเดิมมาใส่) ทันทีเลย! ไม่ต้องรอโหลด Data
        if (typeof drawTextWidget === 'function') {
            drawTextWidget(widgetId);
        }
    } 
    else {
        // กรณีเป็นกราฟ (bar, line, pie)
        boxEl.innerHTML = `
            <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%; color: #0078d7; pointer-events: none;">
                <svg viewBox="0 0 24 24" width="64" height="64" fill="currentColor"><path d="M5 9.2h3V19H5zM10.6 5h2.8v14h-2.8zm5.6 8H19v6h-2.8z"></path></svg>
                <div style="font-size: 16px; font-weight: bold; margin-top: 10px;">${config.chartType.toUpperCase()} Chart</div>
                <div style="font-size: 12px; color: #888; margin-top: 5px;" id="status-${widgetId}">กำลังเตรียมข้อมูล...</div>
            </div>
            ${closeBtnHtml}
        `;

        // 🌟 3. เฉพาะกราฟเท่านั้น ที่ต้องไปดึง Data จาก URL
        if (config.dataSource) {
            if (typeof loadWidgetDataFromURL === 'function') {
                 loadWidgetDataFromURL(widgetId, config.dataSource);
            } else {
                 console.warn("⚠️ หาฟังก์ชัน loadWidgetDataFromURL ไม่เจอ");
            }
        } else {
            const statusEl = document.getElementById(`status-${widgetId}`);
            if(statusEl) statusEl.innerText = "รอการเชื่อมต่อ Data Source...";
        }
    }
}

// ฟังก์ชันสำหรับวาด "กล่องข้อความ" โดยเฉพาะ (แยกออกจากกราฟ)
function drawTextWidget(widgetId) {
    const config = dashboardWidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);
    
    if (!boxEl || !config) return;

    // แปลง \n เป็น <br>
    const textToDisplay = (config.textContent || "พิมพ์หัวข้อที่นี่...").replace(/\n/g, '<br>');
    
    // จัดตำแหน่ง
    let alignCss = 'center';
    let textAlignCss = 'center';
    if (config.textAlign === 'left') { alignCss = 'flex-start'; textAlignCss = 'left'; }
    if (config.textAlign === 'right') { alignCss = 'flex-end'; textAlignCss = 'right'; }

    // วาดลงกล่อง
    boxEl.innerHTML = `
        <div style="width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; align-items: ${alignCss}; 
             color: ${config.fontColor || '#333333'}; 
             font-size: ${config.fontSize || 24}px; 
             padding: 15px; box-sizing: border-box; font-weight: bold; overflow: hidden; 
             word-break: break-word; text-align: ${textAlignCss};">
            ${textToDisplay}
        </div>
        <button onclick="event.stopPropagation(); this.parentElement.remove()" 
                style="position: absolute; top: 5px; right: 5px; border: none; background: none; color: #ff4d4d; cursor: pointer; font-size: 16px;">×</button>
    `;
}






// ฟังก์ชันสำหรับดึงข้อมูลในความทรงจำ มาวาดเป็นกราฟลงบนกล่อง
function drawChart(widgetId) {
    const config = dashboardWidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);

    if (!boxEl || !config || !config.dataset || !config.dataset.data) return;
    if (!config.axisX || !config.axisY) return;

    const rawData = config.dataset.data;

    // 🧹 1. ล้างข้อมูล
    const cleanedData = rawData.map(row => {
        let newRow = { ...row };
        let rawXVal = row[config.axisX];
        let xVal = rawXVal !== null && rawXVal !== undefined ? String(rawXVal).replace(/(<([^>]+)>)/gi, "").trim() : null;

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

    const hasTwoGroups = (config.groupBy && config.groupBy !== "") && (config.groupBy2 && config.groupBy2 !== "");
    const getGroup1 = (row) => config.groupBy ? String(row[config.groupBy] || 'ไม่ระบุ') : "";
    const getGroup2 = (row) => config.groupBy2 ? String(row[config.groupBy2] || 'ไม่ระบุ') : "";

    // 🚀 2. หมัดฮุก! เปลี่ยนวิธีสร้างแกน X: ถ้ามี 2 กลุ่ม ให้เอา "วันที่" + "ชื่อคน" มาต่อกันเป็นแกน X ไปเลย!
    const getXKey = (row) => hasTwoGroups ? (row._cleanX + '|' + getGroup1(row)) : row._cleanX;
    const uniqueXKeys = [...new Set(cleanedData.map(getXKey))].sort();

    let chartDatasets = [];

    if (hasTwoGroups) {
        // 🌟 กรณีมี 2 กลุ่ม: Legend เป็น Group 2 (ประเภท), แกน X เป็น (วันที่+ชื่อ)
        const uniqueG2 = [...new Set(cleanedData.map(getGroup2))].filter(Boolean);

        chartDatasets = uniqueG2.map((g2Name, index) => {
            const groupData = uniqueXKeys.map(xKey => {
                const foundRow = cleanedData.find(row => getXKey(row) === xKey && getGroup2(row) === g2Name);
                return foundRow ? foundRow._cleanY : null;
            });

            const color = `hsl(${(index * 360) / (uniqueG2.length || 1)}, 70%, 50%)`;

            return {
                label: g2Name, // ชื่อบน Legend คือสถานะ
                data: groupData,
                backgroundColor: config.chartType === 'pie' ? undefined : color,
                borderColor: color,
                borderWidth: 2,
                fill: (config.chartType === 'bar' || config.chartType === 'stackedBar'),
                tension: 0.1
            };
        });

    } else if (config.groupBy && config.groupBy !== "") {
        // 🌟 กรณีมีกลุ่มเดียว: Legend เป็น Group 1 (ชื่อคน), แกน X เป็นวันที่
        const uniqueG1 = [...new Set(cleanedData.map(getGroup1))].filter(Boolean);

        chartDatasets = uniqueG1.map((g1Name, index) => {
            const groupData = uniqueXKeys.map(xKey => {
                const foundRow = cleanedData.find(row => getXKey(row) === xKey && getGroup1(row) === g1Name);
                return foundRow ? foundRow._cleanY : null;
            });

            const color = `hsl(${(index * 360) / (uniqueG1.length || 1)}, 70%, 50%)`;

            return {
                label: g1Name,
                data: groupData,
                backgroundColor: config.chartType === 'pie' ? undefined : color,
                borderColor: color,
                borderWidth: 2,
                fill: (config.chartType === 'bar' || config.chartType === 'stackedBar'),
                tension: 0.1
            };
        });

    } else {
        // 🌟 กรณีไม่แบ่งกลุ่มเลย
        const dataValues = uniqueXKeys.map(xKey => {
            const foundRow = cleanedData.find(row => getXKey(row) === xKey);
            return foundRow ? foundRow._cleanY : 0;
        });

        let bgColors = config.themeColor || '#0078d7';
        if (config.chartType === 'pie' || config.chartType === 'doughnut') {
            bgColors = uniqueXKeys.map((_, i) => `hsl(${(i * 360) / uniqueXKeys.length}, 70%, 50%)`);
        }

        chartDatasets = [{
            label: config.axisY,
            data: dataValues,
            backgroundColor: bgColors,
            borderColor: config.chartType === 'pie' ? '#ffffff' : (config.themeColor || '#0078d7'),
            borderWidth: 2,
            fill: (config.chartType === 'bar' || config.chartType === 'stackedBar'),
            tension: 0.1
        }];
    }

    // 🚀 3. แปลงข้อความแกน X ให้โชว์ 2 บรรทัด (วันที่ ขึ้นบรรทัดใหม่เป็น ชื่อคน)
    const displayXLabels = uniqueXKeys.map(key => {
        if (hasTwoGroups) {
            let [datePart, namePart] = key.split('|');
            if (datePart.match(/^\d{4}-\d{2}-\d{2}$/)) {
                let parts = datePart.split('-');
                datePart = `${parts[2]}/${parts[1]}/${parts[0]}`;
            }
            // ใส่ Array ทำให้ Chart.js ปัดขึ้นบรรทัดใหม่ให้อัตโนมัติ!
            return [datePart, `👤 ${namePart}`]; 
        } else {
            if (key.match(/^\d{4}-\d{2}-\d{2}$/)) {
                let parts = key.split('-');
                return `${parts[2]}/${parts[1]}/${parts[0]}`;
            }
            return key;
        }
    });

    boxEl.innerHTML = `
        <div style="width: 100%; height: 100%; padding: 15px; box-sizing: border-box; position: relative;">
            <canvas id="canvas-${widgetId}"></canvas>
        </div>
        <button onclick="event.stopPropagation(); this.parentElement.remove()"
                style="position: absolute; top: 5px; right: 5px; border: none; background: none; color: #ff4d4d; cursor: pointer; font-size: 16px;">×</button>
    `;

    if (chartInstances[widgetId]) chartInstances[widgetId].destroy();

    let actualChartType = config.chartType === 'stackedBar' ? 'bar' : config.chartType;
    let isStacked = config.chartType === 'stackedBar'; // ให้ Chart.js จัดการ Stack อัตโนมัติ

    const ctx = document.getElementById(`canvas-${widgetId}`).getContext('2d');
    chartInstances[widgetId] = new Chart(ctx, {
        type: actualChartType,
        data: {
            labels: displayXLabels,
            datasets: chartDatasets
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            spanGaps: true,
            interaction: { mode: 'index', intersect: false },
            plugins: {
                legend: {
                    display: config.showLabels !== "false",
                    position: 'top'
                },
                tooltip: {
                    enabled: true
                }
            },
            scales: (actualChartType === 'pie' || actualChartType === 'doughnut') ? {} : {
                x: {
                    stacked: isStacked,
                    ticks: {
                        autoSkip: false, // บังคับโชว์ทุกป้าย เพราะมีชื่อคนด้วย
                        maxRotation: 0,  // ไม่เอียงข้อความ จะได้อ่าน 2 บรรทัดง่ายๆ
                        minRotation: 0,
                        font: { size: 11 }
                    }
                },
                y: { stacked: isStacked, beginAtZero: true }
            }
        }
    });
}