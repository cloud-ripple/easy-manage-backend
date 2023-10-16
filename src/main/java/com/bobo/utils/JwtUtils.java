package com.bobo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "bobo"; //指定签名密钥
    private static Long expire = 43200000L; //指定失效到期时间为 12小时  expire(失效，到期)

    /**
     * 生成JWT令牌  generate(生成)
     * @param claims JWT第二部分负载 payload 中存储的内容, 把自定义的内容封装到map集合
     * @return JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims) //在令牌中封装参数信息
                .signWith(SignatureAlgorithm.HS256, signKey) //指定签名算法，密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire)) //设置令牌有效期
                .compact(); //生成
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return 返回JWT第二部分负载 payload 中存储的内容,即自定义的参数信息
     */
    // Claims extends Map<String, Object> 这里的 Claims其实就是一个 Map集合
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
