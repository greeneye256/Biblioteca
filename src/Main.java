import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Library library = new Library("Library of Cluj-Napoca");

        char choice;

        boolean stayInLoop = true;

        while (stayInLoop){

            library.printLibrary();
            choice = library.makeAChoiceMain();

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
