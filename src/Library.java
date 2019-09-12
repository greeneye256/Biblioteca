import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Library {

    private String nameOfLibrary;
    private List<Book> books;
    private List<Author> authors;

    Library(String nameOfLibrary) {

        this.nameOfLibrary = nameOfLibrary;
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();

    }

    void printLibrary() {

        System.out.println();
        System.out.println(this.nameOfLibrary.toUpperCase());
        System.out.println("Number of books: " + books.size());
        System.out.println("Number of authors: " + authors.size());
        System.out.println();

    }

    void listBooks() {

        for (Book book : books
        ) {
            System.out.println(book);
        }
        System.out.println();

    }

    void listAuthors() {

        System.out.println();
        for (Author author : authors
        ) {
            System.out.println(author);
        }
        System.out.println();

    }

    void createAuthor() {

        //name

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter author's full name: ");
        String name = sc.nextLine();

        //email

        System.out.print("Enter author's email: ");

        String email = sc.nextLine();

        while (!email.matches("^([\\w-.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            System.out.println("Your email is not valid, please enter a valid email: ");
            email = sc.nextLine();
        }

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
        authors.add(author);
    }

    void addAuthorToAuthors(Author author) {
        int countEqual = 0;
        for (Author writer : authors
        ) {
            if (writer.getEmail().equals(author.getEmail())) {
                System.out.println("The author already exists. It won't be added to the list.");
                countEqual++;
            }
        }
        if (countEqual == 0) {
            authors.add(author);
        }
    }

    void createBook() {
        //name
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book name: ");
        String name = sc.nextLine();
        //author

        boolean authorsAreAdded = false;

        List<Author> listOfBookAuthors = new ArrayList<>();

        while (!authorsAreAdded) {

            System.out.println();
            System.out.println("Available authors to add to the book: ");

            listAuthors();

            System.out.print("Select authors by ID to add them to the book. You can add multiple authors separated with comma: ");

            String choice = sc.nextLine();

            while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
                System.out.print("Enter valid numbers(Id): ");
                choice = sc.nextLine();
            }

            List<Integer> ids = new ArrayList<>();
            for (String id : choice.split("\\s*,\\s*")
            ) {
                ids.add(Integer.parseInt(id));
            }

            for (Author author : authors
            ) {
                if (ids.contains(author.getId())) {
                    listOfBookAuthors.add(author);
                }
            }

            if (listOfBookAuthors.size() == 0) {
                System.out.println("Your numbers did't match any Id so the book is not created.");
                return;
            }

            System.out.println();
            for (Author author : listOfBookAuthors
            ) {
                System.out.println(author);
            }
            System.out.println();

            System.out.print("Confirm authors that will be added to your book. Type 'y' for yes or 'n' for no. : ");
            String confirmList = sc.nextLine();

            while (!confirmList.matches("[ynYN]")) {

                System.out.print("Enter a valid letter. Confirm authors that will be added to your book. Type 'y' for yes or 'n' for no.");
                confirmList = sc.nextLine();

            }

            if (confirmList.matches("[nN]")){
                listOfBookAuthors.clear();
                authorsAreAdded = false;
            }else authorsAreAdded = true;

        }

        System.out.print("Input the number of pages for this book: ");
        String strPages = sc.nextLine();

        while (!strPages.matches("^[1-9]\\d*$")){

            System.out.print("Only positive integers please. Input the number of pages for this book: ");
            strPages = sc.nextLine();

        }

        int numberOfPages = Integer.parseInt(strPages);

        //rating

        double rating;

        System.out.print("Input the rating of this book: ");

        boolean isRatingValid;

        do {
            try {
                rating = sc.nextDouble();
            } catch (Exception e) {
                rating = 6;
                sc.next();
            }

            if (rating < 1 || rating > 5) {
                System.out.print("Invalid number input! Enter a valid number for rating - (between 1 and 5): ");
                isRatingValid = false;
            }
            else isRatingValid = true;

        } while (!isRatingValid);

        Book newBook = new Book(name, numberOfPages, rating, listOfBookAuthors);
        System.out.println("This is your new book: ");
        System.out.println();
        System.out.println(newBook);
        books.add(newBook);
    }

    void createBook(String name, int pages, double rating, Author... authors){
        List<Author> listOfAuthorsForBook = new ArrayList<>(Arrays.asList(authors));
        books.add(new Book(name,pages,rating,listOfAuthorsForBook));
    }

    void deleteAuthors() {


        boolean authorsAreDeleted = false;

        List<Author> listOfAuthorsToBeDeleted = new ArrayList<>();
        List<Book> listOfBooksToBeDeleted = new ArrayList<>();

        while (!authorsAreDeleted) {

            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println("List of authors");

            listAuthors();

            System.out.print("Choose an author or more by id separated with comma to delete. ");

            String choice = sc.nextLine();

            while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
                System.out.print("Enter valid numbers(Id): ");
                choice = sc.nextLine();
            }

            List<Integer> ids = new ArrayList<>();
            for (String id : choice.split("\\s*,\\s*")
            ) {
                ids.add(Integer.parseInt(id));
            }

            for (Author author : authors
            ) {
                if (ids.contains(author.getId())) {
                    listOfAuthorsToBeDeleted.add(author);
                }
            }
            for (Book book:books
                 ) {
                for (Author author:book.getAuthors()
                     ) {
                    if (ids.contains(author.getId())){
                        listOfBooksToBeDeleted.add(book);
                        break;
                    }
                }
            }

            if (listOfAuthorsToBeDeleted.size() == 0) {
                System.out.println("Your numbers did't match any Id so no author wiil be deleted.");
                return;
            }

            System.out.println();
            for (Author author : listOfAuthorsToBeDeleted
            ) {
                System.out.println(author);
            }
            System.out.println();
            for (Book book:listOfBooksToBeDeleted
                 ) {
                System.out.println(book);
            }
            System.out.println();
            System.out.println("Notice that if you delete an author all books written by that author will also be deteted.");
            System.out.println();
            System.out.print("Confirm authors that will be deleted from your library. Type 'y' for yes or 'n' for no. : ");
            String confirmList = sc.nextLine();

            while (!confirmList.matches("[ynYN]")) {

                System.out.print("Enter a valid letter. Confirm authors that will be deleted from your library. Type 'y' for yes or 'n' for no. : ");
                confirmList = sc.nextLine();

            }

            if (confirmList.matches("[nN]")){
                listOfAuthorsToBeDeleted.clear();
                authorsAreDeleted = false;
            }else authorsAreDeleted = true;

        }

        for (Author author:listOfAuthorsToBeDeleted
             ) {
            authors.remove(author);
        }
        for (Book book :listOfBooksToBeDeleted
             ) {
            books.remove(book);
        }
    }

    void deleteBooks() {


        boolean booksAreDeleted = false;

        List<Book> listOfBooksToBeDeleted = new ArrayList<>();

        while (!booksAreDeleted) {

            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println("List of books");

            listBooks();

            System.out.print("Choose a book or more, by id, separated with comma, to delete: ");

            String choice = sc.nextLine();

            while (!choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
                System.out.print("Enter valid numbers(Id): ");
                choice = sc.nextLine();
            }

            List<Integer> ids = new ArrayList<>();
            for (String id : choice.split("\\s*,\\s*")
            ) {
                ids.add(Integer.parseInt(id));
            }

            for (Book book : books
            ) {
                if (ids.contains(book.getId())) {
                    listOfBooksToBeDeleted.add(book);
                }
            }

            if (listOfBooksToBeDeleted.size() == 0) {
                System.out.println("Your numbers did't match any Id so no book will be deleted.");
                return;
            }

            System.out.println();
            for (Book book : listOfBooksToBeDeleted
            ) {
                System.out.println(book);
            }
            System.out.println();

            System.out.print("Confirm if you want this books to be deleted from your library. Type 'y' for yes or 'n' for no. : ");
            String confirmList = sc.nextLine();

            while (!confirmList.matches("[ynYN]")) {

                System.out.print("Enter a valid letter. Confirm if you want this books to be deleted from your library. Type 'y' for yes or 'n' for no. : ");
                confirmList = sc.nextLine();

            }

            if (confirmList.matches("[nN]")){
                listOfBooksToBeDeleted.clear();
                booksAreDeleted = false;
            }else booksAreDeleted = true;

        }

        for (Book book:listOfBooksToBeDeleted
        ) {
            books.remove(book);
        }

    }

    void printBooksFromAuthor(){
        Scanner sc = new Scanner(System.in);
        listAuthors();
        int booksFound = 0;
        System.out.print("Please enter the Id from author to search his books: ");
        String authorId = sc.nextLine();
        while (!authorId.matches("^[1-9]\\d*$")){
            System.out.print("Wrong input! Please enter the Id from author to search his books: ");
            authorId = sc.nextLine();
        }
        int idToSearch = Integer.parseInt(authorId);
        boolean authorExists = false;
        for (Author author:authors
             ) {
            if (idToSearch == author.getId()){
                authorExists = true;
                break;
            }
        }
        if (!authorExists){
            System.out.println("No author has the Id you have entered!");
            System.out.println();
            return;
        }
        for (Book book:books
             ) {
            for (Author author:book.getAuthors()
                 ) {
                if (author.getId()==idToSearch){
                    booksFound++;
                    System.out.println(book);
                }
            }
        }
        if (booksFound == 0){
            System.out.println("The library doesn't have books from the author you have entered");
        }

    }

    void printBestBookFromAuthor(){
        Scanner sc = new Scanner(System.in);
        listAuthors();
        int booksFound = 0;
        System.out.print("Please enter the Id from author to search for his best book: ");
        String authorId = sc.nextLine();
        while (!authorId.matches("^[1-9]\\d*$")){
            System.out.print("Wrong input! Please enter the Id from author to search his books: ");
            authorId = sc.nextLine();
        }
        int idToSearch = Integer.parseInt(authorId);
        boolean authorExists = false;
        for (Author author:authors
        ) {
            if (idToSearch == author.getId()){
                authorExists = true;
                break;
            }
        }
        if (!authorExists){
            System.out.println("No author has the Id you have entered!");
            System.out.println();
            return;
        }

        double maxValue = 0.0d;
        List<Book> booksWithMaxRating = new ArrayList<>();

        for (Book book:books
        ) {
            for (Author author:book.getAuthors()
            ) {
                if (author.getId()==idToSearch){
                    if (book.getRating() == maxValue){
                        booksWithMaxRating.add(book);
                    }
                    if (book.getRating()>maxValue){
                        booksWithMaxRating.clear();
                        booksWithMaxRating.add(book);
                        maxValue = book.getRating();
                    }
                    booksFound++;
                }
            }
        }
        if (booksFound == 0){
            System.out.println("The library doesn't have books from the author you have entered");
        }

        for (Book book:booksWithMaxRating
             ) {
            System.out.println(book);
        }

    }

}

