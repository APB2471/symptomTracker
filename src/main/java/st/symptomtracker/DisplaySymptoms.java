package st.symptomtracker;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Call methods from ReceiveFromDB to get
 * an arraylist of Symptom objects, then
 * construct graphs using JFreeChart.
 */
public class DisplaySymptoms extends JFrame {

    public DisplaySymptoms() {
        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);

        add(chartPanel);

        pack();
        setTitle("Symptom Chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {
        // Call receiveFromDB methods here to get data. Format the data to an arraylist in some method
        ArrayList<Symptom> symptomsList = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mma");
        String onset = "10/22/2021 10:30PM";
        LocalDateTime date = LocalDateTime.parse(onset, format);
        Symptom cough = new Symptom("cough", date, 3);
        symptomsList.add(cough);
        var dataset = new XYSeriesCollection();
        /*
        HashMap<Symptom, ArrayList<Integer>> symptoms = convertToDict(symptomsList);
        for (Map.Entry<Symptom, ArrayList<Integer>> entry : symptoms.entrySet()) { //Loop through the symptoms in the dictionary;
            var series = new XYSeries(entry.getKey().getName());
            for(Integer timeAfterOnset : entry.getValue()) {
                series.add(entry.getKey().getSeverity(), timeAfterOnset);
            }
            dataset.addSeries(series);
        }
         */

        var series = new XYSeries(cough.getName());
        series.add(0, cough.getSeverity());
        series.add(1, cough.getSeverity() + 2);
        series.add(2, cough.getSeverity() + 4);
        series.add(3, cough.getSeverity());
        series.add(4, 1);
        series.add(5, 0);
        dataset.addSeries(series);
        return dataset;
    }

    private HashMap<Symptom, ArrayList<Integer>> convertToDict(ArrayList<Symptom> symptomsList) {
        HashMap<Symptom, LocalDateTime> earliestDate = new HashMap<>(); // Earliest date for each symptom
        HashMap<Symptom, ArrayList<Integer>> symptomsMap = new HashMap<>(); //Hashmap of all the symptoms and their dates of occurrence.

        for(Symptom symptom : symptomsList) {
            LocalDateTime onset = symptom.getOnset();
            if (!symptomsMap.containsKey(symptom)) {
                ArrayList<Integer> timeAfterOnset = new ArrayList<>();
                timeAfterOnset.add(0);
                earliestDate.put(symptom, onset);
                symptomsMap.put(symptom, timeAfterOnset);
            } else {
                LocalDateTime earliestOccurrence = earliestDate.get(symptom);
                int compareValue = earliestOccurrence.compareTo(onset);
                if (compareValue < 0) { //If the new date is earlier than the other date,
                    // need to reset the earliest date and all the values in the symptomsMap ArrayList.
                    earliestDate.put(symptom, onset);
                    for (int timeDiff : symptomsMap.get(symptom)) {
                        timeDiff = compareValue; //TO:DO Need to make this actually update the values in the arraylist.
                    }
                    symptomsMap.get(symptom).add(compareValue);

                } else {
                    symptomsMap.get(symptom).add(compareValue); // Adds the integer representing how many days have passed since the first occurrence.
                }

            }
        }
        return symptomsMap;
    }

    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Recorded Symptoms over time",
                "Day",
                "Severity",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Recorded symptoms over time",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }
}
