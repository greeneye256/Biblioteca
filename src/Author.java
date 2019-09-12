public class Author {

    private int id;
    private String name;
    private String email;
    private char gender;
    private String phoneNumber;
    private static int authorCount = 1;


    Author(String name, String email, char gender, String phoneNumber) {

        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.id = authorCount++;
    }

    int getId() {
        return id;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setName(String name) {
        this.name = name;
    }

    void setGender(char gender) {
        this.gender = gender;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    String getName() {
        return name;
    }

    char getGender() {
        return gender;
    }

    String getEmail() {
        return email;
    }

    String getPhoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public String toString() {
        return "Author id: " + id + " [name = " + this.name + ", email = " + this.email + ", gender = " + this.gender + ", Phone Number = " + this.phoneNumber + "]";
    }

}
