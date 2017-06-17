/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ALGORITMO;

import java.util.*;
/**
 *
 * @author kevin_000
 */
public  class SubSetAprox {
    
    public int n;
    public int t;
    public ArrayList<Integer> S;
    public ArrayList<ArrayList<Integer>> L;
    public ArrayList<Integer> Ltrimmed;
    Random rand = new Random();
    Scanner reader = new Scanner(System.in);

    public SubSetAprox(int n, int t) {
        this.n = n;
        this.t = t;
        
        //genero el conjunto S
        this.S=this.genS(n); 
        
        //el metodo exactUbSet implementa el algoritmo sugerido en el libro
        //haciendo uso de mergelist y sumlist
        this.exactSubsetSum(S, t);
    }
    
    
   //genera una lista aleatoria de enteros positivos sin duplicados 
    public  ArrayList<Integer> genS(int n){
        this.n=n;
        S= new ArrayList<Integer>();
        while (S.size() < n) {
            int random = rand.nextInt(n+5);
            //----------------------------la funcion contains podria alterar la complejidad
            if (!S.contains(random)) {
                S.add(random);
                System.out.println(random);
            }
        }
        return S;
    }
    
    //sean l1 y l2 dos listas, este metodo retorna una lista que será la 
    //union de l1 y l2 ordenandola y deshaciendose de duplicados
    public ArrayList mergeList(ArrayList<Integer> L1,ArrayList<Integer> L2 ){
        Collections.sort(L2);
        Collections.sort(L1);
        ArrayList<Integer> L1c=L1;
        L1c.removeAll(L2);
        L1c.addAll(L2);
        Collections.sort(L1c);
        return L1c;
    }
    
    // este metodo suma un entero a cada elemento de una lista
    public ArrayList<Integer> sumList(ArrayList<Integer> List, int a){
        ArrayList<Integer> sList=new ArrayList<Integer>();
        for (int i = 0; i < List.size(); i++) {
            sList.add(List.get(i)+a);
        }
        return sList;
    }
    
    //implementación del algorítmo propuesto en el libro
    public int exactSubsetSum(ArrayList<Integer> S, int t){
        this.S=S;
        this.t=t;
        int n= S.size();
        String str="";
        
        L=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> Laux=new ArrayList<Integer>();
        Laux.add(0);
        L.add(Laux);
        
        for (int i = 1; i <= n; i++) {
            
            L.add(mergeList(L.get(i-1), sumList(L.get(i-1), S.get(i-1))));
            Iterator<Integer> it = L.get(i-1).iterator();
            //nos deshacemos de las sumas mayores a t            
            while (it.hasNext()) {
                Integer integer = it.next();
                if (integer >t) {
                    it.remove();
                }
}
            
            System.out.println("-"+arrToString(L.get(i-1)));
        }
        
        
        return 0;
    }
    
    public String arrToString(ArrayList<Integer> a){
        String str="";
        for (int i = 0; i < a.size(); i++) {
            str+=a.get(i).toString()+" ";
        }
        return str;
    }       
        
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner reader = new Scanner(System.in);
        System.out.print("ingrese el valor de n= ");
        int n =  reader.nextInt();
        System.out.print("ingrese el valor de t= ");
        int t =  reader.nextInt();
        
        SubSetAprox ssAprox= new SubSetAprox(n,t);
        
        
       
        
        
    }
}
