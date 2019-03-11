package com.dal.repo;

public class RepoFactory {
    private static IRepo repo;
    //daje instancu Irepoa, ovdje mos promjeniti bilo koji repo., kod nas je sam database
    public  static IRepo getRepo(){

        if (repo==null){
            try {
                repo = new DatabaseRepo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return repo;
        //ako nije null necu uzci i napraviti novu instancu, samo ce se vratit, ako je null prvo napravi instancu onda se vrati
    }
}


