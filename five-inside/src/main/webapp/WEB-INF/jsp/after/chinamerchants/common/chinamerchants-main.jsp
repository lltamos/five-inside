<%@ include file="/common/commonPublic.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
	<script src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
</head>
<script type="text/javascript">

	document.cookie="goodId=";
	function pageGo(url){
		$("#chinamerchants_index").load(url);
	}
</script>
<body>
	<div class="nav">
		<div class="logo">
			<img src="${pageContext.request.contextPath }/img/Logo.png" alt="" />
		</div>
	</div>
	<div class="among">
		<%-- 招商后台左侧菜单 --%>
		<%@ include file="../../chinamerchants/common/chinamerchants-left.jsp"%>

		<%-- 页面刷新主体 --%>
		<div id="chinamerchants_index">
			<c:import url="${ctx}/admin/after/good/toGoodPageList"></c:import>
		</div>
	</div>
</body>
