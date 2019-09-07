import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Author createAuthor(){


        Scanner sc = new Scanner(System.in);
        System.out.println("name = ");
        String name = sc.nextLine();
        System.out.println("email = ");
        String email = sc.nextLine();
        char gender = makeAChoiceForGender();
        String phoneNumber = inputPhoneNumber();
        return new Author(name,email,gender,phoneNumber);
    }

    public static String inputPhoneNumber(){
        Scanner sc = new Scanner(System.in);
        String str;
        do {
            System.out.println("Enter phone number:");
            str = sc.nextLine();
        }
        while (!isPhoneValid(str));
        return str;
    }

    public static char makeAChoice(){

        Scanner sc = new Scanner(System.in);
        String str;
        do {
            printChoices();
            str = sc.nextLine();

        } while (!isValid(str));
        return str.charAt(0);

    }

    public static char makeAChoiceForGender(){

        Scanner sc = new Scanner(System.in);
        String str;
        do {
            System.out.println("Enter gender. ('m' or 'f')");
            str = sc.nextLine();

        } while (!isGenderValid(str));
        return str.charAt(0);

    }

    public static void printLibrary(int numberOfBooks, int numberOfAuthors, List<Book> books, List<Author> authors){

        System.out.println("LIBRARY OF CLUJ-NAPOCA");
        System.out.println("Number of books: " + numberOfBooks);
        System.out.println("Number of authors: " + numberOfAuthors);
        System.out.println("");
        for (Book book:books
             ) {
            System.out.println(book);
        }
        System.out.println("");
        for (Author author:authors
             ) {
            System.out.println(author);
        }
        System.out.println("");

    }

    public static void printChoices(){

        System.out.println("a) Adauga autori");
        System.out.println("b) Adauga carti cu autori existenti deja in memoria(array/arraylist)");
        System.out.println("c) Sterge autori");
        System.out.println("d) sterge carti");
        System.out.println("e) sa cautam carti dupa autori");
        System.out.println("f) sa cautam cartea cea mai bine cotata(cel mai mare rating) a unui autor.");
        System.out.println();
        System.out.println("q) exit the program");
        System.out.println();
        System.out.println("Input the letter which represents your choice:");
    }

    public static boolean isValid(String str){

        if (str.length()>1){
            System.out.println("Only one letter please!");
            return false;
        }
        else {
            char letter = str.charAt(0);
            if ((letter < 'a' || letter > 'g') && letter !='q'){
                System.out.println("You entered a wrong letter!");
                return false;
            }
            else return true;
        }
    }

    public static boolean isPhoneValid(String str){

        int countWrong = 0;

        for (int i = 0; i < str.length() ; i++) {
            char letter = str.charAt(i);
            if (letter < '0' || letter > '9'){
                countWrong++;
            }
        }

        if (str.length()<4 || str.length()>15){
            System.out.println("Can't be number");
            return false;
        }
        else if (countWrong>0){
            System.out.println("Your input contains values that are not digits");
            return false;
        }
        else return true;
    }

    public static boolean isGenderValid(String str){

        if (str.length()>1){
            System.out.println("Only one letter please! ('m' or 'f')");
            return false;
        }
        else {
            char letter = str.charAt(0);
            if (letter !='m' && letter !='f'){
                System.out.println("Wrong input! Enter gender. ('m' or 'f')");
                return false;
            }
            else return true;
        }
    }

    public static void main(String[] args) {

        int numberOfBooks = 0;
        int numberOfAuthors = 0;

        List<Book> libraryBooks = new ArrayList<>(0);
        List<Author> libraryAuthors = new ArrayList<>(0);

        printLibrary(numberOfBooks,numberOfAuthors,libraryBooks,libraryAuthors);

        char choice = makeAChoice();

        switch (choice){
            case 'a':
                libraryAuthors.add(createAuthor());
                numberOfAuthors++;
                printLibrary(numberOfBooks,numberOfAuthors,libraryBooks,libraryAuthors);

        }

    }

}
