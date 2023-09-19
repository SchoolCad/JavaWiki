import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        List<Category> list = new ArrayList<Category>();
        Category auxCategory = new Category();
        
        Scanner input = new Scanner(System.in);
        String aux;
        int rUser; 

        do {
            MenuBuilder.buildMenu("JavaWiki", "1. Ferramentas.;2. Categorias.;3. Sair.");
            rUser = Integer.parseInt(input.nextLine());

            switch (rUser) {
                //Ferramentas
                case 1:
                    do{
                    MenuBuilder.buildMenu("Ferramentas", "1. Gerenciar ferramentas.;2. Remover Ferramenta.;3. Listar Ferramentas.;4. Editar Ferramenta;5. Voltar.");
                    rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                        //Gerenciar
                        case 1:
                            
                            break;
                        
                        //remover
                        case 2:
                            break;
                        
                        //Listar
                        case 3:
                            break;
                        
                        //Editar
                        case 4:
                            break;
                        
                         //Voltar
                        case 5:
                            System.out.println("Voltando...");
                            break;

                        default:
                            System.out.println("opção invalida! Escolha entre 1 e 5.");
                            break;
                        }
                    }while(rUser != 5);
                    
                break;
                //Categorias
                case 2:
                    do{
                    MenuBuilder.buildMenu("Categorias", "1. Gerenciar Categorias.;2. Remover Cateogrias.;3. Listar Categorias.;4. Editar Categorias;5. Voltar.");
                    rUser = Integer.parseInt(input.nextLine());
                        switch (rUser) {
                        //Gerenciar
                        case 1:

                            System.out.print("Insira o nome da categoria: ");
                            aux = input.nextLine();
                            auxCategory.setUsage(aux);
                            auxCategory.save();

                            list.add(auxCategory);

                            break;

                        //remover
                        case 2:                                
                            break;
                        
                        //Listar
                        case 3:
                            break;
                        
                        //Editar
                        case 4:
                            break;
                        
                         //Voltar
                        case 5:
                            System.out.println("Voltando...");
                            break;
                        
                        default:
                            System.out.println("opção invalida! Escolha entre 1 e 5.");
                            break;
                        }
                    }while(rUser != 5);

                break;
                //Sair
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
