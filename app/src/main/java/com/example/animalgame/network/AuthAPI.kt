package com.example.animalgame.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

//Data classes for API requests and responses
data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val id: Long, val username: String, val email: String, val token: String)

data class RegisterRequest(val username: String, val email: String, val password: String, val confirmPassword: String)
//We just use the LoginResponse for the RegisterResponse

//API endpoints, we can update these
interface AuthApi {
    @POST("api/auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("api/auth/register")
    fun register(@Body request: RegisterRequest): Call<LoginResponse>
}
