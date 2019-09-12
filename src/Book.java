import java.util.ArrayList;
import java.util.List;

public class Book {

    private String name;
    private List<Author> authors;
    private int numberOfPages;
    private double rating;
    private int id;
    private static int bookCount = 1;

    public Book(String name, int numberOfPages, double rating, List<Author> authors) {
        this.name = name;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
        this.rating = rating;
        id = bookCount++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    //not necesary

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
