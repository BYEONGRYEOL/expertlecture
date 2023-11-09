package com.sparta.springauth;

import com.sparta.springauth.food.Chicken;
import com.sparta.springauth.food.Food;
import com.sparta.springauth.food.Pizza;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DuplicateBeanTest {
    @Autowired
    Food pizza;

    @Autowired
    Food chicken;

    @Autowired
    Food food;

    @Autowired
    @Qualifier("d")
    Food qualifierFood;




    @Test
    void beanNameDITest(){
        assertTrue(pizza instanceof Pizza);
        assertTrue(chicken instanceof Chicken);
    }

    @Test
    void primaryBeanDITest(){
        assertTrue(food instanceof Pizza);
    }

    @Test
    void primaryAndQualifierTest(){
        assertTrue(food instanceof Pizza);
        assertTrue(qualifierFood instanceof Chicken);
    }
}
