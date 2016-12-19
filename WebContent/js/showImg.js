
	function showImage(){
		var file = document.getElementById("upLoadFile").files[0];				
		var read = new FileReader();
		read.onload=function(){
				var image = document.getElementById('image');
					image.src=read.result;
					image.width=300;
				};
		read.readAsDataURL(file);	
	} 


// 	(function readURL(input) {

//     if (input.files && input.files[0]) {
//         var reader = new FileReader();

//         reader.onload = function (e) {
//             $('#image').attr('src', e.target.result);
//         }

//         reader.readAsDataURL(input.files[0]);
//     }
// }

// $("#upLoadFile").change(function(){
//     readURL(this);
// });

// );
