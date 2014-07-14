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
		idField : 'id',
		columns : [ [ {
			field : 'name',
			title : '编号',
			width : 100
		},{
			field : 'date',
			title : '创建时间',
			width : 100
		},{
			field : 'type',
			title : '类型',
			width : 100,
			formatter : function(value, row) {
				var str = '';
				if(row.type=='D'){
					str += '文件夹';
				}else if(row.type=='F'){
					str += '文件';
				}
				return str;
			}
		},{
			field : 'dir',
			title : '目录',
			width : 100
		},{
			field : 'pdir',
			title : '上级目录',
			width : 100
		}] ],
		toolbar : [ {
			iconCls : 'ext-icon-add',
			text : '上传',
			handler : function() {
				upload();
			}
		},{
			iconCls : 'ext-icon-add',
			text : '创建文件夹',
			handler : function() {
				mkdir();
			}
		} ],
		onRowContextMenu:function(e, rowIndex, rowData){
			e.preventDefault();
			$(this).datagrid('unselectAll');
			$(this).datagrid('selectRow',rowIndex);
			$('#cloud_menu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
	});
});

function upload(){
	var name = null;
	if(cloudDataGrid.datagrid('getSelected')!=null){
		name = cloudDataGrid.datagrid('getSelected').dir;
	}
	if(name==null){
		name = "root";
	}
	//console.info(name);
	var dialog = parent.modalDialog({
		title : '上传文件',
		width : 550,
		height : 460,
		url : '${pageContext.request.contextPath}/cloud/upload11.jsp?dir='+name
	});
	/* Uploader(chunk,dir,function(files){
		 if(files && files.length>0){
			 //$("#res").text("成功上传："+files.join(","));
		 }
	 }); */
}
function mkdir(){
	var name = null;
	//console.info(cloudDataGrid.datagrid('getSelected'));
	if(cloudDataGrid.datagrid('getSelected')!=null){
		name = cloudDataGrid.datagrid('getSelected').dir;
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
			    mkdir.document.getElementById("mkdir_dir").value=name;
			    mkdir.mkdir_submitForm(dialog, cloudDataGrid, parent.$);
			}
		} ]
	});
}
</script>
</head>

<body>
<input type="hidden" id="tree" value="${tree}"/>
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