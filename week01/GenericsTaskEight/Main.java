package GenericsTaskEight;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        //String Memory Storage:
        DataStorage<String> stringDataStorage = new MemoryStorage<>();
        String id = stringDataStorage.store("Test");
        String result = stringDataStorage.retrieve(id);

        System.out.println(result);


        //Integer Memory Storage:
        DataStorage<Integer> integerDataStorage = new MemoryStorage<>();
        String intId = integerDataStorage.store(2);
        int resultInt = integerDataStorage.retrieve(intId);
        System.out.println(resultInt);

        //Double Memory Storage:
        DataStorage<Double> doubleDataStorage = new MemoryStorage<>();
        String doubleId = doubleDataStorage.store(2.2);

        double resultDouble = doubleDataStorage.retrieve(doubleId);
        System.out.println(resultDouble);


        //String file storage:
        DataStorage<String> stringToFileStorage = new FileStorage<>();
        String filename = stringToFileStorage.store("test");
        String out = stringToFileStorage.retrieve(filename);

        System.out.println(out);




    }
}
