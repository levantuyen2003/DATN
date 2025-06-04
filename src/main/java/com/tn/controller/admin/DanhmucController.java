package com.tn.controller.admin;

import com.tn.entity.Danhmuc;
import com.tn.repository.DanhmucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DanhmucController {
    @Autowired
    public DanhmucRepository danhmucRepo;

    @RequestMapping("components/danhmuc")
    public String getAll(Model model) {
        List<Danhmuc> danhmucs = (List<Danhmuc>) danhmucRepo.findAll();
        model.addAttribute("danhmucs", danhmucs);
        return "admin/components/danhmuc";
    }

    @RequestMapping("admin/danhmuc_add")
    public String add(Model model) {
        return "admin/add/danhmuc_add";
    }

    @PostMapping("admin/danhmuc_save")
    public String save(@ModelAttribute Danhmuc danhmuc){
        Danhmuc danhmuc1 = new Danhmuc();
        danhmuc1.setTendanhmuc(danhmuc.getTendanhmuc());

        danhmucRepo.save(danhmuc1);
        return "redirect:/components/danhmuc";
    }

    @RequestMapping("danhmuc_delete/{id}")
    public String delete(@PathVariable int id) {
        danhmucRepo.deleteById(id);
        return "redirect:/components/danhmuc";
    }

}
