package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
