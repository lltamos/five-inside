<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/css/base.css"/>
    <link rel="stylesheet" href="${ctx}/css/Since_17.css"/>
	<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
	<link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css"/>
    <script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
</head>
<body>
    <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li class="datep"><span>开始日期: </span><input class="datainp wicon" type="text" name="begin_time" id="begin_time"  value="${param.begin_time }" /></li>
                    <li class="datep"><span>结束日期: </span><input class="datainp wicon" type="text" name="end_time" id="end_time"  value="${param.end_time }" /></li>
                    <li>
                        <div class="btn_1">
                            <a href="javascript:;" class="btn_33" onclick="findAll();">搜 索</a>
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
                        <span class="wid_6">处理方式</span>
                        <span class="wid_7">备注</span>
                        <span class="wid_8">申请时间</span>
                        <span class="wid_9">当前状态</span>
                        <span class="wid_9">操作</span>
                    </li>
                    <c:if test="${stockTakeApplyList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${stockTakeApplyList.size() > 0 }">
	                    <c:forEach items="${stockTakeApplyList}" var="stockTakeApply" varStatus="status">
		                    <li class="bor_gray">
		                        <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                        <span class="wid_2">${stockTakeApply.good_id }</span>
		                        <span class="wid_3">${stockTakeApply.goodname } </span>
		                        <span class="wid_4">${stockTakeApply.good_money }</span>
		                        <span class="wid_5">${stockTakeApply.num }</span>
		                         <c:if test="${applyRecord.handle_type == 1}">
		                         <span class="wid_6"> 厂家处理</span>
							   </c:if>
		                       <c:if test="${applyRecord.handle_type == 2}">
		                       <span class="wid_6"> 公司支付</span>
							   </c:if>
		                        <span class="wid_6"> ${stockTakeApply.handle_type }</span>
		                        <span class="wid_7">${stockTakeApply.remark }</span>
		                        <span class="wid_8"><fmt:formatDate value="${stockTakeApply.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
		                        <span class="wid_9">
		                        	<c:if test="${stockTakeApply.states == 0 }">
										<i>未审批</i> 
									</c:if>
		                        	<c:if test="${stockTakeApply.states == 1 }">
										<i>已审批</i> 
									</c:if>
		                        	<c:if test="${stockTakeApply.states == 2 }">
										<i>已撤销</i> 
									</c:if>
		                        </span>
		                        <span class="wid_10">
		                        	<c:if test="${ stockTakeApply.states == 0 }">
		                        		<input type="hidden" name="token" id="token" value="${token}">
										<a href="javascript:;" onclick="change('${stockTakeApply.id }',1);">批准</a>
			                            <b>|</b>
			                            <a href="javascript:;" onclick="change(${stockTakeApply.id },2);">撤销</a>
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
	<script type="text/javascript">
		$(function(){
			getPage();
		});
		function getPage(){
			var begin_time = $("#begin_time").val();
		   	var end_time = $("#end_time").val();
			mypager.generPageHtml({
				pagerid:"txmanager-pager",
				pno:"${currPage}",
				total:"${totalPage}",
				totalRecords:"${totalRecords}",
				click:function(currPage){
					$("#financial_index").load("${cxt }/admin/after/financial/stockapproval/findAll?begin_time="+escape(begin_time)+"&end_time="+escape(end_time)+"&rows=10&page="+currPage);
				}
			});
		}
		   function findAll(){
		   	var begin_time = $("#begin_time").val();
		   	var end_time = $("#end_time").val();
		   	pageGo("${cxt }/admin/after/financial/stockapproval/findAll?begin_time="+escape(begin_time)+"&end_time="+escape(end_time));
		   }
		   function change(id,States){
			var token = $("#token").val();
		   	$.ajax({
				 type : "POST",
				 url : "${ctx }/admin/after/financial/stockapproval/updateStates",
				 data : {
						"id" : id,
						"States":States,
						'token' : token,
				 	},
				 success : function(data) {
					var sc = eval('(' + data + ')')
					var message = sc.msg;
					var code = sc.code;
					alert(message);
					pageGo("${ctx }/admin/after/financial/stockapproval/findAll");
				 }
			 });
		   }
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

	</script>
</body>
</html>

