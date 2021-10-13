package ru.haazad.onlinestorage.webapp.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.haazad.onlinestorage.webapp.models.Product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Data
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "product", propOrder = {
        "id",
        "title",
        "price"
})
public class ProductDto {
    @XmlElement(required = true)
    private Long id;
    private String title;
    private Float price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
