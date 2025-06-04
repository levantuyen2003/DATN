package com.tn.controller.page;

import com.tn.entity.Sanpham;
import com.tn.repository.SanphamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class SanphamDetailController {
    @Autowired
    private SanphamRepository sanphamRepository;

    @RequestMapping("/Sp-Detail/{id}")
    public String getAll(@PathVariable int id, Model model){
        Optional<Sanpham> sanphamOpt = sanphamRepository.findById(id);
        if (sanphamOpt.isPresent()) {
            Sanpham sanpham = sanphamOpt.orElse(null);

            sanpham.setHinhanh("../assets/img/imgSP/" + sanpham.getHinhanh());


            model.addAttribute("sanphamdetail", sanpham);

            return "page/productDetail";
        }
        return null;
    }
}
