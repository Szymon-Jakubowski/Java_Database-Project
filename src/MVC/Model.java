package MVC;
import com.company.Pracownik;

import java.io.Serializable;
import java.util.*;

public class Model implements Serializable {
    ArrayList<Pracownik> list = new ArrayList<>();

    Model(){
        list.add(new Pracownik() {
            @Override
            public void wyswietl() {
                System.out.println("yes");
            }
        });
        list.remove(0);
    }
}
