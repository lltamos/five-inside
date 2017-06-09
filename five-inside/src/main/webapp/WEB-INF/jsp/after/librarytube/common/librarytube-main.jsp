<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
<meta charset="UTF-8">
<title>易商通自提后台</title>
<link rel="stylesheet" href="${ctx }/css/base.css" />
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_15.css" /> --%>
<link rel="stylesheet" href="${ctx }/css/bootstrap.min.css" />

<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<script type="text/javascript">
	function pageGo(url) {

		$("#lib-content").load(url);
	}
</script>
<body>
	<div class="nav">
		<div class="logo">
			<img src="${ctx }/img/Logo.png" alt="" />
		</div>
	</div>
	<div class="among">
		<%-- 财务后台左侧菜单 --%>
		<%@ include file="../../../after/librarytube/common/librarytube-left.jsp"%>

		<%-- 页面刷新主体 --%>
		<div id="lib-content">

			<c:import url="${ctx}/admin/after/inventory/inventoryquery?rows=10"></c:import>

		</div>
	</div>
</body>
