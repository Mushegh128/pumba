package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.Car;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {

    CarResponse createCar(CarRequest carRequest);

    String saveImage(MultipartFile multipartFile);

    byte[] getImage(String fileName);

    Car findById(long id);

    Page<CarResponse> findAllForAdmin(Pageable pageable, CarAdminFilterRequest carFilterRequest);
}
