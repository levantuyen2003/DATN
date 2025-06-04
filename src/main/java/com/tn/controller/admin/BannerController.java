package com.tn.controller.admin;

import com.tn.dto.BannerDTO;
import com.tn.entity.Banner;
import com.tn.repository.BannerRepository;
import com.tn.rsq.BannerRSQ;
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
public class BannerController {
    @Autowired
    public BannerRepository bannerRepo;

    @RequestMapping("components/banner")
    public String getAll(Model model){
        List<Banner> banners = bannerRepo.findAll();

        List<BannerDTO> bannerDTOs = new ArrayList<>();
        banners.forEach(obj -> {
            BannerDTO bannerDTO = new BannerDTO();
            bannerDTO.setId(obj.getId());
            bannerDTO.setTenbanner(obj.getTenbanner());
            bannerDTO.setHinhanh("../assets/img/imgBanner/" + obj.getHinhanh());
            bannerDTO.setCreatedAt(obj.getCreatedAt());
            bannerDTO.setUpdatedAt(obj.getUpdatedAt());

            bannerDTOs.add(bannerDTO);
        });

        model.addAttribute("bannerDTOs", bannerDTOs);
        return "admin/components/banner";
    }

    @RequestMapping("admin/banner_add")
    public String addBanner(Model model){

        return "admin/add/banner_add";
    }
    @RequestMapping(value = "admin/banner_save", method = RequestMethod.POST)
    public String saveBanner(@ModelAttribute BannerRSQ bannerRSQ,
                             @RequestParam("hinhanh") MultipartFile file) throws IOException {
        Banner banner = new Banner();
        String nameFile = "";
        if (file != null){
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get("C:\\Users\\OS\\Downloads\\meta\\src\\main\\resources\\static\\assets\\img\\imgBanner",
                    file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            nameFile = file.getOriginalFilename();
            Files.write(fileNameAndPath, file.getBytes());
        }

        banner.setTenbanner(bannerRSQ.getTenbanner());
        banner.setHinhanh(nameFile);

        bannerRepo.save(banner);

        return "redirect:/components/banner";
    }

    @RequestMapping("banner_delete/{id}")
    public String delete(@PathVariable int id) {
        bannerRepo.deleteById(id);
        return "redirect:/components/banner";
    }
}
