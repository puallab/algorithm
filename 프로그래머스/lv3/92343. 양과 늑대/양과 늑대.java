import java.util.*;
class Solution {
    static Node[] tree;
    static boolean[] vis;
    static int ans;
    public int solution(int[] info, int[][] edges) {
        
        init(info.length);
        tree = build(info, edges);
        
        vis[0] = true;
        dfs(0, 1, 0, 1, new ArrayList<>());
        return ans;
    }

    //현재 방문 node, 양, 늑대, 단계, peerNode 
    static void dfs(int idx, int sCnt, int wCnt, int state, List<Node> peer){
        if(sCnt <= wCnt){
            return;
        }

        ans = Math.max(ans, sCnt);

        //방문했어 그럼 뭐해야해? 늑대, 양, 상태 peer update
        peer.add(tree[idx]);

        //갈 수 있는 곳 찾기
        for(int i =0; i<peer.size(); i++){
            Node node = peer.get(i);
            for(Node next: node.list){
                
                //아직 방문하지 않은 곳인지 확인
                int bit = 1<<next.idx;
                int check = state | bit;
                if(vis[check]) continue;

                // 방문 안한 곳이면 방문
                vis[check] = true;

                if(next.flag == 0){
                    dfs(next.idx, sCnt+1, wCnt, check, peer);
                }else{
                    dfs(next.idx, sCnt, wCnt+1, check, peer);
                }
                vis[check] = false;
            }
        }
        
        peer.remove(peer.size()-1);

    }

    static Node[] build(int[] info, int[][] edges){
        Node[] nodes = new Node[info.length];
        for(int i =0; i<info.length; i++){
            nodes[i] = new Node();
            nodes[i].idx = i;
            nodes[i].flag = info[i];
        }

        for(int i =0; i< edges.length; i++ ){
            int parent = edges[i][0];
            int child = edges[i][1];
            nodes[parent].list.add(nodes[child]);
        }
        return nodes;
    }

    static void init(int n){
        //node 수만큼 vis 배열 확장.
        int max = 1<< n;
        vis = new boolean[max];
    }

    static class Node{
        int idx, flag; //flag = 0, 1 양 늑대

        List<Node> list = new ArrayList<>(); // 자식 list,
    }
}