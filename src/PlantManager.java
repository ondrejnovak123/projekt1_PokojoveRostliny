import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PlantManager {
    private List<Plant> plants;

    public void loadFromFile(String fileName){
        plants.clear();
        try (Scanner scanner = new Scanner(
                new BufferedReader(new FileReader(fileName)))
        ) {
            while (scanner.hasNextLine()) {
                String record = scanner.nextLine();
                this.plants.add(Plant.parsePlant(record));
            }
        } catch (IOException ex) {
            System.err.println("Nepodařilo se načíst soubor!"+
                    ex.getLocalizedMessage());
        } catch (PlantException e) {
            System.err.println("Nepodařilo se načíst kytku!"+
                    e.getLocalizedMessage());
        }
    }

    public  void saveToFile(String fileName){
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(fileName))
        ) {

            for (Plant plant : plants) {
                writer.println(plant.exportToString("\t"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void sortByNameWatering(){
        this.plants.sort(Comparator.comparing(Plant::getName).thenComparing(Plant::getWatering));
    }

    public void addPlant(Plant plant){
        this.plants.add(plant);
    }

    public Plant getPlant(int Index){
        return this.plants.get(Index);
    }

    public void removePlant(int Index) {
        this.plants.remove(Index);
    }

    public List<Plant> getCopy(){
        return new ArrayList<>(this.plants);
    }

    public List<String> getPlantsForWatering(){
        List<String> plants1 = new ArrayList<>();
        for (Plant plant : this.plants) {
            if (!plant.getWatering().isAfter(plant.recommendedDateOfWatering())) {
                plants1.add(plant.getName());
            }
        }
        return plants1;
    }

    public PlantManager(List<Plant> plants) {
        this.plants = plants;
    }

    public PlantManager() {
        this.plants = new ArrayList<>();
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
