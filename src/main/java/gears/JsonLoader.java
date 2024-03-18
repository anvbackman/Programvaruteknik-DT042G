package gears;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.Set;

/**
 * This class is used to load the JSON file and get the values from it.
 * @author Andreas Backman
 */
public class JsonLoader {
    private JSONObject jsonObject;

    /**
     * Constructor for the JsonLoader class
     * @param path The path to the JSON file
     */
    public JsonLoader(String path) {
        // Create a JSONParser and a FileReader
        JSONParser parser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader(path); // Read the file
            Object obj = parser.parse(reader);  // Parse the file
            jsonObject = (JSONObject) obj; // Cast the object to a JSONObject
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close(); // Close the reader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get the categories from the JSON file
     * @return A keyset of categories
     */
    public Set<String> getCategories() {
        return jsonObject.keySet();
    }

    /**
     * Get the types from a specific category
     * @param category The category to get the types from
     * @return A keyset of types
     */
    public Set<String> getTypes(String category) {
        JSONObject typeObject = (JSONObject) jsonObject.get(category);
        if (typeObject != null) {
            System.out.println(typeObject.keySet());
            return typeObject.keySet();
        }
        else {
            return null;
        }
    }

    /**
     * Get the value of a specific type and category
     * @param category The category to get the value from
     * @param name The name of the type
     * @return The value of the type
     */
    public int getValue(String category, String name) {
        JSONObject typeObject = (JSONObject) jsonObject.get(category); // Get the category
        if (typeObject != null) {
            Long value = (Long) typeObject.get(name); // Get the value of the type
            if (value != null) {
                return value.intValue(); // Return the value
            }
        }
        return 0;
    }
}