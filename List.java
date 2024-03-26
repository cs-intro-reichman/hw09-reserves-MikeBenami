/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

        // Points to the first node in this list
        private Node first;

        // The number of elements in this list
        private int size;
    
        /** Constructs an empty list. */
        public List() {
            first = null;
            size = 0;
        }
    
        /** Returns the number of elements in this list. */
        public int getSize() {
            return size;
        }
    
        /** Returns the first element in the list */
        public CharData getFirst() {
            return first.cp;
        }
    
        /**
         * GIVE Adds a CharData object with the given character to the beginning of this
         * list.
         */
        public void addFirst(char chr) {
            CharData c = new CharData(chr);
            Node node = new Node(c, first);
            first=node; size++;
        }
    
        /** GIVE Textual representation of this list. */
        public String toString() {
            if (size==0) return "()";
    
            StringBuilder sb = new StringBuilder("(");
            Node curr=first;
            while (curr!=null) {
                sb.append(curr.toString());
                sb.append(" ");
                curr=curr.next;
            }
            sb.replace(sb.length()-1,sb.length(),")");
            return sb.toString();
        }
    
        /**
         * Returns the index of the first CharData object in this list
         * that has the same chr value as the given char,
         * or -1 if there is no such object in this list.
         */
        public int indexOf(char chr) {
            Node curr=first;
            int i=0;
            while (curr!=null) {
                if (curr.cp.equals(chr)) return i;
                i++;
                curr=curr.next;
            }
            return -1;
        }
    
        /**
         * If the given character exists in one of the CharData objects in this list,
         * increments its counter. Otherwise, adds a new CharData object with the
         * given chr to the beginning of this list.
         */
        public void update(char chr) {
            if (indexOf(chr)==-1) addFirst(chr);
            else get(indexOf(chr)).count++;
        }
    
        /**
         * GIVE If the given character exists in one of the CharData objects
         * in this list, removes this CharData object from the list and returns
         * true. Otherwise, returns false.
         */
        public boolean remove(char chr) {
            if (indexOf(chr)==-1) return false;
            Node prev=null;
            Node curr=first;
            while (!curr.cp.equals(chr)&&(curr!=null)) {
                prev=curr;
                curr=curr.next;
            }
            if (prev==null) first=first.next;
            else prev.next=curr.next;
            size--;
            return true;
        }
    
        /**
         * Returns the CharData object at the specified index in this list.
         * If the index is negative or is greater than the size of this list,
         * throws an IndexOutOfBoundsException.
         */
        public CharData get(int index) {
            Node curr=first;
            if (index>size||index<0)
                throw new IndexOutOfBoundsException("index is out of bounds");
            int j=0;
            while (j<index&&curr!=null) {
                curr=curr.next;
                j++;
            }
            return curr.cp;
        }
    
    
        /**
         * Returns an array of CharData objects, containing all the CharData objects in
         * this list.
         */
        public CharData[] toArray() {
            CharData[] charData = new CharData[size];
            Node curr = first;
            int j=0;
            while (curr!=null) {
                charData[j++]=curr.cp;
                curr=curr.next;
            }
            return charData;
        }
    
        /**
         * Returns an iterator over the elements in this list, starting at the given
         * index.
         */
        public ListIterator listIterator(int index) {
            if (size==0) return null;
            Node curr=first;
            int j=0;
            while (j<index) {
                curr=curr.next;
                j++;
            }
            return new ListIterator(curr);
        }
    }
