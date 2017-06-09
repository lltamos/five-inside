<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/baodan_6.css"/>
    <script src="${ctx }/js/jquery-1.7.2.js"></script>
    <style>
    	  .poll_51{
            position: relative;
            width: 68px;
        }
        .tn_3{
            width: 40px;
		    height: 25px;
		    background-color: #fff;
		    border: 1px solid #fff;
		    padding-left: 6px;
        }
        .tn_1{
            position: absolute;
			right: 13px;
			bottom: 1px;
			width: 16px;
			height: 13px;
        }
        .tn_2{
            position: absolute;
			right: 13px;
			top: 1px;
			width: 16px;
			height: 14px;
        }
    </style>
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
            
            
            function out() {
            	var type = $("input[id=type]").val();
            	if(type==0){
            		window.location.href = "${ctx }/front/cashier/to-xytlogin";
            	}
				if(type==1){
					window.location.href = "${ctx }/front/cashier/to-bdsclogin";
            	}
			};
           
            function toinvoice(){
            	var customerMoney= parseFloat($("#customermoney").text());
            	var totalPrice = parseFloat($("#totalprice").text());
            	if(customerMoney>=totalPrice){
            		$.ajax({
	    				type:"POST",
	    				url :"${ctx}/front/pay/to-invoice",
	    				data:{
	    					"totalPrice" : totalPrice
	    				},
	    				success:function(result) {
	    					var sc = eval('(' + result + ')');
	    					if(sc.code == 0){
		    					 alert(sc.msg);
		    				}
	    					if(sc.code == 1){
	    						var totalPrice = sc.content.totalPrice;
	    						var orderNo = sc.content.orderNo;
	    						
	    						window.location.href = "${ctx }/front/pay/to-print-invoice?orderno="+orderNo+"&totalprice="+totalPrice;
	    					}
	    				}
	    			});
            	}else{
            		alert("亲,您的余额已不足!")
            	}
            }
            
            /* $(function(){  
                $('#barcode').bind('keypress',function(event){
                	var barcode = $(this).val();
                    if(event.keyCode == "13"){
                    	alert(barcode);
                    	$.ajax({
		    				type:"POST",
		    				url :"${ctx}/front/customer/addshoppingcart",
		    				data:{
		    					"barcode" : barcode
		    				},
		    				success:function(result) {
		    					var sc = eval('(' + result + ')');
		    					if(sc.code == 0){
			    					 alert(sc.msg);
			    				}
		    					if(sc.code == 1){
		    						alert(sc.content);
		    					}
		    					if(sc.code == 2){
		    						$("#"+barcode).val(sc.content);
		    						alert(sc.content);
		    					}
		    				}
		    			});
                    }  
                });  
            });   */
        </script>
