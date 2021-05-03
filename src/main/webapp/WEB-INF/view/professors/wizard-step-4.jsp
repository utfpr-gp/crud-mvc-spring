<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Professores">
    <jsp:body>

        <div class="container">
            <c:if test="${not empty msg}">
                <div class="card-panel green lighten-1">
                    <span class="white-text">${msg}</span>
                </div>
                <div>
                    <a class="btn btn-large" href="">Ok</a>
                </div>
            </c:if>

            <c:if test="${not empty errors}">
                <h3>Ooops!! Não foi possível realizar o cadastro!</h3>
                <p>Pedimos desculpas! Tente novamente!</p>
                <div class="card-panel red">
                    <c:forEach var="e" items="${errors}">
                        <span class="white-text">${e.getDefaultMessage()}</span><br>
                    </c:forEach>
                </div>
                <div class="row">
                    <div class="col s12 center">
                        <img class="img-responsive" src="assets/res/img/error.png" height="500px">
                    </div>
                </div>
            </c:if>
        </div>
    </jsp:body>
</t:template>

