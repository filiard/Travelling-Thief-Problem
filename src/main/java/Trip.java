import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Trip{
    public ArrayList<City> trip=new ArrayList<>();
    private double fitness=0;
    private double distance=0;
    private double time =0;
    int totalItemsValue = 0;
    int knapsackCapacity = Loader.knapsackCapacity;
    private double currentVelocity=1;

    public Trip(){
        for (int i=0;i<Loader.numberOfCities;i++){
            trip.add(null);
        }
    }
    public Trip(ArrayList trip){
        this.trip=trip;
    }

    public void generateIndiviual(){

        for (int cityIndex=0; cityIndex<Loader.numberOfCities;cityIndex++){
            setCity(cityIndex, TripManager.getCity(cityIndex));
        }
        Collections.shuffle(trip);

    }

    public City getCity(int tripPosition){
        return trip.get(tripPosition);
    }

    public void setCity (int tripPosition, City city){
        trip.set(tripPosition, city);
        fitness=0;
        distance=0;
    }

    public double getFitness(){

            double time = getTime();
            fitness=(double)totalItemsValue-time;

        return fitness;
    }

    public double getDistance(){
        if (distance==0){
            double tripDistance=0;
            for(int cityIndex=0; cityIndex<tripSize(); cityIndex++) ///ew poprawic to
            {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                if(cityIndex+1<tripSize())
                {
                    destinationCity=getCity(cityIndex+1);
                } else{
                    destinationCity=getCity(0);
                }
                tripDistance +=fromCity.distanceTo(destinationCity);
            }
            distance = tripDistance;
        }
        return distance;
    }

    public double getTime(){
        if (distance==0 && time==0){
            Item takenItem;
            for(int cityIndex=0; cityIndex<tripSize(); cityIndex++)
            {
                City fromCity = getCity(cityIndex);
                City destinationCity;
                if(cityIndex+1<tripSize())
                {
                    destinationCity=getCity(cityIndex+1);
                } else{
                    destinationCity=getCity(0);
                }

                if(fromCity.items.size()!=0){
                    takenItem=fromCity.findBestRatioItem();
                    if(takenItem.weigth<knapsackCapacity){
                        knapsackCapacity-=takenItem.weigth;
                        totalItemsValue+=takenItem.profit;
                    }

                }
                double distanceToNextCity = fromCity.distanceTo(destinationCity);
                double timeToNextCity;
                currentVelocity=(-0.9/(Loader.knapsackCapacity))*(Loader.knapsackCapacity-knapsackCapacity)+1;       //TO TUTAJ JEST ZJEBANE
                timeToNextCity=distanceToNextCity/currentVelocity;
                time+=timeToNextCity;

            }

        }
        return time;

    }

    public ArrayList getGenome(){
        return trip;
    }

    public int tripSize(){
        return  trip.size();
    }

    public boolean containsCity(City city){ return trip.contains(city); }

    @Override
    public String toString(){
        String geneString = "ID, X, Y\n";
        for (int i=0;i<tripSize();i++){
            geneString+=getCity(i)+"\n";
        }
        return geneString;
    }

}
