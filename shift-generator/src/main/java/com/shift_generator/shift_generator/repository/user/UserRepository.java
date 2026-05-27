package com.shift_generator.shift_generator.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import com.shift_generator.shift_generator.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Override
    @NonNull
    <S extends User> S save(@NonNull S entity);

    @Override
    @NonNull
    Optional<User> findById(@NonNull Long id);

    Optional <User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findAllByOrderByUsernameAsc();
}
