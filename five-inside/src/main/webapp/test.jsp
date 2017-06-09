<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Insert title here</title>
<script src="${ctx}/js/jquery-1.11.3.js"></script>
</head>
<body>
	<script type="text/javascript">
		var app = {

			contant : 100,
			test1 : function() {

				alert(this.contant);
			},
			test2 : function(n) {
				alert("传入" + n);
			},

			test4 : function(json) {
				alert(json.data);
				json.func();
			}

		}

		function btn() {

			app.test1();
			app.test2(2);
			app.test4({
				data : 11,
				func : function() {
					alert("func");
				}
			});

		}
	</script>




	<button onclick="btn()">button</button>
</body>
</html>