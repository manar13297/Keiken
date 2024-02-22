import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ComposedBeverageTest {

    @Test
    void ComputeCoffeWithMilkAndCreamPrice(){
        var coffe = new BaseBeverage("COFFE", Main.COFFE_PRICE);
        var milk = new BeverageSupplement("Milk", Main.MILK_PRICE);
        var cream = new BeverageSupplement("Cream", Main.CREAM_PRICE);

        var coffeWithMilkAndCream = new ComposedBeverage(coffe , Arrays.asList(milk, cream));
        assertEquals(1.45 ,coffeWithMilkAndCream.price());
    }

    @Test
    void ComputeCoffePrice(){
        var coffe = new BaseBeverage("COFFE", Main.COFFE_PRICE);

        var coffeOrder = new ComposedBeverage(coffe , Arrays.asList());
        assertEquals(1.20 ,coffeOrder.price());
    }


}