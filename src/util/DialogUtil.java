package util;

import javafx.scene.control.Alert;

public class DialogUtil {

    private DialogUtil() {
        // Construtor privado para esconder o implícito público (boa prática)
    }

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DialogUtil.class.getName());

    public static void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    public static void logWarning(String message) {
        LOGGER.warning(message);
    }

    public static void logWarning(String message, Throwable e) {
        LOGGER.log(java.util.logging.Level.WARNING, message, e);
    }

    public static void logError(String message, Throwable e) {
        LOGGER.log(java.util.logging.Level.SEVERE, message, e);
    }
}
