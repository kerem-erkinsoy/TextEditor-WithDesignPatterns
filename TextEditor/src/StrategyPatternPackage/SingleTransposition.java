package StrategyPatternPackage;

public class SingleTransposition {
    
    /***
     * Length checking. If the length of the compared words is not equal, 
     * no operations are performed for single transposition.
     * @param str1 First string to compare
     * @param str2 Second string to compare
     * @return true or false
     */
    public boolean lengthCheck(String str1, String str2){
        return (str1.length()==str2.length());
    }
    
    /***
     * In this method, it is checked whether the letters of the checked word
     * match the word compared in the dictionary.
     * @param str1 First string to compare
     * @param str2 Second string to compare
     * @return true if there is a match, false otherwise
     */
    
    public boolean letterMatch(String str1, String str2) {
        int sayac=0;
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str1.length(); j++) {
                if(str1.substring(i,i+1).equals(str2.substring(j, j+1))) {
                    // harf eşleştiğinde sayacı +1 arttırıyoruz.
                    // eğer en sonunda sayac ilk kelimenin uzunluğuna eşit çıkarsa
                    // iki kelimenin bütün harfleri eşleşiyor demektir.
                    sayac++;
                    break;
                }
            }
        }
        return str1.length()==sayac;
    }
    
    /***
     * Single transposition check will now be performed
     * if letters are matched and words are of equal length. 
     * @param str1 First string to compare
     * @param str2 Second string to compare
     * @return 
     */
    public boolean findSimilar(String str1, String str2){
        if(lengthCheck(str1, str2) && letterMatch(str1, str2) ){
            for (int i = 0; i < str1.length()-1; i++) {
                /*If the letters do not match and the next letter of the 
                input word is equal to the current letter of the word in the dictionary, 
                it returns true and this is double-checked.
                If this is not the case, it returns false.*/
                if(!str1.substring(i,i+1).equals(str2.substring(i,i+1))) {
                    if(str1.substring(i, i+1).equals(str2.substring(i+1, i+2)) &&
                            str2.substring(i, i+1).equals(str1.substring(i+1,i+2))) {
                        if(str1.substring(i+2).equals(str2.substring(i+2))){
                            // Words match and return true. 
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        // It returns false directly if none of the above conditions are met. 
        return false;
    }
}