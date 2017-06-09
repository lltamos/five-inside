<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%><head lang="en">
<head lang="en">
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${ctx }/css/base.css" />
<link rel="stylesheet" href="${ctx }/css/Since_9.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/js/webuploader/webuploader.css">
<script src="${ctx }/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript" src="${ctx }/js/webuploader/webuploader.js"></script>
</head>
<style>
#imgDiv img {
	width: 200px;
	height: 200px;
}

#thumb {
	margin-left: 10px;
}

#uploadFile {
	margin-left: 10px;
}
</style>
<script type="text/javascript">
	var editor = null;

	//图片上传demo
	var aids = "";
	var veteran = "";
	jQuery(function() {
		var $ = jQuery, $list = $('#imgDiv'),
		// 优化retina, 在retina下这个值是2
		ratio = window.devicePixelRatio || 1,
		// 缩略图大小
		thumbnailWidth = 100 * ratio, thumbnailHeight = 100 * ratio,
		// Web Uploader实例
		uploader;
		//初始化Web Uploader
		uploader = WebUploader.create({
			// 选完文件后，是否自动上传。
			auto : true,
			// swf文件路径
			swf : '${ctx}/js/jqueryui/Uploader.swf',
			// 文件接收服务端。
			server : '${ctx}/admin/after/good/uploadGoodImage',
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick : '#uploadFile',
			// 只允许选择图片文件。
			accept : {
				title : 'Images',
				extensions : 'gif,jpg,jpeg,bmp,png',
				mimeTypes : 'image/jpg,image/jpeg,image/png'
			}
		});
		// 当有文件添加进来的时候
		uploader.on('fileQueued', function(file) {
			// 创建缩略图
			//uploader.makeThumb(file, function(error, src) {
			//$("#thumb").attr("src", src);
			//}, thumbnailWidth, thumbnailHeight);
		});
		// 文件上传过程中创建进度条实时显示。
		/*  uploader.on( 'uploadProgress', function( file, percentage ) {
		     var $li = $( '#'+file.id ),
		         $percent = $li.find('.progress span');
		     // 避免重复创建
		     if ( !$percent.length ) {
		         $percent = $('<p class="progress"><span></span></p>')
		                 .appendTo( $li )
		                 .find('span');
		     }
		
		     $percent.css( 'width', percentage * 100 + '%' );
		 }); */
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on('uploadSuccess', function(file, data, response) {
			var aid = data.id;
			aids = aid;
			//$("#imgDiv").empty();
			$("#imgDiv").html(
					' <img src=" '+ "${img_service}"+data.address +' "/> ');
			$("#attachmentAddress").val(data.address);
		});
		// 文件上传失败，现实上传出错。
		uploader.on('uploadError', function(file) {
			var $li = $('#' + file.id), $error = $li.find('div.error');
			// 避免重复创建
			if (!$error.length) {
				$error = $('<div class="error"></div>').appendTo($li);
			}
			$error.text('上传失败');
		});
		/* // 完成上传完了，成功或者失败，先删除进度条。
		uploader.on('uploadComplete', function(file) {
			 $( '#'+file.id ).find('.progress').remove();
		});
		 */
	});

	function check(){
		var goodMoney = $("input[name='GoodMoney']").val();
		var buyPrice = $("input[name='BuyPrice']").val();
		var KeyWord = $("input[name='KeyWord']").val();
		var GoodName = $("input[name='GoodName']").val();
		var attachmentAddress = $("#attachmentAddress").val();
		if(GoodName == null || GoodName ==''){
			alert("商品名称不能为空")
			return false;
		}
		if(goodMoney == null || goodMoney ==''){
			alert("商品价格不能为空")
			return false;
		}
		var conreg = /^\d+(\.\d{1,2})?$/;
		if(!conreg.test(goodMoney)){
			alert("商品价格只可以为数字,允许包含小数点后两位");
			return false;
		}
		if(buyPrice == null || buyPrice ==''){
			alert("进货价不能为空")
			return false;
		}
		if(!conreg.test(buyPrice)){
			alert("进货价只可以为数字,允许包含小数点后两位");
			return false;
		}
		if (parseFloat(goodMoney) < parseFloat(buyPrice)) {
			alert("进货价格不可大于商品价格");
			return false;
		}
		if (parseFloat(goodMoney) <= 0) {
			alert("商品价格不能小于等于0");
			return false;
		}
		if (parseFloat(buyPrice) <= 0) {
			alert("进货价格不能小于等于0");
			return false;
		}
		if(KeyWord == null || KeyWord ==''){
			alert("关键词不能为空")
			return false;
		}
		if(attachmentAddress == null || attachmentAddress ==''){
			alert("图片不能为空")
			return false;
		}
		var detail = $("#detailHtml");
		detail.val(document.getElementById("ckIframe").contentWindow.editor
				.getData());
		if(detail.val() == null || detail.val() ==''){
			alert("商品详情不能为空");
			return false;
		}
		return true;
	}
	function goodSave() {
		var form = $("#goodForm");
		var options = {
			url : '${ctx}/admin/after/good/goodAdd',
			type : 'POST',
			cache : false,
			success : function(data) {
				var sc = eval('(' + data + ')');
				if (sc.code == 1) {
					alert(sc.msg);
					pageGo("${ctx }/admin/after/good/toGoodPageList?&pagetype=1");
				} else {
					alert(sc.msg);
				}
			}
		};
		form.ajaxSubmit(options);
	}
</script>
<body>
	<div class="message_right">
		<div class="proving">
			<form method="post" id="goodForm" enctype="multipart/form-data" onsubmit="return false">
				<ul>
					<li>
						<span>商品名称: </span>
						<input name="GoodName" type="text" placeholder="商品名称" />
						<input type="hidden" name="attachmentAddress" id="attachmentAddress" />
						<input type="hidden" name="Details" id="detailHtml" />
					</li>
					<li>
						<span>商品价格: </span>
						<input name="GoodMoney" type="text" placeholder="商品价格" maxlength="15"/>
					</li>
					<li>
						<span>进货价: </span>
						<input name="BuyPrice" type="text" placeholder="进货价" maxlength="15"/>
					</li>
					<li>
						<span>关键词: </span>
						<input name="KeyWord" type="text" placeholder="关键词" />
					</li>
					<li>
						<span>商品分类: </span>
						<select name="GoodCate" id="" style="width: 240px; height: 28px;">
							<%-- <option value="1">推荐产品</option>
	                       <option value="2">套餐产品</option>
	                       <option value="3">家具产品</option>
	                       <option value="4">食品饮料</option>
	                       <option value="5">美容日用</option>
	                       <option value="6">其它专属</option> --%>
							<option value="7">公司直发</option>
							<option value="8">厂家直邮</option>
							<option value="9">上门自提</option>
							<option value="10">套餐商品</option>
						</select>
					</li>
					<li>
						<span>商品封面: </span>
						<div id="imgDiv"></div>
						<div id="uploadFile">添加图片</div>
					</li>
					<li>
						<span>商品详情: </span>
						<iframe id="ckIframe" width="800px" height="400px" src="${ctx}/admin/after/good/good-ckeditor" scrolling="no" frameborder="0"> </iframe>
					</li>
					<li class="btn_9">
						<button id="btnUpload" onclick="if(check()){goodSave()}" style="cursor: pointer;">保存</button>
					</li>
				</ul>
				 <input id="token" type="hidden" name="token" value="${token}">
			</form>
		</div>
	</div>
</body>
