package com.tn.controller.page;

import com.tn.entity.Sanpham;
import com.tn.service.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SanphamSearchController {
    @Autowired
    private SanphamService sanphamService;

    @RequestMapping("/spSearch")
    public String searchSanphams(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                 Model model) {
        List<Sanpham> sanphams = sanphamService.searchByTensanpham(name);
        model.addAttribute("sanphams", sanphams);
        model.addAttribute("name", name);
        return "page/productSearch";
    }
}
