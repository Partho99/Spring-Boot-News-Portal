GET: $(document).ready(function () {

    // GET REQUEST
    $("#getALlBooks").ready(function (event) {
        // event.preventDefault();
        ajaxGet();
    });

    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "/news",
            dataType: 'json',
            cache: false,

            success: function (data) {
                    $('#getResultDiv').empty();
                for (var i = 0; i < data.length; i++) {
                        var user = "<a>" +data[i].newsHeading + "</a>" +" <br>"+"<p>" +data[i].newsDescription+"</p>";

                        // $('#getResultDiv .liitem1 .liitem2').append(
                        // bookName,author)

                        $("#getResultDiv").append(user);

//						$('.listitem1').html(bookName);
//						$('.listitem2').html(author);
                    }
                    console.log("Success: ", data);

            },
            error: function (e) {
                $("#getResultDiv").html("<strong>Error</strong>");
                console.log("ERROR: ", e);
            }
        });
    }
})