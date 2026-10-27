let viewer_WidgetsConfig = {}; 
let viewer_ChartInstances = {};
let viewer_SelectedItems = []; 
var csrfToken_use;
var alignCss = 'center';
var textAlignCss = 'center';
// 🚀 1. ตัวแปรสำหรับ "พักข้อมูล" (Cache) และจำสถานะ
let viewer_LoadedCheeseData = {}; // เก็บข้อมูล .cheese ที่ดึงมาแล้ว (Key คือ widgetId)
let viewer_ActiveWidgetId = null; // จำว่าตอนนี้คลิกดูกราฟไหนอยู่
let report_id;
// ตัวแปรสำหรับระบบเปลี่ยนหน้า (Pagination)
let viewer_CurrentPage = 1;
let viewer_RowsPerPage = 100;
// ประกาศตัวแปร Global เพื่อคอยจับตาดูว่ามีการแก้ "เงื่อนไขข้อมูล (DB)" หรือไม่
window.viewer_NeedsFetch = window.viewer_NeedsFetch || {};


// ==========================================
// 🧹 ฟังก์ชันล้างค่าที่จำไว้ (Reset Filter)
// ==========================================
async function viewer_clearFilter() {
    // 1. ตรวจสอบว่ากำลังเลือกกราฟไหนอยู่
    if (!viewer_ActiveWidgetId) {
        alert("กรุณาคลิกเลือกกราฟที่ต้องการล้างค่าก่อนครับ");
        return;
    }

    const id = viewer_ActiveWidgetId;
    const storageKey = `custom_filter_${report_id}_${id}`;

    // 2. ลบข้อมูลความจำเก่าทิ้ง ทั้งในเครื่องผู้ใช้และใน Cache ของระบบ
    localStorage.removeItem(storageKey);
    delete viewer_LoadedCheeseData[id];
    console.log(`🗑️ ล้างค่าความจำตัวกรองของ ${id} เรียบร้อยแล้ว`);

    try {
        // 3. สั่งให้ระบบโหลดข้อมูลค่าเริ่มต้น (Default) กลับมาใหม่
        // โดยดึงจากไฟล์ .cheese ดั้งเดิม
        const config = viewer_WidgetsConfig[id];
        if (config && config.dataSource) {
            const fileUrl = config.dataSource.replace("Idoc:/", "/document-filemanager/");
            console.log("📥 กำลังดาวน์โหลดค่าเริ่มต้นใหม่จาก: ", fileUrl);
            
            const res = await fetch(fileUrl);
            if (!res.ok) throw new Error("ไม่สามารถดาวน์โหลดไฟล์ .cheese ได้");
            
            const cheeseJson = await res.json();
            viewer_LoadedCheeseData[id] = cheeseJson;
            
            // 4. วาด UI หน้าต่างตัวกรองใหม่ ให้กลับเป็นค่าเริ่มต้น
            const container = document.getElementById('dynamicQuickEditContainer');
            if (container) {
                // รอให้มั่นใจว่าโหลดค่าเสร็จ ค่อยวาด UI ใหม่
                viewer_renderQuickFilterUI(cheeseJson, id);
            }
            
            // 5. วาดกราฟใหม่ (ถ้ามีฟังก์ชันวาดกราฟ เช่น viewer_drawChart)
            if (typeof viewer_drawChart === 'function') {
                viewer_drawChart(id);
            }

            alert("ล้างค่าสำเร็จ! กลับไปใช้ค่าเริ่มต้นแล้วครับ");
        }
    } catch (error) {
        console.error("❌ เกิดข้อผิดพลาดในการโหลดค่าเริ่มต้นใหม่:", error);
        alert("ล้างค่าสำเร็จ แต่ไม่สามารถดึงค่าเริ่มต้นกลับมาได้ กรุณารีเฟรชหน้าจอครับ");
    }
}

// ==========================================
// 1. ฟังก์ชันคลิกกล่อง (ส่งแค่ ID ไปให้เครื่องจักรตาราง)
// ==========================================
async function viewer_toggleSelect(id) {
    const el = document.getElementById(id);
    if (!el) return;

    // 🌟 จำไว้ว่าตอนนี้ผู้ใช้กำลังดูกราฟ (Widget) ID อะไรอยู่
    viewer_ActiveWidgetId = id;
    const config = viewer_WidgetsConfig[id];
    
    // โชว์ข้อมูลตาราง (ของเดิม)
    if (config && config.dataset && config.dataset.data) {
        console.log(`[Viewer] แสดงข้อมูลของกล่อง ${id} ลงในตาราง`);
        viewer_renderDataToTable(id); 
    } else {
        const container = document.getElementById("table-containers");
        if(container) container.innerHTML = `<div style="padding: 20px; text-align: center; color: #888;">ไม่มีข้อมูลลูกสำหรับกราฟนี้</div>`;
    }

    // ==============================================================
    // 🌟 ส่วนที่ปรับปรุงใหม่: เช็ค LocalStorage ก่อน ค่อยไปโหลดไฟล์ .cheese
    // ==============================================================
    if (config && config.dataSource && !viewer_LoadedCheeseData[id]) {
        try {
            // 1. เช็คในเครื่องผู้ใช้ (LocalStorage) ก่อนว่าเคยเซฟตัวกรองไว้ไหม
           const storageKey = `custom_filter_${report_id}_${id}`;
            const savedFilter = localStorage.getItem(storageKey);

            if (savedFilter) {
                // 👉 1.1 ถ้า "มี" ให้เอาของในเครื่องมาพักไว้เลย (ไม่ต้องเปลืองเน็ตโหลดไฟล์)
                viewer_LoadedCheeseData[id] = JSON.parse(savedFilter);
                console.log(`♻️ ดึงตัวกรองของ ${id} จากเครื่องผู้ใช้สำเร็จ`);
            } else {
                // 👉 1.2 ถ้า "ไม่มี" (เพิ่งเคยเปิดครั้งแรก หรือไม่เคยเซฟ) ค่อยโหลดไฟล์ .cheese
                const fileUrl = config.dataSource.replace("Idoc:/", "/document-filemanager/");
                console.log("📥 กำลังดาวน์โหลดไฟล์ .cheese มาพักไว้จาก: ", fileUrl);
                
                const res = await fetch(fileUrl);
                if (!res.ok) throw new Error("ไม่สามารถดาวน์โหลดไฟล์ .cheese ได้");
                
                const cheeseJson = await res.json();
                
                // พักข้อมูลลง Cache
                viewer_LoadedCheeseData[id] = cheeseJson;
                console.log(`✅ โหลดข้อมูลตัวกรองตั้งต้นของ ${id} เรียบร้อย`);
            }
            
        } catch (error) {
            console.error("❌ โหลดไฟล์การตั้งค่าตัวกรองล้มเหลว:", error);
            viewer_LoadedCheeseData[id] = null; // กันพัง
        }
    }
}

function openFilterGraphModal() {
    // 1. ตรวจสอบก่อนว่าผู้ใช้ได้คลิกเลือกกราฟหรือยัง
    if (!viewer_ActiveWidgetId) {
        alert("กรุณาคลิกเลือกกราฟที่ต้องการดูข้อมูลตัวกรองก่อนครับ");
        return;
    }

    // 2. ⚡️ โค้ดสำหรับเปิดหน้าต่าง Modal ของคุณ ⚡️
    // สมมติว่ามีฟังก์ชันโชว์ Modal หรือการทำให้ Container โผล่ขึ้นมา (เขียนโค้ดของคุณตรงนี้)
    // document.getElementById('myFilterModal').style.display = 'block';

    // 3. ดึงข้อมูล JSON ที่ "พักไว้" ออกมา
    const cheeseJson = viewer_LoadedCheeseData[viewer_ActiveWidgetId];

    // 4. สั่งวาด UI (ตอนนี้ DOM ของ dynamicQuickEditContainer ถูกสร้างมาพร้อมรับแล้ว)
    if (cheeseJson) {
        // ใช้ setTimeout เผื่อไว้ 50ms ให้เบราว์เซอร์ Render DOM ให้เสร็จก่อนแล้วค่อยยัด HTML ลงไป
        setTimeout(() => {
            viewer_renderQuickFilterUI(cheeseJson, viewer_ActiveWidgetId);
        }, 50);
    } else {
        // กรณีไม่มีไฟล์ .cheese ผูกไว้ หรือโหลดล้มเหลว
        setTimeout(() => {
            const container = document.getElementById('dynamicQuickEditContainer');
            if (container) container.innerHTML = "<div style='padding: 20px; color: red;'>ไม่พบข้อมูลเงื่อนไขตัวกรองสำหรับกราฟนี้</div>";
        }, 50);
    }
}



