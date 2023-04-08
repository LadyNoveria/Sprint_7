public class CreateCourierDto {
    private String login;
    private String password;
    private String firstName;

    public CreateCourierDto(String login, String password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
}
