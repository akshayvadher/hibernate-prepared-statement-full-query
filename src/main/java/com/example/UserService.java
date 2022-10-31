package com.example;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import javax.transaction.Transactional;

@Singleton
public class UserService {

  @Inject UserRepo userRepo;

  @Transactional
  List<User> get() {
    return userRepo.get();
  }
}
