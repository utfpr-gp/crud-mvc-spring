<%@tag description="Tabs" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="tabs" type="java.util.List" %>

<div>
    <div class="row">
        <div class="col s12">
            <ul class="tabs tabs-fixed-width">
                <c:forEach var="e" items="${tabs}" varStatus="status">
                    <li class="tab col s3"><a data-url="${e.route}" href="#tab${status.index+1}">${e.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <c:forEach var="e" items="${tabs}" varStatus="status">
            <div id="tab${status.index+1}" class="col s12">
            </div>
        </c:forEach>
    </div>
</div>

<script src="assets/lib/jquery/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        $('.tabs').tabs();
    });
</script>
<script>
    $(document).ready(function(){
        // inicializa a tab com conteúdo
        $('#tab1').load($('.tab .active').attr("data-url"), function (result) {
        });
    });
    $('.tab a').click(function (e) {
        e.preventDefault();

        let url = $(this).attr("data-url");
        let href = this.hash;

        // carrega via ajax do data-url
        $(href).load(url, function (result) {
        });
    });
</script>



