package com.example.robertopinotti.map;

import java.util.ArrayList;

public class ListOfContacts extends ArrayList<Contact>{

    public ListOfContacts(){
        super();
        this.add(new Contact("Perth", -31.952854, 115.857342)); // 1
        this.add(new Contact("Sidney", -33.87365, 151.20689)); // 2
        this.add(new Contact("Brisbane", -27.47093, 153.0235)); // 3
    }

}
