<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/baodan_4.css"/>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
    <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li><span>订单号：</span><input type="text" name="order_num" id="order_num" value=${param.order_num } ></li>
                    <li>
                        <div class="btn_1">
                            <a class="btn_33" href="javascript:;" onclick="findAll();">搜 索</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1">序号</span>
                        <span class="wid_2">商品名称</span>
                        <span class="wid_3">商品数量</span>
                        <span class="wid_4">商品价格</span>
                        <span class="wid_5">剩余数量</span>
                        <span class="wid_6">操作</span>
                    </li>
                    <c:if test="${goodsReturnList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${goodsReturnList.size() > 0 }">
	                    <c:forEach items="${goodsReturnList}" var="goodsReturn" varStatus="status">
		                    <li class="bor_gray">
		                        <span class="wid_1">${status.count}</span>
		                        <span class="wid_2">${goodsReturn.good_name}</span>
		                        <span class="wid_3">${goodsReturn.good_num}</span>
		                        <span class="wid_4">${goodsReturn.good_money}</span>
		                        <span class="wid_5">${goodsReturn.remain_num}</span>
		                        <span class="wid_6">
		                        <c:if test="${goodsReturn.remain_num == 0 }">
									
								</c:if>
		                        <c:if test="${goodsReturn.remain_num != 0 }">
		                            <a href="javascript:;" style="color: #0c48d6;" 
		                            onclick="open_1('${goodsReturn.good_name}','${goodsReturn.order_num}','${goodsReturn.remain_num}',
		                            '${goodsReturn.userid}','${goodsReturn.id}');">退货</a>
								</c:if>
		                        </span>
		                    </li>
	                    </c:forEach>
	                    </div>
			            <%-- 分页demo --%>
						<div class="paging">
							<ul id="txmanager-pager"></ul>
						</div>
                    </c:if>
                </ul>
        </div>
    </div>
    <div id="test"></div>
    <div id="dialog">
        <div class="title">
            <p>商品退货</p>
            <a href="javascript:;" onclick="close_1()">x</a>
        </div>
        <div class="queren">
            <ul>
                <li>
                    <span>商品名称: </span><input type="text" id="good_name" disabled="disabled" />
                </li>
                <li>
                    <span>订单号: </span><input type="text" id="ordernum" disabled="disabled" />
                </li>
                <li>
                    <span>剩余数量: </span><input type="text" id="remain_num" disabled="disabled"/>
                </li>
                <li>
                    <span>退货数量: </span><input type="text" id="return_num"/>
                </li>
                <li>
                    <span>备注: </span><input type="text" id="remark"/>
                     <input type="hidden" id="userid"/>
                     <input type="hidden" id="order_goods_id"/>
                     <input type="hidden" name="token" id="token" value="${token}">
                </li>
                <li class="btn_9">
                    <button class="btn_91" onclick="close_1()">关闭</button>
                    <button class="btn_92" onclick="commit()"  >提交</button>
                </li>
            </ul>
        </div>
    </div>
    <script>
	/* 加载分页控件 */
	$(function(){
		mypager.generPageHtml({
			pagerid:"txmanager-pager",
			pno:"${currPage}",
			total:"${totalPage}",
			totalRecords : "${totalRecords}",
			click:function(currPage){
				$("#aftersales_index").load("${ctx }/admin/after/aftersales/salesreturn/findGoodsReturn?order_num=${param.order_num}&rows=10&page="+currPage);
			}
		});
	});
	function findAll(){
		var order_num = $("#order_num").val();
		pageGo("${cxt }/admin/after/aftersales/salesreturn/findGoodsReturn?order_num="+order_num);
	}
	function open_1(good_name,order_num,remain_num,userid,id){
	    var s = document.getElementById("test");
	    s.style.display = "block";
	    $("#good_name").val(good_name);
	    $("#ordernum").val(order_num);
	    $("#remain_num").val(remain_num);
	    $("#userid").val(userid);
	    $("#order_goods_id").val(id);
	    var l = document.getElementById("dialog");
	    l.style.display = "block";
	}
	function close_1(){
	    var s = document.getElementById("test");
	    s.style.display = "none";
	    $("#return_num").val("");
	    $("#remark").val("");
	    var l = document.getElementById("dialog");
	    l.style.display = "none";
	}
	function commit(){
		var userid = $("#userid").val();
		var return_num = $("#return_num").val();
		var remark = $("#remark").val();
		var order_goods_id = $("#order_goods_id").val();
		var remain_num = $("#remain_num").val();
		var token = $("#token").val();
		if( return_num > remain_num ){
			alert("退货数量不得大于剩余数量");
			return;
		}
		if( return_num == '' ){
			alert("退货数量不能为空");
			return;
		}
		if( remark == ''){
			alert("备注不能为空");
			return;
		}
		else{
			$.ajax({
				 type : "POST",
				 url : "${ctx }/admin/after/aftersales/salesreturn/goodsReturn",
				 data : {
						'userid' : userid,
						'return_num' : return_num,
						'remark' : remark,
						'order_goods_id' : order_goods_id,
						'token' : token,
					},
				 success : function(data) {
					var sc = eval('(' + data + ')')
					var message = sc.msg;
					var code = sc.code;
					alert(message);
					pageGo('${ctx}/admin/after/aftersales/salesreturn/findGoodsReturn?remark=1');
 				}
 			});
		}
	}
    </script>
</body>
</html>