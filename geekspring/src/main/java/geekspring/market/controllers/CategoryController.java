package geekspring.market.controllers;

import geekspring.market.entites.Category;
import geekspring.market.services.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String showCategoryPage(HttpServletRequest request, HttpServletResponse response) {
        return "/admin-edit-category";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            category = new Category();
            category.setId(0L);
        }
        model.addAttribute("category", category);
        return "/admin-edit-category";
    }

    @PostMapping("/edit")
    public String processCategoryAddForm(@Valid @ModelAttribute("category") Category category, BindingResult theBindingResult, Model model) {
        if (category.getId() == 0 && categoryService.isCategoryWithTitleExists(category.getTitle())) {
            theBindingResult.addError(new ObjectError("status.title", "Статус заказа с таким названием уже существует")); // todo не отображает сообщение
        }
        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String processCategoryDelete(@PathVariable(name = "id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            return "redirect:/admin/categories";
        }
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }
}
