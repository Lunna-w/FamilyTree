public class FamilyTreeTest {
    private static Input input;

    public static void main(String[] args) {
        // create input object
        input = new Input();

        // create ancestor and ancestor's partner nodes
        FamilyTreeNode ancestor = new FamilyTreeNode("");
        FamilyTreeNode ancestorPartner = new FamilyTreeNode("");

        // set up relationship between ancestor and partner
        ancestor.partner = ancestorPartner;
        ancestorPartner.partner = ancestor;

        // main loop for menu
        while (true) {
            // display menu
            System.out.println("Menu:");
            System.out.println("1 - Add a child");
            System.out.println("2 - Display the family tree");

            // get user choice
            int choice = input.getInteger("Choose an option: ");

            // handle user choice
            switch (choice) {
                case 1:
                    // add a child
                    try {
                        String childName = input.getString("Enter child's name: ");
                        ancestor.addChild(childName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    // display family tree
                    System.out.println(ancestor.toString());
                    break;
                default:
                    // invalid choice
                    System.out.println("Invalid choice.");
            }
        }
    }
}