// import java.util.List;

public class breeder {
    // Generates a random number between 0 and max - 1
    public int rng (int max) {
        int num = (int) (Math.random() * (max));
        return num;
    }

    // Main breeder function
    public static player_engimon breed (String name, player_engimon parent1, player_engimon parent2) throws Exception {
        if (parent1.getLevel() > 3 && parent2.getLevel() > 3) {
            breeder a = new breeder();
            
            String sp = a.inheritSpecies(parent1, parent2);
            player_engimon child = new player_engimon(name, parent1, parent2, sp, 1, 0);
    
            // !!! Skill bawaan kalau parent1 dan parent2 speciesnya sama? !!!
            a.inheritSkill(child, parent1, parent2);
    
            parent1.setLevel(parent1.getLevel() - 3);
            parent2.setLevel(parent2.getLevel() - 3);
    
            System.out.println("\nBreeding berhasil dilakukan\n");
            child.showAttributes();
            return child;
        } else {
            throw new Exception("Error, salah satu atau kedua parent memiliki level di bawah 4");
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
            database_engimon db = new database_engimon();

            // System.out.println(parent_elmt1 + " " + parent_elmt2);

            if (parent_elmt1.equals(parent_elmt2)) {
                // Case 1: both parents have same selected element, return single element child
                return db.findByElement(parent_elmt1, null).speciesName;
            } else {
                // Case 2: parents have different selected elements, get higher element advantage species
                double elmt_adv = element_advantage.getElementAdvantage(parent_elmt1, parent_elmt2) - element_advantage.getElementAdvantage(parent_elmt2, parent_elmt1);
                if (elmt_adv > 0) {
                    return db.findByElement(parent_elmt1, null).speciesName;
                } else if (elmt_adv < 0) {
                    return db.findByElement(parent_elmt2, null).speciesName;
                } else {
                    // If elements have no edvantage over the other, child inherits both
                    return db.findByElement(parent_elmt1, parent_elmt2).speciesName;
                }
            }
        }
    }

    public void inheritSkill(engimon child, engimon parent1, engimon parent2) {
        int iter1[] = {0, 0, 0, 0};
        int iter2[] = {0, 0, 0, 0};
        int max, idxmax;

        // Mark unique skills
        if (parent1.getSpecies() == child.getSpecies()) {
            iter1[0] = 1;
        }
        if (parent2.getSpecies() == child.getSpecies()) {
            iter2[0] = 1;
        }

        // Mark skills that are null
        for (int i = 1; i < 4; i++) {
            if (parent1.getMove(i) == null) {
                iter1[i] = 1;
            }
            if (parent2.getMove(i) == null) {
                iter2[i] = 1;
            }
        }
        
        // for (int a : iter1) {
        //     System.out.print(a);
        // }
        // System.out.println();
        // for (int a : iter2) {
        //     System.out.print(a);
        // }

        int i = 1;
        Boolean end = false; // True if no more skills can be learned

        while (i < 4 && !end) {
            max = -1;
            idxmax = -1;

            // Find skill with max mastery level
            for (int j = 0; j < 4; j++) {
                if (iter1[j] != 1) {
                    // if (!isLearnable(parent1.getMove(j), child)) {
                    //     iter1[j] = 1;
                    // }
                    if (parent1.getMove(j).getMasteryLv() > max) {
                        max = parent1.getMove(j).getMasteryLv();
                        idxmax = j;
                    }
                }
                if (iter2[j] != 1) {
                    // if (!isLearnable(parent2.getMove(j), child)) {
                    //     iter2[j] = 1;
                    // }
                    if (parent2.getMove(j).getMasteryLv() > max) {
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
                        if(iter2[i] != 1) {
                            if (chosen_skill.getSkillName() == parent2.getMove(j).getSkillName()) {
                                iter2[j] = 1;
                                if (chosen_skill.getMasteryLv() == parent2.getMove(j).getMasteryLv() && mastery < 3) {
                                    // Learn skill, chosen mastery level + 1
                                    mastery = mastery + 1;
                                }
                                break;
                            }
                        }
                    }
                // Check 2nd parent's skills
                } else {
                    iter2[idxmax - 4] = 1;
                    chosen_skill = parent2.getMove(idxmax - 4);
                    mastery = chosen_skill.getMasteryLv();

                    // Mark skills that both parents have
                    for (int j = 0; j < 4; j++) {
                        if (iter1[i] != 1) {
                            if (chosen_skill.getSkillName() == parent1.getMove(j).getSkillName()) {
                                iter1[j] = 1;
                                break;
                            }
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
        if (a.getElmt1().equals(b.getElmt1())) {
            if (a.getElmt2() != null && b.getElmt2() != null) {
                return (a.getElmt2().equals(b.getElmt2()));
            }
            if (a.getElmt2() == null && b.getElmt2() == null) {
                return true;
            }
        }
        return false;
    }

    public String getParentElement(engimon x) {
        if (x.getElmt2() == null) {
            return x.getElmt1();
        } else {
            return getRandom(x.getElmt1(), x.getElmt2());
        }
    }

    public String getRandom(String a, String b) {
        int chance = rng(2);
        if (chance == 0) {
            return a;
        } else {
            return b;
        }
    }

    // public Boolean isLearnable(skill x, engimon y) {
    //     List<String> elements = x.getElements();
    //     for (String e : elements) {
    //         if (e.equals(y.getElmt1())) return true;
    //         if (y.getElmt2() != null) {
    //             if (e.equals(y.getElmt2())) return true;
    //         }
    //     }
    //     return false;
    // }
}