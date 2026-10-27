/**flow()
 * ฟังก์ชันสำหรับส่งฟอร์มด้วย AJAX พร้อมระบบตรวจสอบความถูกต้อง
 * @param {string} url - URL ที่จะส่งข้อมูลไป
 * @param {string} formId - ID ของ Form element
 * @param {string} div - ID ของ Div ที่จะแสดง Response
 * @param {string} Progress_id - ID สำหรับใช้อ้างอิงการโหลดข้อมูลใหม่
 * @param {string} Progress - ชื่อ Title ใหม่ที่จะตั้ง
 */
function flow(url, formId, div, Progress_id, Progress) {
    // 1. ดึง Element ฟอร์มและปุ่มกด
    var form = document.getElementById(formId);
    var submitBtn = document.activeElement; // ปุ่มที่ถูกกดล่าสุด

    if (!form) {
        console.error("ไม่พบ Form ID: " + formId);
        return;
    }

    // 2. ตรวจสอบ HTML5 Validation (required, email, etc.)
    if (!form.checkValidity()) {
        form.reportValidity(); // แสดงบอลลูนเตือนของ Browser
        return; // หยุดการทำงานถ้าข้อมูลไม่ครบ
    }



    var loadingBar = document.getElementById("bar" + div);
    if (loadingBar) {
        loadingBar.style.display = 'block';
    }

    // ป้องกันการกดซ้ำ (Disable ปุ่ม)
    if (submitBtn && (submitBtn.tagName === 'BUTTON' || submitBtn.tagName === 'INPUT')) {
        submitBtn.disabled = true;
    }

    // 4. จัดการข้อมูลและเตรียม AJAX
    var formData = new FormData(form);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);

    // 5. เมื่อส่งข้อมูลเสร็จสิ้น
    xhr.onload = function () {
        // คืนค่าปุ่มให้กดได้ใหม่
        if (submitBtn) submitBtn.disabled = false;
        
        // ซ่อน Loading Bar
        if (loadingBar) loadingBar.style.display = 'none';

        if (xhr.status >= 200 && xhr.status < 300) {
            // กรณีสำเร็จ
            var targetDiv = document.getElementById(div);
            if (targetDiv) {
                targetDiv.innerHTML = xhr.responseText;
            }
				if (xhr.status == 201) {
				    // 1. ดึงค่า send และ to มาเปรียบเทียบ
				    // ปรับเปลี่ยน Selector ตามโครงสร้าง HTML จริงของคุณ เช่น document.getElementById('send').value
				    let sendVal = document.getElementById('send')?.value || ""; 
				  
								// 2. ดึงค่าจาก select 'to' (เอาค่า value ที่เลือกอยู่)
				    let toSelect = document.getElementById('to');
				    let toVal = toSelect ? toSelect.options[toSelect.selectedIndex]?.value : null;
	
			
				
				    if (sendVal !== "" && sendVal === toVal) {
				        // 2. ถ้าตรงกัน ให้หาปุ่ม refresh และสั่งคลิก
				        let refreshBtn = document.getElementById("refresh-" + Progress_id);
				        if (refreshBtn) {
				            refreshBtn.click();
				        }
				        
				        // (Option) ถ้าต้องการปิดหน้าต่างด้วยหลังจากคลิก refresh
				        if (typeof closeed2 === 'function') closeed2('draggable-window6');
				        
				    } else {
				        // 3. ถ้าไม่ตรงกัน ให้ทำงานตาม Flow ปกติเดิม
				        if (typeof closeed2 === 'function') closeed2('draggable-window6');
				        if (typeof getData === 'function') getData('/app-filemanager/' + Progress_id, 'microsofts' + Progress_id);
				        if (typeof setTitleApp === 'function') setTitleApp('app' + Progress_id, Progress);
				    }
				}
        } else {
            // กรณีเกิด Error จาก Server (เช่น 400, 500)
            var errorMsg = xhr.responseText || "เกิดข้อผิดพลาดในการส่งข้อมูล";
            alert("Error: " + errorMsg);

            var targetDiv = document.getElementById(div);
            if (targetDiv) {
                targetDiv.innerHTML = `<p style="color: red;">${errorMsg}</p>`;
            }
            console.error(`HTTP error! Status: ${xhr.status}`);
        }
    };

    // 6. กรณีเกิดปัญหาทาง Network
    xhr.onerror = function () {
        if (submitBtn) submitBtn.disabled = false;
        if (loadingBar) loadingBar.style.display = 'none';
        alert('ไม่สามารถติดต่อ Server ได้ (Network Error)');
    };

    // 7. เริ่มส่งข้อมูล
    xhr.send(formData);
}





