package grsu.regexparser.core.wrapper;

public class StrIteratorWrapper {
    char[] regexString;
    int index;

    public StrIteratorWrapper(char[] regexString) {
        this.regexString = regexString;
    }

    public StrIteratorWrapper(char[] regexString, int index) {
        this.regexString = regexString;
        this.index = index;
    }

    public char getCurrentSymbol(){
        return regexString[index];
    }

    public void incrementIndex(){
        index++;
    }

    public boolean indexLessThanRegexStrLength(){
        return index < regexString.length;
    }
    public boolean currentCharIsLast(){
        return index==regexString.length-1;
    }

    //-------------------------------------------------
    public char[] getRegexString() {
        return regexString;
    }

    public void setRegexString(char[] regexString) {
        this.regexString = regexString;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
