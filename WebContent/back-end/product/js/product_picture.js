function doFirst(){
	document.getElementById('prod_picture').onchange = fileChange;
}

function fileChange(){
	var file = document.getElementById('prod_picture').files[0];
	
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.addEventListener('load', function(){
		var image = document.getElementById('img');
		image.src = readFile.result;
	}, false);
}

window.addEventListener('load', doFirst, false);