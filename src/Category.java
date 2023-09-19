import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class Category {
    private String usage;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Category() {
    }

    public Category(String usage) {
        this.usage = usage;
    }

    // Métodos
    @Override
    public String toString() {
        return "Category: " + usage;
    }

    // Deletar categoria específica:
    protected static Boolean delete(String target) {
        Boolean boo = false;
        try {
            FileReader fileReader = new FileReader("../data/Categories.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int b = 0;
                if (!line.equals(target)) {

                    try {
                        PrintWriter writer = new PrintWriter(new FileWriter("../data/Categories.txt", (b == 1 ? true : false)));
                        b = 1;
                        writer.write(line + "\n");
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("Ocorreu um erro ao gravar o arquivo: " + e.getMessage());
                    }

                } else
                    boo = true;
                }

            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
        }
        return boo;
    }

    // Salvar o objeto em arquivo usando o trycath
    protected Boolean save() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("../data/categories.txt", true));
            pw.println(this.usage);
            pw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return false;
        }
    }

    // Carregar toda a lista na memória em forma de lista:
    protected List<Category> consult(String category) {
        List<Category> list = new ArrayList<Category>();

        try {
            FileReader fileScan = new FileReader("../data/Categories.txt");
            BufferedReader bufferedReader = new BufferedReader(fileScan);
            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                Category partFrom = new Category(linha);
                list.add(partFrom);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }

        return list;
    }

}