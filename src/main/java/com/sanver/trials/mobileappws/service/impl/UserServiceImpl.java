package com.sanver.trials.mobileappws.service.impl;

import com.sanver.trials.mobileappws.io.entity.AddressEntity;
import com.sanver.trials.mobileappws.io.repository.UserRepository;
import com.sanver.trials.mobileappws.io.entity.UserEntity;
import com.sanver.trials.mobileappws.service.UserService;
import com.sanver.trials.mobileappws.shared.Utils;
import com.sanver.trials.mobileappws.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new RuntimeException("Record already exists");

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        String userId = utils.generateUserId(30);
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        List<AddressEntity> addresses = userEntity.getAddresses();
        if (addresses != null)
            for (AddressEntity address : addresses) {
                address.setAddressId(utils.generateAddressId(30));
                address.setUserDetails(userEntity);
            }
        UserEntity storedUserDetails = userRepository.save(userEntity);

        return modelMapper.map(storedUserDetails, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(email);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(user, result);

        return result;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity user = userRepository.findByUserId(userId);

        if (user == null)
            throw new UsernameNotFoundException(userId);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(user, result);

        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException(email);

        return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}
