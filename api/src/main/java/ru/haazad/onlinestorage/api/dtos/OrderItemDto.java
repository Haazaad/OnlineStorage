package ru.haazad.onlinestorage.api.dtos;

public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private Float productPrice;
    private Float price;

    public OrderItemDto() {
    }

    public OrderItemDto(ProductDto productDto) {
        this.productId = productDto.getId();
        this.productTitle = productDto.getTitle();
        this.quantity = 1;
        this.productPrice = productDto.getPrice();
        this.price = productDto.getPrice();
    }

    public OrderItemDto(Long productId, String productTitle, int quantity, Float productPrice, Float price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void changeQuantity(int delta) {
        quantity += delta;
        if (quantity < 0) {
            quantity = 0;
        }
        price = productPrice * quantity;
    }
}
