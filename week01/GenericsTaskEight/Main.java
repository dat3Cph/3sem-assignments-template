package GenericsTaskEight;

public class Main {
    public static void main(String[] args) {
        DataStorage<String> stringDataStorage = new MemoryStorage<>();
        stringDataStorage.store("Hi my name is John.");
        DataStorage<Integer> integerDataStorage = new MemoryStorage<>();
        integerDataStorage.store(2);
        DataStorage<Double> doubleDataStorage = new MemoryStorage<>();
        doubleDataStorage.store(2.2);

        double resultDouble = doubleDataStorage.retrieve("2.2");
        System.out.println(resultDouble);

        int resultInt = integerDataStorage.retrieve("2");
        System.out.println(resultInt);

        String result = stringDataStorage.retrieve("0");
        String testResult = stringDataStorage.retrieve("Hi my name is John.");
        System.out.println(result);
        System.out.println(testResult);
    }
}
