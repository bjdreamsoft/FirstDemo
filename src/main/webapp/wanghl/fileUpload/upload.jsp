<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/upload.css">
<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.form.min.js"></script>
<script type="text/javascript" src="resources/js/upload.js"></script>
<title>上传测试</title>
</head>
<body>
<div class="main">
	<form id="form1" method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload/fileUpload.action">
		<div class="form_div">
			<label>上传文件：</label>
			<input name="uploadFile" type="file" onchange='uploadTemp("form1")'>
			<div>
				<span id="msg"></span>
			</div>
			<input type="hidden" name="attach" id="attach">
		</div>
	</form>
	
	<div  class="form_div">
		<input id="sub_button" class="sub_button" type="button" value="提交">
	</div>
</div>
</body>
</html>