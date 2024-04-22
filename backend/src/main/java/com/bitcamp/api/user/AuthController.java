package com.bitcamp.api.user;
import com.bitcamp.api.common.component.Messenger;
import com.bitcamp.api.common.component.pagination.PageRequestVo;
import com.bitcamp.api.user.model.UserDto;
import com.bitcamp.api.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Log4j2
@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
    @ApiResponse(responseCode = "404", description = "Customer not found")})
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final UserService service;


   

    @PostMapping("/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDto param) {
        log.info("::: login controller parameter ",param);
        Messenger messenger = service.login(param);
        return ResponseEntity.ok(messenger);
    }


    @GetMapping("/exists-username")
    public ResponseEntity<Boolean> existsUsername(@RequestParam("username") String username) {
        log.info("existsUsernam 파라미터 정보 : "+username);
        Boolean flag = service.existsUsername(username);
        log.info("existsUsernam 결과 : "+flag);
        return ResponseEntity.ok(flag);
    }

}
