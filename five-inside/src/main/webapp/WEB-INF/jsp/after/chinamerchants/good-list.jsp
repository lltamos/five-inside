<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx }/css/base.css" />
<link rel="stylesheet" href="${ctx }/css/Since_10.css" />
<script src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/js/mypager.js"></script>
</head>
<script type="text/javascript">

/* 加载分页控件 */
$(function(){
	mypager.generPageHtml({
		pagerid:"provider-pager",
		//当前页码
		pno:"${currPage}",
		total:"${totalPage}",
		totalRecords : "${totalRecords}",
		click:function(currPage){
			$("#chinamerchants_index").load("${ctx }/admin/after/good/toGoodPageList?page="+currPage+"&goodName=${param.goodName}&barCode="+escape("${param.barCode}")+"&pagetype=1");
		}
	});
});

function queryInfo() {
	var goodName=$("#goodName").val().trim();
	pageGo("${ctx }/admin/after/good/toGoodPageList?goodName="+encodeURIComponent(goodName)+"&pagetype=1");
	
}


function modifyGood(g_id,g_states){
	$.ajax({
		url : "${ctx}/admin/after/good/modifGood",
		type : 'POST',
		data:{goodId:g_id,goodStates:g_states},
		success : function(result) {
			var appResult = eval("(" + result + ")");
			var message = appResult.msg;
			alert(message);
			pageGo('${ctx}/admin/after/good/toGoodPageList?pagetype=1&goodName=${param.goodName}');
		}
	})
}
</script>
<body>
	<div class="message_right">
		<div class="proving">
			<div class="date">
				<ul>
					<li>
						<span>商品名称: </span>
						<input value="${param.goodName }" id="goodName" type="text" />
					</li>
					<li>
						<div class="btn_1">
							<a class="btn_33" onclick="queryInfo()" href="javascript:;">搜 索</a>
						</div>
					</li>
				</ul>
			</div>
			<div class="form_1">
				<ul>
					<li class="bg_green">
						<span class="wid_1">序号</span>
						<span class="wid_2">时间</span>
						<span class="wid_3">商品名称</span>
						<span class="wid_4">商品价格</span>
						<span class="wid_5">商品条码</span>
						<span class="wid_6">商品分类</span>
						<span class="wid_7">商品状态</span>
						<span class="wid_8">操作</span>
					</li>
					<c:if test="${goodList.size() <= 0 }">
						<%@include file="/common/nodata.jsp"%>
					</c:if>
					<c:if test="${goodList.size() > 0 }">
						<c:forEach items="${goodList }" var="goods" varStatus="status">
							<li class="bor_gray">
								<span class="wid_1">${(currPage-1)*10+status.count}</span>
								<span class="wid_2">
									<fmt:formatDate value="${goods.created_time }" pattern="yyyy-MM-dd HH:mm:ss" />
								</span>
								<span class="wid_3">${goods.good_name }</span>
								<span class="wid_4">
									<fmt:formatNumber type="number" value="${goods.good_money }" maxFractionDigits="2" />
								</span>
								<span class="wid_5">${goods.bar_code }</span>
								<span class="wid_6">
									<c:if test="${goods.good_cate == 1}">推荐产品</c:if>
									<c:if test="${goods.good_cate == 2}">套餐产品</c:if>
									<c:if test="${goods.good_cate == 3}">家居产品</c:if>
									<c:if test="${goods.good_cate == 4}">食品产品</c:if>
									<c:if test="${goods.good_cate == 5}">美容产品</c:if>
									<c:if test="${goods.good_cate == 6}">其它产品</c:if>
									<c:if test="${goods.good_cate == 7}">公司直发</c:if>
									<c:if test="${goods.good_cate == 8}">厂家直邮</c:if>
									<c:if test="${goods.good_cate == 9}">上门自提</c:if>
									<c:if test="${goods.good_cate == 10}">套餐商品</c:if>
								</span>
								<span class="wid_7">
									<c:if test="${goods.states == 0}">未上架</c:if>
									<c:if test="${goods.states == 1}">上架</c:if>
									<c:if test="${goods.states == 2}">下架</c:if>
									<c:if test="${goods.states == 3}">删除</c:if>
								</span>
								<span class="wid_8">
									<a onclick="modifyGood(${goods.id},3)" href="javascript:;">删除</a>
									<b>|</b>
									<a onclick="pageGo('${ctx}/admin/after/good/toGoodEdit?goodId=${goods.id }')" href="javascript:;">修改</a>
									<b>|</b>
									<c:if test="${goods.states == 0}">
										<a onclick="modifyGood(${goods.id},1)" href="javascript:;">上架</a>
									</c:if>
									<c:if test="${goods.states == 1}">
										<a onclick="modifyGood(${goods.id},2)" href="javascript:;">下架</a>
									</c:if>
									<c:if test="${goods.states == 2}">
										<a onclick="modifyGood(${goods.id},1)" href="javascript:;">上架</a>
									</c:if>
								</span>
							</li>
						</c:forEach>
						<c:if test="${goodList==null||goodList.size()==0}">
							<li>
								<h2 style="margin-left: 460px">没有记录</h2>
							</li>
						</c:if>
			</div>
			<div class="paging">
				<ul>
					<div class="page-location">
						<div id="provider-pager" class="ui-paging ui-paging1"></div>
					</div>
				</ul>
			</div>
			</c:if>
			</ul>
		</div>
	</div>
</body>
