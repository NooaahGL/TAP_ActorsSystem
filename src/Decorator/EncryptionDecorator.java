package Decorator;

import java.security.*;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

import Dades.Actor;
import Messages.EncryptMessage;
import Messages.Message;

public class EncryptionDecorator implements Actor{
	private Actor actor;
	private static String algorithm = "AES/CBC/PKCS5Padding";

	public EncryptionDecorator(Actor actor) {
		this.actor = actor;
	}
	
	@Override
	public void send(Message m) throws InterruptedException {
		EncryptMessage message;
		SecretKey key;
		try {
			key = generateKey(128);
			IvParameterSpec valor = generateIv();
			
			message = new EncryptMessage(m.getFrom(), null ,key, valor);
			message.setMessage(encrypt(algorithm, m.getMessage(), key, valor));
			System.out.println("Mensaje encriptado: " + message.getMessage());
			m.getFrom().send(message);
			
		} catch (InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException e)  {
			e.printStackTrace();
		}
	}

	
	public void processMessage(Message m) throws InterruptedException {
		
		if (m instanceof EncryptMessage) {
			SecretKey key = ((EncryptMessage) m).getKey();
		
			try {
				String text = decrypt(algorithm, m.getMessage(), key, ((EncryptMessage) m).getValor());
				Message desencrypt = new Message(m.getFrom(), text);
				System.out.println("Decrypted message: " + text);
				actor.processMessage(desencrypt);
				
			} catch (InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException e)  {
				e.printStackTrace();
			}
		}
	}
	
	public static IvParameterSpec generateIv() {
	    byte[] iv = new byte[16];
	    new SecureRandom().nextBytes(iv);
	    return new IvParameterSpec(iv);
	}
	
	public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
	    keyGenerator.init(n);
	    SecretKey key = keyGenerator.generateKey();
	    return key;
	}
	
	public static String encrypt(String algorithm, String input, SecretKey key, IvParameterSpec iv) throws 
		NoSuchPaddingException, NoSuchAlgorithmException,InvalidAlgorithmParameterException, 
		InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	    
	    Cipher cipher = Cipher.getInstance(algorithm);
	    cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	    
	    byte[] cipherText = cipher.doFinal(input.getBytes());
	    
	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	public static String decrypt(String algorithm, String cipherText, SecretKey key, IvParameterSpec iv) throws 
		NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, 
		InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	    
	    Cipher cipher = Cipher.getInstance(algorithm);
	    cipher.init(Cipher.DECRYPT_MODE, key, iv);
	    
	    byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
	    
	    return new String(plainText);
	}

	
	
}
