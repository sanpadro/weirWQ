<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>云盘</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var cloud_treeGrid;
$(function() {
	cloud_treeGrid=$('#cloud_treeGrid').treegrid({
		url : '${pageContext.request.contextPath}/cloud/list.do',
		idField : 'id',
		treeField : 'name',
		parentField : 'pid',
		fit : true,
		fitColumns : false,
		border : false,
		columns : [ [ {
			title : '编号',
			field : 'id',
			width : 150,
			hidden : true
		}, {
			title : '菜单名称',
			field : 'name',
			width : 180,
			editor:{
				type:'validatebox',
				options:{
					required:true
				}
			}
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
		} ],
		onContextcloud : function(e, row) {
			e.preventDefault();
			$(this).treegrid('unselectAll');
			$(this).treegrid('select', row.id);
			$('#cloud_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
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
		<table id="cloud_treeGrid"></table>
	</div>
</div>

<div id="cloud_menu" class="easyui-menu" style="width: 120px;display: none;">
<div onclick="userAdd()" iconCls="icon-add">增加</div>
<div onclick="userEdit()" iconCls="icon-edit">编辑</div>
<div onclick="userDelete()" iconCls="icon-remove">删除</div>
</div>
</body>
</html>