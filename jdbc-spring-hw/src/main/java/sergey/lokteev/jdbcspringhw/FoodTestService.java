package sergey.lokteev.jdbcspringhw;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FoodTestService {

    private final FoodRepository foodRepository;

    //Генерим случайную еду
    private FoodEntity createTestFood() {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodName(UUID.randomUUID().toString());
        foodEntity.setFoodType(FoodTypeEnum.values()[(int) Math.round(Math.random())]);
        foodEntity.setFoodExotic(BigDecimal.valueOf(Math.round(Math.random())));
        return foodEntity;
    }
//Сохраняем в БД
    private FoodEntity saveFood(FoodEntity foodEntity) {
        return foodRepository.save(foodEntity);

    }
//Удаляем из БД
    private void deleteFood(FoodEntity foodEntity) {
        foodRepository.delete(foodEntity);


    }
//
    private Optional<FoodEntity> selectFoodByName(String foodName) {
        return foodRepository.findByFoodName(foodName);
    }

    @Transactional
    @PostConstruct
    public void test() {
        FoodEntity testFood = createTestFood();
        Assertions.assertTrue(selectFoodByName(testFood.getFoodName()).isEmpty());
        saveFood(testFood);
        Assertions.assertTrue(selectFoodByName(testFood.getFoodName()).isPresent());
        deleteFood(testFood);
        Assertions.assertTrue(selectFoodByName(testFood.getFoodName()).isEmpty());
    }
}

