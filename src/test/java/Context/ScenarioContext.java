package Context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    //🔷 2️⃣ Scenario Context (Data Sharing Between Steps)

    private Map<String, Object> map = new HashMap<>();

    public Object getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
    //Avoids static variables
    //✅ Parallel-execution safe

}
