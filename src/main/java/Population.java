public class Population {
    Trip[] trips;

    public Population(int populationSize, boolean initialize) {
        trips=new Trip[populationSize];
        if(initialize){
            for (int i=0; i<populationSize(); i++){
                Trip newTrip=new Trip();
                newTrip.generateIndiviual();
                saveTrip(i, newTrip);
            }
        }
    }

    public void saveTrip(int index, Trip trip){
        trips[index]=trip;
    }

    public Trip getTrip(int index){
        return trips[index];
    }

    public int populationSize(){
        return trips.length;
    }

    public Trip getBest(){
        Trip best = trips[0];
        for (int i=1; i<populationSize(); i++){
            if (best.getFitness() <= getTrip(i).getFitness()){
                best=getTrip(i);
            }
        }
        return best;
    }

    public Trip getWorst(){
        Trip worst = trips[0];
        for(int i=1;i<populationSize();i++){
            if(worst.getFitness()>=getTrip(i).getFitness()){
                worst=getTrip(i);
            }
        }
        return worst;
    }

    public double getAverage(){
        double totalFitness=0;
        double averageFitness;
        for(int i=0;i<populationSize();i++){
            totalFitness+=getTrip(i).getFitness();
        }
        averageFitness=totalFitness/populationSize();
        return averageFitness;
    }

}
