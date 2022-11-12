import java.util.*;
import java.io.*;

/*
 *  S -> E, E -> S 까지 2번의 bfs를 진행
 *  인접리스트로 구현하면 시간복잡도 2*O(V+E) 이고 최대 V : 10,000, E가 50,000 이므로 120,000으로 충분함
 *  지나온 경로를 저장하는 List 배열에서의 연산횟수도 최대 10,000!이므로 12,502,500 충분함.
 */

public class Main{

    /*
     * Queue에 사용될 자료구조 
     * 현재 번호와 지나온 경로를 "순서대로" 기록하기위해 List를 멤버로 선언
     */
    static class Pair{
        int num;
        List<Integer> list;

        public Pair(int a, List<Integer> l){
            num = a;
            list = l;
        }
    }


    static int n, m, from, to;
    static List<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    static boolean[] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        vis = new boolean[n+1];
        for(int i =1; i<n+1; i++){
            list[i] = new ArrayList<>();
        }


        for(int  i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
            list[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        /*
         *  사전 순서대로 방문해야 하므로  모든 간선에 대해 정렬
         */
        for(int i =1; i<n+1; i++){
            Collections.sort(list[i]);
        }
        
        int ans = bfs(from, to); // S -> E
        ans += bfs(to, from); // E -> S
        //sb.append(from);
        System.out.println(ans);
    }


    static int bfs(int _from, int _to){
        Queue<Pair> q= new LinkedList<>();
        q.add(new Pair(_from, new ArrayList<>()));
        vis[_from] = true;

        while(!q.isEmpty()){

            Pair now = q.poll();
            List<Integer> nowList = new ArrayList<>(now.list);
            nowList.add(now.num);
            for(int next : list[now.num]){
                
                if(next == _to){

                    //S -> E 의 경우에만 방문 배열을 초기화해준다.
                    if (_to == to) {
                        vis = new boolean[n + 1];
                        for (int i : nowList) {
                            vis[i] = true;
                            // sb.append(i + " ");
                        }
                    }
                    return nowList.size();
                }
                if(vis[next]) continue;
                vis[next] = true;
                q.add(new Pair(next, new ArrayList<>(nowList)));
                
            }
        }

        return -1;
    }


}