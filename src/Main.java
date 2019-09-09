import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Author a1 = new Author("fds sdfsd", "safas@sd.cd", 'm', "12341231");
        System.out.println(a1.getPhoneNumber());

        Library library = new Library("Library of Cluj-Napoca");

        char choice;

        boolean stayInLoop = true;

        while (stayInLoop){

            library.printLibrary();
            choice = library.makeAChoice();

            switch (choice){
                case 'a':
                    library.addAuthor();
                    System.out.println("");
                    stayInLoop = continueProgram();


                    break;
                case 'b':
                    library.addBook();
                case 'g':
                    library.listAuthors();
                    stayInLoop = continueProgram();
                    break;
                case 'h':
                    library.listBooks();
                    stayInLoop = continueProgram();
                    break;
                case 'q':
                    stayInLoop = false;
                    break;
            }
        }
    }

    public static boolean continueProgram(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'q' to exit program or anything else to continue: ");
        String check = scanner.nextLine();
        return (!check.equals("q"));

    }

}
