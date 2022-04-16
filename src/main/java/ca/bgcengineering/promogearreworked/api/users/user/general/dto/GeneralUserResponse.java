package ca.bgcengineering.promogearreworked.api.users.user.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralUserResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedOfficeLocation {
        private final Long id;
        private final String name;
    }

    private final String displayName;
    private final NestedOfficeLocation location;

}
