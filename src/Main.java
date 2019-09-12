import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        Library library = new Library("Library of Cluj-Napoca");

        Author shakespeare = new Author("William Shakespeare","william_shakespeare@gmail.com",'m',"004343454352462");

        Author dickens = new Author("Charles Dickens","charlesdickens@gmail.com",'m',"004143453222462");

        Author austen = new Author("Jane Austen","janeausten@yahoo.com",'f',"001186575432");

        Author scott = new Author("F. Scott Fitzgerald","f_scott_fitzgerald@gmail.com",'m',"0021321765322");
        Author virginia = new Author("Virginia Woolf","virginiawoolf@gmail.com",'f',"00386435400");
        Author kafka = new Author("Franz Kafka","franzkafka@yahoo.com",'m',"004726452886");
        Author rowling = new Author("Joanne Rowling","j_k_rowling@yahoo.com",'f',"00428500456345");
        Author orwell = new Author("George Orwell","georgeorwell@yahoo.com",'m',"0013643530678");
        Author faulkner = new Author("William Faulkner","william_faulkner@gmail.com",'m',"00519996747732");
        Author eliot = new Author("George Eliot","george_eliot@gmail.com",'f',"001565567477960");
        Author sona = new Author("Sona Charaipotra","sonacharaipotra@gmail.com",'f',"0043145654747");
        Author dhonielle = new Author("Dhonielle Clayton","dhonielle_clayton@gmail.com",'f',"0043145654747");
        Author stevens = new Author("Mark Stevens","markstevens@yahoo.com",'m',"00256706788957");
        Author swan = new Author("Annalyn Swan","annalyn_swans@gmail.com",'f',"00334200254988");



        library.createBook("Romeo and Julliet",342,4.7,shakespeare);
        library.createBook("Great Expectations",432,4.5,dickens);
        library.createBook("Sense and Sensibility",277,4.2,austen);
        library.createBook("The Great Gatsby",215,4.4,scott);
        library.createBook("Mrs Dalloway",218,4.5,virginia);
        library.createBook("Der Process",144,4.2,kafka);
        library.createBook("Harry Potter",455,4.9,rowling);
        library.createBook("Light in August",83,3.7,faulkner);
        library.createBook("Animal Farm",188,4.5,orwell);
        library.createBook("The Mill on the Floss",842,3.2,eliot);
        library.createBook("Tiny Pretty Things",240,4.2,sona,dhonielle);
        library.createBook("De Kooning: An American Master",230,3.7,stevens,swan);

        library.addAuthorToAuthors(shakespeare);
        library.addAuthorToAuthors(dickens);
        library.addAuthorToAuthors(austen);
        library.addAuthorToAuthors(scott);
        library.addAuthorToAuthors(virginia);
        library.addAuthorToAuthors(kafka);
        library.addAuthorToAuthors(rowling);
        library.addAuthorToAuthors(orwell);
        library.addAuthorToAuthors(faulkner);
        library.addAuthorToAuthors(eliot);
        library.addAuthorToAuthors(sona);
        library.addAuthorToAuthors(dhonielle);
        library.addAuthorToAuthors(stevens);
        library.addAuthorToAuthors(swan);




        char choice;

        boolean stayInLoop = true;

        while (stayInLoop){

            library.printLibrary();
            choice = makeAChoiceMain();

            switch (choice){
                case 'a':
                    library.createAuthor();
                    System.out.println("");
                    stayInLoop = continueProgram();


                    break;
                case 'b':
                    library.createBook();
                    break;

                case 'c':
                    library.deleteAuthors();
                    break;
                case 'd':
                    library.deleteBooks();
                    break;
                case 'e':
                    break;
                case 'f':
                    break;
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

    public static char makeAChoiceMain(){

        System.out.println("a) Add author");
        System.out.println("b) Add book");
        System.out.println("c) Delete authors");
        System.out.println("d) Delete books");
        System.out.println("e) Search authors");
        System.out.println("f) Search best book of an author");
        System.out.println("g) List authors");
        System.out.println("h) List books");
        System.out.println("q) Exit the program");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        String str;
        System.out.print("Input the letter which represents your choice: ");
        str = sc.nextLine();

        while (!str.matches("[abcdefghq]")){

            System.out.print("You entered a wrong letter! Input the letter which represents your choice: ");
            str = sc.nextLine();

        }
        return str.charAt(0);

    }

    public static boolean continueProgram(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 'q' to exit program or something else to continue: ");
        String check = scanner.nextLine();
        return (!check.equals("q"));

    }

}
