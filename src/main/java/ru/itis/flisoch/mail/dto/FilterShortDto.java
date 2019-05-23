package ru.itis.flisoch.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.flisoch.mail.domain.Filter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterShortDto {

    private Long id;

    private String matches;
    private String words;
    private String actions;

    public static FilterShortDto from(Filter filter) {

        FilterShortDto filterShortDto = new FilterShortDto();
        filterShortDto.setId(filter.getId());
        String matches = "";
        if (filter.getFromUser() != null) {
            matches += "From:" + filter.getFromUser() + " ";
        }
        if (filter.getToUser() != null) {
            matches += "To:" + filter.getToUser() + " ";
        }
        if (filter.getSubject() != null) {
            matches += "Subject:" + filter.getSubject() + " ";
        }
        filterShortDto.setMatches(matches);

        String words = "";
        if (filter.getContainingWords() != null) {
            words += filter.getContainingWords();
        }
        filterShortDto.setWords(words);


        String actions = "";
        if (filter.getMoveTo() != null) {
            actions += "Move to: " + filter.getMoveTo().getName() + " ";
        }
        if (filter.getCopyTo() != null) {
            actions += "Copy to: " + filter.getCopyTo().getName() + " ";
        }
        filterShortDto.setActions(actions);
        return filterShortDto;
    }
}
