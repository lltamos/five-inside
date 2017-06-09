<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/css/base.css"/>
    <link rel="stylesheet" href="${ctx}/css/Since_4.css"/>
    <script src="${ctx}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctx}/js/mypager.js"></script>
</head>
<script type="text/javascript">
/* 加载分页控件 */
$(function(){
	mypager.generPageHtml({
		pagerid:"provider-pager",
		//当前页码
		pno:"${currPage}",
		total:"${totalPage}",
		totalRecords : "${totalRecords}",
		click:function(currPage){
			$("#chinamerchants_index").load("${ctx }/admin/after/provider/toShowAssociatedPage?page="+currPage+"&goodName=${param.goodName}&barCode="+escape("${param.barCode}")+"&proName=${proName }&providerId=${providerId}");
		}
	});
	
	var list1= new Array();
	var godlist = getCookie("goodId");
	var list2 = godlist.split(",")
	$('.bor_gray').each(function(){
		var wl=$(this).find('.wid_2').html();
		list1.push(wl);
	});
	
	
	var sameCount = 0;
	 for(var i=0;i<list1.length;i++){
	    var ida = list1[i];
	    for(var j=0;j<list2.length;j++){
	        var idb = list2[j];
	        if(ida == idb){
	        	$("#checkid"+idb).attr("checked","checked");
	            sameCount++;
	        }
	    }
	}
	
});

function queryInfo() {
	var goodName=$("#goodName").val().trim();
	var barCode=$("#barCode").val().trim();
	pageGo("${ctx }/admin/after/provider/toShowAssociatedPage?&goodName="+encodeURIComponent(goodName)+"&barCode="+escape(barCode)+"&proName=${proName }&providerId=${providerId}");
	
}

function getCookie(name) {
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg))
	return unescape(arr[2]);
	else
	return null;
}

Array.prototype.removeByValue = function(val) {
	  for(var i=0; i<this.length; i++) {
	    if(this[i] == val) {
	      this.splice(i, 1);
	      document.cookie="goodId="+this;
	      break;
	    }
	  }
}

function checkedGood(goodsid){
	var falg = $("#checkid"+goodsid+"").attr("checked");
	if(falg == 'checked'){
		saveCookie(goodsid);
	}else{
		var gids= new Array();
		var gid2 = getCookie("goodId");
		gids=gid2.split(",");
		gids.removeByValue(goodsid);
	}
}

function saveCookie(goodsid){
	var gid = getCookie("goodId");
	if(gid == null || gid == ''){
		document.cookie="goodId="+goodsid;
	}
	var dds =getCookie("goodId");
	var strs= new Array();
	var falg = true;
	var gid2 = getCookie("goodId");
	strs=gid2.split(",");
	for (i=0;i<strs.length ;i++ ) {
		if(strs[i]!=null && strs[i]!=''){
			if(strs[i] == goodsid){
				falg = false;
				break;
			}else{
				falg= true;
			}
		}
	}
	if(falg){
		document.cookie="goodId="+gid2+","+goodsid;
	}
}

function associatedCancel(){
	var goodsid = getCookie("goodId");
	if(goodsid == null || goodsid == ''){
		alert("请选择商品");
		return ;
	}
	$.ajax({
		  type: 'POST',
		  url: "${ctx }/admin/after/provider/associatedCancel?goodsid="+goodsid+"&providerId=${providerId}",
		  dataType:"json",
		  success : function (data){
			  alert(data.msg);
			  pageGo("${ctx }/admin/after/provider/toShowAssociatedPage?goodName=${param.goodName}&barCode=${param.barCode}&proName=${proName }&providerId=${providerId}");
		  }
	  });
}


</script>
<body>
    <div class="message_right">
        <div class="proving">
            <h2>当前选中供应商: <span>${proName }</span></h2>
            <div class="date">
                <ul>
                    <li><span>输入产品名称: </span><input id="goodName" value="${param.goodName }" type="text" placeholder="名称"/></li>
                    <li><span>输入产品条码: </span><input id="barCode" value="${param.barCode }" type="text" placeholder="条码"/></li>
                    <li>
                        <div class="btn_1">
                            <a onclick="queryInfo()" class="btn_33" href="javascript:;">搜 索</a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="form_1">
                <ul>
                    <li class="bg_green">
                        <span class="wid_1"> </span>
                        <span class="wid_2">编号</span>
                        <span class="wid_3">商品名称</span>
                        <span class="wid_4">商品条码</span>
                        <span class="wid_5">添加时间</span>
                    </li>
                    <c:if test="${associatedList.size() <= 0 }">
                    	<%@include file="/common/nodata.jsp"%>
	                </c:if>
	                <c:if test="${associatedList.size() > 0 }">
                    <c:forEach items="${associatedList }" var="goods" varStatus="status">
                    	<li class="bor_gray">
	                        <span class="wid_1"><input id="checkid${goods.id }" onclick="checkedGood('${goods.id }')" type="checkbox" value="${goods.id }"/></span>
	                        <span class="wid_2">${(currPage-1)*10+status.count}</span>
	                        <span class="wid_3">&nbsp;${goods.good_name }</span>
	                        <span class="wid_4">&nbsp;${goods.bar_code }</span>
	                        <span class="wid_5">
	                        	<fmt:formatDate value="${goods.created_time }" pattern="yyyy-MM-dd HH:mm:ss" type="date"/>
	                        </span>
	                    </li>
                    </c:forEach>
                    </div>
		            <div class="paging">
						<ul>
							<div class="page-location">
								<div id="provider-pager" class="ui-paging ui-paging1"></div>
							</div>
						</ul>
					</div>
		            <div class="btn_93">
		                <a onclick="associatedCancel()" class="btn_13" href="javascript:;">取消关联</a>
		            </div>
					</c:if>
                </ul>
        </div>
    </div>
</body>
