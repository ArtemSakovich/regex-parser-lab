package grsu.regexparser.core.token;

public class Token {
    private String tokenString;

    public Token(String tokenString) {
        this.tokenString = tokenString;
    }



    //--------------------------------------------------
    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }
}
