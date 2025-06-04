package com.tn.controller.page;

import com.tn.dto.DonhangDTO;
import com.tn.dto.OrderItemDTO;
import com.tn.entity.Donhang;
import com.tn.entity.Sanpham;
import com.tn.entity.Thongtindonhang;
import com.tn.repository.SanphamRepository;
import com.tn.repository.ThongtindonhangRepository;
import com.tn.service.SanphamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DiaChiController {
    @Autowired
    public ThongtindonhangRepository thongtindonhangRepo;

    @Autowired
    private SanphamRepository sanphamRepo;

    @RequestMapping("/address/{id}")
    public String showOrderInfo(@PathVariable int id, Model model) {
        Optional<Sanpham> sanphamOpt = sanphamRepo.findById(id);
        if (sanphamOpt.isPresent()) {
            Sanpham sanpham = sanphamOpt.get();

            sanpham.setHinhanh("../assets/img/imgSP/" + sanpham.getHinhanh());

            model.addAttribute("productOrder", sanpham);
            return "page/address"; // đây là view thông tin đơn hàng
        }
        return "redirect:/404";
    }

//    @RequestMapping("/thongtindathang")
//    public String submitOrder(@ModelAttribute Thongtindonhang order, RedirectAttributes redirectAttributes) {
//        thongtindonhangRepo.save(order);
//        redirectAttributes.addFlashAttribute("success", "Đơn hàng đã được gửi thành công!");
//        return "redirect:/thank";
//    }

    @RequestMapping("/thongtindathang")
    public String submitOrder(@ModelAttribute DonhangDTO donhangDTO){
        Thongtindonhang thongtindonhang = new Thongtindonhang() ;
        thongtindonhang.setTennguoidat(donhangDTO.getTennguoidat());
        thongtindonhang.setSodienthoai(donhangDTO.getSodienthoai());
        thongtindonhang.setTinhtp(donhangDTO.getTinhtp());
        thongtindonhang.setQuanhuyen(donhangDTO.getQuanhuyen());
        thongtindonhang.setXathon(donhangDTO.getXathon());
        thongtindonhang.setDiachi(donhangDTO.getDiachi());
        thongtindonhang.setThanhtoan(donhangDTO.getThanhtoan());
        thongtindonhang.setTongTien(donhangDTO.getTongTien());
        thongtindonhang.setDonhangDate(donhangDTO.getDonhangDate());


        List<Donhang> donhangs = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : donhangDTO.getItems()){
            Donhang donhang = new Donhang();
            donhang.setTensanpham(orderItemDTO.getTensanpham());
            donhang.setGia(orderItemDTO.getGia());
            donhang.setDonhangDate(orderItemDTO.getDonhangDate());


            donhang.setThongtindonhang(thongtindonhang);

            donhangs.add(donhang);
        }

        thongtindonhang.setDonhangs(donhangs);
        thongtindonhangRepo.save(thongtindonhang);
        return "redirect:/thank";
    }
}
