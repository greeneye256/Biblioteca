import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {

    private String nameOfLibrary;
    private List<Book> books;
    private List<Author> authors;

    public List<Book> getBooks() {
        return books;
    }

    public Library(String nameOfLibrary) {

        this.nameOfLibrary = nameOfLibrary;
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();

    }

    public void printLibrary() {

        System.out.println();
        System.out.println(this.nameOfLibrary.toUpperCase());
        System.out.println("Number of books: " + books.size());
        System.out.println("Number of authors: " + authors.size());
        System.out.println();

    }

    public void listBooks() {

        for (Book book : books
        ) {
            System.out.println(book);
        }
        System.out.println();

    }

    public void listAuthors() {

        System.out.println();
        for (Author author : authors
        ) {
            System.out.println(author);
        }
        System.out.println();

    }

    public Author createAuthor() {

        //name

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter author's full name: ");
        String name = sc.nextLine();

        //email

        System.out.print("Enter author's email: ");

        String email = sc.nextLine();

        while (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
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
        return author;

    }

    // for validating a new author if it doesn't already exists in library. not implemented

    public void addAuthorToAuthors(Author author) {
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

    public void addBook() {
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

        boolean isRatingValid = false;

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

    public void addBook(String name, int pages, double rating, Author... authors) {
        List<Author> authorsForBook = new ArrayList<>();
        for (Author aut : authors
        ) {
            if (aut != null) authorsForBook.add(aut);
        }
        if (authorsForBook.size() > 0) {
            books.add(new Book(name, pages, rating, authorsForBook));
        }
    }

    public char makeAChoiceBookAuthor() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose 1,2,3 or 4: ");
        String choice = sc.nextLine();

        while (!choice.matches("[1234]")) {
            System.out.print("You entered a wrong value. Choose 1,2,3 or 4: ");
            choice = sc.nextLine();
        }
        return choice.charAt(0);

    }


    public void deleteAuthors() {


        listAuthors();

        Scanner sc = new Scanner(System.in);

        boolean stayInLoop = true;

        String choice;
        String[] values;
        int[] indexes;

        while (stayInLoop) {
            System.out.print("Choose the indexes of the authors you want to delete separated with comma (ex. 1,2,3,5,9) or 0 for cancel: ");
            choice = sc.nextLine();
            if (choice.equals("0")) break;
            if (choice.matches("^\\s*[1-9]\\d*(?:\\s*,\\s*[1-9]\\d*)*$")) {
                values = choice.split(",");
                indexes = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    indexes[i] = Integer.parseInt(values[i]);
                }
                if (!distinctValuesAndInRange(indexes)) {
                } else {

                    for (int index : indexes
                    ) {
                        for (Book book : books
                        ) {
                            for (String str : book.getAuthorsEmail()
                            ) {
                                if (str.equals(authors.get(index - 1).getEmail())) {
                                    books.remove(book);
                                    authors.remove(index - 1);
                                } else {
                                    authors.remove(index - 1);
                                }
                            }
                        }


                    }
                    stayInLoop = false;
                }

            } else System.out.print("You have to enter only numbers separated by comma!");
        }


    }

    public void printListsAuthorsAndBookAuthors(List<Author> listOfBookAuthors) {

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

    public void printChoicesAddBookAuthor() {
        System.out.println("1. Create author for your book");
        System.out.println("2. Add author to your book from existing authors");
        System.out.println("3. Finish adding authors");
        System.out.println("4. Cancel (without creating the book)");
        System.out.println();
        System.out.print("Insert your option: ");
    }

    public boolean distinctValuesAndInRange(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > authors.size()) {
                System.out.print("One of your index is grater than maximum index.");
                return false;
            }
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    System.out.println("You can't put two or more numbers with the same value!");
                    return false;
                }
            }
        }
        return true;
    }

}

