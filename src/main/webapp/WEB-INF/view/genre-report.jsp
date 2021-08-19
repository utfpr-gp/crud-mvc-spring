<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:forEach var="e" items="${students}">
    ${e}<br>
</c:forEach>
<t:pagination-tab-ajax pagination="${pagination}"></t:pagination-tab-ajax>

