<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/css/baodan_10.css"/>
    <style>
        .poll_51{
            position: relative;
            width: 382px;
        }
        .tn_3{
            width: 297px;
            height: 30px;
        }
        .tn_1{
            position: absolute;
		    right: 5px;
		    bottom: 21px;
		    width: 20px;
		    height: 15px;
        }
        .tn_2{
            position: absolute;
		    right: 5px;
		    top: 1px;
		    width: 20px;
		    height: 15px;
}
        }
    </style>
</head>
<body>
      <div class="message_right">
          <div class="proving">
             <h2>商品盘存</h2>
              <div class="zhanghao">
                  <div class="zhanghao_1">
                      <div>商品条码:</div>
                      <input type="text" name="BarCode" id="BarCode" onblur="checkvalue()"/>
                      <input type="hidden" name="GoodId" id="GoodId"/>
                      <input type="hidden" name="GoodMoney" id="GoodMoney"/>
                  </div>
                  <div class="zhanghao_1">
                      <div>商品名称:</div>
                      <input type="text" name="Goodname" id="Goodname" disabled="disabled">
                  </div>
                  <div class="zhanghao_1">
                      <div>操作数量:</div>
                      <div class="poll_51">
                       <img class="tn_1" src="${ctx}/img/xia.png">
                       <input class="tn_3" type="text" name="Num" id="Num" value="0">
                       <img class="tn_2" src="${ctx}/img/shang.png">
                      </div>
                  </div>
                  <div class="zhanghao_1">
                      <div>处理类型:</div>
                      <select name="HandleType" id="HandleType">
				<option value="1" >厂家处理</option>
				<option value="2" >公司支付</option>
				</select>
                  </div>
                  <div class="zhanghao_1">
                      <div>备注:</div>
                      <input type="text" name="Remark" id="Remark" maxlength="10"/>
                      <input type="hidden" name="token" id="token" value="${token}">
                  </div>
                  <button class="zhanghao_2" onclick="commit()">提交申请</button>
              </div>
          </div>
      </div>
    
    <script>
    	
    	$("#BarCode").keyup(function(){
    		var BarCode = $("#BarCode").val();
    		$.ajax({
				 type : "POST",
				 url : "${ctx }/admin/after/libraryTube/stocktakeapply/findGoodByBarCode?BarCode="+BarCode,
				 success : function(data) {
					var sc = eval('(' + data + ')')
					var message = sc.msg;
					var code = sc.code;
					if( code == 1){
						var goodName = sc.goodName;
						var id = sc.id;
						var goodMoney = sc.goodMoney;
						$("#GoodId").attr("value",id);
						$("#Goodname").attr("value",goodName);
						$("#GoodMoney").attr("value",goodMoney);
					}
					else if( code == 0){
						$("#GoodId").attr("value","");
						$("#Goodname").attr("value","");
						$("#GoodMoney").attr("value","");
					}
				 }
			 });
    	});
    	function checkvalue(){
    		var Goodname = $("#Goodname").val();
    		var BarCode = $("#BarCode").val();
    		if( Goodname != "" ){
    			$.ajax({
   				 type : "POST",
   				 url : "${ctx }/admin/after/libraryTube/stocktakeapply/getGoodByBarCodeBe?BarCode="+BarCode,
   				 success : function(data) {
   					var sc = eval('(' + data + ')')
   					var message = sc.msg;
   					var code = sc.code;
   					if( code == 0 ){
   						$("#BarCode").val("");
   						$("#GoodId").attr("value","");
						$("#Goodname").attr("value","");
						$("#GoodMoney").attr("value","");
   						alert("请检查商品是否入库!")
   					}
   				 }
   			 });
    		}
    	}
        function commit(){
    		var GoodId = $("#GoodId").val();
    		var Goodname = $("#Goodname").val();
    		var Num = $("#Num").val();
    		var HandleType = $("#HandleType").val();
    		var Remark = $("#Remark").val();
    		var GoodMoney = $("#GoodMoney").val();
    		var token = $("#token").val();
    		if( cheak() ){
    			$.ajax({
   				 type : "POST",
   				 url : "${ctx }/admin/after/libraryTube/stocktakeapply/save-stocktakeapply",
   				 data : {
   						'GoodId' : GoodId,
   						'Goodname' : Goodname,
   						'Num' : Num,
   						'HandleType' : HandleType,
   						'Remark' : Remark,
   						'GoodMoney' : GoodMoney,
   						'token' : token,
   					},
   				 success : function(data) {
   					var sc = eval('(' + data + ')')
   					var message = sc.msg;
   					var code = sc.code;
   					alert(message);
   					pageGo("${ctx }/admin/after/libraryTube/stocktakeapply/to-stockmanage");
	   				}
	   			});
    		}
        };
        function cheak(){
        	var BarCode = $("#BarCode").val();
    		var Num = $("#Num").val();
    		var HandleType = $("#HandleType").val();
    		var Remark = $("#Remark").val();
    		var Goodname = $("#Goodname").val();
    		if( BarCode == null || BarCode == ""){
    			alert("条形码不能为空");
    			return false;
    		}
    		if( Goodname == null || Goodname == ""){
    			alert("商品名称不能为空");
    			return false;
    		}
    		if( Num == 0 || Num == null || Num == "" ){
    			alert("操作数量不能为空且不为零");
    			return false;
    		}
    		if( HandleType == null || HandleType == ""){
    			alert("处理类型不能为空");
    			return false;
    		}
    		if( Remark == null || Remark == ""){
    			alert("备注不能为空");
    			return false;
    		}
    		return true;
        }
        
        $('.tn_1').on('click',function(){
            $(this).siblings('.tn_3').val(parseInt($(this).siblings('.tn_3').val())-1);
            
        });
	    
	    $('.tn_2').on('click',function(){
	            $(this).siblings('.tn_3').val(parseInt($(this).siblings('.tn_3').val())+1);
	            
	        });
	    
	    $(document).click(function(){
	        $('.tn_3').each(function(){
	            if($(this).val()==""){
					alert('操作数量不能为空');
					$(this).val('0');	
	            }
				
	        });
	    });
    </script>
</body>
</html>