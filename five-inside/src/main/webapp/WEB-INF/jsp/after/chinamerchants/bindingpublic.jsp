<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%><head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_7.css"/>
    <link rel="stylesheet" href="${ctx }/js/select2/css/select2.min.css">
    <script type="text/javascript" src="${ctx}/js/select2/js/select2.full.js"></script>
    <script type="text/javascript" src="${ctx}/js/bankAddr.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
</head>
<script type="text/javascript">

	$(function(){
		var banknames=("${provider.bankName}");
		createBankInfo($("#bankName"),banknames);
		$("#bankName").on("change",function(){
			var bankName =$("#select2-bankName-container").html();
			$("input[name='BankName']").val(bankName);
		})
	});
	
	function bindingPublic(){
		//var banknames = $("input[name='BankName']").val();
		var bankName =$("#select2-bankName-container").html();
		var accountNum = $("input[name='AccountNum']").val();
		var aaccountOwner = $("input[name='AccountOwner']").val();
		var accountArea = $("input[name='AccountArea']").val();
		var accountCity = $("input[name='AccountCity']").val();
		var bankNum = $("input[name='BankNum']").val();
		var accountsd = $("#account").val();
		var accountType = $("input[name='AccountType']").val();
		if(bankName == null || bankName == ''){
			alert("开户银行不能为空");
			return false;
		}
		if(accountNum == null || accountNum == ''){
			alert("收款账号不能为空");
			return false;
		}
		console.log(accountNum.length)
		if(accountNum.length != 16 && accountNum.length != 19){
			alert("请输入正确收款账号");
			return false;
		}
		if(aaccountOwner == null || aaccountOwner == ''){
			alert("户主姓名不能为空");
			return false;
		}
		if(accountsd == '对公'){
			if(accountArea == null || accountArea == ''){
				alert("开户地区不能为空");
				return false;
			}
			if(accountCity == null || accountCity == ''){
				alert("户主城市不能为空");
				return false;
			}
			if(bankNum == null || bankNum == ''){
				alert("联行账号不能为空");
				return false;
			}
		}
		
		var form = $("#bindingForm");
		var options = {
			url : '${ctx}/admin/after/provider/bindingAccount',
			type : 'POST',
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				alert(message);
				pageGo("${ctx }/admin/after/provider/toProviderPageList?page=${param.page}&providerName=${param.providerName}&startTime="+escape("${param.startTime}")+"&endTime="+escape("${param.endTime}"));
			}
		};
		form.ajaxSubmit(options);
	}
	
	if(${provider.accountAttribute=="对公"}){
		$("input[name='AccountType']").val("账户");
		$(".dis_none").attr("style","display:block");
	}else{
		$("input[name='AccountType']").val("借记卡");
		$(".dis_none").attr("style","display:none");
	}
</script>
<body>
    <div class="message_right">
        <div class="proving">
            <p>当前选中供应商: <span>${provider.name }</span></p>
            <form id="bindingForm" method="post">
            	<ul>
	                <li>
	                    <span>开户银行: </span><input name="BankName" id="bankName" value="${provider.bankName}" type="text"/>
	                    <input name="id" value="${provider.id}" type="hidden"/>
	                </li>
	                <li>
	                    <span>收款账号: </span><input name="AccountNum" value="${provider.accountNum }" type="text"/>
	                </li>
	                <li>
	                    <span>户主姓名: </span><input name="AccountOwner" value="${provider.accountOwner }" type="text"/>
	                </li>
	                <li>
	                    <span>账户属性: </span>
	                    <select id="account" onchange="aaa()" name="AccountAttribute">
	                    	<option value="对私" <c:if test="${provider.accountAttribute == '对私'}">selected</c:if>>对私</option>
	                    	<option value="对公" <c:if test="${provider.accountAttribute == '对公'}">selected</c:if>>对公</option>
	                    </select>
	                </li>
	                <li class="dis_none">
	                    <span>开户地区: </span><input name="AccountArea" value="${provider.accountArea }" type="text" placeholder="开户地区"/>
	                </li>
	                <li  class="dis_none">
	                    <span>开户城市: </span><input name="AccountCity" value="${provider.accountCity }" type="text" placeholder="开户城市"/>
	                </li>
	                <li  class="dis_none">
	                    <span>联行账号: </span><input name="BankNum" value="${provider.bankNum }" type="text" placeholder="办理开户手续的营业网点代号,官网可查"/>
	                </li>
	                <script>
	                function aaa(){
	                	if($("#account").val()=="对公"){
	                		$("input[name='AccountType']").val("账户");
	                		$(".dis_none").attr("style","display:block");
	                	}else{
	                		$("input[name='AccountType']").val("借记卡");
	                		$(".dis_none").attr("style","display:none");
	                	}
	                }
	                </script>
	            </ul>
	            <ul>
	                <li>
	                    <span>账户类型: </span><input readonly="readonly" name="AccountType" type="text" placeholder="账户"/>
	                </li>
	                <li>
	                    <span>支行名称: </span><input name="BranchName" value="${provider.branchName }" placeholder="支行名称(非必填)" type="text"/>
	                </li>
	                <li>
	                    <span>手机号码: </span><input name="Mobile" value="${provider.mobile }" placeholder="收款人手机号(非必填)" type="text"/>
	                </li>
	                <li>
	                    <span>所属机构: </span><input name="Organization" value="${ provider.organization}" placeholder="所属机构(非必填)" type="text"/>
	                </li>
	            </ul>
            </form>
            <div class="btn_9">
                <button onclick="bindingPublic()">保存</button>
            </div>
        </div>
    </div>
</body>
