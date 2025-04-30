import java.util.ArrayList;
import java.util.HashMap;
class Record{
    int age;
    String name;
    String title;
    Record(String name, String title, int age){
        this.name = name;
        this.title = title;
        this.age = age;
    }
    public String toString(){
        return String.format("%s, %s %d", name,title,age);
    }
}
public class Main {
    public static void main(String[] args) {
        //THIS ONLY SIMULATE VALIDATING ONE RECORD
        ArrayList<Record> list = new ArrayList<>();
        list.add(new Record("kevin","geek", 57));
        list.add(new Record("nina","hero", 57));

        HashMap<String,String> filterMap = new HashMap<>();

        filterMap.put("name","");
        filterMap.put("title",""); //ignore if blank
        filterMap.put("age","57");

        for(Record r : list) {
            boolean match = true;

            //APPLY SOME IF NOT BLANK
            for (String k : filterMap.keySet()) { //loop through each *potential* filter
                String v = filterMap.get(k);
                if (v.isEmpty()) {
                    System.out.printf("ignoring blank value for '%s'.\n", k);
                } else {
                    match = validateKVR(k, v, r);
                    if (!match) {
                        break;
                    }
                }
            }
            if (match) {
                System.out.println(r);
            } else {
                System.out.printf("skipping item: '%s'\n", r);
            }
        }
    }
    static boolean validateKVR(String k, String v, Record record){
        System.out.printf("applying filter for key '%s' and value '%s'.\n", k, v);
        if("name".equalsIgnoreCase(k)){
            if(!isValidName(v,record)){
                return false;
            }
        }
        if ("title".equalsIgnoreCase(k)) {
            if(!isValidTitle(v,record)){ //WILL BE IGNORED AS FILTER IS BLANK
                return false;
            }
        }
        if ("age".equalsIgnoreCase(k)) {
            if(!isValidAge(v,record)){
                return false;
            }
        }
        return true;
    }
    static boolean isValidName(String v, Record record){
        return v.equals(record.name);
    }
    static boolean isValidTitle(String v, Record record){
        return v.equals(record.title);
    }
    static boolean isValidAge(String v, Record record){
        return Integer.parseInt(v) == record.age;
    }
}