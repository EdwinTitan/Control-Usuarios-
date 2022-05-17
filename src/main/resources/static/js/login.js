const d = document;
   async function login(){

    let datos ={};
    //Obtencion de datos directo del DOM
    //Usar REGEX para depurar datos
    datos.email = d.getElementById("InputEmail").value;
    datos.password = d.getElementById("InputPassword").value;

    const request =  await fetch('api/login', {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const result = await request.text();
    console.log(result);

//Retornar objetos del tipo Entity para poder manejar los errores y mensajes desde el servidor...
    if (result != '404'){
                localStorage.token = result;
                localStorage.email = datos.email;
                alert("Login correcto, se redireccionará al dashboard...");
                window.location.href='usuarios.html'
    }   else if(result == '404') {
            alert("Las credenciales insertadas son incorrectas, Favor de reintentar");
        }
        else{
            spam("Hubo un error autenticando las credenciales, intentalo de nuevo...");
        }
}
