
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
		      
		      function createGraph(times, counts) {
		        var ctx = document.getElementById('emailSent').getContext('2d');
		        var gradient = ctx.createLinearGradient(0,0,0,483);
		        gradient.addColorStop(0.2, 'rgba(0,255,0, 0.8)');
		        gradient.addColorStop(0.5, 'rgba(70, 70, 150, 0.7)');
		        gradient.addColorStop(0.8, 'rgba(255, 0, 0, 0.8)');
		        /*var chartElement = new Chart(ctx, {
		          type: 'line',
		          data: {
		            labels: times,
		            datasets: [{
		            	
		              pointHoverBackgroundColor: "white",
		              fill: true,
		              pointHoverRadius: 10,
		              showLine: true, // no line shown
		              pointRadius: (times.length > 50) ? 2 : 6,
		              backgroundColor: gradient,
		            //  pointBackgroundColor: "red",

		              data: counts,
		              lineTension: undefined,
		            }]
		          },

                   options: {
                   				responsive: true,
                   				title: {
                   					display: true,
                   					text: ''
                   				},
                   				tooltips: {
                   					mode: 'index',
                   					intersect: false,
                   				},
                   				hover: {
                   					mode: 'nearest',
                   					intersect: true
                   				},
                   				scales: {
                   					xAxes: [{
                   						display: true,
                   						scaleLabel: {
                   							display: true,
                   							labelString: 'Minute'
                   						}
                   					}],
                   					yAxes: [{
                   						display: true,
                   						scaleLabel: {
                   							display: true,
                   							labelString: 'Counts'
                   						}
                   					}]
                   				}
                   			}


		        })*/

                var chartElement = new Chart(ctx, {
                type: 'line',
               data: {
               		            labels: times,
               		            datasets: [{
                                               label: 'Emails DataSet',
               		                           backgroundColor: [
                                                   'rgba(255, 99, 132, 0.2)',
                                                   'rgba(54, 162, 235, 0.2)',
                                                   'rgba(255, 206, 86, 0.2)',
                                                   'rgba(75, 192, 192, 0.2)',
                                                   'rgba(153, 102, 255, 0.2)',
                                                   'rgba(255, 159, 64, 0.2)'
                                               ],
                                               borderColor: [
                                                   'rgba(255,99,132,1)',
                                                   'rgba(54, 162, 235, 1)',
                                                   'rgba(255, 206, 86, 1)',
                                                   'rgba(75, 192, 192, 1)',
                                                   'rgba(153, 102, 255, 1)',
                                                   'rgba(255, 159, 64, 1)'
                                               ],
                                  fill: false,
               		              data: counts,

               		            }]
               		          }
                         })


		        return chartElement;
		      }
		      var times = jsonData.map(e => e.createdTime);
		      var counts = jsonData.map(e => e.countInSameMinute);

		      createGraph(times, counts)
		      }
      

      
    }
    
	window.addEventListener("load",  () => emails.getData() );

//  var ctx = document.getElementById('emailSent').getContext('2d');
//  var myChart = new Chart(ctx, {
//      type: 'bar',
//      data: {
//          labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
//          datasets: [{
//              label: '# of Votes',
//              data: [12, 19, 3, 5, 2, 3],
//              backgroundColor: [
//                  'rgba(255, 99, 132, 0.2)',
//                  'rgba(54, 162, 235, 0.2)',
//                  'rgba(255, 206, 86, 0.2)',
//                  'rgba(75, 192, 192, 0.2)',
//                  'rgba(153, 102, 255, 0.2)',
//                  'rgba(255, 159, 64, 0.2)'
//              ],
//              borderColor: [
//                  'rgba(255,99,132,1)',
//                  'rgba(54, 162, 235, 1)',
//                  'rgba(255, 206, 86, 1)',
//                  'rgba(75, 192, 192, 1)',
//                  'rgba(153, 102, 255, 1)',
//                  'rgba(255, 159, 64, 1)'
//              ],
//              borderWidth: 1
//          }]
//      },
//      options: {
//          scales: {
//              yAxes: [{
//                  ticks: {
//                      beginAtZero:true
//                  }
//              }]
//          }
//      }
//  });