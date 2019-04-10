package CSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {

        String csvFile = "country.csv";
        String line = "";
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                //한줄씩 돈다.
                String[] country;
                country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]" + " = "+ country[3]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String csvFile_2 = "stud.csv";


        try(BufferedReader br = new BufferedReader(new FileReader(csvFile_2))) {

            while((line = br.readLine())!=null){
                String[] stud;
                stud = line.split(cvsSplitBy);
                boolean a;
                System.out.print(a = Boolean.parseBoolean(stud[1]));
                if(a) System.out.println(" +GOOD+ ");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
