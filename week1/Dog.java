public class Dog {
  int size = 100;
  String name;
  
  public Dog() {
    this.size = this.size * 2;
  }

  public Dog(int size) {
    this();
    this.size = size;
  }

  public Dog(int size, String name) {
    this(size);
    this.name = name;
  }
  
  public static void main(String[] args) {
    Dog Dong = new Dog(70, "Lee Quang Dong");
    System.out.println(Dong.name);
  }
}