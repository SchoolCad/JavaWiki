public class Category {
    private String usage;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Category() {}

    public Category(String usage) {
        this.usage = usage;
    }
    
    @Override
    public String toString() {
        return "Category: " + usage;
    }
}