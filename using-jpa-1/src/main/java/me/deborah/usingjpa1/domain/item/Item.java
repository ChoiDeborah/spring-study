package me.deborah.usingjpa1.domain.item;

import lombok.Getter;
import lombok.Setter;
import me.deborah.usingjpa1.domain.Category;
import me.deborah.usingjpa1.exception.NotEnoughStockException;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//

    /**
     * 재고 (stock) 증가
     */
    public void addStock(int quantitiy) {
        this.stockQuantity += quantitiy;
    }

    /**
     * 재고 (stock) 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
