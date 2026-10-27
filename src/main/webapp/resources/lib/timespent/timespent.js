
var timespent = {};




timespent.run = function() {
	
		var secondsSpentElement = document.getElementById("seconds-spent");
		
	    var millisecondsSpentElement = document.getElementById("milliseconds-spent");
        var focuss =1;
		var performance = window.performance, round = Math.round;

	
		 requestAnimationFrame(
			
			function updateTimeSpent(){
			
					    var timeNow = performance.now();
	
					     secondsSpentElement.value = round( (timeNow/1000) );
					     requestAnimationFrame(updateTimeSpent);
			
					   // millisecondsSpentElement.value = round(timeNow);
					    console.log(timeNow);
				
					  
					});
	
		
}


