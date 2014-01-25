package br.com.dextraining.dao;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import br.com.dextraining.domain.Funcionario;
import br.com.dextraining.domain.UF;
import br.com.dextraining.domain.Usuario;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;

public class EntityManagerFactoryWrapperTest {

    @Test
    public void testaEntityManager() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("JOAO");
        funcionario.getEndereco().setCidade("Campinas");
        funcionario.getEndereco().setEstado(UF.SP);
        funcionario.getEndereco().setRua("Rua 1");
        funcionario.setCpf("111.111.111-11");
        funcionario.setSalario(1559.80);

        EntityManager em = EntityManagerFactoryWrapper.getEntityManager();

        Usuario user = new Usuario();
        user.setLogin("teste");
        user.setSenha("teste");
        user.setFuncionario(funcionario);

        // em.getTransaction().begin();
        EntityManagerFactoryWrapper.init();
        em.persist(user);
        EntityManagerFactoryWrapper.commit();
        // em.getTransaction().commit();

        Usuario userFound = em.find(Usuario.class, user.getId());

        Assert.assertEquals(user, userFound);
    }
}