</head>
<body>
	<input id="type" type="hidden" value="${sessionScope.type}">
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
               <div class="zhanghaod">
                   <span class="zhanghaod_1">
                       <i>帐号：</i><b>${cashier.mobile }</b>
                   </span>
                   <span  class="zhanghaod_1">
                       <i>姓名：</i><b>${cashier.name }</b>
                   </span>
                   <span  class="zhanghaod_1">
                       <i>商城余额：</i><b id="customermoney" >${balance.data }</b>
                   </span>
                   <button class="zhanghaod_2" onclick="out();">退出</button>
               </div>
                <div class="date">
                    <ul>
                        <li><span>商品条码：</span><input id="barcode" type="text" value="" maxlength="13"  onkeyup='this.value=this.value.replace(/\D/gi,"")'/></li>
                    </ul>
                    <script>
                    $('#barcode').keydown(function(e){
                    	var barcode = $(this).val();
                    	if(e.keyCode==13){
                    		if(barcode == null || barcode == "undefined"){
                        		alert("条形码13位数字!");
                        		return false;
                        	}
                        	$.ajax({
        	    				type:"POST",
        	    				url :"${ctx}/front/customer/addshoppingcart",
        	    				data:{
        	    					"barcode" : barcode
        	    				},
        	    				success:function(result) {
        	    					var sc = eval('(' + result + ')');
        	    					if(sc.code == 0){
        		    					 alert(sc.msg);
        		    				}
        	    					if(sc.code == 1){
        	    						/* window.location.reload();  */
        	    						window.location.href = "${ctx }/front/cashier/to-productlist";
        	    					}
        	    					if(sc.code == 2){
        	    						$("#"+barcode).val(sc.content);
        	    						redl();
        	    					}
        	    				}
        	    			});
                    	}
                    	});
                    </script>
                </div>
                <div class="form_1">
                    <ul>
                        <li class="bg_green">
                            <span class="wid_2">商品名称</span>
                            <span class="wid_3">数量</span>
                            <span class="wid_4">单价</span>
                            <span class="wid_5">购买方式</span>
                            <span class="wid_6">操作</span>
                        </li>
                        <c:forEach items="${cartList }" var="item">
                        <li class="bor_gray">
                        	<input id="inventoryid" type="hidden" value="${item.id}" />
                            <span class="wid_2">${item.goods.goodName }</span>
                            <span class="wid_3">
		                       <div class="poll_51">
		                            <img class="tn_1" src="${ctx }/img/xia.png">
		                            <input class="tn_3" type="text" id="${item.goods.barCode }" value="${item.good_num }" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
		                            <img class="tn_2" src="${ctx }/img/shang.png">
		                        </div>
		                        <div class="poll_52" style="display: none;">${item.inventory.num+item.inventory.moreNum  }</div>
                            </span>
                            <span class="wid_4">${item.goods.goodMoney }</span>
                            <span class="wid_5">
                            <c:if test="${sessionScope.type==0}">新业态</c:if>
                             <c:if test="${sessionScope.type==1}">本地商城</c:if>
                            </span>
                            <span class="wid_6" id="${item.id}">
                                <a href="javascript:;"  style="color:#0c48d6;">删&nbsp除</a>
                            </span>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- <div class="paging">
                    <ul>
                        <li><a class="btn_col22" href="javascript:;">首页</a></li>
                        <li><a class="btn_col22" href="javascript:;">上一页</a></li>
                        <li><a class="btn_col33" href="javascript:;">1</a></li>
                        <li><a class="btn_col22" href="javascript:;">下一页</a></li>
                        <li><a class="btn_col22" href="javascript:;">末页</a></li>
                        <li><a class="btn_col22" href="javascript:;">共<span>10</span>条数据</a></li>
                    </ul>
                </div> -->
                
               <div class="zhanghaod">
                   <span class="zhanghaod_1">
                       <i style="font-size:20px;">总金额：</i><b id="totalprice" style="font-size:20px;"></b>
                   </span>
                   <button class="zhanghaod_2" onclick="toinvoice()">立即支付</button>
               </div>
            </div>
        </div>
    </div>
     <script>

						$('.tn_1').on(
								'click',
								function() {
									if (parseInt($(this).siblings('.tn_3')
											.val()) == 1) {
										alert("亲，不能在减了");
										return false;
									}
									var id = $(this).parent('.poll_51').closest('.wid_3').siblings('#inventoryid').val();
									var num =parseInt($(this).siblings('.tn_3').val()) - 1;
									var aa = $(this).siblings('.tn_3')
									update(id,num,aa)

								});

						$('.tn_2').on(
								'click',
								function() {
									var kcsl = $(this).parents('.poll_51').siblings('.poll_52').html();
									if (parseInt($(this).siblings('.tn_3')
											.val()) >=kcsl) {
										alert("亲，超出库存啦!");
										$(this).siblings('.tn_3').val(kcsl);
										return false;
									}
									var id = $(this).parent('.poll_51').closest('.wid_3').siblings('#inventoryid').val();
									var num = parseInt($(this).siblings('.tn_3').val()) + 1
									var aa = $(this).siblings('.tn_3')
									update(id,num,aa);
									/* aa.val( num );
									redl(); */
								});
						$(".tn_3").on('keyup', function() {
							var kcsl = $(this).parents('.poll_51').siblings('.poll_52').html();
							var cul = parseInt($(this).val());
							if ( cul >= 1) {
								var id = $(this).parent('.poll_51').closest('.wid_3').siblings('#inventoryid').val();
								var aa = $(this);
								if (parseInt($(this).val()) > kcsl) {
									alert("亲，超出库存啦!");
									aa.val( kcsl );
									update(id,kcsl,aa);
									return false;
								}else{
									update(id,cul,aa);
								}
								return false;
							} else {
								$(this).val('1');
								alert('亲 ,请输入大于"0"的数字!');
							}
							redl();
						});
						$('.wid_6').live('click',function(){
							var id = $(this).siblings('#inventoryid').val();
							var aa = $(this).parents('.bor_gray')
							del(id,aa);
			            });
						
						$(document).click(function() {
							$('.tn_3').each(function() {
								if ($(this).val() == "") {
									alert('亲 不能为空');
									$(this).val('1');
								}
								redl();
							});
						});

						function redl() {
							var s = 0;
							$('.bor_gray').each(
									function() {

										s += parseInt($(this)
												.children('.wid_3').children(
														'.poll_51').find(
														'.tn_3').val())
												* parseFloat($(this).find(
														'.wid_4').html());
									});
							$("#totalprice").html(s.toFixed(2));
						};
							
						$(function() {
							redl();
						});
						
						function del(id,aa) {
			            	if (id == null || id == "undefined") {
			    				alert("删除有误!");
			    				return false;
			    			};
			            	$.ajax({
			    				type:"POST",
			    				url :"${ctx}/front/customer/delshoppingcart",
			    				data:{
			    					"id" : id
			    				},
			    				success:function(result) {
			    					var sc = eval('(' + result + ')');
			    					if(sc.code == 1){
			    						aa.remove();
						                redl(); 
			    					}else{
			    						alert(sc.msg);
			    					}
			    				}

			    			});
						}
						
						
						function update(id,num,aa) {
			            	if (id == null || id == "undefined") {
			    				alert("修改数量有误!");
			    				return false;
			    			};
			    			if (num == null || num <= 0 || num == "undefined") {
			    				alert("修改数量有误!");
			    				return false;
			    			};
			    			
			            	$.ajax({
			    				type:"POST",
			    				url :"${ctx}/front/customer/updshoppingcart",
			    				data:{
			    					"id" : id,
			    					"num" : num
			    				},
			    				success:function(result) {
			    					var sc = eval('(' + result + ')');
			    					if(sc.code == 1){
			    						aa.val(num);
			    						redl();
			    					}else{
			    						alert(sc.msg);
			    					}
			    				}

			    			});
						}
	</script>
</body>
</html>