function viewer_renderQuickFilterUI(cheeseData, widgetId) {
    const container = document.getElementById('dynamicQuickEditContainer');
    if (!container) return;

    const config = viewer_WidgetsConfig[widgetId];
    const headers = (config && config.dataset && config.dataset.headers) ? config.dataset.headers : [];

    // จำค่าโหมดล่าสุดไว้ (ถ้าไม่มีให้เริ่มที่โหมด 1)
    if (!window.viewer_CurrentMode) window.viewer_CurrentMode = 1;
    
    // เตรียม SVG Icon สำหรับแต่ละเมนู
    const modes = [
        { 
            id: 1, 
            label: 'ค้นหา (Search)', 
            svg: `<svg viewBox="0 0 24 24" width="20" height="20" fill="currentColor"><path d="M4 4h16v16H4V4zm2 2v3h12V6H6zm0 5v3h12v-3H6zm0 5v3h12v-3H6z"></path></svg>` 
        },
        { 
            id: 2, 
            label: 'กรองข้อมูล (Filter)', 
            svg: `<svg viewBox="0 0 24 24" width="20" height="20" fill="currentColor"><path d="M10 18h4v-2h-4v2zM3 6v2h18V6H3zm3 7h12v-2H6v2z"></path></svg>` 
        },
        { 
            id: 3, 
            label: 'วิเคราะห์ (Analysis)', 
            svg: `<svg viewBox="0 0 24 24" width="20" height="20" fill="currentColor"><path d="M11 2v20c-5.07-.5-9-4.79-9-10s3.93-9.5 9-10zm2.03 0v8.99H22c-.47-4.74-4.24-8.52-8.97-8.99zm0 11.01V22c4.74-.47 8.5-4.25 8.97-8.99h-8.97z"></path></svg>` 
        },
        { 
            id: 4, 
            label: 'มุมมอง (View)', 
            svg: `<svg viewBox="0 0 24 24" width="20" height="20" fill="currentColor"><path d="M19.14 12.94c.04-.3.06-.61.06-.94 0-.32-.02-.64-.06-.94l2.03-1.58c.18-.14.23-.41.12-.61l-1.92-3.32c-.12-.22-.37-.29-.59-.22l-2.39.96c-.5-.38-1.03-.7-1.62-.94l-.36-2.54c-.04-.24-.24-.41-.48-.41h-3.84c-.24 0-.43.17-.47.41l-.36 2.54c-.59.24-1.13.57-1.62.94l-2.39-.96c-.22-.08-.47 0-.59.22L2.74 8.87c-.12.21-.08.47.12.61l2.03 1.58c-.05.3-.09.63-.09.94s.02.64.06.94l-2.03 1.58c-.18.14-.23.41-.12.61l1.92 3.32c.12.22.37.29.59.22l2.39-.96c.5.38 1.03.7 1.62.94l.36 2.54c.05.24.24.41.48.41h3.84c.24 0 .43-.17.47-.41l.36-2.54c.59-.24 1.13-.56 1.62-.94l2.39.96c.22.08.47 0 .59-.22l1.92-3.32c.12-.22.07-.49-.12-.61l-2.01-1.58zM12 15.6c-1.98 0-3.6-1.62-3.6-3.6s1.62-3.6 3.6-3.6 3.6 1.62 3.6 3.6-1.62 3.6-3.6 3.6z"></path></svg>` 
        }
    ];

    // เริ่มต้นโครงสร้าง Flexbox
    let html = `<div style="display: flex; gap: 20px; background: white; border: 1px solid #e2e8f0; border-radius: 8px; padding: 15px; min-height: 450px;">`;

    // --- ส่วนที่ 1: เมนูด้านซ้าย (Left Sidebar) ---
    html += `<div style="width: 220px; flex-shrink: 0; border-right: 1px solid #e2e8f0; padding-right: 15px; display: flex; flex-direction: column; gap: 8px;">`;
    
    modes.forEach(m => {
        let isActive = (window.viewer_CurrentMode === m.id);
        let activeBg = isActive ? '#e0f2fe' : 'transparent';
        let activeColor = isActive ? '#0284c7' : '#475569';
        let activeBorder = isActive ? 'border-left: 4px solid #0284c7;' : 'border-left: 4px solid transparent;';

        html += `
            <div onclick="window.viewer_CurrentMode=${m.id}; viewer_renderQuickFilterUI(viewer_LoadedCheeseData['${widgetId}'], '${widgetId}')"
                 style="display: flex; align-items: center; gap: 12px; padding: 12px 15px; cursor: pointer; border-radius: 4px; 
                        background: ${activeBg}; color: ${activeColor}; ${activeBorder} font-weight: 500; transition: background 0.2s;">
                <div style="display:flex; align-items:center; justify-content:center;">${m.svg}</div>
                <span style="font-size: 14px;">${m.label}</span>
            </div>
        `;
    });
    html += `</div>`; 

    // --- ส่วนที่ 2: เนื้อหาด้านขวา (Right Content Area) ---
    html += `<div style="flex: 1; overflow-y: auto; padding-left: 10px;">`;

    // MODE 1: SEARCH
    if (window.viewer_CurrentMode === 1) {
        const chains = cheeseData.FilterChain || [];
        let activeTabNum = 1; 
        let totalTabs = chains.length;

        html += `<div class="tabs2" style="margin-bottom: 15px; border-bottom: 2px solid #f1f5f9;">`;
        chains.forEach((chain, index) => {
            let count = index + 1;
            let activeClass = (count === activeTabNum) ? 'active' : '';
            html += `<div class="tabed ${activeClass}" id="tabChains-${count}" onclick="switchtabActiveAndData('Chains','${totalTabs}','${count}')" style="padding: 10px 15px;">${chain.type || 'Search ' + count}</div>`;
        });
        html += `</div>`;

        chains.forEach((chain, index) => {
            let count = index + 1;
            let displayStyle = (count === activeTabNum) ? '' : 'display:none;';
            html += `<div id="showChains-${count}" class="panel" style="${displayStyle} border: none; padding: 0;">`;
            
            let rulesArray = [];
            try {
                if (chain.json_out_put && chain.json_out_put.trim() !== "") {
                    rulesArray = JSON.parse(chain.json_out_put);
                }
            } catch (e) { console.error("Parse JSON error", e); }

            if (rulesArray.length > 0) {
                let toggleCheckbox = "";
                if (index > 0) {
                    let isChecked = (chain.status === 1) ? 'checked' : '';
                    toggleCheckbox = `<input type="checkbox" onchange="viewer_toggleChainStatus('${widgetId}', ${chain.id}, this.checked)" ${isChecked} style="margin-right: 8px; cursor: pointer;">`;
                }

                html += `
                    <div class="section-header" style="margin-bottom: 15px;">
                        <label style="display: inline-flex; align-items: center; margin: 0; cursor: pointer;" title="เปิด/ปิด">
                            ${toggleCheckbox} <span>Condition Group ${count}</span>
                        </label>
                        <div class="line"></div>
                    </div>
                `;

                rulesArray.forEach(rule => {
                    if (rule.condition === 'OR') {
                        html += `<div class="section-header" style="margin-top: 15px;"><span>OR (New Condition)</span><div class="line"></div></div>`;
                    }

                    let op = rule.operation ? rule.operation.toLowerCase() : "";
                    let isMonthPassed = op.includes('months_passed');
                    let isNoValueCondition = op.includes('null') || op.includes('empty');
                    let isThisVar = rule.value && String(rule.value).startsWith('#this_');

                    html += `<div class="form-row" style="margin-bottom: 10px;">`;
                    html += `<span class="label" style="width: 140px; font-weight: bold; color: #475569;">${rule.column_tag || 'Column'}</span>`;
                    html += `<div style="display:flex; flex:1; align-items:center; gap: 5px;">`;

                    if (isThisVar) {
                        html += `<input type="text" class="inject-val" value="${rule.value}" readonly style="flex:1; background-color: #f1f5f9; color: #64748b; cursor: not-allowed; border: 1px dashed #cbd5e1; text-align: center; padding: 6px;">`;
                    } else if (isMonthPassed) {
                        html += `<select class="inject-val" onchange="viewer_updateFilterValue('${widgetId}', ${chain.id}, ${rule.id}, this.value)" style="flex:1; padding: 6px;">`;
                        const months = [3, 6, 9, 12, 24];
                        months.forEach(m => {
                            let optVal = `${m}_months_passed`;
                            let selected = (op === optVal) ? 'selected' : '';
                            html += `<option value="${optVal}" ${selected}>${m} months have passed.</option>`;
                        });
                        html += `</select>`;
                    } else if (isNoValueCondition) {
                        html += `<input type="text" readonly placeholder="${rule.operation}" style="flex:1; background-color: #f1f5f9; color: #94a3b8; cursor: not-allowed; font-style: italic; padding: 6px; border: 1px solid #e2e8f0;">`;
                    } else if (rule.type === 'date' || rule.type === 'number') {
                        html += `<input type="${rule.type}" class="inject-val" value="${rule.value || ''}" title="${rule.operation}" onchange="viewer_updateFilterValue('${widgetId}', ${chain.id}, ${rule.id}, this.value)" style="flex:1; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">`;
                    } else if (rule.type === 'select' || rule.type === 'select_multiple') {
                        let isMultiple = rule.type === 'select_multiple' ? 'multiple' : '';
                        let selectedValues = rule.value ? String(rule.value).split(',') : [];
                        html += `<select class="inject-val" ${isMultiple} onchange="viewer_updateFilterValue('${widgetId}', ${chain.id}, ${rule.id}, this)" style="flex:1; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">`;
                        html += `<option value=" ">-- Select (${rule.operation}) --</option>`;
                        if (rule.valueselected) {
                            let options = rule.valueselected.split(',');
                            options.forEach(optStr => {
                                if(optStr.trim() === "") return;
                                let parts = optStr.split(':');
                                let optVal = parts[0].trim();
                                let optTxt = parts.length > 1 ? parts[1].trim() : parts[0].trim();
                                let isSelected = selectedValues.includes(optVal) ? 'selected' : '';
                                html += `<option value="${optVal}" ${isSelected}>${optTxt}</option>`;
                            });
                        }
                        html += `</select>`;
                    } else {
                        html += `<input type="text" class="inject-val" value="${rule.value || ''}" placeholder="${rule.operation}" onchange="viewer_updateFilterValue('${widgetId}', ${chain.id}, ${rule.id}, this.value)" style="flex:1; padding: 6px; border: 1px solid #cbd5e1; border-radius: 4px;">`;
                    }

                    html += `</div></div>`; 
                });
            } else {
                html += `<div style="padding: 20px; color: #94a3b8; text-align: center;">ไม่มีเงื่อนไขในกลุ่มนี้</div>`;
            }
            html += `</div>`; 
        });
    }

    // MODE 2: DYNAMIC FILTER
    else if (window.viewer_CurrentMode === 2) {
        let dynFilter = cheeseData.DynamicFilter || { column: '', op: '=', val: '', valTo: '' };
        
        html += `<div class="section-header" style="margin-bottom: 20px;">
                    <span style="font-size: 16px; color: #0284c7;">สร้างเงื่อนไขการกรองอิสระ</span>
                    <div class="line"></div>
                 </div>`;
        
        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 120px; font-weight: bold;">เลือกคอลัมน์</span>
                    <select class="inject-val" onchange="viewer_updateDynFilter('${widgetId}', 'column', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="ALL">-- ทั้งหมด (ทุกคอลัมน์) --</option>`;
        headers.forEach(h => html += `<option value="${h}" ${dynFilter.column === h ? 'selected':''}>${h}</option>`);
        html += `   </select>
                 </div>`;

        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 120px; font-weight: bold;">เงื่อนไข</span>
                    <select class="inject-val" onchange="viewer_updateDynFilter('${widgetId}', 'op', this.value); viewer_renderQuickFilterUI(viewer_LoadedCheeseData['${widgetId}'], '${widgetId}')" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="=" ${dynFilter.op === '=' ? 'selected':''}>เท่ากับ (Equals)</option>
                        <option value="LIKE" ${dynFilter.op === 'LIKE' ? 'selected':''}>คล้ายกัน / มีคำว่า (Contains)</option>
                        <option value="RD_BETWEEN" ${dynFilter.op === 'RD_BETWEEN' ? 'selected':''}>วันที่ท้ายข้อความ (ระบุช่วง จาก-ถึง)</option>
                         <option value="RD_MONTHS_PASSED" ${dynFilter.op === 'RD_MONTHS_PASSED' ? 'selected':''}>วันที่ท้ายข้อความ ผ่านมาแล้ว (เดือน)</option>
                    </select>
                 </div>`;

        if (dynFilter.op === 'RD_BETWEEN') {
            html += `<div class="form-row" style="margin-bottom: 15px;">
                        <span class="label" style="width: 120px; font-weight: bold;">ช่วงวันที่</span>
                        <div style="display:flex; gap:10px; flex:1; align-items: center;">
                            <input type="date" value="${dynFilter.val}" onchange="viewer_updateDynFilter('${widgetId}', 'val', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                            <span style="color: #64748b; font-weight: bold;">ถึง</span>
                            <input type="date" value="${dynFilter.valTo}" onchange="viewer_updateDynFilter('${widgetId}', 'valTo', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        </div>
                    </div>`;
        } else {
            html += `<div class="form-row" style="margin-bottom: 15px;">
                        <span class="label" style="width: 120px; font-weight: bold;">ระบุข้อความ</span>
                        <input type="text" value="${dynFilter.val}" onchange="viewer_updateDynFilter('${widgetId}', 'val', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;" placeholder="พิมพ์คำที่ต้องการค้นหา...">
                    </div>`;
        }
    }

    // MODE 3: ANALYSIS
    else if (window.viewer_CurrentMode === 3) {
        let analysis = cheeseData.AnalysisSettings || { targetCol: '', mode: 'pareto' };
        
        html += `<div class="section-header" style="margin-bottom: 20px;">
                    <span style="font-size: 16px; color: #16a34a;">โหมดวิเคราะห์ประสิทธิภาพ (80/20)</span>
                    <div class="line"></div>
                 </div>`;
        
        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 140px; font-weight: bold;">คอลัมน์คำนวณยอด</span>
                    <select class="inject-val" onchange="viewer_updateAnalysis('${widgetId}', 'targetCol', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="">-- เลือกคอลัมน์ตัวเลข --</option>`;
        headers.forEach(h => html += `<option value="${h}" ${analysis.targetCol === h ? 'selected':''}>${h}</option>`);
        html += `   </select>
                 </div>`;

        html += `<div style="margin-top: 20px; padding: 15px; background: #f0fdf4; border: 1px solid #bbf7d0; border-radius: 8px; font-size: 14px; color: #166534; line-height: 1.6;">
                    <b>💡 Pareto Logic:</b> <br>
                    ระบบจะทำการจัดลำดับใหม่ (Sort) ตามยอดที่เลือก และไฮไลท์ <span style="color:white; background:#16a34a; padding:3px 8px; border-radius:4px; font-weight: bold;">สีเขียว</span> 
                    ในกลุ่มแถวบนสุดที่ทำยอดรวมได้ถึง <b>80%</b> ของยอดทั้งหมด (เพื่อชี้เป้ากลุ่มตัวทำยอดหลักของระบบ)
                 </div>`;
    }

    // MODE 4: VIEW SETTINGS (🌟 เพิ่มรูปแบบการคำนวณ)
    else if (window.viewer_CurrentMode === 4) {
        let graphSettings = cheeseData.GraphSettings || { dateDisplay: 'day', cutoffCycle: '1-end', aggType: 'sum' };
        
        html += `<div class="section-header" style="margin-bottom: 20px;">
                    <span style="font-size: 16px; color: #475569;">ตั้งค่ามุมมองกราฟ</span>
                    <div class="line"></div>
                 </div>`;

        // 🌟 1. เพิ่ม Dropdown ให้เลือกว่าจะรวมข้อมูลแบบไหน (SUM, AVG, MAX, MIN, COUNT)
        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 140px; font-weight: bold;">รูปแบบการคำนวณ</span>
                    <select onchange="viewer_updateGraphSettings('${widgetId}', 'aggType', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="sum" ${graphSettings.aggType==='sum' || !graphSettings.aggType ?'selected':''}>ผลรวม (SUM)</option>
                        <option value="avg" ${graphSettings.aggType==='avg'?'selected':''}>ค่าเฉลี่ย (AVG)</option>
                        <option value="max" ${graphSettings.aggType==='max'?'selected':''}>ค่าสูงสุด (MAX)</option>
                        <option value="min" ${graphSettings.aggType==='min'?'selected':''}>ค่าต่ำสุด (MIN)</option>
                        <option value="count" ${graphSettings.aggType==='count'?'selected':''}>นับจำนวนรายการ (COUNT)</option>
                    </select>
                 </div>`;
                 
        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 140px; font-weight: bold;">รูปแบบแกนเวลา</span>
                    <select onchange="viewer_updateGraphSettings('${widgetId}', 'dateDisplay', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="day" ${graphSettings.dateDisplay==='day'?'selected':''}>แสดงรายวัน (Daily)</option>
                        <option value="month" ${graphSettings.dateDisplay==='month'?'selected':''}>ยุบรวมเป็นรายเดือน (Monthly)</option>
                           <option value="year" ${graphSettings.dateDisplay==='year'?'selected':''}>ยุบรวมเป็นรายปี (Yearly)</option>
                    </select>
                 </div>`;
                 
        html += `<div class="form-row" style="margin-bottom: 15px;">
                    <span class="label" style="width: 140px; font-weight: bold;">รอบตัดวัน (Cutoff)</span>
                    <select onchange="viewer_updateGraphSettings('${widgetId}', 'cutoffCycle', this.value)" style="flex:1; padding: 8px; border: 1px solid #cbd5e1; border-radius: 4px;">
                        <option value="1-end" ${graphSettings.cutoffCycle==='1-end'?'selected':''}>วันที่ 1 ถึง สิ้นเดือน</option>
                        <option value="26-25" ${graphSettings.cutoffCycle==='26-25'?'selected':''}>วันที่ 26 ถึง 25 ของเดือนถัดไป</option>
                    </select>
                 </div>`;
    }

    html += `</div>`; // ปิด Right Content Area
    html += `</div>`; // ปิด Flexbox Container

    container.innerHTML = html;
}

