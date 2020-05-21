package com.johnebri.foodvendorapp.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnebri.foodvendorapp.notification.data.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
