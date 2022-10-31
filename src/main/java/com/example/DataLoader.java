package com.example;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

  private static final Logger log = LoggerFactory.getLogger(DataLoader.class);

  @Inject UserRepo userRepo;

  @Inject CycleRepository cycleRepository;

  @Override
  @Transactional
  public void onApplicationEvent(ServerStartupEvent event) {
    log.info("Loading data at startup");

    List<User> users =
        List.of(
            new User(1, "a"),
            new User(2, "a"),
            new User(3, "b"),
            new User(4, "b"),
            new User(5, "c"),
            new User(6, "c"),
            new User(7, "c"),
            new User(8, "c"),
            new User(9, "b"),
            new User(10, "b"));

    userRepo.saveAll(users);

    users.stream()
        .map(user -> new Cycle(user.getId(), CycleType.A, LocalDate.now()))
        .forEach(cycleRepository::save);

    userRepo.findAll().forEach(user -> log.info("Saved user is {}", user));
  }
}
