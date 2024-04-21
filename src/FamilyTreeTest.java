import java.util.*;

public class FamilyTreeTest {

    private static Input input;

    public static void main(String[] args) {
        // create input object
        input = new Input();

        // create ancestor and ancestor's partner nodes
        FamilyTreeNode ancestor = new FamilyTreeNode("Ancestor");
        FamilyTreeNode ancestorPartner = new FamilyTreeNode("Ancestor Partner");

        // set up relationship between ancestor and partner
        ancestor.partner = ancestorPartner;
        ancestorPartner.partner = ancestor;

        // main loop for menu
        while (true) {
            // display menu
            System.out.println("\nMenu:");
            System.out.println("1 - Add a child");
            System.out.println("2 - Add a partner");
            System.out.println("3 - Display the whole family");
            System.out.println("4 - Display a specific family member");
            System.out.println("5 - Quit");

            // get user choice
            int choice = input.getInteger("Choose an option: ");

            // handle user choice
            switch (choice) {
                case 1:
                    // add a child
                    try {
                        System.out.println("There's no partner for the ancestor. Please add a partner first.");
                        String childName = input.getString("Enter child's name: ");
                        ancestor.addChild(childName);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    // add a partner
                    String partnerIdentifier = input.getString("Who's the family member whose partner is to be added: ");
                    FamilyTreeNode targetMember = findFamilyMember(ancestor, partnerIdentifier);
                    if (targetMember != null) {
                        try {
                            String partnerName = input.getString("Enter partner's name: ");
                            FamilyTreeNode partner = new FamilyTreeNode(partnerName);
                            targetMember.addPartner(partner);
                            System.out.println("Partner added successfully.");
                        } catch (IllegalStateException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid family member identifier.");
                    }
                    break;


                case 3:
                    // display the whole family
                    System.out.println("\nWhole Family:");
                    displayWholeFamily(ancestor);
                    break;
                case 4:
                    // display a specific family member
                    String memberName = input.getString("Enter the name of the family member to display: ");
                    FamilyTreeNode member = findFamilyMember(ancestor, memberName);
                    if (member != null) {
                        System.out.println("\nFamily Member Details:");
                        System.out.println(member.toString());
                    } else {
                        System.out.println("Error: Family member not found.");
                    }
                    break;
                case 5:
                    // quit
                    System.out.println("Exit");
                    return;
                default:
                    // invalid choice
                    System.out.println("Invalid choice.");
            }
        }
    }

    // method to display the whole family
    private static void displayWholeFamily(FamilyTreeNode node) {
        if (node == null) {
            return;
        }

        // display current node
        System.out.println(node.toString());

        // recursively display children
        FamilyTreeNode currentChild = node.firstChild;
        while (currentChild != null) {
            displayWholeFamily(currentChild);
            currentChild = currentChild.nextSibling;
        }
    }

    // method to find a specific family member
    private static FamilyTreeNode findFamilyMember(FamilyTreeNode node, String name) {
        if (node == null) {
            return null;
        }

        // check if current node matches the name
        if (node.name.equalsIgnoreCase(name)) {
            return node;
        }

        // recursively search in children
        FamilyTreeNode currentChild = node.firstChild;
        while (currentChild != null) {
            FamilyTreeNode result = findFamilyMember(currentChild, name);
            if (result != null) {
                return result;
            }
            currentChild = currentChild.nextSibling;
        }

        // not found
        return null;
    }
}
