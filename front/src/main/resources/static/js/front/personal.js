$(function () {
    hger_navbar.isActive.homePage = false;
    hger_navbar.isActive.goodsPage = false;
    hger_navbar.isActive.borrowPage = false;
    hger_navbar.isActive.feedbackPage = false;
    hger_navbar.isActive.myRespoity = false;
    hger_navbar.isActive.shopcar = false;
    hger_navbar.isActive.seller = false;


    var id = $("#user_id").val();
    if (id == '') {
        alert("请登录");
        window.location.href = '/';
    }

    var tab = window.location.href.split("=")[1];
    if (tab) {
        $("#myTab li:eq("+tab+") a").tab('show');
        if (tab == 2) {
            hger_navbar.isActive.myRespoity = true;
        }
        if (tab == 1) {
            hger_navbar.isActive.shopcar = true;
        }
    }

});


var personalInfo = new Vue({
    el: '#personal-info',
    data: {
        rows: {},
        total: 0
    },
    methods: {
        save: function () {
            var id = personalInfo.rows.id;
            var name = $("#user_update_name").val();
            var age = $("#user_update_age").val();
            var sex;
            var radio = document.getElementsByName("sex");
            for (var i = 0; i < radio.length; i++) {
                if (radio[i].checked) {
                    sex = radio[i].value;
                    break;
                }
            }
            var qq = $("#user_update_qq").val();
            var tel = $("#user_update_tel").val();
            var addr = $("#user_update_addr").val();
            if (name == null || name == '') {
                bootbox.alert({
                    message: "姓名不能为空!!!",
                    size: 'small'
                });
                return;
            }
            if (tel == null || tel == '') {
                bootbox.alert({
                    message: "联系电话不能为空!!!",
                    size: 'small'
                });
                return;
            }
            if (qq == null || qq == '') {
                bootbox.alert({
                    message: "qq号码不能为空!!!",
                    size: 'small'
                });
                return;
            }

            $.ajaxFileUpload({
                type: "POST",
                url: "/user/updateUserInfo.action",
                data: {id: id, name: name, age: age, sex: sex, qq: qq, tel: tel, addr: addr},
                secureuri: false,//是否启用安全提交，默认为false
                enctype: "multipart/form-data",
                fileElementId: ["inputfile"],//文件选择框的id属性
                dataType: 'json',//服务器返回的格式
                success: function (data) {
                    if (data > 0) {
                        alert("修改成功");
                        window.location.reload();
                    } else {
                        bootbox.alert({
                            message: "修改失败!!!",
                            size: 'small'
                        });
                    }
                }
            });
        }

    },
    computed: {
        ulevel: function () {
            if (this.rows.ulevel <= 5) {
                return '青铜';
            }
            if (this.rows.ulevel > 5 && this.rows.ulevel <= 10) {
                return '白银';
            }
            if (this.rows.ulevel > 10 && this.rows.ulevel <= 20) {
                return '黄金';
            }
            if (this.rows.ulevel > 20 && this.rows.ulevel <= 35) {
                return '铂金';
            }
            if (this.rows.ulevel > 35 && this.rows.ulevle <= 50) {
                return '钻石';
            }
        }
    },
    created: function () {
        $.ajax({
            url: '/init.action',
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                if (data.rows == null) {
                    bootbox.confirm({
                        title: "提示",
                        message: "请先登录...",
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
                                window.location.href = '/';
                            } else {
                                window.location.href = '/';
                            }
                        }
                    });
                }
                personalInfo.rows = data.rows;
                if (data.rows.sex == '男') {
                    $("#user_update_sex_m").attr('checked', 'checked');
                }
                if (data.rows.sex == '女') {
                    $("#user_update_sex_f").attr('checked', 'checked');
                }
            }
        });
    }
});


var myDeallog = new Vue({
    el: '#myDeallog',
    data: {
        rows: {}
    },
    methods: {},
    created: function () {
        $.ajax({
            url: '/deallog/findDeallogByUid.action',
            data: {pageSize: 15, pageNo: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                myDeallog.rows = data.rows;
            }
        });
    }
});

