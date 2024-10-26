public class Main {
    public static void main(String[] args) throws PlantException {
        PlantManager plantMngr = new PlantManager();
        plantMngr.loadFromFile("kvetiny.txt");

        System.out.println(plantMngr.getPlantsForWatering());

        Plant plant = new Plant("Kaktus");
        plantMngr.addPlant(plant);

        for (int i = 0; i < 10; i ++){
            Plant plant1 = new Plant("Tulipán na prodej " + Integer.toString(i+1), 14);
            plantMngr.addPlant(plant1);
        }

        plantMngr.removePlant(2);

        plantMngr.saveToFile("test.txt");
        plantMngr.loadFromFile("test.txt");

        plantMngr.sortByNameWatering();
        System.out.println(plantMngr.getPlantsForWatering());
    }
}