<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> <!-- Incluindo Font Awesome -->
    <style>
        body {
            background-color: #000000;
            color: #fff;
            font-family: Arial, sans-serif;
        }
        .navbar {
            background-color: #212529;
        }
        .navbar-brand {
            color: #ff6f00;
            font-weight: bold;
            font-size: 1.5rem;
        }
        .nav-link {
            color: #f8f9fa;
            margin-left: 20px;
        }
        .nav-link:hover {
            color: #e65100;
        }
        .table-container {
            background-color: #212529;
            padding: 20px;
            border-radius: 10px;
        }
        h1 {
            font-size: 3rem;
            text-align: center;
            color: #f8f9fa;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
            margin-bottom: 30px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 15px;
            text-align: center; /* Centraliza texto nas células */
            color: #f8f9fa; /* Define o texto das células para branco */
        }
        th {
            background-color: #ff6f00; /* Cabeçalho em laranja */
            color: #fff; /* Texto branco no cabeçalho */
        }
        tr:nth-child(even) {
            background-color: #343a40; /* Linhas pares em cinza escuro */
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg">
    <a class="navbar-brand" href="#">Nojo's</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Início</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Marcas
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Adidas</a>
                    <a class="dropdown-item" href="#">Anti Social Club</a>
                    <a class="dropdown-item" href="#">Bape</a>
                    <a class="dropdown-item" href="#">Jordan</a>
                    <a class="dropdown-item" href="#">Nike</a>
                    <a class="dropdown-item" href="#">Nike Nocta</a>
                    <a class="dropdown-item" href="#">Off-White</a>
                    <a class="dropdown-item" href="#">The North Face</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">
                    <i class="fas fa-shopping-cart"></i> <!-- Ícone de carrinho de compras -->
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <h1>Produtos</h1>
    <div class="table-container">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Descrição</th>
                    <th>Marca</th>
                    <th>Valor</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produto" items="${produtos}">
                <tr>
                    <td>${produto.id}</td>
                    <td>${produto.desc}</td>
                    <td>${produto.marca}</td>
                    <td>${produto.valor}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
