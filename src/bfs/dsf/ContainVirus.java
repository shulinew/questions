/*
 A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.

The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. A wall 
(and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.

Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. Each day, you 
can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the most uninfected cells the 
following night. There will never be a tie.

Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of walls used. 

Input: grid = 
[[0,1,0,0,0,0,0,1],
 [0,1,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,1],
 [0,0,0,0,0,0,0,0]]
Output: 10
Explanation:
There are 2 contaminated regions.
On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:

[[0,1,0,0,0,0,1,1],
 [0,1,0,0,0,0,1,1],
 [0,0,0,0,0,0,1,1],
 [0,0,0,0,0,0,0,1]]

On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.

Input: grid = 
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output: 4
Explanation: Even though there is only one cell saved, there are 4 walls built.
Notice that walls are only built on the shared boundary of two different cells.

Input: grid = 
[[1,1,1,0,0,0,0,0,0],
 [1,0,1,0,1,1,1,1,1],
 [1,1,1,0,0,0,0,0,0]]
Output: 13

Note:

    The number of rows and columns of grid will each be in the range [1, 50].
    Each grid[i][j] will be either 0 or 1.
    Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares in the next round.

*/

public class ContainVirus {
    public int containVirus(int[][] grid) {
        int[] cost = new int[]{0};
        while(check(grid, cost));
        return cost[0];
    }
    
