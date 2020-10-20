package team6.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
    List<Authority> findAllByUsername(String username);
}