// ---------------------------------------------------------
// ฟังก์ชันเสริมสำหรับบันทึกค่าลงหน่วยความจำ
// ---------------------------------------------------------

function viewer_updateDynFilter(widgetId, key, val) {
    if (!viewer_LoadedCheeseData[widgetId].DynamicFilter) {
        viewer_LoadedCheeseData[widgetId].DynamicFilter = { column: 'ALL', op: '=', val: '', valTo: '' };
    }
    viewer_LoadedCheeseData[widgetId].DynamicFilter[key] = val;
}

function viewer_updateAnalysis(widgetId, key, val) {
    if (!viewer_LoadedCheeseData[widgetId].AnalysisSettings) {
        viewer_LoadedCheeseData[widgetId].AnalysisSettings = { targetCol: '', mode: 'pareto' };
    }
    viewer_LoadedCheeseData[widgetId].AnalysisSettings[key] = val;
}



// 🚀 ฟังก์ชันบันทึกการตั้งค่ากราฟลง Memory
function viewer_updateGraphSettings(widgetId, key, value) {
    let cheeseData = viewer_LoadedCheeseData[widgetId];
    if (!cheeseData) return;

    // ถ้ายังไม่เคยมี Object นี้ ให้สร้างใหม่
    if (!cheeseData.GraphSettings) {
        cheeseData.GraphSettings = { dateDisplay: 'day', cutoffCycle: '1-end' };
    }

    // อัปเดตค่าที่ผู้ใช้เลือก
    cheeseData.GraphSettings[key] = value;
    console.log(`[Viewer] ⚙️ ตั้งค่ากราฟ ${widgetId} -> ${key} : ${value}`);
}


// ==========================================
// ฟังก์ชันเมื่อผู้ใช้พิมพ์หรือเลือกค่าในหน้า UI ให้มันจำไว้ในตัวแปรชั่วคราวก่อน
// ==========================================
function viewer_updateFilterValue(widgetId, chainId, ruleId, newValue) {
    let cheeseData = viewer_LoadedCheeseData[widgetId];
    if (!cheeseData || !cheeseData.FilterChain) return;

    // หา Chain (กลุ่ม) ที่กำลังแก้ไข
    let chain = cheeseData.FilterChain.find(c => c.id == chainId);
    if (chain && chain.json_out_put) {
        let rulesArray = JSON.parse(chain.json_out_put);
        
        // หา Rule (คอลัมน์) ที่กำลังแก้ไข
        let rule = rulesArray.find(r => r.id == ruleId);
        if (rule) {
            
            // 🚀 เช็คว่าเป็น HTML Element (Dropdown) หรือไม่
            if (typeof newValue === 'object' && newValue.tagName === 'SELECT') {
                // 🎯 หมัดฮุก: ถ้าเป็น select multiple ให้กวาดมาทุกตัวที่เลือก!
                if (newValue.multiple) {
                    let selectedVals = Array.from(newValue.selectedOptions).map(opt => opt.value);
                    rule.value = selectedVals.join(','); // เอามาต่อกันด้วยลูกน้ำ เช่น "1,2,5"
                } else {
                    rule.value = newValue.value; // ถ้าเป็น Dropdown ธรรมดา เอาค่าเดียว
                }
            } else {
                rule.value = newValue; // กรณีพิมพ์ Text / Date / Number
            }
            
            // แพ็กกลับเป็น JSON String ใส่ไว้ที่เดิม
            chain.json_out_put = JSON.stringify(rulesArray);
            console.log(`[Viewer] 📝 อัปเดตค่าตัวกรอง Rule ${ruleId} เป็น:`, rule.value);
        }
    }
    window.viewer_NeedsFetch[widgetId] = true;
}


// ==========================================
// ฟังก์ชันเซฟลงเครื่อง & โหลดกราฟใหม่
// ==========================================
function applyFilter() {
    if (!viewer_ActiveWidgetId) {
        alert("ไม่พบกราฟที่กำลังทำงานอยู่");
        return;
    }

    const id = viewer_ActiveWidgetId;

    // 1. ดึงข้อมูล JSON ที่ถูกแก้ไขแล้วจากตัวแปร Global
    const currentData = viewer_LoadedCheeseData[id];

    // 2. บันทึกลง "เครื่องผู้ใช้" (LocalStorage) เพื่อจำค่าไว้
    const storageKey = `custom_filter_${report_id}_${id}`;
    localStorage.setItem(storageKey, JSON.stringify(currentData));
    
    console.log(`💾 บันทึกค่าตัวกรองลงเครื่องผู้ใช้ (Key: ${storageKey}) สำเร็จ!`);

  
	   fetchNewDataForGraph();
}


