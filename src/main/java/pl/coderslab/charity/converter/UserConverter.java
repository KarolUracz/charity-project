package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User convert(String s) {
        return userRepository.getOne(Long.parseLong(s));
    }
}
