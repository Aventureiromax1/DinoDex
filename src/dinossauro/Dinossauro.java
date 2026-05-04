package dinossauro;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.dao.DinossauroDAO;
import model.dto.DinossauroDTO;

public class Dinossauro {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        int opcao = -1;

        while (opcao != 0) {
            // Instanciando o scanner localmente no loop do menu
            Scanner scannerMenu = new Scanner(System.in, StandardCharsets.UTF_8.name());

            System.out.println("\n===========================");
            System.out.println("      MENU DINOSSAURO      ");
            System.out.println("===========================");
            System.out.println("1. Cadastrar novo dinossauro");
            System.out.println("2. Listar todos os dinossauros");
            System.out.println("3. Editar um dinossauro");
            System.out.println("4. Excluir um dinossauro");
            System.out.println("0. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = lerNumeroSeguro(scannerMenu);

            switch (opcao) {
                case 1:
                    cadastrarDinossauro();
                    break;
                case 2:
                    listarDinossauros();
                    break;
                case 3:
                    editarDinossauro();
                    break;
                case 4:
                    excluirDinossauro();
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema... Até a próxima aventura!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente digitar um número de 0 a 4.");
            }
            // Evitando fechar o scannerMenu aqui para não matar o System.in do loop principal
        }
    }

    // ------------------------------------------------------------------------
    private static void cadastrarDinossauro() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- CADASTRAR DINOSSAURO ---");
        DinossauroDTO novo = new DinossauroDTO();

        System.out.print("Nome: ");
        novo.setNome(scanner.nextLine());

        System.out.print("Espécie (ex.: Tiranossauro, Triceratops): ");
        novo.setEspecie(scanner.nextLine());

        System.out.print("Peso (kg): ");
        novo.setPeso(lerNumeroSeguro(scanner));

        System.out.print("Altura (m): ");
        novo.setAltura(lerNumeroDecimal(scanner));

        System.out.print("Comprimento (m): ");
        novo.setComprimento(lerNumeroDecimal(scanner));

        System.out.print("Comportamento (ex.: Herbívoro, Carnívoro): ");
        novo.setComportamento(scanner.nextLine());

        dao.inserir(novo);


    }

    private static void listarDinossauros() {
        DinossauroDAO dao = new DinossauroDAO();
        System.out.println("\n--- LISTA DE DINOSSAUROS CADASTRADOS ---");
        List<DinossauroDTO> dinossauros = dao.listar();

        if (dinossauros.isEmpty()) {
            System.out.println("Nenhum dinossauro encontrado no banco de dados.");
            return;
        }

        for (DinossauroDTO d : dinossauros) {
            System.out.println("------------------------------");
            System.out.println("ID: " + d.getId());
            System.out.println("Nome: " + d.getNome());
            System.out.println("Espécie: " + d.getEspecie());
            System.out.println("Peso (kg): " + d.getPeso());
            System.out.println("Altura (m): " + d.getAltura());
            System.out.println("Comprimento (m): " + d.getComprimento());
            System.out.println("Comportamento: " + d.getComportamento());
            System.out.println("Data de criação: " + d.getDataCriacao());
        }
    }

    private static void editarDinossauro() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- EDITAR DINOSSAURO ---");
        listarDinossauros();

        System.out.print("\nDigite o ID do dinossauro que deseja editar: ");
        int idEditar = lerNumeroSeguro(scanner);

        DinossauroDTO editado = new DinossauroDTO();
        editado.setId(idEditar);

        System.out.print("Novo Nome: ");
        editado.setNome(scanner.nextLine());

        System.out.print("Nova Espécie: ");
        editado.setEspecie(scanner.nextLine());

        System.out.print("Novo Peso (kg): ");
        editado.setPeso(lerNumeroSeguro(scanner));

        System.out.print("Nova Altura (m): ");
        editado.setAltura(lerNumeroDecimal(scanner));

        System.out.print("Novo Comprimento (m): ");
        editado.setComprimento(lerNumeroDecimal(scanner));

        System.out.print("Novo Comportamento: ");
        editado.setComportamento(scanner.nextLine());

        dao.alterar(editado);
        scanner.close();
    }

    private static void excluirDinossauro() {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- EXCLUIR DINOSSAURO ---");
        System.out.print("Digite o ID do dinossauro que deseja apagar: ");
        int idExcluir = lerNumeroSeguro(scanner);

        dao.excluir(idExcluir);
        scanner.close();
    }

    // Repassando o scanner criado nos métodos principais para as funções de leitura,
    // garantindo que não estamos criando variáveis globais.
    private static int lerNumeroSeguro(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Erro: Digite apenas números! Tente novamente: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Erro: Digite apenas números inteiros! Tente novamente: ");
            }
        }
    }

    private static double lerNumeroDecimal(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim().replace(",", ".");
                if (input.isEmpty()) {
                    System.out.print("Erro: Digite um número! Tente novamente: ");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Erro: Digite um número válido! Tente novamente: ");
            }
        }
    }
}