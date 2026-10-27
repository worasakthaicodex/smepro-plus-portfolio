package com.smepro.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.smepro.models.notification;

public class notificationmapper implements RowMapper<notification> {

	@Override
	public notification mapRow(ResultSet rs, int rowNum) throws SQLException {
		notification notifications = new notification();
		notifications.setNotification_id(rs.getInt("notification_id"));
		notifications.setNotification_alert(rs.getString("notification_alert"));
		notifications.setNotification_iduser(rs.getString("notification_iduser"));
		notifications.setNotification_iduser2(rs.getString("notification_iduser2"));
		notifications.setNotification_type(rs.getString("notification_type"));
		notifications.setNotification_read(rs.getInt("notification_read"));
		notifications.setNotification_date(rs.getString("notification_date"));
		notifications.setNotification_refer(rs.getString("notification_refer"));
		notifications.setNotification_note(rs.getString("notification_note"));
		notifications.setNotification_process_id(rs.getInt("notification_process_id"));
		return notifications;
	}

}

