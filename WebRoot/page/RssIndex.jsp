<%@page import="com.mpgl.util.CommonUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String root = request.getContextPath();
session.setAttribute("rootPath",root);
session.setAttribute("root",root);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<base target="_block">
		<title><s:text name="COMMON_TITLE"></s:text></title>
		<link rel="shortcut icon"  type="image/x-icon" href="<%=root%>/images/smalllogo.png"  media="screen" >
		<link rel="stylesheet" type="text/css" href="<%=root%>/bootstrap/bootstrap.css" />
		<script type="text/javascript" src="<%=root%>/easyui/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="<%=root%>/bootstrap/bootstrap.js"></script>
	</head>
  <body>
  	<div class="navbar navbar-inverse">
	  <div class="navbar-inner">
	    <a class="brand" href="#"><s:text name="COMMON_TITLE"></s:text></a>
	    <ul class="nav">
	      <li class="active"><a href="#">首页</a></li>
	      <li><a href="<%=root%>/main/goLogin.do">后台管理</a></li>
	    </ul>
	  </div>
	</div>
	
	<div class="container">
		<div class="accordion" id="accordion2">
			<s:iterator value="#request.rssList" id="fatherRss" status="st">
				<div class="accordion-group">
					<div class="accordion-heading">
				      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse<s:property value="#st.index"/>">
				      	<span class="label label-info"><s:property value="title"/></span>
				      </a>
				    </div>
				    <div id="collapse<s:property value="#st.index"/>" class="accordion-body collapse in">
				      <div class="accordion-inner">
				       	<ul>
				       	<s:iterator value="#fatherRss.rss_rows" id="childRss" status="tt">
				       		<s:if test="#tt.index <= 8">
				       			<li>
									<a href="<s:property value="node_link"/>"><s:property value="node_title"/></a>
								</li>
				       		</s:if>
				       	</s:iterator>
						</ul>
						<a href='<s:property value="#fatherRss.rss_link"/>'><span class="label label-important">More...</span></a>
				      </div>
				    </div>
				</div>
			</s:iterator>
		</div>
	</div>
  </body>
</html>