function fetchNewDataForGraph() {
  getData('/ShowReportanalytics-filemanager/'+report_id, 'analytics-main-content');
}



// ==========================================
// 🚀 เครื่องจักรสร้างตาราง Viewer (สไตล์ Windows 11 Fluent Design - คลีน/ไม่มีอิโมจิ)
// ==========================================
function viewer_renderDataToTable(widgetId, filterStr = "") {
    const config = viewer_WidgetsConfig[widgetId];
    if (!config || !config.dataset || config.dataset.data.length === 0) {
        document.getElementById("table-containers").innerHTML = `<div style="padding: 20px; text-align: center; color: #6b6b6b; font-family: 'Segoe UI', sans-serif;">ไม่มีข้อมูล</div>`;
        return;
    }

    // 🌟 โคลนข้อมูล (Deep Copy)
    let dataset = JSON.parse(JSON.stringify(config.dataset)); 

    let cheeseConfig = null;
    if (typeof viewer_LoadedCheeseData !== 'undefined' && viewer_LoadedCheeseData[widgetId]) {
        cheeseConfig = viewer_LoadedCheeseData[widgetId];
    } else if (typeof currentCheeseData !== 'undefined') {
        cheeseConfig = currentCheeseData;
    }

    let globalDateFormat = "yyyy-mm-dd";
    if (cheeseConfig && cheeseConfig.PrepareReport && cheeseConfig.PrepareReport.date_format) {
        globalDateFormat = cheeseConfig.PrepareReport.date_format;
    }

    // ==========================================
    // ⚙️ ENGINE 1: DYNAMIC FILTER 
    // ==========================================
    if (cheeseConfig && cheeseConfig.DynamicFilter) {
        let dyn = cheeseConfig.DynamicFilter;
        if ((dyn.val && dyn.val.trim() !== '') || (dyn.valTo && dyn.valTo.trim() !== '')) {
            dataset.data = dataset.data.filter(row => {
                let checkCols = (dyn.column === 'ALL' || !dyn.column) ? dataset.headers : [dyn.column];
                return checkCols.some(col => {
                    let cellVal = String(row[col] || '');
                    if (!cellVal) return false;

                    let dateMatch = cellVal.match(/(\d{4}-\d{2}-\d{2})/);
                    let textDate = dateMatch ? dateMatch[1] : null;

                    if (dyn.op === '=') return cellVal === dyn.val;
                    else if (dyn.op === 'LIKE') return cellVal.includes(dyn.val);
                    else if (dyn.op === 'RD_BETWEEN' || dyn.op === 'RD_MONTHS_PASSED') {
                        if (textDate) {
                            if (dyn.op === 'RD_BETWEEN') {
                                let pass = true;
                                if (dyn.val) pass = pass && (textDate >= dyn.val);
                                if (dyn.valTo) pass = pass && (textDate <= dyn.valTo);
                                return pass;
                            } 
                            else if (dyn.op === 'RD_MONTHS_PASSED') {
                                let rowDate = new Date(textDate);
                                let valStr = String(dyn.val).trim();
                                if (valStr.includes('-')) {
                                    let parts = valStr.split('-');
                                    let minM = parseInt(parts[0]) || 0;
                                    let maxM = parseInt(parts[1]) || 0;
                                    if (minM > maxM) { let t = minM; minM = maxM; maxM = t; }
                                    let tMin = new Date(); tMin.setMonth(tMin.getMonth() - minM);
                                    let tMax = new Date(); tMax.setMonth(tMax.getMonth() - maxM);
                                    return rowDate <= tMin && rowDate >= tMax;
                                } else {
                                    let target = new Date();
                                    target.setMonth(target.getMonth() - (parseInt(valStr) || 0));
                                    return rowDate <= target;
                                }
                            }
                        }
                        return false;
                    }
                    return false;
                });
            });
            dataset.total_data = dataset.data.length; 
        }
    }
  
    // ==========================================
    // ⚙️ ENGINE 2: ANALYSIS PARETO 80/20 
    // ==========================================
    let isParetoActive = false;
    if (cheeseConfig && cheeseConfig.AnalysisSettings && cheeseConfig.AnalysisSettings.targetCol) {
        isParetoActive = true;
        let targetCol = cheeseConfig.AnalysisSettings.targetCol;

        dataset.data.forEach(row => {
            let rawVal = String(row[targetCol] || '0').replace(/<[^>]*>?/gm, '').replace(/,/g, '');
            row._paretoVal = parseFloat(rawVal) || 0;
        });

        dataset.data.sort((a, b) => b._paretoVal - a._paretoVal);

        let totalSum = dataset.data.reduce((sum, row) => sum + row._paretoVal, 0);
        let cumulativeSum = 0;
        
        dataset.data.forEach(row => {
            if ((cumulativeSum / totalSum) <= 0.8 && totalSum > 0) {
                row._isParetoTop80 = true; 
            } else {
                row._isParetoTop80 = false;
            }
            cumulativeSum += row._paretoVal;
        });
    }

    // ==========================================
    // 🌟 ENGINE 2.5: เครื่องจักรจัดกลุ่มยอดรวม (Group & Subtotal)
    // ==========================================
    let groupTotals = {};
    let isGroupingActive = config.axisX && config.axisY; 
    let graphSettings = cheeseConfig && cheeseConfig.GraphSettings ? cheeseConfig.GraphSettings : { dateDisplay: 'day', cutoffCycle: '1-end' };

    if (isGroupingActive) {
        dataset.data.forEach(row => {
            let rawXVal = row[config.axisX];
            let xVal = rawXVal !== null && rawXVal !== undefined ? String(rawXVal).replace(/(<([^>]+)>)/gi, "").trim() : null;
            let d = null;
            
            if (xVal) {
                if (/^\d+$/.test(xVal)) {
                    let numVal = Number(xVal);
                    if (numVal > 0 && numVal < 20000000000) numVal *= 1000;
                    d = new Date(numVal);
                } else {
                    let matchYMD = xVal.match(/(\d{4})[\-\/](\d{2})[\-\/](\d{2})/);
                    let matchDMY = xVal.match(/(\d{2})[\-\/](\d{2})[\-\/](\d{4})/);
                    if (matchYMD) d = new Date(parseInt(matchYMD[1], 10), parseInt(matchYMD[2], 10) - 1, parseInt(matchYMD[3], 10));
                    else if (matchDMY) d = new Date(parseInt(matchDMY[3], 10), parseInt(matchDMY[2], 10) - 1, parseInt(matchDMY[1], 10));
                    else {
                        let fb = new Date(xVal);
                        if (!isNaN(fb.getTime())) d = fb;
                    }
                }
            }
            if (d && isNaN(d.getTime())) d = null;

            let gKey = 'ไม่ระบุ';
            if (d) {
                let year = d.getFullYear();
                let month = d.getMonth() + 1;
                let date = d.getDate();
                
                // 🚀 แก้บั๊ก: ต้องไม่ใช่ 'day' ถึงจะยอมปัดเดือนให้!
                if (graphSettings.dateDisplay !== 'day' && graphSettings.cutoffCycle === '26-25' && date >= 26) { 
                    month += 1; 
                    if (month > 12) { month = 1; year += 1; } 
                }
                
                if (graphSettings.dateDisplay === 'year') gKey = `${year}`;
                else if (graphSettings.dateDisplay === 'month') gKey = `${year}-${month.toString().padStart(2,'0')}`;
                else gKey = `${year}-${month.toString().padStart(2,'0')}-${date.toString().padStart(2,'0')}`;
            } else {
                gKey = xVal ? String(xVal) : 'ไม่ระบุ';
            }

            row._groupKey = gKey; 

            let rawY = row[config.axisY];
            let yVal = (rawY !== null && rawY !== undefined) ? parseFloat(String(rawY).replace(/(<([^>]+)>)/gi, "").replace(/,/g, '')) || 0 : 0;
            
            if (!groupTotals[gKey]) groupTotals[gKey] = 0;
            groupTotals[gKey] += yVal;
        });

        if (!isParetoActive) {
            dataset.data.sort((a, b) => {
                if (a._groupKey > b._groupKey) return -1;
                if (a._groupKey < b._groupKey) return 1;
                return 0;
            });
        }
    }

    // ==========================================
    // ⚙️ ENGINE 3: เครื่องจักรจัดลำดับ & วุ้นแปลภาษา Header
    // ==========================================
    if (cheeseConfig && cheeseConfig.FilterChain) {
        let headerMapping = {}; 
        let finalHeaders = [];  
        let hasDisplayConfig = false;

        cheeseConfig.FilterChain.forEach(filter => {
            if (filter.display_columns && filter.display_columns.trim() !== "") {
                hasDisplayConfig = true;
                const selectedValues = filter.display_columns.split(',').map(v => v.trim()).filter(Boolean);
                
                selectedValues.forEach(val => { 
                    let originalLabel = val; 
                    let newLabel = val;      

                    if (filter.saved_columns) {
                        const foundCol = filter.saved_columns.find(c => c.value === val);
                        if (foundCol && foundCol.text) originalLabel = foundCol.text;
                    }
                    if (filter.custom_labels) {
                        const foundCol = filter.custom_labels.find(c => c.value === val);
                        if (foundCol && foundCol.text) newLabel = foundCol.text;
                    }

                    let matchedOriginalHeader = null;
                    if (dataset.headers.includes(originalLabel)) matchedOriginalHeader = originalLabel;
                    else if (dataset.headers.includes(val)) matchedOriginalHeader = val;
                    else if (dataset.headers.includes(newLabel)) matchedOriginalHeader = newLabel;

                    if (matchedOriginalHeader) {
                        headerMapping[matchedOriginalHeader] = newLabel;
                        if (!finalHeaders.includes(newLabel)) finalHeaders.push(newLabel);
                    }
                });
            }
        });

        if (!hasDisplayConfig || finalHeaders.length === 0) {
            finalHeaders = [...dataset.headers]; 
        } else {
            dataset.data = dataset.data.map(row => {
                let newRow = { id: row.id, _isParetoTop80: row._isParetoTop80, _groupKey: row._groupKey }; 
                Object.keys(row).forEach(oldKey => {
                    if (headerMapping[oldKey]) newRow[headerMapping[oldKey]] = row[oldKey];
                    else newRow[oldKey] = row[oldKey]; 
                });
                return newRow;
            });
        }
        dataset.headers = finalHeaders;
    }

    // ==========================================
    // 🌟 วาดตาราง HTML (Windows 11 Fluent Design)
    // ==========================================
    if (window.viewer_LastRenderedWidget !== widgetId) {
        viewer_CurrentPage = 1;
        window.viewer_LastRenderedWidget = widgetId;
    }

    if (typeof viewer_CurrentPage === 'undefined') window.viewer_CurrentPage = 1;
    if (typeof viewer_RowsPerPage === 'undefined') window.viewer_RowsPerPage = 100;

    let totalRows = dataset.data.length;
    let totalPages = Math.ceil(totalRows / viewer_RowsPerPage) || 1;
    
    if (viewer_CurrentPage > totalPages) viewer_CurrentPage = totalPages;
    if (viewer_CurrentPage < 1) viewer_CurrentPage = 1;

    let startIndex = (viewer_CurrentPage - 1) * viewer_RowsPerPage;
    let endIndex = startIndex + viewer_RowsPerPage;
    let paginatedData = dataset.data.slice(startIndex, endIndex); 

    const pageInfoEl = document.getElementById('table-page-info');
    if (pageInfoEl) {
        if (totalRows === 0) {
            pageInfoEl.innerText = "0 / 0";
        } else {
            let endDisp = endIndex > totalRows ? totalRows : endIndex;
            pageInfoEl.innerText = `${startIndex + 1} - ${endDisp} / ${totalRows}`;
        }
    }

    // 🎨 ฟอนต์ Segoe UI, ไม่มีขอบแนวตั้ง, สีเรียบหรู
    let html = '<table style="width: 100%; border-collapse: collapse; font-family: \'Segoe UI\', system-ui, sans-serif; font-size: 13px; text-align: left; color: #202020;">';
    
    html += '<thead><tr style="background-color: #ffffff; border-bottom: 2px solid #e5e5e5; color: #111111;">';
    html += '<th style="padding: 12px 16px; font-weight: 600; width: 50px; text-align: center;">#</th>'; 
    dataset.headers.forEach(headerName => {
        html += `<th style="padding: 12px 16px; font-weight: 600;">${headerName}</th>`;
    });
    html += '<th style="padding: 12px 16px; font-weight: 600;">Ref_ID</th></tr></thead>';

    html += '<tbody>';
    
    let currentGroupKey = null;

    paginatedData.forEach((row, index) => {
        const rowNum = startIndex + index + 1; 

        // 🚀 แทรกแถว Group Header
        if (isGroupingActive && !isParetoActive && row._groupKey !== currentGroupKey) {
            currentGroupKey = row._groupKey;
            
            let groupSumFormatted = groupTotals[currentGroupKey].toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2 });
            let displayGroup = currentGroupKey;
            
            if (currentGroupKey.match(/^\d{4}-\d{2}-\d{2}$/)) {
                let parts = currentGroupKey.split('-');
                displayGroup = `${parts[2]}/${parts[1]}/${parts[0]}`;
            } else if (currentGroupKey.match(/^\d{4}-\d{2}$/)) {
                let parts = currentGroupKey.split('-');
                displayGroup = `เดือน ${parts[1]}/${parts[0]}`;
            } else if (currentGroupKey.match(/^\d{4}$/)) {
                displayGroup = `ปี ${currentGroupKey}`;
            }

            // 🎨 พื้นหลังสีเทาอ่อนมาก คลีนๆ แบบ Windows
            html += `<tr style="background-color: #fbfbfb; border-bottom: 1px solid #ededed;">
                        <td colspan="${dataset.headers.length + 2}" style="padding: 12px 16px; color: #3b3b3b;">
                            กลุ่ม/วันที่: <span style="font-weight: 600; color: #005a9e;">${displayGroup}</span>
                            <span style="float: right; font-weight: 600; color: #111111;">
                                ยอดรวม: ${groupSumFormatted}
                            </span>
                        </td>
                     </tr>`;
        }
        
        let refValue = row['id'];
        if (refValue === null || refValue === undefined) refValue = "-";
        
        // 🎨 โหมด Pareto ใช้สีเขียวอ่อนมินิมอล แทนสีเขียวสว่าง
        let bgStyle = "transparent";
        if (row._isParetoTop80) {
            bgStyle = "#f4fbf6"; 
        }

        // 🎨 Hover สไตล์ Windows (เปลี่ยนสี Background แทนการใช้ Filter)
        html += `<tr data-id="${refValue}" 
                     style="border-bottom: 1px solid #ededed; cursor: pointer; background-color: ${bgStyle}; transition: background-color 0.1s ease;" 
                     onmouseover="this.dataset.origBg = this.style.backgroundColor; this.style.backgroundColor = '#f3f3f3';" 
                     onmouseout="this.style.backgroundColor = this.dataset.origBg;">`;
                     
        html += `<td style="font-weight: 600; text-align: center; color: #5d5d5d; padding: 10px 16px;">${rowNum}</td>`; 
        
        dataset.headers.forEach(headerName => {
            let value = row[headerName];
            if (value === null || value === undefined) value = "";
            
            if (headerName === "วันที่ (Trend Axis)" && globalDateFormat !== "raw") {
                if (typeof formatTrendDate === 'function') {
                    value = formatTrendDate(value, globalDateFormat);
                }
            }
            
            if (typeof value === 'string' && value.includes('openDrillDownData')) {
              value = value.replace(/openDrillDownData\(/g, `viewer_openDrillDownData('${widgetId}', `);
            }

            html += `<td style="padding: 10px 16px;">${value}</td>`;
        });

        html += `<td style="color: #999999; padding: 10px 16px;">${refValue}</td>`;
        html += '</tr>';
    });

    html += '</tbody></table>';

    const container = document.getElementById("table-containers");
    if(container) container.innerHTML = html;
}







