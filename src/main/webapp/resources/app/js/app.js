// เก็บสถานะแอปที่กำลังโหลด
const loadingApps = new Set();
// 1. ตัวแปรเก็บค่า (Global State)
var selectedColumns = []; 
const contextMenu_bin = document.getElementById('context-menu-bin');
// 🌟 1. สร้างตัวแปรล็อกไว้ด้านนอกฟังก์ชัน (Global Variable)
var isPageAlreadyRestored = false;
// เปลี่ยนมาใช้ Object เพื่อเก็บคิว Callback แยกตามคีย์
var callbackRegistry = {};











function clearColumnSelect() {
	selectedColumns = [];
}

function Autoclick(id) {

	document.getElementById(id).click();
}




function waitForElementAndLoad(type) {

 
 	if(type == "loadSavedColumns"){
	    // ลองหา element ดูก่อน
			  const element = document.getElementById('sorted_columns_input');
		    if (element) {

		        // ถ้าเจอแล้ว ให้ทำงานเลย
		        loadSavedColumns(element);
		       
		    } else {
		        // ถ้ายังไม่เจอ ให้รออีก 100ms แล้วเรียกตัวเองใหม่ (วนลูป)
		        console.log("Waiting for input...");
		        setTimeout(() => waitForElementAndLoad(type), 100);
		    }
	
	}
 
  
}

function loadSavedColumns(hiddenInputElement) { // รับ parameter มาเผื่อใช้
	
	   selectedColumns = [];
    // 1. ดึงค่า (ใช้ตัวที่ส่งมา หรือหาใหม่ถ้าไม่มี)
    const hiddenInput = hiddenInputElement || document.getElementById('sorted_columns_input');
    const savedValue = hiddenInput.value;

    console.log("Raw Saved Value:", savedValue); // Debug 1

    if (!savedValue || savedValue.trim() === "") {
        selectedColumns = [];
        return;
    }

    // 2. แก้เรื่องเว้นวรรค (Trim)
    selectedColumns = savedValue.split(',').map(s => s.trim()); 
    console.log("Cleaned Array:", selectedColumns); // Debug 2
    
  refreshBadges();
}




// 2. ฟังก์ชันที่ HTML จะเรียกใช้
function toggleColumnSelect(el) {
    const value = el.value;
    
    // หา <li> ที่ครอบอยู่ เพื่อไปจัดการตัวเลข Badge (ถ้ามี)
    const li = el.closest('li');
    const badge = li.querySelector('.order-badge');

    if (el.checked) {
        // --- LOGIC: ติ๊กถูก -> เพิ่มต่อท้าย ---
        selectedColumns.push(value);
    } else {
        // --- LOGIC: เอาออก -> ลบออกจาก Array ---
        selectedColumns = selectedColumns.filter(item => item !== value);
    }

    // 3. เรียกฟังก์ชันอัพเดทหน้าจอ (ตัวเลขลำดับ)
    refreshBadges();
    
    // Debug ดูค่า
    console.log("Current Selection Order:", selectedColumns);
    // document.getElementById('resultDisplay').textContent = JSON.stringify(selectedColumns);
}

// ฟังก์ชันสำหรับไล่อัพเดทตัวเลข Badge ของทุกตัว
function refreshBadges() {
    // หา Checkbox ทั้งหมดใน list (ค้นหาใหม่ทุกครั้ง เพราะ HTML อาจเพิ่งมาใหม่)
    const allCheckboxes = document.querySelectorAll('.dropdown-list input[type="checkbox"]');

						  allCheckboxes.forEach(chk => {
			        const badge = chk.closest('li').querySelector('.order-badge');
			        const index = selectedColumns.indexOf(chk.value);
			        
			        if (index > -1) {
			            // ถ้ามีใน list ให้ใส่เลข (index+1)
			            badge.textContent = index + 1;
			            badge.style.display = 'inline-flex'; // หรือ style ที่คุณใช้
			        } else {
			            badge.style.display = 'none';
			        }
			    });
			    
			    document.getElementById('sorted_columns_input').value = selectedColumns.join(',');
			    // Debug ดูว่าค่าเข้าไหม
			    console.log("Ready to save:", document.getElementById('sorted_columns_input').value);
					       
		
    
  
}







var sortOrder = {
   'title_1': 'asc',
   'title_2': 'asc',
   'title_3': 'asc',
   'title_4': 'asc',
   'title_5': 'asc',
   'title_6': 'asc'
 };
 
 
 function checkLastState(element, Id) {
    const el = document.getElementById(element + Id);
    
    // ไปดูในสมุดจดว่า ID นี้สถานะล่าสุดคืออะไร?
    const lastState = localStorage.getItem("win_state_" + Id);

    if (lastState === "fullscreen") {
        // ถ้าสถานะเดิมคือ "เต็มจอ" -> สั่งให้มันเต็มจอเลยทันที!
        el.classList.add("fullscreen");
        el.style.top = "";
        el.style.left = "";
    } else {
        // ถ้าสถานะเดิมคือ "ปกติ" หรือ "ไม่เคยบันทึก" -> ก็เปิดแบบปกติ (ไม่ต้องทำอะไร)
        el.classList.remove("fullscreen");
    }
}
function expand(element, Id) {
  const el = document.getElementById(element + Id);
  
  // สลับคลาส fullscreen
  const isNowFullscreen = el.classList.toggle("fullscreen");

  if (isNowFullscreen) {
    // 👉 กรณี: ขยายเต็มจอ
    
    // 1. จำตำแหน่งเดิมไว้ก่อน (เผื่อหุบกลับ)
    el.dataset.prevTop = el.style.top || "";
    el.dataset.prevLeft = el.style.left || "";
    
    // 2. ล้างค่า CSS เพื่อให้เต็มจอ
    el.style.top = "";
    el.style.left = "";
    
    // 🔥 3. บันทึกลงเครื่องลูกค้าว่า "หน้าต่างนี้ ID นี้ เต็มจออยู่นะ"
    localStorage.setItem("win_state_" + Id, "fullscreen");

  } else {
    // 👉 กรณี: ย่อกลับ (หุบ)
    
    // 1. คืนค่าตำแหน่งเดิม
    if (el.dataset.prevTop) el.style.top = el.dataset.prevTop;
    if (el.dataset.prevLeft) el.style.left = el.dataset.prevLeft;
    
    // 🔥 2. บันทึกลงเครื่องลูกค้าว่า "หน้าต่างนี้ ID นี้ หุบแล้วนะ"
    localStorage.setItem("win_state_" + Id, "normal");
  }
}


var sortOrder = {}; 
const columnList = ['title_1', 'title_2', 'title_3', 'title_4', 'title_5', 'title_6', 'title_7', 'title_8'];

function sorted(column, forceOrder = null) {
    var table = document.querySelector('.indexapptable');
    if (!table) return; 

    var tableId = table.getAttribute('id') || 'default_table';
    var storageKey = 'tableSort_' + tableId;
    var arrowIcon = document.getElementById('arrow_' + column);

    if (forceOrder) {
        sortOrder[column] = forceOrder;
    } else {
        sortOrder[column] = sortOrder[column] === 'asc' ? 'desc' : 'asc';
    }

    var saveObj = {
        col: column,
        ord: sortOrder[column]
    };
    localStorage.setItem(storageKey, JSON.stringify(saveObj));

    columnList.forEach(function(c) {
        if (c !== column) {
            var otherIcon = document.getElementById('arrow_' + c);
            if (otherIcon) {
                otherIcon.innerHTML = ''; 
            }
        }
    });

    if (arrowIcon) {
        arrowIcon.innerHTML = sortOrder[column] === 'asc' ? 'arrow_upward' : 'arrow_downward';
    }

    var rows = Array.from(table.querySelectorAll('.tr_datatable'));
    if (rows.length === 0) return;

    // ✨ แก้ไข 1: เปลี่ยนจาก + 1 เป็น + 2 เพื่อข้ามคอลัมน์ว่างด้านหน้าสุดไป
    var colIndex = columnList.indexOf(column) + 2; 
    
    // ถ้าหาคอลัมน์ไม่เจอใน columnList (indexOf จะได้ -1) แล้ว -1+2 = 1 ให้ออกจากการทำงานไปเลย
    if (colIndex === 1) return; 
    
    rows.sort(function (a, b) {
        var tdA = a.querySelector('td:nth-child(' + colIndex + ')');
        var tdB = b.querySelector('td:nth-child(' + colIndex + ')');
        
        var aValue = tdA ? tdA.textContent.trim() : '';
        var bValue = tdB ? tdB.textContent.trim() : '';

        function parseValue(val) {
            if (val === '') return val; // กันเหนียวกรณีค่าว่าง

            // 1. ดักจับตัวเลขที่มีลูกน้ำ
            var numVal = Number(val.replace(/,/g, ''));
            if (!isNaN(numVal)) {
                return numVal;
            }
            
            // 2. ดักจับวันที่ 
            if (val.includes('-') || val.includes('/')) {
                // ✨ แก้ไข 2: เปลี่ยนช่องว่างให้เป็น 'T' (เช่น 2026-03-05T09:32) 
                // เพื่อให้ Date.parse อ่านออกในทุกเบราว์เซอร์อย่างแม่นยำ
                var dateStr = val.replace(' ', 'T'); 
                var parsedDate = Date.parse(dateStr);
                if (!isNaN(parsedDate)) {
                    return parsedDate; 
                }
            }
            
            // 3. ถ้าไม่ใช่ทั้งคู่ คืนค่าเป็นข้อความธรรมดา
            return val;
        }

        var valA = parseValue(aValue);
        var valB = parseValue(bValue);
        var result;

        if (typeof valA === 'number' && typeof valB === 'number') {
            result = valA - valB; 
        } else {
            result = String(valA).localeCompare(String(valB), undefined, { numeric: true, sensitivity: 'base' });
        }

        return sortOrder[column] === 'asc' ? result : -result;
    });

    var parent = rows[0].parentNode;
    rows.forEach(function (row) {
        parent.appendChild(row);
    });
}


