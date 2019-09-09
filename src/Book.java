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
    public String getAuthorEmail(Author author){
        return author.getEmail();
    }
    public char getAuthorGender(Author author){
        return author.getGender();
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
