package aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example;

public class Guitar {
    private String brand = "Martin";

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}
