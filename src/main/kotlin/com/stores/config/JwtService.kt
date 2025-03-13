package com.stores.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function
import javax.crypto.SecretKey


@Service
class JwtService {
    fun getToken(user: UserDetails): String {
        return getToken(HashMap(), user)
    }

    private fun getToken(extraClaims: Map<String, Any?>, user: UserDetails): String {
        return Jwts.builder().claims(extraClaims).claim("nose", 1).subject(user.username)
            .issuedAt(Date(System.currentTimeMillis())).expiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getKey()).compact()
    }

    private fun getKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode("586E3272357538782F413F4428472B4B6250655368566B597033733676397924")
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getUsernameFromToken(token: String): String {
        return getClaim(token) { obj: Claims -> obj.subject }
    }

    fun <T> getClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = getAllClaims(token)
        return claimsResolver.apply(claims)
    }

    fun getAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).payload
    }

    fun getExpiration(token: String): Date {
        return getClaim(token, { obj: Claims -> obj.expiration })
    }

    fun isTokenExpired(token: String): Boolean {
        return getExpiration(token).before(Date())
    }

    fun isTokenValid(token: String?, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token!!)
        return (username == userDetails.username && !isTokenExpired(token))
    }
}
