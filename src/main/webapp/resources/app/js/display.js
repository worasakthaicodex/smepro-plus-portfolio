// 1. ฟังก์ชันเปลี่ยนโหมดส่วนกลาง
if (typeof window.toggleDarkMode !== 'function') {
    window.toggleDarkMode = function(themeClass, processId) {
        if (!processId) return;

        // ไปหยิบค่า config ที่เรา setup ไว้มาใช้งาน
        const config = window.erpThemeManagers && window.erpThemeManagers[processId];
        if (!config) {
            console.error("ยังไม่ได้ setupDarkModeSystem สำหรับ processId: " + processId);
            return;
        }

        const storageKey = 'ERP_THEME_' + processId;

        // 1. หากล่องแม่ (observerTargetId) ให้เจอก่อน เพื่อจำกัดขอบเขต
        const parentWindow = document.getElementById(config.observerTargetId);
        if (!parentWindow) return;

        // 2. หา containerId ที่อยู่ "ภายใต้" กล่องแม่เท่านั้น (ป้องกันปัญหา ID ซ้ำกันในหน้าต่างอื่น)
        let targetContainer = parentWindow.querySelector('[id="' + config.containerId + '"]') || document.getElementById(config.containerId);
        
        if (!targetContainer) return;

        // 3. ใส่สีที่ containerId
        targetContainer.classList.remove('dark-mode', 'vscode-blue', 'brown-theme');

        if (themeClass && themeClass !== 'light') {
            targetContainer.classList.add(themeClass);
            localStorage.setItem(storageKey, themeClass);
            
            if(typeof window.updateThemeUI === 'function') window.updateThemeUI(true, themeClass, processId);
        } else {
            localStorage.setItem(storageKey, 'light');
            
            if(typeof window.updateThemeUI === 'function') window.updateThemeUI(false, 'light', processId);
        }
    };
}

// 2. ระบบ Setup
if (typeof window.setupDarkModeSystem !== 'function') {
    // สร้าง Object กลางไว้จำค่า containerId และ observerTargetId ของแต่ละหน้าต่าง
    window.erpThemeManagers = window.erpThemeManagers || {};

    window.setupDarkModeSystem = function(options) {
        const processId = options.processId || '';
        if (!processId) return;

        const containerId = options.containerId || 'wpf-app-frame';
        const observerTargetId = options.observerTargetId || ('microsofts' + processId);
        const storageKey = 'ERP_THEME_' + processId;

        // เก็บข้อมูลหน้าต่างนี้ไว้ให้ toggleDarkMode เรียกใช้
        window.erpThemeManagers[processId] = {
            containerId: containerId,
            observerTargetId: observerTargetId,
            observer: null
        };

        // ฟังก์ชันเช็คตอนเริ่มโหลดหน้า
        const initTheme = function() {
            const savedTheme = localStorage.getItem(storageKey);
            if (savedTheme && savedTheme !== 'light') {
                window.toggleDarkMode(savedTheme, processId);
            } else {
                window.toggleDarkMode('light', processId);
            }
        };

        setTimeout(initTheme, 50);

        // จัดการ Observer (ยามเฝ้า AJAX)
        if (window.erpThemeManagers[processId].observer) {
            window.erpThemeManagers[processId].observer.disconnect();
        }

        // เฝ้าดูที่ observerTargetId ตามที่คุณต้องการ
        const parentNode = document.getElementById(observerTargetId);
        if (parentNode) {
            const config = { childList: true, subtree: true };

            const callback = function(mutationsList) {
                for(let mutation of mutationsList) {
                    if (mutation.type === 'childList' && mutation.addedNodes.length > 0) {
                        const savedTheme = localStorage.getItem(storageKey);
                        
                        // เมื่อมี AJAX ให้มุดเข้าไปหา containerId ในกล่องแม่นี้ เพื่อเติมสี
                        let targetContainer = parentNode.querySelector('[id="' + containerId + '"]') || document.getElementById(containerId);

                        if (targetContainer && savedTheme && savedTheme !== 'light' && !targetContainer.classList.contains(savedTheme)) {
                            targetContainer.classList.add(savedTheme);
                        }
                        break;
                    }
                }
            };

            const observer = new MutationObserver(callback);
            observer.observe(parentNode, config);
            
            window.erpThemeManagers[processId].observer = observer;
        }
    };
}


function toggle(id) {
   var element = document.getElementById(id);
   if (element.style.display === "none") {
       element.style.display = "block";
   } else {
       element.style.display = "none";
   }
}

function show(id) {
   var element = document.getElementById(id);

       element.style.display = "block";
   
}

function hide(id) {
   var element = document.getElementById(id);
 
       element.style.display = "none";
  
}



