package com.example.backend.service;



import com.example.backend.dto.TokenPairDTO;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenService {

    public static void main(String[] args) {
        TokenService ts = new TokenService();
        byte[] bytes = ts.generateTokenBytes();
        System.out.println(ts.bytesToString(bytes));
        System.out.println(ts.hashToken(bytes));
    }

    public byte[] generateTokenBytes(){

        // RNG for cryptography
        SecureRandom random = new SecureRandom();

        // An array of 32 bytes
        byte[] tokenBytes = new byte[32];

        // Fills array with random bytes
        random.nextBytes(tokenBytes);

        return tokenBytes;
    }

    public String bytesToString(byte[] bytes) {
        // Turns array of bytes into string
        return Base64.getEncoder().encodeToString(bytes);
    }

    public String hashToken(byte[] tokenBytes) {
        // MessageDigest is an algorithm to hash data
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            // Digest is the hashing function - hashes bytes - bytesToString turns bytes to string
            return bytesToString(digester.digest(tokenBytes));
        } catch (NoSuchAlgorithmException ignored) {
            throw new RuntimeException("SHA-256 not supported");
        }
    }

    public TokenPairDTO generateTokenPair() {
        byte[] toHandle = generateTokenBytes();
        String clientToken = bytesToString(toHandle);
        String hashedToken = hashToken(toHandle);
        return new TokenPairDTO(clientToken, hashedToken);
    }

}
