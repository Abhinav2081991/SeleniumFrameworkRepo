What is Bytecode? What is JDK, JVM, JRE?

What is the role of ClassLoader in Java?

Can you save a java file without name?

What are wrapper classes and why do we need? Difference between int and Integer?

What is immutable classes? Name 5 immutable classes in Java? How to make a class immutable?

What are OOPS concepts? Explain them. What all OOPS concepts have you used in your framework?

What is Object class in Java? Explain role and importance of toString, equal and hashcode method?

What is method overloading and overriding? What is upcasting and down casting?

Can we overload private and final methods? Can we override static methods?

Can we override private and final methods?

Should return type be same in method overloading and overriding?

Can a overriding method throw new or broader checked exception?

Explain autoboxing in Java.

What is abstract class and interface? Difference between abstract class and interface? Can we have private methods in interfaces? Can we have constructor in abstract classes? Can we make a class abstract without any abstract methods?

What is constructor and its uses? What is the difference between super and this?

Will this code compile?

public class Vehicle {
String model;
String color;

public Vehicle(String model, String color) {
this.model=model;
this.color=color;

}

public static void main(String[] args) {
Vehicle v = new Vehicle();
}
}
What will be the output of below code?



class Vehicle {

public Vehicle() {
System.out.println("I am the super vehicle");
}
}

class FourWheeler extends Vehicle {
public FourWheeler() {
System.out.println("I am a car or a truck or whatever 4 wheel has");
}
}

class Car extends FourWheeler{
public Car() {
System.out.println("I am a car");
}
}

public class Demo{
public static void main(String[] args) {
Car c = new Car();
}
}


What is the use of instanceof operator in java?



What are the access modifiers in java? Explain visibility of public, private, default and protractor?



What is the difference between Parent P = new Child() and Child C = new Child() where Parent is a super class and Child is a base class?



What is the use of final keyword in a variable, method and class?



Difference between final, finally and finalize?



What is the difference between == and equal()?



What will be the output of below code?



public class StringExample {

public static void main(String[] args) {

String s1 = "true";
String s2 = "true";

//first sop
System.out.println(s1==s2);

String s3= new String("true");

//second sop
System.out.println(s1==s3);

String s4= "True";

//Third sop
System.out.println(s2==s4);

}

}
What will be the output of below code?





public class StringExample {

public static void main(String[] args) {

String str = " hello ";
str.trim();
System.out.println(str);   " hello "
}

}


How many methods of String class have you used? Name them with example

What is the difference between String, Stringbuffer and Stringbuilder?

Where are String values stored in memory? Why String class is immutable?

What is static block and instance block?



What is the default value of instance variable and local variable?



What is the use of static variable? Can we use static variables inside non static methods? Can we use non static variables inside static methods?
How to invoke static methods?



Is it a valid for loop?

Int i=0;
for(;i<10; i++){
System.out.println(i);
}


What is the difference between break and continue?



What is the difference between while and do-while?



What will happen if we don’t have break statement inside matching catch? Should default be the last statement in a switch case block?
 Not mandatory, code will execute but readability decreases.


Can your switch statement accept long, double or float data type?

    Floating-point values are not reliable for exact matching
    Precision issues make them unsafe

What is the hierarchy of throwable class? What are checked and unchecked exceptions?



What is the difference between Error and Exception?



What is the difference between throw and throws?



Is try block without finally and catch allowed?



How to handle multi level exceptions?



Will this code compile?



public class ExceptionExample {

public static void main(String[] args) {

try {
FileReader f = new FileReader(new File("D:\\myfile"));
}catch(IOException e) {

}catch(FileNotFoundException e) {

}
}

}
What are the default values of array?



What is garbage collection?



How to run garbage collection? When will it run?



What are the ways to make objects eligible for garbage collection?



How many objects available for GC?



public class GC {

public static void main(String[] args) {

GC gc1= new GC();
GC gc2 = new GC();
GC gc3 = new GC();

gc1 = null;
}

}