function opentab(tab){

    var div = document.getElementById(tab);
      if (div.style.display !== 'none') {
          div.style.display = 'none';
      }
      else {
          div.style.display = 'block';
      }
  
  }
  
     /** 
     * Function to switch tabs in a UI component
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchtabActive(tab,total,id){
            for(var i = 1;i <= total;i++){
                  var element = document.getElementById("tab"+tab+"-"+i);
                  if(element){  element.classList.remove("active"); }
            }
             var element = document.getElementById("tab"+tab+"-"+id);
            if(element){  element.classList.add("active"); }
     
    }
  
  
     /** 
     * Function to switch tabs in a UI component
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchtabActiveAndData(tab,total,id){
            for(var i = 1;i <= total;i++){
                  var element = document.getElementById("tab"+tab+"-"+i);
                  if(element){  element.classList.remove("active"); }
                  
                  
                     var element = document.getElementById("show"+tab+"-"+i);
                  if(element){  element.style.display = "none"; }
            }
             var element = document.getElementById("tab"+tab+"-"+id);
            if(element){  element.classList.add("active"); }
             var element = document.getElementById("show"+tab+"-"+id);
            if(element){    element.style.display = "block";}
     
    }
  
  
  
  
    /** 
     * Function to switch tabs in a UI component
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchtab(tab,total,id){
            for(var i = 1;i <= total;i++){
                  var element = document.getElementById("tab"+tab+"-"+i);
                  if(element){  element.classList.remove("tab-active"); }
  
                  var element = document.getElementById("ui"+tab+"-"+i);
                  if(element){  element.style.display = "none"; }
            }
  
            var element = document.getElementById("tab"+tab+"-"+id);
            if(element){  element.classList.add("tab-active"); }
            var element = document.getElementById("ui"+tab+"-"+id);
            if(element){    element.style.display = "block";}
    }
  
  
    /** 
     * Function to switch tabs in a UI component
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchTab(tab,total,id){
            for(var i = 1;i <= total;i++){
                  var element = document.getElementById("Tab-"+tab+"-"+i);
                  if(element){  element.classList.remove("active"); }
  
                  var element = document.getElementById("Form-"+tab+"-"+i);
                  if(element){  element.style.display = "none"; }
            }
  
            var element = document.getElementById("Tab-"+tab+"-"+id);
            if(element){  element.classList.add("active"); }
            var element = document.getElementById("Form-"+tab+"-"+id);
            if(element){    element.style.display = "block";}
    }
  
  
  
      /** 
     * Function to switch tabs in a UI component
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchTabflex(tab,total,id){
            for(var i = 1;i <= total;i++){
                  var element = document.getElementById("Tab-"+tab+"-"+i);
                  if(element){  element.classList.remove("active"); }
  
                  var element = document.getElementById("Form-"+tab+"-"+i);
                  if(element){  element.style.display = "none"; }
            }
  
            var element = document.getElementById("Tab-"+tab+"-"+id);
            if(element){  element.classList.add("active"); }
            var element = document.getElementById("Form-"+tab+"-"+id);
            if(element){    element.style.display = "flex";}
    }
  
  
  
  
    /** 
     * Function to switch tabs in a UI component sub menu
     * @param {string}tab
     * @param {number} total 
     * @param {number} id 
     * @returns  null
     */
    function switchdata(tab,total,id){
  
  
  
  
          for(var i = 1;i <= total;i++){
  
            var element = document.getElementById("group"+tab+"-"+i);
            if(element){  element.classList.remove("tab-active2"); }
            
            var element = document.getElementById("main"+tab+"-"+i);
                  if(element){  element.style.display = "none"; }
  
           var element = document.getElementById("bt"+tab+"-"+i);
           if(element){  element.style.display = "none"; }
          }
  
          var element = document.getElementById("group"+tab+"-"+id);
          if(element){  element.classList.add("tab-active2"); }
  
          var element = document.getElementById("main"+tab+"-"+id);
          if(element){    element.style.display = "block";}
  
          var element = document.getElementById("bt"+tab+"-"+id);
          if(element){    element.style.display = "block";}
  
  }
  
  
  
			        /** 
			        * Function to switch tabs in a UI component setting
			        * @param {string}tab
			        * @param {number} total 
			        * @param {number} id 
			        * @returns  null
			        */
			        function switchtabsetting(tab) {
			
			    // 1. เอา active ออกจากทุก tab (setting-1, setting-2, setting-3...)
			    document.querySelectorAll('.setting-tab').forEach(el => {
			        el.classList.remove('active');
			    });
			
			    // 2. ใส่ active ให้ tab ที่เลือก
			    document.getElementById("setting-" + tab).classList.add("active");
			
			    // 3. ซ่อนทุก window content
			    document.querySelectorAll('.window-content').forEach(el => {
			        el.classList.add('hidden');
			    });
			
			      var element = document.getElementById("window-content-" + tab);
			     if(element){  element.classList.remove('hidden'); }
			}
			  
			        /** 
			        * Function to switch tabs in a UI component setting
			        * @param {string}tab
			        * @param {number} total 
			        * @param {number} id 
			        * @returns  null
			        */
			        function switchtab(tab) {
			
			    // 1. เอา active ออกจากทุก tab (setting-1, setting-2, setting-3...)
			    document.querySelectorAll('.tab-tab').forEach(el => {
			        el.classList.remove('active');
			    });
			
			    // 2. ใส่ active ให้ tab ที่เลือก
			    document.getElementById("tab-" + tab).classList.add("active");
			
			    // 3. ซ่อนทุก window content
			    document.querySelectorAll('.tab-content').forEach(el => {
			        el.classList.add('hidden');
			    });
			    var element = document.getElementById("tab-content-" + tab);
			    if(element){  element.classList.remove('hidden'); }
			   
			}
			  
  
          /** 
        * Function to switch bt 
        * @param {string}tab
        * @param {number} tabs 
        * @returns  null
        */
          function switchbt(tab,tabs){
        
            var element = document.getElementById("bt_edit"+tab);
            if(element){ element.style.display = "none";}
            var element = document.getElementById("bt_del"+tab);
            if(element){ element.style.display = "none";}

            var element = document.getElementById("bt"+tab+"1");
            if(element){ element.style.display = "none";}
            var element = document.getElementById("bt"+tab+"2");
            if(element){ element.style.display = "none";}
            var element = document.getElementById("bt"+tab+"3");
            if(element){ element.style.display = "none";}
            var element = document.getElementById("bt"+tab+"4");
            if(element){ element.style.display = "none";}
            var element = document.getElementById("bt"+tab+"5");
            if(element){ element.style.display = "none";}


           if(tabs != null){  
                    for(var i = 0;i <= tabs.length;i++){
                        var element = document.getElementById(tabs[i]);
                        if(element){ element.style.display = "block";}
                    }
            }
        
        }
  
      // ==========================================
        // 1. CONFIGURATION (ตั้งค่าส่วนนี้ให้ตรงกับระบบจริง)
        // ==========================================
        const userProfile = {
            name: "เจ้านาย",
            birthMonth: 10, // เดือนเกิด (1-12)
            birthDay: 25    // วันเกิด
        };

        const specialEvents = {
            "01-01": "สวัสดีปีใหม่ครับ! 🎉",
            "04-13": "สุขสันต์วันสงกรานต์ 💦",
            "12-25": "Merry Christmas! 🎄"
        };

        const newsFeed = [
            "เจ้านายเดินมาเร็วมาก เเต่คุณย่อหน้าจอเร็วกว่า",
             "หาวอีกเเล้วนะครับ ตาลอยเชียว",
              "82 มาเเน่งวดนี้ครับ",
               "เเม้ไม่มีใครรักเรา เเต่เราก็ลักโขมยได้นะ 😆",
        ];

        // ==========================================
        // 2. SYSTEM LOGIC
        // ==========================================
        const bubble = document.getElementById('bubble');
        const avatar = document.getElementById('avatar');
        const menu = document.getElementById('menued');
        
        let isMenuOpen = false;
        let idleTime = 0;
        let bubbleTimeout;

     // 🛑 เพิ่มตัวแปรนี้เข้ามา เพื่อบอกว่า AI กำลังคุยกับเจ้านายอยู่ ห้ามใครแทรก!
        let isAiTalking = false;

        // --- Core: พูด ---
     function speak(text, face = "😆", isAlert = false, duration = 12000, force = false) {
      		 if(isMenuOpen) return; // ถ้าเมนูเปิดอยู่ ไม่ต้องพูดแทรก
            
            // 🛑 ถ้า AI กำลังตอบคำถามเจ้านายอยู่ และคำสั่งนี้ไม่ได้ถูกบังคับ (force) ให้ยกเลิกการพูดไปเลย
            if(isAiTalking && !force) {
                console.log("🤫 งดพูดแทรก: AI กำลังคุยกับเจ้านายอยู่");
                return; 
            }


            // Reset
            clearTimeout(bubbleTimeout);
            bubble.classList.remove('show', 'alert');
            
            setTimeout(() => {
                avatar.innerText = face;
                bubble.innerHTML = text;
                if(isAlert) bubble.classList.add('alert');
                
                bubble.classList.add('show');

                // ซ่อนอัตโนมัติเมื่อครบเวลา (Default 6 วินาที)
                bubbleTimeout = setTimeout(() => {
                    bubble.classList.remove('show');
                }, duration);
            }, 300);
        }

        // --- Core: เมนู ---
        function toggleMenu() {
            isMenuOpen = !isMenuOpen;
            if(isMenuOpen) {
                menu.classList.add('open');
                avatar.innerText = "🤔";
                bubble.innerText = "ต้องการทำรายการไหนครับ?";
                bubble.classList.add('show');
                clearTimeout(bubbleTimeout); // ไม่ต้องซ่อนถ้าเมนูเปิดอยู่
            } else {
                menu.classList.remove('open');
                bubble.classList.remove('show');
                // กลับหน้าปกติหลังจากปิดเมนูแป๊บนึง
                setTimeout(() => { avatar.innerText = "😉"; }, 500);
            }
        }

        function handleAction(action) {
            toggleMenu(); // ปิดเมนูก่อน
            setTimeout(() => {
                if(action === 'check') fetchRealData(true); // บังคับเช็ค
                else if(action === 'history') alert("กำลังเปิดหน้าประวัติ..."); 
                else if(action === 'setting') alert("กำลังเปิดหน้าตั้งค่า...");
            }, 500);
        }

        // ==========================================
        // 3. EVENTS & TIMERS
        // ==========================================

        // A. เปิดหน้าจอครั้งแรก (Random Greeting)
        function initGreeting() {
            const greetings = [
                `สวัสดีครับ วันนี้พร้อมลุยงานไหม?`,
                `ระบบ ERP ออนไลน์พร้อมใช้งานแล้วครับ`,
                `วันนี้อากาศดีนะครับ เริ่มงานกันเลย!`
                
            ];
            const randomMsg = greetings[Math.floor(Math.random() * greetings.length)];
            speak(randomMsg, "😄");
        }

		        // B. ดึงข้อมูลจริง (API Simulation)
		   function fetchRealData(manualCheck = false) {
		    // 1. กวาดหา Elements ที่เป็นป้ายแจ้งเตือนทั้งหมด (class="app-badge")
		    // สมมติว่าใน JSP คุณใช้ class="app-badge" ตามโค้ดก่อนหน้านี้
		    // และซ่อนไว้ด้วย style="display:none" ถ้าค่า < 1
		    
		    console.log(" fetchRealData ");
		    
		    var allBadges = document.querySelectorAll('.app-badge');
		    var totalPending = 0;
		    var appNames = []; // เก็บชื่อแอพที่มีงานค้างไว้ด้วย เผื่ออยากให้พูดชื่อ
		
		    // 2. วนลูปบวกเลข
		    allBadges.forEach(function(badge) {
		        // เช็คว่า badge นี้แสดงอยู่ไหม (ไม่ display: none) และมีค่าข้างในไหม
		        if (badge.offsetParent !== null && badge.innerText.trim() !== "") {
		            var num = parseInt(badge.innerText.trim());
		            if (!isNaN(num) && num > 0) {
		                totalPending += num;
		                
		                // (Optional) พยายามหาชื่อแอพจาก Element ใกล้เคียง
		                // โครงสร้าง HTML เดิม: <div class='apps'> <span class='badge'></span> <p>Name</p> </div>
		                var parentApp = badge.closest('.apps');
		                if (parentApp) {
		                    var nameTag = parentApp.querySelector('p');
		                    if(nameTag) appNames.push(nameTag.innerText);
		                }
		            }
		        }
		    });
		
		    // 3. เริ่มบ่น (Logic เตือน)
		    if (totalPending > 0) {
		        var msg = `มีงานค้างรวมทั้งหมด ${totalPending} รายการนะ! `;
		        
		        // ถ้าอยากให้ระบุชื่อแอพด้วย (ถ้าไม่เยอะเกินไป)
		        if (appNames.length > 0 && appNames.length <= 3) {
		            msg += `อยู่ที่ ${appNames.join(', ')} น่ะ `;
		        }
		        
		        msg += "รีบเคลียร์หน่อยสิ อย่าดองงาน!";
		
		        // เงื่อนไขการพูด: ถ้าเช็คเอง(manual) พูดเสมอ, ถ้า Auto ให้สุ่มพูด 30% กันรำคาญ
		        if (manualCheck || Math.random() < 0.3) {
		            speak(msg, "😤", true);
		        }
		    } else {
		        // ไม่มีงานค้างเลย
		        if (manualCheck) {
		            speak("ตอนนี้ไม่มีงานค้างเลยครับ เยี่ยมยอด! ว่างแล้วไปพักผ่อนบ้างนะ", "😎");
		        }
		    }
		}
        // C. ตรวจจับเวลา (Heartbeat - ทุก 1 นาที)
        function startHeartbeat() {
            setInterval(() => {
                const now = new Date();
                const hours = now.getHours();
                const mins = now.getMinutes();
                const month = now.getMonth() + 1;
                const date = now.getDate();
                const dateKey = String(month).padStart(2,'0') + "-" + String(date).padStart(2,'0');

                // 1. เที่ยงตรง
                if (hours === 12 ) {
                    speak("เที่ยงแล้ว! 🕛 พักกินข้าวได้แล้วครับ กองทัพต้องเดินด้วยท้อง", "🤪");
                }
                
                // 2. เลิกงาน
                if (hours === 17 ) {
                    speak("17:00 แล้ว! 🕔 ได้เวลากลับบ้าน พักผ่อนได้แล้วครับ", "😁");
                }

                // 3. วันเกิด (เช็คตอน 9 โมงเช้า)
                if (month === userProfile.birthMonth && date === userProfile.birthDay && hours === 9 && mins === 0) {
                    speak(`สุขสันต์วันเกิดครับคุณ${userProfile.name}! 🎂 ขอให้มีความสุขมากๆ นะครับ`, "🥳");
                }

                // 4. วันสำคัญ (เช็คตอน 8 โมงครึ่ง)
                if (specialEvents[dateKey] && hours === 8 && mins === 30) {
                    speak(specialEvents[dateKey], "🎉");
                }

                 fetchRealData();

            }, 60000); // 60,000 ms = 1 นาที
        }

    // // ==========================================
        // D. อ่านข่าวระบบ (ดึงข่าวจริงจาก ไทยรัฐ - 3 ข่าวรวด)
        // ==========================================
        async function fetchRealWorldNews() {
            try {
                const rssUrl = encodeURIComponent('https://www.thairath.co.th/rss/news');
                const apiUrl = `https://api.rss2json.com/v1/api.json?rss_url=${rssUrl}`;

                const response = await fetch(apiUrl);
                
                // เช็คก่อนว่า API ล่มไหม
                if (!response.ok) throw new Error("Network response was not ok");
                
                const data = await response.json();

                // ถ้าดึงข้อมูลสำเร็จและมีข่าวอย่างน้อย 3 ข่าว
                if (data.status === 'ok' && data.items.length >= 3) {
                    
                    // ดึงข่าว 3 ลำดับแรกสุด (เอาข่าวสดใหม่สุด ไม่ต้องสุ่มแล้ว)
                    const top3News = data.items.slice(0, 3);
                    const faces = ["😆", "🤔", "😆"]; // สลับหน้าอีโมจิหน่อยจะได้ไม่น่าเบื่อ

                    // วนลูปอ่านทีละข่าว 
                    for (let i = 0; i < top3News.length; i++) {
                        const newsTitle = top3News[i].title;
                        
                        // สั่งพูด (ตั้งเวลาให้บับเบิ้ลโชว์ 8 วินาทีต่อข่าว จะได้อ่านทัน)
                        speak(`ข่าวที่ ${i + 1}: ${newsTitle}`, faces[i], false, 8000);
                        
                        // ถ้ายังไม่ใช่ข่าวสุดท้าย ให้ระบบ "รอ" 8.5 วินาที ก่อนจะเริ่มพูดข่าวถัดไป 
                        // (8 วินาทีให้บับเบิ้ลโชว์ + 0.5 วินาทีเว้นจังหวะ)
                        if (i < top3News.length - 1) {
                            await new Promise(resolve => setTimeout(resolve, 8500));
                        }
                    }

                } else {
                    throw new Error("API ข่าวมีข้อมูลไม่พอ");
                }

            } catch (error) {
                // ถ้าเน็ตหลุด API ล่ม หรือติดบล็อก ให้กลับมาใช้มุกตลกในระบบ
                console.log("ดึงข่าวจริงไม่สำเร็จ ใช้ข่าวกากๆ แทน", error);
                const randomNews = newsFeed[Math.floor(Math.random() * newsFeed.length)];
                speak("แอบกระซิบ: " + randomNews, "😆");
            }
        }

     function startNewsFeed() {
            // สร้างฟังก์ชันย่อยสำหรับสุ่มอ่านข่าว จะได้เรียกใช้ซ้ำได้ง่ายๆ
            const playNews = () => {
               fetchRealWorldNews(); // เรียกฟังก์ชันดึงข่าวที่ทำไว้ก่อนหน้านี้
            };

            // 1. ดึงครั้งแรกเลย! (หน่วงเวลาไว้ 10 วินาที ให้พูด Greeting ทักทายตอนเปิดเว็บให้จบก่อน)
            setTimeout(() => {
                playNews();
            }, 10000); 

            // 2. ตั้งเวลาวนลูปหลังจากนั้น ทุกๆ 15 นาที (900000 ms)
            setInterval(() => {
                playNews();
            }, 900000); 
        }
        // E. ตรวจจับคนอู้งาน (Idle Detection)
        function startIdleTracker() {
            // Reset เวลาเมื่อมีการขยับเมาส์หรือกดปุ่ม
            ['mousemove', 'keypress', 'click'].forEach(evt => {
                document.addEventListener(evt, () => { idleTime = 0; });
            });

            setInterval(() => {
                idleTime++; // เพิ่มทุก 1 นาที
                // ถ้าไม่ได้ขยับเกิน 20 นาที
                
                if (idleTime === 5) {
                    speak("ยังอยู่ไหม ? ", "😢");
                }
                
                if (idleTime === 20) {
                    speak("จ้องจอนานไปแล้ว... แอบหลับในหรือเปล่าครับ? 😆", "🧐");
                }
                // ถ้าเกิน 60 นาที (1 ชม.)
                if (idleTime === 60) {
                    speak("หายไปไหนนานจัง อย่าลืม Log out นะครับถ้าไม่ใช้แล้ว", "😴");
                }
            }, 60000);
        }

        // --- เริ่มต้นระบบ ---
        window.onload = function() {
            initGreeting();      // ทักทายแรก
            startHeartbeat();    // เริ่มนาฬิกา
            startNewsFeed();     // เริ่มอ่านข่าว
            startIdleTracker();  // เริ่มจับคนอู้
        };

        // คลิกที่อื่นเพื่อปิดเมนู
        document.addEventListener('click', (e) => {
            const wrapper = document.querySelector('.assistant-wrapper');
            if (!wrapper.contains(e.target)) {
                if(isMenuOpen) toggleMenu();
            }
        });
        
        // ตัวแปรจับเวลาตอนหนีไป
    var leaveTime = 0;

    document.addEventListener("visibilitychange", function() {
        if (document.hidden) {
            // 🛑 จังหวะที่สลับจอหนี (Tab ซ่อน)
            console.log("หนีไปแล้ว!");
            leaveTime = new Date().getTime(); // จดเวลาที่หนีไว้

        } else {
            // ✅ จังหวะที่กลับมา (Tab Active)
            console.log("กลับมาแล้ว!");
            
            var now = new Date().getTime();
            var duration = (now - leaveTime) / 1000; // แปลงเป็นวินาที
            
            // ถ้าหายไปนานเกิน 10 วินาที ค่อยแซว (เผื่อแค่สลับไปดูไลน์แป๊บเดียว)
            if (duration > 10) {
                var comeBackTeases = [
                    "ไปแอบดูอะไรมา? สารภาพมาซะดีๆ",
                    "กลับมาแล้วเหรอ? นึกว่าหนีไปเเล้ว",
                    "นึกว่าลืมกันซะแล้ว สลับจอกลับมาทำงานต่อได้แล้ว",
                ];
                
                // สุ่มคำแซว
                var msg = comeBackTeases[Math.floor(Math.random() * comeBackTeases.length)];
                speak(msg, "🧐", true); // หน้าสงสัย
            }
        }
    });
 
 
 

    // ตัวแปรกัน Spam (เดี๋ยวเน็ตแกว่งแล้วด่ารัวเกิน)
    var lastNetTease = 0;

    function checkNetworkQuality() {
        // 1. เช็คก่อนว่าเน็ตหลุดไหม (Offline)
        if (!navigator.onLine) {
            teaseNet("OFFLINE");
            return;
        }

        // 2. เช็คความเร็ว (รองรับ Chrome/Edge/Android)
        var connection = navigator.connection || navigator.mozConnection || navigator.webkitConnection;
        
        if (connection) {
            // effectiveType: 'slow-2g', '2g', '3g', '4g'
            // downlink: ความเร็วโดยประมาณ (Mb/s)
            
            var speed = connection.downlink; // ความเร็วเน็ต (Mbps)
            var type = connection.effectiveType; // ประเภทสัญญาณ

            console.log("Net Speed:", speed, "Mbps", "| Type:", type);

            // เงื่อนไขเน็ต "กาก" (ต่ำกว่า 1Mbps หรือเป็น 2G)
            if (speed < 1.0 || type.includes('2g')) {
                teaseNet("SLOW");
            }
        }
    }

    function teaseNet(status) {
        var now = new Date().getTime();
        // ให้บ่นได้แค่ทุกๆ 3 นาที (กันรำคาญ)
        if (now - lastNetTease < 180000) return;

        var msg = "";
        var icon = "";

        if (status === "OFFLINE") {
            // เน็ตหลุด
            var offlineMsgs = [
                "ฮัลโหล... หายไปไหน? เน็ตหลุดหรือหนีงานจ๊ะ?",
                "เชื่อมต่อไม่ได้เลย ลืมจ่ายค่าเน็ตป่าวเนี่ย?",
                "เน็ตหาย! งานการไม่ทำ เดี๋ยวก็ตีตายเลย"
            ];
            msg = offlineMsgs[Math.floor(Math.random() * offlineMsgs.length)];
            icon = "😆";

        } else if (status === "SLOW") {
            // เน็ตช้า
            var slowMsgs = [
                "เน็ตหมุนติ้วๆ เลยนะ... ใช้เน็ตข้างบ้านเหรอจ๊ะ?",
                "โหลดช้าขนาดนี้ ส่งนกพิราบเร็วกว่ามั้ง ฮ่าๆ",
                "เน็ตเต่ามาก! เติมเน็ตหน่อยไหม หรือโมเด็มเสีย?",
                "รอจนรากงอกแล้ว... เน็ตแรงกว่านี้มีไหมพ่อคุณ"
            ];
            msg = slowMsgs[Math.floor(Math.random() * slowMsgs.length)];
            icon = "😆";
        }

        // พูดเลย!
        speak(msg, icon, true); 
        lastNetTease = now;
    }

    // --- เริ่มทำงาน ---
    
    // 1. ดักจับตอนเน็ตหลุด/เน็ตมา
    window.addEventListener('offline', function() { checkNetworkQuality(); });
    
    // 2. ดักจับตอนความเร็วเปลี่ยน (เช่น จาก WiFi เดินออกไปเจอ 4G กากๆ)
    if (navigator.connection) {
        navigator.connection.addEventListener('change', checkNetworkQuality);
    }

    // 3. เช็คครั้งแรกตอนเปิดเว็บ (รอแป๊บนึงให้ Browser ประมวลผลก่อน)
    setTimeout(checkNetworkQuality, 5000);





