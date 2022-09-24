public class Person {
  public void walk(Dog c) {
    c = new Dog();
    c.size = 42;
  }
  public static void main(String[] args) {
    Dog dog1 = new Dog();
    Person person1 = new Person();
    person1.walk(dog1);
    dog1.size = 30;
  }
}
