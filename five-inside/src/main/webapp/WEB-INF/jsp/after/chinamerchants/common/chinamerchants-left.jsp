<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="message_left">
	<ul id="accordion" class="accordion">
		<li>
			<div class="link">
				条码管理<i class="fa fa-chevron-down">∨</i>
			</div>
			<ul class="submenu">
				<li><a onclick="pageGo('${ctx}/admin/after/good/toGoodPageList')" href="#">条码绑定</a></li>
			</ul>
		</li>
		<li>
			<div class="link">
				关系管理<i class="fa fa-chevron-down">∨</i>
			</div>
			<ul class="submenu">
				<li><a onclick="pageGo('${ctx}/admin/after/provider/toProviderAddPage')" href="#">厂商添加</a></li>
				<li><a onclick="pageGo('${ctx}/admin/after/provider/toProviderPageList')" href="#">厂家列表</a></li>
			</ul>
		</li>
		<li>
			<div class="link">
				商品管理<i class="fa fa-chevron-down">∨</i>
			</div>
			<ul class="submenu">
				<li><a onclick="pageGo('${ctx}/admin/after/good/toGoodAdd')" href="#">商品添加</a></li>
				<li><a onclick="pageGo('${ctx}/admin/after/good/toGoodPageList?pagetype=1')" href="#">商品列表</a></li>
			</ul>
		</li>
		<li><a class="link" href="${ctx}/route/to-relogin"> 重新登录 </a>
			<ul class="submenu"></ul></li>
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
			;
		}

		var accordion = new Accordion($('#accordion'), false);
	});
</script>