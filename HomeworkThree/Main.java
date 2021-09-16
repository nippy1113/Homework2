package HomeworkThree;

import HomeworkThree.Entities.Information;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Gson gson = new Gson();
        Information inf = gson.fromJson(ReadJson("src/HomeworkThree/Json.json"), Information.class);
     //All tests:
        //inf.OutputAllCompanies();                 //Один и тот же метод в классе Information выполняет пункты 1 3 и 4 дз
        //inf.OutputAllCompanies("EU");            //в зависимости от введенной строки
        //inf.OutputAllCompanies("12.12.1995");
        //inf.OutputOverdueSecurities();


    }

    public static JsonObject ReadJson(String jsonFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        while((line = reader.readLine()) != null ) {
            stringBuilder.append(line);
            stringBuilder.append(lineSeparator);
        }
        String s = stringBuilder.toString();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(s);

        return jsonObject;
    }
}
