public class Author {

    private int id;
    private String name;
    private String email;
    private char gender;
    private String phoneNumber;
    private static int authorCount = 1;


    public Author(String name, String email, char gender, String phoneNumber) {

        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.id = authorCount++;

    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return "Author id: " + id + " [name = " + this.name + ", email = " + this.email + ", gender = " + this.gender + ", Phone Number = " + this.phoneNumber + "]";
    }

}
