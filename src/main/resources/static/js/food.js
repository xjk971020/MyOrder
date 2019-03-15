//弹出添加食品 模态框
$(document).on("click", "#createFood", function () {
    $("#create-modal").modal({show: "true", backdrop: 'static'});
})
//添加食品确定按钮
$(".create-sure").click(function () {
    if ($("#create-foodName").val() == "" || $("#create-foodPrice").val() == "" ||
        $("#create-foodDescription").val() == "" || $("#create-foodDiscountPrice") == "") {
        alert("所有信息都必须填写");
        return false;
    } else {
        $("#text-merchantId").val($("#merchantId").html());
        $("#text-categoryId").val($("#categoryId").html());
        var options = {
            success: function (result) {
                if (result.code == 20000) {
                    alert(result.message);
                    window.location.reload();
                } else {
                    alert(result.message);
                    return false;
                }
            },
            error: function () {
                return false;
            }
        };
        $("#createFoodForm").ajaxSubmit(options);
        return false;
    }
})
//创建食品图片
function sc() {
    var name = $("#create-foodPic").val();
    var src = "#create-foodPic";
    checkFile(name,src);
}
//编辑食品图片
function editPic() {
    var name = $("#edit-modal-foodPic").val();
    var src =  "#edit-modal-foodPic";
    checkFile(name,src);
}
//图片上传判断
function checkFile(name,src) {
    var animateimg =name; //获取上传的图片名 带//
    var imgarr = animateimg.split('\\'); //分割
    var myimg = imgarr[imgarr.length - 1]; //去掉 // 获取图片名
    var houzui = myimg.lastIndexOf('.'); //获取 . 出现的位置
    var ext = myimg.substring(houzui, myimg.length).toUpperCase();  //切割 . 获取文件后缀
    var file = $(src).get(0).files[0]; //获取上传的文件
    var fileSize = file.size;           //获取上传的文件大小
    var maxSize = 10485760;              //最大10MB
    if (ext != '.PNG' && ext != '.GIF' && ext != '.JPG' && ext != '.JPEG' && ext != '.BMP') {
        alert('文件类型错误,请上传图片类型');
        $(src).val("");
        return;
    } else if (parseInt(fileSize) >= maxSize) {
        alert('上传的文件不能超过10MB');
        $(src).val("");
        return;
    }
}
//删除食品
$(document).on("click", "#delete-food-btn", function (e) {
    var foodName = $(e.target).parent().parent().find("h3").text();
    var foodId = $(e.target).parent().parent().find("#foodId").html();
    if (!confirm("确定要删除【" + foodName + "】")) {
        return false;
    }
    $.ajax({
        url: "../../../food/delete/" + foodId,
        type: "GET",
        success: function (result) {
            if (result.code == 20000) {
                alert(result.message);
                window.location.reload();
            } else {
                alert(result.message);
                return false;
            }
        },
    })
})
//编辑食品 弹出模态框
$(document).on("click", "#edit-food-btn", function (e) {
    var foodName = $(e.target).parent().parent().find("h3").text();
    var foodId = $(e.target).parent().parent().find("#foodId").html();
    var categoryId = $(e.target).parent().parent().find("#edit-btn-categoryId").val();
    var foodDescription = $(e.target).parent().parent().find("#edit-btn-foodDescription").val();
    var foodDiscountPrice = $(e.target).parent().parent().find("#edit-btn-foodDiscountPrice").val();
    var foodPrice = $(e.target).parent().parent().find("#edit-btn-foodPrice").val();
    var foodSpecialty = $(e.target).parent().parent().find("#edit-btn-foodSpecialty").val();

    $("#edit-modal-foodName").val(foodName);
    $("#edit-modal-categoryId").val(categoryId);
    $("#edit-modal-foodDescription").val(foodDescription);
    $("#edit-modal-foodDiscountPrice").val(foodDiscountPrice);
    $("#edit-modal-foodId").val(foodId);
    $("#edit-modal-foodPrice").val(foodPrice);

    if (foodSpecialty == "true") {
        $('.idSpecialty').prop("checked", true);
    } else {
        $('.notSpecialty').prop("checked", true);
    }

    var statue = $(e.target).parent().parent().find("#edit-btn-foodStatue").val();
    var statues= $("#edit-modal-foodStatue").find("option");
    for (var i = 0; i < statues.length; i ++) {
        if (statue == $(statues[i]).val()){
            $(statues[i]).attr("selected","selected");
        }
    }
    $("#edit-modal").modal({show: "true", backdrop: 'static'});
})
//修改食品确定按钮
$(".edit-sure").click(function () {
    if ($("#edit-modal-foodName").val() == "" || $("#edit-modal-foodPrice").val() == "" ||
        $("#edit-modal-foodDescription").val() == "" || $("#edit-modal-foodDiscountPrice") == "") {
        alert("所有信息都必须填写");
        return false;
    } else {
        console.log($("#edit-modal-foodId").val());
        $("#edit-modal-merchantId").val($("#merchantId").html());
        $("#edit-modal-categoryId").val($("#categoryId").html());
        var options = {
            success: function (result) {
                if (result.code == 20000) {
                    alert(result.message);
                    window.location.reload();
                } else {
                    alert(result.message);
                    return false;
                }
            },
            error: function () {
                return false;
            }
        };
        $("#editFoodForm").ajaxSubmit(options);
        return false;
    }
})
//弹出 预览 弹出框
$(document).on("click","#see-food-btn",function () {
    $("#see-modal").modal({show: "true", backdrop: 'static'});
})