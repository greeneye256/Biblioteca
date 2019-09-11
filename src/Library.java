import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {

    private String nameOfLibrary;
    private List<Book> books;
    private List<Author> authors;
    private int numberOfBooks;
    private int numberOfAuthors;
    private int bookId = 1;

    public int getNumberOfAuthors() {
        numberOfAuthors = 0;
        for (Author author:authors
             ) {
            numberOfAuthors++;
        }
        return numberOfAuthors;
    }

    public int getNumberOfBooks() {
        numberOfBooks = 0;
        for (Book book:books
             ) {
            numberOfBooks++;
        }
        return numberOfBooks;
    }

    public Library(String nameOfLibrary) {

        this.nameOfLibrary = nameOfLibrary;
        this.books = new ArrayList<>();
        this.authors  = new ArrayList<>();

    }

    public void printLibrary(){

        System.out.println();
        System.out.println(this.nameOfLibrary.toUpperCase());
        System.out.println("Number of books: " + this.numberOfBooks);
        System.out.println("Number of authors: " + this.numberOfAuthors);
        System.out.println("");

    }

    public void printChoices(){

        System.out.println("a) Add author");
        System.out.println("b) Add book");
        System.out.println("c) Delete authors");
        System.out.println("d) Delete books");
        System.out.println("e) Search authors");
        System.out.println("f) Search best book of an author");
        System.out.println("g) List authors");
        System.out.println("h) List books");
        System.out.println("q) exit the program");
        System.out.println();
    }

    public void listBooks(){

        for (Book book:books
        ) {
            System.out.println((books.indexOf(book) + 1) + ". " +book);
        }
        System.out.println();

    }

    public void listAuthors(){

        System.out.println();
        for (Author author:authors
        ) {
            System.out.println((authors.indexOf(author) + 1) + ". " + author);
        }
        System.out.println();

    }

    public char makeAChoiceMain(){

        Scanner sc = new Scanner(System.in);
        String str;
        printChoices();
        System.out.print("Input the letter which represents your choice: ");
        str = sc.nextLine();

        while (!str.matches("[abcdefghq]")){

            System.out.print("You entered a wrong letter! Input the letter which represents your choice: ");
            str = sc.nextLine();

        }
        return str.charAt(0);

    }

    public Author createAuthor() {

        //name

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter author's full name: ");
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
        String gend;
        gend = sc.nextLine();

        while (!gend.matches("[fmFM]")) {
            System.out.print("Please enter author's gender corectly. ('m' or 'f'): ");
            gend = sc.nextLine();
        }

        char gender = gend.charAt(0);

        //phone number

        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        while (!phoneNumber.matches("\\d+")) {
            System.out.print("Author's phone number have to contain only digits. Please input the phone number corectly: ");
            phoneNumber = sc.nextLine();
        }

        Author author = new Author(name, email, gender, phoneNumber);
        return author;
    }


    public void addAuthorToAuthors(Author author){
        int countEqual = 0;
        for (Author writer:authors
             ) {
            if (writer.getEmail().equals(author.getEmail())){
                System.out.println("The author already exists. It won't be added to the list.");
                countEqual++;
            }
        }
        if (countEqual==0){
            authors.add(author);}
        numberOfAuthors++;
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
                    boolean authorExists = false;
                    Author authorToAdd = createAuthor();
                    for (Author author:authors
                         ) {
                        if (authorToAdd.getEmail().equals(author.getEmail())){
                            System.out.println("The author is already in the authors list.");
                            authorExists = true;
                            break;
                        }
                    }
                    if (authorExists=false){
                        addAuthorToAuthors(authorToAdd);
                        listOfBookAuthors.add(authorToAdd);
                    }
                    break;
                case '3':
                    if (listOfBookAuthors.isEmpty()){
                        System.out.println("Your book must have an author. Add an author for your book. If you don't want to create the book anymore press 4");
                    }
                    else stayInLoop = false;
                    break;
                case '4':
                    listOfBookAuthors.removeAll(authors);
                    stayInLoop = false;
                    break;
            }

        }

        if (listOfBookAuthors.isEmpty()){
            System.out.println("You didn't created any book.");
        }
        else {
            String strPages;
            boolean firstTime = true;
            do {

                if (firstTime){
                    System.out.print("Input the number of pages for this book: ");
                    strPages = sc.nextLine();
                }
                else {
                    System.out.print("Only positive integers please. Input the number of pages for this book: ");
                    strPages = sc.nextLine();
                }
                firstTime = false;
            }
            while (!strPages.matches("^[1-9]\\d*$"));
            int numberOfPages = Integer.parseInt(strPages);

            double rating;
            System.out.print("Enter the rating of the book - (between 1 and 5): ");
            do {
                try {
                    rating = sc.nextDouble();
                }
                catch (Exception e){
                    System.out.print("Invalid number input! Enter a valid number for rating - (between 1 and 5): ");
                    rating = 6;
                    sc.next();
                }

                if (rating < 1 || rating > 5) {
                    System.out.print("Invalid number input! Enter a valid number for rating - (between 1 and 5): ");
                }
            } while (rating < 1 || rating >5);



            books.add(new Book(name,numberOfPages,rating,listOfBookAuthors));

            numberOfBooks++;
        }


    }

    public void addBook(String name, int pages, double rating,Author... authors){
        List<Author> authorsForBook = new ArrayList<>();
        for (Author aut:authors
             ) {
            if (aut!=null)authorsForBook.add(aut);
        }
        if (authorsForBook.size()>0){
            books.add(new Book(name,pages,rating,authorsForBook));
            numberOfBooks++;
        }
    }







    public char makeAChoiceBookAuthor(){

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose 1,2,3 or 4: ");
        String choice = sc.nextLine();

        while (!choice.matches("[1234]")){
            System.out.print("You entered a wrong value. Choose 1,2,3 or 4: ");
            choice = sc.nextLine();
        }
        return choice.charAt(0);

    }



    public void deleteAuthors(){


        listAuthors();

        Scanner sc = new Scanner(System.in);

        boolean stayInLoop = true;

        String choice;
        String[] values;
        int[] indexes;

        while (stayInLoop){
            System.out.print("Choose the indexes of the authors you want to delete separated with comma (ex. 1,2,3,5,9) or 0 for cancel: ");
            choice = sc.nextLine();
            if (choice.equals("0"))break;
            if (choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
                values = choice.split(",");
                indexes = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    indexes[i] = Integer.parseInt(values[i]);
                }
                if (!distinctValuesAndInRange(indexes)) {
                }
                else {

                    for (int index:indexes
                    ) {
                        for (Book book:books
                             ) {
                            for (String str:book.getAuthorsEmail()
                                 ) {
                                if (str.equals(authors.get(index-1).getEmail())){
                                    books.remove(book);
                                    authors.remove(index-1);
                                    numberOfAuthors--;
                                    numberOfBooks--;
                                }
                                else {
                                    authors.remove(index-1);
                                    numberOfAuthors--;
                                }
                            }
                        }


                    }
                    stayInLoop = false;
                }

            }
            else System.out.print("You have to enter only numbers separated by comma!");
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
        System.out.println("Available authors");
        for (Author author : this.authors
             ) {
            System.out.println(author);
        }
        System.out.println();

        System.out.println("Current authors list of your book");
        for (Author author : listOfBookAuthors
        ) {
            System.out.println(author);
        }
        System.out.println();
    }
    public void printChoicesAddBookAuhor(){
        System.out.println("1. Create author for your book");
        System.out.println("2. Add author to your book from existing authors");
        System.out.println("3. Finish adding authors");
        System.out.println("4. Cancel (without creating the book)");
        System.out.println();
        System.out.print("Insert your option: ");
    }
    public boolean distinctValuesAndInRange(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>authors.size()){
                System.out.print("One of your index is grater than maximum index.");
                return false;
            }
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    System.out.println("You can't put two or more numbers with the same value!");
                    return false;
                }
            }
        }
        return true;
    }

}