function postData(url, formId, div) {
    // 🌟 1. ใส่ Promise ครอบ เพื่อให้ await รอได้
    return new Promise((resolve) => {
        
        var element = document.getElementById("bar" + div);
        if (element) {
            element.style.display = 'block';
        }

        var form = document.getElementById(formId);
        
        // ตรวจสอบ HTML5 Validation
        if (!form.checkValidity()) {
            form.reportValidity(); 
            if (element) element.style.display = 'none';
            resolve(); // 🌟 ปล่อยให้ไปต่อ (เลิกรอ) ถ้าไม่ได้กรอกข้อมูล
            return; 
        }

        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url, true);

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                var container = document.getElementById(div);
                container.innerHTML = xhr.responseText; 
                
                if (typeof runScripts === 'function') {
                    runScripts(container);
                }

                var fileInput = document.getElementById('file');
                if (fileInput) fileInput.value = "";

                var ID = document.getElementById(formId);
                if (ID) ID.reset();

            } else {
                alert(`เกิดข้อผิดพลาด  : ข้อมูลซ้ำ หรือไม่พบข้อมูล หรือกรอกไม่ครบถ้วน`);
            }

            if (element) {
                element.style.display = 'none';
            }
            
            resolve(); // 🌟 2. สั่ง resolve เมื่อทุกอย่างโหลดเสร็จ (เลิกรอและไปบรรทัดต่อไปได้)
        };

        xhr.onerror = function () {
            alert('Network error occurred');
            if (element) {
                element.style.display = 'none';
            }
            resolve(); // 🌟 กรณีเน็ตพัง ก็สั่งเลิกรอเช่นกัน
        };

        xhr.send(formData);
    });
}


