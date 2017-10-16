/**
 * 
 */

String.prototype.replaceAll=function (replaceStr,target){
	if(!this instanceof String){
		return 
	};
	var res=this.replace(new RegExp(replaceStr,'gm'),target);
	return res.trim();
}
function commonRequestData(uri,sendData,datatype,callback){
	$.ajax({
		url:uri,
		data:sendData,
		dataType:datatype || "json",
		type:"post",
		success:function(res){
			if(typeof(callback)=='function'){
				callback(res);
			}else{
				alert("请确认"+callback+"为function类型");
			}
		},
		error:function(res){
			console.log(res);
		}
	});
}