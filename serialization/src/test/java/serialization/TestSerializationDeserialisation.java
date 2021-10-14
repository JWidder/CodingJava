package serialization;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class TestSerializationDeserialisation {

	@Test
	public void whenSerializingAndDeserializing_ThenObjectIsTheSame()
		throws IOException, ClassNotFoundException { 
		Person person = new Person();
		person.setAlter(20);
		person.setName("Joe");
			    
		FileOutputStream fileOutputStream = new FileOutputStream("yourfile.txt");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(person);
		objectOutputStream.flush();
		objectOutputStream.close();
			    
		FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Person p2 = (Person) objectInputStream.readObject();
		objectInputStream.close(); 
			 
		assertTrue(p2.getAlter() == person.getAlter());
		assertTrue(p2.getName().equals(person.getName()));
	}
}
