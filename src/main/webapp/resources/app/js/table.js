class SmartTable {
    constructor(selector, options = {}) {
        this.table = document.querySelector(selector);
        if (!this.table) return;

        this.tableId = this.table.id;
        if (!this.tableId) {
            console.warn('SmartTable: ตารางนี้ไม่มี ID จะไม่สามารถจำค่าได้');
        }

        this.options = {
            resize: true,
            sort: true, // เปิดใช้งาน Sorting
            toggleEvent: true,
            truncateClass: '',
            resizerIcon: '',
            ...options
        };

        this.init();
    }

    init() {
	
        if (this.options.resize) this.makeResizable();
        if (this.options.sort) this.enableSorting();
        if (this.options.grouping) this.applyGrouping();
        if (this.options.toggleEvent)  this.enableTextToggle();
       this.cleanRawNewlines();
    }
    
  // เพิ่มเข้าไปใน class SmartTable
cleanRawNewlines() {
    const cells = this.table.querySelectorAll('td');
    cells.forEach(cell => {
        // ตรวจสอบว่าใน cell มีตัวอักษร \r\n หรือ \n หรือไม่
        if (cell.innerHTML.includes('\\r\\n') || cell.innerHTML.includes('\\n')) {
            // ใช้ Regex /\\r\\n/g เพื่อหา \r\n ทั้งหมดแล้วแทนที่ด้วยการขึ้นบรรทัดใหม่จริงๆ
            let cleanText = cell.innerHTML
                .replace(/\\r\\n/g, '\n')
                .replace(/\\n/g, '\n');
            
            // ใช้ innerText เพื่อให้ Browser รู้ว่า \n คือการขึ้นบรรทัด
            cell.innerText = cleanText; 
        }
    });
}  
  applyGrouping() {
    const tbody = this.table.querySelector('tbody');
    if (!tbody || !this.options.grouping) return;

    const rows = Array.from(tbody.rows);
    if (rows.length === 0) return;

    const { idColIndex, dateColIndex } = this.options.grouping;
    const groups = {};

    // 1. จัดกลุ่ม (Grouping) และหา Timestamp ล่าสุดของแต่ละกลุ่ม
    rows.forEach(row => {
        const id = row.cells[idColIndex]?.textContent.trim();
        if (!id) return;

        if (!groups[id]) groups[id] = { rows: [], latestTs: 0 };
        
        const d = new Date(row.cells[dateColIndex]?.textContent.trim());
        const ts = isNaN(d.getTime()) ? 0 : d.getTime();
        if (ts > groups[id].latestTs) groups[id].latestTs = ts;
        
        groups[id].rows.push(row);
    });

    // 2. เรียงลำดับกลุ่ม (Sort groups by latest timestamp)
    const sortedGroupKeys = Object.keys(groups).sort((a, b) => groups[b].latestTs - groups[a].latestTs);

    tbody.innerHTML = ''; 
    let parentIndex = 1; // ตัวรันเลขหน้าแถวแม่

    sortedGroupKeys.forEach((id) => {
        const group = groups[id];

        // เรียงแถวภายในกลุ่ม (แม่ต้องเป็นตัวที่ใหม่ที่สุด)
        group.rows.sort((a, b) => {
            const dA = new Date(a.cells[dateColIndex]?.textContent.trim());
            const dB = new Date(b.cells[dateColIndex]?.textContent.trim());
            return (dB || 0) - (dA || 0);
        });

        const parentRow = group.rows[0];
        const childrenRows = group.rows.slice(1);
        const hasChildren = childrenRows.length > 0;

        // --- แต่งตัวแถวแม่ ---
        parentRow.style.backgroundColor = hasChildren ? '#e6f7ff' : '#ffffff'; // ถ้าไม่มีลูกให้เป็นสีขาวปกติ
        parentRow.style.fontWeight = hasChildren ? 'bold' : 'normal';
        parentRow.style.borderLeft = hasChildren ? '5px solid #1890ff' : '1px solid #f0f0f0';
        
        // --- การรันเลขช่องแรก ---
        if (parentRow.cells[0]) {
            // ถ้ามีลูกให้ใส่ไอคอน + เลขแม่ | ถ้าไม่มีลูกให้ใส่แค่เลขเพียวๆ
            const icon = hasChildren ? `<span class="group-status" style="color:#1890ff; margin-right:5px;">◢</span>` : '';
            parentRow.cells[0].innerHTML = `${icon}${parentIndex}`;
        }

        // --- แก้ปัญหาลูกกระโดด (Event Listener) ---
        if (hasChildren) {
            parentRow.style.cursor = 'pointer';
            // ล้าง onclick เดิมป้องกันมันจำค่าซ้อน
            parentRow.onclick = null; 
            parentRow.onclick = (e) => {
                if (e.target.tagName === 'BUTTON' || e.target.tagName === 'A') return;
                
                const isNowExpanding = childrenRows[0].style.display === 'none';
                childrenRows.forEach(child => {
                    child.style.display = isNowExpanding ? '' : 'none';
                });
                
                const statusIcon = parentRow.querySelector('.group-status');
                if (statusIcon) statusIcon.innerHTML = isNowExpanding ? '◥' : '◢';
            };
        } else {
            parentRow.onclick = null;
            parentRow.style.cursor = 'default';
        }

        tbody.appendChild(parentRow);

        // --- การรันเลขแถวลูก (ถ้ามี) ---
        childrenRows.forEach((child, childIdx) => {
            child.style.display = 'none';
            child.style.backgroundColor = '#fafafa';
            if (child.cells[0]) {
                // เลขลูกจะเป็น 1.1, 1.2 และดันเข้าไปนิดนึง
                child.cells[0].innerHTML = `<span style="padding-left:15px; color:#999;">${parentIndex}.${childIdx + 1}</span>`;
            }
            tbody.appendChild(child);
        });

        parentIndex++; // เพิ่มเลขแม่คนต่อไป
    });
    
    
    
}


    // --- ส่วนของ Sorting ---
    enableSorting() {
        const headers = this.table.querySelectorAll('thead th');
        headers.forEach((th, index) => {
            // 🔥 ป้องกันที่ 1: ข้ามคอลัมน์สุดท้าย (Action/Select) ไม่ต้องให้มีปุ่ม Sort
            if (index === headers.length - 1) return;

            // เช็คว่ามีปุ่ม Sort หรือยัง
            if (th.querySelector('.sort-handle')) return;

            th.style.cursor = 'pointer';
            th.style.position = 'relative';
            
            const sortIcon = document.createElement('span');
            sortIcon.classList.add('sort-handle');
            sortIcon.innerHTML = ' ↕'; 
            sortIcon.style.opacity = '0.3';
            th.appendChild(sortIcon);

            th.addEventListener('click', (e) => {
                // ถ้ากำลัง Resize อยู่ ไม่ต้อง Sort
                if (e.target.classList.contains('resizer')) return;
                
                const currentOrder = th.getAttribute('data-order') === 'asc' ? 'desc' : 'asc';
                this.sortTable(index, currentOrder);
            });
        });

        // กู้คืนสถานะการเรียงล่าสุด
        this.restoreSort();
    }

sortTable(index, order) {
        const tbody = this.table.querySelector('tbody');
        if (!tbody) return;

        // 1. ดึงแถวทั้งหมดออกมา แต่ "จับคู่แม่ลูก" ให้ติดกันเสมอ
        const rowGroups = [];
        let currentGroup = null;

        Array.from(tbody.rows).forEach(tr => {
            // เช็คว่าแถวนี้เป็นแถวลูก (sub-row) หรือไม่
            if (tr.classList.contains('subant-table')) {
                // ถ้าเป็นแถวลูก ให้เอาไปใส่ใน group ของแถวแม่ที่เพิ่งสร้าง
                if (currentGroup) {
                    currentGroup.subRow = tr;
                }
            } else {
                // ถ้าเป็นแถวแม่ ให้สร้าง Group ใหม่
                currentGroup = { parentRow: tr, subRow: null };
                rowGroups.push(currentGroup);
            }
        });

        if (rowGroups.length <= 0) return;

        // --- 2. เช็คว่ามีระบบ Grouping หรือไม่ ---
        if (this.options.grouping) {
            const { idColIndex } = this.options.grouping;
            const groups = {};

            // แยกกลุ่ม (Grouping) โดยอิงจากแถวแม่
            rowGroups.forEach(groupPair => {
                const row = groupPair.parentRow;
                const id = row.cells[idColIndex]?.textContent.trim();
                
                if (!id) return; 
                if (!groups[id]) groups[id] = { parent: null, children: [] };
                
                if (row.style.borderLeft.includes('5px') || !groups[id].parent) {
                    if (!groups[id].parent) groups[id].parent = groupPair;
                    else groups[id].children.push(groupPair);
                } else {
                    groups[id].children.push(groupPair);
                }
            });

            const sortedGroups = Object.values(groups);

            // --- เรียงลำดับระหว่าง "กลุ่ม" ---
            sortedGroups.sort((groupA, groupB) => {
                const valA = groupA.parent.parentRow.cells[index]?.textContent.trim() || "";
                const valB = groupB.parent.parentRow.cells[index]?.textContent.trim() || "";

                let cleanA = valA.replace(/,/g, '');
                let cleanB = valB.replace(/,/g, '');

                let isNumA = cleanA !== '' && !isNaN(cleanA);
                let isNumB = cleanB !== '' && !isNaN(cleanB);

                if (isNumA && isNumB) {
                    return order === 'asc' ? Number(cleanA) - Number(cleanB) : Number(cleanB) - Number(cleanA);
                }

                return order === 'asc' 
                    ? valA.localeCompare(valB, 'th', { numeric: true }) 
                    : valB.localeCompare(valA, 'th', { numeric: true });
            });

            // --- วางแถวกลับลงไป พร้อมรันเลขใหม่ ---
            tbody.innerHTML = '';
            let parentIdx = 1;

            sortedGroups.forEach(group => {
                const parentPair = group.parent;
                if (parentPair.parentRow.cells[0]) {
                    const hasChildren = group.children.length > 0;
                    const icon = hasChildren ? `<span class="group-status" style="color:#1890ff; margin-right:5px;">${parentPair.parentRow.querySelector('.group-status')?.innerHTML.includes('◥') ? '◥' : '◢'}</span>` : '';
                    parentPair.parentRow.cells[0].innerHTML = `${icon}${parentIdx}`;
                }
                
                // วางแม่
                tbody.appendChild(parentPair.parentRow);
                if (parentPair.subRow) tbody.appendChild(parentPair.subRow); // วาง sub-row ของแม่

                // วางลูก
                group.children.forEach((childPair, childIdx) => {
                    if (childPair.parentRow.cells[0]) {
                        childPair.parentRow.cells[0].innerHTML = `<span style="padding-left:15px; color:#999;">${parentIdx}.${childIdx + 1}</span>`;
                    }
                    tbody.appendChild(childPair.parentRow);
                    if (childPair.subRow) tbody.appendChild(childPair.subRow); // วาง sub-row ของลูก
                });
                parentIdx++;
            });

        } else {
            // --- กรณีตารางปกติ (ไม่มี Grouping) ---
            rowGroups.sort((groupA, groupB) => {
                // เช็คค่าจากแถวแม่เท่านั้น
                const valA = groupA.parentRow.cells[index]?.textContent.trim() || "";
                const valB = groupB.parentRow.cells[index]?.textContent.trim() || "";

                let cleanA = valA.replace(/,/g, '');
                let cleanB = valB.replace(/,/g, '');

                let isNumA = cleanA !== '' && !isNaN(cleanA);
                let isNumB = cleanB !== '' && !isNaN(cleanB);

                if (isNumA && isNumB) {
                    return order === 'asc' 
                        ? Number(cleanA) - Number(cleanB) 
                        : Number(cleanB) - Number(cleanA);
                }

                return order === 'asc' 
                    ? valA.localeCompare(valB, 'th', { numeric: true }) 
                    : valB.localeCompare(valA, 'th', { numeric: true });
            });

            // --- วางแถวกลับลงไปใน tbody ---
            tbody.innerHTML = '';
            rowGroups.forEach((group, idx) => {
                // if (group.parentRow.cells[0]) group.parentRow.cells[0].innerHTML = idx + 1;
                
                // คืนค่าแถวแม่ลงไปก่อน
                tbody.appendChild(group.parentRow);
                
                // ถ้าแม่มีซับลูกตามมาด้วย ก็คืนค่าลูกต่อท้ายแม่ทันที
                if (group.subRow) {
                    tbody.appendChild(group.subRow);
                }
            });
        }

        // 5. อัปเดต UI ลูกศรที่ Header
        this.updateSortUI(index, order);

        // 6. Save ลง LocalStorage
        if (this.tableId) {
            localStorage.setItem(`ST_${this.tableId}_sort_col`, index);
            localStorage.setItem(`ST_${this.tableId}_sort_order`, order);
        }
    }

// แยกฟังก์ชัน UI ออกมาให้คลีนขึ้น
updateSortUI(index, order) {
    const headers = this.table.querySelectorAll('thead th');
    headers.forEach((h, i) => {
        h.removeAttribute('data-order');
        const icon = h.querySelector('.sort-handle');
        if (icon) { icon.innerHTML = ' ↕'; icon.style.opacity = '0.3'; }
    });
    const currentTh = headers[index];
    currentTh.setAttribute('data-order', order);
    const currentIcon = currentTh.querySelector('.sort-handle');
    if (currentIcon) {
        currentIcon.innerHTML = order === 'asc' ? ' ▲' : ' ▼';
        currentIcon.style.opacity = '1';
    }
}

    restoreSort() {
        if (!this.tableId) return;
        const savedCol = localStorage.getItem(`ST_${this.tableId}_sort_col`);
        const savedOrder = localStorage.getItem(`ST_${this.tableId}_sort_order`);

        if (savedCol !== null && savedOrder) {
            this.sortTable(parseInt(savedCol), savedOrder);
        }
    }

    // --- ส่วนของ Resize คงเดิม ---
    makeResizable() {
        const cols = this.table.querySelectorAll('thead th');
        cols.forEach((col, index) => {
            this.restoreColumnWidth(col, index);
            if(col.querySelector('.resizer')) return; 

            const resizer = document.createElement('div');
            resizer.classList.add('resizer');
            resizer.style.height = '100%'; 
            col.appendChild(resizer);
            this.createResizableColumn(col, resizer, index);
        });
    }

    restoreColumnWidth(col, index) {
        if (!this.tableId) return;
        const savedWidth = localStorage.getItem(`ST_${this.tableId}_col_${index}`);
        if (savedWidth) col.style.width = `${savedWidth}px`;
    }

    createResizableColumn(col, resizer, index) {
        let x = 0, w = 0;
        const mouseDownHandler = (e) => {
            e.stopPropagation(); // กันไม่ให้ไปโดน Event Sort
            x = e.clientX;
            w = parseInt(window.getComputedStyle(col).width, 10);
            document.addEventListener('mousemove', mouseMoveHandler);
            document.addEventListener('mouseup', mouseUpHandler);
            resizer.classList.add('resizing');
        };
        const mouseMoveHandler = (e) => {
            const dx = e.clientX - x;
            col.style.width = `${w + dx}px`;
        };
        const mouseUpHandler = () => {
            document.removeEventListener('mousemove', mouseMoveHandler);
            document.removeEventListener('mouseup', mouseUpHandler);
            resizer.classList.remove('resizing');
            if (this.tableId) {
                localStorage.setItem(`ST_${this.tableId}_col_${index}`, parseInt(col.style.width, 10));
            }
        };
        resizer.addEventListener('mousedown', mouseDownHandler);
    }

    enableTextToggle() {
    const cells = this.table.querySelectorAll(this.options.truncateClass || 'td');
    
    cells.forEach(cell => {
        // 1. ดึงข้อความมาเช็ค "เบื้องต้น" ก่อน เพื่อตั้งค่า Cursor
        // ถ้าข้อความมีการเคาะบรรทัด >= 2 ให้ใช้ Cursor zoom-in ถ้าไม่ถึงให้ใช้ Pointer ปกติ
        const checkContent = cell.innerHTML
		.replace(/<br\s*\/?>/gi, '\n').replace(/<\/p>/gi, '\n')
		.replace(/<[^>]+>/g, '')
		.replace(/\\r\\n/g, '\n') // แปลงรหัส \r\n (ที่เป็นข้อความ) เป็น Newline
        .replace(/\\n/g, '\n')   // แปลงรหัส \n (ที่เป็นข้อความ) เป็น Newline
		.trim();
		
		
        const initialLines = (checkContent.match(/\n/g) || []).length;

        if (initialLines >= 2) {
            cell.style.cursor = 'zoom-in'; 
            cell.title = "คลิกเพื่อดูข้อความเต็ม";
        } else {
            cell.style.cursor = 'default';
        }


			if (cell.querySelector('select, input')) {
			                cell.style.cursor = 'default';
			                return; 
			            }

        cell.onclick = (e) => {
            // กันปุ่ม กันเมนู
            if (e.target.tagName === 'BUTTON' || e.target.tagName === 'SELECT' || e.target.tagName === 'A') return;

            // 1. ดึง HTML มาจัดการเรื่องบรรทัดและล้าง Tag
            let content = e.currentTarget.innerHTML
                            .replace(/<br\s*\/?>/gi, '\n') 
                            .replace(/\\r\\n/g, '\n') // แปลงรหัส \r\n (ที่เป็นข้อความ) เป็น Newline
         				   .replace(/\\n/g, '\n')   // แปลงรหัส \n (ที่เป็นข้อความ) เป็น Newline
                            .replace(/<\/p>/gi, '\n')      
                            .replace(/<[^>]+>/g, '');      

            // 2. ตัดช่องว่างบ้าบอหน้า-หลัง
            let finalContent = content.trim(); 

            // 3. --- เงื่อนไขเด็ด: นับจำนวนการเคาะบรรทัด (\n) ---
            const newlineCount = (finalContent.match(/\n/g) || []).length;

            // ถ้าเคาะไม่ถึง 2 ครั้ง (newline < 2) -> ไม่ต้องทำอะไร จบฟังก์ชันเลย
            if (newlineCount < 2) {
                console.log("ข้อความสั้นไป ไม่เปิด Pop-up");
                return; 
            }

            // 4. ถ้าผ่านเงื่อนไข (เคาะตั้งแต่ 2 ครั้งขึ้นไป) -> ยัดลง Modal และเปิดโชว์
            const modalArea = document.getElementById('fullTextArea');
            modalArea.textContent = finalContent; 
            document.getElementById('textViewerModal').style.display = 'block';
        };
    });
}

}
// ฟังก์ชันปิด Modal (ไว้นอก Class)
function closeTextViewer() {
    document.getElementById('textViewerModal').style.display = 'none';
}