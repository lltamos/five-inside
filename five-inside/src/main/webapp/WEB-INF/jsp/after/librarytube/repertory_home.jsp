<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_15.css" />
</head>
<style>
	input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
    input[type="number"]{
        -moz-appearance: textfield;
    }
</style>

<body>
	<div id="content">
		<div class="message_right">
			<div class="proving">
				<h2>商品入库</h2>
				<div class="zhanghao">
					<form id="addForm">
						<div class="zhanghao_1">
							<div>商品条码:</div>
							<input name="barCode" type="text">
						</div>
						<div class="zhanghao_1">
							<div>商品名称:</div>
							<input name="Goodname" placeholder="商品名称" type="text" disabled="disabled">
						</div>
						<div class="zhanghao_1">
							<div>操作数量:</div>
							<input class="no_arrow" name="Num" length="10" type="number">
						</div>
						<input type="hidden" name="token" value="${token}">
					</form>
					<button id="sum" onclick="submit()" class="zhanghao_2">提交申请</button>
				</div>
			</div>
		</div>
	</div>



	<script>
	
	
		function checkInput() {
			var str1 = $('input[name="barCode"]').val();
			var str2 = $('input[name="Num"]').val();
			if (str1.trim() == "") {
				alert("商品条码不能为空!");
				return false;
			}
			if (str2.trim() == ""||str2.trim()<=0) {
				alert("请正确输入数量!");
				return false;
			}
			if (str2.trim().length>9) {
				alert("数量超出限制!");
				return false;
			}
			if (str2.trim()%1!=0) {
				alert("必须为整数!");
				return false;
			}
		
			return true;
		}
		function submit() {
			var views = $("input[type='text']");
			if (checkInput()) {
				
				var data = $("#addForm").serialize();				
				$.ajax({
		             type: "post",
		             url: "${pageContext.request.contextPath }/admin/after/inventory/add",
		             data: data,
		             cache:false,
		             success: function(data){
		            		var result = JSON.parse(data);
							if (result.code == 1) {								
								alert(result.msg);								
							} else {
								alert(result.msg);
							}							
							pageGo("${ctx }/admin/after/inventory/toInsertProPager");							
		             }
		         });
				
				
				
				
			}
		}
		$(document).ready(function() {
			$("input[name=barCode]").blur(function() {	
				$.get("${pageContext.request.contextPath }/admin/after/inventory/getGoods?barCode="+$("input[name=barCode]").val(),
						 function(data) {
							if(data.length==0){
								alert("未找到该数据");
							
							 pageGo("${ctx }/admin/after/inventory/toInsertProPager");	
							}else{
								var result = JSON.parse(data);	
								$("input[name=Goodname]").val(result.goodName);
							}
							
						
				
				});
			});	
		});
	</script>
</body>
