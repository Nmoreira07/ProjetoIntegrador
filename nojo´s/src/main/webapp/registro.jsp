<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Registro</title>
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
        .form-container {
            background-color: #212529;
            padding: 30px;
            border-radius: 10px;
            margin-top: 20px;
            max-width: 400px;
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
        .btn-custom {
            display: inline-block;
            position: relative;
            padding: 10px 20px;
            text-decoration: none;
            color: #ffffff;
            background-color: #ff6f00;
            border-radius: 5px;
            transition: background-color 0.3s, transform 0.3s;
            margin-top: 20px;
            border: none;
            font-weight: bold;
            font-size: 1.1rem;
            width: 100%;
        }
        .btn-custom:hover {
            background-color: #e65100;
            transform: scale(1.05);
        }
        .form-group input {
            border: 1px solid #ff6f00;
            border-radius: 5px;
            background-color: #2c2c2c;
            color: #ffffff;
        }
        .form-group input:focus {
            border-color: #e65100;
            background-color: #3c3c3c;
            outline: none;
        }
        .login-link {
            display: block;
            margin-top: 15px;
            text-align: center;
            color: #ff6f00;
            font-weight: bold;
            text-decoration: underline;
            transition: color 0.3s;
        }
        .login-link:hover {
            color: #e65100;
        }
        .alert {
            color: #fff;
            background-color: #dc3545;
            border: none;
            margin-bottom: 20px;
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
        </ul>
    </div>
</nav>

<div class="container">
    <h2 class="title">Registro</h2>
    <div class="form-container">
        <form action="/registro" method="post" id="registroForm">
            <div class="form-group">
                <label for="email" class="form-label">Email:</label>
                <input type="email" name="email" id="email" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="password" class="form-label">Senha:</label>
                <input type="password" name="password" id="password" class="form-control" required>
            </div>
            <button type="submit" class="btn-custom">Registrar</button>
            <a href="/login" class="login-link">Ja tem conta? Realize o login</a>
        </form>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
