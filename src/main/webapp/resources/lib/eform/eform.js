
 function addfield(elementType,ids,label,datarefertype,datareferid,datarefercols,mainfield){
 
 	const formPreviews = document.querySelector('.form-preview');
 	 let newInput;
 	 let newInputs;
 

 	  if (elementType === 'text') {
 		  
 	
 		  
 	        newInput = document.createElement('div');
 	        newInput.className = 'row draggable-row';
 	        newInput.innerHTML = `
 	            <div class="col-md-12 col-12">
 	                <div class="mb-1">
 	                    <label class="form-label" for="itemname">Text</label>
 	                    
 	                    
 	                    
 	                    <div class="dropdown pop" style="float:right;margin-right:45px;">
 	                    <i class="cursor-pointer more-options-dropdown me-0" data-feather="settings" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
 	                    Setup
 	                    </i>
 	                        <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
 	                            <div class="mb-1">
 	                                <label for="discount-input" class="form-label">label</label>
 	                                <input type="text" class="form-control change-label" value="text" name="field_label"/>
 	                                 <input type="hidden"  value="text" name="field_type"/>
 	                            </div>
 	                            <div class="form-row mt-50">
 	                                <div class="mb-1 col-md-12">
 	                                <label for="tax-1-input" class="form-label">Main Field</label>
 	                                    <select id="tax-1-input" class="form-select tax-select" name="field_mainfield">
 	                                   <option value="`+mainfield+`" selected>`+label+`</option>
 	                                    <option value="-" >ระบุุเอง</option>
 	                                    <c:forEach items="${textfield }" var="row" varStatus="count">
 	                                    <option value="${row.field_inputname  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
 	            						  </c:forEach>
 	                                    </select>
 	                                </div>
 	                            
 	                            </div>
 	                            <div class="dropdown-divider my-1"></div>
 	                            <div class="d-flex justify-content-between">
 	                                <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
 	                                <button type="button" class="btn btn-outline-secondary">ปิด</button>
 	                            </div>
 	                        </div>
 	                    </div>
 	                    <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
 						  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
 						   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
 	                    <input type="text" class="form-control mydata" name="condition_title" value=""/>
 	                </div>
 	            </div>
 	        `;
 	        
 	        
 	   
 	        let newTableHeader = document.createElement('th');
 			newTableHeader.textContent = 'text';
 			newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 			newTableHeader.classList.add('fixhead'); 
 			
 			const theadElement = document.getElementById('tabledata');
 			const tableRow = theadElement.querySelector('tr');
 			
 			tableRow.appendChild(newTableHeader);
 			      
 			const nodemain = document.querySelector('.node1');
 		
 			
 			
 			newInputs = document.createElement('div');
 		    newInputs.className = 'agm';        
 			newInputs.innerHTML = '<input id="1" value="text" class="change-label2"  readonly>';
 			  nodemain.appendChild(newInputs);
 	        
 			  
 			  
 	        // ดึงข้อมูลเริ่มต้นของ label จาก input
 	        const initialLabelValue = newInput.querySelector('.change-label').value;
 	        const formLabel = newTableHeader.querySelector('.form-label'); 
 	        const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
 	        
 	        
 	        newInput.querySelector('.form-label').textContent = label;
 	        newTableHeader.textContent = label;
 	        newInputs.querySelector('.change-label2').value  = label;
 	        
 	        // หากมีการเปลี่ยนแปลงค่าใน input change-label
 	        newInput.querySelector('.change-label').addEventListener('input', (event) => {
 	            // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
 	            newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
 	            newTableHeader.textContent = event.target.value || initialLabelValue;
 	            newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
 	            
 	        });

 	      
 	            const deleteButton = newInput.querySelector('.delete-button');
 	            deleteButton.addEventListener('click', () => {
 	                newInput.remove(); // ลบ div ตัวเอง
 	                newInputs.remove(); // ลบ div ตัวเอง
 	                newTableHeader.remove();
 	            });
 	            
 	            
 	            // เลือกองค์ประกอบ select
 	            const selectElement = newInput.querySelector('.tax-select');

 	            // สร้างองค์ประกอบ change-label
 	            const changeLabelElement = newInput.querySelector('.change-label');

 	            // เมื่อมีการเปลี่ยนแปลงใน select
 	            selectElement.addEventListener('change', (event) => {
 	              // ดึงค่า data จากตัวเลือกที่ถูกเลือก
 	              const selectedOption = selectElement.options[selectElement.selectedIndex];
 	              const dataValue = selectedOption.getAttribute('data');

 	              // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
 	              changeLabelElement.value = dataValue || 'Text';

 	              // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
 	              newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
 	              newTableHeader.textContent = changeLabelElement.value || 'Text';
 	              newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
 	            });      
 	            
 	            
 	            
 	        
 	        
 	    }
 	
 	  
 	  
 	  
 	  
 	  
 	 if (elementType === 'textarea') {
         newInput = document.createElement('div');
         newInput.className = 'row draggable-row';
         newInput.innerHTML = `
             <div class="col-md-12 col-12">
                 <div class="mb-1">
                     <label class="form-label" for="itemname">Textarea</label>
                     
                     
                     
                     <div class="dropdown pop" style="float:right;margin-right:45px;">
                     <i class="cursor-pointer more-options-dropdown me-0" data-feather="settings" role="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                     Setup
                     </i>
                         <div class="dropdown-menu dropdown-menu-end item-options-menu p-1" aria-labelledby="dropdownMenuButton">
                             <div class="mb-1">
                                 <label for="discount-input" class="form-label">label</label>
                                 <input type="text" class="form-control change-label" value="textarea" name="field_label"/>
                                  <input type="hidden"  value="textarea" name="field_type"/>
                             </div>
                             <div class="form-row mt-50">
                                 <div class="mb-1 col-md-12">
                                 <label for="tax-1-input" class="form-label">Main Field</label>
                                     <select  id="tax-1-input" class="form-select tax-select" name="field_mainfield">
                                 
                                     <option value="`+mainfield+`" selected>`+label+`</option>
	                                    <option value="-" >ระบุุเอง</option>
                                     <c:forEach items="${textareafield }" var="row" varStatus="count">
                                     <option value="${row.field_inputname  }" data="${row.field_name1 } [${row.field_name2 }]">${row.field_name1 } [${row.field_name2 }]</option>
             						  </c:forEach>
                                     </select>
                                 </div>
                             
                             </div>
                             <div class="dropdown-divider my-1"></div>
                             <div class="d-flex justify-content-between">
                                 <button type="button" class="btn btn-outline-danger  delete-button">ลบ</button>
                                 <button type="button" class="btn btn-outline-secondary">ปิด</button>
                             </div>
                         </div>
                     </div>
                     <input type="hidden"  name="field_datarefertype" value="`+datarefertype+`" class="form-control3"  >
					  <input type="hidden"  name="field_datareferid" value="`+datareferid+`" class="form-control2"  >
					   <input type="hidden"  name="field_datarefercols" value="`+datarefercols+`" class="form-control4"  >
                     <textarea class="form-control mydata" name="condition_title" value=""/></textarea>
                 </div>
             </div>
         `;
         
         
       
         let newTableHeader = document.createElement('th');
 		newTableHeader.textContent = 'textarea';
 		newTableHeader.classList.add('form-label'); // เพิ่มคลาส CSS ตามต้องการ
 		newTableHeader.classList.add('fixhead'); 
 		
 		const theadElement = document.getElementById('tabledata');
 		const tableRow = theadElement.querySelector('tr');
 		
 		tableRow.appendChild(newTableHeader);
 		
 		const nodemain = document.querySelector('.node1');       
 		ids++;      
 		newInputs = document.createElement('div');
 	    newInputs.className = 'agm';        
 		newInputs.innerHTML = '<input id="1" value="Textarea" class="change-label2"  readonly>';
 		nodemain.appendChild(newInputs);     
         
         
         
         // ดึงข้อมูลเริ่มต้นของ label จาก input
         const initialLabelValue = newInput.querySelector('.change-label').value;
         const initialLabelValue2 = newInputs.querySelector('.change-label2').value;
         const formLabel = newTableHeader.querySelector('.form-label'); 
         
	        newInput.querySelector('.form-label').textContent = label;
 	        newTableHeader.textContent = label;
 	        newInputs.querySelector('.change-label2').value  = label;
         
         
         // หากมีการเปลี่ยนแปลงค่าใน input change-label
         newInput.querySelector('.change-label').addEventListener('input', (event) => {
             // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
             newInput.querySelector('.form-label').textContent = event.target.value || initialLabelValue;
             newTableHeader.textContent = event.target.value || initialLabelValue;
             newInputs.querySelector('.change-label2').value = event.target.value || initialLabelValue2;
         });

       
             const deleteButton = newInput.querySelector('.delete-button');
             deleteButton.addEventListener('click', () => {
                 newInput.remove(); // ลบ div ตัวเอง
                 newTableHeader.remove();
                 newInputs.remove(); // ลบ div ตัวเอง
             });
             
             
             // เลือกองค์ประกอบ select
             const selectElement = newInput.querySelector('.tax-select');

             // สร้างองค์ประกอบ change-label
             const changeLabelElement = newInput.querySelector('.change-label');

             // เมื่อมีการเปลี่ยนแปลงใน select
             selectElement.addEventListener('change', (event) => {
               // ดึงค่า data จากตัวเลือกที่ถูกเลือก
               const selectedOption = selectElement.options[selectElement.selectedIndex];
               const dataValue = selectedOption.getAttribute('data');

               // กำหนดค่าของ change-label ด้วยค่า data หรือค่าที่ว่าง
               changeLabelElement.value = dataValue || 'textarea';

               // อัพเดตค่าของ label ด้วยค่าที่ถูกเปลี่ยน
               newInput.querySelector('.form-label').textContent = changeLabelElement.value || initialLabelValue;      
               newTableHeader.textContent = changeLabelElement.value || 'textarea';
                newInputs.querySelector('.change-label2').value = changeLabelElement.value || initialLabelValue2;    
             });      
             
             
             
         
         
     }
     
     
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  // เพิ่มองค์ประกอบ Input ลงใน form-preview
 	    formPreviews.appendChild(newInput);
 	    
 	    // เพิ่มการลากและวางให้กับองค์ประกอบใหม่
 	    makeElementDraggable(newInput);
 	    
 	    // เพิ่มการสลับตำแหน่งได้
 	    makeElementSortable();
 	  
 	  
 }