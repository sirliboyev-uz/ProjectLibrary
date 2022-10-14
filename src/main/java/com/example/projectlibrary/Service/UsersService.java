package com.example.projectlibrary.Service;

import com.example.projectlibrary.Payload.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {
    ApiResponse saveUser(String ismandfamilya, String seriya, String email, String number, String parol);

    ApiResponse deleteUser(Integer id);
}
