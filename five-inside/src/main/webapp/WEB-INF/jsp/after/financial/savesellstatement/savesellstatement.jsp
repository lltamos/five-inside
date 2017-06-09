<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/base.css"/>
    <link rel="stylesheet" href="${ctx }/css/Since_11.css"/>
    <link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css"/>
    <script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
    <script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
    <div class="message_right">
        <div class="proving">
            <div class="date">
                <ul>
                    <li class="datep"><span>开始日期: </span><input class="datainp wicon" type="text" name="begin_time" id="begin_time" value="${param.begin_time }" /></li>
                    <li class="datep"><span>结束日期: </span><input class="datainp wicon" type="text" name="end_time" id="end_time" value="${param.end_time }" /></li>
                    <li class="datep"><span>产品名称: </span><input id="good_name" type="text" placeholder="产品名称" value="${param.good_name }"/></li>
                    <li>
                        <div class="btn_1">
                            <a class="btn_33" onclick="findAll();" href="javascript:;">查 询</a>
                            <a class="btn_33" onclick="deriveCxStatement();" href="javascript:;">存销报表</a>
                            <a class="btn_33" onclick="deriveKcStatement();" href="javascript:;">库存报表</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1">序号</span>
                        <span class="wid_2">商品名称</span>
                        <span class="wid_3">规格</span>
                        <span class="wid_4">总金额</span>
                        <span class="wid_5">销量</span>
                        <span class="wid_6">入库量</span>
                        <span class="wid_7">盘差数</span>
                        <span class="wid_8">盘余数</span>
                        <span class="wid_9">退货数</span>
                    </li>
                    <c:if test="${saveSellStatementList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${saveSellStatementList.size() > 0 }">
	                    <c:forEach items="${saveSellStatementList}" var="saveSellStatement" varStatus="status">
		                    <li class="bor_gray">
		                        <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                        <span class="wid_2">${saveSellStatement.good_name }</span>
		                        <span class="wid_3">${saveSellStatement.key_word }</span>
		                        <span class="wid_4">
		                        <c:if test="${saveSellStatement.buyPrice==null }">0</c:if>
		                        <c:if test="${saveSellStatement.buyPrice!=null }">${saveSellStatement.buyPrice }</c:if>
		                        </span>
		                        <span class="wid_5">
		                        <c:if test="${saveSellStatement.xl ==null }">0</c:if>
		                        <c:if test="${saveSellStatement.xl !=null }">${saveSellStatement.xl}</c:if></span>
		                        <span class="wid_6">
		                        <c:if test="${saveSellStatement.rk == null }">0</c:if>
		                        <c:if test="${saveSellStatement.rk != null }">${saveSellStatement.rk }</c:if>
		                        </span>
		                        <span class="wid_7">
		                        <c:if test="${saveSellStatement.pc ==null }">0</c:if>
		                        <c:if test="${saveSellStatement.pc !=null }">${saveSellStatement.pc }</c:if>
		                        </span>
		                        <span class="wid_8">
		                        <c:if test="${saveSellStatement.py ==null}">0</c:if>
		                        <c:if test="${saveSellStatement.py !=null}">${saveSellStatement.py }</c:if>
		                        </span>
		                        <span class="wid_9">
		                        <c:if test="${saveSellStatement.th ==null}">0</c:if>
		                        <c:if test="${saveSellStatement.th !=null}">${saveSellStatement.th }</c:if>
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
    <script type="text/javascript">
		$(function(){
			getPage();
		});
		function getPage(){
			var begin_time = $("#begin_time").val();
		   	var end_time = $("#end_time").val();
		   	/* var good_name = new String($("#good_name").val()); */
		   	var good_name = $("#good_name").val();
			mypager.generPageHtml({
				pagerid:"txmanager-pager",
				pno:"${currPage}",
				total:"${totalPage}",
				totalRecords:"${totalRecords}",
				click:function(currPage){
					$("#financial_index").load("${cxt }/admin/after/financial/savesellstatement/findAll?begin_time="+escape(begin_time)+"&end_time="+escape(end_time)+"&good_name="+encodeURIComponent(good_name)+"&rows=10&page="+currPage);
				}
			});
		}
		function findAll(){
			var begin_time = $("#begin_time").val();
			var end_time = $("#end_time").val();
			var good_name = $("#good_name").val().trim();
			pageGo("${cxt }/admin/after/financial/savesellstatement/findAll?begin_time="+escape(begin_time)+"&end_time="+escape(end_time)+"&good_name="+encodeURIComponent(good_name));
		}
		function deriveCxStatement(){
			var begin_time = $("#begin_time").val();
			var end_time = $("#end_time").val();
			var good_name = $("#good_name").val();
			window.location.href="${cxt }/admin/after/financial/savesellstatement/deriveCxStatement?begin_time="+escape(begin_time)+"&end_time="+escape(end_time)+"&good_name="+encodeURIComponent(good_name);
	    }
		function deriveKcStatement(){
			window.location.href="${cxt }/admin/after/financial/savesellstatement/deriveKcStatement";
	    }
		$("#begin_time").jeDate({
		    isinitVal:false,
		    festival:true,
		    ishmsVal:false,
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
		$("#end_time").jeDate({
		    isinitVal:false,
		    festival:true,
		    ishmsVal:false,
		    maxDate: $.nowDate(0),
		    format:"YYYY-MM-DD",
		    zIndex:3000,
		})
		</script>
</body>
</html>