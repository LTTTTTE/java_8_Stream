import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TestEach {

    private List<String> list;

    @Before
    public void init(){
        list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
    }

    @After
    public void fin(){
        list.clear();
    }

    @Test
    public void test1(){
        assertThat(list).isNotEmpty();
    }

    @Test
    public void test2(){
        assertThat(list).isNotEmpty();
    }
}