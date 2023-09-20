import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class crud_system {
    // Funções básicas de um CRUD para se trabalhar com arquivos .txt em java, em
    // que o arquivo é manipulado com linhas.

    /*-------------------------------------------+ Especificações da classe de CRUD +-------------------------------------------*/
    // Definindo o nome do arquivo que será manipulado:
    private String fileName;

    // Definindo o construtor da classe:
    public crud_system(String fileName) {
        this.fileName = fileName;
    }

    /*-------------------------------------------+ Métodos da classe de CRUD +-------------------------------------------*/

    /*-------------------------------------------+ CRUD - CREATE +-------------------------------------------*/
    // Definindo o método para criar um novo registro no arquivo:
    // Crud - Create
    public boolean create(String newLine, boolean manteinFile, boolean breakLine) {
        try {
            // Criando um objeto da classe FileWriter para escrever no arquivo:
            FileWriter fileWriter = new FileWriter(this.fileName, manteinFile);

            // Criando um objeto da classe PrintWriter para escrever no arquivo:
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Escrevendo no arquivo:
            if(breakLine){
                printWriter.println(newLine);
            }
            else{
                printWriter.print(newLine);
            }

            // Fechando o arquivo:
            printWriter.close();

            // Caso não ocorra nenhum erro, será retornado true, indicando o salvamento do
            // arquivo :)
            return true;
        } catch (Exception e) {

            // Caso ocorra algum erro, será retornado false, indicando que o arquivo não foi
            // salvo :(
            System.out.println("Erro ao criar um novo registro no arquivo: " + e.getMessage());
            return false;
        }
    }

    /*-------------------------------------------+ CRUD - READ +-------------------------------------------*/
    // Definindo o método para ler um registro do arquivo:
    // OBS: Usando a mesma lógica que usamos no projeto: dividir a string lida
    // (usando o método split(";")) no arquivo em partes e comparar ela com o que o
    // usuário digitou (valor passado para a função):
    // cRud - Read
    public String read(String target, int position) {
        try {
            // Fazendo a leitura da linha do arquivo:
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

            // Definindo uma variável para armazenar a linha lida:
            String line;

            // Laço para ler as linhas do arquivo:
            while ((line = bufferedReader.readLine()) != null) {
                // Dividindo a linha lida em partes:
                String[] parts = line.split(";");

                // Comparando a parte da linha lida com o valor passado para a função:
                if (parts[position].equals(target)) {
                    // Caso seja igual, retornamos a linha lida:
                    bufferedReader.close();
                    return line;
                }
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o registro do arquivo: " + e.getMessage());
        }

        return null;
    }

    /*-------------------------------------------+ CRUD - UPDATE +-------------------------------------------*/
    // Definindo o método para atualizar um registro do arquivo:
    // crUd - Update
    public boolean update(String target, int position, String newValue) {
        boolean check = false;

        // Definindo uma variável para armazenar o backup do arquivo:
        String backupString = "";

        try {
            // Fazenod a leitura da linha do arquivo:
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

            // Definindo uma variável para armazenar a linha lida:
            String line;

            // Laço para ler as linhas do arquivo:
            while ((line = bufferedReader.readLine()) != null) {
                // Dividindo a linha lida em partes:
                String[] parts = line.split(";");

                // Comparando a parte da linha lida com o valor passado para a função:
                if (parts[position].equals(target)) {
                    // Limpa a linha antiga para atualizar com os novos valores:
                    line = "";
                    check = true;
                    // Caso encontre o registro procurado, faz a modificação da linha:
                    // Laço para percorrer as partes da linha e encontrar a parte que será atualizada:
                    for (int i = 0; i < parts.length; i++) {
                        if (i != parts.length - 1) {
                            if (i == position) {
                                line += newValue + ";";
                            } else {
                                line += parts[i] + ";";
                            }
                        } else {
                            if (i == position) {
                                line += newValue;
                            } else {
                                line += parts[i];
                            }
                        }
                    }
                }
                // Caso não seja igual, mantemos a linha lida:
                backupString += line + "\n";
            }
            
            bufferedReader.close();
            create(backupString, false, false);
            return check;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar o registro do arquivo: " + e.getMessage());
        }

        return false;
    }

    /*-------------------------------------------+ CRUD - DELETE +-------------------------------------------*/
    // Definindo o método para deletar um registro do arquivo:
    // cruD - Delete
    public String delete(String target, int position) {
        try {
            // Percorrendo o arquivo para encontrar a linha que será deletada:
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

            // Definindo uma variável para armazenar a linha lida:
            String line;
            String deletedLine = null;
            String backupString = "";

            // Laço para ler as linhas do arquivo:
            while ((line = bufferedReader.readLine()) != null) {
                // Dividindo a linha lida em partes:
                String[] parts = line.split(";");

                // Comparando a parte da linha lida com o valor passado para a função:
                if (parts[position].equals(target)) {
                    deletedLine = line;
                } else {
                    backupString += line + "\n";
                }
            }

            bufferedReader.close();
            create(backupString, false, false);
            return deletedLine;
        } catch (Exception e) {
            System.out.println("Erro ao deletar o registro do arquivo: " + e.getMessage());
        }

        return null;
    }

    /*-------------------------------------------+ CRUD - READ ALL +-------------------------------------------*/
    // Definindo o método para ler todos os registros do arquivo:
    // cRud - Read All
    public String readAll() {
        try {
            // Fazendo a leitura da linha do arquivo:
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName));

            // Definindo uma variável para armazenar a linha lida:
            String line;
            String allLines = "";

            // Laço para ler as linhas do arquivo:
            while ((line = bufferedReader.readLine()) != null) {
                allLines += line + "\n";
            }

            bufferedReader.close();
            return allLines;
        } catch (Exception e) {
            System.out.println("Erro ao ler todos os registros do arquivo: " + e.getMessage());
        }

        return null;
    }


}