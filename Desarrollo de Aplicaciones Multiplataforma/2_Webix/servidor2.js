const { MongoClient,ObjectId } = require('mongodb')
const express = require('express')
const app = express()
var cors = require('cors')
const bodyParser = require('body-parser');
const port = 8088 // 8001 en adelante..

const uri = "mongodb://localhost:27017";
const cliente = new MongoClient(uri);

async function insertar(datos,res) {
	try {
		await cliente.connect();
		const coleccion = cliente.db("sara").collection("post");
		await coleccion.insertOne(datos);
		return res.status(201).json({sucess:true});
	} catch(error) { 
		return res.status(400).json({sucess:false,error}); 
	} finally { await cliente.close(); }
}
async function modificar(idBusqueda,datos,res) {
	try {
		await cliente.connect();
		const coleccion = cliente.db("sara").collection("post");
		const id = new ObjectId(idBusqueda);
		delete datos._id;
		await coleccion.updateMany({_id:id},{$set:datos});
		return res.status(201).json({sucess:true});
	} catch(error) { 
		console.log(error);
		return res.status(400).json({sucess:false,error}); 
	} finally { await cliente.close(); }
}
async function eliminar(datosBusqueda,res) {
	try {
		await cliente.connect();
		const coleccion = cliente.db("sara").collection("post");
		await coleccion.deleteMany(datosBusqueda);
		return res.status(201).json({sucess:true});
	} catch(error) { 
		return res.status(400).json({sucess:false,error}); 
	} finally { await cliente.close(); }
}
async function buscar(datosBusqueda, res) {
	try {
		await cliente.connect();
		const coleccion = cliente.db("sara").collection("post");	
		console.log(datosBusqueda);
		const resultados = await coleccion.find(datosBusqueda).toArray();
		return res.send(resultados);
	} catch(error) { 
		return res.status(400).json({sucess:false,error});
	} finally {
		await cliente.close();
	}
}
app.use(cors());
//app.use(express.json());
//app.use(express.urlencoded({ extended:true }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended:true }));

app.post('/guardar',(req, res) => {	
	insertar(JSON.parse(req.body.datos),res);
});
app.post('/borrar',(req, res) => {
	eliminar(JSON.parse(req.body.datosBusqueda),res);
});
app.post('/modificar',(req, res) => {
	modificar(req.body.idBusqueda,JSON.parse(req.body.datos),res);
});
app.post('/buscar',(req, res) => {
	buscar(req.body.datosBusqueda,res)
});
app.get('/buscar',(req, res) => {
	buscar({},res)
});
app.get('/', (req, res) => {
  res.send('Hola ....')
})

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`)
})