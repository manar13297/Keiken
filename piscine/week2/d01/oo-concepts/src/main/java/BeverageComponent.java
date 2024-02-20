public abstract class BeverageComponent implements Beverage {
    protected String name;
    protected double price;

    public BeverageComponent(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public String name() {
        return name;
    }
}

