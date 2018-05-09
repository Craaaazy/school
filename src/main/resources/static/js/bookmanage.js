$(document).ready(function () {

    $('#admintable').dataTable({

        ajax: {
            url: '/bookRemaind',
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
                data: 'username',
            }, {
                targets: 2,
                data: 'bookname',
            }, {
                targets: 3,
                data: 'LendedDate',
            },{
                targets: 4,
                searching: false,
                ordering: false,
                render: function (data, type, row, meta) {
                    return '<button type="button" class="btn btn-primary" onclick="sendmail(this)">remind</button>';
                }
            }
        ],

    });
})

function sendmail(_this) {
    var name = $(_this).parent().siblings().eq(1).text();

    $.ajax({
        url:'/bookRemaind/' + name,
        type:'post',

        success:function (data) {
            alert(data);
        }

    })

}