package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.CustomerForm;
import com.codegym.service.CustomerService;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/customers")
@PropertySource("classpath:upload_file.properties")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")
    public String index(Model model) {

        List<Customer> customerList = customerService.findAll();
        model.addAttribute("customers", customerList);
        return "/index";
    }

//    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("customer", new Customer());
//        return "/create";
//    }
    @GetMapping("/create")
    public String create(ModelMap model) {
        model.addAttribute("customer", new Customer());
        return "/create";
    }
    @Value("${file-upload}")
    private String upload;

    @PostMapping("/save")
    public String save(CustomerForm customer1) {
//        lay anh ra
        MultipartFile file = customer1.getImage();
//        lay ten file
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Customer customer = new Customer();
        customer.setImage(fileName);
        customer.setAddress(customer1.getAddress());
        customer.setEmail(customer1.getEmail());
        customer.setName(customer1.getName());
        customer.setId((int) (Math.random() * 10000));
        customerService.save(customer);
        return "redirect:/customers";
    }
//    @PostMapping("/save")
//    public String save(@RequestParam String name,
//                       @RequestParam String address,
//                       @RequestParam String email) {
//        Customer customer = new Customer(name, address, email);
//        customer.setId((int) (Math.random() * 10000));
//        customerService.save(customer);
//        return "redirect:/customers";
//    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customers";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "/view";
    }
}
