<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/Since_16.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
<link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css" />
<script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
</head>
<body>

	<div class="message_right">
		<div class="proving">
			<div class="date">
				<ul>
					<li class="datep">
						<span>开始日期: </span>
						<input value="${list.filter.start }" class="datainp wicon" type="text" name="start" id="begin_time" readonly>
					</li>
					<li class="datep">
						<span>结束日期: </span>
						<input value="${list.filter.end }" class="datainp wicon" type="text" name="end" id="end_time" readonly>
					</li>
					<li>
						<span>产品名称: </span>
						<input value="${list.filter.pname }" name="pname" type="text" placeholder="产品名称" />
					</li>
					<li>
						<div class="btn_1">
							<button class="btn_33 btn " onclick="fetchData()" id="bquery">搜 索</button>
							<button class="btn_33 btn " onclick="exportExcel()">报表导出</button>
						</div>
					</li>
				</ul>
			</div>
			<div class="form_1">
				<ul>
					<li class="bg_green">
						<span class="wid_1">序号</span>
						<span class="wid_2">供应商名称</span>
						<span class="wid_3">产品名称</span>
						<span class="wid_4">销量</span>
						<span class="wid_5">银行账号</span>
						<span class="wid_6">银行类型</span>
						<span class="wid_7">真实姓名</span>
						<span class="wid_8">付款金额(元)</span>
						<span class="wid_9">账户属性</span>
						<span class="wid_10">账户类型</span>
					</li>

					<c:forEach items="${list.data}" var="d" varStatus="status">
						<li class="bor_gray">
							<span class="wid_1">${status.count }</span>
							<span class="wid_2">${d.name }</span>
							<span class="wid_3"><pre style="padding:0;margin:0;font-family:Microsoft YaHei;background-color: transparent;border: 1px solid transparent;white-space: pre-wrap;">${d.good_name }</pre></span>
							<span class="wid_4">${d.salesNum }</span>
							<span class="wid_5">${d.account_num }</span>
							<span class="wid_6">${d.bank_name }</span>
							<span class="wid_7">${d.account_owner }</span>
							<span class="wid_8">${d.t }</span>
							<span class="wid_9">${d.account_attribute }</span>
							<span class="wid_10">${d.account_type }</span>
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

			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			var pname = $("input[name='pname']").val();
			var datas = {
				searchStr : "start=" + start + "&@&end=" + end + "&@&pname="
						+ pname,
				rows : 10,
				page : currPage
			};

			$("#financial_index").load(
					"${ctx}/admin/after/financial/findstatements", datas)

		};

		function exportExcel() {
			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			var pname = $("input[name='pname']").val();
			var data = "start=" + start + "&@&end=" + end + "&@&pname="
					+ pname;
			if (start.trim() != "") {
				if (end.trim() == "") {
					alert("请选择结束时间");
					return false;
				}
			}
			if (end.trim() != "") {
				if (start.trim() == "") {
					alert("请选择开始时间");
					return false;
				}
			}

			window.location.href = "${ctx}/admin/after/financial/exportExcel?searchStr="
					+ encodeURIComponent(data);/* escape(data); */
			
		}

		$(document).ready(function() {

			$("#begin_time").jeDate({
			    isinitVal:false,
			    festival:true,
			    ishmsVal:false,
			    maxDate: $.nowDate(0),
			    format:"YYYY-MM-DD",
			    zIndex:3000,
			})
			$("#end_time").jeDate({
			    isinitVal:false,
			    festival:true,
			    ishmsVal:false,
			    maxDate: $.nowDate(0),
			    format:"YYYY-MM-DD",
			    zIndex:3000,
			})
			getPage();
		});
	</script>


</body>
