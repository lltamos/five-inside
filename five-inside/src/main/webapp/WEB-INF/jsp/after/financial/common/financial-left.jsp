<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="message_left">
	<ul id="accordion" class="accordion">
		<li>
			<div class="link">
				报表管理
				<i class="fa fa-chevron-down">∨</i>
			</div>
			<ul class="submenu">
				<li>
					<a onclick="pageGo('${ctx}/admin/after/financial/savesellstatement/findAll')" href="#">存销报表</a>
				</li>
				<li>
					<a onclick="pageGo('${ctx}/admin/after/financial/findstatements')" href="#">财务报表</a>
				</li>
				<li>
					<a onclick="pageGo('${ctx}/admin/after/financial/toupload')" href="#">上传报表</a>
				</li>
				<li>
					<a onclick="pageGo('${ctx}/admin/after/financial/summarymanage/findAll')" href="#">汇总管理</a>
				</li>
			</ul>
		</li>
		<li>
			<div class="link">
				审批管理
				<i class="fa fa-chevron-down">∨</i>
			</div>
			<ul class="submenu">
				<li>
					<a onclick="pageGo('${ctx}/admin/after/storageapply/findStorageApplys')" href="#">入库审批</a>
				</li>
				<li>
					<a onclick="pageGo('${ctx}/admin/after/financial/stockapproval/findAll')" href="#">盘存审批</a>
				</li>
			</ul>
		</li>
		<li>
			<a class="link" href="${ctx }/route/to-relogin"> 重新登录 </a>
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
			;
		}

		var accordion = new Accordion($('#accordion'), false);
	});
</script>
</body>
