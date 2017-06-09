<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title>易商通自提后台</title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/invoice.css" >
    <script src="${ctx}/js/jquery.PrintArea.js"></script>
    <script type="text/javascript">
    	function pay(){
    		var token = $("#token").val()
    		var orderNo = $("#orderNo").text();
    		if(null ==orderNo || orderNo=="undefined"){
    			alert("订单号有误!");
    		}
    		$.ajax({
				type:"POST",
				url :"${ctx}/front/pay/gotopay",
				data:{
					"orderno" : orderNo,
					"token" : token
				},
				success:function(result) {
					var sc = eval('(' + result + ')');
					if(sc.code == 0){
    					 alert(sc.msg);
    				}
					if(sc.code == 1){
						$(".invoice").printArea();
						window.location.href ="${ctx}/front/cashier/to-productlist";
					}
					
				}
			});
    	}
    </script>
</head>
<body>
    <div class="invoice">
        <div class="logold">
            <p>易商通体验中心</p>
        </div>
        <input id="token" type="hidden" name="token" value="${token}">
        <div class="invoicebody">
            <p class="ddzxbh clearfix"><i>地址 : </i><span>北京市大兴区亦庄地盛南街甲1号院中电金厂4号楼五层</span></p>
            <p class="ddhma clearfix"><i>订单号 : </i><span id="orderNo">${sessionScope.orderNo}</span></p>
            <p class="tiao">===================================================</p>
            <p class="spsldjje clearfix">
                <span class="jje_1">商品名</span>
                <span class="jje_2">数量</span>
                <span class="jje_3">单价</span>
                <span>金额</span>
            </p>
            <c:forEach items="${cartList }" var="item">
            <div>
            <p class="shangping clearfix"><span>${item.goods.goodName }</span></p>
            <p class="jiashul clearfix">
            <span class="jiash_1">${item.good_num }</span>
            <span class="jiash_2">${item.goods.goodMoney }</span>
            <span class="jiash_3">
            <fmt:formatNumber type="number" value="${item.good_num*item.goods.goodMoney }" maxFractionDigits="2"/>
            </span></p>
            </div>
            </c:forEach>
            <p class="tiao">========================================================</p>
            <p class="yifuhk"><span>操作员 : </span><i>${sessionScope.rolet.name}</i></p>
            <p class="yifuhk"><span>应付货款 : </span><i>${price}</i></p>
            <p class="xfzzhao"><span>消费者帐号 : </span><i>${sessionScope.cashier.mobile}</i></p>
            <p class="yifuhk"><span>消费者姓名 : </span><i>${sessionScope.cashier.name}</i></p>
            <p class="zhhuyue"><span>消费者余额 : </span><i>${balance.data }</i></p>
            <p class="tiao" style="overflow: hidden;white-space: nowrap;vertical-align: middle;">=========<span class="riqi">${time }</span>==========</p>
            <p class="kefudianh">客服电话：4000 325 315</p>
            <p class="dingdanzhong">请当面核实所购买商品与小票清单数据是否一致,如有疑问,请咨询客服或到当地订单中心咨询</p>
            <p class="xiexiehuigu">谢谢惠顾</p>
        </div>
    </div>
    <p class="zhifu"><a class="btn_zhifu" href="javascript:;" onclick="pay()">确认支付</a></p>
</body>
</html>