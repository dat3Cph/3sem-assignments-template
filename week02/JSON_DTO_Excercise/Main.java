package JSON_DTO_Excercise;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import okio.Path;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Nicklas\\Desktop\\GitHub\\3sem-assignments-template-cph-nw89\\week02\\JSON_DTO_Excercise\\account.json");
        Account[] accounts = jsonToAccount(file);
        System.out.println(Arrays.toString(accounts));

        AccountDTO accountDTO = accountToDTO(accounts[0]);
        System.out.println(accountDTO);

        AccountDTO[] accountDTOS = accountsToDTOS(accounts);
        printDTOS(accountDTOS);

    }


    public static Account[] jsonToAccount(File jsonFile) {
        try (Scanner scanner = new Scanner(Paths.get(jsonFile.getAbsolutePath()), StandardCharsets.UTF_8.name());){
            String json = scanner.useDelimiter("\\A").next();
            JsonElement element = JsonParser.parseString(json);
            System.out.println(element);
            if(element.isJsonObject()){
                JsonObject jsonObject = element.getAsJsonObject();
                ResultDTO result = gson.fromJson(jsonObject,ResultDTO.class);
                return result.accounts;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static AccountDTO accountToDTO(Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setFullName(account.getFirstName()+" "+account.getLastName());
        accountDTO.setCity(account.getAddress().getCity());
        accountDTO.setZipCode(String.valueOf(account.getAddress().getZipCode()));
        accountDTO.setIsActive(String.valueOf(account.getAccount().isActive()));
        return accountDTO;
    }

    public static AccountDTO[] accountsToDTOS(Account[] accounts){
        AccountDTO[] result = new AccountDTO[accounts.length];
        for(int i = 0; i < result.length; i++){
            result[i] = accountToDTO(accounts[i]);
        }
        return result;
    }

    public static void printDTOS(AccountDTO[] accountDTOS){
        for(int i = 0; i < accountDTOS.length; i++){
            System.out.println("Account number: "+i);
            System.out.println("Full name: "+accountDTOS[i].getFullName());
            System.out.println("City: "+accountDTOS[i].getCity());
            System.out.println("Account active status: "+accountDTOS[i].getIsActive());
            System.out.println("--------------------------------------------------------------------------------------");
        }
    }
}
