### Base de datos
se debe crear una bd en mysql llamada carsdb para el funcionamiento de la app

### Crear marca
Método POST 
URL http://localhost:8020/brands
JSON: {

	"name": "Marca 2"

}
### Crear Auto
Método POST
URL http://localhost:8020/api
JSON: {
"model":"Modelo 2",
"description":"prueba descripcion marca 2",
"price": "78500262",
"mileage":"369888",
"brands":{

		"brand_id":"1"
	}


}
### Consultar Auto por ID
Método GET
URL: http://localhost:8020/api/1

### Consultar todos los autos
Método GET
URL: http://localhost:8020/api