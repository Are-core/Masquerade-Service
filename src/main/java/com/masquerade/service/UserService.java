package com.masquerade.service;

import com.masquerade.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class UserService {

    private static final long TOKEN_EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET_KEY = "kLZVf1Rcj2Qd3xFo4yG5zHa6bE7vN8mM";

    public String login(User user) {
        if (!ObjectUtils.isEmpty(user.getUsername()) && !ObjectUtils.isEmpty(user.getHashedPassword())) {
            ///// TODO Change to check the login
            if (user.getUsername() != null) {
                return Jwts.builder()
                        .setSubject(user.getUsername())
                        .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                        .compact();
            }
        }
        return null;
    }

    public boolean checkCredentials(String username, String hashedPassword) {
        return true;
    }

    public boolean isUsernameTaken(String username) {
        return false;
    }

    public void saveUser(User newUser) {
    }

    public void update(User user) {
    }
}
