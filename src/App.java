import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        /*---------------------------------------------- Carregando o programa (carregando dados na memória) ----------------------------------------------*/
        // Endereços dos arquivos:
        String ToolsFileName = "Tools.txt";
        String CategoryFileName = "Category.txt";
        String SpecificToolsFileName = "SpecificTools.txt";

        // listas:
        List<Category> CategoryList = new ArrayList<Category>();
        List<Tools> ToolsList = new ArrayList<Tools>();
        List<SpecificTools> SpecificToolsList = new ArrayList<SpecificTools>();

        // Carregando os dados dos arquivos nas listas e criando variáveis auxíliares:

        // Lendo dados do arquivo Tools.txt
        Tools ToolsPicker = new Tools(ToolsFileName);
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

        // Lendo dados do arquivo SpecificTools.txt
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

        // Lendo dados do arquivo Category.txt
        Category CategoryPicker = new Category(CategoryFileName);
        try {
            String data = CategoryPicker.readAll();
            String[] lines = data.split("\n");
            for (int i = 0; i < lines.length; i++) {
                Category catPicker = new Category(CategoryFileName);
                catPicker.setUsage(lines[i]);
                CategoryList.add(catPicker);
            }
        } catch (Exception e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }


        /*---------------------------------------------- Variáveis auxíliares ----------------------------------------------*/
        // Criando variáveis auxíliares:
        String auxString, auxString2, auxString3, auxString4;
        char auxChar;
        int rUser, auxInt;

        // Cadastrando uma categorial inicial para não haver problemas relacionados à
        // lista de categorias:
        if (CategoryList.isEmpty()) {
            CategoryPicker.setUsage("Geral");
            CategoryList.add(CategoryPicker);
            CategoryPicker.create(CategoryPicker.getUsage(), true, true);
        }

        /*---------------------------------------------- Menu ----------------------------------------------*/
        // MenuLoop:
        Scanner input = new Scanner(System.in);

        
        MenuBuilder.pause(input);
        do {
            // Tela inicial -> Menu nível 1:
            MenuBuilder.clearScreen();
            System.out.println("./JavaWiki");
            MenuBuilder.buildMenu("JavaWiki", "1. Ferramentas.;2. Categorias.;3. Sair.");
            rUser = Integer.parseInt(input.nextLine());

            switch (rUser) {
                // Ferramentas
                case 1:
                    do {
                        MenuBuilder.clearScreen();
                        // Tela inicial -> Menu nível 2:
                        System.out.println("./JavaWiki/Ferramentas");
                        MenuBuilder.buildMenu("Ferramentas",
                                "1. Salvar ferramentas.;2. Remover Ferramenta.;3. Listar Ferramentas.;4. Editar Ferramenta;5. Voltar.");
                        rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                            // Salvar:
                            case 1:
                                // verificar se existem tags disponíveis e caso não existam, não permitir
                                // salvar.
                                if (CategoryList.isEmpty()) {
                                    System.out.println("\nNão existem categorias disponíveis para salvar ferramentas!");
                                    MenuBuilder.pause(input);
                                    break;
                                }

                                System.out.print("\nDigite o nome da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setName(auxString);

                                System.out.print("\nDigite a descrição da ferramenta: ");
                                auxString = input.nextLine();
                                ToolsPicker.setDescription(auxString);

                                // liste as categorias disponíveis colocando seu index + 1 como indicador
                                System.out.println("\nCategorias disponíveis: ");
                                for (int i = 0; i < CategoryList.size(); i++) {
                                    System.out.println((i + 1) + " - " + CategoryList.get(i).getUsage() + "\n");
                                }

                                System.out.print("Digite o número da categoria da ferramenta: ");
                                int index = Integer.parseInt(input.nextLine());

                                // Verifica o valor digitado:
                                while (index < 1 || index > CategoryList.size()) {
                                    System.out.print("\nDigite um número válido: ");
                                    index = Integer.parseInt(input.nextLine());
                                }
                                ToolsPicker.setCategory(CategoryList.get(index - 1).getUsage());


                                // Verifica se o usuário deseja inserir uma norma:
                                System.out.print("\nA ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                
                                while (auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("Digite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                // Caso o usuário deseje inserir uma norma:
                                if (auxChar == 'S' || auxChar == 's') {
                                    System.out.print("\nDigite a norma da ferramenta: ");
                                    auxString = input.nextLine();
                                    SpecificToolsPicker.setName(ToolsPicker.getName());
                                    SpecificToolsPicker.setDescription(ToolsPicker.getDescription());
                                    SpecificToolsPicker.setCategory(ToolsPicker.getCategory());
                                    SpecificToolsPicker.setStandards(auxString);
                                    SpecificToolsList.add(SpecificToolsPicker);

                                    
                                    SpecificToolsPicker.create((SpecificToolsPicker.getName() + ";" + SpecificToolsPicker.getDescription() + ";" + SpecificToolsPicker.getStandards() + ";" + SpecificToolsPicker.getCategory()).replace("\n", ""), true, true);
                                }else{
                                    ToolsList.add(ToolsPicker);
                                    ToolsPicker.create((ToolsPicker.getName() + ";" + ToolsPicker.getDescription() + ";"
                                            + ToolsPicker.getCategory()).replace("\n", ""), true, true);
                                }
                                MenuBuilder.pause(input);
                                break;

                            // remover:
                            case 2:
                                MenuBuilder.clearScreen();
                                System.out.print("\nDigite o nome da ferramenta que deseja remover: ");
                                auxString = input.nextLine();

                                System.out.print("\nA ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                while (auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("Digite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                if (auxChar == 'S' || auxChar == 's') {
                                    if (SpecificToolsPicker.delete(auxString, 0) != null) {
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
                                } else {
                                    if (ToolsPicker.delete(auxString, 0) != null) {
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
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Listar:
                            case 3:
                                if (ToolsList.isEmpty() && SpecificToolsList.isEmpty()) {
                                    System.out.println("\nNão existem ferramentas disponíveis para listar!");
                                    MenuBuilder.pause(input);
                                    break;
                                } else {
                                    MenuBuilder.clearScreen();
                                    System.out.print("\nFerramentas: \n");
                                    String[] lines;

                                    // Listando as ferramentas:
                                    if (!ToolsList.isEmpty()) {
                                        auxString = ToolsPicker.readAll();

                                        // Formatando auxString:
                                        lines = auxString.split(";");
                                        for (String line : lines) {
                                            String[] parts = line.split(",");
                                            System.out.println("\nNome: " + parts[0] + "\nDescrição: " + parts[1]
                                                    + "\nCategoria: " + parts[2] + "\n");
                                        }
                                    }

                                    // Listando as ferramentas específicas:
                                    if (!SpecificToolsList.isEmpty()) {
                                        auxString2 = SpecificToolsPicker.readAll();

                                        lines = auxString2.split(";");
                                        for (String line : lines) {
                                            String[] parts = line.split(",");
                                            System.out.println("\nNome: " + parts[0] + "\nDescrição: " + parts[1]
                                                    + "\nNorma: " + parts[2] + "\nCategoria: " + parts[3] + "\n");
                                        }
                                    }
                                }
                                MenuBuilder.pause(input);
                                break;

                            // Editar
                            case 4:
                                MenuBuilder.clearScreen();

                                System.out.print("\nDigite o nome da ferramenta que deseja editar: ");
                                auxString = input.nextLine();

                                System.out.print("\nDigite o novo nome da ferramenta: ");
                                auxString2 = input.nextLine();

                                System.out.print("\nDigite a nova descrição da ferramenta: ");
                                auxString3 = input.nextLine();

                                System.out.println("\nCategorias disponíveis: ");

                                for (int i = 0; i < CategoryList.size(); i++) {
                                    System.out.println((i + 1) + " - " + CategoryList.get(i).getUsage() + "\n");
                                }

                                System.out.print("\nDigite o número da categoria da ferramenta: ");
                                index = Integer.parseInt(input.nextLine());

                                while (index < 1 || index > CategoryList.size()) {
                                    System.out.print("\nDigite um número válido: ");
                                    index = Integer.parseInt(input.nextLine());
                                }

                                String newCategory = CategoryList.get(index - 1).getUsage();

                                System.out.print("\nA ferramenta possui alguma norma?(S/N): ");
                                auxChar = input.nextLine().charAt(0);
                                while (auxChar != 'S' && auxChar != 'N' && auxChar != 's' && auxChar != 'n') {
                                    System.out.print("\nDigite S ou N: ");
                                    auxChar = input.nextLine().charAt(0);
                                }

                                if (auxChar == 'S' || auxChar == 's') {

                                    System.out.print("\nDigite a nova norma da ferramenta: ");
                                    auxString4 = input.nextLine();

                                    SpecificToolsPicker.setName(auxString2);
                                    SpecificToolsPicker.setDescription(auxString3);
                                    SpecificToolsPicker.setCategory(newCategory);
                                    SpecificToolsPicker.setStandards(auxString4);

                                    if (SpecificToolsPicker.update(auxString, 0,
                                            SpecificToolsPicker.getName() + "," + SpecificToolsPicker.getDescription()
                                                    + "," + SpecificToolsPicker.getStandards() + ","
                                                    + SpecificToolsPicker.getCategory() + ";") == true) {
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

                                    if (ToolsPicker.update(auxString, 0,
                                            ToolsPicker.getName() + "," + ToolsPicker.getDescription() + ","
                                                    + ToolsPicker.getCategory() + ";") == true) {
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
                                System.out.println("\nVoltando...");
                                break;

                            default:
                                System.out.println("\nOpção invalida! Escolha entre 1 e 5.");
                                MenuBuilder.pause(input);
                                break;
                        }
                    } while (rUser != 5);

                    break;
                // Categorias
                case 2:
                    do {
                        // Tela inicial -> Menu nível 2:
                        MenuBuilder.clearScreen();
                        System.out.println("./JavaWiki/Categorias");
                        MenuBuilder.buildMenu("Categorias",
                                "1. Salvar Categorias.;2. Remover Cateogrias.;3. Listar Categorias.;4. Editar Categorias;5. Voltar.");
                        rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                            // Salvar
                            case 1:
                                MenuBuilder.clearScreen();

                                System.out.print("\nDigite o nome da categoria: ");
                                auxString = input.nextLine();

                                CategoryPicker.setUsage(auxString);

                                CategoryPicker.create(CategoryPicker.getUsage(), true, true);

                                CategoryList.add(CategoryPicker);
                            break;

                            // remover
                            case 2:
                                // verifica a quantidade de itens na lista de categorias e caso só exista uma, impede de remove-la:
                                auxInt = CategoryList.size();

                                if (auxInt == 1) {
                                    System.out.println("\nNão é possível remover a única categoria disponível!");
                                } else {
                                    MenuBuilder.clearScreen();
                                    System.out.print("Digite o nome da categoria que deseja remover: ");
                                    auxString = input.nextLine();

                                    // Tenta deletar a categoria do arquivo:
                                    if (CategoryPicker.delete(auxString, 0) != null) {

                                        // Caso a categoria seja removida, ela é removida da lista de categorias:
                                        for (Category category : CategoryList) {
                                            if (category.getUsage().equals(auxString)) {
                                                CategoryList.remove(category);
                                                break;
                                            }
                                        }

                                        // Além disso, removemos todos as ferramentas e ferramentas específicas que
                                        // possuem essa categoria:
                                        // Removendo de ferramentas:
                                        if (ToolsPicker.delete(auxString, 2) != null) {
                                            for (Tools tool : ToolsList) {
                                                if (tool.getCategory().equals(auxString)) {
                                                    ToolsList.remove(tool);
                                                }
                                            }
                                        }

                                        // Removendo de ferramentas específicas:
                                        if (SpecificToolsPicker.delete(auxString, 3) != null) {
                                            for (SpecificTools tool : SpecificToolsList) {
                                                if (tool.getCategory().equals(auxString)) {
                                                    SpecificToolsList.remove(tool);
                                                }
                                            }
                                        }
                                        System.out.println("\nCategoria removida com sucesso!");
                                    } else {
                                        System.out.println("\nCategoria não encontrada!");
                                    }
                                }

                                MenuBuilder.pause(input);
                                break;

                            // Listar
                            case 3:
                                if (CategoryList.isEmpty()) {
                                    System.out.println("\nNão existem categorias disponíveis para listar!");
                                } else{
                                    // Listando as categorias:

                                    MenuBuilder.clearScreen();
                                    System.out.print("\nCategorias: \n");

                                    for (Category category : CategoryList) {
                                        System.out.println("Categoria: " + category.getUsage() + "\n");
                                    }

                                }


                            
                                MenuBuilder.pause(input);
                            break;

                            // Editar
                            case 4:
                                MenuBuilder.clearScreen();
                                System.out.print("\nDigite o nome da categoria que deseja editar: ");
                                auxString = input.nextLine();

                                System.out.print("\nDigite o novo nome da categoria: ");
                                auxString2 = input.nextLine();

                                if (CategoryPicker.update(auxString, 0, auxString2 + ";\n") == true) {
                                    // Editando na lista:

                                    /*try {
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
                                        System.out
                                                .println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
                                    }*/

                                    for (Category category : CategoryList) {
                                        if (category.getUsage().equals(auxString)) {
                                            category.setUsage(auxString2);
                                            break;
                                        }
                                    }

                                    // Editando nas lista de ferramentas:
                                    if (ToolsPicker.update(auxString, 2, auxString2) == true) {
                                        for (Tools tool : ToolsList) {
                                            if (tool.getCategory().equals(auxString)) {
                                                tool.setCategory(auxString2);
                                            }
                                        }
                                    }

                                    // Editando nas lista de ferramentas específicas:
                                    if (SpecificToolsPicker.update(auxString, 3, auxString2) == true) {
                                        for (SpecificTools tool : SpecificToolsList) {
                                            if (tool.getCategory().equals(auxString)) {
                                                tool.setCategory(auxString2);
                                            }
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
                                System.out.println("\nVoltando...");
                                break;

                            default:
                                System.out.println("\nOpção invalida! Escolha entre 1 e 5.");
                                MenuBuilder.pause(input);
                                break;
                        }
                    } while (rUser != 5);

                    break;
                // Sair
                case 3:
                    System.out.println("\nSaindo...");
                    MenuBuilder.pause(input);
                    break;

                default:
                    System.out.println("\nOpção inválida! Escolha entre 1 e 3.");
                    MenuBuilder.pause(input);
                    break;
            }
        } while (rUser != 3);
        input.close();
    }
}
