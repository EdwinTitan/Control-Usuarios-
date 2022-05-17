package com.Consultorio.Clinica.DAO;

import com.Consultorio.Clinica.models.Delivers;
import com.Consultorio.Clinica.models.Usuario;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface UsuarioDAO {

    List<Usuario> getUsers();
    List<Usuario> getUserById(Long id);
    void deleteUserById(Long id);
    boolean putUser(Usuario datos);
    void modifyUser(Usuario datos);
    Usuario getUserWithCredentials(Usuario usuario);
    List<Delivers> loadResourcesToDelivers(Delivers deliver);

}
