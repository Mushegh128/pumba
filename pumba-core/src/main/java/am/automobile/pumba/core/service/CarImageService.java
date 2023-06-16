package am.automobile.pumba.core.service;

import java.util.List;

public interface CarImageService {

    void saveAll(long carId, List<String> imageUrls);
}
