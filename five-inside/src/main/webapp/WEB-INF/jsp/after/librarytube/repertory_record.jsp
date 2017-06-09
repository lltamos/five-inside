<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_13.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
	<div class="message_right">
		<div class="proving">
			<div class="date">
				<ul>
					<li>
						<span style="margin-right: 14px;">商品名称：</span>
						<input type="text" value="${list.filter.pname}" name="pname" />
					</li>

					<li>
						<span style="margin-right: 14px;">商品条码：</span>
						<input type="text" value="${list.filter.pcode}" name="pcode" />
					</li>

					<li>
						<span style="margin-right: 14px;">操作类型：</span>
						<select id="ckeckbox" style="height: 28px;">
							<option value="" selected="selected">全部</option>
							<option value="0">商品入库</option>
							<option value="1">扫码购买</option>
						</select>
					</li>

					<li>
						<div class="btn_1">
							<button class="btn_33 btn " onclick="fetchData()" id="bquery">搜 索</button>
						</div>
					</li>
				</ul>
			</div>
			<div class="form_1">
				<ul>
					<li class="bg_green">
						<span class="wid_1">编号</span>
						<span class="wid_2">商品名称</span>
						<span class="wid_3">商品条码</span>
						<span class="wid_4">操作类型</span>
						<span class="wid_5">操作人</span>
						<span class="wid_6">订单号</span>
						<span class="wid_7">操作数量</span>
						<span class="wid_7">库存量</span>
						<span class="wid_7">购买类型</span>
						<span class="wid_7">操作时间</span>
					</li>

					<c:forEach items="${list.data}" var="d" varStatus="st">
						<li class="bor_gray">
							<span class="wid_1">${st.index+1 }</span>
							<span class="wid_2">
								<pre style="padding: 0; margin: 0; font-family: Microsoft YaHei; background-color: transparent; border: 1px solid transparent; white-space: pre-wrap;">${d.good_name }</pre>
							</span>
							<span class="wid_3">${d.bar_code }</span>
							<span class="wid_4">
								<c:if test="${d.operation_type == 0}">商品入库</c:if>
								<c:if test="${d.operation_type == 1}">扫码购买</c:if>
								<c:if test="${d.operation_type == 2}">扫码购买</c:if>
							</span>
							<span class="wid_5">${d.name }</span>
							<span class="wid_6">${d.order_no }</span>
							<span class="wid_7"> ${d.num } </span>
							<span class="wid_7">${d.current_inventory_num }</span>
							<span class="wid_7">
								<c:if test="${d.shop_type == 0}">新业态</c:if>
								<c:if test="${d.shop_type == 1}">本地电商</c:if>
							</span>
							<span class="wid_7">
								<fmt:formatDate value="${d.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
							</span>
						</li>
					</c:forEach>
					<c:if test="${list.data==null||list.data.size()==0}">
						<li>
							<h2 style="margin-left: 460px">没有记录</h2>
						</li>
					</c:if>
				</ul>
			</div>
			<%-- 分页demo --%>
			<div class="paging">
				<ul id="txmanager-pager"></ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getPage() {
			mypager.generPageHtml({
				pagerid : "txmanager-pager",
				pno : "${list.page}",
				total : "${list.totalPage}",
				totalRecords : "${list.totalRecords}",
				click : fetchData
			});
		}

		fetchData = function(currPage) {

			var pname = $("input[name='pname']").val();
			var pcode = $("input[name='pcode']").val();

			var ckeck = $("#ckeckbox").val();
			var datas = {
				searchStr : "pname=" + pname + "&@&pcode=" + pcode
						+ "&@&ckeck=" + ckeck,
				rows : 10,
				page : currPage
			};

			$("#lib-content").load(
					"${cxt }/admin/after/inventory/inventoryrecord", datas);
		};

		$(document).ready(function() {
			getPage();
			$("#ckeckbox").val("${list.filter.ckeck}");

		});
	</script>


</body>
