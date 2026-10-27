<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<title>File index</title>


<style>


.nav-col {
  display: flex;
  flex-direction: column;
  align-items: start;
  justify-content: start;
  background: var(--secondary);
  padding: 24px;
  border-radius: 10px;
  position: sticky;
  top: 80px;
  
  h3 {
    position: relative;
    margin-bottom: 8px;
    padding-right: 6px;
    display: flex;
    justify-content: start;
    align-items: center;
    gap: 4px;
    flex-wrap: wrap;
    font-size: 1.5rem;
    line-height: 1.1;
    
    & span {
      color: inherit;
    }
    
    &:after {
      content: "3 Unlinked";
      font-size: .9rem;
      font-weight: 500;
      line-height: 1;
      color: hsla(29, 93%, 60%, 1);
      padding: 3px 6px;
      position: relative;
      border-radius: .2rem;
      background: hsla(29, 93%, 60%, 0.31);
      display: inline-block;
    }
  }
}

.projects {
  display: grid;
  grid-template-columns: 1fr;
  gap: 2px;
  overflow-y: auto;
  max-height: 260px;
  width: 100%;
  list-style: none;
  padding: 0 16px;
  direction: rtl;
  text-align: left;
  margin-top: 16px;
  
  & a {
    display: block;
    padding: 4px 8px;
    color: #62666D;
    text-decoration: none;
    border-radius: 3px;
    transition-property: background, color;
    transition-duration: .1s;
    transition-timing-function: ease-out;
    
    &:hover {
      color: #D7D8DB !important;
      background: var(--quadinary)
    }
  }
}

.projects::-webkit-scrollbar {
  width: 4px;
}
 
.projects::-webkit-scrollbar-track {
  background: var(--primary);
  border-radius: 8px;
}
 
.projects::-webkit-scrollbar-thumb {
  background-color: var(--accent);
  border-radius: 8px;
}

.search {
  background: var(--quadinary);
  padding: 12px 12px;
  width: 100%;
  border-radius: 5px;
  margin-top: 12px;
  color: inherit;
  border: none;
  outline: none;
  -webkit-appearance:textfield;
  
  &:focus {
    border: none;
    outline: none;
  }
}



/*** ----- CONTENT COLUMN ----- ***/
.cont-col {
  display: grid;
  grid-template-columns: 1fr;
  gap: 24px;
}



/*** ----- PROJECT CARDS ----- ***/
.project-card {
  background: #FFF;
  padding: 24px;
  border-radius: 10px;
  display: grid;
  grid-template-columns: .5fr .8fr;
  gap: 1rem;
  align-items: start;
  
  & img {
    width: 100%;
  }
}

@media screen and (max-width: 1000px) {
  .project-card {
    grid-template-columns: 1fr;
  }
}

.top {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: start;
  gap: 16px;
  margin-bottom: 24px;
  // position: sticky;
  // top: 104px;
  
  & h3 {
    font-size: 1.5rem;
    line-height: 1.2;
  }
  
  & img {
    aspect-ratio: 5/3;
    width: 100%;
    border-radius: 3px;
    object-fit: cover;
  }
}

.p-link div {
  display: flex;
  flex-direction: column;
  gap: 8px;
  margin-top: 1rem;
  
  & a {
    color: hsla(231, 71%, 65%, 1);
    margin-top: 0;
    line-height: 1;
    text-decoration: none;
  }
  
  & a:hover {
    color: hsla(231, 71%, 75%, 1);
  }
  
  & span {
    font-size: .7rem;
    line-height: 1;
    text-transform: uppercase;
    margin-bottom: 0;
    opacity: 1;
    letter-spacing: 1px;
  }
}

.p-link div:first-child {
    margin-bottom: 1.5rem;
}

.boost-wrap {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
}

@media screen and (max-width: 1140px) {
  .boost-wrap {
    display: grid;
    grid-template-columns: 1fr;
  }
}

.booster-card {
  padding: 16px;
  background:  var(--secondary);
  border: 1px solid #eee;
  border-radius: 3px;
  grid-template-columns: 1fr;
  gap: 16px;
  position: relative;
 
}

