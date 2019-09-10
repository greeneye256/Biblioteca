import java.util.ArrayList;
import java.util.List;

public class Book {

    private String name;
    private List<Author> authors;
    private int numberOfPages;
    private double rating;

    public Book(String name, int numberOfPages, double rating, List<Author> authors) {
        this.name = name;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getAuthorsName(){
        StringBuilder result = new StringBuilder();
        result.append("*");
        for (Author author:authors
        ) {
            result.append(author.getName() + " * ");
        }
        String autori = result.toString();
        return autori;
    }
    public List<String> getAuthorsEmail(){
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
        result.append("Book [name = " + name + ", authors = ");
        for (Author author:authors
        ) {
            result.append(author.toString() + ", ");
        }
        result.append("number of pages = " + numberOfPages + ", rating = " + rating + "]");
        return result.toString();
    }

}
