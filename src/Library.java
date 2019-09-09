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

    public Author addAuthor(){

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
        return author;
    }

    public void addBook(){
        //name
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        //author
        List<Author> listOfBookAuthors = new ArrayList<>();

        boolean stayInLoop = true;
        while (stayInLoop){

            printListsAuthorsAndBookAuthors(listOfBookAuthors);
            printChoicesAddBookAuhor();
            char choice = makeAChoiceBookAuthor();

            switch (choice){

                case '1':
                    listOfBookAuthors.add(addAuthor());
                    break;
                case '3':
                    if (listOfBookAuthors.isEmpty()){
                        System.out.println("Your book must have an author. Add an author for your book. If you don't want to create the book anymore press 4");
                    }
                    else stayInLoop = false;
                    break;
                case '4':
                    listOfBookAuthors.removeAll(authors);
                    stayInLoop=false;
                    break;
            }

        }

        if (listOfBookAuthors.isEmpty()){
            System.out.println("You didnt created any book.");
        }
        else {
            System.out.print("Input the number of pages for this book: ");

            while (!sc.hasNext("^((?!(0))[0-9])$+")){
                System.out.println("Input only positive integers please: ");
                sc.next();
            }
            int numberOfPages = Integer.parseInt(sc.nextLine());

            System.out.println("Input the rating of this book: ");

            while (!sc.hasNext("\\.\\d+")){
                System.out.println("Input only positive numbers: ");
                sc.next();
            }
            double rating = Double.parseDouble(sc.nextLine());

            books.add(new Book(name,numberOfPages,rating,listOfBookAuthors));

            numberOfBooks++;
        }


    }

    public char makeAChoiceMain(){

        Scanner sc = new Scanner(System.in);
        String str;
        do {
            printChoices();
            str = sc.nextLine();

        } while (!isChoiceValid(str));
        return str.charAt(0);

    }

    public char makeAChoiceBookAuthor(){

        Scanner sc = new Scanner(System.in);

        while (!sc.hasNext("[1234]")){
            System.out.println("Choose 1,2,3 or 4: ");
            sc.next();
        }
        String choice = sc.nextLine();

        System.out.println(choice);

        return '1';

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
        for (Author author : listOfBookAuthors
        ) {
            System.out.println(author);
        }

        System.out.println();

        System.out.println("Available authors");
        for (Author author : this.authors
             ) {
            System.out.println(author);
        }
        System.out.println();
    }
    public void printChoicesAddBookAuhor(){
        System.out.println("1. Create author for your book");
        System.out.println("2. Add author to your book from existing authors");
        System.out.println("3. Finish adding authors");
        System.out.println("4. Cancel creating the book");
        System.out.println();
        System.out.print("Insert your option: ");
    }

}

