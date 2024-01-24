package com.keretrendszerek.beadando.controller;

import com.keretrendszerek.beadando.dto.UserDto;
import com.keretrendszerek.beadando.entity.Record;
import com.keretrendszerek.beadando.entity.User;
import com.keretrendszerek.beadando.service.ExcelService;
import com.keretrendszerek.beadando.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private ExcelService excelService;

    public UserController(UserService userService, ExcelService excelService) {
        this.userService = userService;
        this.excelService = excelService;
    }

    @GetMapping("/listUsers")
    public String listUsers(Model model) {
        //List<UserDto> users = userService.findAllUsers();
        //model.addAttribute("users", users);
        //return "listUsers";
        //return findPaginated(1, "username", "asc", model);
        return findUserPaginated(1, "username", "asc", model);

    }

    @GetMapping("/page2/{pageNo}")
    public String findUserPaginated(@PathVariable(value = "pageNo") int pageNo,
                                    @RequestParam("sortField") String sortField,
                                    @RequestParam("sortDir") String sortDir,
                                    Model model) {
        int pageSize = 100;

        Page<User> page = userService.findUserPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "listUsers";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") UserDto userDto) {
        userService.saveUser(userDto);
        return "redirect:/listUsers";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user, UserDto userDto) {
        userService.updateUser(user, userDto);
        return "redirect:/listUsers";
    }

    @GetMapping("/showFormForUserUpdate/{id}")
    public String showFormForUserUpdate(@PathVariable(value = "id") long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        this.userService.deleteUserById(id);
        return "redirect:/listUsers";
    }

    @GetMapping("/exportToExcel")
    public void exportToExcel(@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, HttpServletResponse response) throws IOException {
        List<UserDto> users = userService.findAllUsers();

        Workbook workbook = excelService.generateExcel(users);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=userList.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
