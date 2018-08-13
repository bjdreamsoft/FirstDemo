$(function(){
		
	$("#sub_button").click(function(){
		$.ajax({
			url:getRootPath()+"/upload/saveform.action",
			data:$("#form1").serialize(),
			type:"POST",
			success:function(json){				
				if(json.result){
					alert("保存成功!");
				}else{
					alert("保存失败！");
				}
			}
		});
	});
})



function uploadTemp(formid){
	$("#"+formid).ajaxSubmit({
		success:function(json){
			if(json.result){
				$("#attach").val(json.filename);
				$("#msg").html(json.type);
			}
		}
	});
}

function getRootPath(){   

    var url = window.location.href;
    var pathname = window.location.pathname;
    var pos=url.indexOf(pathname);
    
    var prePath=url.substring(0,pos);
    var postPath=pathname.substring(0,pathname.substr(1).indexOf('/')+1);
    
   return prePath+postPath;   
}    
