
hger_navbar.isActive.homePage = false;
hger_navbar.isActive.goodsPage = false;
hger_navbar.isActive.borrowPage = false;
hger_navbar.isActive.feedbackPage = false;
hger_navbar.isActive.myRespoity = false;
hger_navbar.isActive.shopcar = false;
hger_navbar.isActive.seller = true;

var id = $("#user_id").val();
if (id == '') {
    alert("请登录");
    window.location.href = '/';
}

var status = $("#user_status").val();
if (status == 1) {
    $("#myTab li:eq(0) a").tab('show');
    $("#tab_pushGoods").css("display","none");
    $("#tab_managerGoods").css("display","none");
    $("#tab_managerDeal").css("display","none");
} else if (status == 2) {

    $("#tab_toSeller").css("display","none");

}




var toSeller = new Vue({
    el: '#toSeller',
    data: {
        rows: {},
        total: 0
    },
    methods: {
        push: function () {

            var uid = $("#user_id").val();
            var status = 0;
            var date = new Date().getTime();

            $.ajaxFileUpload({
                type: "POST",
                url: "/proof/addProof.action",
                data: {uid: uid, status: status, date: date},
                secureuri: false,//是否启用安全提交，默认为false
                enctype: "multipart/form-data",
                fileElementId: ["inputfile"],//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                success: function (data) {

                    if (data == -2) {
                        bootbox.alert({
                            message: "上传图片不能为空!!!",
                            size: 'small'
                        });
                        return;
                    }

                    if (data > 0) {
                        alert("上传成功，预计在两个工作日内审核完成，审核结果将发往【个人中心】>【系统通知】,请耐心等待...");
                        window.location.reload();
                    } else {
                        bootbox.alert({
                            message: "上传失败!!!",
                            size: 'small'
                        });
                        return;
                    }
                }
            });
        }
    },
    computed: {},
    created: function () {

        $.ajax({
            url: '/init.action',
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                toSeller.rows = data.rows;
            }
        });

        myTab = new Vue({
            el: '#myTab',
            data: {
                status: 0
            }
        });
    }
});


var pushGoods = new Vue({
    el: '#pushGoods',
    data: {
        rows: {}
    },
    methods: {
        pushGoods: function () {
            var uid = $("#user_id").val();
            var name = $("#goods_add_name").val();
            var price = $("#goods_add_price").val();
            var oldprice = $("#goods_add_oldprice").val();
            var gdescribe = $("#goods_add_gdescribe").val();
            var quality;
            var radio = document.getElementsByName("quality");
            for (var i = 0; i < radio.length; i++) {
                if (radio[i].checked) {
                    quality = radio[i].value;
                    break;
                }
            }
            var way = 2;
            var bargaining;
            radio = document.getElementsByName("bargaining");
            for (var i = 0; i < radio.length; i++) {
                if (radio[i].checked) {
                    bargaining = radio[i].value;
                    break;
                }
            }
            var type = $("#goods_add_type").find("option:selected").text();
            $.ajaxFileUpload({
                type: "POST",
                url: "/goods/addGoods.action",
                data: {uid: uid, name: name, price: price, oldprice: oldprice, gdescribe: gdescribe, quality: quality, way: way, bargaining: bargaining, type: type, status: 0, count: 0},
                secureuri: false,//是否启用安全提交，默认为false
                enctype: "multipart/form-data",
                fileElementId: ["inputGoodsFile"],//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                success: function (data) {

                    if (data == -2) {
                        bootbox.alert({
                            message: "上传图片不能为空!!!",
                            size: 'small'
                        });
                        return;
                    }

                    if (data > 0) {
                        alert("上架申请成功，预计在两个工作日内审核完成，审核结果将发往【个人中心】>【系统通知】,请耐心等待...");
                        window.location.reload();
                    } else {
                        bootbox.alert({
                            message: "上传失败!!!",
                            size: 'small'
                        });
                        return;
                    }
                }
            });

        }
    }

});

