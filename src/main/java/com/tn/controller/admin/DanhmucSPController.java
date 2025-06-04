package com.tn.controller.admin;

import com.tn.dto.DanhmucSPDTO;
import com.tn.entity.Danhmuc;
import com.tn.entity.DanhmucSP;
import com.tn.entity.Sanpham;
import com.tn.repository.DanhmucRepository;
import com.tn.repository.DanhmucSPRepository;
import com.tn.rsq.DanhmucSPRSQ;
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
public class DanhmucSPController {
    @Autowired
    public DanhmucSPRepository danhmucSPRepo;

    @Autowired
    public DanhmucRepository DanhmucRepo;

    @RequestMapping("components/danhmucSP")
    public String getAll(Model model) {
        List<DanhmucSP> danhmucSPS = danhmucSPRepo.findAll();

        List<DanhmucSPDTO> danhmucSPDTOS = new ArrayList<>();
        danhmucSPS.forEach(obj -> {
            DanhmucSPDTO danhmucSPDTO = new DanhmucSPDTO();
            danhmucSPDTO.setId(obj.getId());
            danhmucSPDTO.setTendanhmucSP(obj.getTendanhmucSP());
            danhmucSPDTO.setHinhanhDM("../assets/img/imgDMSP/" + obj.getHinhanhDM());
            danhmucSPDTO.setKhuyenmai(obj.getKhuyenmai());
            danhmucSPDTO.setCreatedAt(obj.getCreatedAt());
            danhmucSPDTO.setUpdatedAt(obj.getUpdatedAt());

            if (obj.getDanhmuc() != null) {
                danhmucSPDTO.setTendanhmuc(obj.getDanhmuc().getTendanhmuc());
            }

            danhmucSPDTOS.add(danhmucSPDTO);
        });
        model.addAttribute("danhmucSPDTOS", danhmucSPDTOS);
        return "admin/components/danhmucSP";
    }

    @RequestMapping("admin/danhmucsp_add")
    public String add(Model model) {
        List<Danhmuc> danhmucs = DanhmucRepo.findAll();
        model.addAttribute("danhmucs", danhmucs);
        return "admin/add/danhmucSP_add";
    }

    @RequestMapping(value = "admin/danhmucsp_save", method = RequestMethod.POST)
    public String save(@ModelAttribute DanhmucSPRSQ danhmucSPRSQ,
                       @RequestParam("hinhanhDM") MultipartFile file) throws IOException {

        DanhmucSP danhmucSP = new DanhmucSP();
        String nameFile = "";
        if (file != null){
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get("C:\\Users\\OS\\OneDrive\\Documents\\meta\\src\\main\\resources\\static\\assets\\img\\imgDMSP",
                    file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            nameFile = file.getOriginalFilename();
            Files.write(fileNameAndPath, file.getBytes());
        }

        danhmucSP.setTendanhmucSP(danhmucSPRSQ.getTendanhmucSP());
        danhmucSP.setHinhanhDM(nameFile);
        danhmucSP.setKhuyenmai(danhmucSPRSQ.getKhuyenmai());
        int danhmucId = danhmucSPRSQ.getDanhmucId();
        Danhmuc danhmuc = DanhmucRepo.findById(danhmucId).orElse(null);
        if (danhmuc != null) {
            danhmucSP.setDanhmuc(danhmuc);
        }

        danhmucSPRepo.save(danhmucSP);

        return "redirect:/components/danhmucSP";
    }

    @RequestMapping("danhmucsp_delete/{id}")
    public String delete(@PathVariable int id) {
        danhmucSPRepo.deleteById(id);
        return "redirect:/components/danhmucSP";
    }
}
