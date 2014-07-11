<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>云盘</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var cloudDataGrid;
$(function() {
	cloudDataGrid = $('#cloud_dataGrid').datagrid({
		url : '${pageContext.request.contextPath}/cloud/list.do',
		fit : true,
		fitColumns : true,
		border : false,
		idField : 'name',
		columns : [ [ {
			field : 'name',
			title : '编号',
			width : 100,
			formatter : function(value, row) {
				var str = '';
				str += '<img src="${pageContext.request.contextPath}/folder.png"/>';
				str += row.name;
				return str;
			}
		},{
			field : 'createDate',
			title : '创建时间',
			width : 100
		}] ],
		toolbar : [ {
			iconCls : 'ext-icon-add',
			text : '上传',
			handler : function() {
				addcloudFun('c');
			}
		},{
			iconCls : 'ext-icon-add',
			text : '创建文件夹',
			handler : function() {
				mkdir();
			}
		} ]
	});
});
function mkdir(){
	var pid=null;
	if(cloud_treeGrid.treegrid('getSelected')!=null){
		var id = cloud_treeGrid.treegrid('getSelected').departmentId;
		if(m=='p'){
			pid=id;
		}else if(m=='c'){
			var node = cloud_treeGrid.treegrid('getParent',id);
			if(node!=null){
				pid = cloud_treeGrid.treegrid('getParent',id).departmentId;
			}else{
				pid=null;
			}
		}
	}else{
		pid=null;
	}
	var dialog = parent.modalDialog({
		title : '创建文件夹',
		width : 300,
		height : 300,
		url : '${pageContext.request.contextPath}/cloud/mkdir.jsp',
		buttons : [ {
			text : '创建',
			handler : function() {
			    var mkdir = dialog.find('iframe').get(0).contentWindow;
			    mkdir.document.getElementById("mkdir_pid").value=pid;
			    mkdir.mkdir_submitForm(dialog, cloud_treeGrid, parent.$);
			}
		} ]
	});
}
</script>
</head>

<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false,title:'你的云盘'" style="overflow: hidden;">
		<table id="cloud_dataGrid"></table>
	</div>
</div>
<div id="cloud_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="userAdd()" iconCls="icon-add">增加</div>
<div onclick="userEdit()" iconCls="icon-edit">编辑</div>
<div onclick="userDelete()" iconCls="icon-remove">删除</div>
</div>
</body>
</html>