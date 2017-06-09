<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
</head>
<script type="text/javascript">
	function pageGo(url){
		$("#financial_index").load(url);
	}
</script>
<body>
	<div class="nav">
		<div class="logo">
			<img src="${ctx}/img/Logo.png" alt="" />
		</div>
	</div>
	<div class="among">
		<%-- 财务后台左侧菜单 --%>
		<%@ include file="../../../after/financial/common/financial-left.jsp"%>

		<%-- 页面刷新主体 --%>
		<div id="financial_index">
			<c:import url="${ctx}/admin/after/storageapply/findStorageApplys"></c:import>
		</div>
	</div>
</body>
