<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <form method="post" action="users">
                <div class="row">
                    <div class="col-12 col-lg-12 col-md-12 text-center">
                        <img src="${imageurl}" alt=""
                             class="mx-auto rounded-circle img-fluid">
                        <h3 class="mb-0 text-truncated">${name}</h3>
                        <br>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button class="btn btn-outline-danger btn-block" name="dislike" value="dislike"><span
                                class="fa fa-times"></span>
                            Dislike
                        </button>
                    </div>
                    <div class="col-12 col-lg-6">
                        <button class="btn btn-outline-success btn-block" name="like" value="like"><span
                                class="fa fa-heart"></span>
                            Like
                        </button>
                    </div>
                    <!--/col-->
                </div>
            </form>
            <!--/row-->
        </div>
        <!--/card-block-->
    </div>

</div>
<a class="btn btn-outline-danger btn-block" href="/logout">Logout</a>
<a class="btn btn-outline-primary btn-block" href="/liked">Liked</a>
</body>
</html>