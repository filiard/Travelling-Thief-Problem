import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Loader {

    public Loader(String fileName) {
        this.fileName = fileName;
    }

    String fileName;

    ArrayList<City> cities = new ArrayList<>();
    ArrayList<Item> items = new ArrayList<>();

    static int numberOfCities, numberOfItems, knapsackCapacity;

    FileReader fr;

    public ArrayList<City> loadCities() throws IOException {
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            System.exit(1);
        }
        BufferedReader br = new BufferedReader(fr);
        String strLine;
        ArrayList<String> lines = new ArrayList<>();
        try {
            while ((strLine = br.readLine()) != null) {
                String lastWord = strLine.substring(strLine.lastIndexOf(" ") + 1);
                lines.add(lastWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] parts = lines.get(2).split("\t");
        numberOfCities = Integer.parseInt(parts[1]);

        for (int i = 0; i < numberOfCities; i++) {
            parts = lines.get(10 + i).split("\t");
            int id = (int) Double.parseDouble(parts[0]);
            int x = (int) Double.parseDouble(parts[1]);
            int y = (int) Double.parseDouble(parts[2]);
            cities.add(new City(id, x, y));

        }
        return cities;

    }

    public ArrayList loadItems() throws IOException {
        try {
            fr = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
            System.exit(1);
        }
        BufferedReader br = new BufferedReader(fr);
        String strLine;
        ArrayList<String> lines = new ArrayList<>();
        try {
            while ((strLine = br.readLine()) != null) {
                String lastWord = strLine.substring(strLine.lastIndexOf(" ") + 1);
                lines.add(lastWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] parts = lines.get(3).split("\t");
        numberOfItems = Integer.parseInt(parts[1]);

        parts = lines.get(4).split("\t");
        knapsackCapacity = Integer.parseInt(parts[1]);

        for (int i = 0; i < numberOfItems; i++) {
            parts = lines.get(11 + numberOfCities + i).split("\t");
            int id = (int) Double.parseDouble(parts[0]);
            int profit = (int) Double.parseDouble(parts[1]);
            int weight = (int) Double.parseDouble(parts[2]);
            int node = (int) Double.parseDouble(parts[3]);
            items.add(new Item(id, profit, weight, node));

        }
        return items;
    }

    public void setCities(){

        for (City currentCity:cities)
        {
//            System.out.println(items.get(5));
//            currentCity.items.add(new Item(1,2, 3, currentCity.getId()));

            ArrayList<Item> itemsInCity = new ArrayList<>();
            for(int currentItemId=0;currentItemId<items.size();currentItemId++){
                Item currentItem = items.get(currentItemId);
                if(currentItem.nodeNumber==currentCity.getId()){
                    itemsInCity.add(currentItem);
                }
            }
            currentCity.items=itemsInCity;

        }
    }


}
