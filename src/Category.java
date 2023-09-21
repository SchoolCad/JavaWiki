public class Category extends crud_system{
    private String usage;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Category(String filename) {
        super(filename);
    }

    public Category(String usage, String filename) {
        super(filename);
        this.usage = usage;
    }

    // Métodos
    @Override
    public String toString() {
        return "Categoria: " + usage;
    }
}