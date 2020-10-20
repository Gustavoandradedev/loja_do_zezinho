package br.com.zup.lojaSeuJoseDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.zup.pojo.Peca;

public class PecasDAO {

	EntityManager manager;

	public PecasDAO() {

		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja_de_pecas");
		//this.manager = factory.createEntityManager();
		this.manager = Persistence.createEntityManagerFactory("loja_de_pecas").createEntityManager();
		System.out.println("Conectado");
	}

	public boolean inserirPeca(Peca peca) {

		try {
			manager.getTransaction().begin();
			manager.persist(peca);
			manager.getTransaction().commit();
			
		} catch (EntityExistsException e) {
			System.err.println("Não foi possível cadastrar esse produto");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public List<Peca> listaPecasEstoque() throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			
			Query selecao = manager.createQuery("select p from Peca as p");
		
			listaPecas = selecao.getResultList();
		
		} catch (EntityExistsException e) {
			System.err.println("Não foi possível listar suas peças...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public Peca buscaPecaByCodigo(int codBarras) throws SQLException {
		Peca pecaEncontrada = new Peca();

		try {
			
			pecaEncontrada = manager.find(Peca.class, codBarras);
			
		} catch (EntityExistsException e) {
			System.err.println("Peça não encontrada...");
			System.err.println(e.getMessage());
		}

		return pecaEncontrada;
	}

	public List<Peca> buscaPecaByTrechoNome(String trechoNome) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {

			Query selecao = manager.createQuery("select p from Peca as p"
					+ "where pecas.nome like :trechoNome");
		
			selecao.setParameter("trechoNome", trechoNome+"%");
			listaPecas = selecao.getResultList();
		
		} catch (EntityExistsException e) {
			System.err.println("Nenhuma cidade encontrada...");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaCarroEspecifico(String nomeCarro) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			Query selecao = manager.createQuery("select p from Peca as p"
					+ "where pecas.modelo_do_carro = :modeloCarro");
		
			selecao.setParameter("modeloCarro", nomeCarro);
			listaPecas = selecao.getResultList();
			
		} catch (EntityExistsException e) {
			System.err.println("Não encontramos nenhuma peça para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public List<Peca> buscaPecaByCategoria(String categoria) throws SQLException {

		List<Peca> listaPecas = new ArrayList<>();

		try {
			
			Query selecao = manager.createQuery("select p from Peca as p"
					+ "where pecas.categoria = :categoria");
		
			selecao.setParameter("categoria", categoria);
			listaPecas = selecao.getResultList();
		
		} catch (EntityExistsException e) {
			System.err.println("Não encontramos nenhuma peça para esse carro");
			System.err.println(e.getMessage());
		}
		return listaPecas;
	}

	public boolean removePeca(int codigoBarras) throws SQLException {

		Peca pecaRemovida = new Peca();
		
		try {
			
			pecaRemovida = manager.find(Peca.class, codigoBarras);
			manager.getTransaction().begin();
			manager.remove(pecaRemovida);
			manager.getTransaction().commit();
			
		} catch (EntityExistsException e) {
			System.err.println("Impossível deletar este item");
			System.err.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean atualizaEstoque(int qtdAtualEstoque, int codigoBarras) throws SQLException {

		Peca pecaEstoqueAtualizado = new Peca();
		
		try {
			
			pecaEstoqueAtualizado = manager.find(Peca.class, codigoBarras);
			pecaEstoqueAtualizado.setQuantidadeEstoque(qtdAtualEstoque);
			
			manager.getTransaction().begin();
			manager.merge(pecaEstoqueAtualizado);
			manager.getTransaction().commit();

		} catch (EntityExistsException e) {
			System.err.println("Erro ao dar baixa na peça");
			System.err.println(e.getMessage());
			return false;
		}
		return true;
	}

}
