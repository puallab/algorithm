import java.util.*;


import java.io.*;

public class Main{
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk= new Stack<>();
        n= Integer.parseInt(br.readLine());
        
        while(n -- > 0){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            switch(command){
                case "push":
                    int number= Integer.parseInt(st.nextToken());
                    stk.push(number);
                break;
                case "pop":
                    if(stk.size() == 0) sb.append(-1+"\n");
                    else sb.append(stk.pop()+"\n");
                break;
                case "size":
                    sb.append(stk.size() + "\n");
                break;
                case "empty":
                    if(stk.isEmpty()) sb.append(1 +"\n");
                    else sb.append(0+ "\n");
                break;
                case "top":
                    if(stk.isEmpty()) sb.append(-1+"\n");
                    else sb.append(stk.peek()+"\n");
                break;
            }

        }
        System.out.println(sb.toString());
    }
}