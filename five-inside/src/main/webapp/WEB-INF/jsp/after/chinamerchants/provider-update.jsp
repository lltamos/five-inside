<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_2.css"/>
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
function updatesProvider(){
	if (check()) {
		var name = $("input[name='Name']").val();
		var Contact = $("input[name='Contact']").val();
		var Email = $("input[name='Email']").val();
		var providerid = $("input[name='providerid']").val();
		$.ajax({
			url : '${ctx}/admin/after/provider/providerUpdate',
			type : 'POST',
			async : false,
			global : false,
			cache : false,
			data : {name:name,contact:Contact,email:Email,id:providerid},
			dataType : "json", 
			success : function(result) {
				var appResult = eval("(" + result + ")");
				var message = appResult.msg;
				var rcode = appResult.code;
				if(rcode == 0){
					alert(message);
					$("#chinamerchants_index").load("${ctx }/admin/after/provider/toProviderUpdatePage?providerId="+providerid);
				}
				if(rcode == 1){
					alert(message);
					pageGo("${ctx}/admin/after/provider/toProviderPageList")
				}
				
			}
		})
	}
}
</script>
<body>
    <div class="message_right">
        <div class="proving">
           	<ul>
                <li>
                    <p>修改厂商信息</p>
                </li>
                <li>
                    <span>厂商名称: </span><input name="Name" value="${provider.name }" type="text"/>
                	<input name="providerid" value="${provider.id }" type="hidden"/>
                </li>
                <li>
                    <span>联系方式: </span><input name="Contact" value="${provider.contact }" type="text"/>
                </li>
                <li>
                    <span>电子邮箱: </span><input name="Email" value="${provider.email }" type="text"/>
                </li>
                <li class="btn_9">
                    <button onclick="updatesProvider()">保存</button>
                </li>
            </ul>
        </div>
    </div>
</body>
