package com.Consultorio.Clinica.DAO;

import com.Consultorio.Clinica.models.Delivers;
import com.Consultorio.Clinica.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDAOImplement implements UsuarioDAO {



    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsers() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Usuario> getUserById(Long id) {
        String query = "FROM Usuario WHERE id =" + id;
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUserById(Long id) {

        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public boolean putUser(Usuario usuario) {
        Usuario user = entityManager.find(Usuario.class, usuario.getEmail());
        if(user.getEmail() == usuario.getEmail()){
            //Email ya existente...
            return false;
        }else {
            entityManager.merge(usuario);
            return true;
        }
    }

    @Override
    public void modifyUser(Usuario datos) {
        entityManager.refresh(datos.getId());
    }

    @Override
    public Usuario getUserWithCredentials(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email";

        List<Usuario> result = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(result.isEmpty()) {return null;}

        String passwordHashed = result.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return (argon2.verify(passwordHashed, usuario.getPassword()))
            ? result.get(0)
            :  null;
    }

    @Override
    public List<Delivers> loadResourcesToDelivers(Delivers deliver) {
        String query = "FROM Delivers WHERE status = :status";
        List<Delivers> result = entityManager.createQuery(query)
                .setParameter("status", true)
                .getResultList();
        return result;

    }



}
