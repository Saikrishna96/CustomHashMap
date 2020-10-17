public class Main {

    public static void main(String[] args){
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("1", "sai");
        map.put("2", "krish");
        System.out.println(map.get("2"));
        System.out.println(map.get("1"));
        map.put("3", "yooo");
        System.out.println(map.get("3"));
    }
}
