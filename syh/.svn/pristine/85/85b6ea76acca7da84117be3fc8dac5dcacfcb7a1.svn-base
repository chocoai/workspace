$(function() {
	$("#submitBtn").click(function(){
		if(vilidata()){
			var info=new Object;
			info.cid = $('#cid').val();
			info.roleName=$('#roleName').val();
			info.state==$(":radio[name='state']:checked").val();
			info.orderNum=$('#orderNum').val();
			info.backup=$('#backup').val();
			
			var url=$("#contextPath").val() + "/sysRole/saveToUpdate";
			ajaxSubmit(info,url);
		}
		
	})
	
	$("#cancleBtn").click(function(){
		layer.confirm("确定取消新增信息么？",{
			btn : [ '确定', '取消' ]// 按钮
		},function(){
			self.location.href="index"
		})
	})
	
})

function vilidata(){
	var isSumbit = false;
	if($('#roleName').val().trim()==""){
		layer.tips("请输入角色名称","#roleName")
	}else if($('#orderNum').val().trim()!=""&&!isNum($('#orderNum').val())){
		layer.tips("请输入数字类型","#orderNum")
	}else{
		isSumbit = true;
	}
	return isSumbit;
}