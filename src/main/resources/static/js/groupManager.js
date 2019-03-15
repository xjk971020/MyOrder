//弹出添加菜品分类的模态框
$(document).on("click", "#createCategory", function () {
    $("#create-modal").modal({show: "true", backdrop: 'static'});
});
//添加 菜品分类 确定按钮
$(document).on("click", ".create-sure", function () {
    if ($("#create-foodName").val() == "" || $("#create-foodPrice").val == "") {
        alert("缺少必填信息");
        return false;
    }
    var merchantId = $("#merchantId").html();
    $.ajax({
        url: "../../category/create",
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            categoryName: $(".categoryName").val(),
            merchantId: merchantId
        }),
        success: function (result) {
            if (result.code == 20000) {
                alert(result.message);
                window.location.reload();
            } else {
                alert(result.message);
            }
            $("#create-modal").modal("hide");
        }
    })
})

//jquery dataTable插件
$(document).ready(function () {
    $('#group-manager-table').DataTable({
        'paging': true,
        'lengthChange': true,
        'searching': true,
        'ordering': false,
        'info': true,
        'autoWidth': false,
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "全部"]],
        language: {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项搜索结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 条信息",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "未搜索到数据",
            "sLoadingRecords": "没有数据.",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        ajax: {
            url: "../../category/all",
            type: "GET"
        },

        "columns": [
            {"data": "categoryId"},
            {"data": "categoryName"},
        ],
        'columnDefs': [{
            'targets': 2,
            'data': null,
            'render': function (data, type, row) {

                var param = JSON.stringify(row);
                var html = "&nbsp;<a href='javascript:void(0);' onclick='editCategory(" + param + ")'  class='down btn btn-success btn-xs'><i class='fa fa-trash'></i>编辑</a>";
                html += "&nbsp;<a href='javascript:void(0);' onclick='delCategory(" + param + ")'  class='down btn btn-danger btn-xs'><i class='fa fa-trash'></i>删除</a>";
                return html;

            }
        }]
    });
});

//弹出 编辑模态框
function editCategory(param) {
    $("#edit-modal").modal({show: "true", backdrop: 'static'});
    $("#edit-categoryName").val(param.categoryName);
    $("#categoryId").val(param.categoryId);
}

$(document).on("click", ".edit-sure", function () {
    if ($("#edit-categoryName").val() == "") {
        alert("分类名称不能为空");
        return false;
    }
    var merchantId = $("#merchantId").html();
    $.ajax({
            url: "../../category/edit",
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                categoryId : $("#categoryId").val(),
                categoryName: $("#edit-categoryName").val(),
                merchantId: merchantId
            }),
            success: function (result) {
                if (result.code == 20000) {
                    alert(result.message);
                    window.location.reload();
                } else {
                    alert(result.message);
                    return false;
                }
            }
        }
    );
})

//删除分类
function delCategory(param) {
    if (confirm("确定要删除【" + param.categoryName + "】吗(删除分类后会同时删除该分类的所有菜品)")) {
        $.ajax({
            url: "../../category/delete/" + param.categoryId,
            type: "DELETE",
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                if (result.code == 20000) {
                    alert(result.message);
                    window.location.reload();
                } else {
                    alert(result.message);
                    return false;
                }
            }
        })
    }
}