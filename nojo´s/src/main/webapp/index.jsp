<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Criar Produto</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #000000;
            color: #f8f9fa;
            font-family: Arial, sans-serif;
            text-rendering: optimizeLegibility;
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
            font-weight: 300;
            margin-left: 20px;
            transition: color 0.3s;
        }
        .nav-link:hover {
            color: #e65100;
        }
        .dropdown-menu {
            background-color: #2c2c2c;
        }
        .dropdown-item {
            color: #ffffff;
            transition: background-color 0.3s, color 0.3s;
        }
        .dropdown-item:hover {
            background-color: #1c1c1c;
            color: #ffffff;
        }
        .form-container {
            background-color: #212529;
            padding: 30px;
            border-radius: 10px;
            margin-top: 20px;
            max-width: 500px;
            margin-left: auto;
            margin-right: auto;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.5);
        }
        .form-label {
            color: #f8f9fa;
            font-weight: bold;
        }
        .title {
            font-size: 3rem;
            text-align: center;
            margin-top: 20px;
            color: #f8f9fa;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
        }
        .link_wrapper,
        .delete_wrapper {
            display: inline-block;
            position: relative;
            padding: 10px 20px;
            text-decoration: none;
            color: #ffffff; /* Texto branco */
            background-color: #ff6f00; /* Laranja */
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.3s;
            margin-top: 20px;
            border: none;
            font-weight: bold; /* Texto mais destacado */
            font-size: 1.1rem; /* Aumenta o tamanho do texto */
            width: 100%; /* Ajusta a largura para 100% */
        }
        .link_wrapper:hover {
            background-color: #e65100; /* Laranja mais escuro */
            transform: scale(1.05);
        }
        .delete_wrapper {
            background-color: #dc3545; /* Vermelho */
        }
        .delete_wrapper:hover {
            background-color: #c82333; /* Vermelho mais escuro */
            transform: scale(1.05);
        }
        .form-group input {
            border: 1px solid #ff6f00; /* Borda laranja */
            border-radius: 5px;
            background-color: #2c2c2c; /* Fundo do input */
            color: #ffffff; /* Texto claro no input */
        }
        .form-group input:focus {
            border-color: #e65100; /* Borda laranja mais escura no foco */
            background-color: #3c3c3c; /* Fundo mais claro no foco */
            outline: none;
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
                <a class="nav-link" href="#">Inicio</a>
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
            <li class="nav-item cart-icon">
                <a class="nav-link" href="#">
                    <i class="fas fa-shopping-cart"></i>
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <h2 class="title">Criar Produto</h2>
    <div class="form-container">
        <form action="/create-prod" method="post" id="productForm">
            <div class="form-group">
                <label for="prod-desc" class="form-label">Descricao do Produto:</label>
                <input type="text" name="prod-desc" id="prod-desc" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="prod-marca" class="form-label">Marca do Produto:</label>
                <input type="text" name="prod-marca" id="prod-marca" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="prod-valor" class="form-label">Valor R$:</label>
                <input type="number" name="prod-valor" id="prod-valor" step="0.01" required class="form-control">
            </div>
            <button type="submit" class="link_wrapper">Registrar</button>
        </form>
    </div>
</div>


<div class="container mt-5">
    <h2 class="title">Deletar Produto</h2>
    <div class="form-container">
        <form action="/delete-prod" method="post" id="deleteForm">
            <div class="form-group">
                <label for="prod-id" class="form-label">ID do Produto a ser Deletado:</label>
                <input type="text" name="id" id="id" class="form-control" required>
            </div>
            <button type="submit" class="delete_wrapper">Deletar</button>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>