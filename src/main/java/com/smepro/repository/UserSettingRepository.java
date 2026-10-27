package com.smepro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smepro.entity.HelpDeskTicket;
import com.smepro.entity.UserSetting;


public interface UserSettingRepository  extends JpaRepository<UserSetting, Long>{

	UserSetting findByUserId(int user_id);


}
