$(document).ready(function () {

    $('#myTable').dataTable({

        style: 'display',

        ajax: {
            url: '/book',
            type: 'GET',
            dataType: 'json',
            dataSrc:'',
        },

        columnDefs:[
            {
                targets: 0,
                searching:false,
                ordering:false,
                render: function (data, type, row, meta) {
                    return '<center><input style="width: auto" type="checkbox"></center>';
                }
            },{
                targets:1,
                data:'name',
            },{
                targets:2,
                data:'num',
            },{
                targets:3,
                searching:false,
                ordering:false,
                render: function (data, type, row, meta) {
                    return '<button type="button" class="btn btn-primary" onclick="lend(this)">lend this</button>';
                }
            }
        ],

    });


    $('#submit_btn').on('click',function (event) {
        event.preventDefault();
        var data = new Object();
        data.name = $('#book_name').val();
        data.num = $('#book_num').val();

        $.ajax({
            url:'/book',
            type:'POST',
            contentType: 'application/json',
            dataType:'json',
            data:JSON.stringify(data),

            success:function (data) {
                window.location.reload();
            }
        });

        return false;

    });

});


function lend(_this) {
    var name = $(_this).parent().siblings().eq(1).text();
    var data = new Object();
    data.name = name;

    $.ajax({
        url:'/lib/lend',
        type:'POST',
        data:data,

        success:function(data){
            alert("success");
        },

    });
}