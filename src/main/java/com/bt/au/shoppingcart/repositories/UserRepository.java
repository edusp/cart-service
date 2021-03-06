package com.bt.au.shoppingcart.repositories;


import com.bt.au.shoppingcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByFirstName(String name);

}
