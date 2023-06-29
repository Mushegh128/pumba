package am.automobile.pumba.core.service;

import am.automobile.pumba.core.entity.Car;
import com.automobile.pumba.data.transfer.request.CarAdminFilterRequest;
import com.automobile.pumba.data.transfer.request.CarFilterRequest;
import com.automobile.pumba.data.transfer.request.CarRequest;
import com.automobile.pumba.data.transfer.response.CarDetailResponse;
import com.automobile.pumba.data.transfer.response.CarResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {

    CarResponse createCar(CarRequest carRequest);

    String saveImage(MultipartFile multipartFile);

    byte[] getImage(String fileName);

    Car findById(long id);

    Car findByIdAndIsPublicTrueAndIsApprovedTrue(long id);

    Car findByIdAndOwnerId(long id, long ownerId);

    Page<CarResponse> findAllForAdmin(Pageable pageable, CarAdminFilterRequest carFilterRequest);


    Page<CarDetailResponse> findAllFilter(Pageable pageable, CarFilterRequest carFilterRequest);

    CarResponse editCar(CarRequest carRequest, long carId);

    Car findByIdAndAccess(long id);

    CarResponse approveById(long id);

    CarResponse cancelById(long id);

    List<String> findAllImagesDetailUrlByCarId(long id);
}