.boost-name-wrap {
  display: flex;
  align-items: start;
  flex-direction: column;
  justify-content: start;

}

.boost-name {
  font-size: 1.2rem;
  font-weight: 500;
  line-height: 1;
}

.boost-badge {
  font-size: .7rem;
  font-weight: 600;
  text-transform: uppercase;
  background: hsla(231, 71%, 63%, .3);
  color: hsla(231, 71%, 75%, 1);
  padding: 3px 6px;
  border-radius: 3px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  margin-top: 8px;
  
  & svg {
    width: 15px;
    height: auto;
  }
}

.action-btn-group {
  display: flex;
  gap: 4px;
  position: absolute;
  right: 16px;
  bottom: 16px;
  
  & a {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 5px;
    color: white;
    border-radius: 3px;
    background: hsla(350, 60%, 46%, 1);
    
    &:first-child {
      background: hsla(220, 62%, 42%, 1);
    }
    
    & svg {
      width: 20px;
      height: 20px;
    }
  }
}

.jb-class {
  display: flex;
  gap: 4px;
  position: absolute;
  right: 16px;
  top: 16px;
  line-height: 1;
  padding: 5px;
  border-radius: 3px;
  background: hsla(0, 0%, 42%, 1);
}

.show-boost-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  text-decoration: none;
  padding: 8px;
  background: var(--tertiary);
  border-radius: 3px;
  color: inherit;
  transition-property: background;
  transition-duration: .3s;
  transition-timing-function: ease-out;
  
  &:hover {
    background: var(--quadinary);
  }
}

.connect {
  padding: 16px;
  width: 100%;
  margin-top: 16px;
  background: var(--tertiary);
  border-radius: 5px;
  
  & a {
    display: block;
    text-align: center;
    text-decoration: none;
    background: var(--accent);
    color: white;
    padding: 12px;
    width: 100%;
    border-radius: 3px;
    transition-property: background;
    transition-duration: .3s;
    transition-timing-function: ease-out;
    margin-top: 12px;
    
    &:hover {
      background: #455AD3 !important;
    }
  }
}

.connect-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 1.2rem;
  
  & svg {
    witdth: 20px;
    height: 20px;
  }
  
  & a {
    margin-top: 12px;
  }
}

.toggle-wrap {
  display: flex;
  align-items: start;
  justify-content: start;
  flex-direction: column;
  gap: 4px;
}

.toggle-item {

  align-items: center;
  justify-content: center;
}

.toggle-label {
  color: hsla(133, 62%, 52%, .8);
  font-size: .7rem;
  font-weight: 600;
  text-transform: uppercase;
  background: hsla(133, 62%, 52%, .15);
  padding: 3px 6px;
  min-width: 64px;
  text-align: center;
  border-radius: 3px;
  margin-right: 6px;
  
  &.alt {
    background: hsla(205, 93%, 60%, 0.31);
    color: hsla(205, 93%, 60%, 1);
  }
}

.locked {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
	height: 24px;
  background: grey;
  border-radius: 20px;
  
  & svg {
    width: auto;
	  height: 16px;
  }
}


/*** ----- UTILITIES ----- ***/
.active {
  color: #D7D8DB !important;
  background: var(--quadinary)
}

input[type=checkbox]{
	height: 0;
	width: 0;
	visibility: hidden;
  margin: 0;
}



input:checked + label {
	background: hsla(133, 62%, 52%, 1);
}

input:checked + label:after {
	left: calc(100% - 2px);
	transform: translateX(-100%);
}

label:active:after {
	width: 120px;
}


/*** ----- RESPONSIVE ----- ***/
@media screen and (max-width: 840px) {
  .container {
    display: grid;
    grid-template-columns: 1fr;
    gap: 48px;
  }
  .dash {
    padding: 4%;
  }
  
  .nav-col {
    position: relative;
    top: auto;
  }
}

