# Control Usuarios
Aplicacion web para control de usuarios, registro, eliminación y modificación 

Stack de desarrollo
Se utilizó Maven para gestionar dependencias del proyecto, JAVA como lenguaje principal, Springboot como Framework para su desarrollo, Hibernate para gestionar el mapeo de la base de datos y trabajarla desde el servidor creando de esta manera una API para el acceso a los datos a travez de los controladores.

Por otro lado, se utilizó MySQL como manejador de base de datos y por parte del front end se utilizó vanilla JavaScript, HTML y CSS.

## Controladores--
El proyecto cuenta con controladores que se encargan de mapear las peticiones enviadas desde el front a traves de dos archivos principales: -"UsuarioController.java" y -"AuthController.java". 
### UsuarioController.java
Gestiona todas las peticiones REST a los endpoints "/putUser", "/getUsers", "/deleteUser/id", "/modifyUser" y "/loadResources" siendo esta ultima parte de un modulo no terminado para la implementación de un servicio de delivery pensado para futuro.

Los endpoints (como estos mismos infieren) sirven para agregar, obtener, eliminar y modificar a usuarios de la aplicación, cabe mencionar que el mapeo de cada uno requere de información especifica como lo es un parametro dentro del header "Authorization" el cual contendría un token de autenticación JWT y, dependiendo del caso, se requiere dentro del cuerpo de la petición a un objeto con la información de el usuario a tratar o el identificadpr unico del usuario.

### AuthController.java
Pensado solo para autenticaciones dento de la aplicacion, gestiona las peticiones REST al endpoint "api/login" en donde se requiere enviar como parametro dentro del cuerpo de la petición un objeto contenedor de la información del usuario que desea logearse.

Dentro del controlador se realiza la petición a la base de datos haciendo uso de inyeccion de dependencias y, si el usuario es obtenido satisfactoriamente mediante sus credenciales entonces se genera dentro de esta misma clase un tokenJWT y se le retorna directamente al front para ser almacenado y poder ser utilizado como controlador de sesion.
Falta agregar un retorno del controlador del tipo Entity para poder retornar codigos de respuesta desde el servidor de una manera mas organizada.


### UsuarioDao.java
Interfaz creada para su implementación en controladores y acceso a base de datos.
Contiene metodos de acceso a la base de datos 

### UsuarioDaoImplement.java
Clase que implementa a la interfaz UsuarioDao y sus metodos para realizar peticiones a la base de datos.
