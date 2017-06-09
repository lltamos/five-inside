<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <div class="message_left">
     <ul id="accordion" class="accordion">
         <li>
             <div class="link">退货管理<i class="fa fa-chevron-down">∨</i></div>
             <ul class="submenu">
                 <li><a href="#" onclick="pageGo('${ctx}/admin/after/aftersales/salesreturn/findGoodsReturn?remark=1')">商品退货</a></li>
                 <li><a href="#" onclick="pageGo('${ctx}/admin/after/aftersales/salesreturn/findAllReturnRecord')">退货记录</a></li>
             </ul>
         </li>
         <li>
             <div class="link">订单查询<i class="fa fa-chevron-down">∨</i></div>
             <ul class="submenu">
                 <li><a href="#" onclick="pageGo('${ctx}/admin/after/aftersales/shoporder/findAllXYTOrder?remark=1')">新业态</a></li>
                 <li><a href="#" onclick="pageGo('${ctx}/admin/after/aftersales/shoporder/findAllBDOrder?remark=1')">本地电商</a></li>
             </ul>
         </li>
         <li>
             <a class="link" href="${ctx}/route/to-relogin">
                 重新登录
             </a>
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
             links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
         }

         Accordion.prototype.dropdown = function(e) {
             var $el = e.data.el;
             $this = $(this),
                     $next = $this.next();

             $next.slideToggle(120);
             $this.parent().toggleClass('open');

             if (!e.data.multiple) {
                 $el.find('.submenu').not($next).slideUp(120).parent().removeClass('open');
             };
         }

         var accordion = new Accordion($('#accordion'), false);
     });
 </script>
