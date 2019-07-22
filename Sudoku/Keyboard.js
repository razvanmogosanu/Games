$(document).ready(function () {
    $(".cell").click(clicker);
    $('.cell').keydown(function(e) {
    let str = parseInt($('.selected').attr('id'));
        console.log(e);
        switch(e.key) {
            case 'ArrowDown':
                if(str + 9 < 81 && str + 9 >= 0) {
                    $('.selected').removeClass('selected');
                    $('#' + (str + 9)).addClass('selected');
                }
                break;
            case 'ArrowUp':
                if(str - 9 < 81 && str - 9 >= 0) {
                    $('.selected').removeClass('selected');
                    $('#' + (str - 9)).addClass('selected');
                }
                break;
            case 'ArrowLeft':
                if(str - 1 < 81 && str - 1 >= 0) {
                    $('.selected').removeClass('selected');
                    $('#' + (str - 1)).addClass('selected');
                }
                break;
            case 'ArrowRight':
                if(str + 1 < 81 && str + 1 >= 0){
                    $('.selected').removeClass('selected');
                    $('#' + (str + 1)).addClass('selected');
                }
                break;
            case 'Backspace':
                if (! $('.selected').hasClass('unmodifiable')){
                    cleanTable();
                    $('.selected').text('');
                }
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if(!$('.selected').hasClass('unmodifiable')) {
                    cleanTable();
                    $('.selected').text(e.key);
                    checker();
                }
                break;
        }
    });
});

function clicker() {
    $('.selected').removeClass('selected');
    $(this).addClass('selected');
}

function checker(){
    checkColumn();
    checkLine();
    checkQuarter();
    $('.selected').removeClass('wrong');
}

function cleanTable(){
    cleanColumn();
    cleanLine();
    cleanQuarter();
}