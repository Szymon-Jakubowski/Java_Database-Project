package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class Handlowiec extends Pracownik {
    int stawkaProwizji;
    int limitProwizji;

    public void setStawkaProwizji(int stawkaProwizji) {
        this.stawkaProwizji = stawkaProwizji;
    }

    public void setLimitProwizji(int limitProwizji) {
        this.limitProwizji = limitProwizji;
    }

    @Override
    public void wprowadzDane() throws Exception {
        Scanner sc = new Scanner(System.in);
        super.wprowadzDane();
        System.out.print("Stawka Prowizji: "); setStawkaProwizji(Integer.parseInt(sc.nextLine()));
        System.out.print("Limit Prowizji: "); setLimitProwizji(Integer.parseInt(sc.nextLine()));
        setStanowisko("Handlowiec");
    }

    @Override
    public void wyswietl() {
        System.out.println("Identyfikator Pesel             : " + pesel);
        System.out.println("-------------------------------------------------");
        System.out.println("Imie                            : " + imie);
        System.out.println("Nazwisko                        : " + nazwisko);
        System.out.println("Stanowisko                      : " + stanowisko);
        System.out.println("Wynagrodzenie (zl)              : " + wynagrodzenie);
        System.out.println("Telefon sluzbowy numer          : " + telefon);
        System.out.println("Prowizja (%)                    : " + stawkaProwizji);
        System.out.println("Limit prowizji/miesiac (zl)     : " + limitProwizji);
        System.out.println("-------------------------------------------------");
    }
}
