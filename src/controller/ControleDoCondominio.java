package controller;

import model.*;
import model.DAO.*;
import util.Teclado;

public class ControleDoCondominio {

	private static String funcionarioLogadoEmail;

	public static void main(String[] args) {
		// Ja que não tem funcionario cadastrado so transforma em comentarios para fazero primeiro cadastro de funcinario
	 if (!autenticarFuncionario()) {
		 System.out.println("\n--- Acesso negado. Encerrando o sistema.");
		 return;
		}

		ControleDoCondominio controle = new ControleDoCondominio();
		int opcao;

		do {
			controle.exibirMenuCompleto();
			opcao = Teclado.lerInt("\nDigite sua opção: ");

			switch (opcao) {

			case 1:
				cadastrarMorador();
				break;
			case 2:
				listarMoradores();
				break;
			case 3:
				atualizarMorador();
				break;
			case 4:
				apagarMorador();
				break;

			case 5:
				cadastrarVeiculo();
				break;
			case 6:
				listarVeiculos();
				break;
			case 7:
				atualizarVeiculo();
				break;
			case 8:
				apagarVeiculo();
				break;

			case 9:
				cadastrarApartamento();
				break;
			case 10:
				listarApartamentos();
				break;
			case 11:
				atualizarApartamento();
				break;
			case 12:
				apagarApartamento();
				break;

			case 13:
				registrarInfracao();
				break;
			case 14:
				listarMultas();
				break;
			case 15:
				apagarMulta();
				break;

			case 16:
				cadastrarFuncionario();
				break;

			case 0:
				System.out.println("Sistema encerrado.");
				break;
			default:
				System.out.println("Opção inválida!");
				break;
			}

			if (opcao != 0) {
				Teclado.lerTexto("\nPressione Enter para voltar ao menu...");
			}

		} while (opcao != 0);
	}

	public void exibirMenuCompleto() {
		System.out.println("\n========= CONTROLE DO CONDOMÍNIO VIVER BEM  =========");
		System.out.println("--- Moradores ---");
		System.out.println("1. Cadastrar Morador | 2. Listar Moradores | 3. Atualizar Morador | 4. Apagar Morador");
		System.out.println("\n--- Veículos ---");
		System.out.println("5. Cadastrar Veículo | 6. Listar Veículos | 7. Atualizar Veículo | 8. Apagar Veículo");
		System.out.println("\n--- Apartamentos ---");
		System.out.println(
				"9. Cadastrar Apartamento | 10. Listar Apartamentos | 11. Atualizar Apartamento | 12. Apagar Apartamento");
		System.out.println("\n--- Infrações ---");
		System.out.println("13. Registrar Infração (Aviso/Multa) | 14. Listar Multas | 15. Apagar Multa");
		System.out.println("\n--- Sistema ---");
		System.out.println("16. Cadastrar Novo Funcionário");
		System.out.println("--------------------------------------------------------------------");
		System.out.println("0. Sair do Sistema");
	}

	public static boolean autenticarFuncionario() {
		System.out.println("--- Login do Sistema ---");

		String email = Teclado.lerTexto("E-mail: ");
		String senha = Teclado.lerTexto("Senha: ");
		Funcionario func = FuncionarioDAO.buscarPorLogin(email, senha);
		if (func != null) {

			funcionarioLogadoEmail = func.getEmail();
			System.out.println("\n>>> Bem-vindo(a), " + func.getNome_completo() + "!");
			return true;
		}
		return false;
	}

	public static void cadastrarMorador() {
		System.out.println("\n--- Cadastro de Novo Morador ---");

		Morador m = new Morador();
		m.setCpf(Teclado.lerTexto("CPF: "));
		m.setNome_completo(Teclado.lerTexto("Nome Completo: "));
		m.setEmail(Teclado.lerTexto("E-mail: "));
		m.setTelefones(Teclado.lerTexto("Telefones: "));
		m.setNumero_apartamento(Teclado.lerInt("Número do Apartamento: "));
		if (MoradorDAO.salvar(m) != null)
			System.out.println("Morador cadastrado com sucesso!");
	}