// ==========================================
// 🚀 ฟังก์ชัน Auto-Select: ค้นหากราฟตัวแรกและสั่งคลิกให้อัตโนมัติ
// ==========================================
function viewer_autoSelectFirstChart() {
    if (typeof viewer_WidgetsConfig === 'undefined' || !viewer_WidgetsConfig) return;

    let firstChartId = null;

    // 1. วนลูปหา Widget ทุกตัวที่อยู่ใน Config
    for (const [id, config] of Object.entries(viewer_WidgetsConfig)) {
        
        // 2. เช็คว่ามีข้อมูล (dataset) และหน้าจอถูกวาดลง DOM แล้วหรือไม่
        // (ถ้าคุณมีตัวแปรแยกชนิด เช่น config.type == 'bar' หรือ 'pie' สามารถเติมเงื่อนไข && config.type !== 'table' เข้าไปได้ครับ)
        if (config && config.dataset && config.dataset.data && config.dataset.data.length > 0) {
            
            // ตรวจสอบว่ากล่องนี้มีอยู่บนหน้าจอจริงๆ
            const el = document.getElementById(id);
            if (el) {
                firstChartId = id;
                break; // เจอกราฟตัวแรกปุ๊บ หยุดหาทันที!
            }
        }
    }

    // 3. ถ้าเจอกราฟตัวแรก ให้จำลองการคลิกโดยเรียก viewer_toggleSelect
    if (firstChartId) {
        console.log("🚀 [Auto-Select] พบกราฟตัวแรก สั่งจำลองการคลิกที่ ID:", firstChartId);
        
        // เรียกฟังก์ชันที่เราเขียนไว้ก่อนหน้านี้ (มันจะไปวาดตาราง + โหลดไฟล์ .cheese ให้เอง)
        viewer_toggleSelect(firstChartId);
    } else {
        console.log("⚠️ [Auto-Select] ไม่พบกราฟที่มีข้อมูลบนหน้าจอ");
    }
}

