package sample;

public class StudentInfo {
    private String name;
    private String gender;
    private String birthday;
    private String address;
    private String username;
    private String email;
    private String password;
    private String major;
    private String placeOfBirth;
    private int intake;
    private float score;
    public StudentInfo(){

    }

    public StudentInfo (String name, String gender, String birthday, String address, String username, String email, String password, String major, String placeOfBirth, int intake, float score) {
        this.name = name == null ? "1" : name;
        this.gender = gender == null ? "1" : gender;
        this.birthday = birthday == null ? "1" : birthday;
        this.address = address == null ? "1": address;
        this.username = username == null ? "1": username;
        this.email = email == null ? "1": email;
        this.password = password == null ? "1": password;
        this.major = major == null ? "1": major;
        this.placeOfBirth = placeOfBirth == null ? "1": placeOfBirth;
        this.intake = intake == 0 ? 0 : intake;
        this.score = score == 0 ? 0 : score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public int getIntake() {
        return intake;
    }

    public void setIntake(int intake) {
        this.intake = intake;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
