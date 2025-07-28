**Proyecto Llibreria**

Integrates
Cristhopher Orozco
Dennis Andrade

Resumen
Este es un proyecto de gestion de libros de una libreria con el fin de gestionar los usuarios que utilizan libros, 
gestionar los libros disponibres y prestados, ataves del framework de spring tools.

Instrucciones de uso.
Dado que este proyecto esta en fase de pruebas requiere de instrucciones de uso muy especificas las cuales son:
1.- Tener spring boot con java 17 y su respectivo jdk ya que es la verion mas estable para el proyecto.(java jdk 17.0.13)
**https://www.oracle.com/java/technologies/javase/jdk17-0-13-later-archive-downloads.html**
2.- En spring tools se esta utilizado gradle como base para la creacion, por ello se debe ejecutar el proyecto mediante spring boot app.
3.- Tambien se debe crear la base de datos postgres 17 llamada libreria y modificar en el spring tools en el apartado de aplication.properities.
4.- Usar este link para su ejecucion ya que es su ruta por defecto http://localhost:8080/index.html, en caso de modificar los puertos hacerlo tambien en el enlace.
**Base de datos en Mongo**
Se creo la base de datos nueva para mongodb ya que de esta forma se administraran los libros mediante una base de datos nosql, a continuacion dejo los pasos para instalar la base de datos correctamente
-Crear la base de datos
mongo
use llibreria
db.createCollection("libros")
-Agregar manualmente el ejemplo (De momento dado que recien se esta isntaurando mongo)
{
"titulo": "El Principito",
"autor": "Antoine de Saint-Exupéry",
"anio": 1943,
"descripcion": "Una historia poética y filosófica sobre un pequeño príncipe que viaja por planetas.",
"archivo": {
"nombre": "el_principito.pdf",
"tipo": "application/pdf",
"datos": ""
},
"subido_por": "usuario@example.com",
"fecha_subida": "2025-07-28T12:00:00Z"
}
