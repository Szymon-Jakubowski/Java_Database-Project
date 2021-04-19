package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class Dyrektor extends Pracownik {
    int dodatekSluzbowy;
    String kartaSluzbowa;
    int limit;

    public void setDodatekSluzbowy(int dodatekSluzbowy) {
        this.dodatekSluzbowy = dodatekSluzbowy;
    }

    public void setKartaSluzbowa(String kartaSluzbowa){
        this.kartaSluzbowa = kartaSluzbowa;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    @Override
    public void wprowadzDane() throws Exception {
        Scanner sc = new Scanner(System.in);
        super.wprowadzDane();
        System.out.print("Dodatek Sluzbowy: "); setDodatekSluzbowy(Integer.parseInt(sc.nextLine()));
        System.out.print("Karta Sluzbowa: "); setKartaSluzbowa(sc.nextLine());
        System.out.print("Limit: "); setLimit(Integer.parseInt(sc.nextLine()));
        setStanowisko("Dyrektor");
    }

    @Override
    public void wyswietl() {
        System.out.println("Identyfikator Pesel         : " + pesel);
        System.out.println("-------------------------------------------------");
        System.out.println("Imie                        : " + imie);
        System.out.println("Nazwisko                    : " + nazwisko);
        System.out.println("Stanowisko                  : " + stanowisko);
        System.out.println("Wynagrodzenie (zl)1         : " + wynagrodzenie);
        System.out.println("Telefon sluzbowy numer      : " + telefon);
        System.out.println("Dodatek sluzbowy (zl)       : " + dodatekSluzbowy);
        System.out.println("Karta sluzbowa numer        : " + kartaSluzbowa);
        System.out.println("Limit kosztów/miesiac (zl)  : " + limit);
        System.out.println("-------------------------------------------------");
    }
}
