package effective;
public class DPBean
{
	String term,paragraph;
	double support;
public void setSupport(double support){
	this.support = support;
}
public double getSupport(){
	return support;
}
public void setTerm(String term){
	this.term=term;
}
public String getTerm(){
	return term;
}
public void setParagraph(String paragraph){
	this.paragraph=paragraph;
}
public String getParagraph(){
	return paragraph;
}
}