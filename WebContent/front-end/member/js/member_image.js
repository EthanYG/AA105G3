function doFirst(){
	document.getElementById('mem_image').onchange = fileChange;
}

function fileChange(){
	var file = document.getElementById('mem_image').files[0];
	
	var readFile = new FileReader();
	readFile.readAsDataURL(file);
	readFile.addEventListener('load', function(){
		var image = document.getElementById('img');
		image.src = readFile.result;
	}, false);
}

window.addEventListener('load', doFirst, false);