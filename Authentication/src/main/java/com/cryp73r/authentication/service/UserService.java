package com.cryp73r.authentication.service;

import com.cryp73r.authentication.exception.BadCredentialsException;
import com.cryp73r.authentication.exception.InternalServerErrorException;
import com.cryp73r.authentication.mapper.RoleMapper;
import com.cryp73r.authentication.mapper.UserMapper;
import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.repository.UserRepository;
import com.cryp73r.authentication.dto.UserDTO;
import com.cryp73r.authentication.security.jwt.JwtUtils;
import com.cryp73r.authentication.security.service.ClientUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleService roleService;

    private final ClientUserDetailsService clientUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    final
    JwtUtils jwtUtils;
    private final RoleMapper roleMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, RoleService roleService, ClientUserDetailsService clientUserDetailsService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.clientUserDetailsService = clientUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.roleMapper = roleMapper;
    }

    @Transactional
    public String signUp(UserDTO userDTO) {
        User user = userMapper.UserDTOToUser(userDTO);
        if (!(userDTO.getRoleNames() == null) && !userDTO.getRoleNames().isEmpty()) {
            for (String roleName : userDTO.getRoleNames()) {
                user.addRole(roleMapper.RoleDTOToRole(roleService.getRole(roleName)));
            }
        } else {
            user.addRole(roleMapper.RoleDTOToRole(roleService.getRole("GENERAL_USER")));
        }
        try {
            userRepository.save(user);
            UserDetails userDetails = clientUserDetailsService.loadUserByUsername(user.getEmail());
            return "Bearer " + jwtUtils.generateTokenFromUsername(userDetails);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error encountered while saving or generating token");
        }
    }

    @Transactional
    public String loginUser(UserDTO userDTO) {
        try {
            UserDetails userDetails = clientUserDetailsService.loadUserByUsername(userDTO.getEmail());
            if (passwordEncoder.matches(userDTO.getPassword(), userDetails.getPassword())) {
                return "Bearer " + jwtUtils.generateTokenFromUsername(userDetails);
            } else {
                throw new BadCredentialsException("Invalid username or password");
            }
        } catch (UsernameNotFoundException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public UserDTO getCurrentUser(String authenticatedUsername) {
        return userMapper.UserToUserDTO(userRepository.findByEmail(authenticatedUsername).orElseThrow(() -> new BadCredentialsException("User not found")));
    }

    @Transactional
    public void deleteUser(String authenticatedUsername) {
        User user = userRepository.findByEmail(authenticatedUsername).orElseThrow(() -> new BadCredentialsException("User not found"));
        user.removeAllRoles();
        userRepository.delete(user);
    }
}
