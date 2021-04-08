package com.sda.website.controller;


import com.sda.website.repository.CategoryRepository;
import com.sda.website.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/web/index")
    public ModelAndView getIndex(@RequestParam(name = "floor", defaultValue = "0") String floorParam) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("myName", "Alex");
        modelAndView.addObject("myCity", "Rosu");
        //modelAndView.addObject("product", productRepository.findById(1).get());
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.addObject("isLoggedIn", true);
        modelAndView.addObject("floor", Integer.valueOf(floorParam));
        modelAndView.addObject("categories", categoryRepository.findAll());

        return modelAndView;
    }


}
