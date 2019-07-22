function cleanColumn() {
    let value = $('.selected').text();
    let select = parseInt($('.selected').attr('id'));
    select = select % 9;

    while (select <= 80) {
        if (value === $('#' + select).text()) {
            $('#' + select).removeClass('wrong');
        }
        select = select + 9;
    }
}

function cleanLine() {
    let value = $('.selected').text();

    let select = parseInt($('.selected').attr('id'));
    select = select - select % 9;
    let end = select + 9;

    while (select < end) {
        if (value === $('#' + select).text()) {
            $('#' + select).removeClass('wrong');
        }
        select = select + 1;
    }
}

function cleanQuarter() {
    var quarter = findWhatQuarter();
    let value = $('.selected').text();

    for (let i in quarter) {
        if (value === $('#' + quarter[i]).text()) {
            $('#' + quarter[i]).removeClass('wrong');
        }
    }
}