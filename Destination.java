package com.travelagency.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Destination {
    private int destinationId;
    private String name;
    private String description;
    private String country;
    private String city;
    private BigDecimal pricePerDay;
    private int durationDays;
    private String imageUrl;
    private String highlights;
    private String bestSeason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Destination() {}

    public Destination(String name, String description, String country, String city, BigDecimal pricePerDay) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.city = city;
        this.pricePerDay = pricePerDay;
    }

    // Getters and Setters
    public int getDestinationId() { return destinationId; }
    public void setDestinationId(int destinationId) { this.destinationId = destinationId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public BigDecimal getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(BigDecimal pricePerDay) { this.pricePerDay = pricePerDay; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getHighlights() { return highlights; }
    public void setHighlights(String highlights) { this.highlights = highlights; }

    public String getBestSeason() { return bestSeason; }
    public void setBestSeason(String bestSeason) { this.bestSeason = bestSeason; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
