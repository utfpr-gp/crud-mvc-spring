<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Certificado">
    <jsp:body>
        <h1>Certificado</h1>
        <c:if test="${not empty errors}">
            <div class="card-panel red">
                <c:forEach var="e" items="${errors}">
                    <span class="white-text">${e.getDefaultMessage()}</span><br>
                </c:forEach>
            </div>
        </c:if>

        <div class="row">
            <div class="col s12">
                <!-- -->
                <form action="alunos/certificado" method="post">

                    <label for="email">Email</label>
                    <input id="email" name="email" type="text" value="${dto.email}"/>

                    <label for="year">Ano</label>
                    <input id="year" name="year" type="number" value="${dto.year}"/>

                    <button class="btn waves-effect waves-light right" type="submit">Emitir Certificado
                        <i class="material-icons right">send</i>
                    </button>
                </form>
            </div>
        </div>

    </jsp:body>
</t:template>
