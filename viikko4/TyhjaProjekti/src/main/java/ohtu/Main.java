
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map.Entry;
import ohtu.Submission;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2016.herokuapp.com/students/" + studentNr + "/submissions";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        System.out.println(String.format("opiskelijanumero %s\n", studentNr));

        Gson mapper = new Gson();
        JsonElement[] objs = mapper.fromJson(bodyText, JsonElement[].class);
        Submission[] subs = new Submission[objs.length];
        
        int totalHours = 0;
        int totalTasks = 0;
        
        for (JsonElement jsonElem : objs) {
            Submission sub = new Submission();
            JsonObject obj = jsonElem.getAsJsonObject();
            sub.setStudent_number(studentNr);
            sub.setWeek(obj.get("week").getAsInt());
            sub.setHours(obj.get("hours").getAsInt());
            
            ArrayList<Integer> tasksDone = new ArrayList<Integer>();
            
            for (Entry<String, JsonElement> entry : obj.entrySet()) {
                String key = entry.getKey().replace("a", "");
                int num = -1;
                try {
                    num = Integer.parseInt(key);
                } catch (NumberFormatException e) {
                    continue;
                }
                
                if (!entry.getValue().isJsonNull() && entry.getValue().getAsBoolean()) {
                    tasksDone.add(num);
                }
            }
        
            sub.setDoneTasks(tasksDone);
            
            totalHours += sub.getHours();
            totalTasks += sub.getDoneTasks().size();
            
            System.out.println(sub);
        }
        
        System.out.println(String.format("\nyhteensä: %d tehtävää %d tuntia", totalTasks, totalHours));

    }
}
