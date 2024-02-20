
import java.util.Arrays;

public class Main {
    public static final double COFFE_PRICE = 1.20;
    public static final double TEA_PRICE = 1.50;
    public static final double HOTCHOCOLATE_PRICE = 1.45;
    public static final double MILK_PRICE = 0.10;
    public static final double CREAM_PRICE = 0.15;
    public static final double CINNAMON_PRICE = 0.05;

    public static void main(String[] args) {
        BaseBeverage coffee = new BaseBeverage("COFFEE", COFFE_PRICE);
        BaseBeverage tea = new BaseBeverage("TEA", TEA_PRICE);
        BaseBeverage hotChocolate = new BaseBeverage("HotChocolate", HOTCHOCOLATE_PRICE);

        BeverageSupplement milk = new BeverageSupplement("Milk", MILK_PRICE);
        BeverageSupplement cream = new BeverageSupplement("Cream", CREAM_PRICE);
        BeverageSupplement cinnamon = new BeverageSupplement("Cinnamon", CINNAMON_PRICE);

        ComposedBeverage coffeeWithMilk = new ComposedBeverage(coffee, Arrays.asList(milk));
        System.out.println(coffeeWithMilk);

        ComposedBeverage coffeeWithMilkAndCream = new ComposedBeverage(coffee, Arrays.asList(milk, cream));
        System.out.println(coffeeWithMilkAndCream);

        ComposedBeverage teaWithMilk = new ComposedBeverage(tea, Arrays.asList(milk));
        System.out.println(teaWithMilk);
    }
}

