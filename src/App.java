import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(System.in);
        int rUser; 

        do {
            MenuBuilder.buildMenu("JavaWiki", "1. Categorias.;2. Ferramentas.;3. Sair.");
            rUser = Integer.parseInt(input.nextLine());

            switch (rUser) {
                case 1:
                    MenuBuilder.buildMenu("Ferramentas", "1. Gerenciar ferramentas.;2. Remover Ferramenta.;3. Listar Ferramentas.;");
                    rUser = Integer.parseInt(input.nextLine());
                break;

                case 2:

                break;
                
                case 3:

                break;
            
                default:
                    System.out.println("Opção inválida! Escolha entre 1 e 3.");
                break;
            }
        } while (rUser != 3);


        input.close();
    }
}
