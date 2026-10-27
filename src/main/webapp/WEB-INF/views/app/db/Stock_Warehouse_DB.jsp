<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
 <!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>เปรียบเทียบสินค้า</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .header {
            background: #f4f4f4;
            padding: 10px;
            text-align: center;
        }
        .header h2 {
            margin: 0;
            color: #333;
        }
        .category-buttons {
            display: flex;
            justify-content: center;
            gap: 10px;
            margin: 10px 0;
        }
        .category-buttons button {
            padding: 5px 10px;
            background: #6b48ff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        .category-buttons button:hover {
            background: #5a3de6;
        }
        .contents {
            display: flex;
            flex-grow: 1;
            padding: 10px 20px; /* ลด padding ด้านซ้ายขวาเพื่อให้ชิดขอบมากขึ้น */
            gap: 20px; /* เพิ่มช่องว่างระหว่างสินค้าทั้งหมดและเปรียบเทียบ */
                      background: #f4f4f4;
        }
        .product-list {
            flex: 2;
            margin: 0; /* ลบ margin เพื่อให้ชิดขอบ */
            padding: 0; /* ลบ padding ด้านซ้าย */
        }
        .product-list h2 {
            margin-top: 0;
        }
        .sort-buttons {
            margin-bottom: 20px;
        }
        .sort-buttons button {
            padding: 5px 10px;
            margin-right: 10px;
            background: #ddd;
            border: none;
            cursor: pointer;
        }
        .sort-buttons button.active {
            background: #6b48ff;
            color: white;
        }
        .product-item {
            display: flex;
            align-items: center;
            padding: 10px;
            background: #fff;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            max-height: 70px;
            overflow: hidden;
        }
        .product-item img {
            width: 50px;
            height: 50px;
            margin-right: 10px;
        }
        .product-item button {
            margin-left: auto;
            background: #6b48ff;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
        }
        .comparison {
            flex: 1;
            background: #f0f0f0;
            padding: 20px;
            box-sizing: border-box;
            border-left: 1px solid #ddd;
            min-width: 250px;
        }
        .comparison h2 {
            text-align: center;
            margin-top: 0;
        }
        .comparison-item {
            margin-bottom: 20px;
            padding: 10px;
            background: #fff;
            border-radius: 5px;
            max-height: 50px;
            overflow: hidden;
        }
        
         .category-buttons button {
            padding: 5px 10px;
            background: #6b48ff;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            transition: background 0.3s; /* เพิ่มการเปลี่ยนสีอย่างนุ่มนวล */
        }
        .category-buttons button:hover, .category-buttons button.active {
            background: #5a3de6; /* สีเมื่อกดหรือ hover */
        }
        
        
                /* ... (โค้ด CSS เดิมทั้งหมด) ... */
        .product-item {
            display: flex;
            align-items: center;
            padding: 10px;
            background: #fff;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            max-height: 70px;
            overflow: hidden;
        }
        .product-item img {
            width: 50px;
            height: 50px;
            margin-right: 10px;
        }
        .button-group {
            display: flex;
            gap: 5px; /* ระยะห่างระหว่างปุ่ม */
            margin-left: auto; /* ดันปุ่มไปขวา */
        }
        .product-item button {
            background: #6b48ff;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
        }
        
      .product-item button.loads {
            background: #3CB371; /* เปลี่ยนสีปุ่มที่มีคลาส .loads เป็นสีเขียว */
        }
        
         .comparison-item {
            margin-bottom: 20px;
            padding: 10px;
            background: #fff;
            border-radius: 5px;
            max-height: 50px;
            overflow: hidden;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .comparison-item .remove-btn {
            background: #ff4444;
            color: white;
            border: none;
            padding: 2px 8px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 12px;
        }
        
    </style>
</head>
<body>
   
    <div class="contents" >
        <div class="product-list">
            <h2>สินค้าทั้งหมด      </h2>
<div class="input-group">
                                   
                                       
                                       
 
 
                                        <input type="text" class="form-control" id="display_stock_stockid" value="" >
                                                <span class="input-group-text" id="basic-addon-search1">
                                      <a href="#" >  
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
       								 </a>
                                        </span>
                                        
                                                 
                                                 
                                    </div>
            <br>
            <div class="sort-buttons">
                <button onclick="sortProducts('priceAsc')" class="active">เรียงราคา น้อยไปมาก</button>
                <button onclick="sortProducts('priceDesc')">เรียงราคา มากไปน้อย</button>
                
                <div style="float:right;width:200px" >
                        <select class="select2"  >
    <option value="all">ทั้งหมด</option>
    <option value="Stock">ในสต๊อก</option>
    <option value="Non-stock">สินค้าทั่วไป</option>
    <c:forEach items="${dataset_data}" var="rows">   
       <option value="${rows.dataset_data_title }">${rows.dataset_data_title }</option>
    </c:forEach>
</select>
                
                </div>

 
       		
            </div>
            <div id="productContainer">
                <!-- ผลิตภัณฑ์จะถูกเพิ่มที่นี่โดย JavaScript -->
            </div>
        </div>
        <div class="comparison" style="display:none">
            <h2>เปรียบเทียบ</h2>
            <div id="comparisonContainer">
                <!-- ผลลัพธ์การเปรียบเทียบจะแสดงที่นี่ -->
            </div>
            <p>จำนวน: <span id="itemCount">0</span></p>
        </div>
    </div>

   <script>
    // ข้อมูลผลิตภัณฑ์
    let products = [
        <c:forEach items="${stock}" var="rows">   
            { id: "${rows.stock_code}${rows.stock_receipt_date}${rows.stock_id}", name: "${rows.stock_list}", category: "Stock", groups: "${rows.stock_type}", unit: "${rows.stock_unit}", price: ${rows.stock_price+0}, image: "http://localhost:8830/avatar/GEP2FTWNWX3ZZ6W6X.png" },
        </c:forEach>
    ];
    
    let comparedItems = [];

    // ฟังก์ชันแสดงผลิตภัณฑ์
    function displayProducts(filteredProducts) {
        const container = document.getElementById("productContainer");
        container.innerHTML = "";
        filteredProducts.forEach(function(product) {
            const div = document.createElement("div");
            div.className = "product-item";
            div.innerHTML = 
                '<img src="' + product.image + '" alt="' + product.name + '">' +
                '<span>' + product.name + ' - ' + product.price + ' บาท </span>' +
                '<div class="button-group">' +
                    '<button class="loads" onclick="addToCart(\'' + product.id + '\')" data-bs-dismiss="modal">+</button>' +
                    '<button onclick="addToCompare(\'' + product.id + '\')"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-package"><line x1="16.5" y1="9.4" x2="7.5" y2="4.21"></line><path d="M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z"></path><polyline points="3.27 6.96 12 12.01 20.73 6.96"></polyline><line x1="12" y1="22.08" x2="12" y2="12"></line></svg></button>' +
                '</div>';
            container.appendChild(div);
        });
    }

    // ฟังก์ชันจัดเรียง
    function sortProducts(type) {
        const buttons = document.querySelectorAll(".sort-buttons button");
        buttons.forEach(function(btn) {
            btn.classList.remove("active");
        });
        document.querySelector('.sort-buttons button[onclick*="sortProducts(\'' + type + '\')"]').classList.add("active");

        let sortedProducts = [...currentProducts];
        if (type === "priceAsc") {
            sortedProducts.sort(function(a, b) {
                return a.price - b.price;
            });
        } else if (type === "priceDesc") {
            sortedProducts.sort(function(a, b) {
                return b.price - b.price;
            });
        }
        displayProducts(sortedProducts);
    }

    // ฟังก์ชันกรองหมวดหมู่
    let currentProducts = products;
    function filterCategory(category) {
        if (category === "all") {
            currentProducts = products;
        } else {
            currentProducts = products.filter(function(p) {
                return p.category === category;
            });
        }
        sortProducts("priceAsc");
        
        const buttons = document.querySelectorAll(".category-buttons button");
        buttons.forEach(function(btn) {
            btn.classList.remove("active");
            if (btn.getAttribute("onclick").includes(category)) {
                btn.classList.add("active");
            }
        });
    }

    // ฟังก์ชันเพิ่มสินค้าไปเปรียบเทียบ
    function addToCompare(id) {
        const product = products.find(function(p) {
            return p.id === id; // ใช้ === ได้เลยเพราะทั้งสองฝั่งเป็น string
        });
        if (!comparedItems.some(function(item) {
            return item.id === id;
        }) && comparedItems.length < 5) {
            comparedItems.push(product);
            updateComparison();
        }
    }

    // ฟังก์ชันอัปเดตการเปรียบเทียบ
    function updateComparison() {
        const container = document.getElementById("comparisonContainer");
        container.innerHTML = "";
        comparedItems.forEach(function(item, index) {
            const div = document.createElement("div");
            div.className = "comparison-item";
            div.innerHTML = 
                item.name + " - " + item.price + " บาท" +
                '<button class="remove-btn" onclick="removeFromCompare(' + index + ')">ลบ</button>';
            container.appendChild(div);
        });
        document.getElementById("itemCount").textContent = comparedItems.length;
    }

    // ฟังก์ชันลบรายการจากเปรียบเทียบ
    function removeFromCompare(index) {
        comparedItems.splice(index, 1);
        updateComparison();
    }

    // ฟังก์ชันเพิ่มลงตะกร้า
    function addToCart(id) {
        const product = products.find(function(p) {
            return p.id === id; // ใช้ === ได้เลยเพราะทั้งสองฝั่งเป็น string
        });

        if (product) {
            var itemidFields = document.querySelectorAll('input[name="procurement_itemid"]');
            itemidFields.forEach(function(field) {
                field.value = product.id;
            });
            
            var nameFields = document.querySelectorAll('input[name="procurement_itemname"]');
            nameFields.forEach(function(field) {
                field.value = product.name;
            });
            
            var estimatedpriceField = document.querySelectorAll('input[name="procurement_estimatedprice"]');
            estimatedpriceField.forEach(function(field) {
                field.value = product.price;
            });
            
            var unit_pricepriceField = document.querySelectorAll('input[name="procurement_unit_price"]');
            unit_pricepriceField.forEach(function(field) {
                field.value = product.price;
            });
        } else {
            console.log("ไม่พบสินค้าที่มี ID:", id);
        }
    }

    // เริ่มต้นแสดงสินค้า
    filterCategory("all");
</script>
</body>
</html>