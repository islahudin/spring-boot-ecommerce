package com.islahdev.ecommersapi.model.template;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class InvoiceResponse {
    private String id;
    private String externalId;
    private String userId;
    private String status;
    private String merchantName;
    private String merchantProfilePictureUrl;
    private int amount;
    private String description;
    private String expiryDate;
    private String invoiceUrl;
    private List<AvailableBank> availableBanks;
    private List<AvailableRetailOutlet> availableRetailOutlets;
    private List<AvailableEwallet> availableEwallets;
    private List<AvailableQrCode> availableQrCodes;
    private List<AvailableDirectDebit> availableDirectDebits;
    private List<AvailablePaylater> availablePaylaters;
    private boolean shouldExcludeCreditCard;
    private boolean shouldSendEmail;
    private String successRedirectUrl;
    private String failureRedirectUrl;
    private String created;
    private String updated;
    private String currency;
    private List<Item> items;
    private List<Fee> fees;
    private Customer customer;
    private CustomerNotificationPreference customerNotificationPreference;
    private Object metadata;

    @Data
    @NoArgsConstructor
    public static class AvailableBank {
        private String bankCode;
        private String collectionType;
        private int transferAmount;
        private String bankBranch;
        private String accountHolderName;
        private int identityAmount;
    }

    @Data
    @NoArgsConstructor
    public static class AvailableRetailOutlet {
        // Add relevant fields if any are required in the future
    }

    @Data
    @NoArgsConstructor
    public static class AvailableEwallet {
        // Add relevant fields if any are required in the future
    }

    @Data
    @NoArgsConstructor
    public static class AvailableQrCode {
        private String qrCodeType;
    }

    @Data
    @NoArgsConstructor
    public static class AvailableDirectDebit {
        // Add relevant fields if any are required in the future
    }

    @Data
    @NoArgsConstructor
    public static class AvailablePaylater {
        private String paylaterType;
    }

    @Data
    @NoArgsConstructor
    public static class Item {
        private String name;
        private int quantity;
        private int price;
        private String category;
        private String url;
    }

    @Data
    @NoArgsConstructor
    public static class Fee {
        private String type;
        private int value;
    }

    @Data
    @NoArgsConstructor
    public static class Customer {
        private String givenNames;
        private String mobileNumber;
    }

    @Data
    @NoArgsConstructor
    public static class CustomerNotificationPreference {
        private List<String> invoiceCreated;
        private List<String> invoiceReminder;
        private List<String> invoicePaid;
    }
}
