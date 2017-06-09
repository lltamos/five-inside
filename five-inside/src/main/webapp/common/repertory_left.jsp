<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/base.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/baodan_15.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap-table.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="${ctx }/js/mypager.js"></script>
</head>
<div class="among">
	<div class="message_left" onclick="click(this)">
		<ul id="accordion" class="accordion">
			<li>
				<div class="link">
					库存管理
					<i class="fa fa-chevron-down">∨</i>
				</div>
				<ul class="submenu">
					<li>
						<a href="${pageContext.request.contextPath }/admin/after/inventory/toInsertProPager">商品入库</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/admin/after/inventory/inventoryquery?rows=10">库存查询</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/admin/after/inventory/inventoryrecord?rows=10">库存记录</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath }/admin/after/inventory/storagerecord?rows=10">入库记录</a>
					</li>
				</ul>
			</li>
			<li>
				<div class="link">
					条码管理
					<i class="fa fa-chevron-down">∨</i>
				</div>
				<ul class="submenu">
					<li>
						<a href="#">条码绑定</a>
					</li>
				</ul>
			</li>
			<li>
				<div class="link">
					盘存管理
					<i class="fa fa-chevron-down">∨</i>
				</div>
				<ul class="submenu">
					<li>
						<a href="#">盘存管理</a>
					</li>
					<li>
						<a href="#">盘存记录</a>
					</li>
					<li>
						<a href="#">申请记录</a>
					</li>
				</ul>
			</li>

			<li>
				<div class="link">
					报表管理
					<i class="fa fa-chevron-down">∨</i>
				</div>
				<ul class="submenu">
					<li>
						<a href="#">存销报表</a>
					</li>
				</ul>
			</li>
			<li>
				<a class="link" href="javascript:;"> 重新登录 </a>
				<ul class="submenu"></ul>
			</li>
		</ul>
	</div>
	<script type="text/javascript">
		$(function() {

			var Accordion = function(el, multiple) {
				this.el = el || {};
				this.multiple = multiple || false;

				// Variables privadas
				var links = this.el.find('.link');
				// Evento
				links.on('click', {
					el : this.el,
					multiple : this.multiple
				}, this.dropdown)
			}

			Accordion.prototype.dropdown = function(e) {
				var $el = e.data.el;
				$this = $(this), $next = $this.next();
				$next.slideToggle(120);
				$this.parent().toggleClass('open');

				if (!e.data.multiple) {
					$el.find('.submenu').not($next).slideUp(120).parent()
							.removeClass('open');
				}

			}

			var accordion = new Accordion($('#accordion'), true);
		});
	</script>