var _csrf="";


// 1. จำลองการดึงรหัสจากระบบ (ในหน้าเว็บจริงอาจจะดึงจาก Hidden Input หรือ URL)


// 1. เพิ่มตัวแปรเก็บตัวเลือกทั้ง 8 หัวข้อ
const formOptions = {
    intro: ['เสนอต่ออายุ', 'เสนอโดยเช็คอัปเดต', 'เสนอโดยถามบริการ', 'เสนอในสิ่งที่ขาด'],
    services: ['อบรม', 'ONE STOP SERVICE', 'ที่ปรึกษา'],
    certStatus: ['กำลังจะหมดอายุ', 'เพิ่งเริ่มทำ', 'Failed/NC', 'ยังปกติ', 'ไม่ทราบ'],
    incumbent: ['ไม่มี', 'ไม่ได้ข้อมูล', 'มี'],
    highStd: ['ต้องการ', 'ไม่ได้ข้อมูล', 'ไม่ต้องการ'],
    goals: ['กฎหมายบังคับ', 'ลูกค้าบังคับ/ส่งออก (Ticket to Play)', 'อัปเกรดภาพลักษณ์'],
    contactRole: ['ผู้ตัดสินใจหรือทำหน้าที่โดยตรง', 'ผู้รวบรวมข้อมูลหรือเป็นส่วนหนึ่งของทีมผู้ตัดสินใจ', 'ผู้ไม่มีส่วนเกี่ยวข้อง'],
    serviceSchedule: ['ยังไม่ทราบ', 'ทราบคร่าว ๆ', 'ทราบกำหนดการ']
};

// 1. เพิ่มตัวเลือกสำหรับฟอร์ม Insight (ปรับแก้คำได้ตามที่ใช้จริงเลยครับ)
const insightOptions = {
    characteristics: ['กลุ่มกำลังซื้อสูง', 'กลุ่มเงื่อนไขมากมาย', 'กลุ่มสนใจข้อมูล', 'กลุ่มสนใจราคา', 'เน้นความสัมพันธ์เเละเชื่อใจ'],
    capital: ['สูง (งบประมาณพร้อม)', 'ปานกลาง (มีงบจำกัด)', 'ต่ำ (เน้นราคาถูก)'],
    horsepower: ['ผู้ตัดสินใจหลัก (CEO/MD/เจ้าของ)', 'ผู้มีอำนาจร่วมตัดสินใจ', 'ผู้รวบรวมข้อมูล/ประสานงาน'],
    headcount: ['1-50 คน (SME)', '51-200 คน (ขนาดกลาง)', '201-500 คน (ขนาดใหญ่)', '500 คนขึ้นไป (องค์กรขนาดใหญ่)']
};





