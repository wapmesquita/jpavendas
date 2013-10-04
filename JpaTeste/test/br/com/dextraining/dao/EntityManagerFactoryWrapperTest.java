package br.com.dextraining.dao;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Usuario;

public class EntityManagerFactoryWrapperTest {

    @Test
    public void testaEntityManager() {
        EntityManager em = EntityManagerFactoryWrapper.getEntityManager();

        Usuario user = new Usuario();
        user.setLogin("teste");
        user.setSenha("teste");

        // em.getTransaction().begin();
        EntityManagerFactoryWrapper.init();
        em.persist(user);
        EntityManagerFactoryWrapper.commit();
        // em.getTransaction().commit();

        Usuario userFound = em.find(Usuario.class, user.id);

        Assert.assertEquals(user, userFound);
    }
}
