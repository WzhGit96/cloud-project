var width = $(".page-head").width();
$(".navbar-header").css("margin-left", (width / 6) + "px");
$(".navbar-right-header").css("margin-right", (width / 10) + "px");

$('#hger-carousel').carousel({
    interval: 3000
});

function closeTab() {
    $(".mask").css("display", "none");
}

var box;

function login() {
    var email = $("#user_email_input").val();
    var password = $("#user_password_input").val();
    var code = $("#user_identifycode_input").val();

    $.ajax({
        url: '/user/login.action',
        data: {email: email, password: password, code: code},
        dataType: 'JSON',
        method: 'POST',
        success: function (data) {

            if (data.result == -1) {
                $(".mask").css("display", "none");
                alert("用户名或密码错误...");
                window.location.reload();
            }
            if (data.result == -2) {
                $(".mask").css("display", "none");
                alert("验证码错误...");
                window.location.reload();
            }
            if (data.result == 1) {
                $(".mask").css("display", "none");
                hger_navbar.rows = data.rows;
            }
            window.location.reload();
        }
    });


}

function getEmailCode() {
    var email = $("#register_email_input").val();
    if (email == null || email == '') {
        alert("请输入有效邮箱");
        return;
    }

    $("#sendCode").button("loading");


    $.ajax({
        url: '/identifyCode/email',
        data: {email: email},
        dataType: "JSON",
        method: 'POST',
        success: function (data) {
            if (data == -1) {
                alert("发送验证码失败");
            }
            if (data == 1) {
                reduceTime(60);
            }
        }
    });
}

function reduceTime(t) {
    if (t > 0) {
        $("#sendCode").html("重新发送(" + t + "s)");
        t--;
        setTimeout('reduceTime(' + t + ')', 1000);
    } else {
        $("#sendCode").button("reset");
        return;
    }
}

function register() {
    var email = $("#register_email_input").val();
    var password = $("#register_password_input").val();
    var identifyEmail = $("#register_identifycode_input").val();
    $.ajax({
        url: '/user/register.action',
        data: {email: email, password: password, identifyEmail: identifyEmail},
        dataType: 'JSON',
        method: 'POST',
        success: function (data) {
            if (data == 1) {
                alert('注册成功');
                window.location.reload();
            }
            if (data == -1) {
                alert('此帐号已经存在，请勿重复注册');
            }
            if (data == -2) {
                alert('邮箱验证码错误');

            }
            $(".mask").css("display", "none");
        }
    });
}


