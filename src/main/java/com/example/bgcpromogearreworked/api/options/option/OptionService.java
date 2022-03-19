package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepo;

    public boolean checkOptionExists(Long optionId) {
        return optionRepo.existsById(optionId);
    }

    public Option getOption(Long optionId) {
        return optionRepo.findById(optionId).orElseThrow(OptionNotFoundException::new);
    }

    public Streamable<Option> getOptions() {
        return Streamable.of(optionRepo.findAll());
    }

    public Option createOption(Option option) {
        if (option.getId() != null) {
            throw new RuntimeException("Option ID must be null.");
        }
        return optionRepo.saveAndFlush(option);
    }

    public <T> Option createOption(T source, Function<T, Option> mapper) {
        return createOption(mapper.apply(source));
    }

    public <T> Option updateOption(Option option) {
        if (option.getId() == null) {
            throw new RuntimeException("Option ID must not be null.");
        }
    }

}
