<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: #000000; /* Fundo preto */
            color: #f8f9fa; /* Texto claro */
            font-family: Geneva, sans-serif; /* Fonte Geneva */
        }
        .container {
            margin-top: 50px;
        }
        .table-container {
            background-color: #212529; /* Fundo escuro para a tabela */
            padding: 20px;
            border-radius: 10px;
        }
        h1 {
            font-size: 3rem; /* Aumenta o tamanho da fonte do título */
            text-align: center; /* Centraliza o texto */
            color: #f8f9fa;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7); /* Sombra do texto para maior visibilidade */
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
