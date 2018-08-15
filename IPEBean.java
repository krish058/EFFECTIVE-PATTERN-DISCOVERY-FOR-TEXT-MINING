package effective;
public class IPEBean
{
	String prune_patterns;
	String selected_patterns;
	String term;
public void setTerm(String term){
	this.term=term;
}
public String getTerm(){
	return term;
}
public void setPrune(String prune_patterns){
	this.prune_patterns=prune_patterns;
}
public String getPrune(){
	return prune_patterns;
}
public void setSelected(String selected_patterns){
	this.selected_patterns=selected_patterns;
}
public String getSelected(){
	return selected_patterns;
}
}