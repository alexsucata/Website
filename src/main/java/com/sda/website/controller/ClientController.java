package com.sda.website.controller;

import com.sda.website.entity.ClientEntity;
import com.sda.website.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/web/client/add")
    public ModelAndView addClient() {
        ModelAndView modelAndView = new ModelAndView("client-form");
        modelAndView.addObject("clientObject", new ClientEntity());

        return modelAndView;
    }

    @PostMapping("/web/client/save")
    public ModelAndView saveClient(@Valid @ModelAttribute("clientObject") ClientEntity clientEntity, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("redirect:/web/client/list");
        if (bindingResult.hasErrors()){
            modelAndView.setViewName("client-form");
            modelAndView.addObject("clientObject", clientEntity);
            return modelAndView;
        }
        clientRepository.save(clientEntity);
        return modelAndView;
    }

    @GetMapping("/web/client/list")
    public ModelAndView getClient() {
        ModelAndView modelAndView = new ModelAndView("clients");
        modelAndView.addObject("clientList", clientRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/web/client/edit/{clientId}")
    public ModelAndView editClient (@PathVariable(name = "clientId") Integer clientId) {
        ModelAndView modelAndView = new ModelAndView("client-form");
        modelAndView.addObject("clientObject", clientRepository.findById(clientId).get());
        return modelAndView;
    }

    @GetMapping("/web/client/delete/{clientId}")
    public ModelAndView deleteClient (@PathVariable Integer clientId){
        ModelAndView modelAndView = new ModelAndView("redirect:/web/client/list");
        clientRepository.deleteById(clientId);
        return modelAndView;
    }


}
