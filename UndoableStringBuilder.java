import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A StringBuilder wrapper that supports undo functionality.
 * This implementation follows the Memento Pattern:
 *  Originator: This class (manages the current buffer)
 *  Caretaker:  The 'history' stack (stores state snapshots)
 *  Snapshot:   String objects (frozen copies of the state)
 */
public class UndoableStringBuilder {

    private StringBuilder buffer;
    
    // Caretaker: Manages stack of snapshots
    private final Deque<String> history = new ArrayDeque<>();

    public UndoableStringBuilder() {
        this.buffer = new StringBuilder();
    }

    public UndoableStringBuilder(String initialText) {
        this.buffer = new StringBuilder(initialText);
    }

    /**
     * Originator Logic: Creates "Snapshot" (the String) 
     * and hands it to the Caretaker (the history stack).
     */
    private void saveState() {
        history.push(buffer.toString());
    }

    /**
     * Originator Logic: Restores buffer from the Caretaker last snapshot.
     */
    public UndoableStringBuilder undo() {
        if (!history.isEmpty()) {
            this.buffer = new StringBuilder(history.pop());
        }
        return this;
    }

    // Mutators (Each saves a snapshot before changing) ---

    public UndoableStringBuilder append(String str) {
        saveState();
        buffer.append(str);
        return this;
    }

    public UndoableStringBuilder append(Object obj) {
        saveState();
        buffer.append(obj);
        return this;
    }

    public UndoableStringBuilder delete(int start, int end) {
        saveState();
        buffer.delete(start, end);
        return this;
    }

    public UndoableStringBuilder insert(int offset, String str) {
        saveState();
        buffer.insert(offset, str);
        return this;
    }

    public UndoableStringBuilder replace(int start, int end, String str) {
        saveState();
        buffer.replace(start, end, str);
        return this;
    }

    public UndoableStringBuilder reverse() {
        saveState();
        buffer.reverse();
        return this;
    }

    // --- Accessors ---

    public int length() {
        return buffer.length();
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    public static void main(String[] args) {
        UndoableStringBuilder usb = new UndoableStringBuilder("Version 1.03");
        
        usb.append(" -> Version 2.07");
        System.out.println("Current: " + usb);
        
        usb.undo();
        System.out.println("After Undo: " + usb);
    }
}