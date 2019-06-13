<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
                    <form:form modelAttribute="student" action="/alunos" method="post">
                        <label for="name">Nome Completo</label>
                        <form:input path="name" type="text"/>
                        <form:errors path="name"/>

                        <label for="email">Email</label>
                        <form:input path="email" type="text"/>
                        <form:errors path="email"/>

                        <label for="birthDate">Data de Nascimento</label>
                        <form:input path="birthDate" type="text"/>
                        <form:errors path="birthDate"/>

                        <label for="course">Curso</label>
                        <form:input path="course" type="text"/>
                        <form:errors path="course"/>

                        <form:button class="btn waves-effect waves-light right" type="submit">Salvar
                            <i class="material-icons right">save</i>
                        </form:button>
                    </form:form>
                </div>
            </div>
            <div class="row">
                <div class="col s12">
                    <table>
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Item Name</th>
                            <th>Item Price</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>Alvin</td>
                            <td>Eclair</td>
                            <td>$0.87</td>
                        </tr>
                        <tr>
                            <td>Alan</td>
                            <td>Jellybean</td>
                            <td>$3.76</td>
                        </tr>
                        <tr>
                            <td>Jonathan</td>
                            <td>Lollipop</td>
                            <td>$7.00</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>