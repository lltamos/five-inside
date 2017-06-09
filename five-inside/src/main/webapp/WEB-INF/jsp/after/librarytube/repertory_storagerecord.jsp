<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>

<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_12.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-table.min.css" />
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
						<span class="wid_2">商品ID</span>
						<span class="wid_3">商品名称</span>
						<span class="wid_4">商品单价</span>
						<span class="wid_5">商品数量</span>
						<span class="wid_6">申请时间</span>
						<span class="wid_7">当前状态</span>
					</li>
					<c:forEach items="${list.data}" var="d" varStatus="st">
						<li class="bor_gray">
							<span class="wid_1">${st.index + 1}</span>
							<span class="wid_2">${d.good_id }</span>
							<span class="wid_3"><pre style="padding:0;margin:0;font-family:Microsoft YaHei;background-color: transparent;border: 1px solid transparent;white-space: pre-wrap;">${d.goodname }</pre></span>
							<span class="wid_4">${d.good_money }</span>
							<span class="wid_5">${d.num }</span>
							<span class="wid_6">
								<fmt:formatDate value="${d.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
							</span>
							<span class="wid_7">
								<c:if test="${d.states == 0}">未审批</c:if>
								<c:if test="${d.states == 1}">已审批</c:if>
								<c:if test="${d.states == 2}">已撤销</c:if>
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
			var $s = $("input[name='start']");
			var $e = $("input[name='end']");
			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			if (start.trim() != "") {
				if (end.trim() == "") {
					alert("请选择结束时间");
					$s.val("");
					$e.val("");
					return false;
				}
			}
			if (end.trim() != "") {
				if (start.trim() == "") {
					alert("请选择开始时间");
					$s.val("");
					$e.val("");
					return false;
				}
			}
			var ckeck = $("#ckeckbox").val();
			var datas = {
				searchStr : "start=" + start + "&@&end=" + end,
				rows : 10,
				page : currPage
			};
			$("#lib-content").load(
					"${cxt }/admin/after/inventory/storagerecord", datas);
		};

		$("#begin_time").jeDate({
		    isinitVal:false,
		    festival:true,
		    ishmsVal:false,
		    minDate: '2016-06-16 23:59:59',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
		$("#end_time").jeDate({
		    isinitVal:false,
		    festival:true,
		    ishmsVal:false,
		    minDate: '2016-06-16 23:59:59',
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})

		$(document).ready(function() {
			getPage();

			$("#ckeckbox").val("${list.filter.ckeck}");

		});
	</script>


</body>
