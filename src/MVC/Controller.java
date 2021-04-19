package MVC;

import com.company.Dyrektor;
import com.company.Handlowiec;
import com.company.Pracownik;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Controller {
    Model model = new Model();
    View view = new View();

    boolean exitProgram = false;
    boolean exitList = false;

    public void runMenu() throws Exception {
        do{
            view.printMenu();
            int choice = getInput();
            System.out.println("==============================");
            performChoice(choice);
        }while(!exitProgram);
    }

    private void performChoice(int choice) throws Exception {
        switch(choice){
            case 0:
                exitProgram = true;
                System.out.println("Dziekuje, do zobaczenia");
                break;
            case 1:
                if(model.list.size() > 0) {
                    System.out.println("1. Lista pracownikow\n");
                    listaP();
                }else{
                    System.out.println("Lista jest pusta");
                }
                break;
            case 2:
                System.out.println("2. Dodaj pracownika\n");
                dodajP();
                break;
            case 3:
                if(model.list.size() > 0) {
                    System.out.println("3. Usun pracownika\n");
                    usunP();
                }else{
                    System.out.println("Lista jest pusta");
                }
                break;
            case 4:
                System.out.println("4. Kopia Zapasowa\n");
                kopia();
                break;
            default:
                System.out.println("An unknown error has occured.");
        }
    }

    private int getInput(){
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        while(choice<0 || choice > 4){
            try {
                System.out.print("Wybor: ");
                choice = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.out.println("Nie ma takiej opcji");
            }
        }
        return choice;
    }

    private void listaP(){
        exitList = false;
        Scanner sc = new Scanner(System.in);
        String choice = "0";

        int size = model.list.size();
        int i = 0;

        do{
            model.list.get(i).wyswietl();
            System.out.println("\n");
            System.out.println("[Pozycja: " + (i+1) +"/" + size + "]");
            System.out.println("[Enter/E] - nastepny");
            System.out.println("[Q] - powrót");
            choice = sc.nextLine();
            switch(choice){
                case "E":
                    if(i < size) i++;
                    break;
                case "Q":
                    exitList=true;
                    break;
            }
        }while(!exitList);
    }

    private void dodajP() throws Exception {
        Scanner sc = new Scanner(System.in);
        String choice = "0";


        System.out.print("[D]yrektor/[H]handlowiec: ");
        choice = sc.nextLine();
        switch(choice){
            case "D":
                Dyrektor dyrektor = new Dyrektor();
                dyrektor.wprowadzDane();
                System.out.println("Length: " + model.list.size());
                model.list.add(dyrektor);
                System.out.println("Length: " + model.list.size());
                break;
            case "H":
                Handlowiec handlowiec = new Handlowiec();
                handlowiec.wprowadzDane();
                model.list.add(handlowiec);
                break;
            default:
                System.out.println("Brak takiego typu pracownika");
                break;
        }
    }

    private void usunP() {
        Scanner sc = new Scanner(System.in);
        String choice = "0";

        int size = model.list.size();
        int i = 0;

        do{
            model.list.get(i).wyswietl();
            System.out.println("\n");
            System.out.println("[Pozycja: " + (i+1) +"/" + size + "]");
            System.out.println("[Enter/E] - nastepny");
            System.out.println("[P] - potwierdz");
            System.out.println("[Q] - powrót");
            choice = sc.nextLine();
            switch(choice){
                case "E":
                    if(i+1 < size) i++;
                    break;
                case "Q":
                    exitList=true;
                    break;
                case "P":
                    exitList=true;
                    model.list.remove(i);
                    break;
            }
        }while(!exitList);

    }

    private void kopia() throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        String choice = "0";
        System.out.print("[Z]achowaj/[O]dtwórz: ");
        choice = sc.nextLine();
        System.out.println("------------------------------------");
        switch(choice){
            case "Z":
                copy();
                System.out.println("Zapisano");
                break;
            case "O":
                load();
                System.out.println("Odtworzono");
                break;
        }
    }

    class Task implements Runnable
    {
        int i;
        Task(int i){ this.i = i; }
        @Override
        public void run() {
            try {
                copyEmployer(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class TaskTwo implements Runnable
    {
        String path;
        TaskTwo(String path){ this.path = path; }
        @Override
        public void run() {
            try {
                loadEmployer(path);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyEmployer(int i) throws IOException {
        Pracownik pr = model.list.get(i);
        String path = "SAVE_" + pr.getImie() + pr.getNazwisko() + String.valueOf(pr.getWynagrodzenie() + ".zip");
        FileOutputStream output = new FileOutputStream(path);
        ObjectOutputStream object = new ObjectOutputStream(output);
        object.writeObject(pr);
        object.close();
    }

    private void loadEmployer(String path) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream object = new ObjectInputStream(file);
        model.list.add((Pracownik) object.readObject());
        object.close();
    }

    private void copy() throws IOException, ExecutionException, InterruptedException {
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        ArrayList<CompletableFuture<Void>> arr = new ArrayList<CompletableFuture<Void>>();

        for(int i=0;i<model.list.size(); i++){
            arr.add(CompletableFuture.runAsync(new Task(i), exec));
        }
        for(int i=0;i<model.list.size(); i++){
            arr.get(i).get();
        }
    }

    private void load() throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        ArrayList<CompletableFuture<Void>> arr = new ArrayList<>();

        File mainPath = new File(System.getProperty("user.dir"));
        String [] employers = mainPath.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("SAVE_");
            }
        });

        for(int i=0;i<employers.length; i++){
            arr.add(CompletableFuture.runAsync(new TaskTwo(employers[i]), exec));
        }

        for(int j=0;j<employers.length; j++){
            if(arr.get(j) != null) arr.get(j).get();
            else System.out.println("????");
        }
    }


}
