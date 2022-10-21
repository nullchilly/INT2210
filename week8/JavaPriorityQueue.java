import java.util.*;

class Student {
  private final int id;
  private final String name;
  private final double cgpa;

  public Student(int id, String name, double cgpa) {
    this.id = id;
    this.name = name;
    this.cgpa = cgpa;
  }

  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getCGPA() {
    return cgpa;
  }
}

class Priorities {

  private final PriorityQueue<Student> q = new PriorityQueue<>(
          Comparator.comparing(Student::getCGPA).reversed()
                  .thenComparing(Student::getName)
                  .thenComparing(Student::getID));

  public List<Student> getStudents(List<String> events) {
    events.forEach((event) -> {
      if (event.equals("SERVED")) {
        q.poll();
      } else {
        String[] sp = event.split(" ");
        q.add(new Student(Integer.parseInt(sp[3]), sp[1], Double.parseDouble(sp[2])));
      }
    });

    List<Student> students = new ArrayList<>();
    while (!q.isEmpty()) {
      students.add(q.poll());
    }

    return students;
  }
}

public class JavaPriorityQueue {

  private final static Scanner scan = new Scanner(System.in);
  private final static Priorities priorities = new Priorities();

  public static void main(String[] args) {
    int cnt = Integer.parseInt(scan.nextLine());
    List<String> events = new ArrayList<>();

    while (cnt != 0) {
      String event = scan.nextLine();
      events.add(event);
      cnt--;
    }

    List<Student> students = priorities.getStudents(events);

    if (students.isEmpty()) {
      System.out.println("EMPTY");
    } else {
      for (Student st : students) {
        System.out.println(st.getName());
      }
    }
  }
}