package ru.enenakhov.mai.lessons.lesson01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class CatTest {

    @Test
    void build() {
        Cat catActual = Cat.build("cat", 10.0, 10);
        assertEquals("cat", catActual.name);
        assertEquals(1, catActual.weight);
    }

}