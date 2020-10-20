package br.com.zup.principalLojaSeuJose;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.zup.lojaSeuJoseDAO.PecasDAO;
import br.com.zup.lojaSeuJoseDAO.VendaDAO;
import br.com.zup.lojaSeuJoseEnums.Categoria;
import br.com.zup.pojo.Peca;
import br.com.zup.pojo.Venda;

public class Principal {

	public static final String MENUPRINCIPAL=(
			"===== Sistema de Gerenciamento =====\n\n" 
				+ "Escolha uma opção\n"
				+ "1 - Gerenciamento de Mercadoria\n" 
				+ "2 - Gerenciamento de Vendas\n" 
				+ "0 - Sair"
			);
	
	public static final String MENUGERENCIAESTOQUE=(
			"===== Controle de Estoque =====\n\n" 
					+ "Escolha uma opção\n" 
					+ "1 - Cadastrar nova Peça\n"
					+ "2 - Listagem de Estoque\n"
					+ "3 - Busca Peça pelo código de barras\n"
					+ "4 - Busca Peça por inicio do nome\n"
					+ "5 - Busca Peça de carro específico\n"
					+ "6 - Busca Peça pela categoria\n"
					+ "7 - Remover Peças\n"
					+ "0 - Voltar ao Menu Principal"
			);
	
	public static final String MENUGERENCIAVENDAS=(
			"===== Controle de Vendas =====\n\n" 
					+ "Escolha uma opção\n" 
					+ "1 - Realizar uma Venda\n"
					+ "2 - Gerar relatório de vendas diárias\n"
					+ "0 - Voltar ao Menu Principal"
			);
	
	public static void cadastraPeca(Scanner teclado) throws SQLException {

		System.out.println("Digite o codigo de barra do produto a ser cadastrado:");
		int codBarra = teclado.nextInt();
		System.out.println("Digite o nome da peça:");
		String nome = teclado.next();
		System.out.println("Para qual carro é essa peça?");
		String carroPeca = teclado.next();
		System.out.println("Qual a marca da peça?");
		String marca = teclado.next();
		System.out.println("Digite o preço de custo: ");
		double precoCusto = teclado.nextDouble();
		System.out.println("Digite o preço de venda: ");
		double precoVenda = teclado.nextDouble();
		System.out.println("Quantas peças dessa você tem para o estoque?");
		int estoque = teclado.nextInt();
		Categoria categoria = defineCategoria(teclado);
		
		
		Peca pecaCadastrada = new Peca();
		pecaCadastrada.setCodigoBarras(codBarra);
		pecaCadastrada.setNome(nome);
		pecaCadastrada.setModeloCarro(carroPeca);
		pecaCadastrada.setFabricante(marca);
		pecaCadastrada.setPrecoCusto(precoCusto);
		pecaCadastrada.setPrecoVenda(precoVenda);
		pecaCadastrada.setQuantidadeEstoque(estoque);
		pecaCadastrada.setCategoria(categoria);

		PecasDAO pecaDao = new PecasDAO();
		pecaDao.inserirPeca(pecaCadastrada);

		System.out.println("A peça " + nome + " foi inserida no banco de dados!\n");
	}

	public static Categoria defineCategoria(Scanner teclado) {
		
		int escolheCategoria;		
		
		do {
			System.out.println("Escolha uma categoria:\n"
					+ "1 - Motor\n"
					+ "2 - Funilaria\n"
					+ "3 - Performace\n"
					+ "4 - Suspenção\n"
					+ "5 - Acessórios\n");
			escolheCategoria = teclado.nextInt();
			
			switch (escolheCategoria) {
			case 1: {
				return Categoria.MOTOR;
			}
			
			case 2: {
				return Categoria.FUNILARIA;
			}
			
			case 3: {
				return Categoria.PERFORMACE;
			}
			
			case 4: {
				return Categoria.SUSPENCAO;
			}
			
			case 5: {
				return Categoria.ACESSORIOS;
			}
			
			default:
				System.out.println("Opção Inválida");
				return null;
			}
		} while(escolheCategoria != 0);
		
	}
	
	public static void listaPecasBancoDados() throws SQLException {
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.listaPecasEstoque();
		
		System.out.println("-----------------------------------------\n"
		 		 + "              LISTA DE PEÇAS             \n"
				 + "-----------------------------------------\n");
		for (Peca peca : listagem) {
			
			System.out.println(peca.mostraDados());
		}
	}
	
	public static void buscaPecaByCodigo(Scanner teclado) throws SQLException {
		
		System.out.println("Qual o código de barras do produto que\n"
				+ "você quer encontrar?");
		int codigoProcurado = teclado.nextInt();
		
		PecasDAO pecaProcurada = new PecasDAO();
		Peca pecaEncontrada = pecaProcurada.buscaPecaByCodigo(codigoProcurado);
		
		System.out.println(pecaEncontrada.mostraDados());
	}

