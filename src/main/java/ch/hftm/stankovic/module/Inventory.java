package ch.hftm.stankovic.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_id_seq")
    @SequenceGenerator(name = "inventory_id_seq", sequenceName = "inventory_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "min_quantity", nullable = false)
    private Integer minQuantity;
}
