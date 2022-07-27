package mentorship.reactivesport.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Attributes {
    public String name;
    public String description;
    public Object parent_id;
    public int decathlon_id;
    public String slug;
    public String locale;
    public ArrayList<Object> weather;
    public String icon;
}