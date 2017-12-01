package ca.ece.ubc.cpen221.mp5;

import java.util.Set;
import java.util.function.ToDoubleBiFunction;

public abstract class AbstractMP5Db<T> implements MP5Db<T>{

    public abstract Set<T> getMatches(String queryString);

    public abstract String kMeansClusters_json(int k);

    public abstract ToDoubleBiFunction<MP5Db<T>, String> getPredictorFunction(String user);
}
