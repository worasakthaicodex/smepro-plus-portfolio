<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
/* คอนเทนเนอร์หลักที่มุมขวาล่าง */
#toast-container {
    position: fixed;
    bottom: 20px;
    right: 20px;
    z-index: 9999;
}

/* ดีไซน์กล่องแจ้งเตือนแต่ละอัน */
.toast-msg {
    background-color: #333; /* สีพื้นหลังปกติ */
    color: #fff;
    padding: 15px 20px;
    margin-top: 10px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0,0,0,0.15);
    display: flex;
    align-items: center;
    min-width: 250px;
    opacity: 0;
    transform: translateX(100%); /* เริ่มต้นซ่อนทางขวา */
    animation: slideInRight 0.5s forwards; /* เอฟเฟกต์เลื่อนเข้า */
}

/* สีสำหรับประเภทต่างๆ */
.toast-msg.alert {
    background-color: #dc3545; /* สีแดง สำหรับ Alarm */
    border-left: 5px solid #ff6b6b;
}

.toast-msg.info {
    background-color: #0d6efd; /* สีฟ้า สำหรับแจ้งเตือนทั่วไป */
    border-left: 5px solid #4dabf7;
}

/* Animation เลื่อนเข้ามา */
@keyframes slideInRight {
    to {
        opacity: 1;
        transform: translateX(0);
    }
}

/* Animation เลื่อนออก (ตอนหายไป) */
@keyframes fadeOut {
    to {
        opacity: 0;
        transform: translateY(20px);
    }
}
</style>  

<div id="toast-container"></div>



<script>

//1. สร้างตัวแปรเสียงเป็น Global (ใช้อันเดิมซ้ำๆ อย่า new ใหม่ทุกครั้ง)
var notificationAudio = new Audio();

// 2. ตั้งค่าไฟล์เสียง (ทำทีเดียวตอนโหลด)
var canPlayType = notificationAudio.canPlayType("audio/ogg");
if(canPlayType.match(/maybe|probably/i)) {
    notificationAudio.src = '/readdocument/mp3/digitalstore07-new-message-alert-430414.mp3';
} else {
    notificationAudio.src = '/readdocument/mp3/digitalstore07-new-message-alert-430414.mp3';
}

// 3. ฟังก์ชัน "ปลดล็อกเสียง" (Silent Unlock)
// เมื่อผู้ใช้คลิกที่ไหนก็ได้ในหน้าเว็บ 1 ครั้ง เราจะสั่งเล่นเสียงแล้วหยุดทันที
function unlockAudio() {
    notificationAudio.play().then(() => {
        notificationAudio.pause();
        notificationAudio.currentTime = 0;
        console.log("Audio Engine Unlocked! 🔊");
        
        // เมื่อปลดล็อกเสร็จ ก็ลบ event click ทิ้งไป จะได้ไม่ทำงานซ้ำ
        document.removeEventListener('click', unlockAudio);
        document.removeEventListener('touchstart', unlockAudio);
        document.removeEventListener('keydown', unlockAudio);
    }).catch((error) => {
        console.log("Audio unlock failed (User click required again)");
    });
}

// 4. ผูก Event รอให้ผู้ใช้คลิกเพื่อปลดล็อก
document.addEventListener('click', unlockAudio);
document.addEventListener('touchstart', unlockAudio); // สำหรับมือถือ
document.addEventListener('keydown', unlockAudio);    // สำหรับคีย์บอร์ด

// 5. แก้ไขฟังก์ชันเล่นเสียง ให้ใช้ตัวแปร Global
function playNotificationSound() {
    // รีเซ็ตเวลาให้เริ่มเล่นใหม่ (กรณีเสียงเก่ายังไม่จบ)
    notificationAudio.currentTime = 0;
    
    // สั่งเล่น
    var playPromise = notificationAudio.play();

    if (playPromise !== undefined) {
        playPromise.then(_ => {
            // เล่นสำเร็จ
            console.log("Playing sound...");
        })
        .catch(error => {
            // ยังเล่นไม่ได้ (ผู้ใช้ยังไม่เคยคลิกหน้าเว็บเลย)
            console.warn("Audio play blocked. User interaction required.");
            
            // (Optional) ทางเลือก: แสดงปุ่มเล็กๆ ให้ผู้ใช้กดเพื่อเปิดเสียง
            showUnmuteButton(); 
        });
    }
}

