$(document).ready(function(){

      $("#confirmButton").click(function(event){
          event.preventDefault();
          confirmPressed = true;
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/doSubscription?taskId="+getUrlParameter("taskId")+"&status="+"paid",
            contentType: "application/json",
            success: function(data){
            	close();
            }
          });

      })
      
      $("#cancelButton").click(function(event){
          event.preventDefault();
          confirmPressed = true;
          $.ajax({
            type: "GET",
            url: "http://localhost:8080/doSubscription?taskId="+getUrlParameter("taskId")+"&status="+"expired",
            contentType: "application/json",
            success: function(data){
            	close();
            }
          });

      })
      

    function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;
    
        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');
    
            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };
})
