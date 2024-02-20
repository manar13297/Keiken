public class HelloWorld {
    public static void main(String[] args) {
        int first = 7;
        int second = 8;
        int sum = add(first, second);
        System.out.println("The sum is: "+ sum);

        first = 30;
    }

    private static int add(int first, int second) {
        return first+ second;
    }
}
