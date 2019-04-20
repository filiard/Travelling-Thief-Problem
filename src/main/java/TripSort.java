import java.util.Comparator;

public class TripSort implements Comparator<Trip> {

    @Override
    public int compare(Trip o1, Trip o2) {
        if(o1.getFitness()<o2.getFitness()) return 1;
        if(o1.getFitness()>o2.getFitness()) return -1;
        return 0;
    }
}
