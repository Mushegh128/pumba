package am.automobile.pumba.core.mapper;

import am.automobile.pumba.core.entity.User;
import com.automobile.pumba.data.transfer.request.UserInfoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoUpdateMapper {

    private final ModelMapper modelMapper;

    public void updateUserFromDto(UserInfoUpdateRequest dto, User user) {
        modelMapper.map(dto, user);
    }
}
