public class element_advantage {
    private String[] list_of_elements = {"Fire", "Water", "Electric", "Ground", "Ice"};
    private double[][] element_advantage = {{1, 0, 1, 0.5, 2}, {2, 1, 0, 1, 1}, {1, 2, 1, 0, 1.5}, {1.5, 1, 2, 1, 0}, {0, 1, 0.5, 2, 1}};

    public static double getElementAdvantage(String elmt1, String elmt2) {
        int i = 0;
        int idx1 = -1;
        int idx2 = -1;
        element_advantage a = new element_advantage();
        while (idx1 == -1 || idx2 == -1) {
            if (a.list_of_elements[i].equals(elmt1)) {
                idx1 = i;
            }
            if (a.list_of_elements[i].equals(elmt2)) {
                idx2 = i;
            }
            i++;
        }
        return a.element_advantage[idx1][idx2];
    }
}
