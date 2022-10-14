package com.example.projectlibrary.Repozitory;

import com.example.projectlibrary.Entayti.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    boolean existsByEmail(String email);
}
