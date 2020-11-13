package geekspring.market.controllers;

import geekspring.market.entites.Order;
import geekspring.market.entites.Role;
import geekspring.market.entites.SystemUser;
import geekspring.market.entites.User;
import geekspring.market.services.OrderService;
import geekspring.market.services.RoleService;
import geekspring.market.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;
    private RoleService roleService;
    private OrderService orderService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String showUserPage(HttpServletRequest request, HttpServletResponse response) {
        return "/admin-edit-user";
    }

    @GetMapping("/profile")
    public String showUserPageProfile(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        List<Order> orders = orderService.findByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "/user-page";
    }

    @GetMapping("/roles/{id}")
    public String shoeRolesUser(Model model, @PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        Collection<Role> roles = user.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/admin-userRole-page";
    }

    @GetMapping("/addRole/{id}")
    public String addRole(Model model, @PathVariable(name = "id") Long id) {
        List<Role> roles = roleService.getAllRoles();
        User user = userService.getUserById(id);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return "/admin-edit-userRole";
    }

    @PostMapping("/addRole")
    public String processUserRoleAddForm(@Valid @ModelAttribute("user") User userForm, BindingResult theBindingResult, Model model) {
        if (!userService.isUserWithUserNameExists(userForm.getUserName())) {
            theBindingResult.addError(new ObjectError("status.title", "Пользователь с таким UserName не существует")); // todo не отображает сообщение
        }
        Role newRole = userForm.getRoles().stream().findFirst().get();
        User user = userService.getUserById(userForm.getId());
        user.getRoles().add(newRole);
        userService.saveRole(user);
        return "redirect:/users/roles/" + user.getId();
    }

    @GetMapping("/deleteRole/{id}/{userId}")
    public String processUserAddForm(Model model, @PathVariable(name = "id") Long id, @PathVariable(name = "userId") Long userId) {
        Role removeRole = roleService.getRoleById(id);
        User user = userService.getUserById(userId);
        Collection<Role> rolesUser = user.getRoles();
        rolesUser.remove(removeRole);
        user.setRoles(rolesUser);
        userService.updateUser(user);
        return "redirect:/users/roles/" + user.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/admin-edit-user";
    }

    @PostMapping("/edit")
    public String processUserAddForm(@Valid @ModelAttribute("user") User user, BindingResult theBindingResult, Model model) {
        if (!userService.isUserWithUserNameExists(user.getUserName())) {
            theBindingResult.addError(new ObjectError("status.title", "Пользователь с таким UserName не существует")); // todo не отображает сообщение
        }
        Collection<Role> roleUser = (userService.getUserById(user.getId())).getRoles();
        user.setRoles(roleUser);
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String processUserDelete(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.getUserById(id);
        if (user == null) {
            return "redirect:/admin/users";
        }
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
