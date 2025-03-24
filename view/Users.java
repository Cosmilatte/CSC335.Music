package view;

import java.security.SecureRandom;
import java.util.HashMap;
// import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Users {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException
	{
		new Users();
        run();
        Access.SCANNER.close();
	}

    private static HashMap<String, Access> libraries;
    // private static Scanner scanner;

    public Users()
    {
        // scanner = Access.scanner;
        libraries = new HashMap<String, Access>();
    }

    private static String hash(String username, String password, byte[] cryptography, boolean write) throws NoSuchAlgorithmException, IOException {
        try {
            byte[] salt;
            if (write)
            {
                SecureRandom saltGen = new SecureRandom();
                salt = new byte[2];
                saltGen.nextBytes(salt);
            }

            else
            {
                salt = cryptography.clone();
            }

            MessageDigest hashFunct = MessageDigest.getInstance("MD5");
            byte[] salted = new byte[2 + password.length()];
            for (int i = 0; i < 2; i++) {
                salted[i] = salt[i];
            }

            for (int i = 2; i < 2 + password.length(); i++) {
                salted[i] = password.getBytes()[i - 2];
            }

            String saltMsg = "";
            byte[] digest = hashFunct.digest(salted);
            for (byte b : digest) {
                saltMsg += b + " ";
            }
            
            saltMsg = saltMsg.trim();
            if (write)
                writeData(username, saltMsg, salt);

            return saltMsg;
        }
        
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeData(String username, String password, byte[] salt) throws IOException{
        FileWriter fr = new FileWriter(new File("view/accounts.txt"), true);
        fr.write(username + "\n");
        fr.write(password + "\n");
        for (byte b : salt)
        {
            fr.write(b + " ");
        }

        fr.write("\n");
        fr.close();
    }

    private static void run() throws NoSuchAlgorithmException, IOException {
        System.out.println("|--==============================================--|");
		System.out.println("");
		System.out.println("     Hello User! Welcome to Lilian and Lucian's     ");
		System.out.println("                MUSIC LIBRARY MODEL!                ");
		System.out.println("");
		System.out.println("|--==============================================--|");
		System.out.println("");
        System.out.println(" LOG IN: \n");
        System.out.println(" Type 'login' if you have an account.");
        System.out.println(" Type 'close' to end this application.");
        System.out.println(" Don't have an account? Type 'create' to create one.");
        String action = Access.SCANNER.nextLine();
        while (!action.equals("close"))
        {
            if (action.equals("login"))
            {
                System.out.println(" User Name: ");
                String username = Access.SCANNER.nextLine();
                System.out.println(" Password: ");
                String password = Access.SCANNER.nextLine();
                try
                {
                    BufferedReader accountsReader = new BufferedReader(new FileReader("view/accounts.txt"));
                    String line = accountsReader.readLine().trim();                    
                    while (line != null)
                    {
                        if (libraries.get(line) != null)
                        {
                            String dataKey = accountsReader.readLine().trim();
                            String[] saltLine = accountsReader.readLine().trim().split(" ");
                            byte[] salt = new byte[saltLine.length];
                            for (int i = 0; i < saltLine.length; i++)
                            {
                                salt[i] = Byte.parseByte(saltLine[i]);
                            }

                            password = hash(line, password, salt, false);
                            if (username.equals(line) && password.equals(dataKey))
                            {
                                libraries.get(line).run();
                            }

                            else
                            {
                                System.out.println("Warning: wrong username or password");
                            }

                            break;
                        }
                    }

                    accountsReader.close();
                }

                catch (IOException e)
				{
					System.out.println("Error: This is not a valid input file");
					System.exit(1);
				}
            }

            else if (action.equals("create"))
            {
                System.out.println(" Enter your User Name: ");
                String username = Access.SCANNER.nextLine();
                System.out.println("\n Enter your Password: ");
                String password = Access.SCANNER.nextLine();
                hash(username, password, null, true);
                // scanner.close();
                Access library = new Access();
                // library.main(null);
                // scanner = new Scanner(System.in);
                library.run();
                libraries.put(username, library);
            }

            else
            {
                System.out.println("Invalid Command: Please type again");
            }

            System.out.println(" LOG IN: \n");
            System.out.println(" Type 'login' if you have an account.");
            System.out.println(" Type 'close' to end this application.");
            System.out.println(" Don't have an account? Type 'create' to create one.");
            action = Access.SCANNER.nextLine();
        }

        FileWriter fr = new FileWriter(new File("view/accounts.txt"), false);
        fr.close();
    }
}
