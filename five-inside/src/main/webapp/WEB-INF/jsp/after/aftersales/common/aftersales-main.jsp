<%@ include file="/common/commonPublic.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
</head>
<script type="text/javascript">
	function pageGo(url){
		$("#aftersales_index").load(url);
	}
</script>
<body>
	<div class="nav">
		<div class="logo">
			<img src="${pageContext.request.contextPath }/img/Logo.png" alt="" />
		</div>
	</div>
	<div class="among">
		<%-- 售后后台左侧菜单 --%>
		<%@ include file="../../../after/aftersales/common/aftersales-left.jsp"%>

		<%-- 页面刷新主体 --%>
		<div id="aftersales_index">
			<c:import url="${ctx}/admin/after/aftersales/salesreturn/findGoodsReturn?remark=1"></c:import>
		</div>
	</div>
</body>
