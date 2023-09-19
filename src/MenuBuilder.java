/* FUNÇÃO: Construir um menu de texto simples com título e opções.
 *  
 * Elaborado por Matheus Camargo Ginebro para implementação em projetos escolares.
 * 
 * OBS: pode usar se quiser.
 */

public class MenuBuilder {
    // Método responsável por construir o menu
    public static int buildMenu(String title, String options) {
        // Verifica se o título é nulo
        if (title == null) {
            return -1; // Retorna -1 para indicar um título nulo
        }

        // Verifica se as opções são nulas ou vazias
        if (options == null || options.isEmpty()) {
            System.out.println("O menu não possui opções disponíveis.");
            return 0; // Retorna 0 para indicar opções vazias
        }

        // Divide as opções em um array de strings
        String[] menuOptions = options.split(";");
        
        // Calcula o comprimento do título e das opções mais longas
        int titleLength = title.length();
        int optionsLength = 0;
        for (String option : menuOptions) {
            optionsLength = Math.max(optionsLength, option.trim().length());
        }
        
        // Determina o comprimento total do menu
        int menuLen = Math.max(titleLength, optionsLength);

        // StringBuilder para construir o conteúdo do menu
        StringBuilder menuBuilder = new StringBuilder();

        // Linha superior do menu
        printHorizontalLine(menuBuilder, menuLen);

        // Título do menu centralizado
        menuBuilder.append(String.format("| %s |\n", aligncenterText(title, menuLen)));

        // Linha intermediária do menu
        printHorizontalLine(menuBuilder, menuLen);

        // Opções do menu alinhadas à esquerda
        for (String option : menuOptions) {
            menuBuilder.append(String.format("| %s |\n", alignLeftText(option.trim(), menuLen)));
        }

        // Linha inferior do menu
        printHorizontalLine(menuBuilder, menuLen);

        // Solicitação de entrada do usuário
        menuBuilder.append("\nR: ");

        // Imprime o conteúdo do menu
        System.out.print("\033[H\033[2J");
        System.out.print(menuBuilder.toString());

        return 0; // Retorna 0 para indicar que o menu foi construído com sucesso
    }

    // Método auxiliar para imprimir uma linha horizontal no menu
    private static void printHorizontalLine(StringBuilder builder, int length) {
        builder.append("O");
        for (int i = 0; i < length + 2; i++) {
            builder.append("=");
        }
        builder.append("O\n");
    }

    // Método auxiliar para alinhar o texto centralmente dentro de um comprimento específico
    private static String aligncenterText(String text, int length) {
        int padding = length - text.length();
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        StringBuilder builder = new StringBuilder();

        // Adiciona espaços de preenchimento à esquerda
        for (int i = 0; i < leftPadding; i++) {
            builder.append(" ");
        }

        builder.append(text); // Adiciona o texto

        // Adiciona espaços de preenchimento à direita
        for (int i = 0; i < rightPadding; i++) {
            builder.append(" ");
        }

        return builder.toString(); // Retorna o texto centralizado
    }

    // Método auxiliar para alinhar o texto à esquerda dentro de um comprimento específico
    private static String alignLeftText(String text, int length) {
        StringBuilder builder = new StringBuilder(text);
        int padding = length - text.length();
        for (int i = 0; i < padding; i++) {
            builder.append(" "); // Adiciona espaços de preenchimento à direita
        }
        return builder.toString(); // Retorna o texto alinhado à esquerda
    }
}
