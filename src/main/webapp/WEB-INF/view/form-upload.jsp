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

                <h1>Imagem de perfil</h1>

                <div class="row">
                    <form action="imagens" method="post" enctype="multipart/form-data">
                        <div class="col s12">
                        <label for="name">Nome Completo</label>
                        <input id="name" name="name" type="text"/>
                        </div>
                        <div class="col s12">
                            <div class="file-field input-field">
                                <div class="btn">
                                    <span>Arquivo</span>
                                    <input type="file" name="imageFile" accept=".jpg, .jpeg, .png">
                                </div>
                                <div class="file-path-wrapper">
                                    <label for="upload-image">Arquivo de imagem</label>
                                    <input id="upload-image" class="file-path validate" type="text">
                                </div>
                            </div>
                        </div>
                        <button class="btn waves-effect waves-light right" type="submit">Enviar
                            <i class="material-icons right">save</i>
                        </button>
                    </form>
                </div>

                <c:if test="${not empty imageURL}">
                    <div class="row">
                        <div class="col s12">
                            <img src="${imageURL}" width="200px">
                            <p>${name}</p>
                        </div>
                    </div>
                </c:if>
            </div>




        </jsp:body>
    </t:template>