function postDataNotReset(url, formId, div) {


	  
		var form = document.getElementById(formId);
	    // 2. ตรวจสอบ HTML5 Validation (required, email, etc.)
	    if (!form.checkValidity()) {
	        form.reportValidity(); // แสดงบอลลูนเตือนของ Browser
	        return; // หยุดการทำงานถ้าข้อมูลไม่ครบ
	    }

	    var element = document.getElementById("bar" + div);
	    if (element) {
	        element.style.display = 'block';
	    }



	    var formData = new FormData(document.getElementById(formId));

	    

	    
	    var xhr = new XMLHttpRequest();
	    xhr.open('POST', url, true);

	    xhr.onload = function () {
	        if (xhr.status >= 200 && xhr.status < 300) {
	           
	        	var container = document.getElementById(div);
	            container.innerHTML = xhr.responseText; // 1. ยัด HTML เข้าไปก่อน
	        	runScripts(container);
	        	
	        	
	        	
	        } else {
	          // แสดงข้อความข้อผิดพลาดที่เป็นมิตร
	        var userFriendlyMessage = xhr.responseText || errorMessage;
	        alert(userFriendlyMessage);
	        
	        // หรือคุณอาจแสดงข้อความใน div เฉพาะได้
	        if (responseDiv) {
	            responseDiv.innerHTML = `<p style="color: red;">${userFriendlyMessage}</p>`;
	        }

	        // Log ข้อผิดพลาด
	        console.error(`HTTP error! Status: ${xhr.status}, Response: ${xhr.responseText}`);
	        }

	        if (element) {
	            element.style.display = 'none';
	        }
	    };

	    xhr.onerror = function () {
	        alert('Network error occurred');
	    };

	    // Send the form data in the request body
	    xhr.send(formData);
	}





    // 1. ฟังก์ชันดึงข้อมูลจาก API
    async function loadGamificationData() {

        try {
            const response = await fetch('/mywidget-filemanager');
            const data = await response.json();
               console.log(data);
            
            
            renderWidget(data);
        } catch (error) {
            console.error("Error loading gamification data:", error);
        }
    }

 // // 2. ฟังก์ชันวาดหน้าจอ (Render DOM)
    function renderWidget(data) {
        // อัปเดต Profile
        const pName = data.playerName ? data.playerName : 'Unknown';
        document.querySelector('.player-profile .avatar').innerText = pName.charAt(0);
        document.querySelector('.player-info h6').innerText = pName;
        document.querySelector('.player-info span').innerHTML = 
            `✨ Lv.${data.level} ${data.levelTitle} | XP รวม: ${data.totalXp}`;

        // อัปเดต ภาพรวม (Overall)
        document.querySelector('.overall-header span:last-child').innerText = `${data.overallPercent}%`;
        document.querySelector('.overall-summary .progress-bar').style.width = `${data.overallPercent}%`;
        document.querySelector('.overall-text').innerText = `สำเร็จแล้ว ${data.completedQuests} จาก ${data.totalQuests} ภารกิจ`;

        const questsListDiv = document.querySelector('.quests-list');
        questsListDiv.innerHTML = ''; 

        // --- ✨ ส่วนที่เพิ่ม: จัดเรียงลำดับ (Sort) ก่อนวาดหน้าจอ ---
        // กำหนดคะแนน: รายวัน(1) < รายสัปดาห์(2) < รายเดือน(3) < รายปี(4)
        const priority = {
            'EVERY_DAY': 1,
            'EVERY_WEEK': 2,
            'EVERY_MONTH': 3,
            'EVERY_YEAR': 4
        };

        if (data.quests && data.quests.length > 0) {
            data.quests.sort((a, b) => {
                const weightA = priority[a.frequency.toUpperCase()] || 99;
                const weightB = priority[b.frequency.toUpperCase()] || 99;
                return weightA - weightB; // เรียงจากน้อยไปมาก (1 ไปหา 4)
            });
        }
        // --------------------------------------------------

        data.quests.forEach(quest => {
            // --- สร้างป้ายวงกลมตัวย่อ (D, W, M, Y) ---
            let freqBadge = '';
            const freq = quest.frequency ? quest.frequency.toUpperCase() : '';
            switch(freq) {
                case 'EVERY_DAY':   freqBadge = `<div class="circle-badge bg-day">D</div>`; break;
                case 'EVERY_WEEK':  freqBadge = `<div class="circle-badge bg-week">W</div>`; break;
                case 'EVERY_MONTH': freqBadge = `<div class="circle-badge bg-month">M</div>`; break;
                case 'EVERY_YEAR':  freqBadge = `<div class="circle-badge bg-year">Y</div>`; break;
                default:            freqBadge = `<div class="circle-badge bg-default">G</div>`; 
            }
            
            // --- แยกลอจิกการแสดงผล UI ---
            const isNegativeGoal = ['SLA_24H', 'EXPIRY', 'CALENDAR_MISSED'].includes(quest.goalType);
            let resultText = '';
            let progressBarHtml = '';

            if (isNegativeGoal) {
                let maxScale = quest.targetValue * 2;
                if (maxScale === 0) maxScale = 2; 
                let markerPos = (quest.currentResult / maxScale) * 100;
                if (markerPos > 100) markerPos = 100;

                let textColor = quest.currentResult <= quest.targetValue ? '#28C76F' : '#EA5455';
                resultText = `<span style="font-weight: bold; font-size: 13px; color: ${textColor};">${quest.currentResult}</span> <span style="font-size: 11px; color: #6e6b7b;">/ ห้ามเกิน ${quest.targetValue}</span>`;

                progressBarHtml = `
                    <div class="progress-container mt-1" style="position: relative; background: linear-gradient(to right, #d4edda 50%, #f8d7da 50%); border-radius: 8px; height: 8px; overflow: hidden;">
                        <div style="position: absolute; left: ${markerPos}%; top: 0; height: 100%; width: 4px; background-color: #212529; z-index: 2; transform: translateX(-50%);"></div>
                    </div>
                `;
            } else {
                resultText = `<span style="font-weight: bold; font-size: 13px;">${quest.currentResult} / ${quest.targetValue}</span>`;
                let barColorClass = quest.percentComplete >= 100 ? 'bg-success' : 'bg-primary';
                progressBarHtml = `
                    <div class="progress-container mt-1" style="background-color: #f0f0f0; border-radius: 8px; height: 8px;">
                        <div class="progress-bar ${barColorClass}" style="width: ${quest.percentComplete}%; border-radius: 8px; height: 100%; transition: width 0.5s ease-in-out;"></div>
                    </div>
                `;
            }
            
            const questHtml = `
                <div class="quest-item" style="margin-bottom: 12px;">
                    <div class="quest-header d-flex justify-content-between align-items-center" style="margin-bottom: 4px;">
                        <div class="quest-name d-flex align-items-center">
                            ${freqBadge}
                            <span style="font-weight: 600; font-size: 13px;">${quest.goalName}</span>
                        </div>
                        <div style="text-align: right;">
                            ${resultText} <span class="quest-reward" style="color: #FF9F43; font-weight: bold; margin-left: 5px;">+${quest.rewardXp}</span>
                        </div>
                    </div>
                    ${progressBarHtml}
                </div>
            `;
            questsListDiv.innerHTML += questHtml;
        });

        feather.replace();
    }
    
    
    // สมมติเรามีปุ่ม "ดูสถิติ" หรือกดที่ Profile รูป Avatar
