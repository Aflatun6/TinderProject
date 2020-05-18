<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="static/img/favicon.ico">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-8 offset-2">
            <div class="panel panel-default user_panel">
                <div class="panel-heading">
                    <h3 class="panel-title">User List</h3>
                </div>
                <div class="panel-body">
                    <div class="table-container">
                        <table class="table-users table" border="0">
                            <tbody>
                            <#list users as user>
                                <tr>
                                    <td width="10">
                                        <div class="avatar-img">
                                            <img class="img-circle" src="${user.imageURL}"/>  
                                        </div>

                                    </td>
                                    <td class="align-middle">
                                        ${user.name}
                                    </td>
                                    <td class="align-middle">
                                            <a class="btn btn-primary btn-block" href="messages/${user.id}" name="message">send message</a>
                                    </td>
                                    <td class="align-middle">
                                        Last Login: 6/10/2017<br><small class="text-muted">5 days ago</small>
                                    </td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                        <a class="btn btn-outline-danger btn-block" href="/login">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>