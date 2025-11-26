package framework;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONTest {

    @Test(dataProvider = "data")
    public void testReadJSONasDataProvider(HashMap<String, String> data){

        System.out.println(data.get("name"));
        System.out.println(data.get("surname"));
        System.out.println(data.get("city"));
        System.out.println(data.get("age"));
    }

    @DataProvider
    public Object[][] data() throws IOException {
        List<HashMap<String,String>> list = getDataFromJSON();
        System.out.println(list.size());
        return new Object[][]{{list.get(0)}, {list.get(1)}};
    }

    public List<HashMap<String, String>> getDataFromJSON() throws IOException {
        String json = FileUtils.readFileToString(
                new File(System.getProperty("user.dir") + "//src//main//java//resources//Test.json")
                , StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> list = mapper.readValue(json, new TypeReference<List<HashMap<String, String>>>() {
        });
        return list;
    }
}
