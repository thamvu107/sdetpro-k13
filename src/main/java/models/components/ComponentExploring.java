package models.components;

import java.lang.reflect.Constructor;
import models.components.exploredpages.ExternalLoginPage;
import models.components.exploredpages.InternalLoginPage;
import models.components.exploredpages.LoginPage;

public class ComponentExploring {

  public <T extends LoginPage> void login(Class<T> loginPageClass) {

    Class<?>[] params = new Class[]{};
    try {
      Constructor<T> constructor = loginPageClass.getConstructor(params);
      T loginPageObj = constructor.newInstance();
      loginPageObj.login();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new ComponentExploring().login(InternalLoginPage.class);
    new ComponentExploring().login(ExternalLoginPage.class);
  }

}
