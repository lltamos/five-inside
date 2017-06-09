<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>

<title>登录</title>
<script src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
<style>
.shouji {
	margin-top: 10px;
	height: 36px;
	border-radius: 5px;
	border: 1px solid #ccc;
	margin-bottom: 20px;
}
</style>
</head>
<body id="particles-js" style="background: url(${pageContext.request.contextPath }/img/bj_smr.jpg);">

	<script src="${pageContext.request.contextPath }/js/particles.min.js"></script>
	<script type="text/javascript">
		particlesJS(
				'particles-js',
				{
					"particles" : {
						"number" : {
							"value" : 110,
							"density" : {
								"enable" : true,
								"value_area" : 800
							}
						},
						"color" : {
							"value" : "#ffffff"
						},
						"shape" : {
							"type" : "circle",
							"stroke" : {
								"width" : 0,
								"color" : "#000000"
							},
							"polygon" : {
								"nb_sides" : 5
							},
							"image" : {
								"src" : "${pageContext.request.contextPath }/img/github.svg",
								"width" : 100,
								"height" : 100
							}
						},
						"opacity" : {
							"value" : 0.5,
							"random" : false,
							"anim" : {
								"enable" : false,
								"speed" : 1,
								"opacity_min" : 0.1,
								"sync" : false
							}
						},
						"size" : {
							"value" : 1,
							"random" : true,
							"anim" : {
								"enable" : false,
								"speed" : 20,
								"size_min" : 0.1,
								"sync" : false
							}
						},
						"line_linked" : {
							"enable" : true,
							"distance" : 40,
							"color" : "#b6ff00",
							"opacity" : 1,
							"width" : 1
						},
						"move" : {
							"enable" : true,
							"speed" : 3,
							"direction" : "none",
							"random" : false,
							"straight" : false,
							"out_mode" : "out",
							"attract" : {
								"enable" : false,
								"rotateX" : 600,
								"rotateY" : 1200
							}
						}
					},
					"interactivity" : {
						"detect_on" : "canvas",
						"events" : {
							"onhover" : {
								"enable" : true,
								"mode" : "grab"
							},
							"onclick" : {
								"enable" : true,
								"mode" : "push"
							},
							"resize" : true
						},
						"modes" : {
							"grab" : {
								"distance" : 120,
								"line_linked" : {
									"opacity" : 1
								}
							},
							"bubble" : {
								"distance" : 400,
								"size" : 40,
								"duration" : 2,
								"opacity" : 8,
								"speed" : 3
							},
							"repulse" : {
								"distance" : 300
							},
							"push" : {
								"particles_nb" : 4
							},
							"remove" : {
								"particles_nb" : 2
							}
						}
					},
					"retina_detect" : true,
					"config_demo" : {
						"hide_card" : false,
						"background_color" : "#b61924",
						"background_image" : "",
						"background_position" : "50% 50%",
						"background_repeat" : "no-repeat",
						"background_size" : "cover"
					}
				});
	</script>
	<script type="text/javascript">
		document.body.style.backgroundImage = "url(${pageContext.request.contextPath }/img/bj_smr.jpg)";
	</script>

	<div style="float: left; margin-top: 10%; width: 350px; height: 330px; margin-left: 37%; margin-top: -40%; position: absolute; z-index: 1000; background-color: white; border-radius: 5px;">
		<form id="loginForm" action="${pageContext.request.contextPath }/route/login" method="post">
			<div style="margin-left: 30px; margin-top: 40px; font-size: 15px; color: #555;">
				<label style="font-family: Arial, Helvetica, sans-serif; font-weight: bold;">账号:</label>

			</div>
			<div style="margin-left: 30px; font-size: 15px; color: #555;">

				<input name="name" class="shouji" type="text" id="userAccount" class="form-control" style="width: 300px" placeholder="请输入帐号">
			</div>
			<div style="margin-left: 30px; font-size: 15px; color: #555; font-family: Arial, Helvetica, sans-serif;">
				<label style="font-family: Arial, Helvetica, sans-serif; font-weight: bold;">密码:</label>

			</div>
			<div style="margin-left: 30px; font-size: 15px; color: #555; font-family: Arial, Helvetica, sans-serif;">

				<input name="pass" class="shouji" type="password" style="width: 300px" placeholder="密码" data-form-pw="1493972483222.2932">
			</div>
			<div style="margin-left: 210px; font-size: 12px; color: red; font-family: Arial, Helvetica, sans-serif;">

				<span>${error }</span>
			</div>

		</form>
		<div style="float: left; margin-left: 120px; margin-top: 30px;">
			<button onclick="submit()" style="color: #fff; width: 120px; border: 0px; height: 40px; background-color: rgb(69, 147, 253); border-radius: 5px; pointer-events: auto;">登录</button>
		</div>
	</div>
</body>
<script type="text/javascript">
	function submit() {
		var name = $('input[name="name"]').val();
		var pass = $('input[name="pass"]').val();

		if (name != null && name.trim() != "") {
			if (pass != null && pass.trim() != "") {

				$("#loginForm").submit();
				return false;
			}

		}

		alert("帐号密码不能为空");

	}
</script>
</html>