</style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">

        <div class="content-wrapper container-xxl p-0">
     
          
   
            <div class="content-body">
            
          
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="card-header">
                                <h4 class="card-title" style="float:left;">Workflow Monitor</h4>
                                 
                                 
			                           <div style="float:right;">        
			                       <a href="#" class="btn btn-primary waves-effect waves-float waves-light"  
			                      onclick="return getdata('/add-workflow',1);">ADD</a>
			                           </div>
                            </div>
                   <div class="col-12">
                   
                   
                   
                 <div class="cont-col" id="maindata">
                 
         <c:forEach items="${workflows }" var="row" varStatus="count">         
 <div id="project_${row.workflow_id}" >
 	      <form id="project_card_${row.workflow_id}" modelAttribute="workflow"  >
     <div class="project-card card"  > 
	    <div class="top">
	    
	      <div class="top-name-wrap">

	      <input type="text" name="workflow_name" value="${row.workflow_name}" 
	      style="border:0px;border-bottom: 1px solid black;margin-bottom:10px;width:300px;display:none" id="inputname_${row.workflow_id}">
	      
	      <h3 id="divname_${row.workflow_id}">${row.workflow_name}</h3>
	        <div class="p-link">
	       
	        <span id="key${row.workflow_id}">
	        
	        <c:if test="${row.workflow_key1 != '' }">    
	        <span class="badge badge-light-primary">1.${row.workflow_key1}</span>
	        </c:if>
	        <c:if test="${row.workflow_key2 != '' }">    
	        <span class="badge badge-light-primary">2.${row.workflow_key2}</span>
	        </c:if>
	        <c:if test="${row.workflow_key3 != '' }">    
	        <span class="badge badge-light-primary">3.${row.workflow_key3}</span>
	        </c:if>
	        <c:if test="${row.workflow_key4 != '' }">    
	        <span class="badge badge-light-primary">4.${row.workflow_key4}</span>
	        </c:if>
	        
	           </span>       
	           <a class="btn btn-sm btn-light" style="padding:2px;display:none;" data-bs-toggle="modal" data-bs-target="#addNewCard2"  id="tag_${row.workflow_id}" 
	           onclick="setkey('${row.workflow_id}','${row.workflow_key1}','${row.workflow_key2}','${row.workflow_key3}','${row.workflow_key4}');">
	           <i data-feather='tag'></i></a>
	          <div>
	            <h6>คำอธิบาย:</h6>
	             <input type="hidden"  name="workflow_id"  value="${row.workflow_id}" >
	            <input type="hidden"  name="workflow_key1" class="workflow_key_1_${row.workflow_id}"  value="${row.workflow_key1}" >
	            <input type="hidden"  name="workflow_key2" class="workflow_key_2_${row.workflow_id}" value="${row.workflow_key2}" >
	            <input  type="hidden"  name="workflow_key3" class="workflow_key_3_${row.workflow_id}" value="${row.workflow_key3}">
	            <input  type="hidden" name="workflow_key4" class="workflow_key_4_${row.workflow_id}" value="${row.workflow_key4}" >
	            <textarea  name="workflow_note" style="display:none" id="inputnote_${row.workflow_id}">${row.workflow_note}</textarea>
	          <span id="divnote_${row.workflow_id}">${row.workflow_note}</span>
	          
	         <br> 
	          </div>

	          
	          
	           <a  href="/view-workflow/${row.workflow_id}"    class="btn btn-sm btn-info" style="width:100px;margin-right:5px;" id="view_${row.workflow_id}">View</a>
                 <a class="btn btn-sm btn-success" style="width:100px;margin-right:5px;display:none;" 
                  onclick="postdata('/edit-workflow?${_csrf.parameterName}=${_csrf.token}','project_card_${row.workflow_id}','savestatus');saves('${row.workflow_id}');" id="save_${row.workflow_id}" >Save</a>
                   <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;display:none;"
                    id="cancel_${row.workflow_id}" onclick="cancel(${row.workflow_id})" >Cancel</a>
                    
                     <a class="btn btn-sm btn-danger"  style="width:100px;margin-right:5px;display:none;"
                    id="delete_${row.workflow_id}" onclick="deletes('/del-workflow','${row.workflow_id}');" >Delete</a>
                    
           <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;" onclick="edit(${row.workflow_id})" id="edit_${row.workflow_id}">Edit</a>
	  
	        </div>
	      </div>
	    </div>
	    <div>
	    

          
          
	      <div class="boost-wrap wrap${row.workflow_id}" id="card-drag-area">
	        
	   <c:forEach items="${eflows }" var="rows" varStatus="count"> 

	    <c:if test="${rows.eflow_idform == row.workflow_id }">        
			 <div class="booster-card draggable"  id="booster${rows.eflow_id}" >
		        <div class="boost-name-wrap">
		        <div class="boost-name">
		        ${rows.eflow_name}
		        </div>
		
		      </div>
		      <div class="toggle-wrap" id="subkey${rows.eflow_id}">

		      <c:if test="${rows.eflow_key1 != '' &&  rows.eflow_key1 != null}">    
				       <span class="badge badge-light-primary">1.${rows.eflow_key1}</span>
		      </c:if>
		      <c:if test="${rows.eflow_key2 != '' &&  rows.eflow_key2 != null}">    
			         <span class="badge badge-light-primary">2.${rows.eflow_key2}</span>
		         </c:if>
		     <c:if test="${rows.eflow_key3 != '' &&  rows.eflow_key3 != null}">         
		          <span class="badge badge-light-primary">3.${rows.eflow_key3}</span>
		    </c:if>    
		   <c:if test="${rows.eflow_key4 != '' &&  rows.eflow_key4 != null}">       
		          <span class="badge badge-light-primary">4.${rows.eflow_key4}</span>
		    </c:if>    

		       
		       <br> <br>
		      </div>
		       	<input type="hidden"  name="eflow_key1" class="eflow_key_1_${rows.eflow_id}"  value="${rows.eflow_key1}" >
	            <input type="hidden"  name="eflow_key2" class="eflow_key_2_${rows.eflow_id}" value="${rows.eflow_key2}" >
	            <input  type="hidden"  name="eflow_key3" class="eflow_key_3_${rows.eflow_id}" value="${rows.eflow_key3}">
	            <input  type="hidden" name="eflow_key4" class="eflow_key_4_${rows.eflow_id}" value="${rows.eflow_key4}" >   
		      
		  <input type="hidden"  name="workflow_eforms" class="workflow_eforms"  value="${rows.eflow_idrefer}" >
          <input type="hidden"  name="workflow_names" class="workflow_names"  value="${rows.eflow_name}" >
		      
		      <div class="action-btn-group">
		        <a href="#"  class="dataset${row.workflow_id}" style="display:none"  data-bs-toggle="modal" data-bs-target="#addNewCard3" 
		        onclick="getdata('/addkey-workflow/${rows.eflow_idform}/${rows.eflow_id}','${rows.eflow_idrefer}')">
		          <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" xmlns="http://www.w3.org/2000/svg">
		            <path d="M14.4524 2.89607L11.1742 6.17434L11.6161 8.38405L13.8258 8.82599L17.1041 5.54772L17.1044 5.54758C17.5102 6.50542 17.6072 7.56593 17.382 8.58151C17.1568 9.59708 16.6206 10.5172 15.848 11.2137C15.0754 11.9103 14.1049 12.3486 13.0715 12.4677C12.0381 12.5869 10.9933 12.3808 10.0824 11.8783L10.0825 11.8782L5.7008 16.951C5.3491 17.3023 4.8723 17.4996 4.3752 17.4994C3.8781 17.4993 3.40141 17.3017 3.04991 16.9502C2.69841 16.5987 2.50087 16.122 2.50073 15.6249C2.50059 15.1278 2.69785 14.651 3.04915 14.2993L8.12192 9.91767L8.12181 9.91772C7.61931 9.0069 7.41327 7.96207 7.53239 6.92868C7.65151 5.89528 8.08986 4.92473 8.78641 4.15213C9.48296 3.37952 10.4031 2.84331 11.4186 2.61812C12.4342 2.39293 13.4947 2.48998 14.4526 2.89575L14.4524 2.89607Z"></path>
		          </svg>
		        </a>
		        <a href="#" onclick="del('${rows.eflow_id}')" class="datadel${row.workflow_id}"  style="display:none">
		          <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" xmlns="http://www.w3.org/2000/svg">
		            <path d="M16.8748 4.375L3.12476 4.375"></path>
		            <path d="M8.125 8.125V13.125"></path>
		            <path d="M11.875 8.125V13.125"></path>
		            <path d="M15.625 4.375V16.25C15.625 16.4158 15.5592 16.5747 15.4419 16.6919C15.3247 16.8092 15.1658 16.875 15 16.875H5C4.83424 16.875 4.67527 16.8092 4.55806 16.6919C4.44085 16.5747 4.375 16.4158 4.375 16.25V4.375"></path>
		            <path d="M13.125 4.375V3.125C13.125 2.79348 12.9933 2.47554 12.7589 2.24112C12.5245 2.0067 12.2065 1.875 11.875 1.875H8.125C7.79348 1.875 7.47554 2.0067 7.24112 2.24112C7.0067 2.47554 6.875 2.79348 6.875 3.125V4.375"></path>
		          </svg>
		        </a>
		      </div>
		          
		    </div>
		    
    </c:if>
	  </c:forEach>    
	        
	        
	             <a href="#" data-bs-toggle="modal" data-bs-target="#addNewCard"  
	             onclick="addeform(${row.workflow_id})" style="display:none" id="modal_${row.workflow_id}">
	             <div class="booster-card" style="text-align: center;  justify-content: center;font-size: 20px;border: 1px dotted;height:100px;padding-top:35px"> 
	    
	+

	        </div></a>
	        
	        
	      </div>
	    </div>
	  </div>
	      </form>
	    </div>
        </c:forEach>
       
      </div>
                    </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

            <div id="savestatus"></div>




  <div class="modal fade" id="addNewCard2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">กรอก Key หลัก</h1>
                              

                                <!-- form -->
                               
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                       <input  type="hidden" name="key" id="key" value="0">
                                    
                                    
                                    <div class="col-12">
                                      Key : 1
                                      <input  type="text" name="mainkey1" id="mainkey1" class="form-control" >
                                    </div>
                                  <div class="col-12">
                                      Key : 2
                                      <input  type="text" name="mainkey2" id="mainkey2" class="form-control" >
                                    </div>
                                       <div class="col-12">
                                      Key : 3
                                      <input  type="text" name="mainkey3" id="mainkey3" class="form-control" >
                                    </div>
                                       <div class="col-12">
                                      Key : 4
                                      <input  type="text" name="mainkey4" id="mainkey4" class="form-control" >
                                    </div>
                                      

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='addkey()' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                <
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->





 <div class="modal fade" id="addNewCard3" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">กรอก Key หลัก</h1>
                              

                                <!-- form -->
                               
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                       <input  type="hidden" name="wrap" id="wrap" value="0">
                                    
                                    <form id="setkey">
                                    <div id="setkeyzone">
                   
                                    </div>
                                    </form>   
                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='setsubkey()' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                              
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->










  <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">เลือก E-form</h1>
                              

                                <!-- form -->
                                <form    action="/rejectdocument-filemanager" id="formaddNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                       <input  type="hidden" name="wrap" id="wrap" value="1">
                                    
                                    
                                    <div class="col-12">
                                      
                                       <select class="form-select select2"   name="upload_pn_monitor" id="dataeform" >   
                               	      <c:set var="monitors" value="" />
                               	      <c:if test="${upload_pn.upload_pn_monitor == 0 }">    
                                                              <c:set var="monitors" value="selected" />
                                           </c:if>  
                               	      
                                    <c:forEach items="${conditions }" var="row" varStatus="count">
                                    
                        
                                            <option value="${row.condition_id }" data-custom="${row.condition_name }">${row.condition_name }</option>
                                      </c:forEach>
                                  </select>
                                    </div>
 

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='addeforms()' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>



