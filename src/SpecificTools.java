public class SpecificTools extends Tools {
  private String standards;

  public SpecificTools(String fileName, String standards, String name, String description, String category) {
    super(fileName, name, description, category);
    this.standards = standards;
  }

  public SpecificTools(String fileName) {
    super(fileName);
  }

  public String getStandards() {
    return standards;
  }

  public void setStandards(String standards) {
    this.standards = standards;
  }

  @Override
  public String toString() {
    return super.toString() + "\nNormas: " + standards;
  }
}