function searchTable() {
    // 1. ดึงค่าจากช่อง input
    var input = document.getElementById('searchInput');
    if (!input) return; // กัน Error
    
    var filter = input.value.toLowerCase().trim();
    
    // 2. ดึงตารางและแถว
    var table = document.querySelector('.indexapptable');
    if (!table) return;

    var rows = table.querySelectorAll('.tr_datatable');

    // 3. วนลูปเช็คทีละแถว
    rows.forEach(function(row) {
        // เอาข้อความทั้งหมดในแถวนั้นมาต่อกัน (รวมทุกคอลัมน์)
        var textContent = row.textContent || row.innerText;
        
        // เช็คว่ามีคำที่ค้นหาไหม (ใช้ includes)
        if (textContent.toLowerCase().includes(filter)) {
            row.style.display = ''; // เจอ: แสดง
        } else {
            row.style.display = 'none'; // ไม่เจอ: ซ่อน
        }
    });
}












function sorteds(column) {

   var arrowIcon = document.getElementById('arrows_' + column);
   var table = document.querySelector('.datatable2');
   var rows = Array.from(table.querySelectorAll('.tr_datatable2'));

   sortOrder[column] = sortOrder[column] === 'asc' ? 'desc' : 'asc';

   arrowIcon.innerHTML = sortOrder[column] === 'asc' ? 'arrow_upward' : 'arrow_downward';

   rows.sort(function (a, b) {
     var aValue = a.querySelector('td:nth-child(' + (['title_1', 'title_2', 'title_3', 'title_4', 'title_5', 'title_6'].indexOf(column) + 1) + ')').textContent.trim();
     var bValue = b.querySelector('td:nth-child(' + (['title_1', 'title_2', 'title_3', 'title_4', 'title_5', 'title_6'].indexOf(column) + 1) + ')').textContent.trim();

     if (sortOrder[column] === 'asc') {
       return aValue.localeCompare(bValue);
     } else {
       return bValue.localeCompare(aValue);
     }
   });

   rows.forEach(function (row) {
     table.querySelector('tbody').appendChild(row);
   });
}


function search(filter, list) {


var filterValue = document.getElementById(filter).value;
var elements = document.getElementsByClassName(list);

for (var i = 0; i < elements.length; i++) {
 var element = elements[i];
 var textContent = element.textContent || element.innerText;

 if (new RegExp(filterValue, 'ig').test(textContent)) {
   element.style.display = 'table-row';
 } else {
   element.style.display = 'none';
 }
}
}

function searchs(filter,selectId1,selectId2,selectId3, list) {


   var filterValue = document.getElementById(filter).value;
   var selectElement1 = document.getElementById(selectId1);
  var selectedValue1 = selectElement1.options[selectElement1.selectedIndex].text;
  var selectElement2 = document.getElementById(selectId2);
  var selectedValue2 = selectElement2.options[selectElement2.selectedIndex].text;
  var selectElement3 = document.getElementById(selectId3);
  var selectedValue3 = selectElement3.options[selectElement3.selectedIndex].text;

   //alert(selectedValue1+","+selectedValue2+","+selectedValue3);

   var elements = document.getElementsByClassName(list);
   
   for (var i = 0; i < elements.length; i++) {
    var element = elements[i];
    var textContent = element.textContent || element.innerText;
   
    if (new RegExp(filterValue, 'ig').test(textContent)) {
      element.style.display = 'table-row';
    } else {
      element.style.display = 'none';
    }
         if(selectedValue1 != "ALL"){

            if (new RegExp(selectedValue1, 'ig').test(textContent)) {
               element.style.display = 'table-row';
            } else {
               element.style.display = 'none';
            }

         }
         if(selectedValue2 != "ALL"){

            if (new RegExp(selectedValue2, 'ig').test(textContent)) {
               element.style.display = 'table-row';
            } else {
               element.style.display = 'none';
            }

         }
         if(selectedValue3 != "ALL"){

            if (new RegExp(selectedValue3, 'ig').test(textContent)) {
               element.style.display = 'table-row';
            } else {
               element.style.display = 'none';
            }

         }



   }
   }



function search2(filter, list, selectId, titleId) {
   var filterValue = document.getElementById(filter).value.toLowerCase();
   var elements = document.getElementsByClassName(list);

   for (var i = 0; i < elements.length; i++) {
       var element = elements[i];
       var textContent = element.textContent || element.innerText;

       // Additional condition for searching based on select element
       var selectElement = document.getElementById(selectId);
       var selectedValue = selectElement.options[selectElement.selectedIndex].text.toLowerCase();

       if (new RegExp(filterValue, 'ig').test(textContent) || new RegExp(filterValue, 'ig').test(selectedValue)) {
           element.style.display = 'table-row';
       } else {
           element.style.display = 'none';
       }
   }

   // Reset sorting icons when performing a new search
  // resetSortingIcons();

   // Highlight the selected title for better user feedback
  // highlightSelectedTitle(titleId);
}






var data = {
   id: 2,
   rows: [
      {
         key: 0,
         name: 'Task 1',
         path: '/some/path',
         startDate: '2017-02-15',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      },
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
	  
	  ,
      {
         key: 1,
         name: 'Different',
         path: '/some/other/path',
         startDate: '2017-02-10',
         startTime: '00:00:00',
         endDate: '',
         endTime: '',
         repeatEvery: undefined,
         interval: 'Daily',
         days: '3,5',
         weekNums: undefined
      }
   ]
}






function set_title_app(app,title){

   var apps = document.getElementById(app);
   apps.innerHTML = title;
}






/*----------------| Application |------------------*/

   //Fake some JQuery
   var $ = function(str){ 
      var ele = document.querySelectorAll(str);
      for(var i = 0; i < ele.length; i++){
         ele[i].on = function(event, func){ this.addEventListener(event, func); }
      }
      return ele;
   };
   
   //Fake a Framework
   var app = {
      data: data,
      new: $('#new')[0],
      close: $('#close')[0],
      del: $('#del')[0],
      save: $('#save')[0],
      jobs: $('#body table tbody')[0],
      modalModify: $('#modify')[0],
      inputs: $('.body form input, .body form select'),
      editing: undefined,
      init: function(){
         //Buttons
    //   app.new.on('click', app.newJob);
      //    app.close.on('click', app.closeModify);
      //        app.save.on('click', app.saveJob);
       //     app.del.on('click', app.delJob);

      //      app.closeModify();
      },
      openModify: function(){
         app.modalModify.classList.remove('close');
      },
      closeModify: function(){
         app.loadJobs();
     app.modalModify.classList.add('close');
      },



      loadJobs: function(){

         /*
         app.jobs.innerHTML = "";
         this.data.rows.forEach(function(job, index){
            var jobID = 'i_' + job.key;
            var template = `
               <tr class='jobrow' id='${jobID}'>
                  <td>${job.name}</td>
                  <td>${job.path}</td>
                  <td>${job.startDate} ${job.startTime}</td>
                  <td>${job.interval}</td>
               </tr>`;
             app.jobs.innerHTML += template;
         });
         var rows = $('.jobrow'); var len = rows.length || 1;
         for(var i = 0; i < rows.length; i++){
            rows[i].on('click', app.openJob);
         }
    */

      },
      openJob: function(){
         app.editing = app.getJobIndex(this.id.split('_')[1]);
         for(var i = 0; i < app.inputs.length; i++){
            app.inputs[i].value = app.data.rows[app.editing][app.inputs[i].getAttribute('name')] || "";
         }
         app.openModify();
      },
      newJob: function(){
         for(var i = 0; i < app.inputs.length; i++){
            app.inputs[i].value = "";
         }
         $('input[name=key]').value = app.data.id; 
         app.editing = app.data.id;
         app.data.id += 1;
         app.openModify();
      },
      saveJob: function(){
         var editing = {key: app.editing};
         for(var i = 0; i < app.inputs.length; i++){
            editing[app.inputs[i].getAttribute('name')] = app.inputs[i].value;
         }
         app.data.rows[app.getJobIndex(app.editing)] = editing;
         app.closeModify();
      },
      delJob: function(){
         app.data.rows.splice(app.editing, 1);
         app.closeModify();
      },
      getJobIndex: function(key){
         for(var i = 0; i < app.data.rows.length; i++){
            if(app.data.rows[i].key == Number(key))
                break;
         }
         return i;
      }
   }

   app.init();