    private boolean check(int[][] grid, int[] cost) {
    // update every day information and return false if no improvement can be made
        int count = 1;
        int max = -1;
        boolean flag = false;
        List<int[]> info = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    int[][] walls = new int[grid.length][grid[0].length];
                    int[] res = new int[2];
                    grid[i][j] = count;
                    dfs(i, j, grid, count, walls, res);
                    if (res[0] != 0) flag = true;
                    if (max == -1 || res[0] > info.get(max)[0]) {
                        max = count - 2;
                    }
                    info.add(res);
                }
            }
        }
        if (count == 1) {
            return false;
        }
        cost[0] += info.get(max)[1];
        update(grid, max + 2);
        return flag;
    }
    
    
    private void dfs(int row, int col, int[][] grid, int count, int[][] walls, int[] res) {
    //dfs and record number of walls need to block this area and how many 0s are under infection
        int[] shiftX = new int[]{1, 0, -1, 0};
        int[] shiftY = new int[]{0, 1, 0, -1};
        
        for(int i = 0; i < 4; i++) {
            int newRow = row + shiftX[i];
            int newCol = col + shiftY[i];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                if (grid[newRow][newCol] == 1) {
                    grid[newRow][newCol] = count;
                    dfs(newRow, newCol, grid, count, walls, res);
                } else if (grid[newRow][newCol] == 0) {
                    if (walls[newRow][newCol] == 0) res[0]++;
                    if ((walls[newRow][newCol] & 1 << i) == 0) {
                        res[1]++;
                        walls[newRow][newCol] |= 1 << i;
                    }
                }
            }
        }
    }
        
        
    private void update(int[][] grid, int quarantine) {
    //set the new infected area and set blocked area to be -1
        int[] shiftX = new int[]{1, 0, -1, 0};
        int[] shiftY = new int[]{0, 1, 0, -1};
            
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 1 && grid[i][j] != quarantine) {
                    for (int k = 0; k < 4; k++) {
                        int newRow = i + shiftX[k];
                        int newCol = j + shiftY[k];
                        if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == 0) {
                            grid[newRow][newCol] = 1;
                        }
                    }
                    grid[i][j] = 1;
                } else if (grid[i][j] == quarantine) {
                    grid[i][j] = -1;
                }
            }
        }
    }

    //////
    class Virus {
        int row, col;
        int cellsCovered = 0;
        int wallsNeeded = 0;
        boolean contained = false;
        ArrayList<Virus> cellsCanInfect = new ArrayList<>();
        Virus(int r, int c) {
            row = r;
            col = c;
        }
        void addCellCanInfect(int r, int c) {
            cellsCanInfect.add(new Virus(r,c));
        }
    }
    
    public int containVirus(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        
        LinkedList<Virus> containedViruses = new LinkedList<>();
        ArrayList<Virus> uncontainedViruses = new ArrayList<>();
        findViruses(grid, uncontainedViruses);
        
        int numContainedVirusCells = 0;
        int numUncontainedVirusCells = 0;
        int worldSize = width*height;
        
        while(!uncontainedViruses.isEmpty() && numContainedVirusCells + numUncontainedVirusCells != worldSize) {
            numUncontainedVirusCells = growViruses(grid,uncontainedViruses);
            containedViruses.add(removeBiggestVirus(uncontainedViruses, grid));
            int newCellsContained = containedViruses.getLast().cellsCovered;
            numContainedVirusCells += newCellsContained;
            numUncontainedVirusCells -= newCellsContained;
            removeJoinedViruses(grid,uncontainedViruses);
        }        
        int walls = 0;
        for(Virus v : containedViruses) {
            walls += v.wallsNeeded;
        }
        return walls;
    }
    
    private boolean invalidCell(int i, int j, int[][] grid) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 8; // Contained virus marked with 8
    }
 
    private void findViruses(int[][] grid, ArrayList<Virus> viruses) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == 1) {
                    Virus newVirus = new Virus(i,j);
                    viruses.add(newVirus);
                    markVirusCells(grid,i,j,visited);
                }
            }
        }
    }
    
    private void markVirusCells(int[][] grid, int i, int j, boolean[][] visited) {
        if(invalidCell(i,j,grid) || visited[i][j] || grid[i][j] == 0) return;
        visited[i][j] = true;
        markVirusCells(grid,i+1,j,visited);
        markVirusCells(grid,i-1,j,visited);
        markVirusCells(grid,i,j+1,visited);
        markVirusCells(grid,i,j-1,visited);
    }

    private int growViruses(int[][] grid, ArrayList<Virus> viruses) {
        int uncontainedVirusCells = 0;
        for(Virus v : viruses) {
            for(Virus c : v.cellsCanInfect) {
                grid[c.row][c.col] = 1;
            }
            v.cellsCanInfect.clear();
        }
        for(Virus v : viruses) {
            v.cellsCovered = 0;
            v.wallsNeeded = 0;
            visitVirus(grid,v.row,v.col,v,new boolean[grid.length][grid[0].length]);
            uncontainedVirusCells += v.cellsCovered;
        }
        return uncontainedVirusCells;        
    }
    
    private void visitVirus(int[][] grid, int i, int j, Virus v, boolean[][] visited) {
        if (invalidCell(i,j,grid)) return;
        if(!visited[i][j]) {
            visited[i][j] = true;
            if(grid[i][j] == 0) {
                v.addCellCanInfect(i,j);
            } else {
                v.cellsCovered++;
                visitVirus(grid,i+1,j,v,visited);
                visitVirus(grid,i,j+1,v,visited);
                visitVirus(grid,i-1,j,v,visited);
                visitVirus(grid,i,j-1,v,visited);            
            }
        }
        if(grid[i][j] == 0) {
            v.wallsNeeded++;
        }
    }
        
    private Virus removeBiggestVirus(ArrayList<Virus> viruses, int[][] grid) {
        int biggestVirusIndex = 0;
        int biggestVirusSize = 0;
        for(int i=0; i<viruses.size(); i++) {
            Virus v = viruses.get(i);
            int growthSize = v.cellsCanInfect.size();
            if(growthSize > biggestVirusSize) {
                biggestVirusIndex = i;
                biggestVirusSize = growthSize;
            }
        }
        containVirus(grid,viruses.get(biggestVirusIndex));
        return viruses.remove(biggestVirusIndex);
    }

    private void containVirus(int[][] grid, Virus v) {
        containVirusHelper(grid,v.row,v.col);
        v.contained = true;
    }
    
    private void containVirusHelper(int[][] grid,int i, int j) {
        if(invalidCell(i,j,grid)) return;
        if(grid[i][j] == 1) {
            grid[i][j] = 8;
            containVirusHelper(grid,i,j+1);
            containVirusHelper(grid,i+1,j);
            containVirusHelper(grid,i-1,j);
            containVirusHelper(grid,i,j-1);
        }
    }
    
    // Remove viruses that merged into one
    private void removeJoinedViruses(int[][] grid, List<Virus> viruses) {
        Iterator<Virus> i = viruses.iterator();
        while(i.hasNext()) {
            Virus v = i.next();
            if(grid[v.row][v.col] == 8) {
                i.remove();
            }
        }
    }  


