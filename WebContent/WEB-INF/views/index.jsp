<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link href="<c:url value="/index.css"/>" type="text/css" rel="stylesheet">
    <%--<link href="index.css" rel="stylesheet" media="screen">--%>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.1/jquery.validate.min.js"></script>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
          crossorigin="anonymous">
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
            crossorigin="anonymous"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
            integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.11.0/sweetalert2.css"/>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/7.11.0/sweetalert2.all.min.js"></script>
    <script src="<c:url value="/js/index.js" />"></script>

    <style type="text/css">
        .login-form {
            width: 340px;
            margin: 50px auto;
        }

        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }

        .login-form h2 {
            margin: 0 0 15px;
        }

        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }

        .btn {
            font-size: 15px;
            font-weight: bold;
        }

    </style>
    <script type="text/javascript">

        function submitForm() {

            $.ajax({
                type: 'POST',
                url: 'search',

                data: $('#searchForm').serialize(),
                success: function (res) {

                    swal({
                        text: " " + res,
                    });
                    console.log(res)
                }, error: function (e) {
                    swal({
                        text: "an error occured ",
                        type: "error"
                    });
                }
            })

        }

    </script>

</head>
<body>
<div class="login-form">
    <form:form method="POST" name="searchForm" modelAttribute="searchModel" id="searchForm"
               action="./search">
        <h2 class="text-center">Search here</h2>
        <div class="form-group">
            <form:input path="keyword" class="form-control" name="keyword"
                        placeholder="Keyword" required="required"/>
        </div>
        <div class="form-group">
            <form:input path="url" class="form-control" placeholder="URL" name="url"
                        required="required"/>
        </div>
        <div class="form-group">
            <button type="button" class="btn btn-primary btn-block"
                    onclick="submitForm()">Search
            </button>
        </div>
    </form:form>

</div>
</body>
<head>

</head>

</html>