package com.project.springbootpostgrestodo.Controller;

import com.project.springbootpostgrestodo.Dao.UserRepository;
import com.project.springbootpostgrestodo.entities.User;
import com.project.springbootpostgrestodo.helper.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    /*Autorwiring Jpa Repository*/
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //Home Handler
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home-ToDo");
        return "home";
    }

    //About Handler
    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About-ToDo");
        return "about";
    }

    //Signup Handler
    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Signup");
        model.addAttribute("user", new User());
        return "signup";
    }

    //User Register Handler
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session) {
        //@ModelAttribute will check user properties
        //@RequestParam will check agreement of checkbox is selected or !

        try {
            /*will check agreement checkbox in signup page*/
            if (!agreement) {
                System.out.println("Not agreed");
                throw new Exception("Please agree terms and condition");      // Interact Interface
            } else {
                System.out.println("Agreed");
            }

            /*Default user role as User*/
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            /*Injecting bcriptPasswordEncoder*/
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            /*Default ImageURL*/
            user.setImageUrl("default.png");

            /*for showing user's puted values in terminal*/
            System.out.println("User" + user);

            /*save user input through jpa repository*/
            User result = this.userRepository.save(user);

            /*message class for showing message*/
            session.setAttribute("message", new message("Successful", "alert_success"));

            model.addAttribute("user", new User());

            return "signup";
        } catch (Exception e) {
            e.printStackTrace();        //shows error message in run
            model.addAttribute("user", user);
            session.setAttribute("message", new message("Went wrong!! " + e.getMessage(), "alert_error"));
        }
        return "signup";
    }

    UserController controller = new UserController();

    @RequestMapping("/user_dashboard")
    public UserController getController(Model model) {
        return controller;
    }

    //User Login Handler
    @GetMapping("/Login")
    public String login(Model model) {
        model.addAttribute("title", "login");
        return "login";
    }

    @RequestMapping("/login")
    public String startButton(Model model) {
        model.addAttribute("title", "about");
        return "login";
    }
}