<%@ include file="../common/mainfoot.jspf" %>  
 <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
 	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	  
    <script>
    
    

    
    function  setsubkey(){
    
    	
       var subkey =  $("#subkey").val();
    	
   	  $("#subkey"+subkey).html(" ");
   

     	
     	
  	var keyed4 = $('#keyed4').find(":selected").val();  
 	 if(keyed4 !== "" &&  keyed4 != null){
		 var key4 = ` <span class="badge badge-light-primary">4.`+keyed4+`</span>`; 
		 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key4);
	 }
   	var keyed3 = $('#keyed3').find(":selected").val();  
	 if(keyed3 !== "" &&  keyed3 != null){
		 var key3 = ` <span class="badge badge-light-primary">3.`+keyed3+`</span>`; 
		 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key3);
	 }  
	var keyed2 = $('#keyed2').find(":selected").val();  
	 	 if(keyed2 !== "" &&  keyed2 != null){
			 var key2 = ` <span class="badge badge-light-primary">2.`+keyed2+`</span>`; 
			 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key2);
	}  
	   	  
	    	var keyed1 = $('#keyed1').find(":selected").val();  
	    	
		 	 if(keyed1 !== "" && keyed1 != null ){
				 var key1 = ` <span class="badge badge-light-primary">1.`+keyed1+`</span>`; 
				 document.getElementById("subkey"+subkey).insertAdjacentHTML('afterbegin',key1);
		   }
    
		 	$(".eflow_key_1_"+subkey).val(keyed1);
	    	$(".eflow_key_2_"+subkey).val(keyed2);
	    	$(".eflow_key_3_"+subkey).val(keyed3);
	    	$(".eflow_key_4_"+subkey).val(keyed4); 
  
    }
    
    function  edit(id){
    	
     $("#view_"+id).hide();
   	$("#edit_"+id).hide();
    $("#save_"+id).show();
  	$("#cancel_"+id).show();
 	$("#delete_"+id).show();
  	
 	$("#inputname_"+id).show();
 	$("#divname_"+id).hide();
 	$("#inputnote_"+id).show();
 	$("#divnote_"+id).hide();
 	$("#tag_"+id).show();
 	$("#modal_"+id).show();
 	
 	$(".datadel"+id).show();
	$(".dataset"+id).show();

    }
    
    function cancel(id){
    	$("#delete_"+id).hide();
        $("#view_"+id).show();
      	$("#edit_"+id).show();
       $("#save_"+id).hide();
     	$("#cancel_"+id).hide();
    	$("#inputname_"+id).hide();
    	$("#divname_"+id).show();
    	$("#inputnote_"+id).hide();
    	$("#divnote_"+id).show();
    	$("#tag_"+id).hide();
    	$("#modal_"+id).hide();
     	
     	$(".datadel"+id).hide();
    	$(".dataset"+id).hide();
   }
 function saves(id){
    	
	 var name = $("#inputname_"+id).val();
	 var note = $("#inputname_"+note).val();
	 $("#divnote_"+id).html(note);
	 $("#divname_"+id).html(name);
	 $("#delete_"+id).hide();
	 
		$(".datadel"+id).hide();
    	$(".dataset"+id).hide();
	 
        $("#view_"+id).show();
      	$("#edit_"+id).show();
       $("#save_"+id).hide();
     	$("#cancel_"+id).hide();
    	$("#inputname_"+id).hide();
    	$("#divname_"+id).show();
    	$("#inputnote_"+id).hide();
    	$("#divnote_"+id).show();
    	$("#tag_"+id).hide();
    	$("#modal_"+id).hide();
   }
    
    
     function  addkey(){
    	 var key =  $("#key").val();
    	 var mainkey1 =  $("#mainkey1").val();
    	 var mainkey2 =  $("#mainkey2").val();
    	 var mainkey3 =  $("#mainkey3").val();
    	 var mainkey4 =  $("#mainkey4").val();
    
    	 
    	 
     	$(".workflow_key_1_"+key).val(mainkey1);
    	$(".workflow_key_2_"+key).val(mainkey2);
    	$(".workflow_key_3_"+key).val(mainkey3);
    	$(".workflow_key_4_"+key).val(mainkey4);
   

    	 $("#key"+key).html(" ");
    	
    	 
    	 if(mainkey4 !== ""){
    		 var key4 = ` <span class="badge badge-light-primary">4.`+mainkey4+`</span>`; 
    		 document.getElementById("key"+key).insertAdjacentHTML('afterbegin',key4);
    	 }
    	 if(mainkey3 !== ""){
    		 var key3 = ` <span class="badge badge-light-primary">3.`+mainkey3+`</span>`; 
    		 document.getElementById("key"+key).insertAdjacentHTML('afterbegin',key3);
    	 }
    	 if(mainkey2 !== ""){
    		 var key2 = ` <span class="badge badge-light-primary">2.`+mainkey2+`</span>`; 
    		 document.getElementById("key"+key).insertAdjacentHTML('afterbegin',key2);
    	 }
     	
    	 
    	 if(mainkey1 !== ""){
    		 var key1 = ` <span class="badge badge-light-primary">1.`+mainkey1+`</span>`; 
    		 document.getElementById("key"+key).insertAdjacentHTML('afterbegin',key1);
    	 }
    	 
    	
    	
    	 
    }
     function setkey(id,data1,data2,data3,data4){
    	 $("#key").val(id);
    	 $("#mainkey1").val(data1);
    	 $("#mainkey2").val(data2);
    	 $("#mainkey3").val(data3);
    	 $("#mainkey4").val(data4);
     }
    
    function  addeform(id){
  	  $("#wrap").val(id);
  	  
    }
    
    function   del(id){
    	$("#booster"+id).remove();
    } 
    
    
    var selectElement = document.getElementById('dataeform');
    var selectedOption= selectElement.options[selectElement.selectedIndex];
    var dataCustomValue  = selectedOption.getAttribute('data-custom');
     var selectedValue = selectElement.value;
 	

 	selectElement.addEventListener('change', function() {
 		  selectedOption = selectElement.options[selectElement.selectedIndex];
 		 dataCustomValue = selectedOption.getAttribute('data-custom');
       	  selectedValue = selectElement.value;
         console.log(selectedValue);
 	});
 	
 	
 	function generateRandomNumber() {
 		  let randomNumber = '';
 		  for (let i = 0; i < 9; i++) {
 		    randomNumber += Math.floor(Math.random() * 9);
 		  }
 		  return randomNumber;
 		}
    
    function addeforms(){
    	var temid;
    	var eid =   $("#wrap").val();
    	
    	
    	  $.ajax({
		        type:'GET',
		        url:'/neweflow-workflow',
		        data: {id : eid},
		        
		     success: function(data){
		    	 temid = data;	
		    		var block = `<div class="booster-card draggable"  id="booster`+eid+`">
		            <div class="boost-name-wrap">
		            <div class="boost-name">
		            `+dataCustomValue+`
		            </div>

		          </div>
		          <div class="toggle-wrap" id="subkey`+temid+`">
		          
		          </div>
		          
		         	<input type="hidden"  name="eflow_key1" class="eflow_key_1_`+temid+`"  value="" >
		            <input type="hidden"  name="eflow_key2" class="eflow_key_2_`+temid+`" value="" >
		            <input  type="hidden"  name="eflow_key3" class="eflow_key_3_`+temid+`" value="">
		            <input  type="hidden" name="eflow_key4" class="eflow_key_4_`+temid+`" value="" >   
		          
		      	  <input type="hidden"  name="workflow_eforms" class="workflow_eforms"  value="`+selectedValue+`" >
		          <input type="hidden"  name="workflow_names" class="workflow_names"  value="`+dataCustomValue+`" >
		            
		          <div class="action-btn-group">
		            <a href="#"  class="dataset`+eid+`" data-bs-toggle="modal" data-bs-target="#addNewCard3"
		            onclick="getdata('/addkey-workflow/`+eid+`/`+temid+`','`+selectedValue+`')">
		              <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" xmlns="http://www.w3.org/2000/svg">
		                <path d="M14.4524 2.89607L11.1742 6.17434L11.6161 8.38405L13.8258 8.82599L17.1041 5.54772L17.1044 5.54758C17.5102 6.50542 17.6072 7.56593 17.382 8.58151C17.1568 9.59708 16.6206 10.5172 15.848 11.2137C15.0754 11.9103 14.1049 12.3486 13.0715 12.4677C12.0381 12.5869 10.9933 12.3808 10.0824 11.8783L10.0825 11.8782L5.7008 16.951C5.3491 17.3023 4.8723 17.4996 4.3752 17.4994C3.8781 17.4993 3.40141 17.3017 3.04991 16.9502C2.69841 16.5987 2.50087 16.122 2.50073 15.6249C2.50059 15.1278 2.69785 14.651 3.04915 14.2993L8.12192 9.91767L8.12181 9.91772C7.61931 9.0069 7.41327 7.96207 7.53239 6.92868C7.65151 5.89528 8.08986 4.92473 8.78641 4.15213C9.48296 3.37952 10.4031 2.84331 11.4186 2.61812C12.4342 2.39293 13.4947 2.48998 14.4526 2.89575L14.4524 2.89607Z"></path>
		              </svg>
		            </a>
		            <a href="#" class="datadel`+eid+`" onclick="del('`+eid+`')">
		              <svg viewBox="0 0 20 20" fill="none" stroke="currentColor" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round" xmlns="http://www.w3.org/2000/svg">
		                <path d="M16.8748 4.375L3.12476 4.375"></path>
		                <path d="M8.125 8.125V13.125"></path>
		                <path d="M11.875 8.125V13.125"></path>
		                <path d="M15.625 4.375V16.25C15.625 16.4158 15.5592 16.5747 15.4419 16.6919C15.3247 16.8092 15.1658 16.875 15 16.875H5C4.83424 16.875 4.67527 16.8092 4.55806 16.6919C4.44085 16.5747 4.375 16.4158 4.375 16.25V4.375"></path>
		                <path d="M13.125 4.375V3.125C13.125 2.79348 12.9933 2.47554 12.7589 2.24112C12.5245 2.0067 12.2065 1.875 11.875 1.875H8.125C7.79348 1.875 7.47554 2.0067 7.24112 2.24112C7.0067 2.47554 6.875 2.79348 6.875 3.125V4.375"></path>
		              </svg>
		            </a>
		          </div>
		         
		        </div>`;
		        

		        	 document.getElementsByClassName("wrap"+eid)[0].insertAdjacentHTML('afterbegin',block);
		        
		    	 
		    	 
		    	 
		     }
		});
    	

    	
    	
    
  }
    
    
    function postdata(Mode,Mode2,Mode3) {

    	
	   var formData = new FormData(document.getElementById(Mode2));

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('#' + Mode3).html(result);
		
				       }
				    });

	    return false;
	    }	
    
	function getdata(url,ID){

					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {id : ID},
					        
					     success: function(data){
					    $("#savestatus").html(data);	
				
					     }
					});
		
		return false;
	    
 }
 
	  
	
		function save(url){
	
	        $.ajax({
	        type:'GET',
	        url:url,
	        data: {id : ID},
	        
	     success: function(data){
	    $("#savestatus").html(data);	
	
	     }
	});
	
	return false;
	
	}
	
		function deletes(url,ID){
			if(confirm("Want to delete?")){
				
		        $.ajax({
			        type:'GET',
			        url:url,
			        data: {id : ID},
			        
			     success: function(data){
			    	 $("#project_"+ID).remove();	
			     }
			});

			}
		}
    
    
    var element = document.getElementById("nav_monitor");
    element.classList.add("active");  
    function search() {

	     var filter = $('.files-filter2').val();
	      $('.datatable').filter(function() {
	    
	    	    $(this).hide();
	            var regex = new RegExp(filter, 'ig');
	          
	            if (regex.test($(this).text())) {
	                $(this).show();
	            } else {
	                $(this).hide();
	            }
	            
	            
	        });
	  
   
}
 
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
        document.getElementById("file").onchange = function() {
    document.getElementById("form").submit();
};


function remove(id){
	
	$("#"+id).remove();
}


    </script>
    
     <script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
  
</body>


