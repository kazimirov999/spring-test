package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDto saveUser(UserDto userDto) {
        User persist = userRepository.save(modelMapper.map(userDto, User.class));
        return modelMapper.map(persist, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(toList());
    }

    public UserDto getUserById(Integer id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(()-> new EntityNotFoundException("User not found"));
    }

    public void updateUser(Integer id, UserDto userDto) {
        userRepository.findById(id)
                .map(user -> modelMapper.map(userDto, User.class))
                .ifPresentOrElse(userRepository::save,
                        () -> {
                            throw new EntityNotFoundException("User not found by id " + id);
                        });
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    public void deleteUserAll() {
        userRepository.deleteAll();
    }

}