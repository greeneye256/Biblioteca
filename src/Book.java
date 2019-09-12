import java.util.ArrayList;
import java.util.List;

public class Book {

    private String name;
    private List<Author> authors;
    private int numberOfPages;
    private double rating;
    private int id;
    private static int bookCount = 1;

    Book(String name, int numberOfPages, double rating, List<Author> authors) {
        this.name = name;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
        id = bookCount++;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    List<Author> getAuthors() {
        return authors;
    }

    double getRating() {
        return rating;
    }

    void setRating(double rating) {
        this.rating = rating;
    }

    void setName(String name) {
        this.name = name;
    }

    List<String> getAuthorsEmail(){
        List<String> emails = new ArrayList<>();
        for (Author author1:authors
             ) {
            emails.add(author1.getEmail());
        }
        return emails;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Book ID = ");
        result.append(id);
        result.append(" [name = ");
        result.append(name);
        result.append(", authors = ");

        for (Author author:authors
        ) {
            result.append(author.toString());
            result.append(", ");
        }
        result.append("number of pages = ");
        result.append(numberOfPages);
        result.append(", rating = ");
        result.append(rating);
        result.append("]");
        return result.toString();
    }

}
