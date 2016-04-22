package automata.automaton;

public interface State<T> {
	public boolean initial();

	public boolean terminal();
	
	public T getName();
}