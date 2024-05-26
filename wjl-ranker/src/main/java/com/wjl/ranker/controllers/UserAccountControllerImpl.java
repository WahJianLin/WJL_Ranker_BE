package com.wjl.ranker.controllers;

import com.wjl.ranker.dto.UserAccountDTO;
import com.wjl.ranker.entities.UserAccount;
import com.wjl.ranker.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserAccountControllerImpl implements UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountControllerImpl(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public ResponseEntity<List<UserAccountDTO>> getAllUsers() {
        List<UserAccount> response = userAccountService.getAll();
        return ResponseEntity.ok(response.stream().map(this::toDTO).toList());
    }

    @Override
    public ResponseEntity<UserAccountDTO> getUserById(Long id) {
        UserAccount response = userAccountService.getById(id);
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<UserAccountDTO> createUser(UserAccountDTO userAccountDTO) {
        UserAccount response = userAccountService.create(toEntity(userAccountDTO));
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<UserAccountDTO> updateUser(UserAccountDTO userAccountDTO) {
        UserAccount response = userAccountService.update(toEntity(userAccountDTO));
        return ResponseEntity.ok(toDTO(response));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userAccountService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserAccountDTO toDTO(UserAccount userAccount) {
        UserAccountDTO dto = new UserAccountDTO();
        dto.setId(userAccount.getId());
        dto.setName(userAccount.getName());

        return dto;
    }

    private UserAccount toEntity(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(userAccountDTO.getId());
        userAccount.setName(userAccountDTO.getName());

        return userAccount;
    }
}