class Solution {
    public:
        int containVirus(vector<vector<int>>& grid) {
            int ans = 0;
            while (true) {
                int walls = process(grid);
                if (walls == 0) break; // No more walls to build
                ans += walls;
            }
            return ans;
        }
    private:
        int process(vector<vector<int>>& grid) {
            int m = grid.size(), n = grid[0].size();
            // cnt is max area to be affected by a single virus region; ans is corresponding walls
            int cnt = 0, ans = 0, color = -1, row = -1, col = -1;
            // visited virus as 1, visited 0 using different color to indicate being affected by different virus
            vector<vector<int>> visited(m, vector<int>(n, 0)); 
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) {
                        int walls = 0, area = dfs(grid, visited, i, j, color, walls);
                        if (area > cnt) {
                            ans = walls;
                            cnt = area;
                            row = i; 
                            col = j;
                        }
                        color--;
                    }
                }
            }
            // set this virus region inactive
            buildWall(grid, row, col);
            // propagate other virus by 1 step
            visited = vector<vector<int>>(m, vector<int>(n, 0));
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && visited[i][j] == 0) 
                        spread(grid, visited, i, j);
                }
            }
            return ans;
        }
        int dfs(vector<vector<int>>& grid, vector<vector<int>>& visited, int row, int col, int color, int& walls) {
            int m = grid.size(), n = grid[0].size(), ans = 0;
            if (row < 0 || row >= m || col < 0 || col >= n) return 0;
            if (grid[row][col] == 0) {
                walls++; 
                if (visited[row][col] == color) return 0;
                visited[row][col] = color;
                return 1;
            }
            // grid[row][col] could be -1, inactive virus
            if (visited[row][col] == 1 || grid[row][col] != 1) return 0; 
            visited[row][col] = 1;
            vector<int> dir = {-1, 0, 1, 0, -1};
            for (int i = 0; i < 4; i++) 
                ans += dfs(grid, visited, row+dir[i], col+dir[i+1], color, walls);
            return ans;
        }
        void buildWall(vector<vector<int>>& grid, int row, int col) {
            int m = grid.size(), n = grid[0].size();
            if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != 1) return;
            grid[row][col] = -1; //set inactive
            vector<int> dir = {-1, 0, 1, 0, -1};
            for (int i = 0; i < 4; i++) 
                buildWall(grid, row+dir[i], col+dir[i+1]);
        }
        void spread(vector<vector<int>>& grid, vector<vector<int>>& visited, int row, int col) {
            int m = grid.size(), n = grid[0].size();
            if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] == 1) return;
            if (grid[row][col] == 0) {
                grid[row][col] = 1;
                visited[row][col] = 1;
            }
            else if (grid[row][col] == 1) {
               visited[row][col] = 1;
               vector<int> dir = {-1, 0, 1, 0, -1};
               for (int i = 0; i < 4; i++) 
                   spread(grid, visited, row+dir[i], col+dir[i+1]);
            }
        }
    };

    /*DFS, single pass with intermediate results saved, 19 ms

    Build walls = set those connected virus inactive, i.e. set as -1;
    Affected area != walls; For example, one 0 surrounded by all 1s have area = 1, but walls = 4.

*/

    class Solution {
public:
    int containVirus(vector<vector<int>>& grid) {
        int ans = 0;
        while (true) {
            int walls = model(grid);
            if (walls == 0) break;
            ans += walls;
        }
        return ans;
    }
private:
    int model(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size(), N = 100;
        vector<unordered_set<int>> virus, toInfect;
        vector<vector<int>> visited(m, vector<int>(n, 0));
        vector<int> walls;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    virus.push_back(unordered_set<int>());
                    toInfect.push_back(unordered_set<int>());
                    walls.push_back(0);
                    dfs(grid, visited, virus.back(), toInfect.back(), walls.back(), i, j);
                }
            }
        }
        int maxArea = 0, idx = -1;
        for (int i = 0; i < toInfect.size(); i++) {
            if (toInfect[i].size() > maxArea) {
                maxArea = toInfect[i].size();
                idx = i;
            }
        }
        if (idx == -1) return 0;
        for (int i = 0; i < toInfect.size(); i++) {
            if (i != idx) {
                for (int key : toInfect[i]) 
                    grid[key/N][key%N] = 1;
            }
            else {
                for (int key: virus[i]) 
                    grid[key/N][key%N] = -1;
            }
        }
        return walls[idx];
    }
private:
    void dfs(vector<vector<int>>& grid, vector<vector<int>>& visited, unordered_set<int>& virus, unordered_set<int>& toInfect, int& wall, int row, int col) {
        int m = grid.size(), n = grid[0].size(), N = 100;
        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] == 1) return;
        if (grid[row][col] == 1) {
            visited[row][col] = 1;
            virus.insert(row*N + col);
            vector<int> dir = {0, -1, 0, 1, 0};
            for (int i = 0; i < 4; i++)
                dfs(grid, visited, virus, toInfect, wall, row+dir[i], col+dir[i+1]);
        }
        else if (grid[row][col] == 0) {
            wall++;
            toInfect.insert(row*N + col);
        }
    }

}