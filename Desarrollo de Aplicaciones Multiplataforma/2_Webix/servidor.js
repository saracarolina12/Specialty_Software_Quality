const express = require('express')
var cors = require('cors')
const app = express()
const port = 8088

app.use(cors())

app.post('/servicioweb', (req, res) => {
    res.send(req.params);
})

app.get('/', (req, res) => {
    res.send('Hola...!')
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})      