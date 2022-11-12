import java.util.*;
import java.io.*;

public class Main{

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

        for(int i =1; i<n+1; i++){
            Collections.sort(list[i]);
        }
        
        int ans = bfs(from, to);
        ans += bfs(to, from);
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
                    vis = new boolean[n+1];
                    for(int i : nowList){
                        vis[i] = true;
                        //sb.append(i + " ");
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