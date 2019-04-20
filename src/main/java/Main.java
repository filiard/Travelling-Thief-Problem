import org.jfree.ui.RefineryUtilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
    public static void main(String args[])throws IOException {
        long currentTime = System.currentTimeMillis();
        String filename = "hard_4.ttp";
        String results = filename+"_results_brute.csv";
        Loader l = new Loader(filename);
        Chart chart = new Chart("Tytul 1", "Tytul 2");


        TripManager tm = new TripManager(filename);
        tm.destinationCities=l.loadCities();
        tm.itemsInCities=l.loadItems();
        l.setCities();

        Population pop = new Population(500, true);          ///   POP SIZE   ///
        System.out.println("Plik "+filename);
        //System.out.println("Poczatkowa dlugosc trasy: "+pop.getBest().getDistance());

        try(PrintWriter writer = new PrintWriter(new File(results))) {
            StringBuilder sb = new StringBuilder();
            System.out.println("pokolenie, najslabszy, sredni, najlepszy");
            //sb.append("pokolenie, najslabszy, sredni, najlepszy\n");
            for (int i = 0; i < 1000; i++) {                                    ///   POKOLENIA   ///
                pop = GA.evolvePopulation(pop);
                //sb.append((i + 1) + "," + pop.getWorst().getFitness() + "," + pop.getAverage() + "," + pop.getBest().getFitness()+"\n");
                // System.out.println((i + 1) + "," + pop.getWorst().getFitness() + "," + pop.getAverage() + "," + pop.getBest().getFitness());
                sb.append(pop.getBest().getFitness()+"\n");
                System.out.println((i + 1)+"," +pop.getBest().getFitness());



                //System.out.println(pop.getBest().getTime() +" - "+pop.getBest().getDistance()+" - "+   pop.getBest().totalItemsValue+" - "+pop.getBest().getFitness());
                //System.out.println(String.format("%.2f", pop.getBest().getFitness()));
            }
            writer.write(sb.toString());
        }
        long timeExecuted = System.currentTimeMillis();

        System.out.println("Najlepszy wynik dla "+filename+" : "+pop.getBest().getFitness());

        long duration = timeExecuted-currentTime;
        System.out.println("Czas wykonywania: "+(float)duration/1000+" sek.");
        System.out.println("brute");
        //System.out.println("Rozwiazanie: ");
        //System.out.println(pop.getBest());

//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);


    }
}
