<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>云盘首页</title>
<!-- basic styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
<!-- ace styles -->

<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />


<link rel="stylesheet" href="css/buttons.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<script src="assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
		<script src="assets/js/html5shiv.js"></script>
		<script src="assets/js/respond.min.js"></script>
		<![endif]-->
<!-- basic scripts -->

<!--[if !IE]> -->

<script src="js/jquery-2.1.1.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="js/jquery-1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
			window.jQuery || document.write("<script src='js/jquery-2.1.1.min.js'>"+"<"+"script>");
		</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='js/jquery-1.11.1.min.js'>"+"<"+"script>");
</script>
<![endif]-->
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/easyui1.3.6/jquery.easyui.min.js"></script>

<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
<script src="assets/js/jquery-ui-1.10.3.full.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="js/layer/layer.min.js"></script>

</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<a href="#" class="navbar-brand"> <small> <i class="icon-leaf"></i> 云盘
				</small>
				</a>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="grey"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="icon-tasks"></i> <span class="badge badge-grey">4</span>
					</a>

						<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-ok"></i> 还有4个任务完成</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">软件更新</span> <span class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:65%" class="progress-bar "></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">硬件更新</span> <span class="pull-right">35%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:35%" class="progress-bar progress-bar-danger"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">单元测试</span> <span class="pull-right">15%</span>
									</div>

									<div class="progress progress-mini ">
										<div style="width:15%" class="progress-bar progress-bar-warning"></div>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">错误修复</span> <span class="pull-right">90%</span>
									</div>

									<div class="progress progress-mini progress-striped active">
										<div style="width:90%" class="progress-bar progress-bar-success"></div>
									</div>
							</a></li>

							<li><a href="#"> 查看任务详情 <i class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="purple"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="icon-bell-alt icon-animated-bell"></i> <span class="badge badge-important">8</span>
					</a>

						<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-warning-sign"></i> 8条通知</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i class="btn btn-xs no-hover btn-pink icon-comment"></i> 新闻评论
										</span> <span class="pull-right badge badge-info">+12</span>
									</div>
							</a></li>

							<li><a href="#"> <i class="btn btn-xs btn-primary icon-user"></i> 切换为编辑登录..
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i> 新订单
										</span> <span class="pull-right badge badge-success">+8</span>
									</div>
							</a></li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left"> <i class="btn btn-xs no-hover btn-info icon-twitter"></i> 粉丝
										</span> <span class="pull-right badge badge-info">+11</span>
									</div>
							</a></li>

							<li><a href="#"> 查看所有通知 <i class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="green"><a data-toggle="dropdown" class="dropdown-toggle" href="#"> <i class="icon-envelope icon-animated-vertical"></i> <span class="badge badge-success">5</span>
					</a>

						<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="icon-envelope-alt"></i> 5条消息</li>

							<li><a href="#"> <img src="assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span class="blue">Alex:</span> 不知道写啥 ...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>1分钟以前</span>
									</span>
								</span>
							</a></li>

							<li><a href="#"> <img src="assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span class="blue">Susan:</span> 不知道翻译...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>20分钟以前</span>
									</span>
								</span>
							</a></li>

							<li><a href="#"> <img src="assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" /> <span class="msg-body"> <span class="msg-title"> <span class="blue">Bob:</span> 到底是不是英文 ...
									</span> <span class="msg-time"> <i class="icon-time"></i> <span>下午3:15</span>
									</span>
								</span>
							</a></li>

							<li><a href="inbox.html"> 查看所有消息 <i class="icon-arrow-right"></i>
							</a></li>
						</ul></li>

					<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> <img class="nav-user-photo" src="assets/avatars/avatar4.png" alt="Jason's Photo" /> <span class="user-info"> <small>欢迎光临,</small> ${username}
						</span> <i class="icon-caret-down"></i>
					</a>

						<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="#"> <i class="icon-cog"></i> 设置
							</a></li>

							<li><a href="#"> <i class="icon-user"></i> 个人资料
							</a></li>

							<li class="divider"></li>

							<li><a href="logout.do"> <i class="icon-off"></i> 退出
							</a></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="icon-signal"></i>
						</button>

						<button class="btn btn-info">
							<i class="icon-pencil"></i>
						</button>

						<button class="btn btn-warning">
							<i class="icon-group"></i>
						</button>

						<button class="btn btn-danger">
							<i class="icon-cogs"></i>
						</button>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li class="active"><a href="index.jsp"> <i class="icon-dashboard"></i> <span class="menu-text"> 欢迎页面 </span>
					</a></li>

					<li><a href="cloud/list.do"> <i class="icon-text-width"></i> <span class="menu-text"> 我的网盘 </span>
					</a></li>
					<li><a href="user/followlist.do">
								<i class="icon-list-alt"></i>
								<span class="menu-text"> 关注用户 </span>
							</a></li>

					<li>
							<a href="cloud/getshare.do">
								<i class="icon-calendar"></i>
								<span class="menu-text"> 我的分享</span>
							</a>
						</li>

					<li>
							<a href="cloud/getshareed.do">
								<i class="icon-calendar"></i>
								<span class="menu-text"> 收到分享</span>
							</a>
						</li>


				</ul>
				<!-- /.nav-list -->

				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

					<ul class="breadcrumb">
						<input type="button" class="button purple" value="上传文件" onclick="upload()" />
						<input type="button" class="button blue" value="创建文件夹" onclick="mkdir()" />
						<input type="button" class="button darkblue" value="删除" onclick="deldir()" />
						<input type="button" class="button teal" value="分享" onclick="share()" />

						<!-- <li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">我的网盘</li> -->
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search">
							<span class="input-icon"> <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" /> <i class="icon-search nav-search-icon"></i>
							</span>
						</form>
					</div>
					<!-- #nav-search -->

				</div>

				<div class="page-content">
					<script type="text/javascript">
						   function mkdir(){
							   var str = '';
							   str +="<div><input type='text' id='mkdir' name='mkdir' placeholder='文件名'/>";
							   str +="<input class='button purple' type='button' onclick='addDir()' value='提交'/>&nbsp;&nbsp;&nbsp;";
							   str +="<input class='button black' type='button' onclick='removeDir()' value='取消'/></div>";
							   $("#mkdirForm").append(str);
						   }
						   function addDir(){
							   var mkdir = $.trim($("#mkdir").val());
							   if(mkdir==null || mkdir==''){
								   alert("请输入文件夹名称");
								   return false;
							   }
							   var data = $("#mkdirForm").formToArray();
								$.ajax({
									type:"POST",
									url:"cloud/mkdir.do",
									data:data,
									dataType:"json",
									success:function(json){
										if(json.success){
											$("#mkdirForm").empty();
											/* var str = '';
											str +="<tr>";
											str +="<td><input type='checkbox' value='"+json.obj.id+"'/>"+json.obj.name+"</td>";
											str +="<td>文件夹</td>";
											str +="<td>"+json.obj.date+"</td>";
											str +="</tr>";
											$("#listdir tr:eq(0)").before(str); */
											location.reload();
										}else{
											alert(json.msg);
										}
										
									}
								});
						   }
						   function removeDir(){
							   $("#mkdirForm").empty();
						   }
						   function selectBox(){
							   $("#listdir input[type=checkbox]").each(function(){
								   if(this.checked==true){
										this.checked=false;
									}else{
										this.checked=true;
									}
							   });
						   }
						   function deldir(){
							   var dir = $("#dir").val();
							   var ids = [];
							   $("#listdir input[type=checkbox]").each(function(){
								   if(this.checked==true){
									   ids.push(this.value);
									}
							   });
							   if(ids.length>0){
								   $( "#dialog-confirm" ).removeClass('hide').dialog({
										resizable: false,
										modal: true,
										title: "删除提醒",
										title_html: true,
										buttons: [
											{
												text: "取消",
												"class" : "btn btn-xs",
												click: function() {
													$( this ).dialog( "close" );
												}
											},
											{
												text: "确定",
												"class" : "btn btn-primary btn-xs",
												click: function() {
													$.post('${pageContext.request.contextPath}/cloud/delete.do', {ids:ids.join(','),dir:dir}, function(j) {
														if (j.success) {
															location.reload();
														}else{
															alert(json.msg);
														}
													}, 'json');
												}
											}
										]
									});
							   }else{
								   alert("你没有选择");
							   }
						   }
						   $(document).ready(function(){
							   $('table th input:checkbox').on('click' , function(){
									var that = this;
									$(this).closest('table').find('tr > td:first-child input:checkbox')
									.each(function(){
										this.checked = that.checked;
										$(this).closest('tr').toggleClass('selected');
									});
										
								});
						   });
						   function upload(){
							   var dir = $("#dir").val();
							   //console.info(dir);
							   $.layer({
								    type: 2,
								    border: [0],
								    title: false,
								    closeBtn: [0, true],
								    iframe: {src : 'cloud/upload11.jsp?dir='+dir},
								    area: ['510px', '450px']
								});
						   }
						   function editName(index){
							   $("#edit01"+index).hide();
							   $("#edit02"+index).show();
						   }
						   function removeBut(index){
							   $("#edit02"+index).hide();
							   $("#edit01"+index).show();
						   }
						   function renameBut(index,name,type){
							   var dir = $("#dir").val();
							   var newname = $.trim($("#rename"+index).val());
							   if(newname==null || newname==''){
								   alert("请输入名称");
								   return false;
							   }
							   $.post('${pageContext.request.contextPath}/cloud/rename.do', {dir:dir,name:name,rename:newname,type:type}, function(j) {
								    if (j.success) {
										location.reload();
									}else{
										alert(json.msg);
									}
								}, 'json');
							   //$("#edit02"+index).hide();
							   //$("#edit01"+index).show();
						   }
						   function viewName(index,name){
							   var dir = $("#dir").val();
							   $.layer({
								    type: 2,
								    border: [0],
								    title: false,
								    closeBtn: [0, true],
								    iframe: {src : 'cloud/view.do?dir='+dir+'&name='+name},
								    area: ['950px', '650px']
								});
						   }
						   function share(){
							   var dir = $("#dir").val();
							   var ids = [];
							   $("#listdir input[type=checkbox]").each(function(){
								   if(this.checked==true){
									   ids.push(this.value);
									}
							   });
							   if(ids.length>0){
								   $.layer({
									    type: 2,
									    border: [0],
									    title: '选择要分享的用户',
									    closeBtn: [0, true],
									    iframe: {src : 'user/getFollow.do?ids='+ids+'&dir='+dir},
									    area: ['860px', '400px']
									});
							   }else{
								   alert("你没有选择");
							   }
						   }
						</script>
					<div class="page-header">
						<h1>${url}</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">

							<div id="dialog-confirm" class="hide">

								<p class="bigger-110 bolder center grey">
									<i class="icon-hand-right blue bigger-120"></i> 你确定要删除么？
								</p>
							</div>
							<!-- PAGE CONTENT BEGINS -->
							<div>
								<form id="mkdirForm">
									<input type="hidden" id="dir" name="dirName" value="${dir}" />
								</form>
							</div>
							<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center"><label><input type="checkbox" class="ace" autocomplete="off"/> <span class="lbl" ></span> </label></th>
										<th>分享文件</th>
										<th>分享日期</th>
										<th></th>
									</tr>
								</thead>
								<tbody id="listdir">
									<c:forEach items="${shares}" var="entry" varStatus="sta">
										<tr>
											<td class="center"><label> <input type="checkbox" class="ace" value="" autocomplete="off"/> <span class="lbl"></span>
											</label></td>
											<td><div id="edit01${sta.index}">
													${entry.path}
												</div>
											</td>
											<td>${entry.ts}</td>
											<td>
												<button class="btn btn-xs btn-info" onclick="editName(${sta.index})" title="重命名">
													<i class="icon-edit bigger-120"></i>
												</button>
												<button class="btn btn-xs btn-info" onclick="viewName(${sta.index})" title="浏览">
													<i class="icon-eye-open bigger-120"></i>
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; 选择皮肤</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" /> <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" /> <label class="lbl" for="ace-settings-sidebar"> 固定滑动条</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" /> <label class="lbl" for="ace-settings-breadcrumbs">固定面包屑</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" /> <label class="lbl" for="ace-settings-rtl">切换到左边</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" /> <label class="lbl" for="ace-settings-add-container"> 切换窄屏 <b></b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->

</body>
</html>
