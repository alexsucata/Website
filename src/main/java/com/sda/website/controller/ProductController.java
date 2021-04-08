package com.sda.website.controller;


import com.sda.website.entity.ProductEntity;
import com.sda.website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/web/product/add")
    public ModelAndView addProduct(){
        ModelAndView modelAndView = new ModelAndView("product-form");
        modelAndView.addObject("productObject", new ProductEntity());

        return modelAndView;
    }

    @PostMapping("/web/product/save")
    public ModelAndView saveProduct (@Valid @ModelAttribute("productObject") ProductEntity productEntity, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("redirect:/web/product/list");
        if (bindingResult.hasErrors()){
           modelAndView.setViewName("product-form");
           modelAndView.addObject("productObject", productEntity);
           return modelAndView;
        }
        productRepository.save(productEntity);
        return modelAndView;
    }

    @GetMapping("/web/product/list")
    public ModelAndView getProduct(){
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("productList", productRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/web/product/edit/{productId}")
    public ModelAndView editProduct (@PathVariable(name = "productId") Integer productId){
        ModelAndView modelAndView = new ModelAndView("product-form");
        modelAndView.addObject("productObject", productRepository.findById(productId).get());
        return modelAndView;
    }

    @GetMapping("/web/product/delete/{productId}")
    public ModelAndView deleteProduct (@PathVariable Integer productId){
        ModelAndView modelAndView = new ModelAndView("redirect:/web/product/list");
        productRepository.deleteById(productId);
        return modelAndView;
    }

}
