import java.util.*;
import java.io.*;

public class Main {

    static class Monster{
        public String name;
        public int exp;
        public int hp;
        public int ap;
        public int dp;
        public Monster(String n, int a, int b, int c, int d){
            name = n;
            exp =a;
            hp = b;
            ap = c;
            dp = d;
        }
        public Monster(){
            name = "";
            exp = hp = ap = dp = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       
    }

    public static String pro(){
        String[] monsters = {"Knight 3 10 10 3","Wizard 5 10 15 1","Beginner 1 1 15 1"};
        String character = "10 5 2";
        StringTokenizer st = new StringTokenizer(character);
        int pHP = Integer.parseInt(st.nextToken());
        int pAP = Integer.parseInt(st.nextToken());
        int pDP = Integer.parseInt(st.nextToken());

        Monster[] monster = new Monster[monsters.length];
        
        for(int i =0; i< monsters.length; i++){
            st= new StringTokenizer(monsters[i]);
            monster[i] = new Monster(st.nextToken(), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int index =0;
        double exps =0;
        for(int i=0; i< monsters.length; i++){
            int attack = pAP - monster[i].dp;
            int Mattack = monster[i].ap - pDP;
            double expc = 0;
            int sec = 0;
            if(attack > 0){
                 if(attack >= monster[i].hp){
                     //한 방에 끝남.
                     expc = monster[i].exp;
                 }else{
                    if(Mattack >= pHP){
                        continue;
                    }
                    sec = monster[i].hp%attack == 0? monster[i].hp/attack : monster[i].hp/attack+1;
                    expc = monster[i].exp/sec; 
                 }
            }else{
                continue;
            }
            
            if(exps < expc){
                exps = expc;
                index = i;
            }
            else if(expc == exps){
                if(monster[i].exp > monster[index].exp){
                    exps = expc;
                    index = i;
                }
            }
            
        }
        
        String answer = monster[index].name;
        System.out.println(index);
        return answer;
    }

    
}
