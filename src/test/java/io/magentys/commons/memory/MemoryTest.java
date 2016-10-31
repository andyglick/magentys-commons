package io.magentys.commons.memory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.magentys.commons.typemap.TypedKey;

public class MemoryTest {

    private Memory memory;

    @Before
    public void setUp() {
        memory = new Memory();
    }

    @Test
    public void recallingGenericTypes() {
        List<String> names = new ArrayList<>();
        TypedKey<List<String>> key = new TypedKey<>("names");
        names.add("Kevin");
        memory.commit(key, names);
        assertEquals(memory.recall(key), names);
    }

    @Test
    public void returningADefaultValue() {
        TypedKey<Boolean> key = new TypedKey<>("bool");
        assertTrue(memory.recall(key, true));
    }
}
