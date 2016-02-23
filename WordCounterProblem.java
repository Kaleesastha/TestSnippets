public class WordCounterProblem{
    
   /*
    * This method return word count without using regular expression
    */
	public static void main (String args[]){
		String word = args[0];
		//String word = "Sample Test for String";
		System.err.println("no. of words in : "+wordcount(word));
	}
    public static int wordcount(String word) {
        if (word == null || word.isEmpty()) {
            return 0;
        }
        int count = 0;
        char ch[] = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            ch[i] = word.charAt(i);
            if (((i > 0) && (ch[i] != ' ') && (ch[i - 1] == ' ')) || ((ch[0] != ' ') && (i == 0))) {
                count++;
            }
        }
        return count;
    }
}
