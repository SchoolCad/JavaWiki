import java.io.*;

public class CRUDsys {
    
    /*------------------------------------------------------------------------------------------------------*/

    private String fileName;

    public CRUDsys(String fileName) {
        this.fileName = fileName;
    }

    // deve receber o objeto ja em formato de string -> "prop1,prop2,prop3,prop4;"
    public void create(String data) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(data);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível gravar no arquivo -> " + e.getMessage());
        }
    }

    /*------------------------------------------------------------------------------------------------------*/

    // retorna uma string com todos os dados do arquivo
    public String readAll() {
        String data = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data += line;
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
            return null;
        }
        return data;
    }

    /*------------------------------------------------------------------------------------------------------*/

    // recebe um identificador e um indice e retorna uma string com os dados da
    // linha que contem o identificador
    public String read(String identifier, int index) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(";");
                String[] values = parts[0].split(",");
                if (values.length > 0 && values[index].equals(identifier)) {
                    return line;
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível ler do arquivo -> " + e.getMessage());
        }
        return null;
    }
    
    /*------------------------------------------------------------------------------------------------------*/

    // recebe um identificador e um indice e retorna true se o dado existe
    // e foi alterado com sucesso
    public boolean update(String identifier, String newData, int index) {
        try {
            String fileData = this.readAll();
            if (fileData == null) {
                return false;
            }

            String[] values = fileData.split(";");

            String newFileData = "";
            for (int i = 0; i < values.length; i++) {
                String[] props = values[i].split(",");
                if (props[index].equals(identifier)) {
                    newFileData += newData;
                } else {
                    newFileData += values[i] + ";";
                }
            }

            try (FileWriter fileWriter = new FileWriter(fileName, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(newFileData);
            } catch (IOException e) {
                System.out.println("ERRO: Não foi possível atualizar o dado -> " + e.getMessage());
                return false;
            }

            return true;
        } catch (Exception e2) {
            System.out.println("ERRO: Não foi possível atualizar o dado -> " + e2.getMessage());
            return false;
        }
    }
    
    /*------------------------------------------------------------------------------------------------------*/

    // Delete - Remover um dado específico com base em um identificador
    public boolean delete(String identifier, int index) {
        boolean found = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String fileData = this.readAll();
            if (fileData == null) {
                return false;
            }

            String[] values = fileData.split(";");

            String newFileData = "";

            for (int i = 0; i < values.length; i++) {
                String[] props = values[i].split(",");
                if (props[index].equals(identifier)) {
                    found = true;
                } else {
                    newFileData += values[i] + ";";
                }
            }

            if (!!found) {
                try (FileWriter fileWriter = new FileWriter(fileName, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    String[] newValues = newFileData.split(";");
                    for (int i = 0; i < newValues.length; i++) {
                        if (newValues.length - 1 == i) {
                            bufferedWriter.write(newValues[i]);
                            break;
                        }
                        bufferedWriter.write(newValues[i] + ";");
                        bufferedWriter.newLine();
                    }
                } catch (IOException e) {
                    System.out.println("ERRO: Não foi possível excluir o dado -> " + e.getMessage());
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível excluir o dado -> " + e.getMessage());
        }

        return found;
    }
    
    /*------------------------------------------------------------------------------------------------------*/
}
