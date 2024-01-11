package skateSpots;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		FileOutputStream fileOut = new FileOutputStream("Users.ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		FileInputStream fileIn = new FileInputStream("Users.ser");
		
		boolean sentinel = true;
		while (sentinel) {
			signUp(keyboard, fileOut, out);
			System.out.print("\nWould You like to sign up again?: ");
			String ans = keyboard.next();
			if (!ans.equals("yes")) {
				sentinel = false;
			}
		}
		ArrayList<Users> users = new ArrayList<Users>();
		boolean cont = true;
		while (cont) {
			try (ObjectInputStream input = new ObjectInputStream(fileIn)) {
				Users obj = (Users) input.readObject();
				if (obj != null) {
					System.out.println(obj);
					users.add(obj);
				} else {
					cont = false;
				}
			} catch (Exception e) {
				// System.out.println(e.printStackTrace());
			}
		}

		out.close();
		fileOut.close();
		keyboard.close();
	}
	
	public static void signUp(Scanner keyboard, FileOutputStream fileOut, ObjectOutputStream out) throws IOException {
		System.out.print("Enter Your Username: ");
		String username = keyboard.next();
		System.out.print("\nEnter Your Password: ");
		String password = keyboard.next();
		
		Users user = new Users(username, password);
		out.writeObject(user);
	}

}
