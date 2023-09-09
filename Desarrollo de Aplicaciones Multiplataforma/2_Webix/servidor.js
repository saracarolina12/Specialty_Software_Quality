const mysql = require('mysql2');
const express = require('express')
const app = express()
const port = 8088

var cors = require('cors')


var con = mysql.createConnection({ host: "localhost", user: "alumnos", password: "app.2023", database: "sara" });

app.use(cors())
app.use(express.urlencoded({
    extended: true
}))

app.get('/buscar', (req, res) => {
    // con.connect(function (err) {
    //     if (err) throw err;
    //     console.log("Connected!");
    // });
    con.connect(function (err) {
        if (err) throw err;
        con.query("SELECT * FROM Peliculas", function (err, result, fields) {
            if (err) throw err;
            res.send(result)
        });
    });
})

app.post('/guardar', (req, res) => {
    con.execute(
        `INSERT INTO Peliculas (Id, Nivel, Titulo, Ao, Votos) VALUES (0, ?, ?, ?, ?)`,
        [req.body.Nivel, req.body.Titulo, req.body.Ao, req.body.Votos],
        function (err, result, fields) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into database');
            }
            res.send("mensaje c:");
        }
    );
});

app.post('/modificar', (req, res) => {
    con.connect(function (err) {
        if (err) throw err;
        con.execute("UPDATE Peliculas SET Nivel = ?, Titulo = ?, Ao = ?, Votos = ? WHERE id = ?",
            [
                req.body.Nivel,
                req.body.Titulo,
                req.body.Ao,
                req.body.Votos,
                req.body.id
            ],
            function (err, result, fields) {
                if (err) throw err;
                res.send("Pelicula modificada exitosamente.");
            }
        )
    })
})

app.post('/borrar', (req, res) => {
    var id = req.body.id;
    // console.log(id);
    id = id.replace("[", "");
    id = id.replace("]", "");
    con.execute(
        `delete from Peliculas where id = ?`,
        [id],
        function (err, result, fields) {
            if (err) {
                console.error(err);
                return res.status(500).send('Error inserting data into database');
            }
            res.send("mensaje c:");
        }
    );
});

app.post('/servicioweb', (req, res) => {
    res.send(req.body);
})


app.get('/', (req, res) => {
    res.send('Hola Mundo c:')
})

app.listen(port, () => {
    console.log(`Listening on port ${port}`)
})