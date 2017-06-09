<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/baodan_8.css"/>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
    <link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css"/>
    <script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
    
</head>
<body>
   <div class="message_right">
       <div class="proving">
           <div class="date">
               <ul>
                   <li><span style="margin-right:14px;">开始日期：</span><input class="datainp wicon" type="text" name="begin_time" id="begin_time" value="${param.begin_time }" /></li>
                   <li><span style="margin-right:14px;">结束日期：</span><input class="datainp wicon" type="text" name="end_time" id="end_time" value="${param.end_time }" /></li>
                   <li>
                       <div class="btn_1">
                           <a class="btn_33" onclick="findAll();" href="javascript:;">搜 索</a>
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
                   </li>
                   <c:if test="${applyRecordList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
                   </c:if>
                   <c:if test="${applyRecordList.size() > 0 }">
	                   <c:forEach items="${applyRecordList}" var="applyRecord" varStatus="status">
		                   <li class="bor_gray">
		                       <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                       <span class="wid_2">${applyRecord.good_id}</span>
		                       <span class="wid_3">${applyRecord.goodname}</span>
		                       <span class="wid_4">${applyRecord.good_money}</span>
		                       <span class="wid_5">${applyRecord.num}</span>
		                       <span class="wid_6">
		                       <c:if test="${applyRecord.handle_type == 1}">
									<i>厂家处理</i> 
							   </c:if>
		                       <c:if test="${applyRecord.handle_type == 2}">
									<i>公司支付</i> 
							   </c:if>
		                       </span>
		                       <span class="wid_7">${applyRecord.remark}</span>
		                       <span class="wid_8"><fmt:formatDate value="${applyRecord.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
		                       <span class="wid_9">
		                       	<c:if test="${applyRecord.states == 0 }">
									<i>未审批</i> 
								</c:if>
				                     	<c:if test="${applyRecord.states == 1 }">
									<i>已审批</i> 
								</c:if>
				                     	<c:if test="${applyRecord.states == 2 }">
									<i>已撤销</i> 
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
	    /* 加载分页控件 */
	    $(function(){
	    	var begin_time = $("#begin_time").val();
		   	var end_time = $("#end_time").val();
	    	mypager.generPageHtml({
	    		pagerid:"txmanager-pager",
	    		//当前页码
	    		pno:"${currPage}",
	    		total:"${totalPage}",
	    		totalRecords : "${totalRecords}",
	    		click:function(currPage){
	    			$("#lib-content").load("${ctx }/admin/after/libraryTube/stocktakeapply/findAllApplyRecord?begin_time="+escape(begin_time)+"&end_time="+escape(end_time)+"&rows=10&page="+currPage);
	    		}
	    	});
	    });
		   function findAll(){
			   var begin_time = $("#begin_time").val();
			   var end_time = $("#end_time").val();
			   pageGo("${ctx }/admin/after/libraryTube/stocktakeapply/findAllApplyRecord?begin_time="+escape(begin_time)+"&end_time="+escape(end_time));
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