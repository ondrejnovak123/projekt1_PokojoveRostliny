import java.time.LocalDate;

public class Plant implements Comparable<Plant>{
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name     = name;
        this.notes    = notes;
        this.planted  = planted;

        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException {
        this.name     = name;
        this.notes    = "";
        this.planted  = LocalDate.now();
        this.watering = LocalDate.now();

        setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name) {
        this.name     = name;
        this.notes    = "";
        this.planted  = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }

    public String exportToString(String delimiter) {
        return this.name + delimiter +
                this.notes + delimiter +
                this.frequencyOfWatering + delimiter +
                this.watering + delimiter +
                this.planted;
    }

    public static Plant parsePlant(String record) throws PlantException {
        String[] items = record.split("\t");
        String name = items[0];
        String note = items[1];
        int freq = Integer.parseInt(items[2]);
        LocalDate watering = LocalDate.parse(items[3]);
        LocalDate planted = LocalDate.parse(items[4]);

        return new Plant(name, note, planted, watering, freq);
    }

    public String getWateringInfo(){
        return "Název: " + this.name + ", Poslední zálivka: " + this.planted +
                ", Datum doporučené další zálivky: " + recommendedDateOfWatering();
    }

    public LocalDate recommendedDateOfWatering(){
        return this.watering.plusDays(this.frequencyOfWatering);
    }

    public void doWateringNow(){
        this.watering = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(this.planted)) {
            throw new PlantException("Zalití nesmí být před datem zasazení.");
        }
        else {
            this.watering = watering;
        }
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering > 0) {
            this.frequencyOfWatering = frequencyOfWatering;
        }
        else {
            throw new PlantException("Frekvence nesmí nabývat záporných a nulových hodnot.");
        }

    }

    @Override
    public int compareTo(Plant o) {
        return this.name.compareTo(o.name);
    }
}

