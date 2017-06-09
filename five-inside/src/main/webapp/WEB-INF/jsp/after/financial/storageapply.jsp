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
						<span>供应商: </span>
						<input value="${list.filter.pprovider }" name="pprovider" type="text" placeholder="供应商" />
					</li>
					<li>
						<div class="btn_1">
							<button class="btn_33 btn " onclick="fetchData()" id="bquery">搜 索</button>
							<button class="btn_33 btn " onclick="exportExcel()">导出</button>
						</div>
					</li>
				</ul>
			</div>
			<div class="form_1">
				<ul>
					<li class="bg_green">
						<span class="wid_1">编号</span>
						<span class="wid_2">商品ID</span>
						<span class="wid_3">供应商</span>
						<span class="wid_4">商品名称</span>
						<span class="wid_5">商品单价</span>
						<span class="wid_6">商品数量</span>
						<span class="wid_7">申请时间</span>
						<span class="wid_8">当前状态</span>
						<span class="wid_9">操作</span>
					</li>

					<c:forEach items="${list.data}" var="d" varStatus="st">
						<li class="bor_gray">
							<span class="wid_1">${st.index + 1}</span>
							<span class="wid_2">${d.good_id }</span>
							<span class="wid_3">
								<pre style="padding: 0; margin: 0; font-family: Microsoft YaHei; background-color: transparent; border: 1px solid transparent; white-space: pre-wrap;">${d.NAME }</pre>
							</span>
							<span class="wid_4">${d.goodname }</span>
							<span class="wid_5">${d.good_money }</span>
							<span class="wid_6">${d.num }</span>
							<span class="wid_7">
								<fmt:formatDate value="${d.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
							</span>


							<span class="wid_8" id="items${d.id}">
								<c:if test="${d.states == 2}">已撤销</c:if>
								<c:if test="${d.states == 1}">已审批</c:if>
								<c:if test="${d.states == 0}">未审批</c:if>
							</span>
							<span class="wid_9" id="span${d.id }">
								<c:if test="${d.states == 0}">
									<a style="cursor: pointer" onclick="allow(${d.id})">批准</a>
									<b>|</b>
									<a style="cursor: pointer" onclick="repeal(${d.id})">撤销</a>
								</c:if>
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
	</div>

	<script type="text/javascript">
		
		
		function repeal(id){
			
			$.post("${ctx}/admin/after/storageapply/repeal?sid="+id, function(data) {
				
				var mresult=JSON.parse(data);
				
				if(mresult.code==1){
					var $p=$("#items"+id);
					$p.text("已撤销");
					var $s=$("#span"+id);
					$s.css("display", "none");  
					alert(mresult.msg);
				}else{
					alert(mresult.msg);
				}
			});
		}
		function allow(id){

			$.post("${ctx}/admin/after/storageapply/allow?sid="+id, function(data) {
				var mresult=JSON.parse(data);
				if(mresult.code==1){
					var $p=$("#items"+id);
					$p.text("已审批");					
					var $s=$("#span"+id);
					$s.css("display", "none");  
					alert(mresult.msg);
				}else{
					alert(mresult.msg);
				}
			});

		}

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
			var pprovider = $("input[name='pprovider']").val();
			var datas = {
				searchStr : "start=" + start + "&@&end=" + end+ "&@&pprovider=" + pprovider,
				rows : 10,
				page : currPage
			};

			$("#financial_index").load("${ctx}/admin/after/storageapply/findStorageApplys", datas)

		};
		
		
		function exportExcel(){
				var start = $("input[name='start']").val();
				var end = $("input[name='end']").val();
				var pprovider = $("input[name='pprovider']").val();
				var data = "start=" + start + "&@&end=" + end+ "&@&pprovider=" + pprovider;	

				window.location.href="${ctx}/admin/after/storageapply/exportExcel?searchStr="+ encodeURIComponent(data);/* escape(data); */
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
