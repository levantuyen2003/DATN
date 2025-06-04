package com.tn.controller.admin;

import com.tn.dto.SanphamDTO;
import com.tn.entity.Danhmuc;
import com.tn.entity.DanhmucSP;
import com.tn.entity.Sanpham;
import com.tn.repository.DanhmucRepository;
import com.tn.repository.DanhmucSPRepository;
import com.tn.repository.SanphamRepository;
import com.tn.rsq.SanphamRSQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SanphamController {
    @Autowired
    public SanphamRepository sanphamRepo;

    @Autowired
    public DanhmucSPRepository danhmucSPRepo;

    @RequestMapping("components/sanpham")
    public String sanpham(Model model) {
        List<Sanpham> sanphams = sanphamRepo.findAll();

        List<SanphamDTO> sanphamDTOS = new ArrayList<>();
        sanphams.forEach(obj -> {
            SanphamDTO sanphamDTO = new SanphamDTO();
            sanphamDTO.setId(obj.getId());
            sanphamDTO.setTensanpham(obj.getTensanpham());
            sanphamDTO.setHinhanh("../assets/img/imgSP/" + obj.getHinhanh());
            sanphamDTO.setThuonghieu(obj.getThuonghieu());
            sanphamDTO.setGiamoi(obj.getGiamoi());
            sanphamDTO.setSale(obj.getSale());
            sanphamDTO.setGia(obj.getGia());
            sanphamDTO.setHot(obj.getHot());
            sanphamDTO.setSoluong(obj.getSoluong());
            sanphamDTO.setCreatedAt(obj.getCreatedAt());


            if (obj.getDanhmucSP() != null) {
                sanphamDTO.setTendanhmuc(obj.getDanhmucSP().getTendanhmucSP());
            }

            sanphamDTOS.add(sanphamDTO);
        });
        model.addAttribute("sanphamDTOS", sanphamDTOS);
        return "admin/components/sanpham";
    }

    @RequestMapping("admin/sanpham_add")
    public String add(Model model) {
        List<DanhmucSP> danhmucSP = (List<DanhmucSP>) danhmucSPRepo.findAll();
        model.addAttribute("danhmucSP", danhmucSP);
        return "admin/add/sanpham_add";
    }

    @RequestMapping(value = "admin/sanpham_save", method = RequestMethod.POST)
    public String save(@ModelAttribute SanphamRSQ sanphamRSQ,
                       @RequestParam("hinhanh") MultipartFile file) throws IOException {

        Sanpham sanpham = new Sanpham();
        String nameFile = "";
        if (file != null){
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get("C:\\Users\\OS\\OneDrive\\Documents\\meta\\src\\main\\resources\\static\\assets\\img\\imgSP",
                    file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            nameFile = file.getOriginalFilename();
            Files.write(fileNameAndPath, file.getBytes());
        }

        sanpham.setTensanpham(sanphamRSQ.getTensanpham());
        sanpham.setHinhanh(nameFile);
        sanpham.setGia(sanphamRSQ.getGia());
        sanpham.setThuonghieu(sanphamRSQ.getThuonghieu());
        sanpham.setGiamoi(sanphamRSQ.getGiamoi());
        sanpham.setSale(sanphamRSQ.getSale());
        sanpham.setSoluong(sanphamRSQ.getSoluong());


        int danhmucId = sanphamRSQ.getDanhmucId();
        DanhmucSP danhmucSP = danhmucSPRepo.findById(danhmucId).orElse(null);
        if (danhmucSP != null) {
            sanpham.setDanhmucSP(danhmucSP);
       }

        sanphamRepo.save(sanpham);

        return "redirect:/components/sanpham";
    }

    @RequestMapping("sanpham_delete/{id}")
    public String delete(@PathVariable int id) {
        sanphamRepo.deleteById(id);
        return "redirect:/components/sanpham";
    }

}
