<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Alunos">
    <jsp:body>
        <div class="container">
            <c:if test="${not empty msg}">
                <div class="card-panel green lighten-1">
                    <span class="white-text">${msg}</span>
                </div>
            </c:if>

            <c:if test="${not empty errors}">
                <div class="card-panel red">
                    <c:forEach var="e" items="${errors}">
                        <span class="white-text">${e.getDefaultMessage()}</span><br>
                    </c:forEach>
                </div>
            </c:if>

            <h1>Cadastro de Professor</h1>

            <div class="row">
                <div class="col s12">
                    <!-- -->
                    <form action="professores/passo-2" method="post">

                        <label for="name">Nome Completo</label>
                        <input id="name" name="name" type="text" value="${dto.name}"/>

                        <button class="btn waves-effect waves-light right" style="display: inline; margin: 5px" type="submit">Próximo
                            <i class="material-icons right">forward</i>
                        </button>

                        <a class="btn waves-effect waves-light right" style="display: inline; margin: 5px" href="professores?passo=1">Voltar
                        </a>
                    </form>
                </div>
            </div>
        </div>

    </jsp:body>
</t:template>
