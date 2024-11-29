<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <title>Favoritos</title>
</head>
<body>
    <h1>Produtos Favoritos</h1>
    <c:forEach var="produto" items="${favoritos}">
        <div>
            <h2>${produto.desc}</h2>
            <p>R$ ${produto.valor}</p>
        </div>
    </c:forEach>
</body>
</html>
