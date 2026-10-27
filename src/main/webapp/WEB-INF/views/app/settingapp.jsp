<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
    /* Card Design */
    .setting-card {
        background: #ffffff;
        border: 1px solid #e0e0e0;
        border-radius: 8px;
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 2px 5px rgba(0,0,0,0.05);
    }

    .section-title {
        display: flex;
        align-items: center;
        gap: 10px;
        font-size: 15px;
        font-weight: 600;
        color: #2c3e50;
        margin-bottom: 15px;
        padding-bottom: 10px;
        border-bottom: 1px solid #f0f0f0;
    }

    .icon-svg {
        width: 22px;
        height: 22px;
        fill: none;
        stroke: #0078d7;
        stroke-width: 2;
        stroke-linecap: round;
        stroke-linejoin: round;
    }

    /* --- 🔥 CSS Magic for Wallpapers (แก้ปัญหากดไม่ติด) --- */
    
    /* 1. ซ่อนปุ่ม Radio กลมๆ */
    .bg-radio-input {
        display: none; 
    }

    /* 2. Label ครอบรูปภาพ (เพื่อให้กดที่รูป = กด Radio) */
    .bg-label {
        cursor: pointer;
        display: inline-block;
        margin: 5px;
        position: relative;
        transition: transform 0.2s;
    }

    /* 3. สไตล์รูปภาพปกติ */
    .bg-img {
        width: 95px;
        height: 75px;
        object-fit: cover;
        border-radius: 6px;
        border: 3px solid transparent; /* เตรียมขอบไว้ */
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        transition: all 0.2s;
    }

    /* 4. เมื่อ Radio ถูกเลือก (:checked) ให้เปลี่ยนสไตล์รูปที่อยู่ติดกัน (+) */
    .bg-radio-input:checked + .bg-img {
        border-color: #0078d7; /* ขอบสีฟ้า */
        box-shadow: 0 4px 12px rgba(0, 120, 215, 0.4); /* เงาฟุ้ง */
        transform: scale(1.05); /* ขยายใหญ่ขึ้น */
    }

    .bg-label:hover .bg-img {
        opacity: 0.9;
        transform: translateY(-2px);
    }

    /* --- App Grid --- */
    .app-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
        gap: 10px;
        max-height: 200px;
        overflow-y: auto;
    }

    .app-item-row {
        display: flex;
        align-items: center;
        padding: 8px 12px;
        border-radius: 6px;
        background: #f9f9f9;
        border: 1px solid transparent;
        transition: background 0.2s;
    }
    .app-item-row:hover {
        background: #f0f0f0;
        border-color: #ddd;
    }
</style>

<div class="cl_window">
    
    <div class="window-body" style="padding: 15px; background: #f5f7fa;">
        <form id="formSetting" action="/saveSettingApp" method="POST">
             <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" id="hiddenAppsInput" name="hiddenApps" value="${setting.hiddenApps}">

            <div class="setting-card">
                <div class="section-title">
                    <svg class="icon-svg" viewBox="0 0 24 24"><rect x="3" y="3" width="18" height="18" rx="2" ry="2"></rect><circle cx="8.5" cy="8.5" r="1.5"></circle><polyline points="21 15 16 10 5 21"></polyline></svg>
                    <span>Wallpaper Settings</span>
                </div>
                
             

                <div style="display:flex; flex-wrap:wrap; ">
                    <c:forEach items="${systemWallpapers}" var="bgName">
                        
                        <label class="bg-label" style="width:95px;" title="${bgName}">
                            
                            <input type="radio" 
                                   name="selectedBg" 
                                   value="${bgName}" 
                                   class="bg-radio-input"
                                   <c:if test="${fn:contains(setting.backgroundImage, bgName)}">checked</c:if>>
                            
                            <img src="/app/system/background/${bgName}" class="bg-img">
                            
                        </label>

                    </c:forEach>
                </div>
            </div>

            <div class="setting-card">
                <div class="section-title">
                    <svg class="icon-svg" viewBox="0 0 24 24"><path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19m-6.72-1.07a3 3 0 1 1-4.24-4.24"></path><line x1="1" y1="1" x2="23" y2="23"></line></svg>
                    <span>Application Visibility</span>
                </div>
                
                <div style="font-size:13px; color:#666; margin-bottom:12px;">
                    Check apps to <span style="color:#e74c3c; font-weight:bold;">hide</span> from dashboard:
                </div>
                
                <div id="app-list-container" class="app-grid">
                    <div style="color:#999; font-style:italic;">Loading Apps...</div>
                </div>
            </div>

        </form>
    </div>

    <div class="window-footer" style="background: #fff; border-top: 1px solid #e0e0e0; padding: 10px;">
        <button class="primary" onclick="submitSetting()">Save Changes</button>
        <button onclick="closeed2('draggable-window6')">Cancel</button>
    </div>
</div>

<script>
    // -----------------------------------------------------------
    // ฟังก์ชัน Submit (Global)
    // -----------------------------------------------------------
    window.submitSetting = function() {
        var selectedApps = [];
        var checkboxes = document.querySelectorAll('.app-checkbox:checked');
        
        checkboxes.forEach(function(checkbox) {
            selectedApps.push(checkbox.value);
        });

        // เอาค่าใส่ Hidden Input
        var hiddenInput = document.getElementById('hiddenAppsInput');
        if(hiddenInput) hiddenInput.value = selectedApps.join(',');

        // ส่งฟอร์ม
        var form = document.getElementById('formSetting');
        if(form) form.submit();
    };

    // -----------------------------------------------------------
    // ฟังก์ชันโหลด App (Global App Config)
    // -----------------------------------------------------------
    function loadAppsFromGlobalConfig() {
        var container = document.getElementById('app-list-container');
        if(!container) return;
        
        container.innerHTML = ''; 

        // ดึงค่า Hidden เดิมจาก Server
        var currentHidden = "${setting.hiddenApps}"; 
        var hiddenArray = currentHidden ? currentHidden.split(',') : [];

        if (typeof GLOBAL_APP_CONFIG !== 'undefined') {
            for (var key in GLOBAL_APP_CONFIG) {
                if (GLOBAL_APP_CONFIG.hasOwnProperty(key)) {
                    var app = GLOBAL_APP_CONFIG[key];
                    // เช็คว่าต้องติ๊กถูกไหม
                    var isChecked = hiddenArray.includes(app.id.toString()) ? 'checked' : '';

                    var div = document.createElement('div');
                    div.className = 'app-item-row';
                    
                    div.innerHTML = `
                        <label style="cursor:pointer; display:flex; align-items:center; width:100%; margin:0;">
                            <input type="checkbox" class="app-checkbox" value="` + app.id + `" ` + isChecked + ` style="margin-right:10px;">
                            <img src="` + app.logo + `" style="width:24px; height:24px; margin-right:10px; border-radius:4px;">
                            <span style="font-size:13px; color:#333; font-weight:500;">` + app.name + `</span>
                        </label>
                    `;
                    container.appendChild(div);
                }
            }
        } else {
            container.innerHTML = '<span style="color:red; padding:10px;">Error: GLOBAL_APP_CONFIG not found</span>';
        }
    }

    // เริ่มทำงานทันที
    loadAppsFromGlobalConfig();

</script>