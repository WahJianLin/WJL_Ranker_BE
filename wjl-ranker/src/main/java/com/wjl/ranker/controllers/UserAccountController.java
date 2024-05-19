package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.UserAccountDTO;
import com.wjl.ranker.validations.OnUpdateValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserAccountController {
    @GetMapping()
    ResponseEntity<List<UserAccountDTO>> getAllUsers();

    @GetMapping("{id}")
    ResponseEntity<UserAccountDTO> getUserById(@PathVariable Long id);

    @PostMapping()
    ResponseEntity<UserAccountDTO> createUser(@Valid @RequestBody UserAccountDTO userAccountDTO);

    @PutMapping()
    ResponseEntity<UserAccountDTO> updateUser(@Validated(OnUpdateValidation.class) @RequestBody UserAccountDTO userAccountDTO);

    @DeleteMapping("{id}")
    ResponseEntity deleteUser(@PathVariable Long id);
}
