<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>条码管理</title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_1.css"/>
    <script src="${ctx }/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/mypager.js"></script>
</head>
<script type="text/javascript">

/* 加载分页控件 */
$(function(){
	mypager.generPageHtml({
		pagerid:"zccustomeroutin-pager",
		//当前页码
		pno:"${currPage}",
		total:"${totalPage}",
		totalRecords : "${totalRecords}",
		click:function(currPage){
			$("#lib-content").load("${ctx }/admin/after/librarytube/good/toGoodPageList?page="+currPage+"&goodName=${param.goodName}&barCode="+escape("${param.barCode}"));
		}
	});
});


function queryInfo() {
	var goodName=$("#goodName").val().trim();
	var barCode=$("#barCode").val().trim();
	pageGo("${ctx }/admin/after/librarytube/good/toGoodPageList?goodName="+encodeURIComponent(goodName)+"&barCode="+escape(barCode));
	
}

function removeBarCode(goodId,bar_code){
	if(bar_code == null || bar_code == ''){
		alert("您还未绑定条形码");
		return false;
	}else{
		if(window.confirm("您确定要移除条码吗？")){
			$.ajax({
				url : "${ctx}/admin/after/librarytube/good/removeBarCode?goodId="+goodId,
				type : 'POST',
				success : function(result) {
					var appResult = eval("(" + result + ")");
					var message = appResult.msg;
					alert(message);
					$("#lib-content").load("${ctx }/admin/after/librarytube/good/toGoodPageList?page=${param.page}&goodName=${param.goodName}&barCode="+escape("${param.barCode}"));
				}
			})
		}
	}
}

function bindingBarCode(){
	var goodsids=$("#goodsids").val();
	var barCode=$("#good_bar_code").val();
	if(barCode==null || barCode==''){
		alert("条码不能为空");
		return;
	}
	/* if(!/^\d+$/.test(barCode)){
		alert("请输入13位纯数字的条码");
		return;
	}
	if(barCode.length != 13){
		alert("请输入13位纯数字的条码");
		return;
	} */
	$.ajax({
		url : "${ctx}/admin/after/librarytube/good/bindingBarCode?goodId="+goodsids+"&barCode="+barCode,
		type : 'POST',
		success : function(result) {
			var appResult = eval("(" + result + ")");
			var message = appResult.msg;
			alert(message);
			document.getElementById("dialog").style.display = "none";
			document.getElementById("test").style.display = "none";
			pageGo("${ctx }/admin/after/librarytube/good/toGoodPageList?goodName=${param.goodName}&barCode=${param.barCode}");
		}
	})
}
</script>
<body>
        <div class="message_right">
            <div class="proving">
                <div class="date">
                   	<ul>
                        <li><span style="margin-right:14px;">商品名称：</span><input id="goodName" value="${param.goodName }" type="text"/></li>
                        <li><span>商品条形码：</span><input id="barCode" value="${param.barCode }" type="text"/></li>
                        <li>
                            <div class="btn_1">
                                <a onclick="queryInfo()" class="btn_33" href="javascript:;">搜 索</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="form_1">
                    <ul>
                        <li class="bg_green">
                            <span class="wid_1">序号</span>
                            <span class="wid_2">商品名称</span>
                            <span class="wid_3">商品状态</span>
                            <span class="wid_4">供应商</span>
                            <span class="wid_5">商品单价</span>
                            <span class="wid_6">关键字</span>
                            <span class="wid_7">商品条形码</span>
                            <span class="wid_8">库存数量</span>
                            <span class="wid_9">操作</span>
                        </li>
                        <c:if test="${goodList.size() <= 0 }">
	                    	<%@include file="/common/nodata.jsp"%>
		                </c:if>
		                <c:if test="${goodList.size() > 0 }">
                        	<c:forEach items="${goodList }" var="goods" varStatus="status">
		                        <li class="bor_gray">
		                            <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                            <span class="wid_2">${goods.good_name }</span>
		                            <span class="wid_3">
		                            	<c:if test="${goods.states == 0}">未上架</c:if>
		                            	<c:if test="${goods.states == 1}">上架</c:if>
		                            	<c:if test="${goods.states == 2}">下架</c:if>
		                            	<c:if test="${goods.states == 3}">已删除</c:if>
		                            </span>
		                            <span class="wid_4">${goods.providertName }</span>
		                            <span class="wid_5"><fmt:formatNumber type="number" value="${goods.good_money }"  maxFractionDigits="2"/></span>
		                            <span class="wid_6">${goods.key_word }</span>
		                            <span class="wid_7">${goods.bar_code }</span>
		                            <span class="wid_8"><fmt:formatNumber type="number" value="${goods.num }"  maxFractionDigits="2"/></span>
		                            <span class="wid_9">
		                                <a href="javascript:;" onclick="open_1('${goods.id }','${goods.good_name }','${goods.num }')">绑定</a>
		                                <b>|</b>
		                                <a onclick="removeBarCode('${goods.id}','${goods.bar_code }')" href="javascript:;">清除条码</a>
		                            </span>
		                        </li>
                        	</c:forEach>
                        	</div>
                        	<div class="paging">
			                     <ul>
									<div class="page-location">
										<div id="zccustomeroutin-pager" class="ui-paging ui-paging1"></div>
									</div>
			                    </ul>
			                </div>
                        </c:if>
                    </ul>
                
                
            </div>
        </div>
    <div id="test"></div>
    <div id="dialog">
        <div class="title">
            <p>条码绑定</p>
            <a href="javascript:;" onclick="close_1()">x</a>
        </div>
        <div class="queren">
            <ul>
                <li>
                    <span>商品名称: </span><input id="goodnams" type="text" disabled="disabled"/>
                    <input type="hidden" id="goodsids"/>
                </li>
                <li>
                    <span>库存数量: </span><input id="inventoryNum" type="text" disabled="disabled"/>
                </li>
                <li>
                    <span>商品条码: </span><input id="good_bar_code" type="text"/>
                </li>
                <li class="btn_9">
                    <button class="btn_91" onclick="close_1()">关闭</button>
                    <button onclick="bindingBarCode()" class="btn_92">提交</button>
                </li>
            </ul>
        </div>
    </div>
    <script>
        function open_1(goodsids,goodName,inventoryNum){
        	
			$("#goodnams").val(goodName);
			if(inventoryNum == null || inventoryNum==''){
				$("#inventoryNum").val(0);
			}else{
				$("#inventoryNum").val(inventoryNum);
			}
			
			$("#goodsids").val(goodsids);
			
            var s = document.getElementById("test");
            s.style.display = "block";

            var l = document.getElementById("dialog");
            l.style.display = "block";
        }
        function close_1(){
            var s = document.getElementById("test");
            s.style.display = "none";

            var l = document.getElementById("dialog");
            l.style.display = "none";
        }
    </script>
</body>