/* ฟังก์ชันสำหรับบังคับรัน Script ใน Div ที่โหลดมา */
function runScripts(element) {
    // 1. หา tag <script> ทั้งหมดใน div นั้น
    var scripts = element.getElementsByTagName("script");
    var scriptArray = [];

    // 2. copy มาใส่ array ชั่วคราว (เพราะถ้าเปลี่ยน DOM เลย loop จะเพี้ยน)
    for (var i = 0; i < scripts.length; i++) {
        scriptArray.push(scripts[i]);
    }

    // 3. วนลูปสร้าง script ใหม่เพื่อรัน
    for (var i = 0; i < scriptArray.length; i++) {
        var oldScript = scriptArray[i];
        var newScript = document.createElement("script");

        // ก๊อปปี้ Attributes ทั้งหมด (เช่น src, type)
        if (oldScript.attributes) {
            for (var j = 0; j < oldScript.attributes.length; j++) {
                var attr = oldScript.attributes[j];
                newScript.setAttribute(attr.name, attr.value);
            }
        }

        // ก๊อปปี้ Code ข้างใน
        newScript.text = oldScript.innerHTML;

        // 4. ลบอันเก่าที่ตายอยู่ แล้วใส่ยัดอันใหม่ที่ "มีชีวิต" เข้าไปแทน
        oldScript.parentNode.replaceChild(newScript, oldScript);
    }
}

function bringToFront(elementId) {
    indexs = indexs+2; // ตัวแปร Global indexs
    
    var win = document.getElementById('draggable-windows' + elementId);
    
    if (win) {
        // 1. อัปเดต z-index
        win.style.zIndex = indexs;

        // 2. รัน Animation (ถอดออกแล้วใส่ใหม่เพื่อให้เล่นใหม่ทุกครั้งที่คลิก)
        win.classList.remove('window-focus-animate');
        
        // บังคับให้ Browser ทำการ Reflow เพื่อให้ Animation เริ่มใหม่ได้
        void win.offsetWidth; 
        
        win.classList.add('window-focus-animate');
        
        // 3. เพิ่มความนวลด้วยเงา (Shadow) ให้หน้าต่างที่อยู่บนสุดดูเด่นกว่าตัวล่าง
        // ลบเงาจากวินโดว์อื่น (ถ้ามี) แล้วใส่ให้ตัวนี้
        document.querySelectorAll('.window.microsoft').forEach(el => el.style.boxShadow = 'none');
        win.style.boxShadow = '0 8px 20px rgba(0,0,0,0.2)';
    }
}
// ฟังก์ชันกลางสำหรับเปิด App และโหลดข้อมูล
function openAppAndLoadData(refValue, processId,from = 0) {
    var targetDivId = 'microsofts' + processId;
    var targetDiv = document.getElementById(targetDivId);



    // เช็คว่าหน้าต่าง App เปิดอยู่หรือยัง?
    if (!targetDiv) {
        // --- กรณี: หน้าต่างยังไม่เปิด (ต้องสั่งเปิดก่อน) ---
        console.log("App ยังไม่เปิด กำลังสั่งเปิด Process ID: " + processId);

 
            var config = GLOBAL_APP_CONFIG[processId];
            if(config) {
                // สร้าง Element ปลอมๆ เพื่อส่งให้ฟังก์ชัน App(this)
                var fakeElement = document.createElement('div');
                fakeElement.dataset.id = config.id;
                fakeElement.dataset.name = config.name;
                fakeElement.dataset.link = config.link;
                fakeElement.dataset.logo = config.logo;
                fakeElement.dataset.box = " [In Progress]"; // ค่าสมมติ
                 fakeElement.dataset.startapponly = 1;
                // เรียกฟังก์ชันเปิด App
                if(typeof App === "function") {
                    App(fakeElement); 
                }
                
              
                
            } else {
                alert("ไม่พบข้อมูล Application ID: " + processId);
                return;
            }
      
    }else{
		bringToFront(processId);
	}

    // --- สั่งโหลดข้อมูล (ใส่ Timeout นิดหน่อย เพื่อรอให้หน้าต่าง App สร้างเสร็จ) ---
    setTimeout(function() {
        // เช็คอีกรอบเพื่อความชัวร์
        if(document.getElementById(targetDivId)) {
             getData('/profileapp-filemanager/' + refValue + '/' + processId + '?from='+from, targetDivId);
             
             // สั่ง Focus หน้าต่าง (ถ้ามีฟังก์ชัน bringToFront)
             // bringToFront(targetDivId); 
        } else {
             console.error("ไม่สามารถเปิดหน้าต่าง App ได้");
        }
    }, 100); // รอ 0.5 วินาที (ปรับเพิ่มลดได้ตามความเร็วเครื่อง)
}




// ฟังก์ชันกลางสำหรับเปิด App และโหลดข้อมูล
function openReportApp(data_id,useformPrintId) {
	
	var nextprocessId = 9910;
    var targetDivId = 'microsofts' + nextprocessId;
    var targetDiv = document.getElementById(targetDivId);
    
    
    // เช็คว่าหน้าต่าง App เปิดอยู่หรือยัง?
    if (!targetDiv) {
        // --- กรณี: หน้าต่างยังไม่เปิด (ต้องสั่งเปิดก่อน) ---
        console.log("App ยังไม่เปิด กำลังสั่งเปิด Process ID: " + nextprocessId);

        // วิธีที่ 1: สั่ง Trigger Event Double Click ที่ไอคอน App (ง่ายสุด ถ้าไอคอนอยู่ในหน้านั้น)

            // วิธีที่ 2: (กรณีหาไอคอนไม่เจอ) เรียกฟังก์ชัน App() ตรงๆ โดยจำลอง Object ส่งเข้าไป
            // ต้องเช็คก่อนว่าฟังก์ชัน App() ของคุณรับ parameter แบบไหน
            // สมมติว่ารับ element ที่มี dataset
            var config = GLOBAL_APP_CONFIG[nextprocessId];
            if(config) {
                // สร้าง Element ปลอมๆ เพื่อส่งให้ฟังก์ชัน App(this)
                var fakeElement = document.createElement('div');
                fakeElement.dataset.id = config.id;
                fakeElement.dataset.name = config.name;
                fakeElement.dataset.link = config.link;
                fakeElement.dataset.logo = config.logo;
                fakeElement.dataset.box = " [Print]"; // ค่าสมมติ
                fakeElement.dataset.startapponly = 1;
                // เรียกฟังก์ชันเปิด App
                if(typeof App === "function") {
                    App(fakeElement); 
                }
            } else {
                alert("ไม่พบข้อมูล Application ID: " + nextprocessId);
                return;
            }
        
     }else{
		bringToFront(processId);
	}

    // --- สั่งโหลดข้อมูล (ใส่ Timeout นิดหน่อย เพื่อรอให้หน้าต่าง App สร้างเสร็จ) ---
    setTimeout(function() {
        // เช็คอีกรอบเพื่อความชัวร์
        if(document.getElementById(targetDivId)) {
             getData('/qcompro-filemanager/'+nextprocessId+'?data_id='+data_id+"&&useformPrintId="+useformPrintId,'microsofts'+nextprocessId);
    
             // สั่ง Focus หน้าต่าง (ถ้ามีฟังก์ชัน bringToFront)
             // bringToFront(targetDivId); 
        } else {
             console.error("ไม่สามารถเปิดหน้าต่าง App ได้");
        }
    }, 500); // รอ 0.5 วินาที (ปรับเพิ่มลดได้ตามความเร็วเครื่อง)
}







