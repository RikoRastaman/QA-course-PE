package testsPackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class  JSONParams {

    public static JSONObject Params() {
        JSONObject obj = new JSONObject();
        obj.put("category_id", 1);
        obj.put("title", "test 777");
        obj.put("content", "definitely content");
        obj.put("price", 777);
        obj.put("old_price", 666);
        obj.put("status", 1);
        obj.put("keywords", "777");
        obj.put("description", "huge text");
        obj.put("hit", 1);
        return obj;
    }
}
