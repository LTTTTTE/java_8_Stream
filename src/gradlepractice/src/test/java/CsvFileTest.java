import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvFileTest {

    private CsvFile file;

    @Before
    public void init() throws IOException {
        file = new CsvFile("src/main/resources/testFile.csv");
    }

    @Test
    public void testInit(){
        assertThat(file).isNotNull();
    }

    @Test
    public void testImportFile(){
        assertThat(file.getColumn().size()).isEqualTo(30);

        file.getColumn().forEach(row->{
            assertThat(row.size()).isEqualTo(3);
        });
    }

    @Test
    public void testToString(){
        System.out.println(file.toString());
    }
}
