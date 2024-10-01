package guru.sfg.brewery.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;


class PasswordEncodingTests {
    static final String PASSWORD = "password";

    @Test
    void hashingExample() {
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));
        System.out.println(DigestUtils.md5DigestAsHex(PASSWORD.getBytes()));

        String salted = PASSWORD + "ThisIsMySaltValue";
        System.out.println(DigestUtils.md5DigestAsHex(salted.getBytes()));
    }

    @Test
    void testNoOp() {
        PasswordEncoder noOp = NoOpPasswordEncoder.getInstance();
        System.out.println(noOp.encode(PASSWORD));
    }

    @Test
    void testLdap() {
        PasswordEncoder ldap = new LdapShaPasswordEncoder();
        
        System.out.println(ldap.encode(PASSWORD));
        System.out.println(ldap.encode("tiger"));
        String encodedPassword = ldap.encode(PASSWORD);
        System.out.println(encodedPassword);
        
        assertTrue(ldap.matches(PASSWORD, encodedPassword));
    }
    
    @Test
    void testSha256() {
        PasswordEncoder sha256 = new StandardPasswordEncoder();
        
        System.out.println(sha256.encode(PASSWORD));
        System.out.println(sha256.encode(PASSWORD));
    }
    
    @Test
    void testBcrypt() {
        PasswordEncoder bCrypt = new BCryptPasswordEncoder();
        PasswordEncoder bCrypt15 = new BCryptPasswordEncoder(15);
        
        System.out.println(bCrypt.encode(PASSWORD));
        System.out.println(bCrypt.encode(PASSWORD));
        System.out.println(bCrypt.encode("guru"));
        System.out.println(bCrypt15.encode("tiger"));
    }
}