// ฟังก์ชันกลางสำหรับเปิด App และโหลดข้อมูล
function openAppAndLoadData2(nextprocessId,tabId,apptabId,token) {
    var targetDivId = 'microsofts' + nextprocessId;
    var targetDiv = document.getElementById(targetDivId);
    
    
    if (!confirm('Are you sure you want to create the document?')){
		return false;
	}

    // เช็คว่าหน้าต่าง App เปิดอยู่หรือยัง?
    if (!targetDiv) {
        // --- กรณี: หน้าต่างยังไม่เปิด (ต้องสั่งเปิดก่อน) ---
        console.log("App ยังไม่เปิด กำลังสั่งเปิด Process ID: " + nextprocessId);

        // วิธีที่ 1: สั่ง Trigger Event Double Click ที่ไอคอน App (ง่ายสุด ถ้าไอคอนอยู่ในหน้านั้น)

            // วิธีที่ 2: (กรณีหาไอคอนไม่เจอ) เรียกฟังก์ชัน App() ตรงๆ โดยจำลอง Object ส่งเข้าไป
            // ต้องเช็คก่อนว่าฟังก์ชัน App() ของคุณรับ parameter แบบไหน
            // สมมติว่ารับ element ที่มี dataset
            var config = GLOBAL_APP_CONFIG[nextprocessId];
            if(config) {
                // สร้าง Element ปลอมๆ เพื่อส่งให้ฟังก์ชัน App(this)
                var fakeElement = document.createElement('div');
                fakeElement.dataset.id = config.id;
                fakeElement.dataset.name = config.name;
                fakeElement.dataset.link = config.link;
                fakeElement.dataset.logo = config.logo;
                fakeElement.dataset.box = " [In Progress]"; // ค่าสมมติ
                fakeElement.dataset.startapponly = 1;
                // เรียกฟังก์ชันเปิด App
                if(typeof App === "function") {
                    App(fakeElement); 
                }
            } else {
                alert("ไม่พบข้อมูล Application ID: " + nextprocessId);
                return;
            }
        
     }else{
	bringToFront(processId);
	}

    // --- สั่งโหลดข้อมูล (ใส่ Timeout นิดหน่อย เพื่อรอให้หน้าต่าง App สร้างเสร็จ) ---
    setTimeout(function() {
        // เช็คอีกรอบเพื่อความชัวร์
        if(document.getElementById(targetDivId)) {
             postData('/newfile2-filemanager' +'?'+token,'newfile-'+tabId,'microsofts'+nextprocessId);
             
             
              refreshCurrentTab(apptabId); 
             
             // สั่ง Focus หน้าต่าง (ถ้ามีฟังก์ชัน bringToFront)
             // bringToFront(targetDivId); 
        } else {
             console.error("ไม่สามารถเปิดหน้าต่าง App ได้");
              
        }
    }, 500); // รอ 0.5 วินาที (ปรับเพิ่มลดได้ตามความเร็วเครื่อง)
}



//ฟังก์ชันสำหรับหา Tab ปัจจุบันของ App นั้นๆ
function getCurrentTabId(appId) {
    
	
	
	
	// หา element li ที่ id ขึ้นต้นด้วย tab-link-appId_ และมี class tabselect
    const activeTab = document.querySelector(`li[id^="tab-link-${appId}"].tabselect`);
    
    if (activeTab) {
    	
    	
        // activeTab.id จะเป็นรูปแบบ "tab-link-appId_tabId" 
        // เราจะหั่น string ด้วย "_" เพื่อเอาตัวเลข tabId ตัวหลังสุดมาใช้
        const parts = activeTab.id.split('_');
        return parts[1]; // คืนค่า tabId
    }
    return null; // เผื่อกรณีหาไม่เจอ
}

// ฟังก์ชันสำหรับสั่งรีเฟรชข้อมูลใน Tab ปัจจุบัน
function refreshCurrentTab(appId) {
  const currentTabId = getCurrentTabId(appId);
    
    // หา Element ของตัว Tab link (สมมติว่า ID คือ tab-link-ID)
    const tabElement = document.getElementById('tab-link-' + currentTabId);
    
    if (tabElement) {
        tabElement.click(); // จำลองการคลิกด้วยนิ้วคน
    }
}



