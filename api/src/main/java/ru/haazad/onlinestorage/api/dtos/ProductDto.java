package ru.haazad.onlinestorage.api.dtos;

//import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import ru.haazad.onlinestorage.core.models.Product;
//
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlType;
//
//@Data
//@NoArgsConstructor
//@Getter
//@Setter
//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "product", propOrder = {
//        "id",
//        "title",
//        "price"
//})
public class ProductDto {
//    @XmlElement(required = true)
    private Long id;
    private String title;
    private Float price;

    public ProductDto() {
    }

    public ProductDto(Long id, String title, Float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
