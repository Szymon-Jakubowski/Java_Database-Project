package com.company;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Pracownik implements Serializable {
    String pesel;
    String imie;
    String nazwisko;
    String stanowisko;
    int wynagrodzenie;
    String telefon;

    public void setPesel(String pesel) throws Exception {
        Scanner sc = new Scanner(System.in);

        while(pesel.length() != 11){
            System.out.print("Zly pesel, podaj jeszcze raz: "); pesel = sc.nextLine();
        }
        while(!verifyPesel(pesel)){
            System.out.print("Zly pesel, podaj jeszcze raz: "); pesel = sc.nextLine();
        }

        this.pesel = pesel;
    }

    private boolean verifyPesel(String pesel){
        int veryficationNumber = (pesel.charAt(0) - '0') + (3 * (pesel.charAt(1) - '0')) + (7*(pesel.charAt(2) - '0')) + (9*(pesel.charAt(3) - '0')) + (pesel.charAt(4) - '0') + (3*(pesel.charAt(5) - '0')) + (7*(pesel.charAt(6) - '0')) + (9*(pesel.charAt(7) - '0')) + (pesel.charAt(8) - '0') + (3*(pesel.charAt(9) - '0')) + (pesel.charAt(10) - '0');
        if(veryficationNumber % 10 == 0) return true;
        else return false;
    }
    public void setImie(String imie){
        this.imie = imie;
    }

    public String getImie() {
        return imie;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setWynagrodzenie(int wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    public int getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public abstract void wyswietl();

    public void wprowadzDane() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Pesel: "); setPesel(sc.nextLine());
        System.out.print("Imie: "); setImie(sc.nextLine());
        System.out.print("Nazwisko: "); setNazwisko(sc.nextLine());
        System.out.print("Wynagrodzenie: "); setWynagrodzenie(Integer.parseInt(sc.nextLine()));
        System.out.print("Telefon: "); setTelefon(sc.nextLine());
    }
}
