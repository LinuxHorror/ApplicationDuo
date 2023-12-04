package com.example.applicationduo.web.controllers;


import com.example.applicationduo.dto.ProductDto;
import com.example.applicationduo.entity.ProductEntity;
import com.example.applicationduo.service.ProductService;
import com.example.applicationduo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping
    public ModelAndView getTotalPage(@ModelAttribute("newProduct") ProductDto productDto) {
        ModelAndView modelAndView = new ModelAndView("adminPage");
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("products", productService.findAll());
        return modelAndView;
    }

    @PostMapping("/order")
    public ModelAndView getOrdered(@RequestParam(value = "ascending", required = false) String ascending,
                                   @RequestParam(value = "decreasing", required = false) String decreasing,
                                   @ModelAttribute("newProduct") ProductDto dto) {
        getTotalPage(dto);
        ModelAndView modelAndView = new ModelAndView("adminPage");
        if (isNotBlank(ascending)) {
            modelAndView.addObject("products", productService.getAsc());
        } else if (isNotBlank(decreasing)) {
            modelAndView.addObject("products", productService.getDesc());
        }
        return modelAndView;
    }

    @PostMapping("/updateProduct/{idNew}")
    public ModelAndView update(ProductDto dto,
                               @PathVariable(name = "idNew") Integer id) {
        //TODO add binding result

        ModelAndView totalPage = getTotalPage(new ProductDto());
        if (dto.getPrice() > 0) {
            productService.update(id, dto);
            return new ModelAndView("redirect:/admin");
        } else {
            totalPage.addObject("newProduct", new ProductDto());
        }
        return totalPage;
    }

    @PostMapping("/deleteProduct/{idProduct}")
    public String deleteProduct(@PathVariable("idProduct") Integer id) {
        productService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/deleteCustomer/{idCustomer}")
    public String deleteCustomer(@PathVariable("idCustomer") UUID id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    /*@ModelAttribute(name = "newProduct")
    public ProductDto productDto(){
        return new ProductDto();
    }*/
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute(name = "newProduct") ProductDto product,
                             BindingResult result,
                             @RequestParam(value = "file") MultipartFile file) {
        ModelAndView totalPage = getTotalPage(product);
        if (!result.hasFieldErrors()) {
            try {
                product.setImageToShow(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            productService.save(productService.getMapper().toEntity(product));
            return new ModelAndView("redirect:/admin");
        }
        return totalPage;
    }

    @GetMapping("/search")
    public ModelAndView searchByTitle(@RequestParam(name = "search") String title,
                                      @ModelAttribute(name = "newProduct") ProductDto product) {
        getTotalPage(product);
        var modelAndView = new ModelAndView("adminPage");
        if (isNotBlank(title)) {
            List<ProductEntity> byTitle = productService.getByTitle(title);
            modelAndView.addObject("products", productService.getMapper().toListDto(byTitle));
        }
        return modelAndView;
    }
}
