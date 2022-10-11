import java.util.*;

import java.io.*;

public class Main{

    static class Node{
        boolean isBlue = false, isFull = false;
        int point;
        Node next, bNext;

        public Node(int p){
            point = p;
        }
    }

    static boolean flag =false;
    static int ans;
    static int[] orders = new int[10];
    static int[] picks = new int[10];
    static Node[] horse = new Node[5];
    static Node head, tail;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<10; i++){
            orders[i] = Integer.parseInt(st.nextToken());
        }


        buildMap();
        dfs( 0);
        System.out.println(ans);
    }

    static void buildMap(){

        head = new Node(0);
        Node Point = head;
        Node node25 = new Node(25);

        for(int i =1; i< 20; i++){
            int val = 2*i;
            Point.next = new Node(val);
            Point = Point.next;

            if(val%10 == 0 ){
                Point.isBlue = true;

                if(val == 10){
                    Node node13 = new Node(13);
                    Node node16 = new Node(16);
                    Node node19 = new Node(19);
                    Point.bNext = node13;
                    node13.next = node16;
                    node16.next = node19;
                    node19.next = node25;
                }else if(val == 20){

                    Node node22 = new Node(22);
                    Node node24 = new Node(24);
                    Point.bNext = node22;
                    node22.next = node24;
                    node24.next = node25;
                }else if( val == 30){
                    Node node28 = new Node(28);
                    Node node27 = new Node(27);
                    Node node26 = new Node(26);
                    Point.bNext = node28;
                    node28.next = node27;
                    node27.next = node26;
                    node26.next = node25;
                }
            }

            tail = Point;
        }

        tail.next = new Node(40);
        tail = tail.next;

        node25.next = new Node(30);
        node25.next.next = new Node(35);
        node25.next.next.next = tail;
        
        tail.next = new Node(0);
        tail = tail.next;
        

        for(int i=0; i<5;i ++){
            horse[i] = head;
        }

    }

    static void dfs(int idx){
        if(idx == 10){
            ans = Math.max(gameStart(), ans);
            return;
        }

        for(int i =1; i<5; i++){
            picks[idx] = i;
            dfs(idx+1);
            picks[idx] = 0;
        }
    }

    static int gameStart(){
        int sum = 0;

        for(int i =0; i<5; i++){
            horse[i] = head;
        }

        for(int i =0; i<orders.length; i++){

            int k = move(picks[i], orders[i]);
            if(k == -1){
                return -1;
            }
            sum += k;
        }

        return sum;
    }

    static int move(int idx, int k){
        Node start = horse[idx];

        if(horse[idx] == tail){
            return -1;
        }

        if(start.isBlue){
            horse[idx] = horse[idx].bNext;
        }else{
            horse[idx] = horse[idx].next;
        }

        for(int i=0; i<k-1; i++){

            if(horse[idx] == tail){
                break;
            }

            horse[idx]  = horse[idx].next;
        }

        if(horse[idx] != tail ){
            for(int i =1; i<=4; i++){
                if(i == idx) continue;
                if(horse[idx] == horse[i]){
                    return -1;
                }
            }
            
        }

        return horse[idx].point;
        
    }

    


}