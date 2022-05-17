package com.Consultorio.Clinica.controllers;

import com.Consultorio.Clinica.DAO.UsuarioDAO;
import com.Consultorio.Clinica.Utils.JWTUtils;
import com.Consultorio.Clinica.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthController {
    @Autowired
    private UsuarioDAO usuarioDao;
    @Autowired
    private JWTUtils jwtUtils;


    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario user) {

        String tokenJWT;
        Usuario loggerUser = new Usuario();
        loggerUser = usuarioDao.getUserWithCredentials(user);
        if (loggerUser != null) {
            tokenJWT = jwtUtils.create(String.valueOf(loggerUser.getId()), loggerUser.getEmail());
            return tokenJWT;
        }
        else{
            return tokenJWT= "404";
        }



    }
}