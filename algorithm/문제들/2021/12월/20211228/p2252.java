import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
 
/** 
 *  위상정렬
 *  1. 들어오는 간선이 없는 정점을 모두 큐에 넣는다.
 *  2. 큐에 정점을 하나 빼서 나가는 간선을 삭제한다. 
 *      2-1. 이때 들어오는 간선이 0이된 정점을 큐에 집어 넣는다.
 *      2-2. 정점의 수만큼 반복한다. (n만큼 돌기 전에 큐가 빈다면 사이클이 존재함.)
 *  3. 큐에서 나오는 순서가 위상 정렬의 순서이다. (들어오는 간선이 없는 순으로 뺐으니까.)
 */
public class Main {

    static int n, m;
    static int[] indegree;
    static List<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        indegree = new int[n+1];
        list = new ArrayList[n+1];
        for(int i=0; i< n+1; i++) list[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            list[prev].add(next);
            indegree[next]++;
        }

        pro();
        
    }

    static void pro(){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for(int i =1; i< n+1; i++) {
            if(indegree[i] == 0) q.add(i);
        } 

        for(int i =0; i< n; i++){
            int now = q.poll();
            sb.append(now + " ");
            for(int next : list[now]){
                indegree[next]--;
                if(indegree[next] == 0 ) q.add(next);
            }
        }

        System.out.println(sb);
    }
    

}