function appendMessage(text, sender) {
	
	const chatHistory = document.getElementById('chatHistory');
	



    const msgDiv = document.createElement('div');
    msgDiv.classList.add('message');
    msgDiv.classList.add(sender === 'user' ? 'msg-user' : 'msg-ai');
    
    // ==========================================================
    // 1. ดักจับแบบมีข้อมูลฝังมา [SHOW_SALES_FORM||1||2||3||4||5||6||7||8]
    // ==========================================================
    if (text.includes('[SHOW_SALES_FORM||')) {
        const tagStart = text.indexOf('[SHOW_SALES_FORM||');
        const tagEnd = text.indexOf(']', tagStart);
        const fullTag = text.substring(tagStart, tagEnd + 1);
        
        let cleanText = text.replace(fullTag, '').replace(/\n/g, '<br>');
        
        // แกะข้อมูล 8 ช่องออกมา (parts[0] คือคำว่า SHOW_SALES_FORM)
        const parts = fullTag.replace('[', '').replace(']', '').split('||');
        const oldIntro = parts[1] || '';
        const oldService = parts[2] || '';
        const oldCert = parts[3] || '';
        const oldIncumbent = parts[4] || '';
        const oldHighStd = parts[5] || '';
        const oldGoals = parts[6] || '';
        const oldRole = parts[7] || '';
        const oldSchedule = parts[8] || '';
        
        // โยนค่าทั้ง 8 เข้าฟังก์ชันสร้างฟอร์ม
        msgDiv.innerHTML = cleanText + generateChatForm(oldIntro, oldService, oldCert, oldIncumbent, oldHighStd, oldGoals, oldRole, oldSchedule);
    } 
    // ==========================================================
    // 2. กรณีพิมพ์มาแค่ [SHOW_SALES_FORM] แบบฟอร์มว่างเปล่า
    // ==========================================================
    else if (text.includes('[SHOW_SALES_FORM]')) {
        let cleanText = text.replace('[SHOW_SALES_FORM]', '').replace(/\n/g, '<br>');
        msgDiv.innerHTML = cleanText + generateChatForm();
    }
    // ==========================================================
    // 3. ดักจับ Tag สำหรับ Edit Insight [SHOW_INSIGHT_FORM_EDIT||...||...||...||...]
    // ==========================================================
    else if (text.includes('[SHOW_INSIGHT_FORM_EDIT||')) {
        const tagStart = text.indexOf('[SHOW_INSIGHT_FORM_EDIT||');
        const tagEnd = text.indexOf(']', tagStart);
        const fullTag = text.substring(tagStart, tagEnd + 1);
        
        let cleanText = text.replace(fullTag, '').replace(/\n/g, '<br>');
        
        // แกะข้อมูลเก่าออกจาก Tag (parts[0] คือ SHOW_INSIGHT_FORM_EDIT)
        const parts = fullTag.replace('[', '').replace(']', '').split('||');
        const oldData = {
            chars: parts[1] || '',
            cap: parts[2] || '',
            horse: parts[3] || '',
            head: parts[4] || ''
        };
        
        msgDiv.innerHTML = cleanText + generateInsightForm('EDIT', oldData);
    } 
    // ==========================================================
    // 4. กรณี Insight Form แบบว่างเปล่า [SHOW_INSIGHT_FORM_FULL]
    // ==========================================================
    else if (text.includes('[SHOW_INSIGHT_FORM_FULL]')) {
        let cleanText = text.replace('[SHOW_INSIGHT_FORM_FULL]', '').replace(/\n/g, '<br>');
        msgDiv.innerHTML = cleanText + generateInsightForm('FULL'); 
    }
    // ==========================================================
    // 5. กรณีแชททั่วไป (ไม่มี Tag อะไรเลย)
    // ==========================================================
    else {
        msgDiv.innerHTML = text.replace(/\n/g, '<br>');
    }
    
    chatHistory.appendChild(msgDiv);
    chatHistory.scrollTop = chatHistory.scrollHeight;
}


