package repository;

import model.Category;
import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Iterable<User> findAllByCategory(Category category);
    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}
