<%@tag description="Template principal" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" %>

<html>
<head>
    <title>${title}</title>
    <base href="${pageContext.request.contextPath}/">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="icon" type="image/png" href="favicon.png"/>

    <!-- CSS -->
    <link rel="stylesheet" href="assets/lib/materialize/css/materialize.css"></link>
    <link rel="stylesheet" href="assets/lib/font-awesome/css/font-awesome.css"></link>
    <link rel="stylesheet" href="assets/res/css/index.css"></link>
    <link rel="stylesheet" href="assets/res/css/template.css"></link>


    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet"></link>
    <link href='http://fonts.googleapis.com/css?family=Roboto:200,700'
          rel='stylesheet' type='text/css'></link>

</head>
<body>
<header>
    <div class="navbar-fixed" style="z-index: 999">
        <nav>
            <div class="nav-wrapper blue">
                <div class="container">
                    <a class="brand-logo" href="">
                        <img class="utf-logo" src="assets/res/img/logo.png"/>
                    </a>
                    <a href="#" data-target="mobile-demo" class="sidenav-trigger">
                        <i class="material-icons">menu</i>
                    </a>
                    <ul class="right hide-on-med-and-down">
                        <li><a href="">Início</a></li>
                    </ul>
                    <ul class="sidenav white" id="mobile-demo">
                        <div class="row">
                            <div class="col s8 offset-s2">
                                <div class="center">
                                    <img class="responsive-img utf-logo-nav"
                                         src="assets/res/img/logo.png"/>
                                </div>
                            </div>
                        </div>

                        <li><a href="">Início</a></li>

                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>
<main id="content" class="container">
    <jsp:doBody/>
</main>
<!-- rodape-->
<footer class="page-footer blue darken-5">

    <div class="footer-copyright">
        <div class="container">
            &#169; Copyright <span id="year"></span> - Todos os direitos
            reservados
        </div>
    </div>
</footer>

<script src="assets/lib/jquery/jquery-3.3.1.min.js"></script>
<script src="assets/lib/jquery/jquery.mask.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<!--script src="assets/lib/materialize/js/materialize.js"></script-->
<script src="assets/res/js/index.js"></script>

</body>
</html>
