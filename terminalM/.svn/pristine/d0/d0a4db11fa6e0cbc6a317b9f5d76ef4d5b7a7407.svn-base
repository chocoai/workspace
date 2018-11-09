<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"
	scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="g_syshead">
		<div class="m_baseheader m_basewrap">
			<h1 class="logo fl">
				<span class="img"><img src="../../images/terminalM/logo.png"
					alt="" /></span>
			</h1>
			<!-- 主菜单 S -->
			<div class="wql_g_menunav fl">
				<ul class="wql_ul clearfix  on">
					<c:forEach items="${modularList }" var="modular">
						<c:if test="${modular.parentId ==1 }">
							<li class="wql_li">
								<c:if test="${modular.isParent == 1 }">
									<a href="#">${modular.modularName }</a>
								</c:if> 
								<c:if test="${modular.isParent == 0 }">
									<a href="${ctx}/${modular.modularPath }">${modular.modularName }</a>
								</c:if>
								<c:if test="${not empty modular.modulars}">
								<div class="wql_g_submenu ">
									<div class="wql_submenu01">
										<i class="wql_icon wql_icon01"></i>
											<ul class="wql_subul">
												<c:forEach items="${modular.modulars }" var="modular2">
												<li class="wql_subli">
													<c:if test="${modular2.isParent == 1 }">
														<a href="#">${modular2.modularName }</a>
													</c:if> 
													<c:if test="${modular2.isParent == 0 }">
														<a href="${ctx}/${modular2.modularPath }">${modular2.modularName }</a>
													</c:if>
													<c:if test="${not empty modular2.modulars}">
													<div class="wql_submenu02">
															<ul class="wql_subul02" >
																<c:forEach items="${modular2.modulars }" var="modular3">
																<li class="wql_subli02">
																	<c:if test="${modular3.isParent == 1 }">
																		<a href="#">${modular3.modularName }</a>
																	</c:if> 
																	<c:if test="${modular3.isParent == 0 }">
																		<a href="${ctx}/${modular3.modularPath }">${modular3.modularName }</a>
																	</c:if>
																</li>
																</c:forEach>
															</ul>
													</div>
													</c:if>
												</li>
												</c:forEach>
											</ul>
									</div>
								</div>
								</c:if>
							</li>
						</c:if>
					</c:forEach>
					<li class="wql_li"><a href="${ctx}/manage/user/quit">登出</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>
