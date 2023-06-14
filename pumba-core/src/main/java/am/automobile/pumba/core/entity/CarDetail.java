package am.automobile.pumba.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "car_detail")
public class CarDetail extends AbstractEntity {

    @OneToOne
    private Car car;

    private Boolean adaptiveCruiseControl;
    private Boolean cooledSeats;
    private Boolean airConditioner;
    private Boolean navigationSystem;
    private Boolean remoteStart;
    private Boolean infotainmentSystem;
    private Boolean bluetoothConnectivity;
    private Boolean appleCarPlayAndroidAuto;
    private Boolean premiumAudioSystems;
    private Boolean rearSeatEntertainment;
    private Boolean adaptiveCruiseControlACC;
    private Boolean laneDepartureWarningLDW;
    private Boolean forwardCollisionWarningFCW;
    private Boolean blindSpotDetectionBSD;
    private Boolean electronicStabilityControlESC;
    private Boolean bucketSeats;
    private Boolean benchSeats;
    private Boolean powerAdjustableSeats;
    private Boolean heatedSeats;
    private Boolean ventilatedSeats;
    private Boolean gpsNavigationSystem;
    private Boolean backupCamera;
    private Boolean parkingSensors;
}
