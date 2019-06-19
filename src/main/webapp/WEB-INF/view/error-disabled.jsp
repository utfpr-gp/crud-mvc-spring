<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Erro!!">
    <jsp:body>
        <h2>Ooops!! Um erro desconhecido aconteceu!</h2>
        <p>Pedimos desculpas! Em breve iremos corrigir este erro!</p>
        <p>
            <strong>Mensagem de Erro: </strong>
                ${errorBean.message}
        </p>
        <div class="row">
            <div class="col s12 center">
                <img class="img-responsive" src="assets/res/img/404.png" height="500px">
            </div>
        </div>
    </jsp:body>
</t:template>
