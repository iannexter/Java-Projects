package br.com.gelatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VendaDAO {
    private static final String URL = "jdbc:mysql://db:3306/gelatos_hibernate";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public void incluirVenda(Venda venda) {
        venda.setDataVenda(LocalDate.now());
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(venda);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public Venda atualizarVenda(Venda venda) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        entityManager.getTransaction().begin();        
        Venda vendaRetorno = entityManager.merge(venda);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        
        return vendaRetorno;
    }

    public void removerVenda(Venda venda) {
        removerPorId(venda.getId());
    }

    public void removerPorId(int id) {
        String deleteVendaGelatoSQL = "DELETE FROM venda_gelato WHERE Venda_id = ?";
        String deleteVendaSQL = "DELETE FROM venda WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement deleteVendaGelatoStmt = connection.prepareStatement(deleteVendaGelatoSQL);
             PreparedStatement deleteVendaStmt = connection.prepareStatement(deleteVendaSQL)) {


            connection.setAutoCommit(false);

            try {
                // tirar objetos da tabela venda_gelato
                deleteVendaGelatoStmt.setInt(1, id);
                deleteVendaGelatoStmt.executeUpdate();

                // tirar objetos da tabela venda
                deleteVendaStmt.setInt(1, id);
                deleteVendaStmt.executeUpdate();

                
                connection.commit();
            } catch (SQLException e) {
                
                connection.rollback();
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Venda> getListEntity() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("gelatos_hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    
        List<Venda> listaVenda = entityManager.createQuery("FROM Venda ORDER BY id ASC", Venda.class).getResultList();
        
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return listaVenda;
    }
}
