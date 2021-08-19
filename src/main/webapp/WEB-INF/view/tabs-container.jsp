<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="Alunos por gênero">
    <jsp:body>
            <div class="row">
                <div class="col s12">
                    <ul class="tabs tabs-fixed-width">
                        <li class="tab"><a data-url="alunos/relatorio/masculino/paginacao" href="#tab1">Masculino</a></li>
                        <li class="tab"><a data-url="alunos/relatorio/genero/feminino" href="#tab2">Feminino</a></li>
                    </ul>
                </div>
                <div id="tab1" class="col s12">
                </div>
                <div id="tab2" class="col s12">
                </div>
            </div>

    </jsp:body>
</t:template>
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
            window.location.hash = "#tab1";
        });
    });
    $('.tab a').click(function (e) {
        e.preventDefault();

        let url = $(this).attr("data-url");
        let href = this.hash;
        window.location.hash = href;
        // carrega via ajax do data-url
        $(href).load(url, function (result) {
        });
    });
</script>

