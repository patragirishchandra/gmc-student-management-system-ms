package com.gmc.studentmanagement.util;

import static com.gmc.studentmanagement.exception.enums.ErrorMessageEnum.SOMETHING_WENT_WRONG;

import com.gmc.studentmanagemet.resource.Notification;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * Utility class for handling exceptions and preparing notifications.
 * <p>
 * This class provides methods to prepare notifications based on exceptions and other information.
 * It also utilizes classes like Notification and StringUtils for its operations. The class is
 * designed to be used statically, without instantiation
 *
 * @author Girish Chandra Patra
 */
public final class ExceptionUtil {

  private ExceptionUtil() {
    // Private constructor to prevent instantiation of this utility class
  }

  /**
   * Prepares a list of notifications for response based on the provided throwable.
   *
   * @param throwable the throwable for which notifications are being prepared
   * @param severity  the severity level of the notifications
   * @param errorCode the error code associated with the notifications
   * @param uuid      the UUID associated with the notifications
   * @param metadata  additional metadata to include in the notifications
   * @return a list of notifications prepared based on the input parameters
   */
  public static List<Notification> prepareOutputMessage(Throwable throwable, String severity,
      String errorCode, String uuid, String metadata) {
    List<Notification> notifications = new ArrayList<>();
    Notification notification = createNotification(severity, errorCode, throwable, uuid, metadata);
    notifications.add(notification);
    return notifications;
  }

  /**
   * Method to create a Notification instance based on the provided information
   */
  private static Notification createNotification(String severity, String errorCode,
      Throwable throwable,
      String uuid, String metadata) {
    Notification notification = new Notification();
    notification.setSeverity(severity);
    notification.setCode(errorCode);
    notification.setMessage(
        StringUtils.defaultIfEmpty(throwable.getMessage(), SOMETHING_WENT_WRONG.getErrorMessage()));
    notification.setUuid(uuid);
    notification.setTimestamp(OffsetDateTime.now().toString());
    notification.setMetadata(metadata);
    return notification;
  }
}

