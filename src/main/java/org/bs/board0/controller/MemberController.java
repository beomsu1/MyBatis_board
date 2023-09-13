package org.bs.board0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/member/")
public class MemberController {
    
    @GetMapping("login")
    public void getLoginPage(){

        log.info("Get | loginPage");
    }
}
