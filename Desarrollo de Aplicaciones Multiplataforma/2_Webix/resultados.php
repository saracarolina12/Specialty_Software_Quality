<?php
    $mysqli = new mysqli("localhost","alumnos","app.2023",
        "sara");
    $sentencia = $mysqli->prepare("select * from Peliculas where 1 = ?");
    $variable = 1;
    $sentencia->bind_param("i",$variable);
    $sentencia->execute();
    $resultados = $sentencia->get_result();
    $arreglo = array();
    while($fila =mysqli_fetch_assoc($resultados)) {
        $arreglo[] = $fila;
    }
    echo json_encode($arreglo);
?>
