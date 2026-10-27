// เปลี่ยนตัวแปรหลักให้เป็น Object เพื่อเก็บแยกตาม process_id
let eventDataAll = {}; 
let currentViewDateAll = {}; 
let selectedDateKeyAll = {}; 

function callCalendar(API_URL, process_id) {
    // เซ็ตค่าเริ่มต้นให้ปฏิทินตัวนี้
    currentViewDateAll[process_id] = new Date();
    selectedDateKeyAll[process_id] = null;

    // หา ID แบบเจาะจงตาม process_id
    const list = document.getElementById(`eventList_${process_id}`);
    if (!list) return; // ถ้าหาไม่เจอให้หยุดการทำงาน

    list.innerHTML = '<div class="empty-state">Loading...</div>';

    fetch(API_URL)
        .then(response => {
            if(!response.ok) throw new Error("HTTP Error");
            return response.json();
        })
        .then(data => {
            // เก็บข้อมูลแยกลงใน process_id ของใครของมัน
            eventDataAll[process_id] = data;
            
            renderCalendar(process_id);
            selectDefaultDate(process_id);
        })
        .catch(err => {
            console.error("Fetch failed", err);
        });
}

function renderCalendar(process_id) {
    const grid = document.getElementById(`calendarBody_${process_id}`);
    const label = document.getElementById(`monthYearLabel_${process_id}`);
    if (!grid || !label) return;

    grid.innerHTML = '';

    // ดึงวันที่ของเฉพาะปฏิทินตัวนี้
    const currentViewDate = currentViewDateAll[process_id];
    const year = currentViewDate.getFullYear();
    const month = currentViewDate.getMonth(); 

    const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    label.textContent = `${monthNames[month]} - ${year}`;

    const firstDayObj = new Date(year, month, 1);
    const startDayOfWeek = firstDayObj.getDay(); 
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    
    let currentWeekNum = getWeekNumber(firstDayObj);
    let dayCount = 1;
    let nextMonthDay = 1;
    
    const eventData = eventDataAll[process_id];

    for (let i = 0; i < 6; i++) { 
        const wkCell = document.createElement('div');
        wkCell.className = 'cal-cell cal-week-col';
        wkCell.textContent = currentWeekNum++;
        grid.appendChild(wkCell);

        for (let j = 0; j < 7; j++) {
            const cell = document.createElement('div');
            cell.className = 'cal-cell cal-date';
            
            if (i === 0 && j < startDayOfWeek) {
                cell.classList.add('other-month');
            } else if (dayCount > daysInMonth) {
                cell.classList.add('other-month');
                cell.textContent = nextMonthDay++;
            } else {
                const d = dayCount;
                const m = month + 1; 
                const y = year;
                
                cell.textContent = d;

                const dailyEvents = (eventData[y] && eventData[y][m]) ? eventData[y][m][d] : [];
                if (dailyEvents && dailyEvents.length > 0) {
                    const dot = document.createElement('div');
                    dot.className = 'event-dot';
                    const hasPendingStatus = dailyEvents.some(ev => ev.status == 0);
                    dot.style.backgroundColor = hasPendingStatus ? '#ffc107' : '#28a745'; 
                    cell.appendChild(dot);
                }

                const dateKey = `${y}-${m}-${d}`;
                if (dateKey === selectedDateKeyAll[process_id]) {
                    cell.classList.add('selected');
                }

                // คลิกแล้วส่ง process_id ไปด้วย
                cell.onclick = () => {
                    const old = grid.querySelector('.selected');
                    if (old) old.classList.remove('selected');
                    
                    cell.classList.add('selected');
                    selectedDateKeyAll[process_id] = dateKey;
                    renderEvents(y, m, d, process_id);
                };

                dayCount++;
            }
            grid.appendChild(cell);
        }
    }
}

