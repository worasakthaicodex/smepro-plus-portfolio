<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	 

<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <title>Property Grid UI</title>
 
</head>
<body>

<div id="pg-container">
    <div class="pg-header">
        <div class="pg-title">Property </div>
   <div class="pg-search-box">
    <input type="text" id="pg-search-input" placeholder="Search" oninput="filterProperties()">
    <span style="padding:0 4px; color:#ccc; cursor:pointer;" onclick="clearSearch()">✕</span>
</div>
    </div>

    <div class="pg-body">
        <div class="pg-category">
            <div class="pg-cat-header" onclick="toggleCat(this)">
                <div class="pg-arrow"></div> Data Retrieval
            </div>
            <div class="pg-cat-content">
           <div class="pg-row">
    <div class="pg-label">Limit</div>
    <div class="pg-value">
        <input type="number" id="setting_sync_back_limit" name="sync_back_limit" max="2000" 
               onchange="updateReportSettingsToJS();">
    </div>
</div>
<div class="pg-row">
    <div class="pg-label">Random</div>
    <div class="pg-value"> 
        <select id="setting_sync_back_random" name="sync_back_random" onchange="updateReportSettingsToJS();">
            <option value="0">NO</option>
            <option value="1">Yes</option>
        </select>
    </div>
</div>
<div class="pg-row">
    <div class="pg-label">Not Unique Active</div>
    <div class="pg-value"> 
        <select id="setting_sync_back_not_unique_active" name="sync_back_not_unique_active" onchange="updateReportSettingsToJS();">
            <option value="0">NO</option>
            <option value="1">Yes</option>
        </select>
    </div>
</div>
            </div>
        </div>
              <div class="pg-category">
            <div class="pg-cat-header" onclick="toggleCat(this)">
                <div class="pg-arrow"></div>  Data Info
            </div>
            <div class="pg-cat-content">
        

  
        <div class="pg-row">
            <div class="pg-label">Total Records</div>
            <div class="pg-value">
                <input type="text" value="0" id="TotalRecords" readonly style="color: #666; background: #f9f9f9;"> ROWS
            </div>
        </div>
            </div>
        </div>
      
        
        
        

        <div class="pg-category open" style="display:none">
            <div class="pg-cat-header" onclick="toggleCat(this)">
                <div class="pg-arrow"></div> Appearance
            </div>
            <div class="pg-cat-content">
                <div class="pg-row">
                    <div class="pg-label">Background</div>
                    <div class="pg-value">
                        <div class="pg-icon-marker" style="background: linear-gradient(to right, white, #ccc);"></div>
                        <input type="text" value="System.Windows.Media.LinearGradient">
                    </div>
                </div>
                <div class="pg-row">
                    <div class="pg-label">BorderBrush</div>
                    <div class="pg-value">
                        <div class="pg-icon-marker" style="background: #707070;"></div>
                        <input type="text" value="#FF707070">
                    </div>
                </div>
                <div class="pg-row selected">
                    <div class="pg-label">FontFamily</div>
                    <div class="pg-value">
                        <select>
                            <option selected>Segoe UI</option>
                            <option>Arial</option>
                            <option>Consolas</option>
                        </select>
                    </div>
                </div>
                <div class="pg-row">
                    <div class="pg-label">FontSize</div>
                    <div class="pg-value"><input type="text" value="12" style="text-align: right;"></div>
                </div>
            </div>
        </div>

        <div class="pg-category open" style="display:none">
            <div class="pg-cat-header" onclick="toggleCat(this)">
                <div class="pg-arrow"></div> Behavior
            </div>
            <div class="pg-cat-content">
                <div class="pg-row">
                    <div class="pg-label">ClickMode</div>
                    <div class="pg-value">
                        <select><option>Release</option></select>
                    </div>
                </div>
                <div class="pg-row">
                    <div class="pg-label">IsTabStop</div>
                    <div class="pg-value" style="padding-left: 5px;">
                        <input type="checkbox" checked>
                    </div>
                </div>
            </div>
        </div>

        <div class="pg-category open" style="display:none">
            <div class="pg-cat-header" onclick="toggleCat(this)">
                <div class="pg-arrow"></div> Content
            </div>
            <div class="pg-cat-content">
                <div class="pg-row">
                    <div class="pg-label">Content</div>
                    <div class="pg-value"><input type="text" value="Click Me"></div>
                </div>
            </div>
        </div>
    </div>
  
</div>

<script>
    /**
     * ฟังก์ชันสำหรับเปิด-ปิด Category (Vanilla JS)
     */
    function toggleCat(element) {
        var category = element.parentElement;
        if (category.classList.contains('open')) {
            category.classList.remove('open');
        } else {
            category.classList.add('open');
        }
    }

    /**
     * จัดการเรื่องการคลิกเลือก Row เพื่อความสวยงามเหมือนในรูป
     */
    document.querySelectorAll('.pg-row').forEach(function(row) {
        row.addEventListener('click', function() {
            document.querySelectorAll('.pg-row').forEach(r => r.classList.remove('selected'));
            this.classList.add('selected');
        });
    });
</script>

</body>
</html>


<script>
    function filterProperties() {
        var input = document.getElementById('pg-search-input');
        var filter = input.value.toLowerCase();
        var categories = document.querySelectorAll('.pg-category');

        categories.forEach(function(cat) {
            var rows = cat.querySelectorAll('.pg-row');
            var hasVisibleRow = false;

            rows.forEach(function(row) {
                var label = row.querySelector('.pg-label').textContent.toLowerCase();
                if (label.indexOf(filter) > -1) {
                    row.style.display = ""; // แสดงแถว
                    hasVisibleRow = true;
                } else {
                    row.style.display = "none"; // ซ่อนแถว
                }
            });

            // ถ้าไม่มี Row ไหนในหมวดนี้ที่ตรงกับคำค้นหาเลย ให้ซ่อนทั้งหมวด
            if (hasVisibleRow) {
                cat.style.display = "";
                // ถ้าค้นหาอยู่ ให้เปิดหมวดหมู่ออกมาดูอัตโนมัติ
                if(filter !== "") cat.classList.add('open');
            } else {
                cat.style.display = "none";
            }
        });
    }

    function clearSearch() {
        var input = document.getElementById('pg-search-input');
        input.value = "";
        filterProperties();
        input.focus();
    }
    
    
    loadReportSettingsToUI()
</script>