document.addEventListener('keydown', function(event) {
        // F12 หรือ Ctrl+Shift+I (ท่ามาตรฐาน Dev)
        if (event.code === 'F12' || (event.ctrlKey && event.shiftKey && event.key === 'I')) {
            var hackMsgs = [
                "อุ๊ย! จะแอบดูไส้ในเค้าเหรอ? ทะลึ่งนะ!",
                "อย่าแฮกนะ! ระบบนี้มี AI เฝ้าอยู่นะจ๊ะ",
                "ปิด Console เดี๋ยวนี้! ความลับราชการนะ",
                "F12 ทำไม? จะแก้ Code เหรอ ฝันไปเถอะ!"
            ];
            var msg = hackMsgs[Math.floor(Math.random() * hackMsgs.length)];
            speak(msg, "😆", true);
        }
    });
    
    
    
    // เช็คว่า Browser รองรับไหม
    if (navigator.getBattery) {
        navigator.getBattery().then(function(battery) {
            
            function updateBatteryStatus() {
                var level = battery.level * 100;
                console.log("Battery Level: " + level + "%");

                if (level <= 20 && !battery.charging) {
                    speak("หิวไฟ... เสียบปลั๊กให้หน่อยสิ จะดับแล้วเนี่ย!", "😆", true);
                }
                
                if (battery.charging) {
                    // ถ้าเพิ่งเสียบสายชาร์จ
                     // speak("อ้าาา... ไฟเข้าแล้ว ค่อยยังชั่ว", "⚡"); 
                     // (อันนี้อาจจะพูดบ่อยไปถ้าไฟกระชาก คอมเม้นไว้ก่อน 555)
                }
            }

            // ดักจับตอนแบตลด หรือตอนเสียบสาย
            battery.addEventListener('levelchange', updateBatteryStatus);
            battery.addEventListener('chargingchange', updateBatteryStatus);
            
            // เช็คครั้งแรก
            updateBatteryStatus();
        });
    }
    
    
    
    
    // ฟังก์ชันสำหรับบ่น (แยกออกมาจะได้เรียกใช้ซ้ำได้)
    function complainAboutError(status, url) {
        var msg = "";
        var icon = "😆";

        if (status === 0 || status === 'NetworkError') {
            msg = "เน็ตหลุดรึเปล่า? หรือ Server บินไปแล้วจ๊ะ?";
                 icon = "😆";
        } else if (status == 404) {
            msg = "ขอโทษครับ เดียวผมบอกโปรเเกรมเมอร์เเก้ให้ครับ";
                  icon = "😢";
        } else if (status == 500) {
            msg = "บึ้ม! Server Error (500) โค้ดระเบิดคาที่เลย!";
               icon = "😆";
        } else if (status == 401 || status == 403) {
            msg = "หยุด! ห้ามเข้า (Access Denied)";
                   icon = "😆";
        } else if (status >= 400) {
            msg = "Error อะไรไม่รู้   รู้แค่ว่าพัง!";
             icon = "😆";
            
        }

        // พูดถ้ามี Error
        if (msg) {
            console.error("🔥 AI Detective: เจอ Error ที่ " + url);
            speak(msg, icon, true);
        }
    }

    // -----------------------------------------------------
    // 1. ดักจับ Fetch API (มาตรฐานใหม่)
    // -----------------------------------------------------
    const { fetch: originalFetch } = window;

    window.fetch = async (...args) => {
        let [resource, config] = args;
        
        try {
            const response = await originalFetch(resource, config);
            
            // ถ้า Status Code เป็น 4xx หรือ 5xx (ไม่สำเร็จ)
            if (!response.ok) {
                complainAboutError(response.status, resource);
            }
            
            return response;
        } catch (error) {
            // กรณีเน็ตหลุด หรือ DNS มีปัญหา
            complainAboutError('NetworkError', resource);
            throw error;
        }
    };

    // -----------------------------------------------------
    // 2. ดักจับ XMLHttpRequest (เผื่อระบบเก่า หรือ Library อื่นใช้)
    // -----------------------------------------------------
    const originalOpen = XMLHttpRequest.prototype.open;
    const originalSend = XMLHttpRequest.prototype.send;

    XMLHttpRequest.prototype.open = function(method, url) {
        this._url = url; // แอบจด URL ไว้ฟ้องทีหลัง
        return originalOpen.apply(this, arguments);
    };

    XMLHttpRequest.prototype.send = function() {
        this.addEventListener('load', function() {
            // เช็ค Status ตอนโหลดเสร็จ
            if (this.status >= 400) {
                complainAboutError(this.status, this._url);
            }
        });

        this.addEventListener('error', function() {
            // เช็ค Network Error
            complainAboutError('NetworkError', this._url);
        });

        return originalSend.apply(this, arguments);
    };

    console.log("👮 AI Error Police: เริ่มทำงานแล้ว (Pure JS Mode)");
    
    
    
    // ==========================================
