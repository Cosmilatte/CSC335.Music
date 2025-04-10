package view;

// Users.java
// Created 3 - 20 - 2025
// Authors: Lilian and Lucian
// Purpose: This program is the GUI serving mutiple users to get access to 
// their own music library account.

import java.security.SecureRandom;
import java.util.HashMap;
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

    public Users()
    {
        libraries = new HashMap<String, Access>();
    }

    private static String hash(String username, String password, byte[] cryptography, 
    		boolean write) throws NoSuchAlgorithmException, IOException {
        try
        {
            byte[] salt;
            if (write)
            {
                SecureRandom saltGen = new SecureRandom();
                salt = new byte[2];
                saltGen.nextBytes(salt);
            }

            else
                salt = cryptography.clone();

            MessageDigest hashFunct = MessageDigest.getInstance("MD5");
            byte[] salted = new byte[2 + password.length()];
            for (int i = 0; i < 2; i++)
                salted[i] = salt[i];

            for (int i = 2; i < 2 + password.length(); i++)
                salted[i] = password.getBytes()[i - 2];

            String saltMsg = "";
            byte[] digest = hashFunct.digest(salted);
            for (byte b : digest)
                saltMsg += b + " ";
            
            saltMsg = saltMsg.trim();
            if (write)
                writeData(username, saltMsg, salt);

            return saltMsg;
        }
        
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void writeData(String username, String password, byte[] salt) throws IOException{
    	String absolutePath = "C:\\Users\\akjon\\300sWorkspace\\CSC335la1\\src\\view\\accounts.txt";
    	FileWriter fr = new FileWriter(new File(absolutePath), true);
        fr.write(username + "\n");
        fr.write(password + "\n");
        for (byte b : salt)
            fr.write(b + " ");

        fr.write("\n");
        fr.close();
    }

    private static void run() throws NoSuchAlgorithmException, IOException {
    	String absolutePath = "C:\\Users\\akjon\\300sWorkspace\\CSC335la1\\src\\view\\accounts.txt";
    	
    	System.out.println("|--==============================================--|");
    	System.out.println("");
		System.out.println("     Hello User! Welcome to Lilian and Lucian's     ");
		System.out.println("                MUSIC LIBRARY MODEL!                ");
		System.out.println("               (this is the startup!)               ");
		System.out.println("");
		System.out.println("|--==============================================--|");
        System.out.println("                     --------                        ");
        System.out.println("                    | LOG IN |                       ");
        System.out.println("                     --------                        ");
        System.out.println(" Type 'login' if you have an account.");
        System.out.println(" Type 'exit' to end this application.");
        System.out.println(" Don't have an account? Type 'create' to create one.\n");
        System.out.print(">: ");
        String action = Access.SCANNER.nextLine();
        while (!action.equals("exit"))
        {
            if (action.equals("login"))
            {
                System.out.print(" User Name: ");
                String username = Access.SCANNER.nextLine();
                System.out.print(" Password: ");
                String password = Access.SCANNER.nextLine();
                try
                {
                    BufferedReader accountsReader = new BufferedReader(new FileReader(absolutePath));
                    String line = accountsReader.readLine().trim();                    
                    while (line != null)
                    {
                        if (libraries.get(line) != null)
                        {
                            String dataKey = accountsReader.readLine().trim();
                            String[] saltLine = accountsReader.readLine().trim().split(" ");
                            byte[] salt = new byte[saltLine.length];
                            for (int i = 0; i < saltLine.length; i++)
                                salt[i] = Byte.parseByte(saltLine[i]);

                            password = hash(line, password, salt, false);
                            if (username.equals(line) && password.equals(dataKey))
                                libraries.get(line).run();

                            else
                                System.out.println(" Warning: wrong username or password");

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
                System.out.println(" ** Warning: Don't type in any space **");
                System.out.print("    Enter your User Name: ");
                String username = Access.SCANNER.nextLine();
                System.out.print("    Enter your Password: ");
                String password = Access.SCANNER.nextLine();
                System.out.println();
                hash(username, password, null, true);
                Access library = new Access();
                library.run();
                libraries.put(username, library);
            }

            else
                System.out.println(" Invalid Command: Please type again");

            System.out.println("\n --------");
            System.out.println("| LOG IN |");
            System.out.println(" --------");
            System.out.println(" Type 'login' if you have an account.");
            System.out.println(" Type 'close' to end this application.");
            System.out.println(" Don't have an account? Type 'create' to create one.");
            System.out.print(">: ");
            action = Access.SCANNER.nextLine();
        }

        FileWriter fr = new FileWriter(new File(absolutePath), false);
        fr.close();
    }
}