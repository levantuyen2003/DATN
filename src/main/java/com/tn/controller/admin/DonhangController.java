package com.tn.controller.admin;

import com.tn.dto.DonhangDTO;
import com.tn.dto.OrderItemDTO;
import com.tn.dto.SanphamDTO;
import com.tn.entity.Donhang;
import com.tn.repository.DonhangRepository;
import com.tn.repository.ThongtindonhangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DonhangController {
    @Autowired
    public DonhangRepository donhangRepo;

    @RequestMapping("components/donhang")
    public String getAll(Model model){
        List<Donhang> donhangs = donhangRepo.findAll();

        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        donhangs.forEach(obj -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO();

            orderItemDTO.setId(obj.getId());
            orderItemDTO.setTensanpham(obj.getTensanpham());
            orderItemDTO.setGia(obj.getGia());

            orderItemDTO.setDonhangDate(obj.getDonhangDate());


            if (obj.getThongtindonhang() != null) {
                orderItemDTO.setTennguoidat(obj.getThongtindonhang().getTennguoidat());
            }

            orderItemDTOS.add(orderItemDTO);
        });
        model.addAttribute("donhangsDTO", orderItemDTOS);
        return "admin/components/donhang";
    }
}
