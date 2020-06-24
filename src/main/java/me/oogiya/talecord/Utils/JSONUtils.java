package me.oogiya.talecord.Utils;

import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

	public static <T> void forEach(JSONArray array, Consumer<JSONObject> consumer) {
		
		for (int i = 0; i < array.length(); i++) {
			try 
			{
				JSONObject json = array.getJSONObject(i);
				consumer.accept(json);
				
			} catch (JSONException ex) {
				throw new InternalError("forEach json fail");
			}
		}
		
	}
	
	public static <T> T extractJSON(JSONObject json, String key, Class<T> type, T defaultValue) {
		
		Object value;
		try {
			value = json.get(key);
			return type.cast(value);
		} catch (Exception ex) {
			return defaultValue;
		}
		
		
	}
	
	public static JSONArray extractMessages(JSONObject event) {
		
		Object messageField;
		try {
			messageField = event.get("message");
			
			if (messageField instanceof JSONArray) {
				return extractJSON(event, "message", JSONArray.class, new JSONArray());
			}
			
			else if (messageField instanceof JSONObject) {
				return new JSONArray().put(messageField);
			}
			
			return null;
			
		} catch (JSONException e) {
			return null;
		}
		

		
	}
	
}
