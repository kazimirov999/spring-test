package com.udelphi.service;

import java.util.List;
import com.udelphi.dto.OrderDto;
import com.udelphi.dto.ProductDto;
import com.udelphi.dto.RoleDto;
import com.udelphi.dto.UserDto;
import com.udelphi.exception.EntityNotFoundException;
import com.udelphi.model.User;
import com.udelphi.repository.OrderRepository;
import com.udelphi.repository.ProductRepository;
import com.udelphi.repository.RoleRepository;
import com.udelphi.repository.UserRepository;
import static java.util.stream.Collectors.toList;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository,
                           RoleRepository roleRepository, OrderRepository orderRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto saveUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public UserDto getUser(int id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(toList());
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void updateUser(int id, UserDto userDto) {

        userRepository.findById(id)
                .map(user -> modelMapper.map(user, User.class))
                .ifPresentOrElse(userRepository::save,
                        () -> {
                            throw new EntityNotFoundException("Entity not found with id: " + id);
                        });
    }

    public List<ProductDto> getAllProductsByUserId(int id) {
        return productRepository.findAllByUserId(id)
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(toList());
    }

    public List<RoleDto> getAllRolesByUserId(int id) {
        return roleRepository.findAllByUserId(id)
                .stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(toList());
    }

    public List<OrderDto> getAllOrdersByUserId(int id) {
        return orderRepository.findAllByClientId(id)
                .stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(toList());
    }


    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with email: " + email));
    }

    @PreAuthorize("#id == authentication.principal.id")
    public void deleteUserRole(int id, int roleId) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + id));

        user.deleteRole(roleId);
        userRepository.save(user);
    }
}

