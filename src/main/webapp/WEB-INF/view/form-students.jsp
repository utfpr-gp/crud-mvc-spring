<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Alunos">
    <jsp:body>
        <div class="container">
            <h1>Cadastro de Alunos</h1>
            <div class="row">
                <div class="col s12">
                    <a class="btn-large waves-effect waves-light">Novo</a>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <!-- -->
                    <form action="alunos" method="post">
                        <label for="name">Nome Completo</label>
                        <input name="name" type="text"/>

                        <label for="name">Gênero</label>
                        <select name="gender">
                            <option value="MASCULINE">Masculino</option>
                            <option value="FEMININE">Feminino</option>
                        </select>

                        <label for="email">Email</label>
                        <input name="email" type="text"/>

                        <label for="birthDate">Data de Nascimento</label>
                        <input name="birthDate" type="text"/>

                        <label for="course">Curso</label>
                        <input name="course" type="text"/>

                        <button class="btn waves-effect waves-light right" type="submit">Salvar
                            <i class="material-icons right">save</i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <c:if test="${not empty students}">
                    <table>
                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Gênero</th>
                            <th>Data de Nascimento</th>
                            <th>Curso</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="s" items="${students}">
                            <tr>
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