package Messages;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import Dades.Actor;

public class EncryptMessage extends Message{

	private SecretKey key;
	private IvParameterSpec valor;
	
	public EncryptMessage(Actor from, String mensaje, SecretKey key,IvParameterSpec v ) {
		super(from, mensaje);
		this.key = key;
		this.valor= v;
	}
	
	public IvParameterSpec getValor() {
		return valor;
	}

	public void setValor(IvParameterSpec valor) {
		this.valor = valor;
	}

	public SecretKey getKey() {
		return key;
	}

	public void setKey(SecretKey key) {
		this.key = key;
	}
	
	
}