Difference between System.gc() and Runtime.getRuntime().gc()?
System.gc() → delegates to → Runtime.getRuntime().gc()
System.gc() internall calls - Internally calls Runtime.getRuntime().gc()\


Runtime.getRuntime().gc();
Instance method of Runtime class
Gives direct access to the JVM runtime
Also only requests garbage collection


How do you read contents of a file? How do you write in a file?



What is functional interface? Give example of functional interfaces in Java?



What are marker interfaces in Java?



What is multithreading? Difference between process and thread?



What is the difference between thread class and runnable interface?

Runnable avoids Java’s single inheritance limitation.
Thread	- Cannot extend another class, Uses class inheritance
Create and control a thread
Task + Thread coupled


Runnable - Can extend another class, Uses interface implementation
Define task to run in a thread
Task separated from thread
Runnable follows better design principles  - Single Responsibility Principle



How to get today’s date using date class? How to add days in today’s date?



How to get current month and year using calendar class?



What is stream in Java 8?



What is default and static methods in interface in Java 8?



What is the difference between Collections and Collection?



What is the hierarchy of collection?



What are the sub interfaces and sub classes of List, Set and Map interface?

What is the difference between ArrayList and LinkedList? Which one is faster in insertion, deletion and random access memory?
ArrayList is faster for random access and iteration, while LinkedList is better for frequent insertions and deletions in the middle but uses more memory.

What is the difference between singly linkedlist, doubly linkedlist and circular linkedlist?

What main interfaces LinkedList implements?

What is the difference between Stack and Queue?

What is the difference between List and Set?

What is HashMap?

How to convert Array into Arraylist and Arraylist into Array?

How to convert Set into List?

How to iterate HashSet and HashMap?

What is SortedSet and SortedMap?

What is LinkedHashSet and LinkedHashMap?

What is TreeSet and TreeMap?

What is the difference between HashTable and HashMap?

What are difference methods of Collections class have you used?

Can you iterate list forward and backward? Can you iterate linkedlist forward and backward?

What is the between comparable and comparator?

Why can we compare Strings without implementing compare() or compareTo methods?

WAP to check if a given number in palindrome.

WAP to count duplicates in a given string.

WAP to reverse a string using inbuilt reverse method as well as programmatically.

WAP to sort an array.

WAP to find min and max in array.

WAP to find sum of array.

WAP to find missing number in array.

WAP to find largest and second largest in array? Smallest and second smallest in array?

Where have you used List, Set and Map in Selenium?

Example of Selenium methods using method overloading?

Can you name 10 interfaces in Selenium?

Have you ever designed framework? Please explain your framework.

What is WebDriver and WebElement?

What is the super interface of WebDriver? What is the hierarchy of WebDriver?

What are methods of WebDriver and WebElement?

What is the difference between findelement and findElements?

What are waits in selenium? Difference between implicit wait and explicit wait?

What is the importance of / and // in xpath?

What is the importance of *, $ and ^ in Css selector?

What are the challenges you faced in automation? What are the challenges you faced while working with IE browser?

Explain Page factory model.

How to read data from excel? Difference between HSSFWorkbook and XSSFWorkbook?

How to read data from dynamic web table? How to fetch data from last raw of dynamic web table?

How to handle multiple windows in Selenium? How to open a new window? How do you switch from one window to another window?

How do you handle Alert? Can you write a method to check if alert exists?

How do you handle frames in Selenium?

Can you write isElementPresent method?

What are the various example of locator strategies in Selenium?

What is the difference between Xpath and CSS locators?

How to select values from dropdown?

What is the difference between Action and Actions?

How to do double click, right click, drag and drop?

What is robot class? Where do you use in Selenium?

What is the difference between quit and close?

Can you explain about some common exceptions in Selenium?

What is JavaScriptExecutor in Selenium?

How do you do parallel execution? What is threadlocal?

What are the various ways of click and sendkeys in Selenium?
Selenium supports clicking and typing using WebElement methods, Actions class, JavaScriptExecutor,
 keyboard events, and Robot class.
 Best practice is to use native WebDriver methods first and fall back to JS or Actions only when required.