$(document).ready(function () {
        $('#year').html(getCurrentYear());
        $('select').formSelect();
        $(".sidenav").sidenav();
        $('.modal').modal({
            onOpenEnd: function (modal, trigger) {
                var url = $(trigger).data('url');
                var name = $(trigger).data('name');

                modal = $(modal);
                var form = modal.find('form');
                var action = form.attr('action');
                form.attr('action', url);

                modal.find('#strong-name').text(name);
            }
        });
});

function getCurrentYear(){
    return new Date().getFullYear();    
}



$(document).ready(function() {
    $('#birthDate').mask('00/00/0000');

});




