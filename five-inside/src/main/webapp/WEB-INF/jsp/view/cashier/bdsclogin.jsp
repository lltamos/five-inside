<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
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
            function login(){
            	var mobile = $("input[id=mobile]").val();
            	var newvalid = $("input[id=newvalid]").val();
            	var type = $("input[id=type]").val();
    			if (mobile == null || mobile == "undefined") {
    				alert("手机号不能为空!");
    				return false;
    			}
    			var checkMobile = "^1(3[0-9]|4[57]|5[0-35-9]|7[013678]|8[0-9])\\d{8}$";
    			if (!mobile.match(checkMobile)) {
    				alert("请输入正确的手机号码!");
    				return false;
    			}
    			
    			if (newvalid == null || newvalid == "undefined") {
    				alert("密码不能为空!");
    				return false;
    			}
    			
    			if (type != 1) {
    				alert("操作有误!");
    				return false;
    			}
    			
    			$.ajax({
    				type:"GET",
    				url :"${ctx}/front/cashier/cashierlogin",
    				data:{
    					"mobile" : mobile,
    					"newvalid" : newvalid,
    					"type" : type
    				},
    				success:function(result) {
    					var sc = eval('(' + result + ')')
    					if(sc.code==0){
    						window.location.href = "${ctx }/front/cashier/to-productlist";
    					}else{
    						alert(sc.msg);
    					}
    				}

    			});
            }
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
            <div class="proving2">
                <div class="zhanghao">
                    <div class="zhanghao_1">
                        <div>消费者帐号登录:</div>
                        <input id="mobile" type="text" value="">
                    </div>
                    <div class="zhanghao_1">
                        <div>消费者密码:</div>
                        <input id="newvalid" type="password" value="">
                    </div>
                    <input id="type" type="hidden" value="1">
                    <button class="zhanghao_2" onclick="login();">登录</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>