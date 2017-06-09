<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/baodan_5.css"/>
    <script src="${ctx }/js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
            $(function() {
                var Accordion = function(el, multiple) {
                    this.el = el || {};
                    this.multiple = multiple || false;

                    // Variables privadas
                    var links = this.el.find('.link');
                    // Evento
                    links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
                }

                Accordion.prototype.dropdown = function(e) {
                    var $el = e.data.el;
                    $this = $(this),
                            $next = $this.next();

                    $next.slideToggle(120);
                    $this.parent().toggleClass('open');

                    if (!e.data.multiple) {
                        $el.find('.submenu').not($next).slideUp(120).parent().removeClass('open');
                    };
                }

                var accordion = new Accordion($('#accordion'), false);
            });
        </script>
</head>
<body>
    <div class="nav">
        <div class="logo">
            <img src="${ctx }/img/Logo.png" alt=""/>
        </div>
    </div>
    <div class="among">
        <div class="message_left">
            <ul id="accordion" class="accordion">
                <li>
                    <div class="link">商品购买<i class="fa fa-chevron-down">∨</i></div>
                    <ul class="submenu">
                        <li><a href="${ctx }/front/cashier/to-xytlogin">新业态</a></li>
                        <li><a href="${ctx }/front/cashier/to-bdsclogin">本地电商</a></li>
                    </ul>
                </li>
                <li>
                    <a class="link" href="${ctx }/route/to-relogin">
                        重新登录
                    </a>
                    <ul class="submenu"></ul>
                </li>
            </ul>
        </div>
        <div class="message_right">
            <div class="proving">
                <div class="zhanghao">
                    <div class="zhanghao_1">
                        <div>消费者帐号登录:</div>
                        <input type="text">
                    </div>
                    <div class="zhanghao_1">
                        <div>消费者密码:</div>
                        <input type="text">
                    </div>
                    <button class="zhanghao_2">登录</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>