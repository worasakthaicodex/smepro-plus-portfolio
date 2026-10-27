<c:if test="${log_id > 0 }"> 
  <script>      

$(".u-${log_id}").remove();
</script>  
</c:if>

<c:if test="${edata_id  != null  }"> 
  <script>      

  $(".e-${edata_id}").remove();
</script>  
</c:if>
