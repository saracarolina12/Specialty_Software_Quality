<?php
    if($_POST['Accion'] == 'Guardar') {
    try {
        $mysqli = new mysqli("localhost","alumnos","app.2023","sara");
        $sentencia = $mysqli->prepare("INSERT INTO Peliculas (Id, Nivel, Titulo, Ao, Votos) 
        VALUES (0, ?, ?, ?, ?)");
        $sentencia->bind_param("isii", $_POST['Nivel'],$_POST['Titulo'],$_POST['Ao'],$_POST['Votos']);
        $sentencia->execute();
    } catch (Exception $e) {
        echo 'Error:', $e->getMessage(),"\n";
    }
    } else if($_POST['Accion'] == 'Borrar') {
        $ids = $_POST['Id'];
        $ids = str_replace("[","",$ids);
        $ids = str_replace("]","",$ids);
        //"[4,5,6]"
        $mysqli = new mysqli("localhost","alumnos","app.2023","sara");
        $sentencia = $mysqli->prepare("delete from Peliculas where id in (?)");
        $id = explode(",",$ids);
        foreach ($id as &$valor) {
            $sentencia->bind_param("i",$valor);
            $sentencia->execute();
        }
    }else if($_POST['Accion'] == 'Modificar'){
        $mysqli = new mysqli("localhost","alumnos","app.2023","sara");
        $sentencia = $mysqli->prepare("update Peliculas set Nivel = ?, Titulo = ?, Ao = ?, Votos = ? where Id = ?");
        $sentencia->bind_param("isiii", $_POST['Nivel'],$_POST['Titulo'],$_POST['Ao'],$_POST['Votos'], $_POST['id']);
        $sentencia->execute();
    }
?>