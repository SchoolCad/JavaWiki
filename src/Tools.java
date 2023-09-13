public class Tools extends Category {
    private String name;
    private String description;

    
    public Tools(String usage, String name, String description) {
        super(usage);
        this.name = name;
        this.description = description;
    }

    public Tools() {
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
        return "Nome: " + name + "\nDescrição:" + description;
    }
}