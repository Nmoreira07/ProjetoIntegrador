<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrinho</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #000000; /* Fundo preto */
            color: #ffffff; /* Texto branco */
        }
        .btn-laranja {
            background-color: #ff6f00; /* Cor laranja */
            border: none;
            color: white;
            transition: background-color 0.3s;
        }
        .btn-laranja:hover {
            background-color: #e65100; /* Laranja mais escuro no hover */
        }
        .list-group-item {
            background-color: #212529; /* Fundo escuro para os itens */
            color: white;
            border: 1px solid #333; /* Bordas sutis */
        }
        .total-container {
            margin-top: 20px;
            font-size: 1.5rem;
            text-align: right;
        }
        .remove-item {
            color: #ff6f00; /* Ícone de remoção em laranja */
            cursor: pointer;
            transition: color 0.3s;
        }
        .remove-item:hover {
            color: #e65100; /* Laranja mais escuro no hover */
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Seu Carrinho</h1>
        <c:if test="${not empty carrinho}">
            <ul class="list-group">
                <c:forEach var="produto" items="${carrinho}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                        <span>${produto.desc} - ${produto.marca}</span>
                        <span>
                            R$ ${produto.valor}
                            <!-- Botão para remover item -->
                            <form action="/remove-item" method="post" class="d-inline">
                                <input type="hidden" name="produtoId" value="${produto.id}">
                                <button type="submit" class="btn btn-link remove-item" title="Remover">
                                    <i class="fas fa-times"></i>
                                </button>
                            </form>
                        </span>
                    </li>
                </c:forEach>
            </ul>
            <div class="total-container">
                <c:set var="total" value="0"/>
                <c:forEach var="produto" items="${carrinho}">
                    <c:set var="total" value="${total + produto.valor}"/>
                </c:forEach>
                <p><strong>Total: R$ ${total}</strong></p>
            </div>
        </c:if>
        <c:if test="${empty carrinho}">
            <p class="text-center text-muted">Seu carrinho esta vazio.</p>
        </c:if>
        <a href="/inicio" class="btn btn-laranja mt-3">Continuar Comprando</a>
    </div>
</body>
</html>