var managerGoods = new Vue({
    el: '#managerGoods',
    data: {
        rows: {}
    },
    methods: {},
    created: function () {
        var uid = $("#user_id").val();
        $.ajax({
            url: '/goods/findMyGoods.action',
            data: {uid: uid, pageSize: 15, pageNo: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                managerGoods.rows = data.rows;
            }
        });
    }
});

var staticGoods;

Vue.component('my-goods', {
    props: ['row'],
    template: '<div class="deallog-list">\n' +
    '                                <table class="table table-borde deallog-table-header">\n' +
    '                                    <tbody>\n' +
    '                                        <tr class="active">\n' +
    '                                            <td>{{getTime(row.pushdate)}}</td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                                <table class="table table-border deallog-table-body">\n' +
    '                                    <tbody>\n' +
    '                                        <tr>\n' +
    '                                            <td width="357px;" height="120px;">\n' +
    '                                                <div class="deallog-goods-pic" style="width: 80px;float: left;">\n' +
    '                                                <img :src="row.pic" class="img-thumbnail" style="width: 80px;height: 80px;"/>\n' +
    '                                                </div>\n' +
    '                                                <div class="deallog-goods-info" style="margin-left: 20px;float: left;">\n' +
    '                                                    <p>{{row.name}}</p>\n' +
    '                                                </div>\n' +
    '                                            </td>\n' +
    '                                            <td width="83px;" height="120px;" style="text-align: center;">￥{{row.price}}</td>\n' +
    '                                            <td width="83px;" height="120px;">{{row.uname}}</td>\n' +
    '                                            <td width="136px;" height="120px;"><a href="javascript:void(0)" @click="updateGoods(row)">修改商品</a> </td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{way(row.way)}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{status(row.status)}}</td>\n' +
    '                                            <td width="83px;" height="120px;" style="border-left: 1px solid #ECECEC;"><a v-if="row.status == -1" href="javascript:void(0)" @click="pushGoods(row.id)">上架申请</a><a v-if="row.status == 0" href="javascript:void(0)">取消上架</a><a v-if="row.status == 1" href="javascript:void(0)" @click="pullGoods(row.id)">下架</a></td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                            </div>',
    methods: {
        updateGoods: function (goods) {

            staticGoods = goods;
            $("#myUpdatePic").attr("src",goods.pic);
            $("#goods_update_name").val(goods.name);
            $("#goods_update_price").val(goods.price);
            $("#goods_update_oldprice").val(goods.oldprice);
            $("#goods_update_quality_"+goods.quality).attr("checked","checked");
            $("#goods_update_bargaining_"+goods.bargaining).attr("checked","checked");
            $("#goods_update_gdescribe").val(goods.gdescribe);
            $("#updateGoodsModal").modal("show");

        },
        pushGoods: function (id) {
            $.ajax({
                url: '/goods/updateGoods.action',
                data: {id: id, status: 0},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    if (data > 0) {
                        alert("上架申请成功，预计在两个工作日内审核完成，审核结果将发往【个人中心】>【系统通知】,请耐心等待...!");
                        window.location.reload();
                    }
                }
            });
        },
        pullGoods: function (id) {
            bootbox.confirm({
                title: "提示",
                message: "确认要下架？",
                buttons: {
                    cancel: {
                        label: '<i class="fa fa-check"></i> 取消'
                    },
                    confirm: {
                        label: '<i class="fa fa-check"></i> 确定'
                    }
                },
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: '/goods/updateGoods.action',
                            data: {id: id, status: -1},
                            dataType: 'JSON',
                            method: 'POST',
                            success: function (data) {
                                if (data > 0) {
                                    alert("下架成功!");
                                    window.location.reload();
                                }
                            }
                        });
                    } else {
                        return;
                    }
                }
            });
        }
    },
    computed: {
        getTime: function () {
            return function (t) {
                if (t == null || t == '') {
                    return '未完成';
                }
                var _time = new Date(t);
                var year = _time.getFullYear();
                var month = _time.getMonth() + 1;
                var date = _time.getDate();
                var hour = _time.getHours();
                var mins = _time.getMinutes();
                var sec = _time.getSeconds();
                return year + "年" + month + "月" + date + "日";//这里自己按自己需要的格式拼接
            }
        },
        way: function () {
            return function (w) {
                if (w == 1) {
                    return '在线交易';
                }
                if (w == 2) {
                    return '线下交易';
                }
            }
        },
        status: function () {
            return function (s) {
                if (s == -1) {
                    return '下架';
                } else if (s == 0) {
                    return '审核中...';
                } else if (s == 1) {
                    return '正常上架';
                }
            }
        }
    }
});