Vue.component('deallog-list', {
    props: ['row'],
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
    '                                            <td width="83px;" height="120px;">{{row.guname}}</td>\n' +
    '                                            <td width="136px;" height="120px;"><a href="javascript:void(0)" @click="checkGoods(row.gid, row.status)">查看商品</a> </td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{way(row.gway)}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{status(row.status)}}</td>\n' +
    '                                            <td width="83px;" height="120px;" style="border-left: 1px solid #ECECEC;"><a v-if="row.status == 0" href="javascript:void(0)" @click="confirmGoods(row.id, row.gid)">确认收货</a><a v-if="row.status == 0" href="javascript:void(0)" @click="shutdownDeal(row.id, row.gid)">终止交易</a><a v-if="row.status == 1" href="javascript:void(0)" @click="backGoods(row.id, row.overdate)">退货</a> </td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                            </div>',
    methods: {
        checkGoods: function (id, status) {

            if (status == -1) {
                bootbox.alert({
                    message: "交易已经终止，无法查看",
                    size: 'small'
                });
                return ;
            }


            $.ajax({
                url: '/goods/findGoodsById.action',
                data: {id: id},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    var goods = data.rows;
                    $("#goods_select_name").val(goods.name);
                    $("#goods_select_uname").val(goods.uname);
                    $("#goods_select_price").val(goods.price);
                    $("#goods_select_oldprice").val(goods.oldprice);
                    $("#goods_select_gdescribe").val(goods.gdescribe);
                    if (goods.quality == 1) {
                        $("#goods_select_quality").val("全新");
                    } else if (goods.quality == 2) {
                        $("#goods_select_quality").val("非全新");
                    }
                    $("#goods_select_qq").val(goods.qq);
                    $("#goods_select_tel").val(goods.tel);
                    $("#goodsInfoModal").modal("show");
                }
            });
        },
        confirmGoods: function (id, gid) {
            bootbox.confirm({
                title: "提示",
                message: "商品到手了吗？",
                buttons: {
                    cancel: {
                        label: '<i class="fa fa-check"></i> 还没呢'
                    },
                    confirm: {
                        label: '<i class="fa fa-check"></i> 到手了'
                    }
                },
                callback: function (result) {
                    if (result) {
                        $.ajax({
                            url: '/deallog/confirmGoods.action',
                            data: {id: id, gid: gid},
                            dataType: 'JSOn',
                            method: 'POST',
                            success: function (data) {
                                if (data == 1) {
                                    bootbox.alert({
                                        message: "收货成功!!!",
                                        size: 'small'
                                    });
                                    window.location.reload();
                                }
                            }
                        });
                    } else {
                        return ;
                    }
                }
            });
        },
        backGoods: function (id, date) {
            var now = new Date().getTime();
            var threeDays = 24*60*60*3;
            if (now - date > threeDays) {
                bootbox.alert({
                    message: "交易完成已经超过三天，无法退货",
                    size: 'small'
                });
            } else {


                bootbox.confirm({
                    title: "提示",
                    message: "确定要退货吗？",
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
                                url: '/deallog/backGoods.action',
                                data: {id: id},
                                dataType: 'JSOn',
                                method: 'POST',
                                success: function (data) {
                                    if (data == 1) {
                                        bootbox.alert({
                                            message: "退货成功!!!",
                                            size: 'small'
                                        });
                                        window.location.reload();
                                    }
                                }
                            });
                        } else {
                            return ;
                        }
                    }
                });
            }
        },
        shutdownDeal: function (id, gid) {
            bootbox.confirm({
                title: "提示",
                message: "确定要终止交易吗？",
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
                            url: '/deallog/shutdownDeal.action',
                            data: {id: id, gid: gid},
                            dataType: 'JSON',
                            method: 'POST',
                            success: function (data) {
                                if (data == 1) {
                                    bootbox.alert({
                                        message: "终止成功!!!",
                                        size: 'small'
                                    });
                                    window.location.reload();
                                }
                            }
                        });
                    } else {
                        return ;
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
                    return '交易终止';
                } else if (s == 0) {
                    return '交易中...';
                } else if (s == 1) {
                    return '已完成';
                } else if (s == 2) {
                    return '退货中...';
                } else if (s == 3) {
                    return '退货完成';
                }
            }
        }
    }
});

var myMessage = new Vue({
    el: '#myMessage',
    data: {
        rows: {},
        total: 0
    },
    methods: {},
    computed: {},
    created: function () {
        $.ajax({
            url: '/inform/findAllByUid.action',
            data: {pageSize: 15, pageNo: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                myMessage.rows = data.rows;
            }
        });
    }
});

Vue.component('system-inform', {
    props: ['row'],
    template: '<div class="deallog-list" style="height: 80px;">\n' +
    '                                <table class="table table-borde deallog-table-header">\n' +
    '                                    <tbody>\n' +
    '                                        <tr class="active">\n' +
    '                                            <td>{{getTime(row.date)}}</td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                                <table class="table table-border deallog-table-body">\n' +
    '                                    <tbody>\n' +
    '                                        <tr>\n' +
    '                                            <td  height="44px;" style="text-align:left;border-left: 1px solid #ECECEC;">{{row.context}}</td>\n' +
    '                                            <td width="83px;" height="44px;" style="border-left: 1px solid #ECECEC;"><a href="javascript:void(0)" @click="removeInform(row.id)">删除</a> </td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                            </div>',
    computed: {
        getTime: function () {
            return function (t) {
                var _time = new Date(t);
                var year = _time.getFullYear();
                var month = _time.getMonth() + 1;
                var date = _time.getDate();
                var hour = _time.getHours();
                var mins = _time.getMinutes();
                var sec = _time.getSeconds();
                return year + "年" + month + "月" + date + "日" + hour + "时" + mins + "分" + sec + "秒";//这里自己按自己需要的格式拼接
            }
        }
    },
    methods: {
        removeInform: function (id) {
            $.ajax({
                url: '/inform/removeInform.action',
                data: {id: id, status: 0, pageSize: 15, pageNo: 1},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    myMessage.rows = data.rows;
                    hger_navbar.informSize--;
                }
            })
            
        }
    }
});

