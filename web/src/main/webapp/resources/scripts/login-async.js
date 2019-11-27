function callServlet(){
	$('#loginform').submit(	function(event){ 
		
		event.preventDefault();
		var $form=$(this);
		
		$.ajax({ 	method: $form.attr("method"),
					url: $form.attr("action"),
					data: {
							username: $('#inputUser').val(),
							password: $('#inputPassword').val()
						},
					success: function(result){
								
								if(result["result"]){
									console.log("Üdvözöljük!");
									location.replace("/warehouse/secured/profil.html");
								}
								else{
									console.log("Érvénytelen belépési adatok!");
								}
							}, 		
					failed:  function(){
						
						concole.log("failed");
					}
	
	  });
	});
}