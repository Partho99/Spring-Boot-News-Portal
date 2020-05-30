$(document).ready(function () {

    load_comment();
    $('#comment_form').on('submit', function (event) {
        event.preventDefault();
        const form_data = $(this).serialize();
        $.ajax({
            url: "/postComment",
            method: "POST",
            data: form_data,
            success: function (data) {
                if (data.error != '') {
                    $('#comment_form')[0].reset();
                    const container = document.querySelector(".new_comment");
                    let something = ' <div class="card gedf-card" >\n' +
                        '                            <div class="card-header ">\n' +
                        '                                <div class="d-flex justify-content-between align-items-center">' +
                        '                                    <div class="d-flex justify-content-between align-items-center">' +
                        '                                        <div class="mr-2">\n' +
                        '                                            <img class="rounded-circle" width="45" src="https://picsum.photos/50/50"' +
                        '                                                 alt="">' +
                        '                                        </div>\n' +
                        '                                        <div class="ml-2">' +
                        '                                            <div class="h5 m-0">' +
                        '';
                    something += data.user.username +
                        '</div>' +
                        '                                            <div class="h7 text-muted">Portal Member</div>' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                            <div class="card-body">' +
                        '                                <div class="text-muted h7 mb-2"><i class="fa fa-clock-o"></i> 10 min ago</div>'
                    something += data.comment ;
                    something += '</div>';
                    container.innerHTML += something;

                }
            }
        });
    });

    function load_comment() {
        var newsId = $("#newsid").val();
        var news = {"newsid": newsId};
        $.ajax({
            url: "/showComments",
            data:news,
            method: "POST",
            dataType: "JSON",
            success: function (data) {
                const container = document.querySelector(".axz");
                for (let i = 0; i < data.length; i += 1) {
                    let something = ' <div class="card gedf-card" >\n' +
                        '                            <div class="card-header ">\n' +
                        '                                <div class="d-flex justify-content-between align-items-center">' +
                        '                                    <div class="d-flex justify-content-between align-items-center">' +
                        '                                        <div class="mr-2">\n' +
                        '                                            <img class="rounded-circle" width="45" src="https://picsum.photos/50/50"' +
                        '                                                 alt="">' +
                        '                                        </div>\n' +
                        '                                        <div class="ml-2">' +
                        '                                            <div class="h5 m-0">' +
                        '';
                    something += data[i].user.username +
                        '</div>' +
                        '                                            <div class="h7 text-muted">Portal Member</div>' +
                        '                                        </div>' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                            <div class="card-body">' +
                        '                                <div class="text-muted h7 mb-2"><i class="fa fa-clock-o"></i> 10 min ago</div>'
                    something += data[i].comment ;
                    something += '</div>';
                    container.innerHTML += something;
                }
            }
        })
    }
});