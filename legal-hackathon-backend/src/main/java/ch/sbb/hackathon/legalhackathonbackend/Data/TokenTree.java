package ch.sbb.hackathon.legalhackathonbackend.Data;

import com.google.cloud.language.v1beta2.DependencyEdge;
import com.google.cloud.language.v1beta2.Token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TokenTree {
    List<ChainedToken> chainedTokenList;
    List<Token> tokens;

    public TokenTree(List<Token> tokens) {
        this.tokens = tokens;
        this.chainedTokenList = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            this.chainedTokenList.add(new ChainedToken(tokens.get(i), i));
        }
        for (ChainedToken chainedToken : chainedTokenList) {
            int headTokenIndex = chainedToken.getToken().getDependencyEdge().getHeadTokenIndex();
            chainedToken.registerParent(chainedTokenList.get(headTokenIndex));
        }
    }

    public boolean islinkedtonegation(Token token) {
        return islinkedtonegation(root(chainedTokenList.get(tokens.indexOf(token))));
    }

    private ChainedToken root(ChainedToken token) {
        if (token.getIndex() == token.getParent().getIndex()) {
            return token;
        }
        return root(token.getParent());
    }

    private boolean islinkedtonegation(ChainedToken token) {
        if (token.getToken().getDependencyEdge().getLabel() == DependencyEdge.Label.NEG) {
            return true;
        }

        for (ChainedToken childtoken : token.getChilds()) {
            if (islinkedtonegation(childtoken)) {
                return true;
            }
        }
        return false;
    }
}

class ChainedToken {
    private Token token;
    private ChainedToken parent;
    private Set<ChainedToken> childs;
    private int index;

    public ChainedToken(Token token, int index) {
        this.token = token;
        this.parent = null;
        this.childs = new HashSet<>();
        this.index = index;
    }

    public Token getToken() {
        return token;
    }

    public void registerParent(ChainedToken parent) {
        this.parent = parent;
        parent.registerChild(this);
    }

    public void registerChild(ChainedToken child) {
        if (child != this) {
            this.childs.add(child);
        }
    }

    public Set<ChainedToken> getChilds() {
        return childs;
    }

    public int getIndex() {
        return index;
    }

    public ChainedToken getParent() {
        return parent;
    }
}

