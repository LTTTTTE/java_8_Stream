import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CsvFile {

    private String filePath;
    List<List<String>> column;

    public CsvFile(String filePath) throws IOException {
        this.filePath = filePath;
        column = null;
        importFile(filePath);
    }

    public void importFile(String filePath) throws IOException {
        column = Files.lines(Paths.get(filePath))
                .map(x -> Arrays.asList(x.split(",")))
                .collect(Collectors.toList());
    }

    public String toString(){
        String str = "[";
        column.forEach(row->{
           row.stream().collect(Collectors.joining(","));
        });
        return str + "]";
    }

}
