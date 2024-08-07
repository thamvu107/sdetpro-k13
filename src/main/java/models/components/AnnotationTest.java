package models.components;

import models.components.global.NavComponent;

public class AnnotationTest {

  public <Teo> void printComponentXpathSelector(Class<Teo> componentClass) {
    String xpathSelector = componentClass.getAnnotation(ComponentXpathSelector.class).value();
    System.out.println(xpathSelector);
  }

  public static void main(String[] args) {
    new AnnotationTest().printComponentXpathSelector(NavComponent.class);
  }

}
