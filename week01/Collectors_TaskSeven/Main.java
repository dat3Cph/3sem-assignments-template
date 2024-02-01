package Collectors_TaskSeven;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 200.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        ///////////////////////////////////////////////////////////   Sum of all currencies   ////////////////////////////////////////////////////////////////////////

        double sum = transactions.stream().mapToDouble(Transaction::getAmount).sum();
        System.out.println(sum);

        System.out.println("--------------------------------------------------------------------------------------------------------");

        Map<String, List<Transaction>> groupByCurrency = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
        groupByCurrency.forEach((a,b) -> {
            System.out.println("Currency: "+a);
            double currencySum = 0;
            for (Transaction t: b) {
                currencySum += t.getAmount();
            }
            System.out.println("Currency amount: "+currencySum);
        });

        System.out.println("---------------------------------------------------------------------------------------------------------");

        ///////////////////////////////////////////////////////////   Highest transaction amount   ////////////////////////////////////////////////////////////////////////

        Transaction highestTransaction = transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get();
        System.out.println(highestTransaction.getAmount());


        System.out.println("---------------------------------------------------------------------------------------------------------");

        ///////////////////////////////////////////////////////////   Average transaction amount   ////////////////////////////////////////////////////////////////////////


        OptionalDouble averageTransactionAmount = transactions.stream().mapToDouble(Transaction::getAmount).average();
        System.out.println(averageTransactionAmount);


    }
}
