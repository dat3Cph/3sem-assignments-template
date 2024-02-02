package GenericsTaskEight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MemoryStorage<T> implements DataStorage<T>{

    static int id = 0;
    Map<Integer, T> storage = new HashMap<>();
    //List<T> storage = new ArrayList<>();

    @Override
    public String store(T data) {
        if(data != null){
            storage.put(id,data);
            id++;
            return String.valueOf(id);
        }
        return "Error while adding data: Data cannot be null";
    }

    @Override
    public T retrieve(String source) {
        T obj = null;
        if(source != null){
            if(!source.isEmpty() && !source.isBlank()){
                if(source.matches("[0-9]+")){
                    int id = Integer.parseInt(source);
                    if(id > storage.size()){
                        for (Map.Entry<Integer, T> entry: storage.entrySet()) {
                            if(entry.getValue().equals(id)){
                                return obj = entry.getValue();
                            }
                        }
                    }
                    return storage.get(id);
                }else if(source.matches("[0-9]*\\.[0-9]+")){
                    double id = Double.parseDouble(source);
                    for (Map.Entry<Integer, T> entry: storage.entrySet()) {
                        if(entry.getValue().equals(id)){
                            return obj = entry.getValue();
                        }
                    }
                }else {
                    for (Map.Entry<Integer, T> entry: storage.entrySet()) {
                        if(entry.getValue().equals(source)){
                            return obj = entry.getValue();
                        }
                    }
                }
            }
        }
        return obj;
    }
}