document.querySelector('.player-profile').style.cursor = 'pointer';
document.querySelector('.player-profile').addEventListener('click', async () => {
    
    // ดึงข้อมูลสถิติ
    const res = await fetch('/mystats-filemanager');
    const statsData = await res.json();

    // สร้างกล่องสถิติ (ตัวอย่างคร่าวๆ คุณสามารถเอาไปครอบ Modal สวยๆ ได้)
    const statsModal = document.createElement('div');
    statsModal.style.cssText = `
        position: fixed; top: 10%; left: 50%; transform: translate(-50%, 0);
        width: 800px; height: 500px; background: white; z-index: 10000;
        padding: 20px; border-radius: 12px; box-shadow: 0 10px 40px rgba(0,0,0,0.3);
        display: flex; flex-direction: column;
    `;
    
    statsModal.innerHTML = `
        <div style="display: flex; justify-content: space-between; margin-bottom: 20px;">
            <h3>📊 สถิติผลงานของคุณ</h3>
            <button onclick="this.parentElement.parentElement.remove()" style="border:none; background:red; color:white; padding:5px 10px; cursor:pointer; border-radius: 5px;">ปิด</button>
        </div>
        <div style="display: flex; gap: 20px; flex: 1;">
            <div style="flex: 2; position: relative;">
                <canvas id="dailyChart"></canvas>
            </div>
            <div style="flex: 1; position: relative;">
                <canvas id="questChart"></canvas>
            </div>
        </div>
    `;
    document.body.appendChild(statsModal);

  // (ส่วนสร้าง statsModal ให้อยู่เหมือนเดิม เปลี่ยนแค่ส่วน Chart ด้านล่างนี้)

    // 1. กราฟแท่งรายวัน (ดูว่าวันไหนหลุดเป้า)
    new Chart(document.getElementById('dailyChart'), {
        type: 'bar', // เปลี่ยนเป็นกราฟแท่ง
        data: {
            labels: statsData.dailyLabels,
            datasets: [{
                label: '% ความสำเร็จรายวัน (100% คือตามเป้า)',
                data: statsData.dailyData,
                backgroundColor: statsData.dailyColors, // สีแยกตามวันที่ถึง/ไม่ถึงเป้า
                borderRadius: 4
            }]
        },
        options: {
            scales: {
                y: { 
                    beginAtZero: true, 
                    max: 100, // ล็อกแกน Y ไว้ที่ 100%
                    title: { display: true, text: 'เปอร์เซ็นต์ความสำเร็จ' }
                }
            },
            plugins: {
                title: { display: true, text: '📅 ภาพรวมความสำเร็จ 60 วันย้อนหลัง' }
            }
        }
    });

    // 2. กราฟแท่งแนวนอน (วิเคราะห์จุดอ่อนว่างานไหนขาด)
    new Chart(document.getElementById('questChart'), {
        type: 'bar',
        data: {
            labels: statsData.questLabels,
            datasets: [{
                label: '% ความสำเร็จเฉลี่ยของงาน (ตัวที่อยู่น้อยๆ คือปัญหา)',
                data: statsData.questData,
                backgroundColor: '#FF9F43', // สีส้มเตือนใจ
                borderRadius: 4
            }]
        },
        options: {
            indexAxis: 'y', // ทำให้เป็นกราฟแนวนอน (Horizontal Bar) อ่านชื่องานง่ายๆ
            scales: {
                x: { beginAtZero: true, max: 100 } // ล็อกแกน X ที่ 100%
            },
            plugins: {
                title: { display: true, text: '⚠️ วิเคราะห์จุดอ่อน: งานที่ทำไม่ค่อยถึงเป้า' }
            }
        }
    });
});


 function renderReports() {
    const container = document.getElementById('report-list-container');
    container.innerHTML = ''; // ล้างค่าเก่าก่อน

    // 🌟 1. ดึงคำค้นหาที่ผู้ใช้พิมพ์ (แปลงเป็นตัวเล็กทั้งหมดเพื่อไม่ให้ติดเรื่องตัวพิมพ์เล็ก-ใหญ่)
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput ? searchInput.value.toLowerCase().trim() : '';

    // 🌟 2. กรองข้อมูลก่อนนำไปจัดกลุ่ม
    const filteredReports = reportsData.filter(report => {
        // หาจาก ชื่อรายงาน, คำอธิบาย, รหัส, หรือ หมวดหมู่
        const textToSearch = `
            ${report.reportName || ''} 
            ${report.description || ''} 
            ${report.id || ''} 
            ${report.category || ''}
        `.toLowerCase();
        
        return textToSearch.includes(searchTerm);
    });

    // 🌟 เช็คว่าถ้าหาไม่เจอเลย ให้แสดงข้อความแจ้งเตือน
    if (filteredReports.length === 0) {
        container.innerHTML = `<div style="padding: 30px; text-align: center; color: #888;">ไม่มีรายงานที่ตรงกับคำค้นหา "${searchTerm}"</div>`;
        return;
    }

    // 3. จัดกลุ่มข้อมูลจาก "ข้อมูลที่กรองแล้ว" (filteredReports แทน reportsData)
    var groupedReports = filteredReports.reduce((acc, report) => {
        var cat = report.category || 'OTHER';
        if (!acc[cat]) {
            acc[cat] = [];
        }
        acc[cat].push(report);
        return acc;
    }, {});

    // 4. วาด HTML ตามหมวดหมู่ (ลูปเหมือนเดิมเป๊ะ)
    for (const [categoryCode, reports] of Object.entries(groupedReports)) {
        // แปลงรหัสเป็นชื่อเต็ม
        var categoryName = getCategoryFullName(categoryCode);

        let categoryHtml = `
            <div class="report-category">
                <div class="category-header">หมวดหมู่: ${categoryName}</div>
        `;

        reports.forEach(report => {
            const iconSvg = typeof getIconSvg === 'function' ? getIconSvg(report.iconType) : '';
            
            categoryHtml += `
                <div class="report-item" onclick="getData('/ShowReportanalytics-filemanager/${report.id}', 'analytics-main-content')">
                    <div class="ri-icon">${iconSvg}</div>
                    <div class="ri-content">
                        <div class="ri-title">${report.reportName}</div>
                        <div class="ri-desc">${report.description || 'ไม่มีคำอธิบาย'}</div>
                    </div>
                    <div class="ri-meta">ID: ${report.id}</div>
                    <div class="ri-action" title="แก้ไข" onclick="event.stopPropagation(); getData('/editAnalytics-filemanager/${report.id}','analytics-main-content');">
                        <svg viewBox="0 0 24 24"><path d="M19.43 12.98c.04-.32.07-.64.07-.98s-.03-.66-.07-.98l2.11-1.65c.19-.15.24-.42.12-.64l-2-3.46c-.12-.22-.39-.3-.61-.22l-2.49 1c-.52-.4-1.08-.73-1.69-.98l-.38-2.65C14.46 2.18 14.25 2 14 2h-4c-.25 0-.46.18-.49.42l-.38 2.65c-.61.25-1.17.59-1.69.98l-2.49-1c-.23-.09-.49 0-.61.22l-2 3.46c-.13.22-.07.49.12.64l2.11 1.65c-.04.32-.07.65-.07.98s.03.66.07.98l-2.11 1.65c-.19.15-.24.42-.12.64l2 3.46c.12.22.39.3.61.22l2.49-1c.52.4 1.08.73 1.69.98l.38 2.65c.03.24.24.42.49.42h4c.25 0 .46-.18.49-.42l.38-2.65c.61-.25 1.17-.59 1.69-.98l2.49 1c.23.09.49 0 .61-.22l2-3.46c.12-.22.07-.49-.12-.64l-2.11-1.65zM12 15.5c-1.93 0-3.5-1.57-3.5-3.5s1.57-3.5 3.5-3.5 3.5 1.57 3.5 3.5-1.57 3.5-3.5 3.5z"/></svg>
                    </div>
                </div>
            `;
        });

        categoryHtml += `</div>`; 
        container.innerHTML += categoryHtml; 
    }
}

    // --- Helper Functions ---
    function getCategoryFullName(code) {
        switch(code) {
            case 'FI': return 'Financial Accounting (FI)';
            case 'SD': return 'Sales & Distribution (SD)';
            case 'MM': return 'Materials Management (MM)';
            case 'MK': return 'Marketing (MK)';
            case 'OF': return 'Office & HR (OF)';
            default: return 'หมวดหมู่อื่นๆ (' + code + ')';
        }
    }

    function getIconSvg(iconType) {
        switch(iconType) {
            case 'bar-chart': return '<svg viewBox="0 0 24 24"><path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14zM7 10h2v7H7zm4-3h2v10h-2zm4 6h2v4h-2z"></path></svg>';
            case 'pie-chart': return '<svg viewBox="0 0 24 24"><path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 2.07c3.61.45 6.48 3.33 6.93 6.93H13V4.07zM4 12c0-4.06 3.07-7.44 7-7.93v15.87c-3.93-.5-7-3.88-7-7.94zm9 7.93V13h6.93c-.45 3.61-3.32 6.48-6.93 6.93z"/></svg>';
            case 'line-chart': return '<svg viewBox="0 0 24 24"><path d="M16 6l2.29 2.29-4.88 4.88-4-4L2 16.59 3.41 18l6-6 4 4 6.3-6.29L22 12V6h-6z"/></svg>';
            case 'table': return '<svg viewBox="0 0 24 24"><path d="M4 3h16c1.1 0 2 .9 2 2v15c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V5c0-1.1.9-2 2-2zm0 4v3h4V7H4zm6 0v3h4V7h-4zm6 0v3h4V7h-4zM4 12v3h4v-3H4zm6 0v3h4v-3h-4zm6 0v3h4v-3h-4zM4 17v3h4v-3H4zm6 0v3h4v-3h-4zm6 0v3h4v-3h-4z"/></svg>';
            case 'finance': return '<svg viewBox="0 0 24 24"><path d="M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 14h-3v3h-2v-3H8v-2h3v-3h2v3h3v2zm-3-7V3.5L18.5 9H13z"/></svg>';
            default: return '<svg viewBox="0 0 24 24"><path d="M19 3H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm0 16H5V5h14v14zM7 10h2v7H7zm4-3h2v10h-2zm4 6h2v4h-2z"></path></svg>';
        }
    }
    // --- รันครั้งแรกเมื่อโหลดหน้าเว็บ ---