// (Optional) ฟังก์ชันแสดงปุ่มขอเปิดเสียง ถ้าเล่นไม่ได้จริงๆ
function showUnmuteButton() {
    // เช็คว่ามีปุ่มหรือยัง ถ้ามีแล้วไม่ต้องสร้างใหม่
    if(document.getElementById('unmute-btn')) return;

    var btn = document.createElement("button");
    btn.id = "unmute-btn";
    btn.innerHTML = "🔊 คลิกเพื่อเปิดเสียงแจ้งเตือน";
    btn.style.cssText = "position:fixed; bottom:80px; right:20px; z-index:10000; padding:10px; background:red; color:white; border:none; border-radius:5px; cursor:pointer;";
    
    btn.onclick = function() {
        notificationAudio.play();
        btn.remove(); // ลบปุ่มทิ้งเมื่อกดแล้ว
    };
    
    document.body.appendChild(btn);
}
// ฟังก์ชันแสดงการแจ้งเตือน
function showToast(message, type = 'info') {
    // 1. เล่นเสียงก่อนเลย
    playNotificationSound();

    // 2. สร้าง Element HTML
    var container = document.getElementById('toast-container');
    var toast = document.createElement('div');
    
    // ใส่ class ตามประเภท (alert หรือ info)
    toast.className = 'toast-msg ' + type; 
    
    // ใส่ข้อความ (ใส่ icon เพิ่มได้ถ้าต้องการ)
    toast.innerHTML = `
        <i class="fa fa-bell" style="margin-right:10px;"></i>
        <span>`+message+ `</span>`;

    // 3. เอาไปแปะในหน้าเว็บ
    container.appendChild(toast);

    // 4. ตั้งเวลาลบออกอัตโนมัติเมื่อผ่านไป 5 วินาที
    setTimeout(function() {
        toast.style.animation = 'fadeOut 0.5s forwards'; // เล่นท่าตอนออก
        toast.addEventListener('animationend', function() {
            toast.remove(); // ลบออกจาก DOM จริงๆ
        });
    }, 5000);
}
</script>


<script>
var socket;
var reconnectTimer;
var isIntentionalClose = false; // ตัวแปรเช็คว่าผู้ใช้ตั้งใจปิดเอง (เช่น ปิดหน้าเว็บ) หรือไม่

