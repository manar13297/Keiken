import java.util.List;
import java.util.stream.Collectors;

public class ComposedBeverage implements Beverage {
    private BaseBeverage baseBeverage;
    private List<BeverageSupplement> supplements;

    public ComposedBeverage(BaseBeverage baseBeverage, List<BeverageSupplement> supplements) {
        this.baseBeverage = baseBeverage;
        this.supplements = supplements;
    }

    @Override
    public double price() {
        return baseBeverage.price()
                + supplements.stream()
                .mapToDouble(BeverageSupplement::price)
                .sum();
    }

    @Override
    public String name() {
        String supplementsNames = supplements.stream()
                .map(BeverageSupplement::name)
                .collect(Collectors.joining(" And "));

        return baseBeverage.name() + " with " + supplementsNames;
    }

    @Override
    public String toString() {
        return name() + " -->" + price();
    }
}