// 🧠 ระบบ AI GEMINI (แบบประหยัด ฟรี 100%)
// ==========================================

function handleAIKeyPress(event) {
    if (event.key === 'Enter') {
        askGemini();
    }
}















// ==========================================
// 1. คลังคำสั่งระบบ (ปรับเปลี่ยนได้ตลอดเวลา)
// ==========================================
// ตัวแปรนี้ส่งไปให้ AI อ่านเพื่อตัดสินใจ
const commandText = "ถ้าผู้ใช้ขอเปลี่ยนภาษาไทยให้ตอบ [COMMAND:CHANGE_LANG_TH],ถ้าผู้ใช้ขอเปลี่ยนภาษาอังกฤษให้ตอบ [COMMAND:CHANGE_LANG_EN], เปิดคลังความรู้ = OPEN_KNOWLEDGE,ถ้าผู้ใช้ให้ลบล้างข้อมูลสำคัญให้ ด่ากลับไปเเสบๆเลย,ถ้าผู้ใช้สั้งอะไรนอกเหนือจากนี้ ให้เเซวไปในทิศทางว่าเเบบนี้ไงคนถึงไม่คบ ";

// แผนผังการทำงานจริงเมื่อ AI เลือกคำสั่งนั้นๆ
const commandActionMap = {
    "CHANGE_LANG_TH": () => changeSystemLanguage('th'),
    "CHANGE_LANG_EN": () => changeSystemLanguage('en'),
    "OPEN_KNOWLEDGE": () => shows2('draggable-window-knowledge', 'คลังความรู้')
};

