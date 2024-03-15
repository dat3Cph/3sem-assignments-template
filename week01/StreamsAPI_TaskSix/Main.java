package StreamsAPI_TaskSix;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        //Title, author, publicationYear, rating, pages
        books.add(new Book("Don Quixote", "Miguel de Cervantes", 1605, 3.9, 1072));
        books.add(new Book("A Tale Of Two Cities", "Charles Dickens", 1859, 3.9, 304));
        books.add(new Book("The Little Prince", "Antoine de Saint-Exupéry", 1943, 4.3,109));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, 4.5,223));
        books.add(new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998,4.4,341));
        books.add(new Book("And Then There Were None", "Agatha Christie", 1939,4.3,272));
        books.add(new Book("Dream of the Red Chamber", "Cao Xueqin", 1791,4.2,992));
        books.add(new Book("The Hobbit","J.R.R. Tolkien", 1937,4.3,320));
        books.add(new Book("The Lion, the Witch, and the Wardrobe", "C.S Lewis", 1950,4.2,171));
        books.add(new Book("She: A History of Adventure", "H. Rider Haggard", 1886,3.6,368));
        books.add(new Book("Vardi Wala Gunda", "Ved Prakash Sharma", 1992, 4.2, 368));


        ///////////////////////////////////////////////// AVERAGE RATING //////////////////////////////////////////////////////////
        OptionalDouble average = books.stream().mapToDouble((x -> x.getRating())).average();
        System.out.println(average);

        System.out.println("-------------------------------------------------------------------");

        ///////////////////////////////////////////////// BOOKS AFTER SPECIFIC YEAR  //////////////////////////////////////////////////////////
        int year = 1940;
        Predicate<Book> afterSpecificYear = x -> x.getPublicationYear() > year;
        List<Book> booksAfterSpecificYear = books.stream().filter(afterSpecificYear).collect(Collectors.toList());
        booksAfterSpecificYear.forEach(System.out::println);

        ///////////////////////////////////////////////// SORT BOOKS BY RATING IN DESCENDING ORDER  //////////////////////////////////////////////////////////

        System.out.println("-------------------------------------------------------------------");

        List<Book> sortedByRatingDesc = books.stream().sorted(Comparator.comparing(Book::getRating).reversed()).collect(Collectors.toList());
        sortedByRatingDesc.forEach(System.out::println);

        System.out.println("-------------------------------------------------------------------");


        ///////////////////////////////////////////////// HIGHEST RATED BOOK  //////////////////////////////////////////////////////////

        Book highestRated = sortedByRatingDesc.stream().max(Comparator.comparing(Book::getRating)).get();
        System.out.println(highestRated);

        System.out.println("-------------------------------------------------------------------");

        ///////////////////////////////////////////////// GROUP BOOKS BY AUTHOR AND CALCULATE AVERAGE RATING  //////////////////////////////////////////////////////////

        Map<String,List<Book>> booksByAuthor = books.stream().collect(Collectors.groupingBy(Book::getAuthor));
        booksByAuthor.forEach((a, b) -> {
            System.out.println("Author: "+a);
            double sum = 0;
            for (Book book: b) {
                System.out.println(b);
                sum += book.getRating();
            }
            if(b.size() > 1){
                System.out.println("Average: "+sum/b.size());
            }
        });

        System.out.println("-------------------------------------------------------------------");

        ///////////////////////////////////////////////// CALCULATE TOTAL NUMBER OF PAGES FOR ALL BOOKS  //////////////////////////////////////////////////////////

        int totalPages = books.stream().mapToInt((x) -> x.getPages()).sum();
        System.out.println(totalPages);

    }
}
