package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{

    String reversedSequence;

    public ReversedSequence(String sequence) {
        this.reversedSequence = new StringBuilder(sequence).reverse().toString();
    }

    @Override
    public int length() {
        return reversedSequence.length();
    }

    @Override
    public char charAt(int index) {
        return reversedSequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedSequence.substring(start, end);
    }

    @Override
    public String toString() {
        return reversedSequence;
    }
}
// END
