package com.example.robertopinotti.calcolatricesingleton;

public class MySingleton {

    private int value=0;

    private static MySingleton ourInstance = new MySingleton();

    public static MySingleton getInstance() { return ourInstance; }

    // costruttore privato così nessuno può creare una istanza
    private MySingleton() {}

    public int getValue(){
        return this.value;
    }

    public void setValue(int n){
        value=n;
    }

}
