// Array list
// Collection framework
import java.util.ArrayList;
import java.util.List;

   // Collection adalah suatu objek yang bisa digunakan untuk menyimpan sekumpulan objek.
   public class ListApp{
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();

        strings.add("Program");
        strings.add("Java");
        strings.add("PBO");

        strings.set(0, "Bahasa");

        strings.remove(1);
        
        System.out.println(strings.get(0));
        System.out.println(strings.get(1));
        System.out.println(strings.get(2));

        
    }
}