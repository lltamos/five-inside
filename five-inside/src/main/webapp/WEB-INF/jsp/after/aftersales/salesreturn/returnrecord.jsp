<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/baodan_3.css"/>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
    <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li><span>商品名称：</span><input type="text" name="good_name" id="good_name" value=${param.good_name } ></li>
                    <li><span>订单号：</span><input type="text" name="order_no" id="order_no" value=${param.order_no } ></li>
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
                        <span class="wid_1">序号</span>
                        <span class="wid_2">商品名称</span>
                        <span class="wid_3">商品金额</span>
                        <span class="wid_4">商品数量</span>
                        <span class="wid_5">订单号</span>
                        <span class="wid_6">剩余数量</span>
                        <span class="wid_7">购买类型</span>
                        <span class="wid_8">备注</span>
                    </li>
                    <c:if test="${returnRecordList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${returnRecordList.size() > 0 }">
	                    <c:forEach items="${returnRecordList}" var="returnRecord" varStatus="status">
		                    <li class="bor_gray">
		                        <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                        <span class="wid_2">${returnRecord.good_name}</span>
		                        <span class="wid_3">${returnRecord.good_money}</span>
		                        <span class="wid_4">${returnRecord.good_num}</span>
		                        <span class="wid_5">${returnRecord.order_no}</span>
		                        <span class="wid_6">${returnRecord.good_num - returnRecord.return_num}</span>
		                        <span class="wid_7">
		                        <c:if test="${returnRecord.shop_type == 0 }">
									<i>新业态</i> 
								</c:if>
		                       	<c:if test="${returnRecord.shop_type == 1 }">
									<i>本地报单</i> 
								</c:if>
		                        </span>
		                        <span class="wid_8">${returnRecord.remark}</span>
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
    <script>
    /* 加载分页控件 */
	$(function(){
		mypager.generPageHtml({
			pagerid:"txmanager-pager",
			pno:"${currPage}",
			total:"${totalPage}",
			totalRecords : "${totalRecords}",
			click:function(currPage){
				$("#aftersales_index").load("${ctx }/admin/after/aftersales/salesreturn/findAllReturnRecord?good_name=${param.good_name}&order_no=${param.order_no}&rows=10&page="+currPage);
			}
		});
	});
	function findAll(){
		var good_name = $("#good_name").val();
		var order_no = $("#order_no").val();
		pageGo("${cxt }/admin/after/aftersales/salesreturn/findAllReturnRecord?good_name="+encodeURIComponent(good_name)+"&order_no="+order_no);
	}
    </script>
</body>
</html>