function generateInsightForm(formType, oldData = null) {
    let formHtml = `<div style="background:#e2f0d9; padding:15px; border-radius:8px; margin-top:10px; border:1px solid #c5e0b4;">
        <h4 style="margin-top:0; color:#385723;">✏️ แก้ไขข้อมูลเชิงลึกของลูกค้า</h4>
        <form id="insightForm_${formType}" onsubmit="submitInsightFormAsChat(event, '${formType}')">`;
        
    // 1. ปรับ Checkbox ให้ติ๊กอัตโนมัติจากข้อมูลเก่า
    const createCheckboxGroup = (name, label, options, oldCharsStr) => {
        let selectedArr = oldCharsStr ? oldCharsStr.split(',').map(s => s.trim()) : [];
        
        let html = `<div style="margin-bottom: 10px;">
            <label style="display:block; font-size:12px; font-weight:bold; color:#333; margin-bottom: 5px;">${label} <span style="color:red;">*</span>:</label>
            <div style="padding: 8px; border: 1px solid #ccc; border-radius: 4px; background: #fff;">`;
        
        options.forEach(opt => {
            let isChecked = selectedArr.includes(opt) ? 'checked' : '';
            html += `<label style="display:block; font-size:12px; margin-bottom:4px; cursor:pointer;">
                <input type="checkbox" name="${name}" value="${opt}" ${isChecked}> ${opt}
            </label>`;
        });
        
        html += `</div></div>`;
        return html;
    };

    const createTextInput = (id, label, placeholder, oldVal) => {
        let val = (oldVal && oldVal !== 'ไม่ได้ระบุ') ? oldVal : '';
        return `<div style="margin-bottom: 8px;">
            <label style="display:block; font-size:12px; font-weight:bold; color:#333;">${label}:</label>
            <input type="text" id="${id}" name="${id}" placeholder="${placeholder}" value="${val}" style="width:100%; padding:6px; border-radius:4px; border:1px solid #ccc; box-sizing: border-box;">
        </div>`;
    };

    // แสดงฟอร์ม
    const charOptions = ['กลุ่มกำลังซื้อสูง', 'กลุ่มเงื่อนไขมากมาย', 'กลุ่มสนใจข้อมูล', 'กลุ่มสนใจราคา', 'เน้นความสัมพันธ์เเละเชื่อใจ'];
    
    // ดึงค่าเก่ามาใช้ (ถ้ามี)
    let oldChar = oldData ? oldData.chars : '';
    let oldCap = oldData ? oldData.cap : '';
    let oldHorse = oldData ? oldData.horse : '';
    let oldHead = oldData ? oldData.head : '';

    formHtml += createCheckboxGroup('i_char_checkbox', '1. ลักษณะการซื้อของลูกค้า', charOptions, oldChar);
    formHtml += createTextInput('i_cap', 'ทุนจดทะเบียน (Capital)', 'เช่น 5 ล้านบาท (เว้นว่างได้)', oldCap);
    formHtml += createTextInput('i_horse', 'จำนวนแรงม้า (Horsepower)', 'เช่น 1500 HP (เว้นว่างได้)', oldHorse);
    formHtml += createTextInput('i_head', 'จำนวนพนักงาน (Headcount)', 'เช่น 120 คน (เว้นว่างได้)', oldHead);

    formHtml += `<button type="submit" style="margin-top:10px; width:100%; background:#28a745; color:white; border:none; padding:10px; border-radius:4px; cursor:pointer; font-weight:bold;">
        💾 บันทึกการแก้ไข 🚀
    </button>
    </form></div>`;
        
    return formHtml;
}


