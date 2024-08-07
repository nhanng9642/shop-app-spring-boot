package com.example.demo.token;

import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

    @Query(value = """
                select t from Token t inner join User u
                on t.user.id = u.id
                where u.id = :id and ( t.revoked = false)
    """)
    List<Token> findAllValidTokenByUser(Integer id);
}
