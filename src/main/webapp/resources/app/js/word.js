// =========================================================
//  SECTION 1: GLOBAL VARIABLES (ตัวแปรส่วนกลาง)
//  ต้องประกาศไว้นอกสุด เพื่อให้ทุกฟังก์ชันมองเห็น
// =========================================================
let currentSizeClass = 'page-size-A4';
let selectedElement = null;
let activeTableCell = null;
let isSelectingTable = false;
let startCell = null;
let savedRange = null;


// ตัวแปรสำหรับสี
let currentFillColor = '#000000';
let currentFontColor = '#000000';
let currentHiliteColor = '#ffff00';



let historyStack = [];
let historyIndex = -1; // ตัวชี้ว่าตอนนี้เราอยู่ประวัติลำดับที่เท่าไหร่
const MAX_HISTORY = 50; // จำได้สูงสุด 50 ครั้ง
let isRestoring = false; // ตัวป้องกันการเซฟซ้ำตอนกำลังย้อนกลับ
let saveTimeout;


// 1. เพิ่มตัวแปรไว้ด้านบนสุดของไฟล์ (Global Variable)
let fileHandle = null;


// ตัวแปรสำหรับการย่อขยาย (Resizing)
let isResizing = false, sx, sy, sw, sh;
let  currentZoom = parseInt(localStorage.getItem('my_word_zoom_level')) || 100;
  
  
//ตัวแปรคีย์สำหรับเก็บใน Local Storage
const LOCAL_STORAGE_KEY = "my_word_drafts";
const MAX_SAVED_DOCS = 100; // จำกัดที่ 100 ใบ  
  
function loadword() {
    if (window.isSystemInitialized) return;
    console.log("System Initializing...");

    const docArea = document.getElementById('document-area');
    if (!docArea) return;

    // ตัวแปรจับสถานะการลาก
    let isDraggingTable = false;

    // --- Mousedown Handler ---
    document.addEventListener('mousedown', e => {
        if (!e.target.closest('#document-area')) return;

        let realTarget = document.elementFromPoint(e.clientX, e.clientY);
        if (!realTarget) realTarget = e.target;

        // 1. Check Resizer
        if (realTarget.closest('#resizer-frame') || realTarget.classList.contains('resize-handle')) {
            return;
        }

        // 2. รูปภาพ (IMG/Wrapper)
        if (realTarget.tagName === 'IMG') {
            selectElement(realTarget);
            e.preventDefault(); e.stopPropagation(); return;
        }
        if (realTarget.classList.contains('img-wrapper')) {
            const imgInside = realTarget.querySelector('img');
            if (imgInside) {
                selectElement(imgInside);
                e.preventDefault(); e.stopPropagation(); return;
            }
        }

        // ============================================================
        // CASE C: กดโดน "ตาราง" (แก้ Logic ตรงนี้ครับ!)
        // ============================================================
        const cell = realTarget.closest('td, th');
        if (cell) {
            if (selectedElement && selectedElement.tagName === 'IMG') deselectElement();

            isSelectingTable = true; // บอกว่าเริ่มกระบวนการตารางนะ
            isDraggingTable = false; // แต่ยังไม่ได้เริ่มลาก (แค่กดลง)
            
            startCell = cell;
            
            if (!e.ctrlKey) clearTableSelection();
            
            cell.classList.add('selected-cell');
            activeTableCell = cell;
            checkTableContext();

            // *** จุดสำคัญ: ห้ามใส่ e.preventDefault() ตรงนี้เด็ดขาด ***
            // ปล่อยให้ Browser วาง Cursor ลงไปในช่อง เพื่อให้พิมพ์ได้
            return;
        }

        // 4. พื้นที่ว่าง
        if (selectedElement) deselectElement();
        clearTableSelection();
        activeTableCell = null;
        checkTableContext();
    });

    // --- Mouse Over (ตอนลากเมาส์ผ่านช่องต่างๆ) ---
    document.addEventListener('mouseover', e => {
        // ต้องมีการกดค้าง (isSelectingTable) และมีจุดเริ่ม (startCell)
        if (!isSelectingTable || !startCell) return;
        
        const t = e.target.closest('td, th');

        // ถ้าลากไปโดนเซลล์อื่น (ที่ไม่ใช่เซลล์เริ่มต้น)
        if (t && t !== startCell && t.closest('table') === startCell.closest('table')) {
            
            // *** เริ่มโหมดลากจริงจัง ***
            isDraggingTable = true;

            // ตอนนี้แหละ! ที่ต้องห้าม Browser ไฮไลท์ข้อความมั่วซั่ว
            // เพราะเราตั้งใจจะเลือกหลายช่อง ไม่ได้จะคลุม Text
            if (window.getSelection) {
                window.getSelection().removeAllRanges(); // ล้าง Text Selection ทิ้ง
            }
            
            selectCells(startCell, t);
        }
    });
    
    // --- เพิ่ม: กันเหนียวตอนลาก ---
    // ถ้าเมาส์ขยับในขณะที่ลากตารางอยู่ ให้ฆ่า Event ทิ้ง เพื่อไม่ให้ Text Selection โผล่มา
    document.addEventListener('mousemove', e => {
        if (isDraggingTable) {
            e.preventDefault(); 
        }
    });

    // --- Mouse Up ---
    document.addEventListener('mouseup', () => {
        isSelectingTable = false;
        isDraggingTable = false; // จบการลาก
        startCell = null;
    });

    // --- ส่วนอื่นๆ คงเดิม ---
    document.addEventListener('scroll', e => { if(selectedElement) updateResizerPosition(); }, true);
    window.addEventListener('resize', updateResizerPosition);
    document.addEventListener('input', e => {
        if(selectedElement) updateResizerPosition();
        if (e.target.closest('.content-wrapper')) handleInput(e);
    });

    document.addEventListener('keydown', function(e) {
        if ((e.key === 'Delete' || e.key === 'Backspace') && selectedElement) {
            e.preventDefault();
            if(selectedElement.tagName === 'IMG') {
                 const wrapper = selectedElement.closest('.img-wrapper');
                 if(wrapper) wrapper.remove();
                 else selectedElement.remove();
            } else {
                 selectedElement.remove();
            }
            deselectElement();
            paginateContent();
        }
        if (e.ctrlKey && e.key === 'p') {
            e.preventDefault();
            printDocument();
        }
    });

    document.addEventListener('selectionchange', () => {
        const s = window.getSelection();
        if (s.rangeCount > 0 && s.anchorNode && s.anchorNode.parentNode) {
            if (s.anchorNode.parentNode.closest('.content-wrapper')) {
                savedRange = s.getRangeAt(0);
            }
        }
    });

    window.onclick = function(e) {
        if (!e.target.matches('.menu-item') && !e.target.matches('.menu-item *')) {
            document.querySelectorAll('.dropdown-content').forEach(d => d.style.display = 'none');
        }
    }


// --- Keydown (จัดการปุ่มกดต่างๆ) ---
    document.addEventListener('keydown', function(e) {
        
        // 1. ปุ่ม Delete / Backspace (ลบรูป/ตาราง)
        if ((e.key === 'Delete' || e.key === 'Backspace') && selectedElement) {
            e.preventDefault();
            if(selectedElement.tagName === 'IMG') {
                 const wrapper = selectedElement.closest('.img-wrapper');
                 if(wrapper) wrapper.remove();
                 else selectedElement.remove();
            } else {
                 selectedElement.remove();
            }
            deselectElement();
            paginateContent();
            return;
        }

        // 2. ปุ่ม Ctrl + P (สั่งพิมพ์)
        if (e.ctrlKey && e.key === 'p') {
            e.preventDefault();
            printDocument();
            return;
        }

        // ============================================================
        // 3. ปุ่ม TAB (เดินหน้า/ถอยหลัง ในตาราง) <--- เพิ่มใหม่ตรงนี้ครับ
        // ============================================================
        // ============================================================
        // 3. ปุ่ม TAB (เดินหน้า/ถอยหลัง ในตาราง) - ฉบับแก้ไข
        // ============================================================
        if (e.key === 'Tab') {
	

	
            const sel = window.getSelection();
            if (!sel.rangeCount) return;

            // --- แก้จุดที่หาไม่เจอ ---
            let targetNode = sel.anchorNode;
            
            // ถ้าสิ่งที่เลือกคือ Text Node (ตัวหนังสือ) ให้ดูที่พ่อมัน
            // ถ้าสิ่งที่เลือกคือ Element Node (เช่น TD) ให้ดูที่ตัวมันเอง
            const startElement = (targetNode.nodeType === 3) ? targetNode.parentNode : targetNode;
            const currentCell = startElement.closest('td, th');

            if (currentCell) {
              //  alert("เจอช่องแล้วจ้า!"); // เทสดูได้
                e.preventDefault(); 

		

                const table = currentCell.closest('table');
                const allCells = Array.from(table.querySelectorAll('td, th'));
                const currentIndex = allCells.indexOf(currentCell);

                let nextCell = null;

                if (e.shiftKey) {
                    // --- Shift + Tab: ถอยหลัง ---
                    if (currentIndex > 0) {
                        nextCell = allCells[currentIndex - 1];
                    }
                } else {
                    // --- Tab ธรรมดา: เดินหน้า ---
                    if (currentIndex < allCells.length - 1) {
                        nextCell = allCells[currentIndex + 1];
                    } else {
                        // ช่องสุดท้าย -> เพิ่มแถวใหม่
                        tableModify('insertRowBelow');
                        // รอสร้างแถวเสร็จค่อยโฟกัส
                        setTimeout(() => {
                            const newCells = table.querySelectorAll('td, th');
                            // โฟกัสช่องแรกของแถวใหม่ (คือช่องถัดจากปัจจุบันนั่นเอง)
                            moveToCell(newCells[currentIndex + 1]); 
                        }, 50);
                        return; // จบงานตรงนี้สำหรับเคสเพิ่มแถว
                    }
                }

                // สั่งย้าย Cursor ไปช่องใหม่
                if (nextCell) {
                    moveToCell(nextCell);
                }
            }
        }
    });






    window.isSystemInitialized = true;
    // เรียกใช้ครั้งแรกเผื่อมีของเก่าอยู่
	//setTimeout(initDragEvents, 1000);
    console.log("System Ready (Typing + Selection Support).");
}


// ฟังก์ชันช่วยย้าย Cursor แบบล้างบาง (Clean Move)
function moveToCell(cell) {
    if (!cell) return;

    // 1. ล้างแถบสีที่เลือกค้างไว้ (สำคัญ! ไม่งั้นสีฟ้าจะค้างที่ช่องเดิม)
    if (typeof clearTableSelection === 'function') {
        clearTableSelection();
    }

    // 2. บอกระบบว่า "ตอนนี้ฉันอยู่ที่ช่องนี้แล้วนะ" 
    // (เวลาเทสี จะได้เทลงช่องใหม่ ไม่ใช่ช่องเก่า)
    activeTableCell = cell; 
    
    // 3. ใส่ Class ให้รู้ว่าช่องนี้ถูกเลือกอยู่ (เผื่ออยากให้ขึ้นกรอบสี)
    // cell.classList.add('selected-cell'); // (เปิดบรรทัดนี้ถ้าอยากให้ช่องที่ Tab ไปมีกรอบสีขึ้น)

    // 4. สั่งย้าย Cursor ตัวกระพริบ
    cell.focus();
    
    const range = document.createRange();
    range.selectNodeContents(cell); // เลือกพื้นที่ในช่อง
    range.collapse(true); // true = เอา Cursor ไว้หน้าสุด, false = ไว้หลังสุด
    
    const sel = window.getSelection();
    sel.removeAllRanges(); // ล้าง Cursor เก่าทิ้ง
    sel.addRange(range);   // วาง Cursor ใหม่
    
      debouncedSave(); 
}

