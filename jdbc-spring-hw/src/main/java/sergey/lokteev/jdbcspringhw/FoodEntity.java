package sergey.lokteev.jdbcspringhw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "FOOD")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Генерим id
    @Column(name = "FOOD_ID")
    private Integer foodId;
    @Column(name = "FOOD_NAME")
    private String foodName;
    @Column(name = "FOOD_TYPE")
    @Enumerated(EnumType.STRING)
    private FoodTypeEnum foodType;
    @Column(name = "FOOD_EXOTIC")
    private BigDecimal foodExotic;
}
