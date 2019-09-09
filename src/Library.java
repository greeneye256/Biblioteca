import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {

    private String nameOfLibrary;
    private List<Book> books;
    private List<Author> authors;

    private int numberOfBooks = 0;
    private int numberOfAuthors = 0;

    public Library(String nameOfLibrary) {

        this.nameOfLibrary = nameOfLibrary;
        this.books = new ArrayList<>();
        this.authors  = new ArrayList<>();

    }

    public void addAuthor(){

        //name

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter author's full name: ");

        while (!sc.hasNext("[a-zA-Z]+")){
            System.out.println("The author's name cannot contain anything but letters.");

            sc.next();
        }

        String name = sc.nextLine();

        //email

        System.out.print("Enter author's email: ");

        String email;

        do {
            email = sc.nextLine();
        }

        while (!isMailValid(email));


        //gender

        System.out.print("Input author's gender (m or f): ");

        while (!sc.hasNext("[fm]")){
            System.out.print("Your input is not valid. Please input 'm' or 'f': ");

            sc.next();
        }
        char gender = sc.nextLine().charAt(0);

        //phone number

        System.out.print("Enter phone number: ");
        while (!sc.hasNext("\\d+")){
            System.out.print("Author's phone number have to contain only digits. Please input the phone number corectly: ");
            sc.next();
        }
        String phoneNumber = sc.nextLine();

        Author author = new Author(name,email,gender,phoneNumber);
        authors.add(author);
        numberOfAuthors++;
    }

    public void addBook(){
        List<Author> listOfBookAuthors = new ArrayList<>();
        printListsAuthorsAndBookAuthors(listOfBookAuthors);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter author's full name: ");
        String name = sc.nextLine();
    }

    public char makeAChoice(){

        Scanner sc = new Scanner(System.in);
        String str;
        do {
            printChoices();
            str = sc.nextLine();

        } while (!isChoiceValid(str));
        return str.charAt(0);

    }

    public void listBooks(){

        for (Book book:books
        ) {
            System.out.println(book);
        }
        System.out.println();

    }

    public void listAuthors(){

        System.out.println();
        for (Author author:authors
        ) {
            System.out.println(author);
        }
        System.out.println();

    }

    public void printLibrary(){

        System.out.println(this.nameOfLibrary.toUpperCase());
        System.out.println("Number of books: " + this.numberOfBooks);
        System.out.println("Number of authors: " + this.numberOfAuthors);
        System.out.println("");

    }

    public void printChoices(){

        System.out.println("a) Adauga autori");
        System.out.println("b) Adauga carti cu autori existenti deja in memoria(array/arraylist)");
        System.out.println("c) Sterge autori");
        System.out.println("d) sterge carti");
        System.out.println("e) sa cautam carti dupa autori");
        System.out.println("f) sa cautam cartea cea mai bine cotata(cel mai mare rating) a unui autor.");
        System.out.println("g) list authors");
        System.out.println("h) list books");
        System.out.println();
        System.out.println("q) exit the program");
        System.out.println();
        System.out.print("Input the letter which represents your choice: ");
    }

    public boolean isChoiceValid(String str){

        if (str.matches("[abcdefghq]")){
            return true;
        }
        else {

            System.out.println("You entered a wrong letter!");
            return false;

        }

    }

    private boolean isMailValid(String str){

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches())return true;
        else {
            System.out.print("Your input is not valid. Enter a valid email: ");
            return false;
        }

    }

    public void printListsAuthorsAndBookAuthors(List<Author> listOfBookAuthors){

        System.out.println();
        System.out.println("Current author list of your book");

        System.out.println();
        for (Author author : listOfBookAuthors
        ) {
            System.out.println(author);
        }

        System.out.println("Available authors");

        System.out.println();

        for (Author author : this.authors
             ) {
            System.out.println(author);
        }
        System.out.println();
    }

}

