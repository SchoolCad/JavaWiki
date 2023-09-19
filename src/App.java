import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // listas
        List<Category> CategoryList = new ArrayList<Category>();
        List<Tools> ToolsList = new ArrayList<Tools>();

        Tools ToolsPicker = new Tools("Tools.txt");
        // Lendo dados do arquivo Tools.txt
        try {
            String data = ToolsPicker.readAll();
            String[] lines = data.split(";");
            for (String line : lines) {
                String[] parts = line.split(",");
                ToolsPicker.setName(parts[0]);
                ToolsPicker.setDescription(parts[1]);
                ToolsList.add(ToolsPicker);
            }
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }

        Category CategoryPicker = new Category("Category.txt");
        // Lendo dados do arquivo Category.txt
        try {
            String data = CategoryPicker.readAll();
            String[] lines = data.split(";");
            for (String line : lines) {
                String[] parts = line.split(",");
                CategoryPicker.setUsage(parts[0]);
                CategoryList.add(CategoryPicker);
            }
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }


        String auxString, auxString2;
        int rUser;
        Scanner input = new Scanner(System.in);
        do {
            MenuBuilder.clearScreen();
            MenuBuilder.buildMenu("JavaWiki", "1. Ferramentas.;2. Categorias.;3. Sair.");
            rUser = Integer.parseInt(input.nextLine());

            switch (rUser) {
                // Ferramentas
                case 1:
                    do {
                        MenuBuilder.clearScreen();
                        MenuBuilder.buildMenu("Ferramentas",
                                "1. Salvar ferramentas.;2. Remover Ferramenta.;3. Listar Ferramentas.;4. Editar Ferramenta;5. Voltar.");
                        rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                            // Salvar
                            case 1:
                                System.out.print("Digite o nome da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setName(auxString);

                                System.out.print("Digite a descrição da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setDescription(auxString);

                                ToolsList.add(ToolsPicker);
                                ToolsPicker.create(ToolsPicker.getName() + "," + ToolsPicker.getDescription() + ";");
                                break;

                            // remover
                            case 2:
                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da ferramenta que deseja remover: ");
                                auxString = input.nextLine();

                                if (ToolsPicker.delete(auxString, 0) == true) {
                                    // Removendo da lista:
                                    for (Tools tool : ToolsList) {
                                        if (tool.getName().equals(auxString)) {
                                            ToolsList.remove(tool);
                                            break;
                                        }
                                    }

                                    System.out.println("\nFerramenta removida com sucesso!");
                                } else {
                                    System.out.println("\nFerramenta não encontrada!");
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Listar
                            case 3:
                                MenuBuilder.clearScreen();
                                System.out.print("Ferramentas: \n\n");
                                
                                auxString = ToolsPicker.readAll();
                                String[] lines = auxString.split(";");
                                for (String line : lines) {
                                    String[] parts = line.split(",");
                                    System.out.println("Nome: " + parts[0] + "\nDescrição: " + parts[1] + "\n");
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Editar
                            case 4:
                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da ferramenta que deseja editar: ");
                                auxString = input.nextLine();

                                System.out.print("Digite o novo nome da ferramenta: ");
                                auxString2 = input.nextLine();

                                if (ToolsPicker.update(auxString, auxString2, 0) == true) {
                                    // Editando na lista:
                                    for (Tools tool : ToolsList) {
                                        if (tool.getName().equals(auxString)) {
                                            tool.setName(auxString2);
                                            break;
                                        }
                                    }
                                    System.out.println("\nFerramenta editada com sucesso!");
                                } else {
                                    System.out.println("\nFerramenta não encontrada!");
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Voltar
                            case 5:
                                System.out.println("Voltando...");
                                break;

                            default:
                                System.out.println("opção invalida! Escolha entre 1 e 5.");
                                MenuBuilder.pause(input);
                                break;
                        }
                    } while (rUser != 5);

                    break;
                // Categorias
                case 2:
                    do {
                        MenuBuilder.clearScreen();
                        MenuBuilder.buildMenu("Categorias",
                                "1. Salvar Categorias.;2. Remover Cateogrias.;3. Listar Categorias.;4. Editar Categorias;5. Voltar.");
                        rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                            // Salvar
                            case 1:

                                break;

                            // remover
                            case 2:
                                break;

                            // Listar
                            case 3:
                                break;

                            // Editar
                            case 4:
                                break;
                            // Voltar
                            case 5:

                                System.out.println("Voltando...");
                                break;

                            default:
                                System.out.println("opção invalida! Escolha entre 1 e 5.");
                                break;
                        }
                    } while (rUser != 5);

                    break;
                // Sair
                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 3.");
                    break;
            }
        } while (rUser != 3);

        input.close();
    }
}
