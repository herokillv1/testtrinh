package service.ipl;

import model.Category;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Iterable<User> findAllByCategory(Category category) {
        return userRepository.findAllByCategory(category);
    }

    @Override
    public Page<User> findAllByUsernameContaining(String username, Pageable pageable) {
        return userRepository.findAllByUsernameContaining(username,pageable);
    }
}
