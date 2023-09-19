public class Tools extends CRUDsys {
    private String name;
    private String description;

    
    public Tools(String name, String description, String filename) {
        super(filename);
        this.name = name;
        this.description = description;
    }

    public Tools(String filename) {
        super(filename);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nDescrição: " + description;
    }
}