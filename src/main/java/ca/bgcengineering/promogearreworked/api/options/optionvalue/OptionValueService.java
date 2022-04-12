package ca.bgcengineering.promogearreworked.api.options.optionvalue;

import ca.bgcengineering.promogearreworked.api.options.exceptions.OptionValueNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OptionValueService {

    private final OptionValueRepository valueRepo;

    public boolean checkOptionValueExistsOnOption(Long optionId, Long valueId) {
        assert optionId != null && valueId != null;
        return valueRepo.existsByOptionIdAndId(optionId, valueId);
    }

    public OptionValue getOptionValue(Long id) {
        assert id != null;
        return valueRepo.findById(id).orElseThrow(OptionValueNotFoundException::new);
    }

    public List<OptionValue> getOptionValues(Long optionId) {
        assert optionId != null;
        return valueRepo.findAllByOptionId(optionId);
    }

    public <T> OptionValue createOptionValue(T source, Function<T, OptionValue> mapper) {
        assert source != null && mapper != null;
        OptionValue optionValue = mapper.apply(source);
        assert optionValue.getId() == null;
        return valueRepo.save(optionValue);
    }

    public <T> OptionValue updateOptionValue(Long valueId, T source, BiFunction<T, OptionValue, OptionValue> mapper) {
        assert valueId != null && source != null && mapper != null;
        OptionValue optionValue = mapper.apply(source, valueRepo.findById(valueId).orElseThrow(OptionValueNotFoundException::new));
        assert optionValue.getId().equals(valueId);
        return valueRepo.save(optionValue);
    }

    public void deleteOptionValue(Long id) {
        assert id != null;
        valueRepo.deleteById(id);
    }

}
