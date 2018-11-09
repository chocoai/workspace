  
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>

<p contenteditable="true" class="ppp" width="30px">11</p>



<script>

function atUserInfo(id,name,text){
	   this.id=id;
	   this.name= name;
	   this.text = text;
}

var atUserInfoList=[];

$("#ppp").keyup(function(event){ 
          if (event.ctrlKey && event.keyCode === 67){ 
            alert('你按下了CTRL+C'); 
          } 
 }); 

 $(document).on("input propertychange",".ppp",function(){
    var win=null; 
    
    var aa =$(this).text();
    
    var lastStr = aa.charAt(aa.length - 1)
    
    if(lastStr == '@'){
    	win=window.open("allPlateUser?plateId=5b8e42fd867b4746a4243f98b1b1a23e","win","width=100%,height=100%"); 
    	
    }
    
    //$('#content').html($(this).val().length + ' characters');
    
	//win=window.open("allPlateUser?plateId=5b8e42fd867b4746a4243f98b1b1a23e","win","width=100%,height=100%" ); 
	
 });


 $(document).on("input @",".notifyDetail",function(){
    var win=null; 
	//win=window.open('allPlateUser?plateId=5b8e42fd867b4746a4243f98b1b1a23e','win','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+ ',top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no'); 
	window.open('allPlateUser?plateId=5b8e42fd867b4746a4243f98b1b1a23e','新窗口','width='+(window.screen.availWidth-10)+',height='+(window.screen.availHeight-30)+',top=0,left=0,resizable=yes,status=yes,menubar=no,scrollbars=yes');
 });
 
 function aab(id,username){
 	
 	//$(#notifyDetail).insertContent("123");
 	
 	//var userinfo = new atUserInfo(id,username,'');
 	
 	//atUserInfoList.push(userinfo);
   
   //for(var i=0;i<atUserInfoList.length;i++){
   	// var a = atUserInfoList[i];
   	// console.log(a.name);
   //}
   
 }
 
</script>
