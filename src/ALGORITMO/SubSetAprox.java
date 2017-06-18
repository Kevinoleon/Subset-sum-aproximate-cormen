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
    public float e;
    public ArrayList<Integer> Ltrimmed;
    Random rand = new Random();
    Scanner reader = new Scanner(System.in);

    public SubSetAprox(int n, int t) {
        this.n = n;
        this.t = t;
        this.e=(float)0.40;
                
        //genero el conjunto S
        this.S=this.genS(n); 
        
        //el metodo exactUbSet implementa el algoritmo sugerido en el libro
        //haciendo uso de mergelist y sumlist
        System.out.println("resultado exacto:" + this.exactSubsetSum(S, t));
        //el metodo aproxSubSet implementa el algoritmo aprox sugerido en el libro
        //haciendo uso de la funcion trim
        System.out.println("resultado aproximado "+"(con e= "+e+"): " + this.aproxSubsetSum(S, t, e));
    }
    
    
   //genera una lista aleatoria de enteros positivos sin duplicados 
    public  ArrayList<Integer> genS(int n){
        this.n=n;
        S= new ArrayList<Integer>();
        while (S.size() < n) {
            int random = rand.nextInt(n+11);
            //----------------------------la funcion contains podria alterar la complejidad
            if (!S.contains(random)) {
                S.add(random);
                
            }
        }
        System.out.println("Conjunto S: "+arrToString(S));
        
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
        ArrayList<ArrayList<Integer>> L;
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
            //System.out.println("P"+(i)+": "+arrToString(L.get(i-1)));
        }
        
        
        return Collections.max(L.get(L.size()-1));
    }
    //trimList recorta la lista suministrada de acuerdo a un parametro de aproximación
    public ArrayList<Integer> trimList(ArrayList<Integer> long_list, float p){
        
        int m=long_list.size();
        ArrayList<Integer> t_list= new ArrayList<Integer>();
        
        t_list.add(long_list.get(0));
        int last= long_list.get(0);
        for (int i = 2; i <= m; i++) {
            if(long_list.get(i-1)>=(last*(1+p))){
                t_list.add(long_list.get(i-1));
                last=long_list.get(i-1);
            }
        }        
        return t_list;
    }
    
    
    //realiza el mismo procedimiento de exactSubsetSum pero implementa la funcion
    //trim que recorta la lista deacuerdo a un parametro de aproximación    
    public int aproxSubsetSum(ArrayList<Integer> S, int t, float e){
        this.S=S;
        this.e=e;
        this.t=t;
        int n= S.size();
        String str="";
        
        ArrayList<ArrayList<Integer>> L;
        L=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> Laux=new ArrayList<Integer>();
        Laux.add(0);
        L.add(Laux);
        
        for (int i = 1; i <= n; i++) {
            
            
            L.add(mergeList(L.get(i-1), sumList(L.get(i-1), S.get(i-1))));
            
            //System.out.println("--P"+(i)+": "+arrToString(L.get(i-1)));
            
            //nos deshacemos de las sumas mayores a t
            Iterator<Integer> it = L.get(i-1).iterator();                        
            while (it.hasNext()) {
                Integer integer = it.next();
                if (integer >t) {
                    it.remove();
                }
            }            
            //System.out.println("Pma"+(i)+": "+arrToString(L.get(i-1)));
            L.set(i, trimList(L.get(i), e/(2*n)));
            //System.out.println("Ptr"+(i)+": "+arrToString(L.get(i)));
        }       
        return Collections.max(L.get(L.size()-1));
    }
    
    public String arrToString(ArrayList<Integer> a){
        String str="";
        for (int i = 0; i <= a.size()-1; i++) {
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
