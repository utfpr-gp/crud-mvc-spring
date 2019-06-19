$(document).ready(function () {	
	$('.collapsible').collapsible();
        $('#year').html(getCurrentYear());
        $('select').material_select();
        $(".button-collapse").sideNav();
});

function getCurrentYear(){
    return new Date().getFullYear();    
}

$(document).ready(function() {
    $('#birthDate').mask('00/00/0000');
});




