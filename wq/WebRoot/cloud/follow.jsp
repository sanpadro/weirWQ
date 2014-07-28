<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>云盘用户关注</title>
<!-- basic styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->


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
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
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
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span>
			</a>

			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
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

					<li><a href="user/followlist.do"> <i class="icon-list-alt"></i> <span class="menu-text"> 关注用户 </span>
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
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">欢迎页面</li>
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
					<div class="page-header">
						<form id="searchUser">
							<input type="text" id="username" name="username" placeholder="输入用户名" /> <input class="button purple" type="button" onclick="getUser()" value="搜索" />
						</form>
						
					</div>
					<!-- /.page-header -->
					<div class="row" id="showsearch"></div>
					<div class="row">
						<c:forEach items="${follows}" var="entry">
							<div class="col-lg-1 col-md-1">
								<div class="thumbnail">
									<img src="assets/avatars/profile-pic.jpg" alt="...">
								</div>
								<div class="caption itemdiv commentdiv">
									<h4>${entry}</h4>
									<!-- <p id="follow">
										<button type="button" class="btn btn-success btn-xs">
											<span class="glyphicon glyphicon-ok"></span> 已关注
										</button>
									</p>
									<p class="moveunfollow" style="display: none;">
										<button type="button" class="btn btn-grep btn-xs">
											<span class="glyphicon glyphicon-remove"></span> 取消关注
										</button>
									</p> -->
									<div class="tools">
										<div class="action-buttons bigger-125">
											<!-- <a href="#"> <i class="icon-pencil blue"></i>
											</a> <a href="#"> <i class="icon-trash red"></i>
											</a> -->
											<button type="button" onclick="unfollowUser('${entry}')" class="btn btn-grep btn-xs">
											<span class="glyphicon glyphicon-remove"></span> 取消关注
										</button>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>

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

	<!-- basic scripts -->

	<!--[if !IE]> -->

	<script src="assets/js/jquery-2.0.3.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery-1.10.2.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->

	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.form.js"></script>

	<!-- ace scripts -->

	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		function getUser() {
			var name = $.trim($("#username").val());
			if (name == null || name == '') {
				alert("请输入你要找的用户名");
				return false;
			}
			var data = $("#searchUser").formToArray();
			$.ajax({
						type : "POST",
						url : "user/getuser.do",
						data : data,
						dataType : "json",
						success : function(json) {
							if (json.success) {
								var str = "";
								str += '<div class="col-lg-1 col-md-1">';
								str += '<div class="thumbnail"><img src="assets/avatars/profile-pic.jpg"></div>';
								str += '<div class="caption itemdiv commentdiv"><h4>'
										+ json.msg + '</h4>';
								str += '<div class="tools">';
								str += '<div class="action-buttons bigger-125">';
								str += '<button type="button" onclick="followUser(\''
										+ json.msg
										+ '\')" class="btn btn-success btn-xs">';
								str += '<span class="glyphicon glyphicon-ok"></span> 立刻关注</button>';
								str += '</div></div>';
								str += '</div></div>';
								$("#showsearch").append(str);
								//location.reload();
							} else {
								alert(json.msg);
							}

						}
					});
		}
		function followUser(name) {
			$.post('${pageContext.request.contextPath}/user/follow.do', {
				username : name
			}, function(j) {
				if (j.success) {
					location.reload();
				} else {
					alert(json.msg);
				}
			}, 'json');
		}
		function unfollowUser(name) {
			$.post('${pageContext.request.contextPath}/user/unfollow.do', {
				username : name
			}, function(j) {
				if (j.success) {
					location.reload();
				} else {
					alert(json.msg);
				}
			}, 'json');
		}
		$(document).ready(function() {
		});
	</script>
</body>
</html>
