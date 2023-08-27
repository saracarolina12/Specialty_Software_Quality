<?php
    if($_POST['Accion'] == 'Guardar'){
        $mysqli = new mysqli("localhost", "alumnos", "app.2023", "sara");
        $sentencia = $mysqli -> prepare("INSERT INTO Peliculas (Id, Nivel, Titulo, Ao, Votos) VALUES (0, ?, ?, ?, ?)");
        // isii: integer, string, integer, integer
        $sentencia -> bind_param("isii", $_POST['Nivel'], $_POST['Titulo'], $_POST['Ao'], $_POST['Votos']);
        $sentencia -> execute();
    }
?>