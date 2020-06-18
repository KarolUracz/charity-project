package pl.coderslab.charity.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

@Component
public class categoryConverter implements Converter<String, Category> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category convert(String s) {
        return categoryRepository.getOne(Long.parseLong(s));
    }
}
