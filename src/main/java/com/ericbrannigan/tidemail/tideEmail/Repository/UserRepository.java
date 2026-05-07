package com.ericbrannigan.tidemail.tideEmail.Repository;

import com.ericbrannigan.tidemail.tideEmail.Classes.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {}
