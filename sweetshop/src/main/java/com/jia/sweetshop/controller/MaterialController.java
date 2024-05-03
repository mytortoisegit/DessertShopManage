package com.jia.sweetshop.controller;

import com.jia.sweetshop.model.po.Material;
import com.jia.sweetshop.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public String getAllMaterials(Model model) {
        List<Material> materials = materialService.list();
        model.addAttribute("materials", materials);
        model.addAttribute("material", new Material());
        return "material";
    }

    @PostMapping
    public String addMaterial(@ModelAttribute Material material) {
        materialService.save(material);
        return "redirect:/materials";
    }

    @GetMapping("/edit/{id}")
    public String editMaterial(@PathVariable Long id, Model model) {
        Material material = materialService.getById(id);
        model.addAttribute("material", material);
        return "material";
    }

    @GetMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        materialService.removeById(id);
        return "redirect:/materials";
    }
}
