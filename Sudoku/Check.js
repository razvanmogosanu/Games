function checkColumn() {
    let value = $('.selected').text();
    let select = parseInt($('.selected').attr('id'));
    select = select % 9;

    let check = 0;
    while (select <= 80) {
        if (value === $('#' + select).text()) {
            $('#' + select).addClass('wrong');
            check++;
        }
        select = select + 9;
    }
}

function checkLine() {
    let value = $('.selected').text();

    let select = parseInt($('.selected').attr('id'));
    select = select - select % 9;
    let end = select + 9;

    let check = 0;
    while (select < end) {
        if (value === $('#' + select).text()) {
            $('#' + select).addClass('wrong');
            check++;
        }
        select = select + 1;
    }
}

function checkQuarter() {
    var quarter = findWhatQuarter();
    let value = $('.selected').text();

    let check = 0;
    for (let i in quarter) {
        if (value === $('#' + quarter[i]).text()) {
            $('#' + quarter[i]).addClass('wrong');
            check++;
        }
    }
}

function findWhatQuarter() {
    var quarter1 = [0, 1, 2,
        9, 10, 11,
        18, 19, 20];
    var quarter2 = [3, 4, 5,
        12, 13, 14,
        21, 22, 23];
    var quarter3 = [6, 7, 8,
        15, 16, 17,
        24, 25, 26];

    var quarter4 = [27, 28, 29,
        36, 37, 38,
        45, 46, 47];
    var quarter5 = [30, 31, 32,
        39, 40, 41,
        48, 49, 50];
    var quarter6 = [33, 34, 35,
        42, 43, 44,
        51, 52, 53];

    var quarter7 = [54, 55, 56,
        63, 64, 65,
        72, 73, 74];
    var quarter8 = [57, 58, 59,
        66, 67, 68,
        75, 76, 77];
    var quarter9 = [60, 61, 62,
        69, 70, 71,
        78, 79, 80];

    let select = parseInt($('.selected').attr('id'));

    if (quarter1.includes(select))
        return quarter1;
    if (quarter2.includes(select))
        return quarter2;
    if (quarter3.includes(select))
        return quarter3;

    if (quarter4.includes(select))
        return quarter4;
    if (quarter5.includes(select))
        return quarter5;
    if (quarter6.includes(select))
        return quarter6;

    if (quarter7.includes(select))
        return quarter7;
    if (quarter8.includes(select))
        return quarter8;
    if (quarter9.includes(select))
        return quarter9;
}

function checkEnd() {
    let check = true;
    for (let i = 0; i <= 80; i++) {
        var temp = $('#' + i);
        if (temp.text() === "" || temp.hasClass('wrong')) {
            check = false;
        }
    }
    if (!check)
        alert('Keep trying!');
    else
        alert('Good job, pal');
}