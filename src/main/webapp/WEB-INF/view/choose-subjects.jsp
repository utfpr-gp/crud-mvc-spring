<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:template title="Disciplinas">
    <jsp:body>

        <div class="container">

            <form action="disciplinas" method="post">
                <h3>Escolha algumas disciplinas</h3>
                <p>
                    <label>
                        <input type="checkbox" name="subjects" value="WEB I"/>
                        <span>WEB I</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input type="checkbox" name="subjects" value="WEB II"/>
                        <span>WEB II</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input type="checkbox" name="subjects" value="WEB III"/>
                        <span>WEB III</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input type="checkbox" name="subjects" value="WEB IV"/>
                        <span>WEB IV</span>
                    </label>
                </p>

                <h3>Escolha os períodos disponíveis para estudar</h3>
                <p>
                    <label>
                        <input type="checkbox" name="periods" value="1"/>
                        <span>Manhã</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input type="checkbox" name="periods" value="2"/>
                        <span>Tarde</span>
                    </label>
                </p>
                <p>
                    <label>
                        <input type="checkbox" name="periods" value="3"/>
                        <span>Noite</span>
                    </label>
                </p>

                <button type="submit" class="waves-effect waves-light btn">Enviar</button>
            </form>

            <h4>Disciplinas escolhidas:</h4>
            <c:forEach var="s" items="${subjects}">
                ${s}<br>
            </c:forEach>
            <c:if test="${empty subjects}">
                <div class="row">
                    <div class="col s12 center">
                        <i class="material-icons large">sentiment_dissatisfied</i>
                        <span class="center-block">VOCÊ AINDA NÃO ESCOLHEU AS DISCIPLINAS!</span>
                    </div>
                </div>
            </c:if>

            <h4>Períodos:</h4>
            <c:forEach var="s" items="${periods}">
                ${s}<br>
            </c:forEach>
            <c:if test="${empty periods}">
                <div class="row">
                    <div class="col s12 center">
                        <i class="material-icons large">sentiment_dissatisfied</i>
                        <span class="center-block">VOCÊ AINDA NÃO ESCOLHEU OS PERÍODOS!</span>
                    </div>
                </div>
            </c:if>
        </div>
    </jsp:body>
</t:template>