package com.bobo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

//@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }

    /**
     * 测试 JWT令牌的生成
     */
    @Test
    public void testJWT() {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
//        claims.put("nickname", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "bobo") //签名算法
                .setClaims(claims) //自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置令牌有效期为 1小时
                .compact(); //拿到String返回值，即生成的JWT令牌

        System.out.println("jwt令牌 = " + jwt);
    }

    /**
     * 解析JWT令牌
     * 注意：
     * JWT校验时使用的签名密钥，必须和生成的JWT令牌时使用的密钥是配套的
     * 如果JWT令牌解析校验时报错，则说明 JWT令牌被篡改了或失效了，令牌非法。
     */
    @Test
    public void testParseJWT() {
        Claims claims = Jwts.parser()
                .setSigningKey("bobo") //指定签名密钥 ，解析令牌
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTY4NTc3Nzg5OX0.cEoFb7GYaDDXqKMErM_N7XZUQ5F2Fj0H_Fc5oD7JdkY")
                .getBody();   //注意设置JWT令牌的有效期，否则会解析出错

        System.out.println(claims); // {name=tom, id=1, exp=1685777899}
    }

}
