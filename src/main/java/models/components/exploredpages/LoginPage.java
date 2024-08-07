package models.components.exploredpages;

public abstract class LoginPage {

    public void login() {
        System.out.println(username());
        System.out.println(password());
        System.out.println(loginBtn());
    }

    public void verifyLoginSuccess() {
        System.out.println("Verifying Dashboard display");
    }

    public abstract String username();

    public abstract String password();

    public abstract String loginBtn();
}
