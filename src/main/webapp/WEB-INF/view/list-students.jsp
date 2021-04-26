<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Alunos">
    <jsp:body>
        <div class="row">
                <div class="col s12">
                    <c:if test="${not empty students}">
                    <table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Nome</th>
                            <th>Gênero</th>
                            <th>Data de Nascimento</th>
                            <th>Curso</th>
                            <th></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="s" items="${students}">
                            <tr>
                                <td>${s.registration}</td>
                                <td>${s.name}</td>
                                <td>${s.gender}</td>
                                <td>
                                    <fmt:formatDate value="${s.birthDate}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>${s.course}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                </div>
            </div>
        </div>

    </jsp:body>
</t:template>