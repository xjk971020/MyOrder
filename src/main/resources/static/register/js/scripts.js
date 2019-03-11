
jQuery(document).ready(function() {
	
    /*
        Fullscreen background
    */
    $.backstretch("register/img/backgrounds/1.jpg");
    
    $('#top-navbar-1').on('shown.bs.collapse', function(){
    	$.backstretch("resize");
    });
    $('#top-navbar-1').on('hidden.bs.collapse', function(){
    	$.backstretch("resize");
    });
    
    /*
        Form
    */
    $('.registration-form fieldset:first-child').fadeIn('slow');
    
    $('.registration-form input[type="text"], .registration-form input[type="password"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    // next step
    $('.registration-form .btn-next').on('click', function() {
    	var parent_fieldset = $(this).parents('fieldset');
    	var next_step = true;
    	
    	parent_fieldset.find('input[type="text"], input[type="password"], textarea').each(function() {
    		if( $(this).val() == "" ) {
    			$(this).addClass('input-error');
    			next_step = false;
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    	if( next_step ) {
    		parent_fieldset.fadeOut(400, function() {
	    		$(this).next().fadeIn();
	    	});
    	}
    	
    });
    
    // previous step
    $('.registration-form .btn-previous').on('click', function() {
    	$(this).parents('fieldset').fadeOut(400, function() {
    		$(this).prev().fadeIn();
    	});
    });
    
    // submit
    $('.registration-form .register').on('click', function(e) {
        var success = true;

        if ( $('.form-password').val() != $('.form-repeat-password').val()) {
            $(this).addClass('input-error');
        	alert("两次输入的密码不一致");
            return false;
        } else{
            $(this).removeClass('input-error');
            return;
		}
        // if( success ) {
        //     $.ajax({
        //         url: "merchant/create",
        //         type: "POST",
        //         dataType: "json",
        //         contentType: "application/json;charset=utf-8",
        //         data: JSON.stringify({
        //             merchantName: $("#name").val(),
        //             merchantDescribe: $("#description").val(),
        //             merchantProvince: $("#province").val(),
        //             merchantCity: $("#city").val(),
        //             merchantAddress: $("#address").val(),
        //             merchantPhone: $("#phone").val(),
        //             password: $("#form-password").val(),
        //             startTime: $("#start-time").val(),
        //             endTime: $("#end-time").val()
        //         }),
        //         success: function (result) {
        //             alert(JSON.stringify(result));
        //             if (result == "success") {
        //                 // console.log(JSON.stringify(result));
        //                 window.location.href = "/login";
        //             }
        //         }
        //     })
        // }
    });
    
    
});
