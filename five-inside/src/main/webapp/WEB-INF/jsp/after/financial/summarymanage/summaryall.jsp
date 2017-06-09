<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_14.css"/>
     <link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css"/>
    <script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
    <style type="text/css">
    h2{
    	margin-left: 460px;
    	margin-top:160px;
    	font-size: 25px;
    }
    </style>
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
				$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/findAll?begin_time="+begin_time+"&end_time="+end_time+"&rows=10&page="+currPage);
			}
		});
	}
	
	function findAll(){
	   	var begin_time = $("#begin_time").val();
	   	var end_time = $("#end_time").val();
	   	if(null == begin_time || null ==end_time){
	   		alert("请输入起始时间!");
	   	}
	   	$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/findAll?begin_time="+begin_time+"&end_time="+end_time);
	   }
	$("#begin_time").jeDate({
	    isinitVal:false,
	    festival:true,
	    ishmsVal:false,
	    minDate: '2000-01-01 23:59:59',
	    maxDate: $.nowDate(0),
	    format:"YYYY.MM.DD",
	    zIndex:3000,
	})
	$("#end_time").jeDate({
	    isinitVal:false,
	    festival:true,
	    ishmsVal:false,
	    minDate: '2000-01-01 23:59:59',
	    maxDate: $.nowDate(0),
	    format:"YYYY.MM.DD",
	    zIndex:3000,
	})
		
		
		function del(id) {
    		if(null ==id || id=="undefined"){
    			alert("删除有误!");
    		}
    		$.ajax({
				type:"POST",
				url :"${ctx }/admin/after/financial/summarymanage/del",
				data:{
					"id" : id
				},
				success:function(result) {
					var sc = eval('(' + result + ')');
					if(sc.code == 0){
    					 alert(sc.msg);
    				}
					if(sc.code == 1){
						$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/findAll"); 
					}
					
				}
			});
		}
		
		function initialize(id) {
    		if(null ==id || id=="undefined"){
    			alert("初始化有误!");
    		}
    		$.ajax({
				type:"POST",
				url :"${ctx }/admin/after/financial/summarymanage/initialize",
				data:{
					"id" : id
				},
				success:function(result) {
					var sc = eval('(' + result + ')');
					if(sc.code == 0){
    					 alert(sc.msg);
    				}
					if(sc.code == 1){
						$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/findAll"); 
					}
				}
			});
		}
		function checked(id) {
			$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/checked?id="+id); 
		}
		
		function exportsummary(id) {
			window.location.href="${ctx}/admin/after/financial/summarymanage/export?id="+id;
		}
		function sendemail(id){
			var token = $("input[name='token']").val();
			$.ajax({
				type:"POST",
				url :"${ctx }/admin/after/financial/summarymanage/sendemail",
				data:{
					"id" : id,
					"token" : token
				},
				success:function(result) {
					var sc = eval('(' + result + ')');
					alert(sc.msg);
				}
			});
		}
    </script>
</head>
<body>
 <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li class="datep"><span>开始日期: </span><input class="datainp wicon" type="text" id="begin_time" readonly="readonly" <c:if test="${begin_time==null }">value=""</c:if>></li>
                    <li class="datep"><span>结束日期: </span><input class="datainp wicon" type="text" id="end_time" readonly="readonly" <c:if test="${end_time==null }">value=""</c:if>></li>	 
                    <li>
                        <div class="btn_1">
                            <a class="btn_33" onclick="findAll();" href="javascript:;">查 询</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1">编号</span>
                        <span class="wid_2">时间</span>
                        <span class="wid_3">周期</span>
                        <span class="wid_4">状态</span>
                        <span class="wid_5">操作</span>
                    </li>
                    <c:if test="${reportList!=null && reportList.size()>0 }">
                    <c:forEach items="${reportList }" var="report" varStatus="indexs">
                    <li class="bor_gray">
                        <span class="wid_1">${(currPage-1)*10+indexs.count}</span>
                        <span class="wid_2">
                        <fmt:formatDate value="${report.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
                       </span>
                        <span class="wid_3">${report.cycle }</span>
                        <span class="wid_4">
                        <c:if test="${report.state==0 }">未初始化</c:if>
						<c:if test="${report.state==1 }">已初始化</c:if>
						</span>
                        <span class="wid_5">
                        <c:if test="${report.state==0 }">
                            <a href="javascript:;" onclick="initialize(${report.id});">初始化</a>
                            <b>|</b>
                            <a href="${report.detail_route }">下载原文件</a>
                            <b>|</b>
                            <a href="javascript:;" onclick="del(${report.id});">删除</a>
                        </c:if>
                        <c:if test="${report.state==1 }">
                        	<a href="javascript:;" onclick="exportsummary(${report.id})">导出汇总表</a>
                            <b>|</b>
                            <a href="${report.detail_route }">下载原文件</a>
                            <b>|</b>
                            <a href="javascript:;" onclick="del(${report.id});">删除</a>
                            <b>|</b>
                            <a href="javascript:;" onclick="sendemail(${report.id});">发送邮件通知</a>
                            <b>|</b>
                            <a href="javascript:;" onclick="checked(${report.id});">查看详情</a>
                        </c:if>
                        </span>
                    </li>
                  </c:forEach>
                  </c:if>
                  <c:if test="${reportList == null || reportList.size()<=0 }">
                  		<li>
							<h2>没有记录</h2>
						</li>
                  </c:if>
                </ul>
                <input id="token" type="hidden" name="token" value="${token}">
            </div>
            <%-- 分页demo --%>
			<div class="paging">
				<ul id="txmanager-pager"></ul>
			</div>
        </div>
    </div>
</div>
</body>
</html>