package com.project.repository;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneByEmail(String email);
}