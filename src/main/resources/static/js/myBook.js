$(document).ready(function () {

    $('#myBook').dataTable({

        ajax: {
            url: '/lib/lend',
            type: 'GET',
            dataType: 'json',
            dataSrc: '',
        },

        columnDefs: [
            {
                targets: 0,
                searching: false,
                ordering: false,
                render: function (data, type, row, meta) {
                    return '<center><input style="width: auto" type="checkbox"></center>';
                }
            }, {
                targets: 1,
                data: 'name',
            }, {
                targets: 2,
                data: 'LendedDate',
            }, {
                targets: 3,
                searching: false,
                ordering: false,
                render: function (data, type, row, meta) {
                    return '<button type="button" class="btn btn-primary" onclick="giveBack(this)">lend this</button>';
                }
            }
        ],

    });
}

function giveBack(_this) {
    var name = $(_this).parent().siblings().eq(1).text();



}