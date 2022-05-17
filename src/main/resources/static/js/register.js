const d = document;

   async function registrarUsuario(){

    let datos ={};
    //Obtencion de datos directo del DOM
    //Aplicar REGEX para depurar los datos...
    datos.nombre = d.getElementById("exampleFirstName").value;
    datos.apellido = d.getElementById("exampleLastName").value;
    datos.email = d.getElementById("exampleInputEmail").value;
    datos.password = d.getElementById("exampleInputPassword").value;
    let rPassword = d.getElementById("exampleRepeatPassword").value;
    if(rPassword != datos.password){
    alert('Los Passwords introducidos son incorrectos');
    return;
    }
    const request =  await fetch('api/putUser', {
        method: 'POST',
        headers:{
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert("¡La cuenta ha sido creada con exito!");
    window.location.href='login.html'

}