// ==========================================
// 🔍 ฟังก์ชันเจาะลึกข้อมูล (Drill-down) สำหรับโหมด Viewer (🔥 แก้บั๊กลืมค่าตัวกรองใหม่!)
// ==========================================
async function viewer_openDrillDownData(widgetId, targetChainId, mainId, clickedElement) {
    console.log("[Viewer] กล่อง: " + widgetId + ", Chain ID: " + targetChainId + ", อ้างอิง ID: " + mainId);

    const activeConfig = viewer_WidgetsConfig[widgetId];
    let jsonPayload = "{}";

    // 🌟 1. [แก้ไขแล้ว] ตรวจสอบ Cache หรือ LocalStorage ก่อน! (เอาค่าใหม่ที่เพิ่งถูกแก้มาใช้)
    let currentCheeseConfig = viewer_LoadedCheeseData[widgetId];

    if (!currentCheeseConfig) {
        // สำรอง: ถ้าตัวแปร Cache ว่าง ให้ไปดึงจาก LocalStorage เผื่อเคยเซฟไว้
        const storageKey = `custom_filter_${report_id}_${widgetId}`;
        const savedFilter = localStorage.getItem(storageKey);
        if (savedFilter) {
            currentCheeseConfig = JSON.parse(savedFilter);
            viewer_LoadedCheeseData[widgetId] = currentCheeseConfig; // เซฟกลับเข้า Cache
        }
    }

    // 🌟 2. ประกอบร่าง Payload
    if (currentCheeseConfig) {
        // ✅ มีข้อมูลที่ถูกกรอง/แก้ไขล่าสุดอยู่แล้ว นำไปใช้ได้เลยไม่ต้องโหลดใหม่
        console.log("♻️ ใช้ข้อมูลตัวกรอง .cheese ล่าสุดจากเครื่อง (อัปเดตแล้ว)");
        jsonPayload = JSON.stringify(currentCheeseConfig);
    } 
    else if (activeConfig && activeConfig.dataSource) {
        // ❌ ถ้าไม่มีจริงๆ (คลิกครั้งแรกโดยไม่ได้แก้ตัวกรองเลย) ค่อยโหลดจาก URL ต้นฉบับ
        try {
            const fileUrl = activeConfig.dataSource.replace("Idoc:/", "/document-filemanager/");
            console.log("📥 ไม่พบประวัติการแก้ไข กำลังดาวน์โหลดตัวกรองต้นฉบับจาก: ", fileUrl);
            
            const res = await fetch(fileUrl);
            if (!res.ok) throw new Error("ไม่สามารถดาวน์โหลดไฟล์ .cheese ได้");
            
            const cheeseJson = await res.json();
            viewer_LoadedCheeseData[widgetId] = cheeseJson; // เก็บลง Cache ด้วย
            jsonPayload = JSON.stringify(cheeseJson);
        } catch (error) {
            console.error("❌ โหลดไฟล์การตั้งค่า .cheese ล้มเหลว:", error);
            alert("เกิดข้อผิดพลาด: ไม่สามารถอ่านการตั้งค่าของกราฟนี้ได้");
            return;
        }
    } else {
        console.error("❌ กล่องนี้ไม่มีการเชื่อมต่อกับไฟล์ข้อมูล (dataSource)");
        return;
    }

    console.log("📦 JSON Payload ถูกมัดรวมด้วยค่าใหม่ พร้อมส่งให้ Server แล้ว!");

    const url = `/showdata-drilldown-filemanager?${csrfToken_use}`;
    const params = new URLSearchParams();
    params.append('id_refer', mainId);
    params.append('chain_id', targetChainId);
    params.append('cheeseJsonPayload', jsonPayload); // 🚀 ส่งค่าใหม่ตรงนี้!

    // 🌟 3. สลับหน้าต่างมาที่ Tab 2 (แสดง Loading รอก่อน)
    const sheet1 = document.getElementById('showdata-1');
    const sheet2 = document.getElementById('showdata-2');
    if (sheet1) sheet1.style.display = 'none';
    if (sheet2) sheet2.style.display = 'block';

    const container2 = document.getElementById("table-containers2");
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
        
        // 🚀 5. อัปเกรด: ป้องกันบั๊กกรณี clickedElement ไม่ใช่ Element จริงๆ
        let filterValue = "";
        let filterCategory = "";
        
        if (clickedElement) {
            if (typeof clickedElement === 'string') {
                filterValue = clickedElement; // เผื่อส่งมาเป็นข้อความตรงๆ
            } else if (typeof clickedElement.getAttribute === 'function') {
                filterValue = clickedElement.getAttribute('data-filter') || "";
                filterCategory = clickedElement.getAttribute('data-category') || "";
            }
        }
        
        viewer_renderDrillDownTable(data, "table-containers2", filterValue, filterCategory);
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
function viewer_renderDrillDownTable(jsonDatas, containerId, filterStr = "", filterCategory = "") {
    let dataset = jsonDatas;
    if (typeof jsonDatas === 'string') {
        try { dataset = JSON.parse(jsonDatas); } 
        catch (e) { 
            console.error("JSON Parse Error:", e);
            return; 
        }
    }
	
	console.log(jsonDatas);
    
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

   // 🚀 [อัปเกรด V5: ไม้ตาย ตัดเวลาทิ้ง เทียบแค่วันที่เพียวๆ!] ===================
    // 🌟 Client-Side Filtering (กรองข้อมูลจาก data-filter ที่คลิกมา)
    // ===================================================================
    if (filterStr && filterStr.trim() !== "") {
        const targetValue = filterStr.split("_")[0].trim();
        
        // 🌟 ฟังก์ชันช่วยสกัดวันที่ YYYY-MM-DD ออกจากข้อความอะไรก็ตาม (เพื่อตัดเวลาทิ้ง)
        const extractPureDate = (text) => {
            if (!text) return null;
            const str = String(text).trim();
            // จับ YYYY-MM-DD (เช่น "2026-04-24 09:49" -> จะได้ "2026-04-24")
            const match1 = str.match(/(\d{4})[\-\/](\d{2})[\-\/](\d{2})/);
            if (match1) return `${match1[1]}-${match1[2]}-${match1[3]}`;
            // จับ DD/MM/YYYY (เช่น "24/04/2026 09:49" -> จะแปลงเป็น "2026-04-24")
            const match2 = str.match(/(\d{2})[\-\/](\d{2})[\-\/](\d{4})/);
            if (match2) return `${match2[3]}-${match2[2]}-${match2[1]}`;
            return null; 
        };

        const cleanTarget = extractPureDate(targetValue) || targetValue; 

        dataset.data = dataset.data.filter(row => {
            return Object.entries(row).some(([key, val]) => {
                if (val === null || val === undefined) return false;
                let strVal = String(val).trim();

                // ด่านที่ 1: เทียบแบบตรงๆ ก่อน (ครอบคลุมข้อความทั่วไป หรือ Target เป็นแค่เดือนเช่น 2026-04)
                if (strVal.includes(targetValue)) return true;

                // ด่านที่ 2: ถ้าเป็นรูปแบบวันที่ ให้สกัดเอาแค่วันที่ (ตัดเวลาทิ้งไปเลย) แล้วเทียบ
                const cleanRowDate = extractPureDate(strVal);
                if (cleanRowDate && cleanRowDate.includes(cleanTarget)) {
                    return true;
                }

                // ด่านที่ 3: กรณีข้อมูลหลังบ้านดันมาเป็น Timestamp เพียวๆ (เลขยาวๆ)
                const isTimestamp = !isNaN(val) && strVal.length >= 10 && !strVal.includes("-");
                if (isTimestamp) {
                    try {
                        const formattedDate = formatTrendDate(val, 'yyyy-mm-dd');
                        if (formattedDate && formattedDate.includes(cleanTarget)) return true;
                    } catch(e) { 
                        // ใส่ try-catch ป้องกันฟังก์ชัน formatTrendDate พังแล้วทำระบบค้าง
                    }
                }

                return false;
            });
        });

        // 🌟 ดักจับกรณีไม่มีข้อมูล
        if (dataset.data.length === 0) {
            const container = document.getElementById("table-container2") || document.getElementById("table-container");
            if(container) {
                container.innerHTML = `
                <div style="padding: 15px;">
                    <button onclick="switchtabActiveAndData('data', '1', '1');" style="margin-bottom: 15px; padding: 8px 15px; cursor: pointer; background: #e5e7eb; border: 1px solid #ccc; border-radius: 4px;">⬅️ ย้อนกลับ</button>
                    <p style='text-align:center; color: #888;'>ไม่มีข้อมูลเจาะลึกสำหรับ <b>${targetValue}</b></p>
                </div>`;
            }
            return;
        }
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
    
    switchtabActiveAndData('datas','3','2');
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
    const container2 = document.getElementById("table-containers2");
    if (container2) container2.innerHTML = "";
}










// ============================================================================
// 2. ฟังก์ชันโหลดไฟล์และวาดหน้าจอ (เปลี่ยนชื่อแล้ว)
// ============================================================================

function viewer_loadDashboardFromUrl(fileUrl, segmentationId) {
	
		report_id = segmentationId;
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
            document.getElementById('table-containers3').innerHTML = 
                `<div style="color: red; padding: 20px; text-align: center;">
                    <b>ไม่สามารถโหลดรายงานได้</b><br>
                    URL: ${fileUrl} <br>
                    (ไฟล์อาจถูกลบหรือไม่มีสิทธิ์เข้าถึง)
                </div>`;
        });
}

