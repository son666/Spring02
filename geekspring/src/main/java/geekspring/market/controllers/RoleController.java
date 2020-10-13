package geekspring.market.controllers;

import geekspring.market.entites.Role;
import geekspring.market.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String showRolePage(HttpServletRequest request, HttpServletResponse response) {
        return "/admin-edit-role";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            role = new Role();
            role.setId(0L);
        }
        model.addAttribute("role", role);
        return "/admin-edit-role";
    }

    @PostMapping("/edit")
    public String processRoleAddForm(@Valid @ModelAttribute("role") Role role, BindingResult theBindingResult, Model model) {
        if (role.getId() == 0 && roleService.isRoleWithNameExists(role.getName())) {
            theBindingResult.addError(new ObjectError("status.title", "Статус заказа с таким названием уже существует")); // todo не отображает сообщение
        }
        roleService.saveRole(role);
        return "redirect:/admin/roles";
    }

    @GetMapping("/delete/{id}")
    public String processRoleDelete(@PathVariable(name = "id") Long id, Model model) {
        Role role = roleService.getRoleById(id);
        if (role == null) {
            return "redirect:/admin/roles";
        }
        roleService.deleteRoleById(id);
        return "redirect:/admin/roles";
    }
}
