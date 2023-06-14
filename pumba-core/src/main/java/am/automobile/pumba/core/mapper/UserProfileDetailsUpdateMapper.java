package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.User;
import am.automobile.pumba.core.mapper.base.BaseMapper;
import com.automobile.pumba.data.transfer.request.UserProfileDetailsRequest;
import com.automobile.pumba.data.transfer.response.UserProfileDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileDetailsUpdateMapper implements BaseMapper<User, UserProfileDetailsRequest, UserProfileDetailsResponse> {

    private final ModelMapper modelMapper;

    public void updateUserFromDto(UserProfileDetailsRequest dto, User user) {
        modelMapper.map(dto, user);
    }


    @Override
    public User toEntity(UserProfileDetailsRequest userProfileDetailsRequest) {
        return modelMapper.map(userProfileDetailsRequest, User.class);
    }

    @Override
    public UserProfileDetailsResponse toResponse(User user) {
        return modelMapper.map(user, UserProfileDetailsResponse.class);
    }
}
