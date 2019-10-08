/*
 * Copyright © 2019-2019 Wzh.All rights reserved.
 */

var news = new Vue({
    el: '#news-area',
    data: {rows: null},
    created: function() {
        $.ajax({
            url: 'http://localhost:8804/news/getNews',
            data: null,
            dataType: 'JSON',
            method: 'POST',
            success: function(data) {
                news.rows = data.rows;
            }
        });
    }
});

Vue.component('news-list', {
    props: ['row'],
    template: '<li class="hot-news cf">\n' +
    '                                <span class="col-xs-4 col-md-4 col-lg-4 new-img">\n' +
    '                                    <a v-bind:href="row.url">\n' +
    '                                           <p>\n' +
    '                                               <img class="img-item center-block" v-bind:src="row.img">\n' +
    '                                           </p>\n' +
    '                                    </a>\n' +
    '                                </span>\n' +
    '                                <div class="col-xs-8 col-md-8 col-lg-8 new-list">\n' +
    '                                    <b>\n' +
    '                                        <a href="http://dota2.sgamer.com/news/201910/171344.html">{{row.title}}</a>\n' +
    '                                    </b>\n' +
    '                                    <p>{{row.title}}</p>\n' +
    '                                    <div class="row">\n' +
    '                                        <div class="col-xs-9 col-md-9 col-lg-9" style="float: right">\n' +
    '                                            <span class="col-xs-3 col-md-3 col-lg-3 new-time">{{row.time}}</span>\n' +
    '                                        </div>\n' +
    '                                    </div>\n' +
    '                                </div>\n' +
    '                            </li>'
});