<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/baodan_2.css"/>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
     <div class="message_right">
         <div class="proving">
             <div class="date">
                 <ul>
                     <li><span style="margin-right:14px;">用户账户：</span><input type="text" name="mobile" id="mobile" value=${param.mobile } ></li>
                     <li>
                     <li>
                         <div class="btn_1">
                             <a class="btn_33" href="javascript:;" onclick="findAll();">搜 索</a>
                         </div>
                     </li>
                 </ul>
             </div>
             <div class="form_1">
                 <ul>
                     <li class="bg_green">
                         <span class="wid_1">订单编号</span>
                         <span class="wid_2">下单日期</span>
                         <span class="wid_3">订单金额(元)</span>
                         <span class="wid_4">用户姓名</span>
                         <span class="wid_5">用户帐号</span>
                         <span class="wid_6">订单状态</span>
                         <span class="wid_7">操作</span>
                     </li>
                     <c:if test="${xytOrderList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                 </c:if>
	                 <c:if test="${xytOrderList.size() > 0 }">
	                     <c:forEach items="${xytOrderList}" var="xytOrder" varStatus="status">
		                     <li class="bor_gray">
		                         <span class="wid_1">${xytOrder.order_num}</span>
		                         <span class="wid_2"><fmt:formatDate value="${xytOrder.created_time}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></span>
		                         <span class="wid_3">${xytOrder.total_money}</span>
		                         <span class="wid_4">${xytOrder.NAME}</span>
		                         <span class="wid_5">${xytOrder.Mobile}</span>
		                         <span class="wid_6">
		                         	<c:if test="${xytOrder.States == 1 }">
										<i>订单已支付</i> 
									</c:if>
		                     		<c:if test="${xytOrder.States == 2 }">
										<i>订单已配送</i> 
									</c:if>
		                     		<c:if test="${xytOrder.States == 3 }">
										<i>订单已完成</i> 
									</c:if>
		                     		<c:if test="${xytOrder.States == 4 }">
										<i>订单已撤销</i> 
									</c:if>
		                         </span>
		                         <span class="wid_7">
		                             <a href="javascript:;" style="color: #0c48d6;" onclick="open_1('${xytOrder.order_num}')">详情</a>
		                         </span>
		                     </li>
	                     </c:forEach>
	                     </div>
			             <%-- 分页demo --%>
						 <div class="paging">
							 <ul id="txmanager-pager"></ul>
						 </div>
                     </c:if>
                 </ul>
         </div>
     </div>
     <div id="test"></div>
	 <div id="dialog">
	    <p class="close">
	        <span>订单详情</span>
	        <a class="gb_1" href="#" onclick="close_1();">X</a>
	    </p>
	    <div class="message">
	        <div class="form_1">
	            <ul id="detailUl">
	                <li class="bg_green">
	                    <span class="wid_1">商品名称</span>
	                    <span class="wid_2">商品单价</span>
	                    <span class="wid_3">商品数量</span>
	                    <span class="wid_4">小计</span>
	                </li>
	            </ul>
	        </div>
	    </div>
	 </div>
	 
	 <div style="display: none;" id="tempDetail"></div>
	 
     <script type="text/javascript">
     	/* 加载分页控件 */
	    $(function(){
	    	var begin_time = $("#begin_time").val();
	    	mypager.generPageHtml({
	    		pagerid:"txmanager-pager",
	    		pno:"${currPage}",
	    		total:"${totalPage}",
	    		totalRecords : "${totalRecords}",
	    		click:function(currPage){
	    			$("#aftersales_index").load("${ctx}/admin/after/aftersales/shoporder/findAllXYTOrder?mobile=${param.mobile}&rows=10&page="+currPage);
	    		}
	    	});
	    });
	    function findAll(){
	    	var mobile = $("#mobile").val();
	   		pageGo("${ctx}/admin/after/aftersales/shoporder/findAllXYTOrder?mobile="+mobile);
	    }
	    function open_1(order_num){
        	var s = document.getElementById("test");
            s.style.display = "block";
            var l = document.getElementById("dialog");
            l.style.display = "block";
            $.ajax({
				 type : "POST",
				 url : "${ctx }/admin/after/aftersales/shoporder/checkDetails",
				 data : {
						"order_num":order_num
				 	},
				 success : function(data) {
					var sc = eval('(' + data + ')')
					var code = sc.code;
					if(code==1){
						$("#tempDetail").html("<li class='bor_gray list'>"+
				           "<span class='wid_1 GoodName'>$GoodName$</span>"+
				           "<span class='wid_2 GoodMoney'>$GoodMoney$</span>"+
				           "<span class='wid_3 GoodNum'>$GoodNum$</span>"+
				           "<span class='wid_4 sum'>$sum$</span>"+
				           "</li>");
						var html="";
						for(var i=0;i<sc.content.length;i++){
							var tempDetail=$("#tempDetail").html();
							var content=sc.content[i];
							var GoodName = content.good_name;
							var GoodMoney = content.good_money;
							var GoodNum = content.good_num;
							tempDetail=tempDetail.replace("$GoodName$",GoodName);
							tempDetail=tempDetail.replace("$GoodMoney$",GoodMoney);
							tempDetail=tempDetail.replace("$GoodNum$",GoodNum);
							tempDetail=tempDetail.replace("$sum$",GoodMoney*GoodNum);
							html+=tempDetail;
						}
						$("#detailUl").append(html);
					}
				 }
			 });
        }
        function close_1(){
            var s = document.getElementById("test");
            s.style.display = "none";

            var l = document.getElementById("dialog");
            l.style.display = "none";
            $(".list").remove();
        }
	 </script>
</body>
</html>