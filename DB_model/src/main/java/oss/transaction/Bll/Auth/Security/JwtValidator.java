package oss.transaction.Bll.Auth.Security;


import oss.transaction.Bll.Auth.Model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import oss.transaction.Dal.Models.Transaction;
import oss.transaction.Bll.Services.TransactionService;

@Component
public class JwtValidator {


    private String secret = "youtube";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setPassword((String) body.get("password"));

        }
        catch (Exception e) {
            System.out.println(e);
        }

        return jwtUser;
    }
}
