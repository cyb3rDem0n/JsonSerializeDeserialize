import java.util.HashMap;

public class JsonSeDe {

    public static void main(String[] args) {

        // Iterating over values only
        for (Countermeasure value : Countermeasure.jsonManage().values()) {
            System.out.println("Value = " + value);
        }

        //System.out.printf(" " + Countermeasure.jsonManage().size());

        //Countermeasure.jsonManage();
    }
}
