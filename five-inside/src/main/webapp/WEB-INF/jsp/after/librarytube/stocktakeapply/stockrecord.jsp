<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx }/css/baodan_9.css"/>
	<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<body>
      <div class="message_right">
          <div class="proving">
              <div class="date">
                  <ul>
                      <li><span style="margin-right:14px;">商品名称：</span><input type="text" name="Goodname" id="Goodname" value="${param.Goodname }" /></li>
                      <li><span style="margin-right:14px;">商品条码：</span><input type="text" name="BarCode" id="BarCode"  value="${param.BarCode }" /></li>
                      <li>
                          <div class="btn_1">
                              <a class="btn_33" onclick="findAll();" href="javascript:;">搜 索</a>
                          </div>
                      </li>
                  </ul>
              </div>
              <div class="form_1">
                  <ul>
                      <li class="bg_green">
                          <span class="wid_1">序号</span>
                          <span class="wid_2">商品名称</span>
                          <span class="wid_3">供应商</span>
                          <span class="wid_4">商品条形码</span>
                          <span class="wid_5">操作数量</span>
                          <span class="wid_6">剩余数量</span>
                          <span class="wid_7">备注</span>
                      </li> 
                      <c:if test="${stockRecordList.size() <= 0 }">
                    	  <%@include file="/common/nodata.jsp"%>
                   	  </c:if>
                      <c:if test="${stockRecordList.size() > 0 }">
	                      <c:forEach items="${stockRecordList}" var="stockRecord" varStatus="status">
		                      <li class="bor_gray">
		                           <span class="wid_1">${(currPage-1)*10+status.count}</span>
		                           <span class="wid_2"><pre style="padding:0;margin:0;font-family:Microsoft YaHei;background-color: transparent;border: 1px solid transparent;white-space: pre-wrap;">${stockRecord.Good_Name }</pre></span>
		                           <span class="wid_3">${stockRecord.NAME }</span>
		                           <span class="wid_4">${stockRecord.Bar_Code }</span>
		                           <span class="wid_5">${stockRecord.Num }</span>
		                           <span class="wid_6">${stockRecord.Remain_Num }</span>
		                           <span class="wid_7">${stockRecord.Remark }</span>
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
    /* 加载分页控件 */
    $(function(){
		getPage();
	});
	function getPage(){
		var Goodname = $("#Goodname").val();
	   	var BarCode = $("#BarCode").val();
		mypager.generPageHtml({
			pagerid:"txmanager-pager",
			pno:"${currPage}",
			total:"${totalPage}",
			totalRecords:"${totalRecords}",
			click:function(currPage){
				$("#lib-content").load("${ctx }/admin/after/libraryTube/stocktakeapply/findAllStock?Goodname="+encodeURIComponent(Goodname)+"&BarCode="+escape(BarCode)+"&page="+currPage);
			}
		});
	}
    function findAll(){
    	var Goodname = $("#Goodname").val();
	   	var BarCode = $("#BarCode").val();
	   	pageGo("${ctx }/admin/after/libraryTube/stocktakeapply/findAllStock?Goodname="+encodeURIComponent(Goodname)+"&BarCode="+escape(BarCode));
    }
    
    </script>
</body>
</html>