import java.io.*;
class QuestionLoader{
  protected FileReader in;
  protected int eofs;
  QuestionLoader (){
    eofs=0;  
    System.out.println("Constructoring Question loader!");
    try{
      System.out.print("Making file reader...");
      in = new FileReader("CompSciQuestions.txt");
      
      System.out.println("...Succsess!");
    }
    catch(IOException e) {
      System.out.println("Question file not found");
    }
  }
  //Output the numerical value of the first 2kb of a file
  public void pukeFile (){
    try{
      for (int i=0;i<2048;i++)
        System.out.print(in.read()+", ");
    }catch(IOException e){}
  }
  //boolean evaluates if a gotten character indicates the end of a file
  private void checkEOF(int read) throws IOException{
    eofs++;
    if (read==-1){//if it is the end of the file
      System.out.println("IOException"+eofs);
      throw new IOException();}//throw an exception to be caught in the queston deck
  }
  public Question read() throws IOException{
    //From the way the question is built they must start defined as null
    String question="";
    String category="";
    String value="";
    int points=0;
    String []answers=new String [4];
    for (int i=0;i<4;i++){
      answers[i]="";}
    int read;
    
    //Build value
    //System.out.println("About to load Question");
    while ((read=in.read())!=13&&read!=10){//Runs until newline
      checkEOF(read);  
      value+=(char)read;//Builds the string char by char
      //System.out.println(value);
    }
          System.out.println("Parseing"+value);
    points=Integer.parseInt(value);
    //System.out.println("Points Loaded");
    //Build the category
    while ((read=in.read())!=13&&read!=10){//Runs until newline
      checkEOF(read);  
      category+=(char)read;//Builds the string char by char
    }
    System.out.println(category);
    //System.out.println("Category Loaded");
    //Build the question
    while ((read=in.read())!=13&&read!=10){//Runs until newline
      checkEOF(read);  
      question+=(char)read;//Builds the string char by char
    }
    System.out.println(question);
    //System.out.println("Question Loaded");
    //Build all 4 answers
    for (int i=0;i<4;i++){//For each answer
      while ((read=in.read())!=13&&read!=10){//Builds the string char by char
        checkEOF(read);  
        answers[i]+=(char)read;
        // System.out.println(i+"answer Loaded");
      }
      System.out.println(answers[i]);
    }
    read=in.read();
    //System.out.println("Question Loaded!");
    checkEOF(read);  
    
    //System.out.println("Creating question!");
    Question q= new Question(points,category,question,answers,0);//construct a question object
    
    return q;//Return it
  }
  
  
  
}