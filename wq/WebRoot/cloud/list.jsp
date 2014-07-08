<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>云盘</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var userDataGrid;
$(function() {
	userDataGrid = $('#user_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/user/list.do',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'userId',
		checkOnSelect:true,
		selectOnCheck:true,
		columns : [ [ {
			field : 'userId',
			title : '编号',
			width : 100,
			checkbox : true
		}, {
			field : 'username',
			title : '用户名',
			width : 100,
			sortable : true
		}, {
			field : 'email',
			title : '邮箱',
			width : 100,
			sortable : true
		}] ],
		toolbar : [ {
			text : '上传',
			iconCls : 'ext-icon-add',
			handler : function() {
				userAdd();
			}
		}, '-', {
			text : '新建文件夹',
			iconCls : 'ext-icon-pencil',
			handler : function() {
				mkdir();
			}
		}],
		onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#user_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
	
});
function mkdir(){
	
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'你的云盘'" style="overflow: hidden;">
		<table id="user_datagrid"></table>
	</div>
</div>

<div id="user_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="userAdd()" iconCls="icon-add">增加</div>
<div onclick="userEdit()" iconCls="icon-edit">编辑</div>
<div onclick="userDelete()" iconCls="icon-remove">删除</div>
</div>
</body>
</html>