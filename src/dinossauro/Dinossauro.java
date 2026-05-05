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
        Scanner scannerPrincipal = new Scanner(System.in, "UTF-8");
        while (opcao != 0) {


            System.out.println("\n===========================");
            System.out.println("      MENU DINOSSAURO      ");
            System.out.println("===========================");
            System.out.println("1. Cadastrar novo dinossauro");
            System.out.println("2. Listar todos os dinossauros");
            System.out.println("3. Editar um dinossauro");
            System.out.println("4. Excluir um dinossauro");
            System.out.println("0. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = lerNumeroSeguro(scannerPrincipal);

            switch (opcao) {
                case 1:
                    cadastrarDinossauro(scannerPrincipal);
                    break;
                case 2:
                    listarDinossauros();
                    break;
                case 3:
                    editarDinossauro(scannerPrincipal);
                    break;
                case 4:
                    excluirDinossauro(scannerPrincipal);
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema... Até a próxima aventura!");
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente digitar um número de 0 a 4.");
            }
        }
        scannerPrincipal.close();
    }

    // ------------------------------------------------------------------------
    private static void cadastrarDinossauro(Scanner scannerPrincipal) {
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- CADASTRAR DINOSSAURO ---");
        DinossauroDTO novo = new DinossauroDTO();

        System.out.print("Nome: ");
        novo.setNome(scannerPrincipal.nextLine());

        System.out.print("Espécie (ex.: Tiranossauro, Triceratops): ");
        novo.setEspecie(scannerPrincipal.nextLine());

        System.out.print("Peso (kg): ");
        novo.setPeso(lerNumeroSeguro(scannerPrincipal));

        System.out.print("Altura (m): ");
        novo.setAltura(lerNumeroDecimal(scannerPrincipal));

        System.out.print("Comprimento (m): ");
        novo.setComprimento(lerNumeroDecimal(scannerPrincipal));

        System.out.print("Comportamento (ex.: Herbívoro, Carnívoro): ");
        novo.setComportamento(scannerPrincipal.nextLine());

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

    private static void editarDinossauro(Scanner scannerPrincipal) {
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- EDITAR DINOSSAURO ---");
        listarDinossauros();

        System.out.print("\nDigite o ID do dinossauro que deseja editar: ");
        int idEditar = lerNumeroSeguro(scannerPrincipal);

        DinossauroDTO editado = new DinossauroDTO();
        editado.setId(idEditar);

        System.out.print("Novo Nome: ");
        editado.setNome(scannerPrincipal.nextLine());

        System.out.print("Nova Espécie: ");
        editado.setEspecie(scannerPrincipal.nextLine());

        System.out.print("Novo Peso (kg): ");
        editado.setPeso(lerNumeroSeguro(scannerPrincipal));

        System.out.print("Nova Altura (m): ");
        editado.setAltura(lerNumeroDecimal(scannerPrincipal));

        System.out.print("Novo Comprimento (m): ");
        editado.setComprimento(lerNumeroDecimal(scannerPrincipal));

        System.out.print("Novo Comportamento: ");
        editado.setComportamento(scannerPrincipal.nextLine());

        dao.alterar(editado);
    }

    private static void excluirDinossauro(Scanner scannerPrincipal) {
        DinossauroDAO dao = new DinossauroDAO();

        System.out.println("\n--- EXCLUIR DINOSSAURO ---");
        System.out.print("Digite o ID do dinossauro que deseja apagar: ");
        int idExcluir = lerNumeroSeguro(scannerPrincipal);

        dao.excluir(idExcluir);
    }

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