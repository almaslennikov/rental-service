package helpers;

import com.nntu.containers.info.ModelInfo;
import com.nntu.containers.info.UserInfo;
import com.nntu.containers.info.UserRole;
import com.nntu.models.Model;
import com.nntu.models.User;
import com.nntu.models.Vehicle;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class VehicleTestInfo {
    private Long id;
    private ModelInfo modelInfo;
    private Boolean isBusy;
    private UserInfo landlord;

    private VehicleTestInfo(Long id, ModelInfo modelInfo, Boolean isBusy, UserInfo landlord) {
        this.id = id;
        this.modelInfo = modelInfo;
        this.isBusy = isBusy;
        this.landlord = landlord;
    }

    public String idString() {
        return "?id=" + id;
    }

    public String newVehicleString() {
        return "?ownerId=" + landlord.getId() + '&' +
                "modelId=" + modelInfo.getId();
    }

    private User getUserLandlord() {
        return new User(landlord.getName(),
                landlord.getLastName(),
                landlord.getEmail(),
                UUID.randomUUID().toString(),
                UserRole.CUSTOMER);
    }

    private Model getModelModelInfo() {
        return new Model(modelInfo.getBrand(), modelInfo.getModel());
    }

    public VehicleTestInfo getAnotherRecord() {
        return new VehicleTestInfo(id + 1, modelInfo, !isBusy, landlord);
    }

    public Vehicle getVehicle() {
        return new Vehicle(getModelModelInfo(), getIsBusy(), getUserLandlord());
    }
}