function viewer_restoreDashboardState(stateObj) {
    const container = document.getElementById("table-containers3");
    if (container) {
        container.className = stateObj.layoutClass || "";
    }

    document.querySelectorAll('.dash-item').forEach(el => el.remove());
    viewer_WidgetsConfig = {}; 
    viewer_SelectedItems = []; 

// 🚀 [เพิ่ม 2 บรรทัดนี้] ล้างความจำเก่าทิ้ง ป้องกันของรายงานเก่าติดมา!
    viewer_LoadedCheeseData = {}; 
    viewer_ActiveWidgetId = null;


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
            <div style="color: #adb5bd; font-size: 14px; text-align: center;  padding-top:20px;">
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
            <div style="display: flex; flex-direction: column; justify-content: center; align-items: center; height: 100%; color: #0078d7; ">
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
// ============================================================================
// 3. ฟังก์ชันดึงข้อมูล และวาดกราฟ/ข้อความ (อัปเดตให้จำค่า LocalStorage ตอนโหลด!)
// ============================================================================

async function viewer_loadWidgetDataFromURL(widgetId, rawUrl) {
    if (!rawUrl || rawUrl.trim() === "") return;

    if(!viewer_WidgetsConfig[widgetId]) viewer_WidgetsConfig[widgetId] = {};
    viewer_WidgetsConfig[widgetId].dataSource = rawUrl;

    const statusText = document.getElementById(`status-${widgetId}`);
    if (statusText) statusText.innerText = "⏳ กำลังดึงข้อมูลจาก Server...";

    try {
        let widgetCheeseData = null;

        // 🌟 1. ดักจับตรงนี้! เช็ค LocalStorage ก่อนว่าเคยเซฟตัวกรองไว้ไหม
        const storageKey = `custom_filter_${report_id}_${widgetId}`;
        const savedFilter = localStorage.getItem(storageKey);

        if (savedFilter) {
            // 👉 ถ้าเคยเซฟไว้ ให้โหลดจากเครื่องมาใช้เลย! (กราฟจะได้เป็นค่าที่เคยฟิลเตอร์ไว้)
            widgetCheeseData = JSON.parse(savedFilter);
            viewer_LoadedCheeseData[widgetId] = widgetCheeseData; // ยัดลง Cache ด้วย
            console.log(`[Viewer] ♻️ พบตัวกรองเก่าของ ${widgetId} นำมาใช้ปั้นกราฟทันที!`);
        } else {
            // 👉 ถ้าไม่มี (โหลดครั้งแรก) ค่อยไปโหลด .cheese ต้นฉบับจาก Server
            const formattedURL = rawUrl.replace("Idoc:/", "/document-filemanager/");
            const cheeseRes = await fetch(formattedURL);
            if (!cheeseRes.ok) throw new Error("โหลดไฟล์ .cheese ไม่สำเร็จ");
            
            widgetCheeseData = await cheeseRes.json();
            viewer_LoadedCheeseData[widgetId] = widgetCheeseData; // ยัดลง Cache
            console.log(`[Viewer] 📥 โหลดตัวกรองต้นฉบับจาก Server สำหรับ ${widgetId}`);
        }

        if (!widgetCheeseData || !widgetCheeseData.FilterChain || widgetCheeseData.FilterChain.length === 0) {
            throw new Error("ไฟล์นี้ยังไม่มีเงื่อนไขการกรอง");
        }

        // 🌟 2. เอาข้อมูลที่ได้ (ไม่ว่าจะจากเครื่อง หรือ Server) ส่งไปหา Java เพื่อคิวรี่
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
        // 🚀 🌟 [เพิ่มตรงนี้!] ดักชนิดกราฟด้วย! จะ Auto-Select ก็ต่อเมื่อ "ไม่ใช่กล่องข้อความ"
        const currentType = viewer_WidgetsConfig[widgetId].chartType;
        if (!viewer_ActiveWidgetId && currentType && currentType !== 'text') {
            console.log(`🚀 [Auto-Select] กราฟ (ที่ไม่ใช่ Text) ${widgetId} โหลดเสร็จตัวแรก สั่งเปิดตารางอัตโนมัติ!`);
            viewer_toggleSelect(widgetId);
        }
        

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





























  // 2. ฟังก์ชันกรองและฉีดข้อมูลลง Sidebar
    // currentCategory = หมวดหมู่ที่ต้องการค้นหา
    // currentReportId = ID ของรายงานที่กำลังเปิดอยู่ (ส่งมาเพื่อจะได้ไม่โชว์ซ้ำกับอันที่กำลังดู)
    function renderRelatedReportsSidebar(currentCategory, currentReportId) {
        const section = document.getElementById('related-reports-section');
        const listContainer = document.getElementById('related-reports-list');
        
        if (!section || !listContainer) return;

        // 🌟 กรองข้อมูลจาก Array: เอาเฉพาะที่หมวดหมู่ตรงกัน และ ID ต้องไม่ใช่อันที่ดูอยู่
        let relatedReports = reportsData2.filter(r => 
            r.category === currentCategory && String(r.id) !== String(currentReportId)
        );

        if (relatedReports.length === 0) {
            // ถ้าไม่มีรายงานอื่นเลย ให้ซ่อนหมวดหมู่นี้ไปเลย หรือจะโชว์ข้อความก็ได้
            listContainer.innerHTML = `<div style="padding: 6px 12px; font-size: 12px; color: #888;">ไม่มีรายงานอื่น</div>`;
            section.style.display = 'block';
            return;
        }

        let html = '';
        
        // 🌟 ลูปข้อมูลแค่ 10 อันแรก แล้วสร้างเป็น HTML
        relatedReports.slice(0, 10).forEach(report => {
            html += `
                <div class="nav-item" title="${report.reportName}" 
                     onclick="getData('/ShowReportanalytics-filemanager/${report.id}', 'analytics-main-content')">
                    
                    <div class="nav-icon">
                        <svg viewBox="0 0 24 24" style="width: 14px; height: 14px; opacity: 0.7;">
                            <path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 16H8v-2h8v2zm0-4H8v-2h8v2zm-3-5V3.5L18.5 9H13z"/>
                        </svg>
                    </div>
                    
                    <span style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 160px; font-size: 13px;">
                        ${report.reportName}
                    </span>
                </div>
            `;
        });

        // 🌟 ฉีด HTML เข้าไปใน Sidebar และเปิดให้มองเห็น
        listContainer.innerHTML = html;
        section.style.display = 'block';
    }
    

function viewer_drawChart(widgetId) {
    const config = viewer_WidgetsConfig[widgetId];
    const boxEl = document.getElementById(widgetId);

    if (!boxEl || !config || !config.dataset || !config.dataset.data) return;
    if (!config.axisX || !config.axisY) return; 

    const rawData = config.dataset.data; 

    // 1. โหลดการตั้งค่าจาก Cache (ดึง aggType มาใช้ด้วย)
    let cheeseData = viewer_LoadedCheeseData[widgetId] || {};
    let graphSettings = cheeseData.GraphSettings || { dateDisplay: 'day', cutoffCycle: '1-end', aggType: 'sum' };

    // 2. ทำความสะอาดข้อมูล
    let cleanedData = rawData.map(row => {
        let newRow = { ...row }; 
        let rawXVal = row[config.axisX];
        
        // 🧹 ถอดแท็ก HTML ออก
        let xVal = rawXVal !== null && rawXVal !== undefined ? String(rawXVal).replace(/(<([^>]+)>)/gi, "").trim() : null;

        let yVal = row[config.axisY];
        newRow._cleanY = (yVal !== null && yVal !== undefined) ? parseFloat(String(yVal).replace(/(<([^>]+)>)/gi, "")) || 0 : 0;

        // ========================================================
        // 🧠 วุ้นแปลภาษาวันที่ (แก้บั๊ก Timezone กระโดด)
        // ========================================================
        let d = null;
        if (xVal) {
            if (/^\d+$/.test(xVal)) {
                let numVal = Number(xVal);
                if (numVal > 0 && numVal < 20000000000) numVal *= 1000;
                d = new Date(numVal);
            } 
            else {
                let matchYMD = xVal.match(/(\d{4})[\-\/](\d{2})[\-\/](\d{2})/);
                let matchDMY = xVal.match(/(\d{2})[\-\/](\d{2})[\-\/](\d{4})/);
                
                // ใช้ parseInt ยัดค่าตรงๆ ป้องกันเบราว์เซอร์แปลงเวลา UTC มั่ว
                if (matchYMD) {
                    d = new Date(parseInt(matchYMD[1], 10), parseInt(matchYMD[2], 10) - 1, parseInt(matchYMD[3], 10));
                } else if (matchDMY) {
                    d = new Date(parseInt(matchDMY[3], 10), parseInt(matchDMY[2], 10) - 1, parseInt(matchDMY[1], 10));
                } else {
                    let fallbackDate = new Date(xVal);
                    if (!isNaN(fallbackDate.getTime())) d = fallbackDate;
                }
            }
        }
        
        if (d && isNaN(d.getTime())) d = null;
        // ========================================================

        if (d) {
            let year = d.getFullYear();
            let month = d.getMonth() + 1;
            let date = d.getDate();


			            // 🚀 แก้บั๊ก: ปัดเดือน (Cutoff) จะทำงานก็ต่อเมื่อดูกราฟแบบ 'รายเดือน' หรือ 'รายปี' เท่านั้น!
			if (graphSettings.dateDisplay !== 'day' && graphSettings.cutoffCycle === '26-25' && date >= 26) {
			    month += 1;
			    if (month > 12) { month = 1; year += 1; }
			}
			            

            // แปลงรูปแบบตาม View Settings
            if (graphSettings.dateDisplay === 'year') {
                newRow._cleanX = `${year}`; 
            } else if (graphSettings.dateDisplay === 'month') {
                newRow._cleanX = `${year}-${month.toString().padStart(2,'0')}`; 
            } else {
                newRow._cleanX = `${year}-${month.toString().padStart(2,'0')}-${date.toString().padStart(2,'0')}`; 
            }
        } else {
            newRow._cleanX = xVal ? String(xVal) : 'ไม่ระบุ';
        }
        
        return newRow;
    });

    // ========================================================
    // 🚀 3. ทำการรวมยอดและคำนวณ 
    // ========================================================
    let aggType = graphSettings.aggType || 'sum';
    let aggregatedMap = {};

    cleanedData.forEach(row => {
        let g1 = config.groupBy ? (row[config.groupBy] || 'none') : 'none';
        let g2 = config.groupBy2 ? (row[config.groupBy2] || 'none') : 'none';
        let key = row._cleanX + "|||" + g1 + "|||" + g2;
        
        if (!aggregatedMap[key]) {
            aggregatedMap[key] = { 
                ...row, 
                _aggVal: (aggType === 'min') ? Infinity : ((aggType === 'max') ? -Infinity : 0),
                _count: 0,
                _sum: 0
            };
        }

        let val = row._cleanY; 
        
        aggregatedMap[key]._count += 1;
        aggregatedMap[key]._sum += val;

        if (aggType === 'max') {
            if (val > aggregatedMap[key]._aggVal) aggregatedMap[key]._aggVal = val;
        } else if (aggType === 'min') {
            if (val < aggregatedMap[key]._aggVal) aggregatedMap[key]._aggVal = val;
        }
    });

    cleanedData = Object.values(aggregatedMap).map(item => {
        if (aggType === 'sum') item._cleanY = item._sum;
        else if (aggType === 'avg') item._cleanY = item._count > 0 ? (item._sum / item._count) : 0;
        else if (aggType === 'count') item._cleanY = item._count; 
        else if (aggType === 'max') item._cleanY = item._aggVal === -Infinity ? 0 : item._aggVal;
        else if (aggType === 'min') item._cleanY = item._aggVal === Infinity ? 0 : item._aggVal;
        return item;
    });
    
    // 4. สวิตช์แยกโหมด และเตรียมแกน X ทั้งหมด
    const isStackedBar = config.chartType === 'stackedBar';
    const applyTwoGroupHack = isStackedBar && (config.groupBy && config.groupBy !== "") && (config.groupBy2 && config.groupBy2 !== "");

    const extractCategory = (rawText) => {
        let text = String(rawText || 'ไม่ระบุ').trim();
        if (text === 'ไม่ระบุ') return text;
        let firstWord = text.split(/[\s,]+/)[0]; 
        return firstWord;
    };

    const getGroup1 = (row) => config.groupBy ? extractCategory(row[config.groupBy]) : "";
    const getGroup2 = (row) => config.groupBy2 ? extractCategory(row[config.groupBy2]) : "";

    const getXKey = (row) => applyTwoGroupHack ? (row._cleanX + '|' + getGroup1(row)) : row._cleanX;
    
    const allXKeys = [...new Set(cleanedData.map(getXKey))].sort();

    window.viewer_ChartState = window.viewer_ChartState || {};
    if (!window.viewer_ChartState[widgetId]) {
        window.viewer_ChartState[widgetId] = {
            endIndex: allXKeys.length,
            windowSize: 10,
            lastTotalKeys: allXKeys.length
        };
    }
    let state = window.viewer_ChartState[widgetId];
    
    if (state.lastTotalKeys !== allXKeys.length) {
        state.endIndex = allXKeys.length; 
        state.lastTotalKeys = allXKeys.length;
    }
    
    state.totalKeys = allXKeys.length;

    if (state.endIndex > state.totalKeys) state.endIndex = state.totalKeys;
    if (state.endIndex === 0 && state.totalKeys > 0) state.endIndex = Math.min(state.windowSize, state.totalKeys);

    let startIndex = Math.max(0, state.endIndex - state.windowSize);
    const uniqueXKeys = allXKeys.slice(startIndex, state.endIndex);

    let chartDatasets = [];

    if (applyTwoGroupHack) {
        const uniqueG2 = [...new Set(cleanedData.map(getGroup2))].filter(Boolean);
        chartDatasets = uniqueG2.map((g2Name, index) => {
            const groupData = uniqueXKeys.map(xKey => {
                const foundRow = cleanedData.find(row => getXKey(row) === xKey && getGroup2(row) === g2Name);
                return foundRow ? foundRow._cleanY : null;
            });
            const color = `hsl(${(index * 360) / (uniqueG2.length || 1)}, 70%, 50%)`;
            return {
                label: g2Name, 
                data: groupData,
                backgroundColor: color,
                borderColor: color,
                borderWidth: 2,
                fill: true,
                tension: 0.1
            };
        });
    } else if (config.groupBy && config.groupBy !== "") {
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
                backgroundColor: config.chartType === 'pie' || config.chartType === 'doughnut' ? undefined : color,
                borderColor: config.chartType === 'pie' || config.chartType === 'doughnut' ? '#fff' : color,
                borderWidth: 2,
                fill: (config.chartType === 'bar' || config.chartType === 'stackedBar'),
                tension: 0.1
            };
        });
    } else {
        const dataValues = uniqueXKeys.map(xKey => {
            const foundRow = cleanedData.find(row => getXKey(row) === xKey);
            return foundRow ? foundRow._cleanY : 0;
        });
        let bgColors = config.themeColor || '#0078d7';
        if (config.chartType === 'pie' || config.chartType === 'doughnut') {
            bgColors = uniqueXKeys.map((_, i) => `hsl(${(i * 360) / (uniqueXKeys.length || 1)}, 70%, 50%)`);
        }
        chartDatasets = [{
            label: config.axisY,
            data: dataValues,
            backgroundColor: bgColors,
            borderColor: config.chartType === 'pie' || config.chartType === 'doughnut' ? '#ffffff' : (config.themeColor || '#0078d7'),
            borderWidth: 2,
            fill: (config.chartType === 'bar' || config.chartType === 'stackedBar'),
            tension: 0.1
        }];
    }

    const displayXLabels = uniqueXKeys.map(key => {
        if (applyTwoGroupHack) {
            let [datePart, namePart] = key.split('|');
            if (datePart.match(/^\d{4}-\d{2}-\d{2}$/)) {
                let parts = datePart.split('-');
                datePart = `${parts[2]}/${parts[1]}/${parts[0]}`;
            } else if (datePart.match(/^\d{4}-\d{2}$/)) {
                let parts = datePart.split('-');
                datePart = `${parts[1]}/${parts[0]}`;
            }
            return [datePart, namePart]; 
        } else {
            if (key.match(/^\d{4}-\d{2}-\d{2}$/)) {
                let parts = key.split('-');
                return `${parts[2]}/${parts[1]}/${parts[0]}`;
            } else if (key.match(/^\d{4}-\d{2}$/)) {
                let parts = key.split('-');
                return `${parts[1]}/${parts[0]}`;
            }
            return key;
        }
    });

    let actualChartType = isStackedBar ? 'bar' : config.chartType;
    const btnPrevDisabled = startIndex === 0;
    const btnNextDisabled = state.endIndex >= state.totalKeys;

    boxEl.innerHTML = `
        <div style="width: 100%; height: 100%; padding: 15px; box-sizing: border-box; display: flex; flex-direction: column;">
            <div style="flex-grow: 1; width: 100%; min-height: 280px; position: relative;">
                <canvas id="viewer-canvas-${widgetId}"></canvas>
            </div>
            ${ (actualChartType !== 'pie' && actualChartType !== 'doughnut' && state.totalKeys > state.windowSize) ? `
            <div style="display: flex; justify-content: center; gap: 15px; margin-top: 10px; align-items: center;">
                <button onclick="window.viewer_shiftChartWindow('${widgetId}', -1)" 
                    style="padding: 5px 15px; background: #ffffff; border: 1px solid #d1d5db; color: #374151; font-family: 'Segoe UI', sans-serif; cursor: ${btnPrevDisabled ? 'not-allowed' : 'pointer'}; opacity: ${btnPrevDisabled ? 0.5 : 1};" 
                    ${btnPrevDisabled ? 'disabled' : ''}>
                    ก่อนหน้า
                </button>
                <span style="font-size: 12px; color: #6b7280; font-family: 'Segoe UI', sans-serif;">
                    แสดง ${startIndex + 1} - ${state.endIndex} จาก ${state.totalKeys}
                </span>
                <button onclick="window.viewer_shiftChartWindow('${widgetId}', 1)" 
                    style="padding: 5px 15px; background: #ffffff; border: 1px solid #d1d5db; color: #374151; font-family: 'Segoe UI', sans-serif; cursor: ${btnNextDisabled ? 'not-allowed' : 'pointer'}; opacity: ${btnNextDisabled ? 0.5 : 1};" 
                    ${btnNextDisabled ? 'disabled' : ''}>
                    ถัดไป
                </button>
            </div>
            ` : '' }
        </div>
    `;

    if (typeof window.viewer_shiftChartWindow === 'undefined') {
        window.viewer_shiftChartWindow = function(wId, direction) {
            let st = window.viewer_ChartState[wId];
            if (!st) return;

            if (direction === -1) {
                st.endIndex = Math.max(st.windowSize, st.endIndex - st.windowSize);
            } else {
                st.endIndex = Math.min(st.totalKeys, st.endIndex + st.windowSize);
            }
            viewer_drawChart(wId); 
        };
    }

    if (viewer_ChartInstances[widgetId]) viewer_ChartInstances[widgetId].destroy();

    const ctx = document.getElementById(`viewer-canvas-${widgetId}`).getContext('2d');
    viewer_ChartInstances[widgetId] = new Chart(ctx, {
        type: actualChartType,
        data: {
            labels: displayXLabels,
            datasets: chartDatasets
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            spanGaps: true,
            animation: { duration: 300 },
            interaction: { mode: 'index', intersect: false },
            plugins: {
                legend: { display: config.showLabels !== "false", position: 'top' },
                tooltip: {
                    enabled: true,
                    callbacks: {
                        footer: function(tooltipItems) {
                            if (isStackedBar) {
                                let total = 0;
                                tooltipItems.forEach(item => total += item.parsed.y); 
                                return 'ยอดรวม: ' + total.toLocaleString(); 
                            }
                            return '';
                        }
                    }
                }
            },
            scales: (actualChartType === 'pie' || actualChartType === 'doughnut') ? {} : {
                x: {
                    stacked: isStackedBar,
                    ticks: {
                        autoSkip: false,
                        maxRotation: applyTwoGroupHack ? 0 : 45, 
                        minRotation: 0,
                        font: { size: 11, family: "'Segoe UI', sans-serif" }
                    }
                },
                y: { 
                    stacked: isStackedBar, 
                    beginAtZero: true,
                    ticks: { font: { family: "'Segoe UI', sans-serif" } }
                }
            }
        }
    });
}

