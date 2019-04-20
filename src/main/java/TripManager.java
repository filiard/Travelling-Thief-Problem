import java.io.IOException;
import java.util.ArrayList;

public class TripManager {

    public static ArrayList destinationCities = new ArrayList<City>();
    public static ArrayList itemsInCities = new ArrayList<Item>();

    public TripManager(String filename) throws IOException {
        Loader loader = new Loader(filename);
        destinationCities=loader.loadCities();
        //loader.placeItems((City)destinationCities.get(2));

    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}