function App(element) {
    const elementId = element.dataset.id;
    
    
     var badgeElement = document.getElementById("badge-" + elementId);

        if (badgeElement) {
			badgeElement.innerText = "0";
            badgeElement.style.display = 'none'; // หรือ 'block'
           } 
    
    // 1. ถ้า ID นี้อยู่ในรายการที่กำลังโหลด หรือ มีวินโดว์อยู่แล้ว ให้หยุดทำงานทันที
    if (loadingApps.has(elementId) || document.getElementById('draggable-windows' + elementId)) {
        console.log("App is already loading or opened.");
        return; 
    }

    // 2. มาร์คไว้ว่า ID นี้เริ่มทำงานแล้วนะ
    loadingApps.add(elementId);

    const name = element.dataset.name;
    const logo = element.dataset.logo;
    const box = element.dataset.box;
    const link = element.dataset.link;
    const startAppOnly = element.dataset.startapponly;

    if (Apphas(elementId) == false) {
        alert("cannot open ...");
        loadingApps.delete(elementId); // ปลดล็อคถ้าเปิดไม่ได้
        return;
    }

    // สร้างหน้าต่าง
    newWindow(elementId);

        checkLastState("draggable-windows", elementId);


    if (startAppOnly == 0) {
        // ส่ง callback ไปกับ openApp เพื่อให้ปลดล็อคเมื่อโหลดเสร็จ (ถ้าฟังก์ชัน openApp รองรับ)
        openApp(link + elementId, 'microsofts' + elementId);
    }
	
	

    Addtaskbar(element);

    var title = document.getElementById('app' + elementId);
    title.innerText = name + box;

    var icon = document.getElementById('icon' + elementId);
    icon.innerHTML = " <img src='" + logo + "' style='width:25px'>";

    var winElement = document.getElementById('draggable-windows' + elementId);
    winElement.classList.remove('slide-out');
    winElement.style.display = 'block';

    // 3. ปลดล็อคสถานะเมื่อรันโค้ดส่วนสร้างเสร็จ (หรือย้ายไปไว้ใน openApp success callback)
    // เพื่อให้กดเปิดใหม่ได้หลังจากปิดแอปไปแล้ว
    setTimeout(() => {
        loadingApps.delete(elementId);
    }, 500); 
}





 function loadMangementApp() {
	  

var ra = {
  el: {
    window: document.getElementById('window-body'),
    body: document.getElementById('ra-body'),
    application: document.getElementById('ra-application'),
    header: document.getElementById('ra-header'),
    workspace: document.getElementById('ra-workspace'),
    spine: document.getElementById('ra-spine'),
    asideLeft: document.getElementById('ra-aside-left'),
    asideRight: document.getElementById('ra-aside-right'),
    smoke: document.getElementById('ra-smoke'),
    smokebreak: document.querySelectorAll('.ra-smokebreak'), // NodeList
    ctaModal: document.getElementById('ra-app-cta-modal'),
    notificationDispenser: document.querySelector('#ra-notification-dispenser > ul')
  },
  defaults: {
    headbuttSize: 200
  },
  templates: {
    headbutt: '<article class="ra-headbutt"><span class="order">1</span><span class="area-close"></span></article>',
    notification: '<li class="ra-notification"><span class="area-close"></span><ul><li class="ra-line clickable random-length"></li><li class="ra-line clickable random-length"></li></ul></li>',
    card: '<article class="ra-card"><ul class="ra-card-action-bar"><li class="ra-line random-length small link clickable"></li><li class="ra-line random-length small link clickable"></li></ul></article>'
  },
  vars: {
    appname: 'Workflow Application',
    appcontext: 'Lighthouse Accounting BVBA',
    apppersona: 'Anna Copeland',
    notificationDispenserLocation: 'left'
  }
};

// ======================== HELPER FUNCTIONS (แทน $ และ $$ ) ========================
function $(selector, context) {
  context = context || document;
  return context.querySelector(selector);
}
function $$(selector, context) {
  context = context || document;
  return Array.prototype.slice.call(context.querySelectorAll(selector));
}

// ======================== UTILITIES ========================
function hasClass(el, cls) {
  return (' ' + el.className + ' ').indexOf(' ' + cls + ' ') > -1;
}
function addClass(el, cls) {
  if (!hasClass(el, cls)) el.className += ' ' + cls;
}
function removeClass(el, cls) {
  el.className = el.className.replace(new RegExp('(\\s|^)' + cls + '(\\s|$)', 'g'), ' ').replace(/^\s+|\s+$/g, '');
}
function toggleClass(el, cls) {
  if (hasClass(el, cls)) removeClass(el, cls);
  else addClass(el, cls);
}

// ======================== INIT ========================
document.addEventListener('DOMContentLoaded', function() {
  init();
  window.onresize = renderWorkspace;
});



// ======================== EDITOR ========================
document.addEventListener('click', function(e) {
  var t = e.target;

  // Editor tabs
  if (t.parentNode && t.parentNode.parentNode && t.parentNode.parentNode.id === 'editor' &&
      t.tagName === 'LI' && t.getAttribute('data-tab')) {
    showEditorTab(t.getAttribute('data-tab'));
  }

  // Editor handle
  if (t.id === 'editor-handle') {
    toggleClass(document.getElementById('editor'), 'open');
    toggleBackdrop();
  }

  // Radio select
  if (t.getAttribute('data-value') && hasClass(t.parentNode, 'editor-radioselect')) {
    var lis = t.parentNode.getElementsByTagName('li');
    for (var i = 0; i < lis.length; i++) removeClass(lis[i], 'selected');
    addClass(t, 'selected');

    var key = t.parentNode.getAttribute('data-ra-var');
    var val = t.getAttribute('data-value');
    var elems = $$('*[data-ra-var-radioselect]');
    for (var j = 0; j < elems.length; j++) {
      elems[j].setAttribute('data-ra-var-value', val);
    }
  }
});

document.addEventListener('keyup', function(e) {
  var t = e.target;
  if (t.tagName === 'INPUT' && t.type === 'text' && t.getAttribute('data-ra-var')) {
    var key = t.getAttribute('data-ra-var');
    var elems = $$('*[data-ra-var-text="' + key + '"]');
    for (var i = 0; i < elems.length; i++) {
      elems[i].textContent = t.value;
    }
  }
});

function initEditor() {
  for (var key in ra.vars) {
    if (!ra.vars.hasOwnProperty(key)) continue;
    var val = ra.vars[key];

    // input text
    var input = $('#editor input[data-ra-var="' + key + '"]');
    if (input) input.value = val;

    // text placeholders
    var texts = $$('*[data-ra-var-text="' + key + '"]');
    for (var i = 0; i < texts.length; i++) {
      texts[i].textContent = val;
    }

    // radio selects
    var radio = $('.editor-radioselect[data-ra-var="' + key + '"]');
    if (radio) {
      var lis = radio.getElementsByTagName('li');
      for (var j = 0; j < lis.length; j++) removeClass(lis[j], 'selected');
      var sel = $(`li[data-value="${val}"]`, radio);
      if (sel) addClass(sel, 'selected');
    }
  }
}

function showEditorTab(tab) {
  var headers = $$('#editor header menu li');
  for (var i = 0; i < headers.length; i++) removeClass(headers[i], 'active');
  var active = $('#editor header menu li[data-tab="' + tab + '"]');
  if (active) addClass(active, 'active');

  var tabs = $$('#editor-body .tab');
  for (var j = 0; j < tabs.length; j++) removeClass(tabs[j], 'open');
  var open = $('#editor-body .tab[data-tab="' + tab + '"]');
  if (open) addClass(open, 'open');
}

function toggleBackdrop() {
  toggleClass(document.getElementById('container'), 'masked');
}

// ======================== WORKSPACE & HEADBUTT ========================
function renderWorkspace() {
  var hb = getHeadbutts();
  var bodyW = ra.el.body.offsetWidth;

  ra.el.asideLeft.style.width = hb.left.width + 'px';
  ra.el.asideRight.style.width = hb.right.width + 'px';
  ra.el.workspace.style.width = (bodyW - hb.all.width) + 'px';
}

function getHeadbutts() {
  var left  = $$('.ra-headbutt', ra.el.asideLeft);
  var right = $$('.ra-headbutt', ra.el.asideRight);

  var leftW = 0, rightW = 0;
  for (var i = 0; i < left.length; i++)  leftW += left[i].offsetWidth;
  for (var i = 0; i < right.length; i++) rightW += right[i].offsetWidth;

  return {
    left:  { count: left.length,  width: leftW },
    right: { count: right.length, width: rightW },
    all:   { count: left.length + right.length, width: leftW + rightW }
  };
}

// Add headbutt
document.addEventListener('click', function(e) {
  if (e.target.getAttribute('data-action') === 'add-headbutt') {
    addHeadbutt(e.target.getAttribute('data-side') || 'right');
  }
});

function addHeadbutt(side) {
  side = side || 'right';
  if (ra.el.workspace.offsetWidth <= ra.defaults.headbuttSize) {
    alert('No more space for headbutts');
    return;
  }

  var hbData = getHeadbutts();
  var order = (side === 'left') ? hbData.left.count : hbData.right.count + 1;

  var div = document.createElement('div');
  div.innerHTML = ra.templates.headbutt.replace('>1<', '>' + order + '<');
  var hb = div.firstChild;

  if (side === 'left') ra.el.asideLeft.appendChild(hb);
  else ra.el.asideRight.appendChild(hb);

  renderWorkspace();
}

// Close headbutt
ra.el.body.addEventListener('click', function(e) {
  var t = e.target;
  if (hasClass(t, 'area-close') && hasClass(t.parentNode, 'ra-headbutt')) {
    closeHeadbutt(t.parentNode);
  }
});

function closeHeadbutt(hb) {
  hb.parentNode.removeChild(hb);
  updateHeadbuttOrders();
  renderWorkspace();
}

function updateHeadbuttOrders() {
  var left = $$('.ra-headbutt', ra.el.asideLeft);
  for (var i = 0; i < left.length; i++) {
    left[i].querySelector('.order').textContent = i;
  }
  var right = $$('.ra-headbutt', ra.el.asideRight);
  for (var i = 0; i < right.length; i++) {
    right[i].querySelector('.order').textContent = i + 1;
  }
}

// ======================== CARD ========================
function addCard(container, shape) {
  shape = shape || 'square';

  var div = document.createElement('div');
  div.innerHTML = ra.templates.card;
  var card = div.firstChild;
  addClass(card, 'ra-card-' + shape);

  var btns = card.querySelectorAll('.clickable');
  for (var i = 0; i < btns.length; i++) {
    btns[i].setAttribute('data-action', 'add-headbutt');
    btns[i].setAttribute('data-side', 'right');
  }

  container.appendChild(card);
}

// ======================== SMOKEBREAK ========================
document.addEventListener('click', function(e) {
  var t = e.target;

  // เปิด
  if (t.getAttribute('data-action') === 'open-smokebreak' ||
      (t.id && hasClass(t, 'ra-bubble')) ||
      hasClass(t, 'ra-service')) {
    //openSmokebreak();
  }

  // ปิด
  if (hasClass(t, 'area-close') && hasClass(t.parentNode, 'ra-smokebreak') ||
      t.id === 'ra-smoke') {
    closeSmokebreak();
  }
});

function openSmokebreak() {
  for (var i = 0; i < ra.el.smokebreak.length; i++) {
    addClass(ra.el.smokebreak[i], 'open');
  }
  addClass(ra.el.application, 'masked');
}
function closeSmokebreak() {
  for (var i = 0; i < ra.el.smokebreak.length; i++) {
    removeClass(ra.el.smokebreak[i], 'open');
  }
  removeClass(ra.el.application, 'masked');
}

// ======================== NOTIFICATION ========================
ra.el.notificationDispenser.parentNode.addEventListener('click', function(e) {
  var t = e.target;
  if (hasClass(t, 'area-close') && hasClass(t.parentNode, 'ra-notification')) {
    popNotification(t.parentNode);
  }
});

document.addEventListener('click', function(e) {
  if (e.target.getAttribute('data-action') === 'push-notification') {
    pushNotification(e.target.getAttribute('data-type') || 'info');
  }
  if (e.target.getAttribute('data-location')) {
    setNotificationDispenserLocation(e.target.getAttribute('data-location'));
  }
});

function pushNotification(type) {
  type = type || 'info';

  var div = document.createElement('div');
  div.innerHTML = ra.templates.notification;
  var noti = div.firstChild;
  addClass(noti, 'pushing ra-' + type);

  ra.el.notificationDispenser.appendChild(noti);

  setTimeout(function() {
    removeClass(noti, 'pushing');
  }, 50);
}

function popNotification(el) {
  addClass(el, 'popping');
  setTimeout(function() { el.parentNode.removeChild(el); }, 300);
}

function setNotificationDispenserLocation(loc) {
  removeClass(ra.el.notificationDispenser, 'left');
  removeClass(ra.el.notificationDispenser, 'right');
  addClass(ra.el.notificationDispenser, loc);
}

// ======================== อื่นๆ (CTA, Backstage, Spine) ========================
document.addEventListener('click', function(e) {
  var t = e.target;

  if (t.getAttribute('data-action') === 'open-cta') openCTA();
  if (t.getAttribute('data-action') === 'close-cta') closeCTA();

  if (t.getAttribute('data-action') === 'switch-application') {
    var show = !hasClass(ra.el.application, 'backstage');
    showEcosystem(show);
    showApplicationSwitcher(show, t.getAttribute('data-size'));
  }

  if (t.getAttribute('data-action') === 'toggle-spine-size') {
    toggleClass(ra.el.spine, 'small');
    setTimeout(renderWorkspace, 200);
  }
});

function openCTA() { addClass(ra.el.ctaModal, 'open'); addClass(ra.el.application, 'masked'); }
function closeCTA() { removeClass(ra.el.ctaModal, 'open'); removeClass(ra.el.application, 'masked'); }

function showEcosystem(show) {
  if (show) addClass(document.getElementById('container'), 'wk-ecosystem');
  else removeClass(document.getElementById('container'), 'wk-ecosystem');
}

function showApplicationSwitcher(show, size) {
  size = size || 'default';
  if (show) {
    addClass(ra.el.application, 'backstage');
    addClass(ra.el.application, 'appswitcher');
    addClass(ra.el.application, size);
  } else {
    removeClass(ra.el.application, 'backstage');
    removeClass(ra.el.application, 'appswitcher');
    removeClass(ra.el.application, 'default');
    removeClass(ra.el.application, 'small');
  }
}
  }

