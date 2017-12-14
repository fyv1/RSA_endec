package com.company;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {

        KeyPair kp = create(512);

        PrivateKey privKey = kp.getPrivate();
        PublicKey pubKey = kp.getPublic();

        Cipher cipher = Cipher.getInstance("RSA");

        byte[] encText = encryption(privKey, cipher, "Hello world");
        String enc = new String(encText);
        System.out.println(encText);

        byte[] dec = decryption(pubKey, cipher, encText);
        String decText = new String(dec);
        System.out.println(decText);

    }

    public static KeyPair create(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(keySize);
        return kpg.generateKeyPair();
    }

    public static byte[] encryption(PrivateKey pvk, Cipher cr, String text) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cr.init(Cipher.ENCRYPT_MODE, pvk);
        return cr.doFinal(text.getBytes());
    }

    public static byte[] decryption(PublicKey pck, Cipher cr, byte[] encText) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cr.init(Cipher.DECRYPT_MODE, pck);
        return cr.doFinal(encText);
    }

}
