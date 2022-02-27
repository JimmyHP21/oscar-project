package br.com.renan.project.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SHOE")
@SequenceGenerator(allocationSize = 1, name = "seqShoe", sequenceName = "SEQ_SHOE")
public class Shoe implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seqShoe")
    private Long id;

    @NotNull
    @Column(name = "SIZE_SHOE")
    private int size;

    @NotNull
    @Column(name = "CATEGORY_SHOE")
    private String category;

    @NotNull
    @Column(name = "COLOR_SHOE")
    private String color;

    @NotNull
    @Column(name = "PRICE_SHOE", columnDefinition="Decimal(10,2)")
    private Double price;

    @NotNull
    @Column(name = "BRAND_SHOE")
    private String brand;

    @NotNull
    @Column(name = "QUANTITY_SHOE")
    private Long quantity;

    @Column(name = "DESCRIPTION_SHOE")
    private String description;

    @NotNull
    @CreatedDate
    @Column(name = "DATE_REGISTER")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateRegister;

    public Shoe(int size, String category, String color, Double price, String brand, Long quantity, String description, LocalDate dateRegister) {
        this.size = size;
        this.category = category;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.description = description;
        this.dateRegister = dateRegister;
    }

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(getDateRegister()))
            setDateRegister(LocalDate.now());
    }
}