function toggleasideInfo() {
    // 1. อ้างอิง Element
    var asideInfo = document.getElementById('asideinfo');
    var raApp = document.getElementById('ra-application');

    // 2. สลับ Class (Toggle)
    // ถ้ามี class 'open' อยู่จะลบออก, ถ้าไม่มีจะใส่เพิ่ม
    if (asideInfo) {
        asideInfo.classList.toggle('open');
    }

    if (raApp) {
        raApp.classList.toggle('masked');
    }
}














function newWindow(elementId) {
    var element = document.getElementById("desktop");
    
    var html = `
    <div class="window microsoft draggable2" id="draggable-windows${elementId}">
        <div class="header microsofts" id="header-microsofts${elementId}">
            <div class="title"> 
                <div class="icon" id="icon${elementId}"><i class="material-icons">manage_accounts</i></div>
                <div class="text" id="app${elementId}">App ${elementId}</div>
            </div>
            <div class="buttons">
                <div class="btn minus" onclick="minimize('draggable-windows','${elementId}')"> <i class="material-icons">remove</i></div>
                <div class="btn expand" onclick="expand('draggable-windows','${elementId}');toggle('window__menu')"><i class="fa fa-expand" style="font-size:18px;line-height: 1;"></i></div>
                <div class="btn close" onclick="closeed('draggable-windows','${elementId}');sendCallBackKey('AppTab')"> <i class="material-icons">close</i></div>
            </div>
        </div>
        <div class="bar" id="barmainbody"></div>
        <div class="form" id="microsofts${elementId}" style="background-color:#fff; min-height: 50px;">
            </div>
    </div>`;

    element.insertAdjacentHTML('beforeend', html);
    makeDraggable(elementId);

    // --- ส่วนที่เช็คว่ามี Content หรือยัง ---
    var checkContent = setInterval(function() {
        var contentArea = document.getElementById("microsofts" + elementId);
        var win = document.getElementById("draggable-windows" + elementId);
        
        // เช็คว่าพื้นที่เนื้อหามีตัวอักษรหรือมีแท็ก HTML อื่นอยู่ข้างในหรือยัง
        if (contentArea && contentArea.innerHTML.trim() !== "") {
            
            // เพิ่ม delay เล็กน้อย (เช่น 200ms) เพื่อให้แน่ใจว่า Browser วาด (Render) เสร็จจริงๆ
            setTimeout(function() {
                win.classList.add('show');
            }, 200);
            
            clearInterval(checkContent); // หยุดการวนเช็ค
        }
    }, 100); // เช็คทุกๆ 0.1 วินาที
}


var callBackStatus = 0
var callBacklink;
var callBackShowId;


function callBack(link,showId){
	callBackStatus = 1;
	callBacklink = link;
	callBackShowId = showId;
}
function sendCallBack(){
	if(callBackStatus == 1){
		getData(callBacklink,callBackShowId);
	}
}





// เพิ่มพารามิเตอร์ key เข้ามาตัวแรก
function callBackKey(link, showId,key ) {
    // เก็บข้อมูลใส่กล่อง โดยเอา key แปะป้ายชื่อไว้
    callbackRegistry[key] = {
        status: 1,
        link: link,
        showId: showId
    };
    console.log("📝 บันทึก Callback คีย์ [" + key + "] เรียบร้อย");
}

// เวลาจะเรียกใช้ ก็ส่ง key มาบอกด้วยว่าจะเอาตัวไหนทำงาน
function sendCallBackKey(key) {
    // เช็คก่อนว่ามีคีย์นี้อยู่ในระบบไหม และ status เป็น 1 หรือเปล่า
    var cb = callbackRegistry[key];
    
    if (cb && cb.status === 1) {
        getData(cb.link, cb.showId);
        
        // 💡 [Pro Tip]: ถ้าอยากให้ Callback ทำงานแค่ "ครั้งเดียว" แล้วลบทิ้งเลย
        // ให้ปลดคอมเมนต์บรรทัดล่างนี้ครับ (ป้องกันการกดเบิ้ล หรือเผลอเรียกซ้ำ)
        // delete callbackRegistry[key]; 
    } else {
        console.log("⚠️ ไม่พบ Callback หรือยังไม่ได้ตั้งค่าสำหรับคีย์: " + key);
    }
}
function unsendCallBackKey() {
	callbackRegistry = {};
}