	public static void buscaPecasByTrechoNome(Scanner teclado) throws SQLException {
		System.out.println("Digite a(s) letra(s) iniciais do nome da Peça: ");
		String trechoNomePeca = teclado.next();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaByTrechoNome(trechoNomePeca);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}	
	}
	
	public static void buscaPecasCarroEspecifico(Scanner teclado) throws SQLException {
		System.out.println("Digite o nome do carro: ");
		String nomeCarro = teclado.next();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaCarroEspecifico(nomeCarro);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}
	}

	public static void buscaPecasByCategoria(Scanner teclado) throws SQLException {
		System.out.println("Qual a categoria de peças que deseja encontrar?");
		
		String categoria = defineCategoria(teclado).toString();
		
		PecasDAO pecasDB = new PecasDAO();
		List<Peca> listagem = pecasDB.buscaPecaByCategoria(categoria);
		
		for (Peca pecas : listagem) {
			System.out.println(pecas.mostraDados());
		}
	}
	
	public static void removerPecas(Scanner teclado) throws SQLException {
		
		System.out.println("Digite o codigo de barras da peça que deseja remover: ");
		int codigoBarras = teclado.nextInt();
		
		PecasDAO pecaDB = new PecasDAO();
		pecaDB.removePeca(codigoBarras);
		System.out.println("A peça referente ao código de barra "+codigoBarras+" foi removida do banco de dados.");
	}
		
	public static void gerenciamentoEstoque(Scanner teclado) throws SQLException {
		
		int opcaoMenuEstoque = 0;
		do {
			System.out.println(MENUGERENCIAESTOQUE);
			opcaoMenuEstoque = teclado.nextInt();
			switch (opcaoMenuEstoque) {
			
			case 1: {

				cadastraPeca(teclado);
				break;
			}
			
			case 2: {
				
				listaPecasBancoDados();
				break;
			}
			
			case 3: {
										
				buscaPecaByCodigo(teclado);
				break;
			}
			
			case 4: {
				
				buscaPecasByTrechoNome(teclado);
				break;
			}
			
			case 5: {
				
				buscaPecasCarroEspecifico(teclado);
				break;
			}
			
			case 6: {
				
				buscaPecasByCategoria(teclado);
				break;
			}
			
			case 7: {
				
				removerPecas(teclado);
				break;
			}
			
			case 0: {
				return;
			}
			default:
				System.out.println("Opção Inválida");
			}
		}while (opcaoMenuEstoque != 0);
	}
		
	
	public static void main(String[] args) throws SQLException, IOException {

		Scanner teclado = new Scanner(System.in);
		List<Venda> relatorioDiario = new ArrayList<>();
		double faturamentoDiario = 0;
				
		int opcaoMenuPrincipal = 0;
			
		do {
			System.out.println(MENUPRINCIPAL);
			opcaoMenuPrincipal = teclado.nextInt();
			
			switch (opcaoMenuPrincipal) {
			
			case 1: {
				gerenciamentoEstoque(teclado);
				break;
			}
			
			case 2: {
				
				int opcaoMenuVendas = 0;
				
				do {
					System.out.println(MENUGERENCIAVENDAS);
					opcaoMenuVendas = teclado.nextInt();
					
					
					switch (opcaoMenuVendas) {
					case 1: {
						
						System.out.println("Digite o código de barras da peça: ");
						int codigoBarras = teclado.nextInt();
						System.out.println("Quantas unidades o cliente vai levar?");
						int qtdComprada = teclado.nextInt();
						
						VendaDAO realizarVenda = new VendaDAO();
						Venda venda = realizarVenda.realizarVenda(codigoBarras, qtdComprada);
						faturamentoDiario += venda.getValorCompra();
						relatorioDiario.add(venda);
						
						System.out.println("Venda executada com sucesso");	
						}
						break;
					
					
					case 2: {
						
						System.out.println("-----------------------------------------\n"
								+ "             RELATÓRIO DE VENDAS         \n"
								+ "-----------------------------------------\n"
								+ "Código  -    Nome     - Qtd.  - Valor(R$)\n");
					
						for (Venda venda : relatorioDiario ) {
						System.out.println(venda.gravaVenda());	
						}
						
						System.out.printf("\nFATURAMENTO DO DIA: R$%.2f",faturamentoDiario);
						
						break;
					}
					
					case 0: {
						
						break;
					}
					
					default:
						System.out.println("Opção Inválida");
					}
					
				} while (opcaoMenuVendas != 0);
				break;
			}
			
			case 0: {
				
					
				String nomeArquivo = "Relatorio-Diario.txt";
				FileWriter arquivo = new FileWriter("relatorios-de-vendas/"+nomeArquivo, true);
				
//				File file = new File("/relatorios-de-vendas"); 
//				int contaArquivos = file.listFiles().length;
//				Aqui faltou alguma coisa pra contar os arquivos e atribuir o valor do dia, não consegui achar o problema;
//				Retirei o import pra sumir o warning

				arquivo.append(String.format("Vendas dia: \n"
						+ "Código  -       Nome       - Quantidade -   Valor(R$)\n"));
				
				for (Venda venda : relatorioDiario) {
					
					arquivo.append(String.format(venda.gravaVenda()+"\n"));
				}
				arquivo.append(String.format("\nFATURAMENTO DO DIA: R$%.2f",faturamentoDiario));
				
				System.out.println("Suas vendas deste dia foram armazenadas em um arquivo\n");
				
				arquivo.close();
				
				
				System.out.println("O programa foi encerrado");
				break;
			}
			
			default:
				System.out.println("Opção Inválida");
			}
		} while(opcaoMenuPrincipal != 0);

		
		teclado.close();
		}
	}
