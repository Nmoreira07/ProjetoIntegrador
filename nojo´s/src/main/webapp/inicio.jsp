<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Produtos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #000000;
            color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .navbar {
            background-color: #212529;
            border-bottom: 2px solid #ff6f00;
        }
        .navbar-brand {
            color: #ff6f00;
            font-weight: bold;
            font-size: 1.5rem;
        }
        .nav-link {
            color: #f8f9fa;
        }
        .nav-link:hover {
            color: #e65100;
        }
        .product-card {
            background-color: #212529;
            border-radius: 10px;
            padding: 15px;
            margin: 15px;
            text-align: center;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
        }
        .product-card h5 {
            color: #ff6f00;
            margin-bottom: 10px;
        }
        .product-price {
            color: #f8f9fa;
            font-size: 1.2rem;
            margin: 10px 0;
        }
        .btn-custom {
            background-color: #ff6f00;
            color: #fff;
            border: none;
            transition: background-color 0.3s;
        }
        .btn-custom:hover {
            background-color: #e65100;
        }
        .icon-favorite {
            color: #ff6f00;
            cursor: pointer;
            font-size: 1.5rem;
            transition: color 0.3s;
        }
        .icon-favorite:hover {
            color: #e65100;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="/">Nojo's</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/registro">Cadastro</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/carrinho">
                  <i class="fas fa-shopping-cart"></i> <!-- Ícone de carrinho de compras -->
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container mt-4">
    <h1 class="text-center">Produtos</h1>
    <div class="row">
        <c:forEach var="produto" items="${produtos}">
            <div class="col-md-4">
                <div class="product-card">
                    <h5>${produto.desc}</h5>
                    <p class="product-price">R$ ${produto.valor}</p>
                    <!-- Formulário para adicionar ao carrinho -->
                    <form action="/carrinho" method="post">
                        <input type="hidden" name="produtoId" value="${produto.id}">
                        <button type="submit" class="btn btn-custom btn-sm">Comprar</button>
                    </form>
                    <!-- Botão para favoritar -->
                    <form action="/favoritar" method="post" class="mt-3">
                        <input type="hidden" name="produtoId" value="${produto.id}">
                        <button type="submit" class="btn btn-link p-0">
                            <i class="fas fa-heart icon-favorite"></i>
                        </button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
