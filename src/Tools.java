public class Tools extends CRUDsys {
    private String name;
    private String description;
    private String category;

    
    public Tools(String name, String description, String filename, String category) {
        super(filename);
        this.name = name;
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Nome: " + name + "\nDescrição: " + description + "\nCategoria: " + category;
    }
}