<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_2.css"/>
    <script src="${ctx }/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
</head>
<script type="text/javascript">
	function check(){
		var name = $("input[name='Name']").val();
		var Contact = $("input[name='Contact']").val();
		var Email = $("input[name='Email']").val();
		if(name == null || name ==''){
			alert("厂商名称不能为空")
			return false;
		}
		if(Contact == null || Contact ==''){
			alert("联系方式不能为空")
			return false;
		}
		var conregs = /^((0\d{2,3}-\d{7,8})|(1[35784]\d{9}))$/;
		if(!conregs.test(Contact)){
			alert("请输入正确的手机号码");
			return false;
		}
		if(Email == null || Email ==''){
			alert("电子邮箱不能为空")
			return false;
		}
		var emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		if(!emailReg.test(Email)){
			alert("请输入正确的电子邮箱");
			return false;
		}
		return true;
	}
	
	function providerAdd(token){
		var token = $("#token").val();
		var form = $("#providerForm");
		var options = {
			url : '${ctx}/admin/after/provider/providerAdd?token='+token,
			type : 'POST',
			cache : false,
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var rcode = appResult.code;
				if(rcode == 0){
					alert(message);
					$("#chinamerchants_index").load("${ctx }/admin/after/provider/toProviderAddPage");
				}
				if(rcode == 1){
					alert(message);
					$("#chinamerchants_index").load("${ctx }/admin/after/provider/toProviderPageList");
				}
			}
		};
		form.ajaxSubmit(options);
	}
</script>
<body>
    <div class="message_right">
        <div class="proving">
            <form id="providerForm">
            	<ul>
	                <li>
	                    <p>添加厂商</p>
	                </li>
	                <li>
	                    <span>厂商添加: </span><input name="Name" type="text" placeholder="厂商名称"/>
	                </li>
	                <li>
	                    <span>联系方式: </span><input name="Contact" type="text" placeholder="联系方式"/>
	                </li>
	                <li>
	                    <span>电子邮箱: </span><input name="Email" type="text" placeholder="电子邮箱"/>
	                </li>
	                <li class="btn_9">
	                	<input type="hidden" name="token" id="token" value="${token}">
	                    <a href="javascript:;" onclick="if(check()){providerAdd()}">提交</a>
	                </li>
	            </ul>
            </form>
        </div>
    </div>
</body>