var hger_navbar = new Vue({
    el: '#hger_navbar',
    data: {
        rows: null,
        isActive: {homePage: true, goodsPage: false, borrowPage: false, feedbackPage: false, myRespoity: false, shopcar: false, seller: false},
        informSize: 0
    },
    methods: {
        loginInit: function () {
            $(".mask").css("display", "block");

            var totalX = document.body.clientWidth;
            var totalY = document.body.clientHeight;
            $(".mask").css("height", totalY);
            $(".mask").css("width", totalX);

            var windowX = window.screen.width;
            var windowY = window.screen.height;
            var divX = $(".login-register").width();
            var divY = $(".login-register").height();
            var iTop = (windowY - divY) / 2;
            var iLeft = (windowX - divX) / 2;
            $(".login-register").css("top", iTop);
            $(".login-register").css("left", iLeft);
        },
        feedback: function () {

            var id = $("#user_id").val();
            if (id == '') {
                alert("请登录...");
                return;
            }

            var email = this.rows.email;
            $("#feedback_email").val(email);
            $("#feedbackModal").modal("show");


        },
        myPopover: function () {
            var contentStr = '<div id="box" style="width: 260px;height: 240px;">\n' +
                '\t\t\t\t<div class="box2" style="width: 65px;height: 80px;margin: 0 auto;" >\n' +
                '\t\t\t\t\t<a href="/front/personal/page">\n' +
                '\t\t\t\t\t<img v-bind:src="rows.pic" class="img-circle" style="width: 65px;height: 65px;"><br />\n' +
                '\t\t\t\t\t</a>\n' +
                '\t\t\t\t\t<span style="text-align: center">{{getName}}</span>   <b>{{getSex}}</b>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t\t<div class="box3" style="margin-top: 20px;">\n' +
                '\t\t\t\t\t<label style="float: left;margin-left: 5px;">帐户余额(￥):<b>0.00</b></label>\n' +
                '\t\t\t\t\t<a href="javascript:void(0)" onclick="openInvest()" style="float: right;margin-right: 10px;"><label>充值</label></a>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t\t<hr style="height: 1px;border:none;border-top: 1px solid black;margin-top:60px ;">\n' +
                '\t\t\t\t<div class="u-link">\n' +
                '\t\t\t\t\t<ul class="list-unstyled" style="float: left;">\n' +
                '\t\t\t\t\t\t<li style="float: left;margin-left:20px;"><a href="/front/personal/page"><p>个人中心</p></a></li>\n' +
                '\t\t\t\t\t\t<li style="float: left;margin-left:20px;"><a href="/front/personal/page?tab=2"><p>交易记录</p></a></li>\n' +
                '\t\t\t\t\t\t<li style="float: left;margin-left:20px;"><a href="/front/personal/page?tab=3"><p>我的消息</p></a></li>\n' +
                '\t\t\t\t\t\t<li style="float: left;margin-left:20px;"><a href="javascript:void(0)" onclick="quit()"><p>退出登录</p></a></li>\n' +
                '\t\t\t\t\t</ul>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t</div>';

            $("#myPopover").popover({
                content: contentStr
            });

            $("#myPopover").popover("show");
            box = new Vue({
                el: '#box',
                data: {rows: hger_navbar.rows},
                computed: {
                    getName: function () {
                        if (this.rows.name == null) {
                            return '无名';
                        } else {
                            return this.rows.name;
                        }
                    },
                    getSex: function () {
                        if (this.rows.sex == null) {
                            return '未知';
                        } else {
                            return this.rows.sex;
                        }
                    }
                }
            });
            $(".popover").on("mouseleave", function () {
                $("#myPopover").popover("hide");
            });
        },
        inform: function () {
            if (this.informSize > 0) {
                var informContext = '<p>你有<b id="informNum">'+this.informSize+'</b>条系统通知<a href="/front/personal/page?tab=3">点击查看</a></p>';
                $("#MyInform").popover({
                    content: informContext
                });

                $("#MyInform").popover("show");
            }
        }
    },
    computed: {
        getName: function () {
            if (this.rows.name == null) {
                return '无名';
            } else {
                return this.rows.name;
            }
        }
    },
    created: function () {
        $.ajax({
            url: '/init.action',
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                hger_navbar.rows = data.rows;
                hger_navbar.informSize = data.informSize;
            }
        });
    }
});

function sendUs() {
    var uid = $("#user_id").val();
    var context = $("#feedback_context").val();
    var date = new Date().getTime();
    $.ajax({
        url: '/feedback/addFeedback.action',
        data: {uid: uid, context: context, fdate: date, status: 0},
        dataType: 'JSON',
        method: 'POST',
        success: function (data) {

            if (data > 0) {
                bootbox.alert({
                    message: "反馈成功，您的意见我们会尽快处理!!!",
                    size: 'small'
                });
                $("#feedbackModal").modal("hide");
            }

        }
    })
}

function getTime(t) {
    var _time = new Date(t);
    var year = _time.getFullYear();
    var month = _time.getMonth() + 1;
    var date = _time.getDate();
    var hour = _time.getHours();
    var mins = _time.getMinutes();
    var sec = _time.getSeconds();
    return year + "年" + month + "月" + date + "日" + hour + "时" + mins + "分" + sec + "秒";//这里自己按自己需要的格式拼接
}


function quit() {
    $.ajax({
        url: '/user/quit.action',
        dataType: 'JSON',
        method: 'POST',
        success: function (data) {
            if (data == 1) {
                window.location.reload();
            }
        }
    })
}

$('#MyInform').on('shown.bs.popover', function () {
    $("#informNum").html(hger_navbar.informSize);
   setTimeout('closeInform()',3000);

});

 function closeInform() {
    $("#MyInform").popover("hide");
}



