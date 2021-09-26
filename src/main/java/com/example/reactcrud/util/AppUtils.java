package com.example.reactcrud.util;

import com.example.reactcrud.dto.UserDto;
import com.example.reactcrud.entity.User;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User dtoToEntity(UserDto productDto) {
        User product = new User();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
