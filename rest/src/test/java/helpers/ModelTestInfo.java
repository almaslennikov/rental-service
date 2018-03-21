package helpers;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ModelTestInfo {
    private Long id;
    private String brand;
    private String model;

    public ModelTestInfo(Long id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    @Override
    public String toString() {
        return "?brand='" + brand + '&' +
                "model='" + model + '&';
    }
}
