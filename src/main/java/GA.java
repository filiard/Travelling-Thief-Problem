import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GA {

    private static final double mutationProb = 0.01;
    private static final int tourSize = 20;


    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        newPopulation.saveTrip(0, pop.getBest());

        for (int i = 1; i < newPopulation.populationSize(); i++) {
            Trip parent1 = tournamentSelection(pop);                              ////     RULETKA     ///
            Trip parent2 = tournamentSelection(pop);                              ////     RULETKA     ///
            Trip child = crossoverOX(parent1, parent2);                         ////   CROSSOWANIE   ///
            newPopulation.saveTrip(i, child);
        }
        for (int i = 1; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getTrip(i));
        }
        return newPopulation;

    }

    private static Trip crossoverOX(Trip parent1, Trip parent2) {
        Trip child = new Trip();

        int startPos = (int) (Math.random() * parent1.tripSize());
        int endPos = (int) (Math.random() * parent1.tripSize());

        for (int i = 0; i < child.tripSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        for (int i = 0; i < parent2.tripSize(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int j = 0; j < child.tripSize(); j++) {
                    if (child.getCity(j) == null) {
                        child.setCity(j, parent2.getCity(i));
                        break;
                    }
                }
            }
        }

        return child;
    }

    private static Trip crossoverCX(Trip parent1, Trip parent2) {
        Trip child = new Trip();
        ArrayList<City> genome1 = parent1.getGenome();
        ArrayList<City> genome2 = parent2.getGenome();


        ArrayList<City> childGenome = child.getGenome();
        ArrayList<City> inCycle = new ArrayList();
        for (int i = 0; i < parent1.tripSize(); i++) {
            if (!inCycle.contains(genome2.get(i))) {
                ArrayList tempCycle = new ArrayList();
                City start = genome1.get(i);
                City temp = genome2.get(genome1.indexOf(start));
                tempCycle.add(start);
                tempCycle.add(temp);
                inCycle.add(temp);
                while (!temp.equals(start)) {
                    tempCycle.add(genome2.get(genome1.indexOf(temp)));
                    temp = genome2.get(genome1.indexOf(temp));
                    inCycle.add(temp);
                }
                tempCycle.remove(tempCycle.size() - 1);
                childGenome.addAll(tempCycle);

            }
        }
        childGenome.removeAll(Collections.singletonList(null));
        child.trip = childGenome;


        //KURWA MAĆ


        return child;
    }


    private static void mutate(Trip trip) {
        for (int tripPos1 = 0; tripPos1 < trip.tripSize(); tripPos1++) {
            if (Math.random() < mutationProb) {
                int tripPos2 = (int) (trip.tripSize() * Math.random());
                City city1 = trip.getCity(tripPos1);
                City city2 = trip.getCity(tripPos2);
                trip.setCity(tripPos2, city1);
                trip.setCity(tripPos1, city2);
            }
        }
    }

    private static Trip tournamentSelection(Population pop) {
        Population tournament = new Population(tourSize, false);

        for (int i = 0; i < tourSize; i++) {
            int randomId = (int) (Math.random() * pop.populationSize());
            tournament.saveTrip(i, pop.getTrip(randomId));
        }
        Trip best = tournament.getBest();
        return best;
    }


    private static Trip rouletteSelection (Population population) {
        ArrayList<Trip> pop = new ArrayList(Arrays.asList(population.trips));
        ArrayList<Double> probabilities = new ArrayList<>();
        double totalFitness = 0;
        double rnd;
        int index=0;
        Collections.sort(pop, new TripSort());

        for (int i = 0; i < population.populationSize(); i++) {
            Trip curr = population.getTrip(i);
            double currfitt = Math.exp(curr.getFitness() / 100000);
            totalFitness += currfitt;
        }

        for (int i = 0; i < population.populationSize(); i++) {
            Trip curr = population.getTrip(i);
            probabilities.add((Math.exp(curr.getFitness() / 100000)) / totalFitness);
        }

        Collections.sort(probabilities, Collections.reverseOrder());
            rnd = Math.random();
            double increment=0;

            while(increment<rnd)
            {

                increment+=probabilities.get(index);
                index++;
            }
;
        Trip best =pop.get(index-1);
        return best;


    }


}



