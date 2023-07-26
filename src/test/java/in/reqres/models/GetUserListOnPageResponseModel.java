package in.reqres.models;

import lombok.Data;

import java.util.List;

@Data
public class GetUserListOnPageResponseModel {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Data> data;
    private Support support;

    @lombok.Data
    public static class Data {
        private int id;
        private String email, first_name, last_name, avatar;
    }

    @lombok.Data
    public static class Support {
        private String url, text;
    }
}
