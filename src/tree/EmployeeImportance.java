/*
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
*/

//class Employee{
//    public int id;
//    public int importance;
//    public List<Integer> subordinates;
//}
//
//public EmployeeImportance{
//    public int getImportance(list<Employee> employees, int id) {
//        int importances = 0;
//        int [] visited = new int[2000];
//        HashMap<Integer, Employee> maps = new Map<Integer, Employee>();
//        maps = buildEmployeeMap(employees);
//        importances = dfs(maps, id, visited, importances);
//        return importances;
//    }
//    private int dfs(HashMap<Integer, Employee> maps, int id, int[] visited, int importances) {
//        if (!visited[id]) {
//            visited[id] = true;
//            importances += maps.get(id).importance;
//           for(Integer sub: thisEmployee.subordinates) {
//               importances += dfs(maps, id, visited, importaces);
//           }
//        }
//        return importances;
//    }
//    private void buildEmployeeMap(List<Employee> employees, Map<Integer, Employee> map) {
//        for (Employee employee: employees) {
//            map.put(employee.id, employee);
//        }
//    }
//}