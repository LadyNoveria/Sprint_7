import java.util.Random;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier courierGenerator(){
        return new Courier(getRandomString(), getRandomString(), getRandomString());
    }
    private static String getRandomString(){
        String ALPHABET = new String("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPRQRSTUVWXYZ".toCharArray());
        Random random = new Random();
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int rndCharAt = random.nextInt(ALPHABET.length());
            char rndChar = ALPHABET.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
