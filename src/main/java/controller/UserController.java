package controller;

import model.Category;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryService;
import service.UserService;

import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categorys")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/users")
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, @PageableDefault(value = 3) Pageable pageable){
        Page<User> users;
        if(s.isPresent()){
            users = userService.findAllByUsernameContaining(s.get(), pageable);
        } else {
            users = userService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/create-user")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveCustomer(@ModelAttribute("customer") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/create");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("message", "New user created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-user/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", user);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-user")
    public ModelAndView updateCustomer(@ModelAttribute("user") User user){
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message", "User updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-user/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        User user = userService.findById(id);
        if(user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/delete");
            modelAndView.addObject("user", user);
            return modelAndView;

        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("user") User user){
        userService.remove(user.getId());
        return "redirect:users";
    }

}
