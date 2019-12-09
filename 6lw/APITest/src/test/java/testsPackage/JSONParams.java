package testsPackage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class JSONParams {
    private JSONObject obj = new JSONObject();
    JSONParams() {
        obj.put("category_id", 1);
        obj.put("title", "test 777");
        obj.put("content", "definitely content");
        obj.put("price", 777);
        obj.put("old_price", 666);
        obj.put("status", 1);
        obj.put("keywords", "777");
        obj.put("description", "huge text");
        obj.put("hit", 1);
    }

    void ChangeValue(String keyToChange, String valueToChange) {
        obj.remove(keyToChange);
        obj.put(keyToChange, valueToChange);
    }

    void PutArrayInKeys() {
        obj.put("keywords", "key1, key2");
    }

    void ChangeValue(String keyToChange, int valueToChange) {
        obj.remove(keyToChange);
        obj.put(keyToChange, valueToChange);
    }

    JSONObject GetParams() {
        return obj;
    }
}
