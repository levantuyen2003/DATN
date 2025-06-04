package com.tn.controller.admin;

import com.tn.entity.Thongtindonhang;
import com.tn.repository.ThongtindonhangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ThongtinDHController {
    @Autowired
    public ThongtindonhangRepository thongtindonhangRepo;

    @RequestMapping("components/thongtinDH")
    public String getAll(Model model) {
        List<Thongtindonhang> thongtindonhangs = thongtindonhangRepo.findAll();
        model.addAttribute("thongtindonhangs", thongtindonhangs);
        return "admin/components/thongtinDH";
    }

    @RequestMapping("thongtindh_delete/{id}")
    public String delete(@PathVariable int id) {
        thongtindonhangRepo.deleteById(id);
        return "redirect:/components/thongtinDH";
    }
}
