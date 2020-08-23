package LogIn;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 1. Create a Pojo class say User which 
// implements Serializable Interface
// Implementing this interface is must as 
// this interface tells JVM that its object can
// be serialized. This interface has no methods and 
// it acts as Marker Interface.

class User implements Serializable {

 // 2. Have a few properties to it ... 
 private String firstName;
 private String lastName;
 private String email;
 private String password;


 // 3. Have a constructor which can set properties of it 
 // We can even use setter methods for the same
 public User(String firstName, String lastName, String email, String password) {
  super();
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.password = password;
 }

 // 4. Have getters and setters for each property
 public String getFirstName() {
  return firstName;
 }

 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 public String getLastName() {
  return lastName;
 }

 public void setLastName(String lastName) {
  this.lastName = lastName;
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

}

// 5. Create a Test class which can store object of
// User into a text file and read it back again 
// thus proves that object state was saved into the 
// text file and after its destruction, its state was 
// gained by reading the object stored state in the 
// user.txt

public class LogIN {

 public static void main(String[] args) {

  try {

   // 6. Create a FileOutputStream Object by passing text file
   // name which will be used to store the object state

   FileOutputStream fos = new FileOutputStream("database.txt");

   // 7. Create a ObjectOutputStream object which wraps 
   // object of FileOutputStream thus helping to pass object 
   // to a text file.
   ObjectOutputStream oos = new ObjectOutputStream(fos);

   // 8. Create a User object by passing the dummy values to 
   // its constructor
   User user = new User("Rishav", "Parashar", "Rishav.parashar@mailme.com", "Pass");

   // 9. Calling the writeObject method present in the ObjectOutputStream
   // which will save the object state into the text file created above
   oos.writeObject(user);


   // 10. Flushing and closing the ObjectOutputStream
   // as they are very critical resource
   oos.flush();
   oos.close();

   // 11. Assigning the user object to null so that its actual
   // object goes into unreachable state in heap ... similar to 
   // destruction of object in this case
   user = null;

   // 12. Create a FileInputStream Object by passing text file
   // name which will be used to read the state of the object 

   FileInputStream fis = new FileInputStream("user.txt");

   // 13. Create a ObjectInputStream object which wraps 
   // object of FileInputStream thus helping to pass object 
   // state from text file to Object

   ObjectInputStream ois = new ObjectInputStream(fis);

   // 14. In order to read the User object we will use the 
   // ObjectInputStream.readObject() method. After this method gets 
   // executed it reads object state from text file and return a object
   // of type Object so we need to cast it back the its origin class, 
   // the User class.
   user = (User) ois.readObject();

   // 15. After getting back the required object back, in order to prove
   // the result we outputs its state to console.
   System.out.println("First Name : " + user.getFirstName());
   System.out.println("Last Name : " + user.getLastName());
   System.out.println("Email : " + user.getEmail());
   System.out.println("Phone : " + user.getPassword());

   // closing the resources
   ois.close();
  } catch (IOException e) {
   e.printStackTrace();
  } catch (ClassNotFoundException e) {
   e.printStackTrace();
  }
 }

}