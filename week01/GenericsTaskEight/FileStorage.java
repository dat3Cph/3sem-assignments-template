package GenericsTaskEight;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileStorage<T> implements DataStorage<T> {


    @Override
    public String store(T data) {
        Type typeOf = data.getClass();
        String fileName = typeOf.toString();
        String fileSuffix = (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        fileName = fileName + fileSuffix + ".ser";
        try{
            File file = new File(fileName);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(data);
            objectOut.close();
            fileOut.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public T retrieve(String source) {
        try{
            FileInputStream fileIn = new FileInputStream(source);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            T obj = (T) objIn.readObject();
            fileIn.close();
            objIn.close();
            return obj;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
