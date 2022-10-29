package persistence;

import org.json.JSONObject;

//Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.
// EFFECTS: returns this as JSON object
public interface Writable {

    JSONObject toJson();
}
