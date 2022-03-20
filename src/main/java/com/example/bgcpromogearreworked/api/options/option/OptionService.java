package com.example.bgcpromogearreworked.api.options.option;

import com.example.bgcpromogearreworked.api.options.exceptions.OptionNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.Option;
import com.example.bgcpromogearreworked.persistence.repositories.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final OptionRepository optionRepo;

    public boolean checkOptionExists(Long optionId) {
        assert optionId != null;
        return optionRepo.existsById(optionId);
    }

    public Option getOption(Long optionId) {
        assert optionId != null;
        return optionRepo.findById(optionId).orElseThrow(OptionNotFoundException::new);
    }

    public List<Option> getOptions() {
        return optionRepo.findAll();
    }

    public <T> Option createOption(T source, Function<T, Option> mapper) {
        assert source != null && mapper != null;
        Option option = mapper.apply(source);
        assert option.getId() == null;
        return optionRepo.saveAndFlush(option);
    }

    public <T> Option updateOption(Long optionId, T source, BiFunction<T, Option, Option> mapper) {
        assert optionId != null && source != null && mapper != null;
        Option option = mapper.apply(source, optionRepo.findById(optionId).orElseThrow(OptionNotFoundException::new));
        assert option.getId().equals(optionId);
        return optionRepo.saveAndFlush(option);
    }

    public void deleteOption(Long optionId) {
        assert optionId != null;
        optionRepo.deleteById(optionId);
    }

}