var managerDeal = new Vue({
    el: '#managerDeal',
    data: {
        rows: {},
    },
    methods: {},
    created: function () {
        var uid = $("#user_id").val();
        $.ajax({
            url: '/deallog/findBackDeallog.action',
            data: {uid: uid, pageSize: 15, pageNo: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                managerDeal.rows = data.rows;
            }
        });
    }
});

Vue.component('back-deallog',{
    props:['row'],
    template: '<div class="deallog-list">\n' +
    '                                <table class="table table-borde deallog-table-header">\n' +
    '                                    <tbody>\n' +
    '                                        <tr class="active">\n' +
    '                                            <td>{{getTime(row.overdate)}}</td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                                <table class="table table-border deallog-table-body">\n' +
    '                                    <tbody>\n' +
    '                                        <tr>\n' +
    '                                            <td width="357px;" height="120px;">\n' +
    '                                                <div class="deallog-goods-pic" style="width: 80px;float: left;">\n' +
    '                                                <img :src="row.gpic" class="img-thumbnail" style="width: 80px;height: 80px;"/>\n' +
    '                                                </div>\n' +
    '                                                <div class="deallog-goods-info" style="margin-left: 20px;float: left;">\n' +
    '                                                    <p>{{row.gname}}</p>\n' +
    '                                                </div>\n' +
    '                                            </td>\n' +
    '                                            <td width="83px;" height="120px;" style="text-align: center;">￥{{row.gprice}}</td>\n' +
    '                                            <td width="83px;" height="120px;">{{row.uname}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{way(row.gway)}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">退货中</td>\n' +
    '                                            <td width="83px;" height="120px;" style="border-left: 1px solid #ECECEC;"><a v-if="row.status == 2" href="javascript:void(0)" @click="backClear(row.id, row.gid)">退货完成</a></td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                            </div>',
    computed: {
        getTime: function () {
            return function (t) {
                if (t == null || t == '') {
                    return '未完成';
                }
                var _time = new Date(t);
                var year = _time.getFullYear();
                var month = _time.getMonth() + 1;
                var date = _time.getDate();
                var hour = _time.getHours();
                var mins = _time.getMinutes();
                var sec = _time.getSeconds();
                return year + "年" + month + "月" + date + "日";//这里自己按自己需要的格式拼接
            }
        },
        way: function () {
            return function (w) {
                if (w == 1) {
                    return '在线交易';
                }
                if (w == 2) {
                    return '线下交易';
                }
            }
        }
    },
    methods:{
        backClear: function (id, gid) {
            bootbox.confirm({
                title: "提示",
                message: "确认商品返回了吗？",
                buttons: {
                    cancel: {
                        label: '<i class="fa fa-check"></i> 还没'
                    },
                    confirm: {
                        label: '<i class="fa fa-check"></i> 是的'
                    }
                },
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: '/deallog/updateDeallog.action',
                            data: {id: id,status: 3},
                            dataType: 'JSON',
                            method: 'POST',
                            success: function (data) {
                                if (data > 0) {
                                    bootbox.confirm({
                                        title: "完成退货",
                                        message: "是否要重新上架您的商品？",
                                        buttons: {
                                            cancel: {
                                                label: '<i class="fa fa-check"></i> 算了'
                                            },
                                            confirm: {
                                                label: '<i class="fa fa-check"></i> 好的'
                                            }
                                        },
                                        callback: function (result) {
                                            if (result) {
                                                $.ajax({
                                                    url: '/goods/updateGoods.action',
                                                    data: {id: gid,  status: 1},
                                                    dataType: 'JSON',
                                                    method: 'POST',
                                                    success: function (data) {
                                                        if (data > 0) {
                                                            alert("商品上架成功！！！");
                                                            window.location.reload();
                                                        }
                                                    }
                                                });
                                            } else {
                                                window.location.reload();
                                            }
                                        }
                                    });

                                }
                            }
                        });
                    } else {
                        return;
                    }
                }
            });

        }
    }
});


