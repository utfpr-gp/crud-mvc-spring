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

            <h1>Cadastro de Alunos</h1>
            <div class="row">
                <div class="col s12">
                    <a class="btn-large waves-effect waves-light" href="alunos">Novo</a>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <!-- -->
                    <form action="alunos" method="post">
                        <!-- campo usado em caso de edição -->
                        <input name="registration" type="hidden" value="${dto.registration}"/>

                        <label for="name">Nome Completo</label>
                        <input id="name" name="name" ${not empty dto ? 'readonly' : ''} type="text" value="${dto.name}"/>

                        <label for="gender">Gênero</label>
                        <div class="input-field">
                        <select id="gender" name="gender">
                            <option value="MASCULINE">Masculino</option>
                            <option value="FEMININE">Feminino</option>
                        </select>
                        </div>
                        <label for="email">Email</label>
                        <input id="email" name="email" type="text" value="${dto.email}"/>

                        <label for="birthDate">Data de Nascimento</label>
                        <fmt:formatDate var="birthDateFormatted" value="${dto.birthDate}"
                                        pattern="dd/MM/yyyy" />
                        <input id="birthDate" name="birthDate" type="text" value="${birthDateFormatted}"/>

                        <label for="course">Curso</label>
                        <input id="course" name="course" type="text" value="${dto.course}"/>

                        <button class="btn waves-effect waves-light right" type="submit">Salvar
                            <i class="material-icons right">save</i>
                        </button>
                    </form>
                </div>
            </div>

                    <div id="modal-delete" class="modal">
                        <form action="" method="post">

                            <input type="hidden" name="_method" value="DELETE"/>

                            <div class="modal-content">
                                <h4>Você tem certeza que deseja remover <strong id="strong-name"></strong>?</h4>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="modal-close btn-flat waves-effect waves-light grey">Cancelar</button>
                                <button type="submit" class="modal-close btn waves-effect waves-light gray">Sim</button>
                            </div>
                        </form>
                    </div>

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
                                <td>
                                    <a href="alunos/${s.registration}"><i class="material-icons">edit</i></a>
                                    <a href="#modal-delete" class="modal-trigger" data-url="${pageContext.request.contextPath}/alunos/${s.registration}" data-name="${s.name}"><i class="material-icons red-text">delete</i></a>
                                </td>
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