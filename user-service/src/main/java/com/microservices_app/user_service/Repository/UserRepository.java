package com.microservices_app.user_service.Repository;

import com.microservices_app.user_service.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
}