// ฟังก์ชันส่งฟอร์ม Insight กลับไปหา AI
function submitInsightFormAsChat(event, formType) {
    event.preventDefault(); 
    const form = event.target;
    
    // 1. ดึงค่าช่อง Text 3 ช่อง
    const capInput = form.querySelector('input[name="i_cap"]');
    const horseInput = form.querySelector('input[name="i_horse"]');
    const headInput = form.querySelector('input[name="i_head"]');

    const cap = (capInput && capInput.value.trim() !== '') ? capInput.value.trim() : 'ไม่ได้ระบุ';
    const horse = (horseInput && horseInput.value.trim() !== '') ? horseInput.value.trim() : 'ไม่ได้ระบุ';
    const head = (headInput && headInput.value.trim() !== '') ? headInput.value.trim() : 'ไม่ได้ระบุ';
    
    // 2. ดึงค่า Checkbox ลักษณะการซื้อ (ดึงทุกกรณี ไม่ว่าจะเป็น FULL หรือ EDIT)
    const checkedBoxes = form.querySelectorAll('input[name="i_char_checkbox"]:checked');
    const charValues = Array.from(checkedBoxes).map(cb => cb.value);
    
    if (charValues.length === 0) {
        alert('กรุณาเลือกลักษณะการซื้อของลูกค้าอย่างน้อย 1 ข้อครับ');
        return; 
    }

    const charString = charValues.join(', ');

    // 3. ส่ง 4 ค่ารวดเดียวจบ ให้ AI เอาไปเซฟทับหรืออัปเดตใหม่ได้เลย
    const compiledMessage = `นี่คือข้อมูล Insight ของลูกค้าครับ:
1. ลักษณะลูกค้า: ${charString}
2. ทุนจดทะเบียน: ${cap}
3. แรงม้าเครื่องจักร: ${horse}
4. จำนวนพนักงาน: ${head}
รบกวนบันทึกและวิเคราะห์ต่อเลยครับ`;

    // ซ่อนฟอร์มและโชว์ว่าสำเร็จ
    form.style.display = 'none';
    const completeMsg = document.createElement('div');
    completeMsg.innerHTML = '<span style="color:#28a745; font-weight:bold;">✅ ส่งข้อมูลเรียบร้อยแล้ว</span>';
    form.parentNode.appendChild(completeMsg);

    // ยิงข้อความเข้าแชท AI
    messageInput.value = compiledMessage;
    sendMessage();
}



    // รับพารามิเตอร์ 8 ตัว (ตั้ง Default เป็นค่าว่าง)
    function generateChatForm(oldIntro='', oldService='', oldCert='', oldIncumbent='', oldHighStd='', oldGoals='', oldRole='', oldSchedule='') {
        let formHtml = `<div style="background:#f1f8ff; padding:15px; border-radius:8px; margin-top:10px; border:1px solid #cce5ff;">
            <h4 style="margin-top:0; color:#004085;">📋 กรุณาระบุ/ตรวจสอบ ข้อมูลสถานะการขาย</h4>
            <form onsubmit="submitFormAsChat(event)">`;
            
        const createSelect = (id, label, options, oldVal = '') => {
            let selectHtml = `<div style="margin-bottom: 8px;">
                <label style="display:block; font-size:12px; font-weight:bold; color:#333;">${label}:</label>
                <select id="${id}" required style="width:100%; padding:6px; border-radius:4px; border:1px solid #ccc;">
                    <option value="">-- กรุณาเลือก --</option>`;
            options.forEach(opt => {
                let isSelected = (opt === oldVal) ? 'selected' : '';
                selectHtml += `<option value="${opt}" ${isSelected}>${opt}</option>`;
            });
            selectHtml += `</select></div>`;
            return selectHtml;
        };

        // โยนค่าเก่า (ถ้ามี) ลงไปในแต่ละช่องเลย
        formHtml += createSelect('f_intro', '1. การนำเสนอ (INTRO)', formOptions.intro, oldIntro);
        formHtml += createSelect('f_services', '2. บริการ (SERVICES)', formOptions.services, oldService);
        formHtml += createSelect('f_cert', '3. สถานะระบบ (CERT STATUS)', formOptions.certStatus, oldCert);
        formHtml += createSelect('f_incumbent', '4. มีคู่แข่งไหม (INCUMBENT)', formOptions.incumbent, oldIncumbent);
        formHtml += createSelect('f_highstd', '5. มาตรฐานระดับสูง (HIGH STD)', formOptions.highStd, oldHighStd);
        formHtml += createSelect('f_goals', '6. เป้าหมายลูกค้า (GOALS)', formOptions.goals, oldGoals);
        formHtml += createSelect('f_role', '7. สถานะคนคุย (CONTACT ROLE)', formOptions.contactRole, oldRole);
        formHtml += createSelect('f_schedule', '8. กำหนดการใช้บริการ (SCHEDULE)', formOptions.serviceSchedule, oldSchedule);

        formHtml += `<button type="submit" style="margin-top:10px; width:100%; background:#28a745; color:white; border:none; padding:10px; border-radius:4px; cursor:pointer; font-weight:bold;">
            💾 บันทึกและวิเคราะห์ต่อ 🚀
        </button>
        </form></div>`;
            
        return formHtml;
}

