package com.algaworks.algashop.ordering.ordering.domain;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String fullName;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private String document;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private Integer loyaltyPoints;

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document, Boolean promotionNotificationsAllowed, OffsetDateTime registeredAt) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
        this.registeredAt = registeredAt;
    }

    public Customer(UUID id, String fullName, LocalDate birthDate, String email, String phone, String document, Boolean promotionNotificationsAllowed, Boolean archived, OffsetDateTime registeredAt, OffsetDateTime archivedAt, Integer loyaltyPoints) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.document = document;
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
        this.archived = archived;
        this.registeredAt = registeredAt;
        this.archivedAt = archivedAt;
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(Integer points) {
        if (this.loyaltyPoints == null) {
            this.loyaltyPoints = points;
        }
        this.loyaltyPoints += points;
    }

    public void archive() {
        this.archived = true;
        this.archivedAt = OffsetDateTime.now();
    }

    public void enablePromotionNotifications() {
        this.promotionNotificationsAllowed = true;
    }

    public void disablePromotionNotifications() {
        this.promotionNotificationsAllowed = false;
    }


    public void changeEmail(String email) {
        this.email = email;
    }

    public void changePhone(String phone) {
        this.phone = phone;
    }

    public void changeDocument(String document) {
        this.document = document;
    }

    public void changeName(String fullName) {
        this.fullName = fullName;
    }

    private UUID getId() {
        return id;
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private LocalDate getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private String getDocument() {
        return document;
    }

    private void setDocument(String document) {
        this.document = document;
    }

    private Boolean isPromotionNotificationsAllowed() {
        return promotionNotificationsAllowed;
    }

    private void setPromotionNotificationsAllowed(Boolean promotionNotificationsAllowed) {
        this.promotionNotificationsAllowed = promotionNotificationsAllowed;
    }

    private Boolean isArchived() {
        return archived;
    }

    private void setArchived(Boolean archived) {
        this.archived = archived;
    }

    private OffsetDateTime getRegisteredAt() {
        return registeredAt;
    }

    private void setRegisteredAt(OffsetDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    private OffsetDateTime getArchivedAt() {
        return archivedAt;
    }

    private void setArchivedAt(OffsetDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    private Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    private void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}