package com.fdcend.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_venta;
    private LocalDate fechaVenta;
    private Double total;

    @ManyToOne
    private Cliente unCliente;

    @JsonIgnoreProperties("listaVentas")
    @ManyToMany
    @JoinTable  (name = "venta_productos", 
                joinColumns = @JoinColumn(name = "fk_venta", nullable = false), 
                inverseJoinColumns = @JoinColumn(name = "fk_producto", nullable = false))
    private List<Producto> listaProductos;
}