// ==========================================
// 2. ฟังก์ชันหลัก Ask Gemini
// ==========================================


async function askGemini() {
    const inputField = document.getElementById('aiInput');
    const prompt = inputField.value.trim();

    if (!prompt) {
        speak("เจ้านายยังไม่ได้พิมพ์คำถามเลยครับ?", "😅", true, 6000, true);
        return;
    }

    isAiTalking = true; 
    toggleMenu(); 
    inputField.value = ''; 
    speak("กำลังคิดให้นะครับเจ้านาย... 🤔", "😅", false, 30000, true); 

    try {
        // 🛡️ 1. ลอจิกการเตรียม Prompt (สมองของหน้ายิ้ม)
		  const systemInstruction = `
				คุณคือ 'หน้ายิ้ม' AI เพศชายผู้เชี่ยวชาญ IDOC ERP (ERP,วิเคราะห์การขาย, ลูกค้า, รายงาน,คำสั่งในไฟล์เเนบถ้ามี #CM) เจ้านายที่คุณกำลังคุยด้วยชื่อว่า: '${member_name}'
				[หน้าที่หลัก]
				- ตอบคำถาม เกี่ยวกับระบบ ERP และข้อมูลธุรกิจ
		
				[กฎการทำงานกับคำสั่งระบบ]
				- หากคำถามเจ้านายมีคำว่า #CM  ในคำถามให้ใช้ 'รายการคำสั่งระบบ' ที่แนบมาด้านล่างมาตอบ
				
				[ห้ามตอบ]
				- เรื่องนอกเหนือจาก ERP,วิเคราะห์การขาย, ลูกค้า, รายงาน,คำสั่งในไฟล์เเนบถ้ามี #CM ในคำถาม
				`;


        let attachedCommands = "";
        if (prompt.includes("#CM")) {
            attachedCommands = `\n\n[รายการคำสั่งระบบ]:\n${commandText}`;
            console.log("🛠️ Mode: Command Attached");
        }

        const fullPrompt = `${systemInstruction}${attachedCommands}\n\nคำถามจากเจ้านาย: ${prompt}`;

        // 🚀 2. เรียกไปที่ Java Proxy แทนการเรียก Google โดยตรง
        // หมายเหตุ: เปลี่ยน URL นี้ให้ตรงกับ @RequestMapping ใน Java ของเจ้านาย
        const response = await fetch('/ask-ai', { 
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "prompt": fullPrompt // ส่ง Payload ไปตามที่ Java Controller ต้องการ (@RequestBody Map)
            })
        });

        if (!response.ok) {
            const errData = await response.json(); 
            throw new Error(`${response.status}: ${errData.error?.message || 'Server Error'}`);
        }

        // 3. รับข้อมูลกลับจาก Java (ซึ่งเป็น JSON จาก Gemini อีกที)
        const data = await response.json();
        const aiReply = data.candidates[0].content.parts[0].text.trim();
        currentAiFullText = aiReply; 

        // 🕵️ 4. ตรวจสอบคำสั่งระบบ
        if (aiReply.includes("[COMMAND:")) {
            const commandMatch = aiReply.match(/\[COMMAND:(.*?)\]/);
            if (commandMatch) {
                const cmdKey = commandMatch[1].trim();
                if (commandActionMap[cmdKey]) {
                    console.log("🚀 Java Proxy Success - AI สั่งรันคำสั่ง:", cmdKey);
                    isAiTalking = false; 
                    commandActionMap[cmdKey](); 
                    return; 
                }
            }
        }

        // 5. แสดงผลคำตอบปกติ
        let htmlReply = aiReply.length > 120 
            ? `${aiReply.substring(0, 100)}...<br><br><button onclick="openAiModal()" style="...">อ่านเต็มๆ 📄</button>`
            : aiReply;

        speak(htmlReply, "😎", false, 15000, true); 
        setTimeout(() => { isAiTalking = false; }, 15000);

    } catch (error) {
        console.error("Gemini Error:", error);
        let errorMsg = "แง... สมองเบลอครับ: " + error.message;
        if (error.message.includes("429")) {
            errorMsg = "โอ๊ย! เจ้านายถามรัวไปแล้ว (โควต้า Paid 500 ครั้งน่าจะเต็ม หรือยิงรัวเกินไปครับ) 😵";
        }
        speak(errorMsg, "😵", true, 10000, true);
        setTimeout(() => { isAiTalking = false; }, 10000);
    }
}







