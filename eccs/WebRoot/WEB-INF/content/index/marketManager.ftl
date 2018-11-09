<script type="text/javascript" src="/js/jquery_cmhello.js"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="javascript:window.location.reload();">经营管理</a>
</div>

<div id="content">
	<div id="navmenu">
		<ul class="menu">
			

			<li id="proinfor">
				<a href="/information/list.htm" class="colour_severn"><img src="/images/b1.png" />项目信息</a>
			</li>

			<li id="bidid">
				<a href="#" class="colour_two"><img src="/images/c10.png" />投标管理</a>
				<ul class="sub-menu">
					<!-- <li id="personid">
						<a href="/person/list.htm" class="colour_three2"><img src="/images/b1.png" />人员分配表</a>
					</li> -->

					<#if myResMap['002002'] != null>
					<li>
						<a href="/bidPlanning.htm" class="colour_six2"><img src="/images/b2.png" />投标策划</a>
					</li>
					<#else>
					<li>
						<a class="colour_gray2"><img src="/images/b2.png" />投标策划</a>
					</li>
					</#if>
                    <!--
					<#if myResMap['002003'] != null>
					<li>
						<a href="/bid/listSummy.htm" class="colour_fore2"><img src="/images/b3.png" />投标总结</a>
					</li>
					<#else>
					<li>
						<a class="colour_gray2"><img src="/images/b3.png" />投标总结</a>
					</li>
					</#if>
                    -->
				</ul>
			</li>

            <#if myResMap['002001'] != null>
            <li>
                <a href="/project/list.htm" class="colour_one"><img src="/images/b1.png" />项目立项</a>
            </li>
            <#else>
            <li>
                <a class="colour_gray"><img src="/images/b1.png" />项目立项</a>
            </li>
            </#if>

			<li id="contractid">
				<a href="#" class="colour_five"><img src="/images/b4.png" />合同管理</a>
				<ul class="sub-menu">
					<#if myResMap['002005'] != null>
					<li>
						<a href="/review/list.htm" class="colour_fore2"><img src="/images/b5.png" />合同评审</a>
					</li>
					<#else>
					<li>
						<a class="colour_gray2"><img src="/images/b5.png" />合同评审</a>
					</li>
					</#if> 
					
					<#if myResMap['002004'] != null>
					<li>
						<a href="/contract/list.htm" class="colour_three2"><img src="/images/b2.png" />合同统计</a>
					</li>
					<#else>
					<li>
						<a class="colour_gray2"><img src="/images/b2.png" />合同统计</a>
					</li>
					</#if>
				</ul>
			</li>
			
			<li id="customerid">
				<a class="colour_severn"><img src="/images/h5.png" />客户信息</a>
				<ul class="sub-menu">
					<li>
						<a href="/t_customer/list.htm" class="colour_fore2"><img src="/images/h5.png" />客户登记</a>
					</li>
					<li>
						<a href="/t_customer/squery.htm" class="colour_six2"><img src="/images/h6.png" />客户台账</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>