function showpic(data) {
    document.getElementById("myPic").src = window.URL.createObjectURL(data.files[0]);
}

function showpic2(data) {
    document.getElementById("goodsPic").src = window.URL.createObjectURL(data.files[0]);
}
function showpic3(data) {
    document.getElementById("myUpdatePic").src = window.URL.createObjectURL(data.files[0]);
}

function updateMyGoods() {
    var id = staticGoods.id;
    var pic = $("#myUpdatePic").attr("src");
    var name = $("#goods_update_name").val();
    var price = $("#goods_update_price").val();
    var oldprice = $("#goods_update_oldprice").val();
    var quality;
    var radio = document.getElementsByName("quality_update");
    for (var i = 0; i < radio.length; i++) {
        if (radio[i].checked) {
            quality = radio[i].value;
            break;
        }
    }
    var way = 2;
    var bargaining;
    radio = document.getElementsByName("bargaining_update");
    for (var i = 0; i < radio.length; i++) {
        if (radio[i].checked) {
            bargaining = radio[i].value;
            break;
        }
    }
    var type = $("#goods_update_type").find("option:selected").text();
    var gdescribe = $("#goods_update_gdescribe").val();
    if (pic == staticGoods.pic && (name != staticGoods.name || gdescribe != staticGoods.gdescribe)) {
        bootbox.confirm({
            title: "提示",
            message: "检测到您修改了商品关键信息，商品需要重新审核，确定要修改吗？",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-check"></i> 取消'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> 确定'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajax({
                        url: '/goods/updateGoods.action',
                        data: {id: id, name: name, price: price, oldprice: oldprice, gdescribe: gdescribe, quality: quality, way: way, bargaining: bargaining, type: type, status: 0},
                        dataType: 'JSON',
                        method: 'POST',
                        success: function (data) {
                            if (data > 0) {
                                alert("修改商品成功！！！");
                                window.location.reload();
                            }
                        }
                    });
                } else {
                    return;
                }
            }
        });
    } else if (pic != staticGoods.pic || name != staticGoods.name || gdescribe != staticGoods.gdescribe) {

        bootbox.confirm({
            title: "提示",
            message: "检测到您修改了商品关键信息，商品需要重新审核，确定要修改吗？",
            buttons: {
                cancel: {
                    label: '<i class="fa fa-check"></i> 取消'
                },
                confirm: {
                    label: '<i class="fa fa-check"></i> 确定'
                }
            },
            callback: function (result) {
                if (result) {
                    $.ajaxFileUpload({
                        type: "POST",
                        url: "/goods/changeGoods.action",
                        data: {id: id, name: name, price: price, oldprice: oldprice, gdescribe: gdescribe, quality: quality, way: way, bargaining: bargaining, type: type, status: 0},
                        secureuri: false,//是否启用安全提交，默认为false
                        enctype: "multipart/form-data",
                        fileElementId: ["updateGoodsFile"],//文件选择框的id属性
                        dataType: 'json',//服务器返回的格式
                        success: function (data) {
                            if (data == -2) {
                                bootbox.alert({
                                    message: "上传图片不能为空!!!",
                                    size: 'small'
                                });
                                return;
                            }
                            if (data > 0) {
                                alert("商品修改成功");
                                window.location.reload();
                            } else {
                                bootbox.alert({
                                    message: "修改失败!!!",
                                    size: 'small'
                                });
                                return;
                            }
                        }
                    });
                } else {
                    return;
                }
            }
        });

    } else {
        $.ajax({
            url: '/goods/updateGoods.action',
            data: {id: id, price: price, oldprice: oldprice, quality: quality, way: way, bargaining: bargaining, type: type, status: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                if (data > 0) {
                    alert("修改商品成功！！！");
                    window.location.reload();
                }
            }
        });
    }
}

