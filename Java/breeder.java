import java.util.ArrayList;
import java.util.List;
// ADDED FUNCTIONS
// database_engimon: findByElement

public class breeder {
    private String[] list_of_elements = {"Fire", "Water", "Electric", "Ground", "Ice"};
    private double[][] element_advantage = {{1, 0, 1, 0.5, 2}, {2, 1, 0, 1, 1}, {1, 2, 1, 0, 1.5}, {1.5, 1, 2, 1, 0}, {0, 1, 0.5, 2, 1}};

    // Generates a random number between 0 and max - 1
    public int rng (int max) {
        int num = (int) Math.random() * (max);
        return num;
    }

    // Main breeder function
    public static engimon breed (String name, engimon parent1, engimon parent2) throws Exception {
        if (parent1.getLevel() > 30 && parent2.getLevel() > 30) {
            breeder a;
            
            String sp = a.inheritSpecies(parent1, parent2);
            engimon child = new engimon(name, parent1, parent2, sp, 1, 0);
            // !!! Engimon is abstract, wait for subclass implementation !!!
    
            a.inheritSkill(child, parent1, parent2);
    
            parent1.setLevel(parent1.getLevel() - 30);
            parent2.setLevel(parent2.getLevel() - 30);
    
            System.out.println("Breeding berhasil dilakukan\n");
            child.showAttributes();
            return child;
        } else {
            throw new Exception("Error, salah satu atau kedua parent memiliki level di bawah 31");
        }
    }

    public String inheritSpecies(engimon parent1, engimon parent2) {
        // Jika parent memiliki species sama, langsung turunkan species anak
        if (parent1.getSpecies().equals(parent2.getSpecies())) {
            return parent1.getSpecies();
        // Jika parent memiliki species berbeda, namun elemen sama, randomize species anak
        } else if (sameElements(parent1, parent2)) {
            return getRandom(parent1.getSpecies(), parent2.getSpecies());
        } else {
            // Get one element from each parent
            String parent_elmt1 = getParentElement(parent1);
            String parent_elmt2 = getParentElement(parent2);

            if (parent_elmt1.equals(parent_elmt2)) {
                // Case 1: both parents have same selected element, child inherits randomized species
                return getRandom(parent1.getSpecies(), parent2.getSpecies());
            } else {
                // Case 2: parents have different selected elements, get higher element advantage species
                double elmt_adv = getElementAdvantage(parent_elmt1, parent_elmt2) - getElementAdvantage(parent_elmt2, parent_elmt1);
                if (elmt_adv > 0) {
                    return parent1.getSpecies();
                } else if (elmt_adv < 0) {
                    return parent2.getSpecies();
                } else {
                    // If elements have no edvantage over the other, child inherits both
                    database_engimon db = new database_engimon();
                    return db.findByElement(parent_elmt1, parent_elmt2).speciesName;
                }
            }
        }
    }

    public void inheritSkill(engimon child, engimon parent1, engimon parent2) {
        // Implement
        int iter1[] = {0, 0, 0, 0};
        int iter2[] = {0, 0, 0, 0};
        int max, idxmax;

        int i = 1;
        Boolean end = false; // True if no more skills can be learned

        while (i < 4 && !end) {
            max = -1;
            idxmax = -1;

            // Find skill with max mastery level
            for (int j = 1; j < 4; j++) {
                if (iter1[j] != 1) {
                    if (!isLearnable(parent1.getMove(j), child)) {
                        iter1[j] = 1;
                    } else if (parent1.getMove(j).getMasteryLv() > max) {
                        max = parent1.getMove(j).getMasteryLv();
                        idxmax = j;
                    }
                }
                if (iter2[j] != 1) {
                    if (!isLearnable(parent2.getMove(j), child)) {
                        iter2[j] = 1;
                    } else if (parent2.getMove(j).getMasteryLv() > max) {
                        max = parent2.getMove(j).getMasteryLv();
                        idxmax = j + 4;
                    }
                }
            }

            if (idxmax == -1) {
                // No skills to learn, exit function
                end = true;
            } else {
                // There are still skills to be learned
                int mastery = -1;
                skill chosen_skill = null;
                // Check 1st parent's skills
                if (idxmax < 4) {
                    iter1[idxmax] = 1;
                    chosen_skill = parent1.getMove(idxmax);
                    mastery = chosen_skill.getMasteryLv();

                    // Mark skills that both parents have
                    for (int j = 1; j < 4; j++) {
                        if (chosen_skill.getSkillName() == parent2.getMove(j).getSkillName()) {
                            iter2[j] = 1;
                            if (chosen_skill.getMasteryLv() == parent2.getMove(j).getMasteryLv()) {
                                // Learn skill, chosen mastery level + 1
                                mastery = mastery + 1;
                            }
                            break;
                        }
                    }
                // Check 2nd parent's skills
                } else {
                    iter2[idxmax - 4] = 1;
                    chosen_skill = parent2.getMove(idxmax - 4);
                    mastery = chosen_skill.getMasteryLv();
                    // chosen_skill.printAll();

                    // Mark skills that both parents have
                    for (int j = 0; j < 4; j++) {
                        if (chosen_skill.getSkillName() == parent1.getMove(j).getSkillName()) {
                            iter1[j] = 1;
                            break;
                        }
                    }
                }
                if (mastery != -1 && chosen_skill != null)
                child.setSkill(chosen_skill, i, mastery);
                // cout << chosen_skill.getSkillName() << " has been learned with mastery = " << mastery << endl << endl;
            }
            i++;
        }
    }

    public Boolean sameElements(engimon a, engimon b) {
        if (a.getElmt1().equals(b.getElmt2()) && a.getElmt1().equals(b.getElmt2())) {
            return true;
        } else {
            return false;
        }
    }

    public String getParentElement(engimon x) {
        if (x.getElmt2().equals(engimon.name_null)) {
            return x.getElmt1();
        } else {
            return getRandom(x.getElmt1(), x.getElmt2());
        }
    }

    public double getElementAdvantage(String elmt1, String elmt2) {
        int i = 0;
        int idx1 = -1;
        int idx2 = -1;
        while (idx1 == -1 || idx2 == -1) {
            if (list_of_elements[i].equals(elmt1)) {
                idx1 = i;
            } else if (list_of_elements[i].equals(elmt2)) {
                idx2 = i;
            }
            i++;
        }
        return element_advantage[idx1][idx2];
    }

    public String getRandom(String a, String b) {
        int chance = rng(2);
        if (chance == 0) {
            return a;
        } else {
            return b;
        }
    }

    public Boolean isLearnable(skill x, engimon y) {
        List<String> elements = x.getElements();
        for (String e : elements) {
            if (e.equals(y.getElmt1()) || e.equals(y.getElmt2())) {
                return true;
            }
        }
        return false;
    }
}