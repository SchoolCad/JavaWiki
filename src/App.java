import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String ToolsFileName = "Tools.txt";
        String CategoryFileName = "Category.txt";
        String SpecificToolsFileName = "SpecificTools.txt";
        // listas
        List<Category> CategoryList = new ArrayList<Category>();
        List<Tools> ToolsList = new ArrayList<Tools>();
        List<SpecificTools> SpecificToolsList = new ArrayList<SpecificTools>();

        Tools ToolsPicker = new Tools(ToolsFileName);
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

        SpecificTools SpecificToolsPicker = new SpecificTools(SpecificToolsFileName);
        try {
            String data = SpecificToolsPicker.readAll();
            String[] lines = data.split(";");
            for (String line : lines) {
                String[] parts = line.split(",");
                SpecificToolsPicker.setName(parts[0]);
                SpecificToolsPicker.setDescription(parts[1]);
                SpecificToolsPicker.setStandards(parts[2]);
                SpecificToolsList.add(SpecificToolsPicker);
            }
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }

        Category CategoryPicker = new Category(CategoryFileName);
        // Lendo dados do arquivo Category.txt
        try {
            String data = CategoryPicker.readAll();
            String[] lines = data.split(";");
            
            for (String line : lines) {
                String[] parts = line.split(",");
                Category category = new Category(CategoryFileName);
                category.setUsage(parts[0]);
                
                CategoryList.add(category);
            }
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }

        String auxString, auxString2, auxString3, auxString4;
        char auxChar;
        boolean auxBool = false;
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
                                //verificar se existem tags disponíveis e caso não existam, não permitir salvar

                                if (CategoryList.isEmpty()) {
                                    System.out.println("Não existem categorias disponíveis para salvar ferramentas!");
                                    MenuBuilder.pause(input);
                                    break;
                                }

                                System.out.print("Digite o nome da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setName(auxString);

                                System.out.print("Digite a descrição da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setDescription(auxString);

                                //liste as categorias disponíveis colocando seu index + 1 como indicador
                                System.out.println("Categorias disponíveis: ");
                                
                                for (int i = 0; i < CategoryList.size(); i++) {
                                    System.out.println((i + 1) + " - " + CategoryList.get(i).getUsage() + "\n");
                                }

                                System.out.print("Digite o número da categoria da ferramenta: ");
                                int index = Integer.parseInt(input.nextLine());

                                while (index < 1 || index > CategoryList.size()) {
                                    System.out.print("Digite um número válido: ");
                                    index = Integer.parseInt(input.nextLine());
                                }

                                ToolsPicker.setCategory(CategoryList.get(index - 1).getUsage());

                                System.out.print("A ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                while(auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("Digite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                if(auxChar == 'S' || auxChar == 's') {
                                    System.out.print("Digite a norma da ferramenta: ");
                                    auxString = input.nextLine();
                                    SpecificToolsPicker.setName(ToolsPicker.getName());
                                    SpecificToolsPicker.setDescription(ToolsPicker.getDescription());
                                    SpecificToolsPicker.setCategory(ToolsPicker.getCategory());
                                    SpecificToolsPicker.setStandards(auxString);
                                    auxBool = true;
                                }

                                if(auxBool == true) {
                                    SpecificToolsList.add(SpecificToolsPicker);
                                    SpecificToolsPicker.create(SpecificToolsPicker.getName() + "," + SpecificToolsPicker.getDescription() + "," + SpecificToolsPicker.getStandards() + "," + SpecificToolsPicker.getCategory() + ";");
                                    break;
                                }

                                ToolsList.add(ToolsPicker);
                                ToolsPicker.create(ToolsPicker.getName() + "," + ToolsPicker.getDescription() + "," + ToolsPicker.getCategory() + ";");
                                break;

                            // remover
                            case 2:
                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da ferramenta que deseja remover: ");
                                auxString = input.nextLine();

                                System.out.print("A ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                while (auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("Digite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                if(auxChar == 'S' || auxChar == 's') {
                                    if (SpecificToolsPicker.delete(auxString, 0) == true) {
                                        // Removendo da lista:
                                        for (SpecificTools tool : SpecificToolsList) {
                                            if (tool.getName().equals(auxString)) {
                                                SpecificToolsList.remove(tool);
                                                break;
                                            }
                                        }

                                        System.out.println("\nFerramenta removida com sucesso!");
                                    } else {
                                        System.out.println("\nFerramenta não encontrada!");
                                    }

                                    MenuBuilder.pause(input);
                                    break;
                                } else {
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
                                }


                            // Listar
                            case 3:
                                if (ToolsList.isEmpty() && SpecificToolsList.isEmpty()) {
                                    System.out.println("Não existem ferramentas disponíveis para listar!");
                                    MenuBuilder.pause(input);
                                    break;
                                }

                                MenuBuilder.clearScreen();
                                System.out.print("Ferramentas: \n\n");

                                String[] lines;
                                

                                if(!ToolsList.isEmpty()) {
                                    auxString = ToolsPicker.readAll();

                                    lines = auxString.split(";");
                                    for (String line : lines) {
                                        String[] parts = line.split(",");
                                        System.out.println("Nome: " + parts[0] + "\nDescrição: " + parts[1] + "\nCategoria: " + parts[2] + "\n");
                                    }
                                }
                                if(!SpecificToolsList.isEmpty()){
                                    auxString2 = SpecificToolsPicker.readAll();

                                    lines = auxString2.split(";");
                                    for (String line : lines) {
                                        String[] parts = line.split(",");
                                        System.out.println("Nome: " + parts[0] + "\nDescrição: " + parts[1] + "\nNorma: " + parts[2] + "\nCategoria: " + parts[3] + "\n");
                                    }
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

                                System.out.print("Digite a nova descrição da ferramenta: ");
                                auxString3 = input.nextLine();

                                System.out.println("Categorias disponíveis: ");

                                for (int i = 0; i < CategoryList.size(); i++) {
                                    System.out.println((i + 1) + " - " + CategoryList.get(i).getUsage() + "\n");
                                }

                                System.out.print("Digite o número da categoria da ferramenta: ");
                                index = Integer.parseInt(input.nextLine());

                                while (index < 1 || index > CategoryList.size()) {
                                    System.out.print("Digite um número válido: ");
                                    index = Integer.parseInt(input.nextLine());
                                }

                                String newCategory = CategoryList.get(index - 1).getUsage();

                                System.out.print("A ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                while (auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("Digite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                if (auxChar == 'S' || auxChar == 's') {

                                    System.out.print("Digite a nova norma da ferramenta: ");
                                    auxString4 = input.nextLine();

                                    SpecificToolsPicker.setName(auxString2);
                                    SpecificToolsPicker.setDescription(auxString3);
                                    SpecificToolsPicker.setCategory(newCategory);
                                    SpecificToolsPicker.setStandards(auxString4);

                                    if (SpecificToolsPicker.update(auxString, SpecificToolsPicker.getName() + "," + SpecificToolsPicker.getDescription() + "," + SpecificToolsPicker.getStandards() + "," + SpecificToolsPicker.getCategory() + ";", 0) == true) {
                                        // Editando na lista:
                                        for (SpecificTools tool : SpecificToolsList) {
                                            if (tool.getName().equals(auxString)) {
                                                tool.setName(auxString2);
                                                tool.setDescription(auxString3);
                                                tool.setStandards(auxString4);
                                                tool.setCategory(newCategory);
                                                break;
                                            }
                                        }
                                        System.out.println("\nFerramenta editada com sucesso!");
                                    } else {
                                        System.out.println("\nFerramenta não encontrada!");
                                    }

                                    MenuBuilder.pause(input);
                                    break;
                                } else {
                                    ToolsPicker.setName(auxString2);
                                    ToolsPicker.setDescription(auxString3);
                                    ToolsPicker.setCategory(newCategory);

                                    if (ToolsPicker.update(auxString, ToolsPicker.getName() + "," + ToolsPicker.getDescription() + "," + ToolsPicker.getCategory() + ";", 0) == true) {
                                        // Editando na lista:
                                        for (Tools tool : ToolsList) {
                                            if (tool.getName().equals(auxString)) {
                                                tool.setName(auxString2);
                                                tool.setDescription(auxString3);
                                                tool.setCategory(newCategory);
                                                break;
                                            }
                                        }
                                        System.out.println("\nFerramenta editada com sucesso!");
                                    } else {
                                        System.out.println("\nFerramenta não encontrada!");
                                    }

                                    MenuBuilder.pause(input);
                                    break;
                                }


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
                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da categoria: ");
                                auxString = input.nextLine();
                                CategoryPicker.setUsage(auxString);

                                CategoryList.add(CategoryPicker);
                                CategoryPicker.create(CategoryPicker.getUsage() + ";");
                                break;

                            // remover
                            case 2:

                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da categoria que deseja remover: ");
                                auxString = input.nextLine();

                                if (CategoryPicker.delete(auxString, 0) == true) {
                                    // Removendo da lista:
                                    for (Category category : CategoryList) {
                                        if (category.getUsage().equals(auxString)) {
                                            CategoryList.remove(category);
                                            break;
                                        }
                                    }

                                    System.out.println("\nCategoria removida com sucesso!");
                                } else {
                                    System.out.println("\nCategoria não encontrada!");
                                }
                                MenuBuilder.pause(input);
                                break;

                            // Listar
                            case 3:
                                if (CategoryList.isEmpty()) {
                                    System.out.println("Não existem categorias disponíveis para listar!");
                                    MenuBuilder.pause(input);
                                    break;
                                }

                                MenuBuilder.clearScreen();
                                System.out.print("Categorias: \n\n");

                                auxString = CategoryPicker.readAll();
                                String[] lines = auxString.split(";");
                                for (String line : lines) {
                                    System.out.println("Categoria: " + line + "\n");
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Editar
                            case 4:
                                MenuBuilder.clearScreen();
                                System.out.print("Digite o nome da categoria que deseja editar: ");
                                auxString = input.nextLine();

                                System.out.print("Digite o novo nome da categoria: ");
                                auxString2 = input.nextLine();

                                if (CategoryPicker.update(auxString, auxString2 + ";\n", 0) == true) {
                                    // Editando na lista:
                                    
                                    try {
                                        String data = CategoryPicker.readAll();
                                        lines = data.split(";");
                                        
                                        CategoryList.clear();
                                        
                                        for (String line : lines) {
                                            String[] parts = line.split(",");
                                            
                                            Category category = new Category(CategoryFileName);
                                            category.setUsage(parts[0]);
                                            
                                            CategoryList.add(category);
                                        }
                                    } catch (Exception e) {
                                        System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
                                    }

                                    for (Category category : CategoryList) {
                                        if (category.getUsage().equals(auxString)) {
                                            category.setUsage(auxString2);
                                            break;
                                        }
                                    }

                                    System.out.println("\nCategoria editada com sucesso!");
                                } else {
                                    System.out.println("\nCategoria não encontrada!");
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
                // Sair
                case 3:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 3.");
                    MenuBuilder.pause(input);
                    break;
            }
        } while (rUser != 3);

        input.close();
    }
}
