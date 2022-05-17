package com.Consultorio.Clinica.controllers;

import com.Consultorio.Clinica.DAO.UsuarioDAO;
import com.Consultorio.Clinica.Utils.JWTUtils;
import com.Consultorio.Clinica.models.Delivers;
import com.Consultorio.Clinica.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class UsuarioController {
    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    private UsuarioDAO usuarioDAO;

    @RequestMapping(value = "/getUsers")
    public List<Usuario> getUsers(@RequestHeader(value = "Authorization") String token) {
        return jwtUtils.validateJWT(token)
            ? usuarioDAO.getUsers()
                : new ArrayList<>();


    }


    @RequestMapping(value = "/putUser", method = RequestMethod.POST)
    public void registerUser(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        if(usuarioDAO.putUser(usuario)) {
            System.out.println("usuario guardado ,,,");
        } else {
            System.out.println("Email preexistente...");
        }

    }


    @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token,
                           @PathVariable Long id){
        if (!jwtUtils.validateJWT(token))
            return;

        usuarioDAO.deleteUserById(id);

    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public void modifyUser(@RequestHeader(value = "Authorization") String token,
                           @RequestBody Usuario user){
        if (!jwtUtils.validateJWT(token))
            return;

        usuarioDAO.modifyUser(user);

    }


    //metodo para insertar funcionalidad delivery...
    @RequestMapping(value = "/loadResources", method = RequestMethod.GET)
    public void loadResources(@RequestHeader(value = "Authorization") String token,
                              @RequestBody Delivers deliver){

        usuarioDAO.loadResourcesToDelivers(deliver);
    }


}