	public static void listarMoradores() {
		System.out.println("\n--- Lista de Moradores Cadastrados ---");
		Morador[] moradores = MoradorDAO.buscarTodos();
		if (moradores == null || moradores.length == 0) {
			System.out.println("Nenhum morador encontrado.");
		} else {
			for (Morador m : moradores) {
				if (m != null)
					System.out.println(m.toString());
			}
		}
	}

	public static void atualizarMorador() {
		System.out.println("\n--- Atualizar Dados do Morador ---");
		String cpf = Teclado.lerTexto("Digite o CPF do morador que deseja atualizar: ");
		System.out.println("Digite os novos dados:");
		Morador m = new Morador();
		m.setCpf(cpf);
		m.setNome_completo(Teclado.lerTexto("Novo Nome Completo: "));
		m.setEmail(Teclado.lerTexto("Novo E-mail: "));
		m.setTelefones(Teclado.lerTexto("Novos Telefones: "));
		m.setNumero_apartamento(Teclado.lerInt("Novo Número do Apartamento: "));
		if (MoradorDAO.atualizar(m) != null)
			System.out.println("Dados do morador atualizados com sucesso!");
	}

	public static void apagarMorador() {
		System.out.println("\n--- Apagar Morador ---");
		String cpf = Teclado.lerTexto("Digite o CPF do morador a ser apagado: ");
		MoradorDAO.apagar(cpf);
		System.out.println("Operação de exclusão de morador enviada.");
	}

	public static void cadastrarVeiculo() {
		System.out.println("\n--- Cadastro de Novo Veículo ---");

		Veiculo v = new Veiculo();
		v.setPlaca(Teclado.lerTexto("Placa: "));
		v.setFabricante(Teclado.lerTexto("Fabricante: "));
		v.setModelo(Teclado.lerTexto("Modelo: "));
		v.setCor(Teclado.lerTexto("Cor: "));
		v.setNumero_apartamento_vaga(Teclado.lerInt("Vaga do Apartamento (nº): "));
		if (VeiculoDAO.salvar(v) != null)
			System.out.println("Veículo cadastrado com sucesso!");
	}

	public static void listarVeiculos() {
		System.out.println("\n--- Lista de Veículos Cadastrados ---");
		Veiculo[] veiculos = VeiculoDAO.buscarTodos();
		if (veiculos == null || veiculos.length == 0) {
			System.out.println("Nenhum veículo encontrado.");
		} else {
			for (Veiculo v : veiculos) {
				if (v != null)
					System.out.println(v.toString());
			}
		}
	}

	public static void atualizarVeiculo() {
		System.out.println("\n--- Atualizar Dados do Veículo ---");
		String placa = Teclado.lerTexto("Digite a placa do veículo que deseja atualizar: ");
		System.out.println("Digite os novos dados:");
		Veiculo v = new Veiculo();
		v.setPlaca(placa);
		v.setFabricante(Teclado.lerTexto("Novo Fabricante: "));
		v.setModelo(Teclado.lerTexto("Novo Modelo: "));
		v.setCor(Teclado.lerTexto("Nova Cor: "));
		v.setNumero_apartamento_vaga(Teclado.lerInt("Nova Vaga de Apartamento (nº): "));
		if (VeiculoDAO.atualizar(v) != null)
			System.out.println("Dados do veículo atualizados com sucesso!");
	}

	public static void apagarVeiculo() {
		System.out.println("\n--- Apagar Veículo ---");
		String placa = Teclado.lerTexto("Digite a placa do veículo a ser apagado: ");
		VeiculoDAO.apagar(placa);
		System.out.println("Operação de exclusão de veículo enviada.");
	}

