//Step 1
// class to represent a node in the family tree
class FamilyTreeNode {
    // attributes
    private static int nextId = 1; // static variable to keep track of the next available ID
    private int id; // unique identifier for the family member
    String name; // name of the family member
    FamilyTreeNode partner; // reference to the partner
    FamilyTreeNode nextSibling; // reference to the next sibling
    FamilyTreeNode firstChild; // reference to the first child

    // constructor
    public FamilyTreeNode(String name) {
        this.name = name; // set the name
        this.partner = null; // initialize partner as null
        this.nextSibling = null; // initialize nextSibling as null
        this.firstChild = null; // initialize firstChild as null
        this.id = nextId++; // assign the next available ID and increment for the next node
    }
    // getter for the ID
    public int getId() {
        return this.id;
    }
    // method to add a child with sibling name unique and not case sensitive
    public void addChild(String childName) {
        // Check if childName already exists as a sibling
        if (isSiblingExists(childName)) {
            throw new IllegalArgumentException("Sibling with the same name already exists.");
        }

        // Convert childName to lowercase for case-insensitive comparison
        String lowercaseChildName = childName.toLowerCase();

        // create a new child node
        FamilyTreeNode newChild = new FamilyTreeNode(childName);
        // if there are no existing children, set this child as the firstChild
        if (this.firstChild == null) {
            this.firstChild = newChild;
        } else {
            // else, traverse to the end of the sibling list and add the new child there
            FamilyTreeNode currentChild = this.firstChild;
            while (currentChild.nextSibling != null) {
                // Convert current child's name to lowercase for comparison
                String lowercaseCurrentChildName = currentChild.name.toLowerCase();
                // Compare lowercase names
                if (lowercaseCurrentChildName.equals(lowercaseChildName)) {
                    throw new IllegalArgumentException("Sibling with the same name already exists.");
                }
                currentChild = currentChild.nextSibling;
            }
            currentChild.nextSibling = newChild;
        }
    }

    public void addPartner(FamilyTreeNode partner) {
        if (this.partner != null) {
            throw new IllegalStateException("This family member already has a partner.");
        }
        if (partner.partner != null) {
            throw new IllegalStateException("The specified partner already has a partner.");
        }
        this.partner = partner;
        partner.partner = this;
    }

    // method to check if a sibling with the same name exists
    private boolean isSiblingExists(String name) {
        String lowercaseName = name.toLowerCase();
        FamilyTreeNode currentChild = this.firstChild;
        while (currentChild != null) {
            String lowercaseCurrentChildName = currentChild.name.toLowerCase();
            if (lowercaseCurrentChildName.equals(lowercaseName)) {
                return true;
            }
            currentChild = currentChild.nextSibling;
        }
        return false;
    }

    // method to find a specific family member by ID
    private static FamilyTreeNode getFamilyMemberById(FamilyTreeNode node, int id) {
        if (node == null) {
            return null;
        }

        // check if current node matches the ID
        if (node.getId() == id) {
            return node;
        }

        // recursively search in children
        FamilyTreeNode currentChild = node.firstChild;
        while (currentChild != null) {
            FamilyTreeNode result = getFamilyMemberById(currentChild, id);
            if (result != null) {
                return result;
            }
            currentChild = currentChild.nextSibling;
        }

        // not found
        return null;
    }
    public FamilyTreeNode getPartner() {
        return this.partner;
    }
    // method to convert the node to a string
    // method to convert the node to a string
    public String toString() {
        String result = this.name; // initialize result string with name
        // if there is a partner, add partner info to result
        if (getPartner() != null) {
            result += " partner " + getPartner().name + "\n";
        } else {
            result += "\n"; // if no partner, add new line
        }
        // if there are children, add their names to result
        if (firstChild != null) {
            FamilyTreeNode currentChild = firstChild;
            while (currentChild != null) {
                result += " " + currentChild.name + "\n";
                currentChild = currentChild.nextSibling;
            }
        }
        return result; // return the result string
    }

}