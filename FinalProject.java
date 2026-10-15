package WORK.PROJECT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


class Courier {

    private static int nextId = 101;

    private int id;
    private String name;
    private String destination;
    private int weight;
    private int distance;
    private int charge;
    private String status;
   
    public Courier(String name, String destination, int weight, int distance, int charge) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.destination = destination;
        this.weight = weight;
        this.distance = distance;
        this.charge = charge;
        this.status = "PACKAGE CREATED";
    }

   
    public Courier(int id, String name, String destination, int weight, int distance, int charge, String status) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.weight = weight;
        this.distance = distance;
        this.charge = charge;
        this.status = status;
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public int getCharge() {
        return charge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String toFileString() {
        return id + "|" + name + "|" + destination + "|" + weight + "|" + distance + "|" + charge + "|" + status;
    }

    @Override
    public String toString() {
        return "COURIER ID: " + id
                + "\nNAME: " + name
                + "\nDESTINATION: " + destination
                + "\nWEIGHT: " + weight
                + "\nDISTANCE: " + distance
                + "\nSTATUS: " + status
                + "\nCHARGE: " + charge;
    }
}

public class FinalProject {

    static ArrayList<Courier> couriers = new ArrayList<>();
    static final String FILE_NAME = "couriers.txt";

   

    static int readInt(Scanner s, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(s.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("PLEASE ENTER A VALID NUMBER");
            }
        }
    }

    static String readText(Scanner s, String message) {
        String text = "";
        while (text.isEmpty()) {
            System.out.print(message);
            
            text = s.nextLine().trim().replace("|", " ");
            if (text.isEmpty()) {
                System.out.println("THIS CANNOT BE EMPTY");
            }
        }
        return text.toUpperCase();
    }

   

    static int DeliveryCharge(int weight) {
        int charge = 50 + (weight * 10);
        return charge;
    }

    static boolean DeliveryEligibility(int d) {
        return d <= 500;
    }

    static Courier findCourier(int id) {
        return couriers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    static void CreateDelivery(Scanner s) {
        String name = readText(s, "ENTER NAME: ");
        String dest = readText(s, "ENTER DESTINATION: ");
        int weight = readInt(s, "ENTER WEIGHT OF THE PACKAGE: ");
        int dist = readInt(s, "ENTER THE DISTANCE: ");

        if (weight <= 0 || dist <= 0) {
            System.out.println("WEIGHT AND DISTANCE MUST BE MORE THAN 0");
            return;
        }

        if (!DeliveryEligibility(dist)) {
            System.out.println("NOT ELIGIBLE, WE DELIVER ONLY UPTO 500 KM");
            return;
        }

        int charge = DeliveryCharge(weight);
        Courier c = new Courier(name, dest, weight, dist, charge);
        couriers.add(c);
        saveData();

        System.out.println("YOUR PACKAGE ID: " + c.getId());
        System.out.println("COST: " + charge);
    }

    static String CourierTracking(int trackId) {
        Courier c = findCourier(trackId);
        if (c == null) {
            return "ID NOT FOUND";
        }
        return c.getStatus();
    }

    static void UpdateStatus(Scanner s) {
        int id = readInt(s, "ENTER COURIER ID: ");
        Courier c = findCourier(id);

        if (c == null) {
            System.out.println("INVALID ID");
            return;
        }

        System.out.println("-------------------");
        System.out.println("SELECT THE NEW STATUS: ");
        System.out.println("1. IN TRANSIT");
        System.out.println("2. OUT FOR DELIVERY");
        System.out.println("3. DELIVERED");
        System.out.println("-------------------");

        int choice = readInt(s, "ENTER CHOICE: ");

        switch (choice) {
            case 1:
                c.setStatus("IN TRANSIT");
                break;
            case 2:
                c.setStatus("OUT FOR DELIVERY");
                break;
            case 3:
                c.setStatus("DELIVERED");
                break;
            default:
                System.out.println("INVALID OPTION");
                return;
        }
        saveData();
        System.out.println("STATUS UPDATED!");
    }

    static void ViewCouriers() {
        System.out.println("ALL ORDERS");
        for (Courier c : couriers) {
            System.out.println("-----------------");
            System.out.println(c);
            System.out.println("-----------------");
        }
    }

    
    static void ViewByCharge() {
        ArrayList<Courier> sorted = new ArrayList<>(couriers);
        sorted.sort(Comparator.comparingInt(Courier::getCharge).reversed());

        System.out.println("COURIERS BY HIGHEST CHARGE");
        for (Courier c : sorted) {
            System.out.println("ID: " + c.getId() + "  CHARGE: " + c.getCharge() + "  STATUS: " + c.getStatus());
        }
    }

    static void TotalIncome() {
        int total = couriers.stream().mapToInt(Courier::getCharge).sum();
        System.out.println("TOTAL INCOME: " + total);
    }

    static void StatusSummary() {
        Map<String, Long> summary = couriers.stream()
                .collect(Collectors.groupingBy(Courier::getStatus, Collectors.counting()));

        System.out.println("COURIERS PER STATUS");
        summary.forEach((st, count) -> System.out.println(st + ": " + count));
    }

   

    static void saveData() {
        Path path = Paths.get(FILE_NAME);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Courier c : couriers) {
                bw.write(c.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("COULD NOT SAVE DATA: " + e.getMessage());
        }
    }

    static void loadSampleData() {
        couriers.add(new Courier(101, "SAMPLE ONE", "HYDERABAD", 45, 300, 500, "DELIVERED"));
        couriers.add(new Courier(102, "SAMPLE TWO", "CHENNAI", 15, 400, 200, "IN TRANSIT"));
        couriers.add(new Courier(103, "SAMPLE THREE", "PUNE", 51, 200, 560, "OUT FOR DELIVERY"));
        couriers.add(new Courier(104, "SAMPLE FOUR", "MUMBAI", 55, 450, 600, "PACKAGE CREATED"));
        couriers.add(new Courier(105, "SAMPLE FIVE", "BANGALORE", 29, 350, 340, "IN TRANSIT"));
    }

    static void loadData() {
        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            loadSampleData();
            return;
        }

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p.length != 7) {
                    continue; // skip bad lines
                }
                couriers.add(new Courier(Integer.parseInt(p[0]), p[1], p[2],
                        Integer.parseInt(p[3]), Integer.parseInt(p[4]),
                        Integer.parseInt(p[5]), p[6]));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("COULD NOT READ SAVED DATA, USING SAMPLE DATA");
            couriers.clear();
            loadSampleData();
        }
    }

    

    static void customerMenu(Scanner s) {
        boolean back = false;

        while (!back) {
            System.out.println("\nENTER WHICH SERVICE TO AVAIL: ");
            System.out.println("1. CREATE NEW COURIER\n2. TRACK COURIER\n3. BACK TO MAIN MENU");
            int option = readInt(s, "ENTER CHOICE: ");

            switch (option) {
                case 1:
                    CreateDelivery(s);
                    break;
                case 2:
                    int trackId = readInt(s, "ENTER THE ID OF THE PACKAGE: ");
                    System.out.println("STATUS: " + CourierTracking(trackId));
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("INVALID OPTION");
            }
        }
    }

    static void adminMenu(Scanner s) {
        boolean back = false;

        while (!back) {
            System.out.println("\nWELCOME ADMIN!");
            System.out.println("CHOOSE YOUR TASK TODAY: ");
            System.out.println("1. VIEW ALL COURIERS");
            System.out.println("2. UPDATE COURIER STATUS");
            System.out.println("3. VIEW TOTAL REVENUE");
            System.out.println("4. VIEW STATUS SUMMARY");
            System.out.println("5. VIEW COURIERS BY HIGHEST CHARGE");
            System.out.println("6. BACK TO MAIN MENU");
            int option = readInt(s, "ENTER CHOICE: ");

            switch (option) {
                case 1:
                    ViewCouriers();
                    break;
                case 2:
                    UpdateStatus(s);
                    break;
                case 3:
                    TotalIncome();
                    break;
                case 4:
                    StatusSummary();
                    break;
                case 5:
                    ViewByCharge();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("INVALID INPUT");
            }
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        loadData();

        boolean running = true;
        while (running) {
            System.out.println("\nWELCOME TO SM COURIER SERVICE");
            System.out.println("CHOOSE YOUR ROLE");
            System.out.println("----------------");
            System.out.println("1. CUSTOMER");
            System.out.println("2. ADMIN");
            System.out.println("3. EXIT PROGRAM");
            System.out.println("----------------");
            int identity = readInt(s, "ENTER CHOICE: ");

            switch (identity) {
                case 1:
                    customerMenu(s);
                    break;
                case 2:
                    adminMenu(s);
                    break;
                case 3:
                    System.out.println("--------------");
                    System.out.println("THANK YOU FOR CHOOSING SM COURIER SERVICE");
                    System.out.println("--------------");
                    running = false;
                    break;
                default:
                    System.out.println("INVALID INPUT");
            }
        }
        s.close();
    }
}
