package service;

import model.Category;
import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User findById(Long id);

    void save(User user);

    void remove(Long id);

    Iterable<User> findAllByCategory(Category category);

    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}

