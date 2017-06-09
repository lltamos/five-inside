<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/commonPublic.jsp"%>
<head lang="en">
<link rel="stylesheet" href="${ctx }/css/base.css" />
<link rel="stylesheet" href="${ctx }/css/Since_13.css" />
<link rel="stylesheet" href="${ctx }/js/jqueryjedate/jedate.css" />
<script type="text/javascript" src="${ctx}/js/jqueryjedate/jquery.jedate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ocupload-1.1.2.js"></script>
</head>
<body>
	<div class="message_right">
		<input id="idarea" type="hidden" value="-1" />
		<div class="proving">
			<div class="date">
				<ul>
					<li class="datep">
						<span>开始日期: </span>
						<input value="${list.filter.start }" class="datainp wicon" type="text" name="start" id="begin_time" readonly>
					</li>
					<li class="datep">
						<span>结束日期: </span>
						<input value="${list.filter.end }" class="datainp wicon" type="text" name="end" id="end_time" readonly>
					</li>

					<li>
						<span>Excel文件:</span>
						<span id="area"></span>
						<input type="button" style="width: 60px;height: 25px" id="filename" value="选择文件" />
					</li>
				</ul>

				<div class="btn_1">

					<Button style="cursor:pointer" class="btn_33" id="submit" onclick="submit()">提交</Button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#filename").upload({
				name : 'file',
				action : '${ctx}/admin/after/financial/uploadsheet',
				enctype : 'multipart/form-data',
				onSelect : function() {
					this.autoSubmit = false;
					var regex = /^.*\.(?:xls|xlsx)$/i;
					if (regex.test($("[name = '" + this.name() + "']").val())) {
						this.submit();
						$("#submit").attr('disabled', 'disabled');
					} else {
						alert("文件格式不正确!");
					}
				},
				onComplete : function(response) {

					var data = JSON.parse(response);
					if (data.code == 1) {
						var s = data.msg;
						var arr = s.split(",");

						$("#idarea").val(arr[0]);
						$("#area").text(arr[1]);
					} else {
						alert("文件上传服务器失败！");
					}

					$('#submit').removeAttr('disabled');
				}

			});
			$("#begin_time").jeDate({
				isinitVal : false,
				festival : true,
				ishmsVal : false,
				minDate: '2000-01-01 23:59:59',
				maxDate : $.nowDate(0),
				format : "YYYY.MM.DD",
				zIndex : 3000,
			})
			$("#end_time").jeDate({
				isinitVal : false,
				festival : true,
				ishmsVal : false,
				minDate: '2000-01-01 23:59:59',
				maxDate : $.nowDate(0),
				format : "YYYY.MM.DD",
				zIndex : 3000,
			})

		});

		function submit() {
			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			var idarea = $("#idarea").val();

			var data = {
				startTime : start,
				endTime : end,
				id : idarea
			};

			var oldTime = (new Date(start)).getTime();
			var newTime = (new Date(end)).getTime();

			if (start.trim().length < 1 || end.trim().length < 1) {
				alert("请选择时间范围！");
				return false;
			}

			if (newTime < oldTime) {
				alert("时间范围不正确！");
				return false;
			}

			if ($("#idarea").val() == -1) {
				alert("为选择文件！");
				return false;
			}
			$.post("${ctx}/admin/after/financial/addsheet", data,
					function(data) {

						var dats = JSON.parse(data);
						if (dats.code == 1) {
							alert(dats.msg);
						} else {
							alert(dats.msg);
						}
						$("#idarea").val(-1);
						$("input[name='start']").val("");
						$("input[name='end']").val("");
						$("#area").text("");
					});

		}
	</script>
</body>
