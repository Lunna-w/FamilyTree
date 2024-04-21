//Step 1
// class to represent a node in the family tree
class FamilyTreeNode {
    // attributes
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
    }

    // method to add a child
    public void addChild(String childName) {
        // create a new child node
        FamilyTreeNode newChild = new FamilyTreeNode(childName);
        // if there are no existing children, set this child as the firstChild
        if (this.firstChild == null) {
            this.firstChild = newChild;
        } else {
            // else, traverse to the end of the sibling list and add the new child there
            FamilyTreeNode currentChild = this.firstChild;
            while (currentChild.nextSibling != null) {
                currentChild = currentChild.nextSibling;
            }
            currentChild.nextSibling = newChild;
        }
    }


    // method to convert the node to a string
    public String toString() {
        String result = " "; // initialize result string
        // if there is a partner, add partner info to result
        if (partner != null) {
            result += partner.name + " partner " + name + "\n";
        }
        result += "\n"; // add new line
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