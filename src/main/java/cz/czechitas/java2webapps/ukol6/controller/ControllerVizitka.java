package cz.czechitas.java2webapps.ukol6.controller;

import cz.czechitas.java2webapps.ukol6.entity.Vizitka;
import cz.czechitas.java2webapps.ukol6.repository.VizitkaRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ControllerVizitka {

    private final VizitkaRepository vizitkaRepository;


    public ControllerVizitka(VizitkaRepository vizitkaRepository) {
        this.vizitkaRepository = vizitkaRepository;
    }

    @InitBinder
    public void nullStringBinding(WebDataBinder binder) {

        //prázdné textové řetězce nahradit null hodnotou
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("seznam");
        modelAndView.addObject("seznam", vizitkaRepository.findAll());
        return modelAndView;
    }

    @GetMapping("detail{id}")
    public Object detail(@PathVariable int id) {
        ModelAndView detail = new ModelAndView("vizitka");
        Optional<Vizitka> vizitkaOptional = vizitkaRepository.findById(id);
        if (vizitkaOptional.isPresent()) {
            detail.addObject("vizitka", vizitkaOptional.get());
            return detail;
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nova")
    public ModelAndView nova() {
        ModelAndView modelAndView = new ModelAndView("formular");
        modelAndView.addObject("vizitka", new Vizitka());
        modelAndView.addObject("nova", true);
        return modelAndView;
    }

    @PostMapping("/nova")
    public Object nova(@ModelAttribute("vizitka") @Valid Vizitka vizitka, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formular";
        }
        vizitkaRepository.save(vizitka);
        return "redirect:/";
    }
}