async function saveDocument() {
    // ... (ส่วนที่ 1: ถามชื่อไฟล์ เหมือนเดิม) ...
    let fileName = prompt("ตั้งชื่อไฟล์เอกสารของคุณ:", "my_document");
    if (fileName === null) return;
    if (fileName.trim() === "") fileName = "untitled_document";
    if (!fileName.endsWith(".json")) fileName += ".json";


    // ---------------------------------------------------------
    //  ส่วนที่ 2: สร้าง Thumbnail (ถ่ายรูปหน้าแรก) - ฉบับปรับปรุง
    // ---------------------------------------------------------
    let thumbnailBase64 = "";

    try {
        const firstPage = document.querySelector('.document-page');
        if (firstPage) {
            console.log("Generating thumbnail...");

            // [แก้จุดที่ 1 และ 2] ปรับ option ของ html2canvas
            const fullCanvas = await html2canvas(firstPage, {
                scale: 2,            // <-- เพิ่มเป็น 2 เพื่อให้ภาพต้นฉบับคมชัดขึ้น (เหมือนจอ Retina)
                backgroundColor: '#ffffff', // <-- สำคัญมาก! แก้ปัญหาเงาดำทับ โดยบังคับให้พื้นหลังเป็นสีขาว
                useCORS: true,
                logging: false
            });

            // --- ขั้นตอนย่อรูป ---
            // ถ้าอยากให้รูปชัดขึ้นอีกนิด อาจจะเพิ่ม targetWidth เป็น 250 หรือ 300
            const targetWidth = 250;
            const scaleFactor = targetWidth / fullCanvas.width;
            const targetHeight = fullCanvas.height * scaleFactor;

            const thumbCanvas = document.createElement('canvas');
            thumbCanvas.width = targetWidth;
            thumbCanvas.height = targetHeight;

            const ctx = thumbCanvas.getContext('2d');
            // ใช้การวาดแบบคุณภาพสูงขึ้นเล็กน้อย (อาจช่วยได้ในบาง Browser)
            ctx.imageSmoothingEnabled = true;
            ctx.imageSmoothingQuality = 'high';

            ctx.drawImage(fullCanvas, 0, 0, targetWidth, targetHeight);

            // [แก้จุดที่ 2] เพิ่มคุณภาพ JPEG
            // เปลี่ยนจาก 0.6 เป็น 0.85 (คุณภาพ 85% - ชัดขึ้น ไฟล์ใหญ่ขึ้นนิดหน่อยแต่คุ้ม)
            thumbnailBase64 = thumbCanvas.toDataURL('image/jpeg', 0.85);

            console.log("Thumbnail created! Length:", thumbnailBase64.length);
        }
    } catch (err) {
        console.warn("Could not generate thumbnail:", err);
    }

    // ... (ส่วนที่ 3: แพ็ค JSON และบันทึกไฟล์ เหมือนเดิมทุกประการ) ...
    const pages = document.querySelectorAll('.document-page');
    const isLandscape = document.body.classList.contains('print-landscape');

    const docData = {
        pageSize: (typeof currentSizeClass !== 'undefined') ? currentSizeClass : 'A4',
        orientation: isLandscape ? 'landscape' : 'portrait',
        thumbnail: thumbnailBase64, 
        pages: []
    };

    pages.forEach((page, index) => {
        const wrapper = page.querySelector('.content-wrapper');
        let colCount = 1;
        if (wrapper.style.columnCount && wrapper.style.columnCount !== '') {
            colCount = parseInt(wrapper.style.columnCount);
        } else {
            const computed = getComputedStyle(wrapper).columnCount;
            colCount = (computed === 'auto') ? 1 : parseInt(computed);
        }
        docData.pages.push({
            pageNumber: index + 1,
            content: wrapper.innerHTML,
            columnCount: colCount
        });
    });

    const blob = new Blob([JSON.stringify(docData, null, 2)], { type: 'application/json' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = fileName;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
}

function triggerLoad() {
    document.getElementById('file-load-input').click();
}




// 2. ปรับปรุงฟังก์ชัน Load (รองรับทั้ง Browser ใหม่และเก่า + กันเหนียวเรื่อง HTTPS)
async function loadDocument() {
    try {
        // เช็คก่อนว่า Browser และ Server รองรับ showOpenFilePicker ไหม (ต้องเป็น HTTPS)
        if (window.showOpenFilePicker) {
            // --- แผน A: ใช้ File System Access API (Browser สมัยใหม่ + HTTPS) ---
            [fileHandle] = await window.showOpenFilePicker({
                types: [{ description: 'JSON Document', accept: { 'application/json': ['.json'] } }],
                multiple: false
            });

            const file = await fileHandle.getFile();
            const content = await file.text();
            reconstructDocument(JSON.parse(content));
            
            console.log("File loaded and handle saved.");
            
        } else {
            // --- แผน B: ท่าไม้ตายสำรอง (Fallback) ใช้ <input type="file"> ---
            // ทำงานได้ทุก Server แม้ไม่มี HTTPS และรองรับทุก Browser
            console.warn("showOpenFilePicker ไม่พร้อมใช้งาน กำลังสลับไปใช้โหมดพื้นฐาน...");
            
            const input = document.createElement('input');
            input.type = 'file';
            input.accept = 'application/json,.json';
            
            input.onchange = e => {
                const file = e.target.files[0];
                if (!file) return;
                
                const reader = new FileReader();
                reader.onload = function(event) {
                    try {
                        reconstructDocument(JSON.parse(event.target.result));
                        console.log("File loaded via fallback.");
                        
                        // รีเซ็ต fileHandle ทิ้งไป เพราะวิธีสำรองนี้จะเซฟทับไฟล์เดิมตรงๆ ไม่ได้
                        // (แต่ฟังก์ชัน saveDocumentOnly ที่เราเขียนกันไว้ มันจะเด้งไป Save As แทนให้อัตโนมัติอยู่แล้ว โคตรชิล)
                        fileHandle = null; 
                    } catch (err) {
                        alert('ไฟล์ JSON ไม่ถูกต้องครับผม!');
                    }
                };
                reader.readAsText(file);
            };
            
            // จำลองการคลิกเพื่อเปิดหน้าต่างเลือกไฟล์
            input.click();
        }
    } catch (err) {
        // กรณี User กดปุ่ม Cancel ปิดหน้าต่างไปเอง
        if (err.name !== 'AbortError') {
            console.error("User cancelled or error:", err);
        }
    }
}

// 3. ฟังก์ชัน Save ทับไฟล์เดิม (Save Document Only)
async function saveDocumentOnly() {
    if (!fileHandle) {
        // ถ้ายังไม่มีกุญแจไฟล์ (เช่น เปิดหน้าเว็บมาใหม่ๆ แล้วยังไม่ได้โหลดไฟล์เก่า)
        // ให้เด้งไปฟังก์ชัน Save As แทน
        console.log("No file handle found, switching to Save As...");
        saveDocument(); 
        return;
    }

    try {
        console.log("Saving to existing file...");
        
        // --- ส่วนการสร้าง Data (Copy มาจากฟังก์ชันเดิมของคุณ) ---
        let thumbnailBase64 = "";
        const firstPage = document.querySelector('.document-page');
        if (firstPage) {
            const fullCanvas = await html2canvas(firstPage, { scale: 1, backgroundColor: '#ffffff' });
            thumbnailBase64 = fullCanvas.toDataURL('image/jpeg', 0.7);
        }

        const pagesData = [];
        document.querySelectorAll('.document-page').forEach((page, index) => {
            const wrapper = page.querySelector('.content-wrapper');
            pagesData.push({
                pageNumber: index + 1,
                content: wrapper.innerHTML,
                columnCount: parseInt(wrapper.style.columnCount) || 1
            });
        });

        const docData = {
            pageSize: currentSizeClass,
            orientation: document.body.classList.contains('print-landscape') ? 'landscape' : 'portrait',
            thumbnail: thumbnailBase64,
            pages: pagesData
        };
        // ---------------------------------------------------

        // เริ่มการเขียนทับไฟล์เดิม
        const writable = await fileHandle.createWritable();
        await writable.write(JSON.stringify(docData, null, 2));
        await writable.close();
        
        alert("บันทึกการเปลี่ยนแปลงเรียบร้อยแล้ว!");
    } catch (err) {
        console.error("บันทึกไม่สำเร็จ:", err);
        alert("ไม่สามารถบันทึกทับไฟล์เดิมได้ เนื่องจากข้อจำกัดของ Browser");
    }
}
// ฟังก์ชันสำหรับโหลดเอกสารอัตโนมัติจาก URL หรือ Path ไฟล์ใน Server
async function loadDocumentFromURL(url,realData) {
    try {
        console.log(`Loading document from: ${url}`);
        
        // 1. ยิง Request ไปขอไฟล์ (เหมือนเวลา Backend ยิง API)
        const response = await fetch(url);

        // เช็คว่าไฟล์มีอยู่จริงไหม (HTTP 200 OK)
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // 2. แปลงเป็น JSON (เหมือน ObjectMapper ใน Java)
        const jsonData = await response.json();

        // 3. ส่งเข้าฟังก์ชันสร้างเอกสารตัวเดิมของคุณ
        reconstructDocument(jsonData);
		run(realData);
        console.log("Auto-load successful!");

    } catch (err) {
        console.error("Failed to load document:", err);
        alert('ไม่สามารถโหลดไฟล์อัตโนมัติได้: ' + err.message);
    }
}

async function loadSamplePreviewURL(url) {
    try {
        console.log(`Loading document from: ${url}`);
        
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const jsonData = await response.json();


        // 2. [NEW] อัปเดตค่าที่หน้าจอ (Input fields)
        updateUISettings(jsonData);



        console.log("Auto-load & UI Update successful!");

    } catch (err) {
        console.error("Failed to load document:", err);
        alert('ไม่สามารถโหลดไฟล์อัตโนมัติได้: ' + err.message);
    }
}

// ฟังก์ชันอัปเดตค่าใน Input
function updateUISettings(data) {
    // สมมติโครงสร้าง JSON ของคุณเป็นประมาณนี้:
    // { settings: { width: "21cm", height: "29.7cm" }, pages: [...] }
    
    const w = parseFloat(21);
    const h = parseFloat(29);
    const unit = "cm"; // หรือดึงจาก data ถ้ามี

    // เช็คแนวนอน/แนวตั้ง
    const isLandscape = w > h;

    // หยอดค่าใส่ Input ตาม ID
    document.getElementById('paper-width-disp').value = w + " " + unit;
    document.getElementById('paper-height-disp').value = h + " " + unit;
    document.getElementById('paper-orient-disp').value = data.orientation;
    
    // นับจำนวนหน้า
    const totalPages = data.pages ? data.pages.length : 0;
    document.getElementById('paper-total-disp').value = totalPages;
    
      const imgPreview = document.getElementById('my-preview-img'); // สร้าง img tag รอไว้
    if (data.thumbnail) {
        imgPreview.src = data.thumbnail;
        imgPreview.style.display = 'block';
    } else {
        imgPreview.style.display = 'none';
    }
}






function reconstructDocument(data) {
    const docArea = document.getElementById('document-area');
    const resizer = document.getElementById('resizer-frame');

    // 1. ลบหน้าเก่าทิ้งให้หมด
    docArea.querySelectorAll('.document-page').forEach(p => p.remove());

    // ใส่ resizer กลับเข้าไป (เผื่อโดนลบ)
    if (resizer && resizer.parentNode !== docArea) docArea.appendChild(resizer);

    // 2. คืนค่าขนาดกระดาษ
    currentSizeClass = data.pageSize || 'page-size-A4';

    // 3. *** คืนค่าแนวตั้ง/แนวนอน (สำคัญ!) ***
    const orientation = data.orientation || 'portrait'; // ถ้าไม่มีในไฟล์ ให้เป็นแนวตั้ง
    
    // เรียกใช้ฟังก์ชัน setPageOrientation เพื่อปรับ Body และ Style ให้พร้อม
    // (แต่เรายังไม่มีหน้ากระดาษ มันจะปรับแค่ Body รอไว้ก่อน)
    setPageOrientation(orientation); 

    // 4. สร้างหน้าใหม่และยัดเนื้อหา
    data.pages.forEach(pData => {
        // createNewPage จะไปเช็ค Body แล้วสร้างหน้าแนวนอน/ตั้ง ให้ถูกต้องเอง
        const newPage = createNewPage(); 
        const wrapper = newPage.querySelector('.content-wrapper');
        
        wrapper.innerHTML = pData.content;
        
        if (pData.columnCount && pData.columnCount > 1) {
            wrapper.style.columnCount = pData.columnCount;
        }
    });

    // 5. จัดหน้าใหม่ปิดท้าย
    setTimeout(() => {
        // เรียกซ้ำอีกรอบเพื่อความชัวร์ (เผื่อ createNewPage สร้างมาผิด)
        setPageOrientation(orientation); 
        paginateContent();
		applyZoom();
    }, 150);
	makeTablesResizable();
}




function paginateContent() {
    let ts = selectedElement;
    if (ts) deselectElement();

    let pages = Array.from(document.querySelectorAll('.document-page'));

    for (let i = 0; i < pages.length; i++) {
        const w = pages[i].querySelector('.content-wrapper');

        // =========================================================
        // [FIX] ส่วนแก้ปัญหา Wrapper (แกะเปลือกที่ทำให้ค้าง)
        // =========================================================
        if (w.children.length === 1 && w.firstElementChild.tagName === 'DIV') {
            const wrapper = w.firstElementChild;
            const isImgWrapper = wrapper.classList.contains('img-wrapper');
            const hasTextAlign = wrapper.style.textAlign !== '';
            const isNoUnwrap = wrapper.classList.contains('no-unwrap');

            if (!isImgWrapper && !hasTextAlign && !isNoUnwrap) { 
                while (wrapper.firstChild) {
                    w.appendChild(wrapper.firstChild);
                }
                wrapper.remove();
            }
        }

        if (w.scrollTop > 0) w.scrollTop = 0;
        const colCount = parseInt(w.style.columnCount || 1);

        // =========================================================
        // 🌟 NEW: ระบบ PULL UP (ดึงเนื้อหาจากหน้าถัดไปขึ้นมาโปะหน้าปัจจุบัน)
        // =========================================================
        let nextPage = pages[i + 1];
        if (nextPage) {
            let nextW = nextPage.querySelector('.content-wrapper');
            
            // ตราบใดที่หน้าปัจจุบันยังไม่ล้น และหน้าถัดไปยังมีเนื้อหา ให้ดึงขึ้นมา!
            while ((colCount > 1 ? w.scrollWidth <= w.clientWidth + 5 : w.scrollHeight <= w.clientHeight + 1) && nextW.firstChild) {
                
                let firstEl = nextW.firstChild;

                // [ทีเด็ด] ถ้าดึง "ตาราง" ขึ้นมาชนกับ "ตาราง" หน้าแรก ให้หลอมรวมกันซะ (Merge Tables)
                if (firstEl.tagName === 'TABLE' && w.lastChild && w.lastChild.tagName === 'TABLE') {
                    let targetBody = w.lastChild.querySelector('tbody') || w.lastChild;
                    let sourceRows = Array.from(firstEl.rows);
                    
                    // ข้ามแถว Thead ถ้ารวมตาราง
                    sourceRows.forEach(r => {
                        if (r.parentNode.tagName !== 'THEAD') {
                            targetBody.appendChild(r);
                        }
                    });
                    firstEl.remove(); // ลบซากตารางที่ดึงขึ้นมาหมดแล้ว
                } else {
                    w.appendChild(firstEl); // ถ้าเป็นข้อความปกติก็ดึงมาต่อท้ายเลย
                }

                // ถ้าดึงขึ้นมาแล้วดันทำให้หน้าแรกล้นพอดี ให้หยุดดึง (เดี๋ยว Push down จะจัดการดันกลับไปเอง)
                if (colCount > 1 ? w.scrollWidth > w.clientWidth + 5 : w.scrollHeight > w.clientHeight + 1) {
                    break;
                }
            }

            // ถ้าดึงจนหน้าถัดไปว่างเปล่าแล้ว ให้ลบหน้าถัดไปทิ้งเลย!
            if (nextW.children.length === 0 && nextW.textContent.trim() === '') {
                nextPage.remove();
                pages = Array.from(document.querySelectorAll('.document-page')); // อัปเดต Array หน้าใหม่
            }
        }

        // =========================================================
        // ระบบ PUSH DOWN (ผลักของล้นไปหน้าถัดไป - ของเดิมของคุณ)
        // =========================================================
        let safe = 0;
        let lastMoved = null; // ป้องกันการย้ายของซ้ำไปซ้ำมา

        while (safe < 200) {
            let isOverflow = false;
            if (colCount > 1) {
                if (w.scrollWidth > w.clientWidth + 5) isOverflow = true;
            } else {
                if (w.scrollHeight > w.clientHeight + 1) isOverflow = true;
            }

            if (!isOverflow) break;

            // 🚨 ANTI-FREEZE (เบรกเกอร์กันลูปนรก): 
            // ถ้าในหน้านั้นมีแค่ชิ้นเดียวแต่มันยังล้น (เช่น รูปใหญ่จัด หรือตารางยาวติดกันที่ไม่ยอมขาด) 
            // ให้ "ยอมล้น" ห้ามเตะไปหน้าถัดไปเด็ดขาด ไม่งั้นค้าง!
			// 🛑 ANTI-FREEZE: เบรกเกอร์กันลูปนรก! (ดุขึ้นกว่าเดิม) 🛑
            let validNodes = Array.from(w.childNodes).filter(n => n.nodeType === 1 || (n.nodeType === 3 && n.nodeValue.trim() !== ''));
            
            // ถ้าหน้ากระดาษนี้ มีตารางนี้เป็นสิ่งเดียวในหน้าแล้ว
            if (validNodes.length <= 1) {
                if (l.nodeName === 'TABLE') {
                    // ถ้านับแล้วตารางนี้เหลือแค่ 1 แถว (หรือ 2 แถวถ้ารวม Thead) แล้วมันยังล้น A4
                    // แปลว่าแถวนี้อ้วนเกินกระดาษ JS สับไม่ได้แล้ว -> "ให้หยุดลูปทันที ห้ามผลักไปหน้าถัดไปเด็ดขาด!"
                    let dataRows = Array.from(l.rows).filter(r => r.parentNode.tagName !== 'THEAD');
                    if (dataRows.length <= 1) {
                        console.warn("เจอแถวตารางที่ใหญ่เกินหน้ากระดาษ! ยอมให้ล้นเพื่อป้องกันเว็บค้าง");
                        break; // 💥 ทุบเบรกกะทันหัน! ออกจากลูปการจัดหน้าเลย
                    }
                } else {
                    break; // ถ้าเป็นรูปภาพ หรืออย่างอื่นที่ชิ้นใหญ่เกิน A4 ก็ยอมให้ล้นไปเลย
                }
            }

            // -----------------------------------------------------------
            // [LOGIC ตาราง] (ผลักตารางลงทีละแถว เหมือนเดิมของคุณ)
            // -----------------------------------------------------------
            if (l.nodeName === 'TABLE' && l.rows.length > 0) {
                if (!l.id) l.id = "split-tbl-" + Date.now() + "-" + Math.random().toString(36).substr(2, 5);
                const currentTableID = l.id;

                let lastRow = l.rows[l.rows.length - 1];
                let firstRow = l.rows[0];

                if (l.rows.length === 1 && firstRow.querySelector('th')) {
                    l.remove();
                    safe++; 
                    continue;
                }

                if (lastRow.parentNode.nodeName === 'THEAD') {
                    l.remove();
                    safe++; 
                    continue;
                }

                let targetTable = nextWrapper.firstElementChild;
                let isSameTable = targetTable && targetTable.nodeName === 'TABLE' && targetTable.getAttribute('data-split-from') === currentTableID;

                if (!targetTable || !isSameTable) {
                    targetTable = l.cloneNode(false);
                    targetTable.id = ""; 
                    targetTable.setAttribute('data-split-from', currentTableID); 

                    let headerFound = false;
                    if (l.tHead) {
                        targetTable.appendChild(l.tHead.cloneNode(true));
                        headerFound = true;
                    }

                    if (!headerFound && firstRow && firstRow.querySelector('th')) {
                        let thead = document.createElement('thead');
                        thead.appendChild(firstRow.cloneNode(true));
                        targetTable.appendChild(thead);
                    }

                    let tbody = document.createElement('tbody');
                    targetTable.appendChild(tbody);
                    nextWrapper.prepend(targetTable);
                }

                let targetContainer = targetTable.tBodies[0] || targetTable;

                if (targetContainer.firstChild) {
                    targetContainer.insertBefore(lastRow, targetContainer.firstChild);
                } else {
                    targetContainer.appendChild(lastRow);
                }

                if (l.rows.length === 0) l.remove();
                
                lastMoved = lastRow; // บันทึกว่าเพิ่งผลักแถวนี้ไป
                safe++;
                continue;
            }
            // -----------------------------------------------------------

            nextWrapper.prepend(l);
            lastMoved = l; // บันทึกว่าเพิ่งผลักชิ้นนี้ไป
            safe++;
        }
    }

    updateStatus();
    if (ts && document.body.contains(ts)) selectElement(ts);
}

function paginateContent2() {
    let ts = selectedElement;
    if (ts) deselectElement();

    let pages = Array.from(document.querySelectorAll('.document-page'));

    for (let i = 0; i < pages.length; i++) {
        const w = pages[i].querySelector('.content-wrapper');
// =========================================================
        // [NEW FIX] ส่วนแก้ปัญหา Wrapper (แกะเปลือกที่ทำให้ค้าง)
        // =========================================================
        if (w.children.length === 1 && w.firstElementChild.tagName === 'DIV') {
            const wrapper = w.firstElementChild;
            
            // เพิ่มการตรวจสอบ: ห้ามแกะเปลือกถ้ามันเป็น img-wrapper 
            // หรือถ้ามันมี style การจัดหน้า (textAlign) อยู่!
            const isImgWrapper = wrapper.classList.contains('img-wrapper');
            const hasTextAlign = wrapper.style.textAlign !== '';
            const isNoUnwrap = wrapper.classList.contains('no-unwrap');

            if (!isImgWrapper && !hasTextAlign && !isNoUnwrap) { 
                while (wrapper.firstChild) {
                    w.appendChild(wrapper.firstChild);
                }
                wrapper.remove();
            }
        }
        // =========================================================

        if (w.scrollTop > 0) w.scrollTop = 0;
        const colCount = parseInt(w.style.columnCount || 1);

        let safe = 0;
        while (safe < 200) {
            let isOverflow = false;
            if (colCount > 1) {
                if (w.scrollWidth > w.clientWidth + 5) isOverflow = true;
            } else {
                if (w.scrollHeight > w.clientHeight + 1) isOverflow = true;
            }

            if (!isOverflow) break;

            let l = w.lastChild;
            if (!l) break;

            if (l.nodeType === 3 && l.nodeValue.trim() === '') {
                l.remove();
                safe++; 
                continue;
            }

            let np = pages[i + 1];
            if (!np) {
                np = createNewPage();
                pages.push(np);
            }
            np.querySelector('.content-wrapper').style.columnCount = w.style.columnCount;
            let nextWrapper = np.querySelector('.content-wrapper');

            // -----------------------------------------------------------
            // [LOGIC ตาราง]
            // -----------------------------------------------------------
            if (l.nodeName === 'TABLE' && l.rows.length > 0) {
                if (!l.id) l.id = "split-tbl-" + Date.now() + "-" + Math.random().toString(36).substr(2, 5);
                const currentTableID = l.id;

                let lastRow = l.rows[l.rows.length - 1];
                let firstRow = l.rows[0];

                if (l.rows.length === 1 && firstRow.querySelector('th')) {
                    l.remove();
                    safe++; 
                    continue;
                }

                if (lastRow.parentNode.nodeName === 'THEAD') {
                    l.remove();
                    safe++; 
                    continue;
                }

                let targetTable = nextWrapper.firstElementChild;
                
                let isSameTable = targetTable && 
                                  targetTable.nodeName === 'TABLE' && 
                                  targetTable.getAttribute('data-split-from') === currentTableID;

                if (!targetTable || !isSameTable) {
                    targetTable = l.cloneNode(false);
                    targetTable.id = ""; 
                    targetTable.setAttribute('data-split-from', currentTableID); 

                    let headerFound = false;
                    if (l.tHead) {
                        targetTable.appendChild(l.tHead.cloneNode(true));
                        headerFound = true;
                    }

                    if (!headerFound && firstRow && firstRow.querySelector('th')) {
                        let thead = document.createElement('thead');
                        thead.appendChild(firstRow.cloneNode(true));
                        targetTable.appendChild(thead);
                    }

                    let tbody = document.createElement('tbody');
                    targetTable.appendChild(tbody);

                    nextWrapper.prepend(targetTable);
                }

                let targetContainer = targetTable.tBodies[0] || targetTable;

                if (targetContainer.firstChild) {
                    targetContainer.insertBefore(lastRow, targetContainer.firstChild);
                } else {
                    targetContainer.appendChild(lastRow);
                }

                if (l.rows.length === 0) {
                    l.remove();
                }

                safe++;
                continue;
            }
            // -----------------------------------------------------------

            nextWrapper.prepend(l);
            safe++;
        }
    }

    updateStatus();
    if (ts && document.body.contains(ts)) selectElement(ts);
}



function createNewPage() {
    const docArea = document.getElementById('document-area');
    const newPageNum = document.querySelectorAll('.document-page').length + 1;
    
    // 1. สร้างโครงกระดาษ
    const p = document.createElement('div');
    p.className = 'document-page'; // เริ่มต้นเป็นแนวตั้ง (Default)
    p.id = 'page-' + newPageNum;
    
    const w = document.createElement('div');
    w.className = 'content-wrapper';
    
    // ---------------------------------------------------------
    // *** จุดสำคัญที่สุด: เช็คพันธุกรรมจาก Body ***
    // ---------------------------------------------------------
    const isLandscape = document.body.classList.contains('print-landscape');
    
    if (isLandscape) {
        p.classList.add('landscape'); // สั่งกระดาษให้นอน
        w.classList.add('landscape'); // สั่งเนื้อหาให้นอน
    }
    // ---------------------------------------------------------

    w.contentEditable = true;
    w.spellcheck = false;
    w.addEventListener('input', handleInput);
    
    // เลขหน้า
    const wm = document.createElement('div');
    wm.className = 'page-number-watermark';
    wm.innerText = newPageNum;
    
    p.appendChild(w);
    p.appendChild(wm);
    docArea.appendChild(p);
    
    return p;
}
function setPaperSize(s) {
    currentSizeClass = 'page-size-' + s;
    document.querySelectorAll('.document-page').forEach(p => p.className = `document-page `+currentSizeClass);
    setTimeout(paginateContent, 100);
}

function insertColumnBreak() {
    const breakHTML = '<div class="col-break-marker"></div><br>';
    document.execCommand('insertHTML', false, breakHTML);
    restoreFocus();
    setTimeout(paginateContent, 100);
      debouncedSave(); 
}

function setColumns(c) {
    let w = null;
    if (window.getSelection().rangeCount > 0) {
        const anchor = window.getSelection().anchorNode;
        w = (anchor.nodeType === 3 ? anchor.parentNode : anchor).closest('.content-wrapper');
    }
    if (!w) w = document.querySelector('.content-wrapper');
    if (w) {
        w.style.columnCount = c;
        setTimeout(paginateContent, 100);
    }
      debouncedSave(); 
}

let pt;
function handleInput(e) {
    if (e.target.scrollTop > 0) e.target.scrollTop = 0;
    clearTimeout(pt);
    pt = setTimeout(paginateContent, 100);
    updateResizerPosition();
    
    debouncedSave(); // <--- เพิ่มบรรทัดนี้
}


// =========================================================
//  SECTION 5: FORMATTING & COLORS
// =========================================================
function formatDoc(c, v) {
    const selectedCells = document.querySelectorAll('.selected-cell');

    // ถ้ากำลังเลือก "ช่องตาราง" อยู่ ให้จัดการที่ td โดยตรง
    if (selectedCells.length > 0) {
        selectedCells.forEach(cell => {
            if (c === 'bold') {
                cell.style.fontWeight = (cell.style.fontWeight === 'bold' || cell.style.fontWeight === '700') ? 'normal' : 'bold';
            } else if (c === 'italic') {
                cell.style.fontStyle = (cell.style.fontStyle === 'italic') ? 'normal' : 'italic';
            } else if (c === 'underline') {
                cell.style.textDecoration = (cell.style.textDecoration === 'underline') ? 'none' : 'underline';
            } else if (c === 'fontName') { 
                // 🌟 เพิ่มตรงนี้: ดักคำสั่งเปลี่ยนฟอนต์ แล้วยัดชื่อฟอนต์ (v) ลงไป
                cell.style.fontFamily = v;
            }
            
            // เรียกเครื่องซักผ้ามาปั่นล้าง Tag ขยะข้างในทิ้ง
            cleanAndFlattenCell(cell);
        });
        
        restoreFocus();
        if (typeof debouncedSave === "function") debouncedSave();
        return; // จบงาน ไม่ต้องให้ Browser ไปสร้าง <b>, <i> ให้มั่ว
    }

    // ถ้าเป็นการไฮไลท์ข้อความปกติ (นอกตาราง) ให้ใช้แบบเดิม
    document.execCommand(c, false, v);
    restoreFocus();
    if (typeof debouncedSave === "function") debouncedSave();
}




function alignContent(alignType) { // alignType = 'left', 'center', 'right'
    
    // 1. กรณีรูปภาพ (IMG)
    if (selectedElement && selectedElement.tagName === 'IMG') {
        const wrapper = selectedElement.closest('.img-wrapper');
        if (wrapper) {
            wrapper.style.textAlign = alignType;
            wrapper.style.display = 'block'; 
            updateResizerPosition();
            restoreFocus();
            if (typeof debouncedSave === 'function') debouncedSave();
            return;
        }
    } 
    // 2. กรณีตัวตาราง (TABLE) ดึงให้ตารางไปซ้าย/กลาง/ขวา ทั้งก้อน
    else if (selectedElement && selectedElement.tagName === 'TABLE') {
        const table = selectedElement;
        table.style.display = 'table';
        
        if (alignType === 'center') {
            table.style.marginLeft = 'auto';
            table.style.marginRight = 'auto';
        } else if (alignType === 'right') {
            table.style.marginLeft = 'auto';
            table.style.marginRight = '0';
        } else { // left
            table.style.marginLeft = '0';
            table.style.marginRight = 'auto';
        }
        updateResizerPosition();
        restoreFocus();
        if (typeof debouncedSave === 'function') debouncedSave();
        return;
    } 

    // 🌟 3. กรณีช่องตาราง (จัดข้อความข้างใน TD/TH)
    // ใช้ getSelectedCells() เพื่อให้ทำงานได้ทั้งตอน "ลากคลุมหลายช่อง" และ "คลิกช่องเดียว"
    const cells = typeof getSelectedCells === 'function' ? getSelectedCells() : [];
    
    if (cells.length > 0) {
        cells.forEach(cell => {
            // 3.1 สั่งจัดหน้าผ่าน style ของ td ตรงๆ (ไม่พึ่ง Browser)
            cell.style.textAlign = alignType;
            
            // 3.2 กวาดล้าง div ขยะที่ Browser อาจจะแอบสร้างไว้ก่อนหน้านี้ (ถอดเปลือก div ออก)
            const innerDivs = cell.querySelectorAll('div');
            innerDivs.forEach(div => {
                // ข้ามแกนปรับขนาดตาราง ห้ามลบเด็ดขาด
                if (!div.classList.contains('col-resizer')) {
                    // ดึงไส้ในออกมาแปะข้างนอก แล้วเตะ div ทิ้ง
                    while(div.firstChild) {
                        div.parentNode.insertBefore(div.firstChild, div);
                    }
                    div.remove();
                }
            });
            
            // 3.3 ถ้าใช้เครื่องซักผ้าด้วย จะช่วยล้าง tag อื่นๆ ที่ซ่อนอยู่ได้อีกทาง
            if (typeof cleanAndFlattenCell === 'function') {
                cleanAndFlattenCell(cell);
            }
        });
        
        restoreFocus();
        if (typeof debouncedSave === 'function') debouncedSave();
        return; // จบการทำงานตรงนี้เลย จะได้ไม่หลุดไปทำงานข้อ 4
    }

    // 4. กรณีข้อความปกติ (พิมพ์อยู่นอกตาราง)
    const cmd = 'justify' + alignType.charAt(0).toUpperCase() + alignType.slice(1);
    document.execCommand(cmd, false, null);
    
    restoreFocus();
    if (typeof debouncedSave === 'function') debouncedSave(); 
}


function verticalAlignContent(valign) { 
    // valign รับค่าเป็น 'top', 'middle', 'bottom'

    // 1. ดึงช่องตารางที่เรากำลังเลือกอยู่ 
    const cells = getSelectedCells();
    
    // 2. ถ้ามีการเลือกช่องตาราง (ลากคลุม)
    if (cells.length > 0) {
        cells.forEach(cell => {
            // 🌟 ย้ำหัวตะปู: บังคับพฤติกรรมให้เป็นช่องตารางเสมอ (ป้องกัน CSS อื่นมากวน)
            cell.style.display = 'table-cell';
            cell.style.verticalAlign = valign;
        });
        
        restoreFocus();
        if (typeof debouncedSave === 'function') debouncedSave();
    } 
    // 3. ถ้าไม่ได้ลากคลุม แต่เคอร์เซอร์กะพริบอยู่ในช่องเฉยๆ
    else if (activeTableCell) {
        activeTableCell.style.display = 'table-cell';
        activeTableCell.style.verticalAlign = valign;
        
        restoreFocus();
        if (typeof debouncedSave === 'function') debouncedSave();
    }
}










function scanAndFixTags() {
    const area = document.getElementById('document-area');
    if (!area) return;

    let rawHTML = area.innerHTML;

    // Regex ตัวนี้จะหา "[#" ตามด้วยอะไรก็ได้ที่ไม่ใช่ "]" แล้วปิดท้ายด้วย "]"
    // ซึ่งมันจะกวาดคลุมถึง </div> หรือ </span> ที่มาคั่นกลางด้วย
    let cleanedHTML = rawHTML.replace(/\[#[^\]]*\]/g, (match) => {
        
        // 1. ลบ HTML Tag ทุกชนิดที่อยู่ระหว่าง [# กับ ] ออกให้หมด
        let pureTag = match.replace(/<[^>]*>/g, "");
        
        // 2. เคลียร์ &nbsp; หรือช่องว่าง (เผื่อเผลอกด Spacebar เข้าไปในป้าย)
        pureTag = pureTag.replace(/&nbsp;/g, "").replace(/\s+/g, "");
        
        // Console log ให้ดูว่ามันซ่อมอะไรไปบ้าง (เอาออกได้ตอนใช้งานจริง)
        console.log(`🔧 ซ่อมแซม Tag: ${match} \n➡️ กลายเป็น: ${pureTag}`);
        
        return pureTag;
    });

    // อัปเดต HTML ที่ซ่อมแล้วกลับคืนกระดาน
    if (rawHTML !== cleanedHTML) {
        area.innerHTML = cleanedHTML;
        
        // ดึง Focus และอัปเดตหน้ากระดาษ
        if (typeof restoreFocus === 'function') restoreFocus();
        if (typeof paginateContent === 'function') paginateContent();
        if (typeof debouncedSave === 'function') debouncedSave();
    }
    
    // แจ้งเตือนผู้ใช้ (ใส่หรือไม่ใส่ก็ได้)
    // alert("สแกนและซ่อมแซม Tag สำเร็จ!");
}

function clearCellTags() {
    const cells = getSelectedCells();
    if (cells.length === 0) return;

    cells.forEach(cell => {
        // 1. ดึงข้อความเพียวๆ ออกมา (ละทิ้ง tag div, span, p ทั้งหมด)
        let plainText = cell.innerText; 
        
        // 2. ถ้ามีการขึ้นบรรทัดใหม่ ให้เปลี่ยนเป็น <br> เพื่อให้โครงสร้างยังดูเป็นบรรทัด
        let cleanHTML = plainText.replace(/\n/g, '<br>');

        // 3. ถ้าล้างแล้วว่างเปล่าเลย ให้ใส่ &nbsp; กันช่องตารางยุบ
        if (cleanHTML.trim() === '') {
            cleanHTML = '&nbsp;';
        }

        // 4. สั่งแทนที่ของเก่าทั้งหมด
        cell.innerHTML = cleanHTML;
        
        // (แถม) ล้าง inline-style ของช่องนั้นด้วย เพื่อความสะอาดหมดจด
        // ถ้าไม่อยากให้มันลบเส้นขอบตารางด้วย ให้เอา 4 บรรทัดล่างนี้ออกครับ
        // cell.style.backgroundColor = '';
        // cell.style.color = '';
        // cell.style.fontSize = '';
        // cell.style.fontFamily = '';
         debouncedSave(); 
    });

    restoreFocus();
    if (typeof paginateContent === 'function') setTimeout(paginateContent, 100);
}




function updateAndApplyColor(t, v) {
    if (t === 'bgcolor') currentFillColor = v;
    if (t === 'fontcolor') currentFontColor = v;
    if (t === 'hilite') currentHiliteColor = v;
    
    if (t === 'hilite') document.execCommand('hiliteColor', false, v);
    else applyToSelection(t, v);
}

function applyCurrentColor(t) {
    // 1. ดึง Focus กลับมาที่งานก่อน (สำคัญมาก)
    restoreFocus();



    // 2. ดึงค่าสีจากกล่องตามประเภทที่ส่งมา
    let colorVal = '';
    if (t === 'bgcolor') colorVal = document.getElementById('bg-color-picker').value;
    if (t === 'fontcolor') colorVal = document.getElementById('hilite-color-picker').value;
    if (t === 'hilite') colorVal = document.getElementById('hilite-color-picker').value;

    // 3. ตรวจสอบว่ากำลังเลือก "เซลล์ตาราง" อยู่หรือไม่?
    const selectedCells = document.querySelectorAll('.selected-cell');

    if (selectedCells.length > 0) {
        // ==========================================
        // CASE A: เปลี่ยนสีใน "ตาราง" (ใช้ CSS Style)
        // ==========================================
        selectedCells.forEach(cell => {
            if (t === 'bgcolor' || t === 'hilite') {
                // ถ้าเป็น bgcolor หรือ hilite ในตาราง คือการเทสีพื้นหลังช่อง
                cell.style.backgroundColor = colorVal;
            } 
            else if (t === 'fontcolor') {
                // ถ้าเป็น fontcolor คือเปลี่ยนสีตัวอักษร
                cell.style.color = colorVal;
                
                // บังคับเปลี่ยนสีลูกๆ ข้างในด้วย (เผื่อมี span ซ้อนอยู่)
                cell.querySelectorAll('*').forEach(child => {
                    child.style.color = colorVal;
                });
            }
        });
    } else {
        // ==========================================
        // CASE B: เปลี่ยนสี "ข้อความปกติ" (ใช้ execCommand)
        // ==========================================
        if (t === 'fontcolor') {
            // *** จุดที่แก้: สีตัวอักษรต้องใช้ 'foreColor' ***
            document.execCommand('foreColor', false, colorVal);
        } 
        else if (t === 'hilite') {
            // สีไฮไลท์พื้นหลังข้อความใช้ 'hiliteColor'
            document.execCommand('hiliteColor', false, colorVal);
        }
        // หมายเหตุ: 'bgcolor' ปกติไม่ใช้กับข้อความเพียวๆ มักใช้กับตาราง
    }
      debouncedSave(); 
}
function applyToSelection(t, v) {
    const c = getSelectedCells();
    c.forEach(cell => {
        if (t === 'bgcolor') cell.style.backgroundColor = v;
        if (t === 'fontcolor') {
            cell.style.color = v;
            if (cell.innerText.trim() === '') cell.innerHTML = `<span style="color:${v}">&#8203;</span>`;
        }
    });
    clearTableSelection();
    restoreFocus();
      debouncedSave(); 
}



// ตัวแปรเก็บค่าเริ่มต้น
let currentBorderWidth = '1px';
let currentBorderStyle = 'solid';

function applyBorder(side) {
    restoreFocus();

    const colorPicker = document.getElementById('border-color-picker');
    const color = colorPicker ? colorPicker.value : '#000000';
    
    // สร้างค่า Border
    // ถ้าผู้ใช้เลือก side === 'none' หรือความกว้างเป็น 0px ให้ใช้ 'none'
    const isNone = (side === 'none' || currentBorderWidth === '0px');
    const borderVal = isNone ? 'none' : `${currentBorderWidth} ${currentBorderStyle} ${color}`;

    const cells = getSelectedCells();

    if (cells.length > 0) {
        cells.forEach(cell => {
            if (side === 'all' || side === 'none') {
                cell.style.border = borderVal;
            } else if (side === 'top') {
                cell.style.borderTop = borderVal;
            } else if (side === 'bottom') {
                cell.style.borderBottom = borderVal;
            } else if (side === 'left') {
                cell.style.borderLeft = borderVal;
            } else if (side === 'right') {
                cell.style.borderRight = borderVal;
            }

            // [Trick] ถ้าลบเส้นออก (none) อาจใส่ class 'grid-guide' 
            // เพื่อให้ CSS แสดงเส้นประจางๆ เฉพาะหน้าจอแก้ไข
            if (isNone) {
                cell.classList.add('no-border-guide');
            } else {
                cell.classList.remove('no-border-guide');
            }
        });
    }
    
    clearTableSelection();
      debouncedSave(); // <--- เพิ่มบรรทัดนี้
}

// ฟังก์ชันสำหรับ Dropdown เปลี่ยนขนาด
function setBorderWidth(val) {
    currentBorderWidth = val;
      debouncedSave(); 
}

// ฟังก์ชันสำหรับ Dropdown เปลี่ยนรูปแบบ
function setBorderStyle(val) {
    currentBorderStyle = val;
      debouncedSave(); 
}



function restoreFocus() {
    const s = window.getSelection();
    if (!s.rangeCount && savedRange) {
        s.removeAllRanges();
        s.addRange(savedRange);
    } else if (!savedRange) {
        const w = document.querySelector('.content-wrapper');
        if (w) w.focus();
    }
}


// =========================================================
//  SECTION 6: TABLE MANAGEMENT
// =========================================================
function insertTable() {
    const c = document.getElementById('table-cols').value;
    const r = document.getElementById('table-rows').value;
    let h = '<table style="width:100%; border-collapse:collapse; margin-bottom:10px;">';
    for (let i = 0; i < r; i++) {
        h += '<tr>';
        for (let j = 0; j < c; j++) h += '<td style="border:1px solid #000;padding:5px;">&nbsp;</td>';
        h += '</tr>';
    }
    h += '</table><p><br></p>';
    closeTableModal();
    document.execCommand('insertHTML', false, h);
    setTimeout(paginateContent, 100);
	makeTablesResizable();
      debouncedSave(); 
}

function clearTableSelection() {
    document.querySelectorAll('.selected-cell').forEach(t => t.classList.remove('selected-cell'));
      debouncedSave(); 
}

function selectCells(s, e) {
    clearTableSelection();
    const t = s.closest('table');
    if (!t) return;
    
    // เช็คว่าอยู่ในตารางเดียวกันไหม
    if (e.closest('table') !== t) return;

    const r = Array.from(t.rows);
    const r1 = s.parentElement.rowIndex, c1 = s.cellIndex;
    const r2 = e.parentElement.rowIndex, c2 = e.cellIndex;

    for (let i = Math.min(r1, r2); i <= Math.max(r1, r2); i++) {
        for (let j = Math.min(c1, c2); j <= Math.max(c1, c2); j++) {
            if (r[i] && r[i].cells[j]) r[i].cells[j].classList.add('selected-cell');
        }
    }
}

function getSelectedCells() {
    const s = document.querySelectorAll('.selected-cell');
    return s.length === 0 && activeTableCell ? [activeTableCell] : Array.from(s);
}
function tableModify(a) {
    if (!activeTableCell) return;
    const r = activeTableCell.parentElement;
    const t = r.parentElement.closest('table');
    const ri = r.rowIndex;
    const ci = activeTableCell.cellIndex;

    if (a === 'insertRowBelow') {
        const nr = t.insertRow(ri + 1);
        // 🌟 1. โคลนนิ่งรูปแบบจากแถวที่เรากดเลือกอยู่เป๊ะๆ
        Array.from(r.cells).forEach(cell => {
            // cloneNode(false) คือก๊อปปี้มาแค่เปลือก (Tag + Style + Colspan) ไม่เอาข้อความข้างใน
            let newCell = cell.cloneNode(false); 
            newCell.innerHTML = '&nbsp;';       // ใส่ความว่างเปล่าที่ถูกต้องลงไป
            
            // 🚨 ป้องกันตารางเบี้ยว: ถ้าช่องเดิมผสานแนวตั้ง (Rowspan) ไว้ ต้องบังคับให้ช่องใหม่หดเหลือ 1 
            newCell.rowSpan = 1;                 
            newCell.classList.remove('selected-cell'); // ล้างสถานะไฮไลท์สีฟ้าทิ้ง
            
            nr.appendChild(newCell);
        });
    }
    if (a === 'insertColRight') {
        for (let i = 0; i < t.rows.length; i++) {
            let currentRow = t.rows[i];
            // 🌟 2. หาช่องต้นแบบในแต่ละแถว (ถ้าแถวไหนโดนผสานจนสั้นกว่า index ให้เอาช่องสุดท้ายแทน)
            let refCell = currentRow.cells[ci] || currentRow.cells[currentRow.cells.length - 1];
            
            if (refCell) {
                let newCell = refCell.cloneNode(false);
                newCell.innerHTML = '&nbsp;';
                
                // 🚨 ป้องกันตารางเบี้ยว: ถ้าช่องเดิมผสานแนวนอน (Colspan) ไว้ ต้องบังคับช่องใหม่หดเหลือ 1
                newCell.colSpan = 1;             
                newCell.classList.remove('selected-cell');
                
                // แทรกต่อจากช่องต้นแบบเป๊ะๆ
                if (refCell.nextSibling) {
                    currentRow.insertBefore(newCell, refCell.nextSibling);
                } else {
                    currentRow.appendChild(newCell);
                }
            }
        }
    }
    if (a === 'deleteRow') {
        t.deleteRow(ri);
        if (t.rows.length === 0) t.remove();
    }
    if (a === 'deleteCol') {
        for (let i = 0; i < t.rows.length; i++) {
            if (t.rows[i].cells.length > ci) t.rows[i].deleteCell(ci);
        }
        if (t.rows[0] && t.rows[0].cells.length === 0) t.remove();
    }
    
    setTimeout(paginateContent, 100);
    if (typeof debouncedSave === 'function') debouncedSave(); 
}
// ฟังก์ชันยกเลิกการผสานเซลล์ (Unmerge / Split Cells)
function unmergeSelectedCells() {
    const cells = getSelectedCells();
    if (cells.length === 0) return;

    let hasUnmerged = false;

    cells.forEach(cell => {
        const rSpan = cell.rowSpan;
        const cSpan = cell.colSpan;

        // ถ้าไม่ได้ผสานไว้แต่แรก ก็ข้ามไปเลย
        if (rSpan <= 1 && cSpan <= 1) return;

        hasUnmerged = true;
        const tr = cell.parentElement;
        const table = cell.closest('table');
        const startRowIdx = tr.rowIndex;
        
        // หาตำแหน่ง index ปัจจุบันของช่องนี้ในแถว
        let baseCellIdx = Array.from(tr.cells).indexOf(cell);

        // 1. รีเซ็ตช่องนี้ให้กลับมาเป็น 1x1
        cell.rowSpan = 1;
        cell.colSpan = 1;

        // 2. สร้างเซลล์ใหม่ไปอุดรอยรั่วที่เพิ่งหดตัวกลับมา
        for (let r = 0; r < rSpan; r++) {
            let targetRow = table.rows[startRowIdx + r];
            if (!targetRow) continue; // กันตาราง Error

            for (let c = 0; c < cSpan; c++) {
                // ถ้าเป็นพิกัดซ้ายบนสุด (ช่องที่เรากำลังหดตัว) ให้ข้ามไป เพราะเรามีช่องนี้อยู่แล้ว
                if (r === 0 && c === 0) continue; 

                // สร้าง td ใหม่
                let newCell = document.createElement(cell.nodeName); // ถ้าเป็น th ก็สร้าง th ให้
                newCell.innerHTML = '&nbsp;'; // กฎเหล็ก: ใส่ &nbsp; เพียวๆ
                
                // ก๊อปปี้เส้นขอบและการตั้งค่าพื้นฐานมาจากช่องแม่
                newCell.style.border = cell.style.border || '1px solid #000';
                newCell.style.padding = cell.style.padding || '5px';
                newCell.style.position = 'relative';

                // 3. แทรกช่องใหม่ลงไปใน DOM
                if (r === 0) {
                    // แทรกในแถวเดียวกัน (ต่อท้ายช่องแม่)
                    let refNode = targetRow.cells[baseCellIdx + c];
                    if (refNode) {
                        targetRow.insertBefore(newCell, refNode);
                    } else {
                        targetRow.appendChild(newCell);
                    }
                } else {
                    // แทรกในแถวข้างล่าง
                    let refNode = targetRow.cells[baseCellIdx];
                    if (refNode) {
                        targetRow.insertBefore(newCell, refNode);
                    } else {
                        targetRow.appendChild(newCell);
                    }
                }
            }
        }
    });

    if (hasUnmerged) {
        clearTableSelection();
        
        // สำคัญ: เรียกฟังก์ชันสร้างแกนปรับขนาดใหม่ เพื่อให้ช่องที่เกิดใหม่สามารถลากปรับขนาดได้!
        if (typeof makeTablesResizable === 'function') {
            makeTablesResizable(); 
        }

        setTimeout(paginateContent, 100);
        if (typeof debouncedSave === 'function') debouncedSave();
    } else {
        alert("เซลล์ที่เลือกไม่ได้ถูกผสานไว้ครับลูกพี่!");
    }
}


function mergeSelectedCells() {
    const cells = getSelectedCells();
    if (cells.length <= 1) {
        alert("กรุณาลากคลุมเซลล์ที่ต้องการผสานอย่างน้อย 2 ช่องขึ้นไปครับ");
        return;
    }

    // 1. หาตำแหน่งต่ำสุด-สูงสุด ของแถว(Row) และคอลัมน์(Col)
    let minR = Infinity, maxR = -Infinity;
    let minC = Infinity, maxC = -Infinity;

    cells.forEach(cell => {
        const r = cell.parentElement.rowIndex;
        const c = cell.cellIndex;
        minR = Math.min(minR, r);
        maxR = Math.max(maxR, r);
        minC = Math.min(minC, c);
        maxC = Math.max(maxC, c);
    });

    // 2. กำหนดเซลล์หลัก (เซลล์ซ้ายบนสุด)
    let targetCell = null;
    let mergedContent = "";

    cells.forEach(cell => {
        const r = cell.parentElement.rowIndex;
        const c = cell.cellIndex;
        
        // หาเซลล์ซ้ายบนสุดเพื่อเป็นตัวหลัก
        if (r === minR && c === minC) {
            targetCell = cell;
        }
        
        // รวบรวมข้อความจากเซลล์อื่นๆ (ถ้ามี) มาต่อกัน
        let cellText = cell.innerHTML.trim();
        if (cellText !== '&nbsp;' && cellText !== '') {
            if (cell !== targetCell) {
                mergedContent += " " + cellText;
            }
        }
    });

    if (!targetCell) targetCell = cells[0]; // กันเหนียว

    // 3. เอาเนื้อหาจากช่องอื่นมาใส่ช่องหลัก
    if (mergedContent.trim() !== "") {
         targetCell.innerHTML += mergedContent;
    }

    // 4. ตั้งค่า Colspan และ Rowspan ให้ช่องหลักขยายร่าง
    targetCell.colSpan = (maxC - minC) + 1;
    targetCell.rowSpan = (maxR - minR) + 1;

    // 5. ลบเซลล์ลูกเจี๊ยบอื่นๆ ที่ถูกคลุมทิ้งไป
    cells.forEach(cell => {
        if (cell !== targetCell) {
            cell.remove();
        }
    });

    // 6. ล้างสถานะและจัดหน้ากระดาษ
    clearTableSelection();
    setTimeout(paginateContent, 100);
    debouncedSave();
}// =========================================================
// ระบบลากตาราง "แท่งล่องหน" แบบผลัก (Push Mode) - เลิกหดเอง 100%
// =========================================================
// =========================================================
// ระบบลากตาราง "แท่งล่องหน" แบบ Word (แชร์ 2 ช่อง, กดแล้วไม่หด 100%)
// =========================================================

// 1. ฝัง CSS ซ่อนตัวลากเก่า และบังคับลูกศร (ลบ box-sizing ตัวต้นเหตุที่ทำให้ตารางหดทิ้งไปแล้ว)
if (!document.getElementById('table-resize-style')) {
    const style = document.createElement('style');
    style.id = 'table-resize-style';
    style.innerHTML = `
        .col-resizer { display: none !important; pointer-events: none !important; }
        .table-resize-hover, .table-resize-hover * {
            cursor: col-resize !important;
            user-select: none !important;
        }
    `;
    document.head.appendChild(style);
}

// 2. สร้างแท่งล่องหนจับเป้าหมาย
let globalResizer = document.getElementById('global-table-resizer');
if (!globalResizer) {
    globalResizer = document.createElement('div');
    globalResizer.id = 'global-table-resizer';
    globalResizer.style.cssText = 'position: absolute; width: 14px; cursor: col-resize; z-index: 99999; display: none; background: transparent;'; 
    document.body.appendChild(globalResizer);
}

let isResizingCol = false;
let resizerStartX = 0;
let currentResizeCell = null;
let nextResizeCell = null;
let currentResizeTable = null;
let startWidthCurrent = 0;
let startWidthNext = 0;
let startTableWidth = 0;
let maxTableWidth = 0;

function makeTablesResizable() {} // ปล่อยว่างไว้

// 3. จังหวะเริ่มคลิก (Mousedown) -> 🚨 อ่านค่าอย่างเดียว ห้ามเปลี่ยน Style เด็ดขาด! 🚨
globalResizer.addEventListener('mousedown', function(e) {
    if (e.button !== 0) return; 
    
    isResizingCol = true;
    currentResizeCell = this.targetCell;
    nextResizeCell = currentResizeCell.nextElementSibling;
    currentResizeTable = currentResizeCell.closest('table');
    
    let zoom = (typeof currentZoom !== 'undefined' ? currentZoom : 100) / 100;
    
    const wrapper = currentResizeTable.closest('.content-wrapper');
    maxTableWidth = wrapper.clientWidth - 2;

    // จำค่าตั้งต้นไว้ในใจเฉยๆ (ห้ามยัด style กลับเข้าตารางตอนคลิก ไม่งั้นมันจะหดวูบ)
    startTableWidth = currentResizeTable.getBoundingClientRect().width / zoom;
    startWidthCurrent = currentResizeCell.getBoundingClientRect().width / zoom;
    
    if (nextResizeCell) {
        startWidthNext = nextResizeCell.getBoundingClientRect().width / zoom;
    }
    
    resizerStartX = e.pageX;
    
    if (window.getSelection) window.getSelection().removeAllRanges();
    
    e.preventDefault();
    e.stopPropagation();
});

// 4. ควานหาขอบ และออกแรงลาก (Mousemove)
document.addEventListener('mousemove', function(e) {
    
    // --- โหมด A: กำลังออกแรงลากเมาส์ ---
    if (isResizingCol && currentResizeCell && currentResizeTable) {
        e.preventDefault();
        
        let zoom = (typeof currentZoom !== 'undefined' ? currentZoom : 100) / 100;
        let dx = (e.pageX - resizerStartX) / zoom;
        
        // กรณีที่ 1: ลากเส้นด้านใน (แชร์พื้นที่ 2 ช่อง)
        if (nextResizeCell) {
            let maxDx = startWidthNext - 20;       
            let minDx = -(startWidthCurrent - 20); 
            
            if (dx > maxDx) dx = maxDx;
            if (dx < minDx) dx = minDx;

            let newCurrent = startWidthCurrent + dx;
            let newNext = startWidthNext - dx;
            
            currentResizeCell.style.width = newCurrent + 'px';
            currentResizeCell.style.minWidth = newCurrent + 'px';

            nextResizeCell.style.width = newNext + 'px';
            nextResizeCell.style.minWidth = newNext + 'px';
            
            globalResizer.style.left = (e.pageX - 7) + 'px'; 
        } 
        // กรณีที่ 2: ลากเส้นริมขวาสุด (ขยายตาราง)
        else {
            let minDx = -(startWidthCurrent - 20);
            if (dx < minDx) dx = minDx;

            let newCurrent = startWidthCurrent + dx;
            let newTableWidth = startTableWidth + dx;
            
            if (newTableWidth > maxTableWidth) {
                newTableWidth = maxTableWidth;
                newCurrent = startWidthCurrent + (maxTableWidth - startTableWidth);
            }

            currentResizeCell.style.width = newCurrent + 'px';
            currentResizeCell.style.minWidth = newCurrent + 'px';
            
            currentResizeTable.style.width = newTableWidth + 'px';
            
            globalResizer.style.left = (e.pageX - 7) + 'px'; 
        }
        return; 
    }

    // --- โหมด B: ควานหาขอบตอนไม่ได้กดเมาส์ ---
    const cell = e.target.closest('td, th');
    
    if (cell && cell.closest('#document-area') && !isResizingCol) {
        const rect = cell.getBoundingClientRect();
        let foundEdgeX = null;
        let targetCell = null;

        if (Math.abs(e.clientX - rect.right) <= 8) {
            foundEdgeX = rect.right;
            targetCell = cell; 
        } 
        else if (Math.abs(e.clientX - rect.left) <= 8 && cell.previousElementSibling) {
            foundEdgeX = rect.left;
            targetCell = cell.previousElementSibling; 
        }

        if (foundEdgeX !== null && targetCell) {
            globalResizer.style.display = 'block';
            globalResizer.style.left = (foundEdgeX + window.scrollX - 7) + 'px';
            globalResizer.style.top = (rect.top + window.scrollY) + 'px';
            globalResizer.style.height = rect.height + 'px';
            globalResizer.targetCell = targetCell; 
            document.body.classList.add('table-resize-hover');
        } else {
            if (e.target !== globalResizer) {
                globalResizer.style.display = 'none';
                document.body.classList.remove('table-resize-hover');
            }
        }
    } else if (!isResizingCol && e.target !== globalResizer) {
        globalResizer.style.display = 'none';
        document.body.classList.remove('table-resize-hover');
    }
});

// 5. จังหวะปล่อยเมาส์ (Mouseup)
document.addEventListener('mouseup', function() {
    if (isResizingCol) {
        isResizingCol = false;
        
        currentResizeCell = null;
        nextResizeCell = null;
        currentResizeTable = null;
        
        globalResizer.style.display = 'none'; 
        document.body.classList.remove('table-resize-hover');
        
        if (typeof debouncedSave === 'function') debouncedSave();
    }
});






// ฟังก์ชันปรับ Zoom แบบบวก/ลบ
function changeZoom(amount) {

    currentZoom += amount;
    
    // ตั้งขีดจำกัด ไม่ให้เล็กเกินไป (50%) หรือใหญ่คับจอเกินไป (200%)
    if (currentZoom < 50) currentZoom = 50;
    if (currentZoom > 200) currentZoom = 200;
    
    applyZoom();
}

// ฟังก์ชันกำหนดค่า Zoom ตรงๆ (เช่น กดปุ่ม Reset)
function setZoom(level) {
    currentZoom = level;
    applyZoom();
}

function applyZoom() {
    // 1. อัปเดตตัวเลขเปอร์เซ็นต์ที่หน้าจอ
    

    const zoomText = document.getElementById('zoom-level-text');
    if (zoomText) {
        zoomText.innerText = currentZoom + '%';
    }

    // 2. *** เล็งเป้าไปที่ "หน้ากระดาษ" ทุกแผ่น แทนที่จะเป็น Area รวม ***
    const pages = document.querySelectorAll('.document-page');
    pages.forEach(page => {
        // ใช้ CSS zoom เข้าไปที่หน้ากระดาษตรงๆ
        page.style.zoom = currentZoom + '%'; 
    });

    // 3. จัดการกรอบ Resizer (เผื่อคลิกรูปค้างไว้ กรอบจะต้องขยับตามกระดาษที่ซูม)
    if (typeof updateResizerPosition === 'function') {
        setTimeout(updateResizerPosition, 50); // ดีเลย์นิดนึงให้ Browser วาดกระดาษเสร็จก่อน
    }
    localStorage.setItem('my_word_zoom_level', currentZoom);
}





function checkTableContext() {
    const t = document.getElementById('tab-table-design');
    if (!t) return;
    
    if (document.querySelectorAll('.selected-cell').length > 0 || activeTableCell) {
        t.classList.add('visible');
    } else {
        t.classList.remove('visible');
        if (t.classList.contains('ribbon-tab-active')) wordswitchTab('home');
    }
    
}

function openTableModal() {
    restoreFocus();
    document.getElementById('tableModal').style.display = 'block';
}

function closeTableModal() {
    document.getElementById('tableModal').style.display = 'none';
    restoreFocus();
}

// =========================================================
//  SECTION: IMAGE HANDLING (Safe Resize: คุมทั้งกว้างและสูง)
// =========================================================

function insertImageFile(input) {
    if (input.files && input.files[0]) {
        const file = input.files[0];

        // 1. เช็คชนิดไฟล์
        if (!file.type.startsWith('image/')) {
            alert('ขอเป็นไฟล์รูปภาพเท่านั้นครับลูกพี่!');
            return;
        }

        const imgUrl = URL.createObjectURL(file);
        const img = new Image();
        img.src = imgUrl;

        img.onload = function() {
            // --- ตั้งค่าขีดจำกัด (สำคัญมากตรงนี้!) ---
            
            // A4 ที่ 96DPI กว้าง ~794px, สูง ~1123px
            // หักขอบบนล่างซ้ายขวา (padding) เนื้อหาจะเหลือประมาณ:
            // กว้าง: ~700px
            // สูง: ~950px (เผื่อไว้หน่อย กันล้น)
            
            const MAX_WIDTH = 750;  // กว้างไม่เกินนี้
            const MAX_HEIGHT = 500; // *** ตัวแก้บั๊ก: สูงห้ามเกิน 1 หน้ากระดาษ ***
            const QUALITY = 0.8;    // คุณภาพ JPEG

            // 3. คำนวณไซส์ใหม่ (รักษาสัดส่วน Aspect Ratio)
            let width = img.width;
            let height = img.height;

            // คำนวณสัดส่วนที่ต้องย่อ
            // เลือกตัวที่ย่อเยอะที่สุด (Min Ratio) เพื่อให้รูปไม่ล้นทั้งกว้างและสูง
            if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                const widthRatio = MAX_WIDTH / width;
                const heightRatio = MAX_HEIGHT / height;
                
                // เลือกค่าที่น้อยกว่า เพื่อให้รูป fit ลงในกล่อง A4 ได้พอดีเป๊ะ
                const bestRatio = Math.min(widthRatio, heightRatio);
                
                width = Math.round(width * bestRatio);
                height = Math.round(height * bestRatio);
            }

            // 4. วาดลง Canvas เพื่อย่อไฟล์
            const canvas = document.createElement('canvas');
            canvas.width = width;
            canvas.height = height;
            const ctx = canvas.getContext('2d');
            
            // ใช้เทคนิคนี้เพื่อให้รููปคมชัดขึ้นตอนย่อ
            ctx.imageSmoothingEnabled = true;
            ctx.imageSmoothingQuality = 'high';
            
            ctx.drawImage(img, 0, 0, width, height);

            // 5. แปลงกลับเป็น Base64
            URL.revokeObjectURL(imgUrl); 
            const compressedDataUrl = canvas.toDataURL('image/jpeg', QUALITY);

            // --- ส่งไปแทรก ---
            insertCompressedImageToDoc(compressedDataUrl);
        };

        img.onerror = function() {
            alert('อ่านไฟล์รูปไม่ได้ครับผม');
        };
    }
    input.value = ''; 
      debouncedSave(); 
}

// (ฟังก์ชันลูกมือ ใช้ตัวเดิมได้เลย หรือก๊อปไปใหม่เพื่อความชัวร์)
function insertCompressedImageToDoc(src) {
    restoreFocus();

    const wrapper = document.createElement('div');
    wrapper.className = 'img-wrapper';
    wrapper.style.textAlign = 'center'; 
    wrapper.style.margin = '10px 0';    
    wrapper.style.width = '100%';       
    
    const img = document.createElement('img');
    img.src = src;
    
    // ตรงนี้สำคัญ: ใส่ style ให้ชัดเจนว่าห้ามเกิน wrapper
    img.style.maxWidth = '100%'; 
    img.style.maxHeight = '100%'; // กันเหนียว
    img.style.height = 'auto';
    img.style.display = 'inline-block'; 
    img.style.boxShadow = '0 0 0 2px transparent'; 

    wrapper.appendChild(img);

    const sel = window.getSelection();
    if (sel.rangeCount > 0) {
        let range = sel.getRangeAt(0);
        
        // เช็คว่า Cursor อยู่ในพื้นที่งานไหม
        if (!range.commonAncestorContainer.closest || !range.commonAncestorContainer.closest('.content-wrapper')) {
             const firstPage = document.querySelector('.content-wrapper');
             if(firstPage) {
                 range = document.createRange();
                 range.selectNodeContents(firstPage);
                 range.collapse(false);
             }
        }

        range.deleteContents();
        range.insertNode(wrapper);
        
        range.setStartAfter(wrapper);
        range.setEndAfter(wrapper);
        sel.removeAllRanges();
        sel.addRange(range);
    } else {
        const firstPageWrapper = document.querySelector('.content-wrapper');
        if(firstPageWrapper) firstPageWrapper.appendChild(wrapper);
    }

    img.onload = function() {
        selectElement(img);
        setTimeout(paginateContent, 100);
    };
}


function selectElement(e) {
    selectedElement = e;
    const resizerFrame = document.getElementById('resizer-frame');
    updateResizerPosition();
    if (resizerFrame) resizerFrame.style.display = 'block';
}

function deselectElement() {
    selectedElement = null;
    const resizerFrame = document.getElementById('resizer-frame');
    if (resizerFrame) resizerFrame.style.display = 'none';
}

function updateResizerPosition() {
const resizerFrame = document.getElementById('resizer-frame');
    
    // 1. เช็คความปลอดภัยพื้นฐาน
    if (!selectedElement || !selectedElement.isConnected) {
        if (resizerFrame) resizerFrame.style.display = 'none';
        return;
    }
    
    // 2. หาขอบเขตของ "พื้นที่ทำงาน" (Document Area)
    const docArea = document.getElementById('document-area');
    const areaRect = docArea.getBoundingClientRect();
    
    // 3. หาขอบเขตของ "วัตถุที่เลือก"
    const r = selectedElement.getBoundingClientRect();

    if (resizerFrame) {
        
        // --- LOGIC: ตรวจสอบการชนขอบ (Collision Detection) ---
        
        // A. ชนขอบบน (มุดใต้เมนู)
        // (เช็คจากด้านล่างของรูป ว่าพ้นขอบบนของพื้นที่งานหรือยัง)
        const isAbove = r.bottom < areaRect.top; 
        
        // B. ชนขอบล่าง (หลุดจอลงไปข้างล่าง)
        // (เช็คจากด้านบนของรูป ว่าพ้นขอบล่างของพื้นที่งานหรือยัง)
        const isBelow = r.top > areaRect.bottom;

        // C. ชนขอบซ้าย/ขวา (เผื่อจอเล็ก)
        const isLeftOut = r.right < areaRect.left;
        const isRightOut = r.left > areaRect.right;

        // --- ถ้าหลุดขอบด้านใดด้านหนึ่ง -> ซ่อนทันที ---
        if (isAbove || isBelow || isLeftOut || isRightOut) {
            resizerFrame.style.display = 'none';
            return;
        }

        // --- ถ้าอยู่ในจอ -> แสดงและคำนวณตำแหน่ง ---
        resizerFrame.style.display = 'block';
        
        // คำนวณตำแหน่ง Clip (ตัดส่วนที่เกินออก เพื่อความเนียน)
        // เช่น ถ้ารูปโผล่มาครึ่งเดียว เส้นก็ต้องโชว์แค่ครึ่งเดียว
        
        // หาจุดตัด (Intersection)
        const top = Math.max(r.top, areaRect.top);
        const bottom = Math.min(r.bottom, areaRect.bottom);
        const left = Math.max(r.left, areaRect.left);
        const right = Math.min(r.right, areaRect.right);
        
        // คำนวณความกว้าง/สูงใหม่ จากจุดตัด
        const width = right - left;
        const height = bottom - top;

        // ถ้าคำนวณแล้วพื้นที่เหลือ 0 หรือติดลบ แปลว่ามองไม่เห็น -> ซ่อน
        if (width <= 0 || height <= 0) {
            resizerFrame.style.display = 'none';
            return;
        }

        // Apply ค่าจริง
        resizerFrame.style.top = top + 'px';
        resizerFrame.style.left = left + 'px';
        resizerFrame.style.width = width + 'px';
        resizerFrame.style.height = height + 'px';
    }
      debouncedSave(); 
}

function startResize(e) {
    if (!selectedElement) return;
    isResizing = true;
    sx = e.clientX;
    sy = e.clientY;
    sw = selectedElement.offsetWidth;
    sh = selectedElement.offsetHeight;
    document.addEventListener('mousemove', doResize);
    document.addEventListener('mouseup', stopResize);
    e.preventDefault();
}

function doResize(e) {
    if (!isResizing) return;
    const nw = sw + (e.clientX - sx);
    if (nw > 20) {
        selectedElement.style.width = nw + 'px';
        if (selectedElement.tagName !== 'TABLE') selectedElement.style.height = 'auto';
        updateResizerPosition();
    }
}

function stopResize() {
    isResizing = false;
    document.removeEventListener('mousemove', doResize);
    document.removeEventListener('mouseup', stopResize);
    paginateContent();
}


// =========================================================
//  SECTION 8: UTILITIES & PRINT
// =========================================================
function wordswitchTab(n) {
    document.querySelectorAll('.ribbon-tab-item').forEach(t => t.classList.remove('ribbon-tab-active'));
    document.getElementById('tab-' + n).classList.add('ribbon-tab-active');
    
    document.querySelectorAll('.ribbon-group').forEach(g => {
        g.classList.remove('active');
        g.style.display = 'none';
    });
    
    const tg = document.getElementById(n + '-tools');
    if (tg) {
        tg.style.display = 'flex';
        tg.classList.add('active');
    }
}

function toggleDropdown(el) {
    const content = el.querySelector('.dropdown-content');
    content.style.display = (content.style.display === 'block') ? 'none' : 'block';
}


function updateStatus() {
    document.getElementById('page-status').textContent = `Page Total: ${document.querySelectorAll('.document-page').length}`;
}








function printDocument() {
    // ล้างสถานะไฮไลท์ต่างๆ ก่อนปริ้นท์
    if (typeof restoreFocus === 'function') restoreFocus();
    if (typeof clearTableSelection === 'function') clearTableSelection();
    if (typeof clearShapeSelection === 'function') clearShapeSelection();
    if (typeof deselectElement === 'function') deselectElement();

    // 1. ดึงขนาดกระดาษ "ของจริง" ที่คุณตั้งไว้บนหน้าจอ (เช่น 800px x 1200px)
    const samplePage = document.querySelector('.document-page');
    let pageWidth = '800px';
    let pageHeight = '1200px';
    
    if (samplePage) {
        pageWidth = window.getComputedStyle(samplePage).width;
        pageHeight = window.getComputedStyle(samplePage).height;
    }

    const iframe = document.createElement('iframe');
    iframe.style.position = 'fixed';
    iframe.style.top = '-10000px'; 
    iframe.style.left = '-10000px';
    iframe.style.width = pageWidth;   
    iframe.style.height = pageHeight;
    iframe.style.border = 'none';
    
    document.body.appendChild(iframe);
    const idoc = iframe.contentWindow.document;

    // ดูด CSS ของระบบมาให้ครบ
    let originalStyles = "";
    document.querySelectorAll('style, link[rel="stylesheet"]').forEach(node => {
        originalStyles += node.outerHTML;
    });

    // 2. CSS สำหรับพิมพ์ 
    const printCSS = `
        <style>
            *, *::before, *::after {
                -webkit-print-color-adjust: exact !important;
                print-color-adjust: exact !important;
                box-sizing: border-box !important; 
            }

            @page { 
                size: ${pageWidth} ${pageHeight}; 
                margin: 0 !important; 
            }
            
            html, body {
                width: ${pageWidth} !important;
                height: 100% !important;
                margin: 0 !important;
                padding: 0 !important;
                background: white !important;
            }

            .document-page {
                position: relative !important;
                margin: 0 auto !important; 
                width: ${pageWidth} !important;
                height: ${pageHeight} !important; 
                background: white !important;
                page-break-after: always !important; 
                break-after: page !important;
                
                /* 🌟 ปลดล็อคให้โชว์เส้นขอบที่ล้น 0.5px ได้ (แก้ปัญหาขอบขวาหายโดยไม่ต้องพึ่ง Padding) */
                overflow: visible !important; 
            }
            
            .document-page:last-child {
                page-break-after: auto !important;
                break-after: auto !important;
            }
            
            /* 🌟 เอา Padding ซ้าย/ขวา ออกให้เกลี้ยงแล้วครับ */
            .content-wrapper {
                width: 100% !important;
            }

            table {
                border-collapse: collapse !important; 
                border-spacing: 0;
                width: 100% !important; 
                max-width: 100% !important;
            }

            /* ซ่อนของที่ไม่เกี่ยวกับการปริ้นท์ */
            .col-resizer, .resize-handle, #resizer-frame, .col-break-marker, 
            .drag-handle, .tool-btn, ::-webkit-scrollbar,
            #global-table-resizer, .page-number-watermark { 
                display: none !important; 
            }
            
            .drawing-canvas {
                display: block !important;
                border: none !important;
            }
            
            body, table, td, th {
                 font-family: 'THSarabunNew', sans-serif !important;
            }
        </style>
    `;

    // เตรียมก๊อปปี้หน้ากระดาษ
    let printContent = "";
    const pages = document.querySelectorAll('.document-page');
    
    pages.forEach((page) => {
        const cloned = page.cloneNode(true);
        
        // ลบ Zoom ออก เพื่อให้ขนาดเป็น Pixel ดั้งเดิม
        cloned.style.zoom = '';
        cloned.style.transform = 'none';

        // กวาดล้างซากสีฟ้าและแกนลาก
        cloned.querySelectorAll('.selected-cell').forEach(c => c.classList.remove('selected-cell'));
        cloned.querySelectorAll('.selected-shape').forEach(c => c.classList.remove('selected-shape'));
        cloned.querySelectorAll('.col-resizer').forEach(c => c.remove()); 

        printContent += cloned.outerHTML;
    });

    // โยนเนื้อหาเข้า Iframe
    idoc.open();
    idoc.write(`
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset='UTF-8'>
            <title>Print Document</title>
            ${originalStyles} 
            ${printCSS}
        </head>
        <body>
            ${printContent}
        </body>
        </html>
    `);
    idoc.close();

    // สั่งปริ้นท์
    iframe.onload = function() {
        setTimeout(function() {
            try {
                iframe.contentWindow.focus();
                iframe.contentWindow.print();
            } catch (e) {
                console.error(e);
            }
            // ถอด Iframe ทิ้งหลังปริ้นท์เสร็จ
            setTimeout(function() { 
                if (document.body.contains(iframe)) {
                    document.body.removeChild(iframe); 
                }
            }, 3000); 
        }, 1000); 
    };
}




// =========================================================
//  SECTION: COLOR & FORMATTING (จัดการสีฟอนต์)
// =========================================================

function applyFontColor(color) {
    currentFontColor = color;
    const selectedCells = document.querySelectorAll('.selected-cell');

    if (selectedCells.length > 0) {
        // โหมดตาราง: ยัดสีใส่ td ตรงๆ
        selectedCells.forEach(cell => {
            cell.style.color = color; 
            cleanAndFlattenCell(cell); // ล้าง Tag font/span ข้างในทิ้ง
        });
    } else {
        // โหมดข้อความปกติ
        restoreFocus(); 
        document.execCommand('foreColor', false, color);
    }
    
    if (typeof debouncedSave === "function") debouncedSave();
}



function selectTableForResize() {
        if (activeTableCell) {
            selectElement(activeTableCell.closest('table'));
        }
    }
    
    
    function resizeCell(type, value) {
    const cells = getSelectedCells();
    if (cells.length === 0) return;

    cells.forEach(cell => {
        const t = cell.closest('table');
        // ปลดล็อคตารางให้ขยับอิสระ
        if (t && t.style.width !== 'auto') t.style.width = 'auto'; 
        
        if (type === 'w') {
            // ปรับความกว้าง
            let currentW = cell.getBoundingClientRect().width;
            cell.style.width = Math.max(20, currentW + value) + 'px';
        } 
        else if (type === 'h') {
            // ปรับความสูง
            let currentH = cell.getBoundingClientRect().height;
            cell.style.height = Math.max(20, currentH + value) + 'px';
        }
        else if (type === 'p') {
            // ปรับระยะขอบ (Padding)
            let compStyle = window.getComputedStyle(cell);
            let currentP = parseInt(compStyle.paddingTop) || 0; // ดึงค่าเดิมมาเช็ค
            let newP = Math.max(0, currentP + value); // Padding ติดลบไม่ได้ ต้องกำหนดต่ำสุดคือ 0
            cell.style.padding = newP + 'px';
        }
    });

  clearTableSelection();
    restoreFocus();
      debouncedSave(); 
    
    // อัปเดตหน้ากระดาษเผื่อตารางล้น
    if (typeof paginateContent === 'function') {
        clearTimeout(window.resizeTimeout);
        window.resizeTimeout = setTimeout(paginateContent, 150);
    }
       
}




// =========================================================
//  SECTION: DRAWING CANVAS (ฉบับสมบูรณ์: Z-Index / Text Drag / Reload)
// =========================================================

let activeCanvas = null;
let activeShape = null;

// ---------------------------------------------------------
// 1. ระบบกู้คืนชีพ (แก้ปัญหาข้อ 3: ตอนโหลดใหม่ทำไงให้ลากได้)
// ---------------------------------------------------------
// *** เรียกฟังก์ชันนี้ตอนโหลดหน้าเว็บ หรือหลังจากวาง HTML ก้อนใหญ่ ***
function rebindCanvasEvents() {
    console.log("Rebinding Canvas Events...");
    
    // 1. ปลุกผีกระดาน (Canvas)
    const canvases = document.querySelectorAll('.drawing-canvas');
    canvases.forEach(canvas => {
        canvas.contentEditable = "false";
        canvas.onmousedown = function(e) {
            if(e.target === canvas) clearShapeSelection();
            activeCanvas = this;
        };
    });

    // 2. ปลุกผีรูปร่าง (Shapes)
    const shapes = document.querySelectorAll('.abs-shape');
    shapes.forEach(shape => {
        setupShapeEvents(shape); // ฝังสมองกลับเข้าไปใหม่
    });
      debouncedSave(); 
}

// (แถม) เรียกใช้อัตโนมัติตอนโหลด
window.addEventListener('load', rebindCanvasEvents);
// เรียกใช้ทุกครั้งที่เมาส์ขยับเข้ามาในพื้นที่งาน (เผื่อเพิ่ง Paste มา)
document.addEventListener('mouseover', function(e) {
   if(e.target.closest('.drawing-canvas') && !e.target.closest('.drawing-canvas').onclick) {
       rebindCanvasEvents();
   }
});


// ---------------------------------------------------------
// 2. ฟังก์ชันเพิ่ม Shape
// ---------------------------------------------------------
function insertCanvas() {
    restoreFocus();
    const canvas = document.createElement('div');
    canvas.className = 'drawing-canvas';
    canvas.contentEditable = "false"; 
    
    // คลิกกระดานเพื่อเคลียร์การเลือก
    canvas.onmousedown = function(e) {
        if(e.target === canvas) clearShapeSelection();
        activeCanvas = this;
    };

    const wrapper = document.createElement('div');
    wrapper.className = 'img-wrapper';
    wrapper.style.textAlign = 'center';
    wrapper.appendChild(canvas);

    const sel = window.getSelection();
    if (sel.rangeCount > 0) {
        let range = sel.getRangeAt(0);
        range.deleteContents();
        range.insertNode(wrapper);
    }
      debouncedSave(); 
}

function addShapeToCanvas(type) {
    if (!activeCanvas) {
        // พยายามหากระดานล่าสุดดู
        activeCanvas = document.querySelector('.drawing-canvas');
        if(!activeCanvas) {
            alert("สร้างกระดานวาดรูปก่อนครับลูกพี่!");
            return;
        }
    }

    const shape = document.createElement('div');
    shape.className = 'abs-shape'; 
    shape.style.top = '20px';
    shape.style.left = '20px';

    if (type === 'rect') {
        shape.style.width = '100px'; shape.style.height = '60px';
        shape.style.border = '2px solid black'; shape.style.backgroundColor = 'white';
    } 
    else if (type === 'circle') {
        shape.style.width = '80px'; shape.style.height = '80px';
        shape.style.border = '2px solid black'; shape.style.borderRadius = '50%'; shape.style.backgroundColor = 'white';
    }
    // แก้ข้อ 2: กล่องข้อความ
    else if (type === 'textbox') {
        shape.classList.add('textbox-shape');
        shape.style.width = '150px'; shape.style.height = '40px';
        shape.innerText = 'พิมพ์...';
        shape.contentEditable = "true"; 
    }
    // Emoji & Arrows
    else if (type.startsWith('emoji') || type.startsWith('arrow')) {
        shape.style.width = '60px'; shape.style.height = '60px';
        shape.style.fontSize = '40px'; shape.style.background = 'transparent'; shape.style.border = 'none';
        
        if (type === 'emoji-star') shape.innerText = '⭐';
        if (type === 'emoji-heart') shape.innerText = '❤️';
        if (type === 'arrow-right') shape.innerHTML = '&#10140;';
        if (type === 'arrow-left')  shape.innerHTML = '&#11013;';
        if (type === 'arrow-up')    shape.innerHTML = '&#11014;';
        if (type === 'arrow-down')  shape.innerHTML = '&#11015;';
    }

    setupShapeEvents(shape);
    activeCanvas.appendChild(shape);
    focusShape(shape);
      debouncedSave(); 
}

// ---------------------------------------------------------
// 3. ระบบจัดการ Event (แก้เรื่องลากกล่องข้อความ)
// ---------------------------------------------------------
function setupShapeEvents(shape) {
    
    // Event กดเมาส์ (เพื่อลาก หรือ เพื่อพิมพ์)
    shape.onmousedown = function(e) {
        e.stopPropagation(); // ไม่ให้ทะลุ
        focusShape(shape);   // เลือกตัวนี้ (และดึงมาหน้าสุดด้วย CSS z-index)

        // A. เช็คการ Resize (มุมขวาล่าง 15px)
        if (e.offsetX > shape.offsetWidth - 15 && e.offsetY > shape.offsetHeight - 15) {
            return; // ปล่อยให้ Browser Resize
        }

        // B. เช็คกล่องข้อความ (แก้ข้อ 2: ลากไม่ไป)
        if (shape.classList.contains('textbox-shape')) {
            // ถ้า User กำลังพิมพ์อยู่ (Focus อยู่แล้ว) -> ให้พิมพ์ต่อ อย่าลาก
            if (document.activeElement === shape) {
                return; 
            }
            // ถ้ายังไม่ได้พิมพ์ -> อนุญาตให้ลากได้!
            // (User ต้อง Double Click หรือคลิกอีกทีถ้าจะพิมพ์)
        }

        // C. เริ่มลาก
        startDrag(e, shape);
    };

    // แถม: Double Click เพื่อพิมพ์ทันทีสำหรับกล่องข้อความ
    shape.ondblclick = function(e) {
        if (shape.isContentEditable) {
            shape.focus(); // เข้าโหมดพิมพ์
        }
    }
}

// ---------------------------------------------------------
// 4. ฟังก์ชันลาก (Drag Logic)
// ---------------------------------------------------------
function startDrag(e, elmnt) {
    e.preventDefault();
    let pos3 = e.clientX;
    let pos4 = e.clientY;

    document.onmouseup = closeDragElement;
    document.onmousemove = elementDrag;

    function elementDrag(e) {
        e.preventDefault();
        let pos1 = pos3 - e.clientX;
        let pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;

        elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
        elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
    }

    function closeDragElement() {
        document.onmouseup = null;
        document.onmousemove = null;
    }
      debouncedSave(); 
}

// ---------------------------------------------------------
// 5. ระบบเลือก / ลบ
// ---------------------------------------------------------
function focusShape(shape) {
    clearShapeSelection();
    activeShape = shape;
    shape.classList.add('selected-shape'); // พอใส่ class นี้ z-index จะเป็น 9999 ทันที
}

function clearShapeSelection() {
    if (activeShape) {
        activeShape.classList.remove('selected-shape');
        activeShape = null;
    }
      debouncedSave(); 
}

// ปุ่ม Delete
document.addEventListener('keydown', function(e) {
    if ((e.key === 'Delete' || e.key === 'Backspace') && activeShape) {
        // ถ้าพิมพ์อยู่ในกล่องข้อความ ห้ามลบ shape
        if (document.activeElement === activeShape && activeShape.isContentEditable) return;
        
        e.preventDefault();
        activeShape.remove();
        activeShape = null;
    }
});




// =========================================================
//  SECTION: PAGE ORIENTATION (ปรับแนวตั้ง/แนวนอน)
// =========================================================

// =========================================================
//  SECTION: PAGE ORIENTATION (แก้ไขให้ขยายหน้าจอบน Display ด้วย)
// =========================================================

function setPageOrientation(mode) {
    // 1. หา Element ที่เกี่ยวข้องทั้งหมด
    const contents = document.querySelectorAll('.content-wrapper');
    const pages = document.querySelectorAll('.document-page'); // <--- ต้องหาตัวนี้ด้วย!
    const body = document.body;
    
    // ตั้งค่าตัวแปร Style สำหรับ Print (ถ้ามี)
    const printStyle = document.getElementById('page-orientation-style');

    if (mode === 'landscape') {
        // --- เปลี่ยนเป็น แนวนอน ---
        body.classList.add('print-landscape');
        
        // สั่งเนื้อหาให้เปลี่ยน
        contents.forEach(el => el.classList.add('landscape'));
        
        // *** จุดสำคัญที่เพิ่ม: สั่งให้ตัวกระดาษเปลี่ยนด้วย ***
        pages.forEach(el => el.classList.add('landscape')); 
        
        // อัปเดต Style สำหรับ Print
        if(printStyle) {
            printStyle.innerHTML = '@page { size: A4 landscape; margin: 0; }';
        }

    } else {
        // --- เปลี่ยนกลับเป็น แนวตั้ง ---
        body.classList.remove('print-landscape');
        
        contents.forEach(el => el.classList.remove('landscape'));
        
        // *** จุดสำคัญที่เพิ่ม: สั่งให้ตัวกระดาษเปลี่ยนกลับ ***
        pages.forEach(el => el.classList.remove('landscape'));

        if(printStyle) {
            printStyle.innerHTML = '@page { size: A4 portrait; margin: 0; }';
        }
    }

    // 2. จัดหน้าใหม่ (สำคัญมาก)
    setTimeout(() => {
        // ดึงเนื้อหากลับมารวมกันก่อน เพื่อตัดใหม่แต่ต้น
        mergeAllContentToFirstPage(); 
        
        // สั่งตัดหน้าใหม่ตามขนาดกระดาษปัจจุบัน
        paginateContent(); 
        
        if (selectedElement) updateResizerPosition();
    }, 350); 
    
      debouncedSave(); 
}



function applyTh() {
    // 1. ดึงรายการ cells ที่ถูกเลือกทั้งหมด (สมมติว่า function นี้ return Array หรือ NodeList ของ element ที่มี class 'selected')
    const cells = getSelectedCells(); 
    
    // ถ้าไม่มีการเลือกอะไรเลย ให้จบ
    if (!cells || cells.length === 0) return;

    // 2. วนลูปทำทีละช่อง (ใช้ Array.from เผื่อค่าที่ส่งมาเป็น NodeList)
    Array.from(cells).forEach(el => {
        
        // ถ้าสิ่งที่เลือกไม่ใช่ช่องตาราง ให้ข้ามไป (หรือลองหา closest)
        if (el.nodeName !== 'TD' && el.nodeName !== 'TH') {
             // ถ้ามันไปเลือกโดน span หรือ div ข้างใน ให้หาแม่ที่เป็น td/th
             let parent = el.closest('td, th');
             if (parent) el = parent;
             else return; // ถ้าหาไม่เจอจริงๆ ให้ข้าม loop รอบนี้
        }

        // 3. กำหนด Tag ใหม่ (สลับ TD <-> TH)
        let newTagName = (el.nodeName === 'TD') ? 'th' : 'td';
        let newEl = document.createElement(newTagName);

        // 4. ย้ายข้อมูลข้างในและ Attribute ทั้งหมด
        newEl.innerHTML = el.innerHTML;
        Array.from(el.attributes).forEach(attr => {
            newEl.setAttribute(attr.name, attr.value);
        });

        // 5. จัดการเรื่อง Style
        if (newTagName === 'th') {
            newEl.style.backgroundColor = '#e6e6e6'; 
            newEl.style.fontWeight = 'bold';
            newEl.style.textAlign = 'center';
            newEl.style.border = '1px solid #000'; 
        } else {
            newEl.style.backgroundColor = ''; 
            newEl.style.fontWeight = '';
            newEl.style.textAlign = '';
        }

        // 6. *** สำคัญมาก: ต้องใส่ Class ที่ระบุว่า "ถูกเลือก" กลับไปที่ตัวใหม่ด้วย ***
        // สมมติว่า class ที่คุณใช้ไฮไลท์ชื่อ 'selected' (แก้ชื่อ class ตรงนี้ให้ตรงกับ CSS ของคุณ)
        newEl.classList.add('selected'); 

        // 7. แทนที่ Element เดิม
        el.parentNode.replaceChild(newEl, el);
    });

    debouncedSave(); 
}

function run(realData) {
    // 1. สร้างข้อมูลจำลอง 100 แถว


    // 2. เรียกฟังก์ชันแทนที่ข้อมูล (Inject Data)
    console.log("กำลังนำเข้าข้อมูล 100 รายการ...");
    
     console.log(realData);
    injectData(realData);

    // 3. *สำคัญ* ต้องเรียกจัดหน้า (Paginate) หลังจากข้อมูลเข้าแล้ว
    // ใช้ setTimeout เล็กน้อยเพื่อให้แน่ใจว่า DOM วาดเสร็จแล้วค่อยคำนวณความสูง
    setTimeout(() => {
        console.log("กำลังจัดหน้ากระดาษ...");
        paginateContent();
    }, 100); 
}

function runTest() {
    // 1. สร้างข้อมูลจำลอง 100 แถว
    let testData = {
        "company": "Tech Innovation Co., Ltd.",
        "date": "13/12/2025",
        "name": [],  // เริ่มต้นเป็นอาเรย์เปล่า
        "score": []
    };

    // วนลูปยัดข้อมูล 100 รอบ
    for (let i = 1; i <= 100; i++) {
        testData.name.push(`พนักงาน สมชาย ลำดับที่ ${i}`); 
        testData.score.push(Math.floor(Math.random() * 1000)); // สุ่มคะแนน
    }

    // 2. เรียกฟังก์ชันแทนที่ข้อมูล (Inject Data)
    console.log("กำลังนำเข้าข้อมูล 100 รายการ...");
    injectData(testData);

    // 3. *สำคัญ* ต้องเรียกจัดหน้า (Paginate) หลังจากข้อมูลเข้าแล้ว
    // ใช้ setTimeout เล็กน้อยเพื่อให้แน่ใจว่า DOM วาดเสร็จแล้วค่อยคำนวณความสูง
    setTimeout(() => {
        console.log("กำลังจัดหน้ากระดาษ...");
        paginateContent();
    }, 100); 
}













// ข้อมูลตัวอย่าง (JSON)
// let myData = {
//    "company": "บริษัท ร่ำรวย จำกัด",    // แบบปกติ (String)
//    "date": "14/12/2025",             // แบบปกติ (String)
//    "name": ["นาย A", "นาย B", "นาย C"], // แบบ Loop (Array)
//    "score": [100, 200, 300]            // แบบ Loop (Array)
// };
/**
 * ฟังก์ชันฉีดข้อมูลลงในแบบฟอร์ม (Inject Data)
 * @param {Object} data - ข้อมูล JSON ที่ต้องการนำไปแทนที่
 */
 // =========================================================
// ฟังก์ชันฉีดข้อมูลเข้าเอกสาร (เวอร์ชันสมบูรณ์ที่สุด)
// =========================================================
function injectData(data) {

    // 1. จัดรูปแบบเงิน
    const formatMoney = (val) => {
        if (val === null || val === undefined || val === "") return "";
        let num = parseFloat(val);
        if (isNaN(num)) return val; 
        if (num === 0) return ""; 
        return num.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
    };

    // 2. ป้องกัน HTML แตก
    const safeHTML = (val) => {
        if (typeof val !== 'string') return String(val || "");
        return val.replace(/\r?\n/g, '<br>').replace(/"/g, '&quot;');
    };

    // 🌟 3. ศูนย์กลางการแปลงข้อมูล (เงิน / ข้อความ / รูปภาพ)
    const formatDataValue = (key, val) => {
        if (val === null || val === undefined) return "";
        
        // --- กรณีเป็นรูปภาพ (ชื่อตัวแปรขึ้นต้นด้วย img_) ---
        if (key.startsWith('img_')) {
            if (String(val).trim() === "") return ""; 
            
            // 🖋️ ลายเซ็น: แทรกกลางเส้นประ, ชิดล่าง (สูงไม่เกิน 60px)
          if (key.includes('sign')) {
                // เราห่อหุ้มด้วย div ที่มี height:0 เพื่อไม่ให้รูปไปเบียดบรรทัดอื่นพัง
                // แล้วใช้ transform: translateY ดันรูปลงไปทับเส้นประข้างล่าง
                return `
                <div style="display: block; width: 100%; height: 0; position: relative; text-align: center;">
                    <img src="${val}" style="max-width: 80%; max-height: 50px; object-fit: contain; position: absolute; left: 50%; transform: translateX(-55%) translateY(-8px); z-index: 1;" class="injected-image" />
                </div>`;
            }
            // 🏢 โลโก้: จัดกึ่งกลางช่องตารางพอดีเป๊ะ (สูงไม่เกิน 90px)
            else {
                return `<img src="${val}" style="max-width: 100%; max-height: 90px; object-fit: contain; display: block; margin: 0 auto;" class="injected-image" />`;
            }
        }
        
        // --- กรณีเป็นเงิน (ชื่อตัวแปรลงท้ายด้วย D) ---
        if (key.endsWith('D')) return formatMoney(val);
        
        // --- กรณีเป็นข้อความปกติ ---
        return safeHTML(val);
    };

    // ---------------------------------------------------------
    // เริ่มทำงานกับหน้ากระดาษ
    // ---------------------------------------------------------
    const area = document.getElementById('document-area');
    if (!area) return;

    // =========================================================
    // STEP 1: อัปเดตข้อมูลเก่าที่เคย "ฝังชิป" ไว้แล้ว (รองรับการกดปุ่มซ้ำ)
    // =========================================================
    area.querySelectorAll('.data-tag-global').forEach(span => {
        let key = span.getAttribute('data-key');
        if (data[key] !== undefined) {
            span.innerHTML = formatDataValue(key, data[key]);
        }
    });

    // ทำความสะอาด HTML Tag ขยะที่อาจเผลอไปกดพิมพ์แทรกกลางป้าย
    let rawHTML = area.innerHTML;
    area.innerHTML = rawHTML.replace(/\[#([^\]]+)\]/g, (match) => {
        return match.replace(/<[^>]*>/g, ""); 
    });

    // =========================================================
    // STEP 2: จัดการตาราง (ระบบ Shadow Template & Master-Detail)
    // =========================================================
    
    // 2.1 ซ่อนแถวแม่พิมพ์ให้เป็นเงา
    Array.from(area.querySelectorAll('table tr')).forEach(row => {
        if (row.classList.contains('template-row') || row.classList.contains('generated-row')) return;
        
        let matches = row.outerHTML.match(/\[#([^\]]+)\]/g) || [];
        let isLoop = matches.some(tag => {
            let key = tag.replace(/[\[#\]]/g, '');
            return Array.isArray(data[key]); // เช็คว่าใน data เป็น Array ไหม
        });
        
        if (isLoop) {
            row.classList.add('template-row'); 
            row.style.display = 'none'; // ซ่อนแม่พิมพ์ไว้ใช้รอบถัดไป
        }
    });

    // 2.2 ลบข้อมูลตารางเก่าทิ้ง เตรียมปั๊มใหม่
    area.querySelectorAll('tr.generated-row').forEach(r => r.remove());

    // 2.3 ปั๊มแถวใหม่จากแม่พิมพ์เงา
    let tables = area.querySelectorAll('table');
    tables.forEach(table => {
        let trs = Array.from(table.querySelectorAll('tr'));
        let i = 0;
        
        while (i < trs.length) {
            let row = trs[i];
            
            // ถ้าเจอแม่พิมพ์
            if (row.classList.contains('template-row')) {
                let block = [row];
                let j = i + 1;
                while (j < trs.length && trs[j].classList.contains('template-row')) {
                    block.push(trs[j]);
                    j++;
                }

                // หาจำนวนรายการที่เยอะที่สุดใน Array ของกลุ่มนี้
                let maxItems = 0;
                block.forEach(r => {
                    let matches = r.outerHTML.match(/\[#([^\]]+)\]/g) || [];
                    matches.forEach(tag => {
                        let key = tag.replace(/[\[#\]]/g, '');
                        if (Array.isArray(data[key])) maxItems = Math.max(maxItems, data[key].length);
                    });
                });

                let fragment = document.createDocumentFragment();
                let lastSeenMValues = {}; // เอาไว้จำค่าหมวดหมู่ (M)

                for (let k = 0; k < maxItems; k++) {
                    block.forEach((blockRow, rowIdx) => {
                        let newRow = blockRow.cloneNode(true);
                        
                        // เอาป้ายแม่พิมพ์ออก แปลงเป็นแถวข้อมูลพร้อมโชว์
                        newRow.classList.remove('template-row');
                        newRow.classList.add('generated-row');
                        newRow.style.display = ''; 

                        let tempInner = newRow.innerHTML;
                        let rowMatches = tempInner.match(/\[#([^\]]+)\]/g) || [];
                        
                        let isMRow = false;
                        let mValueEmpty = true;
                        let currentMValueString = "";
                        
                        rowMatches.forEach(tag => {
                            let key = tag.replace(/[\[#\]]/g, '');
                            if (key.endsWith('M')) {
                                isMRow = true;
                                let val = data[key];
                                if (Array.isArray(val)) {
                                    let item = val[k];
                                    currentMValueString += String(item || "").trim() + "|";
                                    if (item !== undefined && item !== null && String(item).trim() !== "" && String(item).trim() !== "-") {
                                        mValueEmpty = false; 
                                    }
                                }
                            }
                        });

                        // 🌟 ระบบจัดกลุ่ม M (ถ้าซ้ำให้ข้ามบรรทัดนี้ไปเลย)
                        if (isMRow) {
                            if (mValueEmpty) return; 
                            if (lastSeenMValues[rowIdx] === currentMValueString) return; 
                            lastSeenMValues[rowIdx] = currentMValueString;
                        }

                        // แทนที่ค่าตัวแปรด้วยข้อมูลจริง (เรียกใช้ไม้ตาย formatDataValue)
                        rowMatches.forEach(tag => {
                            let key = tag.replace(/[\[#\]]/g, '');
                            let val = data[key];
                            let item = Array.isArray(val) ? val[k] : val;
                            let replacement = formatDataValue(key, item);
                            tempInner = tempInner.split(tag).join(replacement);
                        });

                        newRow.innerHTML = tempInner;
                        fragment.appendChild(newRow); // แปะลงกระดาษจำลอง
                    });
                }
                
                // ดันข้อมูลที่ปั๊มเสร็จ แทรกไว้ "ด้านหน้า" แม่พิมพ์
                block[0].parentNode.insertBefore(fragment, block[0]);
                i = j; 
            } else {
                i++;
            }
        }
    });

    // =========================================================
    // STEP 3: แทนที่ป้ายเดี่ยว (Global) พร้อม "ฝังชิป" ไว้โหลดรอบหน้า
    // =========================================================
    let finalContent = area.innerHTML;
    Object.keys(data).forEach(key => {
        let tag = `[#${key}]`;
        if (finalContent.includes(tag) && !Array.isArray(data[key])) {
            let replacementValue = formatDataValue(key, data[key]);
            
            // 🌟 ทีเด็ด: ห่อข้อความด้วย span ล่องหน เพื่อทำสัญลักษณ์ให้โค้ดหาเจอตอนกดโหลดซ้ำ
            let spanWrapper = `<span class="data-tag-global" data-key="${key}">${replacementValue}</span>`;
            finalContent = finalContent.split(tag).join(spanWrapper);
        }
    });
    area.innerHTML = finalContent;

    // =========================================================
    // STEP 4: จัดการ Textarea / Input
    // =========================================================
    const formElements = area.querySelectorAll('textarea, input');
    formElements.forEach(el => {
        // หาลิ้นชักความจำ
        let template = el.getAttribute('data-template');
        if (!template) {
            let content = el.value || el.textContent; 
            if (content && content.match(/\[#([^\]]+)\]/)) {
                // แอบจำโครงสร้างเดิมไปฝากไว้ใน attribute
                el.setAttribute('data-template', content);
                template = content;
            }
        }

        // ถ้ามีลิ้นชักความจำ ให้ดึงมาใช้
        if (template) {
            let newContent = template;
            Object.keys(data).forEach(key => {
                let tag = `[#${key}]`;
                if (newContent.includes(tag)) {
                    let val = data[key];
                    // 🚨 ข้อควรระวัง: ใน Input/Textarea ใส่รูป HTML ไม่ได้ เลยดึงเป็น Text ดิบๆ
                    let item = Array.isArray(val) ? val.join(', ') : val;
                    let replacementValue = (key.endsWith('D')) ? formatMoney(item) : (item || "");
                    
                    newContent = newContent.split(tag).join(replacementValue);
                }
            });
            el.value = newContent;
            el.textContent = newContent; 
        }
    });

    if (typeof debouncedSave === "function") {
        debouncedSave(); 
    }
}
 
 
function setReadOnlyMode(isReadOnly) {
    console.log("Read-Only Mode:", isReadOnly);

    // 1. จัดการพื้นที่กระดาษ (Content Editable)
    const contentWrappers = document.querySelectorAll('.content-wrapper');
    contentWrappers.forEach(wrapper => {
        // ถ้า isReadOnly เป็น true -> contenteditable เป็น false
        wrapper.setAttribute('contenteditable', !isReadOnly);
    });

    // 2. จัดการ Ribbon Toolbar (เครื่องมือข้างบนทั้งหมด)
    const ribbon = document.querySelector('.ribbon-toolbar');
    if (ribbon) {
        if (isReadOnly) {
            ribbon.classList.add('ui-disabled');
        } else {
            ribbon.classList.remove('ui-disabled');
        }
    }

    // 3. จัดการเมนู File (Top Menu)
    // เราต้องเลือกเฉพาะเมนูข้างในที่ไม่ใช่ Print
    const menuItems = document.querySelectorAll('.dropdown-content > *');
    
    menuItems.forEach(item => {
        // ตรวจสอบว่าเป็นปุ่ม Print หรือไม่ 
        // (เช็คจาก onclick หรือ text content ก็ได้ แต่ onclick แม่นกว่า)
        const onclickAttr = item.getAttribute('onclick');
        const isPrintButton = onclickAttr && onclickAttr.includes('printDocument()');

        if (!isPrintButton) {
            if (isReadOnly) {
                item.classList.add('ui-disabled');
            } else {
                item.classList.remove('ui-disabled');
            }
        }
    });

    // 4. (Optional) ซ่อน Resize Handle เพื่อไม่ให้ปรับขนาดกระดาษเล่น
    const resizers = document.querySelectorAll('.resize-handle, #resizer-frame');
    resizers.forEach(el => {
        el.style.display = isReadOnly ? 'none' : 'block';
    });
}


async function pasteFromClipboard() {
    try {
        // 1. สั่งให้อ่านข้อความจาก Clipboard (จังหวะนี้ Browser อาจจะถาม Permission ผู้ใช้)
        const text = await navigator.clipboard.readText();
        
        // 2. ถ้าอ่านได้ ให้เอาข้อความไป "พิมพ์แทรก" (insertText) ตรงจุดที่ Cursor กระพริบ
        // หมายเหตุ: เราใช้ insertText แทน paste เพราะ paste ถูกบล็อก
        document.execCommand('insertText', false, text);
        
    } catch (err) {
        // กรณี User กดไม่อนุญาต หรือ Browser ไม่รองรับ
        console.error('ไม่สามารถวางข้อความได้:', err);
        alert('Browser ไม่อนุญาตให้ปุ่มเข้าถึง Clipboard กรุณากด Ctrl+V แทนครับ');
    }
}


function setFontSize(size) {
    const selectedCells = document.querySelectorAll('.selected-cell');

    if (selectedCells.length > 0) {
        // โหมดตาราง: ยัดขนาดใส่ td ตรงๆ
        selectedCells.forEach(cell => {
            cell.style.fontSize = size; 
            cleanAndFlattenCell(cell); // ล้าง Tag font ข้างในทิ้ง
        });
    } else {
        // โหมดข้อความปกติ (ใช้ทริคเดิมของคุณ)
        document.execCommand("fontSize", false, "7");
        var fontElements = document.getElementsByTagName("font");
        for (var i = 0; i < fontElements.length; i++) {
            if (fontElements[i].getAttribute("size") == "7") {
                fontElements[i].removeAttribute("size"); 
                fontElements[i].style.fontSize = size;  
            }
        }
    }

    if (typeof restoreFocus === "function") restoreFocus(); 
    if (typeof debouncedSave === "function") debouncedSave(); 
}

function fixAllMessyTables() {
    const allCells = document.querySelectorAll('#document-area td, #document-area th');
    
    allCells.forEach(cell => {
        // 1. คลำหาดูว่าข้างในมี Tag บ้าบออะไรซ่อนอยู่ไหม
        let innerChild = cell.querySelector('span, font, b, strong');
        
        if (innerChild) {
            // 2. ถ้าเจอ ให้ดูดพลัง (Style) ออกมาแปะที่ td แทน
            let comp = window.getComputedStyle(innerChild);
            if (comp.fontFamily) cell.style.fontFamily = comp.fontFamily;
            if (comp.fontSize) cell.style.fontSize = comp.fontSize;
            if (comp.color) cell.style.color = comp.color;
            if (comp.fontWeight === 'bold' || innerChild.tagName === 'B' || innerChild.tagName === 'STRONG') {
                cell.style.fontWeight = 'bold';
            }
        }
        
        // 3. ปั่นล้างให้สะอาดเอี่ยม
        cleanAndFlattenCell(cell);
    });

    alert("ซ่อมแซมและกวาดล้าง Tag ขยะในตารางเรียบร้อยแล้วลูกพี่!");
    if (typeof debouncedSave === "function") debouncedSave();
}


function syncToolbar() {

	
    var sel = window.getSelection();
    if (sel.rangeCount > 0) {
        // 1. หา "ก้อน" (Node) ที่เมาส์เราจิ้มอยู่ หรือ ไฮไลท์อยู่
        var node = sel.anchorNode; 
        
        // ถ้าจิ้มโดนตัวหนังสือ (Text Node) ให้ขยับขึ้นไปหาตัวแม่มัน (Parent Element)
        // เพราะตัวหนังสือไม่มี Style แต่ตัวแม่มัน (เช่น <td>, <b>, <font>) มี
        if (node.nodeType === 3) { 
            node = node.parentNode;
        }

        // 2. สั่ง Browser ให้คำนวณค่าจริงออกมา (Compute)
        // บรรทัดนี้คือทีเด็ดครับ มันจะทะลุตาราง ทะลุทุกอย่าง เพื่อดูค่าจริง
        var computed = window.getComputedStyle(node);

        // --- จัดการชื่อฟอนต์ (Font Family) ---
        var fontName = computed.fontFamily.replace(/['"]/g, ""); // ลบเครื่องหมายคำพูดออก
        // ถ้ามีหลายฟอนต์ (เช่น "THSarabunNew, sans-serif") เอาแค่ตัวแรก
        fontName = fontName.split(',')[0].trim();
        
        var fontSelect = document.getElementById('fontNameSelect');
        if(fontSelect) {
             fontSelect.value = fontName; 
        }

        // --- จัดการขนาด (Font Size) ---
        var fontSizePx = computed.fontSize; // ค่าที่ได้จะเป็น px เสมอ เช่น "21.33px"
        
        if (fontSizePx) {
            // สูตรแปลง px เป็น pt (มาตรฐาน: 1px = 0.75pt)
            var pxVal = parseFloat(fontSizePx);
            var ptVal = Math.round(pxVal * 0.75); // ปัดเศษให้เป็นจำนวนเต็ม
            
            var sizeSelect = document.getElementById('fontSizeSelect');
            if(sizeSelect) {
                // พยายามเลือกค่าใน Dropdown ให้ตรงกับที่คำนวณได้
                // ถ้าค่าไม่มีใน Dropdown (เช่นคำนวณได้ 13pt) มันจะว่างไว้ ซึ่งก็ถูกต้องแล้ว
                sizeSelect.value = ptVal + "pt"; 
            }
        }
    }
}




var savedFormat = null; // ตัวแปรเก็บความจำ

// --- ฟังก์ชัน 1: จำค่า (ดูดสี) ---
function toggleMemory() {
    var btnMem = document.getElementById('btnMemory');
    var txtMem = document.getElementById('txtMemory');
    var btnApply = document.getElementById('btnApply');

    // ถ้ายังไม่จำ -> ให้เริ่มจำ
    if (savedFormat === null) {
        var sel = window.getSelection();
        if (sel.rangeCount > 0) {
            // หาจุดที่เมาส์จิ้มอยู่
            var node = sel.anchorNode;
            if (node.nodeType === 3) node = node.parentNode; // ขยับหาแม่มัน
            
            // ดึงค่าจริงๆ จาก Browser (Computed Style)
            var computed = window.getComputedStyle(node);
            
            // บันทึกค่า (รอบนี้เก็บค่าดิบๆ เลย ไม่ต้องแปลงเยอะ)
            savedFormat = {
                fontName: computed.fontFamily.replace(/['"]/g, ""), 
                fontSize: computed.fontSize,       
                color: computed.color,             
                backColor: computed.backgroundColor, 
                fontWeight: computed.fontWeight, // จำตัวหนาด้วย
                fontStyle: computed.fontStyle    // จำตัวเอียงด้วย
            };

            console.log("จำค่าแล้ว:", savedFormat); // เช็คใน Console ได้

            // เปลี่ยนสีปุ่ม
            btnMem.style.backgroundColor = "#d4edda"; // เขียวอ่อน
            txtMem.innerText = "ลืมค่า";
            
            // เปิดปุ่มวาง
            btnApply.disabled = false;
            btnApply.style.opacity = "1";
            btnApply.style.cursor = "pointer";
        }
    } 
    // ถ้าจำอยู่ -> ให้ลืม (Reset)
    else {
        savedFormat = null;
        
        btnMem.style.backgroundColor = ""; // คืนสีเดิม
        txtMem.innerText = "จำค่า";
        
        btnApply.disabled = true;
        btnApply.style.opacity = "0.5";
        btnApply.style.cursor = "not-allowed";
    }
}

// --- ฟังก์ชัน 2: วางค่า (พ่นสี) ---
// ทีเด็ดอยู่ที่นี่: เราจะสร้าง Tag เดียว แล้วใส่ Style ทุกอย่างลงไป
function applyStyle() {
    if (!savedFormat) return;

    restoreFocus(); // ดึง Focus กลับมา

    // 1. สร้าง "กล่องเปล่า" ครอบข้อความที่เลือก (ใช้ fontSize 7 เป็นตัวสร้างกล่อง)
    document.execCommand("fontSize", false, "7");

    // 2. ค้นหากล่องที่เราเพิ่งสร้าง (Tag <font size="7">)
    var fontElements = document.getElementsByTagName("font");
    
    // 3. วนลูปยัดเยียดความสวยงามลงไปในกล่องนั้น
    for (var i = 0; i < fontElements.length; i++) {
        if (fontElements[i].getAttribute("size") == "7") {
            var el = fontElements[i];
            
            // ลบ size="7" ทิ้ง (เพราะเราจะใช้ css แทน)
            el.removeAttribute("size");
            
            // --- ใส่ Style ทุกอย่างลงไปตรงๆ (Direct Style) ---
            
            // 1. ขนาดฟอนต์
            el.style.fontSize = savedFormat.fontSize;
            
            // 2. สีตัวอักษร
            el.style.color = savedFormat.color;
            el.removeAttribute("color"); // ลบ attribute เก่ากันตีกัน
            
            // 3. สีพื้นหลัง (ต้องเช็คว่าไม่ใช่สีใส)
            if (savedFormat.backColor && savedFormat.backColor !== 'rgba(0, 0, 0, 0)' && savedFormat.backColor !== 'transparent') {
                el.style.backgroundColor = savedFormat.backColor;
            } else {
                el.style.backgroundColor = "transparent"; // ถ้าต้นฉบับไม่มีสี ก็เคลียร์ออก
            }

            // 4. ชื่อฟอนต์
            el.style.fontFamily = savedFormat.fontName;
            el.removeAttribute("face");

            // 5. ตัวหนา/ตัวเอียง
            el.style.fontWeight = savedFormat.fontWeight;
            el.style.fontStyle = savedFormat.fontStyle;
        }
    }
}

// 1. ฟังก์ชันถ่ายรูปเก็บประวัติ (เรียกใช้ตอนมีการเปลี่ยนแปลง)
function saveState() {
    if (isRestoring) return; // ถ้าย้อนกลับอยู่ ห้ามเซฟซ้ำ!

    const docArea = document.getElementById('document-area');
    if (!docArea) return;

    const currentState = docArea.innerHTML;

    // ถ้าเหมือนเดิมเป๊ะ ไม่ต้องเซฟให้เปลืองเมม
    if (historyIndex >= 0 && historyStack[historyIndex] === currentState) return;

    // ถ้าเราย้อนกลับไปตรงกลาง แล้วทำอะไรใหม่ -> อนาคต (Redo) ที่เหลือต้องโดนลบทิ้ง
    if (historyIndex < historyStack.length - 1) {
        historyStack = historyStack.slice(0, historyIndex + 1);
    }

    historyStack.push(currentState);
    
    // คุมไซส์ไม่ให้เกินกำหนด
    if (historyStack.length > MAX_HISTORY) {
        historyStack.shift();
    } else {
        historyIndex++;
    }
}

// 2. ฟังก์ชันหน่วงเวลาเซฟ (ใช้ตอนพิมพ์ข้อความ จะได้ไม่เซฟทุกๆ 1 ตัวอักษร)
function debouncedSave() {
    clearTimeout(saveTimeout);
    saveTimeout = setTimeout(saveState, 600); // หยุดพิมพ์ 0.6 วินาทีค่อยเซฟ
}

// 3. ปุ่ม Undo
function undo() {
    if (historyIndex <= 0) return; // ถอยสุดแล้ว
    isRestoring = true;
    historyIndex--;
    applyState(historyStack[historyIndex]);
}

// 4. ปุ่ม Redo
function redo() {
    if (historyIndex >= historyStack.length - 1) return; // ไปหน้าสุดแล้ว
    isRestoring = true;
    historyIndex++;
    applyState(historyStack[historyIndex]);
}

// 5. ฟังก์ชันเท HTML คืนจอ
function applyState(htmlContent) {
    const docArea = document.getElementById('document-area');
    
    // เคลียร์สถานะการเลือกของเดิมทิ้งให้หมด กัน error หาทะลุ
    deselectElement();
    if (typeof clearTableSelection === 'function') clearTableSelection();
    if (typeof clearShapeSelection === 'function') clearShapeSelection();

    // เทข้อมูลคืน
    docArea.innerHTML = htmlContent;

    // *** สำคัญมาก: ผูก Event คืนชีพให้กระดาษและ Canvas ***
    docArea.querySelectorAll('.content-wrapper').forEach(w => {
        w.addEventListener('input', handleInput);
    });

    if (typeof rebindCanvasEvents === 'function') {
        rebindCanvasEvents();
    }

    // ปล่อยให้ระบบเดินต่อ
    setTimeout(() => {
        updateResizerPosition();
        isRestoring = false; // ปลดล็อคให้เซฟต่อได้
    }, 50);
}






  async function saveDocumentToLocal() {
      // ใช้ doc_autoid เป็นรหัสอ้างอิงของแต่ละใบ
      let autoId = "${mydoc.doc_autoid}";
      if (!autoId) {
          console.warn("ไม่มีรหัส doc_autoid สำหรับบันทึก");
          return;
      }

      // 1. สร้าง Thumbnail เล็กๆ (ไม่บังคับ แต่มีไว้ก็ดีครับ)
      let thumbnailBase64 = "";
      try {
          const firstPage = document.querySelector('.document-page');
          if (firstPage) {
              const fullCanvas = await html2canvas(firstPage, { scale: 1, backgroundColor: '#ffffff', logging: false });
              const thumbCanvas = document.createElement('canvas');
              thumbCanvas.width = 150; // ย่อให้เล็กเพื่อประหยัดพื้นที่ Local Storage
              thumbCanvas.height = (fullCanvas.height * 150) / fullCanvas.width;
              thumbCanvas.getContext('2d').drawImage(fullCanvas, 0, 0, thumbCanvas.width, thumbCanvas.height);
              thumbnailBase64 = thumbCanvas.toDataURL('image/jpeg', 0.6);
          }
      } catch (err) {}

      // 2. แพ็คข้อมูลปัจจุบัน
      const pages = document.querySelectorAll('.document-page');
      const isLandscape = document.body.classList.contains('print-landscape');

      const docData = {
          pageSize: (typeof currentSizeClass !== 'undefined') ? currentSizeClass : 'A4',
          orientation: isLandscape ? 'landscape' : 'portrait',
          thumbnail: thumbnailBase64,
          pages: []
      };

      pages.forEach((page, index) => {
          const wrapper = page.querySelector('.content-wrapper');
          docData.pages.push({
              pageNumber: index + 1,
              content: wrapper.innerHTML,
              columnCount: parseInt(wrapper.style.columnCount) || 1
          });
      });

      // 3. จัดการ Local Storage (ดึงของเก่ามาเรียง คัดกรอง และบันทึก)
      try {
          // ดึงประวัติเก่ามา ถ้าไม่มีให้เป็น Array เปล่า
          let savedDocs = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY)) || [];

          // ลบใบเดิมออกก่อน (ถ้ามีเซฟทับเลขที่เอกสารเดิม) จะได้เอาไปต่อคิวเป็นของใหม่สุด
          savedDocs = savedDocs.filter(doc => doc.id !== autoId);

          // ดันเอกสารใบนี้เข้าเป็นลำดับแรกสุด (Index 0)
          savedDocs.unshift({
              id: autoId,
              timestamp: Date.now(),
              data: docData
          });

          // เช็คลิมิต: ถ้าเกิน 100 ให้ตัดหางทิ้ง
          if (savedDocs.length > MAX_SAVED_DOCS) {
              savedDocs = savedDocs.slice(0, MAX_SAVED_DOCS);
          }

          // เซฟกลับเข้า Local Storage
          localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(savedDocs));
          
          console.log(`บันทึกแบบจำค่าสำเร็จ! (ใช้พื้นที่ไป ${savedDocs.length}/${MAX_SAVED_DOCS} ใบ)`);
          
          // (Optional) แจ้งเตือนผู้ใช้เบาๆ
          alert("บันทึกการจัดหน้าในเครื่องสำเร็จ!");

      } catch (e) {
          console.error("บันทึกไม่สำเร็จ Storage อาจจะเต็ม", e);
          // Browser มักจะให้พื้นที่ Local Storage ประมาณ 5MB ถ้ารูปเยอะมากอาจจะเต็มได้
          if (e.name === 'QuotaExceededError') {
              alert("พื้นที่จำของเบราว์เซอร์เต็มครับ แนะนำให้ลบรูปที่ไม่จำเป็นออก");
          }
      }
  }
  
  
  
  
  async function saveDocumentToLocal(autoId) {
    // ใช้ doc_autoid เป็นรหัสอ้างอิงของแต่ละใบ

    if (!autoId) {
        console.warn("ไม่มีรหัส doc_autoid สำหรับบันทึก");
        return;
    }

    // 1. สร้าง Thumbnail เล็กๆ (ไม่บังคับ แต่มีไว้ก็ดีครับ)
    let thumbnailBase64 = "";
    try {
        const firstPage = document.querySelector('.document-page');
        if (firstPage) {
            const fullCanvas = await html2canvas(firstPage, { scale: 1, backgroundColor: '#ffffff', logging: false });
            const thumbCanvas = document.createElement('canvas');
            thumbCanvas.width = 150; // ย่อให้เล็กเพื่อประหยัดพื้นที่ Local Storage
            thumbCanvas.height = (fullCanvas.height * 150) / fullCanvas.width;
            thumbCanvas.getContext('2d').drawImage(fullCanvas, 0, 0, thumbCanvas.width, thumbCanvas.height);
            thumbnailBase64 = thumbCanvas.toDataURL('image/jpeg', 0.6);
        }
    } catch (err) {}

    // 2. แพ็คข้อมูลปัจจุบัน
    const pages = document.querySelectorAll('.document-page');
    const isLandscape = document.body.classList.contains('print-landscape');

    const docData = {
        pageSize: (typeof currentSizeClass !== 'undefined') ? currentSizeClass : 'A4',
        orientation: isLandscape ? 'landscape' : 'portrait',
        thumbnail: thumbnailBase64,
        pages: []
    };

    pages.forEach((page, index) => {
        const wrapper = page.querySelector('.content-wrapper');
        docData.pages.push({
            pageNumber: index + 1,
            content: wrapper.innerHTML,
            columnCount: parseInt(wrapper.style.columnCount) || 1
        });
    });

    // 3. จัดการ Local Storage (ดึงของเก่ามาเรียง คัดกรอง และบันทึก)
    try {
        // ดึงประวัติเก่ามา ถ้าไม่มีให้เป็น Array เปล่า
        let savedDocs = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY)) || [];

        // ลบใบเดิมออกก่อน (ถ้ามีเซฟทับเลขที่เอกสารเดิม) จะได้เอาไปต่อคิวเป็นของใหม่สุด
        savedDocs = savedDocs.filter(doc => doc.id !== autoId);

        // ดันเอกสารใบนี้เข้าเป็นลำดับแรกสุด (Index 0)
        savedDocs.unshift({
            id: autoId,
            timestamp: Date.now(),
            data: docData
        });

        // เช็คลิมิต: ถ้าเกิน 100 ให้ตัดหางทิ้ง
        if (savedDocs.length > MAX_SAVED_DOCS) {
            savedDocs = savedDocs.slice(0, MAX_SAVED_DOCS);
        }

        // เซฟกลับเข้า Local Storage
        localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(savedDocs));
        
        console.log(`บันทึกแบบจำค่าสำเร็จ! (ใช้พื้นที่ไป ${savedDocs.length}/${MAX_SAVED_DOCS} ใบ)`);
        
        // (Optional) แจ้งเตือนผู้ใช้เบาๆ
        alert("บันทึกการจัดหน้าในเครื่องสำเร็จ!");

    } catch (e) {
        console.error("บันทึกไม่สำเร็จ Storage อาจจะเต็ม", e);
        // Browser มักจะให้พื้นที่ Local Storage ประมาณ 5MB ถ้ารูปเยอะมากอาจจะเต็มได้
        if (e.name === 'QuotaExceededError') {
            alert("พื้นที่จำของเบราว์เซอร์เต็มครับ แนะนำให้ลบรูปที่ไม่จำเป็นออก");
        }
    }
}

function loadDraftFromLocal(autoId) {
  
    if (!autoId) return false;

    let savedDocs = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY)) || [];
    
    // หาว่ามีรหัสเอกสารนี้ในกระเป๋าความจำไหม
    let foundDoc = savedDocs.find(doc => doc.id === autoId);

    if (foundDoc) {
        console.log("พบการจัดหน้าที่เคยเซฟไว้ กำลังโหลด...");
        // ส่งข้อมูลเข้าฟังก์ชันสร้างเอกสารของคุณ
        reconstructDocument(foundDoc.data);
        return true; // บอกว่าโหลดสำเร็จนะ
    }else{
		        console.log("ไม่พบการจัดหน้าที่เคยเซฟไว้ ");
	}
    
    return false; // ไม่เคยเซฟไว้
}


// ฟังก์ชันสำหรับ "ยกเลิกการจัดหน้า (Reset)"
function resetDocumentDraft(autoId) {
   
    if (!autoId) return;

    // ถามยืนยันก่อนลบ
    if (confirm("คุณต้องการล้างการจัดหน้าที่เคยบันทึกไว้ และโหลดกลับไปเป็นรูปแบบดั้งเดิม ใช่หรือไม่?")) {
        
        let savedDocs = JSON.parse(localStorage.getItem(LOCAL_STORAGE_KEY)) || [];
        
        // ลบข้อมูลใบนี้ออกตัวแปร
        savedDocs = savedDocs.filter(doc => doc.id !== autoId);
        
        // เซฟทับกลับไปใหม่ (ที่ไม่มีใบนี้แล้ว)
        localStorage.setItem(LOCAL_STORAGE_KEY, JSON.stringify(savedDocs));
        
        // โหลดหน้าเว็บใหม่ 1 รอบ เพื่อให้ระบบไปดึงข้อมูลดั้งเดิมกลับมา
        location.reload(); 
    }
}


// ฟังก์ชันล้าง Tag ขยะข้างใน <td> ให้เหลือแต่ตัวหนังสือเพียวๆ
function cleanAndFlattenCell(cell) {
    // 1. เก็บแกนปรับขนาด (Resizer) ไว้ก่อน (ห้ามลบทิ้งเด็ดขาด)
    const resizer = cell.querySelector('.col-resizer');
    if (resizer) resizer.remove();

    // 2. ดึงเฉพาะข้อความเพียวๆ ออกมา (innerText จะทิ้ง Tag HTML ทั้งหมดอัตโนมัติ)
    let plainText = cell.innerText.trim();

    // 3. จัดการกรณีเป็นช่องว่าง หรือมีแค่ &nbsp; 
    // (String.fromCharCode(160) คือรหัสของ &nbsp; ใน Javascript)
    if (plainText === '' || plainText === String.fromCharCode(160)) {
        cell.innerHTML = '&nbsp;'; // ให้มันอยู่โดดๆ ไม่มีอะไรครอบ
    } else {
        // ถ้าพิมพ์หลายบรรทัด ให้เปลี่ยน Enter กลับเป็น <br>
        cell.innerHTML = plainText.replace(/\n/g, '<br>');
    }

    // 4. แปะแกนลากปรับขนาดกลับเข้าไปในช่อง
    if (resizer) {
        cell.appendChild(resizer);
    }
}



// =========================================================
// ฟังก์ชันบีบความสูงแถวให้พอดีเนื้อหา (AutoFit Row Height)
// =========================================================
function autoFitRowHeight() {
    const cells = getSelectedCells();
    if (cells.length === 0) {
        alert("กรุณาคลิกเลือกช่อง หรือลากคลุมแถวที่ต้องการบีบความสูงครับ");
        return;
    }

    cells.forEach(cell => {
        // 1. ปลดล็อคความสูงที่ตายตัวออก ให้ Browser รัดตารางแบบ Auto
        cell.style.height = 'auto';
        cell.style.minHeight = '0px';

        // 2. ปรับ Padding ให้กลับมาเป็นค่ามาตรฐาน (บน-ล่าง-ซ้าย-ขวา เท่ากัน)
        // ถ้าอยากให้ชิดกว่านี้ เปลี่ยน '5px' เป็น '2px' หรือ '0px' ได้เลยครับ
        cell.style.padding = '5px'; 

        // 3. กวาดล้างขยะ: ลบ <br> หรือช่องว่าง(&nbsp;) ที่เผลอกด Enter ทิ้งไว้ท้ายข้อความ
        // (สาเหตุหลักที่ทำให้ตารางบวมลงข้างล่าง)
        let htmlContent = cell.innerHTML;
        
        // ใช้ Regex กวาด <br> หรือ white-space ที่อยู่ "ท้ายสุด" ของช่องทิ้งให้หมด
        htmlContent = htmlContent.replace(/(<br\s*\/?>|\s|&nbsp;)+$/gi, '');
        
        // ถ้าล้างแล้วโล่งเตียน ให้ใส่ &nbsp; กันตารางพัง
        if (htmlContent === '') {
            htmlContent = '&nbsp;';
        }
        
        cell.innerHTML = htmlContent;
        
        // ต้องเรียก Resizer ของคอลัมน์กลับมาด้วย ไม่งั้นเดี๋ยวแกนลากหาย
        if (!cell.querySelector('.col-resizer')) {
            const resizer = document.createElement('div');
            resizer.className = 'col-resizer';
            resizer.style.width = '6px'; 
            resizer.style.height = '100%';
            resizer.style.position = 'absolute';
            resizer.style.top = '0';
            resizer.style.right = '-3px'; 
            resizer.style.cursor = 'col-resize';
            resizer.style.userSelect = 'none';
            resizer.style.zIndex = '10';
            
            // ผูก Event Mousedown ของแกนลากกลับเข้าไป (ลอกโค้ดเดิมมาใส่)
            resizer.addEventListener('mousedown', function(e) {
                isResizingCol = true;
                currentResizeCell = cell;
                nextResizeCell = cell.nextElementSibling;
                currentResizeTable = cell.closest('table');
                
                const wrapper = currentResizeTable.closest('.content-wrapper');
                maxTableWidth = wrapper.clientWidth - 2;

                let zoom = (typeof currentZoom !== 'undefined' ? currentZoom : 100) / 100;
                const allCells = currentResizeTable.querySelectorAll('td, th');
                const cellWidths = Array.from(allCells).map(c => c.getBoundingClientRect().width / zoom);

                allCells.forEach((c, i) => {
                    c.style.boxSizing = 'border-box';
                    c.style.width = cellWidths[i] + 'px';
                    c.style.minWidth = cellWidths[i] + 'px';
                    c.style.maxWidth = cellWidths[i] + 'px'; 
                });

                startTableWidth = currentResizeTable.getBoundingClientRect().width / zoom;
                currentResizeTable.style.boxSizing = 'border-box';
                currentResizeTable.style.width = startTableWidth + 'px';
                currentResizeTable.style.tableLayout = 'fixed'; 

                resizerStartX = e.pageX;
                startWidthCurrent = currentResizeCell.getBoundingClientRect().width / zoom;
                if (nextResizeCell) startWidthNext = nextResizeCell.getBoundingClientRect().width / zoom;

                e.preventDefault(); e.stopPropagation();
            });
            cell.appendChild(resizer);
        }
    });

    clearTableSelection();
    restoreFocus();

    // จัดหน้าใหม่เผื่อตารางหดแล้วมีพื้นที่ให้ดึงของหน้าล่างขึ้นมา
    if (typeof paginateContent === 'function') setTimeout(paginateContent, 100);
    if (typeof debouncedSave === 'function') debouncedSave();
}