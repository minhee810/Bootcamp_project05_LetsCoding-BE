package com.group.letscoding.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByPassword(String password);

    @Query(value = "SELECT * from user u WHERE u.id IN " +
            "(SELECT user_id FROM group_member gm where gm.group_id = :groupId)", nativeQuery = true)
    List<User> findUsersInGroup(int groupId);
}
