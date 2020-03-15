/**
 * Vigenere that implements the Encryptable interface we discussed in class. In addition to any methods related to 
 * the interface implementation (encrypt() and decrypt()), Vigenere contains a main method that allows a user to encrypt 
 * and decrypt messages using a password.
 *
 * @author Alina Zheng and Leah Teffera
 * @version 3/5/20
 */
public class Vigenere implements Encryptable
{
    // instance variables to hold information about a Vigenere object
    private String message; 
    private String password; 
    private boolean encrypted; 

    /**
     * Constructor: Stores the original message and a password for 
     * encrypting the message
     * @param message the message to encrypt
     * @param password the password to encrypt with 
     */
    public Vigenere(String mes, String pass)
    {
        message = mes;
        password = pass; 
        encrypted = false;  
    }
    
    /**
     * Encrypts this message using Vigenere cipher. Encryption will only work if the message hasn't already 
     * been encrypted (i.e. if encrypted == false). 
     */
    public void encrypt() {
        if (!encrypted) {
            // remove the spaces from the message to encrypt
            message = message.replaceAll("\\s+", "");
            //convert letters in the message and the password to capital letters
            message = message.toUpperCase(); 
            password = password.toUpperCase(); 
            System.out.println("Message to encrypt: " + message); 
            
            String masked = ""; 
            for (int i = 0; i < message.length(); i++) {
                // determines the index of the letter in the password to shift the corresponding letter in the message with
                int pass_index = i % password.length(); 
                // gets the letter in password to shift by, subtract 65 to find alphabetic index of letter (from 0 to 25) to determine shift
                int shift = (password.charAt(pass_index) - 65); 
                // encrypt the character by adding shift to the corresponding letter
                int masked_char = ((int) message.charAt(i) + shift); 
                // if the ASCII value of the encrypted character is greater than 90, wrap it around the alphabet 
                if (masked_char > 90) {
                    masked_char -= 26; 
                }
                masked += (char) masked_char; 
            }
            message = masked; 
            encrypted = true;
            System.out.println("Encrypted message: " + message); 
        }  
    }
    
    
    /**
     * Decrypts and returns this message. This method only works if encrypted is true, which means
     * that the message MUST be encrypted first before it can be decrypted. 
     * @return the decrypted message
     */
    public String decrypt() {
       if (encrypted) {
           String unmasked = ""; 
           for (int i = 0; i < message.length(); i++) {
               // determines the index of the letter in the password to shift the corresponding letter in the message with
               int pass_index = i % password.length(); 
               //  gets the letter in password to shift by, subtract 65 to find alphabetic index of letter (from 0 to 25) to determine shift
               int shift = (password.charAt(pass_index)) - 65; 
               // decrypt the character by subtracting the shift from the corresponding character
               int unmasked_char = message.charAt(i) - shift; 
               // if the ASCCII value of the decrypted character is less than 65, wrap it around the alphabet 
               if (unmasked_char < 65) {
                   unmasked_char += 26; 
               }
               unmasked += (char) unmasked_char; 
           }
           message = unmasked; 
       }
       System.out.println("Decrypted message: " + message); 
       encrypted = false; 
       return message; 
    }
    
    /**
     * Main method allows user to encrypt and decrypt messages using a password
     */
    public static void main(String[] args) {
        System.out.println("Encrypts 'HELLO WORLD' with the password 'NOT'. Should print out 'USEYCPBFEQ'."); 
        Vigenere message1 = new Vigenere("HELLO WORLD", "NOT"); 
        message1.encrypt(); 
        System.out.println("Decrypts 'USEYCPBFEQ' with the password 'NOT'. Should print out 'HELLOWORLD'."); 
        message1.decrypt(); 
        System.out.println("\n"); 
        
        Vigenere message2 = new Vigenere("Attack at Dawn", "CAT"); 
        System.out.println("Encrypts 'Attack at Dawn' with the password 'CAT'. Should print out 'CTMCCDCTWCWG'."); 
        message2.encrypt();
        System.out.println("Decrypts 'CTMCCDCTWCWG' with the password 'CAT'. Should print out 'ATTACKATDAWN'."); 
        message2.decrypt(); 
        System.out.println("\n"); 
        
        Vigenere message3 = new Vigenere("Bob", "superlongkey"); 
        System.out.println("Encrypts 'Bob' with the password 'superlongkey' which is longer than the message itself. Should print out 'TIQ'."); 
        message3.encrypt();
        System.out.println("Decrypts 'TIQ' with the password 'superlongkey'. Should print out 'BOB'."); 
        System.out.println("\n");    
    }
}
