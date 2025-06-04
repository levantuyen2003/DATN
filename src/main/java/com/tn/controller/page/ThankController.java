package com.tn.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThankController {

    @RequestMapping("/thank")
    public String orderSuccess() {
        return "page/thank";  // trả về trang HTML đơn giản cảm ơn
    }
}