	public static void cadastrarApartamento() {
		System.out.println("\n--- Cadastro de Novo Apartamento ---");

		Apartamento apto = new Apartamento();
		apto.setNumero(Teclado.lerInt("Número do Apartamento: "));
		apto.setTipo_responsavel(Teclado.lerTexto("Responsável é 'proprietario' ou 'inquilino'?"));
		if (ApartamentoDAO.salvar(apto) != null)
			System.out.println("Apartamento cadastrado com sucesso!");
	}

	public static void listarApartamentos() {
		System.out.println("\n--- Lista de Apartamentos Cadastrados ---");
		Apartamento[] apartamentos = ApartamentoDAO.buscarTodos();
		if (apartamentos == null || apartamentos.length == 0) {
			System.out.println("Nenhum apartamento encontrado.");
		} else {
			for (Apartamento a : apartamentos) {
				if (a != null)
					System.out.println(a.toString());
			}
		}
	}

	public static void atualizarApartamento() {
		System.out.println("\n--- Atualizar Apartamento ---");
		int numero = Teclado.lerInt("Digite o número do apartamento a ser atualizado: ");
		String tipo = Teclado.lerTexto("Novo status do responsável ('proprietario' ou 'inquilino'): ");
		Apartamento apto = new Apartamento();
		apto.setNumero(numero);
		apto.setTipo_responsavel(tipo);
		if (ApartamentoDAO.atualizar(apto) != null)
			System.out.println("Apartamento atualizado com sucesso!");
	}

	public static void apagarApartamento() {
		System.out.println("\n--- Apagar Apartamento ---");
		int numero = Teclado.lerInt("Digite o número do apartamento a ser apagado: ");
		ApartamentoDAO.apagar(numero);
		System.out.println("Operação de exclusão de apartamento enviada.");
	}

	public static void registrarInfracao() {
		System.out.println("\n--- Registrar Infração ---");
		String cpf = Teclado.lerTexto("Digite o CPF do morador infrator: ");

		int totalAvisos = AvisoDAO.contarAvisosPorCpf(cpf);

		if (totalAvisos == 0) {
			Aviso novoAviso = new Aviso();
			novoAviso.setCpf_morador_infrator(cpf);
			novoAviso.setEmail_funcionario(funcionarioLogadoEmail);
			AvisoDAO.salvar(novoAviso);
			System.out.println("Este morador não possuía avisos. Um AVISO foi emitido.");
		} else {
			Multa novaMulta = new Multa();
			novaMulta.setCpf_morador_infrator(cpf);
			novaMulta.setEmail_funcionario(funcionarioLogadoEmail);
			MultaDAO.salvar(novaMulta);
			System.out.println("Morador reincidente. Uma MULTA de R$ 200,00 foi gerada."); //
		}
	}

	public static void listarMultas() {
		System.out.println("\n--- Histórico de Multas ---");
		Multa[] multas = MultaDAO.buscarTodas();
		if (multas == null || multas.length == 0) {
			System.out.println("Nenhuma multa encontrada.");
		} else {
			for (Multa m : multas) {
				if (m != null)
					System.out.println(m.toString());
			}
		}
	}

	public static void apagarMulta() {
		System.out.println("\n--- Apagar Registro de Multa ---");
		int id = Teclado.lerInt("Digite o ID da multa a ser apagada: ");
		MultaDAO.apagar(id);
		System.out.println("Operação de exclusão de multa enviada.");
	}

	public static void cadastrarFuncionario() {
		System.out.println("\n--- Cadastro de Novo Funcionário ---");
		Funcionario novoFunc = new Funcionario();
		novoFunc.setEmail(Teclado.lerTexto("E-mail: "));
		novoFunc.setNome_completo(Teclado.lerTexto("Nome Completo: "));
		novoFunc.setSenha(Teclado.lerTexto("Senha: "));
		if (FuncionarioDAO.salvar(novoFunc) != null)
			System.out.println("Funcionário cadastrado com sucesso!");
	}
}