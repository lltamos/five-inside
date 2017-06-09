<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_15.css"/>
	<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
    <script type="text/javascript">
    $(function(){
		getPage();
	});
	function getPage(){
		var id = $("#sid").val();
		mypager.generPageHtml({
			pagerid:"txmanager-pager",
			pno:"${currPage}",
			total:"${totalPage}",
			totalRecords:"${totalRecords}",
			click:function(currPage){
				$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/checked?rows=10"+"&page="+currPage+"&id="+id);
			}
		});
	}
	function resendEmail(id){
		var token = $("input[name='token']").val();
		var sid = $("#sid").val();
		$.ajax({
			type:"POST",
			url :"${ctx }/admin/after/financial/summarymanage/resendemail",
			data:{
				"id" : id,
				"sid" : sid,
				"token" : token
			},
			success:function(result) {
				var sc = eval('(' + result + ')');
				if(sc.code == 0){
					alert(sc.msg);
					$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/checked?id="+sid);
				}
				if(sc.code == 1){
					alert(sc.msg);
					$("#financial_index").load("${ctx }/admin/after/financial/summarymanage/checked?id="+sid);
				}
			}
		});
	}
    </script>
</head>
<body>
<input id="sid" hidden="hidden" value="${sid }">
 <div class="message_right">
        <div class="proving">
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1">编号</span>
                        <span class="wid_2">供应商</span>
                        <span class="wid_3">产品名称</span>
                        <span class="wid_4">产品数量</span>
                        <span class="wid_5">状态</span>
                        <span class="wid_6">操作</span>
                    </li>
                    <c:forEach items="${reportList }" var="report" varStatus="indexs">
                    <li class="bor_gray">
                        <span class="wid_1">${(currPage-1)*10+indexs.count}</span>
                        <span class="wid_2">${report.provider.name }</span>
                        <span class="wid_3">${report.goods.goodName }</span>
                        <span class="wid_4">${report.good_num }</span>
                        <span class="wid_5">
                        <c:if test="${report.email_state==1 }">已发送</c:if>
                        <c:if test="${report.email_state==0 }">未发送</c:if>
                        <c:if test="${report.email_state==2 }">发送失败</c:if>
                        </span>
                      	<c:if test="${report.email_state==0 }">
                        <span class="wid_6">
                            <button onclick="resendEmail(${report.provider_id});">发送 </button>
                        </span>
                        </c:if>
                        <c:if test="${report.email_state==2 }">
                        <span class="wid_6">
                            <button onclick="resendEmail(${report.provider_id});">重新发送</button>
                        </span>
                        </c:if>
                    </li>
                   </c:forEach>
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