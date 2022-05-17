// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
  loadUsers();
});

function putHeaders(){
return {
    'Accept':'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
    };
}

async function deleteUserFunction(id){
    if(!confirm('¿Desea elminiar al usuario?')){
        return;
    }

    const request = await fetch('/api/deleteUser/'+id, {
    method: "DELETE",
    headers: putHeaders()
    });
    location.reload();
}

async function loadUsers(){
    const request = await fetch('/api/getUsers', {
    method: "GET",
    headers: putHeaders()
    });
    const content = await request.json();
    let usersList='';
    let counter=1;
    let trText='';
    for(let usuario of content){
        if(counter %2 == 0){
            trText ='<tr style="background-color: whitesmoke;">';
        }else{
            trText ='<tr>';
        }

        let deleteBtnHTML = '<a href="#" onclick="deleteUserFunction('+usuario.id+')" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>';
        let modifyBtnHTML = '<a href="#" class="btn btn-info btn-circle"><i class="fas fa-info-circle"></i></a>';
        let usuarios =trText+'<td>'+usuario.id
        +'</td><td>'+usuario.nombre
        +'</td><td>'+usuario.apellido
        +'</td><td>'+usuario.telefono
        +'</td><td>'+usuario.email
        +'</td><td>'+deleteBtnHTML +' '+ modifyBtnHTML
        +'</td>';
        usersList+= usuarios;
        counter+=1;
    }
        document.querySelector("#usuarios tbody").outerHTML = usersList;

}
