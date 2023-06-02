package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.UserRegistrationRequest;
import com.automobile.pumba.data.transfer.response.UserRegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationMapper implements BaseMapper<User, UserRegistrationRequest, UserRegistrationResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRegistrationRequest userRegistrationRequest) {
        return modelMapper.map(userRegistrationRequest, User.class);
    }

    @Override
    public UserRegistrationResponse toResponse(User user) {
        return modelMapper.map(user, UserRegistrationResponse.class);
    }
}