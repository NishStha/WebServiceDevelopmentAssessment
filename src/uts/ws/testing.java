package uts.ws;
 
import java.io.*;
import javax.xml.bind.*;
 
public class testing implements Serializable {
 public static void main(String[] args) throws Exception {
  Users users = new Users();
  users.addUser(new User("ryan@ryanheise.com", "Ryan Heise", "blahblah"));
  users.addUser(new User("joe@bloggs.com", "Joe Bloggs", "foobar"));
  // Boilerplate code to convert objects to XML...
  JAXBContext jc = JAXBContext.newInstance(Users.class);
  Marshaller m = jc.createMarshaller();
  m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
  m.marshal(users, System.out);
 }
}