// 4. [หัวใจสำคัญ] เมื่อกด Submit ฟอร์ม จะแปลงเป็นข้อความและโยนเข้าแชท!
function submitFormAsChat(event) {
    event.preventDefault(); // กันหน้าเว็บโหลดใหม่
    
    // ดึงค่าจาก Form
    const intro = document.getElementById('f_intro').value;
    const services = document.getElementById('f_services').value;
    const cert = document.getElementById('f_cert').value;
    const incumbent = document.getElementById('f_incumbent').value;
    const highstd = document.getElementById('f_highstd').value;
    const goals = document.getElementById('f_goals').value;
    const role = document.getElementById('f_role').value;
    const schedule = document.getElementById('f_schedule').value;

    // แปลงเป็นข้อความแชทส่งให้ AI
    const compiledMessage = `นี่คือข้อมูลสถานะการขายครับ:
1. INTRO: ${intro}
2. SERVICES: ${services}
3. CERT STATUS: ${cert}
4. INCUMBENT: ${incumbent}
5. HIGH STD: ${highstd}
6. GOALS: ${goals}
7. CONTACT ROLE: ${role}
8. SERVICE SCHEDULE: ${schedule}
รบกวนบันทึกข้อมูลและวิเคราะห์โอกาสการขายต่อให้ด้วยครับ`;

    // ปิดปุ่มและฟอร์ม เพื่อไม่ให้กดซ้ำ
    event.target.style.display = 'none';
    const completeMsg = document.createElement('div');
    completeMsg.innerHTML = '<span style="color:green; font-weight:bold;">✅ ส่งข้อมูลเรียบร้อยแล้ว</span>';
    event.target.parentNode.appendChild(completeMsg);

    // ยัดข้อความลงกล่องแชท แล้วเรียกฟังก์ชัน sendMessage() เดิมของคุณเพื่อยิงไปหา AI
    messageInput.value = compiledMessage;
    sendMessage();
}

// ฟังก์ชันส่งข้อความไปหา Backend
async function sendMessage() {
	const loadingIndicator = document.getElementById('loadingIndicator');
	var messageInput = document.getElementById('messageInput');
    var message = messageInput.value.trim();
    if (!message) return;

    // แสดงข้อความฝั่ง User
    appendMessage(message, 'user');
    messageInput.value = '';
    
    // เปิดสถานะ Loading
    loadingIndicator.style.display = 'block';
    sendBtn.disabled = true;

    try {
        const response = await fetch('/api/chat?'+_csrf, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            // 2. [สำคัญ] ส่ง sessionId แนบไปกับ payload ด้วย
            body: JSON.stringify({ 
                message: message,
                sessionId: currentSessionId,
                edataId: currentEdataId // 👈 ส่ง edataId แนบไปด้วย
            })
        });

        if (response.ok) {
            const aiText = await response.text();
            appendMessage(aiText, 'ai');
        } else {
            appendMessage('เกิดข้อผิดพลาดในการเชื่อมต่อเซิร์ฟเวอร์', 'ai');
        }
    } catch (error) {
        appendMessage('ไม่สามารถติดต่อระบบหลังบ้านได้', 'ai');
        console.error(error);
    } finally {
        // ปิดสถานะ Loading
        loadingIndicator.style.display = 'none';
        sendBtn.disabled = false;
        messageInput.focus();
    }
}



// ฟังก์ชันสำหรับโหลดประวัติเก่ามาโชว์
async function loadChatHistory(sessionId) {
	

	
	
    try {
        const response = await fetch(`/api/chat/history/${sessionId}`);
        const history = await response.json();
        
        chatHistory.innerHTML = ''; // ล้างหน้าจอแชทก่อน

        history.forEach(msg => {
            // โยนเข้าฟังก์ชัน appendMessage เพื่อให้หน้าตากล่องแชทเหมือนกันเป๊ะ
            // msg.role จาก Backend จะเป็น "USER" หรือ "ASSISTANT"
            const senderType = msg.role === 'USER' ? 'user' : 'ai';
            appendMessage(msg.text, senderType);
        });
    } catch (error) {
        console.error("ไม่สามารถโหลดประวัติการสนทนาได้", error);
    }


    
 
}


 