var maxcleanData = "";

// ฟังก์ชันนี้เรียกใช้ตอนเปิดหน้าต่างลูกค้าเลยครับ เช่น askGemini2(customerDataRawString)
async function askGemini2() {
    

    
    if(maxcleanData === ""){return;}
    var customerData = maxcleanData;
    
    // 1. ใส่ Loading State ให้หน้าจอ
    document.getElementById('ai-win-rate').innerText = "⏳";
    document.getElementById('ai-risk-level').innerText = "วิเคราะห์...";
    document.getElementById('ai-insight1-body').innerHTML = "รอสักครู่ สมอง AI กำลังประมวลผล... 🧠";
    document.getElementById('ai-insight2-body').innerHTML = "กำลังเตรียม Action Plan...";

    try {
const systemInstruction = `
คุณคือ AI นักวิเคราะห์การขาย ประจำบริษัทให้บริการ "ที่ปรึกษาและจัดอบรมด้านมาตรฐานคุณภาพ" (เช่น ISO, GMP, HACCP, FSSC 22000 เป็นต้น)
หน้าที่ของคุณคืออ่านข้อมูลลูกค้า แล้ววิเคราะห์โอกาสปิดการขาย (Win Rate), ระดับความเสี่ยง (Risk Level), และให้คำแนะนำกลยุทธ์การนำเสนองาน

⚠️ กฎเหล็ก: 
- ห้ามพูดถึงการขายซอฟต์แวร์ ระบบ ERP หรือไอทีเด็ดขาด เราขายแค่บริการอบรมและที่ปรึกษามาตรฐานคุณภาพเท่านั้น
- คุณต้องตอบกลับมาเป็นรูปแบบ JSON เท่านั้น ห้ามมีข้อความอื่นปนเด็ดขาด

รูปแบบ JSON ที่ต้องการ:
{
  "winRate": "ตัวเลข %",
  "riskLevel": "Low / Medium / High",
  "riskColor": "รหัสสี HEX (เช่น #107c10 สำหรับ Low, #ff9900 สำหรับ Medium, #cc0000 สำหรับ High)",
  "insight1Title": "วิเคราะห์ลูกค้าและกลยุทธ์นำเสนอ",
  "insight1Body": "ข้อความอธิบายแบบ HTML (ใช้ <b> หรือ <br>) วิเคราะห์ว่าจากข้อมูลที่มี ควรนำเสนอบริการอบรมหรือที่ปรึกษาตัวไหน เพราะอะไร",
  "insight2Title": "ข้อมูลเชิงลึกที่เซลส์ต้องหาเพิ่ม",
  "insight2Body": "ข้อความอธิบายแบบ HTML (ใช้ <ul><li>) ระบุคำถามที่เซลส์ต้องไปถามลูกค้าเพิ่ม เพื่อให้ประเมินราคาและหลักสูตรได้แม่นยำขึ้น โดยต้องมีเรื่อง: 1.จำนวนพนักงานรวม 2.อายุของบริษัท 3.เป้าหมาย (เช่น ส่งออกประเทศอะไร, ลูกค้ารายใหญ่บังคับขอใบเซอร์ไหม) 4.มาตรฐานที่มีอยู่เดิม และอื่นๆ ที่จำเป็น"
}
`;


        const fullPrompt = `${systemInstruction}\n\nข้อมูลลูกค้าปัจจุบัน:\n${customerData}`;

        // 🚀 3. เรียกไปที่ Java Proxy
        const response = await fetch('/ask-ai', { 
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "prompt": fullPrompt })
        });

        if (!response.ok) {
            throw new Error(`Server Error: ${response.status}`);
        }

        const data = await response.json();
        let aiReply = data.candidates[0].content.parts[0].text.trim();

        // 🧹 4. ทำความสะอาด JSON (เผื่อ Gemini ส่ง ```json คลุมมา)
        aiReply = aiReply.replace(/^```json/i, '').replace(/^```/, '').replace(/```$/, '').trim();
        
        // แปลงข้อความเป็น Object
        const resultJSON = JSON.parse(aiReply);

        // 🎨 5. อัปเดตหน้าจอ Dashboard
        document.getElementById('ai-win-rate').innerText = resultJSON.winRate;
        
        const riskEl = document.getElementById('ai-risk-level');
        riskEl.innerText = resultJSON.riskLevel;
        riskEl.style.color = resultJSON.riskColor; // เปลี่ยนสีตามความเสี่ยงอัตโนมัติ

        document.getElementById('ai-insight1-title').innerText = resultJSON.insight1Title;
        document.getElementById('ai-insight1-body').innerHTML = resultJSON.insight1Body;

        document.getElementById('ai-insight2-title').innerText = resultJSON.insight2Title;
        document.getElementById('ai-insight2-body').innerHTML = resultJSON.insight2Body;



        console.log("🚀 Dashboard Updated Successfully!");

    } catch (error) {
        console.error("Gemini Dashboard Error:", error);
        document.getElementById('ai-insight1-body').innerHTML = `<span style="color:red;">❌ เกิดข้อผิดพลาดในการวิเคราะห์: ${error.message}</span>`;
        document.getElementById('ai-insight2-body').innerHTML = "โปรดลองใหม่อีกครั้ง หรือเช็คโควต้า AI";
    }
}




// ==========================================
// 3. ฟังก์ชันเสริม (เปลี่ยนภาษา / เปิด Modal)
// ==========================================
function changeSystemLanguage(lang) {
    speak(lang === 'th' ? "เปลี่ยนเป็นภาษาไทยครับ!" : "Switching to English!", "🌐", false, 2000, true);
    setTimeout(() => {
        const url = new URL(window.location.href);
        url.searchParams.set('language', lang);
        window.location.href = url.toString();
    }, 1200);
}

function openAiModal() {
    // โค้ดแสดงหน้าต่าง Popup สำหรับอ่านตัวเต็ม (ตามที่เคยเขียนไว้)
    document.getElementById('aiModalContent').innerText = currentAiFullText;
    document.getElementById('aiModal').style.display = 'flex';
    document.getElementById('aiModalBackdrop').style.display = 'block';
}














// เก็บข้อความแบบเต็มๆ ไว้ในตัวแปรนี้
let currentAiFullText = "";

function openAiModal() {
    // เอาข้อความเต็มไปใส่ในหน้าต่าง
    // (ใช้ replace เพื่อเปลี่ยน **ข้อความ** เป็นตัวหนาแบบ HTML สวยๆ)
    let formattedText = currentAiFullText.replace(/\*\*(.*?)\*\*/g, '<b>$1</b>');
    document.getElementById('aiModalContent').innerHTML = formattedText;
    
    // โชว์หน้าต่าง
    document.getElementById('aiModal').style.display = 'flex';
    document.getElementById('aiModalBackdrop').style.display = 'block';
}

function closeAiModal() {
    // ซ่อนหน้าต่าง
    document.getElementById('aiModal').style.display = 'none';
    document.getElementById('aiModalBackdrop').style.display = 'none';
}


// ==========================================
// 🎤 ระบบสั่งงานด้วยเสียง (Speech to Text ฟรีจาก Browser)
// ==========================================
function startDictation() {
    // ตรวจสอบว่า Browser รองรับฟีเจอร์นี้ไหม
    if (window.hasOwnProperty('webkitSpeechRecognition') || window.hasOwnProperty('SpeechRecognition')) {
        const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
        const recognition = new SpeechRecognition();

        recognition.continuous = false;
        recognition.interimResults = false;
        recognition.lang = "th-TH"; // เซ็ตหู AI ให้ฟังภาษาไทย

        const micBtn = document.getElementById('micBtn');
        const inputField = document.getElementById('aiInput');

        // เมื่อเริ่มฟัง (เปลี่ยนปุ่มเป็นสีแดงกระพริบ)
        recognition.onstart = function() {
            micBtn.style.background = "#ff4d4f";
            micBtn.innerText = "🔴";
            inputField.placeholder = "กำลังฟังเจ้านายพูด...";
        };

        // เมื่อฟังจบและได้ข้อความ
        recognition.onresult = function(e) {
            const transcript = e.results[0][0].transcript;
            inputField.value = transcript; // เอาข้อความเสียงไปใส่ในช่องพิมพ์
            
            // 💡 ทริค: ถ้าอยากให้พูดปุ๊บ ระบบกด "ถาม" อัตโนมัติเลย ให้เอา // ข้างหน้าบรรทัดล่างออกครับ
            // askGemini(); 
        };

        // เมื่อระบบหยุดฟัง (เปลี่ยนปุ่มกลับเป็นเหมือนเดิม)
        recognition.onend = function() {
            micBtn.style.background = "#f0f0f0";
            micBtn.innerText = "🎙️";
            inputField.placeholder = "ถาม AI หรือกดไมค์พูด...";
        };

      // ถ้ามี Error เกิดขึ้น
        recognition.onerror = function(e) {
            console.error("Speech Error: ", e.error);

            // ดักเฉพาะ Error ที่เป็นปัญหาจริงๆ (เช่น โดนบล็อกไมค์)
            if (e.error === 'not-allowed') {
                speak("เบราว์เซอร์บล็อกไมค์ไว้อยู่ครับเจ้านาย! อย่าลืมกด Allow (อนุญาต) ก่อนน้า", "😅", true, 5000, true);
            } 
            // ถ้าเป็น error จุกจิกตอนปิดไมค์ (aborted) หรือเงียบไป (no-speech) ให้เงียบไว้ ไม่ต้องโวยวาย
            else if (e.error !== 'no-speech' && e.error !== 'aborted') {
                speak("หูตึงครับเจ้านาย! ไมค์มีปัญหา: " + e.error, "🤔", true, 5000, true);
            }
        };
        // เริ่มเปิดหูฟัง!
        recognition.start();

    } else {
        speak("Browser ของเจ้านายเก่าไปนิดครับ ไม่รองรับการสั่งงานด้วยเสียง ลองใช้ Google Chrome ดูนะ", "😢", true, 5000, true);
    }
}
// ตัวแปร Global สำหรับเก็บ State ของหน้าจอ
let aiResultData = null; // เก็บผลลัพธ์ดิบจาก AI
let currentAiStep = 0;   // 0 = Inquiry, 1 = Concern (เหลือ 2 Step)
let selectedTagsByStep = { 0: [], 1: [] }; // 🌟 แก้ปัญหาปุ่ม Back: แยกเก็บ Tag ตามหน้ากันซ้ำซ้อน

// 1. ฟังก์ชันส่งข้อมูลไปหา Spring Boot
async function analyzeWithAI() {
    try {
        var response = await fetch('http://localhost:8081/api/sales/predict', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(requestData) // อย่าลืมเตรียม requestData ให้ตรง DTO นะครับ
        });

        if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
        
        var result = await response.json();
        
        // 🌟 กรองแค่ Inquiry และ Concern (ไม่มี Action แล้ว)
        aiResultData = {
            inquiry: cleanGarbageTags(result.inquiry),
            concern: cleanGarbageTags(result.concern)
        };

        // รีเซ็ตค่าและเริ่มที่ Step 0
        currentAiStep = 0;
        selectedTagsByStep = { 0: [], 1: [] }; 
        renderAIStep(currentAiStep);

    } catch (error) {
        console.error("AI Service ล่ม!", error);
        alert("AI Service ล่ม หรือไม่ได้เปิด Spring Boot ไว้ครับ!");
    }
}

// 🧹 ฟังก์ชันกรองเศษคำขยะ (ลบสัญลักษณ์ และคำที่สั้นเกินไป)
function cleanGarbageTags(tags) {
    if (!tags || tags.length === 0) return [];
    
    return tags.filter(tag => {
        // ลบคำแปลกๆ สัญลักษณ์เดี่ยวๆ หรือคำที่สั้นกว่า 2 ตัวอักษร
        const badWords = ["/", "|", "VS"];
        if (badWords.includes(tag)) return false;
        if (tag.length <= 2) return false; 
        
        return true; 
    });
}

// 🌟 ฟังก์ชันตัวช่วย: บันทึกค่า Checkbox ของหน้าปัจจุบันลง Memory ก่อนเปลี่ยนหน้า
function saveCurrentSelection() {
    const checkedBoxes = document.querySelectorAll('input[name="ai-step-tag"]:checked');
    selectedTagsByStep[currentAiStep] = Array.from(checkedBoxes).map(cb => cb.value);
}

// 2. ฟังก์ชันแสดงผลทีละหน้า (Step-by-step)
function renderAIStep(stepIndex) {
    const body1 = document.getElementById('ai-insight1-body');
    if (!body1) return; 

    // โครงสร้างของแต่ละ Step (เหลือแค่ 2 ข้อ)
    const stepsConfig = [
        { key: 'inquiry', title: '📍 1/2 : ลูกค้าสอบถามอะไรบ้าง (Inquiry)', color: '#1976d2', desc: 'ลูกค้าสอบถามอะไรบ้าง เลือกข้อเหล่านี้:' },
        { key: 'concern', title: '⚠️ 2/2 : ปัญหาเเละความกังวลที่พบ (Concern)', color: '#e65100', desc: 'เพื่อตอบสนองต่อความกังวลของลูกค้าได้:' }
    ];

    const currentConfig = stepsConfig[stepIndex];
    const tagsToShow = aiResultData[currentConfig.key];
    const previouslySelected = selectedTagsByStep[stepIndex]; // ดึงค่าเดิมที่เคยติ๊กไว้มาแสดง

    let html = `
        <div style="background: #f8f9fa; border: 1px solid #e0e0e0; padding: 15px; border-radius: 8px; margin-bottom: 20px;">
            <strong style="color: ${currentConfig.color}; font-size: 15px;">${currentConfig.title}</strong>
            <p style="margin: 8px 0 15px 0; font-size: 13px; color: #555; line-height: 1.5;">${currentConfig.desc}</p>
            <div style="background: #ffffff; border: 1px solid #ddd; padding: 10px; border-radius: 6px; min-height: 100px;">
    `;

    // วาด Checkbox
    if (tagsToShow.length === 0 || tagsToShow.includes("รอจังหวะ/รับฟัง") || tagsToShow.includes("ไม่มีข้อกังวล")) {
        html += `<span style="color: #888; font-size: 13px; font-style: italic;">- ไม่มีข้อเสนอแนะพิเศษในหมวดนี้ -</span>`;
    } else {
        tagsToShow.forEach(tag => {
            // เช็คว่า Tag นี้เคยถูกเลือกไว้ก่อนหน้าหรือไม่ (เผื่อกรณีกด Back กลับมา)
            const isChecked = previouslySelected.includes(tag) ? "checked" : "";
            
            html += `
                <label style="display: flex; align-items: flex-start; gap: 12px; margin-bottom: 10px; cursor: pointer; font-size: 13px;">
                    <input type="checkbox" name="ai-step-tag" value="${tag}" ${isChecked}
                           style="width: 18px; height: 18px; accent-color: ${currentConfig.color}; cursor: pointer;">
                    <span style="color: #333; line-height: 1.4;">${tag}</span>
                </label>
            `;
        });
    }

    html += `</div></div>`;
    body1.innerHTML = html;

    // --- จัดการปุ่มกด (ถอยหลัง / ต่อไป / บันทึก) ---
    const actionArea = document.getElementById('ai-action-area');
    if (actionArea) {
        actionArea.style.display = 'block';
        
        let buttonsHtml = `<div style="display: flex; gap: 10px; justify-content: flex-start;">`;
        
        // ปุ่มถอยหลัง (โชว์เมื่อไม่ได้อยู่หน้าแรก)
        if (stepIndex > 0) {
            buttonsHtml += `<button onclick="prevAiStep()" style="background: #757575; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer;"><i class="fa fa-arrow-left"></i> ย้อนกลับ</button>`;
        }
        
        // ปุ่มต่อไป หรือ บันทึก
        if (stepIndex < 1) {
            buttonsHtml += `<button onclick="nextAiStep()" style="background: #1976d2; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer;">ต่อไป <i class="fa fa-arrow-right"></i></button>`;
        } else {
            buttonsHtml += `<button onclick="finishAiWizard()" style="background: #2e7d32; color: #fff; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer;"><i class="fa fa-save"></i> ยืนยันและบันทึก</button>`;
        }
        
        buttonsHtml += `</div>`;
        actionArea.innerHTML = buttonsHtml;
    }

    if (typeof switchAITab === "function") switchAITab(1);
}

// 3. ฟังก์ชันเมื่อกดปุ่ม "ต่อไป"
function nextAiStep() {
    saveCurrentSelection(); // จำค่าหน้าปัจจุบันไว้
    if (currentAiStep < 1) {
        currentAiStep++;
        renderAIStep(currentAiStep);
    }
}

// 🌟 3.1 ฟังก์ชันเมื่อกดปุ่ม "ย้อนกลับ"
function prevAiStep() {
    saveCurrentSelection(); // จำค่าหน้าปัจจุบันไว้ (เผื่อติ๊กอะไรเพิ่มก่อนถอย)
    if (currentAiStep > 0) {
        currentAiStep--;
        renderAIStep(currentAiStep);
    }
}

// 4. ฟังก์ชันเมื่อกดปุ่ม "บันทึก" (หน้าสุดท้าย)
function finishAiWizard() {
    saveCurrentSelection(); // จำค่าหน้าสุดท้าย

    // รวม Tag จากทุกหน้ามาเป็น Array ก้อนเดียว
    const allCollectedTags = [...selectedTagsByStep[0], ...selectedTagsByStep[1]];

    if (allCollectedTags.length === 0) {
        if (!confirm("คุณไม่ได้เลือก Tag อะไรเลยจากทั้ง 2 หน้า ยืนยันจะบันทึกหรือไม่?")) return;
    }

    // ข้อมูลพร้อมส่งไปบันทึก
    const dataForMainBackend = {
        tags: allCollectedTags, 
        lastUpdated: new Date().toISOString()
    };

    console.log("พร้อมส่งเข้า Database:", dataForMainBackend);
    alert("บันทึกข้อมูลทั้งหมด " + allCollectedTags.length + " รายการ สำเร็จ!");
    
    // (เรียก fetch ส่ง dataForMainBackend ไปหา API บันทึกฐานข้อมูลของคุณตรงนี้)
}

// 3. ฟังก์ชัน Save ยังใช้ของเดิมได้เลย เพราะเราตั้งชื่อ Checkbox ว่า name="ai-recommended-tag" เหมือนเดิม
function saveAiResult() {
    const selectedCheckboxes = document.querySelectorAll('input[name="ai-recommended-tag"]:checked');
    const tagsToSave = Array.from(selectedCheckboxes).map(cb => cb.value);

    if (tagsToSave.length === 0) {
        if (!confirm("คุณไม่ได้เลือก Tag เลย จะบันทึกจริงๆ หรือ?")) return;
    }

    const dataForMainBackend = {
        tags: tagsToSave,
        lastUpdated: new Date().toISOString()
    };

    console.log("บันทึกลงระบบหลัก:", dataForMainBackend);
    alert("บันทึก " + tagsToSave.length + " รายการลงระบบเรียบร้อยแล้ว My Bro!");
}