function viewer_prevPage() {
    if (viewer_CurrentPage > 1) {
        viewer_CurrentPage--;
        if (viewer_ActiveWidgetId) viewer_renderDataToTable(viewer_ActiveWidgetId);
    }
}

function viewer_nextPage() {
    // บวกหน้าไปก่อน 1 สเตป แล้วเดี๋ยวไปให้เครื่องจักรคำนวณเอาว่าตันหรือยัง
    viewer_CurrentPage++;
    if (viewer_ActiveWidgetId) viewer_renderDataToTable(viewer_ActiveWidgetId);
}

// ==========================================
// 📥 ฟังก์ชัน Export ข้อมูลทั้งหมดเป็นไฟล์ CSV
// ==========================================
function viewer_exportCSV() {
    // 1. เช็คว่าบอสกดเลือกตารางไหนไว้หรือยัง
    if (!viewer_ActiveWidgetId) {
        alert("กรุณาคลิกเลือกกราฟหรือตารางที่ต้องการส่งออกข้อมูลก่อนครับ");
        return;
    }

    const config = viewer_WidgetsConfig[viewer_ActiveWidgetId];
    if (!config || !config.dataset || !config.dataset.data || config.dataset.data.length === 0) {
        alert("ไม่มีข้อมูลสำหรับส่งออกครับ");
        return;
    }

    const dataset = config.dataset;
    const headers = dataset.headers;
    const data = dataset.data;

    // 2. ใส่ BOM (Byte Order Mark) เพื่อให้ Excel อ่านภาษาไทยได้ไม่เพี้ยน
    let csvContent = "\uFEFF"; 
    
    // 3. สร้างแถว Header (หัวตาราง)
    let headerRow = ["#", ...headers, "Reference_id"]
        .map(h => `"${String(h).replace(/"/g, '""')}"`) // ป้องกันกรณีชื่อหัวตารางมีเครื่องหมาย "
        .join(",");
    csvContent += headerRow + "\n";

    // 4. วนลูปข้อมูลทั้งหมด (ทะลุขีดจำกัด 100 แถวไปเลย!)
    data.forEach((row, index) => {
        let rowData = [index + 1]; // คอลัมน์ที่ 1: ลำดับที่
        
        headers.forEach(h => {
            let val = row[h];
            if (val === null || val === undefined) val = "";
            
            // ถ้าข้อมูลดันมีแท็ก HTML (เช่น แท็ก <a> ลิงก์) ให้ถอดออกเหลือแต่ตัวอักษรเพียวๆ
            if (typeof val === 'string') {
                val = val.replace(/(<([^>]+)>)/gi, "");
            }
            rowData.push(val);
        });

        // คอลัมน์สุดท้าย: Reference_id
        let refId = row['id'] || "-";
        rowData.push(refId);

        // ประกอบร่างทีละแถว คั่นด้วยลูกน้ำ
        let csvRow = rowData.map(val => `"${String(val).replace(/"/g, '""')}"`).join(",");
        csvContent += csvRow + "\n";
    });

    // 5. สั่งสร้างไฟล์จำลองและกดดาวน์โหลดอัตโนมัติ
    const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    
    // ตั้งชื่อไฟล์ (ใช้วันที่และเวลาปัจจุบันต่อท้าย)
    const fileName = `Export_Data_${viewer_ActiveWidgetId}_${new Date().toISOString().slice(0,10)}.csv`;
    
    link.setAttribute("href", url);
    link.setAttribute("download", fileName);
    document.body.appendChild(link);
    link.click(); // จำลองการคลิกดาวน์โหลด
    document.body.removeChild(link); // ลบทิ้งหลังดาวน์โหลดเสร็จ
}