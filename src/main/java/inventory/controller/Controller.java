package inventory.controller;

import inventory.service.InventoryService;
import javafx.scene.control.Alert;

public interface Controller {
    void setService(InventoryService service);

    default void handleError(String errorMessage, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Error!");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
