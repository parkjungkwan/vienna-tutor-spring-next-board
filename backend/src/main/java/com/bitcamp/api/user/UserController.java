package com.bitcamp.api.user;
import com.bitcamp.api.common.component.Messenger;
import com.bitcamp.api.common.component.pagination.PageRequestVo;
import com.bitcamp.api.common.component.security.JwtProvider;
import com.bitcamp.api.user.model.UserDto;
import com.bitcamp.api.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.stream.Stream;
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
@RequestMapping(path = "/api/users")
public class UserController {
    private final UserService service;
    private final JwtProvider jwtProvider;


    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDto dto) {
        return ResponseEntity.ok(service.save(dto));

    }


    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll(PageRequestVo vo){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")

    public ResponseEntity<Optional<UserDto>> findById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }



    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody UserDto param) {
        return ResponseEntity.ok(service.modify(param));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Messenger> count() {
        return ResponseEntity.ok(service.count());

    }

    @PostMapping("/search")
    public ResponseEntity<List<UserDto>> findUsersByName(@RequestBody UserDto param) {
        return ResponseEntity.ok(service.findUsersByName(param.getName()));
    }

  
    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsById(@RequestParam("id") long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String accessToken) {
        log.info("1- logout request : {}", accessToken);
        var flag = service.logout(accessToken); // 토큰이 있으면 false, 없으면 true
        return ResponseEntity.ok(flag);
    }


}
