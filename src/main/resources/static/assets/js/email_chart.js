
    let emails = {
    		jsonData : {},
    	
    		getData : function(){
			    function handleSuccess () {
                    console.log(this.responseText);
				    jsonData = JSON.parse( this.responseText )
				    emails.draw();

				    var element = document.getElementById("email_sentiment_chart_spinner");
					element.parentNode.removeChild(element);
				    }
				function handleError () {
				      console.log( 'An REST endpoint error  occurred' );
				    }

				    const asyncRequestObject = new XMLHttpRequest();
				    asyncRequestObject.open('GET', rest_url + 'emails/'+document.getElementById("user_Id").textContent);
				    asyncRequestObject.onload = handleSuccess;
				    asyncRequestObject.onerror = handleError;
				    asyncRequestObject.send();
			  },
			draw : function(){
		      
		      function createGraph(dates, sents) {
		        var ctx = document.getElementById('emailSent').getContext('2d');
		        var gradient = ctx.createLinearGradient(0,0,0,483);
		        gradient.addColorStop(0.2, 'rgba(0,255,0, 0.8)');
		        gradient.addColorStop(0.5, 'rgba(70, 70, 150, 0.7)');
		        gradient.addColorStop(0.8, 'rgba(255, 0, 0, 0.8)');
		        var chartElement = new Chart(ctx, {
		          type: 'line',
		          data: {
		            labels: dates,
		            datasets: [{
		            	
		              pointHoverBackgroundColor: "white",
		              fill: true,
		              pointHoverRadius: 10,
		              showLine: true, // no line shown
		              pointRadius: (dates.length > 50) ? 2 : 6,
		              backgroundColor: gradient,
		            //  pointBackgroundColor: "red",

		              data: sents,
		              lineTension: undefined,
		            }]
		          },
		          options: {
		        	  
		         	 onClick:  function(e, array){
		         		 let index = array[0]._index;
		         		// console.log("/video/"+jsonData.emails[index].id);
		         		 window.location.href="/email/"+jsonData.emails[index].id;
		               },
		           
		               responsive: true,
		               legend: { display: false },
		               layout: {
		 	              padding: 20 },
		               elements: {
		                   point: { pointStyle: "circle" } },
		               scales: {
		                       xAxes: [{
		                     	  display: false
		                       }],
		 	                  yAxes: [{
		 	                	  display: true
		 	                  }]
		                   }
		           }
		        })
		        return chartElement;
		      }

		      var dates = jsonData.emails
		        .map(e => new Date(e.sent * 1000).toDateString());
		      var sents = jsonData.emails.map(e => {
		    	  return e.text.sentiment.toFixed(2);
		      });
		      
		      createGraph(dates, sents)
		      }
      

      
    }
    
	window.addEventListener("load",  () => emails.getData() );

  