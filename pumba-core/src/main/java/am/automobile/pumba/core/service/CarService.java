package am.automobile.pumba.core.service;

import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {

    CarResponse createCar(CarRequest carRequest);

    String saveImage(MultipartFile multipartFile);
}
