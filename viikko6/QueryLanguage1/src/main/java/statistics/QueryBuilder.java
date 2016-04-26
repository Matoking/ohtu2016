package statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

/**
 *
 * @author matoking
 */
public class QueryBuilder {
    private List<Matcher> matchers;
    private String type;
    
    
    public QueryBuilder() {
        this.matchers = new ArrayList<Matcher>();
        this.type = "and";
    }
    
    public Matcher build() {
        switch(this.type) {
            case "and":
                Matcher and = new And(this.matchers.toArray(new Matcher[]{}));
                return and;
                
            case "or":
                Matcher or = new Or(this.matchers.toArray(new Matcher[]{}));
                return or;
        }
        
        return null;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matchers.add(new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matchers.add(new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matchers = Arrays.asList(matchers);
        this.type = "or";
        
        return this;
        
    }
}
