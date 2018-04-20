package ru.erogov.watty.api;

import java.math.BigDecimal;

public class Offer {
    private String vendorCode;
    private String name;
    private BigDecimal price;

    public Offer() {
    }

    public Offer(String vendorCode, String name, BigDecimal price) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.price = price;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "vendorCode='" + vendorCode + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
