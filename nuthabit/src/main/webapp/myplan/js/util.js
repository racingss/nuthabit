function getRootPath(){  
	var url = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = url.indexOf(pathName);
	var localhostPath = url.substring(0,pos);
	var contextroot = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return(localhostPath + contextroot);
}