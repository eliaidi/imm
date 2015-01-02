<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="CommonPage.jsp"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title><s:text name="COMMON_TITLE"></s:text>-客户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
			var dict = <%=CommonUtil.JSONString(CommonUtil.getDict())%>;
	</script>
  </head>
  
  <body>
    <table id="dg" class="easyui-datagrid"
            data-options="pagination:true,fit:true,rownumbers:true,border:false,singleSelect:true,striped:true,
            url:'${pageContext.request.contextPath}/main/queryRssDataGridByJson.do',method:'post',toolbar:'#dg-toolbar'">
        <thead>
            <tr>
                <th data-options="field:'title',width:100,align:'center'">标题</th>
                <th data-options="field:'url',width:500,align:'center'" formatter="getSexName">URL</th>
                <th data-options="field:'description',width:100,align:'center'">备注</th>
                <th data-options="field:'order_num',width:100,align:'center'">排序</th>
            </tr>
        </thead>
    </table>
    <div id="dg-toolbar" style="padding:5px;height:auto">
    	<div>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="operation('add','${pageContext.request.contextPath}/main/doAddRss.do')"><s:text name="BUTTON.ADD"></s:text></a>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="operation('edit','${pageContext.request.contextPath}/main/doUpdateRss.do')"><s:text name="BUTTON.EDIT"></s:text></a>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'" onclick="operation('del','${pageContext.request.contextPath}/main/doDeleteRss.do')"><s:text name="BUTTON.DEL"></s:text></a>
        </div>
        <div>
                                标题:<input type="text" id="query_titile" name="form.name" style="width:100px">
        	URL:<input type="text" id="query_url" name="form.property_name" style="width:100px">
           	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" onclick="query()"><s:text name="BUTTON.SEARCH"></s:text></a>
           	<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="resetQuery()"><s:text name="BUTTON.RESET"></s:text></a>
        </div>
    </div>
    
    
    <div id="dlg" class="easyui-dialog" style="padding:10px" 
    	data-options="width:650,height:250, iconCls:'icon-base', buttons: '#dlg-buttons', modal:true, closed:true, closable:true">  
	    <form id="dlgFm" action="" method="post">
	    	<input id="dlg_id" type="hidden" name="rss.id" value=""></input>
	    	<input id="dlg_user_id" type="hidden" name="rss.user_id" value=""></input>
            <fieldset>
	    		<legend>RSS信息</legend>
	            <table style="border: 1">
	           	  	<tr>  
		                <td align="right">标题:</td>  
		                <td align="left">
		                	<input id="dlg_title" type="text" maxlength="256" style="width:100px;" name="rss.title" ></input>
		                </td>  
	                    <td align="right">排序:</td>  
		                <td align="left">
		                	<input id="dlg_order_num" type="text" style="width:100px;" name="rss.order_num"></input>
		                </td>  
		                <td align="right">描述:</td>  
		                <td align="left">
		                	<input id="dlg_description" type="text" maxlength="256" style="width:100px;" name="rss.description" ></input>
		                </td>  
		            </tr>  
		            <tr>  
		            	<td align="right" >URL:</td>  
		                <td align="left"colspan="5">
		                	<input id="dlg_url" type="text" style="width:500px;" name="rss.url"></input>
		                </td>
		            </tr> 
	          	</table>
	       </fieldset>
	    </form>
	</div>  
	<div id="dlg-buttons">  
   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'"  onclick="save()"><s:text name="BUTTON.SAVE"></s:text></a>  
   		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'"  onclick="javascript:$('#dlg').dialog('close')"><s:text name="BUTTON.CANCEL"></s:text></a>  
 	</div>  
 	
  </body>
  <script type="text/javascript">
  	/**
	 * 查询
	 */
	function query() {
		$("#dg").datagrid({
			pageNumber : 1,
			queryParams : {
				"form.title" : $("#query_title").val(),
				"form.url" : $("#query_url").val()
			}
		});
	}
	
	var url;
	function operation(type,url) {
		this.url = url;
		clearForm("dlgFm");
		switch(type) {
		case "add" :
			openDialog("dlg","新增");
			break;
		case "edit" :
			var row = $("#dg").datagrid("getSelected");
			if(row) {
				loadForm(row);
				openDialog("dlg","修改");
			} else {
				alertMessageByCode("unSelect");
			}
			break;
		case "del" :
			var row = $("#dg").datagrid("getSelected");
			if(row) {
				$.messager.confirm("确认框","你确定要删除该行数据吗?", function(r) {
					if(r) {
						$.post(
							url,
							{"customer.id":row.id},
							function(code) {
								alertMessageByCode(code);
								reloadDataGrid("dg");
							},"json"
						); 
					}
				});
			} else {
				alertMessageByCode("unSelect");
			}
			break;
		}
	}
	
	/**
	 * 保存表单
	 */
	function save() {
		$("#dlgFm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(code) {
				alertMessageByCode($.parseJSON(code));
				reloadDataGrid("dg");
				$("#dlg").dialog("close");
			}
		});
	}
	
	/**
	 * 为表单加载数据
	 * @param row
	 */
	function loadForm(row) {
		$.post(
			"${pageContext.request.contextPath}/main/queryRssByJson.do",
			{"form.id":row.id},
			function(data){
				if(data != null) {
					$("#dlg_id").val(data.id);
					$("#dlg_title").val(data.title);
					$("#dlg_url").val(data.url);
					$("#dlg_description").val(data.description);
					$("#dlg_order_num").val(data.order_num);
					$("#dlg_user_id").val(data.user_id);
				}
			},"json"
		);
	}
	
  </script>
</html>
