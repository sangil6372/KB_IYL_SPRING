package org.scoula.ex06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")  // 클래스 레벨: 공통 URL 경로 설정
public class UserController {

    @RequestMapping("")          // 최종 URL: /users
    public String listUsers() {
        return "user-list";
    }

    @RequestMapping("/new")      // 최종 URL: /users/new
    public String newUserForm() {
        return "user-form";
    }

    @RequestMapping("/{id}")     // 최종 URL: /users/{id}
    public String getUserDetail(@PathVariable Long id) {
        return "user-detail";
    }
}