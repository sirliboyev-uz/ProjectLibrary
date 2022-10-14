package com.example.projectlibrary.Controller;

import com.example.projectlibrary.Payload.ApiResponse;
import com.example.projectlibrary.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("ismandfamilya") String ismandfamilya,
                                                         @RequestParam("seriya") String seriya,
                                                         @RequestParam("email") String email,
                                                         @RequestParam("number") String number,
                                                         @RequestParam("parol") String parol
                                                         ) throws IOException {
        ApiResponse apiResponse = usersService.saveUser(ismandfamilya,seriya,email,number,parol);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

    @DeleteMapping("/delete/user/{id}")
    public HttpEntity<?> deleteBooks(@PathVariable Integer id){
        ApiResponse apiResponse = usersService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:208).body(apiResponse.getMessage());
    }

//
//    @PutMapping("/qr/books")
//    public @ResponseBody ResponseEntity<?> editnews11(@RequestParam("editId12") Integer editId,
//                                                      @RequestParam("nomiEdit") String nomiEdit,
//                                                      @RequestParam("muallifEdit") String muallifEdit,
//                                                      @RequestParam("janrEdit") String janrEdit,
//                                                      @RequestParam("muqovaEdit") MultipartFile muqovaEdit,
//                                                      @RequestParam("katagoriyaEdit") String katagoriyaEdit) throws IOException {
//        ApiResponse apiResponse = qrcodebookService.editBookQr(editId, nomiEdit, muallifEdit, janrEdit, muqovaEdit, katagoriyaEdit);
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 208).body(apiResponse.getMessage());
//    }
}
