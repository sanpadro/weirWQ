<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>云盘</title>
<meta name="content-type" content="text/html; charset=UTF-8">
<jsp:include page="/pub.jsp"></jsp:include>
<script type="text/javascript">

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
<c:forEach items="${hdfs}" var="entry">
<div>
<img alt="" src="${pageContext.request.contextPath}/folder.png">
${entry.name}
</div>
</c:forEach>
</body>
</html>