function renderEvents(y, m, d, process_id) {
    const list = document.getElementById(`eventList_${process_id}`);
    if (!list) return;

    list.innerHTML = ''; 
    const eventData = eventDataAll[process_id];
    const events = (eventData[y] && eventData[y][m]) ? eventData[y][m][d] : [];

    if (events.length > 0) {
        events.forEach(ev => {
            const item = document.createElement('div');
            item.className = 'event-item';
            const eDataId = ev.edata_id || 0; 

            item.innerHTML = `
                <div class="event-date-text">Date: ${d}/${m}/${y}</div>
                <div class="event-title">${ev.text}</div>
                <div class="event-sub">By: ${ev.by}</div>
                <div class="event-sub" style="margin-top:4px;">
                    Time: ${ev.startTime} - ${ev.endTime} <br>
                    To: ${ev.to}
                </div>
                <div class="event-sub">
                    Status: 
                    <span style="color: ${ev.status == 1 ? '#28a745' : '#ffc107'}; font-weight: bold;">
                        ${ev.status == 1 ? 'เสร็จแล้ว' : 'ยังไม่ดำเนินการ'}
                    </span>
                </div>
                ${eDataId > 0 ? `
                    <div style="margin-top: 10px;">
                        <button class="btn-run-app2" onclick="runApp2FromEvent('${eDataId}')">
                            เปิด
                        </button>
                    </div>
                ` : ''}
            `;
            list.appendChild(item);
        });
    } else {
        list.innerHTML = `<div class="empty-state">No events for this date.</div>`;
    }
}

function changeMonth(delta, process_id) {
    // ใช้ currentViewDateAll[process_id] แทนตัวเดิม
    currentViewDateAll[process_id].setMonth(currentViewDateAll[process_id].getMonth() + delta);
    
    // สั่งวาดปฏิทินใหม่ โดยส่ง process_id กำกับไปด้วย
    renderCalendar(process_id);
}

function selectDefaultDate(process_id) {
    const currentViewDate = currentViewDateAll[process_id];
    renderEvents(currentViewDate.getFullYear(), currentViewDate.getMonth() + 1, 1, process_id);
}

function getWeekNumber(d) {
    d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
    d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay()||7));
    var yearStart = new Date(Date.UTC(d.getUTCFullYear(),0,1));
    return Math.ceil((((d - yearStart) / 86400000) + 1)/7);
}

    
    // เปลี่ยนชื่อฟังก์ชันจาก runApp2FromEven เป็น runApp2FromEvent ให้ตรงกับที่เรียกใช้ก่อนหน้านี้
 async function runApp2FromEvent(edata_id) {
    // 1. ตรวจสอบว่ามี id ส่งมาหรือไม่
    if (!edata_id || edata_id == 0) {
        alert("ไม่พบรหัสอ้างอิงเอกสาร (edata_id)");
        return;
    }

    try {
        // 2. ยิง AJAX (GET Request) ไปที่ Java Backend
        const response = await fetch(`/apppop-filemanager/${edata_id}`);
        
        // เช็คว่าสถานะ HTTP ปกติหรือไม่ (เช่น ไม่ใช่ 404 หรือ 500)
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // 3. แปลงข้อมูลที่ Java ส่งกลับมาในรูปแบบ JSON
        const data = await response.json();

        // 4. สร้าง Dummy Button ขึ้นมาในหน่วยความจำ (ไม่ได้แสดงบนหน้าจอ)
        const dummyBtn = document.createElement('button');

        // 5. นำค่าที่ Java ส่งมา ประกอบเข้ากับ Attribute ของปุ่ม
        // หมายเหตุ: ใช้ || '' เพื่อป้องกัน error ในกรณีที่ Java ไม่ได้ส่งค่านั้นๆ มา (กลายเป็น undefined)
        
        // ดักจับ Key ทั้งแบบที่มีคำว่า 'data-' นำหน้า และแบบไม่มี เพื่อความยืดหยุ่น
        dummyBtn.setAttribute('data-id', data['data-id'] || data.id || '');
        dummyBtn.setAttribute('data-name', data['data-name'] || data.name || '');
        dummyBtn.setAttribute('data-link', data['data-link'] || data.link || '');
        dummyBtn.setAttribute('data-process_refer', data['data-process_refer'] || data.process_refer || '');
        dummyBtn.setAttribute('data-logo', data['data-logo'] || data.logo || '');

        // 6. ส่งปุ่มจำลองไปรันใน App2()
        if (typeof App2 === 'function') {
            App2(dummyBtn);
        } else {
            console.error("ระบบไม่พบฟังก์ชัน App2() สำหรับดำเนินการต่อ");
        }

    } catch (error) {
        console.error("เกิดข้อผิดพลาดระหว่างดึงข้อมูลจาก Server:", error);
        alert("ไม่สามารถดึงข้อมูลเอกสารได้ในขณะนี้");
    }
}