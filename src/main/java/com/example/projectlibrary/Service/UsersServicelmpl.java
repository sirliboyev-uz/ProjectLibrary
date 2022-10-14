package com.example.projectlibrary.Service;

import com.example.projectlibrary.Entayti.Users;
import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Repozitory.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServicelmpl implements UsersService{
    @Autowired
    UsersRepository usersRepository;


    @Override
    public ApiResponse saveUser(String ismandfamilya, String seriya, String email, String number, String parol) {
        boolean b = usersRepository.existsByEmail(email);
        if (b) return new ApiResponse("warning",false);
        Users users = new Users();
        users.setIsmvsfamilya(ismandfamilya);
        users.setSeriya(seriya);
        users.setEmail(email);
        users.setTelefon(number);
        users.setParol(parol);
        usersRepository.save(users);
        return new ApiResponse("success full",true);
    }

    @Override
    public ApiResponse deleteUser(Integer id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        if (!optionalUsers.isPresent()) return new ApiResponse("topilmadi",false);
        usersRepository.deleteById(id);
        return new ApiResponse("success full",true);
    }
}