var myShop = new Vue({
    el: '#myShop',
    data: {
        rows: {},
        total: 0
    },
    methods: {},
    computed: {},
    created: function () {
        var uid = $("#user_id").val();
        $.ajax({
            url: '/shopcar/findAllByUid.action',
            data: {pageSize: 15, pageNo: 1, uid: uid},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                myShop.rows = data.rows;
            }
        });
    }
});

Vue.component('shopcar-list', {
    props: ['row'],
    template: '<div class="deallog-list">\n' +
    '                                <table class="table table-borde deallog-table-header">\n' +
    '                                    <tbody>\n' +
    '                                        <tr class="active">\n' +
    '                                            <td>{{getTime(row.date)}}</td>\n' +
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
    '                                                    <p>{{row.gname}}</p>\n' +
    '                                                </div>\n' +
    '                                            </td>\n' +
    '                                            <td width="83px;" height="120px;" style="text-align: center;">￥{{row.price}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{way(row.way)}}</td>\n' +
    '                                            <td width="136px;" height="120px;" style="border-left: 1px solid #ECECEC;">{{status(row.gstatus)}}</td>\n' +
    '                                            <td width="83px;" height="120px;" style="border-left: 1px solid #ECECEC;"><a href="javascript:void(0)" v-if="row.gstatus == 1" @click="buy(row.id, row.gid, row.gname, row.guid)">立即购买</a> <a  href="javascript:void(0)" @click="remove(row.id)">移除</a></td>\n' +
    '                                        </tr>\n' +
    '                                    </tbody>\n' +
    '                                </table>\n' +
    '                            </div>',
    computed: {
        getTime: function () {
            return function (t) {
                var _time = new Date(t);
                var year = _time.getFullYear();
                var month = _time.getMonth() + 1;
                var date = _time.getDate();
                var hour = _time.getHours();
                var mins = _time.getMinutes();
                var sec = _time.getSeconds();
                return year + "年" + month + "月" + date + "日" + hour + "时" + mins + "分" + sec + "秒";//这里自己按自己需要的格式拼接
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
                if (s != 1) {
                    return '失效';
                } else if (s == 1) {
                    return '正常';
                }
            }
        }
    },
    methods: {
        remove: function (id) {
            $.ajax({
                url: '/shopcar/updateShopCar.action',
                data: {id: id, status: 0},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    if (data == 1) {
                        window.location.reload();
                    }
                }
            })
        },
        buy: function (id, gid, gname, guid) {
            var user = hger_navbar.rows;

            if (user.name == null || user.qq == null || user.tel == null) {
                bootbox.confirm({
                    title: "提示",
                    message: "您的个人资料还未完善，请先完善您的个人资料信息才能购买哦~",
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
                            window.location.href = '/front/personal/page';
                        } else {
                            return;
                        }
                    }
                });
            }

            var uid = user.id;
            var sid = id;
            var gid = gid;
            var status = 0;
            var gname = gname;
            var guid = guid;

            if (uid == guid) {
                bootbox.alert({
                    message: "不能购买!!!",
                    size: 'small'
                });
                return;
            }

            $.ajax({
                url: '/deallog/clearShopcar.action',
                data: {uid: uid, gid: gid, status: status, gname: gname, guid: guid, sid: sid},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    if (data.result == 1) {
                        bootbox.confirm({
                            title: "购买成功",
                            message: "是否查看卖家联系信息（您也可以在 个人中心>交易记录>商品操作中查看对应商品详细信息）",
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
                                    $("#goods_check_name").val(data.rows.name);
                                    $("#goods_check_uname").val(data.rows.uname);
                                    $("#goods_check_uqq").val(data.rows.qq);
                                    $("#goods_check_utel").val(data.rows.tel);
                                    $("#checkbuyGoods").modal("show");
                                } else {
                                    window.location.reload();
                                }
                            }
                        });
                    }
                    if (data.result == -1) {
                        alert("商品已经失效...");
                        window.location.reload();
                    }
                }
            });
        }
    }
});

function showpic(data) {
    document.getElementById("myPic").src = window.URL.createObjectURL(data.files[0]);
}
$("#checkbuyGoods").on('hide.bs.modal', function () {
    window.location.reload();
})