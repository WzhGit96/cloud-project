var pagination;

var allGoods = new Vue({
    el: '#allGoods',
    data: {
        rows: null,
        total: null
    },
    methods: null,
    created: function () {
        $.ajax({
            url: '/goods/findNormalGoods.action',
            data: {pageSize: 32, pageNo: 1},
            dataType: 'JSON',
            method: 'POST',
            success: function (data) {
                allGoods.rows = data.rows;
                allGoods.total = data.total;

                pagination = new Vue({
                    el: '#pagination',
                    data: {
                        total: 0,
                        page: 1,
                        totalPage: 0
                    },
                    methods: {
                        lastPage: function () {
                            this.page--;
                            if (this.page <= 0) {
                                this.page = 1;
                                bootbox.alert({
                                    message: "已经是首页了",
                                    size: 'small'
                                });
                                return;
                            } else {
                                $.ajax({
                                    url: '/goods/findAllByPage.action',
                                    data: {pageSize: 32, pageNo: this.page},
                                    dataType: 'JSON',
                                    method: 'POST',
                                    success: function (data) {
                                        allGoods.rows = data.rows;
                                        scrollTo(0,0);
                                    }
                                });
                            }
                        },
                        selectPage: function (pageNo) {
                            this.page = pageNo;
                            $.ajax({
                                url: '/goods/findAllByPage.action',
                                data: {pageNo: this.page, pageSize: 32},
                                dataType: 'JSON',
                                method: "POST",
                                success: function (data) {
                                    allGoods.rows = data.rows;
                                    scrollTo(0,0);
                                }
                            });
                        },
                        nextPage: function () {
                            this.page++;
                            if (this.page > this.totalPage) {
                                this.page--;
                                bootbox.alert({
                                    message: "最后一页了",
                                    size: 'small'
                                });
                                return;
                            } else {
                                $.ajax({
                                    url: '/goods/findAllByPage.action',
                                    data: {pageSize: 32, pageNo: this.page},
                                    dataType: 'JSON',
                                    method: 'POST',
                                    success: function (data) {
                                        allGoods.rows = data.rows;
                                        scrollTo(0,0);
                                    }
                                });
                            }

                        }
                    },
                    created: function () {
                        var length =Math.ceil(allGoods.total / 32);
                        this.totalPage = length;
                        var str = '<li><a href="javascript:void(0)" @click="lastPage()">&laquo;</a></li>';
                        for (var i = 0; i < length; i++) {
                            var index = i + 1;
                            str += '<li><a href="javascript:void(0)" @click="selectPage('+index+')">' + index + '</a></li>';
                        }
                        str += '<li><a href="javascript:void(0)" @click="nextPage()">&raquo;</a></li>';
                        $(".page-ul").append(str);
                    }
                });




            }
        });
    }
});


Vue.component('goods-list', {
    props: ['row'],
    template: '<div class="items">' +
    '<div class="thumbnail item-content">' +
    '<a href="javascript:void(0)" @click="toGoodsInfo(row.id)">' +
    '<img v-bind:src="row.pic" style="width:250px;height: 250px;">' +
    '<div class="caption">' +
    '<h3>{{row.name}}</h3>' +
    '<p>商品价格(￥):{{row.price}}</p>' +
    '<p>访问量:{{row.count}}</p>' +
    '</div></a></div></div>',
    methods: {
        toGoodsInfo: function (id) {
            $.ajax({
                url: '/goods/addCount.action',
                data: {id:id},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    if (data == 1) {
                        window.location.href= '/front/goodsInfo/page?gid='+id;
                    }
                }
            });
        }
    }
});

var search_header = new Vue({
    el: '#search-header',
    data: {
        rows: null,
        total: 0
    },
    methods: {
        searchGoods : function () {
            var goodsName = $("#goods-header-name").val();
            if (goodsName == null || goodsName == '') {
                bootbox.alert({
                    message: "请输入商品名...",
                    size: 'small'
                });
                return;
            }
            $.ajax({
                url: '/goods/findGoodsByName.action',
                data: {pageSize: 32, pageNo: 1, name: goodsName},
                dataType: 'JSON',
                method: 'POST',
                success: function (data) {
                    if (data.total == 0 || data.rows == null) {
                        bootbox.alert({
                            message: "暂无数据",
                            size: 'small'
                        });
                    } else {
                        allGoods.rows = data.rows;
                    }

                }
            });
        }
    }
});