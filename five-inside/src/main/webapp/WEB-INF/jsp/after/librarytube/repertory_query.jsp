<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_14.css" />
<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
<style>
		 input::-webkit-outer-spin-button,
    	input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    input[type="number"]{
        -moz-appearance: textfield;
    }
</style>
</head>
<body>
	<div class="message_right">
		<div class="proving">
			<div class="date">
				<ul>
					<li>
						<span style="margin-right: 14px;">商品名称：</span>
						<input name="pname" value="${list.filter.pname}" type="text" />
					</li>

					<li>
						<span style="margin-right: 14px;">商品条码：</span>
						<input name="pcode" value="${list.filter.pcode}" type="text" />
					</li>
					<li>
						<span style="margin-right: 14px;">库存数量：</span>
						<input class="no_arrow" name="pnum" value="${list.filter.pnum}" type="number" />
					</li>
					<li>
						<span style="margin-right: 14px;">供应厂商：</span>
						<input name="pprovider" value="${list.filter.pprovider}" type="text" />
					</li>

					<li>

						<button class="btn_33 btn " onclick="fetchData()" id="bquery">搜 索</button>

					</li>
				</ul>
			</div>
			<div class="form_1">
				<ul>
					<li class="bg_green">
						<span class="wid_1">序号</span>
						<span class="wid_2">商品名称</span>
						<span class="wid_3">商品条码</span>
						<span class="wid_4">供应商</span>
						<span class="wid_5">关键字</span>
						<span class="wid_6">库存数</span>
						<span class="wid_7">盘余数量</span>
					</li>
					<c:forEach items="${list.data}" var="d" varStatus="st">
						<li class="bor_gray">
							<span class="wid_1">${st.index + 1}</span>
							<span class="wid_2"><pre style="padding:0;margin:0;font-family:Microsoft YaHei;background-color: transparent;border: 1px solid transparent;white-space: pre-wrap;">${d.good_name }</pre></span>
							<span class="wid_3">${d.bar_code}</span>
							<span class="wid_4"><pre style="padding:0;margin:0;font-family:Microsoft YaHei;background-color: transparent;border: 1px solid transparent;white-space: pre-wrap;">${ d.address}</pre></span>
							<span class="wid_5">${d.key_word }</span>
							<span class="wid_6">${d.num }</span>
							<span class="wid_7">${d.more_num }</span>
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
			var pnum = $("input[name='pnum']").val();
			var pprovider = $("input[name='pprovider']").val();
			var datas = {
				searchStr : "pname=" + pname + "&@&pcode=" + pcode + "&@&pnum="
						+ pnum + "&@&pprovider=" + pprovider,
				rows : 10,
				page : currPage
			};

			$("#lib-content").load(
					"${cxt }/admin/after/inventory/inventoryquery", datas)

		};

		$(document).ready(function() {
			getPage();
		});
	</script>


</body>
