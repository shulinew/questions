package dp;
public class VideoClip {
    public int videoStiching(int[][] clips, int T) {
        int[] dp = new int[T+1]; // dp[i] is how many video clips required for length i.
        int length = clips.length;
        Arrays.fill(dp, length+1); // maximum required is length of clips
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip: clips) {
                if (clip[0] <= i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i] , dp[clip[0]]+1);
                }
            }
            if (dp[i] == length+1) return -1;
        }
        return dp[T];
    }
    public int videoStichGreedy(int[][] clips, int T) {
        int count = 0;
        Arrays.sort(clips,(a, b) -> a[0]-b[0]);
        // start from i = 0 
        for (int i = 0, start = 0, end = 0; start < T; start = end, count++) {
            // find max end from current start
            for (; i < clips.length && clips[i][0] <= start; i++) {
                end = Math.max(end, clips[i][1]);
            }
            if (start == end) return -1;
        }
        return count;
    }

    //TO read
    public int videoStitching(int[][] clips, int T) {
        int[] dic = new int[T+1];
        Arrays.fill(dic,-1);
        for(int j = 0; j < clips.length; j++){
            for(int i = clips[j][0]; i <= Math.min(T,clips[j][1]); i++) dic[i]=Math.max(dic[i],clips[j][1]);
        }        
        for(int i = 0; i <= T; i++){
            if(dic[i]==-1) return -1;
        }
        int cur = 0;
        int ans = 0;
        int max = 0;
        while(max<T){
            max= dic[cur];
            ans++;
            cur = max;
        } 
        return ans;
    }
}