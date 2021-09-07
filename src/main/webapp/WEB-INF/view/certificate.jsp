<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template title="Certificado">
    <jsp:body>
        <h1>Certificado</h1>
        <c:if test="${email}">
            <div class="row">
                <div class="col s12">
                    O link para download do certificado também foi enviado para o seu email. Confira!
                </div>
            </div>
        </c:if>

        <c:if test="${not empty qrCode}">
            <div class="row">
                <div class="col s12">
                    <img src="${qrCode}" width="300px">
                </div>
            </div>
        </c:if>

        <div class="row">
            <div class="col s12">
                <a href="${certificate}">Clique para visualizar o certificado</a>
            </div>
        </div>
    </jsp:body>
</t:template>
