package in.reqres.utils;

import java.util.HashMap;
import java.util.Map;
import in.reqres.models.*;

public class TestData {
    RandomUtils random = new RandomUtils();
    public PostUserBodyModel createData = new PostUserBodyModel();
    {
        createData.setName(random.getRandomUserName());
        createData.setJob(random.getRandomUserName());
    }

    public PostUserBodyModel updateData = new PostUserBodyModel();
    {
        updateData.setName(random.getRandomUserName());
        updateData.setJob(random.getRandomJob());
    }


    public String getUserId() {
        return "2";
    }

    public String getNonExistedUserId() {
        return "23";
    }

    public String getPage() {
        return "1";
    }

    public Map<String,String> user_get_data = new HashMap<>();
    {
        user_get_data.put("id", "2");
        user_get_data.put("email", "janet.weaver@reqres.in");
        user_get_data.put("first_name", "Janet");
        user_get_data.put("last_name", "Weaver");
        user_get_data.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
    }

    public Map<String,String> user_list_data = new HashMap<>();
    {
        user_list_data.put("page", "1");
        user_list_data.put("per_page", "6");
        user_list_data.put("total", "12");
        user_list_data.put("total_pages", "2");
    }







}
