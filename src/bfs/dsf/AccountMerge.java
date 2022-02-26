/*
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, 
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is 
common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could
 have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and 
the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], 
           ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].

=========================

The run time of my AC solution is 95 ms which beats 90% cpp solutions. I have met this kind of problem several times, such as 547. Friend Circles and 737. 
Sentence Similarity II, and you can see my solution for 737. Sentence Similarity II. For this kind of problem, we can use both union-find and DFS algorithms. 
To make a brief summary, I would like to write a general DFS template, hope it helps.

Let us look at this problem, treat each email accounts group (an entity in the given input accounts) as a component, we want to find all connected components 
among these email accounts. Two components are connected if they have any emails in common.

So the first step is to build a map that can help us to find connected components. For this problem, we should find all components that each email belongs to.

//  build a map that can help us to find connected components. 
//  treat each entity in accounts as a unique component
//  find all component index for each email
unordered_map<string, vector<int>> acct;
for (int i = 0; i < n; ++i) {
      for (int j = 1; j < accounts[i].size(); ++j) {
            acct[accounts[i][j]].push_back(i);
      }
}

The second step is to find all emails that in a connected component and generate the output. The idea is simple, we iterate for all component in the input accounts,
 add all emails into a hash set in case of duplicated emails. At the same time, we need to keep the index of connected components so that we can process the connected 
 components in a DFS manner. To make sure that we just visit each component once, we can use a bool vector to mask the visited component.

//  DFS find all emails in a connected component
{       
        vector<vector<string>> res;
        vector<bool> visited(n, false);
        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;    // skip visited component
            visited[i] = true;

            set<string> emails;
            // keep the components index need to visit
            vector<int> to_visit{i};    
            // find all accounts in a DFS manner
            for (int j = 0; j < to_visit.size(); ++j) {
                int cur = to_visit[j];
                
                //  push all email in the current component into emails
                emails.insert(accounts[cur].begin() + 1, accounts[cur].end());
                
                // DFS find all connected components
                for (int k = 1; k < accounts[cur].size(); ++k) {
                    for (int idx: acct[accounts[cur][k]]) {
                        if (visited[idx]) continue;
                        visited[idx] = true;
                        to_visit.push_back(idx);
                    }    
                }
            }
            
            //  first save the user name, then save all emails
            res.push_back(vector<string> {accounts[i][0]});
            auto& r = res.back();
            r.insert(r.end(), emails.begin(), emails.end());
        }
}

So we can summarize the abstract template as a two-step solution to crack such kind of problem:

//  The first step is to find the connection between connected components.
//  In this way, we can visit next component when finishing current component.
//  Usually, we can use a map or unordered_map to represent such kind of connection.
unordered_map<string, vector<int>> map_connection;
for (int i = 0; i < components.size(); ++i) {
      for (int j = 0; j < components[i].size(); ++j) {
            //  build connection
      }
}

//  The second step is to merge connected components in a DFS manner.
//  To avoid visit any components more than once, we need to remember visited components.
//  Usually, this step is a three-layer nested for loop.
vector<vector<string>> res;
vector<bool> visited(n, false);   // make sure visit each component only once
//  first for loop, iterate for each component in the given input
for (int i = 0; i < components.size(); ++i) {
    if (visited[i]) continue;   // skip visited component
    visited[i] = true;

    //  keep the components index need to visit
    vector<int> to_visit{i};
    
    //  save the merged result
    set<string> merged_components;

    //  second loop, find all connected components in a DFS manner
    for (int j = 0; j < to_visit.size(); ++j) {
         int cur = to_visit[j];

         //   deal with current component
         merged_components.insert(components[cur].begin(), components[cur].end());

         // thid loop, DFS find all connected components
         for (int k = 1; k < accounts[cur].size(); ++k) {
              for (int idx: acct[accounts[cur][k]]) {
                   if (visited[idx]) continue;   // skip visited component
                   visited[idx] = true;
                   to_visit.push_back(idx);    //  push into to_visit
              }    
         }
    }
}

Solution for 547. Friend Circles:

int findCircleNum(vector<vector<int>>& M) {
        int n = M.size();
        vector<bool> visited(n, false);
        
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (visited[i]) continue;
            visited[i] = true;
            
            ++cnt;
            vector<int> to_visit{i};
            
            for (int j = 0; j < to_visit.size(); ++j) {               
                int cur = to_visit[j];
                for (int k = 0; k < n; ++k) {
                    if (visited[k] || M[cur][k] == 0) continue;
                    visited[k] = true;
                    
                    to_visit.push_back(k);
                }
            }
        }
        
        return cnt;
    }


*/

public class AccountMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        
    }
}



    The key task here is to connect those emails, and this is a perfect use case for union find.
    to group these emails, each group need to have a representative, or parent.
    At the beginning, set each email as its own representative.
    Emails in each account naturally belong to a same group, and should be joined by assigning to the same parent 
    (let’s use the parent of first email in that list);

Simple Example:

a b c // now b, c have parent a
d e f // now e, f have parent d
g a d // now abc, def all merged to group g

parents populated after parsing 1st account: a b c
a->a
b->a
c->a

parents populated after parsing 2nd account: d e f
d->d
e->d
f->d

parents populated after parsing 3rd account: g a d
g->g
a->g
d->g

Java

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }
}



I have tried my best to make my code clean. Hope the basic idea below may help you. Happy coding!

Basicly, this is a graph problem. Notice that each account[ i ] tells us some edges. What we have to do is as follows:

    Use these edges to build some components. Common email addresses are like the intersections that connect each single 
    component for each account.
    Because each component represents a merged account, do DFS search for each components and add into a list. Before 
    add the name into this list, sort the emails. Then add name string into it.

Examples: Assume we have three accounts, we connect them like this in order to use DFS.
{Name, 1, 2, 3} => Name – 1 – 2 – 3
{Name, 2, 4, 5} => Name – 2 – 4 – 5 (The same graph node 2 appears)
{Name, 6, 7, 8} => Name – 6 – 7 – 8
(Where numbers represent email addresses).

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        Map<String, Node> map = new HashMap<>();    // <Email, email node>  
                
        // Build the graph;
        for (List<String> list : accounts) {
            for (int j = 1; j < list.size(); j++) {
                String email = list.get(j);
                
                if (!map.containsKey(email)) {
                    Node node = new Node(email, list.get(0));
                    map.put(email, node);
                }
                
                if (j == 1) continue;
                //Connect the current email node to the previous email node;
                map.get(list.get(j - 1)).neighbors.add(map.get(email));
                map.get(email).neighbors.add(map.get(list.get(j - 1)));
            }
        }
        
        // DFS search for each components(each account);
        Set<String> visited = new HashSet<>();
        for (String s : map.keySet()) {
            if (visited.add(s)) {
                List<String> list = new LinkedList<>();
                list.add(s);              
                dfs(map.get(s), visited, list);
                Collections.sort(list);
                list.add(0, map.get(s).username);
                res.add(list);
            }
        }        
        return res;
    }
    
    public void dfs(Node root, Set<String> visited, List<String> list) {
        for (Node node : root.neighbors) {
            if (visited.add(node.val)) {
                list.add(node.val);
                dfs(node, visited, list);
            }
        }
    }
    
    class Node {
        String val;         // Email address;
        String username;    // Username;
        List<Node> neighbors;
        Node(String val, String username) {
            this.val = val;
            this.username = username;
            neighbors = new ArrayList<>();
        }
    }
}

