<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>创建文件夹</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">
var mkdir_submitForm = function(dialog, cloud_treeGrid, p) {
	if ($('mkdir_addForm').form('validate')) {
		$.post('${pageContext.request.contextPath}/cloud/mkdir.do', $("#mkdir_addForm").serialize(), function(j) {
			if (j.success) {
				cloud_treeGrid.treegrid('reload');
				dialog.dialog('destroy');
			}
			p.messager.show({
				title : '提示',
				msg : j.msg,
				timeout : 5000,
				showType : 'slide'
			});
		}, 'json');
	}
};
</script>
</head>

<body>
	<form id="mkdir_addForm" method="post">
		<input type="hidden" id="mkdir_pid" name="parentId" value="${mkdir.parentId}"/> 
		<table>
			<tr>
				<th>文件夹名称</th>
			</tr>
			<tr>
				<td><input name="mkdir" value="${mkdir.mkdirartmentname}" class="easyui-validatebox" data-options="required:true,missingMessage:'文件夹名称必填'" /></td>
			</tr>
		</table>
	</form>
</body>
</html>