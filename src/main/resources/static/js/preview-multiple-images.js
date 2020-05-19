// $(function () {
//     // Multiple images preview in browser
//     var imagesPreview = function (input, placeToInsertImagePreview) {
//
//         if (input.files) {
//             var filesAmount = input.files.length;
//
//             for (i = 0; i < filesAmount; i++) {
//                 var reader = new FileReader();
//
//                 reader.onload = function (event) {
//                     $($.parseHTML('<img width="250px" height="250px" style="padding: 10px">')).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
//                 }
//
//                 reader.readAsDataURL(input.files[i]);
//             }
//         }
//
//     };
//
//     $('#files').on('change', function () {
//         imagesPreview(this, 'div.gallery');
//     });
// });




// for removing image on preview

$(document).ready(function() {
    if (window.File && window.FileList && window.FileReader) {
        $("#files").on("change", function(e) {
            var files = e.target.files,
                filesLength = files.length;
            for (var i = 0; i < filesLength; i++) {
                var f = files[i]
                var fileReader = new FileReader();
                fileReader.onload = (function(e) {
                    var file = e.target;
                    $("<span class=\"pip\">" +
                        "<img class=\"imageThumb\" src=\""  + e.target.result + "\" title=\"" + file.name + "\"/>" +
                        "<br/><span class=\"remove\">Remove image</span>" +
                        "</span>").insertAfter("#files");
                    $(".remove").click(function(){
                        $(this).parent(".pip").remove();
                    });

                    // Old code here
                    /*$("<img></img>", {
                      class: "imageThumb",
                      src: e.target.result,
                      title: file.name + " | Click to remove"
                    }).insertAfter("#files").click(function(){$(this).remove();});*/

                });
                fileReader.readAsDataURL(f);
            }
        });
    } else {
        alert("Your browser doesn't support to File API")
    }
});