function postDataCallBack(url, formId, div) {


  if(contextMenu_bin){
    contextMenu_bin.style.display = 'none';
  } 

  


    var element = document.getElementById("bar" + div);
    if (element) {
        element.style.display = 'block';
    }

    var formData = new FormData(document.getElementById(formId));


      
		var form = document.getElementById(formId);
	    // 2. ตรวจสอบ HTML5 Validation (required, email, etc.)
	    if (!form.checkValidity()) {
	        form.reportValidity(); // แสดงบอลลูนเตือนของ Browser
	        return; // หยุดการทำงานถ้าข้อมูลไม่ครบ
	    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);

    xhr.onload = function () {
	
	
	
        if (xhr.status >= 200 && xhr.status < 300) {
            
        	var container = document.getElementById(div);
            container.innerHTML = xhr.responseText; // 1. ยัด HTML เข้าไปก่อน
        	runScripts(container);
        	
         

            
            var fileInput = document.getElementById('file');
            if (fileInput) {
               fileInput.value = "";
            }

              var  ID =   document.getElementById(formId);
              if(ID){ID.reset();}
				sendCallBack();

        } else {
   
        alert(`เกิดข้อผิดพลาด  : ข้อมูลซ้ำ หรือไม่พบข้อมูล หรือกรอกไม่ครบถ้วน`);
        
    

      
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



   function openwindow(elementId,title,customindex){
    	  
    	
    	  
      var element = document.getElementById(elementId);
      element.classList.remove('slide-out');	
      element.style.display = 'block';
      element.style.zIndex = customindex;
  
       if(elementId == "draggable-window3"){
    	  var setting = document.getElementById("materialsystem");
          setting.innerText = title;
      }
      if(elementId == "draggable-window2"){
    	  var setting = document.getElementById("app_draggable-window2");
          setting.innerText = title;
      
       
      }
      if(elementId == "draggable-window6" ){
	  var setting = document.getElementById("miniwindows");
	          setting.innerText = title;
	   
	       
	      }	      
	}








// ==========================================
// 1. ฟังก์ชันสั่งจำ Tab (เอาไปผูกกับปุ่มตอนกดเปลี่ยน Tab)
// ==========================================
function saveActiveTab(tabId) {
    localStorage.setItem("erp_active_tab", tabId);

}

// ==========================================
// 2. ฟังก์ชันสั่งจำคำค้นหา (เรียกใช้ตอนพิมพ์ค้นหา หรือกดปุ่มค้นหา)
// ==========================================
function saveSearchValue() {
    // 💡 เปลี่ยน 'search_input_id' เป็น ID ช่องค้นหาของคุณ
    let keyword = document.getElementById('search_input_id').value; 
     let id = document.getElementById('id').value; 
    localStorage.setItem("erp_tab1_search", keyword);
    localStorage.setItem("erp_tab1_id", id);
}




function AlreadyRestored() {
    isPageAlreadyRestored = false; 
}


function loadthispage() {
	
      let savedSearch = localStorage.getItem("erp_tab1_search");
      let searchInput = document.getElementById('search_input_id'); // 💡 อย่าลืมแก้ ID ตรงนี้นะครับ
        
    // 🌟 2. ดักเช็กก่อนเลย ถ้าเคยโหลดค่าและคลิกไปแล้ว ให้ "หยุดและออกจากฟังก์ชันทันที"
    if (isPageAlreadyRestored) {
	 searchInput.value = savedSearch;
        return; 
    }
    
    // 🌟 3. ล็อกกุญแจทันที ป้องกันไม่ให้มันทำงานซ้ำอีก
    isPageAlreadyRestored = true; 

    // ---------------------------------------------------
    // โค้ดเดิมของคุณด้านล่างนี้ ทำงานแค่รอบเดียวแน่นอนครับ
    // ---------------------------------------------------

    // 3.1) ดึงค่า Tab ที่จำไว้ ถ้าไม่มีค่าให้เริ่มต้นที่ 'tabindex-1'
    let savedTab = localStorage.getItem("erp_active_tab") || 'tabindex-1'; 

    // สั่งจำลองการคลิกเพื่อเปิด Tab นั้น
    let tabElement = document.getElementById(savedTab);
    if (tabElement) {
        tabElement.click(); // ตอนนี้คลิกแล้วจะไม่เกิดลูปนรกอีกต่อไป
    }
}






   
















/*
var menuData = '-[{"label":"Atom","submenu":[{"label":"About Atom","command":"application:about"},{"label":"View License","command":"application:open-license"},{"label":"VERSION","enabled":false},{"label":"Restart and Install Update","visible":false,"command":"application:install-update"},{"label":"Check for Update","visible":false,"command":"application:check-for-update"},{"label":"Checking for Update","enabled":false,"visible":false},{"label":"Downloading Update","enabled":false,"visible":false},{"type":"separator"},{"label":"Preferences…","command":"application:show-settings"},{"type":"separator"},{"label":"Config…","command":"application:open-your-config"},{"label":"Init Script…","command":"application:open-your-init-script"},{"label":"Keymap…","command":"application:open-your-keymap"},{"label":"Snippets…","command":"application:open-your-snippets"},{"label":"Stylesheet…","command":"application:open-your-stylesheet"},{"type":"separator"},{"label":"Install Shell Commands","command":"window:install-shell-commands"},{"type":"separator"},{"label":"Services","submenu":[],"role":"services"},{"type":"separator"},{"label":"Hide Atom","command":"application:hide"},{"label":"Hide Others","command":"application:hide-other-applications"},{"label":"Show All","command":"application:unhide-all-applications"},{"type":"separator"},{"label":"Quit","command":"application:quit"}]},{"label":"File","submenu":[{"label":"New Window","command":"application:new-window"},{"label":"New File","command":"application:new-file"},{"label":"Open…","command":"application:open"},{"label":"Add Project Folder…","command":"application:add-project-folder"},{"label":"Reopen Project","submenu":[{"label":"Clear Project History","command":"application:clear-project-history"},{"type":"separator"},{"label":"atoms-touchbar, atom-browser, cs-engine, cs-warrior","command":"application:reopen-project","commandDetail":{"index":0}},{"label":"atom-browser, cs-engine, cs-warrior, atoms-touchbar","command":"application:reopen-project","commandDetail":{"index":1}},{"label":"cs-engine, cs-warrior, atom-browser, atoms-touchbar","command":"application:reopen-project","commandDetail":{"index":2}},{"label":"cs-engine, cs-warrior, atom-browser, atoms-touchbar, hackerman-syntax, alpine","command":"application:reopen-project","commandDetail":{"index":5}},{"label":"cs-engine, cs-warrior, atom-browser, atoms-touchbar, hackerman-syntax","command":"application:reopen-project","commandDetail":{"index":4}},{"label":"cs-engine, cs-warrior, atom-browser, atoms-touchbar, hackerman-syntax, Google Chrome","command":"application:reopen-project","commandDetail":{"index":6}},{"label":"cs-engine, cs-warrior, atom-browser","command":"application:reopen-project","commandDetail":{"index":7}},{"label":"cs-engine, cs-warrior","command":"application:reopen-project","commandDetail":{"index":8}},{"label":"/Users/workstation/Desktop/Github Projects/cs-engine","command":"application:reopen-project","commandDetail":{"index":9}}]},{"label":"Reopen Last Item","command":"pane:reopen-closed-item"},{"type":"separator"},{"label":"Save","command":"core:save"},{"label":"Save As…","command":"core:save-as"},{"label":"Save All","command":"window:save-all"},{"type":"separator"},{"label":"Close Tab","command":"core:close"},{"label":"Close Pane","command":"pane:close"},{"label":"Close Window","command":"window:close"},{"label":"Close All Tabs","command":"tabs:close-all-tabs"}]},{"label":"Edit","submenu":[{"label":"Undo","command":"core:undo"},{"label":"Redo","command":"core:redo"},{"type":"separator"},{"label":"Cut","command":"core:cut"},{"label":"Copy","command":"core:copy"},{"label":"Copy Path","command":"editor:copy-path"},{"label":"Paste","command":"core:paste"},{"label":"Select All","command":"core:select-all"},{"type":"separator"},{"label":"Toggle Comments","command":"editor:toggle-line-comments"},{"label":"Lines","submenu":[{"label":"Indent","command":"editor:indent-selected-rows"},{"label":"Outdent","command":"editor:outdent-selected-rows"},{"label":"Auto Indent","command":"editor:auto-indent"},{"type":"separator"},{"label":"Move Line Up","command":"editor:move-line-up"},{"label":"Move Line Down","command":"editor:move-line-down"},{"label":"Duplicate Lines","command":"editor:duplicate-lines"},{"label":"Delete Line","command":"editor:delete-line"},{"label":"Join Lines","command":"editor:join-lines"}]},{"label":"Columns","submenu":[{"label":"Move Selection Left","command":"editor:move-selection-left"},{"label":"Move Selection Right","command":"editor:move-selection-right"}]},{"label":"Text","submenu":[{"label":"Upper Case","command":"editor:upper-case"},{"label":"Lower Case","command":"editor:lower-case"},{"type":"separator"},{"label":"Delete to End of Word","command":"editor:delete-to-end-of-word"},{"label":"Delete to Previous Word Boundary","command":"editor:delete-to-previous-word-boundary"},{"label":"Delete to Next Word Boundary","command":"editor:delete-to-next-word-boundary"},{"label":"Delete Line","command":"editor:delete-line"},{"type":"separator"},{"label":"Transpose","command":"editor:transpose"}]},{"label":"Folding","submenu":[{"label":"Fold","command":"editor:fold-current-row"},{"label":"Unfold","command":"editor:unfold-current-row"},{"label":"Fold All","command":"editor:fold-all"},{"label":"Unfold All","command":"editor:unfold-all"},{"type":"separator"},{"label":"Fold Level 1","command":"editor:fold-at-indent-level-1"},{"label":"Fold Level 2","command":"editor:fold-at-indent-level-2"},{"label":"Fold Level 3","command":"editor:fold-at-indent-level-3"},{"label":"Fold Level 4","command":"editor:fold-at-indent-level-4"},{"label":"Fold Level 5","command":"editor:fold-at-indent-level-5"},{"label":"Fold Level 6","command":"editor:fold-at-indent-level-6"},{"label":"Fold Level 7","command":"editor:fold-at-indent-level-7"},{"label":"Fold Level 8","command":"editor:fold-at-indent-level-8"},{"label":"Fold Level 9","command":"editor:fold-at-indent-level-9"}]},{"label":"Reflow Selection","command":"autoflow:reflow-selection"},{"label":"Bookmark","submenu":[{"label":"View All","command":"bookmarks:view-all"},{"label":"Toggle Bookmark","command":"bookmarks:toggle-bookmark"},{"label":"Jump to Next Bookmark","command":"bookmarks:jump-to-next-bookmark"},{"label":"Jump to Previous Bookmark","command":"bookmarks:jump-to-previous-bookmark"}]},{"label":"Select Encoding","command":"encoding-selector:show"},{"label":"Go to Line","command":"go-to-line:toggle"},{"label":"Select Grammar","command":"grammar-selector:show"}]},{"label":"View","submenu":[{"label":"Toggle Full Screen","command":"window:toggle-full-screen"},{"label":"Panes","submenu":[{"label":"Split Up","command":"pane:split-up-and-copy-active-item"},{"label":"Split Down","command":"pane:split-down-and-copy-active-item"},{"label":"Split Left","command":"pane:split-left-and-copy-active-item"},{"label":"Split Right","command":"pane:split-right-and-copy-active-item"},{"type":"separator"},{"label":"Focus Next Pane","command":"window:focus-next-pane"},{"label":"Focus Previous Pane","command":"window:focus-previous-pane"},{"type":"separator"},{"label":"Focus Pane Above","command":"window:focus-pane-above"},{"label":"Focus Pane Below","command":"window:focus-pane-below"},{"label":"Focus Pane On Left","command":"window:focus-pane-on-left"},{"label":"Focus Pane On Right","command":"window:focus-pane-on-right"},{"type":"separator"},{"label":"Close Pane","command":"pane:close"}]},{"label":"Developer","submenu":[{"label":"Open In Dev Mode…","command":"application:open-dev"},{"label":"Reload Window","command":"window:reload"},{"label":"Run Package Specs","command":"window:run-package-specs"},{"label":"Run Benchmarks","command":"window:run-benchmarks"},{"label":"Toggle Developer Tools","command":"window:toggle-dev-tools"}]},{"type":"separator"},{"label":"Increase Font Size","command":"window:increase-font-size"},{"label":"Decrease Font Size","command":"window:decrease-font-size"},{"label":"Reset Font Size","command":"window:reset-font-size"},{"type":"separator"},{"label":"Toggle Soft Wrap","command":"editor:toggle-soft-wrap"},{"label":"Toggle Command Palette","command":"command-palette:toggle"},{"label":"Toggle Git Tab","command":"github:toggle-git-tab"},{"label":"Toggle GitHub Tab","command":"github:toggle-github-tab"},{"label":"Toggle Tree View","command":"tree-view:toggle"}]},{"label":"Selection","submenu":[{"label":"Add Selection Above","command":"editor:add-selection-above"},{"label":"Add Selection Below","command":"editor:add-selection-below"},{"label":"Single Selection","command":"editor:consolidate-selections"},{"label":"Split into Lines","command":"editor:split-selections-into-lines"},{"type":"separator"},{"label":"Select to Top","command":"core:select-to-top"},{"label":"Select to Bottom","command":"core:select-to-bottom"},{"type":"separator"},{"label":"Select Line","command":"editor:select-line"},{"label":"Select Word","command":"editor:select-word"},{"label":"Select to Beginning of Word","command":"editor:select-to-beginning-of-word"},{"label":"Select to Beginning of Line","command":"editor:select-to-beginning-of-line"},{"label":"Select to First Character of Line","command":"editor:select-to-first-character-of-line"},{"label":"Select to End of Word","command":"editor:select-to-end-of-word"},{"label":"Select to End of Line","command":"editor:select-to-end-of-line"},{"label":"Select Inside Brackets","command":"bracket-matcher:select-inside-brackets"}]},{"label":"Find","submenu":[{"label":"Find in Buffer","command":"find-and-replace:show"},{"label":"Replace in Buffer","command":"find-and-replace:show-replace"},{"label":"Select Next","command":"find-and-replace:select-next"},{"label":"Select All","command":"find-and-replace:select-all"},{"label":"Toggle Find in Buffer","command":"find-and-replace:toggle"},{"type":"separator"},{"label":"Find in Project","command":"project-find:show"},{"label":"Toggle Find in Project","command":"project-find:toggle"},{"type":"separator"},{"label":"Find All","command":"find-and-replace:find-all"},{"label":"Find Next","command":"find-and-replace:find-next"},{"label":"Find Previous","command":"find-and-replace:find-previous"},{"label":"Replace Next","command":"find-and-replace:replace-next"},{"label":"Replace All","command":"find-and-replace:replace-all"},{"type":"separator"},{"label":"Clear History","command":"find-and-replace:clear-history"},{"type":"separator"},{"label":"Find Buffer","command":"fuzzy-finder:toggle-buffer-finder"},{"label":"Find File","command":"fuzzy-finder:toggle-file-finder"},{"label":"Find Modified File","command":"fuzzy-finder:toggle-git-status-finder"}]},{"label":"Packages","submenu":[{"label":"Atom Beautify","submenu":[{"label":"Beautify","command":"atom-beautify:beautify-editor"},{"label":"Debug","command":"atom-beautify:help-debug-editor"}]},{"label":"Atom Browser","submenu":[{"label":"Toggle","command":"atom-browser:showHide"}]},{"label":"Bracket Matcher","submenu":[{"label":"Go To Matching Bracket","command":"bracket-matcher:go-to-matching-bracket"},{"label":"Select Inside Brackets","command":"bracket-matcher:select-inside-brackets"},{"label":"Remove Brackets From Selection","command":"bracket-matcher:remove-brackets-from-selection"},{"label":"Close Current Tag","command":"bracket-matcher:close-tag"},{"label":"Remove Matching Brackets","command":"bracket-matcher:remove-matching-brackets"}]},{"label":"Command Palette","submenu":[{"label":"Toggle","command":"command-palette:toggle"}]},{"label":"Dev Live Reload","submenu":[{"label":"Reload All Styles","command":"dev-live-reload:reload-all"}]},{"label":"Git Diff","submenu":[{"label":"Move to Next Diff","command":"git-diff:move-to-next-diff"},{"label":"Move to Previous Diff","command":"git-diff:move-to-previous-diff"},{"label":"Toggle Diff List","command":"git-diff:toggle-diff-list"}]},{"label":"GitHub","submenu":[{"label":"Toggle Git Tab","command":"github:toggle-git-tab"},{"label":"Toggle GitHub Tab","command":"github:toggle-github-tab"}]},{"label":"Keybinding Resolver","submenu":[{"label":"Toggle","command":"key-binding-resolver:toggle"}]},{"label":"Markdown Preview","submenu":[{"label":"Toggle Preview","command":"markdown-preview:toggle"}]},{"label":"Open On GitHub","submenu":[{"label":"Blame","command":"open-on-github:blame"},{"label":"Branch Compare","command":"open-on-github:branch-compare"},{"label":"Copy URL","command":"open-on-github:copy-url"},{"label":"File","command":"open-on-github:file"},{"label":"File on Master","command":"open-on-github:file-on-master"},{"label":"History","command":"open-on-github:history"},{"label":"Issues","command":"open-on-github:issues"},{"label":"Repository","command":"open-on-github:repository"}]},{"label":"Package Generator","submenu":[{"label":"Generate Atom Package","command":"package-generator:generate-package"},{"label":"Generate Atom Syntax Theme","command":"package-generator:generate-syntax-theme"}]},{"label":"Settings View","submenu":[{"label":"Open","command":"settings-view:open"},{"label":"Show Keybindings","command":"settings-view:show-keybindings"},{"label":"Install Packages/Themes","command":"settings-view:install-packages-and-themes"},{"label":"Update Packages/Themes","command":"settings-view:check-for-package-updates"},{"label":"Manage Packages","command":"settings-view:uninstall-packages"},{"label":"Manage Themes","command":"settings-view:uninstall-themes"}]},{"label":"Snippets","submenu":[{"label":"Expand","command":"snippets:show"},{"label":"Next Stop","command":"snippets:next-tab-stop"},{"label":"Previous Stop","command":"snippets:previous-tab-stop"},{"label":"Available","command":"snippets:available"}]},{"label":"Spell Check","submenu":[{"label":"Toggle","command":"spell-check:toggle"}]},{"label":"Styleguide","submenu":[{"label":"Show","command":"styleguide:show"}]},{"label":"Symbols","submenu":[{"label":"File Symbols","command":"symbols-view:toggle-file-symbols"},{"label":"Project Symbols","command":"symbols-view:toggle-project-symbols"}]},{"label":"Timecop","submenu":[{"label":"Show","command":"timecop:view"}]},{"label":"Tree View","submenu":[{"label":"Focus","command":"tree-view:toggle-focus"},{"label":"Toggle","command":"tree-view:toggle"},{"label":"Reveal Active File","command":"tree-view:reveal-active-file"},{"label":"Toggle Tree Side","command":"tree-view:toggle-side"}]},{"label":"Whitespace","submenu":[{"label":"Remove Trailing Whitespace","command":"whitespace:remove-trailing-whitespace"},{"label":"Save With Trailing Whitespace","command":"whitespace:save-with-trailing-whitespace"},{"label":"Save Without Trailing Whitespace","command":"whitespace:save-without-trailing-whitespace"},{"label":"Convert Tabs To Spaces","command":"whitespace:convert-tabs-to-spaces"},{"label":"Convert Spaces To Tabs","command":"whitespace:convert-spaces-to-tabs"},{"label":"Convert All Tabs To Spaces","command":"whitespace:convert-all-tabs-to-spaces"}]}]},{"label":"Window","submenu":[{"label":"Minimize","command":"application:minimize"},{"label":"Zoom","command":"application:zoom"},{"type":"separator"},{"label":"Bring All to Front","command":"application:bring-all-windows-to-front"}],"role":"window"},{"label":"Help","submenu":[{"label":"Terms of Use","command":"application:open-terms-of-use"},{"label":"Documentation","command":"application:open-documentation"},{"label":"Frequently Asked Questions","command":"application:open-faq"},{"type":"separator"},{"label":"Community Discussions","command":"application:open-discussions"},{"label":"Report Issue","command":"application:report-issue"},{"label":"Search Issues","command":"application:search-issues"},{"type":"separator"},{"label":"Welcome Guide","command":"welcome:show"}],"role":"help"}]'

var menus = JSON.parse(menuData)
var container = document.querySelectorAll('.container .menus')[0]
var init = function(){
   for(var menu of menus){
      var menuHTML = buildMenu(menu)
      container.innerHTML += menuHTML
   }
   setTimeout(listenMenu, 0)
}
var listenMenu = function(){
   var menuItems = document.querySelectorAll('.submenu-item')
   for(var i = 0; i < menuItems.length; i++){
      menuItems[i].addEventListener('click', function(){
		  
	
         var undo = this.innerText;
		  
         console.log(undo)
      })
   }
}
var buildMenu = function(menu){
   var submenuHTML = buildSubMenuHTML(menu.submenu)
   return `
      <div class='menu'>
         <label>${menu.label}</label>
         ${submenuHTML}
      </div>`
}
var buildSubMenuHTML = function(submenu){
   if(!submenu) return ''
   var html = ''
   for(var item of submenu){
      // Skip on separator
      if(item.type == 'separator'){ html += `<hr>`; continue }
      
      html += `
         <div class='submenu-item ${item.submenu ? 'has-submenu' : ''}'>
            ${item.label}
            ${buildSubMenuHTML(item.submenu)}
         </div>`
   } 
   return `<div class='submenu'>${html}</div>`
}

init()
*/





