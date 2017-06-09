<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_3.css"/>
    <script src="${ctx }/js/jquery-1.7.2.js"></script>
    <link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css"/>
    <script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
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
			$("#chinamerchants_index").load("${ctx }/admin/after/provider/toProviderPageList?page="+currPage+"&providerName=${param.providerName}&startTime="+escape("${param.startTime}")+"&endTime="+escape("${param.endTime}"));
		}
	});
});

function queryInfo() {
	var providerName=$("#providerName").val().trim();
	var startTime=$("#startTime").val();
	var endTime=$("#endTime").val();
	pageGo("${ctx }/admin/after/provider/toProviderPageList?providerName="+encodeURIComponent(providerName)+"&startTime="+escape(startTime)+"&endTime="+escape(endTime));
	
}
$("#startTime").jeDate({
    isinitVal:false,
    festival:true,
    ishmsVal:false,
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD",
    zIndex:3000,
})
$("#endTime").jeDate({
    isinitVal:false,
    festival:true,
    ishmsVal:false,
    maxDate: $.nowDate(0),
    format:"YYYY-MM-DD",
    zIndex:3000,
})
function associatedAdd(name,providerId){
	document.cookie="goodId=";
	pageGo("${ctx}/admin/after/provider/toAssociatedPage?proName="+encodeURIComponent(name)+"&providerId="+encodeURIComponent(providerId));
}

function showAssociated(providerId,providerName){
	document.cookie="goodId=";
	pageGo("${ctx }/admin/after/provider/toShowAssociatedPage?providerId="+encodeURIComponent(providerId)+"&proName="+encodeURIComponent(providerName));
}

</script>
<body>
    <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li><span>厂商名称: </span><input id="providerName" value="${param.providerName }" type="text"/></li>
                    <li><span>开始日期: </span><input id="startTime" value="${param.startTime }" type="text"/></li>
                    <li><span>结束日期: </span><input id="endTime" value="${param.endTime }" type="text"/></li>
                    <li>
                        <div class="btn_1">
                            <a onclick="queryInfo()" class="btn_33" href="javascript:;">搜 索</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1">编号</span>
                        <span class="wid_2">厂商名称</span>
                        <span class="wid_3">联系方式</span>
                        <span class="wid_4">添加时间</span>
                        <span class="wid_5">操作</span>
                    </li>
                    <c:if test="${providerList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${providerList.size() > 0 }">
                    <c:forEach items="${providerList }" var="provider" varStatus="status">
                    	<li class="bor_gray">
	                        <span class="wid_1">${(currPage-1)*10+status.count}</span>
	                        <span class="wid_2">${provider.name }</span>
	                        <span class="wid_3"> ${provider.contact }</span>
	                        <span class="wid_4"><fmt:formatDate value="${provider.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
	                        <span class="wid_5">
	                            <a onclick="associatedAdd('${provider.name }','${provider.id }')" href="javascript:;">添加关联</a>
	                            <b>|</b>
	                            <a onclick="showAssociated('${provider.id }','${provider.name }')" href="javascript:;">查看关联</a>
	                            <b>|</b>
	                            <a onclick="pageGo('${ctx }/admin/after/provider/toProviderUpdatePage?providerId=${provider.id }')" href="javascript:;">修改信息</a>
	                            <b>|</b>
	                            <a onclick="pageGo('${ctx}/admin/after/provider/toBindingPage?providerId=${provider.id }')" href="javascript:;">绑定账户</a>
	                        </span>
	                    </li>
                    </c:forEach>
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
