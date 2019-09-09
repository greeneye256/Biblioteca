public class Author {

    private String name;
    private String email;
    private char gender;
    private String phoneNumber;

    public Author(String name, String email, char gender, String phoneNumber) {

        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;

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
        return "Author [name = " + this.name + ", email = " + this.email + ", gender = " + this.gender + ", Phone Number = " + this.phoneNumber + "]";
    }

}
