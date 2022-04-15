package ca.bgcengineering.promogearreworked.api.users.user.secured;

import ca.bgcengineering.promogearreworked.api.users.exceptions.GraphUserNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.exceptions.UserNotFoundException;
import ca.bgcengineering.promogearreworked.api.users.user.UserService;
import ca.bgcengineering.promogearreworked.api.users.user.secured.dto.*;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/secured/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SecuredUserController {

    private final UserService service;
    private final SecuredUserHandlerService handlerService;
    private final SecuredUserMapper mapper;
    private final GraphServiceClient graphClient;

    @GetMapping("/{userId}")
    private SecuredUserResponse getUser(@PathVariable Long userId) {
        if (!service.checkUserExists(userId)) {
            throw new UserNotFoundException();
        }
        SecuredUserResponse response = mapper.toResponse(handlerService.handleUserGet(userId));
        User user = graphClient.users(response.getOid().toString()).buildRequest().get();
        if (user == null) {
            throw new GraphUserNotFoundException();
        }
        response.setEmail(user.mail);
        return response;
    }

    @GetMapping
    private SecuredUserBatchResponse getUserBatch(@QuerydslPredicate(root = ca.bgcengineering.promogearreworked.persistence.entities.User.class) Predicate predicate,
                                                  Pageable pageable) {
        return mapper.toBatchResponse(handlerService.handleUserBatchGet(predicate, pageable));
    }

    @PutMapping("/{userId}")
    private SecuredUserResponse updateUser(@PathVariable Long userId, @RequestBody SecuredUserUpdate userUpdate) {
        if (!service.checkUserExists(userId)) {
            throw new UserNotFoundException();
        }
        userUpdate.setId(userId);
        return mapper.toResponse(handlerService.handleUserUpdate(userUpdate));
    }

    @PatchMapping("/{userId}")
    private SecuredUserResponse updateUserPartial(@PathVariable Long userId,
                                                  @RequestBody SecuredUserPartialUpdate userPartialUpdate) {
        if (!service.checkUserExists(userId)) {
            throw new UserNotFoundException();
        }
        userPartialUpdate.setId(userId);
        return mapper.toResponse(handlerService.handleUserPartialUpdate(userPartialUpdate));
    }

}