if (typeof (WebSocket) == "undefined") {
    console.log("ขออภัย: เบราว์เซอร์ของคุณไม่รองรับ WebSocket");
    alert("ขออภัย: เบราว์เซอร์ของคุณไม่รองรับ WebSocket");
} else {
    
    // สร้างฟังก์ชันครอบไว้ เพื่อให้เรียกซ้ำตอนต่อใหม่ได้
    function connectWebSocket() {
        // 1. เช็คว่าเป็น http หรือ https เพื่อเลือก ws หรือ wss
        var protocol = (window.location.protocol === 'https:') ? 'wss://' : 'ws://';
        var host = window.location.host;
        var wsUrl = protocol + host + "/Socket_alert/${Member_id}";

        console.log("Connecting to: " + wsUrl);

        // 2. สร้าง WebSocket
        socket = new WebSocket(wsUrl);

        // --- เมื่อเชื่อมต่อสำเร็จ ---
        socket.onopen = function() {
            let timestamp2 = new Date();    
            var time = timestamp2.getTime();
            console.log("🟢 Socket เปิดการเชื่อมต่อสำเร็จ");
            
            // ยกเลิกการนับถอยหลังต่อใหม่ (ถ้ามีค้างอยู่)
            if (reconnectTimer) {
                clearTimeout(reconnectTimer);
                reconnectTimer = null;
            }
        };

        // --- เมื่อได้รับข้อความ ---
        socket.onmessage = function(msg) {
            var data = JSON.parse(msg.data);
            
            if (data.type === "ACTION_REQUIRED") {
                var badgeElement = document.getElementById("badge-" + data.process);
                if (badgeElement) {
                    var currentCount = parseInt(badgeElement.innerText);
                    if (isNaN(currentCount)) { currentCount = 0; }
                    badgeElement.innerText = "!";
                    badgeElement.style.display = 'inline-block'; 
                    updateReferenceBadges(); 
                } else {
                     console.log("หา Badge ไม่เจอ! ID: " + data.process);
                }
                showToast("มีการแจ้งเตือนใหม่: " + data.message, "alert"); 
            }
            else if (data.type === "ACTION_REQUIRED_ALL") { 
                var Policies = "${Policies}"; 
                var myPolicies = Policies.split(',').map(function(item) { return item.trim(); });

                var rawAllowed = data.access; 
                var allowedList = [];

                if (typeof rawAllowed === 'string') {
                    allowedList = rawAllowed.split(',').map(function(item) { return item.trim(); });
                } else if (Array.isArray(rawAllowed)) {
                    allowedList = rawAllowed;
                }

                var isAllowed = false;
                if (allowedList && allowedList.length > 0) {
                    isAllowed = myPolicies.some(function(myPolicy) {
                        return allowedList.includes(myPolicy);
                    });
                }

                if (!isAllowed) {
                    console.log("คุณไม่มีสิทธิ์เห็นแจ้งเตือนนี้");
                    return; 
                }
                
                var badgeElement = document.getElementById("badge-" + data.process);
                if (badgeElement) {
                    var currentCount = parseInt(badgeElement.innerText);
                    if (isNaN(currentCount)) { currentCount = 0; }
                    badgeElement.innerText = "!";
                    badgeElement.style.display = 'inline-block'; 
                    updateReferenceBadges(); 
                }
                showToast("มีการแจ้งเตือนใหม่: " + data.message, "alert"); 
            }
        };

        // --- เมื่อการเชื่อมต่อหลุด หรือถูกปิด ---
        socket.onclose = function(event) {
            console.log("🔴 Socket หยุดการทำงาน (หลุดการเชื่อมต่อ)");
            
            // ถ้าไม่ได้ตั้งใจปิด (เช่น เน็ตหลุด หรือ Server รีสตาร์ท) ให้ต่อใหม่
            if (!isIntentionalClose) {
                console.log("⏳ กำลังพยายามเชื่อมต่อใหม่ในอีก 3 วินาที...");
                reconnectTimer = setTimeout(function() {
                    connectWebSocket();
                }, 3000); // หน่วงเวลา 3 วินาที (3000 ms) ก่อนต่อใหม่
            }
        };

        // --- เมื่อเกิดข้อผิดพลาด ---
        socket.onerror = function(error) {
            console.error("⚠️ Socket เกิดข้อผิดพลาดขึ้น", error);
            // เมื่อเกิด error ส่วนใหญ่ socket จะเข้าสู่สถานะ close ทันที
            // ปล่อยให้ onclose ทำหน้าที่เชื่อมต่อใหม่ (ไม่ต้องเรียก connectWebSocket() ตรงนี้ซ้ำ)
            socket.close(); 
        };
    }

    // 3. เรียกใช้งานครั้งแรกเมื่อโหลดหน้าเว็บ
    connectWebSocket();

    // 4. เมื่อปิดหน้าต่าง/รีเฟรชหน้าเว็บ ให้ปิด Socket อย่างสมบูรณ์และไม่ต้องต่อใหม่
    window.addEventListener("beforeunload", function() {
        isIntentionalClose = true; // ตั้งค่าว่าตั้งใจปิด เพื่อไม่ให้ Trigger การ Reconnect
        if (socket) {
            socket.close();
        }
    });
}


	</script>