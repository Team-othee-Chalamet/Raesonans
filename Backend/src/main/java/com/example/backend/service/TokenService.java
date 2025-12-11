package com.example.backend.service;



import com.example.backend.dto.TokenDTO;
import com.example.backend.dto.TokenMapper;
import com.example.backend.dto.TokenPairDTO;
import com.example.backend.model.Token;
import com.example.backend.repo.TokenRepo;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenService {

    private final TokenRepo tokenRepo;

    public TokenService(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
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

    public byte[] stringToBytes(String token) {
        return Base64.getDecoder().decode(token);
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
        // Generate bytes
        byte[] toHandle = generateTokenBytes();

        // Turn to string
        String clientToken = bytesToString(toHandle);

        // Turn to hashed string
        String hashedToken = hashToken(toHandle);

        // Return both
        return new TokenPairDTO(clientToken, hashedToken);
    }

    public TokenDTO saveToken(TokenDTO tokenDTO) {

        Token token = TokenMapper.toEntity(tokenDTO);

        return TokenMapper.toDto(tokenRepo.save(token));
    }

    public Token getTokenById(Long id) {
        return tokenRepo.getById(id);
    }
}
